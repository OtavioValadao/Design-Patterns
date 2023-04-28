package StrategyV2.estrategia;

import StrategyV2.loja.Compra;

import java.math.BigDecimal;

public interface EstrategiaPagamento {
    void pagar(Pagavel valor);
}
