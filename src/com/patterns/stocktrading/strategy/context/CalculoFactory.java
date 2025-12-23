package com.patterns.stocktrading.strategy.context;

import com.patterns.stocktrading.model.AcaoPosicao;
import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.strategy.CalculoStrategy;
import com.patterns.stocktrading.strategy.impl.ImpostoStrategy;
import com.patterns.stocktrading.strategy.impl.RentabilidadeStrategy;
import com.patterns.stocktrading.strategy.impl.RiscoStrategy;

import java.util.List;
import java.util.Optional;

public class CalculoFactory {

    private final List<CalculoStrategy> calculoStrategies;

    public CalculoFactory(List<CalculoStrategy> calculoStrategies) {
        this.calculoStrategies = calculoStrategies;
    }

    public Double calculoStrategyContext(String tipoOperacao, Carteira carteira) {

        Optional<Double> calculoStrategy = calculoStrategies.stream()
                .filter(strategyValidation -> strategyValidation.validarStrategy(tipoOperacao))
                .map(calculo -> calculo.calcular(carteira))
                .findFirst();

        if (calculoStrategy.isPresent()) {
            return calculoStrategy.get();
        } else {
            double total = 0.0;
            for (AcaoPosicao posicao : carteira.getPosicoes()) {
                total = total + posicao.getQuantidade() * posicao.getAcao().getPrecoAtual();
            }
            return total;
        }
    }

}
