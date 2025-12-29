package stocktrading.decorator;

import stocktrading.model.Carteira;
import stocktrading.util.MockDadosUtil;

/**
 * Exemplo visual mostrando COMO o Decorator funciona passo a passo
 */
public class ExemploVisualDecorator {
    
    public static void main(String[] args) {
        var acoes = MockDadosUtil.criarAcoesIniciais();
        var usuario = MockDadosUtil.criarUsuarioPadrao(acoes);
        Carteira carteira = usuario.getCarteiras().get(0);
        
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  EXEMPLO VISUAL: Como o Decorator Funciona");
        System.out.println("═══════════════════════════════════════════════════════\n");
        
        System.out.println("PASSO 1: Criar relatório básico");
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("Relatorio r = new RelatorioBasico();");
        System.out.println("┌─────────────────────┐");
        System.out.println("│  RelatorioBasico     │");
        System.out.println("└─────────────────────┘");
        Relatorio r = new RelatorioBasico();
        
        System.out.println("\nPASSO 2: Envolver com decorator de rentabilidade");
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("r = new RelatorioComRentabilidade(r);");
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│ RelatorioComRentabilidade    │");
        System.out.println("│  ┌─────────────────────┐     │");
        System.out.println("│  │  RelatorioBasico     │     │");
        System.out.println("│  └─────────────────────┘     │");
        System.out.println("└──────────────────────────────┘");
        r = new RelatorioComRentabilidade(r);
        
        System.out.println("\nPASSO 3: Envolver com decorator de imposto");
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("r = new RelatorioComImposto(r);");
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│ RelatorioComImposto          │");
        System.out.println("│  ┌──────────────────────────┐│");
        System.out.println("│  │ RelatorioComRentabilidade ││");
        System.out.println("│  │  ┌─────────────────────┐││");
        System.out.println("│  │  │  RelatorioBasico     │││");
        System.out.println("│  │  └─────────────────────┘││");
        System.out.println("│  └──────────────────────────┘│");
        System.out.println("└──────────────────────────────┘");
        r = new RelatorioComImposto(r);
        
        System.out.println("\nPASSO 4: Chamar gerar() - Veja a ordem de execução:");
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("r.gerar(carteira);");
        System.out.println("\nFluxo de execução:");
        System.out.println("  1. ↓ RelatorioComImposto.gerar()");
        System.out.println("     ↓   super.gerar() → RelatorioComRentabilidade.gerar()");
        System.out.println("     ↓     super.gerar() → RelatorioBasico.gerar()");
        System.out.println("     ↓       [Imprime: Carteira, Valor Total, Saldo]");
        System.out.println("     ↑     [Adiciona: Rentabilidade]");
        System.out.println("     ↑   [Adiciona: Imposto]");
        System.out.println("     ↑ [Retorna]\n");
        
        System.out.println("RESULTADO:");
        System.out.println("─────────────────────────────────────────────────────");
        r.gerar(carteira);
        
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("  POR QUE ISSO É MELHOR?");
        System.out.println("═══════════════════════════════════════════════════════\n");
        
        System.out.println("SEM DECORATOR:");
        System.out.println("  ❌ Precisaria criar classe: RelatorioComRentabilidadeEImposto");
        System.out.println("  ❌ Se adicionar Risco: mais 4 classes");
        System.out.println("  ❌ Total: 8+ classes para 3 funcionalidades");
        
        System.out.println("\nCOM DECORATOR:");
        System.out.println("  ✅ Apenas 4 classes (Basico + 3 decorators)");
        System.out.println("  ✅ Compõe qualquer combinação em tempo de execução");
        System.out.println("  ✅ Adicionar nova funcionalidade = 1 classe nova");
        System.out.println("  ✅ Não precisa criar classe para cada combinação");
        
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("  EXEMPLO: Criar combinação diferente");
        System.out.println("═══════════════════════════════════════════════════════\n");
        
        System.out.println("Quer apenas Básico + Risco?");
        System.out.println("─────────────────────────────────────────────────────");
        Relatorio r2 = new RelatorioBasico();
        r2 = new RelatorioComRisco(r2);
        r2.gerar(carteira);
        
        System.out.println("\n✅ Mesma estrutura, combinação diferente!");
        System.out.println("✅ Sem precisar criar nova classe!");
    }
}

