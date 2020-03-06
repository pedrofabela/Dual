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
public class cantidadReportesMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
 
       
        
        
           if (rs.getString("CANTIDAD") != null) {
            dat.setCANTIDAD(rs.getString("CANTIDAD").trim());
        } else {
            dat.setCANTIDAD(rs.getString("CANTIDAD"));
        }
            if (rs.getString("ERROR_PLANMS") != null) {
            dat.setERROR_PLANMS(rs.getString("ERROR_PLANMS").trim());
        } else {
            dat.setERROR_PLANMS(rs.getString("ERROR_PLANMS"));
        }
            
        
      
          
          
        return dat;
    }
    
}
