/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import beans.actividadesBean;
import beans.cctPlanBean;
import beans.competenciaBean;
import beans.escuelaBean;
import beans.materiaBean;
import beans.moduloAuxBean;
import beans.moduloBean;
import beans.periodoBean;
import beans.programaEsBean;
import beans.renapoBean;
import beans.usuarioBean;
import business.ConsultasBusiness;
import com.opensymphony.xwork2.ActionSupport;
import daos.UnidadEDAOImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import utilidades.Constantes;

/**
 *
 * @author pedro
 */
public class Catalogos_Action extends ActionSupport implements SessionAware {

    private usuarioBean usuariocons;
    private String cveusuario;
    private String pasusuario;
    private String nomModulo;
    private String modulo;
    private String nombreUsuario;
    private String tabSelect;

    private String nombreEsc;
    private String nombrePrograma;

    public List<moduloBean> modulosAUX = new ArrayList<moduloBean>();
    public List<moduloAuxBean> modulosAUXP = new ArrayList<moduloAuxBean>();

    // LISTAS DE LA APLICACIÓN
    public List<programaEsBean> ListaProgramasEdu = new ArrayList<programaEsBean>();

    public List<programaEsBean> ListaProgramasEscuela = new ArrayList<programaEsBean>();

    public List<escuelaBean> ListaIdEs = new ArrayList<escuelaBean>();

    public List<cctPlanBean> ValidacctPlan = new ArrayList<cctPlanBean>();

    public List<cctPlanBean> ValidaPlanCctMateria = new ArrayList<cctPlanBean>();

    public List<materiaBean> ListaPlanMateria = new ArrayList<materiaBean>();

    public List<competenciaBean> ValidaMateriaCompetencia = new ArrayList<competenciaBean>();

    public List<actividadesBean> ListaActividadCct = new ArrayList<actividadesBean>();

    public List<periodoBean> ListaPeriodo = new ArrayList<periodoBean>();

    public List<programaEsBean> ListaProgramasRegistro = new ArrayList<programaEsBean>();

    //BANDERAS
    public boolean bnprog = false;
    public boolean bnprogno = false;
    public boolean bnmateria = false;
    public boolean bncompetencia = false;
    public boolean bnactividad = false;

    programaEsBean programa = new programaEsBean();
    escuelaBean escuela = new escuelaBean();
    Constantes mensaje = new Constantes();
    cctPlanBean cctplan = new cctPlanBean();
    materiaBean materia = new materiaBean();
    competenciaBean competencia = new competenciaBean();
    actividadesBean actividad = new actividadesBean();
    renapoBean objRenapo = new renapoBean();

