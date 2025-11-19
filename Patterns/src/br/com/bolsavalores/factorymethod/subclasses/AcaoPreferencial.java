package br.com.bolsavalores.factorymethod.subclasses;

import br.com.bolsavalores.factorymethod.Acao;

public class AcaoPreferencial extends Acao {

    public AcaoPreferencial(String codigo, String bolsa, double precoAtual) {
        super(codigo, bolsa, precoAtual);
    }

}
