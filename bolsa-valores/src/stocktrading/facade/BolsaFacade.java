package stocktrading.facade;

import stocktrading.adapter.CotacaoCliente;
import stocktrading.adapter.CotacaoClienteFactory;
import stocktrading.decorator.Relatorio;
import stocktrading.decorator.RelatorioFactory;
import stocktrading.factorymethod.Acao;
import stocktrading.model.AcaoPosicao;
import stocktrading.model.Carteira;
import stocktrading.model.Cotacao;
import stocktrading.model.Ordem;
import stocktrading.model.Usuario;
import stocktrading.service.CotacaoService;
import stocktrading.service.NotificacaoService;
import stocktrading.service.OperacaoService;
import stocktrading.singleton.ConfiguracaoSistema;
import stocktrading.strategy.context.CalculoFactory;
import stocktrading.strategy.impl.ImpostoStrategy;
import stocktrading.strategy.impl.RentabilidadeStrategy;
import stocktrading.strategy.impl.RiscoStrategy;
import stocktrading.util.FormatoUtil;
import stocktrading.util.MockDadosUtil;

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
        // Cria o adaptador apropriado baseado na configuração do sistema
        CotacaoCliente cotacaoCliente = CotacaoClienteFactory.criar(
            ConfiguracaoSistema.getInstance().getFonteDadosPadrao()
        );
        this.cotacaoService = new CotacaoService(cotacaoCliente);
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
        gerarRelatorioCompleto();
    }
    
    /**
     * Gera relatório básico (sem decorators)
     */
    public void gerarRelatorioBasico() {
        Relatorio relatorio = RelatorioFactory.criarBasico();
        relatorio.gerar(usuarioLogado.getCarteiras().get(0));
    }
    
    /**
     * Gera relatório completo com rentabilidade, risco e imposto
     */
    public void gerarRelatorioCompleto() {
        Relatorio relatorio = RelatorioFactory.criarCompleto();
        relatorio.gerar(usuarioLogado.getCarteiras().get(0));
    }
    
    /**
     * Gera relatório com rentabilidade
     */
    public void gerarRelatorioComRentabilidade() {
        Relatorio relatorio = RelatorioFactory.criarComRentabilidade();
        relatorio.gerar(usuarioLogado.getCarteiras().get(0));
    }
    
    /**
     * Gera relatório com risco
     */
    public void gerarRelatorioComRisco() {
        Relatorio relatorio = RelatorioFactory.criarComRisco();
        relatorio.gerar(usuarioLogado.getCarteiras().get(0));
    }
    
    /**
     * Gera relatório com imposto
     */
    public void gerarRelatorioComImposto() {
        Relatorio relatorio = RelatorioFactory.criarComImposto();
        relatorio.gerar(usuarioLogado.getCarteiras().get(0));
    }
    
    /**
     * Gera relatório com rentabilidade e risco
     */
    public void gerarRelatorioComRentabilidadeERisco() {
        Relatorio relatorio = RelatorioFactory.criarComRentabilidadeERisco();
        relatorio.gerar(usuarioLogado.getCarteiras().get(0));
    }
    
    /**
     * Gera relatório com rentabilidade e imposto
     */
    public void gerarRelatorioComRentabilidadeEImposto() {
        Relatorio relatorio = RelatorioFactory.criarComRentabilidadeEImposto();
        relatorio.gerar(usuarioLogado.getCarteiras().get(0));
    }
    
    /**
     * Gera relatório com risco e imposto
     */
    public void gerarRelatorioComRiscoEImposto() {
        Relatorio relatorio = RelatorioFactory.criarComRiscoEImposto();
        relatorio.gerar(usuarioLogado.getCarteiras().get(0));
    }
    
    /**
     * Permite criar relatórios customizados combinando decorators
     */
    public void gerarRelatorioCustomizado(Relatorio relatorio) {
        relatorio.gerar(usuarioLogado.getCarteiras().get(0));
    }

    // Métodos públicos para uso pelos Commands
    public Carteira getCarteiraPadrao() {
        return usuarioLogado.getCarteiras().get(0);
    }

    public Cotacao buscarCotacao(String codigoAcao) {
        return cotacaoService.buscarCotacaoAtual(codigoAcao, configuracaoSistema.getFonteDadosPadrao());
    }
}
