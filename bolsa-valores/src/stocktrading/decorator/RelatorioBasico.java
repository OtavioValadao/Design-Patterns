package stocktrading.decorator;

import stocktrading.model.Carteira;
import stocktrading.util.CalculoUtil;
import stocktrading.util.FormatoUtil;

public class RelatorioBasico implements Relatorio {
    
    @Override
    public void gerar(Carteira carteira) {
        double valorTotal = CalculoUtil.calcularValorTotalCarteira(carteira);
        System.out.println("=== Relatório Básico ===");
        System.out.println("Carteira: " + carteira.getNome());
        System.out.println("Valor Total: " + FormatoUtil.formatarValor(valorTotal, "MOEDA"));
        System.out.println("Saldo Disponível: " + FormatoUtil.formatarValor(carteira.getSaldoDisponivel(), "MOEDA"));
    }
}

