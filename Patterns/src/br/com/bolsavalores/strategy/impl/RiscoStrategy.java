package br.com.bolsavalores.strategy.impl;

import br.com.bolsavalores.model.AcaoPosicao;
import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.strategy.CalculoStrategy;

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
