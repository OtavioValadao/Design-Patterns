package StrategyV1.calculator;

import StrategyV1.store.budget;

import java.math.BigDecimal;

public class ICMS  implements tax {

    public BigDecimal calculator(budget budget){
        return budget.getValue().multiply(new BigDecimal("0.01"));
    }
}
