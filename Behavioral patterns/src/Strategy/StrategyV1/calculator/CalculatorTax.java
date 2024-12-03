package Strategy.StrategyV1.calculator;

import Strategy.StrategyV1.store.budget;

import java.math.BigDecimal;

public class CalculatorTax {

    public BigDecimal calculate(budget budget, tax tax){
        return tax.calculator(budget);
    }

}
