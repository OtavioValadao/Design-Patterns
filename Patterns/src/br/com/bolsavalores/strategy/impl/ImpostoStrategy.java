package br.com.bolsavalores.strategy.impl;

import br.com.bolsavalores.model.AcaoPosicao;
import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.strategy.CalculoStrategy;

public class ImpostoStrategy implements CalculoStrategy {
    @Override
    public Boolean validarStrategy(String tipoCalculo) {
        return "IMPOSTO".equals(tipoCalculo);
    }

    @Override
    public Double calcular(Carteira carteira) {
        double totalGanho = 0.0;
        double totalAtual = 0.0;
        double totalInvestido = 0.0;
        for (AcaoPosicao posicao : carteira.getPosicoes()) {
            totalAtual = totalAtual + posicao.getQuantidade() * posicao.getAcao().getPrecoAtual();
            totalInvestido = totalInvestido + posicao.getQuantidade() * posicao.getPrecoMedio();
        }
        if (totalAtual > totalInvestido) {
            totalGanho = totalAtual - totalInvestido;
        }
        return totalGanho * 0.15;
    }
}
