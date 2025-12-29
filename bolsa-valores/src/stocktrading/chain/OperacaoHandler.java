package stocktrading.chain;

import stocktrading.model.Carteira;
import stocktrading.model.Cotacao;
import stocktrading.model.Ordem;

import java.util.List;

public interface OperacaoHandler {
    void setNext(OperacaoHandler next);
    boolean handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio);
}
