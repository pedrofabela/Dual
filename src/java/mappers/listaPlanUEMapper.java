/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.PlanFormacionBean;
import beans.programaEsBean;
import beans.responsablesBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class listaPlanUEMapper implements Mapper{
    
    public Object mapRow(ResultSet rs) throws SQLException {
        
       
        programaEsBean dat = new programaEsBean();
        
        
         if (rs.getString("ID_PLAN_FORMA") != null) {
            dat.setID_PLAN_FORMA(rs.getString("ID_PLAN_FORMA").trim());
        } else {
            dat.setID_PLAN_FORMA(rs.getString("ID_PLAN_FORMA"));
        }
            if (rs.getString("NOMBREPLAN_FORM") != null) {
            dat.setNOMBREPLAN_FORM(rs.getString("NOMBREPLAN_FORM").trim());
        } else {
            dat.setNOMBREPLAN_FORM(rs.getString("NOMBREPLAN_FORM"));
        } 
      
         if (rs.getString("DURACION") != null) {
            dat.setDURACION(rs.getString("DURACION").trim());
        } else {
            dat.setDURACION(rs.getString("DURACION"));
        } 
         
        return dat;
    }
    
    
}
