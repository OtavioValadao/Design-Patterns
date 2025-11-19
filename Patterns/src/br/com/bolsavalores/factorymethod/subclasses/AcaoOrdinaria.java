package br.com.bolsavalores.factorymethod.subclasses;

import br.com.bolsavalores.factorymethod.Acao;

public class AcaoOrdinaria extends Acao {

    public AcaoOrdinaria(String codigo, String bolsa, Double preco) {
        super(codigo, bolsa, preco);
    }
}
