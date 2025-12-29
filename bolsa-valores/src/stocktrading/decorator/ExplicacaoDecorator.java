package stocktrading.decorator;

import stocktrading.model.Carteira;
import stocktrading.util.MockDadosUtil;

/**
 * Explicação prática do padrão Decorator
 * 
 * PROBLEMA SEM DECORATOR:
 * =======================
 * Se você quiser todas as combinações possíveis de relatórios, precisaria criar:
 * 
 * 1. RelatorioBasico
 * 2. RelatorioComRentabilidade
 * 3. RelatorioComRisco
 * 4. RelatorioComImposto
 * 5. RelatorioComRentabilidadeERisco
 * 6. RelatorioComRentabilidadeEImposto
 * 7. RelatorioComRiscoEImposto
 * 8. RelatorioComRentabilidadeERiscoEImposto
 * 
 * Total: 8 classes (2^3 combinações)
 * 
 * Se adicionar mais 1 decorator (ex: RelatorioComDividendos):
 * Total: 16 classes (2^4 combinações)
 * 
 * Isso é uma EXPLOSÃO DE CLASSES!
 * 
 * SOLUÇÃO COM DECORATOR:
 * ======================
 * Você cria apenas:
 * 1. RelatorioBasico (componente base)
 * 2. RelatorioComRentabilidade (decorator)
 * 3. RelatorioComRisco (decorator)
 * 4. RelatorioComImposto (decorator)
 * 
 * Total: 4 classes para criar QUALQUER combinação!
 * 
 * Como funciona a composição:
 * ===========================
 * Quando você faz:
 *   Relatorio r = new RelatorioBasico();
 *   r = new RelatorioComRentabilidade(r);
 *   r = new RelatorioComImposto(r);
 * 
 * O que acontece internamente:
 * 
 * r.gerar(carteira) chama:
 *   → RelatorioComImposto.gerar(carteira)
 *     → super.gerar(carteira) chama o relatório decorado (RelatorioComRentabilidade)
 *       → RelatorioComRentabilidade.gerar(carteira)
 *         → super.gerar(carteira) chama o relatório decorado (RelatorioBasico)
 *           → RelatorioBasico.gerar(carteira) - imprime informações básicas
 *         → adiciona rentabilidade
 *     → adiciona imposto
 * 
 * Resultado: Básico + Rentabilidade + Imposto
 */
public class ExplicacaoDecorator {
    
    public static void demonstrarProblema() {
        System.out.println("\n=== PROBLEMA SEM DECORATOR ===");
        System.out.println("Para ter todas as combinações, você precisaria criar:");
        System.out.println("1. RelatorioBasico");
        System.out.println("2. RelatorioComRentabilidade");
        System.out.println("3. RelatorioComRisco");
        System.out.println("4. RelatorioComImposto");
        System.out.println("5. RelatorioComRentabilidadeERisco");
        System.out.println("6. RelatorioComRentabilidadeEImposto");
        System.out.println("7. RelatorioComRiscoEImposto");
        System.out.println("8. RelatorioComRentabilidadeERiscoEImposto");
        System.out.println("\nTotal: 8 classes (2^3 combinações)");
        System.out.println("Se adicionar mais 1 decorator: 16 classes (2^4)");
        System.out.println("Se adicionar mais 2 decorators: 32 classes (2^5)");
        System.out.println("Isso é uma EXPLOSÃO DE CLASSES!\n");
    }
    
    public static void demonstrarSolucao() {
        System.out.println("=== SOLUÇÃO COM DECORATOR ===");
        System.out.println("Você cria apenas:");
        System.out.println("1. RelatorioBasico (componente base)");
        System.out.println("2. RelatorioComRentabilidade (decorator)");
        System.out.println("3. RelatorioComRisco (decorator)");
        System.out.println("4. RelatorioComImposto (decorator)");
        System.out.println("\nTotal: 4 classes para criar QUALQUER combinação!\n");
    }
    
    public static void demonstrarComoFunciona() {
        System.out.println("=== COMO FUNCIONA A COMPOSIÇÃO ===\n");
        
        var acoes = MockDadosUtil.criarAcoesIniciais();
        var usuario = MockDadosUtil.criarUsuarioPadrao(acoes);
        Carteira carteira = usuario.getCarteiras().get(0);
        
        System.out.println("Passo 1: Criar relatório básico");
        System.out.println("  Relatorio r = new RelatorioBasico();");
        Relatorio r = new RelatorioBasico();
        
        System.out.println("\nPasso 2: Envolver com decorator de rentabilidade");
        System.out.println("  r = new RelatorioComRentabilidade(r);");
        System.out.println("  Agora 'r' é um RelatorioComRentabilidade que contém um RelatorioBasico");
        r = new RelatorioComRentabilidade(r);
        
        System.out.println("\nPasso 3: Envolver com decorator de imposto");
        System.out.println("  r = new RelatorioComImposto(r);");
        System.out.println("  Agora 'r' é um RelatorioComImposto que contém um RelatorioComRentabilidade que contém um RelatorioBasico");
        r = new RelatorioComImposto(r);
        
        System.out.println("\nPasso 4: Gerar relatório");
        System.out.println("  r.gerar(carteira);");
        System.out.println("\nO que acontece internamente:");
        System.out.println("  1. RelatorioComImposto.gerar() é chamado");
        System.out.println("  2. Ele chama super.gerar() que é RelatorioComRentabilidade.gerar()");
        System.out.println("  3. Que chama super.gerar() que é RelatorioBasico.gerar()");
        System.out.println("  4. RelatorioBasico imprime informações básicas");
        System.out.println("  5. RelatorioComRentabilidade adiciona rentabilidade");
        System.out.println("  6. RelatorioComImposto adiciona imposto");
        System.out.println("\nResultado:");
        System.out.println("----------------------------------------");
        r.gerar(carteira);
    }
    
    public static void demonstrarFlexibilidade() {
        System.out.println("\n\n=== FLEXIBILIDADE DO DECORATOR ===");
        System.out.println("Com apenas 4 classes, você pode criar QUALQUER combinação:\n");
        
        var acoes = MockDadosUtil.criarAcoesIniciais();
        var usuario = MockDadosUtil.criarUsuarioPadrao(acoes);
        Carteira carteira = usuario.getCarteiras().get(0);
        
        System.out.println("Combinação 1: Apenas básico");
        new RelatorioBasico().gerar(carteira);
        
        System.out.println("\nCombinação 2: Básico + Risco");
        Relatorio r2 = new RelatorioBasico();
        r2 = new RelatorioComRisco(r2);
        r2.gerar(carteira);
        
        System.out.println("\nCombinação 3: Básico + Rentabilidade + Risco + Imposto");
        Relatorio r3 = new RelatorioBasico();
        r3 = new RelatorioComRentabilidade(r3);
        r3 = new RelatorioComRisco(r3);
        r3 = new RelatorioComImposto(r3);
        r3.gerar(carteira);
        
        System.out.println("\nCombinação 4: Básico + Imposto + Rentabilidade (ordem diferente)");
        Relatorio r4 = new RelatorioBasico();
        r4 = new RelatorioComImposto(r4);
        r4 = new RelatorioComRentabilidade(r4);
        r4.gerar(carteira);
        
        System.out.println("\n✓ Todas essas combinações com apenas 4 classes!");
        System.out.println("✓ Sem Decorator: precisaria de 8+ classes");
        System.out.println("✓ Com Decorator: apenas 4 classes\n");
    }
    
    public static void main(String[] args) {
        demonstrarProblema();
        demonstrarSolucao();
        demonstrarComoFunciona();
        demonstrarFlexibilidade();
    }
}

