/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static action.ResMentorAcad_Action.checkEmail;
import static action.ResProgEducativo_Action.checkEmail;
import static action.ResProgEducativo_Action.checktelefono;
import beans.actividadesBean;
import beans.cctPlanBean;
import beans.competenciaBean;
import beans.escuelaBean;
import beans.materiaBean;
import beans.moduloAuxBean;
import beans.moduloBean;
import beans.programaEsBean;
import beans.renapoBean;
import beans.resProgEstBean;
import beans.subsistemaBean;
import beans.usuarioBean;
import business.ConsultasBusiness;
import com.opensymphony.xwork2.ActionSupport;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.struts2.interceptor.SessionAware;
import utilidades.Constantes;

/**
 *
 * @author pedro
 */
public class ResAcademico_Action extends ActionSupport implements SessionAware {
    
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
        public List<escuelaBean> ListaIdEs = new ArrayList<escuelaBean>();
            public List<resProgEstBean> ListaResProgPEInst = new ArrayList<resProgEstBean>();
     public List<resProgEstBean> ListaResProgPE = new ArrayList<resProgEstBean>();
     
     
        programaEsBean programa=new programaEsBean();
    escuelaBean escuela=new escuelaBean();
    Constantes mensaje=new Constantes();
    cctPlanBean cctplan=new cctPlanBean();
    materiaBean materia=new materiaBean();
    competenciaBean competencia=new competenciaBean();
    actividadesBean actividad= new actividadesBean();
     renapoBean objRenapo=new renapoBean();
     consultaRenapo renapo=new consultaRenapo();
     
     public boolean banGeneraUser=false;
     
