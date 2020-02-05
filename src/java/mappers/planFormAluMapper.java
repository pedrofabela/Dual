/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.programaEsBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class planFormAluMapper implements Mapper{
    
    public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
 
       
        
        
           if (rs.getString("ID_HIST_ALUM") != null) {
            dat.setID_HIST_ALUM(rs.getString("ID_HIST_ALUM").trim());
        } else {
            dat.setID_HIST_ALUM(rs.getString("NOM_CARRERA"));
        }
             if (rs.getString("ID_ALUMNO") != null) {
            dat.setID_ALUMNO(rs.getString("ID_ALUMNO").trim());
        } else {
            dat.setID_ALUMNO(rs.getString("ID_ALUMNO"));
        }
        
        if (rs.getString("ID_IE_UE") != null) {
            dat.setID_IE_UE(rs.getString("ID_IE_UE").trim());
        } else {
            dat.setID_IE_UE(rs.getString("ID_IE_UE"));
        }
        
        
          
        if  (rs.getString("STATUS_GENERAL") != null) {
            dat.setSTATUS_GENERAL(rs.getString("STATUS_GENERAL").trim());
        } else {
            dat.setSTATUS_GENERAL(rs.getString("STATUS_GENERAL"));
        }    
          
          
          
           if  (rs.getString("FECHA_INICIO_PF") != null) {
            dat.setFECHA_INICIO_PF(rs.getString("FECHA_INICIO_PF").trim());
        } else {
            dat.setFECHA_INICIO_PF(rs.getString("FECHA_INICIO_PF"));
        }    
           
             if  (rs.getString("FECHA_BAJA") != null) {
            dat.setFECHA_BAJA(rs.getString("FECHA_BAJA").trim());
        } else {
            dat.setFECHA_BAJA(rs.getString("FECHA_BAJA"));
        }    
            
            if  (rs.getString("FECHA_EGRESO") != null) {
            dat.setFECHA_EGRESO(rs.getString("FECHA_EGRESO").trim());
        } else {
            dat.setFECHA_EGRESO(rs.getString("FECHA_EGRESO"));
        }    
       
              if  (rs.getString("ID_CICLO") != null) {
            dat.setID_CICLO(rs.getString("ID_CICLO").trim());
        } else {
            dat.setID_CICLO(rs.getString("ID_CICLO"));
            
            
        }    
              
              
              if  (rs.getString("FECHA_INICIOPF") != null) {
            dat.setFECHA_INICIOPF(rs.getString("FECHA_INICIOPF").trim());
        } else {
            dat.setFECHA_INICIOPF(rs.getString("FECHA_INICIOPF"));
            
            
        }      
              
                if  (rs.getString("FECHA_TERMINOPF") != null) {
            dat.setFECHA_TERMINOPF(rs.getString("FECHA_TERMINOPF").trim());
        } else {
            dat.setFECHA_TERMINOPF(rs.getString("FECHA_TERMINOPF"));
            
            
        }        
          
                 if  (rs.getString("ESTATUS_PF") != null) {
            dat.setESTATUS_PF(rs.getString("ESTATUS_PF").trim());
        } else {
            dat.setESTATUS_PF(rs.getString("ESTATUS_PF"));
            
            
        }   
                 
                  if  (rs.getString("NOMBREPLAN_FORM") != null) {
            dat.setNOMBREPLAN_FORM(rs.getString("NOMBREPLAN_FORM").trim());
        } else {
            dat.setNOMBREPLAN_FORM(rs.getString("NOMBREPLAN_FORM"));
            
            
        }        
                  
                  if  (rs.getString("DESCRIPCION") != null) {
            dat.setDESCRIPCION(rs.getString("DESCRIPCION").trim());
        } else {
            dat.setDESCRIPCION(rs.getString("DESCRIPCION"));
            
            
        }    
                   if  (rs.getString("DURACION") != null) {
            dat.setDURACION(rs.getString("DURACION").trim());
        } else {
            dat.setDURACION(rs.getString("DURACION"));
            
            
        }    
                      if  (rs.getString("HORAS_SEMANA") != null) {
            dat.setHORAS_SEMANA(rs.getString("HORAS_SEMANA").trim());
        } else {
            dat.setHORAS_SEMANA(rs.getString("HORAS_SEMANA"));
            
            
        }        
                      
            if  (rs.getString("TOTAL_REPORTES") != null) {
            dat.setTOTAL_REPORTES(rs.getString("TOTAL_REPORTES").trim());
        } else {
            dat.setTOTAL_REPORTES(rs.getString("TOTAL_REPORTES"));
            
            
        }                    
                      
          
              if  (rs.getString("REPORTES_HECHOS") != null) {
            dat.setREPORTES_HECHOS(rs.getString("REPORTES_HECHOS").trim());
        } else {
            dat.setREPORTES_HECHOS(rs.getString("REPORTES_HECHOS"));
            
            
        }                
                      
           if  (rs.getString("REPORTES_FALTANTES") != null) {
            dat.setREPORTES_FALTANTES(rs.getString("REPORTES_FALTANTES").trim());
        } else {
            dat.setREPORTES_FALTANTES(rs.getString("REPORTES_FALTANTES"));
            
            
        }    
           
             if  (rs.getString("POR_REG_ALUMNO") != null) {
            dat.setPOR_REG_ALUMNO(rs.getString("POR_REG_ALUMNO").trim());
        } else {
            dat.setPOR_REG_ALUMNO(rs.getString("POR_REG_ALUMNO"));
            
            
        }      
                if  (rs.getString("ID_PLAN_FORM") != null) {
            dat.setID_PLAN_FORM(rs.getString("ID_PLAN_FORM").trim());
        } else {
            dat.setID_PLAN_FORM(rs.getString("ID_PLAN_FORM"));
            
            
        }        
          
        return dat;
    }
    
    
}
