package pt.isec.pd.servidores.controladores;

import pt.isec.pd.comum.enumeracoes.Estados;

import pt.isec.pd.comum.modelos.Grupos;
import pt.isec.pd.comum.modelos.mensagens.*;
import pt.isec.pd.db.Bd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ControladorGrupoServidor {

    public static Estados grupoRegisto(CriaGrupo criaGrupo) {
        Serializable grupoRegisto = null;
        try {
            grupoRegisto = Bd.setGrupoDB(criaGrupo.getNome(), criaGrupo.getCriadoPor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("ESTADOOOOSSSS " + grupoRegisto);
        return grupoRegisto == null ? Estados.ERRO_GRUPO : Estados.GRUPO_REGISTADO_COM_SUCESSO;


    }
    public static Estados insereGrupo(InsereGrupo insereGrupo) {
        Serializable insertGroup = null;
        try {
            insertGroup = Bd.integraGrupo(insereGrupo.getNomeGrupo(),insereGrupo.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return insertGroup == null ? Estados.ERRO_GRUPO : Estados.GRUPO_USER_INSERIDO_COM_SUCESSO;


    }
    public static /*List<String>*/ Estados listarGrupos(ListarGrupo listarGrupo) {
        //List<String> grupos = new ArrayList<>();
        Serializable grupo = null;
        try {
            grupo = Bd.listarGruposDB(listarGrupo.getSolicitadoPor());
        } catch (Exception e) {
            System.err.println("Erro ao listar grupos: " + e.getMessage());
        }
        //System.out.println("ControladorGrupoServidor ->" + grupo);
        return grupo == null ? Estados.ERRO_SEM_GRUPOS : Estados.GRUPO_LISTADO_COM_SUCESSO.setDados(grupo);
    }
    public static Estados selecionaGrupo(SelecionarGrupo selecionarGrupo) {
        //List<String> grupos = new ArrayList<>();
        Serializable selecionaGrupo = null;
        try {
            selecionaGrupo = Bd.getGrupoDB(selecionarGrupo.getSolicitadoPor(),selecionarGrupo.getGrupoNome());
        } catch (Exception e) {
            System.err.println("Erro ao listar grupos: " + e.getMessage());
        }
        //System.out.println("ControladorGrupoServidor ->" + grupo);
        return selecionaGrupo == null ? Estados.ERRO_AO_SELECIONAR_GRUPO : Estados.USER_GRUPO_SELECIONADO_COM_SUCESSO.setDados(selecionaGrupo);
    }



}

