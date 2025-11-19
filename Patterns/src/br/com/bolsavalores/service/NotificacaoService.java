package br.com.bolsavalores.service;

import br.com.bolsavalores.model.Acao;
import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Usuario;
import br.com.bolsavalores.util.FormatoUtil;

import java.util.List;

public class NotificacaoService {
    public void notificarUsuariosPorPreco(List<Usuario> usuarios, Acao acao, double precoAnterior, double precoAtual, double limiteVariacao) {
        double variacao = 0.0;
        if (precoAnterior > 0.0) {
            variacao = (precoAtual - precoAnterior) / precoAnterior;
        }
        if (variacao >= limiteVariacao || variacao <= -limiteVariacao) {
            for (Usuario usuario : usuarios) {
                for (Carteira carteira : usuario.getCarteiras()) {
                    System.out.println("Enviando notificacao para " + usuario.getEmail() + " sobre a carteira " + carteira.getNome());
                    System.out.println("Acao " + acao.getCodigo() + " variou " + FormatoUtil.formatarValor(variacao, "PORCENTAGEM"));
                }
            }
        }
    }
}


