package com.patterns.stocktrading.chain;

import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.model.Cotacao;
import com.patterns.stocktrading.model.Ordem;

import java.util.List;

public abstract class AbstractOperacaoHandler implements OperacaoHandler {
    private OperacaoHandler next;

    @Override
    public void setNext(OperacaoHandler next) {
        this.next = next;
    }

    protected boolean callNext(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        if (next != null) {
            return next.handle(ordem, carteira, cotacoes, tipoRelatorio);
        }
        return true;
    }
}
