package com.patterns.stocktrading.template;

import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.model.Cotacao;
import com.patterns.stocktrading.model.Ordem;

import java.util.List;

public abstract class ProcessadorOrdem {

    public final void processar(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        if (validar(ordem, carteira, cotacoes)) {
            executar(ordem, carteira, cotacoes);
            gerarRelatorio(ordem, carteira, tipoRelatorio);
        }
    }

    protected abstract boolean validar(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes);

    protected abstract void executar(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes);

    protected abstract void gerarRelatorio(Ordem ordem, Carteira carteira, String tipoRelatorio);
}
