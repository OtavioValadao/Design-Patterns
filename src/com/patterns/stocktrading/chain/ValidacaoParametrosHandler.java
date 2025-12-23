package com.patterns.stocktrading.chain;

import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.model.Cotacao;
import com.patterns.stocktrading.model.Ordem;

import java.util.List;

public class ValidacaoParametrosHandler extends AbstractOperacaoHandler {
    @Override
    public boolean handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        boolean dadosInvalidos = false;

        // Regra 1: Quantidade deve ser positiva (exceto para relatórios que podem ter qtd 0)
        if (!"RELATORIO".equals(ordem.getTipoOperacao()) && ordem.getQuantidade() <= 0) {
            System.out.println("Erro de Validação: Quantidade deve ser maior que zero.");
            dadosInvalidos = true;
        }

        // Regra 2: Preço não pode ser negativo
        if (ordem.getPrecoLimite() < 0.0) {
            System.out.println("Erro de Validação: Preço limite não pode ser negativo.");
            dadosInvalidos = true;
        }

        if (dadosInvalidos) {
            // Interrompe a cadeia aqui. Não chama o next.
            return false;
        }

        // Se passou, segue para a próxima verificação
        return callNext(ordem, carteira, cotacoes, tipoRelatorio);
    }
}
