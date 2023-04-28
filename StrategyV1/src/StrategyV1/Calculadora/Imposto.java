package StrategyV1.Calculadora;

import StrategyV1.loja.Orcamento;

import java.math.BigDecimal;

public interface Imposto {
    BigDecimal calcular(Orcamento orcamento);
}
