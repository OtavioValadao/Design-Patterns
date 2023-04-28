import StrategyV1.Calculadora.CalculadoraDeImpostos;
import StrategyV1.Calculadora.ICMS;
import StrategyV1.Calculadora.ISS;
import StrategyV1.loja.Orcamento;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(new BigDecimal("100"));
        CalculadoraDeImpostos calculadoraDeImpostos = new CalculadoraDeImpostos();
        System.out.println(calculadoraDeImpostos.calcular(orcamento, new ISS()));
    }
}