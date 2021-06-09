/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;


import beans.AlumnoBean;
import beans.CatalogoBean;
import beans.ColoniasBean;
import beans.UnidadesEconomicasBean;
import beans.escuelaBean;
import beans.moduloAuxBean;
import beans.moduloBean;
import beans.programaEsBean;
import beans.renapoBean;
import beans.responsablesBean;
import beans.usuarioBean;
import com.opensymphony.xwork2.ActionSupport;

import daos.UnidadesEconomicasDAOImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import utilidades.Constantes;

import business.ConsultasBusiness;
import daos.AlumnoDAOImpl;
import daos.PlanFDAOImpl;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import mx.gob.edomex.dgsei.ws.ConsultaRenapoPorCurp;
import mx.gob.edomex.dgsei.ws.ConsultaDatosRenapo;
import mx.gob.edomex.dgsei.ws.PersonasDTO;
import org.apache.commons.io.FileUtils;
import static org.apache.commons.io.FilenameUtils.getExtension;
import org.apache.tomcat.dbcp.pool2.PoolUtils;
import static utilidades.Constantes.rutaArchivosRemota;

/**
 *
 * @author pedro
 */
public class EstudianteDual_Action extends ActionSupport implements SessionAware {

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
    private List<AlumnoBean> ListaProgramas = new ArrayList<AlumnoBean>();
    private List<CatalogoBean> ListaGrados = new ArrayList<CatalogoBean>();
    private List<AlumnoBean> DatosAlumno = new ArrayList<AlumnoBean>();
    private List<AlumnoBean> ListaInteresados = new ArrayList<AlumnoBean>();
    private List<AlumnoBean> ListaInteresadosPE = new ArrayList<AlumnoBean>();
    private List<AlumnoBean> ListaCorreos = new ArrayList<AlumnoBean>();
    private List<CatalogoBean> ListaEvaluacion = new ArrayList<CatalogoBean>();
    private List<CatalogoBean> ListaSituacion = new ArrayList<CatalogoBean>();
    private List<CatalogoBean> ListaPromedio = new ArrayList<CatalogoBean>();
    private List<AlumnoBean> ListaPreseleccionados = new ArrayList<AlumnoBean>();
    private List<AlumnoBean> ListaPEUE = new ArrayList<AlumnoBean>();
    private List<AlumnoBean> ListaValidados = new ArrayList<AlumnoBean>();
    private List<AlumnoBean> ListaAceptados = new ArrayList<AlumnoBean>();
    public List<UnidadesEconomicasBean> ListaUE = new ArrayList<UnidadesEconomicasBean>();
    private List<ColoniasBean> ListaColonia = new ArrayList<ColoniasBean>();
    private List<ColoniasBean> ListaColoniaP = new ArrayList<ColoniasBean>();
    public List<programaEsBean> ListaProgramasRegistro = new ArrayList<programaEsBean>();
    public List<programaEsBean> ListaPlanFormAlu = new ArrayList<programaEsBean>();
    public List<programaEsBean> ListaPlanAluMateria = new ArrayList<programaEsBean>();
    public List<programaEsBean> ListaPlanAluMateriaCom = new ArrayList<programaEsBean>();
    public List<programaEsBean> ListaPlanAluMateriaComAct = new ArrayList<programaEsBean>();
    public List<programaEsBean> ListaRubrica = new ArrayList<programaEsBean>();
    public List<programaEsBean> ListaSemanas = new ArrayList<programaEsBean>();
    public List<programaEsBean> ListaFechas = new ArrayList<programaEsBean>();
    public List<programaEsBean> ListaRegistroEstMs = new ArrayList<programaEsBean>();
    

    public List<programaEsBean> ListaPlanAlumno = new ArrayList<programaEsBean>();

    public List<AlumnoBean> ListaEstudiantes = new ArrayList<AlumnoBean>();
    public List<responsablesBean> ListaResUE = new ArrayList<responsablesBean>();
    public List<responsablesBean> ListaResMentorUE = new ArrayList<responsablesBean>();
    public List<responsablesBean> ListaResAcad = new ArrayList<responsablesBean>();
    public List<responsablesBean> ListaMentorAcad = new ArrayList<responsablesBean>();
    public List<escuelaBean> ListaIdEs = new ArrayList<escuelaBean>();

    AlumnoBean alumno = new AlumnoBean();
    UnidadesEconomicasBean unidad = new UnidadesEconomicasBean();
    responsablesBean responsables = new responsablesBean();
    programaEsBean programa = new programaEsBean();
    renapoBean objRenapo = new renapoBean();

    private List<programaEsBean> ListaPlanUE = new ArrayList<programaEsBean>();

    public List<programaEsBean> ListaProgramasRegistroDatos = new ArrayList<programaEsBean>();

    private boolean banMuestraForm = false;
    private boolean banMuestraForm1 = false;
    private boolean banMuestraFormDUAL = false;
    private boolean banGuardaInteresado = false;
    private boolean banActualizaInteresado = false;
    private boolean banFormPad = false;
    private boolean banMuestraCurpP = false;
    boolean mensajeSnAct = false;
    boolean banListaAlu = false;
    boolean banCampAlumno = false;
    boolean banregistro = false;
    boolean banactualiza = false;
    boolean banFechaTermino = false;
    boolean banRegAluNiveS=false;
     boolean banRegAluNiveMS=false;
     boolean banActualiza=false;
     boolean banGuarda=false;
      boolean banVisualiza=false;
      boolean banGuardaEvalMUE=false;
     
     
    private boolean banT = false;
    private String archiFileName;
    private File archi;

    private String TipoError;
    private String TipoException;
    
    String ruta = null;


    escuelaBean escuela = new escuelaBean();
    AlumnoBean al = new AlumnoBean();

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

