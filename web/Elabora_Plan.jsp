<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>



 <script>
     
    function telquita() {
  var x = document.getElementById("quitamen").style="display:none;";
  
}
 function telquita1() {
  var x = document.getElementById("quitamen1").style="display:none;";
  
}

 function telquita2() {
  var x = document.getElementById("quitamen2").style="display:none;";
  
}

 function telquita3() {
  var x = document.getElementById("quitamen3").style="display:none;";
  
}

 function telquita4() {
  var x = document.getElementById("quitamen4").style="display:none;";
  
}

 function telquita5() {
  var x = document.getElementById("quitamen5").style="display:none;";
  
}

 function telquita6() {
  var x = document.getElementById("quitamen6").style="display:none;";
  
}

 function telquita7() {
  var x = document.getElementById("quitamen7").style="display:none;";
  
}

 function telquita8() {
  var x = document.getElementById("quitamen8").style="display:none;";
  
}

     
 function telquita9() {
  var x = document.getElementById("quitamen9").style="display:none;";
  
}
    
     
     
     
     
     
     
     
     
     
  function validarSiNumero(numero){
    if (!/^\-?[1-9]\d{0,2}$/.test(numero))
     
    
     
     document.getElementById('NO_ESTUDIANTES2').value="";
     
  }
  
  function validarSiNumero2(numero){
    if (!/^\-?[1-9]\d{0,2}$/.test(numero))
     
    
     
     document.getElementById('NO_MENTORES_UE2').value="";
     
  }
  
  function validarSiNumero3(numero){
    if (!/^\-?[1-9]\d{0,2}$/.test(numero))
     
    
     
     document.getElementById('NO_MENTORES_ACAD2').value="";
     
  }
  function validarSiNumero4(numero){
    if (/^\-?[1-9]\d{0,2}$/.test(numero) ){
     
     if(parseInt(numero)>40 || parseInt(numero)==0){
         
         alert("el númeor de horas debe ser mayor a 0 y menor o igual a 40");
         document.getElementById('HORAS_SEMANA').value="";
     }
     
    
 }
 else{
      document.getElementById('HORAS_SEMANA').value="";
     
 }
 
 
 
  }
  
</script>
    
     

