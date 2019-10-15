/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.usuarioBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class usuarioConsultaMapper implements Mapper{
    public Object mapRow(ResultSet rs) throws SQLException {
		usuarioBean usr = new usuarioBean();
                
                
               if (rs.getString("NOMBRE_COMPLETO") != null) {
            usr.setNOMBRE_COMPLETO(rs.getString("NOMBRE_COMPLETO").trim());
        } else {
            usr.setNOMBRE_COMPLETO(rs.getString("NOMBRE_COMPLETO"));
        }   
        
         if (rs.getString("NAMEPERFIL") != null) {
            usr.setNAMEPERFIL(rs.getString("NAMEPERFIL").trim());
        } else {
            usr.setNAMEPERFIL(rs.getString("NAMEPERFIL"));
        }     
         
           if (rs.getString("USUARIO_LOGIN") != null) {
            usr.setUSUARIO_LOGIN(rs.getString("USUARIO_LOGIN").trim());
        } else {
            usr.setUSUARIO_LOGIN(rs.getString("USUARIO_LOGIN"));
        }   
             if (rs.getString("PASSWORD") != null) {
            usr.setPASSWORD(rs.getString("PASSWORD").trim());
        } else {
            usr.setPASSWORD(rs.getString("PASSWORD"));
        }  
           if (rs.getString("TELCASA_PERSONA") != null) {
            usr.setTELCASA_PERSONA(rs.getString("TELCASA_PERSONA").trim());
        } else {
            usr.setTELCASA_PERSONA(rs.getString("TELCASA_PERSONA"));
        }           
           if (rs.getString("TELCEL_PERSONA") != null) {
            usr.setTELCEL_PERSONA(rs.getString("TELCEL_PERSONA").trim());
        } else {
            usr.setTELCEL_PERSONA(rs.getString("TELCEL_PERSONA"));
        }             

            if (rs.getString("CORREO_PERSONA") != null) {
            usr.setCORREO_PERSONA(rs.getString("CORREO_PERSONA").trim());
        } else {
            usr.setCORREO_PERSONA(rs.getString("CORREO_PERSONA"));
        }        
           
            usr.setPERFIL(rs.getInt("PERFIL"));
           
	  if (rs.getString("ID_PERSONA") != null) {
            usr.setID_PERSONA(rs.getString("ID_PERSONA").trim());
        } else {
            usr.setID_PERSONA(rs.getString("ID_PERSONA"));
        }        	
             
           if (rs.getString("ID_ESCUELA") != null) {
            usr.setID_ESCUELA(rs.getString("ID_ESCUELA").trim());
        } else {
            usr.setID_ESCUELA(rs.getString("ID_ESCUELA"));
        }     
           
            if (rs.getString("ID_IE_UE") != null) {
            usr.setID_IE_UE(rs.getString("ID_IE_UE").trim());
        } else {
            usr.setID_IE_UE(rs.getString("ID_IE_UE"));
        }      
             
             
          
		return usr;
	}
}
