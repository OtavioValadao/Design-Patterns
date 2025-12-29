package stocktrading.adapter;

import stocktrading.model.Cotacao;

import java.util.Random;

public class B3ApiAdapter implements CotacaoCliente {
    private static final String FONTE_DADOS = "B3";

    @Override
    public Cotacao buscarCotacao(String codigo) {
        // Simula chamada à API da B3
        System.out.println("[B3ApiAdapter] Buscando cotação na API da B3 para: " + codigo);
        
        double precoBase = obterPrecoBase(codigo);
        double fatorFonte = 1.0; // B3 não aplica fator adicional
        
        Random random = new Random();
        double variacao = (random.nextDouble() - 0.5) * 2.0;
        double preco = precoBase * fatorFonte + variacao;
        
        if (preco < 1.0) {
            preco = 1.0;
        }

        return new Cotacao(codigo, preco, FONTE_DADOS);
    }

    @Override
    public String getNomeFonte() {
        return FONTE_DADOS;
    }

    private double obterPrecoBase(String codigo) {
        // Simula mapeamento de códigos para preços base da B3
        switch (codigo) {
            case "PETR4": return 38.5;
            case "VALE3": return 68.2;
            case "ITUB4": return 29.7;
            case "BOVA11": return 110.0;
            case "AAPL34": return 50.0;
            default: return 20.0;
        }
    }
}

