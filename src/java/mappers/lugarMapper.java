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
public class lugarMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        

        
         if (rs.getString("ID_LUGAR") != null) {
            dat.setID_LUGAR(rs.getString("ID_LUGAR").trim());
        } else {
            dat.setID_LUGAR(rs.getString("ID_LUGAR"));
        }
         if (rs.getString("LUGAR") != null) {
            dat.setLUGAR(rs.getString("LUGAR").trim());
        } else {
            dat.setLUGAR(rs.getString("LUGAR"));
        }
        
      
        
        return dat;
    }
     
}
