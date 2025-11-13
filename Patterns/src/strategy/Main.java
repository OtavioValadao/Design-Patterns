package strategy;

import strategy.nopattern.ProcessadorPagamento;

public class Main {

    public static void main(String[] args) {
        ProcessadorPagamento processador = new ProcessadorPagamento();

        System.out.println("TESTE 1:");
        processador.processarPagamento("PIX", 100.0, "cliente@email.com");

        System.out.println("\n\nTESTE 2:");
        processador.processarPagamento("CARTAO_CREDITO", 250.0, "cliente@email.com");

        System.out.println("\n\nTESTE 3:");
        processador.processarPagamento("BOLETO", 500.0, "cliente@email.com");
    }
}
