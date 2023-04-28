package StrategyV2.loja;

import StrategyV2.estrategia.EstrategiaPagamento;
import StrategyV2.estrategia.Pagavel;

import java.math.BigDecimal;

public class Compra implements Pagavel {
    BigDecimal valor;

    public Compra(BigDecimal valor) {
        this.valor = valor;
    }

    public void processarCompra(EstrategiaPagamento estrategiaPagamento){
        estrategiaPagamento.pagar(this);
    }

    @Override
    public BigDecimal getValor() {
        return this.valor;
    }
}
