package stocktrading.decorator;

import stocktrading.model.Carteira;
import stocktrading.strategy.context.CalculoFactory;
import stocktrading.strategy.impl.ImpostoStrategy;
import stocktrading.util.FormatoUtil;

import java.util.List;

public class RelatorioComImposto extends RelatorioDecorator {
    
    public RelatorioComImposto(Relatorio relatorio) {
        super(relatorio);
    }

    @Override
    public void gerar(Carteira carteira) {
        super.gerar(carteira);
        adicionarImposto(carteira);
    }

    private void adicionarImposto(Carteira carteira) {
        CalculoFactory calculoFactory = new CalculoFactory(List.of(new ImpostoStrategy()));
        Double imposto = calculoFactory.calculoStrategyContext("IMPOSTO", carteira);
        System.out.println("Imposto Potencial: " + FormatoUtil.formatarValor(imposto, "MOEDA"));
    }
}

