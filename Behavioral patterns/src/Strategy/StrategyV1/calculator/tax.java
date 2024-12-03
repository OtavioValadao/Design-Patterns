package Strategy.StrategyV1.calculator;

import Strategy.StrategyV1.store.budget;

import java.math.BigDecimal;

public interface tax {
    BigDecimal calculator(budget budget);
}
