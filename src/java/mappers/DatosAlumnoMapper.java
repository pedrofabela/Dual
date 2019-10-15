/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.AlumnoBean;
import beans.programaEsBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class DatosAlumnoMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        AlumnoBean dat = new AlumnoBean();

        if (rs.getString("id_alumno") != null) {
            dat.setID_ALUMNO(rs.getString("id_alumno").trim());
        } else {
            dat.setID_ALUMNO(rs.getString("id_alumno"));
        }

        if (rs.getString("curp") != null) {
            dat.setCURP(rs.getString("curp").trim());
        } else {
            dat.setCURP(rs.getString("curp"));
        }
        if (rs.getString("nombre") != null) {
            dat.setNOMBRE(rs.getString("nombre").trim());
        } else {
            dat.setNOMBRE(rs.getString("nombre"));
        }
        if (rs.getString("apellidop") != null) {
            dat.setAPELLIDOP(rs.getString("apellidop").trim());
        } else {
            dat.setAPELLIDOP(rs.getString("apellidop"));
        }
        if (rs.getString("apellidom") != null) {
            dat.setAPELLIDOM(rs.getString("apellidom").trim());
        } else {
            dat.setAPELLIDOM(rs.getString("apellidom"));
        }
        if (rs.getString("fec_nac") != null) {
            dat.setFEC_NAC(rs.getString("fec_nac").trim());
        } else {
            dat.setFEC_NAC(rs.getString("fec_nac"));
        }
        if (rs.getString("entidad_nac") != null) {
            dat.setENTIDAD_NAC(rs.getString("entidad_nac").trim());
        } else {
            dat.setENTIDAD_NAC(rs.getString("entidad_nac"));
        }
        if (rs.getString("sexo") != null) {
            dat.setSEXO(rs.getString("sexo").trim());
        } else {
            dat.setSEXO(rs.getString("sexo"));
        }
        if (rs.getString("matricula") != null) {
            dat.setMATRICULA(rs.getString("matricula").trim());
        } else {
            dat.setMATRICULA(rs.getString("matricula"));
        }

        if (rs.getString("cve_pro_edu") != null) {
            dat.setCVE_PRO_EDU(rs.getString("cve_pro_edu").trim());
        } else {
            dat.setCVE_PRO_EDU(rs.getString("cve_pro_edu"));
        }

        if (rs.getString("grado") != null) {
            dat.setGRADO(rs.getString("grado").trim());
        } else {
            dat.setGRADO(rs.getString("grado"));
        }

        if (rs.getString("promedio") != null) {
            dat.setPROMEDIO(rs.getString("promedio").trim());
        } else {
            dat.setPROMEDIO(rs.getString("promedio"));
        }
        if (rs.getString("situacion_academica") != null) {
            dat.setSITUACION_ACADEMICA(rs.getString("situacion_academica").trim());
        } else {
            dat.setSITUACION_ACADEMICA(rs.getString("situacion_academica"));
        }
        if (rs.getString("tel_casa") != null) {
            dat.setTEL_CASA(rs.getString("tel_casa").trim());
        } else {
            dat.setTEL_CASA(rs.getString("tel_casa"));
        }
        if (rs.getString("tel_cel") != null) {
            dat.setTEL_CEL(rs.getString("tel_cel").trim());
        } else {
            dat.setTEL_CEL(rs.getString("tel_cel"));
        }
        if (rs.getString("email_ins") != null) {
            dat.setEMAIL_INS(rs.getString("email_ins").trim());
        } else {
            dat.setEMAIL_INS(rs.getString("email_ins"));
        }
        if (rs.getString("email_per") != null) {
            dat.setEMAIL_PER(rs.getString("email_per").trim());
        } else {
            dat.setEMAIL_PER(rs.getString("email_per"));
        }
        if (rs.getString("fecha_inc_padron") != null) {
            dat.setFECHA_INC_PADRON(rs.getString("fecha_inc_padron").trim());
        } else {
            dat.setFECHA_INC_PADRON(rs.getString("fecha_inc_padron"));
        }
        if (rs.getString("no_seguro") != null) {
            dat.setNO_SEGURO(rs.getString("no_seguro").trim());
        } else {
            dat.setNO_SEGURO(rs.getString("no_seguro"));
        }
        if (rs.getString("domicilio") != null) {
            dat.setDOMICILIO(rs.getString("domicilio").trim());
        } else {
            dat.setDOMICILIO(rs.getString("domicilio"));
        }
        if (rs.getString("colonia") != null) {
            dat.setCOLONIA(rs.getString("colonia").trim());
        } else {
            dat.setCOLONIA(rs.getString("colonia"));
        }
        if (rs.getString("localidad") != null) {
            dat.setLOCALIDAD(rs.getString("localidad").trim());
        } else {
            dat.setLOCALIDAD(rs.getString("localidad"));
        }
        if (rs.getString("cp") != null) {
            dat.setCP(rs.getString("cp").trim());
        } else {
            dat.setCP(rs.getString("cp"));
        }
        if (rs.getString("cve_mun") != null) {
            dat.setCVE_MUN(rs.getString("cve_mun").trim());
        } else {
            dat.setCVE_MUN(rs.getString("cve_mun"));
        }
        if (rs.getString("fecha_inicio_dual") != null) {
            dat.setFECHA_INICIO_DUAL(rs.getString("fecha_inicio_dual").trim());
        } else {
            dat.setFECHA_INICIO_DUAL(rs.getString("fecha_inicio_dual"));
        }
        if (rs.getString("tipo_alumno") != null) {
            dat.setTIPO_ALUMNO(rs.getString("tipo_alumno").trim());
        } else {
            dat.setTIPO_ALUMNO(rs.getString("tipo_alumno"));
        }
        if (rs.getString("curp_padre") != null) {
            dat.setCURP_PADRE(rs.getString("curp_padre").trim());
        } else {
            dat.setCURP_PADRE(rs.getString("curp_padre"));
        }
         if (rs.getString("nom_padre") != null) {
            dat.setNOM_PADRE(rs.getString("nom_padre").trim());
        } else {
            dat.setNOM_PADRE(rs.getString("nom_padre"));
        }
        if (rs.getString("apellidop_padre") != null) {
            dat.setAPELLIDOP_PADRE(rs.getString("apellidop_padre").trim());
        } else {
            dat.setAPELLIDOP_PADRE(rs.getString("apellidop_padre"));
        } 
        if (rs.getString("apellidom_padre") != null) {
            dat.setAPELLIDOM_PADRE(rs.getString("apellidom_padre").trim());
        } else {
            dat.setAPELLIDOM_PADRE(rs.getString("apellidom_padre"));
        } 
        if (rs.getString("tel_padre") != null) {
            dat.setTEL_PADRE(rs.getString("tel_padre").trim());
        } else {
            dat.setTEL_PADRE(rs.getString("tel_padre"));
        } 
        if (rs.getString("email_padre") != null) {
            dat.setEMAIL_PADRE(rs.getString("email_padre").trim());
        } else {
            dat.setEMAIL_PADRE(rs.getString("email_padre"));
        } 
        if (rs.getString("domicilio_padre") != null) {
            dat.setDOMICILIO_PADRE(rs.getString("domicilio_padre").trim());
        } else {
            dat.setDOMICILIO_PADRE(rs.getString("domicilio_padre"));
        } 
        if (rs.getString("colonia_padre") != null) {
            dat.setCOLONIA_PADRE(rs.getString("colonia_padre").trim());
        } else {
            dat.setCOLONIA_PADRE(rs.getString("colonia_padre"));
        } 
         if (rs.getString("localidad_padre") != null) {
            dat.setLOCALIDAD_PADRE(rs.getString("localidad_padre").trim());
        } else {
            dat.setLOCALIDAD_PADRE(rs.getString("localidad_padre"));
        } 
        if (rs.getString("cp_padre") != null) {
            dat.setCP_PADRE(rs.getString("cp_padre").trim());
        } else {
            dat.setCP_PADRE(rs.getString("cp_padre"));
        }  
        if (rs.getString("cve_mun_padre") != null) {
            dat.setCVE_MUN_PADRE(rs.getString("cve_mun_padre").trim());
        } else {
            dat.setCVE_MUN_PADRE(rs.getString("cve_mun_padre"));
        }  
        if (rs.getString("cve_mun_padre") != null) {
            dat.setCVE_MUN_PADRE(rs.getString("cve_mun_padre").trim());
        } else {
            dat.setCVE_MUN_PADRE(rs.getString("cve_mun_padre"));
        } 
         

        return dat;
    }

}
