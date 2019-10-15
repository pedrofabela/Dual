<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
  <link href="<s:property value="@utilidades.Constantes@rutaEstilos"/>css/estiloUDAIV.css" rel="stylesheet" type="text/css" />
 
	  <link href="<s:property value="@utilidades.Constantes@rutaEstilos"/>css/tablaV.css" rel="stylesheet" type="text/css" />
	  <link href="<s:property value="@utilidades.Constantes@rutaEstilos"/>css/menu.css" rel="stylesheet" type="text/css" />
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      
      <!-- calendario -->

	    <script type="text/javascript" src="<s:url value="/js/jquery-1.8.3.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/jquery-ui-1.9.2.custom.js"/>"></script>
        <link type="text/css" href="<s:url value="/css/jquery-ui-1.9.2.custom.css"/>" rel="stylesheet" />
        
        <title>Curso CIC</title>

  

        <script type="text/javascript">

            function guarda(accion){
            	
                document.forma.action = accion;
                document.forma.submit();
            }
           

            //PARA REGRESAR EN DONDE SE QUEDO...........
            window.onload = function() {
            	  var pos = window.name || 0;
            	  window.scrollTo(0, pos);
            	 }
            	 window.onunload = function() {
            	 window.name = self.pageYOffset
            	    || (document.documentElement.scrollTop + document.body.scrollTop);
            	 }
        </script>




</head>
<body> 
  <img src="images/bannerCursoCIC.png"  alt="UDAI" align="center"/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
 <s:form name="forma" action="Acceso">

	<table width="49%" class="FormS" align="center">
            <tr>
                <td align="left" valign="top" background="<s:url value="images/accesoframe1.jpg"/>">
                    <table width="60%" align="center">
                        <tr>
                            <td height="25px" align="center">
                                <s:actionmessage cssStyle="text-align:center;font-size: 8pt" />
                            </td>
                        </tr>
                        <tr><td class="subtituloR">ACCESO</td></tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr>
                            <td align="left">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;USUARIO:
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <s:textfield cssClass="estTF" name="cveusuario" id="cveusuario" size="15" maxlength="20" required="true" />
                            </td>
                        </tr>
                            <tr><td>&nbsp;</td></tr>
                        <tr>
                            <td align="left">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CONTRASEÑA:&nbsp;&nbsp;&nbsp;<s:password cssClass="estTF" name="pasusuario" id="pasusuario" size="15" maxlength="12" required="true" />
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>              
             <!-- ANTES  -->
	         <td align="right"><a href="Javascript:guarda('verAcceso')"> <img
				src="<s:property value="@utilidades.Constantes@rutaEstilos"/>images/bIngresar.png"
				alt="Ingresar" /> </a></td>
               
            </tr>            
       </table>  
       </br>
       </br>
       </br>
       <div align="center"></div>
                <div class="Error" align="center">
                    <s:actionerror />
                    <s:fielderror />
       </div>
       </br>
       </br>
       </br>
       </br>
       </br>
       </br>
            
     <table class="piePagina">
        <tbody>
            <tr>
                <td valign="middle">
                    <a href="http://seduc.edomex.gob.mx/" target="_blank">
                        <img src="images/portalSE.png">&nbsp;&nbsp;Portal de la Secretar&iacute;a de Educaci&oacute;n
                    </a>
                </td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </tbody>               
    </table>
    
    
</s:form>
</body>
</html>