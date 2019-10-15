package daos;

import java.sql.*;
import java.util.List;

import beans.usuarioBean;
import mappers.ciclosMapper;
import mappers.moduloAuxMapper;
import mappers.moduloMapper;

import mappers.usuarioMapper;
import utilidades.Constantes;

public class AccesoUsarioDAOImpl extends OracleDAOFactory implements AccesoUsarioDAO {

    OracleDAOFactory oraDaoFac = new OracleDAOFactory();

    //buscando los datos del USUARIO 
    public usuarioBean consultaAcceso(String cveUsuario, String pswUsuario) throws Exception {

        String query = " select NAMEUSUARIO, PASSWORD, PERFIL, NAMEPERFIL, USUARIO, FILTRO, USUARIO_LOGIN, ID_PERSONA, ID_USUARIO, ESTATUS, ID_IE_UE, ID_ESCUELA "
                + " from " + Constantes.TablaUsuarios + " where USUARIO_LOGIN = '" + cveUsuario + "' AND PASSWORD = '" + pswUsuario + "'";
        Constantes.enviaMensajeConsola(" query consulta folio--> " + query);
        usuarioBean usu = (usuarioBean) oraDaoFac.queryForObject(query, new usuarioMapper());
        return usu;
    }

    public List consultaModulosPerfilMenu(Integer cvePer, String cvemodpadre) throws Exception {
        String query = "SELECT MP.CVEMODULO,NAMEMOD,ACTION,MODPADRE,IMAGEN FROM " + Constantes.TablaModulosPerfiles + " MP "
                + " LEFT OUTER JOIN " + Constantes.TablaModulos + " M ON MP.CVEMODULO=M.CVEMODULO "
                + " WHERE PERFIL='" + cvePer + "' AND M.ESTATUS=1 ORDER BY ORDEN";
        System.out.println("QueryConsultaSubModulosPerfil ---> " + query);
        List list = null;
        list = queryForList(query, new moduloMapper());
        return list;
    }

    public List consultaModulosHijosPerfilMenu(Integer cvePer, String cvemodpadre) throws Exception {
        String query = "SELECT MP.CVEMODULO,NAMEMOD,ACTION,MODPADRE FROM " + Constantes.TablaModulosPerfiles + " MP "
                + " LEFT OUTER JOIN " + Constantes.TablaModulos + " M ON MP.CVEMODULO=M.CVEMODULO "
                + " WHERE PERFIL='" + cvePer + "' AND MP.ESTATUS=1 ORDER BY ORDEN";
        System.out.println("QueryConsultaSubModulosPerfil ---> " + query);
        List list = null;
        list = queryForList(query, new moduloAuxMapper());
        return list;
    }

    public List cicloAct() throws Exception {
        String query = "SELECT ID_CICLO, TO_CHAR( FECHA_INICIO,'DD/MM/YYYY') AS FECHA_INICIO, TO_CHAR(FECHA_TERMINO,'DD/MM/YYYY') AS FECHA_TERMINO, CICLO FROM CAT_CICLOS WHERE ESTATUS=1 AND ROWNUM=1 ";
        Constantes.enviaMensajeConsola("Consulta CICLOS----->" + query);
        List list = null;
        list = queryForList(query, new ciclosMapper());
        return list;
    }

}
