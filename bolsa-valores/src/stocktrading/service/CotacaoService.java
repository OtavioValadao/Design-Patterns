package stocktrading.service;

import stocktrading.model.Cotacao;

import java.util.Random;

public class CotacaoService {
    public Cotacao buscarCotacaoAtual(String codigo, String fonteDados) {
        double precoBase = 0.0;
        if ("PETR4".equals(codigo)) {
            precoBase = 38.5;
        } else if ("VALE3".equals(codigo)) {
            precoBase = 68.2;
        } else if ("ITUB4".equals(codigo)) {
            precoBase = 29.7;
        } else if ("BOVA11".equals(codigo)) {
            precoBase = 110.0;
        } else if ("AAPL34".equals(codigo)) {
            precoBase = 50.0;
        } else {
            precoBase = 20.0;
        }

        double fatorFonte = 1.0;
        if ("B3".equals(fonteDados)) {
            fatorFonte = 1.0;
        } else if ("NYSE".equals(fonteDados)) {
            fatorFonte = 1.1;
        } else if ("EXTERNA".equals(fonteDados)) {
            fatorFonte = 0.95;
        } else {
            fatorFonte = 1.0;
        }

        Random random = new Random();
        double variacao = (random.nextDouble() - 0.5) * 2.0;
        double preco = precoBase * fatorFonte + variacao;
        if (preco < 1.0) {
            preco = 1.0;
        }

        return new Cotacao(codigo, preco, fonteDados);
    }
}


