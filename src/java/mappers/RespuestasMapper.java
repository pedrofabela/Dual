package mappers;

import beans.RespuestaBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RespuestasMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        RespuestaBean dat = new RespuestaBean();
        
       
        if (rs.getString("id_respuesta") != null) {
            dat.setID_RESPUESTA(rs.getString("id_respuesta").trim());
        } else {
            dat.setID_RESPUESTA(rs.getString("id_respuesta"));
        }
        if (rs.getString("respuesta") != null) {
            dat.setRESPUESTA(rs.getString("respuesta").trim());
        } else {
            dat.setRESPUESTA(rs.getString("respuesta"));
        }
        
         
        return dat;
    }

}
