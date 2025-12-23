package com.patterns.stocktrading.factorymethod.subclasses;

import com.patterns.stocktrading.factorymethod.Acao;

public class AcaoOrdinaria extends Acao {

    public AcaoOrdinaria(String codigo, String bolsa, Double preco) {
        super(codigo, bolsa, preco);
    }
}