<script type="text/javascript">
    
    
   
    
    

    function validarFormulario() {



        var no_seguro = document.getElementById('NOSEGURO').value;

        var domicilio = document.getElementById('DOM').value;

        var localidad = document.getElementById('LOC').value;

        var cp = document.getElementById('CPA').value;

        var colonia = document.getElementById('COL').value;

        var fecha_inicio_dual = document.getElementById('FECHAINICIODUAL').value;

        var edad = parseInt(document.getElementById('EDAD').value);






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

        if (colonia == null || colonia.length == 0 || /^\s+$/.test(colonia)) {
            //alert('ERROR: El campo giro no debe ir vacío.');
            document.getElementById('COL').style.boxShadow = "0px 1px 5px red";
            document.getElementById('COL').focus();
            return false;
        } else {
            document.getElementById('COL').style.boxShadow = "";
        }

        if (fecha_inicio_dual == null || fecha_inicio_dual.length == 0 || /^\s+$/.test(fecha_inicio_dual)) {
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

            if (!mismo_domicilio.checked) {

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

                if (colonia_padre == null || colonia_padre.length == 0 || /^\s+$/.test(colonia_padre)) {
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

    function consulta(accion) {

        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
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


    window.onload = function () {/*hace que se cargue la función lo que predetermina que div estará oculto hasta llamar a la función nuevamente*/
        var pos = window.name || 0;
        window.scrollTo(0, pos);

        var checkbox = document.getElementById("MISMO_DOMICILIO");

        if (checkbox.checked) {
            alert("entro");
            oculta('formularioDomicilio');
        }



    }

    window.onunload = function () {
        window.name = self.pageYOffset
                || (document.documentElement.scrollTop + document.body.scrollTop);


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
                                                                    <h3> Elaboración del plan de formación</h3>
                                                                </div>                                                                                                                                   
                                                                <div class="card-body">  

                                                                    <div class="row" >
                                                                        <div class="col-lg-12">
                                                                            <label for="exampleEmail" class="bmd-label-floating">Programa Educativo: </label>
                                                                        <s:select  data-style="select-with-transition"   name="escuela.AUXIDCCTPLAN" id="escuela.AUXIDCCTPLAN" list="ListaPE"  listKey="ID_PE+-+PERIODO_INICIO_DUAL"  listValue="NOM_CARRERA" headerKey="" headerValue="Seleccione un programa educativo" onchange="Javascript:consulta('planConsulta',PERIODO_INICIO_DUAL)" cssClass="selectpicker col-lg-12" onkeyup="telquita()"  />
                                                                        <s:iterator value="ListaPE" id="ListaPE" status="stat">  
                                                                            <s:hidden  name = "ListaPE[%{#stat.index}].ID_PE" id="ID_PE"></s:hidden>
                                                                            <s:hidden  name = "ListaPE[%{#stat.index}].NOM_CARRERA" id="NOM_CARRERA"></s:hidden>
                                                                            <s:hidden  name = "ListaPE[%{#stat.index}].PERIODO_INICIO_DUAL" id="PERIODO_INICIO_DUAL"></s:hidden>
                                                                        </s:iterator>

                                                                        <s:fielderror  fieldName="ERRORCCTPLA" cssClass="col-lg-12 alert alert-danger" id="quitamen"></s:fielderror>
                                                                        </div>

                                                                           <s:if test="ListaProgramasRegistro.size()>0"> 
                                                                               
                                                                               <div class="col-lg-12" style="margin-top: 30px;">
                                                                            <label for="exampleEmail" class="bmd-label-floating">Unidad Económica </label>
                                                                            <s:select  data-style="select-with-transition"   name="escuela.ID_IE_UE" id="escuela.ID_IE_UE" list="ListaUE"  listKey="ID_IE_UE"  listValue="RAZON_SOCIAL" headerKey="" headerValue="Seleccione Unidad Económica"  cssClass="selectpicker col-lg-12" onchange="telquita1();"  />
                                                                        <s:iterator value="ListaPE" id="ListaPE" status="stat">  
                                                                            <s:hidden  name = "ListaUE[%{#stat.index}].ID_IE_UE" id="ID_IE_UE"></s:hidden>
                                                                            <s:hidden  name = "ListaUE[%{#stat.index}].RFC" id="RFC"></s:hidden>
                                                                            <s:hidden  name = "ListaUE[%{#stat.index}].RAZON_SOCIAL" id="RAZON_SOCIAL"></s:hidden>
                                                                        </s:iterator>

                                                                        <s:fielderror  fieldName="ERROREUE" cssClass="col-lg-12 alert alert-danger" id="quitamen1"></s:fielderror>
                                                                        </div>   
                                                                               
                                                                               
                                                                               
                                                                               

                                                                        <div class="col-lg-8 " style="margin-top: 20px;">
                                                                            <label   for="RAZON">Nombre del plan de formación:</label>
                                                                        <s:textfield  cssClass="form-control" name="programa.NOM_PLAN_FORM" id="nom_planf" onkeyup="telquita2();"  ></s:textfield> 
                                                                        <s:fielderror  fieldName="ERRORNOMPLAN" cssClass="col-lg-12 alert alert-danger" id="quitamen2"></s:fielderror>
                                                                        </div>






                                                                        <div class="col-lg-4" style="margin-top: 20px;">
                                                                            <label for="exampleEmail" class="bmd-label-floating">Duración en Periodos </label>
                                                                        <s:select  data-style="select-with-transition"   name="programa.PERIODO" id="PERIODO" list="ListaPeriodo"  listKey="ID_PERIODO"  listValue="PERIODO" headerKey="" headerValue="Seleccione un periodo"  cssClass="selectpicker col-lg-12" onchange="telquita4()"  />
                                                                        <s:iterator value="ListaPeriodo" id="ListaPeriodo" status="stat">  
                                                                            <s:hidden  name = "ListaPeriodo[%{#stat.index}].ID_PERIODO" id="ID_PERIODO"></s:hidden>
                                                                            <s:hidden  name = "ListaPeriodo[%{#stat.index}].PERIODO" id="PERIODO"></s:hidden>
                                                                        </s:iterator>
                                                                        <s:fielderror  fieldName="ERRORPERPLAN" cssClass="col-lg-12 alert alert-danger" id="quitamen4"></s:fielderror>

                                                                        </div>

                                                                        
                                                                        <div class="col-lg-3" style="margin-top: 20px;">
                                                                            <label   for="RAZON">Número de Estudiantes:</label>
                                                                        <s:textfield  cssClass="form-control" name="programa.NO_ESTUDIANTES" id="NO_ESTUDIANTES2" onkeyup="validarSiNumero(this.value); telquita5(); "    ></s:textfield> 
                                                                        <s:fielderror  fieldName="ERRORNOEST" cssClass="col-lg-12 alert alert-danger" id="quitamen5"></s:fielderror>
                                                                        </div>
                                                             
                                                                        <div class="col-lg-3" style="margin-top: 20px;">
                                                                            <label   for="RAZON">Número de Mentores de UE:</label>
                                                                        <s:textfield  cssClass="form-control" name="programa.NO_MENTORES_UE" id="NO_MENTORES_UE2" onkeyup="validarSiNumero2(this.value); telquita6(); "  ></s:textfield> 
                                                                        <s:fielderror  fieldName="ERRORNOMENUE" cssClass="col-lg-12 alert alert-danger" id="quitamen6"></s:fielderror>
                                                                        </div>
                                                                      
                                                                        <div class="col-lg-3" style="margin-top: 20px;">
                                                                            <label   for="RAZON">Número de Mentores Académcios:</label>
                                                                        <s:textfield  cssClass="form-control" name="programa.NO_MENTORES_ACAD" id="NO_MENTORES_ACAD2" onkeyup="validarSiNumero3(this.value); telquita7(); "  ></s:textfield> 
                                                                        <s:fielderror  fieldName="ERRORNOMENACAD" cssClass="col-lg-12 alert alert-danger" id="quitamen7"></s:fielderror>
                                                                        </div>
                                                                        
                                                                          <div class="col-lg-3" style="margin-top: 20px;">
                                                                            <label   for="RAZON">Horas a la semana</label>
                                                                        <s:textfield  cssClass="form-control" name="programa.HORAS_SEMANA" id="HORAS_SEMANA" onkeyup="validarSiNumero4(this.value); telquita9(); "  ></s:textfield> 
                                                                        <s:fielderror  fieldName="HORASSEMANA" cssClass="col-lg-12 alert alert-danger" id="quitamen9"></s:fielderror>
                                                                        </div>


                                                                        <div class="col-lg-12 " style="margin-top: 20px; margin-bottom: 30px;">
                                                                            <label   for="RAZON">Descripción del Plan de Formación</label>
                                                                        <s:textarea  cssClass="form-control" name="programa.DESCRIPCION_FORM" id="descripcion"  onkeyup="telquita8();" ></s:textarea>  
                                                                        <s:fielderror  fieldName="ERRORDESPLAN" cssClass="col-lg-12 alert alert-danger" id="quitamen8"></s:fielderror>
                                                                        </div>  

                                                                        <div class="col-md-12">


                                                                            <div id="dvData">  





                                                                         




                                                                                <div  style="width: 100%; ">

                                                                                    <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                                                                                        <thead style="background: #302f2f; color:white;">
                                                                                            <tr style="text-align: center;">

                                                                                                <th style="width: 15%;">Asignatura</th>
                                                                                                <th style="width: 5%;" >Periodo</th>
                                                                                                <th style="width: 15%;" >Competencia</th>
                                                                                                <th style="width: 15%;" >Actividad</th>
                                                                                                <th style="width: 5%;">Seleccionar</th>
                                                                                                <th style="width: 10%;" >Horas</th>
                                                                                                <th style="width: 10%;" >Lugar</th>
                                                                                                <th style="width: 10%;" >Escala</th>
                                                                                            <s:if test="programa.ID_NIVEL==1">
                                                                                            <th style="width: 10%;" >Plan de Rotación</th>
                                                                                            </s:if>
                                                                                                <th style="width: 15%;" >Descripcion de Actividad </th>
                                                                                                 <th style="width: 15%;" >Evidencias </th>
                                                                                            </tr>
                                                                                        </thead>

                                                                                        <tbody>
                                                                                            <s:iterator value="ListaProgramasRegistro" id="ListaProgramasRegistro" status="stat">                                                                                                                                      


                                                                            <s:if test="ACTIVIDAD.length()>0">


                                                                                <tr style="color: #666666;    <s:if test="ID_ACTIVIDAD==NO_PASA && NO_PASA!=null">background: #ffcccc ; </s:if>"  >


                                                                                                    

                                                                                                        <th><s:property value="NOMBRE_MATERIA" /></th>
                                                                                                        <th style="text-align: center;" ><s:property value="NUMERO_PERIODO" /></th>
                                                                                                        <th ><s:property value="COMPETENCIA" /></th>
                                                                                                        <th ><s:property value="ACTIVIDAD" /></th>  
                                                                                                        <th align="center" style="align-content: center; text-align: center;"> <s:checkbox cssClass="form-check-sign"   name="ListaProgramasRegistro[%{#stat.index}].VALIDAR"  type="checkbox" id="VALIDAR" >  </s:checkbox>    </th>  
                                                                                                        <th align="center" style="align-content: center;"> <s:select data-style="select-with-transition" cssStyle="width:40px;" name="ListaProgramasRegistro[%{#stat.index}].HORAS_PLAN"   id="HORAS_PLAN" list="ListaHora"  listKey="ID_HORA"  listValue="HORA+'hrs'" cssClass="selectpicker col-lg-12" headerValue="No.Horas" headerKey=""  >  </s:select>   </th>  
                                                                                                        <th align="center" style="align-content: center;"> <s:select data-style="select-with-transition" cssStyle="width:40px;" name="ListaProgramasRegistro[%{#stat.index}].LUGAR_PLAN"   id="LUGAR_PLAN" list="ListaLugar"  listKey="ID_LUGAR"  listValue="LUGAR" cssClass="selectpicker col-lg-12" headerValue="Lugar" headerKey="" >  </s:select>   </th>  
                                                                                                        <th align="center" style="align-content: center;"> <s:select data-style="select-with-transition" cssStyle="width:40px;" name="ListaProgramasRegistro[%{#stat.index}].ESCALA_PLAN"   id="ESCALA_PLAN" list="ListaEscala"  listKey="ID_ESCALA"  listValue="ESCALA+'%'" cssClass="selectpicker col-lg-12" headerValue="Escala" headerKey="" >  </s:select>   </th>  
                                                                                                        
                                                                                                        <s:if test="programa.ID_NIVEL==1">
                                                                                                            <th align="center" style="align-content: center;"> <s:textfield cssClass="form-check-sign"   name="ListaProgramasRegistro[%{#stat.index}].PLAN_ROTACION"    id="PLAN_ROTACION" >  </s:textfield>    </th> 
                                                                                                            </s:if>
                                                                                                            <th align="center" style="align-content: center;"> <s:textarea cssClass="form-check-sign"   name="ListaProgramasRegistro[%{#stat.index}].DES_ACTIVIDAD"    id="DES_ACTIVIDAD" >  </s:textarea>    </th>  
                                                                                                      
                                                                                                         <th align="center" style="align-content: center;"> <s:textfield cssClass="form-check-sign"   name="ListaProgramasRegistro[%{#stat.index}].EVIDENCIAS"    id="EVIDENCIAS" >  </s:textfield>    </th> 



                                                                                                </tr>

                                                                                </s:if>




                                                                                                <s:hidden  name = "ListaProgramasRegistro[%{#stat.index}].NOMBRE_MATERIA" id="NOMBRE_MATERIA"></s:hidden>
                                                                                                <s:hidden  name = "ListaProgramasRegistro[%{#stat.index}].NUMERO_PERIODO" id="NUMERO_PERIODO"></s:hidden>
                                                                                                <s:hidden  name = "ListaProgramasRegistro[%{#stat.index}].COMPETENCIA" id="COMPETENCIA"></s:hidden>
                                                                                                <s:hidden  name = "ListaProgramasRegistro[%{#stat.index}].ACTIVIDAD" id="ACTIVIDAD"></s:hidden>
                                                                                                <s:hidden  name = "ListaProgramasRegistro[%{#stat.index}].ID_MATERIA" id="ID_MATERIA"></s:hidden>
                                                                                                <s:hidden  name = "ListaProgramasRegistro[%{#stat.index}].ID_COMPETENCIA" id="ID_COMPETENCIA"></s:hidden>
                                                                                                <s:hidden  name = "ListaProgramasRegistro[%{#stat.index}].ID_ACTIVIDAD" id="ID_ACTIVIDAD"></s:hidden>
                                                                                                <s:hidden  name = "ListaProgramasRegistro[%{#stat.index}].NO_PASSA" id="NO_PASA"></s:hidden>
                                                                               

                                                                                                
                                                                                                

                                                                                            </s:iterator>  
                                                                                            
                                                                                            
                                                                                            
                                                                                        <s:iterator value="ListaEscala" id="ListaEscala" status="stat">         
                                                                                            <s:hidden  name = "ListaEscala[%{#stat.index}].ID_ESCALA" id="ID_ESCALA"></s:hidden>
                                                                                            <s:hidden  name = "ListaEscala[%{#stat.index}].ESCALA" id="ESCALA"></s:hidden>
                                                                                        </s:iterator>
                                                                                        <s:iterator value="ListaLugar" id="ListaLugar" status="stat">         
                                                                                            <s:hidden  name = "ListaLugar[%{#stat.index}].ID_LUGAR" id="ID_LUGAR"></s:hidden>
                                                                                            <s:hidden  name = "ListaLugar[%{#stat.index}].LUGAR" id="LUGAR"></s:hidden>
                                                                                        </s:iterator> 
                                                                                        <s:iterator value="ListaHora" id="ListaHora" status="stat">         
                                                                                            <s:hidden  name = "ListaHora[%{#stat.index}].ID_HORA" id="ID_HORA"></s:hidden>
                                                                                            <s:hidden  name = "ListaHora[%{#stat.index}].HORA" id="HORA"></s:hidden>
                                                                                        </s:iterator>
                                                                                            
                                                                                            
                                                                                            
                                                                                            



                                                                                        </tbody>
                                                                                    </table>

                                                                                    <s:fielderror  fieldName="ERRORACTPLAN" cssClass="col-lg-12 alert alert-danger"></s:fielderror>
                                                                                    <s:fielderror  fieldName="ERRORFORM" cssClass="col-lg-12 alert alert-danger"></s:fielderror>
                                                                                      <s:fielderror  fieldName="ERRORTOTALESCALA" cssClass="col-lg-12 alert alert-danger"></s:fielderror>


                                                                                    

                                                                                    </div>



                                                                           



                                                                        </div>
                                                                                      


                                                                                       <div class="form-group col-md-12 text-center" >
                                                                            <a href="Javascript:consulta('guardaPlanForm')"  class="btn btn-round btn-primary">Guardar</a>
                                                                        </div>

                                                                        </s:if>   
                                                                            
                                                                         <s:if test="mensajeSnAct"> 
                                                                            
                                                                             <div class="col-lg-12 alert alert-danger" style="margin-top: 30px;">El programa educativo no cuenta con actividades validas </div>
                                                                            
                                                                        </s:if>

                                                                          <s:fielderror  fieldName="SEGUARDO" cssClass="col-lg-12 alert alert-success"></s:fielderror>

                                                                    </div>
                                                                        
                                                                       
                                                                        

                                                                </div>
                                                            </div>
                                                        </div>
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


                                            <s:textfield name="pf.ID_ESCUELA" id="pf.ID_ESCUELA" ></s:textfield>
                                            <s:textfield name="pf.ID_CICLO" id="al.ID_CICLO" ></s:textfield>

                                            <s:hidden name="bnprog" value="%{bnprog}"></s:hidden>
                                             <s:hidden name="programa.ID_NIVEL" value="%{programa.ID_NIVEL}"></s:hidden>

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
