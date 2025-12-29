package stocktrading.template;

import stocktrading.model.Carteira;
import stocktrading.model.Cotacao;
import stocktrading.model.Ordem;

import java.util.List;

public abstract class ProcessadorOrdem {

    public final void processar(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        if (validar(ordem, carteira, cotacoes)) {
            executar(ordem, carteira, cotacoes);
            gerarRelatorio(ordem, carteira, tipoRelatorio);
        }
    }

    protected abstract boolean validar(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes);

    protected abstract void executar(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes);

    protected abstract void gerarRelatorio(Ordem ordem, Carteira carteira, String tipoRelatorio);
}
