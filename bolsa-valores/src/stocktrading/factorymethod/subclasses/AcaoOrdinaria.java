package stocktrading.factorymethod.subclasses;

import stocktrading.factorymethod.Acao;

public class AcaoOrdinaria extends Acao {

    public AcaoOrdinaria(String codigo, String bolsa, Double preco) {
        super(codigo, bolsa, preco);
    }
}
