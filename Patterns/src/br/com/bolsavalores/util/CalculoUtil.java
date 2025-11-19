package br.com.bolsavalores.util;

import br.com.bolsavalores.model.AcaoPosicao;
import br.com.bolsavalores.model.Carteira;

public class CalculoUtil {
    public static double calcularValorTotalCarteira(Carteira carteira) {
        double total = 0.0;
        for (AcaoPosicao posicao : carteira.getPosicoes()) {
            total = total + posicao.getQuantidade() * posicao.getAcao().getPrecoAtual();
        }
        return total;
    }

    public static double calcularIndicadorCarteira(Carteira carteira, String tipoCalculo) {
        if ("RENTABILIDADE".equals(tipoCalculo)) {
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
        } else if ("RISCO".equals(tipoCalculo)) {
            double variacaoTotal = 0.0;
            for (AcaoPosicao posicao : carteira.getPosicoes()) {
                variacaoTotal = variacaoTotal + Math.abs(posicao.getAcao().getPrecoAtual() - posicao.getPrecoMedio());
            }
            return variacaoTotal;
        } else if ("IMPOSTO".equals(tipoCalculo)) {
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
        } else {
            return calcularValorTotalCarteira(carteira);
        }
    }
}


