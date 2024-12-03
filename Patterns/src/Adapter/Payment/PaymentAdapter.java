package Adapter.Payment;

public class PaymentAdapter implements PaymentProcessor {

    private OldPaymentGateway oldPaymentGateway;

    public PaymentAdapter(OldPaymentGateway oldPaymentGateway) {
        this.oldPaymentGateway = oldPaymentGateway;
    }

    @Override
    public void processPayment(double amount) {
        oldPaymentGateway.pay(amount);
    }
}
