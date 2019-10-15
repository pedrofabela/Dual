/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.ciclosBean;
import beans.tipoPeriodoBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class ciclosMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         ciclosBean dat = new ciclosBean();
        
        
         if (rs.getString("ID_CICLO") != null) {
            dat.setID_CICLO(rs.getString("ID_CICLO").trim());
        } else {
            dat.setID_CICLO(rs.getString("ID_CICLO"));
        }
           if (rs.getString("FECHA_INICIO") != null) {
            dat.setFECHA_INICIO(rs.getString("FECHA_INICIO").trim());
        } else {
            dat.setFECHA_INICIO(rs.getString("FECHA_INICIO"));
        }
           
          if (rs.getString("FECHA_TERMINO") != null) {
            dat.setFECHA_TERMINO(rs.getString("FECHA_TERMINO").trim());
        } else {
            dat.setFECHA_TERMINO(rs.getString("FECHA_TERMINO"));
        }   
          
            if (rs.getString("CICLO") != null) {
            dat.setCICLO(rs.getString("CICLO").trim());
        } else {
            dat.setCICLO(rs.getString("CICLO"));
        }   
            
           
      
        return dat;
    }
    
    
}
