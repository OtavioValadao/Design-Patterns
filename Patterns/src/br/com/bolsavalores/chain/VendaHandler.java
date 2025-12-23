package br.com.bolsavalores.chain;

import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;
import br.com.bolsavalores.template.ProcessadorOrdemVenda;

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
