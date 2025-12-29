package stocktrading.decorator;

import stocktrading.model.Carteira;

public interface Relatorio {
    void gerar(Carteira carteira);
}

