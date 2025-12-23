package br.com.bolsavalores.chain;

import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;
import br.com.bolsavalores.template.ProcessadorOrdemRelatorio;

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
