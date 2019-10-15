/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;


import beans.AlumnoBean;
import beans.programaEsBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class InteresadosMapper implements Mapper{
    
   public Object mapRow(ResultSet rs) throws SQLException {
        AlumnoBean dat = new AlumnoBean();
        
        if (rs.getString("id_hist_alum") != null) {
            dat.setID_HISTORICO(rs.getString("id_hist_alum").trim());
        } else {
            dat.setID_HISTORICO(rs.getString("id_hist_alum"));
        }
        
         if (rs.getString("id_alumno") != null) {
            dat.setID_ALUMNO(rs.getString("id_alumno").trim());
        } else {
            dat.setID_ALUMNO(rs.getString("id_alumno"));
        }
        
          if (rs.getString("curp") != null) {
            dat.setCURP(rs.getString("curp").trim());
        } else {
            dat.setCURP(rs.getString("curp"));
        }
         if (rs.getString("nombre_completo") != null) {
            dat.setNOMBRE_COMPLETO(rs.getString("nombre_completo").trim());
        } else {
            dat.setNOMBRE_COMPLETO(rs.getString("nombre_completo"));
        }  
        if (rs.getString("fec_nac") != null) {
            dat.setFEC_NAC(rs.getString("fec_nac").trim());
        } else {
            dat.setFEC_NAC(rs.getString("fec_nac"));
        }   
        if (rs.getString("entidad_nac") != null) {
            dat.setENTIDAD_NAC(rs.getString("entidad_nac").trim());
        } else {
            dat.setENTIDAD_NAC(rs.getString("entidad_nac"));
        }   
         if (rs.getString("sexo") != null) {
            dat.setSEXO(rs.getString("sexo").trim());
        } else {
            dat.setSEXO(rs.getString("sexo"));
        }   
         if (rs.getString("matricula") != null) {
            dat.setMATRICULA(rs.getString("matricula").trim());
        } else {
            dat.setMATRICULA(rs.getString("matricula"));
        }  
         
          if (rs.getString("cve_pro_edu") != null) {
            dat.setNOM_PRO_EDU(rs.getString("cve_pro_edu").trim());
        } else {
            dat.setNOM_PRO_EDU(rs.getString("cve_pro_edu"));
        }  
          
         if (rs.getString("grado") != null) {
            dat.setGRADO(rs.getString("grado").trim());
        } else {
            dat.setGRADO(rs.getString("grado"));
        }  
         
         if (rs.getString("status_proceso") != null) {
            dat.setSTATUS_PROCESO(rs.getString("status_proceso").trim());
        } else {
            dat.setSTATUS_PROCESO(rs.getString("status_proceso"));
        }  
          
            
        return dat;
    }
    
}
