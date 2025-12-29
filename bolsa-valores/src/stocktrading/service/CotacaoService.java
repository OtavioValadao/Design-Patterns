package stocktrading.service;

import stocktrading.adapter.CotacaoCliente;
import stocktrading.model.Cotacao;

public class CotacaoService {
    private final CotacaoCliente cotacaoCliente;

    public CotacaoService(CotacaoCliente cotacaoCliente) {
        this.cotacaoCliente = cotacaoCliente;
    }

    public Cotacao buscarCotacaoAtual(String codigo, String fonteDados) {
        // Delega a busca de cotação para o adaptador específico
        // A fonte de dados é gerenciada pelo adaptador, não mais pelo serviço
        return cotacaoCliente.buscarCotacao(codigo);
    }
}


