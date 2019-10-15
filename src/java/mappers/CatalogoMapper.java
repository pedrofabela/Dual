package mappers;

import beans.CatalogoBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogoMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        CatalogoBean dat = new CatalogoBean();
        
       
        if (rs.getString("ID_CATALOGO") != null) {
            dat.setID_CATALOGO(rs.getString("ID_CATALOGO").trim());
        } else {
            dat.setID_CATALOGO(rs.getString("ID_CATALOGO"));
        }
        if (rs.getString("DESCRIPCION") != null) {
            dat.setDESCRIPCION(rs.getString("DESCRIPCION").trim());
        } else {
            dat.setDESCRIPCION(rs.getString("DESCRIPCION"));
        }
        
         
        return dat;
    }

}
