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
public class horaMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        

        
         if (rs.getString("ID_HORA") != null) {
            dat.setID_HORA(rs.getString("ID_HORA").trim());
        } else {
            dat.setID_HORA(rs.getString("ID_HORA"));
        }
         if (rs.getString("HORA") != null) {
            dat.setHORA(rs.getString("HORA").trim());
        } else {
            dat.setHORA(rs.getString("HORA"));
        }
        
      
        
        return dat;
    }
}
