package br.com.bolsavalores.service;

import br.com.bolsavalores.factorymethod.Acao;
import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;
import br.com.bolsavalores.util.CalculoUtil;
import br.com.bolsavalores.util.FormatoUtil;

import java.util.List;

public class OperacaoService {
    public void executarOperacao(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio, String tipoValidacao) {
        Cotacao cotacaoEncontrada = null;
        for (Cotacao cotacao : cotacoes) {
            if (cotacao.getCodigo().equals(ordem.getCodigoAcao())) {
                cotacaoEncontrada = cotacao;
            }
        }
        if (cotacaoEncontrada == null) {
            System.out.println("Nao foi possivel encontrar cotacao para " + ordem.getCodigoAcao());
            return;
        }

        boolean validacaoBasicaOk = true;
        if ("SIMPLES".equals(tipoValidacao)) {
            if (ordem.getQuantidade() <= 0) {
                validacaoBasicaOk = false;
            }
            if (ordem.getPrecoLimite() <= 0.0) {
                validacaoBasicaOk = false;
            }
        } else if ("COMPLETA".equals(tipoValidacao)) {
            if (ordem.getQuantidade() <= 0) {
                validacaoBasicaOk = false;
            }
            if (ordem.getPrecoLimite() <= 0.0) {
                validacaoBasicaOk = false;
            }
            if (!"COMPRA".equals(ordem.getTipoOperacao()) && !"VENDA".equals(ordem.getTipoOperacao())) {
                validacaoBasicaOk = false;
            }
            if (!"ORDINARIA".equals(ordem.getTipoAcao()) && !"PREFERENCIAL".equals(ordem.getTipoAcao()) && !"ETF".equals(ordem.getTipoAcao())) {
                validacaoBasicaOk = false;
            }
        } else {
            if (ordem.getQuantidade() <= 0) {
                validacaoBasicaOk = false;
            }
        }

        if (!validacaoBasicaOk) {
            System.out.println("Ordem invalida para " + ordem.getCodigoAcao());
            return;
        }

        if ("COMPRA".equals(ordem.getTipoOperacao())) {
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
            Acao acao = new Acao(ordem.getCodigoAcao(), ordem.getTipoAcao(), ordem.getBolsa(), cotacaoEncontrada.getPreco());
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
        } else if ("VENDA".equals(ordem.getTipoOperacao())) {
            double precoExecucao = cotacaoEncontrada.getPreco();
            if (ordem.getPrecoLimite() > 0.0 && ordem.getPrecoLimite() > precoExecucao) {
                precoExecucao = ordem.getPrecoLimite();
            }
            double valorTotal = precoExecucao * ordem.getQuantidade();
            Acao acao = new Acao(ordem.getCodigoAcao(), ordem.getTipoAcao(), ordem.getBolsa(), cotacaoEncontrada.getPreco());
            carteira.reduzirPosicao(acao, ordem.getQuantidade());
            carteira.setSaldoDisponivel(carteira.getSaldoDisponivel() + valorTotal);
            if ("SIMPLIFICADO".equals(tipoRelatorio)) {
                System.out.println("Venda realizada de " + ordem.getQuantidade() + " de " + ordem.getCodigoAcao() + " por " + FormatoUtil.formatarValor(precoExecucao, "MOEDA"));
            } else if ("DETALHADO".equals(tipoRelatorio)) {
                System.out.println("Venda realizada de " + ordem.getQuantidade() + " de " + ordem.getCodigoAcao());
                System.out.println("Preco de execucao " + FormatoUtil.formatarValor(precoExecucao, "MOEDA"));
                System.out.println("Valor total " + FormatoUtil.formatarValor(valorTotal, "MOEDA"));
                System.out.println("Saldo apos venda " + FormatoUtil.formatarValor(carteira.getSaldoDisponivel(), "MOEDA"));
                double indicador = CalculoUtil.calcularIndicadorCarteira(carteira, "RENTABILIDADE");
                System.out.println("Indicador de rentabilidade apos a venda " + FormatoUtil.formatarValor(indicador, "PORCENTAGEM"));
            } else {
                System.out.println("Venda executada");
            }
        } else if ("RELATORIO".equals(ordem.getTipoOperacao())) {
            double valorTotal = CalculoUtil.calcularValorTotalCarteira(carteira);
            double rentabilidade = CalculoUtil.calcularIndicadorCarteira(carteira, "RENTABILIDADE");
            double risco = CalculoUtil.calcularIndicadorCarteira(carteira, "RISCO");
            double imposto = CalculoUtil.calcularIndicadorCarteira(carteira, "IMPOSTO");
            if ("SIMPLIFICADO".equals(tipoRelatorio)) {
                System.out.println("Resumo da carteira " + carteira.getNome());
                System.out.println("Valor total " + FormatoUtil.formatarValor(valorTotal, "MOEDA"));
            } else if ("DETALHADO".equals(tipoRelatorio)) {
                System.out.println("Relatorio detalhado da carteira " + carteira.getNome());
                System.out.println("Valor total " + FormatoUtil.formatarValor(valorTotal, "MOEDA"));
                System.out.println("Rentabilidade " + FormatoUtil.formatarValor(rentabilidade, "PORCENTAGEM"));
                System.out.println("Risco aproximado " + FormatoUtil.formatarValor(risco, "PADRAO"));
                System.out.println("Imposto potencial " + FormatoUtil.formatarValor(imposto, "MOEDA"));
            } else {
                System.out.println("Relatorio simples da carteira");
            }
        } else {
            System.out.println("Tipo de operacao nao suportado " + ordem.getTipoOperacao());
        }
    }
}


