package stocktrading.observer;

import stocktrading.factorymethod.Acao;
import stocktrading.model.Usuario;
import stocktrading.util.FormatoUtil;

import java.util.List;

public class EmailNotificacaoObserver implements PrecoObserver {
    private final List<Usuario> usuarios;
    private final double limiteVariacao;

    public EmailNotificacaoObserver(List<Usuario> usuarios, double limiteVariacao) {
        this.usuarios = usuarios;
        this.limiteVariacao = limiteVariacao;
    }

    @Override
    public void atualizar(Acao acao, double precoAnterior, double precoAtual) {
        double variacao = 0.0;
        if (precoAnterior > 0.0) {
            variacao = (precoAtual - precoAnterior) / precoAnterior;
        }

        if (variacao >= limiteVariacao || variacao <= -limiteVariacao) {
            for (Usuario usuario : usuarios) {
                System.out.println("[EMAIL] Enviando notificação por email para " + usuario.getEmail());
                System.out.println("[EMAIL] Ação " + acao.getCodigo() + " variou " + 
                    FormatoUtil.formatarValor(variacao, "PORCENTAGEM"));
                System.out.println("[EMAIL] Preço anterior: R$ " + FormatoUtil.formatarValor(precoAnterior, "MOEDA"));
                System.out.println("[EMAIL] Preço atual: R$ " + FormatoUtil.formatarValor(precoAtual, "MOEDA"));
                System.out.println();
            }
        }
    }
}

