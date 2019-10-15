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
public class descargaProgramasMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
        
       
        
        
           if (rs.getString("NOM_CARRERA") != null) {
            dat.setNOM_CARRERA(rs.getString("NOM_CARRERA").trim());
        } else {
            dat.setNOM_CARRERA(rs.getString("NOM_CARRERA"));
        }
             if (rs.getString("CVE_PLAN_EST") != null) {
            dat.setCVE_PLAN_EST(rs.getString("CVE_PLAN_EST").trim());
        } else {
            dat.setCVE_PLAN_EST(rs.getString("CVE_PLAN_EST"));
        }
        
        if (rs.getString("ENFASIS") != null) {
            dat.setENFASIS(rs.getString("ENFASIS").trim());
        } else {
            dat.setENFASIS(rs.getString("ENFASIS"));
        }
        
          if  (rs.getString("VERSION") != null) {
            dat.setVERSION(rs.getString("VERSION").trim());
        } else {
            dat.setVERSION(rs.getString("VERSION"));
        }    
          
        if  (rs.getString("NOMBRE_MATERIA") != null) {
            dat.setNOMBRE_MATERIA(rs.getString("NOMBRE_MATERIA").trim());
        } else {
            dat.setNOMBRE_MATERIA(rs.getString("NOMBRE_MATERIA"));
        }    
          
          
          
           if  (rs.getString("NUMERO_PERIODO") != null) {
            dat.setNUMERO_PERIODO(rs.getString("NUMERO_PERIODO").trim());
        } else {
            dat.setNUMERO_PERIODO(rs.getString("NUMERO_PERIODO"));
        }    
           
             if  (rs.getString("COMPETENCIA") != null) {
            dat.setCOMPETENCIA(rs.getString("COMPETENCIA").trim());
        } else {
            dat.setCOMPETENCIA(rs.getString("COMPETENCIA"));
        }    
            
            if  (rs.getString("ACTIVIDAD") != null) {
            dat.setACTIVIDAD(rs.getString("ACTIVIDAD").trim());
        } else {
            dat.setACTIVIDAD(rs.getString("ACTIVIDAD"));
        }    
       
          
        return dat;
    }
    
}
