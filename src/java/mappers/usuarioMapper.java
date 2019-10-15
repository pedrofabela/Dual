package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import beans.usuarioBean;

 

public class usuarioMapper implements Mapper {
	
	public Object mapRow(ResultSet rs) throws SQLException {
		usuarioBean usr = new usuarioBean();

		usr.setNAMEUSUARIO(rs.getString("NAMEUSUARIO"));
		usr.setPASSWORD(rs.getString("PASSWORD"));
		usr.setPERFIL(rs.getInt("PERFIL"));
		usr.setNAMEPERFIL(rs.getString("NAMEPERFIL"));
		usr.setUSUARIO(rs.getString("USUARIO"));
		usr.setFILTRO(rs.getString("FILTRO"));
                usr.setUSUARIO_LOGIN(rs.getString("USUARIO_LOGIN"));
                 usr.setESTATUS(rs.getString("ESTATUS"));
                  usr.setID_PERSONA(rs.getString("ID_PERSONA"));
                   usr.setID_USUARIO(rs.getString("ID_USUARIO"));
                    usr.setID_IE_UE(rs.getString("ID_IE_UE"));
                     usr.setID_ESCUELA(rs.getString("ID_ESCUELA"));
		return usr;
	}
	

}


