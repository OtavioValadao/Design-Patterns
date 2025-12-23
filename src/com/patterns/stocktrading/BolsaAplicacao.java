package com.patterns.stocktrading;

import com.patterns.stocktrading.facade.BolsaFacade;
import com.patterns.stocktrading.model.Ordem;
import com.patterns.stocktrading.model.OrdemBuilder;

public class BolsaAplicacao {
    public static void main(String[] args) {
        BolsaAplicacao aplicacao = new BolsaAplicacao();
        aplicacao.executarDemonstracao();
    }

    public void executarDemonstracao() {
        System.out.println("Iniciando demonstração com BolsaFacade...");
        BolsaFacade bolsaFacade = new BolsaFacade();

        // 1. Consultar estado inicial
        System.out.println("\n--- Estado Inicial ---");
        bolsaFacade.consultarCarteira("Investidor Demo");

        // 2. Executar Compra
        System.out.println("\n--- Executando Compra ---");
        Ordem ordemCompra = new OrdemBuilder()
                .paraCompra()
                .comTipoAcao("ORDINARIA")
                .comCodigo("BOVA11")
                .comQuantidade(3)
                .comPrecoLimite(115.0)
                .naBolsa("B3")
                .comOrigem("APP")
                .comTipoExecucao("A_MERCADO")
                .comValidade("DIA")
                .build();
        bolsaFacade.executarCompra(ordemCompra);

        // 3. Executar Venda
        System.out.println("\n--- Executando Venda ---");
        Ordem ordemVenda = new OrdemBuilder()
                .paraVenda()
                .comTipoAcao("ORDINARIA")
                .comCodigo("PETR4")
                .comQuantidade(5)
                .comPrecoLimite(0.0)
                .naBolsa("B3")
                .comOrigem("WEB")
                .comTipoExecucao("A_MERCADO")
                .comValidade("ATE_CANCELAR")
                .build();
        bolsaFacade.executarVenda(ordemVenda);

        // 4. Consultar estado final
        System.out.println("\n--- Estado Final ---");
        bolsaFacade.consultarCarteira("Investidor Demo");

        // 5. Relatório
        System.out.println("\n--- Relatório ---");
        bolsaFacade.gerarRelatorioCarteira();
    }
}
