package beans;

import java.io.Serializable;
import java.util.Date;


public class UnidadesEconomicasBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;
    
    private String RFCAUX;     
    private String ID_UE;
    private String ID_ESC;
    private String RFC;
    private String RAZON_SOCIAL;
    private String NOMBRE_COMER;
    private String GIRO;
    private String SECTOR;
    private String DOMICILIO;
    private String COLONIA;
    private String LOCALIDAD;
    private String CP;
    private String CVE_MUNICIPIO;
    private String MUNICIPIO;
    
    private String RFCR;
    private String RAZON_SOCIALR;
    private String NOMBRE_COMERR;
    private String GIROR;
    private String SECTORR;
    private String DOMICILIOR;
    private String COLONIAR;
    private String LOCALIDADR;
    private String CPR;
    private String CVE_MUNICIPIOR;
    private String MUNICIPIOR;
    
    
    
    
    private String ID_IE_UE;
    private String STATUS_UE;
    private String STATUS_EVALUACION;
    private String STATUS_RESULTADO_EVALUACION;
    private String STATUS_GENERAL;
    private String PORCENTAJE_EVALUACION;
    
    private Date FEC_VINCULACION;
    private String RFCVALIDO;
    
  
    
    //*********************************** REGISTRO DE SUCURSALES Y PLANTELES
      private String OPCION;
      private String VALOR;
      private String ID_SUC;
      
      private String CCT_PLA;
      private String NOM_PLA;
      private String DOM_PLA;
      private String CP_PLA;
      private String COLONIA_PLA;
      private String MUN_PLA;
      private String CVE_MUNICIPIO_PLA;
      
      private String CCT_SUC;
      private String NOM_SUC;
      private String DOM_SUC;
      private String CP_SUC;
      private String COLONIA_SUC;
      private String MUN_SUC;
      private String CVE_MUNICIPIO_SUC;
      
      
      //********************Beans de Consulta suc**************
      private String ID_SUC_CON;
      private String CCT_SUC_CON;
      private String NOM_SUC_CON;
      private String DOM_SUC_CON;
      private String CP_SUC_CON;
      private String COLONIA_SUC_CON;
      private String MUN_SUC_CON;
      private String MUNICIPIO_SUC_CON;
      private String TIPO_CON;
      private String NOMBRE_SUC;

    public String getNOMBRE_SUC() {
        return NOMBRE_SUC;
    }

    public void setNOMBRE_SUC(String NOMBRE_SUC) {
        this.NOMBRE_SUC = NOMBRE_SUC;
    }
      
      

    public String getTIPO_CON() {
        return TIPO_CON;
    }

    public void setTIPO_CON(String TIPO_CON) {
        this.TIPO_CON = TIPO_CON;
    }
      
      

    public String getID_SUC_CON() {
        return ID_SUC_CON;
    }

    public void setID_SUC_CON(String ID_SUC_CON) {
        this.ID_SUC_CON = ID_SUC_CON;
    }

    public String getCCT_SUC_CON() {
        return CCT_SUC_CON;
    }

    public void setCCT_SUC_CON(String CCT_SUC_CON) {
        this.CCT_SUC_CON = CCT_SUC_CON;
    }

    public String getNOM_SUC_CON() {
        return NOM_SUC_CON;
    }

    public void setNOM_SUC_CON(String NOM_SUC_CON) {
        this.NOM_SUC_CON = NOM_SUC_CON;
    }

    public String getDOM_SUC_CON() {
        return DOM_SUC_CON;
    }

    public void setDOM_SUC_CON(String DOM_SUC_CON) {
        this.DOM_SUC_CON = DOM_SUC_CON;
    }

    public String getCP_SUC_CON() {
        return CP_SUC_CON;
    }

    public void setCP_SUC_CON(String CP_SUC_CON) {
        this.CP_SUC_CON = CP_SUC_CON;
    }

    public String getCOLONIA_SUC_CON() {
        return COLONIA_SUC_CON;
    }

    public void setCOLONIA_SUC_CON(String COLONIA_SUC_CON) {
        this.COLONIA_SUC_CON = COLONIA_SUC_CON;
    }

    public String getMUN_SUC_CON() {
        return MUN_SUC_CON;
    }

    public void setMUN_SUC_CON(String MUN_SUC_CON) {
        this.MUN_SUC_CON = MUN_SUC_CON;
    }

    public String getMUNICIPIO_SUC_CON() {
        return MUNICIPIO_SUC_CON;
    }

    public void setMUNICIPIO_SUC_CON(String MUNICIPIO_SUC_CON) {
        this.MUNICIPIO_SUC_CON = MUNICIPIO_SUC_CON;
    }
      
      

    public String getID_SUC() {
        return ID_SUC;
    }

    public void setID_SUC(String ID_SUC) {
        this.ID_SUC = ID_SUC;
    }
      
     

    public String getOPCION() {
        return OPCION;
    }

    public void setOPCION(String OPCION) {
        this.OPCION = OPCION;
    }

    public String getVALOR() {
        return VALOR;
    }

    public void setVALOR(String VALOR) {
        this.VALOR = VALOR;
    }
    
    

    public String getCCT_PLA() {
        return CCT_PLA;
    }

    public void setCCT_PLA(String CCT_PLA) {
        this.CCT_PLA = CCT_PLA;
    }

    public String getNOM_PLA() {
        return NOM_PLA;
    }

    public void setNOM_PLA(String NOM_PLA) {
        this.NOM_PLA = NOM_PLA;
    }

    public String getDOM_PLA() {
        return DOM_PLA;
    }

    public void setDOM_PLA(String DOM_PLA) {
        this.DOM_PLA = DOM_PLA;
    }

    public String getCP_PLA() {
        return CP_PLA;
    }

    public void setCP_PLA(String CP_PLA) {
        this.CP_PLA = CP_PLA;
    }

    public String getCOLONIA_PLA() {
        return COLONIA_PLA;
    }

    public void setCOLONIA_PLA(String COLONIA_PLA) {
        this.COLONIA_PLA = COLONIA_PLA;
    }

    public String getMUN_PLA() {
        return MUN_PLA;
    }

    public void setMUN_PLA(String MUN_PLA) {
        this.MUN_PLA = MUN_PLA;
    }

    public String getCVE_MUNICIPIO_PLA() {
        return CVE_MUNICIPIO_PLA;
    }

    public void setCVE_MUNICIPIO_PLA(String CVE_MUNICIPIO_PLA) {
        this.CVE_MUNICIPIO_PLA = CVE_MUNICIPIO_PLA;
    }

    public String getCCT_SUC() {
        return CCT_SUC;
    }

    public void setCCT_SUC(String CCT_SUC) {
        this.CCT_SUC = CCT_SUC;
    }

    public String getNOM_SUC() {
        return NOM_SUC;
    }

    public void setNOM_SUC(String NOM_SUC) {
        this.NOM_SUC = NOM_SUC;
    }

    public String getDOM_SUC() {
        return DOM_SUC;
    }

    public void setDOM_SUC(String DOM_SUC) {
        this.DOM_SUC = DOM_SUC;
    }

    public String getCP_SUC() {
        return CP_SUC;
    }

    public void setCP_SUC(String CP_SUC) {
        this.CP_SUC = CP_SUC;
    }

    public String getCOLONIA_SUC() {
        return COLONIA_SUC;
    }

    public void setCOLONIA_SUC(String COLONIA_SUC) {
        this.COLONIA_SUC = COLONIA_SUC;
    }

    public String getMUN_SUC() {
        return MUN_SUC;
    }

    public void setMUN_SUC(String MUN_SUC) {
        this.MUN_SUC = MUN_SUC;
    }

    public String getCVE_MUNICIPIO_SUC() {
        return CVE_MUNICIPIO_SUC;
    }

    public void setCVE_MUNICIPIO_SUC(String CVE_MUNICIPIO_SUC) {
        this.CVE_MUNICIPIO_SUC = CVE_MUNICIPIO_SUC;
    }
   
    public String getRFCAUX() {
        return RFCAUX;
    }

    public void setRFCAUX(String RFCAUX) {
        this.RFCAUX = RFCAUX;
    }

    public String getID_UE() {
        return ID_UE;
    }

    public void setID_UE(String ID_UE) {
        this.ID_UE = ID_UE;
    }

    public String getID_ESC() {
        return ID_ESC;
    }

    public void setID_ESC(String ID_ESC) {
        this.ID_ESC = ID_ESC;
    }
    
    

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getRAZON_SOCIAL() {
        return RAZON_SOCIAL;
    }

    public void setRAZON_SOCIAL(String RAZON_SOCIAL) {
        this.RAZON_SOCIAL = RAZON_SOCIAL;
    }

    public String getNOMBRE_COMER() {
        return NOMBRE_COMER;
    }

    public void setNOMBRE_COMER(String NOMBRE_COMER) {
        this.NOMBRE_COMER = NOMBRE_COMER;
    }
    
    

    public String getGIRO() {
        return GIRO;
    }

    public void setGIRO(String GIRO) {
        this.GIRO = GIRO;
    }

    public String getSECTOR() {
        return SECTOR;
    }

    public void setSECTOR(String SECTOR) {
        this.SECTOR = SECTOR;
    }

    public String getDOMICILIO() {
        return DOMICILIO;
    }

    public void setDOMICILIO(String DOMICILIO) {
        this.DOMICILIO = DOMICILIO;
    }

    public String getCOLONIA() {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) {
        this.COLONIA = COLONIA;
    }

    public String getLOCALIDAD() {
        return LOCALIDAD;
    }

    public void setLOCALIDAD(String LOCALIDAD) {
        this.LOCALIDAD = LOCALIDAD;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getCVE_MUNICIPIO() {
        return CVE_MUNICIPIO;
    }

    public void setCVE_MUNICIPIO(String CVE_MUNICIPIO) {
        this.CVE_MUNICIPIO = CVE_MUNICIPIO;
    }

    public String getMUNICIPIO() {
        return MUNICIPIO;
    }

    public void setMUNICIPIO(String MUNICIPIO) {
        this.MUNICIPIO = MUNICIPIO;
    }

    public String getID_IE_UE() {
        return ID_IE_UE;
    }

    public void setID_IE_UE(String ID_IE_UE) {
        this.ID_IE_UE = ID_IE_UE;
    }
    
    
    

    public String getSTATUS_UE() {
        return STATUS_UE;
    }

    public void setSTATUS_UE(String STATUS_UE) {
        this.STATUS_UE = STATUS_UE;
    }

    public String getSTATUS_EVALUACION() {
        return STATUS_EVALUACION;
    }

    public void setSTATUS_EVALUACION(String STATUS_EVALUACION) {
        this.STATUS_EVALUACION = STATUS_EVALUACION;
    }

    public String getSTATUS_RESULTADO_EVALUACION() {
        return STATUS_RESULTADO_EVALUACION;
    }

    public void setSTATUS_RESULTADO_EVALUACION(String STATUS_RESULTADO_EVALUACION) {
        this.STATUS_RESULTADO_EVALUACION = STATUS_RESULTADO_EVALUACION;
    }

    public String getSTATUS_GENERAL() {
        return STATUS_GENERAL;
    }

    public void setSTATUS_GENERAL(String STATUS_GENERAL) {
        this.STATUS_GENERAL = STATUS_GENERAL;
    }

    public String getPORCENTAJE_EVALUACION() {
        return PORCENTAJE_EVALUACION;
    }

    public void setPORCENTAJE_EVALUACION(String PORCENTAJE_EVALUACION) {
        this.PORCENTAJE_EVALUACION = PORCENTAJE_EVALUACION;
    }

    public Date getFEC_VINCULACION() {
        return FEC_VINCULACION;
    }

    public void setFEC_VINCULACION(Date FEC_VINCULACION) {
        this.FEC_VINCULACION = FEC_VINCULACION;
    }

    public String getRFCVALIDO() {
        return RFCVALIDO;
    }

    public void setRFCVALIDO(String RFCVALIDO) {
        this.RFCVALIDO = RFCVALIDO;
    }

    public String getRFCR() {
        return RFCR;
    }

    public void setRFCR(String RFCR) {
        this.RFCR = RFCR;
    }

    public String getRAZON_SOCIALR() {
        return RAZON_SOCIALR;
    }

    public void setRAZON_SOCIALR(String RAZON_SOCIALR) {
        this.RAZON_SOCIALR = RAZON_SOCIALR;
    }

    public String getNOMBRE_COMERR() {
        return NOMBRE_COMERR;
    }

    public void setNOMBRE_COMERR(String NOMBRE_COMERR) {
        this.NOMBRE_COMERR = NOMBRE_COMERR;
    }
    
    

    public String getGIROR() {
        return GIROR;
    }

    public void setGIROR(String GIROR) {
        this.GIROR = GIROR;
    }

    public String getSECTORR() {
        return SECTORR;
    }

    public void setSECTORR(String SECTORR) {
        this.SECTORR = SECTORR;
    }

    public String getDOMICILIOR() {
        return DOMICILIOR;
    }

    public void setDOMICILIOR(String DOMICILIOR) {
        this.DOMICILIOR = DOMICILIOR;
    }

    public String getCOLONIAR() {
        return COLONIAR;
    }

    public void setCOLONIAR(String COLONIAR) {
        this.COLONIAR = COLONIAR;
    }

    public String getLOCALIDADR() {
        return LOCALIDADR;
    }

    public void setLOCALIDADR(String LOCALIDADR) {
        this.LOCALIDADR = LOCALIDADR;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public String getCVE_MUNICIPIOR() {
        return CVE_MUNICIPIOR;
    }

    public void setCVE_MUNICIPIOR(String CVE_MUNICIPIOR) {
        this.CVE_MUNICIPIOR = CVE_MUNICIPIOR;
    }

    public String getMUNICIPIOR() {
        return MUNICIPIOR;
    }

    public void setMUNICIPIOR(String MUNICIPIOR) {
        this.MUNICIPIOR = MUNICIPIOR;
    }
    
    
    
    
    	
}
