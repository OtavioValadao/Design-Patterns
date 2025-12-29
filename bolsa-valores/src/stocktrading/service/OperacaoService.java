package stocktrading.service;

import stocktrading.chain.CompraHandler;
import stocktrading.chain.OperacaoHandler;
import stocktrading.chain.RelatorioHandler;
import stocktrading.chain.ValidacaoParametrosHandler;
import stocktrading.chain.ValidacaoRegrasHandler;
import stocktrading.chain.VendaHandler;
import stocktrading.model.Carteira;
import stocktrading.model.Cotacao;
import stocktrading.model.Ordem;

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
