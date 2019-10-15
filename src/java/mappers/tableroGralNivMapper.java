/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.tableroGralBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class tableroGralNivMapper implements Mapper{
    
    
     public Object mapRow(ResultSet rs) throws SQLException {
         tableroGralBean dat = new tableroGralBean();
        
        
         if (rs.getString("ID_NIVEL") != null) {
            dat.setID_NIVEL(rs.getString("ID_NIVEL").trim());
        } else {
            dat.setID_NIVEL(rs.getString("ID_NIVEL"));
        }
           if (rs.getString("NOMBRE_NIVEL") != null) {
            dat.setNOMBRE_NIVEL(rs.getString("NOMBRE_NIVEL").trim());
        } else {
            dat.setNOMBRE_NIVEL(rs.getString("NOMBRE_NIVEL"));
        }
           
          if (rs.getString("ACTIVOS_NIV") != null) {
            dat.setACTIVOS_NIV(rs.getString("ACTIVOS_NIV").trim());
        } else {
            dat.setACTIVOS_NIV(rs.getString("ACTIVOS_NIV"));
        }   
          
            if (rs.getString("BAJAS_NIV") != null) {
            dat.setBAJAS_NIV(rs.getString("BAJAS_NIV").trim());
        } else {
            dat.setBAJAS_NIV(rs.getString("BAJAS_NIV"));
        }   
            
        if (rs.getString("EGRESADOS_NIV") != null) {
            dat.setEGRESADOS_NIV(rs.getString("EGRESADOS_NIV").trim());
        } else {
            dat.setEGRESADOS_NIV(rs.getString("EGRESADOS_NIV"));
        } 
          if (rs.getString("TOTAL_NIV") != null) {
            dat.setTOTAL_NIV(rs.getString("TOTAL_NIV").trim());
        } else {
            dat.setTOTAL_NIV(rs.getString("TOTAL_NIV"));
        } 
         
      
        return dat;
    }
}
