package strategy.pattern.first_solution;

import java.util.function.Consumer;

public enum PaymentType {

    CARTAO_CREDITO(PaymentMethods::creditCard),
    CARTAO_DEBITO(PaymentMethods::debitCard),
    PIX(PaymentMethods::pix),
    BOLETO(PaymentMethods::boleto),
    PAYPAL(PaymentMethods::paypal),
    CRIPTOMOEDA(PaymentMethods::cripto);

    private Consumer<Double> paymentStrategy;

    PaymentType(Consumer<Double> paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(Double amount) {
        paymentStrategy.accept(amount);
    }
}
