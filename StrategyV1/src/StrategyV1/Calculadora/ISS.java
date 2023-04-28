package StrategyV1.Calculadora;

import StrategyV1.loja.Orcamento;

import java.math.BigDecimal;

public class ISS implements Imposto {

    public BigDecimal calcular(Orcamento orcamento){
        return orcamento.getValor().multiply(new BigDecimal("0.06"));
    }
}
