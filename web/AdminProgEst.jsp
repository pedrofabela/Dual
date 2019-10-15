<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>



<script type="text/javascript">

    function cerrar(accion) {

        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
    }
    
      function consulta(accion) {

        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
    }
    
    function guardaPrograma(accion) {


        var IDPLAN = document.getElementById("IDPLAN").value;
          var NOMCARRERA = document.getElementById("NOMCARRERA").value;
            var TIPO_PERIODO = document.getElementById("TIPO_PERIODO").value;
              var PERIODO2 = document.getElementById("PERIODO2").value;



              if(IDPLAN.length<1 || NOMCARRERA.length<1 || TIPO_PERIODO.length<1 || PERIODO2.length<1)
              
               
                {
                     alert("¡Favor de registrar todos los campos obligatorios!");

                if(IDPLAN.length<1){
                    
                    document.getElementById('IDPLAN').style.boxShadow = "0px 1px 5px red";
                    
                }

                  if(NOMCARRERA.length<1){
                    
                    document.getElementById('NOMCARRERA').style.boxShadow = "0px 1px 5px red";
                   
                }
                
                  if(TIPO_PERIODO.length<1){
                    
                    document.getElementById('TIPO_PERIODO').style.backgroundColor = "red";
                   
                }
                
                  if(PERIODO2.length<1){
                    
                    document.getElementById('PERIODO2').style.backgroundColor = "red";
                   
                }
            }
            
            else{
                 document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
                
            }
/*

        */
    }
    
     function actualizaProgramaGuarda(accion) {


        var IDPLAN = document.getElementById("IDPLAN").value;
          var NOMCARRERA = document.getElementById("NOMCARRERA").value;
            var TIPO_PERIODO = document.getElementById("TIPO_PERIODO").value;
              var PERIODO2 = document.getElementById("PERIODO2").value;



              if(IDPLAN.length<1 || NOMCARRERA.length<1 || TIPO_PERIODO.length<1 || PERIODO2.length<1)
              
               
                {
                     alert("¡Favor de registrar todos los campos obligatorios!");

                if(IDPLAN.length<1){
                    
                    document.getElementById('IDPLAN').style.boxShadow = "0px 1px 5px red";
                    
                }

                  if(NOMCARRERA.length<1){
                    
                    document.getElementById('NOMCARRERA').style.boxShadow = "0px 1px 5px red";
                   
                }
                
                  if(TIPO_PERIODO.length<1){
                    
                    document.getElementById('TIPO_PERIODO').style.backgroundColor = "red";
                   
                }
                
                  if(PERIODO2.length<1){
                    
                    document.getElementById('PERIODO2').style.backgroundColor = "red";
                   
                }
            }
            
            else{
                 document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
                
            }
/*

        */
    }
    
    function actualizaPrograma(accion, programa) {

 document.formularioPrincipal.AUXPLAN.value = programa;
       
         document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
                
            
/*

        */
    }
    
    
    
    
    
    
    function enviar(accion) {

        var cveplanE=document.getElementById("cveplan").value;

        if(cveplanE.length>0){

        document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();
        }
        
        else{
            alert("Favor de capturar la clave o nombre del Programa de Estudio");
            captura();
        }
        function captura() {
  document.getElementById("cveplan").style.backgroundColor = "#e8f0fe";
}
    
    
    }
    function progcct(accion, programa, escuela) {

      document.formularioPrincipal.AUXESCUELA.value = escuela;
      document.formularioPrincipal.AUXPLAN.value = programa;


      document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();

    }
    function act(accion, programa, estatus) {

     
      document.formularioPrincipal.AUXPLAN.value = programa;
       document.formularioPrincipal.AUXSTATUS.value = estatus;

  document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();

    }
    
     function eliminaProg(accion, programa) {

     
      document.formularioPrincipal.AUXPLAN.value = programa;
     

  document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();

    }
    
    function actProg(accion, programa) {

     
      document.formularioPrincipal.AUXPLAN.value = programa;
     

  document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();

    }
    
    function elimina(accion, idplancct) {

      document.formularioPrincipal.AUXIDCCTPLAN.value = idplancct;
   

  document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();

    }
    
    
     window.onload = function () {/*hace que se cargue la función lo que predetermina que div estará oculto hasta llamar a la función nuevamente*/
                                    var pos = window.name || 0;
                                    window.scrollTo(0, pos);

            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button" //chrome
            window.onhashchange = function () {
                window.location.hash = "no-back-button";
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
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon2.png">
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

                                                                <div class="col-md-12" style="margin-top: 20px;">


                                                                    <div style="width: 90%; margin: auto;  color:#302f2f; text-align: center; margin-top: 20px;" >

                                                                        <h3> Administración de Programas de Estudio </h3>
                                                                    </div>



                                                                    <div style="width: 90%; margin: auto; " >

                                                                        <div class="card-body">

                                                                            <div class="row" style="margin-top: 20px;">
                                                                                <div class="col-md-12">

                                                                                    <div class="form-group col-md-12">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Subsistema Educativo</label>
                                                                                        
                                                                                    <s:select  name="programa.ID_SUBSISTEMA_CONSULTA" id="ID_SUBSISTEMA_CONSULTA" list="ListSubsistema"  listKey="ID_SUBSISTEMA"  listValue="NOMBRE_SUBISTEMA"  headerKey="" headerValue="" cssClass="form-control " cssStyle="margin-top:20px;" onchange="Javascript:consulta('consultaProgEstAdmin')" ></s:select>

                                                                                    <s:iterator value="ListSubsistema" id="ListSubsistema" status="stat">   
                                                                                        <s:hidden  name = "ListSubsistema[%{#stat.index}].ID_SUBSISTEMA" id="ID_SUBSISTEMA"></s:hidden>
                                                                                        <s:hidden  name = "ListSubsistema[%{#stat.index}].NOMBRE_SUBISTEMA" id="NOMBRE_SUBISTEMA"></s:hidden>
                                                                                        <s:hidden  name = "ListSubsistema[%{#stat.index}].ID_NIVEL" id="ID_NIVEL"></s:hidden>
                                                                                    </s:iterator>
                                                                                    
                                                                                    
                                                                                    
                                                                                   
                                                                                    <s:fielderror fieldName="CURP" id="CURP" ></s:fielderror>
                                                                                    </div>




                                                                                   
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                           
                                                                          <s:if test="banNuvProg">
                                                                                    
                                                                           <div class="row" style="margin-top: 20px;">
                                                                                <div class="col-md-12">

                                                                                    <div class="form-group col-md-12">
                                                                                       <div class="form-group">
                                                                                        <a href="Javascript:consulta('activaRegProg')" class="btn btn-rose pull-left">Agregar Nuevo Programa</a>
                                                                                        <div class="clearfix"></div>
                                                                                    </div>
                                                                                    </div>




                                                                                    
                                                                                </div>
                                                                            </div>         
                                                                                    
                                                                             </s:if>     
                                                                                    
                                                                                    <s:if test="banAgrProg">    
                                                                       <div class="card-body">

                                                                            <div class="row">
                                                                            

                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Clave del Programa de Estudio</label>

                                                                                    <s:textfield name="programa.ID_PLAN" id="IDPLAN" cssClass="form-control " ></s:textfield>
                                                                                   
                                                                                    </div>

                                                                                     <div class="form-group col-md-8">
                                                                                    <label for="exampleEmail" class="bmd-label-floating">Nombre del Programa de Estudios</label>

                                                                                    <s:textfield name="programa.NOM_CARRERA" id="NOMCARRERA" cssClass="form-control " ></s:textfield>
                                                                                   
                                                                                    </div>
                                                                                    
                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Clave del Plan de Estudios</label>

                                                                                    <s:textfield name="programa.CVE_PLAN_EST" id="CVEPLANEST" cssClass="form-control " ></s:textfield>
                                                                                   
                                                                                    </div>
                                                                                   
                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Énfasis</label>

                                                                                    <s:textfield name="programa.ENFASIS" id="ENFASIS" cssClass="form-control " ></s:textfield>
                                                                                   
                                                                                    </div>
                                                                                    
                                                                                     <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Vesión</label>

                                                                                    <s:textfield name="programa.VERSION" id="VERSION" cssClass="form-control " ></s:textfield>
                                                                                   
                                                                                    </div>
                                                                                    
                                                                                     <div class="form-group col-md-4">
                                                                                    
                                                                        <s:select  name="programa.TIPO_PERIODO" id="TIPO_PERIODO" list="ListaTipoPeriodo"  listKey="ID_TIPO_PERIODO"  listValue="TIPO_PERIODO"  headerKey="" headerValue="" cssClass="selectpicker " cssStyle="margin-top:20px;"  data-style="select-with-transition" title="Tipo de Periodo" data-size="7" tabindex="-98" ></s:select>
                                                                       
                                                                            <s:iterator value="ListaTipoPeriodo" id="ListaTipoPeriodo" status="stat">
                                                                                <s:hidden  name = "ListaTipoPeriodo[%{#stat.index}].ID_TIPO_PERIODO" id="ID_TIPO_PERIODO"></s:hidden>
                                                                                <s:hidden  name = "ListaTipoPeriodo[%{#stat.index}].TIPO_PERIODO" id="TIPO_PERIODO"></s:hidden>
                                                                            </s:iterator>
                                                                                    </div>
                                                                        
                                                                        
                                                                                     <div class="form-group col-md-4">
                                                                                    
                                                                        <s:select  name="programa.NUMERO_PERIODO" id="PERIODO2" list="ListaPeriodo"  listKey="ID_PERIODO"  listValue="PERIODO"  headerKey="" headerValue="" cssClass="selectpicker " cssStyle="margin-top:20px;" data-style="select-with-transition" title="Periodo" data-size="7" tabindex="-98" ></s:select>

                                                                                    <s:iterator value="ListaPeriodo" id="ListaPeriodo" status="stat">
                                                                                <s:hidden  name = "ListaPeriodo[%{#stat.index}].ID_PERIODO" id="ID_PERIODO"></s:hidden>
                                                                                <s:hidden  name = "ListaPeriodo[%{#stat.index}].PERIODO" id="PERIODO"></s:hidden>
                                                                            </s:iterator>
                                                                                   
                                                                                    </div>
                                                                                 
                                                                                    
                                                                                    


                                                                                    <div class="form-group">
                                                                                        <a href="Javascript:guardaPrograma('guardaPrograma')" class="btn btn-rose pull-left">  Guardar Programa de Estudios</a>
                                                                                        <div class="clearfix"></div>
                                                                                    </div>
                                                                                
                                                                            </div>
                                                                        </div>          
                                                                                    
                                                                                    
                                                                                    
                                                                              </s:if> 
                                                                                    
                                                                                    
                                                                                     <s:if test="banActProg">    
                                                                       <div class="card-body">

                                                                            <div class="row">
                                                                            

                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Clave del Programa de Estudio</label>

                                                                                    <s:textfield name="programa.ID_PLAN" id="IDPLAN" cssClass="form-control " ></s:textfield>
                                                                                   
                                                                                    </div>

                                                                                     <div class="form-group col-md-8">
                                                                                    <label for="exampleEmail" class="bmd-label-floating">Nombre del Programa de Estudios</label>

                                                                                    <s:textfield name="programa.NOM_CARRERA" id="NOMCARRERA" cssClass="form-control " ></s:textfield>
                                                                                   
                                                                                    </div>
                                                                                    
                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Clave del Plan de Estudios</label>

                                                                                    <s:textfield name="programa.CVE_PLAN_EST" id="CVEPLANEST" cssClass="form-control " ></s:textfield>
                                                                                   
                                                                                    </div>
                                                                                   
                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Énfasis</label>

                                                                                    <s:textfield name="programa.ENFASIS" id="ENFASIS" cssClass="form-control " ></s:textfield>
                                                                                   
                                                                                    </div>
                                                                                    
                                                                                     <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Vesión</label>

                                                                                    <s:textfield name="programa.VERSION" id="VERSION" cssClass="form-control " ></s:textfield>
                                                                                   
                                                                                    </div>
                                                                                    
                                                                                     <div class="form-group col-md-4">
                                                                                    
                                                                        <s:select  name="programa.TIPO_PERIODO" id="TIPO_PERIODO" list="ListaTipoPeriodo"  listKey="ID_TIPO_PERIODO"  listValue="TIPO_PERIODO"  headerKey="" headerValue="" cssClass="selectpicker " cssStyle="margin-top:20px;"  data-style="select-with-transition" title="Tipo de Periodo" data-size="7" tabindex="-98" ></s:select>
                                                                       
                                                                            <s:iterator value="ListaTipoPeriodo" id="ListaTipoPeriodo" status="stat">
                                                                                <s:hidden  name = "ListaTipoPeriodo[%{#stat.index}].ID_TIPO_PERIODO" id="ID_TIPO_PERIODO"></s:hidden>
                                                                                <s:hidden  name = "ListaTipoPeriodo[%{#stat.index}].TIPO_PERIODO" id="TIPO_PERIODO"></s:hidden>
                                                                            </s:iterator>
                                                                                    </div>
                                                                        
                                                                        
                                                                                     <div class="form-group col-md-4">
                                                                                    
                                                                        <s:select  name="programa.NUMERO_PERIODO" id="PERIODO2" list="ListaPeriodo"  listKey="ID_PERIODO"  listValue="PERIODO"  headerKey="" headerValue="" cssClass="selectpicker " cssStyle="margin-top:20px;" data-style="select-with-transition" title="Periodo" data-size="7" tabindex="-98" ></s:select>

                                                                                    <s:iterator value="ListaPeriodo" id="ListaPeriodo" status="stat">
                                                                                <s:hidden  name = "ListaPeriodo[%{#stat.index}].ID_PERIODO" id="ID_PERIODO"></s:hidden>
                                                                                <s:hidden  name = "ListaPeriodo[%{#stat.index}].PERIODO" id="PERIODO"></s:hidden>
                                                                            </s:iterator>
                                                                                   
                                                                                    </div>
                                                                                 
                                                                                    
                                                                                    


                                                                                    <div class="form-group">
                                                                                        <a href="Javascript:actualizaProgramaGuarda('actualizaProgramaGuarda')" class="btn btn-rose pull-left">  Actualizar Programa de Estudios</a>
                                                                                        <div class="clearfix"></div>
                                                                                    </div>
                                                                                
                                                                            </div>
                                                                        </div>          
                                                                                    
                                                                                    
                                                                                    
                                                                              </s:if>       


                                                                      




                                                                  <!--PROGRAMAS EDUCATIVOS CCT -->                  


                                                                    <div class="col-md-12" style="margin-top: 80px;">
                                                                        <div class="card">
                                                                            <div class="card-header card-header-primary card-header-icon">
                                                                                <div class="card-icon">
                                                                                    <i class="material-icons">assignment</i>
                                                                                </div>
                                                                                <h4 class="card-title">Programas Educativos del Subsistema</h4>
                                                                            </div>
                                                                            <div class="card-body">
                                                                                <div class="toolbar">
                                                                                    <!--        Here you can write extra buttons/actions for the toolbar              -->
                                                                                </div>
                                                                                <div class="material-datatables">

                                                                                     <div style="width: 90%; height: 30px; padding: 5px; margin: auto; text-align: center; color: tomato;"><s:fielderror fieldName="ErrorBorrar" />  </div>
                                                                                    
                                                                                    
                                                                                    
                                                                                    <div style="height: 500px; overflow-y: scroll;">



                                                                                        <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                                                                                            <thead style="background: #302f2f; color:white;">
                                                                                                <tr>
                                                                                                    <th>Clave del Programa</th>
                                                                                                    <th>Nombre del Programa</th>
                                                                                                    <th>Clave Plan Estudios</th>
                                                                                                      <th>Versión</th>
                                                                                                    <th>Énfasis</th>
                                                                                                    <th>Activar</th>
                                                                                                    <th>Actualizar</th>
                                                                                                      <th>Eliminar</th>
                                                                                                </tr>
                                                                                            </thead>
                                                                                           
                                                                                            <tbody>
                                                                                                <s:iterator value="ListaProgramasEdu" id="ListaProgramasEdu" status="stat">                                                                                                                                      
                                                                                                    <tr <s:if test="ID_PLAN==escuela.AUXPLAN">style= " color:purple; "</s:if>>
                                                                                                        <td><s:property value="CVE_PLAN"/></td>
                                                                                                        <td><s:property value="NOM_CARRERA"/></td>
                                                                                                        <td><s:property value="CVE_PLAN_EST"/></td>
                                                                                                        <td><s:property value="VERSION"/></td>
                                                                                                        <td><s:property value="ENFASIS"/></td>
                                                                                                        <td align="center"><s:if test="STATUS==1"><a href="Javascript:act('actualizaStatusProg','<s:property value="ID_PLAN"/>','1')" style="text-decoration: none; "><i class="material-icons" style="text-align: center;">check_box</i></a></s:if><s:if test="STATUS==0"><a href="Javascript:act('actualizaStatusProg','<s:property value="ID_PLAN"/>','0')" ><i class="material-icons" style="text-align: center;">check_box_outline_blank</i></a></s:if></td>
                                                                                                        <td align="center"><a href="Javascript:actProg('actualizaPrograma','<s:property value="ID_PLAN"/>')" style="text-decoration: none; "><i class="material-icons" style="color:blue; text-align: center;">create</i></a></td>                                                                                                            
                                                                                                        <td align="center"><a href="Javascript:eliminaProg('eliminarPrograma','<s:property value="ID_PLAN"/>')" style="text-decoration: none; "><i class="material-icons" style="color:red; text-align: center;">delete_forever</i></a></td>
                                                                                                    </tr>
                                                                                                        
                                                                                                    <s:hidden  name = "ListaProgramasEdu[%{#stat.index}].CVE_PLAN" id="CVE_PLAN"></s:hidden>
                                                                                                    <s:hidden  name = "ListaProgramasEdu[%{#stat.index}].NOM_CARRERA" id="NOM_CARRERA"></s:hidden>
                                                                                                    <s:hidden  name = "ListaProgramasEdu[%{#stat.index}].ENFASIS" id="ENFASIS"></s:hidden>
                                                                                                    <s:hidden  name = "ListaProgramasEdu[%{#stat.index}].STATUS" id="STATUS"></s:hidden>
                                                                                                    <s:hidden name =  "ListaProgramasEdu[%{#stat.index}].ID_CCT_PLAN" id="ID_CCT_PLAN"></s:hidden>
                                                                                                    <s:hidden name =  "ListaProgramasEdu[%{#stat.index}].ID_ESCUELA" id="ID_ESCUELA"></s:hidden>
                                                                                                     <s:hidden name =  "ListaProgramasEdu[%{#stat.index}].ID_PLAN" id="ID_PLAN"></s:hidden>
                                                                                                </s:iterator>  



                                                                                            </tbody>
                                                                                        </table>
                                                                                        
                                                                            

                                                                                          
                                                                                        
                                                                                        
                                                                                    </div> 
                                                                                </div>
                                                                            </div>
                                                                            <!-- end content-->
                                                                            
                                                                           
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
                                                                        </div>
                                                                        <!--  end card  -->
                                                                    </div>   
                                                                                    
                                                                           
                                                                      
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        

                                                                </div>

                                                            </div>
                                                            <div class="clearfix"></div>

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
                                              
                                                 <s:textfield name="escuela.AUXPLAN" id="AUXPLAN" cssStyle="display:none;"></s:textfield>        
                                                    <s:textfield name="escuela.AUXSTATUS" id="AUXSTATUS" cssStyle="display:none;"></s:textfield>
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






                        </body>
                                                                                    
                                                                           
                                                                                    
                                                                                    
                    </s:form>
                    </html>
