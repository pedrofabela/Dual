package mappers;

import beans.CatalogoBean;
import beans.SectorSubsectorBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilidades.Constantes;

public class SECTORMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        SectorSubsectorBean dat = new SectorSubsectorBean();
        
       // Constantes.enviaMensajeConsola("entro mapper sector");
       
        if (rs.getString("ID_SECTOR") != null) {
            dat.setID_SECTOR(rs.getString("ID_SECTOR").trim());
        } else {
            dat.setID_SECTOR(rs.getString("ID_SECTOR"));
        }
        if (rs.getString("NOM_SECTOR") != null) {
            dat.setNOM_SECTOR(rs.getString("NOM_SECTOR").trim());
        } else {
            dat.setNOM_SECTOR(rs.getString("NOM_SECTOR"));
        }
        
         
        return dat;
    }

}
