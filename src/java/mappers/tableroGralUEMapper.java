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
public class tableroGralUEMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         tableroGralBean dat = new tableroGralBean();
        
        
         if (rs.getString("ID_UE") != null) {
            dat.setID_UE(rs.getString("ID_UE").trim());
        } else {
            dat.setID_UE(rs.getString("ID_UE"));
        }
           if (rs.getString("ID_SUC") != null) {
            dat.setID_SUC(rs.getString("ID_SUC").trim());
        } else {
            dat.setID_SUC(rs.getString("ID_SUC"));
        }
           
          if (rs.getString("EN_PROCESO") != null) {
            dat.setEN_PROCESO(rs.getString("EN_PROCESO").trim());
        } else {
            dat.setEN_PROCESO(rs.getString("EN_PROCESO"));
        }   
          
            if (rs.getString("ACTIVO_UE") != null) {
            dat.setACTIVO_UE(rs.getString("ACTIVO_UE").trim());
        } else {
            dat.setACTIVO_UE(rs.getString("ACTIVO_UE"));
        }   
            
        if (rs.getString("BAJA_UE") != null) {
            dat.setBAJA_UE(rs.getString("BAJA_UE").trim());
        } else {
            dat.setBAJA_UE(rs.getString("BAJA_UE"));
        } 
          if (rs.getString("EGRESADO_UE") != null) {
            dat.setEGRESADO_UE(rs.getString("EGRESADO_UE").trim());
        } else {
            dat.setEGRESADO_UE(rs.getString("EGRESADO_UE"));
        } 
         if (rs.getString("RFC") != null) {
            dat.setRFC(rs.getString("RFC").trim());
        } else {
            dat.setRFC(rs.getString("RFC"));
        } 
      
          if (rs.getString("RAZON_SOCIAL") != null) {
            dat.setRAZON_SOCIAL(rs.getString("RAZON_SOCIAL").trim());
        } else {
            dat.setRAZON_SOCIAL(rs.getString("RAZON_SOCIAL"));
        } 
          
        return dat;
    }
    
}
