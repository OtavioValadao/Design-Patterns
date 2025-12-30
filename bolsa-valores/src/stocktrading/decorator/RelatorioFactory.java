package stocktrading.decorator;

public class RelatorioFactory {
    
    /**
     * Cria um relatório básico sem decorators
     */
    public static Relatorio criarBasico() {
        return new RelatorioBasico();
    }
    
    /**
     * Cria um relatório com rentabilidade
     */
    public static Relatorio criarComRentabilidade() {
        return new RelatorioComRentabilidade(new RelatorioBasico());
    }
    
    /**
     * Cria um relatório com risco
     */
    public static Relatorio criarComRisco() {
        return new RelatorioComRisco(new RelatorioBasico());
    }
    
    /**
     * Cria um relatório com imposto
     */
    public static Relatorio criarComImposto() {
        return new RelatorioComImposto(new RelatorioBasico());
    }
    
    /**
     * Cria um relatório completo com rentabilidade, risco e imposto
     */
    public static Relatorio criarCompleto() {
        Relatorio relatorio = new RelatorioBasico();
        relatorio = new RelatorioComRentabilidade(relatorio);
        relatorio = new RelatorioComRisco(relatorio);
        relatorio = new RelatorioComImposto(relatorio);
        return relatorio;
    }
    
    /**
     * Cria um relatório com rentabilidade e risco
     */
    public static Relatorio criarComRentabilidadeERisco() {
        Relatorio relatorio = new RelatorioBasico();
        relatorio = new RelatorioComRentabilidade(relatorio);
        relatorio = new RelatorioComRisco(relatorio);
        return relatorio;
    }
    
    /**
     * Cria um relatório com rentabilidade e imposto
     */
    public static Relatorio criarComRentabilidadeEImposto() {
        Relatorio relatorio = new RelatorioBasico();
        relatorio = new RelatorioComRentabilidade(relatorio);
        relatorio = new RelatorioComImposto(relatorio);
        return relatorio;
    }
    
    /**
     * Cria um relatório com risco e imposto
     */
    public static Relatorio criarComRiscoEImposto() {
        Relatorio relatorio = new RelatorioBasico();
        relatorio = new RelatorioComRisco(relatorio);
        relatorio = new RelatorioComImposto(relatorio);
        return relatorio;
    }
}

