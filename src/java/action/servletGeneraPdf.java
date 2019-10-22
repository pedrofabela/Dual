package action;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;




import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.OracleDAOFactory;
import utilidades.Constantes;
import java.io.File;
import java.net.URL;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;



public class servletGeneraPdf extends HttpServlet{


	Connection conexion = null;
	
	private String nombreReporte;
	private String rutaJasper;
	private String rutaImg;
	private String esExcel = "false";
	private String esPDF = "false";
	private String pusuario;
	private String fechaActual;
	private String whereval;


	
	

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HashMap parameters = new HashMap();
		
		//constantes que indican la ruta de las imagenes, reporte
		//nombreReporte="ejem.jasper";
		
        nombreReporte=request.getParameter("reporte");
        rutaJasper= Constantes.rutareportesjasper; 
        rutaImg=Constantes.rutaImages;
		//rutaImg=Constantes.rutaImages;
		
		//subir a sesion los datos
               
        
        if(nombreReporte.equalsIgnoreCase("anexo1Dual.jasper")){
			parameters.put("nombreEsc", request.getParameter("nombreEsc"));				
                        parameters.put("programa",request.getParameter("programa") );
                        parameters.put("id_programa",request.getParameter("id_programa") );
		
    	}
        

		ServletOutputStream servletOutputStream;

		
						try {
					   try {
							
							OracleDAOFactory orac = new OracleDAOFactory();
							conexion = orac.createConnection();
						} catch (SQLException sqlEx) {
							Constantes.enviaMensajeConsola(sqlEx.getMessage());
						}
						

	            File file = new File(rutaJasper + nombreReporte);
				JasperReport jasperReport = (JasperReport) JRLoader
						.loadObject(file);
				
				byte[] fichero = JasperRunManager.runReportToPdf(jasperReport,
						parameters, conexion);
				
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition",
						"inline; filename=informe.pdf");
				response.setHeader("Cache-Control", "max-age=30");
				response.setHeader("Pragma", "No-cache");
				response.setDateHeader("Expires", 0);
				response.setContentLength(fichero.length);
				response.setHeader("Content-Description", "Pdf");
				
				servletOutputStream = response.getOutputStream();
				
				servletOutputStream.write(fichero, 0, fichero.length);
				servletOutputStream.flush();
				servletOutputStream.close();
				
			 
				
			} catch (Exception e) {

	            Constantes.enviaMensajeConsola("error: "+e);
				StringWriter stringWriter = new StringWriter();
				PrintWriter printWriter = new PrintWriter(stringWriter);
				e.printStackTrace(printWriter);
				response.setContentType("text/plain");
				response.getOutputStream().print(stringWriter.toString());
			 } 
		
	}
        

		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}


	 
	 
	 
	 
	//get y set	
	public Connection getConexion() {
		
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public String getRutaJasper() {
		return rutaJasper;
	}

	public void setRutaJasper(String rutaJasper) {
		this.rutaJasper = rutaJasper;
	}

	public String getRutaImg() {
		return rutaImg;
	}

	public void setRutaImg(String rutaImg) {
		this.rutaImg = rutaImg;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getEsExcel() {
		return esExcel;
	}

	public void setEsExcel(String esExcel) {
		this.esExcel = esExcel;
	}

	public String getEsPDF() {
		return esPDF;
	}

	public void setEsPDF(String esPDF) {
		this.esPDF = esPDF;
	}

	

	public String getWhereval() {
		return whereval;
	}

	public void setWhereval(String whereval) {
		this.whereval = whereval;
	}

	public String getPusuario() {
		return pusuario;
	}

	public void setPusuario(String pusuario) {
		this.pusuario = pusuario;
	}

}