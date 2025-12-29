package stocktrading;

import stocktrading.command.CompraCommand;
import stocktrading.command.HistoricoComandos;
import stocktrading.command.RelatorioCommand;
import stocktrading.command.VendaCommand;
import stocktrading.facade.BolsaFacade;
import stocktrading.model.Ordem;
import stocktrading.model.OrdemBuilder;

public class BolsaAplicacao {
    public static void main(String[] args) {
        BolsaAplicacao aplicacao = new BolsaAplicacao();
        aplicacao.executarDemonstracao();
    }

    public void executarDemonstracao() {
        System.out.println("=== Demonstração do Padrão Command ===");
        BolsaFacade bolsaFacade = new BolsaFacade();
        HistoricoComandos historico = new HistoricoComandos();

        // 1. Consultar estado inicial
        System.out.println("\n--- Estado Inicial ---");
        bolsaFacade.consultarCarteira("Investidor Demo");

        // 2. Criar e executar CompraCommand
        System.out.println("\n--- Executando CompraCommand ---");
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
        CompraCommand compraCommand = new CompraCommand(bolsaFacade, ordemCompra);
        historico.executarComando(compraCommand);

        // 3. Criar e executar VendaCommand
        System.out.println("\n--- Executando VendaCommand ---");
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
        VendaCommand vendaCommand = new VendaCommand(bolsaFacade, ordemVenda);
        historico.executarComando(vendaCommand);

        // 4. Criar e executar RelatorioCommand
        System.out.println("\n--- Executando RelatorioCommand ---");
        RelatorioCommand relatorioCommand = new RelatorioCommand(bolsaFacade);
        historico.executarComando(relatorioCommand);

        // 5. Consultar estado após comandos
        System.out.println("\n--- Estado Após Comandos ---");
        bolsaFacade.consultarCarteira("Investidor Demo");

        // 6. Demonstrar histórico de comandos
        System.out.println("\n--- Histórico de Comandos ---");
        historico.listarHistorico();

        // 7. Demonstrar desfazer último comando
        System.out.println("\n--- Desfazendo Último Comando ---");
        historico.desfazerUltimo();

        // 8. Consultar estado após desfazer
        System.out.println("\n--- Estado Após Desfazer ---");
        bolsaFacade.consultarCarteira("Investidor Demo");

        // 9. Demonstrar reexecução de comando
        System.out.println("\n--- Reexecutando Primeiro Comando ---");
        historico.reexecutarComando(0);

        // 10. Estado final
        System.out.println("\n--- Estado Final ---");
        bolsaFacade.consultarCarteira("Investidor Demo");
        historico.listarHistorico();
    }
}
