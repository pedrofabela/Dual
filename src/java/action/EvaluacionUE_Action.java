/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import beans.CatalogoBean;
import beans.ColoniasBean;
import beans.EncabezadoBean;
import beans.PreguntasBean;
import beans.Res_ConBean;
import beans.RespuestaBean;
import beans.SectorSubsectorBean;
import beans.UnidadesEconomicasBean;
import beans.escuelaBean;
import beans.moduloAuxBean;
import beans.moduloBean;
import beans.usuarioBean;
import business.ConsultasBusiness;
import com.opensymphony.xwork2.ActionSupport;
import daos.EvaluarUEDAOImpl;

import daos.UnidadesEconomicasDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
public class EvaluacionUE_Action extends ActionSupport implements SessionAware {

    private usuarioBean usuariocons;
    private String cveusuario;
    private String pasusuario;
    private String nomModulo;
    private String modulo;
    private String nombreUsuario;
    private String TipoError;
    private String TipoException;

    private boolean banMuestraForm;
    private boolean banMuestraFormAct;

    public List<moduloBean> modulosAUX = new ArrayList<moduloBean>();
    public List<moduloAuxBean> modulosAUXP = new ArrayList<moduloAuxBean>();

    private List<UnidadesEconomicasBean> ListaUEIE = new ArrayList<UnidadesEconomicasBean>();
    private List<escuelaBean> ListaIdEs = new ArrayList<escuelaBean>();

    private List<EncabezadoBean> ListaEncabezado = new ArrayList<EncabezadoBean>();
    private List<PreguntasBean> listaPregEnca = new ArrayList<PreguntasBean>();

    private List<RespuestaBean> ListaRespuestas1 = new ArrayList<RespuestaBean>();
    private List<RespuestaBean> ListaRespuestas2 = new ArrayList<RespuestaBean>();
    private List<Res_ConBean> ListaContestados = new ArrayList<Res_ConBean>();

    UnidadesEconomicasBean ue = new UnidadesEconomicasBean();
    escuelaBean escuela = new escuelaBean();
    Res_ConBean res = new Res_ConBean();

    //conexiones................................PARA LAS LISTAS
    Statement objConexion;
    PreparedStatement objPreConexion;
    Connection conecta;

    //******************** PARA OBJETO DE NAVEGACIoN ***********************************************
    private Map session;

