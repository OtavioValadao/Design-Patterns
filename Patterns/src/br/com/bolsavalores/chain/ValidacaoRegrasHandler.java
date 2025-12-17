package br.com.bolsavalores.chain;

import br.com.bolsavalores.model.Carteira;
import br.com.bolsavalores.model.Cotacao;
import br.com.bolsavalores.model.Ordem;

import java.util.Arrays;
import java.util.List;

/**
 * Responsável por validar regras de negócio, como tipos de operação e ação.
 * Esta validação é mais "cara" e só é executada se os parâmetros básicos estiverem OK.
 */
public class ValidacaoRegrasHandler extends AbstractOperacaoHandler {
    private static final List<String> TIPOS_OPERACAO_VALIDOS = Arrays.asList("COMPRA", "VENDA", "RELATORIO");
    private static final List<String> TIPOS_ACAO_VALIDOS = Arrays.asList("ORDINARIA", "PREFERENCIAL", "ETF");

    @Override
    public void handle(Ordem ordem, Carteira carteira, List<Cotacao> cotacoes, String tipoRelatorio) {
        boolean regrasInvalidas = false;

        // Regra 3: Tipo de operação deve ser um dos conhecidos
        if (!TIPOS_OPERACAO_VALIDOS.contains(ordem.getTipoOperacao())) {
            System.out.println("Erro de Regra: Tipo de operação '" + ordem.getTipoOperacao() + "' não é suportado.");
            regrasInvalidas = true;
        }

        // Regra 4: Tipo de ação deve ser um dos conhecidos
        if (ordem.getTipoAcao() != null && !TIPOS_ACAO_VALIDOS.contains(ordem.getTipoAcao())) {
            System.out.println("Erro de Regra: Tipo de ação '" + ordem.getTipoAcao() + "' não é suportado.");
            regrasInvalidas = true;
        }

        if (regrasInvalidas) {
            // Interrompe a cadeia aqui.
            return;
        }

        // Se passou, segue para a execução da operação
        callNext(ordem, carteira, cotacoes, tipoRelatorio);
    }
}
