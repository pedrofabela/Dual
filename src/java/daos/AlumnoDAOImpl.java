package daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.AlumnoBean;
import beans.UnidadesEconomicasBean;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mappers.AceptadosMapper;
import mappers.CatalogoMapper;
import mappers.ColoniaMapper;
import mappers.DatosAlumnoMapper;
import mappers.InteresadosMapper;
import mappers.InteresadosPEMapper;
import mappers.PEMapper;
import mappers.PEUEMapper;
import mappers.PreSeleccionadosMapper;
import mappers.SECTORMapper;
import mappers.SECTORSUBMapper;
import mappers.SucursalesMapper;
import mappers.UEIEMapper;
import mappers.UEMapper;
import mappers.ValidadosMapper;
import mappers.sectordosMapper;
import mappers.ueIeactivasMapper;
import utilidades.Constantes;
import utilidades.ObjPrepareStatement;

/**
 *
 * @author dinamarca
 */
public class AlumnoDAOImpl {

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

    public String ConsultaID(String cct) throws Exception {
        String query = "SELECT id_escuela FROM cat_cct WHERE cct='" + cct + "'";
        Constantes.enviaMensajeConsola("ConsultaIDESCUELA----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public String ConsultaCICLO() throws Exception {
        String query = "SELECT id_ciclo FROM cat_ciclos WHERE estatus=1";
        Constantes.enviaMensajeConsola("ConsultaIDCICLO----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public String VerificaAspirante(AlumnoBean al) throws Exception {
        String query = "SELECT ca.curp FROM cat_alumno ca INNER JOIN tbl_hist_alum hp ON ca.id_alumno=hp.id_alumno where ca.curp='" + al.getCURPA() + "' and  hp.status_general=1";
        Constantes.enviaMensajeConsola("VerificaAspirante----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public List ConsultaDatos(AlumnoBean al) throws Exception {
        String query = "SELECT ca.id_alumno,ca.curp,ca.nombre,ca.apellidop,ca.apellidom,ca.fec_nac,ca.entidad_nac,ca.matricula,ca.cve_pro_edu,ca.grado,ca.promedio,ca.situacion_academica,ca.tel_casa,ca.tel_cel,ca.email_ins, "
                + "    ca.email_per,ca.fecha_inc_padron,ca.no_seguro,ca.domicilio,ca.domicilio,ca.colonia,ca.localidad,ca.cp,ca.cve_mun,ca.fecha_inicio_dual,ca.tipo_alumno,ca.curp_padre,ca.nom_padre,ca.apellidop_padre, "
                + "    ca.apellidom_padre,ca.tel_padre,ca.email_padre,ca.domicilio_padre,ca.colonia_padre,ca.localidad_padre,ca.cp_padre,ca.cve_mun_padre,ca.sexo "
                + "    FROM cat_alumno ca where ca.curp='" + al.getCURPA() + "'";
        Constantes.enviaMensajeConsola("DatosAlumno----->" + query);
        List lista = null;
        lista = oraDaoFac.queryForList(query, new DatosAlumnoMapper());
        return lista;
    }

    public List ConsultaDatosID(AlumnoBean al) throws Exception {
        String query = "SELECT ca.id_alumno,ca.curp,ca.nombre,ca.apellidop,ca.apellidom,ca.fec_nac,ca.entidad_nac,ca.matricula,ca.cve_pro_edu,ca.grado,ca.promedio,ca.situacion_academica,ca.tel_casa,ca.tel_cel,ca.email_ins, "
                + "    ca.email_per,ca.fecha_inc_padron,ca.no_seguro,ca.domicilio,ca.domicilio,ca.colonia,ca.localidad,ca.cp,ca.cve_mun,ca.fecha_inicio_dual,ca.tipo_alumno,ca.curp_padre,ca.nom_padre,ca.apellidop_padre, "
                + "    ca.apellidom_padre,ca.tel_padre,ca.email_padre,ca.domicilio_padre,ca.colonia_padre,ca.localidad_padre,ca.cp_padre,ca.cve_mun_padre,ca.sexo "
                + "    FROM cat_alumno ca where ca.id_alumno='" + al.getID_ALUMNO() + "'";
        Constantes.enviaMensajeConsola("DatosAlumnoxid----->" + query);
        List lista = null;
        lista = oraDaoFac.queryForList(query, new DatosAlumnoMapper());
        return lista;
    }

    public AlumnoBean ConsultaDatosxobjeto(AlumnoBean al) throws Exception {
        String query = "SELECT ca.id_alumno,ca.curp,ca.nombre,ca.apellidop,ca.apellidom,ca.fec_nac,ca.entidad_nac,ca.matricula,ca.cve_pro_edu,ca.grado,ca.promedio,ca.situacion_academica,ca.tel_casa,ca.tel_cel,ca.email_ins, "
                + "    ca.email_per,ca.fecha_inc_padron,ca.no_seguro,ca.domicilio,ca.domicilio,ca.colonia,ca.localidad,ca.cp,ca.cve_mun,ca.fecha_inicio_dual,ca.tipo_alumno,ca.curp_padre,ca.nom_padre,ca.apellidop_padre, "
                + "    ca.apellidom_padre,ca.tel_padre,ca.email_padre,ca.domicilio_padre,ca.colonia_padre,ca.localidad_padre,ca.cp_padre,ca.cve_mun_padre,ca.sexo "
                + "    FROM cat_alumno ca where ca.curp='" + al.getCURPA() + "'";

        Constantes.enviaMensajeConsola("DatosAlumno----->" + query);
        AlumnoBean resu = (AlumnoBean) oraDaoFac.queryForObject(query, new DatosAlumnoMapper());
        return resu;
    }

    public List ConsultaPE(String usuario) throws Exception {
        String query = "SELECT id_cct_plan,(SELECT nom_carrera FROM CAT_PLAN WHERE ID_PLAN=tbl_cct_plan.id_plan)as programa "
                + "FROM TBL_CCT_PLAN  WHERE id_escuela = (SELECT id_escuela FROM cat_cct  where cct = '" + usuario + "')";
        Constantes.enviaMensajeConsola("VerificaAspirante----->" + query);
        List lista = null;
        lista = oraDaoFac.queryForList(query, new PEMapper());
        return lista;
    }

    public boolean GuardaInteresado(AlumnoBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("CURP", "STRING", ue.getCURP());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOMBRE", "STRING", ue.getNOMBRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APELLIDOP", "STRING", ue.getAPELLIDOP());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APELLIDOM", "STRING", ue.getAPELLIDOM());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FEC_NAC", "STRING", ue.getFEC_NAC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTIDAD_NAC", "STRING", ue.getENTIDAD_NAC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("SEXO", "STRING", ue.getSEXO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("MATRICULA", "STRING", ue.getMATRICULA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_PRO_EDU", "STRING", ue.getCVE_PRO_EDU());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("GRADO", "STRING", ue.getGRADO());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("CAT_ALUMNO", arregloCampos);
    }

    public boolean GuardaBitacora(AlumnoBean bita) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_ALUMNO", "STRING", bita.getID_ALUMNO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CURP", "STRING", bita.getCURP());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOMBRE", "STRING", bita.getNOMBRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APELLIDOP", "STRING", bita.getAPELLIDOP());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APELLIDOM", "STRING", bita.getAPELLIDOM());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FEC_NAC", "STRING", bita.getFEC_NAC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTIDAD_NAC", "STRING", bita.getENTIDAD_NAC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("SEXO", "STRING", bita.getSEXO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("MATRICULA", "STRING", bita.getMATRICULA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_PRO_EDU", "STRING", bita.getCVE_PRO_EDU());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("GRADO", "STRING", bita.getGRADO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PROMEDIO", "STRING", bita.getPROMEDIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("SITUACION_ACADEMICA", "STRING", bita.getSITUACION_ACADEMICA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TEL_CASA", "STRING", bita.getTEL_CASA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TEL_CEL", "STRING", bita.getTEL_CEL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("EMAIL_INS", "STRING", bita.getEMAIL_INS());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("EMAIL_PER", "STRING", bita.getEMAIL_PER());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FECHA_INC_PADRON", "STRING", bita.getFECHA_INC_PADRON());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NO_SEGURO", "STRING", bita.getNO_SEGURO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO", "STRING", bita.getDOMICILIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COLONIA", "STRING", bita.getCOLONIA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("LOCALIDAD", "STRING", bita.getLOCALIDAD());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP", "STRING", bita.getCP());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MUN", "STRING", bita.getCVE_MUN());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FECHA_INICIO_DUAL", "STRING", bita.getFECHA_INICIO_DUAL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TIPO_ALUMNO", "STRING", bita.getTIPO_ALUMNO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CURP_PADRE", "STRING", bita.getCURP_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOM_PADRE", "STRING", bita.getNOM_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APELLIDOP_PADRE", "STRING", bita.getAPELLIDOP_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APELLIDOM_PADRE", "STRING", bita.getAPELLIDOM_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TEL_PADRE", "STRING", bita.getTEL_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("EMAIL_PADRE", "STRING", bita.getEMAIL_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO_PADRE", "STRING", bita.getDOMICILIO_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COLONIA_PADRE", "STRING", bita.getCOLONIA_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("LOCALIDAD_PADRE", "STRING", bita.getLOCALIDAD_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP_PADRE", "STRING", bita.getCP_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MUN_PADRE", "STRING", bita.getCVE_MUN_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("USUARIO", "STRING", bita.getUSUARIO());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("BINNACLE_CHANGE_STUDENTS", arregloCampos);
    }

    public boolean ActualizaInteresado(AlumnoBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("CURP", "STRING", ue.getCURP());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOMBRE", "STRING", ue.getNOMBRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APELLIDOP", "STRING", ue.getAPELLIDOP());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APELLIDOM", "STRING", ue.getAPELLIDOM());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FEC_NAC", "STRING", ue.getFEC_NAC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTIDAD_NAC", "STRING", ue.getENTIDAD_NAC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("SEXO", "STRING", ue.getSEXO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("MATRICULA", "STRING", ue.getMATRICULA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_PRO_EDU", "STRING", ue.getCVE_PRO_EDU());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("GRADO", "STRING", ue.getGRADO());
        arregloCampos.add(temporal);

        String Condicion = "where id_alumno='" + ue.getID_ALUMNO() + "' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("CAT_ALUMNO", arregloCampos, Condicion);
    }

    public String ConsultaID_ALUMNO(String curp) throws Exception {
        String query = "SELECT id_alumno FROM cat_alumno where curp='" + curp + "'";
        Constantes.enviaMensajeConsola("consulta id_alumno----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public boolean GuardaStatus(AlumnoBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_ALUMNO", "STRING", ue.getID_ALUMNO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_GENERAL", "STRING", "0");
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_PROCESO", "STRING", ue.getSTATUS_PROCESO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", ue.getID_ESCUELA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_CCT_PLAN", "STRING", ue.getCVE_PRO_EDU());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_CICLO", "STRING", ue.getID_CICLO());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_HIST_ALUM", arregloCampos);
    }

    public List ConsultaInteresados(AlumnoBean al) throws Exception {
        String query = "SELECT * FROM (SELECT sa.id_hist_alum, ca.id_alumno,ca.curp,ca.nombre || ' ' || ca.apellidop || ' ' || ca.apellidom as nombre_completo, ca.fec_nac,ca.entidad_nac,ca.sexo,ca.matricula, "
                + "(select p.nom_carrera from tbl_cct_plan tc INNER JOIN cat_plan p on tc.id_plan=p.id_plan WHERE tc.id_cct_plan=cve_pro_edu) as cve_pro_edu, "
                + "(SELECT catalogo.descripcion FROM catalogo where id_catalogo=grado) as grado, "
                + "sa.status_proceso FROM cat_alumno CA INNER JOIN TBL_HIST_ALUM SA ON ca.id_alumno=sa.id_alumno  WHERE id_escuela='" + al.getID_ESCUELA() + "'  and sa.status_proceso=1)ORDER BY CVE_PRO_EDU, GRADO ASC ";
        Constantes.enviaMensajeConsola("ConsultaInteresados----->" + query);
        List lista = null;
        lista = oraDaoFac.queryForList(query, new InteresadosMapper());
        return lista;
    }

    public boolean EliminaInteresado(AlumnoBean al) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del delete ELIMINAR ALUMNO...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_HIST_ALUM", "STRING", al.getID_HISTORICO());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryDelete("TBL_HIST_ALUM", arregloCampos);
    }

    public List ConsultaInteresadosPE(AlumnoBean al) throws Exception {
        String query = "SELECT * FROM (SELECT sa.id_hist_alum, CA.id_alumno,CA.curp, CA.nombre || ' ' || CA.apellidop || ' ' || CA.apellidom as nombre_completo, CA.matricula,ca.cve_pro_edu, "
                + "(select p.nom_carrera from tbl_cct_plan tc INNER JOIN cat_plan p on tc.id_plan=p.id_plan WHERE tc.id_cct_plan=CA.cve_pro_edu) as nom_pro_edu , "
                + "(SELECT catalogo.descripcion FROM catalogo where id_catalogo=CA.grado) as grado , "
                + "ca.promedio,ca.situacion_academica,ca.tel_casa,ca.tel_cel,ca.email_ins,ca.email_per,TO_CHAR(sa.FECHA_INC_PADRON) as FECHA_INC_PADRON,sa.res_eva,sa.status_proceso "
                + "FROM cat_alumno  CA INNER JOIN tbl_hist_alum SA ON ca.id_alumno=sa.id_alumno  "
                + "WHERE  ca.cve_pro_edu in (SELECT cp.id_cct_plan FROM tbl_plan_responsable pr INNER JOIN tbl_cct_plan cp on cp.id_plan=pr.id_plan where cp.id_escuela='"+al.getID_ESCUELA()+"' and pr.perfil='"+al.getPERFIL()+"' AND  pr.id_persona='"+al.getID_PERSONA()+"' and pr.estatus=1)   ORDER BY ca.cve_pro_edu "
                + ") where STATUS_PROCESO=1 OR STATUS_PROCESO=2";
        Constantes.enviaMensajeConsola("ConsultaInteresadosPE----->" + query);
        List lista = null;
        lista = oraDaoFac.queryForList(query, new InteresadosPEMapper());
        return lista;
    }

    public boolean ActualizaDatos1(AlumnoBean al) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("PROMEDIO", "STRING", al.getPROMEDIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("SITUACION_ACADEMICA", "STRING", al.getSITUACION_ACADEMICA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TEL_CASA", "STRING", al.getTEL_CASA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TEL_CEL", "STRING", al.getTEL_CEL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("EMAIL_INS", "STRING", al.getEMAIL_INS());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("EMAIL_PER", "STRING", al.getEMAIL_PER());
        arregloCampos.add(temporal);

        String Condicion = "where id_alumno='" + al.getID_ALUMNO() + "'";
//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("CAT_ALUMNO", arregloCampos, Condicion);
    }

    public boolean ActualizaStatus1(AlumnoBean al) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("STATUS_PROCESO", "STRING", al.getSTATUS_PROCESO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("RES_EVA", "STRING", al.getRES_EVA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FECHA_INC_PADRON", "STRING", al.getFECHA_INC_PADRON());
        arregloCampos.add(temporal);

        String Condicion = "where ID_HIST_ALUM='" + al.getID_HISTORICO() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_HIST_ALUM", arregloCampos, Condicion);
    }

    public List ueIeActiva(String IDESCUELA) throws Exception {
        String query = " SELECT ID_IE_UE, RFC,  CASE WHEN NOMBRE IS NOT NULL THEN ('RS:\"'||RAZON_SOCIAL||'\"/'||'NC:\"' || NOM_COMERCIAL||'\"/'||'SUC:\"'||NOMBRE ||'\"') ELSE 'RS:\"'||RAZON_SOCIAL||'\"/'||'NC:\"'||NOM_COMERCIAl||'\"' END AS RAZON_SOCIAL FROM VISTA_IE_UE WHERE ID_ESCUELA='" + IDESCUELA + "' AND STATUS_GENERAL=1";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new ueIeactivasMapper());
        return list;
    }

    public List ConsultaPreseleccionados(AlumnoBean al) throws Exception {
        String query = "SELECT sa.id_hist_alum,sa.id_ie_ue,ca.id_alumno,ca.curp,ca.nombre || ' ' || ca.apellidop || ' ' || ca.apellidom as nombre_completo, ca.fec_nac,ca.entidad_nac,ca.sexo,ca.matricula, "
                + "(select p.nom_carrera from tbl_cct_plan tc INNER JOIN cat_plan p on tc.id_plan=p.id_plan WHERE tc.id_cct_plan=cve_pro_edu) as cve_pro_edu, "
                + "(SELECT catalogo.descripcion FROM catalogo where id_catalogo=grado) as grado, sa.status_proceso "
                + "FROM cat_alumno CA INNER JOIN TBL_HIST_ALUM SA ON ca.id_alumno=sa.id_alumno  WHERE sa.status_proceso='2' or sa.status_proceso='3' AND id_escuela='" + al.getID_ESCUELA() + "' order by sa.status_proceso, cve_pro_edu, grado ";
        Constantes.enviaMensajeConsola("CONSULTAPRESELECCIONADOS----->" + query);
        List lista = null;
        lista = oraDaoFac.queryForList(query, new PreSeleccionadosMapper());
        return lista;
    }

    public boolean ValidaAlumnos(AlumnoBean al) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_IE_UE", "STRING", al.getID_UE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_PROCESO", "STRING", al.getSTATUS_PROCESO());
        arregloCampos.add(temporal);

        String Condicion = "where ID_HIST_ALUM='" + al.getID_HISTORICO() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_HIST_ALUM", arregloCampos, Condicion);
    }

    public List ConsultaPEUE(AlumnoBean al) throws Exception {
        String query = "SELECT cp.id_cct_plan,p.nom_carrera "
                + "FROM tbl_plan_responsable pr INNER JOIN tbl_cct_plan cp on cp.id_plan=pr.id_plan "
                + "INNER JOIN cat_plan p on cp.id_plan=p.id_plan "
                + "where pr.id_persona='" + al.getID_PERSONA() + "'AND cp.id_escuela='" + al.getID_ESCUELA() + "' and pr.perfil='" + al.getPERFIL() + "'";

        Constantes.enviaMensajeConsola("PE vinculados----->" + query);
        List lista = null;
        lista = oraDaoFac.queryForList(query, new PEUEMapper());
        return lista;
    }

    public List ConsultaAlumnosValidos(AlumnoBean al) throws Exception {
        String query = "SELECT sa.id_hist_alum, CA.id_alumno,CA.curp, CA.nombre || ' ' || CA.apellidop || ' ' || CA.apellidom as nombre_completo, CA.matricula,ca.cve_pro_edu, "
                + "(select p.nom_carrera from tbl_cct_plan tc INNER JOIN cat_plan p on tc.id_plan=p.id_plan WHERE tc.id_cct_plan=CA.cve_pro_edu) as nom_pro_edu , "
                + "(SELECT catalogo.descripcion FROM catalogo where id_catalogo=CA.grado) as grado , "
                + "(SELECT catalogo.descripcion FROM catalogo where id_catalogo=CA.promedio) as promedio, "
                + "(SELECT catalogo.descripcion FROM catalogo where id_catalogo=ca.situacion_academica) as situacion_academica, "
                + "ca.tel_casa,ca.tel_cel,ca.email_ins,ca.email_per,sa.res_eva,TO_CHAR(sa.FECHA_INC_PADRON) as FECHA_INC_PADRON,sa.status_proceso "
                + "FROM cat_alumno ca INNER JOIN tbl_hist_alum sa ON ca.id_alumno=sa.id_alumno where id_ie_ue='" + al.getID_UE() + "' AND sa.status_proceso=3 OR sa.status_proceso=4  and CA.cve_pro_edu='" + al.getAUX_CVE_PRO_EDU() + "'";

        Constantes.enviaMensajeConsola("alumnos Validados----->" + query);
        List lista = null;
        lista = oraDaoFac.queryForList(query, new ValidadosMapper());
        return lista;
    }

    public boolean AprobarAlumno(AlumnoBean al) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("STATUS_PROCESO", "STRING", al.getSTATUS_PROCESO());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("AUX_RES_ACAD", "STRING", al.getAUX_RES_ACAD());
        arregloCampos.add(temporal);

        String Condicion = "where ID_HIST_ALUM='" + al.getID_HISTORICO() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_HIST_ALUM", arregloCampos, Condicion);
    }

    public boolean DesAprobarAlumno(AlumnoBean al) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_IE_UE", "STRING", al.getID_UE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_ALUMNO", "STRING", al.getSTATUS_ALUMNO());
        arregloCampos.add(temporal);

        String Condicion = "where ID_HIST_ALUM='" + al.getID_HISTORICO() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_HIST_ALUM", arregloCampos, Condicion);
    }

    public boolean RechazarAlumno(AlumnoBean al) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_IE_UE", "STRING", al.getID_UE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_PROCESO", "STRING", al.getSTATUS_PROCESO());
        arregloCampos.add(temporal);

        String Condicion = "where ID_HIST_ALUM='" + al.getID_HISTORICO() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_HIST_ALUM", arregloCampos, Condicion);
    }

    public List ConsultaAceptados(AlumnoBean al) throws Exception {
        String query = " SELECT * FROM (SELECT SA.AUX_RES_ACAD, SA.ID_IE_UE, SA.ID_CCT_PLAN, sa.id_hist_alum, CA.id_alumno,CA.curp, CA.nombre || ' ' || CA.apellidop || ' ' || CA.apellidom as nombre_completo,CA.fec_nac,CA.matricula,ca.cve_pro_edu, "
                + "(select p.nom_carrera from tbl_cct_plan tc INNER JOIN cat_plan p on tc.id_plan=p.id_plan WHERE tc.id_cct_plan=CA.cve_pro_edu) as nom_pro_edu ,"
                + "(SELECT catalogo.descripcion FROM catalogo where id_catalogo=CA.grado) as grado, "
                + "ca.no_seguro,ca.domicilio,ca.colonia,ca.localidad,ca.cp,ca.cve_mun,TO_CHAR(ca.fecha_inicio_dual) as fecha_inicio_dual,ca.tipo_alumno,ca.curp_padre,ca.nom_padre,ca.apellidop_padre,ca.apellidom_padre,ca.tel_padre,ca.email_padre,ca.domicilio_padre,ca.colonia_padre, "
                + "ca.localidad_padre,ca.cp_padre,ca.cve_mun_padre,ca.mismo_domicilio, sa.status_proceso "
                + "FROM cat_alumno  CA INNER JOIN tbl_hist_alum SA ON ca.id_alumno=sa.id_alumno WHERE CA.cve_pro_edu IN (SELECT cp.id_cct_plan FROM tbl_plan_responsable pr INNER JOIN tbl_cct_plan cp on cp.id_plan=pr.id_plan where  pr.id_persona='" + al.getID_PERSONA() + "'AND cp.id_escuela='" + al.getID_ESCUELA() + "' and pr.perfil='" + al.getPERFIL() + "') "
                + "ORDER BY  sa.id_hist_alum,ca.cve_pro_edu) where STATUS_PROCESO=4 OR STATUS_PROCESO=5 OR STATUS_PROCESO=6";
        Constantes.enviaMensajeConsola("ConsultaAceptados----->" + query);
        List lista = null;
        lista = oraDaoFac.queryForList(query, new AceptadosMapper());
        return lista;
    }

    public List ConsultaColonia(String obj) throws Exception {
        String query = "SELECT cp.asenta,mun.idn_mpio,mun.desc_mpio from cat_cp cp INNER JOIN cat_mun_nac mun on cp.idn_mpio=mun.idn_mpio WHERE cp.cp='" + obj + "'";
        Constantes.enviaMensajeConsola("CONSULTA COLONIAS ---> " + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new ColoniaMapper());
        return list;
    }

    public String consultaHistoricoAlumno(AlumnoBean al) throws Exception {
        String query = "SELECT id_alumno FROM tbl_hist_alum where id_alumno='" + al.getID_ALUMNO() + "' and status_general >1 and id_escuela='" + al.getID_ESCUELA() + "'";
        Constantes.enviaMensajeConsola("consultaHistoricoAlumno----->" + query);
        String valor = null;
        valor = oraDaoFac.queryStringUnCampo(query);
        return valor;
    }

    public boolean ActualizaDatosDUAL(AlumnoBean al) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("NO_SEGURO", "STRING", al.getNO_SEGURO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO", "STRING", al.getDOMICILIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COLONIA", "STRING", al.getCOLONIA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("LOCALIDAD", "STRING", al.getLOCALIDAD());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP", "STRING", al.getCP());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MUN", "STRING", al.getCVE_MUN());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FECHA_INICIO_DUAL", "STRING", al.getFECHA_INICIO_DUAL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TIPO_ALUMNO", "STRING", al.getTIPO_ALUMNO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CURP_PADRE", "STRING", al.getCURP_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOM_PADRE", "STRING", al.getNOM_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APELLIDOP_PADRE", "STRING", al.getAPELLIDOP_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APELLIDOM_PADRE", "STRING", al.getAPELLIDOM_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TEL_PADRE", "STRING", al.getTEL_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("EMAIL_PADRE", "STRING", al.getEMAIL_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO_PADRE", "STRING", al.getDOMICILIO_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COLONIA_PADRE", "STRING", al.getCOLONIA_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("LOCALIDAD_PADRE", "STRING", al.getLOCALIDAD_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP_PADRE", "STRING", al.getCP_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MUN_PADRE", "STRING", al.getCVE_MUN_PADRE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("MISMO_DOMICILIO", "STRING", al.getMISMO_DOMICILIO());
        arregloCampos.add(temporal);

        String Condicion = "where id_alumno='" + al.getID_ALUMNO() + "'";
//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("CAT_ALUMNO", arregloCampos, Condicion);
    }

    public boolean ActualizaStatusDUAL(AlumnoBean al) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("STATUS_PROCESO", "STRING", al.getSTATUS_PROCESO());
        arregloCampos.add(temporal);

        String Condicion = "where ID_HIST_ALUM='" + al.getID_HISTORICO() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_HIST_ALUM", arregloCampos, Condicion);
    }

}
