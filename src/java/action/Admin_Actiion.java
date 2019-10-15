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
import beans.subsistemaBean;
import beans.tipoPeriodoBean;
import beans.usuarioBean;
import business.ConsultasBusiness;
import com.opensymphony.xwork2.ActionSupport;
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
public class Admin_Actiion extends ActionSupport implements SessionAware {
    
     private usuarioBean usuariocons;
    private String cveusuario;
    private String pasusuario;
    private String nomModulo;
    private String modulo;
    private String nombreUsuario;
    private String tabSelect;
    
    
    public List<moduloBean> modulosAUX = new ArrayList<moduloBean>();
    public List<moduloAuxBean> modulosAUXP = new ArrayList<moduloAuxBean>();
    
     public List<subsistemaBean> ListSubsistema = new ArrayList<subsistemaBean>();
      public List<programaEsBean> ListaProgramasEscuela = new ArrayList<programaEsBean>();
     public List<programaEsBean> ListaProgramasEdu = new ArrayList<programaEsBean>();
      public List<tipoPeriodoBean> ListaTipoPeriodo = new ArrayList<tipoPeriodoBean>();
         public List<periodoBean> ListaPeriodo = new ArrayList<periodoBean>();
    
     programaEsBean programa=new programaEsBean();
    escuelaBean escuela=new escuelaBean();
    Constantes mensaje=new Constantes();
    cctPlanBean cctplan=new cctPlanBean();
    materiaBean materia=new materiaBean();
    competenciaBean competencia=new competenciaBean();
    actividadesBean actividad= new actividadesBean();
    
