package br.com.bolsavalores;

import br.com.bolsavalores.factorymethod.Acao;
import br.com.bolsavalores.factorymethod.AcaoFactory;
import br.com.bolsavalores.factorymethod.TipoDeAcao;
import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;
import br.com.bolsavalores.model.Usuario;
import br.com.bolsavalores.service.CotacaoService;
import br.com.bolsavalores.service.NotificacaoService;
import br.com.bolsavalores.service.OperacaoService;
import br.com.bolsavalores.singleton.ConfiguracaoSistema;
import br.com.bolsavalores.util.CalculoUtil;
import br.com.bolsavalores.util.FormatoUtil;
import br.com.bolsavalores.util.MockDadosUtil;

import java.util.ArrayList;
import java.util.List;

public class BolsaAplicacao {
    public static void main(String[] args) {
        BolsaAplicacao aplicacao = new BolsaAplicacao();
        aplicacao.executarDemonstracao();
    }

    public void executarDemonstracao() {

        ConfiguracaoSistema config = ConfiguracaoSistema.getInstance();
        config.setFonteDadosPadrao("EXTERNA");

        List<Acao> acoes = MockDadosUtil.criarAcoesIniciais();
        Usuario usuario = MockDadosUtil.criarUsuarioPadrao(acoes);

        List<Acao> acoesDuplicadas = new ArrayList<>();
        AcaoFactory acaoFactory = new AcaoFactory();
        acoesDuplicadas.add(acaoFactory.criar(TipoDeAcao.ORDINARIA, "PETR4", "B3", 38.5));
        acoesDuplicadas.add(acaoFactory.criar(TipoDeAcao.ORDINARIA, "VALE3","B3", 68.2));
        acoesDuplicadas.add(acaoFactory.criar(TipoDeAcao.ETF,"ITUB4", "B3", 29.7));

        Carteira carteiraPrincipal = usuario.getCarteiras().getFirst();

        CotacaoService cotacaoService = new CotacaoService();
        OperacaoService operacaoService = new OperacaoService();
        NotificacaoService notificacaoService = new NotificacaoService();

        List<Cotacao> cotacoes = new ArrayList<>();
        for (Acao acao : acoes) {
            Cotacao cotacao = cotacaoService.buscarCotacaoAtual(acao.getCodigo(), config.getFonteDadosPadrao());
            acao.setPrecoAtual(cotacao.getPreco());
            cotacoes.add(cotacao);
        }

        Cotacao cotacaoPetrNyse = cotacaoService.buscarCotacaoAtual("PETR4", "NYSE");
        Cotacao cotacaoValeExterna = cotacaoService.buscarCotacaoAtual("VALE3", config.getFonteDadosPadrao());
        cotacoes.add(cotacaoValeExterna);

        Ordem ordemCompra = new Ordem();
        ordemCompra.setTipoOperacao("COMPRA");
        ordemCompra.setTipoAcao("ORDINARIA");
        ordemCompra.setCodigoAcao("BOVA11");
        ordemCompra.setQuantidade(3);
        ordemCompra.setPrecoLimite(115.0);
        ordemCompra.setBolsa("B3");
        ordemCompra.setOrigem("APP");
        ordemCompra.setTipoExecucao("A_MERCADO");
        ordemCompra.setValidade("DIA");

        double valorAntes = CalculoUtil.calcularValorTotalCarteira(carteiraPrincipal);
        System.out.println("Valor inicial da carteira " + FormatoUtil.formatarValor(valorAntes, "MOEDA"));

        operacaoService.executarOperacao(ordemCompra, carteiraPrincipal, cotacoes, "DETALHADO", "COMPLETA");

        Ordem ordemVenda = new Ordem();
        ordemVenda.setTipoOperacao("VENDA");
        ordemVenda.setTipoAcao("ORDINARIA");
        ordemVenda.setCodigoAcao("PETR4");
        ordemVenda.setQuantidade(5);
        ordemVenda.setPrecoLimite(0.0);
        ordemVenda.setBolsa("B3");
        ordemVenda.setOrigem("WEB");
        ordemVenda.setTipoExecucao("A_MERCADO");
        ordemVenda.setValidade("ATE_CANCELAR");

        operacaoService.executarOperacao(ordemVenda, carteiraPrincipal, cotacoes, "SIMPLIFICADO", "SIMPLES");

        Ordem ordemRelatorio = new Ordem();
        ordemRelatorio.setTipoOperacao("RELATORIO");
        ordemRelatorio.setTipoAcao("ORDINARIA");
        ordemRelatorio.setCodigoAcao("VALE3");
        ordemRelatorio.setQuantidade(0);
        ordemRelatorio.setPrecoLimite(0.0);
        ordemRelatorio.setBolsa("B3");
        ordemRelatorio.setOrigem("APP");
        ordemRelatorio.setTipoExecucao("A_MERCADO");
        ordemRelatorio.setValidade("DIA");

        operacaoService.executarOperacao(ordemRelatorio, carteiraPrincipal, cotacoes, "DETALHADO", "COMPLETA");

        double valorApos = CalculoUtil.calcularValorTotalCarteira(carteiraPrincipal);
        double rentabilidade = CalculoUtil.calcularIndicadorCarteira(carteiraPrincipal, "RENTABILIDADE");
        System.out.println("Valor final da carteira " + FormatoUtil.formatarValor(valorApos, "MOEDA"));
        System.out.println("Rentabilidade estimada " + FormatoUtil.formatarValor(rentabilidade, "PORCENTAGEM"));

        List<Usuario> usuariosNotificacao = new ArrayList<>();
        usuariosNotificacao.add(usuario);

        Acao acaoPetr = null;
        for (Acao acao : acoesDuplicadas) {
            if ("PETR4".equals(acao.getCodigo())) {
                acaoPetr = acao;
            }
        }

        if (acaoPetr != null) {
            double precoAnterior = cotacaoPetrNyse.getPreco();
            Cotacao novaCotacaoPetr = cotacaoService.buscarCotacaoAtual("PETR4", "B3");
            notificacaoService.notificarUsuariosPorPreco(usuariosNotificacao, acaoPetr, precoAnterior, novaCotacaoPetr.getPreco(), 0.02);
        }

        if (valorApos > valorAntes && rentabilidade > 0.0) {
            System.out.println("Perfil do investidor: RENTABILIDADE");
        } else if (valorApos < valorAntes && rentabilidade < 0.0) {
            System.out.println("Perfil do investidor: RISCO");
        } else {
            System.out.println("Perfil do investidor: NEUTRO");
        }
    }
}


