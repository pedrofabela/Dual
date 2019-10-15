package mappers;


import beans.EncabezadoBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EncabezadoMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        EncabezadoBean dat = new EncabezadoBean();
        
       
        if (rs.getString("id_encabezado") != null) {
            dat.setID_ENCABEZADO(rs.getString("id_encabezado").trim());
        } else {
            dat.setID_ENCABEZADO(rs.getString("id_encabezado"));
        }
        if (rs.getString("nom_encabezado") != null) {
            dat.setNOM_ENCABEZADO(rs.getString("nom_encabezado").trim());
        } else {
            dat.setNOM_ENCABEZADO(rs.getString("nom_encabezado"));
        }
        
         
        return dat;
    }

}
