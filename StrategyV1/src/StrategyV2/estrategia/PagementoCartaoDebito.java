package StrategyV2.estrategia;

import StrategyV2.loja.Compra;

import java.math.BigDecimal;

public class PagementoCartaoDebito implements EstrategiaPagamento{
    @Override
    public void pagar(Pagavel pagevel) {
        System.out.println("pagou no cartão de Débito o valor de: " + pagevel.getValor());
    }
}
