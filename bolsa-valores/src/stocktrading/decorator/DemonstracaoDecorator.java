package stocktrading.decorator;

import stocktrading.model.Carteira;
import stocktrading.util.MockDadosUtil;

/**
 * Classe de demonstração do padrão Decorator
 * Mostra como diferentes combinações de relatórios podem ser criadas
 * sem explosão de classes, usando composição flexível.
 */
public class DemonstracaoDecorator {
    
    public static void demonstrar() {
        System.out.println("\n=== Demonstração do Padrão Decorator ===\n");
        
        // Cria uma carteira de exemplo
        var acoes = MockDadosUtil.criarAcoesIniciais();
        var usuario = MockDadosUtil.criarUsuarioPadrao(acoes);
        Carteira carteira = usuario.getCarteiras().get(0);
        
        System.out.println("1. Relatório Básico:");
        System.out.println("-------------------");
        Relatorio relatorioBasico = RelatorioFactory.criarBasico();
        relatorioBasico.gerar(carteira);
        
        System.out.println("\n2. Relatório com Rentabilidade:");
        System.out.println("-------------------------------");
        Relatorio relatorioComRentabilidade = RelatorioFactory.criarComRentabilidade();
        relatorioComRentabilidade.gerar(carteira);
        
        System.out.println("\n3. Relatório com Risco:");
        System.out.println("----------------------");
        Relatorio relatorioComRisco = RelatorioFactory.criarComRisco();
        relatorioComRisco.gerar(carteira);
        
        System.out.println("\n4. Relatório com Imposto:");
        System.out.println("------------------------");
        Relatorio relatorioComImposto = RelatorioFactory.criarComImposto();
        relatorioComImposto.gerar(carteira);
        
        System.out.println("\n5. Relatório com Rentabilidade e Risco:");
        System.out.println("----------------------------------------");
        Relatorio relatorioComRentabilidadeERisco = RelatorioFactory.criarComRentabilidadeERisco();
        relatorioComRentabilidadeERisco.gerar(carteira);
        
        System.out.println("\n6. Relatório Completo (Rentabilidade + Risco + Imposto):");
        System.out.println("----------------------------------------------------------");
        Relatorio relatorioCompleto = RelatorioFactory.criarCompleto();
        relatorioCompleto.gerar(carteira);
        
        System.out.println("\n7. Relatório Customizado (criado manualmente):");
        System.out.println("-----------------------------------------------");
        // Cria um relatório customizado: básico + imposto + rentabilidade
        Relatorio relatorioCustomizado = new RelatorioBasico();
        relatorioCustomizado = new RelatorioComImposto(relatorioCustomizado);
        relatorioCustomizado = new RelatorioComRentabilidade(relatorioCustomizado);
        relatorioCustomizado.gerar(carteira);
        
        System.out.println("\n=== Benefícios do Padrão Decorator ===");
        System.out.println("✓ Composição flexível: Combine funcionalidades sem criar muitas classes");
        System.out.println("✓ Extensibilidade: Adicione novos decorators sem modificar código existente");
        System.out.println("✓ Reutilização: Cada decorator pode ser usado independentemente");
        System.out.println("✓ Manutenibilidade: Mudanças em um decorator não afetam outros");
        System.out.println("✓ Evita explosão de classes: Não precisa criar classe para cada combinação\n");
    }
}

