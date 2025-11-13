package strategy.pattern.first_solution;

public class PaymentMethods {

    public static void creditCard(final Double amount){
        System.out.println("=== Processando Pagamento com Cartão de Crédito ===");

        // Validações específicas do cartão
        if (amount < 5.0) {
            throw new IllegalArgumentException("Valor mínimo para cartão é R$ 5,00");
        }

        // Lógica de processamento
        System.out.println("1. Validando número do cartão...");
        System.out.println("2. Verificando limite disponível...");
        System.out.println("3. Aplicando taxa de 2.5%: R$ " + (amount * 0.025));
        System.out.println("4. Enviando para operadora...");
        System.out.println("5. Aguardando aprovação...");

        double valorFinal = amount + (amount * 0.025);
        System.out.println("Pagamento aprovado! Valor final: R$ " + valorFinal);
    }

    public static void debitCard(final Double amount){
        System.out.println("=== Processando Pagamento com Cartão de Débito ===");

        // Validações específicas do débito
        if (amount < 1.0) {
            throw new IllegalArgumentException("Valor mínimo para débito é R$ 1,00");
        }

        // Lógica de processamento
        System.out.println("1. Validando número do cartão...");
        System.out.println("2. Verificando saldo em conta...");
        System.out.println("3. Aplicando taxa de 1.5%: R$ " + (amount * 0.015));
        System.out.println("4. Debitando da conta...");

        double valorFinal = amount + (amount * 0.015);
        System.out.println("Pagamento aprovado! Valor final: R$ " + valorFinal);
    }

    public static void pix(final Double amount){
        System.out.println("=== Processando Pagamento via PIX ===");

        // Validações específicas do PIX
        if (amount < 0.01) {
            throw new IllegalArgumentException("Valor mínimo para PIX é R$ 0,01");
        }

        // Lógica de processamento
        System.out.println("1. Gerando QR Code...");
        System.out.println("2. Validando chave PIX do destinatário...");
        System.out.println("3. Sem taxas! Taxa de 0%");
        System.out.println("4. Processamento instantâneo...");
        System.out.println("5. Enviando notificação...");

        System.out.println("Pagamento aprovado! Valor final: R$ " + amount);
    }

    public static void boleto(final Double amount){
        System.out.println("=== Processando Pagamento via Boleto ===");

        // Validações específicas do boleto
        if (amount < 10.0) {
            throw new IllegalArgumentException("Valor mínimo para boleto é R$ 10,00");
        }

        // Lógica de processamento
        System.out.println("1. Gerando código de barras...");
        System.out.println("2. Aplicando taxa de emissão: R$ 3,50");
        System.out.println("3. Definindo data de vencimento (3 dias)...");
        System.out.println("4. Enviando boleto por email...");

        double valorFinal = amount + 3.50;
        System.out.println("Boleto gerado! Valor final: R$ " + valorFinal);
        System.out.println("Aguardando pagamento...");
    }

    public static void paypal(final Double amount){
        System.out.println("=== Processando Pagamento via PayPal ===");

        // Validações específicas do PayPal
        if (amount < 2.0) {
            throw new IllegalArgumentException("Valor mínimo para PayPal é R$ 2,00");
        }

        // Lógica de processamento
        System.out.println("1. Redirecionando para PayPal...");
        System.out.println("2. Validando conta PayPal...");
        System.out.println("3. Aplicando taxa de 4.5%: R$ " + (amount * 0.045));
        System.out.println("4. Processando transação internacional...");

        double valorFinal = amount + (amount * 0.045);
        System.out.println("Pagamento aprovado! Valor final: R$ " + valorFinal);
    }

    public static void cripto(final Double amount){
        System.out.println("=== Processando Pagamento via Criptomoeda ===");

        // Validações específicas de cripto
        if (amount < 50.0) {
            throw new IllegalArgumentException("Valor mínimo para cripto é R$ 50,00");
        }

        // Lógica de processamento
        System.out.println("1. Gerando endereço da carteira...");
        System.out.println("2. Convertendo para Bitcoin...");
        System.out.println("3. Aplicando taxa de rede: R$ " + (amount * 0.01));
        System.out.println("4. Aguardando confirmações na blockchain...");
        System.out.println("5. Validando transação...");

        double valorFinal = amount + (amount * 0.01);
        System.out.println("Pagamento confirmado! Valor final: R$ " + valorFinal);
    }
}
