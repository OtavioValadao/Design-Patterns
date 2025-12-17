package br.com.bolsavalores.service;

import br.com.bolsavalores.chain.CompraHandler;
import br.com.bolsavalores.chain.OperacaoHandler;
import br.com.bolsavalores.chain.RelatorioHandler;
import br.com.bolsavalores.chain.ValidacaoParametrosHandler;
import br.com.bolsavalores.chain.ValidacaoRegrasHandler;
import br.com.bolsavalores.chain.VendaHandler;
import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;

import java.util.List;

public class OperacaoService {
    private final OperacaoHandler chain;

    public OperacaoService() {
        // 1. Primeiro, a validação de parâmetros (rápida)
        this.chain = new ValidacaoParametrosHandler();
        
        // 2. Depois, a validação de regras de negócio (mais lenta)
        OperacaoHandler validacaoRegras = new ValidacaoRegrasHandler();
        chain.setNext(validacaoRegras);

        // 3. Se tudo estiver válido, seguem os handlers de execução
        OperacaoHandler compraHandler = new CompraHandler();
        validacaoRegras.setNext(compraHandler);
        
        OperacaoHandler vendaHandler = new VendaHandler();
        compraHandler.setNext(vendaHandler);
        
        OperacaoHandler relatorioHandler = new RelatorioHandler();
        vendaHandler.setNext(relatorioHandler);
    }

    public void executarOperacao(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        System.out.println("\n--- Processando Ordem: " + ordem.getTipoOperacao() + " de " + ordem.getCodigoAcao() + " ---");
        chain.handle(ordem, carteira, cotacoes, tipoRelatorio);
    }
}
