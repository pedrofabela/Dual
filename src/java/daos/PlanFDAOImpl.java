package daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.AlumnoBean;
import beans.PlanFormacionBean;
import beans.Res_ConBean;
import beans.UnidadesEconomicasBean;
import beans.escuelaBean;
import beans.programaEsBean;
import beans.renapoBean;
import beans.usuarioBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import mappers.PEPFMapper;
import mappers.PEUEMapper;
import mappers.PreSeleccionadosMapper;
import mappers.SECTORMapper;
import mappers.SECTORSUBMapper;
import mappers.SucursalesMapper;
import mappers.UEIEMapper;
import mappers.UEMapper;
import mappers.ValidadosMapper;
import mappers.consultaAluRegMapper;
import mappers.consultaPlanAluHist;
import mappers.consultaPlanFormDatosMapper;
import mappers.consultaPlanFormMapper;
import mappers.consultaUEMapper;
import mappers.descargaProgramasMapper;
import mappers.descargaProgramasMapper2;
import mappers.descargaProgramasMapper3;
import mappers.escalaMapper;
import mappers.horaMapper;
import mappers.listaPlanUEMapper;
import mappers.lugarMapper;
import mappers.periodoMapper;
import mappers.plaMateriaAlumnoMapper;
import mappers.planAsigMatPlanMapper;
import mappers.planCttResMapper;
import mappers.planFormAluMUEMapper;
import mappers.planFormAluMapper;
import mappers.reporteComActMapper;
import mappers.responsablePerfilMapper;
import mappers.rubricaMapper;
import mappers.sectordosMapper;
import mappers.ueIeactivasMapper;
import utilidades.Constantes;
import utilidades.ObjPrepareStatement;

/**
 *
 * @author dinamarca
 */
public class PlanFDAOImpl {

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

    public List ConsultaPE(PlanFormacionBean pf) throws Exception {
        String query = "SELECT cp.id_cct_plan, ('PE: '|| p.nom_carrera || ' ' || 'version: ' || p.version || ' ' ||'enfasis:'|| p.enfasis) as nom_carrera, p.periodo_inicio_dual "
                + "FROM tbl_plan_responsable pr INNER JOIN tbl_cct_plan cp on cp.id_plan=pr.id_plan INNER JOIN cat_plan p on p.id_plan=pr.id_plan "
                + "WHERE cp.id_escuela='"+pf.getID_ESCUELA()+"' and pr.id_persona='"+pf.getID_PERSONA()+"' and pr.perfil='"+pf.getID_PERFIL()+"'";
        Constantes.enviaMensajeConsola("PE vinculados al men. academico ----->" + query);
        List lista = null;
        lista = oraDaoFac.queryForList(query, new PEPFMapper());
        return lista;
    }
    
    public List programasEscuelaDescarga(escuelaBean escuela, renapoBean renapo) throws Exception {
        String query = "SELECT  NOM_CARRERA, CVE_PLAN_EST,ENFASIS, VERSION, NOMBRE_MATERIA, NUMERO_PERIODO, COMPETENCIA, ACTIVIDAD, ID_CCT_PLAN, ID_MATERIA, ID_COMPETENCIA, ID_ACTIVIDAD, 'DES_ACTIVIDAD' AS DES_ACTIVIDAD, 'VALIDAR' AS VALIDAR, 'NO_PASA' AS NO_PASA FROM VISTA_REGISTRO_PROG WHERE ID_CCT_PLAN='"+escuela.getAUXIDCCTPLAN2()+"' AND ACTIVIDAD IS NOT NULL AND NUMERO_PERIODO>='"+escuela.getPERIODO_INICIO()+"' ORDER BY TO_NUMBER(NUMERO_PERIODO), ID_COMPETENCIA, ID_ACTIVIDAD ASC ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new descargaProgramasMapper2());
        return list;
    }
    
