package br.com.bolsavalores.util;

public class FormatoUtil {
    public static String formatarValor(double valor, String tipo) {
        if ("MOEDA".equals(tipo)) {
            return String.format("R$ %.2f", valor);
        } else if ("PORCENTAGEM".equals(tipo)) {
            return String.format("%.2f%%", valor * 100);
        } else if ("PADRAO".equals(tipo)) {
            return String.format("%.2f", valor);
        } else {
            return String.valueOf(valor);
        }
    }
}


