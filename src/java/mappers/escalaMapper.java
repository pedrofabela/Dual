/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.UnidadesEconomicasBean;
import beans.programaEsBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class escalaMapper implements Mapper{
     
    public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        

        
         if (rs.getString("ID_ESCALA") != null) {
            dat.setID_ESCALA(rs.getString("ID_ESCALA").trim());
        } else {
            dat.setID_ESCALA(rs.getString("ID_ESCALA"));
        }
         if (rs.getString("ESCALA") != null) {
            dat.setESCALA(rs.getString("ESCALA").trim());
        } else {
            dat.setESCALA(rs.getString("ESCALA"));
        }
        
      
        
        return dat;
    }
}
