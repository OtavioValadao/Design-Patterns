package StrategyV1.store;

import java.math.BigDecimal;

public class budget {

    private BigDecimal value;

    public budget(BigDecimal valor) {
        this.value = valor;
    }

    public BigDecimal getValue() {
        return value;
    }
}
