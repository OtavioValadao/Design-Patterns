package stocktrading.service;

import stocktrading.adapter.CotacaoCliente;
import stocktrading.factorymethod.Acao;
import stocktrading.model.Cotacao;
import stocktrading.observer.PrecoObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CotacaoService {
    private final CotacaoCliente cotacaoCliente;
    private final List<PrecoObserver> observers;
    private final Map<String, Double> precosAnteriores;

    public CotacaoService(CotacaoCliente cotacaoCliente) {
        this.cotacaoCliente = cotacaoCliente;
        this.observers = new ArrayList<>();
        this.precosAnteriores = new HashMap<>();
    }

    public void adicionarObserver(PrecoObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removerObserver(PrecoObserver observer) {
        observers.remove(observer);
    }

    private void notificarObservers(Acao acao, double precoAnterior, double precoAtual) {
        for (PrecoObserver observer : observers) {
            observer.atualizar(acao, precoAnterior, precoAtual);
        }
    }

    public Cotacao buscarCotacaoAtual(String codigo, String fonteDados) {
        return cotacaoCliente.buscarCotacao(codigo);
    }
    
    public boolean verificarMudancaPreco(String codigo, double precoAtual) {
        Double precoAnterior = precosAnteriores.get(codigo);
        if (precoAnterior != null && precoAnterior != precoAtual) {
            precosAnteriores.put(codigo, precoAtual);
            return true;
        }
        if (precoAnterior == null) {
            precosAnteriores.put(codigo, precoAtual);
        }
        return false;
    }

    public void registrarPrecoInicial(String codigo, double preco) {
        precosAnteriores.put(codigo, preco);
    }

    public void notificarMudancaPreco(Acao acao, double precoAnterior, double precoAtual) {
        notificarObservers(acao, precoAnterior, precoAtual);
        precosAnteriores.put(acao.getCodigo(), precoAtual);
    }
}


