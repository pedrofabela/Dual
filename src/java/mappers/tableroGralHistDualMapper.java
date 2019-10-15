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
public class tableroGralHistDualMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         tableroGralBean dat = new tableroGralBean();
        
        
         if (rs.getString("ID_HISTORIA") != null) {
            dat.setID_HISTORIA(rs.getString("ID_HISTORIA").trim());
        } else {
            dat.setID_HISTORIA(rs.getString("ID_HISTORIA"));
        }
           if (rs.getString("PERIODO") != null) {
            dat.setPERIODO(rs.getString("PERIODO").trim());
        } else {
            dat.setPERIODO(rs.getString("PERIODO"));
        }
           
          if (rs.getString("MSUPERIOR") != null) {
            dat.setMSUPERIOR(rs.getString("MSUPERIOR").trim());
        } else {
            dat.setMSUPERIOR(rs.getString("MSUPERIOR"));
        }   
          
            if (rs.getString("SUPERIOR") != null) {
            dat.setSUPERIOR(rs.getString("SUPERIOR").trim());
        } else {
            dat.setSUPERIOR(rs.getString("SUPERIOR"));
        }   
            
        if (rs.getString("TOTAL_DUAL_HIST") != null) {
            dat.setTOTAL_DUAL_HIST(rs.getString("TOTAL_DUAL_HIST").trim());
        } else {
            dat.setTOTAL_DUAL_HIST(rs.getString("TOTAL_DUAL_HIST"));
        } 
          if (rs.getString("ESTATUS") != null) {
            dat.setESTATUS(rs.getString("ESTATUS").trim());
        } else {
            dat.setESTATUS(rs.getString("ESTATUS"));
        } 
          if (rs.getString("ACTUAL") != null) {
            dat.setACTUAL(rs.getString("ACTUAL").trim());
        } else {
            dat.setACTUAL(rs.getString("ACTUAL"));
        } 
      
        return dat;
    }
    
}
