/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.UnidadesEconomicasBean;
import beans.actividadesBean;
import beans.competenciaBean;
import beans.escuelaBean;
import beans.materiaBean;
import beans.programaEsBean;
import beans.renapoBean;
import beans.usuarioBean;
import java.util.List;

/**
 *
 * @author pedro
 */
public interface ConsultaDAO {

    public List programasEdu(programaEsBean programa) throws Exception;

    public List programasEduAdmin(programaEsBean programa) throws Exception;

    public List programasEduAdminAct(escuelaBean escuela) throws Exception;

    public List subsistema(programaEsBean programa) throws Exception;

    public List cctPlanMateria(escuelaBean escuela) throws Exception;

    public List tipoPeriodo(escuelaBean escuela) throws Exception;

    public List periodo(escuelaBean escuela) throws Exception;

    public List programasIdEs(escuelaBean escuela) throws Exception;

    public List escuelasSubsistema(escuelaBean escuela) throws Exception;

    public List validaCctPlan(escuelaBean escuela) throws Exception;

    public List validaMateriaComp(materiaBean materia) throws Exception;

    public List actividadCct(competenciaBean competencia) throws Exception;

    public List resProgEst(renapoBean renapo) throws Exception;

    public List resProgEstInst(renapoBean renapo) throws Exception;

    public List resUE(renapoBean renapo, UnidadesEconomicasBean ue) throws Exception;

    public List ueIeActiva(renapoBean renapo) throws Exception;

    public List validaCctPlanMateria(escuelaBean escuela) throws Exception;

    public List programasEscuela(escuelaBean escuela) throws Exception;

    public List programasEscuelaRes(escuelaBean escuela, renapoBean renapo) throws Exception;
     public List programasEscuelaDescarga(escuelaBean escuela, renapoBean renapo) throws Exception;

    public List programasEscuelaResProg(escuelaBean escuela, renapoBean renapo) throws Exception;

    public List programasEscuelaResMentorProg(escuelaBean escuela, renapoBean renapo) throws Exception;

    public List indicadoresTablero(usuarioBean usuario) throws Exception;
      public List indicadoresTableroSubDir(usuarioBean usuario, programaEsBean programa) throws Exception;
       public List indicadoresTableroEsc(usuarioBean usuario, programaEsBean programa) throws Exception;
    public List indicadoresTableroUE(usuarioBean usuario) throws Exception;
    
     public List indicadoresTableroSubDirUE(usuarioBean usuario, programaEsBean programa) throws Exception;
     
      public List indicadoresTableroEscUE(usuarioBean usuario, programaEsBean programa) throws Exception;
         public List listaUsuarios(usuarioBean usuario, programaEsBean programa) throws Exception;
      
     public List indicadoresTableroPlan(usuarioBean usuario) throws Exception;
     public List indicadoresTableroSubDirPlan(usuarioBean usuario, programaEsBean programa) throws Exception;
      public List indicadoresTableroEscPlan(usuarioBean usuario, programaEsBean programa) throws Exception;
     public List indicadoresTableroMun(usuarioBean usuario) throws Exception;
      public List indicadoresTableroSubDirMun(usuarioBean usuario, programaEsBean programa) throws Exception;
      public List indicadoresTableroEscMun(usuarioBean usuario, programaEsBean programa) throws Exception;
      public List indicadoresTableroSec(usuarioBean usuario) throws Exception;
       public List indicadoresTableroSubDirSec(usuarioBean usuario, programaEsBean programa) throws Exception;
        public List indicadoresTableroEscSec(usuarioBean usuario, programaEsBean programa) throws Exception;
      public List indicadoresTableroHist(usuarioBean usuario) throws Exception;

    public List indicadoresTableroNiv(usuarioBean usuario) throws Exception;
    
     public List indicadoresTableroDirSubNiv(usuarioBean usuario, programaEsBean programa) throws Exception;

    public boolean guardaPlanCct(escuelaBean escuela) throws Exception;

    public boolean guardaResPe(renapoBean renapo) throws Exception;

    public boolean guardaResPeIe(renapoBean renapo) throws Exception;

    public boolean guardaResUE(renapoBean renapo, UnidadesEconomicasBean ue) throws Exception;

    public boolean guardaResPeInst(renapoBean renapo, escuelaBean escuela) throws Exception;

    public boolean guardaResPeInstMentor(renapoBean renapo, escuelaBean escuela) throws Exception;

    public boolean actualizaResPeInst(renapoBean renapo, escuelaBean escuela) throws Exception;

    public boolean guardaUserResPeIe(renapoBean renapo, usuarioBean usuario) throws Exception;
      public boolean guardaUserAlu(renapoBean renapo, usuarioBean usuario) throws Exception;
        public String consultaIdEst(renapoBean renapo, usuarioBean usuario) throws Exception;
       public List consultaCorreo(renapoBean renapo, usuarioBean usuario) throws Exception;

    public boolean guardaUserResUE(renapoBean renapo, usuarioBean usuario, UnidadesEconomicasBean ue) throws Exception;

    public boolean guardaUserResAcad(renapoBean renapo, usuarioBean usuario, escuelaBean escuela) throws Exception;

    public boolean guardaCompetenciaCct(competenciaBean competencia, materiaBean materia) throws Exception;

    public boolean guardaActividad(competenciaBean competencia, actividadesBean actividad) throws Exception;

    public boolean guardaPlanCctMateria(escuelaBean escuela, materiaBean materia) throws Exception;

    public boolean guardaPrograma(escuelaBean escuela, programaEsBean programa) throws Exception;

    public boolean actualizaProgramaGuarda(escuelaBean escuela, programaEsBean programa) throws Exception;

    public boolean eliminarPrograma(escuelaBean escuela, programaEsBean programa) throws Exception;

    public boolean eliminarPlanCct(escuelaBean escuela) throws Exception;

    public boolean eliminarCompetenciaCct(competenciaBean competencia) throws Exception;

    public boolean eliminaActividad(actividadesBean actividad) throws Exception;

    public boolean eliminarMateria(materiaBean materia) throws Exception;

    public boolean actualizaEstatusPlanCct(escuelaBean escuela) throws Exception;

    public boolean actualizaEstatusPlan(escuelaBean escuela) throws Exception;

    public boolean actualizaCompetenciaCct(competenciaBean competencia) throws Exception;

    public boolean actualizaActividad(actividadesBean actividad) throws Exception;

    public boolean actualizaResPe(renapoBean renapo) throws Exception;

    public boolean actualizaUsuario(renapoBean renapo) throws Exception;
}
