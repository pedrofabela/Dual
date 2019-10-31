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
public class AceptadosMapper implements Mapper{
    
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
         
        if (rs.getString("no_seguro") != null) {
            dat.setNO_SEGURO(rs.getString("no_seguro").trim());
        } else {
            dat.setNO_SEGURO(rs.getString("no_seguro"));
        }   
        
        if (rs.getString("domicilio") != null) {
            dat.setDOMICILIO(rs.getString("domicilio").trim());
        } else {
            dat.setDOMICILIO(rs.getString("domicilio"));
        }   
         
        
        if (rs.getString("colonia") != null) {
            dat.setCOLONIA(rs.getString("colonia").trim());
        } else {
            dat.setCOLONIA(rs.getString("colonia"));
        } 
         if (rs.getString("localidad") != null) {
            dat.setLOCALIDAD(rs.getString("localidad").trim());
        } else {
            dat.setLOCALIDAD(rs.getString("localidad"));
        } 
         
        if (rs.getString("cp") != null) {
            dat.setCP(rs.getString("cp").trim());
        } else {
            dat.setCP(rs.getString("cp"));
        } 
        
        if (rs.getString("cve_mun") != null) {
            dat.setCVE_MUN(rs.getString("cve_mun").trim());
        } else {
            dat.setCVE_MUN(rs.getString("cve_mun"));
        } 
        if (rs.getString("fecha_inicio_dual") != null) {
            dat.setFECHA_INICIO_DUAL(rs.getString("fecha_inicio_dual").trim());
        } else {
            dat.setFECHA_INICIO_DUAL(rs.getString("fecha_inicio_dual"));
        } 
        if (rs.getString("tipo_alumno") != null) {
            dat.setTIPO_ALUMNO(rs.getString("tipo_alumno").trim());
        } else {
            dat.setTIPO_ALUMNO(rs.getString("tipo_alumno"));
        } 
        
        if (rs.getString("curp_padre") != null) {
            dat.setCURP_PADRE(rs.getString("curp_padre").trim());
        } else {
            dat.setCURP_PADRE(rs.getString("curp_padre"));
        } 
         if (rs.getString("nom_padre") != null) {
            dat.setNOM_PADRE(rs.getString("nom_padre").trim());
        } else {
            dat.setNOM_PADRE(rs.getString("nom_padre"));
        } 
         if (rs.getString("apellidop_padre") != null) {
            dat.setAPELLIDOP_PADRE(rs.getString("apellidop_padre").trim());
        } else {
            dat.setAPELLIDOP_PADRE(rs.getString("apellidop_padre"));
        } 
        if (rs.getString("apellidom_padre") != null) {
            dat.setAPELLIDOM_PADRE(rs.getString("apellidom_padre").trim());
        } else {
            dat.setAPELLIDOM_PADRE(rs.getString("apellidom_padre"));
        }  
        if (rs.getString("tel_padre") != null) {
            dat.setTEL_PADRE(rs.getString("tel_padre").trim());
        } else {
            dat.setTEL_PADRE(rs.getString("tel_padre"));
        }  
        if (rs.getString("email_padre") != null) {
            dat.setEMAIL_PADRE(rs.getString("email_padre").trim());
        } else {
            dat.setEMAIL_PADRE(rs.getString("email_padre"));
        } 
        if (rs.getString("domicilio_padre") != null) {
            dat.setDOMICILIO_PADRE(rs.getString("domicilio_padre").trim());
        } else {
            dat.setDOMICILIO_PADRE(rs.getString("domicilio_padre"));
        } 
        if (rs.getString("colonia_padre") != null) {
            dat.setCOLONIA_PADRE(rs.getString("colonia_padre").trim());
        } else {
            dat.setCOLONIA_PADRE(rs.getString("colonia_padre"));
        } 
        if (rs.getString("localidad_padre") != null) {
            dat.setLOCALIDAD_PADRE(rs.getString("localidad_padre").trim());
        } else {
            dat.setLOCALIDAD_PADRE(rs.getString("localidad_padre"));
        } 
        if (rs.getString("cp_padre") != null) {
            dat.setCP_PADRE(rs.getString("cp_padre").trim());
        } else {
            dat.setCP_PADRE(rs.getString("cp_padre"));
        } 
         if (rs.getString("cve_mun_padre") != null) {
            dat.setCVE_MUN_PADRE(rs.getString("cve_mun_padre").trim());
        } else {
            dat.setCVE_MUN_PADRE(rs.getString("cve_mun_padre"));
        } 
        if (rs.getString("mismo_domicilio") != null) {
            dat.setMISMO_DOMICILIO(rs.getString("mismo_domicilio").trim());
        } else {
            dat.setMISMO_DOMICILIO(rs.getString("mismo_domicilio"));
        }  
         if (rs.getString("status_proceso") != null) {
            dat.setSTATUS_PROCESO(rs.getString("status_proceso").trim());
        } else {
            dat.setSTATUS_PROCESO(rs.getString("status_proceso"));
        }  
          if (rs.getString("AUX_RES_ACAD") != null) {
            dat.setAUX_RES_ACAD(rs.getString("AUX_RES_ACAD").trim());
        } else {
            dat.setAUX_RES_ACAD(rs.getString("AUX_RES_ACAD"));
        }  
             if (rs.getString("ID_IE_UE") != null) {
            dat.setID_IE_UE(rs.getString("ID_IE_UE").trim());
        } else {
            dat.setID_IE_UE(rs.getString("ID_IE_UE"));
        }  
            if (rs.getString("ID_CCT_PLAN") != null) {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN").trim());
        } else {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN"));
        }     
             
        return dat;
    }
    
}
