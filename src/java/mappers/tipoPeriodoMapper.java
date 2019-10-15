/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.programaEsBean;
import beans.tipoPeriodoBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class tipoPeriodoMapper implements Mapper{
    
    
    public Object mapRow(ResultSet rs) throws SQLException {
         tipoPeriodoBean dat = new tipoPeriodoBean();
        
        
         if (rs.getString("ID_TIPO_PERIODO") != null) {
            dat.setID_TIPO_PERIODO(rs.getString("ID_TIPO_PERIODO").trim());
        } else {
            dat.setID_TIPO_PERIODO(rs.getString("ID_TIPO_PERIODO"));
        }
           if (rs.getString("TIPO_PERIODO") != null) {
            dat.setTIPO_PERIODO(rs.getString("TIPO_PERIODO").trim());
        } else {
            dat.setTIPO_PERIODO(rs.getString("TIPO_PERIODO"));
        }
      
        return dat;
    }
    
    
}
