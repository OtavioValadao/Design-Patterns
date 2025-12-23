package com.patterns.stocktrading.factorymethod.subclasses;

import com.patterns.stocktrading.factorymethod.Acao;

public class AcaoPreferencial extends Acao {

    public AcaoPreferencial(String codigo, String bolsa, double precoAtual) {
        super(codigo, bolsa, precoAtual);
    }

}
