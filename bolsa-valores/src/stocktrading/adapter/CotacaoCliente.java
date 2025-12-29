package stocktrading.adapter;

import stocktrading.model.Cotacao;

public interface CotacaoCliente {
    Cotacao buscarCotacao(String codigo);
    String getNomeFonte();
}

