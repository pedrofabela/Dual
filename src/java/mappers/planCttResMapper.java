/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.programaEsBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class planCttResMapper implements Mapper {
    
      public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
        
         if (rs.getString("ID_PLAN") != null) {
            dat.setID_PLAN(rs.getString("ID_PLAN").trim());
        } else {
            dat.setID_PLAN(rs.getString("ID_PLAN"));
        }
        
          if (rs.getString("CVE_PLAN") != null) {
            dat.setCVE_PLAN(rs.getString("CVE_PLAN").trim());
        } else {
            dat.setCVE_PLAN(rs.getString("CVE_PLAN"));
        }
           if (rs.getString("NOM_CARRERA") != null) {
            dat.setNOM_CARRERA(rs.getString("NOM_CARRERA").trim());
        } else {
            dat.setNOM_CARRERA(rs.getString("NOM_CARRERA"));
        }
        if (rs.getString("FECHA_AUT_DUAL") != null) {
            dat.setFECHA_AUT_DUAL(rs.getString("FECHA_AUT_DUAL").trim());
        } else {
            dat.setFECHA_AUT_DUAL(rs.getString("FECHA_AUT_DUAL"));
        }
        if (rs.getString("ENFASIS") != null) {
            dat.setENFASIS(rs.getString("ENFASIS").trim());
        } else {
            dat.setENFASIS(rs.getString("ENFASIS"));
        }
         if (rs.getString("STATUS") != null) {
            dat.setSTATUS(rs.getString("STATUS").trim());
        } else {
            dat.setSTATUS(rs.getString("STATUS"));
        }
         
         if (rs.getString("ID_ESCUELA") != null) {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA").trim());
        } else {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA"));
        }
         
          if  (rs.getString("ID_CCT_PLAN") != null) {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN").trim());
        } else {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN"));
        }
          
                
         if  (rs.getString("CVE_PLAN_EST") != null) {
            dat.setCVE_PLAN_EST(rs.getString("CVE_PLAN_EST").trim());
        } else {
            dat.setCVE_PLAN_EST(rs.getString("CVE_PLAN_EST"));
        }
           if  (rs.getString("TIPO_PERIODO") != null) {
            dat.setTIPO_PERIODO(rs.getString("TIPO_PERIODO").trim());
        } else {
            dat.setTIPO_PERIODO(rs.getString("TIPO_PERIODO"));
        }
           if  (rs.getString("NUMERO_PERIODO") != null) {
            dat.setNUMERO_PERIODO(rs.getString("NUMERO_PERIODO").trim());
        } else {
            dat.setNUMERO_PERIODO(rs.getString("NUMERO_PERIODO"));
        }    
           
           
           if  (rs.getString("VERSION") != null) {
            dat.setVERSION(rs.getString("VERSION").trim());
        } else {
            dat.setVERSION(rs.getString("VERSION"));
        }       
           
          if  (rs.getString("PROGVINCULADO") != null) {
            dat.setPROGVINCULADO(rs.getString("PROGVINCULADO").trim());
        } else {
            dat.setPROGVINCULADO(rs.getString("PROGVINCULADO"));
        }         
           
          if  (rs.getString("ID_RES_PROGEDU") != null) {
            dat.setID_RES_PROGEDU(rs.getString("ID_RES_PROGEDU").trim());
        } else {
            dat.setID_RES_PROGEDU(rs.getString("ID_RES_PROGEDU"));
        }   
           if  (rs.getString("PERFIL") != null) {
            dat.setPERFIL(rs.getString("PERFIL").trim());
        } else {
            dat.setPERFIL(rs.getString("PERFIL"));
        }    
           
            if  (rs.getString("ID_PERSONA") != null) {
            dat.setID_PERSONA(rs.getString("ID_PERSONA").trim());
        } else {
            dat.setID_PERSONA(rs.getString("ID_PERSONA"));
        }    
          
        return dat;
    }
    
}
