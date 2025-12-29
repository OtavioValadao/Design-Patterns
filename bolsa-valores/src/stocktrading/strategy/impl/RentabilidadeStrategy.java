package stocktrading.strategy.impl;

import stocktrading.model.AcaoPosicao;
import stocktrading.model.Carteira;
import stocktrading.strategy.CalculoStrategy;

public class RentabilidadeStrategy implements CalculoStrategy {

    @Override
    public Boolean validarStrategy(String tipoCalculo) {
        return "RENTABILIDADE".equals(tipoCalculo);
    }

    @Override
    public Double calcular(Carteira carteira) {
        double totalAtual = 0.0;
        double totalInvestido = 0.0;
        for (AcaoPosicao posicao : carteira.getPosicoes()) {
            totalAtual = totalAtual + posicao.getQuantidade() * posicao.getAcao().getPrecoAtual();
            totalInvestido = totalInvestido + posicao.getQuantidade() * posicao.getPrecoMedio();
        }
        if (totalInvestido == 0.0) {
            return 0.0;
        }
        return (totalAtual - totalInvestido) / totalInvestido;
    }
}
