/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static action.ResProgEducativo_Action.checkEmail;
import static action.ResProgEducativo_Action.checktelefono;
import beans.escuelaBean;
import beans.moduloAuxBean;
import beans.moduloBean;
import beans.renapoBean;
import beans.usuarioBean;
import business.ConsultasBusiness;
import action.consultaRenapo;
import beans.UnidadesEconomicasBean;
import beans.programaEsBean;
import beans.resProgEstBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import daos.UnidadesEconomicasDAOImpl;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.functions.Match;
import org.apache.struts2.interceptor.SessionAware;
import utilidades.Constantes;

/**
 *
 * @author pedro
 */
public class ResUe_Action extends ActionSupport implements SessionAware {

    private usuarioBean usuariocons;
    private String cveusuario;
    private String pasusuario;
    private String nomModulo;
    private String modulo;
    private String nombreUsuario;
    private String tabSelect;

    public List<moduloBean> modulosAUX = new ArrayList<moduloBean>();
    public List<moduloAuxBean> modulosAUXP = new ArrayList<moduloAuxBean>();
    public List<escuelaBean> ListaIdEs = new ArrayList<escuelaBean>();
    public List<resProgEstBean> ListaResProgPE = new ArrayList<resProgEstBean>();
    public List<resProgEstBean> ListaResProgPEInst = new ArrayList<resProgEstBean>();
    public List<programaEsBean> ListaProgramasEscuela = new ArrayList<programaEsBean>();
    public List<UnidadesEconomicasBean> ListaUEIE = new ArrayList<UnidadesEconomicasBean>();

    UnidadesEconomicasBean ue = new UnidadesEconomicasBean();
    renapoBean objRenapo = new renapoBean();
    consultaRenapo renapo = new consultaRenapo();
    escuelaBean escuela = new escuelaBean();
    Catalogos_Action catalogos = new Catalogos_Action();

    private String TipoError;
    private String TipoException;

    public boolean banGuardaResProg = false;
    public boolean banActualizaResProg = false;
    public boolean banGeneraUser = false;
    public boolean banRegResUE = false;
    public boolean banformPersona = false;

    private Map session;

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

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

