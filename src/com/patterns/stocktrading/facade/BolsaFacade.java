package com.patterns.stocktrading.facade;

import com.patterns.stocktrading.factorymethod.Acao;
import com.patterns.stocktrading.model.AcaoPosicao;
import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.model.Cotacao;
import com.patterns.stocktrading.model.Ordem;
import com.patterns.stocktrading.model.Usuario;
import com.patterns.stocktrading.service.CotacaoService;
import com.patterns.stocktrading.service.NotificacaoService;
import com.patterns.stocktrading.service.OperacaoService;
import com.patterns.stocktrading.singleton.ConfiguracaoSistema;
import com.patterns.stocktrading.strategy.context.CalculoFactory;
import com.patterns.stocktrading.strategy.impl.ImpostoStrategy;
import com.patterns.stocktrading.strategy.impl.RentabilidadeStrategy;
import com.patterns.stocktrading.strategy.impl.RiscoStrategy;
import com.patterns.stocktrading.util.FormatoUtil;
import com.patterns.stocktrading.util.MockDadosUtil;

import java.util.ArrayList;
import java.util.List;

public class BolsaFacade {

    private final CotacaoService cotacaoService;
    private final OperacaoService operacaoService;
    private final NotificacaoService notificacaoService;
    private final CalculoFactory calculoFactory;
    private final ConfiguracaoSistema configuracaoSistema;
    private final Usuario usuarioLogado;
    private final List<Acao> acoesDisponiveis;

    public BolsaFacade() {
        this.cotacaoService = new CotacaoService();
        this.operacaoService = new OperacaoService();
        this.notificacaoService = new NotificacaoService();
        this.calculoFactory = new CalculoFactory(List.of(
                new ImpostoStrategy(),
                new RentabilidadeStrategy(),
                new RiscoStrategy()
        ));
        this.configuracaoSistema = ConfiguracaoSistema.getInstance();
        this.acoesDisponiveis = MockDadosUtil.criarAcoesIniciais();
        this.usuarioLogado = MockDadosUtil.criarUsuarioPadrao(acoesDisponiveis);
    }


    public void consultarCarteira(String usuario) {
        // Simulação: sempre usa o usuário logado do mock
        System.out.println("Consultando carteira de: " + usuarioLogado.getNome());
        for (Carteira carteira : usuarioLogado.getCarteiras()) {
            System.out.println("Carteira: " + carteira.getNome());
            System.out.println("Saldo Disponível: " + FormatoUtil.formatarValor(carteira.getSaldoDisponivel(), "MOEDA"));
            System.out.println("Posições:");
            for (AcaoPosicao posicao : carteira.getPosicoes()) {
                System.out.println(" - " + posicao.getAcao().getCodigo() + ": " + posicao.getQuantidade() + " ações (Médio: " + FormatoUtil.formatarValor(posicao.getPrecoMedio(), "MOEDA") + ")");
            }
            
            // Usando Strategy para cálculos
            Double rentabilidade = calculoFactory.calculoStrategyContext("RENTABILIDADE", carteira);
            System.out.println("Rentabilidade Estimada: " + FormatoUtil.formatarValor(rentabilidade, "PORCENTAGEM"));
            
            Double risco = calculoFactory.calculoStrategyContext("RISCO", carteira);
            System.out.println("Risco da Carteira: " + FormatoUtil.formatarValor(risco, "PADRAO"));
        }
    }

    public void executarCompra(Ordem ordem) {
        ordem.setTipoOperacao("COMPRA");
        processarOrdem(ordem);
    }

    public void executarVenda(Ordem ordem) {
        ordem.setTipoOperacao("VENDA");
        processarOrdem(ordem);
    }

    private void processarOrdem(Ordem ordem) {
        // Busca cotação atual
        Cotacao cotacao = cotacaoService.buscarCotacaoAtual(ordem.getCodigoAcao(), configuracaoSistema.getFonteDadosPadrao());
        List<Cotacao> cotacoes = new ArrayList<>();
        cotacoes.add(cotacao);

        // Define carteira padrão (primeira do usuário)
        Carteira carteira = usuarioLogado.getCarteiras().get(0);

        // Executa operação via Chain of Responsibility
        operacaoService.executarOperacao(ordem, carteira, cotacoes, "COMPLETO");

        // Verifica notificações
        Acao acaoEncontrada = null;
        for (Acao acao : acoesDisponiveis) {
            if (acao.getCodigo().equals(ordem.getCodigoAcao())) {
                acaoEncontrada = acao;
                break;
            }
        }
        
        if (acaoEncontrada != null) {
            // Simula variação de preço para notificação
            double precoAnterior = acaoEncontrada.getPrecoAtual();
            double precoAtual = cotacao.getPreco();
            // Atualiza preço na lista de ações disponíveis (mock)
            acaoEncontrada.setPrecoAtual(precoAtual);
            
            notificacaoService.notificarUsuariosPorPreco(List.of(usuarioLogado), acaoEncontrada, precoAnterior, precoAtual, 0.05); // 5% variação
        }
    }

    public void gerarRelatorioCarteira() {
        System.out.println("Gerando relatório consolidado...");
        Carteira carteira = usuarioLogado.getCarteiras().get(0);
        
        Double imposto = calculoFactory.calculoStrategyContext("IMPOSTO", carteira);
        System.out.println("Imposto estimado a pagar: " + FormatoUtil.formatarValor(imposto, "MOEDA"));
    }
}
