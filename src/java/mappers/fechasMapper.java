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
public class fechasMapper implements Mapper{
    
       public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
 
       
        
        
           if (rs.getString("ID_FECHA") != null) {
            dat.setID_FECHA(rs.getString("ID_FECHA").trim());
        } else {
            dat.setID_FECHA(rs.getString("ID_FECHA"));
        }
             if (rs.getString("FECHA") != null) {
            dat.setFECHA(rs.getString("FECHA").trim());
        } else {
            dat.setFECHA(rs.getString("FECHA"));
        }
        
      
          
          
        return dat;
    }
    
    
}
