/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;


import beans.UnidadesEconomicasBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class UEMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         UnidadesEconomicasBean dat = new UnidadesEconomicasBean();
        

        
         if (rs.getString("id_ue") != null) {
            dat.setID_UE(rs.getString("id_ue").trim());
        } else {
            dat.setID_UE(rs.getString("id_ue"));
        }
         if (rs.getString("rfc") != null) {
            dat.setRFC(rs.getString("rfc").trim());
        } else {
            dat.setRFC(rs.getString("rfc"));
        }
         if (rs.getString("razon_social") != null) {
            dat.setRAZON_SOCIAL(rs.getString("razon_social").trim());
        } else {
            dat.setRAZON_SOCIAL(rs.getString("razon_social"));
        }
        if (rs.getString("nom_comercial") != null) {
            dat.setNOMBRE_COMER(rs.getString("nom_comercial").trim());
        } else {
            dat.setNOMBRE_COMER(rs.getString("nom_comercial"));
        } 
         if (rs.getString("giro") != null) {
            dat.setGIRO(rs.getString("giro").trim());
        } else {
            dat.setGIRO(rs.getString("giro"));
        } 
        
        if (rs.getString("sector") != null) {
            dat.setSECTOR(rs.getString("sector").trim());
        } else {
            dat.setSECTOR(rs.getString("sector"));
        }  
         if (rs.getString("domicilio") != null) {
            dat.setDOMICILIO(rs.getString("domicilio").trim());
        } else {
            dat.setDOMICILIO(rs.getString("domicilio"));
        }  
         if (rs.getString("colonia") != null) {
            dat.setCOLONIA(rs.getString("colonia").trim());
        } else {
            dat.setCOLONIA(rs.getString("colonia"));
        } 
        if (rs.getString("localidad") != null) {
            dat.setLOCALIDAD(rs.getString("localidad").trim());
        } else {
            dat.setLOCALIDAD(rs.getString("localidad"));
        }  
        if (rs.getString("cp") != null) {
            dat.setCP(rs.getString("cp").trim());
        } else {
            dat.setCP(rs.getString("cp"));
        }  
        if (rs.getString("municipio") != null) {
            dat.setMUNICIPIO(rs.getString("municipio").trim());
        } else {
            dat.setMUNICIPIO(rs.getString("municipio"));
        }  
        
        
        return dat;
    }
}
