<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>



<script type="text/javascript">

    function validarFormulario() {
        
         

        var no_seguro = document.getElementById('NOSEGURO').value;

        var domicilio = document.getElementById('DOM').value;

        var localidad = document.getElementById('LOC').value;

        var cp = document.getElementById('CPA').value;

        var colonia = document.getElementById('COL').value;
         
        var fecha_inicio_dual = document.getElementById('FECHAINICIODUAL').value;
        
        var edad=parseInt(document.getElementById('EDAD').value);      
         
        
        
       
        

        if (no_seguro == null || no_seguro.length == 0 || /^\s+$/.test(no_seguro)) {
            
                document.getElementById('NOSEGURO').style.boxShadow = "0px 1px 5px red";
                document.getElementById('NOSEGURO').focus(); 
                return false;
             

        } else {
            document.getElementById('NOSEGURO').style.boxShadow = "";
        }
        
        if (domicilio == null || domicilio.length == 0 || /^\s+$/.test(domicilio)) {
            //alert('ERROR: El campo razon social no debe ir vacío.');
            document.getElementById('DOM').style.boxShadow = "0px 1px 5px red";
            document.getElementById('DOM').focus();
            return false;
        } else {
            document.getElementById('DOM').style.boxShadow = "";
        }
        
        if (localidad == null || localidad.length == 0 || /^\s+$/.test(localidad)) {
            //alert('ERROR: El campo razon social no debe ir vacío.');
            document.getElementById('LOC').style.boxShadow = "0px 1px 5px red";
            document.getElementById('LOC').focus();
            return false;
        } else {
            document.getElementById('LOC').style.boxShadow = "";
        }
        
        if (cp == null || cp.length == 0 || isNaN(cp)) {
            //alert('ERROR: El campo giro no debe ir vacío.');
            document.getElementById('CPA').style.boxShadow = "0px 1px 5px red";
            document.getElementById('CPA').focus();
            return false;
        } else {
            document.getElementById('CPA').style.boxShadow = "";
        }
        
        if (colonia == null || colonia.length == 0 ||  /^\s+$/.test(colonia)) {
            //alert('ERROR: El campo giro no debe ir vacío.');
            document.getElementById('COL').style.boxShadow = "0px 1px 5px red";
            document.getElementById('COL').focus();
            return false;
        } else {
            document.getElementById('COL').style.boxShadow = "";
        }
        
        if (fecha_inicio_dual == null || fecha_inicio_dual.length == 0 ||  /^\s+$/.test(fecha_inicio_dual)) {
            //alert('ERROR: El campo giro no debe ir vacío.');
            document.getElementById('FECHAINICIODUAL').style.boxShadow = "0px 1px 5px red";
            document.getElementById('FECHAINICIODUAL').focus();
            return false;
        } else {
            document.getElementById('FECHAINICIODUAL').style.boxShadow = "";
        }
        
           
        if (edad < 18) {
            
            //alert("si entro a edad menor")
            
            
            var tel_padre = document.getElementById('TELPADRE').value;
        
            var email_padre = document.getElementById('EMAILPADRE').value;
        
            var mismo_domicilio = document.getElementById('MISMO_DOMICILIO');
        
            var domicilio_padre = document.getElementById('DOMICILIOPADRE').value;
        
            var localidad_padre = document.getElementById('LOCALIDADPADRE').value;
        
            var cp_padre = document.getElementById('CPPADRE').value;
        
            var colonia_padre = document.getElementById('COLONIAPADRE').value;
            
            
            
            if (tel_padre == null || tel_padre.length == 0 || isNaN(cp)) {
                //alert('ERROR: El campo giro no debe ir vacío.');
                document.getElementById('TELPADRE').style.boxShadow = "0px 1px 5px red";
                document.getElementById('TELPADRE').focus();
            return false;
            } else {
                document.getElementById('TELPADRE').style.boxShadow = "";
            }
            
            if (!(/\S+@\S+\.\S+/.test(email_padre))) {
                document.getElementById('EMAILPADRE').style.boxShadow = "0px 1px 5px red";
                document.getElementById('EMAILPADRE').focus();
                return false;
            } else {
                document.getElementById('EMAILPADRE').style.boxShadow = "";
            }
            
            if(!mismo_domicilio.checked){
                
                if (domicilio_padre == null || domicilio_padre.length == 0 || /^\s+$/.test(domicilio_padre)) {
                    //alert('ERROR: El campo razon social no debe ir vacío.');
                    document.getElementById('DOMICILIOPADRE').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('DOMICILIOPADRE').focus();
                    return false;
                } else {
                    document.getElementById('DOMICILIOPADRE').style.boxShadow = "";
                }
        
                if (localidad_padre == null || localidad_padre.length == 0 || /^\s+$/.test(localidad_padre)) {
                    //alert('ERROR: El campo razon social no debe ir vacío.');
                    document.getElementById('LOCALIDADPADRE').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('LOCALIDADPADRE').focus();
                    return false;
                } else {
                    document.getElementById('LOCALIDADPADRE').style.boxShadow = "";
                }
        
                if (cp_padre == null || cp_padre.length == 0 || isNaN(cp)) {
                    //alert('ERROR: El campo giro no debe ir vacío.');
                    document.getElementById('CPPADRE').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('CPPADRE').focus();
                    return false;
                } else {
                    document.getElementById('CPPADRE').style.boxShadow = "";
                }
        
                if (colonia_padre == null || colonia_padre.length == 0 ||  /^\s+$/.test(colonia_padre)) {
                    //alert('ERROR: El campo giro no debe ir vacío.');
                    document.getElementById('COLONIAPADRE').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('COLONIAPADRE').focus();
                    return false;
                } else {
                    document.getElementById('COLONIAPADRE').style.boxShadow = "";
                }
            }
          

        }
 
         
        return true;

    }

    function cerrar(accion) {

        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
    }
    
    function evento(accion) {

        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
    }

    function evento2(accion) {

    
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }
    
    function guarda(accion) {
       
         if (validarFormulario()) {  
        
             document.formularioPrincipal.action = accion;
             document.formularioPrincipal.submit();   
         }
        

    }

    function Registra(accion, id) {

        document.formularioPrincipal.id_alumno.value = id;
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }

    function RegistraDual(accion, id_alumno, id_historico, curp) {

        document.formularioPrincipal.id_alumno.value = id_alumno;
        document.formularioPrincipal.id_historico.value = id_historico;
        document.formularioPrincipal.curp.value = curp;
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }


    function ActEstatus(accion, historico) {

        document.formularioPrincipal.id_historico.value = historico;
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
    }


    function ConsultaCP(accion) {
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }


    window.onload = function () {/*hace que se cargue la función lo que predetermina que div estará oculto hasta llamar a la función nuevamente*/
        var pos = window.name || 0;
        window.scrollTo(0, pos);
        
        var checkbox=document.getElementById("MISMO_DOMICILIO");
        
        if (checkbox.checked) {
            alert("entro");
            oculta('formularioDomicilio');
        }



    }

    window.onunload = function () {
        window.name = self.pageYOffset
                || (document.documentElement.scrollTop + document.body.scrollTop);


    }


    function validarInput(input) {
        var curp = input.value.toUpperCase(),
                resultado = document.getElementById("resultado"),
                valido = "No válido";
        div = document.getElementById('btnvalidar');
        div.style.display = 'none';

        if (curpValida(curp)) {
            valido = "Válido";
            resultado.classList.add("ok");

            div = document.getElementById('btnvalidar');
            div.style.display = '';
        } else {
            resultado.classList.remove("ok");
        }

        resultado.innerText = "\nFormato de CURP: " + valido;
    }



    function curpValida(curp) {
        var re = /^([A-Z][AEIOUX][A-Z]{2}\d{2}(?:0\d|1[0-2])(?:[0-2]\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\d])(\d)$/,
                validado = curp.match(re);

        if (!validado)  //Coincide con el formato general?
            return false;

        //Validar que coincida el dígito verificador
        function digitoVerificador(curp17) {
            //Fuente https://consultas.curp.gob.mx/CurpSP/
            var diccionario = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ",
                    lngSuma = 0.0,
                    lngDigito = 0.0;
            for (var i = 0; i < 17; i++)
                lngSuma = lngSuma + diccionario.indexOf(curp17.charAt(i)) * (18 - i);
            lngDigito = 10 - lngSuma % 10;
            if (lngDigito == 10)
                return 0;
            return lngDigito;
        }
        if (validado[2] != digitoVerificador(validado[1]))
            return false;

        return true; //Validado
    }

   

