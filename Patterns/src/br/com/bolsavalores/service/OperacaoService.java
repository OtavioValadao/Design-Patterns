package br.com.bolsavalores.service;

import br.com.bolsavalores.chain.OperacaoHandler;
import br.com.bolsavalores.chain.ValidacaoParametrosHandler;
import br.com.bolsavalores.chain.ValidacaoRegrasHandler;
import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;
import br.com.bolsavalores.template.ProcessadorOrdem;
import br.com.bolsavalores.template.ProcessadorOrdemCompra;
import br.com.bolsavalores.template.ProcessadorOrdemRelatorio;
import br.com.bolsavalores.template.ProcessadorOrdemVenda;

import java.util.List;

public class OperacaoService {
    private final OperacaoHandler validationChain;

    public OperacaoService() {
        this.validationChain = new ValidacaoParametrosHandler();
        OperacaoHandler validacaoRegras = new ValidacaoRegrasHandler();
        validationChain.setNext(validacaoRegras);
    }

    public void executarOperacao(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        System.out.println("\n--- Processando Ordem: " + ordem.getTipoOperacao() + " de " + ordem.getCodigoAcao() + " ---");

        boolean isValido = validationChain.handle(ordem, carteira, cotacoes, tipoRelatorio);

        if (isValido) {
            ProcessadorOrdem processador = null;
            if ("COMPRA".equals(ordem.getTipoOperacao())) {
                processador = new ProcessadorOrdemCompra();
            } else if ("VENDA".equals(ordem.getTipoOperacao())) {
                processador = new ProcessadorOrdemVenda();
            } else if ("RELATORIO".equals(ordem.getTipoOperacao())) {
                processador = new ProcessadorOrdemRelatorio();
            }

            if (processador != null) {
                processador.processar(ordem, carteira, cotacoes, tipoRelatorio);
            }
        }
    }
}
