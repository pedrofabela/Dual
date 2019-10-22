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
import beans.responsablesBean;
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
import java.text.ParseException;
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
      public List<responsablesBean> ListaResUE = new ArrayList<responsablesBean>();
      public List<responsablesBean> ListaResMentorUE = new ArrayList<responsablesBean>();
      public List<responsablesBean> ListaResAcad = new ArrayList<responsablesBean>();
      public List<responsablesBean> ListaMentorAcad = new ArrayList<responsablesBean>();
     
      private List<programaEsBean> ListaPlanUE = new ArrayList<programaEsBean>();
      
      public List<programaEsBean> ListaProgramasRegistroDatos = new ArrayList<programaEsBean>();

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
    UnidadesEconomicasBean unidad=new UnidadesEconomicasBean();
    responsablesBean responsables=new responsablesBean();

    boolean mensajeSnAct = false;
     boolean banListaAlu = false;
     boolean banCampAlumno = false;

     
     
     
     
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

    public boolean isBanListaAlu() {
        return banListaAlu;
    }

    public void setBanListaAlu(boolean banListaAlu) {
        this.banListaAlu = banListaAlu;
    }

    public UnidadesEconomicasBean getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadesEconomicasBean unidad) {
        this.unidad = unidad;
    }

    public List<responsablesBean> getListaResUE() {
        return ListaResUE;
    }

    public void setListaResUE(List<responsablesBean> ListaResUE) {
        this.ListaResUE = ListaResUE;
    }

    public responsablesBean getResponsables() {
        return responsables;
    }

    public void setResponsables(responsablesBean responsables) {
        this.responsables = responsables;
    }

    public List<responsablesBean> getListaResMentorUE() {
        return ListaResMentorUE;
    }

    public void setListaResMentorUE(List<responsablesBean> ListaResMentorUE) {
        this.ListaResMentorUE = ListaResMentorUE;
    }

    public List<responsablesBean> getListaResAcad() {
        return ListaResAcad;
    }

    public void setListaResAcad(List<responsablesBean> ListaResAcad) {
        this.ListaResAcad = ListaResAcad;
    }

    public List<responsablesBean> getListaMentorAcad() {
        return ListaMentorAcad;
    }

    public void setListaMentorAcad(List<responsablesBean> ListaMentorAcad) {
        this.ListaMentorAcad = ListaMentorAcad;
    }

    public List<programaEsBean> getListaPlanUE() {
        return ListaPlanUE;
    }

    public void setListaPlanUE(List<programaEsBean> ListaPlanUE) {
        this.ListaPlanUE = ListaPlanUE;
    }

    public List<programaEsBean> getListaProgramasRegistroDatos() {
        return ListaProgramasRegistroDatos;
    }

    public void setListaProgramasRegistroDatos(List<programaEsBean> ListaProgramasRegistroDatos) {
        this.ListaProgramasRegistroDatos = ListaProgramasRegistroDatos;
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
            ListaEstudiantes.size();
            

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
          
          
          if(ListaEstudiantes.size()==0){
               addFieldError("ERRORNOALUMNO", "No hay alumnos registrados ó no se completado el registro de los mismos");
              
          }
          
          else if(ListaEstudiantes.size()>0){
              
              banListaAlu=true;
              banCampAlumno=false;
          }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

      public String eligeAlumno() {

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

        
              
              banListaAlu=false;
              banCampAlumno=true;
              System.out.println("action.PlanFormacion_Action.eligeAlumno()");
              
              System.out.println("el valor a consultar es "+alumno.getAUXIDHISTALUM());
              
          ListaEstudiantes=con2.consultaAlumPlanHit(alumno, usuariocons, escuela);
          
            for (int i = 0; i < ListaEstudiantes.size(); i++) {
                
                alumno.setCURP(ListaEstudiantes.get(i).getCURP());
                 alumno.setNOMBRE(ListaEstudiantes.get(i).getNOMBRE());
                alumno.setMATRICULA(ListaEstudiantes.get(i).getMATRICULA());
                 alumno.setID_UE(ListaEstudiantes.get(i).getID_UE());
                 alumno.setID_PLAN(ListaEstudiantes.get(i).getID_PLAN());
                
            }
            
            
            
            ListaUE=con2.consultaUE(alumno, usuariocons, escuela);
            
            System.out.println("unidad"+ListaUE.size());
            for (int j = 0; j < ListaUE.size(); j++) {
                unidad.setRFC(ListaUE.get(j).getRFC());
                 unidad.setRAZON_SOCIAL(ListaUE.get(j).getRAZON_SOCIAL());
            }
            
            int perfil=0;
            
            perfil=26;
            ListaResUE=con2.listaResUE(alumno, usuariocons, escuela, perfil);
            
            
            
             perfil=28;
            ListaResMentorUE=con2.listaResUE(alumno, usuariocons, escuela, perfil);
            
            
            
            ListaResAcad=con2.listaResAcad(alumno, usuariocons, escuela, perfil);
            
            
            
            
            ListaMentorAcad=con2.listaMentorAcad(alumno, usuariocons, escuela, perfil);
            
            
            
            
            ListaPlanUE=con2.listaPlanUE(alumno, usuariocons, escuela, perfil);
            
            
            
            
           
          return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
      
      
              
       public String consultaPlanEstudiante2() {

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

           
              String nivel = "";

            nivel = "";
            nivel = con2.nivel(escuela, usuariocons);
             
           //   Constantes.enviaMensajeConsola("sentre a la consulta de programa educativo");
             
            ListaProgramasRegistro=con2.programasPlanForm(alumno, escuela,  usuariocons, programa );
            
            banCampAlumno=true;
            
            ListaProgramasRegistroDatos=con2.programasPlanFormDatos(alumno, escuela,  usuariocons, programa );
            
            Constantes.enviaMensajeConsola("sali de registro datos");
            
            for (int i = 0; i < ListaProgramasRegistroDatos.size(); i++) {
                
                programa.setNOMBREPLAN_FORM(ListaProgramasRegistroDatos.get(i).getNOMBREPLAN_FORM());
                  programa.setNO_ESTUDIANTES(ListaProgramasRegistroDatos.get(i).getNO_ESTUDIANTES());
                  programa.setNO_MENTORES_UE(ListaProgramasRegistroDatos.get(i).getNO_MENTORES_UE());
                   programa.setNO_MENTORES_ACAD(ListaProgramasRegistroDatos.get(i).getNO_MENTORES_ACAD());
                    programa.setDURACION(ListaProgramasRegistroDatos.get(i).getDURACION());
                     programa.setDESCRIPCION(ListaProgramasRegistroDatos.get(i).getDESCRIPCION());
                
                
                
                
            }
            
            
            
            
            
            
            
        //  Constantes.enviaMensajeConsola("sali de la consulta de programa educativo con:"+ListaProgramasRegistro.size());
            
            
           
          return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }         
              
    public static boolean validarFecha(String fecha) {

        try {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

            formatoFecha.setLenient(false);

            formatoFecha.parse(fecha);

        } catch (ParseException e) {

            return false;

        }

        return true;

    }

       
              
    public String guardaPlanFormEst() {

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

           
             boolean banResUE=false;
             boolean banMentorUE=false;
             boolean banResAcad=false;
             boolean banMetorAcad=false;
             boolean planForm=false;
             boolean fechaInicio=false;
             boolean fechaTermino=false;
             String fecha;
                  boolean res=false;
                     boolean res2=false;
                          
             if(programa.getID_RESUE().length()>0){
                 banResUE=true;
                 
             }
             else{
                 
                 banResUE=false;
                   addFieldError("ERRORRESUE", "Favor de seleccionar a un Responsble");
             }
             
             
            
               if(programa.getID_MENTORUE().length()>0){
                 banMentorUE=true;
                 
             }
             else{
                 
                 banMentorUE=false;
                   addFieldError("ERRORMENTORUE", "Favor de seleccionar a un Mentor");
             }
               
            
              if(programa.getID_RESACAD().length()>0){
                 banResAcad=true;
                 
             }
             else{
                 
                 banResAcad=false;
                   addFieldError("ERRORRESACAD", "Favor de seleccionar a un Responsable");
             }
              
                if(programa.getID_MENTORACAD().length()>0){
                 banMetorAcad=true;
                 
             }
             else{
                 
                 banMetorAcad=false;
                   addFieldError("ERRORMENTORACAD", "Favor de seleccionar a un Mentor");
             }
                
                 
              res=validarFecha(programa.getFECHA_REG_PLAN());
            
              if(res==true){
                
                  
                  
                  
                  fechaInicio=true;
                 
             }
             else{
                 
                 fechaInicio=false;
                   addFieldError("ERRORFECHAINICIO", "Favor de seleccionar una fecha valida");
             }
              
                if(programa.getID_PLAN_FORMA().length()>0){
                 
                    
                    planForm=true;
                 
             }
             else{
                 
                 planForm=false;
                   addFieldError("ERRORPLANFORM", "Favor de seleccionar un plan de Formación");
             }
                   res2=validarFecha(programa.getFECHA_TERMINO_PLAN());
                
                  if(res2==true){
                 
                    
                    fechaTermino=true;
                 
             }
             else{
                 
                 fechaTermino=false;
                   addFieldError("ERRORFECHATERMINO", "Favor de seleccionar una fecha valida");
             }
                
               
                  
                  
                  
                  
                   if (banResUE && banMentorUE && banResAcad && banMetorAcad && planForm && fechaInicio && fechaTermino) {

                conecta = con2.crearConexion();
                //statement
                objConexion = con2.crearStatement(conecta);
                
                
                        con2.GuardaPlanFormEst(conecta, objPreConexion, escuela, objRenapo, programa, alumno);
                       
                       
                        
                        
                        
                        
                        
                       
                       
               

             

            

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
                    programa.setID_ESCALA(obj3.getID_ESCALA());
                     programa.setID_LUGAR(obj3.getID_LUGAR());
                      programa.setID_HORA(obj3.getID_HORA());

                        con2.GuardaPlanFormaActividadesAlu(conecta, objPreConexion, escuela, objRenapo,  programa, alumno);
                   

                }
                
                con2.ActualizaEstatusAlumno(conecta, objPreConexion, programa, alumno);

                cierraConexiones();

                addFieldError("SEGUARDO", "El Plan de Formación se guardo con éxito");
                
            ListaProgramasRegistro.clear();
            ListaEstudiantes.size();
                                                                                banCampAlumno=false;
                                                                                banCampAlumno=false;

               

            } 
                  
                  
                  
                  
                
                
                
                
              
               Constantes.enviaMensajeConsola(alumno.getAUXIDHISTALUM());
              
            
        //  Constantes.enviaMensajeConsola("sali de la consulta de programa educativo con:"+ListaProgramasRegistro.size());
            
            
           
          return "SUCCESS";

        } catch (Exception sqle) {

            TipoException = sqle.getMessage();
            
            System.out.println("action.PlanFormacion_Action.guardaPlanFormEst()"+TipoException
            );
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

    public boolean isBanCampAlumno() {
        return banCampAlumno;
    }

    public void setBanCampAlumno(boolean banCampAlumno) {
        this.banCampAlumno = banCampAlumno;
    }
    
    

}
