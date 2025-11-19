package br.com.bolsavalores.util;

import br.com.bolsavalores.model.Acao;
import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MockDadosUtil {
    public static List<Acao> criarAcoesIniciais() {
        List<Acao> acoes = new ArrayList<>();
        acoes.add(new Acao("PETR4", "ORDINARIA", "B3", 38.5));
        acoes.add(new Acao("VALE3", "ORDINARIA", "B3", 68.2));
        acoes.add(new Acao("ITUB4", "PREFERENCIAL", "B3", 29.7));
        acoes.add(new Acao("BOVA11", "ETF", "B3", 110.0));
        acoes.add(new Acao("AAPL34", "PREFERENCIAL", "B3", 50.0));
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


