package br.com.bolsavalores.model;

public class OrdemBuilder {

    private final Ordem ordem;

    public OrdemBuilder() {
        this.ordem = new Ordem();
    }

    public OrdemBuilder paraCompra() {
        ordem.setTipoOperacao("COMPRA");
        return this;
    }

    public OrdemBuilder paraVenda() {
        ordem.setTipoOperacao("VENDA");
        return this;
    }

    public OrdemBuilder comTipoAcao(String tipoAcao) {
        ordem.setTipoAcao(tipoAcao);
        return this;
    }

    public OrdemBuilder comCodigo(String codigo) {
        ordem.setCodigoAcao(codigo);
        return this;
    }

    public OrdemBuilder comQuantidade(int quantidade) {
        ordem.setQuantidade(quantidade);
        return this;
    }

    public OrdemBuilder comPrecoLimite(double precoLimite) {
        ordem.setPrecoLimite(precoLimite);
        return this;
    }

    public OrdemBuilder naBolsa(String bolsa) {
        ordem.setBolsa(bolsa);
        return this;
    }

    public OrdemBuilder comOrigem(String origem) {
        ordem.setOrigem(origem);
        return this;
    }

    public OrdemBuilder comTipoExecucao(String tipoExecucao) {
        ordem.setTipoExecucao(tipoExecucao);
        return this;
    }

    public OrdemBuilder comValidade(String validade) {
        ordem.setValidade(validade);
        return this;
    }

    public Ordem build() {
        return this.ordem;
    }
}
