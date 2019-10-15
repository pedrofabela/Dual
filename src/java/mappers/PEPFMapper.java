/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;


import beans.PlanFormacionBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class PEPFMapper implements Mapper{
    
   public Object mapRow(ResultSet rs) throws SQLException {
        PlanFormacionBean dat = new PlanFormacionBean();
        
        
         if (rs.getString("id_cct_plan") != null) {
            dat.setID_PE(rs.getString("id_cct_plan").trim());
        } else {
            dat.setID_PE(rs.getString("id_cct_plan"));
        }
        
          if (rs.getString("nom_carrera") != null) {
            dat.setNOM_CARRERA(rs.getString("nom_carrera").trim());
        } else {
            dat.setNOM_CARRERA(rs.getString("nom_carrera"));
        }
            if (rs.getString("PERIODO_INICIO_DUAL") != null) {
            dat.setPERIODO_INICIO_DUAL(rs.getString("PERIODO_INICIO_DUAL").trim());
        } else {
            dat.setPERIODO_INICIO_DUAL(rs.getString("PERIODO_INICIO_DUAL"));
        }
            
        return dat;
    }
    
}
