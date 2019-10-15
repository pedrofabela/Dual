/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;

/**
 *
 * @author gioca
 */
public class EncabezadoBean {
    
    private String ID_ENCABEZADO;   
    private String NOM_ENCABEZADO;  
    private List<PreguntasBean> listaPregEnca; 

    public String getID_ENCABEZADO() {
        return ID_ENCABEZADO;
    }

    public void setID_ENCABEZADO(String ID_ENCABEZADO) {
        this.ID_ENCABEZADO = ID_ENCABEZADO;
    }

  

    public String getNOM_ENCABEZADO() {
        return NOM_ENCABEZADO;
    }

    public void setNOM_ENCABEZADO(String NOM_ENCABEZADO) {
        this.NOM_ENCABEZADO = NOM_ENCABEZADO;
    }

   

    public List<PreguntasBean> getListaPregEnca() {
        return listaPregEnca;
    }

    public void setListaPregEnca(List<PreguntasBean> listaPregEnca) {
        this.listaPregEnca = listaPregEnca;
    }
    
    
    
    
    
}
