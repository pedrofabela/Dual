package beans;

import java.io.Serializable;


public class ColoniasBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String COLONIA_AUX;
	private String MUNICIPIO;
        private String ID_MUNICIPIO;

    public String getCOLONIA_AUX() {
        return COLONIA_AUX;
    }

    public void setCOLONIA_AUX(String COLONIA_AUX) {
        this.COLONIA_AUX = COLONIA_AUX;
    }

    

    public String getMUNICIPIO() {
        return MUNICIPIO;
    }

    public void setMUNICIPIO(String MUNICIPIO) {
        this.MUNICIPIO = MUNICIPIO;
    }

    public String getID_MUNICIPIO() {
        return ID_MUNICIPIO;
    }

    public void setID_MUNICIPIO(String ID_MUNICIPIO) {
        this.ID_MUNICIPIO = ID_MUNICIPIO;
    }

   
	
   
	
}
