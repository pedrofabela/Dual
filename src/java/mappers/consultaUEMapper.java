/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.UnidadesEconomicasBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class consultaUEMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         UnidadesEconomicasBean dat = new UnidadesEconomicasBean();
        

        
       
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
