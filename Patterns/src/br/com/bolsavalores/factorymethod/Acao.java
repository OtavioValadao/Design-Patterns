package br.com.bolsavalores.factorymethod;

public abstract class Acao {

    public String codigo;
    public String bolsa = "B3";
    public Double preco;


    public Acao(String codigo, String bolsa, Double preco) {
        this.codigo = codigo;
        this.bolsa = bolsa;
        this.preco = preco;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
