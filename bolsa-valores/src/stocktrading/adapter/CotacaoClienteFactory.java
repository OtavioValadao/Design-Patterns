package stocktrading.adapter;

public class CotacaoClienteFactory {
    
    public static CotacaoCliente criar(String fonteDados) {
        if (fonteDados == null) {
            fonteDados = "B3";
        }
        
        switch (fonteDados.toUpperCase()) {
            case "B3":
                return new B3ApiAdapter();
            case "NYSE":
                return new NyseApiAdapter();
            case "EXTERNA":
                return new ApiExternaAdapter();
            default:
                System.out.println("Fonte de dados '" + fonteDados + "' não reconhecida. Usando B3 como padrão.");
                return new B3ApiAdapter();
        }
    }
}

