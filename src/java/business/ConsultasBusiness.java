/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import beans.UnidadesEconomicasBean;
import beans.actividadesBean;
import beans.competenciaBean;
import beans.escuelaBean;
import beans.materiaBean;
import beans.programaEsBean;
import beans.renapoBean;
import beans.usuarioBean;
import java.util.List;
import daos.ConsultaDAO;
import daos.ConsultaDAOImpl;


/**
 *
 * @author pedro
 */
public class ConsultasBusiness {
    
     private ConsultaDAO con;
    
     public ConsultasBusiness() throws Exception {
        this.con = new ConsultaDAOImpl();
    }

   
    
    public List programasEdu(programaEsBean programa) throws Exception {
        List lista = this.con.programasEdu(programa);
        return lista;
    }
    public List programasEduAdmin(programaEsBean programa) throws Exception {
        List lista = this.con.programasEduAdmin(programa);
        return lista;
    }
     public List programasEduAdminAct(escuelaBean escuela) throws Exception {
        List lista = this.con.programasEduAdminAct(escuela);
        return lista;
    }
     public List subsistema(programaEsBean programa) throws Exception {
        List lista = this.con.subsistema(programa);
        return lista;
    }
    
    public List cctPlanMateria(escuelaBean escuela) throws Exception {
        List lista = this.con.cctPlanMateria(escuela);
        return lista;
    }
     public List tipoPeriodo(escuelaBean escuela) throws Exception {
        List lista = this.con.tipoPeriodo(escuela);
        return lista;
    }
     public List periodo(escuelaBean escuela) throws Exception {
        List lista = this.con.periodo(escuela);
        return lista;
    }
    
     public List programasIdEs(escuelaBean escuela) throws Exception {
        List lista = this.con.programasIdEs(escuela);
        return lista;
    }
     
      public List escuelasSubsistema(escuelaBean escuela) throws Exception {
        List lista = this.con.escuelasSubsistema(escuela);
        return lista;
    }
     
       public List validaCctPlan(escuelaBean escuela) throws Exception {
        List lista = this.con.validaCctPlan(escuela);
        return lista;
    }
     
     public List validaMateriaComp(materiaBean materia) throws Exception {
        List lista = this.con.validaMateriaComp(materia);
        return lista;
    }   
     
      public List actividadCct(competenciaBean competencia) throws Exception {
        List lista = this.con.actividadCct(competencia);
        return lista;
    }   
         public List resProgEst(renapoBean renapo) throws Exception {
        List lista = this.con.resProgEst(renapo);
        return lista;
    }   
         
           public List resProgEstInst(renapoBean renapo) throws Exception {
        List lista = this.con.resProgEstInst(renapo);
        return lista;
    }   
           
            public List resUE(renapoBean renapo, UnidadesEconomicasBean ue) throws Exception {
        List lista = this.con.resUE(renapo, ue);
        return lista;
    }   
            public List ueIeActiva(renapoBean renapo) throws Exception {
        List lista = this.con.ueIeActiva(renapo);
        return lista;
    }   
       
         public List validaCctPlanMateria(escuelaBean escuela) throws Exception {
        List lista = this.con.validaCctPlanMateria(escuela);
        return lista;
    }
    
      public List programasEscuela(escuelaBean escuela) throws Exception {
        List lista = this.con.programasEscuela(escuela);
        return lista;
    }
       public List programasEscuelaRes(escuelaBean escuela, renapoBean renapo) throws Exception {
        List lista = this.con.programasEscuelaRes(escuela, renapo);
        return lista;
    }
       
        public List programasEscuelaDescarga(escuelaBean escuela, renapoBean renapo) throws Exception {
        List lista = this.con.programasEscuelaDescarga(escuela, renapo);
        return lista;
    }
        public List programasEscuelaResProg(escuelaBean escuela, renapoBean renapo) throws Exception {
        List lista = this.con.programasEscuelaResProg(escuela, renapo);
        return lista;
    }
         public List programasEscuelaResMentorProg(escuelaBean escuela, renapoBean renapo) throws Exception {
        List lista = this.con.programasEscuelaResMentorProg(escuela, renapo);
        return lista;
    }
         
