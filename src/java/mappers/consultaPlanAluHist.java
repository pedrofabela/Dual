/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.CatalogoBean;
import beans.programaEsBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class consultaPlanAluHist implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
       
         
        if (rs.getString("ID_HIST_ALU") != null) {
            dat.setID_HIST_ALU(rs.getString("ID_HIST_ALU").trim());
        } else {
            dat.setID_HIST_ALU(rs.getString("ID_HIST_ALU"));
        }
       
           if (rs.getString("ID_PLAN_FORM") != null) {
            dat.setID_PLAN_FORM(rs.getString("ID_PLAN_FORM").trim());
        } else {
            dat.setID_PLAN_FORM(rs.getString("ID_PLAN_FORM"));
        }
       
           if (rs.getString("FECHA_INICIOPF") != null) {
            dat.setFECHA_INICIOPF(rs.getString("FECHA_INICIOPF").trim());
        } else {
            dat.setFECHA_INICIOPF(rs.getString("FECHA_INICIOPF"));
        }   
         if (rs.getString("FECHA_TERMINOPF") != null) {
            dat.setFECHA_TERMINOPF(rs.getString("FECHA_TERMINOPF").trim());
        } else {
            dat.setFECHA_TERMINOPF(rs.getString("FECHA_TERMINOPF"));
        }  
         
          if (rs.getString("ID_MENTOR_UE") != null) {
            dat.setID_MENTOR_UE(rs.getString("ID_MENTOR_UE").trim());
        } else {
            dat.setID_MENTOR_UE(rs.getString("ID_MENTOR_UE"));
        }      
          
           if (rs.getString("ID_MENTOR_ACAD") != null) {
            dat.setID_MENTOR_ACAD(rs.getString("ID_MENTOR_ACAD").trim());
        } else {
            dat.setID_MENTOR_ACAD(rs.getString("ID_MENTOR_ACAD"));
        }      
         
              if (rs.getString("ID_RES_UE") != null) {
            dat.setID_RES_UE(rs.getString("ID_RES_UE").trim());
        } else {
            dat.setID_RES_UE(rs.getString("ID_RES_UE"));
        }      
               if (rs.getString("ID_RES_ACAD") != null) {
            dat.setID_RES_ACAD(rs.getString("ID_RES_ACAD").trim());
        } else {
            dat.setID_RES_ACAD(rs.getString("ID_RES_ACAD"));
        }      
           
           if (rs.getString("ESTATUS_PF") != null) {
            dat.setESTATUS_PF(rs.getString("ESTATUS_PF").trim());
        } else {
            dat.setESTATUS_PF(rs.getString("ESTATUS_PF"));
        }    
             if (rs.getString("ID_RES_PROGEDU") != null) {
            dat.setID_RES_PROGEDU(rs.getString("ID_RES_PROGEDU").trim());
        } else {
            dat.setID_RES_PROGEDU(rs.getString("ID_RES_PROGEDU"));
        }       
           
           
           
           
        return dat;
    }
    
    
}
