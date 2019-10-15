/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.ciclosBean;
import beans.tableroGralBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class tableroGralMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         tableroGralBean dat = new tableroGralBean();
        
        
         if (rs.getString("TOTAL_ALUMNOS") != null) {
            dat.setTOTAL_ALUMNOS(rs.getString("TOTAL_ALUMNOS").trim());
        } else {
            dat.setTOTAL_ALUMNOS(rs.getString("TOTAL_ALUMNOS"));
        }
           if (rs.getString("ACTIVOS") != null) {
            dat.setACTIVOS(rs.getString("ACTIVOS").trim());
        } else {
            dat.setACTIVOS(rs.getString("ACTIVOS"));
        }
           
          if (rs.getString("BAJAS") != null) {
            dat.setBAJAS(rs.getString("BAJAS").trim());
        } else {
            dat.setBAJAS(rs.getString("BAJAS"));
        }   
          
            if (rs.getString("EGRESADOS") != null) {
            dat.setEGRESADOS(rs.getString("EGRESADOS").trim());
        } else {
            dat.setEGRESADOS(rs.getString("EGRESADOS"));
        }   
            
        if (rs.getString("NUEVO_INGRESO") != null) {
            dat.setNUEVO_INGRESO(rs.getString("NUEVO_INGRESO").trim());
        } else {
            dat.setNUEVO_INGRESO(rs.getString("NUEVO_INGRESO"));
        } 
        
          if (rs.getString("HOMBRES") != null) {
            dat.setHOMBRES(rs.getString("HOMBRES").trim());
        } else {
            dat.setHOMBRES(rs.getString("HOMBRES"));
        }     
          if (rs.getString("MUJER") != null) {
            dat.setMUJER(rs.getString("MUJER").trim());
        } else {
            dat.setMUJER(rs.getString("MUJER"));
        }      
      
        return dat;
    }
    
    
}
