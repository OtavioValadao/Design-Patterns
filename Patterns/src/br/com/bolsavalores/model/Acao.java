package br.com.bolsavalores.model;

public class Acao {
    private String codigo;
    private String tipo;
    private String bolsa;
    private double precoAtual;

    public Acao(String codigo, String tipo, String bolsa, double precoAtual) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.bolsa = bolsa;
        this.precoAtual = precoAtual;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBolsa() {
        return bolsa;
    }

    public void setBolsa(String bolsa) {
        this.bolsa = bolsa;
    }

    public double getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(double precoAtual) {
        this.precoAtual = precoAtual;
    }
}