    public String InicioEvaluacion() {

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

            escuela.setCCT(usuariocons.getUSUARIO());

            ListaIdEs = con.programasIdEs(escuela);

            Iterator LPE = ListaIdEs.iterator();

            escuelaBean obj;

            while (LPE.hasNext()) {
                obj = (escuelaBean) LPE.next();

                ue.setID_ESC(obj.getID_ESCUELA());

            }

            Constantes.enviaMensajeConsola("ID ESC: " + ue.getID_ESC());

            EvaluarUEDAOImpl con2 = new EvaluarUEDAOImpl();

            ListaUEIE = con2.ConsultaUEIE(ue.getID_ESC());

            Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String MuestraFormEvaluaion() {

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

            EvaluarUEDAOImpl con = new EvaluarUEDAOImpl();

            //Constantes.enviaMensajeConsola("ID que llega: "+ue.getID_IE_UE());
            for (int i = 0; i < ListaUEIE.size(); i++) {
                //Constantes.enviaMensajeConsola("ID LISTA: "+ListaUEIE.get(i).getID_IE_UE());
                if (ue.getID_IE_UE().equals(ListaUEIE.get(i).getID_IE_UE())) {
                    //Constantes.enviaMensajeConsola("entro a id igual"+ListaUEIE.get(i).getID_IE_UE());
                    ue.setRFC(ListaUEIE.get(i).getRFC());
                    ue.setRAZON_SOCIAL(ListaUEIE.get(i).getRAZON_SOCIAL());
                    ue.setNOMBRE_SUC(ListaUEIE.get(i).getNOMBRE_SUC());
                }

            }
            
            ListaEncabezado = con.ConsSecciones();
            ListaRespuestas1 = con.ConsultaRespuestas1();
            ListaRespuestas2 = con.ConsultaRespuestas2();
            ListaContestados.clear();

            Constantes.enviaMensajeConsola("lista Respuesta: " + ListaRespuestas1.size());
            Constantes.enviaMensajeConsola("lista Respuesta: " + ListaRespuestas2.size());

            banMuestraForm = true;

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
    public String ActualizarFormEvalucion() {

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

            EvaluarUEDAOImpl con = new EvaluarUEDAOImpl();

            //Constantes.enviaMensajeConsola("ID que llega: "+ue.getID_IE_UE());
            for (int i = 0; i < ListaUEIE.size(); i++) {
                //Constantes.enviaMensajeConsola("ID LISTA: "+ListaUEIE.get(i).getID_IE_UE());
                if (ue.getID_IE_UE().equals(ListaUEIE.get(i).getID_IE_UE())) {
                    //Constantes.enviaMensajeConsola("entro a id igual"+ListaUEIE.get(i).getID_IE_UE());
                    ue.setRFC(ListaUEIE.get(i).getRFC());
                    ue.setRAZON_SOCIAL(ListaUEIE.get(i).getRAZON_SOCIAL());
                    ue.setNOMBRE_SUC(ListaUEIE.get(i).getNOMBRE_SUC());
                }

            }

            ListaEncabezado = con.ConsSecciones();
            ListaRespuestas1 = con.ConsultaRespuestas1();
            ListaRespuestas2 = con.ConsultaRespuestas2();
            
            ListaContestados=con.ConsultaRespuestas(ue.getID_IE_UE());

            Constantes.enviaMensajeConsola("lista Respuesta: " + ListaRespuestas1.size());
            Constantes.enviaMensajeConsola("lista Respuesta: " + ListaRespuestas2.size());

            banMuestraFormAct = true;

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String GuardaEvaluacion() {

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

            EvaluarUEDAOImpl con = new EvaluarUEDAOImpl();

            //abriendo la conexion.....
            conecta = con.crearConexion();
            //statement
            objConexion = con.crearStatement(conecta);
            //ListaSecciones=(ArrayList<Bean_Secciones>) superior.ConsSecciones(CveCuestionario);

            int cont_preg = 0;
            int cont_res = 0;
            int total_aciertos = 0;
            float total = 0;
            int porcentaje = 0;

            int cont_preg2 = 0;
            int cont_res2 = 0;
            int total_aciertos2 = 0;
            float total2 = 0;
            int porcentaje2 = 0;

            int cont_preg3 = 0;
            int cont_res3 = 0;
            int total_aciertos3 = 0;
            float total3 = 0;
            int porcentaje3 = 0;

            int suma_porcentajes = 0;
            float total_general = 0;
            int porcentaje_total = 0;

            boolean respuestas;

            int contador = 0;

            for (int i = 0; i < ListaContestados.size(); i++) {

                res.setID_RESPUESTA(ListaContestados.get(i).getID_RESPUESTA());

                if (res.getID_RESPUESTA().length() == 0) {
                    contador = contador + 1;
                    break;
                }

            }

            if (contador > 0) {
                respuestas = false;
                banMuestraForm=true;
                addFieldError("ERRORPREG", "Debes responder todas las preguntas");
                return "ERROR";

            } else {
                respuestas = true;

            }

            if (respuestas) {

                //Constantes.enviaMensajeConsola("ID que llega: "+ue.getID_IE_UE());
                Constantes.enviaMensajeConsola("ListaContestados: " + ListaContestados.size());

                for (int i = 0; i < ListaContestados.size(); i++) {

                    res.setID_ENCABEZADO(ListaContestados.get(i).getID_ENCABEZADO());
                    res.setID_PREGUNTA(ListaContestados.get(i).getID_PREGUNTA());
                    res.setID_RESPUESTA(ListaContestados.get(i).getID_RESPUESTA());
                    res.setRESPUESTA(ListaContestados.get(i).getID_RESPUESTA());

                    res.setID_IE_UE(ue.getID_IE_UE());
                    Constantes.enviaMensajeConsola("id ie ue: " + res.getID_IE_UE());
                    Constantes.enviaMensajeConsola("id encabezado: " + res.getID_ENCABEZADO());
                    Constantes.enviaMensajeConsola("id pregunta: " + res.getID_PREGUNTA());
                    Constantes.enviaMensajeConsola("id respuesta: " + res.getID_RESPUESTA());

                    con.GuardaEvaluacionUE(conecta, objPreConexion, res);

                    if (res.getID_ENCABEZADO().equals("1")) {
                        cont_preg = cont_preg + 1;
                        cont_res = cont_res + Integer.valueOf(res.getID_RESPUESTA());
                    }

                    if (res.getID_ENCABEZADO().equals("2")) {
                        cont_preg2 = cont_preg2 + 1;
                        cont_res2 = cont_res2 + Integer.valueOf(res.getID_RESPUESTA());
                    }

                    if (res.getID_ENCABEZADO().equals("3")) {
                        cont_preg3 = cont_preg3 + 1;
                        cont_res3 = cont_res3 + Integer.valueOf(res.getID_RESPUESTA());
                    }

                }

                //cerrando conexiones...
                cierraConexiones();

                Constantes.enviaMensajeConsola("seccion 1: ");

                total_aciertos = cont_preg * 2;
                total = (float) cont_res / total_aciertos;
                porcentaje = (int) (total * 100);

            Constantes.enviaMensajeConsola("total aciertos: " + total_aciertos);
            Constantes.enviaMensajeConsola("total respuesta: " + cont_res);
            Constantes.enviaMensajeConsola("total : " + total);
            Constantes.enviaMensajeConsola("porcentaje : " + porcentaje);
                Constantes.enviaMensajeConsola("seccion 2: ");

                total_aciertos2 = cont_preg2 * 3;
                total2 = (float) cont_res2 / total_aciertos2;
                porcentaje2 = (int) (total2 * 100);

            Constantes.enviaMensajeConsola("total aciertos2: " + total_aciertos2);
            Constantes.enviaMensajeConsola("total respuesta2: " + cont_res2);
            Constantes.enviaMensajeConsola("total 2: " + total2);
            Constantes.enviaMensajeConsola("porcentaje 2: " + porcentaje2);
                Constantes.enviaMensajeConsola("seccion 3: ");

                total_aciertos3 = cont_preg3 * 3;
                total3 = (float) cont_res3 / total_aciertos3;
                porcentaje3 = (int) (total3 * 100);

            Constantes.enviaMensajeConsola("total aciertos3: " + total_aciertos3);
            Constantes.enviaMensajeConsola("total respuesta3: " + cont_res3);
            Constantes.enviaMensajeConsola("total 3: " + total3);
            Constantes.enviaMensajeConsola("porcentaje 3: " + porcentaje3);
                suma_porcentajes = porcentaje + porcentaje2 + porcentaje3;
                total_general = (float) suma_porcentajes / 3;
                porcentaje_total = (int) (total_general);

                Constantes.enviaMensajeConsola("suma porcentajes: " + suma_porcentajes);
                Constantes.enviaMensajeConsola("total general: " + total_general);
                Constantes.enviaMensajeConsola("porcentaje total: " + porcentaje_total);

                ue.getID_IE_UE();
                ue.setSTATUS_EVALUACION("1");
                ue.setPORCENTAJE_EVALUACION(String.valueOf(porcentaje_total));

                if (porcentaje_total >= 0 && porcentaje_total <= 45) {
                    ue.setSTATUS_RESULTADO_EVALUACION("1");
                    ue.setSTATUS_GENERAL("0");
                } else if (porcentaje_total >= 46 && porcentaje_total <= 66) {
                    ue.setSTATUS_RESULTADO_EVALUACION("2");
                    ue.setSTATUS_GENERAL("0");
                } else if (porcentaje_total >= 67 && porcentaje_total <= 100) {
                    ue.setSTATUS_RESULTADO_EVALUACION("3");
                    ue.setSTATUS_GENERAL("1");

                }

                con.ActualizaStatusUE_IE(ue);
                ListaUEIE = con.ConsultaUEIE(ue.getID_ESC());

                ListaContestados.clear();

                banMuestraForm = false;

            }
            
             return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
       
    public String ActualizaEvaluacion() {

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

            EvaluarUEDAOImpl con = new EvaluarUEDAOImpl();

            //abriendo la conexion.....
            conecta = con.crearConexion();
            //statement
            objConexion = con.crearStatement(conecta);
            //ListaSecciones=(ArrayList<Bean_Secciones>) superior.ConsSecciones(CveCuestionario);

            int cont_preg = 0;
            int cont_res = 0;
            int total_aciertos = 0;
            float total = 0;
            int porcentaje = 0;

            int cont_preg2 = 0;
            int cont_res2 = 0;
            int total_aciertos2 = 0;
            float total2 = 0;
            int porcentaje2 = 0;

            int cont_preg3 = 0;
            int cont_res3 = 0;
            int total_aciertos3 = 0;
            float total3 = 0;
            int porcentaje3 = 0;

            int suma_porcentajes = 0;
            float total_general = 0;
            int porcentaje_total = 0;

            boolean respuestas;

            int contador = 0;

            for (int i = 0; i < ListaContestados.size(); i++) {

                res.setID_RESPUESTA(ListaContestados.get(i).getID_RESPUESTA());

                if (res.getID_RESPUESTA().length() == 0) {
                    contador = contador + 1;
                    break;
                }

            }

            if (contador > 0) {
                respuestas = false;
                banMuestraForm=true;
                addFieldError("ERRORPREG", "Debes responder todas las preguntas");
                return "ERROR";

            } else {
                respuestas = true;

            }

            if (respuestas) {

                //Constantes.enviaMensajeConsola("ID que llega: "+ue.getID_IE_UE());
                Constantes.enviaMensajeConsola("ListaContestados: " + ListaContestados.size());

                for (int i = 0; i < ListaContestados.size(); i++) {
                    res.setID_EVALUACION(ListaContestados.get(i).getID_EVALUACION());
                    res.setID_ENCABEZADO(ListaContestados.get(i).getID_ENCABEZADO());
                    res.setID_PREGUNTA(ListaContestados.get(i).getID_PREGUNTA());
                    res.setID_RESPUESTA(ListaContestados.get(i).getID_RESPUESTA());
                    res.setRESPUESTA(ListaContestados.get(i).getID_RESPUESTA());

                    res.setID_IE_UE(ue.getID_IE_UE());
                    Constantes.enviaMensajeConsola("id ie ue: " + res.getID_IE_UE());
                    Constantes.enviaMensajeConsola("id encabezado: " + res.getID_ENCABEZADO());
                    Constantes.enviaMensajeConsola("id pregunta: " + res.getID_PREGUNTA());
                    Constantes.enviaMensajeConsola("id respuesta: " + res.getID_RESPUESTA());

                    con.ActualizaEvaluacionUE(conecta, objPreConexion, res);

                    if (res.getID_ENCABEZADO().equals("1")) {
                        cont_preg = cont_preg + 1;
                        cont_res = cont_res + Integer.valueOf(res.getID_RESPUESTA());
                    }

                    if (res.getID_ENCABEZADO().equals("2")) {
                        cont_preg2 = cont_preg2 + 1;
                        cont_res2 = cont_res2 + Integer.valueOf(res.getID_RESPUESTA());
                    }

                    if (res.getID_ENCABEZADO().equals("3")) {
                        cont_preg3 = cont_preg3 + 1;
                        cont_res3 = cont_res3 + Integer.valueOf(res.getID_RESPUESTA());
                    }

                }

                //cerrando conexiones...
                cierraConexiones();

                Constantes.enviaMensajeConsola("seccion 1: ");

                total_aciertos = cont_preg * 2;
                total = (float) cont_res / total_aciertos;
                porcentaje = (int) (total * 100);

            Constantes.enviaMensajeConsola("total aciertos: " + total_aciertos);
            Constantes.enviaMensajeConsola("total respuesta: " + cont_res);
            Constantes.enviaMensajeConsola("total : " + total);
            Constantes.enviaMensajeConsola("porcentaje : " + porcentaje);
                Constantes.enviaMensajeConsola("seccion 2: ");

                total_aciertos2 = cont_preg2 * 3;
                total2 = (float) cont_res2 / total_aciertos2;
                porcentaje2 = (int) (total2 * 100);

            Constantes.enviaMensajeConsola("total aciertos2: " + total_aciertos2);
            Constantes.enviaMensajeConsola("total respuesta2: " + cont_res2);
            Constantes.enviaMensajeConsola("total 2: " + total2);
            Constantes.enviaMensajeConsola("porcentaje 2: " + porcentaje2);
                Constantes.enviaMensajeConsola("seccion 3: ");

                total_aciertos3 = cont_preg3 * 3;
                total3 = (float) cont_res3 / total_aciertos3;
                porcentaje3 = (int) (total3 * 100);

            Constantes.enviaMensajeConsola("total aciertos3: " + total_aciertos3);
            Constantes.enviaMensajeConsola("total respuesta3: " + cont_res3);
            Constantes.enviaMensajeConsola("total 3: " + total3);
            Constantes.enviaMensajeConsola("porcentaje 3: " + porcentaje3);
                suma_porcentajes = porcentaje + porcentaje2 + porcentaje3;
                total_general = (float) suma_porcentajes / 3;
                porcentaje_total = (int) (total_general);

                Constantes.enviaMensajeConsola("suma porcentajes: " + suma_porcentajes);
                Constantes.enviaMensajeConsola("total general: " + total_general);
                Constantes.enviaMensajeConsola("porcentaje total: " + porcentaje_total);

                ue.getID_IE_UE();
                ue.setSTATUS_EVALUACION("1");
                ue.setPORCENTAJE_EVALUACION(String.valueOf(porcentaje_total));

                if (porcentaje_total >= 0 && porcentaje_total <= 45) {
                    ue.setSTATUS_RESULTADO_EVALUACION("1");
                    ue.setSTATUS_GENERAL("0");
                } else if (porcentaje_total >= 46 && porcentaje_total <= 66) {
                    ue.setSTATUS_RESULTADO_EVALUACION("2");
                    ue.setSTATUS_GENERAL("0");
                } else if (porcentaje_total >= 67 && porcentaje_total <= 100) {
                    ue.setSTATUS_RESULTADO_EVALUACION("3");
                    ue.setSTATUS_GENERAL("1");

                }

                con.ActualizaStatusUE_IE(ue);
                ListaUEIE = con.ConsultaUEIE(ue.getID_ESC());

                ListaContestados.clear();

                banMuestraForm = false;

            }
            
             return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
     public String ActualizarEstatusGeneral() {

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

            Constantes.enviaMensajeConsola("id_ue_ie: " + ue.getID_IE_UE());
            Constantes.enviaMensajeConsola("status: " + ue.getSTATUS_GENERAL());

            EvaluarUEDAOImpl con = new EvaluarUEDAOImpl();

            con.ActualizaStatusGenUE(ue);

            ListaUEIE = con.ConsultaUEIEEva(ue.getID_ESC());

            Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
    //****************************************valida UE***********************************************
    
     public String ValidaUE() {

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

            escuela.setCCT(usuariocons.getUSUARIO());

            ListaIdEs = con.programasIdEs(escuela);

            Iterator LPE = ListaIdEs.iterator();

            escuelaBean obj;

            while (LPE.hasNext()) {
                obj = (escuelaBean) LPE.next();

                ue.setID_ESC(obj.getID_ESCUELA());

            }

            Constantes.enviaMensajeConsola("ID ESC: " + ue.getID_ESC());

            EvaluarUEDAOImpl con2 = new EvaluarUEDAOImpl();

            ListaUEIE = con2.ConsultaUEIEPOT(ue.getID_ESC());

            Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
     
      public String ActualizarEstatusUE() {

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

            Constantes.enviaMensajeConsola("id_ue_ie: " + ue.getID_IE_UE());
            Constantes.enviaMensajeConsola("status: " + ue.getSTATUS_UE());

            EvaluarUEDAOImpl con = new EvaluarUEDAOImpl();

            con.ActualizaStatusUE(ue);

            ListaUEIE = con.ConsultaUEIEPOT(ue.getID_ESC());

            Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

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

    public boolean isBanMuestraForm() {
        return banMuestraForm;
    }

    public void setBanMuestraForm(boolean banMuestraForm) {
        this.banMuestraForm = banMuestraForm;
    }

    public boolean isBanMuestraFormAct() {
        return banMuestraFormAct;
    }

    public void setBanMuestraFormAct(boolean banMuestraFormAct) {
        this.banMuestraFormAct = banMuestraFormAct;
    }
    
    

    public List<UnidadesEconomicasBean> getListaUEIE() {
        return ListaUEIE;
    }

    public void setListaUEIE(List<UnidadesEconomicasBean> ListaUEIE) {
        this.ListaUEIE = ListaUEIE;
    }

    public List<escuelaBean> getListaIdEs() {
        return ListaIdEs;
    }

    public void setListaIdEs(List<escuelaBean> ListaIdEs) {
        this.ListaIdEs = ListaIdEs;
    }

    public List<EncabezadoBean> getListaEncabezado() {
        return ListaEncabezado;
    }

    public void setListaEncabezado(List<EncabezadoBean> ListaEncabezado) {
        this.ListaEncabezado = ListaEncabezado;
    }

    public List<PreguntasBean> getListaPregEnca() {
        return listaPregEnca;
    }

    public void setListaPregEnca(List<PreguntasBean> listaPregEnca) {
        this.listaPregEnca = listaPregEnca;
    }

    public List<RespuestaBean> getListaRespuestas1() {
        return ListaRespuestas1;
    }

    public void setListaRespuestas1(List<RespuestaBean> ListaRespuestas1) {
        this.ListaRespuestas1 = ListaRespuestas1;
    }

    public List<RespuestaBean> getListaRespuestas2() {
        return ListaRespuestas2;
    }

    public void setListaRespuestas2(List<RespuestaBean> ListaRespuestas2) {
        this.ListaRespuestas2 = ListaRespuestas2;
    }

    public List<Res_ConBean> getListaContestados() {
        return ListaContestados;
    }

    public void setListaContestados(List<Res_ConBean> ListaContestados) {
        this.ListaContestados = ListaContestados;
    }

    public UnidadesEconomicasBean getUe() {
        return ue;
    }

    public void setUe(UnidadesEconomicasBean ue) {
        this.ue = ue;
    }

    public escuelaBean getEscuela() {
        return escuela;
    }

    public void setEscuela(escuelaBean escuela) {
        this.escuela = escuela;
    }

    public Res_ConBean getRes() {
        return res;
    }

    public void setRes(Res_ConBean res) {
        this.res = res;
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

}
