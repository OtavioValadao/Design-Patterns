package stocktrading.factorymethod;

import stocktrading.factorymethod.subclasses.AcaoEtf;
import stocktrading.factorymethod.subclasses.AcaoOrdinaria;
import stocktrading.factorymethod.subclasses.AcaoPreferencial;

import java.util.HashMap;
import java.util.Map;

public class AcaoFactory {

    private final Map<TipoDeAcao, TriFunction<String, String, Double, Acao>> criadores = new HashMap<>();


    public AcaoFactory() {
        registrar(TipoDeAcao.PREFERENCIAL, AcaoPreferencial::new);
        registrar(TipoDeAcao.ETF, AcaoEtf::new);
        registrar(TipoDeAcao.ORDINARIA, AcaoOrdinaria::new);
    }

    public void registrar(TipoDeAcao tipoDeAcao, TriFunction<String, String, Double, Acao> criador){
        criadores.put(tipoDeAcao, criador);
    }

    public Acao criar(TipoDeAcao tipo, String codigo, String bolsa, Double preco){
        var criador = criadores.get(tipo);

        if(criador == null){
            throw new IllegalArgumentException("Tipo de acao nao registrada " + tipo);
        }

        Acao apply = criador.apply(codigo, bolsa, preco);
        return apply;
    }
}
