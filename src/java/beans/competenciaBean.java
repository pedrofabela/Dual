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
public class competenciaBean {
    
 private String ID_MATERIA;
 private String ID_COMPETENCIA;
 private String COMPETENCIA;
 private String FECH_REC_COMPETENCIA;
 private String AUXCOMPETENCIA;
 

    public String getAUXCOMPETENCIA() {
        return AUXCOMPETENCIA;
    }

    public void setAUXCOMPETENCIA(String AUXCOMPETENCIA) {
        this.AUXCOMPETENCIA = AUXCOMPETENCIA;
    }
 
    public String getID_MATERIA() {
        return ID_MATERIA;
    }

    public void setID_MATERIA(String ID_MATERIA) {
        this.ID_MATERIA = ID_MATERIA;
    }

    public String getID_COMPETENCIA() {
        return ID_COMPETENCIA;
    }

    public void setID_COMPETENCIA(String ID_COMPETENCIA) {
        this.ID_COMPETENCIA = ID_COMPETENCIA;
    }

    public String getCOMPETENCIA() {
        return COMPETENCIA;
    }

    public void setCOMPETENCIA(String COMPETENCIA) {
        this.COMPETENCIA = COMPETENCIA;
    }

    public String getFECH_REC_COMPETENCIA() {
        return FECH_REC_COMPETENCIA;
    }

    public void setFECH_REC_COMPETENCIA(String FECH_REC_COMPETENCIA) {
        this.FECH_REC_COMPETENCIA = FECH_REC_COMPETENCIA;
    }
    
    
    
    
}
