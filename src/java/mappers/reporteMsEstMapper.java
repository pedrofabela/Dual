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
public class reporteMsEstMapper implements Mapper{
    
    
      public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
 
       
        
        
           if (rs.getString("ID_EVAL_DIA") != null) {
            dat.setID_EVAL_DIA(rs.getString("ID_EVAL_DIA").trim());
        } else {
            dat.setID_EVAL_DIA(rs.getString("CANTIDAD"));
        }
            if (rs.getString("ID_HIST_ALU") != null) {
            dat.setID_HIST_ALU(rs.getString("ID_HIST_ALU").trim());
        } else {
            dat.setID_HIST_ALU(rs.getString("ID_HIST_ALU"));
        }
            
         if (rs.getString("ID_SEMANA") != null) {
            dat.setID_SEMANA(rs.getString("ID_SEMANA").trim());
        } else {
            dat.setID_SEMANA(rs.getString("ID_SEMANA"));
        }
        if (rs.getString("NOMBRE_ACTIVIDAD") != null) {
            dat.setNOMBRE_ACTIVIDAD(rs.getString("NOMBRE_ACTIVIDAD").trim());
        } else {
            dat.setNOMBRE_ACTIVIDAD(rs.getString("NOMBRE_ACTIVIDAD"));
        }
          if (rs.getString("OBJETIVO") != null) {
            dat.setOBJETIVO(rs.getString("OBJETIVO").trim());
        } else {
            dat.setOBJETIVO(rs.getString("OBJETIVO"));
        }
          
            if (rs.getString("MARCO_TEORICO") != null) {
            dat.setMARCO_TEORICO(rs.getString("MARCO_TEORICO").trim());
        } else {
            dat.setMARCO_TEORICO(rs.getString("MARCO_TEORICO"));
        }
          
            if (rs.getString("DESARROLLO") != null) {
            dat.setDESARROLLO(rs.getString("DESARROLLO").trim());
        } else {
            dat.setDESARROLLO(rs.getString("DESARROLLO"));
        }
              if (rs.getString("EQUIPO") != null) {
            dat.setEQUIPO(rs.getString("EQUIPO").trim());
        } else {
            dat.setEQUIPO(rs.getString("EQUIPO"));
        }
              
               if (rs.getString("MEDIDAS_SEGURIDAD") != null) {
            dat.setMEDIDAS_SEGURIDAD(rs.getString("MEDIDAS_SEGURIDAD").trim());
        } else {
            dat.setMEDIDAS_SEGURIDAD(rs.getString("MEDIDAS_SEGURIDAD"));
        }
               
                if (rs.getString("NORMAS_CALIDAD") != null) {
            dat.setNORMAS_CALIDAD(rs.getString("NORMAS_CALIDAD").trim());
        } else {
            dat.setNORMAS_CALIDAD(rs.getString("NORMAS_CALIDAD"));
        }
                
                    if (rs.getString("FECHA_REPORTE") != null) {
            dat.setFECHA_REPORTE(rs.getString("FECHA_REPORTE").trim());
        } else {
            dat.setFECHA_REPORTE(rs.getString("FECHA_REPORTE"));
        }
           
              if (rs.getString("FECHA_REG") != null) {
            dat.setFECHA_REG(rs.getString("FECHA_REG").trim());
        } else {
            dat.setFECHA_REG(rs.getString("FECHA_REG"));
        }      
                  if (rs.getString("ID_DIA") != null) {
            dat.setID_DIA(rs.getString("ID_DIA").trim());
        } else {
            dat.setID_DIA(rs.getString("ID_DIA"));
        }      
                if (rs.getString("ID_MATERIA") != null) {
            dat.setID_MATERIA(rs.getString("ID_MATERIA").trim());
        } else {
            dat.setID_MATERIA(rs.getString("ID_MATERIA"));
        }     
                  if (rs.getString("ID_COMPETENCIA") != null) {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA").trim());
        } else {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA"));
        }        
             if (rs.getString("ID_ACTIVIDAD") != null) {
            dat.setID_ACTIVIDAD(rs.getString("ID_ACTIVIDAD").trim());
        } else {
            dat.setID_ACTIVIDAD(rs.getString("ID_ACTIVIDAD"));
        }           
          if (rs.getString("RUTA_EVIDENCIA") != null) {
            dat.setRUTA_EVIDENCIA(rs.getString("RUTA_EVIDENCIA").trim());
        } else {
            dat.setRUTA_EVIDENCIA(rs.getString("RUTA_EVIDENCIA"));
        }                     
            if (rs.getString("EVAL_MA") != null) {
            dat.setEVAL_MA(rs.getString("EVAL_MA").trim());
        } else {
            dat.setEVAL_MA(rs.getString("EVAL_MA"));
        }   
            
             if (rs.getString("FECHA_EVAL_MA") != null) {
            dat.setFECHA_EVAL_MA(rs.getString("FECHA_EVAL_MA").trim());
        } else {
            dat.setFECHA_EVAL_MA(rs.getString("FECHA_EVAL_MA"));
        }    
              if (rs.getString("AUTO_ESTUDIO") != null) {
            dat.setAUTO_ESTUDIO(rs.getString("AUTO_ESTUDIO").trim());
        } else {
            dat.setAUTO_ESTUDIO(rs.getString("AUTO_ESTUDIO"));
        }         
            if (rs.getString("OBSERVACION") != null) {
            dat.setOBSERVACION(rs.getString("OBSERVACION").trim());
        } else {
            dat.setOBSERVACION(rs.getString("OBSERVACION"));
        }         
           if (rs.getString("EVAL_MUE") != null) {
            dat.setEVAL_MUE(rs.getString("EVAL_MUE").trim());
        } else {
            dat.setEVAL_MUE(rs.getString("EVAL_MUE"));
        }       
            if (rs.getString("FECHA_EVAL_MUE") != null) {
            dat.setFECHA_EVAL_MUE(rs.getString("FECHA_EVAL_MUE").trim());
        } else {
            dat.setFECHA_EVAL_MUE(rs.getString("FECHA_EVAL_MUE"));
        }           
            
        return dat;
    }
}