    public String RegResUE() {

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
            objRenapo.setAUXESCUELA("");
            objRenapo.setAUXPERFIL("");
            objRenapo.setENTIDAD_NACIMINETO_RENAPO("");
            ue.setID_IE_UE("");

            banActualizaResProg = false;
            banGuardaResProg = false;
            banRegResUE = false;
            banformPersona = false;
            ListaResProgPE.clear();

            objRenapo.setAUXESCUELA(idEsc());

            ListaUEIE = con.ueIeActiva(objRenapo);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String consultaResUEIE() {

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

            banRegResUE = true;
            banActualizaResProg = false;
            banGuardaResProg = false;
            banGeneraUser = false;
            banformPersona = false;

            objRenapo.setCONSULTA_CURP("");
            objRenapo.setCURP("");
            objRenapo.setNOMBRE_RENAPO("");
            objRenapo.setAPATERNO_RENAPO("");
            objRenapo.setAMATERNO_RENAPO("");
            objRenapo.setFEC_NAC_RENAPO("");
            objRenapo.setNACIONALIDAD_RENAPO("");
            objRenapo.setEN_PERIODO("");
            objRenapo.setGENERO_RENAPO("");
            ;

            objRenapo.setAUXESCUELA(idEsc());

            ListaUEIE = con.ueIeActiva(objRenapo);

            objRenapo.setAUXPERFIL("26");

            ListaResProgPEInst = con.resUE(objRenapo, ue);

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
    

    public String consultaCuprResUE() {

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

            ListaResProgPE = con.resProgEst(objRenapo);

            if (ListaResProgPE.size() > 0) {

                banActualizaResProg = true;
                banGuardaResProg = false;
                banformPersona = false;

                Iterator LRPE = ListaResProgPE.iterator();

                resProgEstBean obj;

                while (LRPE.hasNext()) {
                    obj = (resProgEstBean) LRPE.next();

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
                if (objRenapo.getNOMBRE_RENAPO().length() > 0) {
                    banRegResUE = true;
                    banformPersona = true;
                }

                objRenapo.setAUXESCUELA(idEsc());
                objRenapo.setAUXPERFIL("26");

                ListaResProgPEInst = con.resUE(objRenapo, ue);

                Iterator LRPPI = ListaResProgPEInst.iterator();

                resProgEstBean obj2;

                boolean nuevo = false;

                while (LRPPI.hasNext()) {
                    obj2 = (resProgEstBean) LRPPI.next();

                    if (objRenapo.getCURP().equals(obj2.getCURP_PERSONA())) {

                        nuevo = true;
                    }

                }

                if (!nuevo) {
                    banGeneraUser = true;

                } else {
                    banGeneraUser = false;

                }

                //  addFieldError("YARPE", "¡El responsable ya se encuentra registrado, solo puede actualizar los datos!");
            } else {

                objRenapo = renapo.consultaRenapo(objRenapo.getCONSULTA_CURP());

                banGuardaResProg = true;
                banActualizaResProg = false;
                banformPersona = true;
                banRegResUE = true;
                banGeneraUser=false;

                if (objRenapo.getNOMBRE_RENAPO().length() == 0) {
                    banGuardaResProg = false;
                    banActualizaResProg = false;

                    addFieldError("NOCURP", "No existe la CURP, favor de verificar");

                }

            }

            objRenapo.setAUXESCUELA(idEsc());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String guardaResUE() {

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

                    ListaResProgPE = con.resProgEst(objRenapo);

                    if (ListaResProgPE.size() > 0) {

                        Iterator LRPE = ListaResProgPE.iterator();

                        resProgEstBean obj;

                        while (LRPE.hasNext()) {
                            obj = (resProgEstBean) LRPE.next();

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

                    objRenapo.setPERFIL("26");

                    con.guardaResUE(objRenapo, ue);

                    objRenapo.setUSUARIO(objRenapo.getCURP().substring(1, 4) + "-RUE" + objRenapo.getAUXESCUELA());

                    objRenapo.setPASSWORD(conspassword());
                    System.out.println("salio de cons password");

                    usuariocons.setESTATUS_VARIABLE("1");
                    usuariocons.setNAMEPERFIL_VARIABLE("Responsable de Unidad Económica");

                    con.guardaUserResUE(objRenapo, usuariocons, ue);

                    objRenapo.setAUXPERFIL("26");

                    ListaResProgPEInst = con.resUE(objRenapo, ue);

                    banGeneraUser = false;

                    banRegResUE = true;
                    
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

                    banGuardaResProg = true;

                }

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

            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String conspassword() throws NoSuchAlgorithmException {

        String[] caracter = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "F", "G", "H", "I", "J", "K"};

        Random r = new Random();
        String letra = "";
        int numero = 0;

        String password = "";

        for (int i = 0; i < 10; i++) {

            numero = 0;
            letra = "";

            numero = r.nextInt(19) + 1;

            letra = caracter[numero];

            password = password + letra;

        }

        return password;
    }

    public String actualizaResUE() {

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
            
             banRegResUE = true;
                    banformPersona = true;
                    banGeneraUser = true;
                    banActualizaResProg = true;

            if (valEmail && telCasa && telCel) {
                   
                        con.actualizaResPe(objRenapo);
                addFieldError("ACTRESPROGEDU", "¡Los datos se actualizaron correctamente!");
                consultaResUEIE();
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
                banGeneraUser=false;

            }
            
            
            
            
            
            

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String actualizarEstatusUsuarioUE() {

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

            objRenapo.setAUXPERFIL("26");

            ListaResProgPEInst = con.resUE(objRenapo, ue);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String VinResUEProg() {

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
            objRenapo.setAUXESCUELA("");
            objRenapo.setAUXPERFIL("");
            objRenapo.setAUXESTATUS("");
            objRenapo.setAUXPROGVINCULADO("");
            objRenapo.setENTIDAD_NACIMINETO_RENAPO("");
            objRenapo.setID_PERSONA("");
            escuela.setAUXPLAN("");

            banActualizaResProg = false;
            banGuardaResProg = false;

            ListaResProgPE.clear();

            objRenapo.setAUXESCUELA(idEsc());

            objRenapo.setAUXPERFIL("26");

            ListaUEIE = con.ueIeActiva(objRenapo);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String conRUE() {

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

            objRenapo.setAUXESCUELA(idEsc());
            objRenapo.setAUXPERFIL("26");

            ListaResProgPEInst = con.resUE(objRenapo, ue);

            Constantes.enviaMensajeConsola("entre a validar");
            ListaProgramasEscuela = con.programasEscuelaRes(escuela, objRenapo);
            Constantes.enviaMensajeConsola("sali de la consulta de programas");

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String guardaResUEProgInst() {

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

            con.guardaResPeInst(objRenapo, escuela);

            objRenapo.setAUXESCUELA(idEsc());
            objRenapo.setAUXPERFIL("26");

            ListaResProgPEInst = con.resUE(objRenapo, ue);

            escuela.setID_ESCUELA(idEsc());

            ListaProgramasEscuela = con.programasEscuelaRes(escuela, objRenapo);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String actualizaResUEProgInst() {

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

            con.actualizaResPeInst(objRenapo, escuela);

            objRenapo.setAUXESCUELA(idEsc());
            objRenapo.setAUXPERFIL("26");

            ListaResProgPEInst = con.resUE(objRenapo, ue);

            escuela.setID_ESCUELA(idEsc());

            ListaProgramasEscuela = con.programasEscuelaRes(escuela, objRenapo);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String generaUsuarioResUe() {

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

            objRenapo.setPERFIL("26");

            con.guardaResUE(objRenapo, ue);

            objRenapo.setUSUARIO(objRenapo.getCURP().substring(1, 4) + "-RUE" + objRenapo.getAUXESCUELA());
            System.out.println("salio de construcción de usuario");
            objRenapo.setPASSWORD(conspassword());
            System.out.println("salio de cons password");

            usuariocons.setESTATUS_VARIABLE("1");
            usuariocons.setNAMEPERFIL_VARIABLE("Responsable de Unidad Económica");

            con.guardaUserResUE(objRenapo, usuariocons, ue);

            objRenapo.setAUXPERFIL("26");

            ListaResProgPEInst = con.resUE(objRenapo, ue);

            banGeneraUser = false;

            banRegResUE = true;

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public static boolean checkEmail(String email) {
        // Establecer el patron
        Pattern p = Pattern.compile("[-\\w\\.]+@[\\.\\w]+\\.\\w+");
        // Asociar el string al patron
        Matcher m = p.matcher(email);
        // Comprobar si encaja

        return m.matches();
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

    public renapoBean getObjRenapo() {
        return objRenapo;
    }

    public void setObjRenapo(renapoBean objRenapo) {
        this.objRenapo = objRenapo;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public consultaRenapo getRenapo() {
        return renapo;
    }

    public void setRenapo(consultaRenapo renapo) {
        this.renapo = renapo;
    }

    public escuelaBean getEscuela() {
        return escuela;
    }

    public void setEscuela(escuelaBean escuela) {
        this.escuela = escuela;
    }

    public Catalogos_Action getCatalogos() {
        return catalogos;
    }

    public void setCatalogos(Catalogos_Action catalogos) {
        this.catalogos = catalogos;
    }

    public List<escuelaBean> getListaIdEs() {
        return ListaIdEs;
    }

    public void setListaIdEs(List<escuelaBean> ListaIdEs) {
        this.ListaIdEs = ListaIdEs;
    }

    public List<resProgEstBean> getListaResProgPE() {
        return ListaResProgPE;
    }

    public void setListaResProgPE(List<resProgEstBean> ListaResProgPE) {
        this.ListaResProgPE = ListaResProgPE;
    }

    public boolean isBanGuardaResProg() {
        return banGuardaResProg;
    }

    public void setBanGuardaResProg(boolean banGuardaResProg) {
        this.banGuardaResProg = banGuardaResProg;
    }

    public boolean isBanActualizaResProg() {
        return banActualizaResProg;
    }

    public void setBanActualizaResProg(boolean banActualizaResProg) {
        this.banActualizaResProg = banActualizaResProg;
    }

    public List<resProgEstBean> getListaResProgPEInst() {
        return ListaResProgPEInst;
    }

    public void setListaResProgPEInst(List<resProgEstBean> ListaResProgPEInst) {
        this.ListaResProgPEInst = ListaResProgPEInst;
    }

    public List<programaEsBean> getListaProgramasEscuela() {
        return ListaProgramasEscuela;
    }

    public void setListaProgramasEscuela(List<programaEsBean> ListaProgramasEscuela) {
        this.ListaProgramasEscuela = ListaProgramasEscuela;
    }

    public boolean isBanGeneraUser() {
        return banGeneraUser;
    }

    public void setBanGeneraUser(boolean banGeneraUser) {
        this.banGeneraUser = banGeneraUser;
    }

    public List<UnidadesEconomicasBean> getListaUEIE() {
        return ListaUEIE;
    }

    public void setListaUEIE(List<UnidadesEconomicasBean> ListaUEIE) {
        this.ListaUEIE = ListaUEIE;
    }

    public UnidadesEconomicasBean getUe() {
        return ue;
    }

    public void setUe(UnidadesEconomicasBean ue) {
        this.ue = ue;
    }

    public boolean isBanRegResUE() {
        return banRegResUE;
    }

    public void setBanRegResUE(boolean banRegResUE) {
        this.banRegResUE = banRegResUE;
    }

    public boolean isBanformPersona() {
        return banformPersona;
    }

    public void setBanformPersona(boolean banformPersona) {
        this.banformPersona = banformPersona;
    }

}
