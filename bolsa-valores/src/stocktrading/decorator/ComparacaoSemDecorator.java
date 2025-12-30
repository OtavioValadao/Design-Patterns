package stocktrading.decorator;

/**
 * EXEMPLO DO PROBLEMA SEM DECORATOR:
 * 
 * Se você NÃO usar Decorator, precisaria criar uma classe para CADA combinação:
 */
public class ComparacaoSemDecorator {
    
    // SEM DECORATOR - Você precisaria criar TODAS essas classes:
    
    // 1. RelatorioBasico
    // 2. RelatorioComRentabilidade
    // 3. RelatorioComRisco  
    // 4. RelatorioComImposto
    // 5. RelatorioComRentabilidadeERisco
    // 6. RelatorioComRentabilidadeEImposto
    // 7. RelatorioComRiscoEImposto
    // 8. RelatorioComRentabilidadeERiscoEImposto
    
    // Total: 8 classes para apenas 3 funcionalidades!
    // Se adicionar mais 1 funcionalidade (ex: dividendos):
    // Total: 16 classes (2^4)
    // Se adicionar mais 2 funcionalidades:
    // Total: 32 classes (2^5)
    
    // Isso é uma EXPLOSÃO DE CLASSES!
    
    // COM DECORATOR - Você cria apenas:
    // 1. RelatorioBasico
    // 2. RelatorioComRentabilidade (decorator)
    // 3. RelatorioComRisco (decorator)
    // 4. RelatorioComImposto (decorator)
    
    // Total: 4 classes para QUALQUER combinação!
}

