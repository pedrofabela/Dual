/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.cctPlanBean;
import beans.escuelaBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class cctPlanMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
         cctPlanBean dat = new cctPlanBean();
        

        
         if (rs.getString("ID_ESCUELA") != null) {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA").trim());
        } else {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA"));
        }
         if (rs.getString("ID_PLAN") != null) {
            dat.setID_PLAN(rs.getString("ID_PLAN").trim());
        } else {
            dat.setID_PLAN(rs.getString("ID_PLAN"));
        }
         if (rs.getString("ESTATUS") != null) {
            dat.setESTATUS(rs.getString("ESTATUS").trim());
        } else {
            dat.setESTATUS(rs.getString("ESTATUS"));
        }
          if (rs.getString("ID_CCT_PLAN") != null) {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN").trim());
        } else {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN"));
        }
            
        
        return dat;
    }
}
