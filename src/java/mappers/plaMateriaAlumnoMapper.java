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
public class plaMateriaAlumnoMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
 
       
        
        
           if (rs.getString("ID_MATERIA") != null) {
            dat.setID_MATERIA(rs.getString("ID_MATERIA").trim());
        } else {
            dat.setID_MATERIA(rs.getString("ID_MATERIA"));
        }
             if (rs.getString("NOMBRE_MATERIA") != null) {
            dat.setNOMBRE_MATERIA(rs.getString("NOMBRE_MATERIA").trim());
        } else {
            dat.setNOMBRE_MATERIA(rs.getString("NOMBRE_MATERIA"));
        }
        
       
                      
                      
                      
          
        return dat;
    }
    
}
