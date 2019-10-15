/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.escuelaBean;
import beans.programaEsBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class escuelaMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         escuelaBean dat = new escuelaBean();
        

        
         if (rs.getString("ID_ESCUELA") != null) {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA").trim());
        } else {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA"));
        }
         if (rs.getString("CCT") != null) {
            dat.setCCT(rs.getString("CCT").trim());
        } else {
            dat.setCCT(rs.getString("CCT"));
        }
         if (rs.getString("NOMBRE_ESCUELA") != null) {
            dat.setNOMBRE_ESCUELA(rs.getString("NOMBRE_ESCUELA").trim());
        } else {
            dat.setNOMBRE_ESCUELA(rs.getString("NOMBRE_ESCUELA"));
        }
          if (rs.getString("DIRECCION") != null) {
            dat.setDIRECCION(rs.getString("DIRECCION").trim());
        } else {
            dat.setDIRECCION(rs.getString("DIRECCION"));
        }
            if (rs.getString("MUNICIPIO") != null) {
            dat.setMUNICIPIO(rs.getString("MUNICIPIO").trim());
        } else {
            dat.setMUNICIPIO(rs.getString("MUNICIPIO"));
        }
             if (rs.getString("ID_NIVEL") != null) {
            dat.setID_NIVEL(rs.getString("ID_NIVEL").trim());
        } else {
            dat.setID_NIVEL(rs.getString("ID_NIVEL"));
        }
              if (rs.getString("ID_SUBSISTEMA") != null) {
            dat.setID_SUBSISTEMA(rs.getString("ID_SUBSISTEMA").trim());
        } else {
            dat.setID_SUBSISTEMA(rs.getString("ID_SUBSISTEMA"));
        }
        
        return dat;
    }
}
