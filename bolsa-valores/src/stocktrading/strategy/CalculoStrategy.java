package stocktrading.strategy;

import stocktrading.model.Carteira;

public interface CalculoStrategy {

    Boolean validarStrategy(String tipoCalculo);

    Double calcular(Carteira carteira);

}
