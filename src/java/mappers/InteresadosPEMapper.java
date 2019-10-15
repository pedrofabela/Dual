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
public class InteresadosPEMapper implements Mapper{
    
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
         if (rs.getString("matricula") != null) {
            dat.setMATRICULA(rs.getString("matricula").trim());
        } else {
            dat.setMATRICULA(rs.getString("matricula"));
        }  
         
          if (rs.getString("cve_pro_edu") != null) {
            dat.setCVE_PRO_EDU(rs.getString("cve_pro_edu").trim());
        } else {
            dat.setCVE_PRO_EDU(rs.getString("cve_pro_edu"));
        }  
           if (rs.getString("nom_pro_edu") != null) {
            dat.setNOM_PRO_EDU(rs.getString("nom_pro_edu").trim());
        } else {
            dat.setNOM_PRO_EDU(rs.getString("nom_pro_edu"));
        }  
          
         if (rs.getString("grado") != null) {
            dat.setGRADO(rs.getString("grado").trim());
        } else {
            dat.setGRADO(rs.getString("grado"));
        }  
         
        if (rs.getString("promedio") != null) {
            dat.setPROMEDIO(rs.getString("promedio").trim());
        } else {
            dat.setPROMEDIO(rs.getString("promedio"));
        }   
        
        if (rs.getString("situacion_academica") != null) {
            dat.setSITUACION_ACADEMICA(rs.getString("situacion_academica").trim());
        } else {
            dat.setSITUACION_ACADEMICA(rs.getString("situacion_academica"));
        }   
         
        
        if (rs.getString("tel_casa") != null) {
            dat.setTEL_CASA(rs.getString("tel_casa").trim());
        } else {
            dat.setTEL_CASA(rs.getString("tel_casa"));
        } 
         if (rs.getString("tel_cel") != null) {
            dat.setTEL_CEL(rs.getString("tel_cel").trim());
        } else {
            dat.setTEL_CEL(rs.getString("tel_cel"));
        } 
         
        if (rs.getString("email_ins") != null) {
            dat.setEMAIL_INS(rs.getString("email_ins").trim());
        } else {
            dat.setEMAIL_INS(rs.getString("email_ins"));
        } 
        
        if (rs.getString("email_per") != null) {
            dat.setEMAIL_PER(rs.getString("email_per").trim());
        } else {
            dat.setEMAIL_PER(rs.getString("email_per"));
        } 
        if (rs.getString("FECHA_INC_PADRON") != null) {
            dat.setFECHA_INC_PADRON(rs.getString("FECHA_INC_PADRON").trim());
        } else {
            dat.setFECHA_INC_PADRON(rs.getString("FECHA_INC_PADRON"));
        } 
        
        if (rs.getString("res_eva") != null) {
            dat.setRES_EVA(rs.getString("res_eva").trim());
        } else {
            dat.setRES_EVA(rs.getString("res_eva"));
        }  
        
        if (rs.getString("status_proceso") != null) {
            dat.setSTATUS_PROCESO(rs.getString("status_proceso").trim());
        } else {
            dat.setSTATUS_PROCESO(rs.getString("status_proceso"));
        } 
          
            
        return dat;
    }
    
}
