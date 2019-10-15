package daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.EncabezadoBean;
import beans.PreguntasBean;
import beans.Res_ConBean;
import beans.RespuestaBean;
import beans.UnidadesEconomicasBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import mappers.CatalogoMapper;
import mappers.ColoniaMapper;
import mappers.ConsultaRespuestasMapper;
import mappers.CuestionarioMapper;
import mappers.EncabezadoMapper;
import mappers.RespuestasMapper;
import mappers.SECTORMapper;
import mappers.SECTORSUBMapper;
import mappers.SucursalesMapper;
import mappers.UEIEMapper;
import mappers.UEMapper;
import mappers.sectordosMapper;
import utilidades.Constantes;
import utilidades.ObjPrepareStatement;

/**
 *
 * @author dinamarca
 */
public class EvaluarUEDAOImpl {

    OracleDAOFactory oraDaoFac = new OracleDAOFactory();

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    
    public Connection crearConexion() throws Exception {
        
        Connection conne;
        conne = oraDaoFac.createConnection();
        Constantes.enviaMensajeConsola("conexion abierta.........");
        return conne;

    }

    //creando statement
    public Statement crearStatement(Connection cone) throws Exception {
        Statement stei;
        stei = oraDaoFac.createStatement2(cone);
        return stei;
    }
    