    boolean banNuvProg=false;
    boolean banAgrProg=false;
     boolean banActProg=false;
    
    
      private String TipoError;
    private String TipoException;
     //******************** PARA OBJETO DE NAVEGACIoN ***********************************************
    
    
     public String AdminProgEst() {

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
        
           ListSubsistema.clear();
           escuela.setAUXPLAN("");
           escuela.setAUXSTATUS("");
           programa.setID_SUBSISTEMA_CONSULTA("");
           ListaProgramasEdu.clear();
           
          if(usuariocons.getFILTRO().equals("50")){
              
              programa.setID_NIVEL_CONSULTA("1");
            
              
          }
          
            if(usuariocons.getFILTRO().equals("60")){
              
              programa.setID_NIVEL_CONSULTA("2");
                
          }
           
           
           ListSubsistema=con.subsistema(programa);
           
            
          


            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }
        
        
    }
     
     public String consultaProgEstAdmin() {

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
            
       
             if(usuariocons.getFILTRO().equals("50")){
              
              programa.setID_NIVEL_CONSULTA("1");
            
              
          }
          
            if(usuariocons.getFILTRO().equals("60")){
              
              programa.setID_NIVEL_CONSULTA("2");
                
          }
            banNuvProg=true;
            banAgrProg=false;
            
            System.out.println("ID_SUBSISTEMA"+programa.getID_SUBSISTEMA_CONSULTA());    
            
         ListaProgramasEdu=con.programasEduAdmin(programa);
             


            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }
        
        
    }
    
     
      public String actualizaStatusProg() {

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
            
           
             String status= escuela.getAUXSTATUS();
            
            if(status.equals("1")){
                
                escuela.setAUXSTATUS("0");
                
            }
             if(status.equals("0")){
                
                escuela.setAUXSTATUS("1");
                
            }
            
             con.actualizaEstatusPlan(escuela);
           
           
           
           
       
             if(usuariocons.getFILTRO().equals("50")){
              
              programa.setID_NIVEL_CONSULTA("1");
            
              
          }
          
            if(usuariocons.getFILTRO().equals("60")){
              
              programa.setID_NIVEL_CONSULTA("2");
                
          }
            
            
            
         ListaProgramasEdu=con.programasEduAdmin(programa);
             


            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }
        
        
    }
    
    
    
    
    
     public String activaRegProg() {

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
        
          banAgrProg=true;
          banNuvProg=false;
           
            ListaTipoPeriodo=con.tipoPeriodo(escuela);
          ListaPeriodo=con.periodo(escuela);


            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }
        
        
    }
    
     public String guardaPrograma() {

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
        
            if(usuariocons.getFILTRO().equals("50")){
              
              programa.setID_NIVEL_CONSULTA("1");
            
              
          }
          
            if(usuariocons.getFILTRO().equals("60")){
              
              programa.setID_NIVEL_CONSULTA("2");
                
          }
           
           
           
           
           
           
           
          con.guardaPrograma(escuela, programa);
          
          
          consultaProgEstAdmin();
          banNuvProg=false;
          banAgrProg=false;
          
         

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }
        
        
    }
    
    public String actualizaPrograma() {

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
        
           
           
             ListaProgramasEdu=con.programasEduAdminAct(escuela);
                ListaTipoPeriodo=con.tipoPeriodo(escuela);
          ListaPeriodo=con.periodo(escuela);
             
             
        Iterator LPE=ListaProgramasEdu.iterator();
        
        programaEsBean obj;
        
            while (LPE.hasNext()) {
               obj = (programaEsBean) LPE.next();
               
               programa.setID_PLAN(obj.getID_PLAN());
               programa.setNOM_CARRERA(obj.getNOM_CARRERA());
               programa.setCVE_PLAN_EST(obj.getCVE_PLAN_EST());
               programa.setENFASIS(obj.getENFASIS());
               programa.setVERSION(obj.getVERSION());
               programa.setTIPO_PERIODO(obj.getTIPO_PERIODO());
               programa.setNUMERO_PERIODO(obj.getNUMERO_PERIODO());
               
               
                
            }
            
            
            consultaProgEstAdmin();
          
           
            banNuvProg=false;
          banAgrProg=false;
          banActProg=true;
           
           
        // con.guardaPrograma(escuela, programa);
          
          
         // consultaProgEstAdmin();
         
          
         

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }
        
        
    }
    
    
    
      public String actualizaProgramaGuarda() {

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
        
           con.actualizaProgramaGuarda(escuela, programa);
           
           
             if(usuariocons.getFILTRO().equals("50")){
              
              programa.setID_NIVEL_CONSULTA("1");
            
              
          }
          
            if(usuariocons.getFILTRO().equals("60")){
              
              programa.setID_NIVEL_CONSULTA("2");
                
          }
           
           
               ListaProgramasEdu=con.programasEduAdmin(programa);
           
           
           
           
          banNuvProg=false;
          banAgrProg=false;
          banActProg=false;
          
           
           
           
           
           
        // 
          
          
       
         
          
         

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }
        
        
    }
    
      public String eliminarPrograma() {

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
        
           con.eliminarPrograma(escuela, programa);
           
           
             if(usuariocons.getFILTRO().equals("50")){
              
              programa.setID_NIVEL_CONSULTA("1");
            
              
          }
          
            if(usuariocons.getFILTRO().equals("60")){
              
              programa.setID_NIVEL_CONSULTA("2");
                
          }
           
           
               ListaProgramasEdu=con.programasEduAdmin(programa);
           
           
           
           
          banNuvProg=false;
          banAgrProg=false;
          banActProg=false;
          
           
           
           
           
           
        // 
          
          
       
         
          
         

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }
        
        
    }
    
    
    
    
    
    private Map session;

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
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

    public programaEsBean getPrograma() {
        return programa;
    }

    public void setPrograma(programaEsBean programa) {
        this.programa = programa;
    }

    public escuelaBean getEscuela() {
        return escuela;
    }

    public void setEscuela(escuelaBean escuela) {
        this.escuela = escuela;
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

    public List<subsistemaBean> getListSubsistema() {
        return ListSubsistema;
    }

    public void setListSubsistema(List<subsistemaBean> ListSubsistema) {
        this.ListSubsistema = ListSubsistema;
    }

    public List<programaEsBean> getListaProgramasEscuela() {
        return ListaProgramasEscuela;
    }

    public void setListaProgramasEscuela(List<programaEsBean> ListaProgramasEscuela) {
        this.ListaProgramasEscuela = ListaProgramasEscuela;
    }

    public List<programaEsBean> getListaProgramasEdu() {
        return ListaProgramasEdu;
    }

    public void setListaProgramasEdu(List<programaEsBean> ListaProgramasEdu) {
        this.ListaProgramasEdu = ListaProgramasEdu;
    }

    public boolean isBanNuvProg() {
        return banNuvProg;
    }

    public void setBanNuvProg(boolean banNuvProg) {
        this.banNuvProg = banNuvProg;
    }

    public boolean isBanAgrProg() {
        return banAgrProg;
    }

    public void setBanAgrProg(boolean banAgrProg) {
        this.banAgrProg = banAgrProg;
    }

    public List<tipoPeriodoBean> getListaTipoPeriodo() {
        return ListaTipoPeriodo;
    }

    public void setListaTipoPeriodo(List<tipoPeriodoBean> ListaTipoPeriodo) {
        this.ListaTipoPeriodo = ListaTipoPeriodo;
    }

    public List<periodoBean> getListaPeriodo() {
        return ListaPeriodo;
    }

    public void setListaPeriodo(List<periodoBean> ListaPeriodo) {
        this.ListaPeriodo = ListaPeriodo;
    }

    public boolean isBanActProg() {
        return banActProg;
    }

    public void setBanActProg(boolean banActProg) {
        this.banActProg = banActProg;
    }

   
    
    
    
    
    
    
}
