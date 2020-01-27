/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.AlumnoBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class conCorreoMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
        AlumnoBean dat = new AlumnoBean();
        
        if (rs.getString("EMAIL_INS") != null) {
            dat.setEMAIL_INS(rs.getString("EMAIL_INS").trim());
        } else {
            dat.setEMAIL_INS(rs.getString("EMAIL_INS"));
        }
        
         if (rs.getString("EMAIL_PER") != null) {
            dat.setEMAIL_PER(rs.getString("EMAIL_PER").trim());
        } else {
            dat.setEMAIL_PER(rs.getString("EMAIL_PER"));
        }
        
               
        return dat;
    }
    
    
    
}
