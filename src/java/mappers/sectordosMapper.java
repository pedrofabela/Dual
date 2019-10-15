package mappers;

import beans.SectorSubsectorBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilidades.Constantes;

public class sectordosMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
         Constantes.enviaMensajeConsola("entro mapper subsector");
       
        SectorSubsectorBean dat = new SectorSubsectorBean();
        
       
        if (rs.getString("ID_SUBSECTOR") != null) {
            dat.setID_SUBSECTOR(rs.getString("ID_SUBSECTOR").trim());
            Constantes.enviaMensajeConsola("idsubsector: "+dat.getID_SUBSECTOR());
        } else {
            dat.setID_SUBSECTOR(rs.getString("ID_SUBSECTOR"));
        }
        if (rs.getString("NOM_SUBSECTOR") != null) {
            dat.setNOM_SUBSECTOR(rs.getString("NOM_SUBSECTOR").trim());
            Constantes.enviaMensajeConsola("nom_subsector: "+dat.getNOM_SUBSECTOR());
        } else {
            dat.setNOM_SUBSECTOR(rs.getString("NOM_SUBSECTOR"));
        }
        
         
        return dat;
    }

}
