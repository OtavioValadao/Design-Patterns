package strategy;

import strategy.nopattern.PaymentProcessor;
import strategy.pattern.first_solution.PaymentType;

public class Main {

    public static void main(String[] args) {
        //noPattern();
        firstPattern();
    }

    public static void noPattern(){
        PaymentProcessor processador = new PaymentProcessor();

        System.out.println("TESTE 1:");
        processador.paymentProcessor("PIX", 100.0, "cliente@email.com");
    }

    public static void firstPattern(){
        String paymentMethod = "CRIPTOMOEDA";
        PaymentType.valueOf(paymentMethod).pay(100.00);
    }
}
