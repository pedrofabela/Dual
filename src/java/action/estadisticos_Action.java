/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import beans.ciclosBean;
import beans.moduloAuxBean;
import beans.moduloBean;
import beans.programaEsBean;
import beans.tableroGralBean;
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
public class estadisticos_Action extends ActionSupport implements SessionAware {

    private usuarioBean usuariocons;
    private String cveusuario;
    private String pasusuario;
    private String nomModulo;
    private String modulo;
    private String nombreUsuario;
    private String tabSelect;
    private tableroGralBean indicadores;

    tableroGralBean indic = new tableroGralBean();

    public List<moduloBean> modulosAUX = new ArrayList<moduloBean>();
    public List<moduloAuxBean> modulosAUXP = new ArrayList<moduloAuxBean>();
    public List<tableroGralBean> indicadoresTablero = new ArrayList<tableroGralBean>();
    public List<tableroGralBean> indicadoresTableroNiv = new ArrayList<tableroGralBean>();
    public List<tableroGralBean> ListaUEEstatus = new ArrayList<tableroGralBean>();
    public List<tableroGralBean> ListaMunEstatus = new ArrayList<tableroGralBean>();
    public List<tableroGralBean> ListaPlanEstatus = new ArrayList<tableroGralBean>();
    public List<tableroGralBean> ListaSecEstatus = new ArrayList<tableroGralBean>();
     public List<tableroGralBean> ListaHistDual = new ArrayList<tableroGralBean>();
     
      public List<usuarioBean> ListaUsuarios = new ArrayList<usuarioBean>();

    private String TipoError;
    private String TipoException;
    
