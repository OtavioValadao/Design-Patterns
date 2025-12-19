package br.com.bolsavalores.chain;

import br.com.bolsavalores.factorymethod.Acao;
import br.com.bolsavalores.factorymethod.AcaoFactory;
import br.com.bolsavalores.factorymethod.TipoDeAcao;
import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;
import br.com.bolsavalores.strategy.context.CalculoFactory;
import br.com.bolsavalores.strategy.impl.ImpostoStrategy;
import br.com.bolsavalores.strategy.impl.RentabilidadeStrategy;
import br.com.bolsavalores.strategy.impl.RiscoStrategy;
import br.com.bolsavalores.util.FormatoUtil;

import java.util.List;

public class VendaHandler extends AbstractOperacaoHandler {

    private final CalculoFactory calculoFactory;

    public VendaHandler() {
        this.calculoFactory = new CalculoFactory(List.of(new ImpostoStrategy(), new RentabilidadeStrategy(), new RiscoStrategy()));
    }

    @Override
    public void handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        if ("VENDA".equals(ordem.getTipoOperacao())) {
            Cotacao cotacaoEncontrada = cotacoes.stream()
                    .filter(c -> c.getCodigo().equals(ordem.getCodigoAcao()))
                    .findFirst()
                    .orElse(null);

            if (cotacaoEncontrada == null) {
                System.out.println("Não foi possível encontrar cotação para " + ordem.getCodigoAcao());
                return;
            }

            double precoExecucao = cotacaoEncontrada.getPreco();
            if (ordem.getPrecoLimite() > 0.0 && ordem.getPrecoLimite() > precoExecucao) {
                precoExecucao = ordem.getPrecoLimite();
            }
            double valorTotal = precoExecucao * ordem.getQuantidade();
            AcaoFactory acaoFactory = new AcaoFactory();
            Acao acao = acaoFactory.criar(TipoDeAcao.valueOf(ordem.getTipoAcao()), ordem.getCodigoAcao(), ordem.getBolsa(), cotacaoEncontrada.getPreco());
            carteira.reduzirPosicao(acao, ordem.getQuantidade());
            carteira.setSaldoDisponivel(carteira.getSaldoDisponivel() + valorTotal);

            if ("SIMPLIFICADO".equals(tipoRelatorio)) {
                System.out.println("Venda realizada de " + ordem.getQuantidade() + " de " + ordem.getCodigoAcao() + " por " + FormatoUtil.formatarValor(precoExecucao, "MOEDA"));
            } else if ("DETALHADO".equals(tipoRelatorio)) {
                System.out.println("Venda realizada de " + ordem.getQuantidade() + " de " + ordem.getCodigoAcao());
                System.out.println("Preco de execucao " + FormatoUtil.formatarValor(precoExecucao, "MOEDA"));
                System.out.println("Valor total " + FormatoUtil.formatarValor(valorTotal, "MOEDA"));
                System.out.println("Saldo apos venda " + FormatoUtil.formatarValor(carteira.getSaldoDisponivel(), "MOEDA"));
                //double indicador = CalculoUtil.calcularIndicadorCarteira(carteira, "RENTABILIDADE");
                var indicador = calculoFactory.calculoStrategyContext("RENTABILIDADE", carteira);
                System.out.println("Indicador de rentabilidade apos a venda " + FormatoUtil.formatarValor(indicador, "PORCENTAGEM"));
            } else {
                System.out.println("Venda executada");
            }
        } else {
            callNext(ordem, carteira, cotacoes, tipoRelatorio);
        }
    }
}
