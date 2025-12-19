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
}


