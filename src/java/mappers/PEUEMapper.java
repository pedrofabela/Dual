/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;


import beans.AlumnoBean;
import beans.programaEsBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class PEUEMapper implements Mapper{
    
   public Object mapRow(ResultSet rs) throws SQLException {
        AlumnoBean dat = new AlumnoBean();
        
        
         if (rs.getString("id_cct_plan") != null) {
            dat.setID_CCT_PLAN(rs.getString("id_cct_plan").trim());
        } else {
            dat.setID_CCT_PLAN(rs.getString("id_cct_plan"));
        }
        
          if (rs.getString("nom_carrera") != null) {
            dat.setCARRERA(rs.getString("nom_carrera").trim());
        } else {
            dat.setCARRERA(rs.getString("nom_carrera"));
        }
            
        return dat;
    }
    
}
