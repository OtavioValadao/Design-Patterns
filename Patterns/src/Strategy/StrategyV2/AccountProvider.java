package Strategy.StrategyV2;

import java.util.Set;

public class AccountProvider {

    private final Set<StrategyMembers> strategies;

    public AccountProvider(Set<StrategyMembers> strategies) {
        this.strategies = strategies;
    }

    public void get(String member) {
        for (StrategyMembers strategy : strategies) {
            if (strategy.support(member)) {
                strategy.getAccountInfo();
                return;
            }
        }

        throw new RuntimeException("Unprocessable member");
    }
}
