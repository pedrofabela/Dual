/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.actividadesBean;
import beans.cctPlanBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class actividadMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
         actividadesBean dat = new actividadesBean();
        


         if (rs.getString("ID_COMPETENCIA") != null) {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA").trim());
        } else {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA"));
        }
          if (rs.getString("ID_ACTIVIDAD") != null) {
            dat.setID_ACTIVIDAD(rs.getString("ID_ACTIVIDAD").trim());
        } else {
            dat.setID_ACTIVIDAD(rs.getString("ID_ACTIVIDAD"));
        }
          if (rs.getString("ACTIVIDAD") != null) {
            dat.setACTIVIDAD(rs.getString("ACTIVIDAD").trim());
        } else {
            dat.setACTIVIDAD(rs.getString("ACTIVIDAD"));
        }   
          if (rs.getString("FECHA_REG_ACTIVIDAD") != null) {
            dat.setFECHA_REG_ACTIVIDAD(rs.getString("FECHA_REG_ACTIVIDAD").trim());
        } else {
            dat.setFECHA_REG_ACTIVIDAD(rs.getString("FECHA_REG_ACTIVIDAD"));
        }   
        
        return dat;
    }
}
