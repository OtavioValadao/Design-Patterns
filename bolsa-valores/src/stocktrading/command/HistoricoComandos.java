package stocktrading.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HistoricoComandos {
    private final List<CommandOperacao> historicoCompleto;
    private final Stack<CommandOperacao> pilhaDesfazer;

    public HistoricoComandos() {
        this.historicoCompleto = new ArrayList<>();
        this.pilhaDesfazer = new Stack<>();
    }

    public void executarComando(CommandOperacao comando) {
        comando.executar();
        historicoCompleto.add(comando);
        pilhaDesfazer.push(comando);
        System.out.println("Comando adicionado ao histórico: " + comando.getDescricao());
    }

    public void desfazerUltimo() {
        if (pilhaDesfazer.isEmpty()) {
            System.out.println("Não há comandos para desfazer.");
            return;
        }

        CommandOperacao comando = pilhaDesfazer.pop();
        comando.desfazer();
        System.out.println("Comando desfeito: " + comando.getDescricao());
    }

    public void reexecutarComando(int indice) {
        if (indice < 0 || indice >= historicoCompleto.size()) {
            System.out.println("Índice inválido. Histórico tem " + historicoCompleto.size() + " comandos.");
            return;
        }

        CommandOperacao comando = historicoCompleto.get(indice);
        comando.executar();
        pilhaDesfazer.push(comando);
        System.out.println("Comando reexecutado: " + comando.getDescricao());
    }

    public void listarHistorico() {
        System.out.println("\n--- Histórico de Comandos ---");
        if (historicoCompleto.isEmpty()) {
            System.out.println("Nenhum comando executado ainda.");
            return;
        }

        for (int i = 0; i < historicoCompleto.size(); i++) {
            System.out.println((i + 1) + ". " + historicoCompleto.get(i).getDescricao());
        }
        System.out.println("Total: " + historicoCompleto.size() + " comandos executados");
    }

    public int getTotalComandos() {
        return historicoCompleto.size();
    }

    public boolean temComandosParaDesfazer() {
        return !pilhaDesfazer.isEmpty();
    }
}

