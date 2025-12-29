package stocktrading.template;

import stocktrading.factorymethod.Acao;
import stocktrading.factorymethod.AcaoFactory;
import stocktrading.factorymethod.TipoDeAcao;
import stocktrading.model.Carteira;
import stocktrading.model.Cotacao;
import stocktrading.model.Ordem;
import stocktrading.util.FormatoUtil;

import java.util.List;

public class ProcessadorOrdemVenda extends ProcessadorOrdem {

    private Cotacao cotacaoEncontrada;
    private double precoExecucao;
    private double valorTotal;

    @Override
    protected boolean validar(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes) {
        cotacaoEncontrada = cotacoes.stream()
                .filter(c -> c.getCodigo().equals(ordem.getCodigoAcao()))
                .findFirst()
                .orElse(null);

        if (cotacaoEncontrada == null) {
            System.out.println("Não foi possível encontrar cotação para " + ordem.getCodigoAcao());
            return false;
        }

        precoExecucao = cotacaoEncontrada.getPreco();
        if (ordem.getPrecoLimite() > 0.0 && ordem.getPrecoLimite() > precoExecucao) {
            precoExecucao = ordem.getPrecoLimite();
        }
        valorTotal = precoExecucao * ordem.getQuantidade();
        return true;
    }

    @Override
    protected void executar(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes) {
        AcaoFactory acaoFactory = new AcaoFactory();
        Acao acao = acaoFactory.criar(TipoDeAcao.valueOf(ordem.getTipoAcao()), ordem.getCodigoAcao(), ordem.getBolsa(), cotacaoEncontrada.getPreco());
        carteira.reduzirPosicao(acao, ordem.getQuantidade());
        carteira.setSaldoDisponivel(carteira.getSaldoDisponivel() + valorTotal);
    }

    @Override
    protected void gerarRelatorio(Ordem ordem, Carteira carteira, String tipoRelatorio) {
        if ("SIMPLIFICADO".equals(tipoRelatorio)) {
            System.out.println("Venda realizada de " + ordem.getQuantidade() + " de " + ordem.getCodigoAcao() + " por " + FormatoUtil.formatarValor(precoExecucao, "MOEDA"));
        } else if ("DETALHADO".equals(tipoRelatorio)) {
            System.out.println("Venda realizada de " + ordem.getQuantidade() + " de " + ordem.getCodigoAcao());
            System.out.println("Preco de execucao " + FormatoUtil.formatarValor(precoExecucao, "MOEDA"));
            System.out.println("Valor total " + FormatoUtil.formatarValor(valorTotal, "MOEDA"));
            System.out.println("Saldo apos venda " + FormatoUtil.formatarValor(carteira.getSaldoDisponivel(), "MOEDA"));
        } else {
            System.out.println("Venda executada");
        }
    }
}
