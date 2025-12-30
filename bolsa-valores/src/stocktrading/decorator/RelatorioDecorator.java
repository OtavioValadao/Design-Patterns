package stocktrading.decorator;

import stocktrading.model.Carteira;

public abstract class RelatorioDecorator implements Relatorio {
    protected Relatorio relatorio;

    public RelatorioDecorator(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    @Override
    public void gerar(Carteira carteira) {
        relatorio.gerar(carteira);
    }
}

