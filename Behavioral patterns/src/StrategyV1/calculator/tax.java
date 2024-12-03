package StrategyV1.calculator;

import StrategyV1.store.budget;

import java.math.BigDecimal;

public interface tax {
    BigDecimal calculator(budget budget);
}
