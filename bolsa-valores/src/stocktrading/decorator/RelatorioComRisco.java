package stocktrading.decorator;

import stocktrading.model.Carteira;
import stocktrading.strategy.context.CalculoFactory;
import stocktrading.strategy.impl.RiscoStrategy;
import stocktrading.util.FormatoUtil;

import java.util.List;

public class RelatorioComRisco extends RelatorioDecorator {
    
    public RelatorioComRisco(Relatorio relatorio) {
        super(relatorio);
    }

    @Override
    public void gerar(Carteira carteira) {
        super.gerar(carteira);
        adicionarRisco(carteira);
    }

    private void adicionarRisco(Carteira carteira) {
        CalculoFactory calculoFactory = new CalculoFactory(List.of(new RiscoStrategy()));
        Double risco = calculoFactory.calculoStrategyContext("RISCO", carteira);
        System.out.println("Risco Aproximado: " + FormatoUtil.formatarValor(risco, "PADRAO"));
    }
}

