/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import beans.AlumnoBean;
import beans.CatalogoBean;
import beans.ColoniasBean;
import beans.PlanFormacionBean;
import beans.UnidadesEconomicasBean;
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

import daos.UnidadesEconomicasDAOImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import utilidades.Constantes;

import daos.AlumnoDAOImpl;
import daos.PlanFDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import mx.gob.edomex.dgsei.ws.ConsultaRenapoPorCurp;
import mx.gob.edomex.dgsei.ws.ConsultaDatosRenapo;
import mx.gob.edomex.dgsei.ws.PersonasDTO;
import org.apache.poi.hssf.record.PageBreakRecord;

/**
 *
 * @author pedro
 */
public class PlanFormacion_Action extends ActionSupport implements SessionAware {

    //instancias para web service//
    ConsultaDatosRenapo service = null;
    ConsultaRenapoPorCurp port;
    PersonasDTO personas;

    private usuarioBean usuariocons;
    private String cveusuario;
    private String pasusuario;
    private String nomModulo;
    private String modulo;
    private String nombreUsuario;
    private String tabSelect;

    public List<moduloBean> modulosAUX = new ArrayList<moduloBean>();
    public List<moduloAuxBean> modulosAUXP = new ArrayList<moduloAuxBean>();
    private List<PlanFormacionBean> ListaPE = new ArrayList<PlanFormacionBean>();
    public List<programaEsBean> ListaProgramasRegistro = new ArrayList<programaEsBean>();
    public List<periodoBean> ListaPeriodo = new ArrayList<periodoBean>();
    public List< UnidadesEconomicasBean> ListaUE = new ArrayList< UnidadesEconomicasBean>();
    public List<programaEsBean> ListaEscala = new ArrayList<programaEsBean>();
    public List<programaEsBean> ListaLugar = new ArrayList<programaEsBean>();
    public List<programaEsBean> ListaHora = new ArrayList<programaEsBean>();
     public List<AlumnoBean> ListaEstudiantes = new ArrayList<AlumnoBean>();

    private String TipoError;
    private String TipoException;

    escuelaBean escuela = new escuelaBean();
    PlanFormacionBean pf = new PlanFormacionBean();

    programaEsBean programa = new programaEsBean();

    Constantes mensaje = new Constantes();
    cctPlanBean cctplan = new cctPlanBean();
    materiaBean materia = new materiaBean();
    competenciaBean competencia = new competenciaBean();
    actividadesBean actividad = new actividadesBean();
    renapoBean objRenapo = new renapoBean();
    AlumnoBean alumno=new AlumnoBean();

    boolean mensajeSnAct = false;

