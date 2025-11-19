package br.com.bolsavalores.factorymethod.subclasses;

import br.com.bolsavalores.factorymethod.Acao;

public class AcaoEtf extends Acao {

    public AcaoEtf(String codigo, String bolsa, double precoAtual) {
        super(codigo, bolsa, precoAtual);
    }
}
