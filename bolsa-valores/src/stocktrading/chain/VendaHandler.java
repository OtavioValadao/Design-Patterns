package stocktrading.chain;

import stocktrading.model.Carteira;
import stocktrading.model.Cotacao;
import stocktrading.model.Ordem;
import stocktrading.template.ProcessadorOrdemVenda;

import java.util.List;

public class VendaHandler extends AbstractOperacaoHandler {
    @Override
    public boolean handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        if ("VENDA".equals(ordem.getTipoOperacao())) {
            new ProcessadorOrdemVenda().processar(ordem, carteira, cotacoes, tipoRelatorio);
            return true;
        }
        return callNext(ordem, carteira, cotacoes, tipoRelatorio);
    }
}
