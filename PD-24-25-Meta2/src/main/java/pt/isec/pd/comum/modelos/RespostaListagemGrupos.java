package pt.isec.pd.comum.modelos;

import pt.isec.pd.comum.enumeracoes.Estados;

import java.io.Serializable;
import java.util.List;

public class RespostaListagemGrupos extends RespostaServidorMensagem {
    public RespostaListagemGrupos(Estados estado, List<String> grupos) {
        super(estado, (Serializable) grupos);
    }

}
