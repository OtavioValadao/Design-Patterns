package stocktrading.adapter;

import stocktrading.model.Cotacao;

/**
 * Classe de demonstração do padrão Adapter
 * Mostra como diferentes adaptadores podem ser usados para buscar cotações
 * de diferentes fontes de dados sem alterar a lógica de negócio.
 */
public class DemonstracaoAdapter {
    
    public static void demonstrar() {
        System.out.println("\n=== Demonstração do Padrão Adapter ===\n");
        
        String codigoAcao = "PETR4";
        
        // Demonstra uso de diferentes adaptadores
        System.out.println("Buscando cotação para " + codigoAcao + " em diferentes fontes:\n");
        
        // 1. Usando adaptador B3
        CotacaoCliente clienteB3 = CotacaoClienteFactory.criar("B3");
        Cotacao cotacaoB3 = clienteB3.buscarCotacao(codigoAcao);
        System.out.println("Resultado B3: R$ " + String.format("%.2f", cotacaoB3.getPreco()) + 
                          " (Fonte: " + cotacaoB3.getFonteDados() + ")\n");
        
        // 2. Usando adaptador NYSE
        CotacaoCliente clienteNyse = CotacaoClienteFactory.criar("NYSE");
        Cotacao cotacaoNyse = clienteNyse.buscarCotacao(codigoAcao);
        System.out.println("Resultado NYSE: R$ " + String.format("%.2f", cotacaoNyse.getPreco()) + 
                          " (Fonte: " + cotacaoNyse.getFonteDados() + ")\n");
        
        // 3. Usando adaptador API Externa
        CotacaoCliente clienteExterna = CotacaoClienteFactory.criar("EXTERNA");
        Cotacao cotacaoExterna = clienteExterna.buscarCotacao(codigoAcao);
        System.out.println("Resultado API Externa: R$ " + String.format("%.2f", cotacaoExterna.getPreco()) + 
                          " (Fonte: " + cotacaoExterna.getFonteDados() + ")\n");
        
        System.out.println("=== Benefícios do Padrão Adapter ===");
        System.out.println("✓ Desacoplamento: CotacaoService não precisa conhecer detalhes das APIs");
        System.out.println("✓ Flexibilidade: Fácil adicionar novas fontes de dados");
        System.out.println("✓ Manutenibilidade: Mudanças em uma API não afetam outras");
        System.out.println("✓ Testabilidade: Fácil criar mocks para testes\n");
    }
}

