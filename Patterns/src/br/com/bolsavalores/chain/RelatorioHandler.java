package br.com.bolsavalores.chain;

import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;
import br.com.bolsavalores.strategy.context.CalculoFactory;
import br.com.bolsavalores.strategy.impl.ImpostoStrategy;
import br.com.bolsavalores.strategy.impl.RentabilidadeStrategy;
import br.com.bolsavalores.strategy.impl.RiscoStrategy;
import br.com.bolsavalores.util.CalculoUtil;
import br.com.bolsavalores.util.FormatoUtil;

import java.util.List;

public class RelatorioHandler extends AbstractOperacaoHandler {
    @Override
    public void handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        if ("RELATORIO".equals(ordem.getTipoOperacao())) {
            double valorTotal = CalculoUtil.calcularValorTotalCarteira(carteira);

            CalculoFactory calculoFactory = new CalculoFactory(List.of(new ImpostoStrategy(), new RentabilidadeStrategy(), new RiscoStrategy()));
            var rentabilidade = calculoFactory.calculoStrategyContext("RENTABILIDADE", carteira);
            var risco = calculoFactory.calculoStrategyContext("RISCO", carteira);
            var imposto = calculoFactory.calculoStrategyContext("IMPOSTO", carteira);
//            double rentabilidade = CalculoUtil.calcularIndicadorCarteira(carteira, "RENTABILIDADE");
//            double risco = CalculoUtil.calcularIndicadorCarteira(carteira, "RISCO");
//            double imposto = CalculoUtil.calcularIndicadorCarteira(carteira, "IMPOSTO");

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
        } else {
            callNext(ordem, carteira, cotacoes, tipoRelatorio);
        }
    }
}