    private String TipoError;
    private String TipoException;
    //******************** PARA OBJETO DE NAVEGACIoN ***********************************************
    private Map session;

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }
    //******************** TERMINA OBJETO DE NAVEGACIoN **********************************************

    public String idEsc() throws Exception {

        ConsultasBusiness con = new ConsultasBusiness();

        escuela.setCCT(usuariocons.getUSUARIO());

        ListaIdEs = con.programasIdEs(escuela);

        Iterator LPE = ListaIdEs.iterator();

        escuelaBean obj;

        while (LPE.hasNext()) {
            obj = (escuelaBean) LPE.next();

            escuela.setID_ESCUELA(obj.getID_ESCUELA());

        }

        return escuela.getID_ESCUELA();
    }

    public String idSubSis() throws Exception {

        programa.setID_SUBSISTEMA_CONSULTA(usuariocons.getFILTRO());

        return programa.getID_SUBSISTEMA_CONSULTA();
    }

    public String RegPlanEstudioCct() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());

            ListaProgramasEscuela = con.programasEscuela(escuela);

            escuela.setAUXPLAN("");
            escuela.setAUXSTATUS("");

            programa.setCONSULTA_CVE_PLAN("");

            bnprog = false;

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String RegMateriaPlan() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());

            objRenapo.setAUXESCUELA(escuela.getID_ESCUELA());
            objRenapo.setID_PERSONA(usuariocons.getID_PERSONA());
            objRenapo.setAUXPERFIL(String.valueOf(usuariocons.getPERFIL()));

            ListaProgramasEscuela = con.programasEscuelaRes(escuela, objRenapo);

            escuela.setAUXPLAN("");
            escuela.setAUXSTATUS("");
            escuela.setAUXIDCCTPLAN("");
            programa.setCONSULTA_CVE_PLAN("");
            programa.setCVE_PLAN("");
            programa.setNOM_CARRERA("");
            bnmateria = false;
            ListaPlanMateria.clear();

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String GuardaMateriaPlan() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            mensaje.enviaMensajeConsola("ID DEL PLAN" + escuela.getAUXIDCCTPLAN());

            mensaje.enviaMensajeConsola("CVE MATERIA" + materia.getCVE_MATERIA());

            mensaje.enviaMensajeConsola("NOMBRE MATERRIA" + materia.getNOMBRE_MATERIA());

            ListaPlanMateria = con.cctPlanMateria(escuela);

            Iterator LPM = ListaPlanMateria.iterator();

            materiaBean obj;

            boolean cvemat = false;
            boolean cvenommat = false;

            while (LPM.hasNext()) {
                obj = (materiaBean) LPM.next();

                if (obj.getCVE_MATERIA().equals(materia.getCVE_MATERIA())) {

                    cvemat = true;

                }

            }

            if (!cvemat) {

                con.guardaPlanCctMateria(escuela, materia);

                ListaPlanMateria = con.cctPlanMateria(escuela);

                materia.setCVE_MATERIA("");
                materia.setNOMBRE_MATERIA("");
                materia.setNUMERO_PERIODO("");

            } else {

                System.out.println("entre al mensaje de error");

                addFieldError("ya", "Ya se encuentra registrada la Materia");

            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String AgregarMateriaPlan() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());

            ListaPlanMateria = con.cctPlanMateria(escuela);
            ListaPeriodo = con.periodo(escuela);

            bnmateria = true;

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String consultaProgramas() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());
            programa.setID_SUBSISTEMA_CONSULTA(idSubSis());

            ListaProgramasEdu = con.programasEdu(programa);

            if (ListaProgramasEdu.size() > 0) {

                bnprog = true;
                bnprogno = false;
            } else {
                bnprog = false;
                bnprogno = true;

            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String agregarProgramaCct() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());

            mensaje.enviaMensajeConsola("ID DEL PLAN" + escuela.getAUXPLAN());
            mensaje.enviaMensajeConsola("ID DE LA ESCUELA" + escuela.getAUXESCUELA());

            ValidacctPlan = con.validaCctPlan(escuela);

            if (ValidacctPlan.size() > 0) {

            } else {

                escuela.setESTATUS("1");

                con.guardaPlanCct(escuela);

                RegPlanEstudioCct();

            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String eliminarProgramaCct() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());

            ValidacctPlan = con.validaCctPlanMateria(escuela);

            if (ValidacctPlan.size() > 0) {

                addFieldError("ErrorBorrar", "No se puede borrar el Programa de Estudio, borre las Materias vinculadas");
                System.out.println("no se puede borrar");
            } else {

                con.eliminarPlanCct(escuela);

                RegPlanEstudioCct();
            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String EliminaMateriaPlan() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            ValidaMateriaCompetencia = con.validaMateriaComp(materia);

            if (ValidaMateriaCompetencia.size() > 0) {

                addFieldError("ErrorBorrarMateria", "No se puede borrar la materia, borre las competencias vinculadas");
            } else {
                con.eliminarMateria(materia);

                ListaPlanMateria = con.cctPlanMateria(escuela);
            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String actualizarEstatusplaCct() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());

            mensaje.enviaMensajeConsola("ID DEL PLAN" + escuela.getAUXPLAN());
            mensaje.enviaMensajeConsola("ID DE LA ESCUELA" + escuela.getAUXESCUELA());

            String status = escuela.getAUXSTATUS();

            if (status.equals("1")) {

                escuela.setAUXSTATUS("0");

            }
            if (status.equals("0")) {

                escuela.setAUXSTATUS("1");

            }

            con.actualizaEstatusPlanCct(escuela);

            System.out.println("SALI DE LA ACTUALIZACIÓN DE ESTATUS");

            ListaProgramasEscuela = con.programasEscuela(escuela);

            escuela.setAUXPLAN("");
            escuela.setAUXSTATUS("");

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String RegMateriaPlanCompetencia() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());

            objRenapo.setAUXESCUELA(escuela.getID_ESCUELA());
            objRenapo.setID_PERSONA(usuariocons.getID_PERSONA());
            objRenapo.setAUXPERFIL(String.valueOf(usuariocons.getPERFIL()));

            ListaProgramasEscuela = con.programasEscuelaRes(escuela, objRenapo);

            escuela.setAUXPLAN("");
            escuela.setAUXSTATUS("");
            escuela.setAUXIDCCTPLAN("");
            programa.setCONSULTA_CVE_PLAN("");
            programa.setCVE_PLAN("");
            programa.setNOM_CARRERA("");
            bnmateria = false;
            ListaPlanMateria.clear();
            ListaPlanMateria.clear();
            materia.setAUXMATERIA("");
            ValidaMateriaCompetencia.clear();
            bncompetencia = false;
            competencia.setAUXCOMPETENCIA("");
            ListaActividadCct.clear();
            bnactividad = false;

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String RegProgramaDescarga() {
        UnidadEDAOImpl unidadD=new UnidadEDAOImpl();

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            nombreEsc=unidadD.ConsultaNombreCct(usuariocons.getID_ESCUELA());
           
            Constantes.enviaMensajeConsola("nombreEsc"+nombreEsc);
            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());

            objRenapo.setAUXESCUELA(escuela.getID_ESCUELA());
            objRenapo.setID_PERSONA(usuariocons.getID_PERSONA());
            objRenapo.setAUXPERFIL(String.valueOf(usuariocons.getPERFIL()));

            ListaProgramasRegistro = con.programasEscuelaDescarga(escuela, objRenapo);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String AgregarMateriaPlanCompetencia() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());

            ListaPlanMateria = con.cctPlanMateria(escuela);

            bnmateria = true;
            bncompetencia = false;

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String agregarPlanMateriaCom() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            bncompetencia = true;
            ValidaMateriaCompetencia.clear();

            ValidaMateriaCompetencia = con.validaMateriaComp(materia);

            competencia.setAUXCOMPETENCIA("");

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String guardaCompetenciaCct() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            escuela.setID_ESCUELA(idEsc());

            con.guardaCompetenciaCct(competencia, materia);

            ValidaMateriaCompetencia = con.validaMateriaComp(materia);
            competencia.setCOMPETENCIA("");

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String actualizaCompetenciaCct() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            System.out.println("COMPETENCIA ID:" + competencia.getAUXCOMPETENCIA());

            con.actualizaCompetenciaCct(competencia);

            competencia.setAUXCOMPETENCIA("");
            competencia.setCOMPETENCIA("");

            ValidaMateriaCompetencia = con.validaMateriaComp(materia);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String eliminarCompetenciaCct() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            /*  ValidacctPlan=con.validaCctPlanMateria(escuela);
           
          if(ValidacctPlan.size()>0){
             
               addFieldError("ErrorBorrar", "No se puede borrar el Programa de Estudio, borre las Materias vinculadas");
              System.out.println("no se puede borrar");
          }
          else{
              
              
             con.eliminarPlanCct(escuela);  
              
                RegPlanEstudioCct();
          }
             */
            con.eliminarCompetenciaCct(competencia);
            ValidaMateriaCompetencia.clear();
            ValidaMateriaCompetencia = con.validaMateriaComp(materia);
            competencia.setAUXCOMPETENCIA("");

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String agregarActividad() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            bnactividad = true;

            ListaActividadCct = con.actividadCct(competencia);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String guardaActividad() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            con.guardaActividad(competencia, actividad);
            actividad.setACTIVIDAD("");

            ListaActividadCct = con.actividadCct(competencia);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String actualizaActividad() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            con.actualizaActividad(actividad);

            actividad.setAUXACTIVIDAD("");
            actividad.setACTIVIDAD("");

            ListaActividadCct = con.actividadCct(competencia);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String eliminaActividad() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            /*  ValidacctPlan=con.validaCctPlanMateria(escuela);
           
          if(ValidacctPlan.size()>0){
             
               addFieldError("ErrorBorrar", "No se puede borrar el Programa de Estudio, borre las Materias vinculadas");
              System.out.println("no se puede borrar");
          }
          else{
              
              
             con.eliminarPlanCct(escuela);  
              
                RegPlanEstudioCct();
          }
             */
            con.eliminaActividad(actividad);
            ListaActividadCct.clear();
            ListaActividadCct = con.actividadCct(competencia);
            actividad.setAUXACTIVIDAD("");

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String DescargaManual() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public boolean isBncompetencia() {
        return bncompetencia;
    }

    //GET Y SET
    public void setBncompetencia(boolean bncompetencia) {
        this.bncompetencia = bncompetencia;
    }

    public usuarioBean getUsuariocons() {
        return usuariocons;
    }

    public void setUsuariocons(usuarioBean usuariocons) {
        this.usuariocons = usuariocons;
    }

    public String getCveusuario() {
        return cveusuario;
    }

    public void setCveusuario(String cveusuario) {
        this.cveusuario = cveusuario;
    }

    public String getPasusuario() {
        return pasusuario;
    }

    public void setPasusuario(String pasusuario) {
        this.pasusuario = pasusuario;
    }

    public String getNomModulo() {
        return nomModulo;
    }

    public void setNomModulo(String nomModulo) {
        this.nomModulo = nomModulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTabSelect() {
        return tabSelect;
    }

    public void setTabSelect(String tabSelect) {
        this.tabSelect = tabSelect;
    }

    public List<moduloBean> getModulosAUX() {
        return modulosAUX;
    }

    public void setModulosAUX(List<moduloBean> modulosAUX) {
        this.modulosAUX = modulosAUX;
    }

    public List<moduloAuxBean> getModulosAUXP() {
        return modulosAUXP;
    }

    public void setModulosAUXP(List<moduloAuxBean> modulosAUXP) {
        this.modulosAUXP = modulosAUXP;
    }

    public programaEsBean getPrograma() {
        return programa;
    }

    public void setPrograma(programaEsBean programa) {
        this.programa = programa;
    }

    public List<programaEsBean> getListaProgramasEdu() {
        return ListaProgramasEdu;
    }

    public void setListaProgramasEdu(List<programaEsBean> ListaProgramasEdu) {
        this.ListaProgramasEdu = ListaProgramasEdu;
    }

    public boolean isBnprog() {
        return bnprog;
    }

    public void setBnprog(boolean bnprog) {
        this.bnprog = bnprog;
    }

    public String getTipoError() {
        return TipoError;
    }

    public void setTipoError(String TipoError) {
        this.TipoError = TipoError;
    }

    public String getTipoException() {
        return TipoException;
    }

    public void setTipoException(String TipoException) {
        this.TipoException = TipoException;
    }

    public boolean isBnprogno() {
        return bnprogno;
    }

    public void setBnprogno(boolean bnprogno) {
        this.bnprogno = bnprogno;
    }

    public List<programaEsBean> getListaProgramasEscuela() {
        return ListaProgramasEscuela;
    }

    public void setListaProgramasEscuela(List<programaEsBean> ListaProgramasEscuela) {
        this.ListaProgramasEscuela = ListaProgramasEscuela;
    }

    public escuelaBean getEscuela() {
        return escuela;
    }

    public void setEscuela(escuelaBean escuela) {
        this.escuela = escuela;
    }

    public List<escuelaBean> getListaIdEs() {
        return ListaIdEs;
    }

    public void setListaIdEs(List<escuelaBean> ListaIdEs) {
        this.ListaIdEs = ListaIdEs;
    }

    public Constantes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Constantes mensaje) {
        this.mensaje = mensaje;
    }

    public List<cctPlanBean> getValidacctPlan() {
        return ValidacctPlan;
    }

    public void setValidacctPlan(List<cctPlanBean> ValidacctPlan) {
        this.ValidacctPlan = ValidacctPlan;
    }

    public cctPlanBean getCctplan() {
        return cctplan;
    }

    public void setCctplan(cctPlanBean cctplan) {
        this.cctplan = cctplan;
    }

    public List<cctPlanBean> getValidaPlanCctMateria() {
        return ValidaPlanCctMateria;
    }

    public void setValidaPlanCctMateria(List<cctPlanBean> ValidaPlanCctMateria) {
        this.ValidaPlanCctMateria = ValidaPlanCctMateria;
    }

    public List<materiaBean> getListaPlanMateria() {
        return ListaPlanMateria;
    }

    public void setListaPlanMateria(List<materiaBean> ListaPlanMateria) {
        this.ListaPlanMateria = ListaPlanMateria;
    }

    public materiaBean getMateria() {
        return materia;
    }

    public void setMateria(materiaBean materia) {
        this.materia = materia;
    }

    public boolean isBnmateria() {
        return bnmateria;
    }

    public void setBnmateria(boolean bnmateria) {
        this.bnmateria = bnmateria;
    }

    public List<competenciaBean> getValidaMateriaCompetencia() {
        return ValidaMateriaCompetencia;
    }

    public void setValidaMateriaCompetencia(List<competenciaBean> ValidaMateriaCompetencia) {
        this.ValidaMateriaCompetencia = ValidaMateriaCompetencia;
    }

    public competenciaBean getCompetencia() {
        return competencia;
    }

    public void setCompetencia(competenciaBean competencia) {
        this.competencia = competencia;
    }

    public boolean isBnactividad() {
        return bnactividad;
    }

    public void setBnactividad(boolean bnactividad) {
        this.bnactividad = bnactividad;
    }

    public List<actividadesBean> getListaActividadCct() {
        return ListaActividadCct;
    }

    public void setListaActividadCct(List<actividadesBean> ListaActividadCct) {
        this.ListaActividadCct = ListaActividadCct;
    }

    public actividadesBean getActividad() {
        return actividad;
    }

    public void setActividad(actividadesBean actividad) {
        this.actividad = actividad;
    }

    public List<periodoBean> getListaPeriodo() {
        return ListaPeriodo;
    }

    public void setListaPeriodo(List<periodoBean> ListaPeriodo) {
        this.ListaPeriodo = ListaPeriodo;
    }

    public renapoBean getObjRenapo() {
        return objRenapo;
    }

    public void setObjRenapo(renapoBean objRenapo) {
        this.objRenapo = objRenapo;
    }

    public List<programaEsBean> getListaProgramasRegistro() {
        return ListaProgramasRegistro;
    }

    public void setListaProgramasRegistro(List<programaEsBean> ListaProgramasRegistro) {
        this.ListaProgramasRegistro = ListaProgramasRegistro;
    }

    public String getNombreEsc() {
        return nombreEsc;
    }

    public void setNombreEsc(String nombreEsc) {
        this.nombreEsc = nombreEsc;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

}
