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
public class consultaAluRegMapper implements Mapper{
    
    public Object mapRow(ResultSet rs) throws SQLException {
        AlumnoBean dat = new AlumnoBean();

        if (rs.getString("ID_HIST_ALUM") != null) {
            dat.setID_HIST_ALUM(rs.getString("ID_HIST_ALUM").trim());
        } else {
            dat.setID_HIST_ALUM(rs.getString("ID_HIST_ALUM"));
        }

        if (rs.getString("ID_ALUMNO") != null) {
            dat.setID_ALUMNO(rs.getString("ID_ALUMNO").trim());
        } else {
            dat.setID_ALUMNO(rs.getString("ID_ALUMNO"));
        }
        
        
        if (rs.getString("ID_ESCUELA") != null) {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA").trim());
        } else {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA"));
        }
        
        
        if (rs.getString("ID_PLAN") != null) {
            dat.setID_PLAN(rs.getString("ID_PLAN").trim());
        } else {
            dat.setID_PLAN(rs.getString("ID_PLAN"));
        }
        
        
        if (rs.getString("ID_CCT_PLAN") != null) {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN").trim());
        } else {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN"));
        }
        
        
        if (rs.getString("CURP") != null) {
            dat.setCURP(rs.getString("CURP").trim());
        } else {
            dat.setCURP(rs.getString("CURP"));
        }
        
        if (rs.getString("NOMBRE") != null) {
            dat.setNOMBRE(rs.getString("NOMBRE").trim());
        } else {
            dat.setNOMBRE(rs.getString("NOMBRE"));
        }
        if (rs.getString("ID_UE") != null) {
            dat.setID_UE(rs.getString("ID_UE").trim());
        } else {
            dat.setID_UE(rs.getString("ID_UE"));
        }
        
         

        return dat;
    }
    
}
