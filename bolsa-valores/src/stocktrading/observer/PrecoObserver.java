package stocktrading.observer;

import stocktrading.factorymethod.Acao;

public interface PrecoObserver {
    void atualizar(Acao acao, double precoAnterior, double precoAtual);
}

