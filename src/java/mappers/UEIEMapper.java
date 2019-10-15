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
public class UEIEMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         UnidadesEconomicasBean dat = new UnidadesEconomicasBean();
        

        
         if (rs.getString("id_ie_ue") != null) {
            dat.setID_IE_UE(rs.getString("id_ie_ue").trim());
        } else {
            dat.setID_IE_UE(rs.getString("id_ie_ue"));
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
         if (rs.getString("sector") != null) {
            dat.setSECTOR(rs.getString("sector").trim());
        } else {
            dat.setSECTOR(rs.getString("sector"));
        }
        if (rs.getString("id_suc") != null) {
            dat.setID_SUC(rs.getString("id_suc").trim());
        } else {
            dat.setID_SUC(rs.getString("id_suc"));
        }   
        if (rs.getString("nombre") != null) {
            dat.setNOMBRE_SUC(rs.getString("nombre").trim());
        } else {
            dat.setNOMBRE_SUC(rs.getString("nombre"));
        }  
        
        if (rs.getString("status_ue") != null) {
            dat.setSTATUS_UE(rs.getString("status_ue").trim());
        } else {
            dat.setSTATUS_UE(rs.getString("status_ue"));
        }  
         if (rs.getString("status_evaluacion") != null) {
            dat.setSTATUS_EVALUACION(rs.getString("status_evaluacion").trim());
        } else {
            dat.setSTATUS_EVALUACION(rs.getString("status_evaluacion"));
        }  
         if (rs.getString("status_resultado_evaluacion") != null) {
            dat.setSTATUS_RESULTADO_EVALUACION(rs.getString("status_resultado_evaluacion").trim());
        } else {
            dat.setSTATUS_RESULTADO_EVALUACION(rs.getString("status_resultado_evaluacion"));
        } 
        if (rs.getString("porcentaje_evaluacion") != null) {
            dat.setPORCENTAJE_EVALUACION(rs.getString("porcentaje_evaluacion").trim());
        } else {
            dat.setPORCENTAJE_EVALUACION(rs.getString("porcentaje_evaluacion"));
        }  
        if (rs.getString("status_general") != null) {
            dat.setSTATUS_GENERAL(rs.getString("status_general").trim());
        } else {
            dat.setSTATUS_GENERAL(rs.getString("status_general"));
        }  
        
        
        
        return dat;
    }
}
