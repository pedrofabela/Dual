/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.resProgEstBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class resProgEduConMapper implements Mapper{
    
    
      public Object mapRow(ResultSet rs) throws SQLException {
         resProgEstBean dat = new resProgEstBean();
        
          if (rs.getString("ID_RESPROG_INST") != null) {
            dat.setID_RESPROG_INST(rs.getString("ID_RESPROG_INST").trim());
        } else {
            dat.setID_RESPROG_INST(rs.getString("ID_RESPROG_INST"));
        }
         
         
          if (rs.getString("ID_PERSONA") != null) {
            dat.setID_PERSONA(rs.getString("ID_PERSONA").trim());
        } else {
            dat.setID_PERSONA(rs.getString("ID_PERSONA"));
        }
         
             if (rs.getString("ID_ESCUELA") != null) {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA").trim());
        } else {
            dat.setID_ESCUELA(rs.getString("ID_ESCUELA"));
        }
             
                if (rs.getString("PERFIL") != null) {
            dat.setPERFIL(rs.getString("PERFIL").trim());
        } else {
            dat.setPERFIL(rs.getString("PERFIL"));
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
        
         if (rs.getString("USUARIO_LOGIN") != null) {
            dat.setUSUARIO_LOGIN(rs.getString("USUARIO_LOGIN").trim());
        } else {
            dat.setUSUARIO_LOGIN(rs.getString("USUARIO_LOGIN"));
        }
        
          if (rs.getString("PASSWORD") != null) {
            dat.setPASSWORD(rs.getString("PASSWORD").trim());
        } else {
            dat.setPASSWORD(rs.getString("PASSWORD"));
        }
         
          
        if (rs.getString("ESTATUS") != null) {
            dat.setESTATUS(rs.getString("ESTATUS").trim());
        } else {
            dat.setESTATUS(rs.getString("ESTATUS"));
        }

         if (rs.getString("ID_USUARIO") != null) {
            dat.setID_USUARIO(rs.getString("ID_USUARIO").trim());
        } else {
            dat.setID_USUARIO(rs.getString("ID_USUARIO"));
        }
           if (rs.getString("PERFIL") != null) {
            dat.setPERFIL(rs.getString("PERFIL").trim());
        } else {
            dat.setPERFIL(rs.getString("PERFIL"));
        }
        if (rs.getString("ID_PERSONA") != null) {
            dat.setID_PERSONA(rs.getString("ID_PERSONA").trim());
        } else {
            dat.setID_PERSONA(rs.getString("ID_PERSONA"));
        }
        
        
        return dat;
    }
    
}
