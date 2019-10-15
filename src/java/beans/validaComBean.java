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
public class validaComBean {
    
    private String ID_COMPEPTENCIA;
    private String PORCENTAJE_SUMA;

    public validaComBean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getID_COMPEPTENCIA() {
        return ID_COMPEPTENCIA;
    }

    public void setID_COMPEPTENCIA(String ID_COMPEPTENCIA) {
        this.ID_COMPEPTENCIA = ID_COMPEPTENCIA;
    }

    public String getPORCENTAJE_SUMA() {
        return PORCENTAJE_SUMA;
    }

    public void setPORCENTAJE_SUMA(String PORCENTAJE_SUMA) {
        this.PORCENTAJE_SUMA = PORCENTAJE_SUMA;
    }

    public validaComBean(String ID_COMPEPTENCIA, String PORCENTAJE_SUMA) {
        this.ID_COMPEPTENCIA = ID_COMPEPTENCIA;
        this.PORCENTAJE_SUMA = PORCENTAJE_SUMA;
    }

   
  
    
}
