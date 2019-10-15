/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.programaEsBean;
import beans.resProgEstBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class resProgEstMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         resProgEstBean dat = new resProgEstBean();
        
          if (rs.getString("ID_PERSONA") != null) {
            dat.setID_PERSONA(rs.getString("ID_PERSONA").trim());
        } else {
            dat.setID_PERSONA(rs.getString("ID_PERSONA"));
        }
         
           if (rs.getString("CURP_PERSONA") != null) {
            dat.setCURP_PERSONA(rs.getString("CURP_PERSONA").trim());
        } else {
            dat.setCURP_PERSONA(rs.getString("CURP_PERSONA"));
        }
         
           if (rs.getString("NOMBRE_PERSONA") != null) {
            dat.setNOMBRE_PERSONA(rs.getString("NOMBRE_PERSONA").trim());
        } else {
            dat.setNOMBRE_PERSONA(rs.getString("NOMBRE_PERSONA"));
        }
        
         if (rs.getString("APATERNO_PERSONA") != null) {
            dat.setAPATERNO_PERSONA(rs.getString("APATERNO_PERSONA").trim());
        } else {
            dat.setAPATERNO_PERSONA(rs.getString("APATERNO_PERSONA"));
        }
        
          if (rs.getString("AMATERNO_PERSONA") != null) {
            dat.setAMATERNO_PERSONA(rs.getString("AMATERNO_PERSONA").trim());
        } else {
            dat.setAMATERNO_PERSONA(rs.getString("AMATERNO_PERSONA"));
        }
         
        if (rs.getString("FECNAC_PERSONA") != null) {
            dat.setFECNAC_PERSONA(rs.getString("FECNAC_PERSONA").trim());
        } else {
            dat.setFECNAC_PERSONA(rs.getString("FECNAC_PERSONA"));
        }

         if (rs.getString("NAC_PERSONA") != null) {
            dat.setNAC_PERSONA(rs.getString("NAC_PERSONA").trim());
        } else {
            dat.setNAC_PERSONA(rs.getString("NAC_PERSONA"));
        }

         if (rs.getString("ENTIDAD_NACIMIENTO") != null) {
            dat.setENTIDAD_NACIMIENTO(rs.getString("ENTIDAD_NACIMIENTO").trim());
        } else {
            dat.setENTIDAD_NACIMIENTO(rs.getString("ENTIDAD_NACIMIENTO"));
        }

          if (rs.getString("GENERO") != null) {
            dat.setGENERO(rs.getString("GENERO").trim());
        } else {
            dat.setGENERO(rs.getString("GENERO"));
        }

           if (rs.getString("TELCASA_PERSONA") != null) {
            dat.setTELCASA_PERSONA(rs.getString("TELCASA_PERSONA").trim());
        } else {
            dat.setTELCASA_PERSONA(rs.getString("TELCASA_PERSONA"));
        }

           if (rs.getString("TELCEL_PERSONA") != null) {
            dat.setTELCEL_PERSONA(rs.getString("TELCEL_PERSONA").trim());
        } else {
            dat.setTELCEL_PERSONA(rs.getString("TELCEL_PERSONA"));
        }
       
            if (rs.getString("CORREO_PERSONA") != null) {
            dat.setCORREO_PERSONA(rs.getString("CORREO_PERSONA").trim());
        } else {
            dat.setCORREO_PERSONA(rs.getString("CORREO_PERSONA"));
        }
            if (rs.getString("RFC") != null) {
            dat.setRFC(rs.getString("RFC").trim());
        } else {
            dat.setRFC(rs.getString("RFC"));
        }
        
            if (rs.getString("ID_ESCUELA") != null) {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA").trim());
        } else {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA"));
        }




            
        
        
        return dat;
    }
    
    
    
}
