package strategy.nopattern;

public class PaymentProcessor {

    public void paymentProcessor(String tipoPagamento, double value, String customerData) {

        //Poderiamos trocar por switch case?
        if (tipoPagamento.equals("CARTAO_CREDITO")) {
            System.out.println("=== Processando Pagamento com Cartão de Crédito ===");

            // Validações específicas do cartão
            if (value < 5.0) {
                throw new IllegalArgumentException("Valor mínimo para cartão é R$ 5,00");
            }

            // Lógica de processamento
            System.out.println("1. Validando número do cartão...");
            System.out.println("2. Verificando limite disponível...");
            System.out.println("3. Aplicando taxa de 2.5%: R$ " + (value * 0.025));
            System.out.println("4. Enviando para operadora...");
            System.out.println("5. Aguardando aprovação...");

            double valorFinal = value + (value * 0.025);
            System.out.println("Pagamento aprovado! Valor final: R$ " + valorFinal);

        } else if (tipoPagamento.equals("CARTAO_DEBITO")) {
            System.out.println("=== Processando Pagamento com Cartão de Débito ===");

            // Validações específicas do débito
            if (value < 1.0) {
                throw new IllegalArgumentException("Valor mínimo para débito é R$ 1,00");
            }

            // Lógica de processamento
            System.out.println("1. Validando número do cartão...");
            System.out.println("2. Verificando saldo em conta...");
            System.out.println("3. Aplicando taxa de 1.5%: R$ " + (value * 0.015));
            System.out.println("4. Debitando da conta...");

            double valorFinal = value + (value * 0.015);
            System.out.println("Pagamento aprovado! Valor final: R$ " + valorFinal);

        } else if (tipoPagamento.equals("PIX")) {
            System.out.println("=== Processando Pagamento via PIX ===");

            // Validações específicas do PIX
            if (value < 0.01) {
                throw new IllegalArgumentException("Valor mínimo para PIX é R$ 0,01");
            }

            // Lógica de processamento
            System.out.println("1. Gerando QR Code...");
            System.out.println("2. Validando chave PIX do destinatário...");
            System.out.println("3. Sem taxas! Taxa de 0%");
            System.out.println("4. Processamento instantâneo...");
            System.out.println("5. Enviando notificação...");

            System.out.println("Pagamento aprovado! Valor final: R$ " + value);

        } else if (tipoPagamento.equals("BOLETO")) {
            System.out.println("=== Processando Pagamento via Boleto ===");

            // Validações específicas do boleto
            if (value < 10.0) {
                throw new IllegalArgumentException("Valor mínimo para boleto é R$ 10,00");
            }

            // Lógica de processamento
            System.out.println("1. Gerando código de barras...");
            System.out.println("2. Aplicando taxa de emissão: R$ 3,50");
            System.out.println("3. Definindo data de vencimento (3 dias)...");
            System.out.println("4. Enviando boleto por email...");

            double valorFinal = value + 3.50;
            System.out.println("Boleto gerado! Valor final: R$ " + valorFinal);
            System.out.println("Aguardando pagamento...");

        } else if (tipoPagamento.equals("PAYPAL")) {
            System.out.println("=== Processando Pagamento via PayPal ===");

            // Validações específicas do PayPal
            if (value < 2.0) {
                throw new IllegalArgumentException("Valor mínimo para PayPal é R$ 2,00");
            }

            // Lógica de processamento
            System.out.println("1. Redirecionando para PayPal...");
            System.out.println("2. Validando conta PayPal...");
            System.out.println("3. Aplicando taxa de 4.5%: R$ " + (value * 0.045));
            System.out.println("4. Processando transação internacional...");

            double valorFinal = value + (value * 0.045);
            System.out.println("Pagamento aprovado! Valor final: R$ " + valorFinal);

        } else if (tipoPagamento.equals("CRIPTOMOEDA")) {
            System.out.println("=== Processando Pagamento via Criptomoeda ===");

            // Validações específicas de cripto
            if (value < 50.0) {
                throw new IllegalArgumentException("Valor mínimo para cripto é R$ 50,00");
            }

            // Lógica de processamento
            System.out.println("1. Gerando endereço da carteira...");
            System.out.println("2. Convertendo para Bitcoin...");
            System.out.println("3. Aplicando taxa de rede: R$ " + (value * 0.01));
            System.out.println("4. Aguardando confirmações na blockchain...");
            System.out.println("5. Validando transação...");

            double valorFinal = value + (value * 0.01);
            System.out.println("Pagamento confirmado! Valor final: R$ " + valorFinal);

        } else {
            throw new IllegalArgumentException("Tipo de pagamento não suportado: " + tipoPagamento);
        }

        // Mais lógica comum após o pagamento
        registerTransaction(tipoPagamento, value);
        sendNotification(customerData);
    }

    private void registerTransaction(String tipo, double valor) {
        System.out.println("\n[LOG] Transação registrada: " + tipo + " - R$ " + valor);
    }

    private void sendNotification(String cliente) {
        System.out.println("[NOTIFICAÇÃO] Email enviado para: " + cliente);
    }
}
