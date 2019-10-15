<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>



<script type="text/javascript">

    function cerrar(accion) {

        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
    }

    function     guardaMateria(accion) {

        var cvemateria = document.getElementById("CVEMATERIANUEVA").value;
        var nommateria = document.getElementById("NOMMATERIANUEVA").value;

        if (cvemateria.length > 0 && nommateria.length > 0) {

            document.formularioPrincipal.action = accion;
            document.formularioPrincipal.submit();

        } else {
            alert("Favor de capturar la Clave de la Materia y Nombre de la Materia");

        }


    }
    
    
     function     guardaCompetecia(accion) {

        var com = document.getElementById("COMPETENCIA2").value;
      

        if (com.length > 0 ) {

            document.formularioPrincipal.action = accion;
            document.formularioPrincipal.submit();

        } else {
            alert("Favor de capturar la Competencia");

        }


    }
    
     function     actualizaCompetencia(accion) {

        var com = document.getElementById("COMPETENCIA2").value;
      

        if (com.length > 0 ) {

            document.formularioPrincipal.action = accion;
            document.formularioPrincipal.submit();

        } else {
            alert("Favor de capturar la Competencia");

        }


    }
    
        function muestra_oculta2(com, idcom){
        
        
         document.formularioPrincipal.COMPETENCIA2.value = com;
         document.formularioPrincipal.AUXCOMPETENCIA.value = idcom;
        
        
        document.getElementById('actualiza').style.display = 'block';
         document.getElementById('guarda').style.display = 'none';
     
    }
        
      
    



    function enviar(accion) {

        var cveplanE = document.getElementById("cveplan").value;

        if (cveplanE.length > 0) {

            document.formularioPrincipal.action = accion;

            document.formularioPrincipal.submit();
        } else {
            alert("Favor de campturar la clave o nombre del Programa de Estudio");
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
    function act(accion, auxid, cveplan, nomplan) {

        document.formularioPrincipal.AUXIDCCTPLAN.value = auxid;
        document.formularioPrincipal.CVEPLAN.value = cveplan;
        document.formularioPrincipal.NOMCARRERA.value = nomplan;


        document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();

    }

    function eliminaM(accion, idmagteria) {

        document.formularioPrincipal.AUXMATERIA.value = idmagteria;



        document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();

    }







    function elimina(accion, idplancct) {

        document.formularioPrincipal.AUXIDCCTPLAN.value = idplancct;


        document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();

    }
    
     function eliminaCompetencia(accion, idcom) {

        document.formularioPrincipal.AUXCOMPETENCIA.value = idcom;


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

                                                                        <h3> Registro de Competencias </h3>
                                                                    </div>



                                                                    <div style="width: 90%; margin: auto; " >








                                                                        <!--PROGRAMAS EDUCATIVOS CCT -->                  



                                                                        <div class="col-md-12" style="margin-top: 80px;">
                                                                            <div class="card">
                                                                                <div class="card-header card-header-primary card-header-icon">
                                                                                    <div class="card-icon">
                                                                                        <i class="material-icons">assignment</i>
                                                                                    </div>
                                                                                    <h4 class="card-title">Programas Educativos ofertados por la Institución Educativa</h4>
                                                                                </div>
                                                                                <div class="card-body">
                                                                                    <div class="toolbar">
                                                                                        <!--        Here you can write extra buttons/actions for the toolbar              -->
                                                                                    </div>
                                                                                    <div class="material-datatables">

                                                                                        <div style="width: 90%; height: 30px; padding: 5px; margin: auto; text-align: center; color: tomato;"><s:fielderror fieldName="ErrorBorrar" />  </div>



                                                                                    <div style="height: 200px; overflow-y: scroll;">



                                                                                        <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                                                                                            <thead style="background: #302f2f; color:white;">
                                                                                                <tr>
                                                                                                    <th>Clave del Programa</th>
                                                                                                    <th>Nombre del Programa</th>
                                                                                                    <th>Énfasis</th>
                                                                                                    <th>Agregar Materia</th>

                                                                                                </tr>
                                                                                            </thead>
                                                                                          
                                                                                            <tbody>
                                                                                                <s:iterator value="ListaProgramasEscuela" id="ListaProgramasEscuela" status="stat">  
                                                                                                    
                                                                                                    <s:if test="escuela.AUXIDCCTPLAN.length()==0">
                                                                                                      
                                                                                                   
                                                                                                    
                                                                                                    <tr <s:if test="ID_CCT_PLAN==escuela.AUXIDCCTPLAN">style=" color:purple; "</s:if>>
                                                                                                        <td><s:property value="CVE_PLAN"/></td>
                                                                                                        <td><s:property value="NOM_CARRERA"/></td>
                                                                                                        <td><s:property value="ENFASIS"/></td>
                                                                                                        <td align="center"><a href="Javascript:act('AgregarMateriaPlanCompetencia','<s:property value="ID_CCT_PLAN"/>','<s:property value="CVE_PLAN"/>','<s:property value="NOM_CARRERA"/>')" ><i class="material-icons"><s:if test="escuela.AUXIDCCTPLAN==ID_CCT_PLAN">check_box</s:if><s:else>check_box_outline_blank</s:else></i></a></td>
                                                                                                    </tr>
                                                                                                    
                                                                                                     </s:if>
                                                                                                    <s:else>
                                                                                                        <s:if test="ID_CCT_PLAN==escuela.AUXIDCCTPLAN">
                                                                                                        
                                                                                                    <tr <s:if test="ID_CCT_PLAN==escuela.AUXIDCCTPLAN">style=" color:purple; "</s:if>>
                                                                                                        <td><s:property value="CVE_PLAN"/></td>
                                                                                                        <td><s:property value="NOM_CARRERA"/></td>
                                                                                                        <td><s:property value="ENFASIS"/></td>
                                                                                                        <td align="center"><a href="Javascript:act('AgregarMateriaPlanCompetencia','<s:property value="ID_CCT_PLAN"/>','<s:property value="CVE_PLAN"/>','<s:property value="NOM_CARRERA"/>')" ><i class="material-icons"><s:if test="escuela.AUXIDCCTPLAN==ID_CCT_PLAN">check_box</s:if><s:else>check_box_outline_blank</s:else></i></a></td>
                                                                                                    </tr>
                                                                                                        
                                                                                                        </s:if>
                                                                                                        
                                                                                                        
                                                                                                    </s:else>
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    

                                                                                                    <s:hidden  name = "ListaProgramasEscuela[%{#stat.index}].CVE_PLAN" id="CVE_PLAN"></s:hidden>
                                                                                                    <s:hidden  name = "ListaProgramasEscuela[%{#stat.index}].NOM_CARRERA" id="NOM_CARRERA"></s:hidden>
                                                                                                    <s:hidden  name = "ListaProgramasEscuela[%{#stat.index}].ENFASIS" id="ENFASIS"></s:hidden>
                                                                                                    <s:hidden  name = "ListaProgramasEscuela[%{#stat.index}].STATUS" id="STATUS"></s:hidden>
                                                                                                    <s:hidden name =  "ListaProgramasEscuela[%{#stat.index}].ID_CCT_PLAN" id="ID_CCT_PLAN"></s:hidden>
                                                                                                    <s:hidden name =  "ListaProgramasEscuela[%{#stat.index}].ID_ESCUELA" id="ID_ESCUELA"></s:hidden>
                                                                                                    <s:hidden name =  "ListaProgramasEscuela[%{#stat.index}].ID_PLAN" id="ID_PLAN"></s:hidden>
                                                                                                </s:iterator>  



                                                                                            </tbody>
                                                                                        </table>






                                                                                    </div> 


                                                                                    <!--lista de Materias del Programa Educativo -->













                                                                                </div>
                                                                            </div>
                                                                            <!-- end content-->
                                                                        </div>
                                                                        <!--  end card  -->
                                                                    </div>   





                                                                    <s:if test="bnmateria">





                                                                        <div class="col-md-12" style="margin-top: 80px;">
                                                                            <div class="card">
                                                                                <div class="card-header card-header-rose card-header-icon">
                                                                                    <div class="card-icon">
                                                                                        <i class="material-icons">assignment</i>
                                                                                    </div>
                                                                                    <h4 class="card-title">Materias del Programa de Estudio</h4>
                                                                                </div>  



                                                                                <div class="row" style="margin-top: 30px;">

                                                                                    <div class="form-group col-md-4">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Clave de Plan</label>

                                                                                        <s:textfield name="programa.CVE_PLAN" id="CVEPLAN" cssClass="form-control " readonly="true" ></s:textfield>

                                                                                        </div>
                                                                                        <div class="form-group col-md-8">
                                                                                            <label for="exampleEmail" class="bmd-label-floating">Nombre del Plan</label>

                                                                                        <s:textfield name="programa.NOM_CARRERA" id="NOMCARRERA" cssClass="form-control " readonly="true" ></s:textfield>

                                                                                        </div>




                                                                                        <div style="width: 100%; text-align: center; color: #b11a4f; "> <s:fielderror fieldName="ya" id="ya" ></s:fielderror></div>








                                                                                    </div>  
                                                                                    <div class="row">     

                                                                                        <div class="col-md-12">










                                                                                        <s:if test="ListaPlanMateria.size()>0"> 


                                                                                            <div style="height: 200px; overflow-y: scroll; margin-top: 20px;">

                                                                                                <div style="width: 100%; text-align: center; color: #b11a4f; "> <s:fielderror fieldName="ErrorBorrarMateria" id="ErrorBorrarMateria" ></s:fielderror></div>

                                                                                                    <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                                                                                                        <thead style="background: #302f2f; color:white;">
                                                                                                            <tr>
                                                                                                                <th>Clave de la Materia</th>
                                                                                                                <th>Nombre de la Materia</th>

                                                                                                                <th align="center"style="text-align: center;">Borrar Materia</th>

                                                                                                            </tr>
                                                                                                        </thead>

                                                                                                        <tbody>
                                                                                                        <s:iterator value="ListaPlanMateria" id="ListaPlanMateria" status="stat">                                                                                                                                      
                                                                                                            
                                                                                                            <s:if test="materia.AUXMATERIA.length()==0">



                                                                                                                 <tr <s:if test="ID_MATERIA==materia.AUXMATERIA">style=" color:red; "</s:if> >
                                                                                                                <td ><s:property value="CVE_MATERIA"/></td>
                                                                                                                <td><s:property value="NOMBRE_MATERIA"/></td>

                                                                                                                <td align="center"><a href="Javascript:eliminaM('agregarPlanMateriaCom','<s:property value="ID_MATERIA"/>')" ><i class="material-icons" style="color:red;"><s:if test="materia.AUXMATERIA==ID_MATERIA">check_box</s:if><s:else>check_box_outline_blank</s:else></i></a></td>
                                                                                                             </tr>

                                                                                                            </s:if>
                                                                                                            <s:else>
                                                                                                                <s:if test="ID_MATERIA==materia.AUXMATERIA">

                                                                                                                     <tr <s:if test="ID_MATERIA==materia.AUXMATERIA">style=" color:red; "</s:if> >
                                                                                                                <td ><s:property value="CVE_MATERIA"/></td>
                                                                                                                <td><s:property value="NOMBRE_MATERIA"/></td>

                                                                                                                <td align="center"><a href="Javascript:eliminaM('agregarPlanMateriaCom','<s:property value="ID_MATERIA"/>')" ><i class="material-icons" style="color:red;"><s:if test="materia.AUXMATERIA==ID_MATERIA">check_box</s:if><s:else>check_box_outline_blank</s:else></i></a></td>
                                                                                                             </tr>
                                                                                                                </s:if>


                                                                                                            </s:else>

                                                                                                            
                                                                                                            
                                                                                                            
                                                                                                            
                                                                                                            

                                                                                                            <s:hidden  name = "ListaPlanMateria[%{#stat.index}].CVE_MATERIA" id="CVE_MATERIA"></s:hidden>
                                                                                                            <s:hidden  name = "ListaPlanMateria[%{#stat.index}].NOMBRE_MATERIA" id="NOMBRE_MATERIA"></s:hidden>
                                                                                                            <s:hidden  name = "ListaPlanMateria[%{#stat.index}].ID_MATERIA" id="ID_MATERIA"></s:hidden>
                                                                                                            <s:hidden  name = "ListaPlanMateria[%{#stat.index}].ID_CCT_PLAN" id="ID_CCT_PLAN"></s:hidden>

                                                                                                        </s:iterator>  



                                                                                                    </tbody>
                                                                                                </table>


                                                                                            </div> 

                                                                                        </s:if>
                                                                                    </div>



                                                                                </div>





                                                                            </div>
                                                                        </div>    




                                                                    </s:if>         


                                                                    <s:if test="bncompetencia">      
                                                                        
                                                                        
                                                                        
                                                                      
                                                                        
                                                                        


                                                                        <div class="col-md-12" style="margin-top: 80px;">
                                                                            <div class="card">
                                                                                <div class="card-header card-header-info card-header-icon">
                                                                                    <div class="card-icon">
                                                                                        <i class="material-icons">assignment</i>
                                                                                    </div>
                                                                                    <h4 class="card-title">Competencias de la Materia</h4>
                                                                                </div>  



                                                                                <div class="row" style="margin-top: 30px;">




                                                                                </div>  
                                                                                <div class="row">     
                                                                                    
                                                                                       <div class="form-group col-md-12">
                                                                                        <label for="exampleEmail" class="bmd-label-floating">Competencia</label>

                                                                                        <s:textfield name="competencia.COMPETENCIA" id="COMPETENCIA2" cssClass="form-control "  ></s:textfield>
                                                                                 
                                                                                    </div>
                                                                              
                                                                                    
                                                                                    
                                                                                      <div class="form-group col-md-12" id="guarda">
                                                                                      
                                                                                          <div class="card-footer justify-content-center"  >
                                                                                              <a href="Javascript:guardaCompetecia('guardaCompetenciaCct')" class="btn btn-rose btn-link btn-lg" ><div style="background: #b11a4f; color: white; max-width: 200px; min-width: 180px; height: 40px; padding: 10px; border-radius: 3px;"> Registrar Competencia</div></a> 
                                                                                          </div>
                                                                                          
                                                                                          
                                                                                          
                                                                                    </div>
                                                                                      
                                                                                    
                                                                                          <div class="form-group col-md-12" style="display: none;"  id="actualiza" >
                                                                                      
                                                                                         <div class="card-footer justify-content-center"   >
                                                                                              <a href="Javascript:actualizaCompetencia('actualizaCompetenciaCct')" class="btn btn-rose btn-link btn-lg" ><div style="background: #b11a4f; color: white; max-width: 200px; min-width: 180px; height: 40px; padding: 10px; border-radius: 3px;"> Actualizar Competencia</div></a> 
                                                                                          </div>
                                                                                          
                                                                                          
                                                                                          
                                                                                    </div>
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                    

                                                                                    <div class="col-md-12">










                                                                                        <s:if test="ValidaMateriaCompetencia.size()>0"> 


                                                                                            <div style="height: 200px; overflow-y: scroll; margin-top: 20px;">

                                                                                                <div style="width: 100%; text-align: center; color: #b11a4f; "> <s:fielderror fieldName="ErrorBorrarMateria" id="ErrorBorrarMateria" ></s:fielderror></div>

                                                                                                    <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                                                                                                        <thead style="background: #302f2f; color:white;">
                                                                                                            <tr>
                                                                                                                <th>Clave de la Competencia</th>
                                                                                                                <th>Nombre de la Competecia</th>

                                                                                                                <th align="center"style="text-align: center;">Actualizar</th>
                                                                                                                <th>Eliminar</th>   
                                                                                                            </tr>
                                                                                                        </thead>

                                                                                                        <tbody>
                                                                                                        <s:iterator value="ValidaMateriaCompetencia" id="ValidaMateriaCompetencia" status="stat">                                                                                                                                      
                                                                                                            <tr <s:if test="ID_COMPETENCIA==competencia.AUXCOMPETENCIA">style=" color:#309cdd; "</s:if> >
                                                                                                                <td ><s:property value="ID_COMPETENCIA"/></td>
                                                                                                                <td><s:property value="COMPETENCIA"/></td>

                                                                                                            <td align="center"><a  href="Javascript:muestra_oculta2('<s:property value="COMPETENCIA"/>','<s:property value="ID_COMPETENCIA"/>')" ><i class="material-icons" style="color:blue;">create</i></a></td>
                                                                                                            <td><a href="Javascript:eliminaCompetencia('eliminarCompetenciaCct','<s:property value="ID_COMPETENCIA"/>')" style="text-decoration: none; "><i class="material-icons" style="color:red;">delete_forever</i></a></td>
                                                                                                            
                                                                                                            
                                                                                                            </tr>

                                                                                                            <s:hidden  name = "ValidaMateriaCompetencia[%{#stat.index}].ID_COMPETENCIA" id="ID_COMPETENCIA"></s:hidden>
                                                                                                            <s:hidden  name = "ValidaMateriaCompetencia[%{#stat.index}].COMPETENCIA" id="COMPETENCIA"></s:hidden>
                                                                                                            <s:hidden  name = "ValidaMateriaCompetencia[%{#stat.index}].ID_MATERIA" id="ID_MATERIA"></s:hidden>


                                                                                                        </s:iterator>  



                                                                                                    </tbody>
                                                                                                </table>


                                                                                            </div> 

                                                                                        </s:if>
                                                                                    </div>



                                                                                </div>





                                                                            </div>
                                                                        </div>    





                                                                    </s:if>  




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
                                                    <s:textfield name="escuela.AUXESCUELA" id="AUXESCUELA" cssStyle="display:none;"></s:textfield>                                                          
                                                    <s:textfield name="escuela.AUXPLAN" id="AUXPLAN" cssStyle="display:none;"></s:textfield>        
                                                    <s:textfield name="escuela.AUXSTATUS" id="AUXSTATUS" cssStyle="display:none;"></s:textfield>
                                                    <s:textfield name="escuela.AUXIDCCTPLAN" id="AUXIDCCTPLAN" cssStyle="display:none;" ></s:textfield>
                                                      <s:textfield name="competencia.AUXCOMPETENCIA" id="AUXCOMPETENCIA" cssStyle="display:none;" ></s:textfield>
                                                    <s:if test="!bnmateria">
                                                        <s:textfield name="programa.CVE_PLAN" id="CVEPLAN" cssStyle="display:none;" ></s:textfield>
                                                        <s:textfield name="programa.NOM_CARRERA" id="NOMCARRERA" cssStyle="display:none;" ></s:textfield>
                                                    </s:if>

                                                    <s:textfield name="materia.AUXMATERIA" id="AUXMATERIA" cssStyle="display:none;" ></s:textfield>

                                                    <s:hidden name="bnprog" value="%{bnprog}"></s:hidden>
                                                    <s:hidden name="bnprogno" value="%{bnprogno}"></s:hidden>
                                                    <s:hidden name="bnmateria" value="%{bnmateria}"></s:hidden>
                                                     <s:hidden name="bncompetencia" value="%{bncompetencia}"></s:hidden>
                                                

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
