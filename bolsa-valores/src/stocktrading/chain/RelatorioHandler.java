package stocktrading.chain;

import stocktrading.model.Carteira;
import stocktrading.model.Cotacao;
import stocktrading.model.Ordem;
import stocktrading.template.ProcessadorOrdemRelatorio;

import java.util.List;

public class RelatorioHandler extends AbstractOperacaoHandler {
    @Override
    public boolean handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        if ("RELATORIO".equals(ordem.getTipoOperacao())) {
            new ProcessadorOrdemRelatorio().processar(ordem, carteira, cotacoes, tipoRelatorio);
            return true;
        }
        return callNext(ordem, carteira, cotacoes, tipoRelatorio);
    }
}
