package com.patterns.stocktrading.factorymethod.subclasses;

import com.patterns.stocktrading.factorymethod.Acao;

public class AcaoEtf extends Acao {

    public AcaoEtf(String codigo, String bolsa, double precoAtual) {
        super(codigo, bolsa, precoAtual);
    }
}
