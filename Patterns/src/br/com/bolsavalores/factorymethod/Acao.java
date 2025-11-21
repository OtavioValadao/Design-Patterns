package br.com.bolsavalores.factorymethod;

public abstract class Acao {

    public String codigo;
    public String bolsa = "B3";
    public Double precoAtual;


    public Acao(String codigo, String bolsa, Double precoAtual) {
        this.codigo = codigo;
        this.bolsa = bolsa;
        this.precoAtual = precoAtual;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getBolsa() {
        return bolsa;
    }

    public void setBolsa(String bolsa) {
        this.bolsa = bolsa;
    }

    public Double getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(Double precoAtual) {
        this.precoAtual = precoAtual;
    }
}