      public boolean banActualizaResProg=false;
         public boolean  banGuardaResProg=false;
    
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
    
    
    public String RegResAcad() {

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
           
             programa.setID_SUBSISTEMA_CONSULTA("");
             escuela.setAUXSUBSISTEMA("");
             ListaResProgPEInst.clear();
             objRenapo.setAUXESCUELA("");
             escuela.setAUXNOMESC("");
             escuela.setAUXPERFILUSR("");
             escuela.setAUXCCT("");
           banGeneraUser=false;
           banActualizaResProg=false;
           banGuardaResProg=false;
             
             
          if(usuariocons.getFILTRO().equals("50")){
              
              programa.setID_NIVEL_CONSULTA("1");
            
              
          }
          
            if(usuariocons.getFILTRO().equals("60")){
              
              programa.setID_NIVEL_CONSULTA("2");
                
          }
           
           
           
           
            
            ListSubsistema=con.subsistema(programa);
            ListaIdEs.clear();

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }
        
        
    }
    
    
     public List consultaInstEducativas(String subsistema) throws Exception {

        //validando session***********************************************************************
      
            
            
           ConsultasBusiness con = new ConsultasBusiness();
           
          
         escuela.setAUXSUBSISTEMA(subsistema);
       
         
            ListaIdEs=con.escuelasSubsistema(escuela);
            
           

      
        
        return ListaIdEs;
        
        
    }
     
     
      public String muestraEcualasNivel() {

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
           
           escuela.setAUXSUBSISTEMA("");
           objRenapo.setAUXESCUELA("");
           
          escuela.setAUXSUBSISTEMA(programa.getID_SUBSISTEMA_CONSULTA());
          ListaResProgPEInst.clear();
            
           
           ListaIdEs=consultaInstEducativas(escuela.getAUXSUBSISTEMA());
           
            
           


            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }
        
        
    }
    
    
    
    public String seleccionaEcualasNivel() {

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
           
         objRenapo.setAUXPERFIL("24");
           
          ListaResProgPEInst=con.resProgEstInst(objRenapo);
         
           
            
           


            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
                    return "ERROR";
                }


            }
    
     public String consultaCurpResAcad() {

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
            
            
              
                   banGuardaResProg=false;
                   banActualizaResProg=false;
            
           
            ListaResProgPE=con.resProgEst(objRenapo);
            
            if(ListaResProgPE.size()>0){
                
                banActualizaResProg=true;
                banGuardaResProg=false;
            
            Iterator LRPE=ListaResProgPE.iterator();
            
            resProgEstBean obj;
            
            while (LRPE.hasNext()) {
                obj= (resProgEstBean) LRPE.next();
                
                objRenapo.setCURP(obj.getCURP_PERSONA());
                objRenapo.setNOMBRE_RENAPO(obj.getNOMBRE_PERSONA());
                objRenapo.setAPATERNO_RENAPO(obj.getAPATERNO_PERSONA());
                objRenapo.setAMATERNO_RENAPO(obj.getAMATERNO_PERSONA());
                objRenapo.setFEC_NAC_RENAPO(obj.getFECNAC_PERSONA());
                objRenapo.setNACIONALIDAD_RENAPO(obj.getNAC_PERSONA());
                objRenapo.setENTIDAD_NACIMINETO_RENAPO(obj.getENTIDAD_NACIMIENTO());
                objRenapo.setGENERO_RENAPO(obj.getGENERO());
                objRenapo.setTELEFONO(obj.getTELCASA_PERSONA());
                objRenapo.setCELULAR(obj.getTELCEL_PERSONA());
                objRenapo.setRFC(obj.getRFC());
                objRenapo.setCORREO_PERSONA(obj.getCORREO_PERSONA());
                objRenapo.setID_PERSONA(obj.getID_PERSONA());
           
            }
            
            
          objRenapo.setAUXPERFIL("24");
           
          ListaResProgPEInst=con.resProgEstInst(objRenapo);
          
          Iterator LRPPI=ListaResProgPEInst.iterator();
          
            resProgEstBean obj2;
            
            boolean nuevo=false;
            
                while (LRPPI.hasNext()) {
                    obj2 = (resProgEstBean) LRPPI.next();
                    
                   if(objRenapo.getCURP().equals(obj2.getCURP_PERSONA())){
                       
                       nuevo=true;
                   } 
                    
                    
                }
            
            
            if(!nuevo){
                banGeneraUser=true;
            }
            else{
                banGeneraUser=false;
                
            }
            
            
            
            
            
            
            
            
             //  addFieldError("YARPE", "¡El responsable ya se encuentra registrado, solo puede actualizar los datos!");
            
            }
            else{
                
                String auxescuela="";
                
                auxescuela=objRenapo.getAUXESCUELA();
                
                  objRenapo=renapo.consultaRenapo(objRenapo.getCONSULTA_CURP());
                  
                 objRenapo.setAUXESCUELA(auxescuela);
                  
                   banGuardaResProg=true;
                   banActualizaResProg=false;
                  
                  if(objRenapo.getNOMBRE_RENAPO().length()==0){
                        banGuardaResProg=false;
                        banActualizaResProg=false;
                       
                        addFieldError("NOCURP", "No existe la CURP, favor de verificar");
                      
                  }
                
            }
            
          

          

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    } 
     
     
     public String actualizaResAcad() {

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
           
          
              boolean valEmail = checkEmail(objRenapo.getCORREO_PERSONA());
            boolean telCasa = checktelefono(objRenapo.getTELEFONO());
            boolean telCel = checktelefono(objRenapo.getCELULAR());

            if (valEmail && telCasa && telCel) {

                con.actualizaResPe(objRenapo);
                addFieldError("ACTRESPROGEDU", "¡Los datos se actualizaron correctamente!");
                RegResponsableAcad();
            } else {

                if (!valEmail) {
                    addFieldError("ERROREMAIL", "Debe registrar un correo electrónico válido");
                }
                if (!telCasa) {
                    addFieldError("ERRORTEL", "Debe registrar un Teléfono válido");
                }
                if (!telCel) {
                    addFieldError("ERRORCEL", "Debe registrar un Celular válido");
                }
                banActualizaResProg = true;

            }
            
            
            
            

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    } 
     
       public String RegResponsableAcad() {

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
            //objRenapo=null;
            objRenapo.setCONSULTA_CURP("");
             objRenapo.setCURP("");
             objRenapo.setNOMBRE_RENAPO("");
               objRenapo.setAPATERNO_RENAPO("");
                objRenapo.setAMATERNO_RENAPO("");
                 objRenapo.setFEC_NAC_RENAPO("");
                  objRenapo.setNACIONALIDAD_RENAPO("");
                   objRenapo.setEN_PERIODO("");
                    objRenapo.setGENERO_RENAPO("");
                     objRenapo.setTELEFONO("");
                      objRenapo.setCELULAR("");
                       objRenapo.setCORREO_PERSONA("");
                        objRenapo.setRFC("");
                        
                        objRenapo.setAUXPERFIL("");
              objRenapo.setENTIDAD_NACIMINETO_RENAPO("");
            
            banActualizaResProg=false;
            banGuardaResProg=false;
            
            ListaResProgPE.clear();
            
            
            
            
        
          objRenapo.setAUXPERFIL("24");
           
          ListaResProgPEInst=con.resProgEstInst(objRenapo);
            
            
          

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    } 
       
       
       
        public String generaUsuarioResAcad() {

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
            //objRenapo=null;
         
          
         objRenapo.setPERFIL("24");
            
                  con.guardaResPeIe(objRenapo);
                  
                  
                  objRenapo.setUSUARIO(objRenapo.getCURP().substring(1,4)+"-RESACAD"+objRenapo.getAUXESCUELA());
                 
                  objRenapo.setPASSWORD(conspassword());
                 
          
                  
                  usuariocons.setESTATUS_VARIABLE("1");
                  usuariocons.setNAMEPERFIL_VARIABLE("Responsable Académico");
                  
                  
                  
                  
                
                 con.guardaUserResAcad(objRenapo, usuariocons, escuela);
                  
                 
                 
                
        
                  
                  objRenapo.setAUXPERFIL("24");
           
          ListaResProgPEInst=con.resProgEstInst(objRenapo);
          
          banGeneraUser=false;
          
          

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }   
        
        
        public String conspassword() throws NoSuchAlgorithmException {
               
              String[] caracter = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "F", "G", "H", "I", "J","K"};
              
           Random r = new Random();
           String letra="";
           int numero=0;
           
            String password ="";
              
            for (int i = 0; i < 10; i++) {
             
                numero=0;
                letra="";
                
                numero = r.nextInt(19)+1;
                
                
                letra=caracter[numero];
                
                password=password+letra;
                
                
         }
            
            
              
              
              
              
         
         
      return  password;   
     }
        
        
        
        
         public String actualizarEstatusResAcad() {

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
           
           con.actualizaUsuario(objRenapo);
           
           RegResponsableAcad();
           
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    } 
         public static boolean checktelefono(String tel) {
        // Establecer el patron
        Pattern p = Pattern.compile("[0-9]{10}");
        // Asociar el string al patron

        Matcher m = p.matcher(tel);

        // Comprobar si encaja
        return m.matches();
    }
         public static boolean checkEmail(String email) {
        // Establecer el patron
        Pattern p = Pattern.compile("[-\\w\\.]+@[\\.\\w]+\\.\\w+");
        // Asociar el string al patron
        Matcher m = p.matcher(email);
        // Comprobar si encaja
        return m.matches();
    }
        
        
         public String guardaResAcad() {

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
           
          
            boolean valEmail = checkEmail(objRenapo.getCORREO_PERSONA());
            boolean telCasa = checktelefono(objRenapo.getTELEFONO());
            boolean telCel = checktelefono(objRenapo.getCELULAR());

            if (valEmail && telCasa && telCel) {

                if (valEmail) {
                    
                    con.guardaResPe(objRenapo);
                  
                      ListaResProgPE=con.resProgEst(objRenapo);
            
            if(ListaResProgPE.size()>0){
                
              
            
            Iterator LRPE=ListaResProgPE.iterator();
            
            resProgEstBean obj;
            
            while (LRPE.hasNext()) {
                obj= (resProgEstBean) LRPE.next();
                
                objRenapo.setCURP(obj.getCURP_PERSONA());
                objRenapo.setNOMBRE_RENAPO(obj.getNOMBRE_PERSONA());
                objRenapo.setAPATERNO_RENAPO(obj.getAPATERNO_PERSONA());
                objRenapo.setAMATERNO_RENAPO(obj.getAMATERNO_PERSONA());
                objRenapo.setFEC_NAC_RENAPO(obj.getFECNAC_PERSONA());
                objRenapo.setNACIONALIDAD_RENAPO(obj.getNAC_PERSONA());
                objRenapo.setENTIDAD_NACIMINETO_RENAPO(obj.getENTIDAD_NACIMIENTO());
                objRenapo.setGENERO_RENAPO(obj.getGENERO());
                objRenapo.setTELEFONO(obj.getTELCASA_PERSONA());
                objRenapo.setCELULAR(obj.getTELCEL_PERSONA());
                objRenapo.setRFC(obj.getRFC());
                objRenapo.setCORREO_PERSONA(obj.getCORREO_PERSONA());
                objRenapo.setID_PERSONA(obj.getID_PERSONA());
           
            }
            
           
            
            }
                
                objRenapo.setPERFIL("24");
            
                  con.guardaResPeIe(objRenapo);
                  
                  
                  
                  objRenapo.setUSUARIO(objRenapo.getCURP().substring(1,4)+"-RESACAD"+objRenapo.getAUXESCUELA());
                
                  objRenapo.setPASSWORD(conspassword());
                  
          
                  
                  usuariocons.setESTATUS_VARIABLE("1");
                  usuariocons.setNAMEPERFIL_VARIABLE("Responsable Académico");
                
                  con.guardaUserResAcad(objRenapo, usuariocons, escuela);
                 
                  
                  objRenapo.setAUXPERFIL("24");
           
          ListaResProgPEInst=con.resProgEstInst(objRenapo);
                
            RegResponsableAcad();
          
                     addFieldError("GUARDADO", "Usuario Registrado");
                     
                     objRenapo.setNOMBRE_RENAPO("");
                    
                  
                } else {
                      if (!valEmail) {
                        addFieldError("ERROREMAIL", "Debe registrar un correo electrónico válido");
                    }
                    if (!telCasa) {
                        addFieldError("ERRORTEL", "Debe registrar un Teléfono válido");
                    }
                    if (!telCel) {
                        addFieldError("ERRORCEL", "Debe registrar un Celular válido");
                    }
                    
                   banGuardaResProg=true;

                }

            } 
            
            else{
                 if (!valEmail) {
                    addFieldError("ERROREMAIL", "Debe registrar un correo electrónico válido");
                }
                if (!telCasa) {
                    addFieldError("ERRORTEL", "Debe registrar un Teléfono válido");
                }
                if (!telCel) {
                    addFieldError("ERRORCEL", "Debe registrar un Celular válido");
                }
                
            }
            
            
            
            
            
            
            
             
              
                
                

          

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    } 
      

            public List<resProgEstBean> getListaResProgPEInst() {
                return ListaResProgPEInst;
            }

            public void setListaResProgPEInst(List<resProgEstBean> ListaResProgPEInst) {
                this.ListaResProgPEInst = ListaResProgPEInst;
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

    public List<subsistemaBean> getListSubsistema() {
        return ListSubsistema;
    }

    public void setListSubsistema(List<subsistemaBean> ListSubsistema) {
        this.ListSubsistema = ListSubsistema;
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

    public List<escuelaBean> getListaIdEs() {
        return ListaIdEs;
    }

    public void setListaIdEs(List<escuelaBean> ListaIdEs) {
        this.ListaIdEs = ListaIdEs;
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

    public renapoBean getObjRenapo() {
        return objRenapo;
    }

    public void setObjRenapo(renapoBean objRenapo) {
        this.objRenapo = objRenapo;
    }

    public List<resProgEstBean> getListaResProgPE() {
        return ListaResProgPE;
    }

    public void setListaResProgPE(List<resProgEstBean> ListaResProgPE) {
        this.ListaResProgPE = ListaResProgPE;
    }

    public consultaRenapo getRenapo() {
        return renapo;
    }

    public void setRenapo(consultaRenapo renapo) {
        this.renapo = renapo;
    }

    public boolean isBanGeneraUser() {
        return banGeneraUser;
    }

    public void setBanGeneraUser(boolean banGeneraUser) {
        this.banGeneraUser = banGeneraUser;
    }

    public boolean isBanActualizaResProg() {
        return banActualizaResProg;
    }

    public void setBanActualizaResProg(boolean banActualizaResProg) {
        this.banActualizaResProg = banActualizaResProg;
    }

    public boolean isBanGuardaResProg() {
        return banGuardaResProg;
    }

    public void setBanGuardaResProg(boolean banGuardaResProg) {
        this.banGuardaResProg = banGuardaResProg;
    }
    
    
    
    
    
    
}
