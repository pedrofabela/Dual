<%--
    Author     : Esteban y yo
--%>

<%@page import="beans.moduloBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <script type="text/javascript">

            function SeleccionMenu(accion){
                 if (accion == "accesoManual"){
                     document.formularioPrincipal.target="_blank";
                     document.formularioPrincipal.action = accion;
                     document.formularioPrincipal.submit();
                 }else {
                    document.formularioPrincipal.target="_self";
                    document.formularioPrincipal.action = accion;
                    document.formularioPrincipal.submit();
                 }
            }

        </script>
    </head>
    <body>
        <s:form name="formularioPrincipal" method="post" id="formularioPrincipal">
            <s:iterator value="modulosAUX" id="modulosAUX" status="stat">
                        <s:if test='CVE_MODPADRE=="S"'>
                            <li class="nav-item ">
                               
                                
                                <a class="nav-link" data-toggle="collapse" href="#<s:property value="DESC_MOD"/>"  style="background: rgb(177,26,79);">
                                    <i class="material-icons"><s:property value="IMAGEN"></s:property></i>
                                    <p> <s:property value="DESC_MOD"/>
                                        <b class="caret"></b>
                                    </p>
                                </a>
                                
                                
                                        <div class="collapse" id="<s:property value="DESC_MOD"/>">
                                            <ul class="nav">
                                                <li class="nav-item " >
                                                 
                                                    <a class="nav-link"  href="Javascript:SeleccionMenu('<s:property value="ACCION"/>')">
                                                        
                                                        
                                                        <span >  <s:property value="MOD"/> </span>
                                                        
                                                       
                                                        <s:set var="myVar">
                                                            <s:property value="MOD.length()" />
                                                        </s:set>
                                                        <s:if test='MOD.length()<40'>
                                                            <c:forEach begin="${myVar}" end="40" varStatus="lp">
                                                                &nbsp;
                                                            </c:forEach>
                                                        </s:if>                                                        
                                                    </a> 
                                                    
                                                    
                                                        <s:iterator value="modulosAUXP" id="modulosAUXP" status="stat">
                                                            <s:if test='CVE_MODULO==MODPADRE'>
                                                                <a href="Javascript:SeleccionMenu('<s:property value="ACCION"/>' )" class="nav-link" style="margin-top: -17px; background: rgba(177,26,79, 0.6);" >
                                                                  
                                                                      <i class="material-icons"><s:property value="IMAGEN"></s:property></i><s:property value="MOD"/> 
                                                                    <s:set var="myVar">
                                                                        <s:property value="MOD.length()" />
                                                                    </s:set>
                                                                    <s:if test='MOD.length()<40'>
                                                                        <c:forEach begin="${myVar}" end="40" varStatus="lp">
                                                                            &nbsp;
                                                                        </c:forEach>
                                                                    </s:if>                                                        
                                                                </a>
                                                                <br/>
                                                            </s:if>
                                                        </s:iterator>
                                                    
                                                </li>

                                            </ul>
                                        </div>
                                
                                
                                
                                
                               
                                
                                    
                                  
                                   
                                
                            </li>
                        </s:if>
                        <s:hidden name = "modulosAUX[%{#stat.index}].CVE_MODULO" id="CVE_MODULO"></s:hidden>
                        <s:hidden name = "modulosAUX[%{#stat.index}].CVE_MODPADRE" id="CVE_MODPADRE"></s:hidden>
                        <s:hidden name = "modulosAUX[%{#stat.index}].DESC_MOD" id="DESC_MOD"></s:hidden>
                        <%-- <s:hidden  name = "modulosAUX[%{#stat.index}].ACTION" id="ACTION"></s:hidden> --%>
                        <s:hidden name = "modulosAUX[%{#stat.index}].IMAGEN" id="IMAGEN"></s:hidden>
                        <s:hidden name = "modulosAUX[%{#stat.index}].NAMEUNIDAD" id="NAMEUNIDAD"></s:hidden>
                        <s:hidden name = "modulosAUX[%{#stat.index}].VALORU" id="VALORU"></s:hidden>
                        
                        
                    </s:iterator>
            
            <s:iterator value="modulosAUXP" id="modulosAUXP" status="stat">                        
                <s:hidden  name = "modulosAUXP[%{#stat.index}].MODULO" id="MODULO"></s:hidden>
                <s:hidden  name = "modulosAUXP[%{#stat.index}].MODPADRE" id="MODPADRE"></s:hidden>
                <s:hidden  name = "modulosAUXP[%{#stat.index}].MOD" id="MOD"></s:hidden>
                <s:hidden  name = "modulosAUXP[%{#stat.index}].ACCION" id="ACCION"></s:hidden>
                <s:hidden name =  "modulosAUXP[%{#stat.index}].CVE_ACCESO_AUX" id="CVE_ACCESO_AUX"></s:hidden>
            </s:iterator>
            
        </s:form>
    </body>
</html>