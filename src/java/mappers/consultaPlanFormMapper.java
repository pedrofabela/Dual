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
public class consultaPlanFormMapper implements Mapper{
    
    
    
       public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
        
       
        
        
          
        if  (rs.getString("ID_PLAN_FORMA") != null) {
            dat.setID_PLAN_FORMA(rs.getString("ID_PLAN_FORMA").trim());
        } else {
            dat.setID_PLAN_FORMA(rs.getString("ID_PLAN_FORMA"));
        }    
          
          
          
           if  (rs.getString("ID_MATERIA") != null) {
            dat.setID_MATERIA(rs.getString("ID_MATERIA").trim());
        } else {
            dat.setID_MATERIA(rs.getString("ID_MATERIA"));
        }    
           
           
             if  (rs.getString("ID_COMPETENCIA") != null) {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA").trim());
        } else {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA"));
            
            
        }        
           
              if  (rs.getString("ID_ESCALA") != null) {
            dat.setID_ESCALA(rs.getString("ID_ESCALA").trim());
        } else {
            dat.setID_ESCALA(rs.getString("ID_ESCALA"));
            
            
        }           
           
             if  (rs.getString("ID_LUGAR") != null) {
            dat.setID_LUGAR(rs.getString("ID_LUGAR").trim());
        } else {
            dat.setID_LUGAR(rs.getString("ID_LUGAR"));
        }    
             
               if  (rs.getString("PLAN_ROTACION") != null) {
            dat.setPLAN_ROTACION(rs.getString("PLAN_ROTACION").trim());
        } else {
            dat.setPLAN_ROTACION(rs.getString("PLAN_ROTACION"));
        }        
             
                 
            if  (rs.getString("DES_ACTIVIDAD") != null) {
            dat.setDES_ACTIVIDAD(rs.getString("DES_ACTIVIDAD").trim());
        } else {
            dat.setDES_ACTIVIDAD(rs.getString("DES_ACTIVIDAD"));
        }    
               
               
              if  (rs.getString("FECHA_REG") != null) {
            dat.setFECHA_REG(rs.getString("FECHA_REG").trim());
        } else {
            dat.setFECHA_REG(rs.getString("FECHA_REG"));
            
            
        }    
               
               
                 if  (rs.getString("ID_ACTIVIDAD") != null) {
            dat.setID_ACTIVIDAD(rs.getString("ID_ACTIVIDAD").trim());
        } else {
            dat.setID_ACTIVIDAD(rs.getString("ID_ACTIVIDAD"));
            
            
        }   
             
             if  (rs.getString("ID_ACT_EVALUA") != null) {
            dat.setID_ACT_EVALUA(rs.getString("ID_ACT_EVALUA").trim());
        } else {
            dat.setID_ACT_EVALUA(rs.getString("ID_ACT_EVALUA"));
            
            
        }      
              
       if  (rs.getString("NOMBRE_MATERIA") != null) {
            dat.setNOMBRE_MATERIA(rs.getString("NOMBRE_MATERIA").trim());
        } else {
            dat.setNOMBRE_MATERIA(rs.getString("NOMBRE_MATERIA"));
            
            
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
               
                 
                  
          if  (rs.getString("HORA") != null) {
            dat.setHORA(rs.getString("HORA").trim());
        } else {
            dat.setHORA(rs.getString("HORA"));
            
            
        }                 
          
          if  (rs.getString("LUGAR") != null) {
            dat.setLUGAR(rs.getString("LUGAR").trim());
        } else {
            dat.setLUGAR(rs.getString("LUGAR"));
            
            
        } 
          
         if  (rs.getString("NUMERO_PERIODO") != null) {
            dat.setNUMERO_PERIODO(rs.getString("NUMERO_PERIODO").trim());
        } else {
            dat.setNUMERO_PERIODO(rs.getString("NUMERO_PERIODO"));
            
            
        }        
                    
                   
          
        return dat;
    }
    
    
    
    
    
    
    
    
    
    
    
}
