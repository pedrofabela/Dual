/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.cctPlanBean;
import beans.competenciaBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class competenciaMapper implements Mapper{
    
    public Object mapRow(ResultSet rs) throws SQLException {
         competenciaBean dat = new competenciaBean();
        

        
         if (rs.getString("ID_MATERIA") != null) {
            dat.setID_MATERIA(rs.getString("ID_MATERIA").trim());
        } else {
            dat.setID_MATERIA(rs.getString("ID_MATERIA"));
        }
         if (rs.getString("ID_COMPETENCIA") != null) {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA").trim());
        } else {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA"));
        }
         if (rs.getString("COMPETENCIA") != null) {
            dat.setCOMPETENCIA(rs.getString("COMPETENCIA").trim());
        } else {
            dat.setCOMPETENCIA(rs.getString("COMPETENCIA"));
        }
          if (rs.getString("FECH_REC_COMPETENCIA") != null) {
            dat.setFECH_REC_COMPETENCIA(rs.getString("FECH_REC_COMPETENCIA").trim());
        } else {
            dat.setFECH_REC_COMPETENCIA(rs.getString("FECH_REC_COMPETENCIA"));
        }
            
        
        return dat;
    }
}
