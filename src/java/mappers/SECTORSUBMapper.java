package mappers;

import beans.CatalogoBean;
import beans.SectorSubsectorBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilidades.Constantes;

public class SECTORSUBMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        SectorSubsectorBean dat = new SectorSubsectorBean();
        
        //Constantes.enviaMensajeConsola("entro mapper sector");
       
        if (rs.getString("ID_SUBSECTOR") != null) {
            dat.setID_SUBSECTOR(rs.getString("ID_SUBSECTOR").trim());
        } else {
            dat.setID_SUBSECTOR(rs.getString("ID_SUBSECTOR"));
        }
        if (rs.getString("NOM_SUBSECTOR") != null) {
            dat.setNOM_SUBSECTOR(rs.getString("NOM_SUBSECTOR").trim());
        } else {
            dat.setNOM_SUBSECTOR(rs.getString("NOM_SUBSECTOR"));
        }
        
         
        return dat;
    }

}
