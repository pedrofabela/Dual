package mappers;

import beans.Res_ConBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaRespuestasMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        Res_ConBean dat = new Res_ConBean();
        
       
        if (rs.getString("id_evaluacion") != null) {
            dat.setID_EVALUACION(rs.getString("id_evaluacion").trim());
        } else {
            dat.setID_EVALUACION(rs.getString("id_evaluacion"));
        }
        if (rs.getString("id_pregunta") != null) {
            dat.setID_PREGUNTA(rs.getString("id_pregunta").trim());
        } else {
            dat.setID_PREGUNTA(rs.getString("id_pregunta"));
        }
        if (rs.getString("id_respuesta") != null) {
            dat.setID_RESPUESTA(rs.getString("id_respuesta").trim());
        } else {
            dat.setID_RESPUESTA(rs.getString("id_respuesta"));
        }
        
         
        return dat;
    }

}
