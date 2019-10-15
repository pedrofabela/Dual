/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.periodoBean;
import beans.tipoPeriodoBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class periodoMapper implements Mapper{
    
    public Object mapRow(ResultSet rs) throws SQLException {
         periodoBean dat = new periodoBean();
        
        
         if (rs.getString("ID_PERIODO") != null) {
            dat.setID_PERIODO(rs.getString("ID_PERIODO").trim());
        } else {
            dat.setID_PERIODO(rs.getString("ID_PERIODO"));
        }
           if (rs.getString("PERIODO") != null) {
            dat.setPERIODO(rs.getString("PERIODO").trim());
        } else {
            dat.setPERIODO(rs.getString("PERIODO"));
        }
      
        return dat;
    }
    
}
