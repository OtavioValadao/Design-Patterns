package stocktrading.chain;

import stocktrading.model.Carteira;
import stocktrading.model.Cotacao;
import stocktrading.model.Ordem;
import stocktrading.template.ProcessadorOrdemCompra;

import java.util.List;

public class CompraHandler extends AbstractOperacaoHandler {
    @Override
    public boolean handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        if ("COMPRA".equals(ordem.getTipoOperacao())) {
            new ProcessadorOrdemCompra().processar(ordem, carteira, cotacoes, tipoRelatorio);
            return true;
        }
        return callNext(ordem, carteira, cotacoes, tipoRelatorio);
    }
}
