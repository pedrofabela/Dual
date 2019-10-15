package daos;

import beans.UnidadesEconomicasBean;
import beans.actividadesBean;
import beans.competenciaBean;
import beans.escuelaBean;
import beans.materiaBean;
import beans.programaEsBean;
import beans.renapoBean;
import beans.usuarioBean;
import java.util.ArrayList;
import java.util.List;
import mappers.Mapper;
import mappers.actividadMapper;
import mappers.cctPlanMapper;
import mappers.ciclosMapper;
import mappers.competenciaMapper;
import mappers.descargaProgramasMapper;
import mappers.escuelaMapper;
import mappers.materiaMapper;
import mappers.periodoMapper;
import mappers.planCctMapper;
import mappers.planCttResMapper;
import mappers.programaCctMapper;
import mappers.resProgEduConMapper;
import mappers.resProgEstMapper;
import mappers.subsistemaMapper;
import mappers.tipoPeriodoMapper;
import mappers.ueIeactivasMapper;
import mappers.tableroGralUEMapper;

import utilidades.Constantes;
import utilidades.ObjPrepareStatement;

public class ConsultaDAOImpl extends OracleDAOFactory implements ConsultaDAO {

    OracleDAOFactory oraDaoFac = new OracleDAOFactory();

    @Override
    public List programasEdu(programaEsBean programa) throws Exception {
        String query = " SELECT * FROM (SELECT ID_PLAN, CVE_PLAN, NOM_CARRERA, STATUS, TO_CHAR(FECHA_AUT_DUAL,'DD/MM/YYYY') AS FECHA_AUT_DUAL, ENFASIS, CVE_PLAN_EST, TIPO_PERIODO, NUMERO_PERIODO, VERSION, ID_SUBSISTEMA, ID_NIVEL   FROM CAT_PLAN WHERE  CVE_PLAN LIKE '%" + programa.getCONSULTA_CVE_PLAN() + "%' OR NOM_CARRERA LIKE '%" + programa.getCONSULTA_CVE_PLAN() + "%' AND STATUS=1) WHERE ID_SUBSISTEMA='" + programa.getID_SUBSISTEMA_CONSULTA() + "'";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new programaCctMapper());
        return list;
    }

    public List programasEduAdmin(programaEsBean programa) throws Exception {
        String query = "SELECT * FROM (SELECT ID_PLAN, CVE_PLAN, NOM_CARRERA, STATUS, TO_CHAR(FECHA_AUT_DUAL,'DD/MM/YYYY') AS FECHA_AUT_DUAL, ENFASIS, CVE_PLAN_EST, TIPO_PERIODO, NUMERO_PERIODO, VERSION, ID_SUBSISTEMA, ID_NIVEL   FROM CAT_PLAN WHERE ID_NIVEL='" + programa.getID_NIVEL_CONSULTA() + "') WHERE ID_SUBSISTEMA='" + programa.getID_SUBSISTEMA_CONSULTA() + "' ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new programaCctMapper());
        return list;
    }

    public List programasEduAdminAct(escuelaBean escuela) throws Exception {
        String query = "SELECT * FROM (SELECT ID_PLAN, CVE_PLAN, NOM_CARRERA, STATUS, TO_CHAR(FECHA_AUT_DUAL,'DD/MM/YYYY') AS FECHA_AUT_DUAL, ENFASIS, CVE_PLAN_EST, TIPO_PERIODO, NUMERO_PERIODO, VERSION, ID_SUBSISTEMA, ID_NIVEL   FROM CAT_PLAN ) WHERE ID_PLAN='" + escuela.getAUXPLAN() + "' ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new programaCctMapper());
        return list;
    }

    public List subsistema(programaEsBean programa) throws Exception {
        String query = "SELECT ID_SUBSISTEMA, NOMBRE_SUBISTEMA, ID_NIVEL FROM CAT_SUBSISTEMA WHERE ID_NIVEL='" + programa.getID_NIVEL_CONSULTA() + "' AND TO_NUMBER(ID_SUBSISTEMA)<20 ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new subsistemaMapper());
        return list;
    }

    public List cctPlanMateria(escuelaBean escuela) throws Exception {
        String query = "SELECT ID_CCT_PLAN,  ID_MATERIA,  NOMBRE_MATERIA,  FECH_REG_MATERIA,  CVE_MATERIA FROM TBL_PLAN_MATERIA WHERE ID_CCT_PLAN='" + escuela.getAUXIDCCTPLAN() + "' ORDER BY ID_MATERIA ASC ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new materiaMapper());
        return list;
    }

    public List tipoPeriodo(escuelaBean escuela) throws Exception {
        String query = "SELECT ID_TIPO_PERIODO, TIPO_PERIODO FROM CAT_TIPO_PERIDO";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new tipoPeriodoMapper());
        return list;
    }

    public List periodo(escuelaBean escuela) throws Exception {
        String query = "SELECT ID_PERIODO, PERIODO FROM CAT_PERIODO";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new periodoMapper());
        return list;
    }

    public List programasIdEs(escuelaBean escuela) throws Exception {
        String query = "SELECT ID_ESCUELA,  CCT,  NOMBRE_ESCUELA,  DIRECCION,  MUNICIPIO,  ID_NIVEL,  ID_SUBSISTEMA FROM CAT_CCT WHERE CCT='" + escuela.getCCT() + "' AND ROWNUM=1 ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new escuelaMapper());
        return list;
    }

    public List escuelasSubsistema(escuelaBean escuela) throws Exception {
        String query = "SELECT ID_ESCUELA,  CCT,  NOMBRE_ESCUELA,  DIRECCION,  MUNICIPIO,  ID_NIVEL,  ID_SUBSISTEMA FROM CAT_CCT WHERE ID_SUBSISTEMA='" + escuela.getAUXSUBSISTEMA() + "' ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new escuelaMapper());
        return list;
    }

    public List validaCctPlan(escuelaBean escuela) throws Exception {
        String query = "SELECT ID_PLAN, ID_ESCUELA, ESTATUS, ID_CCT_PLAN FROM TBL_CCT_PLAN WHERE ID_PLAN='" + escuela.getAUXPLAN() + "' AND ID_ESCUELA='" + escuela.getID_ESCUELA() + "'";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new cctPlanMapper());
        return list;
    }

    public List validaMateriaComp(materiaBean materia) throws Exception {
        String query = "SELECT ID_MATERIA,  ID_COMPETENCIA,  COMPETENCIA,  FECH_REC_COMPETENCIA FROM TBL_MATERIA_COMPETENCIA WHERE ID_MATERIA='" + materia.getAUXMATERIA() + "' ORDER BY ID_COMPETENCIA ASC";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new competenciaMapper());
        return list;
    }

    public List actividadCct(competenciaBean competencia) throws Exception {
        String query = "SELECT ID_COMPETENCIA,  ID_ACTIVIDAD,  ACTIVIDAD,  FECHA_REG_ACTIVIDAD FROM TBL_COMPETENCIA_ACTIVIDAD WHERE ID_COMPETENCIA='" + competencia.getAUXCOMPETENCIA() + "' ORDER BY ID_ACTIVIDAD ASC ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new actividadMapper());
        return list;
    }

    public List resProgEst(renapoBean renapo) throws Exception {
        String query = " SELECT ID_PERSONA,  CURP_PERSONA,  NOMBRE_PERSONA,  APATERNO_PERSONA,  AMATERNO_PERSONA,  TO_CHAR(FECNAC_PERSONA,'DD/MM/YYYY') AS FECNAC_PERSONA,  NAC_PERSONA,  ENTIDAD_NACIMIENTO,  GENERO,  TELCASA_PERSONA,  TELCEL_PERSONA,  CORREO_PERSONA,  RFC,  ID_ESCUELA FROM CAT_PERSONA WHERE CURP_PERSONA='" + renapo.getCONSULTA_CURP() + "'";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new resProgEstMapper());
        return list;
    }

    public List resProgEstInst(renapoBean renapo) throws Exception {
        String query = " SELECT ID_RESPROG_INST,  RPE.ID_PERSONA,  RPE.ID_ESCUELA,   RPE.PERFIL, PERSONA.CURP_PERSONA, PERSONA.NOMBRE_PERSONA||' '|| PERSONA.APATERNO_PERSONA||' '|| PERSONA.AMATERNO_PERSONA AS NOMBRE_PERSONA, USU.USUARIO_LOGIN, USU.PASSWORD, USU.ESTATUS, USU.ID_USUARIO, RPE.PERFIL, RPE.ID_PERSONA FROM TBL_RESPE_INST RPE JOIN (SELECT ID_PERSONA, CURP_PERSONA,  NOMBRE_PERSONA,  APATERNO_PERSONA,  AMATERNO_PERSONA,  CORREO_PERSONA FROM CAT_PERSONA ) PERSONA ON RPE.ID_PERSONA=PERSONA.ID_PERSONA  JOIN (SELECT   PASSWORD,  PERFIL,    USUARIO_LOGIN,  ID_PERSONA,  ESTATUS, ID_USUARIO FROM TBL_USUARIOS WHERE PERFIL='" + renapo.getAUXPERFIL() + "' AND ID_ESCUELA='" + renapo.getAUXESCUELA() + "') USU ON USU.ID_PERSONA=RPE.ID_PERSONA WHERE RPE.ID_ESCUELA='" + renapo.getAUXESCUELA() + "' AND RPE.PERFIL='" + renapo.getAUXPERFIL() + "' ORDER BY ID_RESPROG_INST DESC ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new resProgEduConMapper());
        return list;
    }

    public List resUE(renapoBean renapo, UnidadesEconomicasBean ue) throws Exception {
        String query = " SELECT ID_RESPROG_INST,  RPE.ID_PERSONA,  RPE.ID_ESCUELA,   RPE.PERFIL, PERSONA.CURP_PERSONA, PERSONA.NOMBRE_PERSONA||' '|| PERSONA.APATERNO_PERSONA||' '|| PERSONA.AMATERNO_PERSONA AS NOMBRE_PERSONA, USU.USUARIO_LOGIN, USU.PASSWORD, USU.ESTATUS, USU.ID_USUARIO, RPE.PERFIL, RPE.ID_PERSONA FROM TBL_RESPE_INST RPE JOIN (SELECT ID_PERSONA, CURP_PERSONA,  NOMBRE_PERSONA,  APATERNO_PERSONA,  AMATERNO_PERSONA,  CORREO_PERSONA FROM CAT_PERSONA ) PERSONA ON RPE.ID_PERSONA=PERSONA.ID_PERSONA  JOIN (SELECT   PASSWORD,  PERFIL,    USUARIO_LOGIN,  ID_PERSONA,  ESTATUS, ID_USUARIO FROM TBL_USUARIOS WHERE PERFIL='" + renapo.getAUXPERFIL() + "' AND ID_ESCUELA='" + renapo.getAUXESCUELA() + "' AND ID_IE_UE='" + ue.getID_IE_UE() + "') USU ON USU.ID_PERSONA=RPE.ID_PERSONA WHERE RPE.ID_ESCUELA='" + renapo.getAUXESCUELA() + "' AND RPE.PERFIL='" + renapo.getAUXPERFIL() + "' ORDER BY  ID_RESPROG_INST DESC ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new resProgEduConMapper());
        return list;
    }

    public List ueIeActiva(renapoBean renapo) throws Exception {
        String query = " SELECT ID_IE_UE, RFC,  CASE WHEN NOMBRE IS NOT NULL THEN ('RS:\"'||RAZON_SOCIAL||'\"/'||'NC:\"' || NOM_COMERCIAL||'\"/'||'SUC:\"'||NOMBRE ||'\"') ELSE 'RS:\"'||RAZON_SOCIAL||'\"/'||'NC:\"'||NOM_COMERCIAl||'\"' END AS RAZON_SOCIAL FROM VISTA_IE_UE WHERE ID_ESCUELA='" + renapo.getAUXESCUELA() + "' AND STATUS_GENERAL=1";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new ueIeactivasMapper());
        return list;
    }

    public List validaCctPlanMateria(escuelaBean escuela) throws Exception {
        String query = "SELECT ID_CCT_PLAN,  ID_MATERIA,  NOMBRE_MATERIA,  FECH_REG_MATERIA,  CVE_MATERIA FROM TBL_PLAN_MATERIA WHERE ID_CCT_PLAN='" + escuela.getAUXIDCCTPLAN() + "'";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new materiaMapper());
        return list;
    }

    public List programasEscuela(escuelaBean escuela) throws Exception {
        String query = "SELECT TBL_CCT_PLAN.ID_PLAN, TBL_CCT_PLAN.ID_ESCUELA,  TBL_CCT_PLAN.ID_CCT_PLAN, PLA.CVE_PLAN, PLA.NOM_CARRERA,  TBL_CCT_PLAN.ESTATUS AS STATUS, PLA.FECHA_AUT_DUAL, PLA.ENFASIS, PLA.CVE_PLAN_EST, PLA.TIPO_PERIODO, PLA.NUMERO_PERIODO, PLA.VERSION FROM TBL_CCT_PLAN JOIN (SELECT * FROM CAT_PLAN)PLA ON TBL_CCT_PLAN.ID_PLAN=PLA.ID_PLAN WHERE TBL_CCT_PLAN.ID_ESCUELA='" + escuela.getID_ESCUELA() + "'AND PLA.STATUS=1   ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new planCctMapper());
        return list;
    }

    public List programasEscuelaRes(escuelaBean escuela, renapoBean renapo) throws Exception {
        String query = "SELECT PLA.*, RESP.ESTATUS AS PROGVINCULADO, RESP.ID_RES_PROGEDU, RESP.PERFIL, RESP.ID_PERSONA  FROM (SELECT TBL_CCT_PLAN.ID_PLAN, TBL_CCT_PLAN.ID_ESCUELA,  TBL_CCT_PLAN.ID_CCT_PLAN, PLA.CVE_PLAN, PLA.NOM_CARRERA,  TBL_CCT_PLAN.ESTATUS AS STATUS, PLA.FECHA_AUT_DUAL, PLA.ENFASIS, PLA.CVE_PLAN_EST, PLA.TIPO_PERIODO, PLA.NUMERO_PERIODO, PLA.VERSION FROM TBL_CCT_PLAN JOIN (SELECT * FROM CAT_PLAN)PLA ON TBL_CCT_PLAN.ID_PLAN=PLA.ID_PLAN WHERE TBL_CCT_PLAN.ID_ESCUELA='" + renapo.getAUXESCUELA() + "'AND PLA.STATUS=1 )PLA LEFT OUTER JOIN (SELECT * FROM TBL_PLAN_RESPONSABLE WHERE ID_PERSONA='" + renapo.getID_PERSONA() + "' AND ID_ESCUELA='" + renapo.getAUXESCUELA() + "' AND PERFIL='" + renapo.getAUXPERFIL() + "')RESP ON PLA.ID_PLAN=RESP.ID_PLAN   ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new planCttResMapper());
        return list;
    }
     public List programasEscuelaDescarga(escuelaBean escuela, renapoBean renapo) throws Exception {
        String query = "SELECT  NOM_CARRERA, CVE_PLAN_EST,ENFASIS, VERSION, NOMBRE_MATERIA, NUMERO_PERIODO, COMPETENCIA, ACTIVIDAD FROM VISTA_REGISTRO_PROG WHERE ID_CCT_PLAN='"+escuela.getAUXIDCCTPLAN()+"' ORDER BY NOMBRE_MATERIA,NUMERO_PERIODO ASC ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new descargaProgramasMapper());
        return list;
    }

    public List programasEscuelaResProg(escuelaBean escuela, renapoBean renapo) throws Exception {

        String query = "SELECT PLANEST.*,  PRES.ESTATUS AS PROGVINCULADO, PRES.ID_RES_PROGEDU, PRES.PERFIL, PRES.ID_PERSONA   FROM (SELECT PLA.* FROM (SELECT TBL_CCT_PLAN.ID_PLAN, TBL_CCT_PLAN.ID_ESCUELA,  TBL_CCT_PLAN.ID_CCT_PLAN, PLA.CVE_PLAN, PLA.NOM_CARRERA,  TBL_CCT_PLAN.ESTATUS AS STATUS, PLA.FECHA_AUT_DUAL, PLA.ENFASIS, PLA.CVE_PLAN_EST, PLA.TIPO_PERIODO, PLA.NUMERO_PERIODO, PLA.VERSION FROM TBL_CCT_PLAN JOIN (SELECT * FROM CAT_PLAN)PLA ON TBL_CCT_PLAN.ID_PLAN=PLA.ID_PLAN WHERE TBL_CCT_PLAN.ID_ESCUELA='" + renapo.getAUXESCUELA() + "' AND PLA.STATUS>0 )PLA JOIN (SELECT * FROM TBL_PLAN_RESPONSABLE WHERE ID_PERSONA='" + renapo.getID_PERSONA() + "' AND PERFIL=25 AND ESTATUS=1 ) RESPON ON PLA.ID_PLAN=RESPON.ID_PLAN AND PLA.ID_ESCUELA=RESPON.ID_ESCUELA  )PLANEST LEFT OUTER JOIN(SELECT * FROM TBL_PLAN_RESPONSABLE WHERE PERFIL=27 AND ID_PERSONA='" + renapo.getAUXPERSONA() + "'  )PRES ON PLANEST.ID_PLAN=PRES.ID_PLAN AND PLANEST.ID_ESCUELA=PRES.ID_ESCUELA   ";

        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new planCttResMapper());
        return list;
    }

    public List programasEscuelaResMentorProg(escuelaBean escuela, renapoBean renapo) throws Exception {

        String query = "SELECT PLANEST.*,  PRES.ESTATUS AS PROGVINCULADO, PRES.ID_RES_PROGEDU, PRES.PERFIL, PRES.ID_PERSONA   FROM (SELECT PLA.* FROM (SELECT TBL_CCT_PLAN.ID_PLAN, TBL_CCT_PLAN.ID_ESCUELA,  TBL_CCT_PLAN.ID_CCT_PLAN, PLA.CVE_PLAN, PLA.NOM_CARRERA,  TBL_CCT_PLAN.ESTATUS AS STATUS, PLA.FECHA_AUT_DUAL, PLA.ENFASIS, PLA.CVE_PLAN_EST, PLA.TIPO_PERIODO, PLA.NUMERO_PERIODO, PLA.VERSION FROM TBL_CCT_PLAN JOIN (SELECT * FROM CAT_PLAN)PLA ON TBL_CCT_PLAN.ID_PLAN=PLA.ID_PLAN WHERE TBL_CCT_PLAN.ID_ESCUELA='" + renapo.getAUXESCUELA() + "' AND PLA.STATUS>0 )PLA JOIN (SELECT * FROM TBL_PLAN_RESPONSABLE WHERE ID_PERSONA='" + renapo.getID_PERSONA() + "' AND PERFIL=26 AND ESTATUS=1 ) RESPON ON PLA.ID_PLAN=RESPON.ID_PLAN AND PLA.ID_ESCUELA=RESPON.ID_ESCUELA  )PLANEST LEFT OUTER JOIN(SELECT * FROM TBL_PLAN_RESPONSABLE WHERE PERFIL=28 AND ID_PERSONA='" + renapo.getAUXPERSONA() + "'  )PRES ON PLANEST.ID_PLAN=PRES.ID_PLAN AND PLANEST.ID_ESCUELA=PRES.ID_ESCUELA   ";

        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new planCttResMapper());
        return list;
    }
    
    public List indicadoresTablero(usuarioBean usuario) throws Exception {

        String query = "SELECT (ACTIVOS+BAJAS+EGRESADOS) AS TOTAL_ALUMNOS, DATOS.* FROM (SELECT * FROM (SELECT COUNT(ID_ALUMNO) AS ACTIVOS FROM TBL_HIST_ALUM WHERE STATUS_GENERAL=1),(SELECT COUNT(ID_ALUMNO) AS BAJAS FROM TBL_HIST_ALUM WHERE (STATUS_GENERAL>=2 AND STATUS_GENERAL<=5) AND (FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"')),(SELECT COUNT(ID_ALUMNO) AS EGRESADOS FROM TBL_HIST_ALUM WHERE STATUS_GENERAL=10 AND (FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"')),(SELECT COUNT(ID_ALUMNO) AS NUEVO_INGRESO FROM TBL_HIST_ALUM WHERE  (FECHA_INICIO_PF>='"+usuario.getFECHA_INICIO()+"' AND FECHA_INICIO_PF<='"+usuario.getFECHA_TERMINO()+"')),(SELECT (TOTAL_HOMBRE_ACTIVO+TOTAL_HOMBRE_BAJAS+TOTAL_HOMBRE_EGRESADOS) AS HOMBRES, (TOTAL_MUJER_ACTIVO+TOTAL_MUJER_BAJAS+TOTAL_MUJER_EGRESADOS) AS MUJER FROM (SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_HOMBRE_ACTIVO FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM WHERE STATUS_GENERAL=1)HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='HOMBRE'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_MUJER_ACTIVO FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM WHERE STATUS_GENERAL=1)HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='MUJER'),    (SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_HOMBRE_BAJAS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM WHERE  (STATUS_GENERAL>=2 AND STATUS_GENERAL<=5) AND  (FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='HOMBRE'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_MUJER_BAJAS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM WHERE  (STATUS_GENERAL>=2 AND STATUS_GENERAL<=5) AND (FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='MUJER'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_HOMBRE_EGRESADOS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM WHERE STATUS_GENERAL=10 AND (FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='HOMBRE'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_MUJER_EGRESADOS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM WHERE STATUS_GENERAL=10 AND (FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='MUJER')))DATOS  ";

        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralMapper());
        return list;
    }
    
     public List indicadoresTableroSubDir(usuarioBean usuario, programaEsBean programa) throws Exception {

         String query="SELECT (ACTIVOS+BAJAS+EGRESADOS) AS TOTAL_ALUMNOS, DATOS.* FROM (SELECT * FROM (SELECT COUNT(ID_ALUMNO) AS ACTIVOS FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA WHERE ALUHIST.STATUS_GENERAL=1 AND CATC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"'),(SELECT COUNT(ID_ALUMNO) AS BAJAS FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA WHERE  (STATUS_GENERAL>=2 AND STATUS_GENERAL<=5) AND (FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') AND(CATC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"')),(SELECT COUNT(ID_ALUMNO) AS EGRESADOS FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA  WHERE STATUS_GENERAL=10 AND (FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"')AND(CATC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"')),(SELECT COUNT(ID_ALUMNO) AS NUEVO_INGRESO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA   WHERE  (FECHA_INICIO_PF>='"+usuario.getFECHA_INICIO()+"' AND FECHA_INICIO_PF<='"+usuario.getFECHA_TERMINO()+"')AND(CATC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"')),(SELECT (TOTAL_HOMBRE_ACTIVO+TOTAL_HOMBRE_BAJAS+TOTAL_HOMBRE_EGRESADOS) AS HOMBRES, (TOTAL_MUJER_ACTIVO+TOTAL_MUJER_BAJAS+TOTAL_MUJER_EGRESADOS) AS MUJER FROM (SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_HOMBRE_ACTIVO FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA WHERE STATUS_GENERAL=1 AND(CATC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='HOMBRE'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_MUJER_ACTIVO FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA          WHERE STATUS_GENERAL=1 AND(CATC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='MUJER'),    (SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_HOMBRE_BAJAS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA         WHERE  (STATUS_GENERAL>=2 AND STATUS_GENERAL<=5) AND  (FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') AND(CATC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='HOMBRE'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_MUJER_BAJAS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM  ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA                WHERE  (STATUS_GENERAL>=2 AND STATUS_GENERAL<=5) AND (FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') AND(CATC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='MUJER'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_HOMBRE_EGRESADOS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA                          WHERE STATUS_GENERAL=10 AND (FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') AND(CATC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='HOMBRE'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_MUJER_EGRESADOS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA           WHERE STATUS_GENERAL=10 AND (FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') AND(CATC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='MUJER')))DATOS";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralMapper());
        return list;
    }
      public List indicadoresTableroEsc(usuarioBean usuario, programaEsBean programa) throws Exception {

        String query="SELECT (ACTIVOS+BAJAS+EGRESADOS) AS TOTAL_ALUMNOS, DATOS.* FROM (SELECT * FROM (SELECT COUNT(ID_ALUMNO) AS ACTIVOS FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA WHERE ALUHIST.STATUS_GENERAL=1 AND CATC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"'),(SELECT COUNT(ID_ALUMNO) AS BAJAS FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA WHERE  (STATUS_GENERAL>=2 AND STATUS_GENERAL<=5) AND (FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') AND(CATC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"')),(SELECT COUNT(ID_ALUMNO) AS EGRESADOS FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA  WHERE STATUS_GENERAL=10 AND (FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"')AND(CATC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"')),(SELECT COUNT(ID_ALUMNO) AS NUEVO_INGRESO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA   WHERE  (FECHA_INICIO_PF>='"+usuario.getFECHA_INICIO()+"' AND FECHA_INICIO_PF<='"+usuario.getFECHA_TERMINO()+"')AND(CATC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"')),(SELECT (TOTAL_HOMBRE_ACTIVO+TOTAL_HOMBRE_BAJAS+TOTAL_HOMBRE_EGRESADOS) AS HOMBRES, (TOTAL_MUJER_ACTIVO+TOTAL_MUJER_BAJAS+TOTAL_MUJER_EGRESADOS) AS MUJER FROM (SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_HOMBRE_ACTIVO FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA WHERE STATUS_GENERAL=1 AND(CATC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='HOMBRE'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_MUJER_ACTIVO FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA          WHERE STATUS_GENERAL=1 AND(CATC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='MUJER'),    (SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_HOMBRE_BAJAS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA         WHERE  (STATUS_GENERAL>=2 AND STATUS_GENERAL<=5) AND  (FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') AND(CATC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='HOMBRE'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_MUJER_BAJAS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM  ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA                WHERE  (STATUS_GENERAL>=2 AND STATUS_GENERAL<=5) AND (FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') AND(CATC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='MUJER'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_HOMBRE_EGRESADOS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA                          WHERE STATUS_GENERAL=10 AND (FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') AND(CATC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='HOMBRE'),(SELECT COUNT(ALU.ID_ALUMNO) AS TOTAL_MUJER_EGRESADOS FROM (SELECT ID_ALUMNO FROM TBL_HIST_ALUM ALUHIST JOIN (SELECT * FROM CAT_CCT) CATC ON ALUHIST.ID_ESCUELA=CATC.ID_ESCUELA           WHERE STATUS_GENERAL=10 AND (FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') AND(CATC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"'))HIST JOIN (SELECT * FROM CAT_ALUMNO ) ALU ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE SEXO='MUJER')))DATOS";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralMapper());
        return list;
    }
     
    public List indicadoresTableroUE(usuarioBean usuario) throws Exception {

        String query = "SELECT UE AS ID_UE, ID_SUC, EN_PROCESO, ACTIVO_UE, BAJA_UE, EGRESADO_UE, RFC, RAZON_SOCIAL FROM(SELECT RESU.*, RESU.BAJA2+RESU.BAJA3+RESU.BAJA4+RESU.BAJA5 AS BAJA_UE, UES.RFC, CASE WHEN NOMBRE IS NOT NULL THEN (UES.RAZON_SOCIAL||'/'||'SUC:'||SUCUR.NOMBRE ) ELSE UES.RAZON_SOCIAL  END AS RAZON_SOCIAL  FROM(SELECT * FROM(  SELECT INSUE.ID_UE AS UE, HIST.STATUS_GENERAL, INSUE.ID_UE, INSUE.ID_SUC FROM (SELECT *  FROM TBL_HIST_ALUM ) HIST JOIN (SELECT * FROM TBL_INS_UE)INSUE  ON HIST.ID_IE_UE=INSUE.ID_IE_UE WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1) PIVOT(  COUNT(ID_UE)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE)))RESU JOIN (SELECT * FROM CAT_UNIDAD_ECONOMICA)UES ON RESU.UE=UES.ID_UE LEFT OUTER JOIN (SELECT * FROM TBL_SUCURSALES)SUCUR ON SUCUR.ID_SUC=RESU.ID_SUC) ORDER BY ACTIVO_UE, EGRESADO_UE DESC";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralUEMapper());
        return list;
    }
     public List indicadoresTableroSubDirUE(usuarioBean usuario, programaEsBean programa) throws Exception {
        String query="SELECT CT.ID_UE, CT.ID_SUC, CT.EN_PROCESO, CT.ACTIVO_UE, CT.BAJA_UE, CT.EGRESADO_UE, CT.RFC, CT.RAZON_SOCIAL FROM(SELECT UE AS ID_UE, ID_SUC, EN_PROCESO, ACTIVO_UE, BAJA_UE, EGRESADO_UE, RFC, RAZON_SOCIAL, ID_ESCUELA FROM(SELECT RESU.*, RESU.BAJA2+RESU.BAJA3+RESU.BAJA4+RESU.BAJA5 AS BAJA_UE, UES.RFC, CASE WHEN NOMBRE IS NOT NULL THEN (UES.RAZON_SOCIAL||'/'||'SUC:'||SUCUR.NOMBRE ) ELSE UES.RAZON_SOCIAL  END AS RAZON_SOCIAL  FROM(SELECT * FROM(  SELECT INSUE.ID_UE AS UE, HIST.STATUS_GENERAL, INSUE.ID_UE, INSUE.ID_SUC, HIST.ID_ESCUELA FROM (SELECT *  FROM TBL_HIST_ALUM ) HIST JOIN (SELECT * FROM TBL_INS_UE)INSUE  ON HIST.ID_IE_UE=INSUE.ID_IE_UE WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1) PIVOT(  COUNT(ID_UE)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE)))RESU JOIN (SELECT * FROM CAT_UNIDAD_ECONOMICA)UES ON RESU.UE=UES.ID_UE LEFT OUTER JOIN (SELECT * FROM TBL_SUCURSALES)SUCUR ON SUCUR.ID_SUC=RESU.ID_SUC) ORDER BY ACTIVO_UE, EGRESADO_UE DESC) CT JOIN (SELECT * FROM CAT_CCT)ESC ON CT.ID_ESCUELA=ESC.ID_ESCUELA WHERE ESC.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"'"; 
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralUEMapper());
        return list;
    }
     
      public List indicadoresTableroEscUE(usuarioBean usuario, programaEsBean programa) throws Exception {
        String query="SELECT CT.ID_UE, CT.ID_SUC, CT.EN_PROCESO, CT.ACTIVO_UE, CT.BAJA_UE, CT.EGRESADO_UE, CT.RFC, CT.RAZON_SOCIAL FROM(SELECT UE AS ID_UE, ID_SUC, EN_PROCESO, ACTIVO_UE, BAJA_UE, EGRESADO_UE, RFC, RAZON_SOCIAL, ID_ESCUELA FROM(SELECT RESU.*, RESU.BAJA2+RESU.BAJA3+RESU.BAJA4+RESU.BAJA5 AS BAJA_UE, UES.RFC, CASE WHEN NOMBRE IS NOT NULL THEN (UES.RAZON_SOCIAL||'/'||'SUC:'||SUCUR.NOMBRE ) ELSE UES.RAZON_SOCIAL  END AS RAZON_SOCIAL  FROM(SELECT * FROM(  SELECT INSUE.ID_UE AS UE, HIST.STATUS_GENERAL, INSUE.ID_UE, INSUE.ID_SUC, HIST.ID_ESCUELA FROM (SELECT *  FROM TBL_HIST_ALUM ) HIST JOIN (SELECT * FROM TBL_INS_UE)INSUE  ON HIST.ID_IE_UE=INSUE.ID_IE_UE WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1) PIVOT(  COUNT(ID_UE)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE)))RESU JOIN (SELECT * FROM CAT_UNIDAD_ECONOMICA)UES ON RESU.UE=UES.ID_UE LEFT OUTER JOIN (SELECT * FROM TBL_SUCURSALES)SUCUR ON SUCUR.ID_SUC=RESU.ID_SUC) ORDER BY ACTIVO_UE, EGRESADO_UE DESC) CT JOIN (SELECT * FROM CAT_CCT)ESC ON CT.ID_ESCUELA=ESC.ID_ESCUELA WHERE ESC.ID_ESCUELA='"+usuario.getID_ESCUELA()+"'"; 
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralUEMapper());
        return list;
    }
      
        public List listaUsuarios(usuarioBean usuario, programaEsBean programa) throws Exception {
        String query=" SELECT PER.NOMBRE_PERSONA||' '||APATERNO_PERSONA||' ' ||PER.AMATERNO_PERSONA AS NOMBRE_COMPLETO, UPPER( USR.NAMEPERFIL) AS NAMEPERFIL, USR.USUARIO_LOGIN, USR.PASSWORD, PER.TELCASA_PERSONA, PER.TELCEL_PERSONA, PER.CORREO_PERSONA, USR.PERFIL, USR.ID_PERSONA, USR.ID_ESCUELA, USR.ID_IE_UE FROM (SELECT * FROM TBL_USUARIOS WHERE ID_ESCUELA='"+usuario.getID_ESCUELA()+"')USR JOIN (SELECT * FROM CAT_PERSONA)PER ON USR.ID_PERSONA=PER.ID_PERSONA ORDER BY USR.PERFIL ASC"; 
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.usuarioConsultaMapper());
        return list;
    }
    
      public List indicadoresTableroPlan(usuarioBean usuario) throws Exception {

        String query = " SELECT INDICA.ID_PLAN_COM AS ID_PLAN, INDICA.NOM_CARRERA,   INDICA.EN_PROCESO, INDICA.ACTIVO_UE, INDICA.BAJA2+INDICA.BAJA3+INDICA.BAJA4+INDICA.BAJA5 AS BAJA_UE, INDICA.EGRESADO_UE FROM(SELECT PLA.ID_PLAN AS ID_PLAN_COM, HIST.STATUS_GENERAL, CATPLAN.NOM_CARRERA, PLA.ID_PLAN FROM (SELECT *  FROM TBL_HIST_ALUM ) HIST JOIN (SELECT * FROM TBL_CCT_PLAN)PLA  ON HIST.ID_CCT_PLAN=PLA.ID_CCT_PLAN JOIN(SELECT * FROM CAT_PLAN)CATPLAN ON PLA.ID_PLAN=CATPLAN.ID_PLAN  WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1)  PIVOT (  COUNT(ID_PLAN)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE))INDICA ORDER BY INDICA.ACTIVO_UE, INDICA.EGRESADO_UE DESC";
         Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralPEMapper());
        return list;
    }
       public List indicadoresTableroSubDirPlan(usuarioBean usuario , programaEsBean programa) throws Exception {
           String query="SELECT INDICA.ID_PLAN_COM AS ID_PLAN, INDICA.NOM_CARRERA,   INDICA.EN_PROCESO, INDICA.ACTIVO_UE, INDICA.BAJA2+INDICA.BAJA3+INDICA.BAJA4+INDICA.BAJA5 AS BAJA_UE, INDICA.EGRESADO_UE FROM(SELECT PLA.ID_PLAN AS ID_PLAN_COM, HIST.STATUS_GENERAL, CATPLAN.NOM_CARRERA, PLA.ID_PLAN FROM (SELECT *  FROM TBL_HIST_ALUM ALUMN JOIN (SELECT * FROM CAT_CCT)CATALUM ON ALUMN.ID_ESCUELA=CATALUM.ID_ESCUELA WHERE CATALUM.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"' ) HIST JOIN (SELECT * FROM TBL_CCT_PLAN)PLA  ON HIST.ID_CCT_PLAN=PLA.ID_CCT_PLAN JOIN(SELECT * FROM CAT_PLAN)CATPLAN ON PLA.ID_PLAN=CATPLAN.ID_PLAN  WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1)  PIVOT (  COUNT(ID_PLAN)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE))INDICA ORDER BY INDICA.ACTIVO_UE, INDICA.EGRESADO_UE DESC";
         Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralPEMapper());
        return list;
    }
       
        public List indicadoresTableroEscPlan(usuarioBean usuario , programaEsBean programa) throws Exception {
           String query="SELECT INDICA.ID_PLAN_COM AS ID_PLAN, INDICA.NOM_CARRERA,   INDICA.EN_PROCESO, INDICA.ACTIVO_UE, INDICA.BAJA2+INDICA.BAJA3+INDICA.BAJA4+INDICA.BAJA5 AS BAJA_UE, INDICA.EGRESADO_UE FROM(SELECT PLA.ID_PLAN AS ID_PLAN_COM, HIST.STATUS_GENERAL, CATPLAN.NOM_CARRERA, PLA.ID_PLAN FROM (SELECT *  FROM TBL_HIST_ALUM ALUMN JOIN (SELECT * FROM CAT_CCT)CATALUM ON ALUMN.ID_ESCUELA=CATALUM.ID_ESCUELA WHERE CATALUM.ID_ESCUELA='"+usuario.getID_ESCUELA()+"' ) HIST JOIN (SELECT * FROM TBL_CCT_PLAN)PLA  ON HIST.ID_CCT_PLAN=PLA.ID_CCT_PLAN JOIN(SELECT * FROM CAT_PLAN)CATPLAN ON PLA.ID_PLAN=CATPLAN.ID_PLAN  WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1)  PIVOT (  COUNT(ID_PLAN)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE))INDICA ORDER BY INDICA.ACTIVO_UE, INDICA.EGRESADO_UE DESC";
         Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralPEMapper());
        return list;
    }
     public List indicadoresTableroMun(usuarioBean usuario) throws Exception {

        String query = "SELECT INDICA.CVE_MUN_GRAL AS CVE_MUN, MUN.DESC_MPIO AS MUNICIPIO, INDICA.EN_PROCESO, INDICA.ACTIVO_UE, INDICA.BAJA2+INDICA.BAJA3+INDICA.BAJA4+INDICA.BAJA5 AS BAJA_UE, INDICA.EGRESADO_UE FROM(SELECT * FROM(SELECT ALU.CVE_MUN AS CVE_MUN_GRAL, HIST.STATUS_GENERAL, ALU.CVE_MUN FROM (SELECT *  FROM TBL_HIST_ALUM ) HIST JOIN (SELECT * FROM CAT_ALUMNO)ALU  ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1) PIVOT (  COUNT(CVE_MUN)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE))RESU)INDICA JOIN (SELECT * FROM CAT_MUN_NAC) MUN  ON INDICA.CVE_MUN_GRAL=MUN.IDN_MPIO WHERE INDICA.CVE_MUN_GRAL>=655 AND INDICA.CVE_MUN_GRAL<=779  ORDER BY MUN.DESC_MPIO, INDICA.ACTIVO_UE, INDICA.EGRESADO_UE";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralMunMapper());
        return list;
    }
     
     public List indicadoresTableroSubDirMun(usuarioBean usuario, programaEsBean programa) throws Exception {

         String query="SELECT INDICA.CVE_MUN_GRAL AS CVE_MUN, MUN.DESC_MPIO AS MUNICIPIO, INDICA.EN_PROCESO, INDICA.ACTIVO_UE, INDICA.BAJA2+INDICA.BAJA3+INDICA.BAJA4+INDICA.BAJA5 AS BAJA_UE, INDICA.EGRESADO_UE FROM(SELECT * FROM(SELECT ALU.CVE_MUN AS CVE_MUN_GRAL, HIST.STATUS_GENERAL, ALU.CVE_MUN FROM (SELECT *  FROM TBL_HIST_ALUM ALUMN JOIN (SELECT * FROM CAT_CCT)CATALUM ON ALUMN.ID_ESCUELA=CATALUM.ID_ESCUELA WHERE CATALUM.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"' ) HIST JOIN (SELECT * FROM CAT_ALUMNO)ALU  ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1) PIVOT (  COUNT(CVE_MUN)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE))RESU)INDICA JOIN (SELECT * FROM CAT_MUN_NAC) MUN  ON INDICA.CVE_MUN_GRAL=MUN.IDN_MPIO WHERE INDICA.CVE_MUN_GRAL>=655 AND INDICA.CVE_MUN_GRAL<=779  ORDER BY MUN.DESC_MPIO, INDICA.ACTIVO_UE, INDICA.EGRESADO_UE";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralMunMapper());
        return list;
    }
     
      public List indicadoresTableroEscMun(usuarioBean usuario, programaEsBean programa) throws Exception {

         String query="SELECT INDICA.CVE_MUN_GRAL AS CVE_MUN, MUN.DESC_MPIO AS MUNICIPIO, INDICA.EN_PROCESO, INDICA.ACTIVO_UE, INDICA.BAJA2+INDICA.BAJA3+INDICA.BAJA4+INDICA.BAJA5 AS BAJA_UE, INDICA.EGRESADO_UE FROM(SELECT * FROM(SELECT ALU.CVE_MUN AS CVE_MUN_GRAL, HIST.STATUS_GENERAL, ALU.CVE_MUN FROM (SELECT *  FROM TBL_HIST_ALUM ALUMN JOIN (SELECT * FROM CAT_CCT)CATALUM ON ALUMN.ID_ESCUELA=CATALUM.ID_ESCUELA WHERE CATALUM.ID_ESCUELA='"+usuario.getID_ESCUELA()+"' ) HIST JOIN (SELECT * FROM CAT_ALUMNO)ALU  ON HIST.ID_ALUMNO=ALU.ID_ALUMNO WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1) PIVOT (  COUNT(CVE_MUN)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE))RESU)INDICA JOIN (SELECT * FROM CAT_MUN_NAC) MUN  ON INDICA.CVE_MUN_GRAL=MUN.IDN_MPIO WHERE INDICA.CVE_MUN_GRAL>=655 AND INDICA.CVE_MUN_GRAL<=779  ORDER BY MUN.DESC_MPIO, INDICA.ACTIVO_UE, INDICA.EGRESADO_UE";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralMunMapper());
        return list;
    }
      public List indicadoresTableroSec(usuarioBean usuario) throws Exception {

        String query = " (SELECT RESU.NOM_SECTOR, RESU.EN_PROCESO, RESU.ACTIVO_UE, RESU.EGRESADO_UE, RESU.BAJA2+RESU.BAJA3+RESU.BAJA4+RESU.BAJA5 AS BAJA_UE FROM(SELECT UEC.SECTOR, PORUE.STATUS_GENERAL, SECT.NOM_SECTOR FROM( SELECT INSUE.ID_UE AS UE, HIST.STATUS_GENERAL FROM (SELECT *  FROM TBL_HIST_ALUM ) HIST JOIN (SELECT * FROM TBL_INS_UE)INSUE  ON HIST.ID_IE_UE=INSUE.ID_IE_UE WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1)PORUE JOIN (SELECT * FROM CAT_UNIDAD_ECONOMICA)UEC ON  UEC.ID_UE=PORUE.UE JOIN (SELECT * FROM CAT_SECTOR)SECT ON  SECT.ID_SECTOR=UEC.SECTOR) PIVOT( COUNT(SECTOR)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE))RESU) ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralSec());
        return list;
    }
      
       public List indicadoresTableroSubDirSec(usuarioBean usuario, programaEsBean programa) throws Exception {

        String query="(SELECT RESU.NOM_SECTOR, RESU.EN_PROCESO, RESU.ACTIVO_UE, RESU.EGRESADO_UE, RESU.BAJA2+RESU.BAJA3+RESU.BAJA4+RESU.BAJA5 AS BAJA_UE FROM(SELECT UEC.SECTOR, PORUE.STATUS_GENERAL, SECT.NOM_SECTOR FROM( SELECT INSUE.ID_UE AS UE, HIST.STATUS_GENERAL FROM (SELECT *  FROM TBL_HIST_ALUM ALUMN JOIN (SELECT * FROM CAT_CCT)CATALUM ON ALUMN.ID_ESCUELA=CATALUM.ID_ESCUELA WHERE CATALUM.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"'  ) HIST JOIN (SELECT * FROM TBL_INS_UE)INSUE  ON HIST.ID_IE_UE=INSUE.ID_IE_UE WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1)PORUE JOIN (SELECT * FROM CAT_UNIDAD_ECONOMICA)UEC ON  UEC.ID_UE=PORUE.UE JOIN (SELECT * FROM CAT_SECTOR)SECT ON  SECT.ID_SECTOR=UEC.SECTOR) PIVOT( COUNT(SECTOR)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE))RESU) ";   
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralSec());
        return list;
    }
        public List indicadoresTableroEscSec(usuarioBean usuario, programaEsBean programa) throws Exception {

        String query="(SELECT RESU.NOM_SECTOR, RESU.EN_PROCESO, RESU.ACTIVO_UE, RESU.EGRESADO_UE, RESU.BAJA2+RESU.BAJA3+RESU.BAJA4+RESU.BAJA5 AS BAJA_UE FROM(SELECT UEC.SECTOR, PORUE.STATUS_GENERAL, SECT.NOM_SECTOR FROM( SELECT INSUE.ID_UE AS UE, HIST.STATUS_GENERAL FROM (SELECT *  FROM TBL_HIST_ALUM ALUMN JOIN (SELECT * FROM CAT_CCT)CATALUM ON ALUMN.ID_ESCUELA=CATALUM.ID_ESCUELA WHERE CATALUM.ID_ESCUELA='"+usuario.getID_ESCUELA()+"'  ) HIST JOIN (SELECT * FROM TBL_INS_UE)INSUE  ON HIST.ID_IE_UE=INSUE.ID_IE_UE WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1)PORUE JOIN (SELECT * FROM CAT_UNIDAD_ECONOMICA)UEC ON  UEC.ID_UE=PORUE.UE JOIN (SELECT * FROM CAT_SECTOR)SECT ON  SECT.ID_SECTOR=UEC.SECTOR) PIVOT( COUNT(SECTOR)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE))RESU) ";   
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralSec());
        return list;
    }
       public List indicadoresTableroHist(usuarioBean usuario) throws Exception {

        String query = "SELECT ID_HISTORIA,  PERIODO,  MSUPERIOR,  SUPERIOR,  MSUPERIOR+SUPERIOR AS TOTAL_DUAL_HIST,  ESTATUS,  ACTUAL FROM TBL_HISTO_DUAL ORDER BY ID_HISTORIA ASC   ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        
        List list = null;
        list = queryForList(query, new mappers.tableroGralHistDualMapper());
        return list;
    }
    
     public List indicadoresTableroNiv(usuarioBean usuario) throws Exception {

        String query = "SELECT ACT.*, BJ.BAJAS AS BAJAS_NIV, EG.EGRESADOS AS EGRESADOS_NIV, (ACT.ACTIVOS_NIV +BJ.BAJAS+ EG.EGRESADOS) AS TOTAL_NIV FROM(SELECT NIV.ID_NIVEL, NIV.NOMBRE_NIVEL, NVL(AC.ACTIVOS, '0') AS ACTIVOS_NIV FROM (SELECT * FROM CAT_NIVEL)NIV LEFT OUTER JOIN (SELECT DISTINCT(ID_NIVEL), COUNT(ID_ALUMNO)AS ACTIVOS FROM (SELECT * FROM TBL_HIST_ALUM WHERE STATUS_GENERAL=1 ) ACT JOIN (SELECT * FROM CAT_CCT  ) CCT ON ACT.ID_ESCUELA=CCT.ID_ESCUELA GROUP BY ID_NIVEL)AC ON NIV.ID_NIVEL=AC.ID_NIVEL)ACT LEFT OUTER JOIN (SELECT NIV.ID_NIVEL, NIV.NOMBRE_NIVEL, NVL(AC.BAJAS, '0') AS BAJAS FROM (SELECT * FROM CAT_NIVEL)NIV LEFT OUTER JOIN (SELECT DISTINCT(ID_NIVEL), COUNT(ID_ALUMNO)AS BAJAS FROM (SELECT * FROM TBL_HIST_ALUM WHERE  (STATUS_GENERAL>=2 AND STATUS_GENERAL<=5) AND (FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"')  ) ACT JOIN (SELECT * FROM CAT_CCT  ) CCT ON ACT.ID_ESCUELA=CCT.ID_ESCUELA GROUP BY ID_NIVEL)AC ON NIV.ID_NIVEL=AC.ID_NIVEL)BJ ON ACT.ID_NIVEL = BJ.ID_NIVEL LEFT OUTER JOIN(SELECT NIV.ID_NIVEL, NIV.NOMBRE_NIVEL, NVL(AC.EGRESADOS, '0') AS EGRESADOS FROM (SELECT * FROM CAT_NIVEL)NIV LEFT OUTER JOIN (SELECT DISTINCT(ID_NIVEL), COUNT(ID_ALUMNO)AS EGRESADOS FROM (SELECT * FROM TBL_HIST_ALUM WHERE  STATUS_GENERAL=10 AND (FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') ) ACT JOIN (SELECT * FROM CAT_CCT  ) CCT ON ACT.ID_ESCUELA=CCT.ID_ESCUELA GROUP BY ID_NIVEL)AC ON NIV.ID_NIVEL=AC.ID_NIVEL) EG ON ACT.ID_NIVEL=EG.ID_NIVEL ORDER BY ACT.ID_NIVEL ASC  ";

        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralNivMapper());
        return list;
    }
     public List indicadoresTableroDirSubNiv(usuarioBean usuario, programaEsBean programa) throws Exception {

        String query = "SELECT ID_SUBSISTEMA AS ID_NIVEL, NOMBRE_SUBISTEMA AS NOMBRE_NIVEL, ACTIVO_UE AS ACTIVOS_NIV, (BAJA2+BAJA3+BAJA3+BAJA4) AS BAJAS_NIV, EGRESADO_UE AS EGRESADOS_NIV, (ACTIVO_UE+(BAJA2+BAJA3+BAJA3+BAJA4)+EGRESADO_UE) AS TOTAL_NIV   FROM (SELECT RESU.*, RESU.BAJA2+RESU.BAJA3+RESU.BAJA4+RESU.BAJA5 AS BAJA_UE, SUBSIS.NOMBRE_SUBISTEMA  FROM(SELECT * FROM(  SELECT  HIST.STATUS_GENERAL, INSUE.ID_UE,  HIST.ID_ESCUELA, HIST.ID_SUBSISTEMA FROM (SELECT ALUMN.*, CATALUM.ID_SUBSISTEMA  FROM TBL_HIST_ALUM ALUMN JOIN (SELECT * FROM CAT_CCT)CATALUM ON ALUMN.ID_ESCUELA=CATALUM.ID_ESCUELA WHERE CATALUM.ID_NIVEL='"+programa.getID_NIVEL_CONSULTA()+"' ) HIST JOIN (SELECT * FROM TBL_INS_UE)INSUE  ON HIST.ID_IE_UE=INSUE.ID_IE_UE WHERE (HIST.FECHA_BAJA>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_BAJA<='"+usuario.getFECHA_TERMINO()+"') OR (HIST.FECHA_EGRESO>='"+usuario.getFECHA_INICIO()+"' AND HIST.FECHA_EGRESO<='"+usuario.getFECHA_TERMINO()+"') OR HIST.STATUS_GENERAL=1) PIVOT(  COUNT(ID_UE)  FOR STATUS_GENERAL IN (0 AS EN_PROCESO, 1 AS ACTIVO_UE, 2 AS BAJA2, 3 AS BAJA3, 4 AS BAJA4 ,5 AS BAJA5 ,10 AS EGRESADO_UE)))RESU JOIN (SELECT * FROM CAT_SUBSISTEMA)SUBSIS ON  RESU.ID_SUBSISTEMA=SUBSIS.ID_SUBSISTEMA) ";

        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = queryForList(query, new mappers.tableroGralNivMapper());
        return list;
    }

    public boolean guardaPlanCct(escuelaBean escuela) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PLAN", "STRING", escuela.getAUXPLAN());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", escuela.getAUXESCUELA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", escuela.getESTATUS());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_CCT_PLAN", arregloCampos);
    }

    public boolean guardaResPe(renapoBean renapo) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("CURP_PERSONA", "STRING", renapo.getCURP());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOMBRE_PERSONA", "STRING", renapo.getNOMBRE_RENAPO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APATERNO_PERSONA", "STRING", renapo.getAPATERNO_RENAPO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("AMATERNO_PERSONA", "STRING", renapo.getAMATERNO_RENAPO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FECNAC_PERSONA", "STRING", renapo.getFEC_NAC_RENAPO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NAC_PERSONA", "STRING", renapo.getNACIONALIDAD_RENAPO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTIDAD_NACIMIENTO", "STRING", renapo.getENTIDAD_NACIMINETO_RENAPO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("GENERO", "STRING", renapo.getGENERO_RENAPO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TELCASA_PERSONA", "STRING", renapo.getTELEFONO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TELCEL_PERSONA", "STRING", renapo.getCELULAR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CORREO_PERSONA", "STRING", renapo.getCORREO_PERSONA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("RFC", "STRING", renapo.getRFC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", renapo.getAUXESCUELA());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("CAT_PERSONA", arregloCampos);
    }

    public boolean guardaResPeIe(renapoBean renapo) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PERSONA", "STRING", renapo.getID_PERSONA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", renapo.getAUXESCUELA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PERFIL", "STRING", renapo.getPERFIL());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_RESPE_INST", arregloCampos);
    }

    public boolean guardaResUE(renapoBean renapo, UnidadesEconomicasBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PERSONA", "STRING", renapo.getID_PERSONA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", renapo.getAUXESCUELA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PERFIL", "STRING", renapo.getPERFIL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_IE_UE", "STRING", ue.getID_IE_UE());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_RESPE_INST", arregloCampos);
    }

    public boolean guardaResPeInst(renapoBean renapo, escuelaBean escuela) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PERSONA", "STRING", renapo.getID_PERSONA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", renapo.getAUXESCUELA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_PLAN", "STRING", escuela.getAUXPLAN());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PERFIL", "STRING", renapo.getAUXPERFIL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", "1");
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_PLAN_RESPONSABLE", arregloCampos);
    }

    public boolean guardaResPeInstMentor(renapoBean renapo, escuelaBean escuela) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PERSONA", "STRING", renapo.getAUXPERSONA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", renapo.getAUXESCUELA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_PLAN", "STRING", escuela.getAUXPLAN());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PERFIL", "STRING", renapo.getAUXPERFIL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", "1");
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_PLAN_RESPONSABLE", arregloCampos);
    }

    public boolean guardaUserResPeIe(renapoBean renapo, usuarioBean usuario) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("NAMEUSUARIO", "STRING", usuario.getNAMEUSUARIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FILTRO", "STRING", usuario.getFILTRO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("USUARIO", "STRING", usuario.getUSUARIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PERFIL", "STRING", renapo.getPERFIL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NAMEPERFIL", "STRING", usuario.getNAMEPERFIL_VARIABLE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_PERSONA", "STRING", renapo.getID_PERSONA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("USUARIO_LOGIN", "STRING", renapo.getUSUARIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PASSWORD", "STRING", renapo.getPASSWORD());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", usuario.getESTATUS_VARIABLE());
        arregloCampos.add(temporal);

        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", renapo.getAUXESCUELA());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_USUARIOS", arregloCampos);
    }

    public boolean guardaUserResUE(renapoBean renapo, usuarioBean usuario, UnidadesEconomicasBean ue) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("NAMEUSUARIO", "STRING", usuario.getNAMEUSUARIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FILTRO", "STRING", usuario.getFILTRO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("USUARIO", "STRING", usuario.getUSUARIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PERFIL", "STRING", renapo.getPERFIL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NAMEPERFIL", "STRING", usuario.getNAMEPERFIL_VARIABLE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_PERSONA", "STRING", renapo.getID_PERSONA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("USUARIO_LOGIN", "STRING", renapo.getUSUARIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PASSWORD", "STRING", renapo.getPASSWORD());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", usuario.getESTATUS_VARIABLE());
        arregloCampos.add(temporal);

        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", renapo.getAUXESCUELA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_IE_UE", "STRING", ue.getID_IE_UE());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_USUARIOS", arregloCampos);
    }

    public boolean guardaUserResAcad(renapoBean renapo, usuarioBean usuario, escuelaBean escuela) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("NAMEUSUARIO", "STRING", escuela.getAUXNOMESC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FILTRO", "STRING", escuela.getAUXPERFILUSR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("USUARIO", "STRING", escuela.getAUXCCT());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PERFIL", "STRING", renapo.getPERFIL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NAMEPERFIL", "STRING", usuario.getNAMEPERFIL_VARIABLE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_PERSONA", "STRING", renapo.getID_PERSONA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("USUARIO_LOGIN", "STRING", renapo.getUSUARIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PASSWORD", "STRING", renapo.getPASSWORD());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", usuario.getESTATUS_VARIABLE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESCUELA", "STRING", renapo.getAUXESCUELA());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_USUARIOS", arregloCampos);
    }

    public boolean guardaCompetenciaCct(competenciaBean competencia, materiaBean materia) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_MATERIA", "STRING", materia.getAUXMATERIA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COMPETENCIA", "STRING", competencia.getCOMPETENCIA());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_MATERIA_COMPETENCIA", arregloCampos);
    }

    public boolean guardaActividad(competenciaBean competencia, actividadesBean actividad) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_COMPETENCIA", "STRING", competencia.getAUXCOMPETENCIA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ACTIVIDAD", "STRING", actividad.getACTIVIDAD());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_COMPETENCIA_ACTIVIDAD", arregloCampos);
    }

    public boolean guardaPlanCctMateria(escuelaBean escuela, materiaBean materia) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_CCT_PLAN", "STRING", escuela.getAUXIDCCTPLAN());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MATERIA", "STRING", materia.getCVE_MATERIA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOMBRE_MATERIA", "STRING", materia.getNOMBRE_MATERIA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NUMERO_PERIODO", "STRING", materia.getNUMERO_PERIODO());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_PLAN_MATERIA", arregloCampos);
    }

    public boolean guardaPrograma(escuelaBean escuela, programaEsBean programa) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("CVE_PLAN", "STRING", programa.getID_PLAN());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOM_CARRERA", "STRING", programa.getNOM_CARRERA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS", "STRING", "1");
        arregloCampos.add(temporal);

        temporal = new ObjPrepareStatement("CVE_PLAN_EST", "STRING", programa.getCVE_PLAN_EST());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENFASIS", "STRING", programa.getENFASIS());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("VERSION", "STRING", programa.getVERSION());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TIPO_PERIODO", "STRING", programa.getTIPO_PERIODO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NUMERO_PERIODO", "STRING", programa.getNUMERO_PERIODO());
        arregloCampos.add(temporal);

        temporal = new ObjPrepareStatement("ID_NIVEL", "STRING", programa.getID_NIVEL_CONSULTA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_SUBSISTEMA", "STRING", programa.getID_SUBSISTEMA_CONSULTA());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("CAT_PLAN", arregloCampos);
    }

    public boolean actualizaEstatusPlanCct(escuelaBean escuela) throws Exception {
        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        //Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS...................................");
        //En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        // Integer a=Integer.parseInt(correspondencia1.getCANTI1());
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", escuela.getAUXSTATUS());
        arregloCampos.add(temporal);

        String Condicion;
        Condicion = " WHERE ID_ESCUELA='" + escuela.getAUXESCUELA() + "' AND ID_PLAN='" + escuela.getAUXPLAN() + "' ";

        //Se terminan de adicionar a nuesto ArrayLis lbjos oetos
        //Ejecutar la funcion del OracleDAOFactory queryInsert
        return oraDaoFac.queryUpdate("TBL_CCT_PLAN", arregloCampos, Condicion);
    }

    public boolean actualizaResPeInst(renapoBean renapo, escuelaBean escuela) throws Exception {
        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        //Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS...................................");
        //En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        // Integer a=Integer.parseInt(correspondencia1.getCANTI1());
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", renapo.getAUXESTATUS());
        arregloCampos.add(temporal);

        String Condicion;
        Condicion = " WHERE ID_RES_PROGEDU='" + renapo.getAUXPROGVINCULADO() + "' ";

        //Se terminan de adicionar a nuesto ArrayLis lbjos oetos
        //Ejecutar la funcion del OracleDAOFactory queryInsert
        return oraDaoFac.queryUpdate("TBL_PLAN_RESPONSABLE", arregloCampos, Condicion);
    }

    public boolean actualizaProgramaGuarda(escuelaBean escuela, programaEsBean programa) throws Exception {
        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        //Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS...................................");
        //En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        // Integer a=Integer.parseInt(correspondencia1.getCANTI1());
        temporal = new ObjPrepareStatement("CVE_PLAN", "STRING", programa.getID_PLAN());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOM_CARRERA", "STRING", programa.getNOM_CARRERA());
        arregloCampos.add(temporal);

        temporal = new ObjPrepareStatement("CVE_PLAN_EST", "STRING", programa.getCVE_PLAN_EST());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENFASIS", "STRING", programa.getENFASIS());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("VERSION", "STRING", programa.getVERSION());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TIPO_PERIODO", "STRING", programa.getTIPO_PERIODO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NUMERO_PERIODO", "STRING", programa.getNUMERO_PERIODO());
        arregloCampos.add(temporal);

        String Condicion;
        Condicion = " WHERE ID_PLAN='" + escuela.getAUXPLAN() + "' ";

        //Se terminan de adicionar a nuesto ArrayLis lbjos oetos
        //Ejecutar la funcion del OracleDAOFactory queryInsert
        return oraDaoFac.queryUpdate("CAT_PLAN", arregloCampos, Condicion);
    }

    public boolean actualizaEstatusPlan(escuelaBean escuela) throws Exception {
        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        //Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS...................................");
        //En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        // Integer a=Integer.parseInt(correspondencia1.getCANTI1());
        temporal = new ObjPrepareStatement("STATUS", "STRING", escuela.getAUXSTATUS());
        arregloCampos.add(temporal);

        String Condicion;
        Condicion = " WHERE  ID_PLAN='" + escuela.getAUXPLAN() + "' ";

        //Se terminan de adicionar a nuesto ArrayLis lbjos oetos
        //Ejecutar la funcion del OracleDAOFactory queryInsert
        return oraDaoFac.queryUpdate("CAT_PLAN", arregloCampos, Condicion);
    }

    public boolean actualizaCompetenciaCct(competenciaBean competencia) throws Exception {
        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        //Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS...................................");
        //En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        // Integer a=Integer.parseInt(correspondencia1.getCANTI1());
        temporal = new ObjPrepareStatement("COMPETENCIA", "STRING", competencia.getCOMPETENCIA());
        arregloCampos.add(temporal);

        String Condicion;
        Condicion = " WHERE ID_COMPETENCIA='" + competencia.getAUXCOMPETENCIA() + "' ";

        //Se terminan de adicionar a nuesto ArrayLis lbjos oetos
        //Ejecutar la funcion del OracleDAOFactory queryInsert
        return oraDaoFac.queryUpdate("TBL_MATERIA_COMPETENCIA", arregloCampos, Condicion);
    }

    public boolean actualizaActividad(actividadesBean actividad) throws Exception {
        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        //Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS...................................");
        //En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        // Integer a=Integer.parseInt(correspondencia1.getCANTI1());
        temporal = new ObjPrepareStatement("actividad", "STRING", actividad.getACTIVIDAD());
        arregloCampos.add(temporal);

        String Condicion;
        Condicion = " WHERE ID_ACTIVIDAD='" + actividad.getAUXACTIVIDAD() + "' ";

        //Se terminan de adicionar a nuesto ArrayLis lbjos oetos
        //Ejecutar la funcion del OracleDAOFactory queryInsert
        return oraDaoFac.queryUpdate("TBL_COMPETENCIA_ACTIVIDAD", arregloCampos, Condicion);
    }

    public boolean actualizaResPe(renapoBean renapo) throws Exception {
        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        //Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS...................................");
        //En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        // Integer a=Integer.parseInt(correspondencia1.getCANTI1());
        temporal = new ObjPrepareStatement("TELCASA_PERSONA", "STRING", renapo.getTELEFONO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TELCEL_PERSONA", "STRING", renapo.getCELULAR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("RFC", "STRING", renapo.getRFC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CORREO_PERSONA", "STRING", renapo.getCORREO_PERSONA());
        arregloCampos.add(temporal);

        String Condicion;
        Condicion = " WHERE ID_PERSONA='" + renapo.getID_PERSONA() + "' ";

        //Se terminan de adicionar a nuesto ArrayLis lbjos oetos
        //Ejecutar la funcion del OracleDAOFactory queryInsert
        return oraDaoFac.queryUpdate("CAT_PERSONA", arregloCampos, Condicion);
    }

    public boolean actualizaUsuario(renapoBean renapo) throws Exception {
        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        //Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS...................................");
        //En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        // Integer a=Integer.parseInt(correspondencia1.getCANTI1());
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", renapo.getAUXESTATUS());
        arregloCampos.add(temporal);

        String Condicion;
        Condicion = " WHERE ID_USUARIO='" + renapo.getAUXIDUSUARIO() + "' ";

        //Se terminan de adicionar a nuesto ArrayLis lbjos oetos
        //Ejecutar la funcion del OracleDAOFactory queryInsert
        return oraDaoFac.queryUpdate("TBL_USUARIOS", arregloCampos, Condicion);
    }

    public boolean eliminarPlanCct(escuelaBean escuela) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_CCT_PLAN", "STRING", escuela.getAUXIDCCTPLAN());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryDelete("TBL_CCT_PLAN", arregloCampos);
    }

    public boolean eliminarPrograma(escuelaBean escuela, programaEsBean programa) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PLAN", "STRING", escuela.getAUXPLAN());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryDelete("CAT_PLAN", arregloCampos);
    }

    public boolean eliminarCompetenciaCct(competenciaBean competencia) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_COMPETENCIA", "STRING", competencia.getAUXCOMPETENCIA());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryDelete("TBL_MATERIA_COMPETENCIA", arregloCampos);
    }

    public boolean eliminarMateria(materiaBean materia) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_MATERIA", "STRING", materia.getAUXMATERIA());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryDelete("TBL_PLAN_MATERIA", arregloCampos);
    }

    public boolean eliminaActividad(actividadesBean actividad) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_ACTIVIDAD", "STRING", actividad.getAUXACTIVIDAD());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryDelete("TBL_COMPETENCIA_ACTIVIDAD", arregloCampos);
    }

}
