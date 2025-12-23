package com.patterns.stocktrading.chain;

import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.model.Cotacao;
import com.patterns.stocktrading.model.Ordem;

import java.util.List;

public interface OperacaoHandler {
    void setNext(OperacaoHandler next);
    boolean handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio);
}
