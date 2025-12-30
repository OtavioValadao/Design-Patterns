package stocktrading.observer;

import stocktrading.factorymethod.Acao;
import stocktrading.model.Usuario;
import stocktrading.util.FormatoUtil;

import java.util.List;

public class SmsNotificacaoObserver implements PrecoObserver {
    private final List<Usuario> usuarios;
    private final double limiteVariacao;

    public SmsNotificacaoObserver(List<Usuario> usuarios, double limiteVariacao) {
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
                System.out.println("[SMS] Enviando notificação por SMS para " + usuario.getNome());
                System.out.println("[SMS] " + acao.getCodigo() + ": " + 
                    FormatoUtil.formatarValor(variacao, "PORCENTAGEM") + 
                    " (R$ " + FormatoUtil.formatarValor(precoAnterior, "MOEDA") + 
                    " -> R$ " + FormatoUtil.formatarValor(precoAtual, "MOEDA") + ")");
                System.out.println();
            }
        }
    }
}

