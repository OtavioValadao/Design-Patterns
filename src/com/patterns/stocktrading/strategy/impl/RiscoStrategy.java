package com.patterns.stocktrading.strategy.impl;

import com.patterns.stocktrading.model.AcaoPosicao;
import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.strategy.CalculoStrategy;

public class RiscoStrategy implements CalculoStrategy {

    @Override
    public Boolean validarStrategy(String tipoCalculo) {
        return "RISCO".equals(tipoCalculo);
    }

    @Override
    public Double calcular(Carteira carteira) {
        double variacaoTotal = 0.0;
        for (AcaoPosicao posicao : carteira.getPosicoes()) {
            variacaoTotal = variacaoTotal + Math.abs(posicao.getAcao().getPrecoAtual() - posicao.getPrecoMedio());
        }
        return variacaoTotal;
    }
}
