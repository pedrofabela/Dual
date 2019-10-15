/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author pedro
 */
public class actividadesBean {
    
    public String ID_COMPETENCIA;
public String ID_ACTIVIDAD;
public String ACTIVIDAD;
public String FECHA_REG_ACTIVIDAD;
private String AUXACTIVIDAD;

    public String getAUXACTIVIDAD() {
        return AUXACTIVIDAD;
    }

    public void setAUXACTIVIDAD(String AUXACTIVIDAD) {
        this.AUXACTIVIDAD = AUXACTIVIDAD;
    }



    public String getID_COMPETENCIA() {
        return ID_COMPETENCIA;
    }

    public void setID_COMPETENCIA(String ID_COMPETENCIA) {
        this.ID_COMPETENCIA = ID_COMPETENCIA;
    }

    public String getID_ACTIVIDAD() {
        return ID_ACTIVIDAD;
    }

    public void setID_ACTIVIDAD(String ID_ACTIVIDAD) {
        this.ID_ACTIVIDAD = ID_ACTIVIDAD;
    }

    public String getACTIVIDAD() {
        return ACTIVIDAD;
    }

    public void setACTIVIDAD(String ACTIVIDAD) {
        this.ACTIVIDAD = ACTIVIDAD;
    }

    public String getFECHA_REG_ACTIVIDAD() {
        return FECHA_REG_ACTIVIDAD;
    }

    public void setFECHA_REG_ACTIVIDAD(String FECHA_REG_ACTIVIDAD) {
        this.FECHA_REG_ACTIVIDAD = FECHA_REG_ACTIVIDAD;
    }
    
    
    
    
}