          public List indicadoresTablero(usuarioBean usuario) throws Exception {
        List lista = this.con.indicadoresTablero(usuario);
        return lista;
    }
          
           public List indicadoresTableroSubDir(usuarioBean usuario, programaEsBean programa) throws Exception {
        List lista = this.con.indicadoresTableroSubDir(usuario, programa);
        return lista;
    }
           
             public List indicadoresTableroEsc(usuarioBean usuario, programaEsBean programa) throws Exception {
        List lista = this.con.indicadoresTableroEsc(usuario, programa);
        return lista;
    }
          public List indicadoresTableroUE(usuarioBean usuario) throws Exception {
        List lista = this.con.indicadoresTableroUE(usuario);
        return lista;
    }
          public List indicadoresTableroSubDirUE(usuarioBean usuario, programaEsBean programa) throws Exception {
        List lista = this.con.indicadoresTableroSubDirUE(usuario, programa);
        return lista;
    }
       public List indicadoresTableroEscUE(usuarioBean usuario, programaEsBean programa) throws Exception {
        List lista = this.con.indicadoresTableroEscUE(usuario, programa);
        return lista;
    }   
     
       public List listaUsuarios(usuarioBean usuario, programaEsBean programa) throws Exception {
        List lista = this.con.listaUsuarios(usuario, programa);
        return lista;
    }    
       
          
           public List indicadoresTableroPlan(usuarioBean usuario) throws Exception {
        List lista = this.con.indicadoresTableroPlan(usuario);
        return lista;
    }
           
            public List indicadoresTableroSubDirPlan(usuarioBean usuario, programaEsBean programa) throws Exception {
        List lista = this.con.indicadoresTableroSubDirPlan(usuario, programa);
        return lista;
    }
            
             public List indicadoresTableroEscPlan(usuarioBean usuario, programaEsBean programa) throws Exception {
        List lista = this.con.indicadoresTableroEscPlan(usuario, programa);
        return lista;
    }
           
           public List indicadoresTableroMun(usuarioBean usuario) throws Exception {
        List lista = this.con.indicadoresTableroMun(usuario);
        return lista;
    }
           
            public List indicadoresTableroSubDirMun(usuarioBean usuario, programaEsBean programa ) throws Exception {
        List lista = this.con.indicadoresTableroSubDirMun(usuario,  programa);
        return lista;
    } 
        public List indicadoresTableroEscMun(usuarioBean usuario, programaEsBean programa ) throws Exception {
        List lista = this.con.indicadoresTableroEscMun(usuario,  programa);
        return lista;
    }      
           
            public List indicadoresTableroSec(usuarioBean usuario) throws Exception {
        List lista = this.con.indicadoresTableroSec(usuario);
        return lista;
    }
            
             public List indicadoresTableroSubDirSec(usuarioBean usuario, programaEsBean programa) throws Exception {
        List lista = this.con.indicadoresTableroSubDirSec(usuario, programa);
        return lista;
    }
      public List indicadoresTableroEscSec(usuarioBean usuario, programaEsBean programa) throws Exception {
        List lista = this.con.indicadoresTableroEscSec(usuario, programa);
        return lista;
    }        
             
             public List indicadoresTableroHist(usuarioBean usuario) throws Exception {
        List lista = this.con.indicadoresTableroHist(usuario);
        return lista;
    }
          
          public List indicadoresTableroNiv(usuarioBean usuario) throws Exception {
        List lista = this.con.indicadoresTableroNiv(usuario);
        return lista;
    }
            public List indicadoresTableroDirSubNiv(usuarioBean usuario, programaEsBean programa) throws Exception {
        List lista = this.con.indicadoresTableroDirSubNiv(usuario, programa);
        return lista;
    }
      
    public boolean guardaPlanCct(escuelaBean escuela) throws Exception {
        return this.con.guardaPlanCct(escuela);
    }   
        
    public boolean guardaResPe(renapoBean renapo) throws Exception {
        return this.con.guardaResPe(renapo);
    }  
    
     
    
