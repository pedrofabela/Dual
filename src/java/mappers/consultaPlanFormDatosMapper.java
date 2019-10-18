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
public class consultaPlanFormDatosMapper implements Mapper {
    
    
      public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
        
          
        if  (rs.getString("ID_PLAN_FORMA") != null) {
            dat.setID_PLAN_FORMA(rs.getString("ID_PLAN_FORMA").trim());
        } else {
            dat.setID_PLAN_FORMA(rs.getString("ID_PLAN_FORMA"));
        }    
          
          
          
           if  (rs.getString("NOMBREPLAN_FORM") != null) {
            dat.setNOMBREPLAN_FORM(rs.getString("NOMBREPLAN_FORM").trim());
        } else {
            dat.setNOMBREPLAN_FORM(rs.getString("NOMBREPLAN_FORM"));
        }    
           
           
             if  (rs.getString("DESCRIPCION") != null) {
            dat.setDESCRIPCION(rs.getString("DESCRIPCION").trim());
        } else {
            dat.setDESCRIPCION(rs.getString("DESCRIPCION"));
            
            
        }        
           
              if  (rs.getString("DURACION") != null) {
            dat.setDURACION(rs.getString("DURACION").trim());
        } else {
            dat.setDURACION(rs.getString("DURACION"));
            
            
        }           
           
             if  (rs.getString("NO_ESTUDIANTES") != null) {
            dat.setNO_ESTUDIANTES(rs.getString("NO_ESTUDIANTES").trim());
        } else {
            dat.setNO_ESTUDIANTES(rs.getString("NO_ESTUDIANTES"));
        }    
             
               if  (rs.getString("NO_MENTORES_UE") != null) {
            dat.setNO_MENTORES_UE(rs.getString("NO_MENTORES_UE").trim());
        } else {
            dat.setNO_MENTORES_UE(rs.getString("NO_MENTORES_UE"));
        }        
             
                 
            if  (rs.getString("NO_MENTORES_ACAD") != null) {
            dat.setNO_MENTORES_ACAD(rs.getString("NO_MENTORES_ACAD").trim());
        } else {
            dat.setNO_MENTORES_ACAD(rs.getString("NO_MENTORES_ACAD"));
        }    
               
               
            
                    
                   
          
        return dat;
    }
    
    
    
    
    
    
    
    
    
}
