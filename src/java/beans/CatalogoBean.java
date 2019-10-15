package beans;

import java.io.Serializable;


public class CatalogoBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	
	private String DESCRIPCION;
        private String ID_CATALOGO;

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getID_CATALOGO() {
        return ID_CATALOGO;
    }

    public void setID_CATALOGO(String ID_CATALOGO) {
        this.ID_CATALOGO = ID_CATALOGO;
    }

    
   
	
}
