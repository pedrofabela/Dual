package beans;

import java.io.Serializable;

public class usuarioBean implements Serializable {

    private static final long serialVersionUID = -3358884679090829992L;

    private String NAMEUSUARIO;
    private String PASSWORD;
    private Integer PERFIL;
    private String NAMEPERFIL;
    private String USUARIO;
    private String FILTRO;
    private String USUARIO_LOGIN;
    private String ESTATUS_VARIABLE;
    private String NAMEPERFIL_VARIABLE;
    private String ESTATUS;
    private String ID_PERSONA;
    private String ID_USUARIO;
    private String AUXNOMESC;
    private String AUXPERFILUSR;
    private String AUXCCT;
    private String ID_IE_UE;
    private String ID_CICLO;
    private String FECHA_INICIO;
    private String FECHA_TERMINO;
    private String CICLO;
    private String ID_ESCUELA;
     private String NOMBRE_COMPLETO;
      private String TELCASA_PERSONA;
       private String TELCEL_PERSONA;
        private String CORREO_PERSONA;

    public String getCORREO_PERSONA() {
        return CORREO_PERSONA;
    }

    public void setCORREO_PERSONA(String CORREO_PERSONA) {
        this.CORREO_PERSONA = CORREO_PERSONA;
    }
        

    public String getTELCASA_PERSONA() {
        return TELCASA_PERSONA;
    }

    public void setTELCASA_PERSONA(String TELCASA_PERSONA) {
        this.TELCASA_PERSONA = TELCASA_PERSONA;
    }

    public String getTELCEL_PERSONA() {
        return TELCEL_PERSONA;
    }

    public void setTELCEL_PERSONA(String TELCEL_PERSONA) {
        this.TELCEL_PERSONA = TELCEL_PERSONA;
    }

     
     
    public String getNOMBRE_COMPLETO() {
        return NOMBRE_COMPLETO;
    }

    public void setNOMBRE_COMPLETO(String NOMBRE_COMPLETO) {
        this.NOMBRE_COMPLETO = NOMBRE_COMPLETO;
    }
    
    
    
    

    public String getID_ESCUELA() {
        return ID_ESCUELA;
    }

    public void setID_ESCUELA(String ID_ESCUELA) {
        this.ID_ESCUELA = ID_ESCUELA;
    }
    
    
    
    public String getID_IE_UE() {
        return ID_IE_UE;
    }

    public void setID_IE_UE(String ID_IE_UE) {
        this.ID_IE_UE = ID_IE_UE;
    }

    public String getAUXNOMESC() {
        return AUXNOMESC;
    }

    public void setAUXNOMESC(String AUXNOMESC) {
        this.AUXNOMESC = AUXNOMESC;
    }

    public String getAUXPERFILUSR() {
        return AUXPERFILUSR;
    }

    public void setAUXPERFILUSR(String AUXPERFILUSR) {
        this.AUXPERFILUSR = AUXPERFILUSR;
    }

    public String getAUXCCT() {
        return AUXCCT;
    }

    public void setAUXCCT(String AUXCCT) {
        this.AUXCCT = AUXCCT;
    }

    public String getID_PERSONA() {
        return ID_PERSONA;
    }

    public void setID_PERSONA(String ID_PERSONA) {
        this.ID_PERSONA = ID_PERSONA;
    }

    public String getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(String ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getESTATUS_VARIABLE() {
        return ESTATUS_VARIABLE;
    }

    public void setESTATUS_VARIABLE(String ESTATUS_VARIABLE) {
        this.ESTATUS_VARIABLE = ESTATUS_VARIABLE;
    }

    public String getNAMEPERFIL_VARIABLE() {
        return NAMEPERFIL_VARIABLE;
    }

    public void setNAMEPERFIL_VARIABLE(String NAMEPERFIL_VARIABLE) {
        this.NAMEPERFIL_VARIABLE = NAMEPERFIL_VARIABLE;
    }

    public String getESTATUS() {
        return ESTATUS;
    }

    public void setESTATUS(String ESTATUS) {
        this.ESTATUS = ESTATUS;
    }

    public String getUSUARIO_LOGIN() {
        return USUARIO_LOGIN;
    }

    public void setUSUARIO_LOGIN(String USUARIO_LOGIN) {
        this.USUARIO_LOGIN = USUARIO_LOGIN;
    }

    public String getNAMEUSUARIO() {
        return NAMEUSUARIO;
    }

    public void setNAMEUSUARIO(String nAMEUSUARIO) {
        NAMEUSUARIO = nAMEUSUARIO;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String pASSWORD) {
        PASSWORD = pASSWORD;
    }

    public Integer getPERFIL() {
        return PERFIL;
    }

    public void setPERFIL(Integer pERFIL) {
        PERFIL = pERFIL;
    }

    public String getNAMEPERFIL() {
        return NAMEPERFIL;
    }

    public void setNAMEPERFIL(String nAMEPERFIL) {
        NAMEPERFIL = nAMEPERFIL;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String uSUARIO) {
        USUARIO = uSUARIO;
    }

    public String getFILTRO() {
        return FILTRO;
    }

    public void setFILTRO(String fILTRO) {
        FILTRO = fILTRO;
    }

    public String getID_CICLO() {
        return ID_CICLO;
    }

    public void setID_CICLO(String ID_CICLO) {
        this.ID_CICLO = ID_CICLO;
    }

    public String getFECHA_INICIO() {
        return FECHA_INICIO;
    }

    public void setFECHA_INICIO(String FECHA_INICIO) {
        this.FECHA_INICIO = FECHA_INICIO;
    }

    public String getFECHA_TERMINO() {
        return FECHA_TERMINO;
    }

    public void setFECHA_TERMINO(String FECHA_TERMINO) {
        this.FECHA_TERMINO = FECHA_TERMINO;
    }

    public String getCICLO() {
        return CICLO;
    }

    public void setCICLO(String CICLO) {
        this.CICLO = CICLO;
    }

}
