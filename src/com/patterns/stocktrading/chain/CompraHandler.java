package com.patterns.stocktrading.chain;

import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.model.Cotacao;
import com.patterns.stocktrading.model.Ordem;
import com.patterns.stocktrading.template.ProcessadorOrdemCompra;

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
