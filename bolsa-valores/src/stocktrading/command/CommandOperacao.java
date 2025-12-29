package stocktrading.command;

public interface CommandOperacao {
    void executar();
    void desfazer();
    String getDescricao();
}

