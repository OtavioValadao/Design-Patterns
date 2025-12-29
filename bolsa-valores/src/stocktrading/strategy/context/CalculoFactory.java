package stocktrading.strategy.context;

import stocktrading.model.AcaoPosicao;
import stocktrading.model.Carteira;
import stocktrading.strategy.CalculoStrategy;

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
