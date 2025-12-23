package com.patterns.stocktrading.service;

import com.patterns.stocktrading.factorymethod.Acao;
import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.model.Usuario;
import com.patterns.stocktrading.util.FormatoUtil;

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


