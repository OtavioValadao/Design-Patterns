package com.patterns.stocktrading.util;

import com.patterns.stocktrading.factorymethod.Acao;
import com.patterns.stocktrading.factorymethod.AcaoFactory;
import com.patterns.stocktrading.factorymethod.TipoDeAcao;
import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MockDadosUtil {


    public static List<Acao> criarAcoesIniciais() {
        List<Acao> acoes = new ArrayList<>();

        AcaoFactory acaoFactory = new AcaoFactory();

        acoes.add(acaoFactory.criar(TipoDeAcao.ORDINARIA, "PETR4", "B3", 38.5));
        acoes.add(acaoFactory.criar(TipoDeAcao.ORDINARIA, "VALE3", "B3", 68.2));
        acoes.add(acaoFactory.criar(TipoDeAcao.PREFERENCIAL, "ITUB4", "B3", 29.7));
        acoes.add(acaoFactory.criar(TipoDeAcao.ETF, "BOVA11", "B3", 111.0));
        acoes.add(acaoFactory.criar(TipoDeAcao.PREFERENCIAL, "AAPL34", "B3", 50.0));
        return acoes;
    }

    public static Usuario criarUsuarioPadrao(List<Acao> acoes) {
        Usuario usuario = new Usuario("Investidor Demo", "demo@bolsa.com");
        Carteira carteiraPrincipal = new Carteira("Carteira Principal", 10000.0);
        if (!acoes.isEmpty()) {
            carteiraPrincipal.adicionarOuAtualizarPosicao(acoes.get(0), 10, acoes.get(0).getPrecoAtual());
        }
        if (acoes.size() > 1) {
            carteiraPrincipal.adicionarOuAtualizarPosicao(acoes.get(1), 5, acoes.get(1).getPrecoAtual());
        }
        usuario.adicionarCarteira(carteiraPrincipal);
        return usuario;
    }
}