    public String IniReg() {

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

            al.setID_ESCUELA(con.ConsultaID(usuariocons.getUSUARIO()));

            ListaInteresados = con.ConsultaInteresados(al);
            al.setCURPA("");
            banMuestraForm = false;

            al.setID_CICLO(con.ConsultaCICLO());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String consultaCurpA() {
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

            UnidadesEconomicasDAOImpl con2 = new UnidadesEconomicasDAOImpl();

            ListaProgramas = con.ConsultaPE(usuariocons.getUSUARIO());
            ListaGrados = con2.ConsultaCatalogo("300");

            String valor = null;

            valor = con.VerificaAspirante(al);

            Constantes.enviaMensajeConsola("llego a metodo consulta" + valor);

            if (valor.length() == 0) {

                DatosAlumno = con.ConsultaDatos(al);

                Constantes.enviaMensajeConsola("datos alumo: " + DatosAlumno.size());

                if (DatosAlumno.size() > 0) {

                    Iterator DA = DatosAlumno.iterator();
                    AlumnoBean gobj;

                    while (DA.hasNext()) {
                        gobj = (AlumnoBean) DA.next();
                        al.setID_ALUMNO(gobj.getID_ALUMNO());
                        al.setCURP(gobj.getCURP());
                        al.setNOMBRE(gobj.getNOMBRE());
                        al.setAPELLIDOP(gobj.getAPELLIDOP());
                        al.setAPELLIDOM(gobj.getAPELLIDOM());
                        al.setFEC_NAC(gobj.getFEC_NAC());
                        al.setENTIDAD_NAC(gobj.getENTIDAD_NAC());
                        al.setSEXO(gobj.getSEXO());
                        al.setMATRICULA(gobj.getMATRICULA());
                        al.setCVE_PRO_EDU(gobj.getCVE_PRO_EDU());
                        al.setGRADO(gobj.getGRADO());

                    }
                    banMuestraForm = true;
                    banActualizaInteresado = true;

                } else {
                    System.out.println("MICURP ES: " + al.getCURPA());
                    service = new ConsultaDatosRenapo();
                    port = service.getConsultaRenapoPorCurpPort();
                    personas = port.consultaPorCurp(al.getCURPA());
                    //port.consultaPorCurp(micurp)

                    Constantes.enviaMensajeConsola("resultado de renapo" + personas.getResultado());

                    if (personas.getResultado().equals("EXITO")) {

                        al.setNOMBRE(personas.getNombre());
                        al.setAPELLIDOP(personas.getApellidoPaterno());
                        al.setAPELLIDOM(personas.getApellidoMaterno());
                        al.setFEC_NAC(personas.getFechaNacimientoAxu());
                        al.setENTIDAD_NAC(personas.getCveEntidadNacimiento());
                        al.setCURP(personas.getCurp());

                        if (personas.getSexo().equals("H")) {
                            al.setSEXO("HOMBRE");
                        } else {
                            al.setSEXO("MUJER");
                        }

                        al.setCURPA("");

                        banMuestraForm = true;
                        banGuardaInteresado = true;

                    } else {

                        System.out.println("Resultado            : " + personas.getResultado());
                        System.out.println("Codigo de error      : " + personas.getCodigoError());
                        System.out.println("Descripcion Error    : " + personas.getDescripcionError());

                        addFieldError("ErrorValCurp", personas.getDescripcionError());

                        banMuestraForm = false;
                    }

                    return "input";
                }

            } else {

                addFieldError("YaRegistrado", "Alumno ya  registrado");
                banMuestraForm = false;
            }

        } catch (Exception e) {
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String GuardaInteresado() {
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

            con.GuardaInteresado(al);

            al.setID_ALUMNO(con.ConsultaID_ALUMNO(al.getCURP()));
            al.setSTATUS_PROCESO("1");

            con.GuardaStatus(al);

            ListaInteresados = con.ConsultaInteresados(al);
            banMuestraForm = false;
            al.setCURPA("");

            Constantes.enviaMensajeConsola("llego a metodo consulta" + al);

        } catch (Exception e) {
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ActualizaInteresado() {
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

            AlumnoBean bita = new AlumnoBean();

            bita = con.ConsultaDatosxobjeto(al);

            bita.setUSUARIO(usuariocons.getUSUARIO_LOGIN());

            con.GuardaBitacora(bita);

            con.ActualizaInteresado(al);

            al.setID_ALUMNO(con.ConsultaID_ALUMNO(al.getCURP()));
            al.setSTATUS_PROCESO("1");

            con.GuardaStatus(al);

            ListaInteresados = con.ConsultaInteresados(al);
            banMuestraForm = false;
            al.setCURPA("");

            Constantes.enviaMensajeConsola("llego a metodo consulta" + al);

        } catch (Exception e) {
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String EliminaInteresado() {
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

            con.EliminaInteresado(al);

            ListaInteresados = con.ConsultaInteresados(al);

        } catch (Exception e) {
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    //**************************************************PRESELECCION****************************************************
    public String selectPre() {

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

            al.setID_ESCUELA(con.ConsultaID(usuariocons.getUSUARIO()));
            al.setPERFIL(String.valueOf(usuariocons.getPERFIL()));
            al.setID_PERSONA(usuariocons.getID_PERSONA());

            ListaInteresadosPE = con.ConsultaInteresadosPE(al);

            al.setID_ALUMNO("");

            Constantes.enviaMensajeConsola("ListaInteresadosPE: " + ListaInteresadosPE.size());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String RegDatos() {

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

            UnidadesEconomicasDAOImpl con2 = new UnidadesEconomicasDAOImpl();

            Iterator LIP = ListaInteresadosPE.iterator();
            AlumnoBean objg;

            Constantes.enviaMensajeConsola("id alumno: " + al.getID_ALUMNO());

            while (LIP.hasNext()) {
                objg = (AlumnoBean) LIP.next();

                if (al.getID_ALUMNO().equals(objg.getID_ALUMNO())) {

                    al.setCURP(objg.getCURP());
                    al.setNOMBRE_COMPLETO(objg.getNOMBRE_COMPLETO());
                    al.setMATRICULA(objg.getMATRICULA());
                    al.setGRADO(objg.getGRADO());
                    al.setPROMEDIO(objg.getPROMEDIO());
                    al.setSITUACION_ACADEMICA(objg.getSITUACION_ACADEMICA());
                    al.setTEL_CASA(objg.getTEL_CASA());
                    al.setTEL_CEL(objg.getTEL_CEL());
                    al.setEMAIL_INS(objg.getEMAIL_INS());
                    al.setEMAIL_PER(objg.getEMAIL_PER());
                    al.setRES_EVA(objg.getRES_EVA());
                    al.setFECHA_INC_PADRON(objg.getFECHA_INC_PADRON());
                    Constantes.enviaMensajeConsola("fecha" + al.getFECHA_INC_PADRON());
                }

            }
            Constantes.enviaMensajeConsola("nombre_completo" + al.getNOMBRE_COMPLETO());
            Constantes.enviaMensajeConsola("matricula" + al.getMATRICULA());
            Constantes.enviaMensajeConsola("promedio " + al.getPROMEDIO());
            Constantes.enviaMensajeConsola("sit" + al.getSITUACION_ACADEMICA());
            Constantes.enviaMensajeConsola("tc" + al.getTEL_CASA());
            Constantes.enviaMensajeConsola("tcc" + al.getTEL_CEL());
            Constantes.enviaMensajeConsola("email ins" + al.getEMAIL_INS());
            Constantes.enviaMensajeConsola("emil per" + al.getEMAIL_PER());
            Constantes.enviaMensajeConsola("reseva" + al.getRES_EVA());
            Constantes.enviaMensajeConsola("fecha" + al.getFECHA_INC_PADRON());

            ListaEvaluacion = con2.ConsultaCatalogo("500");
            ListaSituacion = con2.ConsultaCatalogo("600");
            ListaPromedio = con2.ConsultaCatalogo("400");

            banMuestraForm1 = true;

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String ActualizaDatos1() {

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
            UnidadesEconomicasDAOImpl con2 = new UnidadesEconomicasDAOImpl();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            AlumnoDAOImpl con = new AlumnoDAOImpl();

            boolean promedio = false;
            boolean situ = false;
            boolean cel = false;
            boolean email = false;
            boolean eva = false;
            boolean fecha = false;

            ListaEvaluacion = con2.ConsultaCatalogo("500");
            ListaSituacion = con2.ConsultaCatalogo("600");
            ListaPromedio = con2.ConsultaCatalogo("400");

            if (al.getPROMEDIO().length() > 0) {

                promedio = true;

            } else {
                addFieldError("ErrorProomedio", "Debe seleccionar un promedio");
                promedio = false;
            }
            if (al.getSITUACION_ACADEMICA().length() > 0) {

                situ = true;

            } else {
                addFieldError("ErrorSitu", "Debe seleccionar la Situación Academica");
                situ = false;
            }
            if (al.getTEL_CEL().length() > 0) {

                cel = true;

            } else {
                addFieldError("ErrorCel", "Debe Ingresar un número celular");
                cel = false;
            }
            if (al.getEMAIL_PER().length() > 0) {

                email = true;

            } else {
                addFieldError("ErrorCorreo", "Debe ingresar un correo electronico personal");
                email = false;
            }
            if (al.getRES_EVA().length() > 0) {

                eva = true;

            } else {
                addFieldError("ErrorEvaluacion", "Debe seleccionar un resultado de evaluación");
                eva = false;
            }

            if (al.getFECHA_INC_PADRON().length() > 0) {

                Constantes.enviaMensajeConsola("fecha recibe: " + al.getFECHA_INC_PADRON());

                String FechaActual = dateFormat.format(new Date());
                Constantes.enviaMensajeConsola("llego actual: " + FechaActual);

                Date fechaInicial = dateFormat.parse(FechaActual);
                Constantes.enviaMensajeConsola("fecha Inicio" + fechaInicial);
                Date fechaFinal = dateFormat.parse(al.getFECHA_INC_PADRON());

                Constantes.enviaMensajeConsola("fecha final" + fechaFinal);

                int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
                System.out.println("Hay " + dias + " dias de diferencia");

                if (dias <= -6 || dias >= 1) {

                    Constantes.enviaMensajeConsola("entro a dias no permitidos");
                    addFieldError("ErrorFechaNoPermitida", "Debe seleccionar una fecha No menor a 5 dias de la actual ni mayor a la actual");
                    fecha = false;

                } else {
                    Constantes.enviaMensajeConsola("entro a dias  permitidos");
                    fecha = true;
                }

            } else {
                addFieldError("ErrorFecha", "Debe seleccionar una fecha de Incorporación al padrón ");
                fecha = false;
            }

            if (promedio && situ && cel && email && eva && fecha) {

                con.ActualizaDatos1(al);

                al.setSTATUS_PROCESO("2");

                con.ActualizaStatus1(al);

                al.setID_ESCUELA(con.ConsultaID(usuariocons.getUSUARIO()));
                al.setPERFIL(String.valueOf(usuariocons.getPERFIL()));
                al.setID_PERSONA(usuariocons.getID_PERSONA());

                ListaInteresadosPE = con.ConsultaInteresadosPE(al);

                Constantes.enviaMensajeConsola("ListaInteresadosPE: " + ListaInteresadosPE.size());

                return "SUCCESS";
            } else {

                banMuestraForm1 = true;
                return "ERROR";

            }

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String ActualizaStatusI() {

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

            al.setFECHA_INC_PADRON(al.getFECHA_INC_PADRON_AUX());
            al.setRES_EVA(al.getRES_EVA_AUX());

            con.ActualizaStatus1(al);

            al.setID_ESCUELA(con.ConsultaID(usuariocons.getUSUARIO()));
            al.setPERFIL(String.valueOf(usuariocons.getPERFIL()));
            al.setID_PERSONA(usuariocons.getID_PERSONA());

            ListaInteresadosPE = con.ConsultaInteresadosPE(al);

            Constantes.enviaMensajeConsola("ListaInteresadosPE: " + ListaInteresadosPE.size());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    //**************************************************VALIDA PRESELECCION****************************************************
    public String ValidaPre() {

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

            al.setID_ESCUELA(con.ConsultaID(usuariocons.getUSUARIO()));
            al.setPERFIL(String.valueOf(usuariocons.getPERFIL()));
            al.setID_PERSONA(usuariocons.getID_PERSONA());

            ListaUE = con.ueIeActiva(al.getID_ESCUELA());
            ListaPreseleccionados = con.ConsultaPreseleccionados(al);

            Iterator LP = ListaPreseleccionados.iterator();
            AlumnoBean objg;

            while (LP.hasNext()) {
                objg = (AlumnoBean) LP.next();

                if (objg.getSTATUS_PROCESO().equals("3")) {

                    objg.setVALIDAR("true");

                } else {
                    objg.setVALIDAR("false");
                }

            }

            Constantes.enviaMensajeConsola("ListaInteresadosPE: " + ListaInteresadosPE.size());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String actualizarEstatus() {

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

            //abriendo la conexion.....
            conecta = con.crearConexion();
            //statement
            objConexion = con.crearStatement(conecta);
            //ListaSecciones=(ArrayList<Bean_Secciones>) superior.ConsSecciones(CveCuestionario);

            boolean valida = false;
            int contador = 0;

            for (int i = 0; i < ListaPreseleccionados.size(); i++) {
                if (ListaPreseleccionados.get(i).getVALIDAR().equals("true")) {

                    if (ListaPreseleccionados.get(i).getID_UE().length() == 0) {

                        contador = contador + 1;

                    }

                }
            }

            Constantes.enviaMensajeConsola("valor contador: " + contador);

            if (contador > 0) {
                addFieldError("ERRORUE", "SI SELECCIONA AL ALUMNO DEBE AGREGAR LA UNIDAD ECONOMICA");
                valida = false;
            } else {
                valida = true;
            }

            if (valida) {

                for (int i = 0; i < ListaPreseleccionados.size(); i++) {

                    al.setID_HISTORICO(ListaPreseleccionados.get(i).getID_HISTORICO());
                    al.setID_UE(ListaPreseleccionados.get(i).getID_UE());

                    if (ListaPreseleccionados.get(i).getVALIDAR().equals("true")) {
                        al.setSTATUS_PROCESO("3");

                    } else {
                        al.setSTATUS_PROCESO("2");
                        al.setID_UE("");
                    }

                    Constantes.enviaMensajeConsola("id historico: " + al.getID_HISTORICO());
                    Constantes.enviaMensajeConsola("id ue: " + al.getID_UE());
                    Constantes.enviaMensajeConsola("validado: " + al.getSTATUS_PROCESO());
                    con.ValidaAlumnos(al);
                }

                //
                //al.setID_ESCUELA(con.ConsultaID(usuariocons.getUSUARIO()));
                //ListaPreseleccionados = con.ConsultaPreseleccionados(al);
                //cerrando conexiones...
                cierraConexiones();

                ValidaPre();

                Constantes.enviaMensajeConsola("ListaPRESELECCIONADOS: " + ListaPreseleccionados.size());

            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    //**************************************************SELECCION de alumnos****************************************************
    public String MuestraListaPE() {

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

            al.setID_ESCUELA(con.ConsultaID(usuariocons.getUSUARIO()));
            al.setPERFIL(String.valueOf(usuariocons.getPERFIL()));
            al.setID_PERSONA(usuariocons.getID_PERSONA());

            ListaPEUE = con.ConsultaPEUE(al);

            al.setAUX_CVE_PRO_EDU("");
            ListaValidados.clear();

            Constantes.enviaMensajeConsola("ListaInteresadosPE: " + ListaInteresadosPE.size());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String consultaAlumnosValidos() {

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

            Constantes.enviaMensajeConsola("aux cve_nom_pro " + al.getAUX_CVE_PRO_EDU());
            al.setID_UE(usuariocons.getID_IE_UE());

            ListaValidados = con.ConsultaAlumnosValidos(al);

            //Constantes.enviaMensajeConsola("ListaInteresadosPE: " + ListaInteresadosPE.size());
            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String AprobarAlumno() {

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

            if (al.getSTATUS_PROCESO().equals("4")) {

                al.setAUX_RES_ACAD(usuariocons.getID_PERSONA());

            }
            if (al.getSTATUS_PROCESO().equals("3")) {
                al.setAUX_RES_ACAD("");
            }

            con.AprobarAlumno(al);

            consultaAlumnosValidos();

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String RechazarAlumno() {

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

            al.setID_UE("");

            con.RechazarAlumno(al);

            consultaAlumnosValidos();

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    //***********************************************REGISTRA ALUMNOS DUAL******************************************************
    public String ConsultaAlumnosAceptados() {

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

            ListaAceptados.clear();

            al.setAUX_RES_ACAD("");
            al.setID_CCT_PLAN("");
            al.setID_IE_UE("");
            al.setAUXIDHISTALUM("");
            programa.setID_RES_PROGEDU("");
            programa.setID_RESUE("");
            alumno.setAUXIDHISTALUM("");
            ListaEstudiantes.clear();
            al.setFECHA_INICIO_DUAL("");
            ListaPlanAlumno.clear();
            ListaProgramasRegistro.clear();
            ListaPlanAlumno.clear();
            ListaProgramasRegistroDatos.clear();
            ListaUE.clear();
            ListaPlanUE.clear();
            ListaResAcad.clear();
            ListaResMentorUE.clear();
            ListaMentorAcad.clear();
            ListaResUE.clear();

            banListaAlu = false;
            banCampAlumno = false;

            al.setID_ESCUELA(con.ConsultaID(usuariocons.getUSUARIO()));
            al.setPERFIL(String.valueOf(usuariocons.getPERFIL()));
            al.setID_PERSONA(usuariocons.getID_PERSONA());

            ListaAceptados = con.ConsultaAceptados(al);

            al.setID_ALUMNO("");
            banMuestraFormDUAL = false;

            Constantes.enviaMensajeConsola("ListaAceptados: " + ListaAceptados.size());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String MuestraForm() {

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

            Iterator LIP = ListaAceptados.iterator();
            AlumnoBean objg;

            Constantes.enviaMensajeConsola("id alumno: " + al.getID_ALUMNO());

            while (LIP.hasNext()) {
                objg = (AlumnoBean) LIP.next();

                if (al.getID_ALUMNO().equals(objg.getID_ALUMNO())) {

                    al.setNOMBRE_COMPLETO(objg.getNOMBRE_COMPLETO());
                    al.setMATRICULA(objg.getMATRICULA());
                    al.setNO_SEGURO(objg.getNO_SEGURO());
                    al.setDOMICILIO(objg.getDOMICILIO());
                    al.setCOLONIA(objg.getCOLONIA());
                    al.setLOCALIDAD(objg.getLOCALIDAD());
                    al.setCP(objg.getCP());
                    al.setCVE_MUN(objg.getCVE_MUN());
                    al.setFECHA_INICIO_DUAL(objg.getFECHA_INICIO_DUAL());
                    al.setCURP_PADRE(objg.getCURP_PADRE());
                    al.setNOM_PADRE(objg.getNOM_PADRE());
                    al.setAPELLIDOP_PADRE(objg.getAPELLIDOP_PADRE());
                    al.setAPELLIDOM_PADRE(objg.getAPELLIDOM_PADRE());
                    al.setTEL_PADRE(objg.getTEL_PADRE());
                    al.setEMAIL_PADRE(objg.getEMAIL_PADRE());
                    al.setDOMICILIO_PADRE(objg.getDOMICILIO_PADRE());
                    al.setCOLONIA_PADRE(objg.getCOLONIA_PADRE());
                    al.setLOCALIDAD_PADRE(objg.getLOCALIDAD_PADRE());
                    al.setCP_PADRE(objg.getCP_PADRE());
                    al.setCVE_MUN_PADRE(objg.getCVE_MUN_PADRE());
                    al.setMISMO_DOMICILIO(objg.getMISMO_DOMICILIO());
                    al.setSTATUS_ALUMNO(objg.getSTATUS_ALUMNO());

                }

            }

            ListaColonia = con.ConsultaColonia(al.getCP());

            if (ListaColonia.size() > 0) {

                Iterator LC = ListaColonia.iterator();
                ColoniasBean objg2;

                while (LC.hasNext()) {
                    objg2 = (ColoniasBean) LC.next();

                    al.setCVE_MUN(objg2.getID_MUNICIPIO());
                    al.setMUNICIPIO(objg2.getMUNICIPIO());

                }

            }

            ListaColoniaP = con.ConsultaColonia(al.getCP_PADRE());

            if (ListaColoniaP.size() > 0) {

                Iterator LC = ListaColoniaP.iterator();
                ColoniasBean objg3;

                while (LC.hasNext()) {
                    objg3 = (ColoniasBean) LC.next();

                    al.setCVE_MUN_PADRE(objg3.getID_MUNICIPIO());
                    al.setMUNICIPIO_PADRE(objg3.getMUNICIPIO());

                }
            }

            String valor = con.consultaHistoricoAlumno(al);

            if (valor.isEmpty()) {

                al.setTIPO_ALUMNO("NUEVO INGRESO");

            } else {
                Constantes.enviaMensajeConsola("entro a reingreso");
                al.setTIPO_ALUMNO("REINGRESO");
            }

            String Cadena = al.getFEC_NAC();
            String sSubCadena = Cadena.substring(6, 10);
            System.out.println(sSubCadena);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date fechaActual = new Date();

            String fechaSistema = dateFormat.format(fechaActual);

            String Cadena2 = fechaSistema;
            String sSubCadena2 = Cadena2.substring(6, 10);
            System.out.println(sSubCadena2);

            Constantes.enviaMensajeConsola("fecha Sistema " + fechaSistema);

            int anioE = 0, anioA = 0, edad = 0;

            anioE = Integer.valueOf(sSubCadena);

            anioA = Integer.valueOf(sSubCadena2);

            edad = anioA - anioE;

            al.setEDAD(String.valueOf(edad));

            Constantes.enviaMensajeConsola("edad: " + edad);

            if (edad < 18) {
                banFormPad = true;

                if (al.getCURP_PADRE().length() > 0) {
                    banMuestraCurpP = true;
                } else {
                    banMuestraCurpP = false;
                }

            } else {
                banFormPad = false;
            }

            banMuestraFormDUAL = true;

            PlanFDAOImpl con2 = new PlanFDAOImpl();

            alumno.setAUXIDHISTALUM(al.getID_HISTORICO());

            banListaAlu = false;
            banCampAlumno = true;
            System.out.println("action.PlanFormacion_Action.eligeAlumno()");

            System.out.println("el valor a consultar es " + alumno.getAUXIDHISTALUM());

            ListaEstudiantes = con2.consultaAlumPlanHit(alumno, usuariocons, escuela);

            for (int i = 0; i < ListaEstudiantes.size(); i++) {

                alumno.setCURP(ListaEstudiantes.get(i).getCURP());
                alumno.setNOMBRE(ListaEstudiantes.get(i).getNOMBRE());
                alumno.setMATRICULA(ListaEstudiantes.get(i).getMATRICULA());
                alumno.setID_UE(ListaEstudiantes.get(i).getID_UE());
                alumno.setID_PLAN(ListaEstudiantes.get(i).getID_PLAN());

            }

            ListaUE = con2.consultaUE(alumno, usuariocons, escuela);

            System.out.println("unidad" + ListaUE.size());
            for (int j = 0; j < ListaUE.size(); j++) {
                unidad.setRFC(ListaUE.get(j).getRFC());
                unidad.setRAZON_SOCIAL(ListaUE.get(j).getRAZON_SOCIAL());
            }

            int perfil = 0;

            programa.setID_RESUE(al.getAUX_RES_ACAD());
            programa.setID_RES_PROGEDU(usuariocons.getID_PERSONA());

            perfil = 26;
            ListaResUE = con2.listaResUE(alumno, usuariocons, escuela, perfil);

            perfil = 28;
            ListaResMentorUE = con2.listaResUE(alumno, usuariocons, escuela, perfil);

            ListaResAcad = con2.listaResAcad(alumno, usuariocons, escuela, perfil);

            ListaMentorAcad = con2.listaMentorAcad(alumno, usuariocons, escuela, perfil);

            ListaPlanUE = con2.listaPlanUE(alumno, usuariocons, escuela, perfil);

            banregistro = true;
            banactualiza = false;

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String MuestraFormActualiza() {

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

            Iterator LIP = ListaAceptados.iterator();
            AlumnoBean objg;

            Constantes.enviaMensajeConsola("id alumno: " + al.getID_ALUMNO());

            while (LIP.hasNext()) {
                objg = (AlumnoBean) LIP.next();

                if (al.getID_ALUMNO().equals(objg.getID_ALUMNO())) {

                    al.setNOMBRE_COMPLETO(objg.getNOMBRE_COMPLETO());
                    al.setMATRICULA(objg.getMATRICULA());
                    al.setNO_SEGURO(objg.getNO_SEGURO());
                    al.setDOMICILIO(objg.getDOMICILIO());
                    al.setCOLONIA(objg.getCOLONIA());
                    al.setLOCALIDAD(objg.getLOCALIDAD());
                    al.setCP(objg.getCP());
                    al.setCVE_MUN(objg.getCVE_MUN());
                    al.setFECHA_INICIO_DUAL(objg.getFECHA_INICIO_DUAL());
                    al.setCURP_PADRE(objg.getCURP_PADRE());
                    al.setNOM_PADRE(objg.getNOM_PADRE());
                    al.setAPELLIDOP_PADRE(objg.getAPELLIDOP_PADRE());
                    al.setAPELLIDOM_PADRE(objg.getAPELLIDOM_PADRE());
                    al.setTEL_PADRE(objg.getTEL_PADRE());
                    al.setEMAIL_PADRE(objg.getEMAIL_PADRE());
                    al.setDOMICILIO_PADRE(objg.getDOMICILIO_PADRE());
                    al.setCOLONIA_PADRE(objg.getCOLONIA_PADRE());
                    al.setLOCALIDAD_PADRE(objg.getLOCALIDAD_PADRE());
                    al.setCP_PADRE(objg.getCP_PADRE());
                    al.setCVE_MUN_PADRE(objg.getCVE_MUN_PADRE());
                    al.setMISMO_DOMICILIO(objg.getMISMO_DOMICILIO());
                    al.setSTATUS_ALUMNO(objg.getSTATUS_ALUMNO());

                }

            }

           ListaColonia = con.ConsultaColonia(al.getCP());

            if (ListaColonia.size() > 0) {

                Iterator LC = ListaColonia.iterator();
                ColoniasBean objg2;

                while (LC.hasNext()) {
                    objg2 = (ColoniasBean) LC.next();

                    al.setCVE_MUN(objg2.getID_MUNICIPIO());
                    al.setMUNICIPIO(objg2.getMUNICIPIO());

                }

            }

           ListaColoniaP = con.ConsultaColonia(al.getCP_PADRE());

            if (ListaColoniaP.size() > 0) {

                Iterator LC = ListaColoniaP.iterator();
                ColoniasBean objg3;

                while (LC.hasNext()) {
                    objg3 = (ColoniasBean) LC.next();

                    al.setCVE_MUN_PADRE(objg3.getID_MUNICIPIO());
                    al.setMUNICIPIO_PADRE(objg3.getMUNICIPIO());

                }
            }

            String valor = con.consultaHistoricoAlumno(al);

            if (valor.isEmpty()) {

                al.setTIPO_ALUMNO("NUEVO INGRESO");

            } else {
                Constantes.enviaMensajeConsola("entro a reingreso");
                al.setTIPO_ALUMNO("REINGRESO");
            }

            String Cadena = al.getFEC_NAC();
            String sSubCadena = Cadena.substring(6, 10);
            System.out.println(sSubCadena);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date fechaActual = new Date();

            String fechaSistema = dateFormat.format(fechaActual);

            String Cadena2 = fechaSistema;
            String sSubCadena2 = Cadena2.substring(6, 10);
            System.out.println(sSubCadena2);

            Constantes.enviaMensajeConsola("fecha Sistema " + fechaSistema);

            int anioE = 0, anioA = 0, edad = 0;

            anioE = Integer.valueOf(sSubCadena);

            anioA = Integer.valueOf(sSubCadena2);

            edad = anioA - anioE;

            al.setEDAD(String.valueOf(edad));

            Constantes.enviaMensajeConsola("edad: " + edad);

            if (edad < 18) {
                banFormPad = true;

                if (al.getCURP_PADRE().length() > 0) {
                    banMuestraCurpP = true;
                } else {
                    banMuestraCurpP = false;
                }

            } else {
                banFormPad = false;
            }

            banMuestraFormDUAL = true;

            PlanFDAOImpl con2 = new PlanFDAOImpl();

            alumno.setAUXIDHISTALUM(al.getID_HISTORICO());

            banListaAlu = false;
            banCampAlumno = true;
            System.out.println("action.PlanFormacion_Action.eligeAlumno()");

            System.out.println("el valor a consultar es " + alumno.getAUXIDHISTALUM());

            ListaEstudiantes = con2.consultaAlumPlanHit(alumno, usuariocons, escuela);

            for (int i = 0; i < ListaEstudiantes.size(); i++) {

                alumno.setCURP(ListaEstudiantes.get(i).getCURP());
                alumno.setNOMBRE(ListaEstudiantes.get(i).getNOMBRE());
                alumno.setMATRICULA(ListaEstudiantes.get(i).getMATRICULA());
                alumno.setID_UE(ListaEstudiantes.get(i).getID_UE());
                alumno.setID_PLAN(ListaEstudiantes.get(i).getID_PLAN());

            }

            ListaUE = con2.consultaUE(alumno, usuariocons, escuela);

            System.out.println("unidad" + ListaUE.size());
            for (int j = 0; j < ListaUE.size(); j++) {
                unidad.setRFC(ListaUE.get(j).getRFC());
                unidad.setRAZON_SOCIAL(ListaUE.get(j).getRAZON_SOCIAL());
            }

            int perfil = 0;

            programa.setID_RESUE(al.getAUX_RES_ACAD());
            programa.setID_RES_PROGEDU(usuariocons.getID_PERSONA());

            perfil = 26;
            ListaResUE = con2.listaResUE(alumno, usuariocons, escuela, perfil);

            perfil = 28;
            ListaResMentorUE = con2.listaResUE(alumno, usuariocons, escuela, perfil);

            ListaResAcad = con2.listaResAcad(alumno, usuariocons, escuela, perfil);

            ListaMentorAcad = con2.listaMentorAcad(alumno, usuariocons, escuela, perfil);

            ListaPlanUE = con2.listaPlanUE(alumno, usuariocons, escuela, perfil);

            banregistro = false;
            banactualiza = true;

            ListaPlanAlumno = con2.consultaPlanAlumno(alumno, usuariocons, escuela);

            for (int w = 0; w < ListaPlanAlumno.size(); w++) {

                programa.setID_MENTORUE(ListaPlanAlumno.get(w).getID_MENTOR_UE());
                programa.setID_MENTORACAD(ListaPlanAlumno.get(w).getID_MENTOR_ACAD());
                programa.setFECHA_REG_PLAN(ListaPlanAlumno.get(w).getFECHA_INICIOPF());
                programa.setFECHA_TERMINO_PLAN(ListaPlanAlumno.get(w).getFECHA_TERMINOPF());
                programa.setID_PLAN_FORMA(ListaPlanAlumno.get(w).getID_PLAN_FORM());

            }

            consultaPlanEstudiante3();

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String ConsultaCP() {

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

            ListaColonia = con.ConsultaColonia(al.getCP());

            if (ListaColonia.size() > 0) {

                Iterator LC = ListaColonia.iterator();
                ColoniasBean objg;

                while (LC.hasNext()) {
                    objg = (ColoniasBean) LC.next();

                    al.setCVE_MUN(objg.getID_MUNICIPIO());
                    al.setMUNICIPIO(objg.getMUNICIPIO());

                }
            } else {
                addFieldError("NoCP", "Codigo Postal No encontrado favor de verificar");
            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String consultaCurpP() {
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

            DatosAlumno = con.ConsultaDatosID(al);

            Constantes.enviaMensajeConsola("datos alumo: " + DatosAlumno.size());

            if (DatosAlumno.size() > 0) {

                Iterator DA = DatosAlumno.iterator();
                AlumnoBean gobj;

                while (DA.hasNext()) {
                    gobj = (AlumnoBean) DA.next();
                    al.setCURP_PADRE(gobj.getCURP_PADRE());
                    al.setNOM_PADRE(gobj.getNOM_PADRE());
                    al.setAPELLIDOP_PADRE(gobj.getAPELLIDOP_PADRE());
                    al.setAPELLIDOM_PADRE(gobj.getAPELLIDOM_PADRE());
                    al.setTEL_PADRE(gobj.getTEL_PADRE());
                    al.setEMAIL_PADRE(gobj.getEMAIL_PADRE());
                    al.setMISMO_DOMICILIO(gobj.getMISMO_DOMICILIO());
                    al.setDOMICILIO_PADRE(gobj.getDOMICILIO_PADRE());
                    al.setLOCALIDAD_PADRE(gobj.getLOCALIDAD_PADRE());
                    al.setCP_PADRE(gobj.getCP_PADRE());
                    al.setCOLONIA_PADRE(gobj.getCOLONIA_PADRE());
                    al.setMUNICIPIO_PADRE(gobj.getMUNICIPIO_PADRE());
                    al.setCVE_MUN_PADRE(gobj.getCVE_MUN_PADRE());

                }
                banMuestraCurpP = true;

            }

            Constantes.enviaMensajeConsola("NOM PADRE: " + al.getNOM_PADRE());
            Constantes.enviaMensajeConsola("AP PADRE: " + al.getAPELLIDOP_PADRE());
            Constantes.enviaMensajeConsola("AM PADRE: " + al.getAPELLIDOM_PADRE());

            if (al.getNOM_PADRE() == null && al.getAPELLIDOP_PADRE() == null) {

                Constantes.enviaMensajeConsola("entro a if 2");
                System.out.println("MICURP ES: " + al.getCURP_PADRE_AUX());
                service = new ConsultaDatosRenapo();
                port = service.getConsultaRenapoPorCurpPort();
                personas = port.consultaPorCurp(al.getCURP_PADRE_AUX());
                //port.consultaPorCurp(micurp)

                Constantes.enviaMensajeConsola("resultado de renapo: " + personas.getResultado());

                if (personas.getResultado().equals("EXITO")) {
                    al.setCURP_PADRE(personas.getCurp());
                    al.setNOM_PADRE(personas.getNombre());
                    al.setAPELLIDOP_PADRE(personas.getApellidoPaterno());
                    al.setAPELLIDOM_PADRE(personas.getApellidoMaterno());

                } else {

                    System.out.println("Resultado            : " + personas.getResultado());
                    System.out.println("Codigo de error      : " + personas.getCodigoError());
                    System.out.println("Descripcion Error    : " + personas.getDescripcionError());

                    addFieldError("ErrorValCurp", personas.getDescripcionError());

                    banMuestraCurpP = false;
                }

                al.setCURP_PADRE_AUX("");

                return "input";
            }

        } catch (Exception e) {
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ConsultaCPP() {

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

            ListaColoniaP = con.ConsultaColonia(al.getCP_PADRE());

            if (ListaColoniaP.size() > 0) {

                Iterator LC = ListaColoniaP.iterator();
                ColoniasBean objg;

                while (LC.hasNext()) {
                    objg = (ColoniasBean) LC.next();

                    al.setCVE_MUN_PADRE(objg.getID_MUNICIPIO());
                    al.setMUNICIPIO_PADRE(objg.getMUNICIPIO());

                }
            } else {
                addFieldError("NoCPP", "Codigo Postal No encontrado favor de verificar");
            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String RegistraDual() {

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

            if (Integer.parseInt(al.getEDAD()) < 18) {

                if (al.getMISMO_DOMICILIO().equals("true")) {

                    al.setDOMICILIO_PADRE("");
                    al.setLOCALIDAD_PADRE("");
                    al.setCP_PADRE("");
                    al.setCOLONIA_PADRE("");
                    al.setCVE_MUN_PADRE("");

                    con.ActualizaDatosDUAL(al);

                } else {
                    con.ActualizaDatosDUAL(al);
                }

            } else {

                al.setCURP_PADRE("");
                al.setNOM_PADRE("");
                al.setAPELLIDOP_PADRE("");
                al.setAPELLIDOM_PADRE("");
                al.setTEL_PADRE("");
                al.setEMAIL_PADRE("");
                al.setMISMO_DOMICILIO("");
                al.setDOMICILIO_PADRE("");
                al.setLOCALIDAD_PADRE("");
                al.setCP_PADRE("");
                al.setCOLONIA_PADRE("");
                al.setCVE_MUN_PADRE("");

                con.ActualizaDatosDUAL(al);

            }

            al.setSTATUS_PROCESO("5");

            con.ActualizaStatusDUAL(al);

            ConsultaAlumnosAceptados();

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String validaFechaInicoDual() {

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

            String fecha_inicio = programa.getFECHA_REG_PLAN();

            String ano = fecha_inicio.substring(6, 10);
            String diames = fecha_inicio.substring(0, 5);

            int ano_defaul = 0;
            ano_defaul = Integer.parseInt(ano) + 1;

            programa.setFECHA_TERMINO_PLAN(diames + ano_defaul);

            return "SUCCESS";
        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public static String enviarMail(String mail) {
        String rol = "";
        //obtener funcion

        Properties properties = new Properties();
        String siEnvio = "NO";

        //CORREOOOOO SUSTITUIR
        try {

            properties.put("mail.smtp.host", "smtp.office365.com");	//smtp.live.com
            properties.put("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.user", "dual.msys@edugem.gob.mx"); //ericamh30@hotmail.com
            properties.setProperty("mail.password", "F@bel@#041287"); //PSW
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", "587"); //587

            //sesion
            Session mailSession = Session.getInstance(
                    properties, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication("dual.msys@edugem.gob.mx",
                            "F@bel@#041287");
                }
            });

            System.out.println("inicie sesion");

            //Construyendo el Mensaje
            Message msg = new MimeMessage(mailSession);
            msg.setSubject("Registro de Aplicadores del Concurso de Asignación de la Zona Metropolitana 2019");
            try {
                msg.setFrom(new InternetAddress("dual.msys@edugem.gob.mx", "Dual"));
            } catch (Exception e) {

                // TODO: handle exception
            }

            msg.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(mail)});  //erica.macedo@edugem.gob.mx
            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(
                    "<html>"
                    + "<br/>"
                    + "<h3><span style='color: #8f1309;'>Estimad@</span></h3>"
                    + "<h3><span style='color: #8f1309;'>" + "</span></h3>"
                    + "<p>Realizaste tu registro como " + rol + " para el Concurso de Asignaci&oacute;n a la Educaci&oacute;n Media Superior de la Zona Metropolitana de la Ciudad de M&eacute;xico 2019, con <span style='color: #8f1309;'>No. " + "</span>."
                    + "<br/>"
                    + "<p>En pr&oacute;ximas fechas recibir&aacute;s un correo electr&oacute;nico del Centro Nacional de Evaluaci&oacute;n para la Educaci&oacute;n Superior (CENEVAL) para que tengas acceso a su plataforma y recibas una capacitaci&oacute;n en l&iacute;nea, posteriormente se convocar&aacute; a una capacitaci&oacute;n presencial."
                    + "<br/>"
                    + "<p>Te pedimos estar atento a las indicaciones."
                    + "<br/><br/><br/><br/><br/>"
                    + "<strong><span style='color:#164C06;'>Comisi&oacute;n Metropolitana de Instituciones</span></strong>"
                    + "<br/>"
                    + "<strong><span style='color:#164C06;'>P&uacute;blicas de Educaci&oacute;n Media Superior (COMIPEMS).</span></strong>"
                    + "<br/><br/><br/><br/><br/>"
                    + "</p></html>", "text/html");
            multipart.addBodyPart(htmlPart);
            msg.setContent(multipart);
            javax.mail.Transport.send(msg, msg.getAllRecipients());
            siEnvio = "SI";

            return siEnvio;

        } catch (MessagingException me) {

            return "NO";
        }

    }

    public String consultaPlanEstudiante3() {

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
            ListaProgramasRegistro = con2.programasPlanForm(alumno, escuela, usuariocons, programa);

            banCampAlumno = true;

            ListaProgramasRegistroDatos = con2.programasPlanFormDatos(alumno, escuela, usuariocons, programa);

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

    public String guardaPlanFormEst2() {

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
            AlumnoDAOImpl con = new AlumnoDAOImpl();

            ConsultasBusiness con3 = new ConsultasBusiness();

            boolean banResUE = false;
            boolean banMentorUE = false;
            boolean banResAcad = false;
            boolean banMetorAcad = false;
            boolean planForm = false;
            boolean fechaInicio = false;
            boolean fechaTermino = false;
            String fecha;
            boolean res = false;
            boolean res2 = false;
            boolean validafecha = false;

            if (programa.getID_RESUE().length() > 0) {
                banResUE = true;

            } else {

                banResUE = false;
                addFieldError("ERRORRESUE", "Favor de seleccionar a un Responsble");
            }

            if (programa.getID_MENTORUE().length() > 0) {
                banMentorUE = true;

            } else {

                banMentorUE = false;
                addFieldError("ERRORMENTORUE", "Favor de seleccionar a un Mentor");
            }

            if (programa.getID_MENTORACAD().length() > 0) {
                banMetorAcad = true;

            } else {

                banMetorAcad = false;
                addFieldError("ERRORMENTORACAD", "Favor de seleccionar a un Mentor");
            }

            res = validarFecha(programa.getFECHA_REG_PLAN());

            if (res == true) {

                fechaInicio = true;

            } else {

                fechaInicio = false;
                addFieldError("ERRORFECHAINICIO", "Favor de seleccionar una fecha valida");
            }

            if (programa.getID_PLAN_FORMA().length() > 0) {

                planForm = true;

            } else {

                planForm = false;
                addFieldError("ERRORPLANFORM", "Favor de seleccionar un plan de Formación");
            }
            res2 = validarFecha(programa.getFECHA_TERMINO_PLAN());

            if (res2 == true) {

                fechaTermino = true;

            } else {

                fechaTermino = false;
                addFieldError("ERRORFECHATERMINO", "Favor de seleccionar una fecha valida");
            }

            if (programa.getFECHA_REG_PLAN().length() > 0 && programa.getFECHA_TERMINO_PLAN().length() > 0) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                Date fechaInicial = dateFormat.parse(programa.getFECHA_REG_PLAN());

                Date fechaFinal = dateFormat.parse(programa.getFECHA_TERMINO_PLAN());

                int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);

                if (dias >= 365 && dias <= 1095) {

                    validafecha = true;

                } else {

                    validafecha = false;
                    addFieldError("ERRORINTERVALO", "El tiempo mínimo de duración del Plan es de 1 año y máximo 3 años ");

                }

            }

            System.out.println("sali de la vaidacion de plan de formación ");

            Constantes.enviaMensajeConsola("" + banResUE);
            Constantes.enviaMensajeConsola("" + banMentorUE);
            Constantes.enviaMensajeConsola("" + banMetorAcad);
            Constantes.enviaMensajeConsola("" + planForm);
            Constantes.enviaMensajeConsola("" + fechaInicio);
            Constantes.enviaMensajeConsola("" + fechaTermino);

            if (banResUE && banMentorUE && banMetorAcad && planForm && fechaInicio && fechaTermino && validafecha) {

                if (al.getFECHA_INICIO_DUAL().length() > 0) {

                    Constantes.enviaMensajeConsola("la fecha a actualizar es:" + al.getFECHA_INICIO_DUAL());
                } else {

                    al.setFECHA_INICIO_DUAL(programa.getFECHA_REG_PLAN());

                }

                if (Integer.parseInt(al.getEDAD()) < 18) {

                    if (al.getMISMO_DOMICILIO().equals("true")) {

                        al.setDOMICILIO_PADRE("");
                        al.setLOCALIDAD_PADRE("");
                        al.setCP_PADRE("");
                        al.setCOLONIA_PADRE("");
                        al.setCVE_MUN_PADRE("");

                        con.ActualizaDatosDUAL(al);

                    } else {
                        con.ActualizaDatosDUAL(al);
                    }

                } else {

                    al.setCURP_PADRE("");
                    al.setNOM_PADRE("");
                    al.setAPELLIDOP_PADRE("");
                    al.setAPELLIDOM_PADRE("");
                    al.setTEL_PADRE("");
                    al.setEMAIL_PADRE("");
                    al.setMISMO_DOMICILIO("");
                    al.setDOMICILIO_PADRE("");
                    al.setLOCALIDAD_PADRE("");
                    al.setCP_PADRE("");
                    al.setCOLONIA_PADRE("");
                    al.setCVE_MUN_PADRE("");

                    con.ActualizaDatosDUAL(al);

                }

                objRenapo.setAUXESCUELA(idEsc());
                objRenapo.setPERFIL("29");

                objRenapo.setUSUARIO(al.getID_ALUMNO() + "-EST-" + objRenapo.getAUXESCUELA());
                System.out.println("salio de construcción de usuario");
                objRenapo.setPASSWORD(conspassword());
                System.out.println("salio de cons password");

                usuariocons.setESTATUS_VARIABLE("1");
                usuariocons.setNAMEPERFIL_VARIABLE("Estudiamte Dual");

                objRenapo.setID_ESTUDIANTE(al.getID_ALUMNO());

                String conIdEst = "";

                conIdEst = con3.consultaIdEst(objRenapo, usuariocons);

                if (conIdEst.length() > 0) {

                } else {
                    con3.guardaUserAlu(objRenapo, usuariocons);

                }

                al.setSTATUS_PROCESO("5");

                con.ActualizaStatusDUAL(al);

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

                    con2.GuardaPlanFormaActividadesAlu(conecta, objPreConexion, escuela, objRenapo, programa, alumno);

                }

                con2.ActualizaEstatusAlumno(conecta, objPreConexion, programa, alumno);

                cierraConexiones();

                addFieldError("SEGUARDO", "El Plan de Formación se guardo con éxito");

                ListaProgramasRegistro.clear();
                ListaEstudiantes.size();
                banCampAlumno = false;
                banCampAlumno = false;

                ListaCorreos = con3.consultaCorreo(objRenapo, usuariocons);

                String correo1 = "";
                String correo2 = "";

                if (ListaCorreos.size() > 0) {

                    for (int i = 0; i < ListaCorreos.size(); i++) {

                        correo1 = ListaCorreos.get(i).getEMAIL_INS();
                        correo2 = ListaCorreos.get(i).getEMAIL_PER();
                    }

                    if (correo1.length() > 0) {

                        enviarMail(correo1);

                    }
                    if (correo2.length() > 0) {

                        enviarMail(correo2);

                    }

                }

                ConsultaAlumnosAceptados();

            }

            //  Constantes.enviaMensajeConsola("sali de la consulta de programa educativo con:"+ListaProgramasRegistro.size());
            return "SUCCESS";

        } catch (Exception sqle) {

            TipoException = sqle.getMessage();

            System.out.println("action.PlanFormacion_Action.guardaPlanFormEst()" + TipoException
            );
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

    public String actualizaPlanFormEst2() {

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
            AlumnoDAOImpl con = new AlumnoDAOImpl();

            boolean banResUE = false;
            boolean banMentorUE = false;
            boolean banResAcad = false;
            boolean banMetorAcad = false;
            boolean planForm = false;
            boolean fechaInicio = false;
            boolean fechaTermino = false;
            String fecha;
            boolean res = false;
            boolean res2 = false;
            boolean validafecha = false;

            if (programa.getID_RESUE().length() > 0) {
                banResUE = true;

            } else {

                banResUE = false;
                addFieldError("ERRORRESUE", "Favor de seleccionar a un Responsble");
            }

            if (programa.getID_MENTORUE().length() > 0) {
                banMentorUE = true;

            } else {

                banMentorUE = false;
                addFieldError("ERRORMENTORUE", "Favor de seleccionar a un Mentor");
            }

            if (programa.getID_MENTORACAD().length() > 0) {
                banMetorAcad = true;

            } else {

                banMetorAcad = false;
                addFieldError("ERRORMENTORACAD", "Favor de seleccionar a un Mentor");
            }

            res = validarFecha(programa.getFECHA_REG_PLAN());

            if (res == true) {

                fechaInicio = true;

            } else {

                fechaInicio = false;
                addFieldError("ERRORFECHAINICIO", "Favor de seleccionar una fecha valida");
            }

            if (programa.getID_PLAN_FORMA().length() > 0) {

                planForm = true;

            } else {

                planForm = false;
                addFieldError("ERRORPLANFORM", "Favor de seleccionar un plan de Formación");
            }
            res2 = validarFecha(programa.getFECHA_TERMINO_PLAN());

            if (res2 == true) {

                fechaTermino = true;

            } else {

                fechaTermino = false;
                addFieldError("ERRORFECHATERMINO", "Favor de seleccionar una fecha valida");
            }

            if (programa.getFECHA_REG_PLAN().length() > 0 && programa.getFECHA_TERMINO_PLAN().length() > 0) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                Date fechaInicial = dateFormat.parse(programa.getFECHA_REG_PLAN());

                Date fechaFinal = dateFormat.parse(programa.getFECHA_TERMINO_PLAN());

                int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);

                if (dias >= 365 && dias <= 1095) {

                    validafecha = true;

                } else {

                    validafecha = false;
                    addFieldError("ERRORINTERVALO", "El tiempo mínimo de duración del Plan es de 1 año y máximo 3 años ");

                }

            }

            Constantes.enviaMensajeConsola("" + banResUE);
            Constantes.enviaMensajeConsola("" + banMentorUE);
            Constantes.enviaMensajeConsola("" + banMetorAcad);
            Constantes.enviaMensajeConsola("" + planForm);
            Constantes.enviaMensajeConsola("" + fechaInicio);
            Constantes.enviaMensajeConsola("" + fechaTermino);

            if (banResUE && banMentorUE && banMetorAcad && planForm && fechaInicio && fechaTermino && validafecha) {

                if (al.getFECHA_INICIO_DUAL().length() > 0) {

                    Constantes.enviaMensajeConsola("la fecha a actualizar es:" + al.getFECHA_INICIO_DUAL());
                } else {

                    al.setFECHA_INICIO_DUAL(programa.getFECHA_REG_PLAN());

                }

                if (Integer.parseInt(al.getEDAD()) < 18) {

                    if (al.getMISMO_DOMICILIO().equals("true")) {

                        al.setDOMICILIO_PADRE("");
                        al.setLOCALIDAD_PADRE("");
                        al.setCP_PADRE("");
                        al.setCOLONIA_PADRE("");
                        al.setCVE_MUN_PADRE("");

                        con.ActualizaDatosDUAL(al);

                    } else {
                        con.ActualizaDatosDUAL(al);
                    }

                } else {

                    al.setCURP_PADRE("");
                    al.setNOM_PADRE("");
                    al.setAPELLIDOP_PADRE("");
                    al.setAPELLIDOM_PADRE("");
                    al.setTEL_PADRE("");
                    al.setEMAIL_PADRE("");
                    al.setMISMO_DOMICILIO("");
                    al.setDOMICILIO_PADRE("");
                    al.setLOCALIDAD_PADRE("");
                    al.setCP_PADRE("");
                    al.setCOLONIA_PADRE("");
                    al.setCVE_MUN_PADRE("");

                    con.ActualizaDatosDUAL(al);

                }

                conecta = con2.crearConexion();
                //statement
                objConexion = con2.crearStatement(conecta);

                con2.actualizaPlanFormEst(conecta, objPreConexion, escuela, objRenapo, programa, alumno);

                String PlanGuardado = "";

                for (int w = 0; w < ListaPlanAlumno.size(); w++) {

                    PlanGuardado = ListaPlanAlumno.get(w).getID_PLAN_FORM();

                }

                if (PlanGuardado.equals(programa.getID_PLAN_FORMA())) {

                    System.out.println("plan registrado" + PlanGuardado + "nuevo programa" + programa.getID_PLAN_FORMA());

                } else {

                    con2.borrarPlanFormEstAct(conecta, objPreConexion, escuela, objRenapo, programa, alumno);

                    System.out.println("lista registro" + ListaProgramasRegistro.size());

                    Iterator LPR = ListaProgramasRegistro.iterator();
                    programaEsBean obj3;
                    while (LPR.hasNext()) {
                        obj3 = (programaEsBean) LPR.next();

                        programa.setID_MATERIA(obj3.getID_MATERIA());
                        programa.setID_COMPETENCIA(obj3.getID_COMPETENCIA());
                        programa.setID_ACTIVIDAD(obj3.getID_ACTIVIDAD());
                        programa.setHORAS_PLAN(obj3.getID_HORA());
                        programa.setLUGAR_PLAN(obj3.getID_LUGAR());
                        programa.setESCALA_PLAN(obj3.getID_ESCALA());
                        programa.setDES_ACTIVIDAD(obj3.getDES_ACTIVIDAD());
                        programa.setPLAN_ROTACION(obj3.getPLAN_ROTACION());
                        programa.setID_HIST_ALU(obj3.getID_HIST_ALU());

                        System.out.println("Materia:" + programa.getID_MATERIA());

                        System.out.println("competencia:" + programa.getID_COMPETENCIA());
                        System.out.println("actividad" + programa.getID_ACTIVIDAD());
                        System.out.println("horas" + programa.getHORAS_PLAN());
                        System.out.println("lugar" + programa.getLUGAR_PLAN());
                        System.out.println("escala" + programa.getESCALA_PLAN());
                        System.out.println("actividad" + programa.getDES_ACTIVIDAD());
                        System.out.println("plan de rotación" + programa.getPLAN_ROTACION());
                        System.out.println("" + programa.getID_PLAN_FORMA());
                        System.out.println("" + alumno.getAUXIDHISTALUM());

                        con2.GuardaPlanFormaActividadesAluAct(conecta, objPreConexion, escuela, objRenapo, programa, alumno);

                    }

                }

                cierraConexiones();

                addFieldError("SEGUARDO", "El Plan de Formación se guardo con éxito");

                ListaProgramasRegistro.clear();
                ListaEstudiantes.size();
                banCampAlumno = false;
                banCampAlumno = false;

                ConsultaAlumnosAceptados();

            }

            //  Constantes.enviaMensajeConsola("sali de la consulta de programa educativo con:"+ListaProgramasRegistro.size());
            return "SUCCESS";

        } catch (Exception sqle) {

            TipoException = sqle.getMessage();

            System.out.println("action.PlanFormacion_Action.guardaPlanFormEst()" + TipoException
            );
            return "ERROR";
        }

    }

    public String ActualizaStatusA() {

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

            al.setSTATUS_PROCESO("4");

            con.ActualizaStatusDUAL(al);

            ConsultaAlumnosAceptados();

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String RepActAlu() {

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
                 ListaPlanAluMateria.clear();
            ListaPlanAluMateriaCom.clear();
            banRegAluNiveMS=false;
            banRegAluNiveS=false;
             alumno.setAUXIDHISTALUM("");
             programa.setAUX_IDCOMPETENCIA("");
             banActualiza=false;
             banGuarda=false;
             banVisualiza=false;
             programa.setID_PLAN_FORMA("");
             ListaProgramasRegistro.clear();
             
             
             String nivel = "";
            nivel = con2.nivel(escuela, usuariocons);
            
            if(nivel.equals("1")){
                 ListaPlanFormAlu=con2.planFormAluMs(alumno, escuela, usuariocons, programa);
              
                
                
            }
            if(nivel.equals("2")){
                
              ListaPlanFormAlu=con2.planFormAlu(alumno, escuela, usuariocons, programa);
                
            }
             
             
             
             
             
          
            
          

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    public String RepActAluMUE() {

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
                 ListaPlanAluMateria.clear();
            ListaPlanAluMateriaCom.clear();
            banRegAluNiveMS=false;
            banRegAluNiveS=false;
             alumno.setAUXIDHISTALUM("");
             programa.setAUX_IDCOMPETENCIA("");
             banActualiza=false;
             banGuarda=false;
             banVisualiza=false;
             
          
            
            ListaPlanFormAlu=con2.planFormAlu(alumno, escuela, usuariocons, programa);

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
     public String EvaluaActEst() {

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
                 ListaPlanAluMateria.clear();
            ListaPlanAluMateriaCom.clear();
            banRegAluNiveMS=false;
            banRegAluNiveS=false;
             alumno.setAUXIDHISTALUM("");
             programa.setAUX_IDCOMPETENCIA("");
             banActualiza=false;
             banGuarda=false;
             banVisualiza=false;
             programa.setFECHA_INICIOPF("");
             programa.setFECHA_INICIO_PF("");
             programa.setFECHA_TERMINOPF("");
             programa.setFECHA_TERMINO_PLAN("");
               programa.setAUX_INICIOSEMANA("");
                              programa.setAUX_FINSEMANA("");
             
                String nivel = "";
            nivel = con2.nivel(escuela, usuariocons);
            
            if(nivel.equals("1")){
               ListaPlanFormAlu=con2.evaluaActEstMS(alumno, escuela, usuariocons, programa);
              
                
                
            }
            if(nivel.equals("2")){
                
             
                  ListaPlanFormAlu=con2.evaluaActEst(alumno, escuela, usuariocons, programa);
            }
             
          
            
          

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
     
      public String EvalEstMA() {

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
                 ListaPlanAluMateria.clear();
            ListaPlanAluMateriaCom.clear();
            banRegAluNiveMS=false;
            banRegAluNiveS=false;
             alumno.setAUXIDHISTALUM("");
             programa.setAUX_IDCOMPETENCIA("");
             banActualiza=false;
             banGuarda=false;
             banVisualiza=false;
             
             
             
                String nivel = "";
            nivel = con2.nivel(escuela, usuariocons);
            
            if(nivel.equals("1")){
               ListaPlanFormAlu=con2.evaluaActEstMSMA(alumno, escuela, usuariocons, programa);
              
                
                
            }
            if(nivel.equals("2")){
                
             
                 ListaPlanFormAlu=con2.evaluaActEst2MA(alumno, escuela, usuariocons, programa);
            }
             
          
            
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    
    public String eligePlanFormAlu() {

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
             
             String nivel = "";
            ListaPlanAluMateria.clear();
            ListaPlanAluMateriaCom.clear();
            ListaPlanAluMateriaComAct.clear();
           

            
            banRegAluNiveMS=false;
            banRegAluNiveS=false;
         

            nivel = "";
            nivel = con2.nivel(escuela, usuariocons);
            
            if(nivel.equals("1")){
                 banRegAluNiveMS=true;
                 
              
                 
                  Iterator LPFA= ListaPlanFormAlu.iterator();
               programaEsBean obj;
                while (LPFA.hasNext()) {
                    obj = (programaEsBean) LPFA.next();
                    if(obj.getID_HIST_ALUM().equals(alumno.getAUXIDHISTALUM())){
                        programa.setFECHA_INICIOPF(obj.getFECHA_INICIOPF());
                        programa.setFECHA_TERMINOPF(obj.getFECHA_TERMINOPF());
                    }
                    
                    
                }
                
                   ListaSemanas=con2.listaSemanas(alumno, escuela, usuariocons, programa);
                   
              
                         
                 
                 
                
            }
            if(nivel.equals("2")){
                
              banRegAluNiveS=true;  
              ListaPlanAluMateria=con2.planFormAluMateria(alumno, escuela, usuariocons, programa);
                
            }
            
         
          
            
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
     public String eligePlanFormMUE() {

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
             
             String nivel = "";
            ListaPlanAluMateria.clear();
            ListaPlanAluMateriaCom.clear();
            ListaPlanAluMateriaComAct.clear();
           

            
            banRegAluNiveMS=false;
            banRegAluNiveS=false;
         

            nivel = "";
            nivel = con2.nivel(escuela, usuariocons);
            
            if(nivel.equals("1")){
                 banRegAluNiveMS=true;
                 
                   Iterator LPFA= ListaPlanFormAlu.iterator();
               programaEsBean obj;
                while (LPFA.hasNext()) {
                    obj = (programaEsBean) LPFA.next();
                    if(obj.getID_HIST_ALUM().equals(alumno.getAUXIDHISTALUM())){
                        programa.setFECHA_INICIOPF(obj.getFECHA_INICIOPF());
                        programa.setFECHA_TERMINOPF(obj.getFECHA_TERMINOPF());
                    }
                    
                    
                }
                
                   ListaSemanas=con2.listaSemanas(alumno, escuela, usuariocons, programa);
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                
            }
            if(nivel.equals("2")){
                
              banRegAluNiveS=true;  
              ListaPlanAluMateria=con2.planFormAluMateria(alumno, escuela, usuariocons, programa);
                
            }
            
         
          
            
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
     
     
     
     
      public String eligePlanFormMA() {

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
             
             String nivel = "";
            ListaPlanAluMateria.clear();
            ListaPlanAluMateriaCom.clear();
            ListaPlanAluMateriaComAct.clear();
           

            
            banRegAluNiveMS=false;
            banRegAluNiveS=false;
         

            nivel = "";
            nivel = con2.nivel(escuela, usuariocons);
            
            if(nivel.equals("1")){
                 banRegAluNiveMS=true;
                 
                   Iterator LPFA= ListaPlanFormAlu.iterator();
               programaEsBean obj;
                while (LPFA.hasNext()) {
                    obj = (programaEsBean) LPFA.next();
                    if(obj.getID_HIST_ALUM().equals(alumno.getAUXIDHISTALUM())){
                        programa.setFECHA_INICIOPF(obj.getFECHA_INICIOPF());
                        programa.setFECHA_TERMINOPF(obj.getFECHA_TERMINOPF());
                    }
                    
                    
                }
                
                   ListaSemanas=con2.listaSemanas(alumno, escuela, usuariocons, programa);
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                
            }
            if(nivel.equals("2")){
                
              banRegAluNiveS=true;  
              ListaPlanAluMateria=con2.planFormAluMateria(alumno, escuela, usuariocons, programa);
                
            }
            
         
         
          
            
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
     
    public String eligePlanAsigMateria() {

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
             ListaPlanAluMateriaCom.clear();
             ListaPlanAluMateriaComAct.clear();
             programa.setAUX_IDCOMPETENCIA("");
             programa.setEDITA("");
             
             
           
         ListaPlanAluMateriaCom=con2.planFormAluMateriaCom(alumno, escuela, usuariocons, programa);
         
        
         
         
         
         
         
          
            Constantes.enviaMensajeConsola("EL TAÑO TOTAL DE LA LISTA ES DE "+ListaPlanAluMateriaCom.size());
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
    
     public String eligeSemana() {

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
           
             
               
                ListaRegistroEstMs= con2.listaRegistroEstMs(alumno, escuela, usuariocons, programa);
             
                     
                     
             Iterator ES= ListaSemanas.iterator();
               programaEsBean obj;
                while (ES.hasNext()) {
                    obj = (programaEsBean) ES.next();
                    if(obj.getID_SEMANA().equals(programa.getID_SEMANA())){
                        programa.setAUX_INICIOSEMANA(obj.getINICIO_SEMANA());
                        programa.setAUX_FINSEMANA(obj.getFIN_SEMANA());
                        programa.setESTATUS_REG(obj.getESTATUS_REG());
                       
                    }
                    
                    
                    
                }
                 ListaFechas=con2.fechas(alumno, escuela, usuariocons, programa);
                System.out.println("Estatus Reg"+programa.getESTATUS_REG());
             
                 if(programa.getESTATUS_REG().equals("NO REGISTRADO")){
                     
                     Constantes.enviaMensajeConsola("ENTRE A VALIDAR ESTE DATO");
                    
                    banGuarda=true;
                    banActualiza=false;
                    banVisualiza=false;
                    
                    
                 
                    
                    
                    
                    
                }
                 if(programa.getESTATUS_REG().equals("REGISTRADO")){
                     
                     
                       Constantes.enviaMensajeConsola("ENTRE A VALIDAR ESTE DATO");
                     
                      banGuarda=false;
                    banActualiza=false;
                        banVisualiza=true;
                        
                       
                        
                         ListaRegistroEstMs=con2.listaReporteMsEst(alumno, escuela, usuariocons, programa);  
                         
                         Iterator LREMS=ListaRegistroEstMs.iterator();
                         programaEsBean obj3;
                         
                         while (LREMS.hasNext()) {
                        obj3= (programaEsBean) LREMS.next();
                        
                        
                        
                        obj3.setID_FECHA(obj3.getID_DIA());
                        obj3.setAUTOESTUDIO(obj3.getAUTO_ESTUDIO());
                        obj3.setOBSERVACIONES(obj3.getOBSERVACION());
                        obj3.setRUTA_EVIDENCIA(rutaArchivosRemota+obj3.getRUTA_EVIDENCIA());
                        
                        
                    
                        
                        
                        
                         
                     }
                        
                        
                        
                        
                 }
                
                
                
               
                
                 
                
                
           
       
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
     
     public String eligeSemanaMue() {

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
           
             
               
                ListaRegistroEstMs= con2.listaRegistroEstMs(alumno, escuela, usuariocons, programa);
             
                     
                     
             Iterator ES= ListaSemanas.iterator();
               programaEsBean obj;
                while (ES.hasNext()) {
                    obj = (programaEsBean) ES.next();
                    if(obj.getID_SEMANA().equals(programa.getID_SEMANA())){
                        programa.setAUX_INICIOSEMANA(obj.getINICIO_SEMANA());
                        programa.setAUX_FINSEMANA(obj.getFIN_SEMANA());
                        programa.setESTATUS_REG(obj.getESTATUS_REG());
                       
                    }
                    
                    
                    
                }
                 ListaFechas=con2.fechas(alumno, escuela, usuariocons, programa);
                System.out.println("Estatus Reg"+programa.getESTATUS_REG());
             
                 if(programa.getESTATUS_REG().equals("NO REGISTRADO")){
                    
                    banGuarda=false;
                    banActualiza=false;
                    banVisualiza=false;
                    banGuardaEvalMUE=false;
                    
                    
                    
                      addFieldError("NOEVALMUE", "***No se puede evaluar el reporte porque el alumno aún no lo registra***");
                    
                 
                    
                    
                    
                    
                }
                 if(programa.getESTATUS_REG().equals("REGISTRADO")){
                 
                     banGuarda = false;
                     banActualiza = false;
                     banVisualiza = true;
                     banGuardaEvalMUE = true;

                        
                          ListaRubrica=con2.rubrica(alumno, escuela, usuariocons, programa);    
                         ListaRegistroEstMs=con2.listaReporteMsEst(alumno, escuela, usuariocons, programa);  
                         
                         Iterator LREMS=ListaRegistroEstMs.iterator();
                         programaEsBean obj3;
                         
                         while (LREMS.hasNext()) {
                        obj3= (programaEsBean) LREMS.next();
                        
                        
                        
                        obj3.setID_FECHA(obj3.getID_DIA());
                        obj3.setAUTOESTUDIO(obj3.getAUTO_ESTUDIO());
                        obj3.setOBSERVACIONES(obj3.getOBSERVACION());
                        obj3.setRUTA_EVIDENCIA(rutaArchivosRemota+obj3.getRUTA_EVIDENCIA());
                        
                        
                    
                        
                        
                        
                         
                     }
                        
                        
                        
                        
                 }
                
                
                
               
                
                 
                
                
           
       
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
     
     public String eligeSemanaMa() {

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
           
             
               
                ListaRegistroEstMs= con2.listaRegistroEstMs(alumno, escuela, usuariocons, programa);
                 ListaProgramasRegistro = con2.programasPlanForm(alumno, escuela, usuariocons, programa);
             
                     
                     
             Iterator ES= ListaSemanas.iterator();
               programaEsBean obj;
                while (ES.hasNext()) {
                    obj = (programaEsBean) ES.next();
                    if(obj.getID_SEMANA().equals(programa.getID_SEMANA())){
                        programa.setAUX_INICIOSEMANA(obj.getINICIO_SEMANA());
                        programa.setAUX_FINSEMANA(obj.getFIN_SEMANA());
                        programa.setESTATUS_REG(obj.getESTATUS_REG());
                        programa.setESTATUS_EVAL_MUE(obj.getESTATUS_EVAL_MUE());
                        programa.setESTATUS_EVAL_MA(obj.getESTATUS_EVAL_MA());
                       
                    }
                    
                    
                    
                }
                 ListaFechas=con2.fechas(alumno, escuela, usuariocons, programa);
                System.out.println("Estatus Reg"+programa.getESTATUS_REG());
             
                 if(programa.getESTATUS_EVAL_MUE().equals("NO REGISTRADO")){
                    
                    banGuarda=false;
                    banActualiza=false;
                    banVisualiza=false;
                    banGuardaEvalMUE=false;
                    
                    
                    
                      addFieldError("NOEVALMUE", "***Reporte no evaluado por el Mentor de la Unidad Económica***");
                    
                 
                    
                    
                    
                    
                }
                 if(programa.getESTATUS_EVAL_MUE().equals("REGISTRADO")){
                 
                     banGuarda = false;
                     banActualiza = false;
                     banVisualiza = true;
                     banGuardaEvalMUE = true;

                        
                          ListaRubrica=con2.rubrica(alumno, escuela, usuariocons, programa);    
                         ListaRegistroEstMs=con2.listaReporteMsEst(alumno, escuela, usuariocons, programa);  
                         
                         Iterator LREMS=ListaRegistroEstMs.iterator();
                         programaEsBean obj3;
                         
                         while (LREMS.hasNext()) {
                        obj3= (programaEsBean) LREMS.next();
                        
                        
                        
                        obj3.setID_FECHA(obj3.getID_DIA());
                        obj3.setAUTOESTUDIO(obj3.getAUTO_ESTUDIO());
                        obj3.setOBSERVACIONES(obj3.getOBSERVACION());
                        obj3.setRUTA_EVIDENCIA(rutaArchivosRemota+obj3.getRUTA_EVIDENCIA());
                        obj3.setID_RUBRICA(obj3.getEVAL_MA());
                        obj3.setID_ACT_EVALUA(obj3.getID_ACT_EVALUA());
                        
                        
                    
                        
                        
                        
                         
                     }
                        
                        
                        
                        
                 }
                
                
                
               
                
                 
                
                
           
       
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
     public String eligePlanAsigMateriaMUE() {

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
             ListaPlanAluMateriaCom.clear();
             ListaPlanAluMateriaComAct.clear();
             programa.setAUX_IDCOMPETENCIA("");
             programa.setEDITA("");
             
             
           
         ListaPlanAluMateriaCom=con2.planFormAluMateriaCom(alumno, escuela, usuariocons, programa);
         
        
         
         
         
         
         
          
            Constantes.enviaMensajeConsola("EL TAÑO TOTAL DE LA LISTA ES DE "+ListaPlanAluMateriaCom.size());
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
     
      public String eligePlanAsigMateriaMA() {

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
             ListaPlanAluMateriaCom.clear();
             ListaPlanAluMateriaComAct.clear();
             programa.setAUX_IDCOMPETENCIA("");
           
             
             
           
         ListaPlanAluMateriaCom=con2.planFormAluMateriaCom(alumno, escuela, usuariocons, programa);
         
        
         
         
         
         
         
          
            Constantes.enviaMensajeConsola("EL TAÑO TOTAL DE LA LISTA ES DE "+ListaPlanAluMateriaCom.size());
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
      
      public String HabilitaEditRegEst() {

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
             
             
             
           
                  
         con2.actualizaEditaReporteEst(alumno, programa);
         programa.setEDITA("");
         
         
         eligePlanAsigMateriaMA();
         
         
         
         
         
          
            Constantes.enviaMensajeConsola("EL TAÑO TOTAL DE LA LISTA ES DE "+ListaPlanAluMateriaCom.size());
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
      
     public void validate2() {

        try {
//agregando la validacion de tipo de archivo...
            if (archiFileName.toLowerCase() != null) {
                Constantes.enviaMensajeConsola("--EL ARCHIVO ES .... " + archiFileName.toLowerCase());

                if (!archiFileName.toLowerCase().contains(".pdf")) {

                    addFieldError("archierror", "*** La extensión del archivo no es aceptada debe ser (pdf)***");
                    Constantes.enviaMensajeConsola("--EL ARCHIVO ES DIFERENTE DE PDF .... " + archiFileName.toLowerCase());
                    banT = true;

                }

                //if (archiFileName.length() > 2097152 ) 
                if (5000000 <= FileUtils.sizeOf(archi)) {

                    Constantes.enviaMensajeConsola("--EL ARCHIVO ES MAYOR .... " + archiFileName.toLowerCase());
                    addFieldError("archierror", "*** No se permiten archivos mayores a 5MB ***");

                    banT = true;

                }

            }
        } catch (Exception e) {
            TipoError = "SESSION";
            TipoException = e.getMessage();

        }

    }
     
      public void validate3(String archiFileName) {

        try {
//agregando la validacion de tipo de archivo...
            if (archiFileName.toLowerCase() != null) {
                Constantes.enviaMensajeConsola("--EL ARCHIVO ES .... " + archiFileName.toLowerCase());

                if (!archiFileName.toLowerCase().contains(".pdf")) {

                    addFieldError("archierror", "*** La extensión del archivo no es aceptada debe ser (pdf)***");
                    Constantes.enviaMensajeConsola("--EL ARCHIVO ES DIFERENTE DE PDF .... " + archiFileName.toLowerCase());
                    banT = true;

                }

                //if (archiFileName.length() > 2097152 ) 
                if (2000000 <= FileUtils.sizeOf(archi)) {

                    Constantes.enviaMensajeConsola("--EL ARCHIVO ES MAYOR .... " + archiFileName.toLowerCase());
                    addFieldError("archierror", "*** No se permiten archivos mayores a 5MB ***");

                    banT = true;

                }

            }
        } catch (Exception e) {
            TipoError = "SESSION";
            TipoException = e.getMessage();

        }

    }
      
    public String eligePlanAsigMateriaCom() {

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
             
           
       ListaPlanAluMateriaComAct=con2.planFormAluMateriaComAct(alumno, escuela, usuariocons, programa);
       
        String actualiza="";
         
         Iterator LPAM =ListaPlanAluMateriaCom.iterator();
            programaEsBean obj;
            
            while (LPAM.hasNext()) {
                obj = (programaEsBean) LPAM.next();
                
                actualiza=obj.getEDITA();
                
                programa.setMARCO_TEORICO(obj.getMARCO_TEORICO());
                programa.setDES_ACT(obj.getDES_ACT());
                programa.setRUTA_EVIDENCIAS(Constantes.rutaArch + obj.getRUTA_EVIDENCIAS());
                programa.setEDITA(obj.getEDITA());
                
                
                
                
            }
            
            Constantes.enviaMensajeConsola("EL ICONO DE EFDITA ES "+actualiza);
            
            if(actualiza.equals("1")){
                
                banActualiza=false;
                banGuarda=false;
                banVisualiza=true;
                
            }
            if(actualiza.equals("2")){
                banActualiza=true;
                banGuarda=false;
                  banVisualiza=false;
            }
            
             if(actualiza.equals("0")){
                banActualiza=false;
                banGuarda=true;
                  banVisualiza=false;
            }
       
       
          
           Constantes.enviaMensajeConsola("EL TAÑO TOTAL DE LA LISTA ES DE "+ListaPlanAluMateriaComAct.size());
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
       public String eligePlanAsigMateriaComMUE() {

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
             
         ListaRubrica=con2.rubrica(alumno, escuela, usuariocons, programa);    
           
       ListaPlanAluMateriaComAct=con2.planFormAluMateriaComAct(alumno, escuela, usuariocons, programa);
       
        String actualiza="";
         
         Iterator LPAM =ListaPlanAluMateriaCom.iterator();
            programaEsBean obj;
            
            while (LPAM.hasNext()) {
                obj = (programaEsBean) LPAM.next();
                
                actualiza=obj.getEDITA();
                
                programa.setMARCO_TEORICO(obj.getMARCO_TEORICO());
                programa.setDES_ACT(obj.getDES_ACT());
                programa.setRUTA_EVIDENCIAS(Constantes.rutaArch + obj.getRUTA_EVIDENCIAS());
                programa.setEDITA(obj.getEDITA());
                
                
                
                
            }
            
            Constantes.enviaMensajeConsola("EL ICONO DE EFDITA ES "+actualiza);
            
            if(actualiza.equals("1")){
                
                banActualiza=false;
                banGuarda=false;
                banVisualiza=true;
                
            }
            if(actualiza.equals("2")){
                banActualiza=true;
                banGuarda=false;
                  banVisualiza=false;
            }
            
             if(actualiza.equals("0")){
                banActualiza=false;
                banGuarda=true;
                  banVisualiza=false;
            }
       
       
          
           Constantes.enviaMensajeConsola("EL TAÑO TOTAL DE LA LISTA ES DE "+ListaPlanAluMateriaComAct.size());
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
        public String eligePlanAsigMateriaComMA() {

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
             
         ListaRubrica=con2.rubrica(alumno, escuela, usuariocons, programa);    
           
       ListaPlanAluMateriaComAct=con2.planFormAluMateriaComAct(alumno, escuela, usuariocons, programa);
       
        String actualiza="";
         
         Iterator LPAM =ListaPlanAluMateriaCom.iterator();
            programaEsBean obj;
            
            while (LPAM.hasNext()) {
                obj = (programaEsBean) LPAM.next();
                
                actualiza=obj.getEDITA();
                
                programa.setMARCO_TEORICO(obj.getMARCO_TEORICO());
                programa.setDES_ACT(obj.getDES_ACT());
                programa.setRUTA_EVIDENCIAS(Constantes.rutaArch + obj.getRUTA_EVIDENCIAS());
                programa.setEDITA(obj.getEDITA());
                
                
                
                
            }
            
            Constantes.enviaMensajeConsola("EL ICONO DE EFDITA ES "+actualiza);
            
            if(actualiza.equals("1")){
                
                banActualiza=false;
                banGuarda=false;
                banVisualiza=true;
                
            }
            if(actualiza.equals("2")){
                banActualiza=true;
                banGuarda=false;
                  banVisualiza=false;
            }
            
             if(actualiza.equals("0")){
                banActualiza=false;
                banGuarda=true;
                  banVisualiza=false;
            }
       
       
          
           Constantes.enviaMensajeConsola("EL TAÑO TOTAL DE LA LISTA ES DE "+ListaPlanAluMateriaComAct.size());
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
        
        
        
        
    public String guardaEvalMUE() {

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
             
             boolean banValidaArch=false;
             boolean banValidaMarco=false;
             boolean banValidaDesc=false;
             int errores=0;
             String mensajeError="";
             
           
             Constantes.enviaMensajeConsola("entre a guardar");
         
        
         Iterator LREM2 =ListaRegistroEstMs.iterator();
              programaEsBean obj2;
              
              
               Constantes.enviaMensajeConsola("Registros"+ListaRegistroEstMs.size());
              
              
              
                     while (LREM2.hasNext()) {
                        obj2 = (programaEsBean) LREM2.next();
                         Constantes.enviaMensajeConsola("Entre al while");
                        
                     if(obj2.getID_RUBRICA().length()>0){
                         
                          Constantes.enviaMensajeConsola("Correcto");
                         
                     }
                     else
                     {
                         
                          Constantes.enviaMensajeConsola("Incorrecto");
                         errores+=1;
                         mensajeError="Registrar evaluación"+'\n';
                           obj2.setERROR_PLANMS(mensajeError);
                         
                     }
                        
                        
                        
                        
                        
                       
                         
                     }
                     
                     
                     
                     
                        Constantes.enviaMensajeConsola("Errores:"+ errores);
                     
                     
               if (errores == 0) {
                   
                        Constantes.enviaMensajeConsola("ENTRE A GUARDAR LA INFORMACIÓN ");
                     
         Iterator LREM3 =ListaRegistroEstMs.iterator();
              programaEsBean obj3;
              
               String fechaHoy=fecha();

                //abriendo la conexion.....
                conecta = con.crearConexion();
                //statement
                objConexion = con.crearStatement(conecta);
             

                while (LREM3.hasNext()) {
                    obj3 = (programaEsBean) LREM3.next();
                    
                    programa.setID_DIA(obj3.getID_FECHA());
                    programa.setID_RUBRICA(obj3.getID_RUBRICA());
                   
                    

                   con2.guardaEvalMUE(conecta, objPreConexion, programa, alumno, fechaHoy);


                }

               
                cierraConexiones();
                
                  EvaluaActEst();
                  
                  addFieldError("GUARDADOEXT", "Se guardo la evaluación");  
                  
                 programa.setFECHA_INICIOPF("");
                          programa.setFECHA_TERMINOPF("");
                          programa.setAUX_INICIOSEMANA("");
                                  programa.setAUX_FINSEMANA("");

            }
               
               else{
                   
                   
                   
                   
                   
                   
               }
              

                  
                  
                
                  
               
                  
                
       
           
         
           
           
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
     public String guardaEvalMA() {

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
             
             boolean banValidaArch=false;
             boolean banValidaMarco=false;
             boolean banValidaDesc=false;
             int errores=0;
             String mensajeError="";
             
           
             Constantes.enviaMensajeConsola("entre a guardar");
         
        
         Iterator LREM2 =ListaRegistroEstMs.iterator();
              programaEsBean obj2;
              
              
               Constantes.enviaMensajeConsola("Registros"+ListaRegistroEstMs.size());
              
              
              
                     while (LREM2.hasNext()) {
                        obj2 = (programaEsBean) LREM2.next();
                         Constantes.enviaMensajeConsola("Entre al while");
                        
                     if(obj2.getID_RUBRICA().length()>0 && obj2.getID_ACT_EVALUA().length()>0){
                         
                          Constantes.enviaMensajeConsola("Correcto");
                           Constantes.enviaMensajeConsola("el valor del del reporte es "+ obj2.getID_ACT_EVALUA());
                         
                     }
                     else
                     {
                         
                     
                         
                          Constantes.enviaMensajeConsola("Incorrecto");
                         errores+=1;
                         mensajeError="Registrar evaluación de desempeño y Actividad"+'\n';
                           obj2.setERROR_PLANMS(mensajeError);
                        
                         
                        
                         
                         
                         
                         
                         
                         
                     }
                        
                        
                        
                        
                        
                       
                         
                     }
                     
                     
                     
                     
                        Constantes.enviaMensajeConsola("Errores:"+ errores);
                     
                     
               if (errores == 0) {
                   
                        Constantes.enviaMensajeConsola("ENTRE A GUARDAR LA INFORMACIÓN ");
                     
         Iterator LREM3 =ListaRegistroEstMs.iterator();
              programaEsBean obj3;
              int j=0;
               
               String fechaHoy=fecha();

                //abriendo la conexion.....
                conecta = con.crearConexion();
                //statement
                objConexion = con.crearStatement(conecta);
             
                
                
                

                while (LREM3.hasNext()) {
                    
                   
                    
                    obj3 = (programaEsBean) LREM3.next();
                    
                    programa.setID_DIA(obj3.getID_FECHA());
                    programa.setID_RUBRICA(obj3.getID_RUBRICA());
                     programa.setID_ACT_EVALUA(obj3.getID_ACT_EVALUA());
                    
                   Constantes.enviaMensajeConsola("id act_"+obj3.getID_ACT_EVALUA());
                   
                    

                   con2.guardaEvalMA(conecta, objPreConexion, programa, alumno, fechaHoy);


                }

               
                cierraConexiones();
                
                  EvalEstMA();
                  
                  addFieldError("GUARDADOEXT", "Se guardo la evaluación");  
                  
                 programa.setFECHA_INICIOPF("");
                          programa.setFECHA_TERMINOPF("");
                          programa.setAUX_INICIOSEMANA("");
                                  programa.setAUX_FINSEMANA("");

            }
               
               else{
                   
                   
                   
                   
                   
                   
               }
              

                  
                  
                
                  
               
                  
                
       
           
         
           
           
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
     
     
     
     
     
     
     
     
     
     
    
    
     public String guardaReporteEst() {

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
             
             boolean banValidaArch=false;
             boolean banValidaMarco=false;
             boolean banValidaDesc=false;
             
             String nivel = "";
            nivel = con2.nivel(escuela, usuariocons);
             
           if(nivel.equals("2")){
      // ListaPlanAluMateriaComAct=con2.planFormAluMateriaComAct(alumno, escuela, usuariocons, programa);
      
      
      
       Constantes.enviaMensajeConsola("--EL ARCHIVO DE RESPONSABLES ES .... " + archiFileName);
       
        if (archiFileName != null) {
                    Constantes.enviaMensajeConsola("entro a validar");

                    validate2();
                    Constantes.enviaMensajeConsola("Valor de la bandera: " + banT);
                    if (banT == false) {

                        String Extension = "";

                        Extension = getExtension(archiFileName);

                        //System.out.println("esta es la extension del archivo: "+Extension);
                        archiFileName = programa.getAUX_IDCOMPETENCIA() + "COMP" + alumno.getAUXIDHISTALUM() + "." + Extension;

                     
                        programa.setARCH_EVIDENCIAS(archiFileName);

                        Constantes.enviaMensajeConsola("archivo: " +  programa. getARCH_EVIDENCIAS());
                        ruta = Constantes.rutaArch + archiFileName;

                        Constantes.enviaMensajeConsola(ruta);
                        File newarch = new File(ruta);

                        FileUtils.copyFile(archi, newarch);
                        
                        programa.setRUTA_EVIDENCIAS(archiFileName);
                        
                        banValidaArch=true;
                    } else {
                        banValidaArch=false;
                      
                    }
                } 
        else{
           Constantes.enviaMensajeConsola("entre al else");
            banValidaArch=true;
            
        }
        
        
        
        
        if(programa.getMARCO_TEORICO().length()>0){
            
            banValidaMarco=true;
            
        }
        else
        {
             addFieldError("ERRORMARCO", "El campo es requerido");
            banValidaMarco=false;
        }
        
         if(programa.getDES_ACT().length()>0){
            
            banValidaDesc=true;
            
        }
        else
        {
             addFieldError("ERRORDESC", "El campo es requerido");
            banValidaDesc=false;
        }
         Constantes.enviaMensajeConsola(""+banValidaArch);
          Constantes.enviaMensajeConsola(""+banValidaArch);
           Constantes.enviaMensajeConsola(""+banValidaArch);
           
         if(banValidaArch && banValidaDesc && banValidaMarco){
             
          
             
             Constantes.enviaMensajeConsola("Aux de Alumno"+alumno.getAUXIDHISTALUM());
              Constantes.enviaMensajeConsola("Competencia: "+programa.getAUX_IDCOMPETENCIA());
              Constantes.enviaMensajeConsola("Descripción de Actividades"+programa.getDES_ACT());
              Constantes.enviaMensajeConsola("Ruta de las actividades"+programa.getRUTA_EVIDENCIAS());
             
             
             
             con2.guardaRegAluComp(alumno, programa);
             
          RepActAlu();
             
               addFieldError("GUARDADOEXT", "El reporte fue guardado con éxito");
             
             
         }
        
        
       
       
          
           Constantes.enviaMensajeConsola("EL TAÑO TOTAL DE LA LISTA ES DE "+ListaPlanAluMateriaComAct.size());
           
           
           }
           
         
           
           if(nivel.equals("1")){
               
               
           
              
              Iterator LREM =ListaRegistroEstMs.iterator();
              programaEsBean obj;
              
               int Fecha = 0;
               int Nombre = 0;
               int objetivo = 0;
               int Marco = 0;
               int Desarrollo = 0;
               int Equipo = 0;
               int Medidas = 0;
               int Normas = 0;
               int Archi = 0;
               int errorDatos=0;
               int contadorreporte=0;
               
               
               
               
               List<String> listaSinDuplicados=null;
           
               String mensajeError = "";
               
              
              
            listaSinDuplicados = ListaRegistroEstMs.stream().map(item->item.getID_FECHA()).distinct().collect(Collectors.toList());

              if(listaSinDuplicados.size()==5){
                  
                  Constantes.enviaMensajeConsola("fechas validas");
                  
              }
              
              else{
                  
                     
                    Constantes.enviaMensajeConsola("No se puede guardar favor de validar información, fechas de registro duplicadas ");
                  
                  errorDatos+=1;
                      
               addFieldError("ERRORFECHAS", "Fecha o fechas no validas, favor de verificar");  
                     
                     
               
              }
              
              
              
              
               while (LREM.hasNext()) {
                 obj = (programaEsBean) LREM.next();
                 
                 contadorreporte+=1;
                 
               
                 mensajeError="";
                 
                 if(obj.getID_FECHA().length()==0){
                     Fecha+=1; 
                     mensajeError+="***** Favor de elegir una fecha "+'\n';
                     errorDatos+=1;
                 }
                
                 
                  if(obj.getNOMBRE_ACTIVIDAD().length()==0){
                     Nombre+=1;
                     mensajeError+="***** Registrar nombre de la Actividad "+'\n';
                     errorDatos+=1;
                 }
                  
                  
                     if(obj.getOBJETIVO().length()==0){
                     objetivo+=1;
                     mensajeError+="***** Registrar objetivo"+'\n';
                     errorDatos+=1;
                 }
                     
                      if(obj.getMARCO_TEORICO().length()==0){
                     Marco+=1; 
                     mensajeError+="***** Registrar Marco Teórico"+'\n';
                     errorDatos+=1;
                 }
                     
                    
                      if(obj.getDESARROLLO().length()==0){
                     Desarrollo+=1; 
                     mensajeError+="***** Registrar desarrollo y procedimiento"+'\n';
                     errorDatos+=1;
                 }
                 
                       if(obj.getEQUIPO().length()==0){
                     Equipo+=1;
                     mensajeError+="***** Registrar equipo y Herramientas"+'\n';
                     errorDatos+=1;
                 }
                  
                       
                         
                       
                        if(obj.getMEDIDAS_SEGURIDAD().length()==0){
                     Medidas+=1;
                     mensajeError+="***** Registrar medidas de seguridad físicas y entorno"+'\n';
                     errorDatos+=1;
                 }
                        
                  
                          if(obj.getNORMAS_CALIDAD().length()==0){
                     Normas+=1; 
                     mensajeError+="***** Registrar normas de calidad"+'\n';
                     errorDatos+=1;
                 }
                          
                        if(obj.getAUTOESTUDIO().length()==0){
                  
                     mensajeError+="***** Registrar actividades de autoestudio"+'\n';
                     errorDatos+=1;
                 }    
                        
                         if(obj.getOBSERVACIONES().length()==0){
                  
                     mensajeError+="***** Registrar observaciones"+'\n';
                     errorDatos+=1;
                 }    
                          
                          
                          
                          
                          
                       Constantes.enviaMensajeConsola("ARCHIVO FILENAME:"+  obj.getArchiFileName());
                          
                   if (obj.getArchiFileName() != null) {
                       Constantes.enviaMensajeConsola("entro a validar");

                       validate3(obj.getArchiFileName());
                       Constantes.enviaMensajeConsola("Valor de la bandera: " + banT);
                       if (banT == false) {

                           String Extension = "";

                           Extension = getExtension(obj.getArchiFileName());

                           //System.out.println("esta es la extension del archivo: "+Extension);
                           obj.setArchiFileName(programa.getID_SEMANA() +"_"+obj.getID_FECHA()+"_"+alumno.getAUXIDHISTALUM()+ "." + Extension);
                          

                           programa.setARCH_EVIDENCIAS(obj.getArchiFileName());

                           Constantes.enviaMensajeConsola("archivo: " + programa.getARCH_EVIDENCIAS());
                           ruta = Constantes.rutaArch +"reportesEstMS/"+ obj.getArchiFileName();

                           Constantes.enviaMensajeConsola(ruta);
                           File newarch = new File(ruta);

                           FileUtils.copyFile(obj.getArchi(), newarch);

                           programa.setRUTA_EVIDENCIAS(obj.getArchiFileName());

                           banValidaArch = true;
                       } else {
                           
                            errorDatos+=1;
                           banValidaArch = false;
                           
                            addFieldError("ERRORFECHAS", "Formato o tamaño de archivo  no valido en el reporte "+contadorreporte);  

                       }
                   } else {
                       Constantes.enviaMensajeConsola("entre al else");
                       
                       errorDatos+=1;
                        addFieldError("ERRORFECHAS", "Favor de seleccionar un archivo en el reporte número: "+contadorreporte);  
                       banValidaArch = true;

                   }

                        
                        
                
                             
                          
                   Constantes.enviaMensajeConsola("Cantidad de Fechas Registradas;" + Fecha);
                   Constantes.enviaMensajeConsola("Cantidad de Nombre Registradas;" + Nombre);
                   Constantes.enviaMensajeConsola("Cantidad de Marco Registradas;" + Marco);
                   Constantes.enviaMensajeConsola("Cantidad de Desarrollo Registradas;" + Desarrollo);
                   Constantes.enviaMensajeConsola("Cantidad de Equipo Registradas;" + Equipo);
                   Constantes.enviaMensajeConsola("Cantidad de Medidas Registradas;" + Medidas);
                   Constantes.enviaMensajeConsola("Cantidad de Normas Registradas;" + Normas);
                   Constantes.enviaMensajeConsola("Cantidad de Archivos Registradas;" + Archi);

                  
                  
                  
             
                  
                  
                  if( errorDatos==0 ){
                      
                      
                      System.out.println("Registro correcto");
                      
                      
                      
                  }
                  else{
                        System.out.println("No se guardo la información");                             
                      
                      obj.setERROR_PLANMS(mensajeError);
                      
                  
                      
                  }
                  
                
                
                          
                          

                   
               }
               
             
               
               
              
                 if(errorDatos==0 ){
                      
                      
                  Constantes.enviaMensajeConsola("Voy a guardar el registro semanal");
                  
                  
                    Iterator LREM2 =ListaRegistroEstMs.iterator();
              programaEsBean obj2;
              
              
                //abriendo la conexion.....
            conecta = con.crearConexion();
            //statement
            objConexion = con.crearStatement(conecta);
            //ListaSecciones=(ArrayList<Bean_Secciones>) superior.ConsSecciones(CveCuestionario);
              
              
              
              
                     while (LREM2.hasNext()) {
                        obj2 = (programaEsBean) LREM2.next();
                        
                        programa.setID_FECHA(obj2.getID_FECHA());
                        programa.setRUTA_EVIDENCIAS(obj2.getArchiFileName());
                        programa.setNOMBRE_ACTIVIDAD(obj2.getNOMBRE_ACTIVIDAD());
                        programa.setOBJETIVO(obj2.getOBJETIVO());
                        programa.setMARCO_TEORICO(obj2.getOBJETIVO());
                        programa.setDESARROLLO(obj2.getDESARROLLO());
                        programa.setEQUIPO(obj2.getEQUIPO());
                        programa.setMEDIDAS_SEGURIDAD(obj2.getMEDIDAS_SEGURIDAD());
                        programa.setNORMAS_CALIDAD(obj2.getNORMAS_CALIDAD());
                        programa.setAUTOESTUDIO(obj2.getAUTOESTUDIO());
                         programa.setOBSERVACIONES(obj2.getOBSERVACIONES());
                        
                        
                        
                        
                        
                        con2.guardaReporteEstMs(conecta, objPreConexion, programa, alumno); 
                         
                     }
                  
                  
                  
                  
                  
                   cierraConexiones();
                  
                  RepActAlu();
                  
                  addFieldError("GUARDADOEXT", "Se guardo el reporte semanal con éxito ");  
                  
                 programa.setFECHA_INICIOPF("");
                          programa.setFECHA_TERMINOPF("");
                          programa.setAUX_INICIOSEMANA("");
                                  programa.setAUX_FINSEMANA("");
                      
                      
                      
                  }
                 else{
                     
                    Constantes.enviaMensajeConsola("No se puede guardar favor de validar información");
                     
                     
                 }
               
               
               
               
             
               
           }
           
           
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
    
    
      public String guardaEvalEst() {

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
             
             boolean banValidaArch=false;
             boolean banValidaMarco=false;
             boolean banValidaDesc=false;
             
           
   Iterator LPMCA= ListaPlanAluMateriaComAct.iterator();
            programaEsBean obj;
            
            int errores=0;
            
            while (LPMCA.hasNext()) {
                obj = (programaEsBean) LPMCA.next();
                
              if(obj.getID_RUBRICA().length()==0)  {
                  
                  errores=errores+1;
              }
                
            }
            
            if(errores>0){
                
               addFieldError("ERROREVA", "Debe registrar una evaluación para cada una de las Actividades");  
            }
            
            else{
                conecta = con.crearConexion();
            //statement
            objConexion = con.crearStatement(conecta);
            
            
             Iterator LPMCA2= ListaPlanAluMateriaComAct.iterator();
            programaEsBean obj2;
            
         
            
            while (LPMCA2.hasNext()) {
                obj2 = (programaEsBean) LPMCA2.next();
                
             programa.setID_ACTIVIDAD(obj2.getID_ACTIVIDAD());
             programa.setID_RUBRICA(obj2.getID_RUBRICA());

             con2.registraEvaluacionMUE(conecta, objPreConexion, programa, alumno);   
            }
            
            String fechaHoy="";
            
             fechaHoy=fecha();
            
             con2.registraEvaluacioncComMUE(conecta, objPreConexion, programa, alumno, fechaHoy);   
            
         
            
            cierraConexiones();
            
            EvaluaActEst();
            
             addFieldError("EVALGUARDADA", "la evaluación fue guardada con éxito");  
                
            }
             
             
          //   con2.guardaRegAluComp(alumno, programa);
             
         // RepActAlu();
             
            
       
       
          
          
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
      
      
    
      
      
      
      
       public String guardaEvalEst2() {

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
             
             boolean banValidaArch=false;
             boolean banValidaMarco=false;
             boolean banValidaDesc=false;
             
           
   Iterator LPMCA= ListaPlanAluMateriaComAct.iterator();
            programaEsBean obj;
            
            int errores=0;
            
            while (LPMCA.hasNext()) {
                obj = (programaEsBean) LPMCA.next();
                
              if(obj.getID_RUBRICA().length()==0)  {
                  
                  errores=errores+1;
              }
                
            }
            
            if(errores>0){
                
               addFieldError("ERROREVA", "Debe registrar una evaluación para cada una de las Actividades");  
            }
            
            else{
                conecta = con.crearConexion();
            //statement
            objConexion = con.crearStatement(conecta);
            
            
             Iterator LPMCA2= ListaPlanAluMateriaComAct.iterator();
            programaEsBean obj2;
            
         
            
            while (LPMCA2.hasNext()) {
                obj2 = (programaEsBean) LPMCA2.next();
                
             programa.setID_ACTIVIDAD(obj2.getID_ACTIVIDAD());
             programa.setID_RUBRICA(obj2.getID_RUBRICA());

             con2.registraEvaluacionMA(conecta, objPreConexion, programa, alumno);   
            }
            
            String fechaHoy="";
            
             fechaHoy=fecha();
            
             con2.registraEvaluacioncComMA(conecta, objPreConexion, programa, alumno, fechaHoy);   
            
         
            
            cierraConexiones();
            
            EvalEstMA();
            
             addFieldError("EVALGUARDADA", "la evaluación fue guardada con éxito");  
                
            }
             
             
          //   con2.guardaRegAluComp(alumno, programa);
             
         // RepActAlu();
             
            
       
       
          
          
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
       
        public String DespliegaPlanAlu() {

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
             
           
             
            
      consultaPlanEstudiante3();
       
          
          
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

      
      public String fecha(){
    String fechaHoy;
     
      java.util.Date fecha = new Date();
        System.out.println (fecha);
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-YYYY");
    fechaHoy=dt1.format(fecha);
     
     
     return fechaHoy;
 }     
    
    
     public String actualizaReporteEst() {

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
             
             boolean banValidaArch=false;
             boolean banValidaMarco=false;
             boolean banValidaDesc=false;
             
           
      // ListaPlanAluMateriaComAct=con2.planFormAluMateriaComAct(alumno, escuela, usuariocons, programa);
      
       Constantes.enviaMensajeConsola("--EL ARCHIVO DE RESPONSABLES ES .... " + archiFileName);
       
        if (archiFileName != null) {
                    Constantes.enviaMensajeConsola("entro a validar");

                    validate2();
                    Constantes.enviaMensajeConsola("Valor de la bandera: " + banT);
                    if (banT == false) {

                        String Extension = "";

                        Extension = getExtension(archiFileName);

                        //System.out.println("esta es la extension del archivo: "+Extension);
                        archiFileName = programa.getAUX_IDCOMPETENCIA() + "COMP" + alumno.getAUXIDHISTALUM() + "." + Extension;

                     
                        programa.setARCH_EVIDENCIAS(archiFileName);

                        Constantes.enviaMensajeConsola("archivo: " +  programa. getARCH_EVIDENCIAS());
                        ruta = Constantes.rutaArch + archiFileName;

                        Constantes.enviaMensajeConsola(ruta);
                        File newarch = new File(ruta);

                        FileUtils.copyFile(archi, newarch);
                        
                        programa.setRUTA_EVIDENCIAS(archiFileName);
                        
                        banValidaArch=true;
                    } else {
                        banValidaArch=false;
                      
                    }
                } 
        else{
           Constantes.enviaMensajeConsola("entre al else");
            banValidaArch=true;
            
        }
        
        
        
        
        if(programa.getMARCO_TEORICO().length()>0){
            
            banValidaMarco=true;
            
        }
        else
        {
             addFieldError("ERRORMARCO", "El campo es requerido");
            banValidaMarco=false;
        }
        
         if(programa.getDES_ACT().length()>0){
            
            banValidaDesc=true;
            
        }
        else
        {
             addFieldError("ERRORDESC", "El campo es requerido");
            banValidaDesc=false;
        }
         Constantes.enviaMensajeConsola(""+banValidaArch);
          Constantes.enviaMensajeConsola(""+banValidaArch);
           Constantes.enviaMensajeConsola(""+banValidaArch);
           
         if(banValidaArch && banValidaDesc && banValidaMarco){
             
          
             
             Constantes.enviaMensajeConsola("Aux de Alumno"+alumno.getAUXIDHISTALUM());
              Constantes.enviaMensajeConsola("Competencia: "+programa.getAUX_IDCOMPETENCIA());
              Constantes.enviaMensajeConsola("Descripción de Actividades"+programa.getDES_ACT());
              Constantes.enviaMensajeConsola("Ruta de las actividades"+programa.getRUTA_EVIDENCIAS());
             
             
             
             con2.actualizaRegAluComp(alumno, programa);
             
          RepActAlu();
             
               addFieldError("GUARDADOEXT", "El reporte se actualizó");
             
             
         }
        
        
       
       
          
           Constantes.enviaMensajeConsola("EL TAÑO TOTAL DE LA LISTA ES DE "+ListaPlanAluMateriaComAct.size());
           

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

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

    public List<programaEsBean> getListaProgramasRegistro() {
        return ListaProgramasRegistro;
    }

    public void setListaProgramasRegistro(List<programaEsBean> ListaProgramasRegistro) {
        this.ListaProgramasRegistro = ListaProgramasRegistro;
    }

    public programaEsBean getPrograma() {
        return programa;
    }

    public void setPrograma(programaEsBean programa) {
        this.programa = programa;
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

    public escuelaBean getEscuela() {
        return escuela;
    }

    public List<AlumnoBean> getListaProgramas() {
        return ListaProgramas;
    }

    public void setListaProgramas(List<AlumnoBean> ListaProgramas) {
        this.ListaProgramas = ListaProgramas;
    }

    public List<CatalogoBean> getListaGrados() {
        return ListaGrados;
    }

    public void setListaGrados(List<CatalogoBean> ListaGrados) {
        this.ListaGrados = ListaGrados;
    }

    public List<AlumnoBean> getDatosAlumno() {
        return DatosAlumno;
    }

    public void setDatosAlumno(List<AlumnoBean> DatosAlumno) {
        this.DatosAlumno = DatosAlumno;
    }

    public List<AlumnoBean> getListaInteresados() {
        return ListaInteresados;
    }

    public void setListaInteresados(List<AlumnoBean> ListaInteresados) {
        this.ListaInteresados = ListaInteresados;
    }

    public List<AlumnoBean> getListaInteresadosPE() {
        return ListaInteresadosPE;
    }

    public void setListaInteresadosPE(List<AlumnoBean> ListaInteresadosPE) {
        this.ListaInteresadosPE = ListaInteresadosPE;
    }

    public List<CatalogoBean> getListaEvaluacion() {
        return ListaEvaluacion;
    }

    public void setListaEvaluacion(List<CatalogoBean> ListaEvaluacion) {
        this.ListaEvaluacion = ListaEvaluacion;
    }

    public List<CatalogoBean> getListaSituacion() {
        return ListaSituacion;
    }

    public void setListaSituacion(List<CatalogoBean> ListaSituacion) {
        this.ListaSituacion = ListaSituacion;
    }

    public List<CatalogoBean> getListaPromedio() {
        return ListaPromedio;
    }

    public void setListaPromedio(List<CatalogoBean> ListaPromedio) {
        this.ListaPromedio = ListaPromedio;
    }

    public List<AlumnoBean> getListaPreseleccionados() {
        return ListaPreseleccionados;
    }

    public void setListaPreseleccionados(List<AlumnoBean> ListaPreseleccionados) {
        this.ListaPreseleccionados = ListaPreseleccionados;
    }

    public List<AlumnoBean> getListaPEUE() {
        return ListaPEUE;
    }

    public void setListaPEUE(List<AlumnoBean> ListaPEUE) {
        this.ListaPEUE = ListaPEUE;
    }

    public List<AlumnoBean> getListaValidados() {
        return ListaValidados;
    }

    public void setListaValidados(List<AlumnoBean> ListaValidados) {
        this.ListaValidados = ListaValidados;
    }

    public List<AlumnoBean> getListaAceptados() {
        return ListaAceptados;
    }

    public void setListaAceptados(List<AlumnoBean> ListaAceptados) {
        this.ListaAceptados = ListaAceptados;
    }

    public List<UnidadesEconomicasBean> getListaUE() {
        return ListaUE;
    }

    public void setListaUE(List<UnidadesEconomicasBean> ListaUE) {
        this.ListaUE = ListaUE;
    }

    public List<ColoniasBean> getListaColonia() {
        return ListaColonia;
    }

    public void setListaColonia(List<ColoniasBean> ListaColonia) {
        this.ListaColonia = ListaColonia;
    }

    public List<ColoniasBean> getListaColoniaP() {
        return ListaColoniaP;
    }

    public void setListaColoniaP(List<ColoniasBean> ListaColoniaP) {
        this.ListaColoniaP = ListaColoniaP;
    }

    public boolean isBanMuestraForm() {
        return banMuestraForm;
    }

    public void setBanMuestraForm(boolean banMuestraForm) {
        this.banMuestraForm = banMuestraForm;
    }

    public boolean isBanMuestraForm1() {
        return banMuestraForm1;
    }

    public void setBanMuestraForm1(boolean banMuestraForm1) {
        this.banMuestraForm1 = banMuestraForm1;
    }

    public boolean isBanMuestraFormDUAL() {
        return banMuestraFormDUAL;
    }

    public void setBanMuestraFormDUAL(boolean banMuestraFormDUAL) {
        this.banMuestraFormDUAL = banMuestraFormDUAL;
    }

    public boolean isBanActualizaInteresado() {
        return banActualizaInteresado;
    }

    public void setBanActualizaInteresado(boolean banActualizaInteresado) {
        this.banActualizaInteresado = banActualizaInteresado;
    }

    public boolean isBanFormPad() {
        return banFormPad;
    }

    public void setBanFormPad(boolean banFormPad) {
        this.banFormPad = banFormPad;
    }

    public boolean isBanMuestraCurpP() {
        return banMuestraCurpP;
    }

    public void setBanMuestraCurpP(boolean banMuestraCurpP) {
        this.banMuestraCurpP = banMuestraCurpP;
    }

    public boolean isBanGuardaInteresado() {
        return banGuardaInteresado;
    }

    public void setBanGuardaInteresado(boolean banGuardaInteresado) {
        this.banGuardaInteresado = banGuardaInteresado;
    }

    public void setEscuela(escuelaBean escuela) {
        this.escuela = escuela;
    }

    public AlumnoBean getAl() {
        return al;
    }

    public void setAl(AlumnoBean al) {
        this.al = al;
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

    public List<AlumnoBean> getListaEstudiantes() {
        return ListaEstudiantes;
    }

    public void setListaEstudiantes(List<AlumnoBean> ListaEstudiantes) {
        this.ListaEstudiantes = ListaEstudiantes;
    }

    public List<responsablesBean> getListaResUE() {
        return ListaResUE;
    }

    public void setListaResUE(List<responsablesBean> ListaResUE) {
        this.ListaResUE = ListaResUE;
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

    public AlumnoBean getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoBean alumno) {
        this.alumno = alumno;
    }

    public UnidadesEconomicasBean getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadesEconomicasBean unidad) {
        this.unidad = unidad;
    }

    public responsablesBean getResponsables() {
        return responsables;
    }

    public void setResponsables(responsablesBean responsables) {
        this.responsables = responsables;
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

    public boolean isMensajeSnAct() {
        return mensajeSnAct;
    }

    public void setMensajeSnAct(boolean mensajeSnAct) {
        this.mensajeSnAct = mensajeSnAct;
    }

    public boolean isBanListaAlu() {
        return banListaAlu;
    }

    public void setBanListaAlu(boolean banListaAlu) {
        this.banListaAlu = banListaAlu;
    }

    public boolean isBanCampAlumno() {
        return banCampAlumno;
    }

    public void setBanCampAlumno(boolean banCampAlumno) {
        this.banCampAlumno = banCampAlumno;
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

    public renapoBean getObjRenapo() {
        return objRenapo;
    }

    public void setObjRenapo(renapoBean objRenapo) {
        this.objRenapo = objRenapo;
    }

    public boolean isBanregistro() {
        return banregistro;
    }

    public void setBanregistro(boolean banregistro) {
        this.banregistro = banregistro;
    }

    public boolean isBanactualiza() {
        return banactualiza;
    }

    public void setBanactualiza(boolean banactualiza) {
        this.banactualiza = banactualiza;
    }

    public List<programaEsBean> getListaPlanAlumno() {
        return ListaPlanAlumno;
    }

    public void setListaPlanAlumno(List<programaEsBean> ListaPlanAlumno) {
        this.ListaPlanAlumno = ListaPlanAlumno;
    }

    public List<escuelaBean> getListaIdEs() {
        return ListaIdEs;
    }

    public void setListaIdEs(List<escuelaBean> ListaIdEs) {
        this.ListaIdEs = ListaIdEs;
    }

    public boolean isBanFechaTermino() {
        return banFechaTermino;
    }

    public void setBanFechaTermino(boolean banFechaTermino) {
        this.banFechaTermino = banFechaTermino;
    }

    public List<AlumnoBean> getListaCorreos() {
        return ListaCorreos;
    }

    public void setListaCorreos(List<AlumnoBean> ListaCorreos) {
        this.ListaCorreos = ListaCorreos;
    }

    public List<programaEsBean> getListaPlanFormAlu() {
        return ListaPlanFormAlu;
    }

    public void setListaPlanFormAlu(List<programaEsBean> ListaPlanFormAlu) {
        this.ListaPlanFormAlu = ListaPlanFormAlu;
    }

    public boolean isBanRegAluNiveS() {
        return banRegAluNiveS;
    }

    public void setBanRegAluNiveS(boolean banRegAluNiveS) {
        this.banRegAluNiveS = banRegAluNiveS;
    }

    public boolean isBanRegAluNiveMS() {
        return banRegAluNiveMS;
    }

    public void setBanRegAluNiveMS(boolean banRegAluNiveMS) {
        this.banRegAluNiveMS = banRegAluNiveMS;
    }

    public List<programaEsBean> getListaPlanAluMateria() {
        return ListaPlanAluMateria;
    }

    public void setListaPlanAluMateria(List<programaEsBean> ListaPlanAluMateria) {
        this.ListaPlanAluMateria = ListaPlanAluMateria;
    }

    public List<programaEsBean> getListaPlanAluMateriaCom() {
        return ListaPlanAluMateriaCom;
    }

    public void setListaPlanAluMateriaCom(List<programaEsBean> ListaPlanAluMateriaCom) {
        this.ListaPlanAluMateriaCom = ListaPlanAluMateriaCom;
    }

    public List<programaEsBean> getListaPlanAluMateriaComAct() {
        return ListaPlanAluMateriaComAct;
    }

    public void setListaPlanAluMateriaComAct(List<programaEsBean> ListaPlanAluMateriaComAct) {
        this.ListaPlanAluMateriaComAct = ListaPlanAluMateriaComAct;
    }

    public boolean isBanT() {
        return banT;
    }

    public void setBanT(boolean banT) {
        this.banT = banT;
    }

    public String getArchiFileName() {
        return archiFileName;
    }

    public void setArchiFileName(String archiFileName) {
        this.archiFileName = archiFileName;
    }

    public File getArchi() {
        return archi;
    }

    public void setArchi(File archi) {
        this.archi = archi;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean isBanActualiza() {
        return banActualiza;
    }

    public void setBanActualiza(boolean banActualiza) {
        this.banActualiza = banActualiza;
    }

    public boolean isBanGuarda() {
        return banGuarda;
    }

    public void setBanGuarda(boolean banGuarda) {
        this.banGuarda = banGuarda;
    }

    public boolean isBanVisualiza() {
        return banVisualiza;
    }

    public void setBanVisualiza(boolean banVisualiza) {
        this.banVisualiza = banVisualiza;
    }

    public List<programaEsBean> getListaRubrica() {
        return ListaRubrica;
    }

    public void setListaRubrica(List<programaEsBean> ListaRubrica) {
        this.ListaRubrica = ListaRubrica;
    }

    public List<programaEsBean> getListaSemanas() {
        return ListaSemanas;
    }

    public void setListaSemanas(List<programaEsBean> ListaSemanas) {
        this.ListaSemanas = ListaSemanas;
    }

    public List<programaEsBean> getListaFechas() {
        return ListaFechas;
    }

    public void setListaFechas(List<programaEsBean> ListaFechas) {
        this.ListaFechas = ListaFechas;
    }

    public List<programaEsBean> getListaRegistroEstMs() {
        return ListaRegistroEstMs;
    }

    public void setListaRegistroEstMs(List<programaEsBean> ListaRegistroEstMs) {
        this.ListaRegistroEstMs = ListaRegistroEstMs;
    }

    public boolean isBanGuardaEvalMUE() {
        return banGuardaEvalMUE;
    }

    public void setBanGuardaEvalMUE(boolean banGuardaEvalMUE) {
        this.banGuardaEvalMUE = banGuardaEvalMUE;
    }
    
    

}
