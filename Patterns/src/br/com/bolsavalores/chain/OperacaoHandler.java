package br.com.bolsavalores.chain;

import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;

import java.util.List;

public interface OperacaoHandler {
    void setNext(OperacaoHandler next);
    void handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio);
}
