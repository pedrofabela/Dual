package daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.UnidadesEconomicasBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mappers.CatalogoMapper;
import mappers.ColoniaMapper;
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
public class UnidadesEconomicasDAOImpl {

    OracleDAOFactory oraDaoFac = new OracleDAOFactory();

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

    public List ConsultaRFC(String rfc) throws Exception {
        String query = "SELECT id_ue,rfc,razon_social,nom_comercial,giro,sector,domicilio,colonia,localidad,cp,(SELECT cm.DESC_MPIO FROM cat_mun_nac cm where cm.id_municipio=cat_unidad_economica.cve_municipio ) as municipio "
                + "FROM cat_unidad_economica WHERE rfc='" + rfc + "' AND STATUS=1";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new UEMapper());
        return list;
    }

    public List ConsultaSector() throws Exception {
        String query = "SELECT ID_SECTOR,NOM_SECTOR FROM CAT_SECTOR WHERE STATUS=1";
        Constantes.enviaMensajeConsola("Consulta SECTOR----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new SECTORMapper());
        return list;
    }
    
     public List ConsultaSectorSub(String sector) throws Exception {
        String query = "SELECT ID_SUBSECTOR,NOM_SUBSECTOR FROM CAT_SUBSECTOR WHERE ID_SECTOR='"+sector+"' AND STATUS=1 ";
        Constantes.enviaMensajeConsola("Consulta SECTORSUB----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new SECTORSUBMapper());
        return list;
    }


    public boolean agregarUE(UnidadesEconomicasBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", ue.getID_ESC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_UE", "STRING", ue.getID_UE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_UE", "STRING", ue.getSTATUS_UE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_EVALUACION", "STRING", ue.getSTATUS_EVALUACION());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_RESULTADO_EVALUACION", "STRING", ue.getSTATUS_RESULTADO_EVALUACION());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_GENERAL", "STRING", ue.getSTATUS_GENERAL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_SUC", "STRING", ue.getID_SUC());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_INS_UE", arregloCampos);
    }

    public List ConsultaUEIE(String id_esc) throws Exception {
        String query = "SELECT ieue.id_ie_ue, ue.rfc,ue.razon_social,ue.nom_comercial,(SELECT nom_sector FROM cat_sector where id_sector=ue.sector)as sector,ieue.id_suc,(SELECT NOMBRE  FROM tbl_sucursales WHERE id_suc=ieue.id_suc)as nombre,ieue.status_ue,ieue.status_evaluacion,ieue.status_resultado_evaluacion,ieue.porcentaje_evaluacion,ieue.status_general "
                + "FROM tbl_ins_ue ieue INNER JOIN cat_unidad_economica ue on ieue.id_ue=ue.id_ue "
                + "where id_escuela='" + id_esc + "'";
        Constantes.enviaMensajeConsola("Consulta UE en IE ----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new UEIEMapper());
        return list;
    }

    public boolean EliminarUE(UnidadesEconomicasBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del delete ELIMINAR UE...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_IE_UE", "STRING", ue.getID_IE_UE());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryDelete("TBL_INS_UE", arregloCampos);
    }

    public String validaSoloUE(UnidadesEconomicasBean ue) throws Exception {
        String query = "SELECT ieue.id_ie_ue FROM tbl_ins_ue ieue  where id_escuela=" + ue.getID_ESC() + " and id_ue=" + ue.getID_UE() + "";
        Constantes.enviaMensajeConsola("valida si  UE en IE ----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public String validaUE(UnidadesEconomicasBean ue) throws Exception {
        String query = "SELECT ieue.id_ie_ue FROM tbl_ins_ue ieue  where id_escuela=" + ue.getID_ESC() + " and id_ue=" + ue.getID_UE() + " and id_suc='" + ue.getID_SUC() + "'";
        Constantes.enviaMensajeConsola("valida si  UE en IE ----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public List ConsultaColonia(String obj) throws Exception {
        String query = "SELECT cp.asenta,mun.idn_mpio,mun.desc_mpio from cat_cp cp INNER JOIN cat_mun_nac mun on cp.idn_mpio=mun.idn_mpio WHERE cp.cp='" + obj + "'";
        Constantes.enviaMensajeConsola("CONSULTA COLONIAS ---> " + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new ColoniaMapper());
        return list;
    }

    public boolean GuardaUE(UnidadesEconomicasBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("RFC", "STRING", ue.getRFCR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("RAZON_SOCIAL", "STRING", ue.getRAZON_SOCIALR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOM_COMERCIAL", "STRING", ue.getNOMBRE_COMERR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("GIRO", "STRING", ue.getGIROR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("SECTOR", "STRING", ue.getSECTORR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO", "STRING", ue.getDOMICILIOR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COLONIA", "STRING", ue.getCOLONIAR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("LOCALIDAD", "STRING", ue.getLOCALIDADR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP", "STRING", ue.getCPR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MUNICIPIO", "STRING", ue.getCVE_MUNICIPIOR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS", "STRING", "1");
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("CAT_UNIDAD_ECONOMICA", arregloCampos);
    }

    public List ConsultaSucursales(UnidadesEconomicasBean ue) throws Exception {
        String query = "SELECT id_suc,nombre,domicilio,cp,colonia,(SELECT cm.DESC_MPIO FROM cat_mun_nac cm where cm.id_municipio=tbl_sucursales.cve_mun ) as municipio,cct_plantel,tipo\n"
                + "FROM tbl_sucursales WHERE id_ue='" + ue.getID_UE() + "'";
        Constantes.enviaMensajeConsola("Consulta sucursales----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new SucursalesMapper());
        return list;
    }

    public String validaPlantel(UnidadesEconomicasBean ue) throws Exception {
        String query = "SELECT id_suc FROM tbl_sucursales  where id_ue=" + ue.getID_UE() + " and cct_plantel='" + ue.getCCT_PLA() + "' ";
        Constantes.enviaMensajeConsola("valida si  plantel exsiste en sucursales ----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public boolean GuardaPla(UnidadesEconomicasBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_UE", "STRING", ue.getID_UE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOMBRE", "STRING", ue.getNOM_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO", "STRING", ue.getDOM_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP", "STRING", ue.getCP_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COLONIA", "STRING", ue.getCOLONIA_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MUN", "STRING", ue.getCVE_MUNICIPIO_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CCT_PLANTEL", "STRING", ue.getCCT_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TIPO", "STRING", ue.getOPCION());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_SUCURSALES", arregloCampos);
    }

    public String validaSuc(UnidadesEconomicasBean ue) throws Exception {
        String query = "SELECT id_suc FROM tbl_sucursales  where id_ue=" + ue.getID_UE() + " and nombre='" + ue.getNOM_SUC() + "' and cve_mun='" + ue.getCVE_MUNICIPIO_SUC() + "' ";
        Constantes.enviaMensajeConsola("valida si  sucursal exsiste en sucursales ----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public boolean GuardaSuc(UnidadesEconomicasBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_UE", "STRING", ue.getID_UE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOMBRE", "STRING", ue.getNOM_SUC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO", "STRING", ue.getDOM_SUC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP", "STRING", ue.getCP_SUC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COLONIA", "STRING", ue.getCOLONIA_SUC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MUN", "STRING", ue.getCVE_MUNICIPIO_SUC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CCT_PLANTEL", "STRING", ue.getCCT_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TIPO", "STRING", ue.getOPCION());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_SUCURSALES", arregloCampos);
    }

    public String consultaID_UE(String rfc) throws Exception {
        String query = "SELECT id_ue FROM cat_unidad_economica  where rfc='" + rfc + "' ";
        Constantes.enviaMensajeConsola("consulta ID_UE asignado ----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public String consultaID_PLA(UnidadesEconomicasBean ue) throws Exception {
        String query = "SELECT id_suc FROM tbl_sucursales  where id_ue='" + ue.getID_UE() + "' and nombre='" + ue.getNOM_PLA() + "' ";
        Constantes.enviaMensajeConsola("consulta ID_SUC_PLA asignado ----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public String consultaID_SUC(UnidadesEconomicasBean ue) throws Exception {
        String query = "SELECT id_suc FROM tbl_sucursales  where id_ue='" + ue.getID_UE() + "' and nombre='" + ue.getNOM_SUC() + "' ";
        Constantes.enviaMensajeConsola("consulta ID_SUC asignado ----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public List ConsultaCatalogo(String opc) throws Exception {
        String query = "SELECT ID_CATALOGO,DESCRIPCION FROM CATALOGO WHERE NO_CATALOGO='" + opc + "'";
        Constantes.enviaMensajeConsola("CONSULTA CATALOGO ---> " + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new CatalogoMapper());
        return list;
    }

}
