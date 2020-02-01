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
public class planAsigMatPlanMapper implements Mapper{
    public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
 
       
        
        
           if (rs.getString("ID_COMPETENCIA") != null) {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA").trim());
        } else {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA"));
        }
             if (rs.getString("COMPETENCIA") != null) {
            dat.setCOMPETENCIA(rs.getString("COMPETENCIA").trim());
        } else {
            dat.setCOMPETENCIA(rs.getString("COMPETENCIA"));
        }
        
        if (rs.getString("ID_REGALUMCOMP") != null) {
            dat.setID_REGALUMCOMP(rs.getString("ID_REGALUMCOMP").trim());
        } else {
            dat.setID_REGALUMCOMP(rs.getString("ID_REGALUMCOMP"));
        }
                      
          if (rs.getString("MARCO_TEORICO") != null) {
            dat.setMARCO_TEORICO(rs.getString("MARCO_TEORICO").trim());
        } else {
            dat.setMARCO_TEORICO(rs.getString("MARCO_TEORICO"));
        }            
           if (rs.getString("DES_ACT") != null) {
            dat.setDES_ACT(rs.getString("DES_ACT").trim());
        } else {
            dat.setDES_ACT(rs.getString("DES_ACT"));
        }                   
                      
         if (rs.getString("RUTA_EVIDENCIAS") != null) {
            dat.setRUTA_EVIDENCIAS(rs.getString("RUTA_EVIDENCIAS").trim());
        } else {
            dat.setRUTA_EVIDENCIAS(rs.getString("RUTA_EVIDENCIAS"));
        }                   
            if (rs.getString("FECHA_REG") != null) {
            dat.setFECHA_REG(rs.getString("FECHA_REG").trim());
        } else {
            dat.setFECHA_REG(rs.getString("FECHA_REG"));
        }  
             if (rs.getString("EDITA") != null) {
            dat.setEDITA(rs.getString("EDITA").trim());
        } else {
            dat.setEDITA(rs.getString("EDITA"));
        }     
             
            if (rs.getString("EVAL_MUE") != null) {
            dat.setEVAL_MUE(rs.getString("EVAL_MUE").trim());
        } else {
            dat.setEVAL_MUE(rs.getString("EVAL_MUE"));
        }  
             if (rs.getString("EVAL_MA") != null) {
            dat.setEVAL_MA(rs.getString("EVAL_MA").trim());
        } else {
            dat.setEVAL_MA(rs.getString("EVAL_MA"));
        }         
          
        return dat;
    }
}