    //conexiones................................PARA LAS LISTAS
    Statement objConexion;
    PreparedStatement objPreConexion;
    Connection conecta;
    //******************** PARA OBJETO DE NAVEGACIoN ***********************************************
    private Map session;

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }
    //**************************************************INTERESADOS****************************************************

    public String FormPlan() {

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

            AlumnoDAOImpl con = new AlumnoDAOImpl();
            PlanFDAOImpl con2 = new PlanFDAOImpl();
            ConsultasBusiness con3 = new ConsultasBusiness();

            pf.setID_ESCUELA(con.ConsultaID(usuariocons.getUSUARIO()));
            pf.setID_PERSONA(usuariocons.getID_PERSONA());
            pf.setID_PERFIL(String.valueOf(usuariocons.getPERFIL()));
            pf.setID_CICLO(con.ConsultaCICLO());
            escuela.setAUXIDCCTPLAN("");

            ListaPE.clear();
            ListaPeriodo.clear();

            ListaPE = con2.ConsultaPE(pf);

            ListaPeriodo = con2.periodo(escuela);

            ListaProgramasRegistro.clear();

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String planConsulta() {

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

            PlanFDAOImpl con2 = new PlanFDAOImpl();

            ListaPeriodo = con2.periodo(escuela);

            String cctconsulta = escuela.getAUXIDCCTPLAN();

            String[] palabras = cctconsulta.split("-");
            String part1 = palabras[0]; //Clave del Plan CCT
            String part2 = palabras[1];

            escuela.setAUXIDCCTPLAN2(part1);
            escuela.setPERIODO_INICIO(part2);

            ListaUE = con2.listaUE(escuela, usuariocons);

            ListaEscala = con2.listaEscala(escuela, usuariocons);
            ListaLugar = con2.listaLugar(escuela, usuariocons);
            ListaHora = con2.listaHora(escuela, usuariocons);

            ListaProgramasRegistro = con2.programasEscuelaDescarga(escuela, objRenapo);

            if (ListaProgramasRegistro.size() == 0) {

                mensajeSnAct = true;
            }

            Iterator LPR = ListaProgramasRegistro.iterator();
            programaEsBean obj2;

            while (LPR.hasNext()) {
                obj2 = (programaEsBean) LPR.next();

                obj2.setVALIDAR("true");

            }

            String nivel = "";

            nivel = "";
            nivel = con2.nivel(escuela, usuariocons);

            programa.setID_NIVEL(nivel);

            System.out.println("NIVEL EDUCATIVO" + programa.getID_NIVEL());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
    
     public String ConsultaPlanAlumUE() {

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

            PlanFDAOImpl con2 = new PlanFDAOImpl();

          ListaEstudiantes=con2.consultaAlumPlan(alumno, usuariocons, escuela);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String guardaPlanForm() {

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

            PlanFDAOImpl con2 = new PlanFDAOImpl();

            String cctconsulta = escuela.getAUXIDCCTPLAN();

            String[] palabras = cctconsulta.split("-");
            String part1 = palabras[0]; //Clave del Plan CCT
            String part2 = palabras[1];

            escuela.setAUXIDCCTPLAN2(part1);
            escuela.setPERIODO_INICIO(part2);

            boolean sec1 = false;
            boolean sec2 = false;
            boolean valDat = false;
            boolean sumaEscala = false;
            int auxActividades = 0;

            if (escuela.getAUXIDCCTPLAN().length() > 0 && programa.getNOM_PLAN_FORM().length() > 0 && programa.getDESCRIPCION_FORM().length() > 0 && programa.getPERIODO().length() > 0 && programa.getNO_ESTUDIANTES().length() > 0 && programa.getNO_MENTORES_UE().length() > 0 && programa.getNO_MENTORES_ACAD().length() > 0 && escuela.getID_IE_UE().length() > 0) {

                sec1 = true;

            } else {

                sec1 = false;

                if (escuela.getAUXIDCCTPLAN2().length() == 0) {
                    addFieldError("ERRORCCTPLA", "Debes elegir un Programa");
                }

                if (programa.getNOM_PLAN_FORM().length() == 0) {
                    addFieldError("ERRORNOMPLAN", "Registrar nombre de Plan");

                }
                if (programa.getDESCRIPCION_FORM().length() == 0) {
                    addFieldError("ERRORDESPLAN", "Debes registrar una descripción");

                }
                if (programa.getPERIODO().length() == 0) {
                    addFieldError("ERRORPERPLAN", "Debes registrar una duración");

                }

                if (programa.getNO_ESTUDIANTES().length() == 0) {
                    addFieldError("ERRORNOEST", "Debes registrar un número");

                }
                if (programa.getNO_MENTORES_UE().length() == 0) {
                    addFieldError("ERRORNOMENUE", "Debes registrar un número");

                }
                if (programa.getNO_MENTORES_ACAD().length() == 0) {
                    addFieldError("ERRORNOMENACAD", "Debes registrar un número");

                }

                if (escuela.getID_IE_UE().length() == 0) {
                    addFieldError("ERROREUE", "Debes elegir una Unidad Económica");

                }

            }

            Iterator LP = ListaProgramasRegistro.iterator();

            programaEsBean obj1;
            boolean horas = false;
            boolean lugar = false;
            boolean escala = false;
            boolean desact = false;
            boolean planrot = false;

            int errores = 0;
            int correctos = 0;
            int escalaValida = 0;
            int errorEscala = 0;
            int validosEscala = 0;

            while (LP.hasNext()) {
                obj1 = (programaEsBean) LP.next();

                if (obj1.getVALIDAR().equals("true") && obj1.getVALIDAR() != null) {
                    auxActividades = auxActividades + 1;

                    if (obj1.getHORAS_PLAN().length() > 0) {
                        horas = true;
                    }

                    if (obj1.getLUGAR_PLAN().length() > 0) {
                        lugar = true;
                    }
                    if (obj1.getESCALA_PLAN().length() > 0) {
                        escala = true;
                    }
                    if (obj1.getDES_ACTIVIDAD().length() > 0) {
                        desact = true;
                    }

                    // validación de campos de la lista     
                    if (programa.getID_NIVEL().equals("1")) {

                        if (obj1.getPLAN_ROTACION().length() > 0) {
                            planrot = true;
                        }

                        if (horas && escala && lugar && desact && planrot) {

                            int valorAux = 0;

                            valorAux = Integer.parseInt(obj1.getESCALA_PLAN());

                            int sumacompetecnia = 0;
                            int competencia_consulta = 0;
                          

                            for (int i = 0; i < ListaProgramasRegistro.size(); i++) {

                                if (obj1.getID_COMPETENCIA().equals(ListaProgramasRegistro.get(i).getID_COMPETENCIA()) && obj1.getVALIDAR().equals("true") && obj1.getVALIDAR() != null) {

                                    if (ListaProgramasRegistro.get(i).getESCALA_PLAN().length() > 0) {
                                        competencia_consulta = competencia_consulta + Integer.parseInt(ListaProgramasRegistro.get(i).getESCALA_PLAN());
                                    }
                                  
                                }
                               

                            }
                            
                             System.out.println("competencia_consulta:"+competencia_consulta);
                                
                                    if(competencia_consulta!=100){
                                           errorEscala=errorEscala+1;
                                    }


                            System.out.println("errorEscala:"+errorEscala);
                          

                            correctos = correctos + 1;

                        } else {
                            obj1.setNO_PASA(obj1.getID_ACTIVIDAD());

                            errores = errores + 1;

                        }

                    }

                    if (programa.getID_NIVEL().equals("2")) {

                        if (horas && escala && lugar && desact) {

                            int valorAux = 0;

                            valorAux = Integer.parseInt(obj1.getESCALA_PLAN());

                            int sumacompetecnia = 0;
                            int competencia_consulta = 0;
                           

                            for (int i = 0; i < ListaProgramasRegistro.size(); i++) {

                                if (obj1.getID_COMPETENCIA().equals(ListaProgramasRegistro.get(i).getID_COMPETENCIA()) && obj1.getVALIDAR().equals("true") && obj1.getVALIDAR() != null) {

                                    competencia_consulta = competencia_consulta + Integer.parseInt(ListaProgramasRegistro.get(i).getESCALA_PLAN());
                                   
                                }
                            }
                            
                            System.out.println("competencia_consulta:"+competencia_consulta);
                                
                                    if(competencia_consulta!=100){
                                           errorEscala=errorEscala+1;
                                    }


                            System.out.println("errorEscala:"+errorEscala);
                          
                          

                          

                            correctos = correctos + 1;

                        } else {
                            obj1.setNO_PASA(obj1.getID_ACTIVIDAD());

                            errores = errores + 1;

                        }

                    }

                    horas = false;
                    lugar = false;
                    escala = false;
                    desact = false;
                    planrot = false;
                    
                    

                }
                
                
                

            }

            if (errores > 0) {

                addFieldError("ERRORFORM", "Debes capturar todos los campos de las actividades seleccionadas");

            }

            if (errores == 0 && correctos > 0) {

                valDat = true;

            }


            if (errorEscala > 0) {
                sumaEscala = false;
                if (valDat) {
                    addFieldError("ERRORTOTALESCALA", "Las Actividades de una o más competencias no suman 100%");
                }
            } else {

                sumaEscala = true;
            }

         
            
            if (auxActividades >= 1) {

                sec2 = true;
                System.out.println("Sección 2 aprobada");
            } else {

                addFieldError("ERRORACTPLAN", "Debes elegir almenos una Actividad");
                sec2 = false;
            }

            if (sec1 && sec2 && valDat && sumaEscala) {

                String claveContruida = "";

                claveContruida = construyeClave(escuela.getAUXIDCCTPLAN2());

                conecta = con2.crearConexion();
                //statement
                objConexion = con2.crearStatement(conecta);

                con2.GuardaPlanForm(conecta, objPreConexion, escuela, objRenapo, claveContruida, programa);

                Iterator LPR = ListaProgramasRegistro.iterator();
                programaEsBean obj3;
                while (LPR.hasNext()) {
                    obj3 = (programaEsBean) LPR.next();
                    programa.setID_MATERIA(obj3.getID_MATERIA());
                    programa.setID_COMPETENCIA(obj3.getID_COMPETENCIA());
                    programa.setID_ACTIVIDAD(obj3.getID_ACTIVIDAD());
                    programa.setHORAS_PLAN(obj3.getHORAS_PLAN());
                    programa.setLUGAR_PLAN(obj3.getLUGAR_PLAN());
                    programa.setESCALA_PLAN(obj3.getESCALA_PLAN());
                    programa.setDES_ACTIVIDAD(obj3.getDES_ACTIVIDAD());
                    programa.setPLAN_ROTACION(obj3.getPLAN_ROTACION());
                    if (obj3.getVALIDAR().equals("true")) {

                        con2.GuardaPlanFormaActividades(conecta, objPreConexion, escuela, objRenapo, claveContruida, programa);
                    }

                }

                cierraConexiones();

                addFieldError("SEGUARDO", "El Plan de Formación se guardo con éxito");

                FormPlan();

            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String construyeClave(String cctplan) throws Exception {

        PlanFDAOImpl con2 = new PlanFDAOImpl();

        int Totalplan = 0;
        String TotalplanString = "";

        TotalplanString = con2.totalcctplan(escuela, objRenapo);

        Totalplan = Integer.parseInt(TotalplanString) + 1;

        TotalplanString = cctplan + '-' + String.valueOf(Totalplan);

        return TotalplanString;

    }

    //********************CONEXION PARA LSITAS
    private void cierraConexiones() {
        try {
            objConexion.close();
//objPreConexion.close();
            conecta.close();

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error al cerrar conexiones: " + e);
        }
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

    public List<PlanFormacionBean> getListaPE() {
        return ListaPE;
    }

    public void setListaPE(List<PlanFormacionBean> ListaPE) {
        this.ListaPE = ListaPE;
    }

    public escuelaBean getEscuela() {
        return escuela;
    }

    public void setEscuela(escuelaBean escuela) {
        this.escuela = escuela;
    }

    public PlanFormacionBean getPf() {
        return pf;
    }

    public void setPf(PlanFormacionBean pf) {
        this.pf = pf;
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

    public ConsultaDatosRenapo getService() {
        return service;
    }

    public void setService(ConsultaDatosRenapo service) {
        this.service = service;
    }

    public ConsultaRenapoPorCurp getPort() {
        return port;
    }

    public void setPort(ConsultaRenapoPorCurp port) {
        this.port = port;
    }

    public PersonasDTO getPersonas() {
        return personas;
    }

    public void setPersonas(PersonasDTO personas) {
        this.personas = personas;
    }

    public List<programaEsBean> getListaProgramasRegistro() {
        return ListaProgramasRegistro;
    }

    public void setListaProgramasRegistro(List<programaEsBean> ListaProgramasRegistro) {
        this.ListaProgramasRegistro = ListaProgramasRegistro;
    }

    public Statement getObjConexion() {
        return objConexion;
    }

    public void setObjConexion(Statement objConexion) {
        this.objConexion = objConexion;
    }

    public PreparedStatement getObjPreConexion() {
        return objPreConexion;
    }

    public void setObjPreConexion(PreparedStatement objPreConexion) {
        this.objPreConexion = objPreConexion;
    }

    public Connection getConecta() {
        return conecta;
    }

    public void setConecta(Connection conecta) {
        this.conecta = conecta;
    }

    public programaEsBean getPrograma() {
        return programa;
    }

    public void setPrograma(programaEsBean programa) {
        this.programa = programa;
    }

    public Constantes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Constantes mensaje) {
        this.mensaje = mensaje;
    }

    public cctPlanBean getCctplan() {
        return cctplan;
    }

    public void setCctplan(cctPlanBean cctplan) {
        this.cctplan = cctplan;
    }

    public materiaBean getMateria() {
        return materia;
    }

    public void setMateria(materiaBean materia) {
        this.materia = materia;
    }

    public competenciaBean getCompetencia() {
        return competencia;
    }

    public void setCompetencia(competenciaBean competencia) {
        this.competencia = competencia;
    }

    public actividadesBean getActividad() {
        return actividad;
    }

    public void setActividad(actividadesBean actividad) {
        this.actividad = actividad;
    }

    public renapoBean getObjRenapo() {
        return objRenapo;
    }

    public void setObjRenapo(renapoBean objRenapo) {
        this.objRenapo = objRenapo;
    }

    public List<periodoBean> getListaPeriodo() {
        return ListaPeriodo;
    }

    public void setListaPeriodo(List<periodoBean> ListaPeriodo) {
        this.ListaPeriodo = ListaPeriodo;
    }

    public boolean isMensajeSnAct() {
        return mensajeSnAct;
    }

    public void setMensajeSnAct(boolean mensajeSnAct) {
        this.mensajeSnAct = mensajeSnAct;
    }

    public List<UnidadesEconomicasBean> getListaUE() {
        return ListaUE;
    }

    public void setListaUE(List<UnidadesEconomicasBean> ListaUE) {
        this.ListaUE = ListaUE;
    }

    public List<programaEsBean> getListaEscala() {
        return ListaEscala;
    }

    public void setListaEscala(List<programaEsBean> ListaEscala) {
        this.ListaEscala = ListaEscala;
    }

    public List<programaEsBean> getListaLugar() {
        return ListaLugar;
    }

    public void setListaLugar(List<programaEsBean> ListaLugar) {
        this.ListaLugar = ListaLugar;
    }

    public List<programaEsBean> getListaHora() {
        return ListaHora;
    }

    public void setListaHora(List<programaEsBean> ListaHora) {
        this.ListaHora = ListaHora;
    }

    public List<AlumnoBean> getListaEstudiantes() {
        return ListaEstudiantes;
    }

    public void setListaEstudiantes(List<AlumnoBean> ListaEstudiantes) {
        this.ListaEstudiantes = ListaEstudiantes;
    }

    public AlumnoBean getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoBean alumno) {
        this.alumno = alumno;
    }
    
    

}
