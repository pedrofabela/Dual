package beans;

import java.io.Serializable;


public class SectorSubsectorBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	
	private String ID_SECTOR;
        private String NOM_SECTOR;
        private String ID_SUBSECTOR;
        private String NOM_SUBSECTOR;

    public String getID_SECTOR() {
        return ID_SECTOR;
    }

    public void setID_SECTOR(String ID_SECTOR) {
        this.ID_SECTOR = ID_SECTOR;
    }

    public String getNOM_SECTOR() {
        return NOM_SECTOR;
    }

    public void setNOM_SECTOR(String NOM_SECTOR) {
        this.NOM_SECTOR = NOM_SECTOR;
    }

    public String getID_SUBSECTOR() {
        return ID_SUBSECTOR;
    }

    public void setID_SUBSECTOR(String ID_SUBSECTOR) {
        this.ID_SUBSECTOR = ID_SUBSECTOR;
    }

    public String getNOM_SUBSECTOR() {
        return NOM_SUBSECTOR;
    }

    public void setNOM_SUBSECTOR(String NOM_SUBSECTOR) {
        this.NOM_SUBSECTOR = NOM_SUBSECTOR;
    }

        
    
   
	
}
