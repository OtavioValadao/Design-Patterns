package br.com.bolsavalores.strategy;

import br.com.bolsavalores.model.Carteira;

public interface CalculoStrategy {

    Boolean validarStrategy(String tipoCalculo);

    Double calcular(Carteira carteira);

}
