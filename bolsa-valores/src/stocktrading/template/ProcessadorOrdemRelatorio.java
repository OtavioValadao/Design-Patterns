package stocktrading.template;

import stocktrading.model.Carteira;
import stocktrading.model.Cotacao;
import stocktrading.model.Ordem;
import stocktrading.strategy.context.CalculoFactory;
import stocktrading.strategy.impl.ImpostoStrategy;
import stocktrading.strategy.impl.RentabilidadeStrategy;
import stocktrading.strategy.impl.RiscoStrategy;
import stocktrading.util.CalculoUtil;
import stocktrading.util.FormatoUtil;

import java.util.List;

public class ProcessadorOrdemRelatorio extends ProcessadorOrdem {

    private double valorTotal;
    private double rentabilidade;
    private double risco;
    private double imposto;

    @Override
    protected boolean validar(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes) {
        return true;
    }

    @Override
    protected void executar(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes) {
        valorTotal = CalculoUtil.calcularValorTotalCarteira(carteira);

        CalculoFactory calculoFactory = new CalculoFactory(List.of(new ImpostoStrategy(), new RentabilidadeStrategy(), new RiscoStrategy()));
        rentabilidade = calculoFactory.calculoStrategyContext("RENTABILIDADE", carteira);
        risco = calculoFactory.calculoStrategyContext("RISCO", carteira);
        imposto = calculoFactory.calculoStrategyContext("IMPOSTO", carteira);
    }

    @Override
    protected void gerarRelatorio(Ordem ordem, Carteira carteira, String tipoRelatorio) {
        if ("SIMPLIFICADO".equals(tipoRelatorio)) {
            System.out.println("Resumo da carteira " + carteira.getNome());
            System.out.println("Valor total " + FormatoUtil.formatarValor(valorTotal, "MOEDA"));
        } else if ("DETALHADO".equals(tipoRelatorio)) {
            System.out.println("Relatorio detalhado da carteira " + carteira.getNome());
            System.out.println("Valor total " + FormatoUtil.formatarValor(valorTotal, "MOEDA"));
            System.out.println("Rentabilidade " + FormatoUtil.formatarValor(rentabilidade, "PORCENTAGEM"));
            System.out.println("Risco aproximado " + FormatoUtil.formatarValor(risco, "PADRAO"));
            System.out.println("Imposto potencial " + FormatoUtil.formatarValor(imposto, "MOEDA"));
        } else {
            System.out.println("Relatorio simples da carteira");
        }
    }
}