     public boolean guardaResPeIe(renapoBean renapo) throws Exception {
        return this.con.guardaResPeIe(renapo);
    }   
      public boolean guardaResUE(renapoBean renapo, UnidadesEconomicasBean ue) throws Exception {
        return this.con.guardaResUE(renapo, ue);
    } 
       public boolean guardaResPeInst(renapoBean renapo, escuelaBean escuela) throws Exception {
        return this.con.guardaResPeInst(renapo, escuela);
    }   
       
        public boolean guardaResPeInstMentor(renapoBean renapo, escuelaBean escuela) throws Exception {
        return this.con.guardaResPeInstMentor(renapo, escuela);
    }   
       
        public boolean actualizaResPeInst(renapoBean renapo, escuelaBean escuela) throws Exception {
        return this.con.actualizaResPeInst(renapo, escuela);
    }   
     
      public boolean guardaUserResPeIe(renapoBean renapo, usuarioBean usuario) throws Exception {
        return this.con.guardaUserResPeIe(renapo, usuario);
    }  
      
      public boolean guardaUserResUE(renapoBean renapo, usuarioBean usuario, UnidadesEconomicasBean ue) throws Exception {
        return this.con.guardaUserResUE(renapo, usuario, ue);
    }   
        public boolean guardaUserResAcad(renapoBean renapo, usuarioBean usuario, escuelaBean escuela) throws Exception {
        return this.con.guardaUserResAcad(renapo, usuario, escuela);
    }   
    
      public boolean guardaPlanCctMateria(escuelaBean escuela, materiaBean materia) throws Exception {
        return this.con.guardaPlanCctMateria(escuela, materia);
    } 
      
      public boolean guardaPrograma(escuelaBean escuela, programaEsBean programa) throws Exception {
        return this.con.guardaPrograma(escuela, programa);
    }   
       public boolean actualizaProgramaGuarda(escuelaBean escuela, programaEsBean programa) throws Exception {
        return this.con.actualizaProgramaGuarda(escuela, programa);
    }   
         public boolean eliminarPrograma(escuelaBean escuela, programaEsBean programa) throws Exception {
        return this.con.eliminarPrograma(escuela, programa);
    }   
       
      public boolean guardaCompetenciaCct(competenciaBean competencia, materiaBean materia) throws Exception {
        return this.con.guardaCompetenciaCct(competencia, materia);
    }   
    
     public boolean guardaActividad(competenciaBean competencia, actividadesBean actividad) throws Exception {
        return this.con.guardaActividad(competencia, actividad);
    }   
    
     public boolean eliminarPlanCct(escuelaBean escuela) throws Exception {
        return this.con.eliminarPlanCct(escuela);
    }   
     
      public boolean eliminarCompetenciaCct(competenciaBean competencia) throws Exception {
        return this.con.eliminarCompetenciaCct(competencia);
    }   
      
       public boolean eliminaActividad(actividadesBean actividad) throws Exception {
        return this.con.eliminaActividad(actividad);
    }   
     
      public boolean eliminarMateria(materiaBean materia) throws Exception {
        return this.con.eliminarMateria(materia);
    } 
     
      
      public boolean actualizaEstatusPlanCct(escuelaBean escuela) throws Exception {
        return this.con.actualizaEstatusPlanCct(escuela);
    }   
       public boolean actualizaEstatusPlan(escuelaBean escuela) throws Exception {
        return this.con.actualizaEstatusPlan(escuela);
    }   
        
      public boolean actualizaCompetenciaCct(competenciaBean competencia) throws Exception {
        return this.con.actualizaCompetenciaCct(competencia);
    }     
      
    public boolean actualizaActividad(actividadesBean actividad) throws Exception {
        return this.con.actualizaActividad(actividad);
    }        
     public boolean actualizaResPe(renapoBean renapo) throws Exception {
        return this.con.actualizaResPe(renapo);
    }   
      public boolean actualizaUsuario(renapoBean renapo) throws Exception {
        return this.con.actualizaUsuario(renapo);
    }   
      
}
