package stocktrading.model;

import stocktrading.factorymethod.Acao;

public class AcaoPosicao {
    private Acao acao;
    private int quantidade;
    private double precoMedio;

    public AcaoPosicao(Acao acao, int quantidade, double precoMedio) {
        this.acao = acao;
        this.quantidade = quantidade;
        this.precoMedio = precoMedio;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(double precoMedio) {
        this.precoMedio = precoMedio;
    }
}


