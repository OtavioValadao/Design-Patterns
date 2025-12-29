package stocktrading.command;

import stocktrading.facade.BolsaFacade;

public class RelatorioCommand implements CommandOperacao {
    private final BolsaFacade bolsaFacade;

    public RelatorioCommand(BolsaFacade bolsaFacade) {
        this.bolsaFacade = bolsaFacade;
    }

    @Override
    public void executar() {
        bolsaFacade.gerarRelatorioCarteira();
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

