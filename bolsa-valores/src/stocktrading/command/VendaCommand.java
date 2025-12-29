package stocktrading.command;

import stocktrading.facade.BolsaFacade;
import stocktrading.model.Carteira;
import stocktrading.model.Ordem;

public class VendaCommand implements CommandOperacao {
    private final BolsaFacade bolsaFacade;
    private final Ordem ordem;
    private Carteira carteira;
    private double saldoAnterior;
    private int quantidadeAnterior;
    private double precoMedioAnterior;
    private double valorTotal;
    private boolean executado;

    public VendaCommand(BolsaFacade bolsaFacade, Ordem ordem) {
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

        ordem.setTipoOperacao("VENDA");
        carteira = bolsaFacade.getCarteiraPadrao();
        saldoAnterior = carteira.getSaldoDisponivel();

        // Salva estado anterior da posição
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
        stocktrading.model.Cotacao cotacao = bolsaFacade.buscarCotacao(ordem.getCodigoAcao());
        double precoExecucao = cotacao.getPreco();
        if (ordem.getPrecoLimite() > 0.0 && ordem.getPrecoLimite() > precoExecucao) {
            precoExecucao = ordem.getPrecoLimite();
        }
        valorTotal = precoExecucao * ordem.getQuantidade();

        // Executa a venda
        bolsaFacade.executarVenda(ordem);
        executado = true;
        System.out.println("✓ VendaCommand executado: " + ordem.getQuantidade() + "x " + ordem.getCodigoAcao());
    }

    @Override
    public void desfazer() {
        if (!executado) {
            System.out.println("Comando não foi executado ainda.");
            return;
        }

        // Restaura saldo (remove o valor recebido)
        carteira.setSaldoDisponivel(saldoAnterior);

        // Restaura posição (adiciona de volta as ações vendidas)
        boolean encontrou = false;
        for (stocktrading.model.AcaoPosicao posicao : carteira.getPosicoes()) {
            if (posicao.getAcao().getCodigo().equals(ordem.getCodigoAcao())) {
                posicao.setQuantidade(quantidadeAnterior);
                posicao.setPrecoMedio(precoMedioAnterior);
                encontrou = true;
                break;
            }
        }

        // Se a posição foi completamente removida, precisa recriar
        if (!encontrou && quantidadeAnterior > 0) {
            stocktrading.factorymethod.AcaoFactory acaoFactory = new stocktrading.factorymethod.AcaoFactory();
            stocktrading.model.Cotacao cotacao = bolsaFacade.buscarCotacao(ordem.getCodigoAcao());
            stocktrading.factorymethod.Acao acao = acaoFactory.criar(
                stocktrading.factorymethod.TipoDeAcao.valueOf(ordem.getTipoAcao()),
                ordem.getCodigoAcao(),
                ordem.getBolsa(),
                cotacao.getPreco()
            );
            carteira.adicionarOuAtualizarPosicao(acao, quantidadeAnterior, precoMedioAnterior);
        }

        executado = false;
        System.out.println("✓ VendaCommand desfeito: " + ordem.getQuantidade() + "x " + ordem.getCodigoAcao());
    }

    @Override
    public String getDescricao() {
        return "Venda de " + ordem.getQuantidade() + " ações de " + ordem.getCodigoAcao();
    }
}

