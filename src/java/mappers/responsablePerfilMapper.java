/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.SectorSubsectorBean;
import beans.responsablesBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilidades.Constantes;

/**
 *
 * @author pedro
 */
public class responsablePerfilMapper implements Mapper {
     public Object mapRow(ResultSet rs) throws SQLException {
        
       
        responsablesBean dat = new responsablesBean();
        
        
         if (rs.getString("ID_RESPROG_INST") != null) {
            dat.setID_RESPROG_INST(rs.getString("ID_RESPROG_INST").trim());
        } else {
            dat.setID_RESPROG_INST(rs.getString("ID_RESPROG_INST"));
        }
           if (rs.getString("ID_PERSONA") != null) {
            dat.setID_PERSONA(rs.getString("ID_PERSONA").trim());
        } else {
            dat.setID_PERSONA(rs.getString("ID_PERSONA"));
        }
           
          if (rs.getString("CURP_PERSONA") != null) {
            dat.setCURP_PERSONA(rs.getString("CURP_PERSONA").trim());
        } else {
            dat.setCURP_PERSONA(rs.getString("CURP_PERSONA"));
        }   
          
            if (rs.getString("NOMBRE_PERSONA") != null) {
            dat.setNOMBRE_PERSONA(rs.getString("NOMBRE_PERSONA").trim());
        } else {
            dat.setNOMBRE_PERSONA(rs.getString("NOMBRE_PERSONA"));
        }   
      
        
         
        return dat;
    }
}
