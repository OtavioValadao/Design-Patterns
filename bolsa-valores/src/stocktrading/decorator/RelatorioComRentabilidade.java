package stocktrading.decorator;

import stocktrading.model.Carteira;
import stocktrading.strategy.context.CalculoFactory;
import stocktrading.strategy.impl.RentabilidadeStrategy;
import stocktrading.util.FormatoUtil;

import java.util.List;

public class RelatorioComRentabilidade extends RelatorioDecorator {
    
    public RelatorioComRentabilidade(Relatorio relatorio) {
        super(relatorio);
    }

    @Override
    public void gerar(Carteira carteira) {
        super.gerar(carteira);
        adicionarRentabilidade(carteira);
    }

    private void adicionarRentabilidade(Carteira carteira) {
        CalculoFactory calculoFactory = new CalculoFactory(List.of(new RentabilidadeStrategy()));
        Double rentabilidade = calculoFactory.calculoStrategyContext("RENTABILIDADE", carteira);
        System.out.println("Rentabilidade: " + FormatoUtil.formatarValor(rentabilidade, "PORCENTAGEM"));
    }
}

