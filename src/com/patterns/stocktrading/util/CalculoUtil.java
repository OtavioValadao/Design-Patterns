package com.patterns.stocktrading.util;

import com.patterns.stocktrading.model.AcaoPosicao;
import com.patterns.stocktrading.model.Carteira;

public class CalculoUtil {
    public static double calcularValorTotalCarteira(Carteira carteira) {
        double total = 0.0;
        for (AcaoPosicao posicao : carteira.getPosicoes()) {
            total = total + posicao.getQuantidade() * posicao.getAcao().getPrecoAtual();
        }
        return total;
    }
}


