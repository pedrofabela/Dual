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
public class PreguntasBean {
    
    private String ID_ENCABEZADO;   
    private String NOM_ENCABEZADO;  
    private String ID_PREGUNTA;
    private String PREGUNTA;
    private String TIPO_PREGUNTA;
    private String ORDEN;
    private List<RespuestaBean> listaPregRespuesta; 

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
    
    

    public String getID_PREGUNTA() {
        return ID_PREGUNTA;
    }

    public void setID_PREGUNTA(String ID_PREGUNTA) {
        this.ID_PREGUNTA = ID_PREGUNTA;
    }

    public String getPREGUNTA() {
        return PREGUNTA;
    }

    public void setPREGUNTA(String PREGUNTA) {
        this.PREGUNTA = PREGUNTA;
    }

    public String getTIPO_PREGUNTA() {
        return TIPO_PREGUNTA;
    }

    public void setTIPO_PREGUNTA(String TIPO_PREGUNTA) {
        this.TIPO_PREGUNTA = TIPO_PREGUNTA;
    }

    public String getORDEN() {
        return ORDEN;
    }

    public void setORDEN(String ORDEN) {
        this.ORDEN = ORDEN;
    }

    public List<RespuestaBean> getListaPregRespuesta() {
        return listaPregRespuesta;
    }

    public void setListaPregRespuesta(List<RespuestaBean> listaPregRespuesta) {
        this.listaPregRespuesta = listaPregRespuesta;
    }
    
    
    
}
