/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.programaEsBean;
import beans.subsistemaBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class subsistemaMapper implements Mapper{
      public Object mapRow(ResultSet rs) throws SQLException {
          subsistemaBean dat = new subsistemaBean();
        
        
         if (rs.getString("ID_SUBSISTEMA") != null) {
            dat.setID_SUBSISTEMA(rs.getString("ID_SUBSISTEMA").trim());
        } else {
            dat.setID_SUBSISTEMA(rs.getString("ID_SUBSISTEMA"));
        }
           if (rs.getString("NOMBRE_SUBISTEMA") != null) {
            dat.setNOMBRE_SUBISTEMA(rs.getString("NOMBRE_SUBISTEMA").trim());
        } else {
            dat.setNOMBRE_SUBISTEMA(rs.getString("NOMBRE_SUBISTEMA"));
        }
         if (rs.getString("ID_NIVEL") != null) {
            dat.setID_NIVEL(rs.getString("ID_NIVEL").trim());
        } else {
            dat.setID_NIVEL(rs.getString("ID_NIVEL"));
        }
       
        return dat;
    }
    
    
    
}
