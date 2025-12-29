package stocktrading.command;

import stocktrading.facade.BolsaFacade;
import stocktrading.model.Carteira;
import stocktrading.model.Cotacao;
import stocktrading.model.Ordem;

public class CompraCommand implements CommandOperacao {
    private final BolsaFacade bolsaFacade;
    private final Ordem ordem;
    private Carteira carteira;
    private double saldoAnterior;
    private int quantidadeAnterior;
    private double precoMedioAnterior;
    private double valorTotal;
    private boolean executado;

    public CompraCommand(BolsaFacade bolsaFacade, Ordem ordem) {
        this.bolsaFacade = bolsaFacade;
        this.ordem = ordem;
        this.executado = false;
    }

    @Override
    public void executar() {
        if (executado) {
            System.out.println("Comando já foi executado anteriormente.");
            return;
        }

        ordem.setTipoOperacao("COMPRA");
        carteira = bolsaFacade.getCarteiraPadrao();
        saldoAnterior = carteira.getSaldoDisponivel();

        // Verifica se já existe posição
        quantidadeAnterior = 0;
        precoMedioAnterior = 0.0;
        for (stocktrading.model.AcaoPosicao posicao : carteira.getPosicoes()) {
            if (posicao.getAcao().getCodigo().equals(ordem.getCodigoAcao())) {
                quantidadeAnterior = posicao.getQuantidade();
                precoMedioAnterior = posicao.getPrecoMedio();
                break;
            }
        }

        // Calcula valor total antes da execução
        Cotacao cotacao = bolsaFacade.buscarCotacao(ordem.getCodigoAcao());
        double precoExecucao = cotacao.getPreco();
        if (ordem.getPrecoLimite() > 0.0 && ordem.getPrecoLimite() < precoExecucao) {
            precoExecucao = ordem.getPrecoLimite();
        }
        valorTotal = precoExecucao * ordem.getQuantidade();

        // Executa a compra
        bolsaFacade.executarCompra(ordem);
        executado = true;
        System.out.println("✓ CompraCommand executado: " + ordem.getQuantidade() + "x " + ordem.getCodigoAcao());
    }

    @Override
    public void desfazer() {
        if (!executado) {
            System.out.println("Comando não foi executado ainda.");
            return;
        }

        // Restaura saldo
        carteira.setSaldoDisponivel(saldoAnterior);

        // Restaura posição
        if (quantidadeAnterior == 0) {
            // Remove a posição se não existia antes
            carteira.getPosicoes().removeIf(pos -> pos.getAcao().getCodigo().equals(ordem.getCodigoAcao()));
        } else {
            // Restaura quantidade e preço médio anteriores
            for (stocktrading.model.AcaoPosicao posicao : carteira.getPosicoes()) {
                if (posicao.getAcao().getCodigo().equals(ordem.getCodigoAcao())) {
                    posicao.setQuantidade(quantidadeAnterior);
                    posicao.setPrecoMedio(precoMedioAnterior);
                    break;
                }
            }
        }

        executado = false;
        System.out.println("✓ CompraCommand desfeito: " + ordem.getQuantidade() + "x " + ordem.getCodigoAcao());
    }

    @Override
    public String getDescricao() {
        return "Compra de " + ordem.getQuantidade() + " ações de " + ordem.getCodigoAcao();
    }
}