    public List programasEscuelaConsulta(AlumnoBean alumno, escuelaBean escuela,  usuarioBean usuariocons, programaEsBean programa) throws Exception {
        
       String query = "SELECT ACT.NOM_CARRERA, ACT.CVE_PLAN_EST, ACT.ENFASIS, ACT.VERSION, ACT.NOMBRE_MATERIA, ACT.NUMERO_PERIODO, ACT.COMPETENCIA, ACT.ACTIVIDAD, ACT.ID_CCT_PLAN, ACT.ID_MATERIA, ACT.ID_COMPETENCIA, ACT.ID_ACTIVIDAD, CASE WHEN (LENGTH(ACTPLAN.DES_ACTIVIDAD)>0) THEN ACTPLAN.DES_ACTIVIDAD   ELSE ACT.ACTIVIDAD END AS DES_ACTIVIDAD,  CASE WHEN LENGTH(ACTPLAN.ID_ACTIVIDAD)>0 THEN 'true' ELSE '' END AS VALIDAR,  ACT.NO_PASA, ACTPLAN.ID_ACTIVIDAD AS INCLUIDA, ACTPLAN.ID_LUGAR,  ACTPLAN.ID_HORA, ACTPLAN.ID_ESCALA,  ACTPLAN.PLAN_ROTACION,    ACTPLAN.FECHA_REG,   ACTPLAN.ID_ACT_EVALUA,  ACTPLAN.EVIDENCIAS FROM (SELECT  NOM_CARRERA, CVE_PLAN_EST,ENFASIS, VERSION, NOMBRE_MATERIA, NUMERO_PERIODO, COMPETENCIA, ACTIVIDAD, ID_CCT_PLAN, ID_MATERIA, ID_COMPETENCIA, ID_ACTIVIDAD, 'DES_ACTIVIDAD' AS DES_ACTIVIDAD, 'VALIDAR' AS VALIDAR, '' AS NO_PASA FROM VISTA_REGISTRO_PROG WHERE ID_CCT_PLAN='"+escuela.getAUXIDCCTPLAN2()+"' AND ACTIVIDAD IS NOT NULL AND NUMERO_PERIODO>='"+escuela.getPERIODO_INICIO()+"' ORDER BY TO_NUMBER(NUMERO_PERIODO), ID_COMPETENCIA, ID_ACTIVIDAD ASC)ACT LEFT OUTER JOIN (SELECT ID_PLAN_FORMA,ID_ACTIVIDAD, ID_LUGAR,  ID_HORA, ID_ESCALA,  PLAN_ROTACION,    FECHA_REG,   ID_ACT_EVALUA,  EVIDENCIAS, DES_ACTIVIDAD FROM TBL_PLANFORM_ACT WHERE ID_PLAN_FORMA='"+programa.getID_PLAN_FORMA()+"')ACTPLAN ON ACT.ID_ACTIVIDAD=ACTPLAN.ID_ACTIVIDAD";
       
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new descargaProgramasMapper3());
        return list;
    }
    
     public List planFormAlu(AlumnoBean alumno, escuelaBean escuela,  usuarioBean usuariocons, programaEsBean programa) throws Exception {
        
       String query = " SELECT ID_HIST_ALUM, ID_ALUMNO, ID_IE_UE, STATUS_GENERAL, FECHA_INICIO_PF, FECHA_BAJA, FECHA_EGRESO, ID_CICLO, TO_CHAR(FECHA_INICIOPF, 'DD/MM/YYYY' ) AS FECHA_INICIOPF, TO_CHAR(FECHA_TERMINOPF,'DD/MM/YYYY') AS FECHA_TERMINOPF, ESTATUS_PF, NOMBREPLAN_FORM, DESCRIPCION, DURACION, HORAS_SEMANA, TOTAL_REPORTES, REPORTES_HECHOS, REPORTES_FALTANTES,POR_REG_ALUMNO FROM (\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"SELECT TBL_HIST_ALUM.ID_HIST_ALUM,\n" +
"  TBL_HIST_ALUM.ID_ALUMNO,\n" +
"  TBL_HIST_ALUM.ID_IE_UE,\n" +
"  TBL_HIST_ALUM.STATUS_GENERAL,\n" +
"  TBL_HIST_ALUM.FECHA_REGISTRO,\n" +
"  TBL_HIST_ALUM.ID_ESCUELA,\n" +
"  TBL_HIST_ALUM.STATUS_PROCESO,\n" +
"  TBL_HIST_ALUM.ID_CCT_PLAN,\n" +
"  TBL_HIST_ALUM.FECHA_INICIO_PF,\n" +
"  TBL_HIST_ALUM.FECHA_BAJA,\n" +
"  TBL_HIST_ALUM.FECHA_EGRESO,\n" +
"  TBL_HIST_ALUM.ID_CICLO,\n" +
"  TBL_HIST_ALUM.FECHA_INC_PADRON,\n" +
"  TBL_HIST_ALUM.RES_EVA,\n" +
"  TBL_HIST_ALUM.AUX_RES_ACAD,\n" +
"  TBL_HISTALU_PF.ID_HISTALU_PF,\n" +
"  TBL_HISTALU_PF.ID_PLAN_FORM,\n" +
"  TBL_HISTALU_PF.FECHA_INICIOPF,\n" +
"  TBL_HISTALU_PF.FECHA_TERMINOPF,\n" +
"  TBL_HISTALU_PF.ID_MENTOR_UE,\n" +
"  TBL_HISTALU_PF.ID_MENTOR_ACAD,\n" +
"  TBL_HISTALU_PF.ID_RES_UE,\n" +
"  TBL_HISTALU_PF.ID_RES_ACAD,\n" +
"  TBL_HISTALU_PF.ESTATUS_PF,\n" +
"  TBL_HISTALU_PF.ID_RES_PROGEDU,\n" +
"  TBL_PLAN_FORM.NOMBREPLAN_FORM,\n" +
"  TBL_PLAN_FORM.DESCRIPCION,\n" +
"  TBL_PLAN_FORM.DURACION,\n" +
"  TBL_PLAN_FORM.FECHA_REG,\n" +
"  TBL_PLAN_FORM.ID_CCT_PLAN AS ID_CCT_PLAN1,\n" +
"  TBL_PLAN_FORM.ESTATUS,\n" +
"  TBL_PLAN_FORM.ID_PER_VALIDA,\n" +
"  TBL_PLAN_FORM.FECHA_VALIDA,\n" +
"  TBL_PLAN_FORM.NO_ESTUDIANTES,\n" +
"  TBL_PLAN_FORM.NO_MENTORES_UE,\n" +
"  TBL_PLAN_FORM.NO_MENTORES_ACAD,\n" +
"  TBL_PLAN_FORM.ID_IE_UE AS ID_IE_UE1,\n" +
"  TBL_PLAN_FORM.HORAS_SEMANA, \n" +
"  AVANCE.TOTAL_REPORTES,\n" +
"  AVANCE.REPORTES_HECHOS,\n" +
"  AVANCE.REPORTES_FALTANTES,\n" +
"  AVANCE.POR_REG_ALUMNO\n" +
"FROM TBL_HIST_ALUM\n" +
"INNER JOIN TBL_HISTALU_PF\n" +
"ON TBL_HIST_ALUM.ID_HIST_ALUM = TBL_HISTALU_PF.ID_HIST_ALU\n" +
"INNER JOIN TBL_PLAN_FORM\n" +
"ON TBL_HISTALU_PF.ID_PLAN_FORM = TBL_PLAN_FORM.ID_PLAN_FORMA\n" +
"INNER JOIN (SELECT TOTAL.ID_HIST_ALU, TOTAL.TOTAL_REPORTES, NVL(HECHOS.TOTAL_REPOR_HECHOS,'0') AS REPORTES_HECHOS, (TO_NUMBER(TOTAL.TOTAL_REPORTES)-TO_NUMBER(NVL(HECHOS.TOTAL_REPOR_HECHOS,'0'))) AS REPORTES_FALTANTES, TRUNC( (TO_NUMBER(NVL(HECHOS.TOTAL_REPOR_HECHOS,'0')) * 100 )/TO_NUMBER(TOTAL.TOTAL_REPORTES), 1) AS POR_REG_ALUMNO FROM (SELECT DISTINCT(ID_HIST_ALU), COUNT(ID_HIST_ALU) AS TOTAL_REPORTES  FROM(SELECT DISTINCT(ID_COMPETENCIA), COUNT(ID_COMPETENCIA) AS NO_COMPETENCIAS, ID_HIST_ALU FROM TBL_PLANFORM_ACT_ALU GROUP BY ID_COMPETENCIA, ID_HIST_ALU) GROUP BY ID_HIST_ALU )TOTAL LEFT OUTER JOIN (SELECT DISTINCT(ID_HIST_ALU), COUNT(ID_HIST_ALU) AS TOTAL_REPOR_HECHOS FROM TBL_REGALUMCOMP GROUP BY ID_HIST_ALU)HECHOS ON TOTAL.ID_HIST_ALU=HECHOS.ID_HIST_ALU) AVANCE\n" +
"ON TBL_HIST_ALUM.ID_HIST_ALUM=AVANCE.ID_HIST_ALU\n" +
"WHERE TBL_HIST_ALUM.ID_ALUMNO  = '"+usuariocons.getID_ESTUDIANTE()+"' AND STATUS_GENERAL='1' ORDER BY STATUS_GENERAL, ESTATUS_PF, FECHA_INICIOPF DESC)";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new planFormAluMapper());
        return list;
    }
     
     
     public List evaluaActEst(AlumnoBean alumno, escuelaBean escuela,  usuarioBean usuariocons, programaEsBean programa) throws Exception {
        
       String query = "SELECT PROG.ID_CCT_PLAN, PROG.CVE_PLAN, PROG.NOM_CARRERA, PROG.VERSION, PROG.ENFASIS, AVC.*,   MEN.ID_PLAN_FORM,      MEN.ID_MENTOR_UE,   MEN.ID_MENTOR_ACAD,  MEN.ID_RES_UE, ESTUD.NOMBRE_EST FROM (SELECT ESTU.ID_HIST_ALUM, PLANAUT.ID_CCT_PLAN, PLANAUT.CVE_PLAN, PLANAUT.NOM_CARRERA, PLANAUT.VERSION, PLANAUT.ENFASIS  FROM (\n" +
"SELECT ID_HIST_ALUM,\n" +
"  ID_ALUMNO,\n" +
"  ID_IE_UE,\n" +
"  STATUS_GENERAL,\n" +
"  FECHA_REGISTRO,\n" +
"  ID_ESCUELA,\n" +
"  STATUS_PROCESO,\n" +
"  ID_CCT_PLAN,\n" +
"  FECHA_INICIO_PF,\n" +
"  FECHA_BAJA,\n" +
"  FECHA_EGRESO\n" +
"FROM TBL_HIST_ALUM WHERE ID_IE_UE='"+usuariocons.getID_IE_UE()+"' AND STATUS_GENERAL='1' AND STATUS_PROCESO>=6  )ESTU INNER JOIN (SELECT PLANEST.*,  PRES.ESTATUS AS PROGVINCULADO, PRES.ID_RES_PROGEDU, PRES.PERFIL, PRES.ID_PERSONA   FROM (SELECT PLA.* FROM (SELECT TBL_CCT_PLAN.ID_PLAN, TBL_CCT_PLAN.ID_ESCUELA,  TBL_CCT_PLAN.ID_CCT_PLAN, PLA.CVE_PLAN, PLA.NOM_CARRERA,  TBL_CCT_PLAN.ESTATUS AS STATUS, PLA.FECHA_AUT_DUAL, PLA.ENFASIS, PLA.CVE_PLAN_EST, PLA.TIPO_PERIODO, PLA.NUMERO_PERIODO, PLA.VERSION FROM TBL_CCT_PLAN JOIN (SELECT * FROM CAT_PLAN)PLA ON TBL_CCT_PLAN.ID_PLAN=PLA.ID_PLAN WHERE TBL_CCT_PLAN.ID_ESCUELA='"+usuariocons.getID_ESCUELA()+"' AND PLA.STATUS>0 )PLA JOIN (SELECT * FROM TBL_PLAN_RESPONSABLE WHERE ID_PERSONA='"+usuariocons.getID_PERSONA()+"' AND PERFIL=28 AND ESTATUS=1 ) RESPON ON PLA.ID_PLAN=RESPON.ID_PLAN AND PLA.ID_ESCUELA=RESPON.ID_ESCUELA  )PLANEST LEFT OUTER JOIN(SELECT * FROM TBL_PLAN_RESPONSABLE WHERE PERFIL=28 AND ID_PERSONA='"+usuariocons.getID_PERSONA()+"'  )PRES ON PLANEST.ID_PLAN=PRES.ID_PLAN AND PLANEST.ID_ESCUELA=PRES.ID_ESCUELA\n" +
") PLANAUT ON ESTU.ID_CCT_PLAN=PLANAUT.ID_CCT_PLAN ) PROG \n" +
"INNER JOIN (\n" +
"SELECT ID_HIST_ALUM, ID_ALUMNO, ID_IE_UE, STATUS_GENERAL, FECHA_INICIO_PF, FECHA_BAJA, FECHA_EGRESO, ID_CICLO, TO_CHAR(FECHA_INICIOPF, 'DD/MM/YYYY' ) AS FECHA_INICIOPF, TO_CHAR(FECHA_TERMINOPF,'DD/MM/YYYY') AS FECHA_TERMINOPF, ESTATUS_PF, NOMBREPLAN_FORM, DESCRIPCION, DURACION, HORAS_SEMANA, TOTAL_REPORTES, REPORTES_HECHOS, REPORTES_FALTANTES,POR_REG_ALUMNO, EVAL_MUE, EVAL_MA FROM (\n" +
"SELECT TBL_HIST_ALUM.ID_HIST_ALUM,\n" +
"  TBL_HIST_ALUM.ID_ALUMNO,\n" +
"  TBL_HIST_ALUM.ID_IE_UE,\n" +
"  TBL_HIST_ALUM.STATUS_GENERAL,\n" +
"  TBL_HIST_ALUM.FECHA_REGISTRO,\n" +
"  TBL_HIST_ALUM.ID_ESCUELA,\n" +
"  TBL_HIST_ALUM.STATUS_PROCESO,\n" +
"  TBL_HIST_ALUM.ID_CCT_PLAN,\n" +
"  TBL_HIST_ALUM.FECHA_INICIO_PF,\n" +
"  TBL_HIST_ALUM.FECHA_BAJA,\n" +
"  TBL_HIST_ALUM.FECHA_EGRESO,\n" +
"  TBL_HIST_ALUM.ID_CICLO,\n" +
"  TBL_HIST_ALUM.FECHA_INC_PADRON,\n" +
"  TBL_HIST_ALUM.RES_EVA,\n" +
"  TBL_HIST_ALUM.AUX_RES_ACAD,\n" +
"  TBL_HISTALU_PF.ID_HISTALU_PF,\n" +
"  TBL_HISTALU_PF.ID_PLAN_FORM,\n" +
"  TBL_HISTALU_PF.FECHA_INICIOPF,\n" +
"  TBL_HISTALU_PF.FECHA_TERMINOPF,\n" +
"  TBL_HISTALU_PF.ID_MENTOR_UE,\n" +
"  TBL_HISTALU_PF.ID_MENTOR_ACAD,\n" +
"  TBL_HISTALU_PF.ID_RES_UE,\n" +
"  TBL_HISTALU_PF.ID_RES_ACAD,\n" +
"  TBL_HISTALU_PF.ESTATUS_PF,\n" +
"  TBL_HISTALU_PF.ID_RES_PROGEDU,\n" +
"  TBL_PLAN_FORM.NOMBREPLAN_FORM,\n" +
"  TBL_PLAN_FORM.DESCRIPCION,\n" +
"  TBL_PLAN_FORM.DURACION,\n" +
"  TBL_PLAN_FORM.FECHA_REG,\n" +
"  TBL_PLAN_FORM.ID_CCT_PLAN AS ID_CCT_PLAN1,\n" +
"  TBL_PLAN_FORM.ESTATUS,\n" +
"  TBL_PLAN_FORM.ID_PER_VALIDA,\n" +
"  TBL_PLAN_FORM.FECHA_VALIDA,\n" +
"  TBL_PLAN_FORM.NO_ESTUDIANTES,\n" +
"  TBL_PLAN_FORM.NO_MENTORES_UE,\n" +
"  TBL_PLAN_FORM.NO_MENTORES_ACAD,\n" +
"  TBL_PLAN_FORM.ID_IE_UE AS ID_IE_UE1,\n" +
"  TBL_PLAN_FORM.HORAS_SEMANA, \n" +
"  AVANCE.TOTAL_REPORTES,\n" +
"  AVANCE.REPORTES_HECHOS,\n" +
"  AVANCE.REPORTES_FALTANTES,\n" +
"  AVANCE.POR_REG_ALUMNO,\n" +
"  AVANCE.EVAL_MUE,\n" +
"  AVANCE.EVAL_MA\n" +
"\n" +
"FROM TBL_HIST_ALUM\n" +
"INNER JOIN TBL_HISTALU_PF\n" +
"ON TBL_HIST_ALUM.ID_HIST_ALUM = TBL_HISTALU_PF.ID_HIST_ALU\n" +
"INNER JOIN TBL_PLAN_FORM\n" +
"ON TBL_HISTALU_PF.ID_PLAN_FORM = TBL_PLAN_FORM.ID_PLAN_FORMA\n" +
"INNER JOIN (SELECT TOTAL.ID_HIST_ALU, TOTAL.TOTAL_REPORTES, NVL(HECHOS.TOTAL_REPOR_HECHOS,'0') AS REPORTES_HECHOS, NVL(EVALMUE.TOTAL_EVALMUE,'0') AS EVAL_MUE, NVL(EVALMA.TOTAL_EVALMA,'0') AS EVAL_MA, (TO_NUMBER(TOTAL.TOTAL_REPORTES)-TO_NUMBER(NVL(HECHOS.TOTAL_REPOR_HECHOS,'0'))) AS REPORTES_FALTANTES, TRUNC( (TO_NUMBER(NVL(HECHOS.TOTAL_REPOR_HECHOS,'0')) * 100 )/TO_NUMBER(TOTAL.TOTAL_REPORTES), 1) AS POR_REG_ALUMNO FROM (SELECT DISTINCT(ID_HIST_ALU), COUNT(ID_HIST_ALU) AS TOTAL_REPORTES  FROM(SELECT DISTINCT(ID_COMPETENCIA), COUNT(ID_COMPETENCIA) AS NO_COMPETENCIAS, ID_HIST_ALU FROM TBL_PLANFORM_ACT_ALU GROUP BY ID_COMPETENCIA, ID_HIST_ALU) GROUP BY ID_HIST_ALU )TOTAL LEFT OUTER JOIN (SELECT DISTINCT(ID_HIST_ALU), COUNT(ID_HIST_ALU) AS TOTAL_REPOR_HECHOS FROM TBL_REGALUMCOMP GROUP BY ID_HIST_ALU)HECHOS ON TOTAL.ID_HIST_ALU=HECHOS.ID_HIST_ALU LEFT OUTER JOIN (SELECT DISTINCT(ID_HIST_ALU), COUNT(EVAL_MUE) AS TOTAL_EVALMUE FROM TBL_REGALUMCOMP GROUP BY ID_HIST_ALU)EVALMUE ON TOTAL.ID_HIST_ALU=EVALMUE.ID_HIST_ALU  LEFT OUTER JOIN (SELECT DISTINCT(ID_HIST_ALU), COUNT(EVAL_MA) AS TOTAL_EVALMA FROM TBL_REGALUMCOMP GROUP BY ID_HIST_ALU)EVALMA ON TOTAL.ID_HIST_ALU=EVALMA.ID_HIST_ALU\n" +
")AVANCE\n" +
"ON TBL_HIST_ALUM.ID_HIST_ALUM=AVANCE.ID_HIST_ALU ORDER BY STATUS_GENERAL, ESTATUS_PF, FECHA_INICIOPF DESC) \n" +
") AVC  ON PROG.ID_HIST_ALUM=AVC.ID_HIST_ALUM INNER JOIN (SELECT ID_HIST_ALU, ID_PLAN_FORM, FECHA_INICIOPF,FECHA_TERMINOPF, ID_MENTOR_UE, ID_MENTOR_ACAD, ID_RES_UE   FROM TBL_HISTALU_PF)MEN ON PROG.ID_HIST_ALUM= MEN.ID_HIST_ALU INNER JOIN (SELECT ID_ALUMNO, NOMBRE||' '||APELLIDOP||' '||APELLIDOM AS NOMBRE_EST FROM CAT_ALUMNO)ESTUD ON ESTUD.ID_ALUMNO=AVC.ID_ALUMNO WHERE MEN.ID_MENTOR_UE='"+usuariocons.getID_PERSONA()+"'    ORDER BY PROG.NOM_CARRERA ASC ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new planFormAluMUEMapper());
        return list;
    }  
      public List evaluaActEst2(AlumnoBean alumno, escuelaBean escuela,  usuarioBean usuariocons, programaEsBean programa) throws Exception {
        
       String query = "  SELECT PROG.ID_CCT_PLAN, PROG.CVE_PLAN, PROG.NOM_CARRERA, PROG.VERSION, PROG.ENFASIS, AVC.*,   MEN.ID_PLAN_FORM,      MEN.ID_MENTOR_UE,   MEN.ID_MENTOR_ACAD,  MEN.ID_RES_UE, ESTUD.NOMBRE_EST FROM (SELECT ESTU.ID_HIST_ALUM, PLANAUT.ID_CCT_PLAN, PLANAUT.CVE_PLAN, PLANAUT.NOM_CARRERA, PLANAUT.VERSION, PLANAUT.ENFASIS  FROM (\n" +
"SELECT ID_HIST_ALUM,\n" +
"  ID_ALUMNO,\n" +
"  ID_IE_UE,\n" +
"  STATUS_GENERAL,\n" +
"  FECHA_REGISTRO,\n" +
"  ID_ESCUELA,\n" +
"  STATUS_PROCESO,\n" +
"  ID_CCT_PLAN,\n" +
"  FECHA_INICIO_PF,\n" +
"  FECHA_BAJA,\n" +
"  FECHA_EGRESO\n" +
"FROM TBL_HIST_ALUM WHERE  STATUS_GENERAL='1' AND STATUS_PROCESO>=6  )ESTU INNER JOIN (SELECT PLANEST.*,  PRES.ESTATUS AS PROGVINCULADO, PRES.ID_RES_PROGEDU, PRES.PERFIL, PRES.ID_PERSONA   FROM (SELECT PLA.* FROM (SELECT TBL_CCT_PLAN.ID_PLAN, TBL_CCT_PLAN.ID_ESCUELA,  TBL_CCT_PLAN.ID_CCT_PLAN, PLA.CVE_PLAN, PLA.NOM_CARRERA,  TBL_CCT_PLAN.ESTATUS AS STATUS, PLA.FECHA_AUT_DUAL, PLA.ENFASIS, PLA.CVE_PLAN_EST, PLA.TIPO_PERIODO, PLA.NUMERO_PERIODO, PLA.VERSION FROM TBL_CCT_PLAN JOIN (SELECT * FROM CAT_PLAN)PLA ON TBL_CCT_PLAN.ID_PLAN=PLA.ID_PLAN WHERE TBL_CCT_PLAN.ID_ESCUELA='"+usuariocons.getID_ESCUELA()+"' AND PLA.STATUS>0 )PLA JOIN (SELECT * FROM TBL_PLAN_RESPONSABLE WHERE ID_PERSONA='"+usuariocons.getID_PERSONA()+"' AND PERFIL=27 AND ESTATUS=1 ) RESPON ON PLA.ID_PLAN=RESPON.ID_PLAN AND PLA.ID_ESCUELA=RESPON.ID_ESCUELA  )PLANEST LEFT OUTER JOIN(SELECT * FROM TBL_PLAN_RESPONSABLE WHERE PERFIL=27 AND ID_PERSONA='"+usuariocons.getID_PERSONA()+"'  )PRES ON PLANEST.ID_PLAN=PRES.ID_PLAN AND PLANEST.ID_ESCUELA=PRES.ID_ESCUELA\n" +
") PLANAUT ON ESTU.ID_CCT_PLAN=PLANAUT.ID_CCT_PLAN ) PROG \n" +
"INNER JOIN (\n" +
"SELECT ID_HIST_ALUM, ID_ALUMNO, ID_IE_UE, STATUS_GENERAL, FECHA_INICIO_PF, FECHA_BAJA, FECHA_EGRESO, ID_CICLO, TO_CHAR(FECHA_INICIOPF, 'DD/MM/YYYY' ) AS FECHA_INICIOPF, TO_CHAR(FECHA_TERMINOPF,'DD/MM/YYYY') AS FECHA_TERMINOPF, ESTATUS_PF, NOMBREPLAN_FORM, DESCRIPCION, DURACION, HORAS_SEMANA, TOTAL_REPORTES, REPORTES_HECHOS, REPORTES_FALTANTES,POR_REG_ALUMNO, EVAL_MUE, EVAL_MA FROM (\n" +
"SELECT TBL_HIST_ALUM.ID_HIST_ALUM,\n" +
"  TBL_HIST_ALUM.ID_ALUMNO,\n" +
"  TBL_HIST_ALUM.ID_IE_UE,\n" +
"  TBL_HIST_ALUM.STATUS_GENERAL,\n" +
"  TBL_HIST_ALUM.FECHA_REGISTRO,\n" +
"  TBL_HIST_ALUM.ID_ESCUELA,\n" +
"  TBL_HIST_ALUM.STATUS_PROCESO,\n" +
"  TBL_HIST_ALUM.ID_CCT_PLAN,\n" +
"  TBL_HIST_ALUM.FECHA_INICIO_PF,\n" +
"  TBL_HIST_ALUM.FECHA_BAJA,\n" +
"  TBL_HIST_ALUM.FECHA_EGRESO,\n" +
"  TBL_HIST_ALUM.ID_CICLO,\n" +
"  TBL_HIST_ALUM.FECHA_INC_PADRON,\n" +
"  TBL_HIST_ALUM.RES_EVA,\n" +
"  TBL_HIST_ALUM.AUX_RES_ACAD,\n" +
"  TBL_HISTALU_PF.ID_HISTALU_PF,\n" +
"  TBL_HISTALU_PF.ID_PLAN_FORM,\n" +
"  TBL_HISTALU_PF.FECHA_INICIOPF,\n" +
"  TBL_HISTALU_PF.FECHA_TERMINOPF,\n" +
"  TBL_HISTALU_PF.ID_MENTOR_UE,\n" +
"  TBL_HISTALU_PF.ID_MENTOR_ACAD,\n" +
"  TBL_HISTALU_PF.ID_RES_UE,\n" +
"  TBL_HISTALU_PF.ID_RES_ACAD,\n" +
"  TBL_HISTALU_PF.ESTATUS_PF,\n" +
"  TBL_HISTALU_PF.ID_RES_PROGEDU,\n" +
"  TBL_PLAN_FORM.NOMBREPLAN_FORM,\n" +
"  TBL_PLAN_FORM.DESCRIPCION,\n" +
"  TBL_PLAN_FORM.DURACION,\n" +
"  TBL_PLAN_FORM.FECHA_REG,\n" +
"  TBL_PLAN_FORM.ID_CCT_PLAN AS ID_CCT_PLAN1,\n" +
"  TBL_PLAN_FORM.ESTATUS,\n" +
"  TBL_PLAN_FORM.ID_PER_VALIDA,\n" +
"  TBL_PLAN_FORM.FECHA_VALIDA,\n" +
"  TBL_PLAN_FORM.NO_ESTUDIANTES,\n" +
"  TBL_PLAN_FORM.NO_MENTORES_UE,\n" +
"  TBL_PLAN_FORM.NO_MENTORES_ACAD,\n" +
"  TBL_PLAN_FORM.ID_IE_UE AS ID_IE_UE1,\n" +
"  TBL_PLAN_FORM.HORAS_SEMANA, \n" +
"  AVANCE.TOTAL_REPORTES,\n" +
"  AVANCE.REPORTES_HECHOS,\n" +
"  AVANCE.REPORTES_FALTANTES,\n" +
"  AVANCE.POR_REG_ALUMNO,\n" +
"  AVANCE.EVAL_MUE,\n" +
"  AVANCE.EVAL_MA\n" +
"\n" +
"FROM TBL_HIST_ALUM\n" +
"INNER JOIN TBL_HISTALU_PF\n" +
"ON TBL_HIST_ALUM.ID_HIST_ALUM = TBL_HISTALU_PF.ID_HIST_ALU\n" +
"INNER JOIN TBL_PLAN_FORM\n" +
"ON TBL_HISTALU_PF.ID_PLAN_FORM = TBL_PLAN_FORM.ID_PLAN_FORMA\n" +
"INNER JOIN (SELECT TOTAL.ID_HIST_ALU, TOTAL.TOTAL_REPORTES, NVL(HECHOS.TOTAL_REPOR_HECHOS,'0') AS REPORTES_HECHOS, NVL(EVALMUE.TOTAL_EVALMUE,'0') AS EVAL_MUE, NVL(EVALMA.TOTAL_EVALMA,'0') AS EVAL_MA, (TO_NUMBER(TOTAL.TOTAL_REPORTES)-TO_NUMBER(NVL(HECHOS.TOTAL_REPOR_HECHOS,'0'))) AS REPORTES_FALTANTES, TRUNC( (TO_NUMBER(NVL(HECHOS.TOTAL_REPOR_HECHOS,'0')) * 100 )/TO_NUMBER(TOTAL.TOTAL_REPORTES), 1) AS POR_REG_ALUMNO FROM (SELECT DISTINCT(ID_HIST_ALU), COUNT(ID_HIST_ALU) AS TOTAL_REPORTES  FROM(SELECT DISTINCT(ID_COMPETENCIA), COUNT(ID_COMPETENCIA) AS NO_COMPETENCIAS, ID_HIST_ALU FROM TBL_PLANFORM_ACT_ALU GROUP BY ID_COMPETENCIA, ID_HIST_ALU) GROUP BY ID_HIST_ALU )TOTAL LEFT OUTER JOIN (SELECT DISTINCT(ID_HIST_ALU), COUNT(ID_HIST_ALU) AS TOTAL_REPOR_HECHOS FROM TBL_REGALUMCOMP GROUP BY ID_HIST_ALU)HECHOS ON TOTAL.ID_HIST_ALU=HECHOS.ID_HIST_ALU LEFT OUTER JOIN (SELECT DISTINCT(ID_HIST_ALU), COUNT(EVAL_MUE) AS TOTAL_EVALMUE FROM TBL_REGALUMCOMP GROUP BY ID_HIST_ALU)EVALMUE ON TOTAL.ID_HIST_ALU=EVALMUE.ID_HIST_ALU  LEFT OUTER JOIN (SELECT DISTINCT(ID_HIST_ALU), COUNT(EVAL_MA) AS TOTAL_EVALMA FROM TBL_REGALUMCOMP GROUP BY ID_HIST_ALU)EVALMA ON TOTAL.ID_HIST_ALU=EVALMA.ID_HIST_ALU\n" +
")AVANCE\n" +
"ON TBL_HIST_ALUM.ID_HIST_ALUM=AVANCE.ID_HIST_ALU ORDER BY STATUS_GENERAL, ESTATUS_PF, FECHA_INICIOPF DESC) \n" +
") AVC  ON PROG.ID_HIST_ALUM=AVC.ID_HIST_ALUM INNER JOIN (SELECT ID_HIST_ALU, ID_PLAN_FORM, FECHA_INICIOPF,FECHA_TERMINOPF, ID_MENTOR_UE, ID_MENTOR_ACAD, ID_RES_UE   FROM TBL_HISTALU_PF)MEN ON PROG.ID_HIST_ALUM= MEN.ID_HIST_ALU INNER JOIN (SELECT ID_ALUMNO, NOMBRE||' '||APELLIDOP||' '||APELLIDOM AS NOMBRE_EST FROM CAT_ALUMNO)ESTUD ON ESTUD.ID_ALUMNO=AVC.ID_ALUMNO WHERE MEN.ID_MENTOR_ACAD='"+usuariocons.getID_PERSONA()+"'    ORDER BY PROG.NOM_CARRERA ASC    ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new planFormAluMUEMapper());
        return list;
    }  
     
     
     
     public List planFormAluMateria(AlumnoBean alumno, escuelaBean escuela,  usuarioBean usuariocons, programaEsBean programa) throws Exception {
        
       String query =" SELECT DISTINCT(ID_MATERIA), NOMBRE_MATERIA FROM(\n" +
"  \n" +
"  SELECT TBL_PLANFORM_ACT_ALU.ID_PLAN_FORMA,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_MATERIA,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_COMPETENCIA,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_HIST_ALU,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_ACT_ALU,\n" +
"  TBL_PLAN_MATERIA.NOMBRE_MATERIA,\n" +
"  TBL_PLAN_MATERIA.CVE_MATERIA,\n" +
"  TBL_PLAN_MATERIA.ID_CCT_PLAN,\n" +
"  TBL_PLAN_MATERIA.NUMERO_PERIODO,\n" +
"  TBL_MATERIA_COMPETENCIA.COMPETENCIA\n" +
"FROM TBL_PLANFORM_ACT_ALU\n" +
"INNER JOIN TBL_PLAN_MATERIA\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_MATERIA = TBL_PLAN_MATERIA.ID_MATERIA\n" +
"INNER JOIN TBL_MATERIA_COMPETENCIA\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_COMPETENCIA = TBL_MATERIA_COMPETENCIA.ID_COMPETENCIA\n" +
"WHERE TBL_PLANFORM_ACT_ALU.ID_HIST_ALU = '"+alumno.getAUXIDHISTALUM()+"'\n" +
"ORDER BY TBL_PLAN_MATERIA.NOMBRE_MATERIA,\n" +
"  TBL_PLAN_MATERIA.NUMERO_PERIODO) ORDER BY ID_MATERIA, NOMBRE_MATERIA ASC";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new plaMateriaAlumnoMapper());
        return list;
    }
       public List planFormAluMateriaCom(AlumnoBean alumno, escuelaBean escuela,  usuarioBean usuariocons, programaEsBean programa) throws Exception {
        
       String query ="SELECT COMP.*, NVL(REGISTRA.ID_REGALUMCOMP, '0') AS ID_REGALUMCOMP, REGISTRA.MARCO_TEORICO, REGISTRA.DES_ACT, REGISTRA.RUTA_EVIDENCIAS, REGISTRA.FECHA_REG, NVL(REGISTRA. EDITA,'0')AS EDITA, REGISTRA.EVAL_MUE, REGISTRA.EVAL_MA FROM(\n" +
"\n" +
"\n" +
"SELECT DISTINCT(ID_COMPETENCIA), COMPETENCIA, ID_HIST_ALU FROM(\n" +
"  \n" +
"  SELECT TBL_PLANFORM_ACT_ALU.ID_PLAN_FORMA,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_MATERIA,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_COMPETENCIA,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_HIST_ALU,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_ACT_ALU,\n" +
"  TBL_PLAN_MATERIA.NOMBRE_MATERIA,\n" +
"  TBL_PLAN_MATERIA.CVE_MATERIA,\n" +
"  TBL_PLAN_MATERIA.ID_CCT_PLAN,\n" +
"  TBL_PLAN_MATERIA.NUMERO_PERIODO,\n" +
"  TBL_MATERIA_COMPETENCIA.COMPETENCIA\n" +
"FROM TBL_PLANFORM_ACT_ALU\n" +
"INNER JOIN TBL_PLAN_MATERIA\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_MATERIA = TBL_PLAN_MATERIA.ID_MATERIA\n" +
"INNER JOIN TBL_MATERIA_COMPETENCIA\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_COMPETENCIA = TBL_MATERIA_COMPETENCIA.ID_COMPETENCIA\n" +
"\n" +
"WHERE TBL_PLANFORM_ACT_ALU.ID_HIST_ALU = '"+alumno.getAUXIDHISTALUM()+"' AND TBL_PLANFORM_ACT_ALU.ID_MATERIA='"+programa.getAUX_IDMATERIA()+"'\n" +
"ORDER BY TBL_PLAN_MATERIA.NOMBRE_MATERIA,\n" +
"  TBL_PLAN_MATERIA.NUMERO_PERIODO) ORDER BY ID_COMPETENCIA, COMPETENCIA, ID_HIST_ALU ASC)COMP LEFT OUTER JOIN(SELECT ID_REGALUMCOMP, ID_COMPETENCIA, ID_HIST_ALU, MARCO_TEORICO, DES_ACT, RUTA_EVIDENCIAS, FECHA_REG, EDITA, EVAL_MUE, EVAL_MA FROM TBL_REGALUMCOMP)REGISTRA ON COMP.ID_HIST_ALU=REGISTRA.ID_HIST_ALU AND COMP.ID_COMPETENCIA=REGISTRA.ID_COMPETENCIA \n" +
" ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new planAsigMatPlanMapper());
        return list;
    }
    
        public List planFormAluMateriaComAct(AlumnoBean alumno, escuelaBean escuela,  usuarioBean usuariocons, programaEsBean programa) throws Exception {
        
       String query ="SELECT TBL_PLANFORM_ACT_ALU.ID_PLAN_FORMA,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_MATERIA,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_COMPETENCIA,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_HIST_ALU,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_ACT_ALU,\n" +
"  TBL_PLAN_MATERIA.NOMBRE_MATERIA,\n" +
"  TBL_PLAN_MATERIA.CVE_MATERIA,\n" +
"  TBL_PLAN_MATERIA.ID_CCT_PLAN,\n" +
"  TBL_PLAN_MATERIA.NUMERO_PERIODO,\n" +
"  TBL_MATERIA_COMPETENCIA.COMPETENCIA,\n" +
"  TBL_COMPETENCIA_ACTIVIDAD.ACTIVIDAD,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_ESCALA,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_LUGAR,\n" +
"  TBL_PLANFORM_ACT_ALU.ID_HORA,\n" +
"  TBL_PLANFORM_ACT_ALU.PLAN_ROTACION,\n" +
"  TBL_PLANFORM_ACT_ALU.DES_ACTIVIDAD,\n" +
"  TBL_PLANFORM_ACT_ALU.FECHA_REG,\n" +
"  CAT_LUGARES.LUGAR,\n" +
"  CAT_HORAS.HORA,\n" +
"  CAT_ESCALA.ESCALA,\n" +
"  TBL_COMPETENCIA_ACTIVIDAD.ID_ACTIVIDAD,\n" +
"  TBL_PLANFORM_ACT.EVIDENCIAS\n" +
"FROM TBL_PLANFORM_ACT_ALU\n" +
"INNER JOIN TBL_PLAN_MATERIA\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_MATERIA = TBL_PLAN_MATERIA.ID_MATERIA\n" +
"INNER JOIN TBL_MATERIA_COMPETENCIA\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_COMPETENCIA = TBL_MATERIA_COMPETENCIA.ID_COMPETENCIA\n" +
"INNER JOIN TBL_COMPETENCIA_ACTIVIDAD\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_ACTIVIDAD = TBL_COMPETENCIA_ACTIVIDAD.ID_ACTIVIDAD\n" +
"INNER JOIN CAT_LUGARES\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_LUGAR = CAT_LUGARES.ID_LUGAR\n" +
"INNER JOIN CAT_HORAS\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_HORA = CAT_HORAS.ID_HORA\n" +
"INNER JOIN CAT_ESCALA\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_ESCALA = CAT_ESCALA.ID_ESCALA\n" +
"INNER JOIN TBL_PLANFORM_ACT\n" +
"ON TBL_PLANFORM_ACT_ALU.ID_ACTIVIDAD    = TBL_PLANFORM_ACT.ID_ACTIVIDAD\n"+
               
"WHERE TBL_PLANFORM_ACT_ALU.ID_MATERIA   = '"+programa.getAUX_IDMATERIA()+"'\n" +
"AND TBL_PLANFORM_ACT_ALU.ID_COMPETENCIA = '"+programa.getAUX_IDCOMPETENCIA()+"'\n" +
"AND TBL_PLANFORM_ACT_ALU.ID_HIST_ALU    = '"+alumno.getAUXIDHISTALUM()+"'\n" +
"ORDER BY TBL_PLAN_MATERIA.NOMBRE_MATERIA,\n" +
"  TBL_PLAN_MATERIA.NUMERO_PERIODO";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new reporteComActMapper());
        return list;
    }
   public List rubrica(AlumnoBean alumno, escuelaBean escuela,  usuarioBean usuariocons, programaEsBean programa) throws Exception {
        
       String query ="SELECT ID_RUBRICA, DESC_RUBRICA FROM CAT_RUBRICA ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new rubricaMapper());
        return list;
    }
             
        
    public List programasPlanForm(AlumnoBean alumno, escuelaBean escuela, usuarioBean usuario, programaEsBean programa) throws Exception {
        String query = "SELECT ID_PLAN_FORMA, NUMERO_PERIODO, ID_MATERIA, ID_COMPETENCIA, ID_ESCALA, ID_LUGAR, PLAN_ROTACION, DES_ACTIVIDAD, FECHA_REG, ID_ACTIVIDAD, ID_ACT_EVALUA, NOMBRE_MATERIA, COMPETENCIA, ACTIVIDAD, HORA, LUGAR, ID_HORA FROM (\n" +
"\n" +
"SELECT TBL_PLAN_MATERIA.NOMBRE_MATERIA, TBL_PLAN_MATERIA.NUMERO_PERIODO ,  TBL_MATERIA_COMPETENCIA.COMPETENCIA,  CAT_LUGARES.LUGAR,  CAT_HORAS.HORA,  TBL_PLANFORM_ACT.ID_PLAN_FORMA,  TBL_PLANFORM_ACT.ID_MATERIA,  TBL_PLANFORM_ACT.ID_COMPETENCIA, TBL_PLANFORM_ACT.ID_ESCALA, TBL_PLANFORM_ACT.ID_LUGAR, TBL_PLANFORM_ACT.ID_HORA, TBL_PLANFORM_ACT.PLAN_ROTACION, TBL_PLANFORM_ACT.DES_ACTIVIDAD, TBL_PLANFORM_ACT.FECHA_REG, TBL_PLANFORM_ACT.ID_ACTIVIDAD, TBL_PLANFORM_ACT.ID_ACT_EVALUA, TBL_COMPETENCIA_ACTIVIDAD.ACTIVIDAD FROM TBL_PLANFORM_ACT\n" +
"INNER JOIN TBL_PLAN_MATERIA\n" +
"ON TBL_PLANFORM_ACT.ID_MATERIA = TBL_PLAN_MATERIA.ID_MATERIA\n" +
"INNER JOIN TBL_MATERIA_COMPETENCIA\n" +
"ON TBL_PLANFORM_ACT.ID_COMPETENCIA = TBL_MATERIA_COMPETENCIA.ID_COMPETENCIA\n" +
"INNER JOIN TBL_COMPETENCIA_ACTIVIDAD\n" +
"ON TBL_PLANFORM_ACT.ID_ACTIVIDAD = TBL_COMPETENCIA_ACTIVIDAD.ID_ACTIVIDAD\n" +
"INNER JOIN CAT_LUGARES\n" +
"ON TBL_PLANFORM_ACT.ID_LUGAR = CAT_LUGARES.ID_LUGAR\n" +
"INNER JOIN CAT_HORAS\n" +
"ON TBL_PLANFORM_ACT.ID_HORA = CAT_HORAS.ID_HORA)  WHERE ID_PLAN_FORMA='"+programa.getID_PLAN_FORMA()+"' ORDER BY NUMERO_PERIODO, ID_COMPETENCIA ASC  ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new consultaPlanFormMapper());
        return list;
    }
    
    
    
    
    public List programasPlanFormDatos(AlumnoBean alumno, escuelaBean escuela, usuarioBean usuario, programaEsBean programa) throws Exception {
        String query = " SELECT ID_PLAN_FORMA,  NOMBREPLAN_FORM,  DESCRIPCION,  DURACION,  NO_ESTUDIANTES,  NO_MENTORES_UE,  NO_MENTORES_ACAD, HORAS_SEMANA FROM TBL_PLAN_FORM WHERE ID_PLAN_FORMA='"+programa.getID_PLAN_FORMA()+"' ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new consultaPlanFormDatosMapper());
        return list;
    }
    
    
    
    
    
    
    
    
    public String totalcctplan(escuelaBean escuela, renapoBean renapo) throws Exception {
        String query = "SELECT COUNT (ID_PLAN_FORMA) FROM TBL_PLAN_FORM WHERE ID_CCT_PLAN='"+escuela.getAUXIDCCTPLAN2()+"'  ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        String list = "";
        list = oraDaoFac.queryStringUnCampo(query);
        return list;
    }
    
    
     public boolean GuardaPlanForm(Connection conn, PreparedStatement stat,escuelaBean escuela, renapoBean renapo, String claveConstruida, programaEsBean programa) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PLAN_FORMA", "STRING", claveConstruida);
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_CCT_PLAN", "STRING", escuela.getAUXIDCCTPLAN2());
        arregloCampos.add(temporal);
           temporal = new ObjPrepareStatement("NOMBREPLAN_FORM", "STRING", programa.getNOM_PLAN_FORM());
        arregloCampos.add(temporal);
         
         temporal = new ObjPrepareStatement("DURACION", "STRING", programa.getPERIODO());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("DESCRIPCION", "STRING", programa.getDESCRIPCION_FORM());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("NO_ESTUDIANTES", "STRING", programa.getNO_ESTUDIANTES());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NO_MENTORES_UE", "STRING", programa.getNO_MENTORES_UE());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("NO_MENTORES_ACAD", "STRING", programa.getNO_MENTORES_ACAD());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_IE_UE", "STRING", escuela.getID_IE_UE());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("HORAS_SEMANA", "STRING", programa.getHORAS_SEMANA());
        arregloCampos.add(temporal);
        
        

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsertTransaccion(conn, stat,"TBL_PLAN_FORM", arregloCampos);
    }
     
    public boolean actualizaPlanForm(Connection conn, PreparedStatement stat,escuelaBean escuela, renapoBean renapo, String claveConstruida, programaEsBean programa) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
       
         temporal = new ObjPrepareStatement("ID_CCT_PLAN", "STRING", escuela.getAUXIDCCTPLAN2());
        arregloCampos.add(temporal);
           temporal = new ObjPrepareStatement("NOMBREPLAN_FORM", "STRING", programa.getNOMBREPLAN_FORM());
        arregloCampos.add(temporal);
         
         temporal = new ObjPrepareStatement("DURACION", "STRING", programa.getPERIODO());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("DESCRIPCION", "STRING", programa.getDESCRIPCION_FORM());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("NO_ESTUDIANTES", "STRING", programa.getNO_ESTUDIANTES());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NO_MENTORES_UE", "STRING", programa.getNO_MENTORES_UE());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("NO_MENTORES_ACAD", "STRING", programa.getNO_MENTORES_ACAD());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_IE_UE", "STRING", escuela.getID_IE_UE());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("HORAS_SEMANA", "STRING", programa.getHORAS_SEMANA());
        arregloCampos.add(temporal);
        
         String Condicion = "where ID_PLAN_FORMA='" + programa.getID_PLAN_FORMA() + "' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdateTransaccion(conn, stat,"TBL_PLAN_FORM", arregloCampos,Condicion);
    }
      
     
     
     
     
     public boolean GuardaPlanFormEst(Connection conn, PreparedStatement stat,escuelaBean escuela, renapoBean renapo, programaEsBean programa, AlumnoBean alumno) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_HIST_ALU", "STRING", alumno.getAUXIDHISTALUM());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_PLAN_FORM", "STRING", programa.getID_PLAN_FORMA());
        arregloCampos.add(temporal);
           temporal = new ObjPrepareStatement("FECHA_INICIOPF", "STRING", programa.getFECHA_REG_PLAN());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("FECHA_TERMINOPF", "STRING", programa.getFECHA_TERMINO_PLAN());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_MENTOR_UE", "STRING", programa.getID_MENTORUE());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_MENTOR_ACAD", "STRING", programa.getID_MENTORACAD());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_RES_UE", "STRING", programa.getID_RESUE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_RES_ACAD", "STRING", "");
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ESTATUS_PF", "STRING", "1");
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_RES_PROGEDU", "STRING", programa.getID_RES_PROGEDU());
        arregloCampos.add(temporal);
        

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsertTransaccion(conn, stat,"TBL_HISTALU_PF", arregloCampos);
    }
     
     
        public boolean actualizaPlanFormEst(Connection conn, PreparedStatement stat,escuelaBean escuela, renapoBean renapo, programaEsBean programa, AlumnoBean alumno) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_HIST_ALU", "STRING", alumno.getAUXIDHISTALUM());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_PLAN_FORM", "STRING", programa.getID_PLAN_FORMA());
        arregloCampos.add(temporal);
           temporal = new ObjPrepareStatement("FECHA_INICIOPF", "STRING", programa.getFECHA_REG_PLAN());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("FECHA_TERMINOPF", "STRING", programa.getFECHA_TERMINO_PLAN());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_MENTOR_UE", "STRING", programa.getID_MENTORUE());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_MENTOR_ACAD", "STRING", programa.getID_MENTORACAD());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_RES_UE", "STRING", programa.getID_RESUE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_RES_ACAD", "STRING", "");
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ESTATUS_PF", "STRING", "1");
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_RES_PROGEDU", "STRING", programa.getID_RES_PROGEDU());
        arregloCampos.add(temporal);
          String Condicion = "where ID_HIST_ALU='" + alumno.getAUXIDHISTALUM() + "' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdateTransaccion(conn, stat,"TBL_HISTALU_PF", arregloCampos, Condicion );
    }
     
      public boolean borrarPlanFormEstAct(Connection conn, PreparedStatement stat,escuelaBean escuela, renapoBean renapo, programaEsBean programa, AlumnoBean alumno) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_HIST_ALU", "STRING", alumno.getAUXIDHISTALUM());
        arregloCampos.add(temporal);
        
        
