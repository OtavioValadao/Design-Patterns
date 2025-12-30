package stocktrading.command;

import stocktrading.decorator.Relatorio;
import stocktrading.facade.BolsaFacade;

public class RelatorioCommand implements CommandOperacao {
    private final BolsaFacade bolsaFacade;
    private final Relatorio relatorio;

    public RelatorioCommand(BolsaFacade bolsaFacade) {
        this.bolsaFacade = bolsaFacade;
        this.relatorio = null; // Usa relatório completo por padrão
    }

    public RelatorioCommand(BolsaFacade bolsaFacade, Relatorio relatorio) {
        this.bolsaFacade = bolsaFacade;
        this.relatorio = relatorio;
    }

    @Override
    public void executar() {
        if (relatorio != null) {
            bolsaFacade.gerarRelatorioCustomizado(relatorio);
        } else {
            bolsaFacade.gerarRelatorioCarteira();
        }
        System.out.println("✓ RelatorioCommand executado");
    }

    @Override
    public void desfazer() {
        // Relatórios não podem ser desfeitos, apenas informativos
        System.out.println("Relatórios são apenas informativos e não podem ser desfeitos.");
    }

    @Override
    public String getDescricao() {
        return "Geração de relatório da carteira";
    }
}

