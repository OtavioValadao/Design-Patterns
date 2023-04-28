import StrategyV1.Calculadora.CalculadoraDeImpostos;
import StrategyV1.Calculadora.ISS;
import StrategyV1.loja.Orcamento;
import StrategyV2.estrategia.PagamentoCartaoCredito;
import StrategyV2.loja.Compra;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        //StrategyV1
        Orcamento orcamento = new Orcamento(new BigDecimal("100"));
        CalculadoraDeImpostos calculadoraDeImpostos = new CalculadoraDeImpostos();
        System.out.println(calculadoraDeImpostos.calcular(orcamento, new ISS()));

        //StrategyV2
        BigDecimal valor = new BigDecimal("10");
        Compra compra = new Compra(valor);
        compra.processarCompra(new PagamentoCartaoCredito());
    }
}