//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryDelete("TBL_PLANFORM_ACT_ALU", arregloCampos );
    }
     
      public boolean GuardaPlanFormaActividades(Connection conn, PreparedStatement stat,escuelaBean escuela, renapoBean renapo, String claveConstruida, programaEsBean programa) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PLAN_FORMA", "STRING", claveConstruida);
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_MATERIA", "STRING", programa.getID_MATERIA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_COMPETENCIA", "STRING", programa.getID_COMPETENCIA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_ACTIVIDAD", "STRING", programa.getID_ACTIVIDAD());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_ESCALA", "STRING", programa.getESCALA_PLAN());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("ID_HORA", "STRING", programa.getHORAS_PLAN());
        arregloCampos.add(temporal);
       temporal = new ObjPrepareStatement("ID_LUGAR", "STRING", programa.getLUGAR_PLAN());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("PLAN_ROTACION", "STRING", programa.getPLAN_ROTACION());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("DES_ACTIVIDAD", "STRING", programa.getDES_ACTIVIDAD());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("EVIDENCIAS", "STRING", programa.getEVIDENCIAS());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsertTransaccion(conn, stat,"TBL_PLANFORM_ACT", arregloCampos);
    }
      
       public boolean GuardaPlanFormaActividadesFaltantes(Connection conn, PreparedStatement stat,escuelaBean escuela, renapoBean renapo, String claveConstruida, programaEsBean programa) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PLAN_FORMA", "STRING", programa.getID_PLAN_FORMA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_MATERIA", "STRING", programa.getID_MATERIA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_COMPETENCIA", "STRING", programa.getID_COMPETENCIA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_ACTIVIDAD", "STRING", programa.getID_ACTIVIDAD());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_ESCALA", "STRING", programa.getID_ESCALA());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("ID_HORA", "STRING", programa.getID_HORA());
        arregloCampos.add(temporal);
       temporal = new ObjPrepareStatement("ID_LUGAR", "STRING", programa.getID_LUGAR());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("PLAN_ROTACION", "STRING", programa.getPLAN_ROTACION());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("DES_ACTIVIDAD", "STRING", programa.getDES_ACTIVIDAD());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("EVIDENCIAS", "STRING", programa.getEVIDENCIAS());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsertTransaccion(conn, stat,"TBL_PLANFORM_ACT", arregloCampos);
    }
      
      public boolean ActualizaPlanFormaActividades(Connection conn, PreparedStatement stat,escuelaBean escuela, renapoBean renapo, String claveConstruida, programaEsBean programa) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
      
         temporal = new ObjPrepareStatement("ID_MATERIA", "STRING", programa.getID_MATERIA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_COMPETENCIA", "STRING", programa.getID_COMPETENCIA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_ACTIVIDAD", "STRING", programa.getID_ACTIVIDAD());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_ESCALA", "STRING", programa.getID_ESCALA());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("ID_HORA", "STRING", programa.getID_HORA());
        arregloCampos.add(temporal);
       temporal = new ObjPrepareStatement("ID_LUGAR", "STRING", programa.getID_LUGAR());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("PLAN_ROTACION", "STRING", programa.getPLAN_ROTACION());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("DES_ACTIVIDAD", "STRING", programa.getDES_ACTIVIDAD());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("EVIDENCIAS", "STRING", programa.getEVIDENCIAS());
        arregloCampos.add(temporal);
        
        String Condicion = "where ID_PLAN_FORMA='" + programa.getID_PLAN_FORMA() + "' AND ID_ACT_EVALUA='" + programa.getID_ACT_EVALUA() + "' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdateTransaccion(conn, stat,"TBL_PLANFORM_ACT", arregloCampos,Condicion);
    }
      
        public boolean GuardaPlanFormaActividadesAlu(Connection conn, PreparedStatement stat,escuelaBean escuela, renapoBean renapo, programaEsBean programa, AlumnoBean alumno) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PLAN_FORMA", "STRING", programa.getID_PLAN_FORMA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_MATERIA", "STRING", programa.getID_MATERIA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_COMPETENCIA", "STRING", programa.getID_COMPETENCIA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_ACTIVIDAD", "STRING", programa.getID_ACTIVIDAD());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_ESCALA", "STRING", programa.getID_ESCALA());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("ID_HORA", "STRING", programa.getHORAS_PLAN());
        arregloCampos.add(temporal);
       temporal = new ObjPrepareStatement("ID_LUGAR", "STRING", programa.getLUGAR_PLAN());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("PLAN_ROTACION", "STRING", programa.getPLAN_ROTACION());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("DES_ACTIVIDAD", "STRING", programa.getDES_ACTIVIDAD());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_HIST_ALU", "STRING", alumno.getAUXIDHISTALUM());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_LUGAR", "STRING", programa.getID_LUGAR());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_HORA", "STRING", programa.getID_HORA());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsertTransaccion(conn, stat,"TBL_PLANFORM_ACT_ALU", arregloCampos);
    }
      
           public boolean GuardaPlanFormaActividadesAluAct(Connection conn, PreparedStatement stat,escuelaBean escuela, renapoBean renapo, programaEsBean programa, AlumnoBean alumno) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_PLAN_FORMA", "STRING", programa.getID_PLAN_FORMA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_MATERIA", "STRING", programa.getID_MATERIA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_COMPETENCIA", "STRING", programa.getID_COMPETENCIA());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_ACTIVIDAD", "STRING", programa.getID_ACTIVIDAD());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("ID_ESCALA", "STRING", programa.getID_ESCALA());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("ID_HORA", "STRING", programa.getHORAS_PLAN());
        arregloCampos.add(temporal);
       temporal = new ObjPrepareStatement("ID_LUGAR", "STRING", programa.getLUGAR_PLAN());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("PLAN_ROTACION", "STRING", programa.getPLAN_ROTACION());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("DES_ACTIVIDAD", "STRING", programa.getDES_ACTIVIDAD());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_HIST_ALU", "STRING", alumno.getAUXIDHISTALUM());
        arregloCampos.add(temporal);
        

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsertTransaccion(conn, stat,"TBL_PLANFORM_ACT_ALU", arregloCampos);
    }
      
      
      
    
      public List periodo(escuelaBean escuela) throws Exception {
        String query = "SELECT ID_PERIODO, PERIODO FROM CAT_PERIODO";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new periodoMapper());
        return list;
    }
    
    public List listaUE(escuelaBean escuela, usuarioBean usuario) throws Exception {
        String query = "SELECT CATUE.ID_IE_UE, UNIECO.RFC, CASE WHEN NOMBRE IS NOT NULL THEN (UNIECO.RAZON_SOCIAL||'/'||'SUC:'||SUCU.NOMBRE ) ELSE UNIECO.RAZON_SOCIAL  END AS RAZON_SOCIAL FROM (SELECT UE.ID_IE_UE, UEINST.ID_UE, UEINST.ID_SUC  FROM(SELECT * FROM (SELECT DISTINCT(ID_IE_UE) FROM(SELECT RESIN.*, PLRES.ID_PLAN, CCPLAN.ID_CCT_PLAN  FROM(SELECT * FROM TBL_RESPE_INST WHERE ID_ESCUELA='"+usuario.getID_ESCUELA()+"' AND PERFIL='26')RESIN JOIN (SELECT * FROM TBL_PLAN_RESPONSABLE WHERE ID_ESCUELA='"+usuario.getID_ESCUELA()+"' AND PERFIL=26 AND ESTATUS=1)PLRES ON RESIN.ID_PERSONA=PLRES.ID_PERSONA JOIN (SELECT * FROM TBL_CCT_PLAN WHERE ID_ESCUELA='"+usuario.getID_ESCUELA()+"' )CCPLAN ON PLRES.ID_PLAN=CCPLAN.ID_PLAN) WHERE ID_CCT_PLAN='"+escuela.getAUXIDCCTPLAN2()+"'))UE JOIN (SELECT * FROM TBL_INS_UE WHERE STATUS_GENERAL=1 )UEINST  ON UE.ID_IE_UE=UEINST.ID_IE_UE) CATUE LEFT OUTER JOIN (SELECT * FROM CAT_UNIDAD_ECONOMICA)UNIECO ON CATUE.ID_UE=UNIECO.ID_UE LEFT OUTER JOIN (SELECT * FROM TBL_SUCURSALES)SUCU ON CATUE.ID_SUC=SUCU.ID_SUC";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new ueIeactivasMapper());
        return list;
    }
    
     public String nivel(escuelaBean escuela, usuarioBean usuario) throws Exception {
        String query = "SELECT ID_NIVEL FROM CAT_CCT WHERE ID_ESCUELA='"+usuario.getID_ESCUELA()+"'";
         
         Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        String list = "";
        list = oraDaoFac.queryStringUnCampo(query);
        return list;
    }
     
      
      
      public List consultaAlumPlan(AlumnoBean alumno, usuarioBean usuario, escuelaBean escuela) throws Exception {
       String query ="SELECT * FROM(SELECT ID_HIST_ALUM, ID_ALUMNO, ID_ESCUELA, ID_PLAN, ID_CCT_PLAN, CURP, NOMBRE||' '||APELLIDOP||' '||APELLIDOM AS NOMBRE, ID_UE, MATRICULA, GRADO, STATUS_PROCESO  FROM VISTA_META_ALUMNO WHERE STATUS_PROCESO>=5 AND STATUS_UE=1) WHERE ID_ESCUELA='"+usuario.getID_ESCUELA()+"' AND ID_CCT_PLAN='"+escuela.getAUXIDCCTPLAN()+"' ORDER BY GRADO, NOMBRE ASC";
        
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new consultaAluRegMapper());
        return list;
    }
        public List consultaAlumPlanHit(AlumnoBean alumno, usuarioBean usuario, escuelaBean escuela) throws Exception {
       String query ="SELECT * FROM(SELECT ID_HIST_ALUM, ID_ALUMNO, ID_ESCUELA, ID_PLAN, ID_CCT_PLAN, CURP, NOMBRE||' '||APELLIDOP||' '||APELLIDOM AS NOMBRE, ID_UE, MATRICULA, GRADO, STATUS_PROCESO  FROM VISTA_META_ALUMNO WHERE STATUS_PROCESO>=4 AND STATUS_UE=1) WHERE ID_HIST_ALUM='"+alumno.getAUXIDHISTALUM()+"' ORDER BY GRADO, NOMBRE ASC";
        
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new consultaAluRegMapper());
        return list;
    }
         public List consultaPlanAlumno(AlumnoBean alumno, usuarioBean usuario, escuelaBean escuela) throws Exception {
       String query ="SELECT  ID_HIST_ALU,  ID_PLAN_FORM,  TO_CHAR(FECHA_INICIOPF,'dd/mm/yyyy') AS FECHA_INICIOPF , TO_CHAR( FECHA_TERMINOPF, 'dd/mm/yyyy') as FECHA_TERMINOPF,  ID_MENTOR_UE,  ID_MENTOR_ACAD,  ID_RES_UE,  ID_RES_ACAD,  ESTATUS_PF,  ID_RES_PROGEDU FROM TBL_HISTALU_PF  WHERE ID_HIST_ALU='"+alumno.getAUXIDHISTALUM()+"'";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new consultaPlanAluHist());
        return list;
    }
        
        public List consultaUE(AlumnoBean alumno, usuarioBean usuario, escuelaBean escuela) throws Exception {
       String query ="SELECT UE.RFC, UE.RAZON_SOCIAL||' / NOMBRE COMERCIAL:'|| NVL(UE.NOM_COMERCIAL,'SIN NOMBRE COMERCIAL')||'/SUCURSAL:'||NVL(SUC.NOMBRE,'SIN SUCURSAL') AS RAZON_SOCIAL FROM (SELECT * FROM CAT_UNIDAD_ECONOMICA WHERE ID_UE= '"+alumno.getID_UE()+"' AND STATUS=1) UE LEFT OUTER JOIN (SELECT * FROM TBL_SUCURSALES)SUC ON UE.ID_UE=SUC.ID_UE";
        
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new consultaUEMapper());
        return list;
    } 
        
          public List listaResUE(AlumnoBean alumno, usuarioBean usuario, escuelaBean escuela,int perfil) throws Exception {
       String query ="SELECT ID_RESPROG_INST, ID_PERSONA, CURP_PERSONA, NOMBRE_PERSONA || ' ' || APATERNO_PERSONA||' '||AMATERNO_PERSONA AS NOMBRE_PERSONA  FROM(SELECT RESINST.*, PLANRES.ID_PLAN, USR.ESTATUS AS ESTATUS_RESPONSABLE, PERS.CURP_PERSONA, PERS.NOMBRE_PERSONA, PERS.APATERNO_PERSONA, PERS.AMATERNO_PERSONA, INUE.ID_UE  FROM (SELECT ID_RESPROG_INST,  ID_PERSONA,  ID_ESCUELA,  PERFIL,  FECHA_REG,  ID_IE_UE FROM TBL_RESPE_INST WHERE PERFIL='"+perfil+"' AND ID_ESCUELA='"+usuario.getID_ESCUELA()+"') RESINST JOIN (SELECT * FROM TBL_PLAN_RESPONSABLE WHERE  PERFIL='"+perfil+"' AND ID_ESCUELA='"+usuario.getID_ESCUELA()+"' AND ESTATUS=1)PLANRES ON RESINST.ID_PERSONA=PLANRES.ID_PERSONA JOIN (SELECT * FROM TBL_USUARIOS)USR ON RESINST.ID_ESCUELA=USR.ID_ESCUELA AND RESINST.ID_PERSONA=USR.ID_PERSONA AND RESINST.PERFIL=USR.PERFIL AND RESINST.ID_IE_UE=USR.ID_IE_UE JOIN(SELECT ID_PERSONA, CURP_PERSONA, NOMBRE_PERSONA, APATERNO_PERSONA, AMATERNO_PERSONA FROM CAT_PERSONA)  PERS ON RESINST.ID_PERSONA=PERS.ID_PERSONA JOIN (SELECT * FROM TBL_INS_UE) INUE ON RESINST.ID_IE_UE=INUE.ID_IE_UE ) WHERE ID_UE='"+alumno.getID_UE()+"' AND ID_PLAN='"+alumno.getID_PLAN()+"'  ";
        
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new responsablePerfilMapper());
        return list;
    } 
          
           public List listaResAcad(AlumnoBean alumno, usuarioBean usuario, escuelaBean escuela,int perfil) throws Exception {
       String query =" SELECT '01' AS ID_RESPROG_INST , ID_PERSONA, CURP_PERSONA, NOMBRE_PERSONA || ' ' || APATERNO_PERSONA||' '||AMATERNO_PERSONA AS NOMBRE_PERSONA FROM(SELECT TBL_USUARIOS.NAMEUSUARIO,  TBL_USUARIOS.PERFIL,  TBL_USUARIOS.ID_PERSONA,  TBL_USUARIOS.ESTATUS,  TBL_USUARIOS.ID_USUARIO,  TBL_USUARIOS.ID_ESCUELA,  TBL_USUARIOS.ID_IE_UE,  CAT_PERSONA.CURP_PERSONA,  CAT_PERSONA.NOMBRE_PERSONA,  CAT_PERSONA.APATERNO_PERSONA,  CAT_PERSONA.AMATERNO_PERSONA FROM TBL_USUARIOS INNER JOIN CAT_PERSONA ON TBL_USUARIOS.ID_PERSONA = CAT_PERSONA.ID_PERSONA) WHERE ID_ESCUELA='"+usuario.getID_ESCUELA()+"' AND PERFIL='24' ";
        
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new responsablePerfilMapper());
        return list;
    } 
    
             public List listaMentorAcad(AlumnoBean alumno, usuarioBean usuario, escuelaBean escuela,int perfil) throws Exception {
       String query =" SELECT ID_RESPROG_INST, ID_PERSONA, CURP_PERSONA, NOMBRE_PERSONA || ' ' || APATERNO_PERSONA||' '||AMATERNO_PERSONA AS NOMBRE_PERSONA  FROM(SELECT RESINST.*, PLANRES.ID_PLAN, USR.ESTATUS AS ESTATUS_RESPONSABLE, PERS.CURP_PERSONA, PERS.NOMBRE_PERSONA, PERS.APATERNO_PERSONA, PERS.AMATERNO_PERSONA  FROM (SELECT ID_RESPROG_INST,  ID_PERSONA,  ID_ESCUELA,  PERFIL,  FECHA_REG FROM TBL_RESPE_INST WHERE PERFIL='27' AND ID_ESCUELA='"+usuario.getID_ESCUELA()+"') RESINST JOIN (SELECT * FROM TBL_PLAN_RESPONSABLE WHERE  PERFIL='27' AND ID_ESCUELA='"+usuario.getID_ESCUELA()+"' AND ESTATUS=1)PLANRES ON RESINST.ID_PERSONA=PLANRES.ID_PERSONA JOIN (SELECT * FROM TBL_USUARIOS)USR ON RESINST.ID_ESCUELA=USR.ID_ESCUELA AND RESINST.ID_PERSONA=USR.ID_PERSONA AND RESINST.PERFIL=USR.PERFIL  JOIN(SELECT ID_PERSONA, CURP_PERSONA, NOMBRE_PERSONA, APATERNO_PERSONA, AMATERNO_PERSONA FROM CAT_PERSONA)  PERS ON RESINST.ID_PERSONA=PERS.ID_PERSONA  ) where ID_PLAN='"+alumno.getID_PLAN()+"' ";
        
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new responsablePerfilMapper());
        return list;
    } 
             
         public List listaPlanUE(AlumnoBean alumno, usuarioBean usuario, escuelaBean escuela,int perfil) throws Exception {
       String query =" SELECT ID_PLAN_FORMA, NOMBREPLAN_FORM, DURACION FROM (SELECT TBL_CCT_PLAN.ID_PLAN,  TBL_INS_UE.ID_ESCUELA,  TBL_INS_UE.ID_UE,  TBL_INS_UE.STATUS_GENERAL,  TBL_PLAN_FORM.ID_PLAN_FORMA,  TBL_PLAN_FORM.NOMBREPLAN_FORM,  TBL_PLAN_FORM.DURACION,  TBL_PLAN_FORM.ID_CCT_PLAN,  TBL_PLAN_FORM.ESTATUS,  TBL_PLAN_FORM.ID_IE_UE,  TBL_PLAN_FORM.DESCRIPCION FROM TBL_PLAN_FORM INNER JOIN TBL_CCT_PLAN ON TBL_PLAN_FORM.ID_CCT_PLAN = TBL_CCT_PLAN.ID_CCT_PLAN INNER JOIN TBL_INS_UE ON TBL_PLAN_FORM.ID_IE_UE = TBL_INS_UE.ID_IE_UE)WHERE ID_PLAN='"+alumno.getID_PLAN()+"' AND ID_ESCUELA='"+usuario.getID_ESCUELA()+"' AND ID_UE='"+alumno.getID_UE()+"' AND STATUS_GENERAL=1 ORDER BY ID_PLAN_FORMA ASC";
        
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new listaPlanUEMapper());
        return list;
    }      
             
       public List listaPlanUEEdita(AlumnoBean alumno, usuarioBean usuario, escuelaBean escuela) throws Exception {
       String query =" SELECT ID_PLAN_FORMA, NOMBREPLAN_FORM, DURACION FROM (SELECT TBL_CCT_PLAN.ID_PLAN,  TBL_INS_UE.ID_ESCUELA,  TBL_INS_UE.ID_UE,  TBL_INS_UE.STATUS_GENERAL,  TBL_PLAN_FORM.ID_PLAN_FORMA,  TBL_PLAN_FORM.NOMBREPLAN_FORM,  TBL_PLAN_FORM.DURACION,  TBL_PLAN_FORM.ID_CCT_PLAN,  TBL_PLAN_FORM.ESTATUS,  TBL_PLAN_FORM.ID_IE_UE,  TBL_PLAN_FORM.DESCRIPCION FROM TBL_PLAN_FORM INNER JOIN TBL_CCT_PLAN ON TBL_PLAN_FORM.ID_CCT_PLAN = TBL_CCT_PLAN.ID_CCT_PLAN INNER JOIN TBL_INS_UE ON TBL_PLAN_FORM.ID_IE_UE = TBL_INS_UE.ID_IE_UE)WHERE ID_CCT_PLAN='"+escuela.getAUXIDCCTPLAN2()+"'  AND ID_IE_UE='"+escuela.getID_IE_UE()+"' AND STATUS_GENERAL=1 ORDER BY ID_PLAN_FORMA ASC";
        
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new listaPlanUEMapper());
        return list;
    }             
             
    public List listaEscala(escuelaBean escuela, usuarioBean usuario) throws Exception {
       String query ="SELECT ID_ESCALA, ESCALA FROM CAT_ESCALA ORDER BY ID_ESCALA ASC";
        
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new escalaMapper());
        return list;
    }
    public List listaLugar(escuelaBean escuela, usuarioBean usuario) throws Exception {
       String query = "SELECT ID_LUGAR, LUGAR FROM CAT_LUGARES ORDER BY ID_LUGAR ASC";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new lugarMapper());
        return list;
    }
    public List listaHora(escuelaBean escuela, usuarioBean usuario) throws Exception {
        String query = "SELECT ID_HORA, HORA FROM CAT_HORAS ORDER BY ID_HORA ASC ";
        Constantes.enviaMensajeConsola("Consulta cct----->" + query);
        List list = null;
        list = oraDaoFac.queryForList(query, new horaMapper());
        return list;
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
    
    public boolean guardaRegAluComp(AlumnoBean alumno, programaEsBean programa) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_COMPETENCIA", "STRING", programa.getAUX_IDCOMPETENCIA());
        arregloCampos.add(temporal);
          temporal = new ObjPrepareStatement("ID_HIST_ALU", "STRING", alumno.getAUXIDHISTALUM());
        arregloCampos.add(temporal);
           temporal = new ObjPrepareStatement("DES_ACT", "STRING", programa.getDES_ACT());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("MARCO_TEORICO", "STRING", programa.getMARCO_TEORICO());
        arregloCampos.add(temporal);
      temporal = new ObjPrepareStatement("RUTA_EVIDENCIAS", "STRING", programa.getRUTA_EVIDENCIAS());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("EDITA", "STRING", "1");
        arregloCampos.add(temporal);
        

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert("TBL_REGALUMCOMP", arregloCampos);
    }
     public boolean actualizaRegAluComp(AlumnoBean alumno, programaEsBean programa) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
      
           temporal = new ObjPrepareStatement("DES_ACT", "STRING", programa.getDES_ACT());
        arregloCampos.add(temporal);
         temporal = new ObjPrepareStatement("MARCO_TEORICO", "STRING", programa.getMARCO_TEORICO());
        arregloCampos.add(temporal);
       temporal = new ObjPrepareStatement("EDITA", "STRING", "1");
        arregloCampos.add(temporal);
       
        String Condicion = "WHERE ID_COMPETENCIA='" + programa.getAUX_IDCOMPETENCIA() + "'  AND  ID_HIST_ALU='" + alumno.getAUXIDHISTALUM() + "' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_REGALUMCOMP", arregloCampos, Condicion);
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
    
    
    public boolean ActualizaEstatusAlumno(Connection conn, PreparedStatement stat, programaEsBean programa, AlumnoBean alumno) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("STATUS_GENERAL", "STRING", "1");
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS_PROCESO", "STRING", "6");
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FECHA_INICIO_PF", "STRING", programa.getFECHA_REG_PLAN());
        arregloCampos.add(temporal);
        

        String Condicion = "where ID_HIST_ALUM='" + alumno.getAUXIDHISTALUM()+ "' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate("TBL_HIST_ALUM", arregloCampos, Condicion);
    }
    
     public boolean registraEvaluacionMUE(Connection conn, PreparedStatement stat, programaEsBean programa, AlumnoBean alumno) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("EVAL_MUE", "STRING", programa.getID_RUBRICA());
        arregloCampos.add(temporal);
       
        

        String Condicion = "where ID_HIST_ALU='" + alumno.getAUXIDHISTALUM()+ "' AND ID_ACTIVIDAD='" + programa.getID_ACTIVIDAD()+ "' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdateTransaccion(conn, stat,"TBL_PLANFORM_ACT_ALU", arregloCampos, Condicion);
    }
     
      public boolean registraEvaluacionMA(Connection conn, PreparedStatement stat, programaEsBean programa, AlumnoBean alumno) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("EVAL_MA", "STRING", programa.getID_RUBRICA());
        arregloCampos.add(temporal);
       
        

        String Condicion = "where ID_HIST_ALU='" + alumno.getAUXIDHISTALUM()+ "' AND ID_ACTIVIDAD='" + programa.getID_ACTIVIDAD()+ "' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdateTransaccion(conn, stat,"TBL_PLANFORM_ACT_ALU", arregloCampos, Condicion);
    }
     public boolean registraEvaluacioncComMUE(Connection conn, PreparedStatement stat, programaEsBean programa, AlumnoBean alumno, String fechaHoy) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("EVAL_MUE", "STRING", fechaHoy);
        arregloCampos.add(temporal);
       
        

        String Condicion = "where ID_HIST_ALU='" + alumno.getAUXIDHISTALUM()+ "' AND ID_COMPETENCIA='" + programa.getAUX_IDCOMPETENCIA()+ "' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdateTransaccion(conn, stat,"TBL_REGALUMCOMP", arregloCampos, Condicion);
    }
      public boolean registraEvaluacioncComMA(Connection conn, PreparedStatement stat, programaEsBean programa, AlumnoBean alumno, String fechaHoy) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("EVAL_MA", "STRING", fechaHoy);
        arregloCampos.add(temporal);
       
        

        String Condicion = "where ID_HIST_ALU='" + alumno.getAUXIDHISTALUM()+ "' AND ID_COMPETENCIA='" + programa.getAUX_IDCOMPETENCIA()+ "' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdateTransaccion(conn, stat,"TBL_REGALUMCOMP", arregloCampos, Condicion);
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
        String query = "SELECT sa.id_hist_alum, ca.id_alumno,ca.curp,ca.nombre || ' ' || ca.apellidop || ' ' || ca.apellidom as nombre_completo, ca.fec_nac,ca.entidad_nac,ca.sexo,ca.matricula, "
                + "(select p.nom_carrera from tbl_cct_plan tc INNER JOIN cat_plan p on tc.id_plan=p.id_plan WHERE tc.id_cct_plan=cve_pro_edu) as cve_pro_edu, "
                + "(SELECT catalogo.descripcion FROM catalogo where id_catalogo=grado) as grado, "
                + "sa.status_proceso FROM cat_alumno CA INNER JOIN TBL_HIST_ALUM SA ON ca.id_alumno=sa.id_alumno  WHERE id_escuela='" + al.getID_ESCUELA() + "'  and sa.status_proceso=1 ";
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
     public boolean EliminaActividad(Connection conn, PreparedStatement stat, programaEsBean programa) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del delete ELIMINAR ALUMNO...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_ACT_EVALUA", "STRING", programa.getID_ACT_EVALUA());
        arregloCampos.add(temporal);
         String Condicion = "where ID_PLAN_FORMA='" + programa.getID_PLAN_FORMA()+ "' AND ID_COMPETENCIA='" + programa.getID_COMPETENCIA()+"' ";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryDeleteCondicion(conn, stat,"TBL_PLANFORM_ACT", arregloCampos,Condicion);
    }

    public List ConsultaInteresadosPE(AlumnoBean al) throws Exception {
        String query = "SELECT sa.id_hist_alum, CA.id_alumno,CA.curp, CA.nombre || ' ' || CA.apellidop || ' ' || CA.apellidom as nombre_completo, CA.matricula,ca.cve_pro_edu, "
                + "(select p.nom_carrera from tbl_cct_plan tc INNER JOIN cat_plan p on tc.id_plan=p.id_plan WHERE tc.id_cct_plan=CA.cve_pro_edu) as nom_pro_edu , "
                + "(SELECT catalogo.descripcion FROM catalogo where id_catalogo=CA.grado) as grado , "
                + "ca.promedio,ca.situacion_academica,ca.tel_casa,ca.tel_cel,ca.email_ins,ca.email_per,TO_CHAR(sa.FECHA_INC_PADRON) as FECHA_INC_PADRON,sa.res_eva,sa.status_proceso "
                + "FROM cat_alumno  CA INNER JOIN tbl_hist_alum SA ON ca.id_alumno=sa.id_alumno WHERE CA.cve_pro_edu IN (SELECT cp.id_cct_plan FROM tbl_plan_responsable pr INNER JOIN tbl_cct_plan cp on cp.id_plan=pr.id_plan where  pr.id_persona='" + al.getID_PERSONA() + "'AND cp.id_escuela='" + al.getID_ESCUELA() + "' and pr.perfil='" + al.getPERFIL() + "' AND sa.status_proceso=1 OR sa.status_proceso=2 ) "
                + "ORDER BY ca.cve_pro_edu";
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
                + "FROM cat_alumno CA INNER JOIN TBL_HIST_ALUM SA ON ca.id_alumno=sa.id_alumno  WHERE id_escuela='" + al.getID_ESCUELA() + "'  AND sa.status_proceso='2' or sa.status_proceso='3' order by sa.status_proceso, cve_pro_edu, grado ";
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
        String query = "SELECT sa.id_hist_alum, CA.id_alumno,CA.curp, CA.nombre || ' ' || CA.apellidop || ' ' || CA.apellidom as nombre_completo,CA.fec_nac,CA.matricula,ca.cve_pro_edu, "
                + "(select p.nom_carrera from tbl_cct_plan tc INNER JOIN cat_plan p on tc.id_plan=p.id_plan WHERE tc.id_cct_plan=CA.cve_pro_edu) as nom_pro_edu ,"
                + "(SELECT catalogo.descripcion FROM catalogo where id_catalogo=CA.grado) as grado, "
                + "ca.no_seguro,ca.domicilio,ca.colonia,ca.localidad,ca.cp,ca.cve_mun,TO_CHAR(ca.fecha_inicio_dual) as fecha_inicio_dual,ca.tipo_alumno,ca.curp_padre,ca.nom_padre,ca.apellidop_padre,ca.apellidom_padre,ca.tel_padre,ca.email_padre,ca.domicilio_padre,ca.colonia_padre, "
                + "ca.localidad_padre,ca.cp_padre,ca.cve_mun_padre,ca.mismo_domicilio, sa.status_proceso "
                + "FROM cat_alumno  CA INNER JOIN tbl_hist_alum SA ON ca.id_alumno=sa.id_alumno WHERE CA.cve_pro_edu IN (SELECT cp.id_cct_plan FROM tbl_plan_responsable pr INNER JOIN tbl_cct_plan cp on cp.id_plan=pr.id_plan where  pr.id_persona='" + al.getID_PERSONA() + "'AND cp.id_escuela='" + al.getID_ESCUELA() + "' and pr.perfil='" + al.getPERFIL() + "' AND sa.status_proceso=4 or sa.status_proceso=5 ) "
                + "ORDER BY  sa.id_hist_alum,ca.cve_pro_edu ";
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
