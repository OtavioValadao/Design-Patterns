package com.patterns.stocktrading.service;

import com.patterns.stocktrading.chain.CompraHandler;
import com.patterns.stocktrading.chain.OperacaoHandler;
import com.patterns.stocktrading.chain.RelatorioHandler;
import com.patterns.stocktrading.chain.ValidacaoParametrosHandler;
import com.patterns.stocktrading.chain.ValidacaoRegrasHandler;
import com.patterns.stocktrading.chain.VendaHandler;
import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.model.Cotacao;
import com.patterns.stocktrading.model.Ordem;

import java.util.List;

public class OperacaoService {
    private final OperacaoHandler chain;

    public OperacaoService() {
        // Monta a cadeia completa: Validações -> Compra -> Venda -> Relatório
        this.chain = new ValidacaoParametrosHandler();

        OperacaoHandler validacaoRegras = new ValidacaoRegrasHandler();
        chain.setNext(validacaoRegras);

        OperacaoHandler compraHandler = new CompraHandler();
        validacaoRegras.setNext(compraHandler);

        OperacaoHandler vendaHandler = new VendaHandler();
        compraHandler.setNext(vendaHandler);

        OperacaoHandler relatorioHandler = new RelatorioHandler();
        vendaHandler.setNext(relatorioHandler);
    }

    public void executarOperacao(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        System.out.println("\n--- Processando Ordem: " + ordem.getTipoOperacao() + " de " + ordem.getCodigoAcao() + " ---");
        
        // A cadeia agora cuida de tudo: validação e roteamento para o Template Method correto
        chain.handle(ordem, carteira, cotacoes, tipoRelatorio);
    }
}
