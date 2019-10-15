<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>



<script type="text/javascript">
    
     function validarFormulario(){
        
          
         
        var promedio=document.getElementById('PROMEDIO').value;
         
        var sit_aca=document.getElementById('SITUACION_ACADEMICA').value;
        
        var tel_cel=document.getElementById('TEL_CEL').value;
        
        var email_per=document.getElementById('EMAIL_PER').value;
        
        var res_eva=document.getElementById('RES_EVA').value;
  
       
    
    if(promedio == null || promedio.length == 0 || /^\s+$/.test(promedio)){
      //alert('ERROR: El campo razon social no debe ir vacío.');
      document.getElementById('PROMEDIO').style.boxShadow="0px 1px 5px red";
      document.getElementById('PROMEDIO').focus();
      return false;
    }else{
      document.getElementById('PROMEDIO').style.boxShadow="";  
    }
    
     if(sit_aca == null || sit_aca.length == 0 || /^\s+$/.test(sit_aca)){
      //alert('ERROR: El campo razon social no debe ir vacío.');
      document.getElementById('SITUACION_ACADEMICA').style.boxShadow="0px 1px 5px red";
      document.getElementById('SITUACION_ACADEMICA').focus();
      return false;
    }else{
      document.getElementById('SITUACION_ACADEMICA').style.boxShadow="";  
    }
    
    if(tel_cel == null || tel_cel.length == 0 || isNaN(tel_cel)){
      //alert('ERROR: El campo giro no debe ir vacío.');
      document.getElementById('TEL_CEL').style.boxShadow="0px 1px 5px red";
      document.getElementById('TEL_CEL').focus();
      return false;
    }else{
        document.getElementById('TEL_CEL').style.boxShadow="";  
    }
    
    if(!(/\S+@\S+\.\S+/.test(email_per))){
      document.getElementById('EMAIL_PER').style.boxShadow="0px 1px 5px red";
      document.getElementById('EMAIL_PER').focus();
      return false;
    }else{
       document.getElementById('EMAIL_PER').style.boxShadow="";  
    }
    
    if(res_eva == null || res_eva.length == 0 || /^\s+$/.test(res_eva)){
      //alert('ERROR: El campo razon social no debe ir vacío.');
      document.getElementById('RES_EVA').style.boxShadow="0px 1px 5px red";
      document.getElementById('RES_EVA').focus();
      return false;
    }else{
      document.getElementById('RES_EVA').style.boxShadow="";  
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
    
    function actRes(accion, curp) {
        
        document.formularioPrincipal.CONSULTA_CURP.value = curp;      
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
    }
    
    function ActStatus(accion, idhistorico, estatus) {
        
        document.formularioPrincipal.id_historico.value = idhistorico;
        document.formularioPrincipal.status.value = estatus;
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
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
    function act(accion, programa, escuela, estatus) {

      document.formularioPrincipal.AUXESCUELA.value = escuela;
      document.formularioPrincipal.AUXPLAN.value = programa;
       document.formularioPrincipal.AUXSTATUS.value = estatus;

  document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();

    }
    
  
    
    
    
   
    
    
     window.onload = function () {/*hace que se cargue la función lo que predetermina que div estará oculto hasta llamar a la función nuevamente*/
                                    var pos = window.name || 0;
                                    window.scrollTo(0, pos);

          

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
        var diccionario  = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ",
            lngSuma      = 0.0,
            lngDigito    = 0.0;
        for(var i=0; i<17; i++)
            lngSuma= lngSuma + diccionario.indexOf(curp17.charAt(i)) * (18 - i);
        lngDigito = 10 - lngSuma % 10;
        if(lngDigito == 10)
            return 0;
        return lngDigito;
    }
    if (validado[2] != digitoVerificador(validado[1])) 
    	return false;
        
	return true; //Validado
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
                                                                        <div style="width: 90%; margin: auto;  color:#302f2f; text-align: center; margin-top: 20px;" >
                                                                            <h3> Validación de Alumnos Preseleccionados</h3>
                                                                        </div>                                                                                                                                   
                                                                        <div class="card-body">                                                          
                                                                            <div class="material-datatables">                                                                                                                                                              
                                                                                <div style="height: 500px; overflow-y: scroll;">
                                                                                    <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                                                                                        <thead style="background: #302f2f; color:white;">
                                                                                            <tr>
                                                                                                <th>Curp</th>
                                                                                                <th>Nombre</th>
                                                                                                <th>Matrícula</th>
                                                                                                <th>Programa Educativo</th>                                                                                                  
                                                                                                <th>Periodo que cursa</th>
                                                                                                <th>Estatus</th>
                                                                                                <th>UE a vincular</th>
                                                                                                <th>Validar</th>
                                                                                            </tr>
                                                                                        </thead>

                                                                                        <tbody>
                                                                                        <s:iterator value="ListaPreseleccionados" id="ListaPreseleccionados" status="stat">
                                                                                            <s:if test="STATUS_PROCESO==2 || STATUS_PROCESO==3">
                                                                                            <tr>
                                                                                                <td><s:property value="CURP"/></td>
                                                                                                <td><s:property value="NOMBRE_COMPLETO"/></td>
                                                                                                <td><s:property value="MATRICULA"/></td>
                                                                                                <td><s:property value="NOM_PRO_EDU"/></td> 
                                                                                                <td><s:property value="GRADO"/></td>                                                           
                                                                                                <s:if test="STATUS_PROCESO==2">
                                                                                                    <td class="text-warning text-center font-weight-bold"><i class="material-icons">sentiment_dissatisfied</i>Preseleccionado</td>
                                                                                                </s:if>
                                                                                                <s:if test="STATUS_PROCESO==3">
                                                                                                    <td class="text-success text-center font-weight-bold"><i class=" material-icons">sentiment_very_satisfied</i>validado</td>
                                                                                                </s:if>
                                                                                                    <td width="25%">
                                                                                                    <s:select  data-style="select-with-transition"   name="ListaPreseleccionados[%{#stat.index}].ID_UE" id="ID_UE" list="ListaUE"  listKey="ID_IE_UE"  listValue="RAZON_SOCIAL" headerKey="" headerValue="--SELECCIONE--"  cssClass="selectpicker col-lg-12"  />
                                                                                                        
                                                                                                    </td>    
                                                                                                    <td  align="center">                                                                            
                                                                                                        <s:checkbox cssClass="form-check-input" name="ListaPreseleccionados[%{#stat.index}].VALIDAR"  type="checkbox" id="VALIDAR" >  </s:checkbox>
                                                                                                        

                                                                                                    </td>

                                                                                            </tr> 

                                                                                            </tr>
                                                                                            </s:if>   
                                                                                            <s:hidden  name = "ListaPreseleccionados[%{#stat.index}].ID_HISTORICO" id="ID_HISTORICO"></s:hidden>
                                                                                            <s:hidden  name = "ListaPreseleccionados[%{#stat.index}].ID_ALUMNO" id="ID_ALUMNO"></s:hidden>
                                                                                            <s:hidden  name = "ListaPreseleccionados[%{#stat.index}].CURP" id="CURP"></s:hidden>
                                                                                            <s:hidden  name = "ListaPreseleccionados[%{#stat.index}].NOMBRE_COMPLETO" id="NOMBRE_COMPLETO"></s:hidden>
                                                                                            <s:hidden  name = "ListaPreseleccionados[%{#stat.index}].MATRICULA" id="MATRICULA"></s:hidden>
                                                                                            <s:hidden name =  "ListaPreseleccionados[%{#stat.index}].NOM_PRO_EDU" id="NOM_PRO_EDU"></s:hidden>
                                                                                            <s:hidden name =  "ListaPreseleccionados[%{#stat.index}].GRADO" id="GRADO"></s:hidden>
                                                                                            <s:hidden name =  "ListaPreseleccionados[%{#stat.index}].STATUS_PROCESO" id="STATUS_PROCESO"></s:hidden>
                                                                                            

                                                                                        </s:iterator>  
                                                                                        
                                                                                        <s:iterator value="ListaUE" id="ListaUE" status="stat">
                                                                                             <s:hidden  name = "ListaUE[%{#stat.index}].ID_IE_UE" id="ID_IE_UE"></s:hidden>
                                                                                             <s:hidden  name = "ListaUE[%{#stat.index}].RAZON_SOCIAL" id="RAZON_SOCIAL"></s:hidden>
                                                                                        </s:iterator>


                                                                                    </tbody>
                                                                                        
                                                                                          <s:if test="ListaResProgPEInst.size()==0">
                                                                                            </tbody>

                                                                                            <tr >
                                                                                                <td colspan="8"><div style="width: 100%; height: 30px; text-align: center; color: #b11a4f">¡No se encontraron alumnos preseleccionados!</div></td>
                                                                                            </tr>

                                                                                            <tbody>
                                                                                                </s:if>
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                </table>                                              
                                                                            </div> 
                                                                        </div>
                                                                            <div class="form-group col-md-12 text-center">
                                                                                 <s:fielderror fieldName="ERRORUE"  cssClass="alert alert-danger"></s:fielderror>
                                                                            </div>
                                                                            <div class="form-group col-md-12 text-center">
                                                                                <a href="Javascript:evento('actualizarEstatus')"  class="btn btn-round btn-primary">Concluir Validación</a>
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
                                                
                                                
                                                 <s:textfield name="al.ID_ESCUELA" id="al.ID_ESCUELA" cssStyle="display:none;"></s:textfield>
                                                 <s:textfield name="al.ID_ALUMNO" id="id_alumno" cssStyle="display:none;"></s:textfield>
                                                 <s:textfield name="al.ID_HISTORICO" id="id_historico" cssStyle="display:none;"></s:textfield>
                                                 <s:textfield name="al.STATUS_PROCESO" id="status" cssStyle="display:none;"></s:textfield>
                                                
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






                        </body>
                                                                                    
                                                                           
                                                                                    
                                                                                    
                    </s:form>
                    </html>