</script>

<script>
     function oculta(elemento) {
        ///// capturamos el elemento
    item=$("#"+elemento);
       ///// verificamos su estado
    if($(item).hasClass('visible')) {
        $(item).removeClass('visible');
                //// cambiamos su estado
        $(item).addClass('invisible');
                //// animamos
        $(item).slideUp('fast');
    } else {
        $(item).removeClass('invisible');
        $(item).addClass('visible');
        $(item).slideDown('fast');
    }
}
</script>    


 

<html xmlns="http://www.w3.org/1999/xhtml">


    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
            <link rel="icon" type="image/png" href="assets/img/favicon.png">
                <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
                <title>
                    Educación DUAL
                </title>
                <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
                <!--     Fonts and icons     -->
                <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
                    <!-- CSS Files -->
                    <link href="assets/css/material-dashboard.css?v=2.1.0" rel="stylesheet" />
                    <!-- CSS Just for demo purpose, don't include it in your project -->
                    <link href="assets/demo/demo.css" rel="stylesheet" />
                    </head>

                    <body class="">
                        <s:form name="formularioPrincipal" id="formularioPrincipal" enctype="multipart/form-data">
                            <div class="wrapper ">
                                <div class="sidebar" data-color="rose" data-background-color="black" data-image="assets/img/sidebar-1.jpg">
                                    <!--
                                      Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"
                              
                                      Tip 2: you can also add an image using data-image tag
                                    -->
                                    <div class="logo">


                                        <a href="#" class="simple-text logo-normal" style="text-align: center;">
                                            Estado de México
                                        </a>
                                    </div>
                                    <div class="sidebar-wrapper">
                                        <div class="user">
                                            <div class="photo">
                                                <img src="assets/img/faces/m.png" />
                                            </div>
                                            <div class="user-info">
                                                <a data-toggle="collapse" href="#collapseExample" class="username">
                                                    <span>
                                                        <s:property value="usuariocons.USUARIO"></s:property>
                                                            <!--  <b class="caret"></b> -->
                                                        </span>
                                                    </a>
                                                    <div class="collapse" id="collapseExample">
                                                        <!-- <ul class="nav">
                                                           <li class="nav-item">
                                                             <a class="nav-link" href="#">
                                                               <span class="sidebar-mini"> MP </span>
                                                               <span class="sidebar-normal"> My Profile </span>
                                                             </a>
                                                           </li>
                                                           <li class="nav-item">
                                                             <a class="nav-link" href="#">
                                                               <span class="sidebar-mini"> EP </span>
                                                               <span class="sidebar-normal"> Edit Profile </span>
                                                             </a>
                                                           </li>
                                                           <li class="nav-item">
                                                             <a class="nav-link" href="#">
                                                               <span class="sidebar-mini"> S </span>
                                                               <span class="sidebar-normal"> Settings </span>
                                                             </a>
                                                           </li>
                                                         </ul> -->
                                                    </div>
                                                </div>
                                            </div>















                                            <ul class="nav">

                                            <jsp:include page="menu.jsp"></jsp:include>	


                                            </ul>



                                        </div>
                                    </div>
                                    <div class="main-panel">
                                        <!-- Navbar -->
                                        <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
                                            <div class="container-fluid">
                                                <div class="navbar-wrapper">

                                                    <a class="navbar-brand" href="#pablo">Educación DUAL</a>
                                                </div>
                                                <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                                                    <span class="sr-only">Toggle navigation</span>
                                                    <span class="navbar-toggler-icon icon-bar"></span>
                                                    <span class="navbar-toggler-icon icon-bar"></span>
                                                    <span class="navbar-toggler-icon icon-bar"></span>
                                                </button>
                                                <div class="collapse navbar-collapse justify-content-end">

                                                    <ul class="navbar-nav">


                                                        <li class="nav-item dropdown">
                                                            <a class="nav-link" href="#pablo" id="navbarDropdownProfile" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                                <i class="material-icons">person</i>
                                                                <p class="d-lg-none d-md-block">
                                                                    Account
                                                                </p>
                                                            </a>
                                                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownProfile">

                                                                <!-- <div class="dropdown-divider"></div>-->
                                                                <a class="dropdown-item" href="Javascript:cerrar('CerrarSesion')">Cerrar Sesión</a>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </nav>
                                        <!-- End Navbar -->
                                        <div class="content">
                                            <div class="row" style="margin-top: -40px;">
                                                <div class="col-md-12">
                                                    <div class="card">
                                                        <div class="card-header card-header-icon card-header-rose">
                                                            <div class="card-icon">
                                                                <i class="material-icons">perm_identity</i>
                                                            </div>
                                                            <h4 class="card-title">Perfil: 
                                                                <small class="category"><s:property value="usuariocons.NAMEPERFIL"></s:property></small>
                                                            </h4>



                                                        </div>
                                                        <div class="card-body">

                                                            <div class="row">
                                                                <div class="col-md-12" style="margin-top: 20px;">
                                                                    <div style="height: 50px; width: 100%; background: #302f2f; color: white; text-align: center; line-height: 0.5; padding: 10px 0px 0px 0px;"> <p></p> <p> <s:property value="usuariocons.NAMEUSUARIO"></s:property></p></div>
                                                                </div>
                                                                <div style="width: 90%; margin: auto;  color:#302f2f; text-align: center; margin-top: 20px;" >
                                                                    <h3> Alumnos Aceptados por la UE </h3>
                                                                </div>                                                                                                                                   
                                                                <div class="card-body">                                                          
                                                                    <div class="material-datatables">                                                                                                                                                              
                                                                        <div style="height: 250px; overflow-y: scroll;">
                                                                            <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                                                                                <thead style="background: #302f2f; color:white;">
                                                                                    <tr>
                                                                                        <th>Curp</th>
                                                                                        <th>Nombre</th>
                                                                                        <th>Matrícula</th>
                                                                                        <th>Programa Educativo</th>                                                                                                  
                                                                                        <th>Periodo que cursa</th>
                                                                                        <th>Estatus</th>
                                                                                        <th>Registrar Estudiante DUAL</th>
                                                                                    </tr>
                                                                                </thead>

                                                                                <tbody>
                                                                                <s:iterator value="ListaAceptados" id="ListaAceptados" status="stat"> 
                                                                                    <s:if test="STATUS_PROCESO==4 || STATUS_PROCESO==5" >
                                                                                    <tr>
                                                                                        <td><s:property value="CURP"/></td>
                                                                                        <td><s:property value="NOMBRE_COMPLETO"/></td>
                                                                                        <td><s:property value="MATRICULA"/></td>
                                                                                        <td><s:property value="NOM_PRO_EDU"/></td> 
                                                                                        <td><s:property value="GRADO"/></td>
                                                                                        <s:if test="STATUS_PROCESO==4">
                                                                                            <td class="text-warning text-center font-weight-bold"><i class="material-icons">sentiment_satisfied</i>Aceptado</td>
                                                                                        </s:if>
                                                                                        <s:if test="STATUS_PROCESO==5">
                                                                                            <td class="text-success text-center font-weight-bold"><i class=" material-icons">sentiment_very_satisfied</i>Alumno DUAL </td>
                                                                                        </s:if>

                                                                                        <td class="text-center"><s:if test="STATUS_PROCESO==4">
                                                                                                <a href="Javascript:RegistraDual('MuestraFormE','<s:property value="ID_ALUMNO"/>','<s:property value="ID_HISTORICO"/>','<s:property value="FEC_NAC"/>' )" ><i class="material-icons text-success">add_circle</i>
                                                                                                </a>
                                                                                            </s:if>
                                                                                            <s:if test="STATUS_PROCESO==5">
                                                                                                <a href="Javascript:ActEstatus('ActualizaStatusA','<s:property value="ID_HISTORICO"/>')" ><i class="material-icons">check_box</i>
                                                                                                </a>
                                                                                            </s:if>
                                                                                        </td>

                                                                                    </tr>
                                                                                    </s:if>    
                                                                                    <s:hidden  name = "ListaAceptados[%{#stat.index}].ID_HISTORICO" id="ID_HISTORICO"></s:hidden>    
                                                                                    <s:hidden  name = "ListaAceptados[%{#stat.index}].ID_ALUMNO" id="ID_ALUMNO"></s:hidden>
                                                                                    <s:hidden  name = "ListaAceptados[%{#stat.index}].CURP" id="CURP"></s:hidden>
                                                                                    <s:hidden  name = "ListaAceptados[%{#stat.index}].NOMBRE_COMPLETO" id="NOMBRE_COMPLETO"></s:hidden>
                                                                                    <s:hidden  name = "ListaAceptados[%{#stat.index}].FEC_NAC" id="FEC_NAC"></s:hidden>
                                                                                    <s:hidden  name = "ListaAceptados[%{#stat.index}].MATRICULA" id="MATRICULA"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].NOM_PRO_EDU" id="NOM_PRO_EDU"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].GRADO" id="GRADO"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].NO_SEGURO" id="NO_SEGURO"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].DOMICILIO" id="DOMICILIO"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].COLONIA" id="COLONIA"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].LOCALIDAD" id="LOCALIDAD"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].CP" id="CP"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].CVE_MUN" id="CVE_MUN"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].FECHA_INICIO_DUAL" id="FECHA_INICIO_DUAL"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].TIPO_ALUMNO" id="TIPO_ALUMNO"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].CURP_PADRE" id="CURP_PADRE"></s:hidden>                
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].NOM_PADRE" id="NOM_PADRE"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].APELLIDOP_PADRE" id="APELLIDOP_PADRE"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].APELLIDOM_PADRE" id="APELLIDOM_PADRE"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].TEL_PADRE" id="TEL_PADRE"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].EMAIL_PADRE" id="EMAIL_PADRE"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].DOMICILIO_PADRE" id="DOMICILIO_PADRE"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].COLONIA_PADRE" id="COLONIA_PADRE"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].LOCALIDAD_PADRE" id="LOCALIDAD_PADRE"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].CP_PADRE" id="CP_PADRE"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].CVE_MUN_PADRE" id="CVE_MUN_PADRE"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].MISMO_DOMICILIO" id="MISMO_DOMCILIO"></s:hidden>
                                                                                    <s:hidden name =  "ListaAceptados[%{#stat.index}].STATUS_PROCESO" id="STATUS_PROCESO"></s:hidden>


                                                                                </s:iterator>  



                                                                            </tbody>
                                                                        </table>                                              
                                                                    </div> 
                                                                </div>
                                                            </div>

                                                        </div>
                                                        <div class="row">
                                                            <s:if test="banMuestraFormDUAL">
                                                                <s:hidden name="banMuestraFormDUAL" id="%{banMuestraFormDUAL}"></s:hidden>
                                                                    <div class="card">
                                                                        <div class="card-header card-header-primary card-header-icon">
                                                                            <div class="card-icon">
                                                                                <i class="material-icons">how_to_reg</i>
                                                                            </div>
                                                                            <h4 class="card-title">Registro de alumno DUAL</h4>
                                                                        </div>
                                                                        <div class="card-body">
                                                                            <div class="row">
                                                                                <br></br>
                                                                                <br></br>
                                                                                <div class="col-lg-12">
                                                                                    <p class="font-weight-bold">Si desea Registrar al alumno: <span class="text-primary"><s:property value="al.NOMBRE_COMPLETO"></s:property></span> , con matrícula <span class="text-primary"><s:property value="al.MATRICULA"/></span> como estudiante DUAL, por favor llene los siguientes datos y presione Registrar</p>
                                                                                <s:hidden name="al.NOMBRE_COMPLETO" id="%{al.NOMBRE_COMPLETO}"></s:hidden>
                                                                                <s:hidden name="al.MATRICULA" id="%{al.MATRICULA}"></s:hidden>
                                                                                </div>
                                                                                <br></br>
                                                                                <br></br>
                                                                                <div class="col-lg-4 ">
                                                                                    <label   for="RAZON">No. de seguro social</label>
                                                                                <s:textfield  cssClass="form-control" name="al.NO_SEGURO" id="NOSEGURO"  ></s:textfield>                                                                                               
                                                                                </div> 
                                                                                <div class="col-lg-4 ">
                                                                                    <label   for="RAZON">Domicilio</label>
                                                                                <s:textfield  cssClass="form-control" name="al.DOMICILIO" id="DOM"  ></s:textfield>                                                                                               
                                                                                </div> 
                                                                                <div class="col-lg-4 ">
                                                                                    <label   for="RAZON">Localidad</label>
                                                                                <s:textfield  cssClass="form-control" name="al.LOCALIDAD" id="LOC"  ></s:textfield>                                                                                               
                                                                                </div>
                                                                                <div class="col-lg-4 ">
                                                                                    <label   for="RAZON">Codigo Postal</label>
                                                                                <s:textfield  cssClass="form-control" name="al.CP" id="CPA" onchange="Javascript:ConsultaCP('ConsultaCPE')" ></s:textfield>   
                                                                                <s:fielderror fieldName="NoCP" cssClass="alert alert-danger"></s:fielderror>
                                                                                <s:fielderror fieldName="CP" cssClass="alert alert-danger"></s:fielderror>
                                                                                </div>                                    
                                                                                <div class="col-lg-4">
                                                                                    <label for="examplePass" class="bmd-label-floating">Colonia</label>
                                                                                <s:select  data-style="select-with-transition"   name="al.COLONIA" id="COL" list="ListaColonia"  listKey="COLONIA_AUX"  listValue="COLONIA_AUX" headerKey="" headerValue="--SELECCIONE--"  cssClass="selectpicker col-lg-12"  />
                                                                                <s:fielderror fieldName="ErrorProomedio" cssClass="alert alert-danger"/>
                                                                                <s:iterator value="ListaColonia" id="ListaColonia" status="stat">                        
                                                                                    <s:hidden  name = "ListaColonia[%{#stat.index}].COLONIA_AUX" id="COLONIA_AUX"></s:hidden>
                                                                                </s:iterator>

                                                                            </div>
                                                                            <div class="col-lg-4 ">
                                                                                <label   for="RAZON">Municipio</label>
                                                                                <s:textfield  cssClass="form-control" name="al.MUNICIPIO" id="MUN"  readonly="true"></s:textfield>                                                                                               
                                                                                </div>  
                                                                                <div class="col-lg-4">
                                                                                    <label  for="Domicilio">Fecha de Inicio DUAL:</label>
                                                                                <s:textfield  cssClass="form-control datepicker" name="al.FECHA_INICIO_DUAL" id="FECHAINICIODUAL" ></s:textfield>
                                                                                <s:fielderror fieldName="ErrorFecha" cssClass="alert alert-danger"/>
                                                                                <s:fielderror fieldName="ErrorFechaNoPermitida" cssClass="alert alert-danger"/>
                                                                            </div> 
                                                                            <div class="col-lg-4 ">
                                                                                <label   for="RAZON">Tipo de alumno</label>
                                                                                <s:textfield  cssClass="form-control" name="al.TIPO_ALUMNO" id="TIPO_ALUMNO" readonly="true" ></s:textfield>                                                                                               
                                                                                </div>  


                                                                            <s:if test="banFormPad">
                                                                                <s:hidden name="banFormPad" id="%{banFormPad}"></s:hidden>
                                                                                <div class="col-lg-12" >&nbsp;</div>  
                                                                                <div class="col-lg-12" >&nbsp;</div>  
                                                                                <div class="card-header card-header-primary card-header-icon col-lg-12">
                                                                                    <div class="card-icon">
                                                                                        <i class="material-icons">person</i>
                                                                                    </div>
                                                                                    <h4 class="card-title">Registrar datos del padre o tutor</h4>
                                                                                </div> 
                                                                                <div class="col-lg-12" >&nbsp;</div> 

                                                                                <div class="form-group col-md-4">
                                                                                    <label for="exampleEmail" class="bmd-label-floating">CURP del padre o tutor</label>
                                                                                    <s:textfield  cssClass="form-control " name="al.CURP_PADRE_AUX" id="CURP_PADRE_AUX" oninput="validarInput(this)"/>
                                                                                    <s:fielderror fieldName="NOMA" id="NOMA" cssClass="alert alert-danger"></s:fielderror>

                                                                                    </div>                              
                                                                                    <div class="col-md-12">
                                                                                        <pre id="resultado" > </pre>
                                                                                    </div>
                                                                                
                                                                                <div id="btnvalidar" style="display: none;">

                                                                                   

                                                                                    <div class="col-md-12"  >
                                                                                        <a href="Javascript:evento('consultaCurpP')" class="btn btn-success" >Consultar CURP</a>
                                                                                    </div>

                                                                                    

                                                                                </div>
                                                                                    
                                                                                    <s:if test="banMuestraCurpP">   
                                                                                        <s:hidden name="banMuestraCurpP" id="%{banMuestraCurpP}"></s:hidden>
                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">CURP del padre</label>

                                                                                    <s:textfield  cssClass="form-control " name="al.CURP_PADRE" id="CURPPADRE" readonly="true"/>
                                                                                    <s:fielderror fieldName="NOMA" id="NOMA" cssClass="alert alert-danger"></s:fielderror>

                                                                                    </div>    
                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Nombre del padre</label>

                                                                                    <s:textfield  cssClass="form-control " name="al.NOM_PADRE" id="NOM_PADRE" readonly="true"/>
                                                                                    <s:fielderror fieldName="NOMA" id="NOMA" cssClass="alert alert-danger"></s:fielderror>

                                                                                    </div>
                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="examplePass" class="bmd-label-floating">Apellido Paterno del padre</label>
                                                                                    <s:textfield cssClass="form-control" name="al.APELLIDOP_PADRE" id="APELLIDOP_PADRE" readonly="true"/>
                                                                                    <s:fielderror fieldName="APA" cssClass="alert alert-danger"></s:fielderror>
                                                                                    </div>
                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="examplePass" class="bmd-label-floating">Apellido Materno del padre</label>
                                                                                    <s:textfield cssClass="form-control" name="al.APELLIDOM_PADRE" id="APELLIDOM_PADRE" readonly="true"/>
                                                                                    <s:fielderror fieldName="AMA" cssClass="alert alert-danger"></s:fielderror>
                                                                                    </div>
                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="examplePass" class="bmd-label-floating">Teléfono del padre</label>
                                                                                    <s:textfield cssClass="form-control" name="al.TEL_PADRE" id="TELPADRE" />
                                                                                    <s:fielderror fieldName="AMA" cssClass="alert alert-danger"></s:fielderror>
                                                                                    </div>
                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="examplePass" class="bmd-label-floating">Email del padre</label>
                                                                                    <s:textfield cssClass="form-control" name="al.EMAIL_PADRE" id="EMAILPADRE" />
                                                                                    <s:fielderror fieldName="AMA" cssClass="alert alert-danger"></s:fielderror>
                                                                                    </div>
                                                                                    <br></br>
                                                                                    <br></br>
                                                                                    <div class="form-inline col-md-12">
                                                                                        <p class="font-weight-bold ">Marque la casilla si el Domicilio del padre es el mismo que el del alumno: <s:checkbox cssClass="form-check-input" name="al.MISMO_DOMICILIO" id="MISMO_DOMICILIO"  type="checkbox"  onclick="javascript:oculta('formularioDomicilio')">  </s:checkbox></p>                                                                                     
                                                                                    </div> 
                                                                                    
                                                                                    <div class="col-lg-12 visible" id="formularioDomicilio" >
                                                                                        
                                                                                        <div class="row">
                                                                                        <div class=" col-lg-4  ">
                                                                                            <label   for="RAZON">Domicilio</label>
                                                                                        <s:textfield  cssClass="form-control" name="al.DOMICILIO_PADRE" id="DOMICILIOPADRE"  ></s:textfield>                                                                                               
                                                                                        </div> 
                                                                                        <div class=" col-lg-4">
                                                                                            <label   for="RAZON">Localidad</label>
                                                                                        <s:textfield  cssClass="form-control" name="al.LOCALIDAD_PADRE" id="LOCALIDADPADRE"  ></s:textfield>                                                                                               
                                                                                        </div>
                                                                                        <div class="col-lg-4 ">
                                                                                            <label   for="RAZON">Codigo Postal</label>
                                                                                        <s:textfield  cssClass="form-control" name="al.CP_PADRE" id="CPPADRE" onchange="Javascript:ConsultaCP('ConsultaCPP')" ></s:textfield>   
                                                                                        <s:fielderror fieldName="NoCPP" cssClass="alert alert-danger"></s:fielderror>
                                                                                        <s:fielderror fieldName="CP" cssClass="alert alert-danger"></s:fielderror>
                                                                                        </div>                                    
                                                                                        <div class="col-lg-4 ">
                                                                                            <label for="examplePass" class="bmd-label-floating">Colonia</label>
                                                                                        <s:select  data-style="select-with-transition"   name="al.COLONIA_PADRE" id="COLONIAPADRE" list="ListaColoniaP"  listKey="COLONIA_AUX"  listValue="COLONIA_AUX" headerKey="" headerValue="--SELECCIONE--"  cssClass="selectpicker col-lg-12"  />
                                                                                        <s:fielderror fieldName="ErrorProomedio" cssClass="alert alert-danger"/>
                                                                                        <s:iterator value="ListaColoniaP" id="ListaColoniaP" status="stat">                        
                                                                                            <s:hidden  name = "ListaColoniaP[%{#stat.index}].COLONIA_AUX" id="COLONIA_AUX"></s:hidden>
                                                                                        </s:iterator>

                                                                                    </div>
                                                                                    <div class="col-lg-4 ">
                                                                                        <label   for="RAZON">Municipio</label>
                                                                                        <s:textfield  cssClass="form-control" name="al.MUNICIPIO_PADRE" id="MUNICIPIOPADRE"  readonly="true"></s:textfield>                                                                                               
                                                                                        </div> 


                                                                                    </div>
                                                                                   </div>
                                                                                   </s:if>     
                                                                            </s:if>         

                                                                            &nbsp;
                                                                            &nbsp;

                                                                            
                                                                        </div>             

                                                                        &nbsp;
                                                                        &nbsp;
                                                                        <div class=" col-md-12 text-center">
                                                                            <a class="btn btn-round btn-rose"  href="Javascript:guarda('RegistraDual')">Registrar Alumno</a>
                                                                        </div>   


                                                                    </div>
                                                                    <!-- end content-->
                                                                </div>
                                                            </s:if>

                                                        </div>        

                                                    </div>                                                          
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <footer class="footer">
                                        <div class="container-fluid">
                                            <nav class="float-left">
                                                <ul>

                                                    <!-- <li>
                                                       <a href="https://www.creative-tim.com/license">
                                                         Licenses
                                                       </a>
                                                     </li> -->
                                                </ul>
                                            </nav>
                                            <div class="copyright float-center">
                                                <i class="material-icons"></i> Gobierno del Estado de México.


                                                <s:textfield name="al.ID_ESCUELA" id="al.ID_ESCUELA" cssStyle="display:none;"></s:textfield>
                                                <s:textfield name="al.ID_ALUMNO" id="id_alumno" cssStyle="display:none;" ></s:textfield>
                                                <s:textfield name="al.ID_HISTORICO" id="id_historico" cssStyle="display:none;"></s:textfield> 
                                                <s:textfield name="al.CVE_MUN" id="CVE_MUN"  cssStyle="display:none;"></s:textfield>  
                                                <s:textfield name="al.CVE_MUN_PADRE" id="CVE_MUN_PADRE" cssStyle="display:none;" ></s:textfield>  
                                                <s:textfield name="al.FEC_NAC" id="curp"  cssStyle="display:none;"></s:textfield>  
                                                <s:textfield name="al.EDAD" id="EDAD"  cssStyle="display:none;"></s:textfield>  

                                                <s:hidden name="bnprog" value="%{bnprog}"></s:hidden>


                                                </div>
                                            </div>
                                        </footer>
                                    </div>        
                                </div>


                                <!--   Core JS Files   -->
                                <script src="assets/js/core/jquery.min.js"></script>
                                <script src="assets/js/core/popper.min.js"></script>
                                <script src="assets/js/core/bootstrap-material-design.min.js"></script>
                                <script src="assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
                                <!-- Plugin for the momentJs  -->
                                <script src="assets/js/plugins/moment.min.js"></script>
                                <!--  Plugin for Sweet Alert -->
                                <script src="assets/js/plugins/sweetalert2.js"></script>
                                <!-- Forms Validations Plugin -->
                                <script src="assets/js/plugins/jquery.validate.min.js"></script>
                                <!-- Plugin for the Wizard, full documentation here: https://github.com/VinceG/twitter-bootstrap-wizard -->
                                <script src="assets/js/plugins/jquery.bootstrap-wizard.js"></script>
                                <!--	Plugin for Select, full documentation here: http://silviomoreto.github.io/bootstrap-select -->
                                <script src="assets/js/plugins/bootstrap-selectpicker.js"></script>
                                <!--  Plugin for the DateTimePicker, full documentation here: https://eonasdan.github.io/bootstrap-datetimepicker/ -->
                                <script src="assets/js/plugins/bootstrap-datetimepicker.min.js"></script>
                                <!--  DataTables.net Plugin, full documentation here: https://datatables.net/  -->
                                <script src="assets/js/plugins/jquery.dataTables.min.js"></script>
                                <!--	Plugin for Tags, full documentation here: https://github.com/bootstrap-tagsinput/bootstrap-tagsinputs  -->
                                <script src="assets/js/plugins/bootstrap-tagsinput.js"></script>
                                <!-- Plugin for Fileupload, full documentation here: http://www.jasny.net/bootstrap/javascript/#fileinput -->
                                <script src="assets/js/plugins/jasny-bootstrap.min.js"></script>
                                <!--  Full Calendar Plugin, full documentation here: https://github.com/fullcalendar/fullcalendar    -->
                                <script src="assets/js/plugins/fullcalendar.min.js"></script>
                                <!-- Vector Map plugin, full documentation here: http://jvectormap.com/documentation/ -->
                                <script src="assets/js/plugins/jquery-jvectormap.js"></script>
                                <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
                                <script src="assets/js/plugins/nouislider.min.js"></script>
                                <!-- Include a polyfill for ES6 Promises (optional) for IE11, UC Browser and Android browser support SweetAlert -->
                                <script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
                                <!-- Library for adding dinamically elements -->
                                <script src="assets/js/plugins/arrive.min.js"></script>
                                <!--  Google Maps Plugin    -->
                                <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
                                <!-- Chartist JS -->
                                <script src="assets/js/plugins/chartist.min.js"></script>
                                <!--  Notifications Plugin    -->
                                <script src="assets/js/plugins/bootstrap-notify.js"></script>
                                <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
                                <script src="assets/js/material-dashboard.js?v=2.1.0" type="text/javascript"></script>
                                <!-- Material Dashboard DEMO methods, don't include it in your project! -->
                                <script src="assets/demo/demo.js"></script>
                                <script>
    $(document).ready(function () {
        $().ready(function () {
            $sidebar = $('.sidebar');

            $sidebar_img_container = $sidebar.find('.sidebar-background');

            $full_page = $('.full-page');

            $sidebar_responsive = $('body > .navbar-collapse');

            window_width = $(window).width();

            fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();

            if (window_width > 767 && fixed_plugin_open == 'Dashboard') {
                if ($('.fixed-plugin .dropdown').hasClass('show-dropdown')) {
                    $('.fixed-plugin .dropdown').addClass('open');
                }

            }

            $('.fixed-plugin a').click(function (event) {
                // Alex if we click on switch, stop propagation of the event, so the dropdown will not be hide, otherwise we set the  section active
                if ($(this).hasClass('switch-trigger')) {
                    if (event.stopPropagation) {
                        event.stopPropagation();
                    } else if (window.event) {
                        window.event.cancelBubble = true;
                    }
                }
            });

            $('.fixed-plugin .active-color span').click(function () {
                $full_page_background = $('.full-page-background');

                $(this).siblings().removeClass('active');
                $(this).addClass('active');

                var new_color = $(this).data('color');

                if ($sidebar.length != 0) {
                    $sidebar.attr('data-color', new_color);
                }

                if ($full_page.length != 0) {
                    $full_page.attr('filter-color', new_color);
                }

                if ($sidebar_responsive.length != 0) {
                    $sidebar_responsive.attr('data-color', new_color);
                }
            });

            $('.fixed-plugin .background-color .badge').click(function () {
                $(this).siblings().removeClass('active');
                $(this).addClass('active');

                var new_color = $(this).data('background-color');

                if ($sidebar.length != 0) {
                    $sidebar.attr('data-background-color', new_color);
                }
            });

            $('.fixed-plugin .img-holder').click(function () {
                $full_page_background = $('.full-page-background');

                $(this).parent('li').siblings().removeClass('active');
                $(this).parent('li').addClass('active');


                var new_image = $(this).find("img").attr('src');

                if ($sidebar_img_container.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
                    $sidebar_img_container.fadeOut('fast', function () {
                        $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
                        $sidebar_img_container.fadeIn('fast');
                    });
                }

                if ($full_page_background.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
                    var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

                    $full_page_background.fadeOut('fast', function () {
                        $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
                        $full_page_background.fadeIn('fast');
                    });
                }

                if ($('.switch-sidebar-image input:checked').length == 0) {
                    var new_image = $('.fixed-plugin li.active .img-holder').find("img").attr('src');
                    var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

                    $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
                    $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
                }

                if ($sidebar_responsive.length != 0) {
                    $sidebar_responsive.css('background-image', 'url("' + new_image + '")');
                }
            });

            $('.switch-sidebar-image input').change(function () {
                $full_page_background = $('.full-page-background');

                $input = $(this);

                if ($input.is(':checked')) {
                    if ($sidebar_img_container.length != 0) {
                        $sidebar_img_container.fadeIn('fast');
                        $sidebar.attr('data-image', '#');
                    }

                    if ($full_page_background.length != 0) {
                        $full_page_background.fadeIn('fast');
                        $full_page.attr('data-image', '#');
                    }

                    background_image = true;
                } else {
                    if ($sidebar_img_container.length != 0) {
                        $sidebar.removeAttr('data-image');
                        $sidebar_img_container.fadeOut('fast');
                    }

                    if ($full_page_background.length != 0) {
                        $full_page.removeAttr('data-image', '#');
                        $full_page_background.fadeOut('fast');
                    }

                    background_image = false;
                }
            });

            $('.switch-sidebar-mini input').change(function () {
                $body = $('body');

                $input = $(this);

                if (md.misc.sidebar_mini_active == true) {
                    $('body').removeClass('sidebar-mini');
                    md.misc.sidebar_mini_active = false;

                    $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar();

                } else {

                    $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar('destroy');

                    setTimeout(function () {
                        $('body').addClass('sidebar-mini');

                        md.misc.sidebar_mini_active = true;
                    }, 300);
                }

                // we simulate the window Resize so the charts will get updated in realtime.
                var simulateWindowResize = setInterval(function () {
                    window.dispatchEvent(new Event('resize'));
                }, 180);

                // we stop the simulation of Window Resize after the animations are completed
                setTimeout(function () {
                    clearInterval(simulateWindowResize);
                }, 1000);

            });
        });
    });
                                </script>
                                <script>
                                    $(document).ready(function () {
                                        md.checkFullPageBackgroundImage();
                                    });
                                </script>

                                <script>
                                    $(document).ready(function () {
                                        // initialise Datetimepicker and Sliders
                                        md.initFormExtendedDatetimepickers();

                                    });
                                </script>




                            </body>




                    </s:form>
                    </html>
