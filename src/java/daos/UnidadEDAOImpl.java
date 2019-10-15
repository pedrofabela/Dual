/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.UnidadesEconomicasBean;
import java.util.ArrayList;
import utilidades.ObjPrepareStatement;

/**
 *
 * @author dinamarca
 */
public class UnidadEDAOImpl extends OracleDAOFactory{
    
     public boolean ActualizarUnidadE(UnidadesEconomicasBean ue) throws Exception {
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        temporal = new ObjPrepareStatement("RAZON_SOCIAL", "STRING", ue.getRAZON_SOCIAL());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOM_COMERCIAL", "STRING", ue.getNOMBRE_COMER());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("GIRO", "STRING", ue.getGIRO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("SECTOR", "STRING", ue.getSECTOR());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO", "STRING", ue.getDOMICILIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COLONIA", "STRING", ue.getCOLONIA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("LOCALIDAD", "STRING", ue.getLOCALIDAD());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP", "STRING", ue.getCP());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MUNICIPIO", "STRING", ue.getCVE_MUNICIPIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS", "STRING", "1");
        arregloCampos.add(temporal);

        String Condicion = "where id_ue=" + ue.getID_UE();
       return queryUpdate("CAT_UNIDAD_ECONOMICA", arregloCampos, Condicion);
    }
     
     
     public boolean ActualizarSucursal(UnidadesEconomicasBean ue) throws Exception {
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        temporal = new ObjPrepareStatement("NOMBRE", "STRING", ue.getNOM_SUC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO", "STRING", ue.getDOM_SUC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP", "STRING", ue.getCP_SUC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COLONIA", "STRING", ue.getCOLONIA_SUC());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MUN", "STRING", ue.getCVE_MUNICIPIO_SUC());
        arregloCampos.add(temporal);

        String Condicion = "where id_suc=" + ue.getID_SUC();
       return queryUpdate("TBL_SUCURSALES", arregloCampos, Condicion);
    }
    
          public boolean ActualizarPlantel(UnidadesEconomicasBean ue) throws Exception {
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        temporal = new ObjPrepareStatement("NOMBRE", "STRING", ue.getNOM_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO", "STRING", ue.getDOM_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP", "STRING", ue.getCP_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("COLONIA", "STRING", ue.getCOLONIA_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CVE_MUN", "STRING", ue.getCVE_MUNICIPIO_PLA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CCT_PLANTEL", "STRING", ue.getCCT_PLA());
        arregloCampos.add(temporal);

        String Condicion = "where id_suc=" + ue.getID_ESC();
       return queryUpdate("TBL_SUCURSALES", arregloCampos, Condicion);
    }
}
