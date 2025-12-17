package br.com.bolsavalores.chain;

import br.com.bolsavalores.factorymethod.Acao;
import br.com.bolsavalores.factorymethod.AcaoFactory;
import br.com.bolsavalores.factorymethod.TipoDeAcao;
import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;
import br.com.bolsavalores.util.CalculoUtil;
import br.com.bolsavalores.util.FormatoUtil;

import java.util.List;

public class CompraHandler extends AbstractOperacaoHandler {
    @Override
    public void handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        if ("COMPRA".equals(ordem.getTipoOperacao())) {
            Cotacao cotacaoEncontrada = cotacoes.stream()
                    .filter(c -> c.getCodigo().equals(ordem.getCodigoAcao()))
                    .findFirst()
                    .orElse(null);

            if (cotacaoEncontrada == null) {
                System.out.println("Não foi possível encontrar cotação para " + ordem.getCodigoAcao());
                return;
            }

            double precoExecucao = cotacaoEncontrada.getPreco();
            if (ordem.getPrecoLimite() > 0.0 && ordem.getPrecoLimite() < precoExecucao) {
                precoExecucao = ordem.getPrecoLimite();
            }
            double valorTotal = precoExecucao * ordem.getQuantidade();
            if (carteira.getSaldoDisponivel() < valorTotal) {
                System.out.println("Saldo insuficiente na carteira " + carteira.getNome());
                return;
            }
            carteira.setSaldoDisponivel(carteira.getSaldoDisponivel() - valorTotal);
            AcaoFactory acaoFactory = new AcaoFactory();
            Acao acao = acaoFactory.criar(TipoDeAcao.valueOf(ordem.getTipoAcao()), ordem.getCodigoAcao(), ordem.getBolsa(), cotacaoEncontrada.getPreco());
            carteira.adicionarOuAtualizarPosicao(acao, ordem.getQuantidade(), precoExecucao);

            if ("SIMPLIFICADO".equals(tipoRelatorio)) {
                System.out.println("Compra realizada de " + ordem.getQuantidade() + " de " + ordem.getCodigoAcao() + " por " + FormatoUtil.formatarValor(precoExecucao, "MOEDA"));
            } else if ("DETALHADO".equals(tipoRelatorio)) {
                System.out.println("Compra realizada de " + ordem.getQuantidade() + " de " + ordem.getCodigoAcao());
                System.out.println("Preco de execucao " + FormatoUtil.formatarValor(precoExecucao, "MOEDA"));
                System.out.println("Valor total " + FormatoUtil.formatarValor(valorTotal, "MOEDA"));
                System.out.println("Saldo restante " + FormatoUtil.formatarValor(carteira.getSaldoDisponivel(), "MOEDA"));
                double indicador = CalculoUtil.calcularIndicadorCarteira(carteira, "RENTABILIDADE");
                System.out.println("Indicador de rentabilidade apos a operacao " + FormatoUtil.formatarValor(indicador, "PORCENTAGEM"));
            } else {
                System.out.println("Compra executada");
            }
        } else {
            callNext(ordem, carteira, cotacoes, tipoRelatorio);
        }
    }
}
