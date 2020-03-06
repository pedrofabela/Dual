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
public class pfSemanasMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
 
       
        
        
           if (rs.getString("ID_SEMANA") != null) {
            dat.setID_SEMANA(rs.getString("ID_SEMANA").trim());
        } else {
            dat.setID_SEMANA(rs.getString("ID_SEMANA"));
        }
             if (rs.getString("INICIO_SEMANA") != null) {
            dat.setINICIO_SEMANA(rs.getString("INICIO_SEMANA").trim());
        } else {
            dat.setINICIO_SEMANA(rs.getString("INICIO_SEMANA"));
        }
        
        if (rs.getString("FIN_SEMANA") != null) {
            dat.setFIN_SEMANA(rs.getString("FIN_SEMANA").trim());
        } else {
            dat.setFIN_SEMANA(rs.getString("FIN_SEMANA"));
        }
        
        
          
        if  (rs.getString("ESTATUS_REG") != null) {
            dat.setESTATUS_REG(rs.getString("ESTATUS_REG").trim());
        } else {
            dat.setESTATUS_REG(rs.getString("ESTATUS_REG"));
        }    
          
          
          
        return dat;
    }
    
    
}
