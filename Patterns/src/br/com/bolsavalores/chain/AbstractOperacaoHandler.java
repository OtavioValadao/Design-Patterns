package br.com.bolsavalores.chain;

import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;

import java.util.List;

public abstract class AbstractOperacaoHandler implements OperacaoHandler {
    private OperacaoHandler next;

    @Override
    public void setNext(OperacaoHandler next) {
        this.next = next;
    }

    protected boolean callNext(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        if (next != null) {
            return next.handle(ordem, carteira, cotacoes, tipoRelatorio);
        }
        return true;
    }
}
