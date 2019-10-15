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
public class materiaBean {
    
    public String ID_CCT_PLAN;
    public String ID_MATERIA;
    public String NOMBRE_MATERIA;
    public String FECH_REG_MATERIA;
    public String CVE_MATERIA;
      public String AUXMATERIA;
     public String NUMERO_PERIODO;

     
    public String getNUMERO_PERIODO() {
        return NUMERO_PERIODO;
    }

    public void setNUMERO_PERIODO(String NUMERO_PERIODO) {
        this.NUMERO_PERIODO = NUMERO_PERIODO;
    }
     
     
    public String getAUXMATERIA() {
        return AUXMATERIA;
    }

    public void setAUXMATERIA(String AUXMATERIA) {
        this.AUXMATERIA = AUXMATERIA;
    }
     
      
    public String getID_CCT_PLAN() {
        return ID_CCT_PLAN;
    }

    public void setID_CCT_PLAN(String ID_CCT_PLAN) {
        this.ID_CCT_PLAN = ID_CCT_PLAN;
    }

    public String getID_MATERIA() {
        return ID_MATERIA;
    }

    public void setID_MATERIA(String ID_MATERIA) {
        this.ID_MATERIA = ID_MATERIA;
    }

    public String getNOMBRE_MATERIA() {
        return NOMBRE_MATERIA;
    }

    public void setNOMBRE_MATERIA(String NOMBRE_MATERIA) {
        this.NOMBRE_MATERIA = NOMBRE_MATERIA;
    }

    public String getFECH_REG_MATERIA() {
        return FECH_REG_MATERIA;
    }

    public void setFECH_REG_MATERIA(String FECH_REG_MATERIA) {
        this.FECH_REG_MATERIA = FECH_REG_MATERIA;
    }

    public String getCVE_MATERIA() {
        return CVE_MATERIA;
    }

    public void setCVE_MATERIA(String CVE_MATERIA) {
        this.CVE_MATERIA = CVE_MATERIA;
    }



    
}
