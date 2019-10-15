package mappers;


import beans.UnidadesEconomicasBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SucursalesMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        UnidadesEconomicasBean dat = new UnidadesEconomicasBean();
        
       
        if (rs.getString("id_suc") != null) {
            dat.setID_SUC_CON(rs.getString("id_suc").trim());
        } else {
            dat.setID_SUC_CON(rs.getString("id_suc"));
        }
        if (rs.getString("nombre") != null) {
            dat.setNOM_SUC_CON(rs.getString("nombre").trim());
        } else {
            dat.setNOM_SUC_CON(rs.getString("nombre"));
        }
         if (rs.getString("domicilio") != null) {
            dat.setDOM_SUC_CON(rs.getString("domicilio").trim());
        } else {
            dat.setDOM_SUC_CON(rs.getString("domicilio"));
        }
         if (rs.getString("cp") != null) {
            dat.setCP_SUC_CON(rs.getString("cp").trim());
        } else {
            dat.setCP_SUC_CON(rs.getString("cp"));
        }
         if (rs.getString("colonia") != null) {
            dat.setCOLONIA_SUC_CON(rs.getString("colonia").trim());
        } else {
            dat.setCOLONIA_SUC_CON(rs.getString("colonia"));
        }
         if (rs.getString("municipio") != null) {
            dat.setMUNICIPIO_SUC_CON(rs.getString("municipio").trim());
        } else {
            dat.setMUNICIPIO_SUC_CON(rs.getString("municipio"));
        }
        if (rs.getString("cct_plantel") != null) {
            dat.setCCT_SUC_CON(rs.getString("cct_plantel").trim());
        } else {
            dat.setCCT_SUC_CON(rs.getString("cct_plantel"));
        } 
        if (rs.getString("tipo") != null) {
            dat.setTIPO_CON(rs.getString("tipo").trim());
        } else {
            dat.setTIPO_CON(rs.getString("tipo"));
        } 
        
        
         
        return dat;
    }

}