     programaEsBean programa=new programaEsBean();
    //******************** PARA OBJETO DE NAVEGACIoN ***********************************************
    private Map session;

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    public String TableroSec() {

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

            indicadoresTablero = con.indicadoresTablero(usuariocons);

            Iterator iT = indicadoresTablero.iterator();

            tableroGralBean obj;

            int continuidad = 0;

            while (iT.hasNext()) {
                obj = (tableroGralBean) iT.next();

                indic.setTOTAL_ALUMNOS(obj.getTOTAL_ALUMNOS());
                indic.setACTIVOS(obj.getACTIVOS());
                indic.setBAJAS(obj.getBAJAS());
                indic.setEGRESADOS(obj.getEGRESADOS());
                indic.setHOMBRES(obj.getHOMBRES());
                indic.setMUJER(obj.getMUJER());
                indic.setNUEVO_INGRESO(obj.getNUEVO_INGRESO());

            }

            continuidad = Integer.parseInt(indic.getTOTAL_ALUMNOS()) - Integer.parseInt(indic.getNUEVO_INGRESO());
            indic.setCONTUINIDAD(String.valueOf(continuidad));

            indicadoresTableroNiv = con.indicadoresTableroNiv(usuariocons);

            ListaUEEstatus = con.indicadoresTableroUE(usuariocons);

            ListaMunEstatus = con.indicadoresTableroMun(usuariocons);

            ListaPlanEstatus = con.indicadoresTableroPlan(usuariocons);

            ListaSecEstatus = con.indicadoresTableroSec(usuariocons);
            
            ListaHistDual=con.indicadoresTableroHist(usuariocons);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

      public String TableroSubDir() {

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
            
            

            indicadoresTablero = con.indicadoresTableroSubDir(usuariocons, programa);

            Iterator iT = indicadoresTablero.iterator();

            tableroGralBean obj;

            int continuidad = 0;

            while (iT.hasNext()) {
                obj = (tableroGralBean) iT.next();

                indic.setTOTAL_ALUMNOS(obj.getTOTAL_ALUMNOS());
                indic.setACTIVOS(obj.getACTIVOS());
                indic.setBAJAS(obj.getBAJAS());
                indic.setEGRESADOS(obj.getEGRESADOS());
                indic.setHOMBRES(obj.getHOMBRES());
                indic.setMUJER(obj.getMUJER());
                indic.setNUEVO_INGRESO(obj.getNUEVO_INGRESO());

            }

            continuidad = Integer.parseInt(indic.getTOTAL_ALUMNOS()) - Integer.parseInt(indic.getNUEVO_INGRESO());
            indic.setCONTUINIDAD(String.valueOf(continuidad));

            indicadoresTableroNiv = con.indicadoresTableroDirSubNiv(usuariocons, programa);
            
            
            

            ListaUEEstatus = con.indicadoresTableroSubDirUE(usuariocons, programa);

            ListaMunEstatus = con.indicadoresTableroSubDirMun(usuariocons, programa);

            ListaPlanEstatus = con.indicadoresTableroSubDirPlan(usuariocons, programa);

            ListaSecEstatus = con.indicadoresTableroSubDirSec(usuariocons, programa);
            
            
            
            
            
            ListaHistDual=con.indicadoresTableroHist(usuariocons);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
       public String TableroEsc() {

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
            
            

            indicadoresTablero = con.indicadoresTableroEsc(usuariocons, programa);

            Iterator iT = indicadoresTablero.iterator();

            tableroGralBean obj;

            int continuidad = 0;

            while (iT.hasNext()) {
                obj = (tableroGralBean) iT.next();

                indic.setTOTAL_ALUMNOS(obj.getTOTAL_ALUMNOS());
                indic.setACTIVOS(obj.getACTIVOS());
                indic.setBAJAS(obj.getBAJAS());
                indic.setEGRESADOS(obj.getEGRESADOS());
                indic.setHOMBRES(obj.getHOMBRES());
                indic.setMUJER(obj.getMUJER());
                indic.setNUEVO_INGRESO(obj.getNUEVO_INGRESO());

            }

            continuidad = Integer.parseInt(indic.getTOTAL_ALUMNOS()) - Integer.parseInt(indic.getNUEVO_INGRESO());
            indic.setCONTUINIDAD(String.valueOf(continuidad));

           // indicadoresTableroNiv = con.indicadoresTableroDirSubNiv(usuariocons, programa);
            
            
            

            ListaUEEstatus = con.indicadoresTableroEscUE(usuariocons, programa);

            ListaMunEstatus = con.indicadoresTableroEscMun(usuariocons, programa);

            ListaPlanEstatus = con.indicadoresTableroEscPlan(usuariocons, programa);

            ListaSecEstatus = con.indicadoresTableroEscSec(usuariocons, programa);
            
            
            
            
            
           // ListaHistDual=con.indicadoresTableroHist(usuariocons);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
       
       
       
         public String ConsulUsu () {

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
            
           
            
            
            

            ListaUsuarios = con.listaUsuarios(usuariocons, programa);

            
            
            
            
            
           // ListaHistDual=con.indicadoresTableroHist(usuariocons);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
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

    public List<tableroGralBean> getIndicadoresTablero() {
        return indicadoresTablero;
    }

    public void setIndicadoresTablero(List<tableroGralBean> indicadoresTablero) {
        this.indicadoresTablero = indicadoresTablero;
    }

    public tableroGralBean getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(tableroGralBean indicadores) {
        this.indicadores = indicadores;
    }

    public tableroGralBean getIndic() {
        return indic;
    }

    public void setIndic(tableroGralBean indic) {
        this.indic = indic;
    }

    public List<tableroGralBean> getIndicadoresTableroNiv() {
        return indicadoresTableroNiv;
    }

    public void setIndicadoresTableroNiv(List<tableroGralBean> indicadoresTableroNiv) {
        this.indicadoresTableroNiv = indicadoresTableroNiv;
    }

    public List<tableroGralBean> getListaUEEstatus() {
        return ListaUEEstatus;
    }

    public void setListaUEEstatus(List<tableroGralBean> ListaUEEstatus) {
        this.ListaUEEstatus = ListaUEEstatus;
    }

    public List<tableroGralBean> getListaMunEstatus() {
        return ListaMunEstatus;
    }

    public void setListaMunEstatus(List<tableroGralBean> ListaMunEstatus) {
        this.ListaMunEstatus = ListaMunEstatus;
    }

    public List<tableroGralBean> getListaPlanEstatus() {
        return ListaPlanEstatus;
    }

    public void setListaPlanEstatus(List<tableroGralBean> ListaPlanEstatus) {
        this.ListaPlanEstatus = ListaPlanEstatus;
    }

    public List<tableroGralBean> getListaSecEstatus() {
        return ListaSecEstatus;
    }

    public void setListaSecEstatus(List<tableroGralBean> ListaSecEstatus) {
        this.ListaSecEstatus = ListaSecEstatus;
    }

    public List<tableroGralBean> getListaHistDual() {
        return ListaHistDual;
    }

    public void setListaHistDual(List<tableroGralBean> ListaHistDual) {
        this.ListaHistDual = ListaHistDual;
    }

    public programaEsBean getPrograma() {
        return programa;
    }

    public void setPrograma(programaEsBean programa) {
        this.programa = programa;
    }

    public List<usuarioBean> getListaUsuarios() {
        return ListaUsuarios;
    }

    public void setListaUsuarios(List<usuarioBean> ListaUsuarios) {
        this.ListaUsuarios = ListaUsuarios;
    }
    
    

}
