package stocktrading.model;

public class Cotacao {
    private String codigo;
    private double preco;
    private String fonteDados;

    public Cotacao(String codigo, double preco, String fonteDados) {
        this.codigo = codigo;
        this.preco = preco;
        this.fonteDados = fonteDados;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getFonteDados() {
        return fonteDados;
    }

    public void setFonteDados(String fonteDados) {
        this.fonteDados = fonteDados;
    }
}


