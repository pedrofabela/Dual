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
public class rubricaMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
    
        
           if (rs.getString("ID_RUBRICA") != null) {
            dat.setID_RUBRICA(rs.getString("ID_RUBRICA").trim());
        } else {
            dat.setID_RUBRICA(rs.getString("ID_RUBRICA"));
        }
             if (rs.getString("DESC_RUBRICA") != null) {
            dat.setDESC_RUBRICA(rs.getString("DESC_RUBRICA").trim());
        } else {
            dat.setDESC_RUBRICA(rs.getString("DESC_RUBRICA"));
        }
        
        
         
                      
          
        return dat;
    }
    
}
