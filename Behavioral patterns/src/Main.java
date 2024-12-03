import Strategy.StrategyV1.calculator.CalculatorTax;
import Strategy.StrategyV1.calculator.ICMS;
import Strategy.StrategyV1.store.budget;
import Strategy.StrategyV2.AccountProvider;
import Strategy.StrategyV2.BusinessMember;
import Strategy.StrategyV2.PhysicalPerson;
import Strategy.StrategyV2.StrategyMembers;

import java.math.BigDecimal;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        strategyV1(true);
        strategyV2(true);
    }

    public static void strategyV1(boolean isProcessable) {
        if (isProcessable) {
            budget budget = new budget(new BigDecimal("100"));

            CalculatorTax calculatorTax = new CalculatorTax();

            //change instance
            System.out.println(calculatorTax.calculate(budget, new ICMS()));
        }
    }

    public static void strategyV2(boolean isProcessable) {
        if (isProcessable) {
            var business = new BusinessMember();
            var pj = new PhysicalPerson();
            Set<StrategyMembers> strategyMembers = Set.of(business, pj);
            var accountProvider = new AccountProvider(strategyMembers);
            accountProvider.get("PF");
        }
    }
}