    public List ConsultaUEIE(String id_esc) throws Exception {
        String query = "SELECT ieue.id_ie_ue, ue.rfc,ue.razon_social,ue.nom_comercial,(SELECT nom_sector FROM cat_sector where id_sector=ue.sector)as sector,ieue.id_suc,(SELECT NOMBRE  FROM tbl_sucursales WHERE id_suc=ieue.id_suc)as nombre,ieue.status_ue,ieue.status_evaluacion,ieue.status_resultado_evaluacion,ieue.porcentaje_evaluacion,ieue.status_general "
                + "FROM tbl_ins_ue ieue INNER JOIN cat_unidad_economica ue on ieue.id_ue=ue.id_ue "
                + "where id_escuela='" + id_esc + "' and ieue.STATUS_UE='1'";
        Constantes.enviaMensajeConsola("Consulta UE en IE ----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new UEIEMapper());
        return list;
    }
    
     public List ConsultaUEIEPOT(String id_esc) throws Exception {
        String query = "SELECT ieue.id_ie_ue, ue.rfc,ue.razon_social,ue.nom_comercial,(SELECT nom_sector FROM cat_sector where id_sector=ue.sector)as sector,ieue.id_suc,(SELECT NOMBRE  FROM tbl_sucursales WHERE id_suc=ieue.id_suc)as nombre,ieue.status_ue,ieue.status_evaluacion,ieue.status_resultado_evaluacion,ieue.porcentaje_evaluacion,ieue.status_general "
                + "FROM tbl_ins_ue ieue INNER JOIN cat_unidad_economica ue on ieue.id_ue=ue.id_ue "
                + "where id_escuela='" + id_esc + "' and ieue.STATUS_EVALUACION='0' ";
        Constantes.enviaMensajeConsola("Consulta UE en IE ----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new UEIEMapper());
        return list;
    }
     
     public boolean ActualizaStatusUE(UnidadesEconomicasBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del ACTUALIZAR ...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("STATUS_UE", "STRING", ue.getSTATUS_UE());
        arregloCampos.add(temporal);

        String condicion = "WHERE ID_IE_UE=" + ue.getID_IE_UE() + "";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_INS_UE", arregloCampos, condicion);
    } 
    

    public List ConsultaSecciones() throws Exception {
        String query = "SELECT id_encabezado,nom_encabezado FROM cat_encabezado";
        Constantes.enviaMensajeConsola("Consulta encabezado----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new EncabezadoMapper());
        return list;
    }
    
    
    public List ConsSecciones() throws Exception {
        
        List<PreguntasBean> listaPreguntas = null;
        
        List<PreguntasBean> listaPregSecc = new ArrayList<PreguntasBean>();
        

        List ListaFinal = new ArrayList();
        List listaSecciones = null;

        EncabezadoBean SECC;
        PreguntasBean PREG;
        

        String sWhere = "' ";
        String query = "SELECT id_encabezado,nom_encabezado FROM cat_encabezado";
        Constantes.enviaMensajeConsola("--consulta secciones tabla.....-->" + query);
//        List list = null;
//        list = queryForList(query, new Mapper_Secciones());
//        return list;

        listaSecciones = oraDaoFac.queryForList(query, new EncabezadoMapper());

        listaPreguntas = ConsultaPreguntas(); //llamamos metodo lista preguntas
        
        //System.out.println("TAMAÑO DE LISTA SECCIONES---" + listaSecciones.size());
        
        if (listaSecciones.size() > 0) {
            
            Iterator LS = listaSecciones.iterator();
            while (LS.hasNext()) {
                
                SECC = (EncabezadoBean) LS.next();
                //System.out.println("SECC--->"+ SECC.getNOM_SECCIONS());

                listaPregSecc = new ArrayList<PreguntasBean>();
                
                //System.out.println("TAMAÑO DE LISTA PREGUNTAS---" + listaPreguntas.size());
                
                Iterator LP = listaPreguntas.iterator();
                
                while (LP.hasNext()) {
                    //System.out.println("llegoaqui****************");
                    
                    PREG = (PreguntasBean) LP.next();
                    
                        //System.out.println("IDSECC--->"+ SECC.getID_SECCIONS());
                        //System.out.println("IDSECCPREG--->"+ PREG.getIDSECC());
                        
                    if (SECC.getID_ENCABEZADO().equals(PREG.getID_ENCABEZADO()) ) {
                        
                        listaPregSecc.add(PREG);
                        //System.out.println("si coincide Preguntas---" + PREG.getNOMPREG());
                        //contador=contador+1;
                        //}
                        
                    }
                }
                SECC.setListaPregEnca(listaPregSecc);
                ListaFinal.add(SECC);
            }

        }
        //System.out.println("TAMAÑO FINAL..." + ListaFinal.size());
        return ListaFinal;
    }
    

    public List ConsultaPreguntas() throws Exception {
        String query = "SELECT c.id_encabezado,c.nom_encabezado,p.id_pregunta,p.pregunta,p.tipo_pregunta,r.orden "
                + "FROM TBL_EN_PR R INNER JOIN cat_encabezado C ON r.id_encabezado=c.id_encabezado "
                + "INNER JOIN cat_preguntas P ON r.id_pregunta=p.id_pregunta ORDER BY c.id_encabezado, r.orden";
        Constantes.enviaMensajeConsola("Consulta PREGUNTAS----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new CuestionarioMapper());
        return list;
    }
    
    public List ConsultaRespuestas1() throws Exception {
        String query = "SELECT id_respuesta,respuesta FROM cat_respuestas where id_tipo_pregunta='100' ";
        Constantes.enviaMensajeConsola("Consulta respuestas----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new RespuestasMapper());
        return list;
    }
    public List ConsultaRespuestas2() throws Exception {
        String query = "SELECT id_respuesta,respuesta FROM cat_respuestas where id_tipo_pregunta='200'";
        Constantes.enviaMensajeConsola("Consulta respuestas----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new RespuestasMapper());
        return list;
    }
    
    public List ConsultaRespuestas(String id_ie_ue) throws Exception {
        String query = "SELECT id_evaluacion,id_pregunta,id_respuesta FROM tbl_evaluacion_ue where id_ie_ue='"+id_ie_ue+"' ORDER BY id_evaluacion";
        Constantes.enviaMensajeConsola("Consulta UE en IE ----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new ConsultaRespuestasMapper());
        return list;
    }
    
    public boolean GuardaEvaluacionUE(Connection conn, PreparedStatement stat,Res_ConBean res) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_IE_UE", "STRING", res.getID_IE_UE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ENCABEZADO", "STRING", res.getID_ENCABEZADO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_PREGUNTA", "STRING", res.getID_PREGUNTA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_RESPUESTA", "STRING", res.getID_RESPUESTA());
        arregloCampos.add(temporal);
        

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsertTransaccion(conn, stat,"TBL_EVALUACION_UE", arregloCampos);
    }
    
     public boolean ActualizaEvaluacionUE(Connection conn, PreparedStatement stat,Res_ConBean res) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        
        temporal = new ObjPrepareStatement("ID_RESPUESTA", "STRING", res.getID_RESPUESTA());
        arregloCampos.add(temporal);
        
        String Condicion="where ID_IE_UE='"+res.getID_IE_UE()+"' AND ID_ENCABEZADO='"+res.getID_ENCABEZADO()+"' AND ID_PREGUNTA='"+res.getID_PREGUNTA()+"' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdateTransaccion(conn, stat,"TBL_EVALUACION_UE", arregloCampos, Condicion);
    }
     
     
     public boolean ActualizaStatusUE_IE(UnidadesEconomicasBean res) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        
        temporal = new ObjPrepareStatement("STATUS_EVALUACION", "STRING", res.getSTATUS_EVALUACION());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_RESULTADO_EVALUACION", "STRING", res.getSTATUS_RESULTADO_EVALUACION());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_GENERAL", "STRING", res.getSTATUS_GENERAL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PORCENTAJE_EVALUACION", "STRING", res.getPORCENTAJE_EVALUACION());
        arregloCampos.add(temporal);
        
        String condicion="";
        condicion="where ID_IE_UE='"+res.getID_IE_UE()+"'";
//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_INS_UE", arregloCampos,condicion);
    }

     public boolean ActualizaStatusGenUE(UnidadesEconomicasBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del ACTUALIZAR ...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("STATUS_GENERAL", "STRING", ue.getSTATUS_GENERAL());
        arregloCampos.add(temporal);

        String condicion = "WHERE ID_IE_UE=" + ue.getID_IE_UE() + "";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_INS_UE", arregloCampos, condicion);
    }
     
     public List ConsultaUEIEEva(String id_esc) throws Exception {
        String query = "SELECT ieue.id_ie_ue, ue.rfc,ue.razon_social,ue.nom_comercial,(SELECT nom_sector FROM cat_sector where id_sector=ue.sector)as sector,ieue.id_suc,(SELECT NOMBRE  FROM tbl_sucursales WHERE id_suc=ieue.id_suc)as nombre,ieue.status_ue,ieue.status_evaluacion,ieue.status_resultado_evaluacion,ieue.porcentaje_evaluacion,ieue.status_general "
                + "FROM tbl_ins_ue ieue INNER JOIN cat_unidad_economica ue on ieue.id_ue=ue.id_ue "
                + "where id_escuela='" + id_esc + "' and ieue.STATUS_UE='1' ";
        Constantes.enviaMensajeConsola("Consulta UE en IE ----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new UEIEMapper());
        return list;
    }
    
}
