package mappers;

import beans.ColoniasBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColoniaMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        ColoniasBean dat = new ColoniasBean();
        
        if (rs.getString("asenta") != null) {
            dat.setCOLONIA_AUX(rs.getString("asenta").trim());
        } else {
            dat.setCOLONIA_AUX(rs.getString("asenta"));
        }
        if (rs.getString("desc_mpio") != null) {
            dat.setMUNICIPIO(rs.getString("desc_mpio").trim());
        } else {
            dat.setMUNICIPIO(rs.getString("desc_mpio"));
        }
        if (rs.getString("idn_mpio") != null) {
            dat.setID_MUNICIPIO(rs.getString("idn_mpio").trim());
        } else {
            dat.setID_MUNICIPIO(rs.getString("idn_mpio"));
        }
        
         
        return dat;
    }

}
