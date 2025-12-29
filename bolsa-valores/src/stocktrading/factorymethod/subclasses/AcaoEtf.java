package stocktrading.factorymethod.subclasses;

import stocktrading.factorymethod.Acao;

public class AcaoEtf extends Acao {

    public AcaoEtf(String codigo, String bolsa, double precoAtual) {
        super(codigo, bolsa, precoAtual);
    }
}
