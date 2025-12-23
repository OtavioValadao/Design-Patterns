package com.patterns.stocktrading.strategy;

import com.patterns.stocktrading.model.Carteira;

public interface CalculoStrategy {

    Boolean validarStrategy(String tipoCalculo);

    Double calcular(Carteira carteira);

}
