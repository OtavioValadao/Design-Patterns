package Adapter;

import Adapter.Payment.OldPaymentGateway;
import Adapter.Payment.PaymentAdapter;
import Adapter.Payment.PaymentProcessor;

public class MainAdapter {
    public static void main(String[] args) {
        adapterV1(true);
    }

    public static void adapterV1(boolean isProcessable) {
        if (isProcessable) {
            var oldPay = new OldPaymentGateway();
            PaymentProcessor paymentProcessor = new PaymentAdapter(oldPay);
            paymentProcessor.processPayment(150.00);
        }
    }
}
