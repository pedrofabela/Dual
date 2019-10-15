package mappers;


import beans.PreguntasBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuestionarioMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        PreguntasBean dat = new PreguntasBean();
        
       
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
         if (rs.getString("id_pregunta") != null) {
            dat.setID_PREGUNTA(rs.getString("id_pregunta").trim());
        } else {
            dat.setID_PREGUNTA(rs.getString("id_pregunta"));
        }
        if (rs.getString("pregunta") != null) {
            dat.setPREGUNTA(rs.getString("pregunta").trim());
        } else {
            dat.setPREGUNTA(rs.getString("pregunta"));
        }
        if (rs.getString("tipo_pregunta") != null) {
            dat.setTIPO_PREGUNTA(rs.getString("tipo_pregunta").trim());
        } else {
            dat.setTIPO_PREGUNTA(rs.getString("tipo_pregunta"));
        }
         if (rs.getString("orden") != null) {
            dat.setORDEN(rs.getString("orden").trim());
        } else {
            dat.setORDEN(rs.getString("orden"));
        }
        
         
        return dat;
    }

}
