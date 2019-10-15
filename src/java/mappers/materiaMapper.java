/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.escuelaBean;
import beans.materiaBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class materiaMapper implements Mapper{
   public Object mapRow(ResultSet rs) throws SQLException {
         materiaBean dat = new materiaBean();
        

        
         if (rs.getString("ID_CCT_PLAN") != null) {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN").trim());
        } else {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN"));
        }
        
        
          if (rs.getString("ID_MATERIA") != null) {
            dat.setID_MATERIA(rs.getString("ID_MATERIA").trim());
        } else {
            dat.setID_MATERIA(rs.getString("ID_MATERIA"));
        }
        
         
          if (rs.getString("NOMBRE_MATERIA") != null) {
            dat.setNOMBRE_MATERIA(rs.getString("NOMBRE_MATERIA").trim());
        } else {
            dat.setNOMBRE_MATERIA(rs.getString("NOMBRE_MATERIA"));
        }
        
         
         
           if (rs.getString("FECH_REG_MATERIA") != null) {
            dat.setFECH_REG_MATERIA(rs.getString("FECH_REG_MATERIA").trim());
        } else {
            dat.setFECH_REG_MATERIA(rs.getString("FECH_REG_MATERIA"));
        }
        
           
            if (rs.getString("CVE_MATERIA") != null) {
            dat.setCVE_MATERIA(rs.getString("CVE_MATERIA").trim());
        } else {
            dat.setCVE_MATERIA(rs.getString("CVE_MATERIA"));
        }
        
       

        return dat;
    } 
}
