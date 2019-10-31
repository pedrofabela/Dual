<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>




<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Periodo', 'Media Superior',{role: 'annotation'}, 'Superior', {role: 'annotation'}],
          
       <s:iterator value="ListaHistDual" id="ListaHistDual" status="stat">
                   ['<s:property value="PERIODO" />',  <s:property value="MSUPERIOR" />, <s:property value="MSUPERIOR" /> ,    <s:property value="SUPERIOR" />, <s:property value="SUPERIOR" />],
      </s:iterator>
         
        
        ]);

        var options = {
          title: 'Histórico de Estudiantes Dual',
          curveType: 'function',
          legend: { position: 'bottom' },
          backgroundColor: '#edecec',
             colors: ['#28a745', '#dc3545','#ffc107'],
             animation: {
                duration: 4500,
                startup: true //This is the new option
            },
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>




<script type="text/javascript">
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day', {role: "style"}],
    <s:iterator value="ListaTotalEstatus" id="ListaTotalEstatus" status="stat">

            ['<s:property value="NOM_ESTATUS"/>', <s:property value="TOTAL_ESTATUS"/>, '<s:if test="NOM_ESTATUS==ACTIVO">red</s:if>'],

    </s:iterator>

                    ]);

                    var options = {

                        backgroundColor: '#edecec',
                        

                    };

                    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

                    chart.draw(data, options);
                }
</script>    


<script type="text/javascript"  charset="UTF-8">

    //GRAFICA REGION
    // Load the Visualization API and the piechart package.

    google.load('visualization', '1.0', {'packages': ['corechart']});
    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);
    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart() {
        // Create the data table.
        var data = google.visualization.arrayToDataTable([
            ['Genre', 'Activos', 'Bajas','Egresados'],

    <s:iterator value="indicadoresTableroNiv" id=" indicadoresTableroNiv" status="stat">
            ['<s:property value="NOMBRE_NIVEL"/>', <s:property value="ACTIVOS_NIV"/>, <s:property value="BAJAS_NIV"/>,<s:property value="EGRESADOS_NIV"/>],

    </s:iterator>

        ]);

        // Set chart options 

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div2'));
        function selectHandler() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
                var topping = data.getValue(selectedItem.row, 0);
                window.location.assign(rutaGraficasOk + 'TipoD?topping=' + topping + '');
            }
        }

        var options = {

            legend: {position: 'top', maxLines: 3, legend: 'left'},
            bar: {groupWidth: '95%'},

            titleTextStyle: {
                color: '#A4A4A4',
                fontSize: 16,
                fontName: 'Arial',

            },

            colors: ['#28a745', '#dc3545','#1464a5'],

            animation: {
                duration: 2500,
                startup: true, //This is the new option
               
            },
            bar: {groupWidth: '60%'},
            isStacked: true,

            textStyle: {
                color: '#1b9e77',
                fontSize: 20,
                fontName: 'Arial',

                italic: true
            },
            backgroundColor: '#edecec',
            annotations: {
                textStyle: {

                    fontSize: 20,

                    // The color of the text.
                    color: '#333333',
                     
                    
                            // The color of the text outline.

                            // The transparency of the text.

                }
            }
            




        };
        google.visualization.events.addListener(chart, 'select', selectHandler);
        chart.draw(data, options);
    }


</script>



<script type="text/javascript">
    google.charts.load("current", {packages: ['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ["Element", "Estudiantes", {role: "style"}],
    <s:iterator value="ListaTotalEsuela" id="ListaTotalEsuela" status="stat">
            ["<s:property value="CCT"/>", <s:property value="TOTAL_CCT"/>, "purple"],
    </s:iterator>



        ]);

        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
            {calc: "stringify",
                sourceColumn: 1,
                type: "string",
                role: "annotation"},
            2]);
        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
            {calc: "stringify",
                sourceColumn: 1,
                type: "string",
                role: "annotation"},
            2]);

        var options = {

            backgroundColor: '#edecec',
            chartArea: {width: '90%'},

            bar: {groupWidth: "85%"},
            legend: {position: "none"},
        };
        var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
        chart.draw(view, options);
    }
</script>

<script type="text/javascript">



    google.charts.load('current', {packages: ['corechart', 'bar']});
    google.charts.setOnLoadCallback(drawBarColors);

    function drawBarColors() {
        var data = google.visualization.arrayToDataTable([
            ['City', 'pedro', {role: 'annotation'}],

            ['Hombre', <s:property value="datos.TOTAL_HOMBRE" />, '<s:property value="datos.TOTAL_HOMBRE" />'],
            ['Mujer', <s:property value="datos.TOTAL_MUJER" />, '<s:property value="datos.TOTAL_MUJER" />'],
        ]);

        var options = {

            chartArea: {width: '50%'},
            colors: ['#b0120a', '#ffab91'],
            hAxis: {
                title: 'Total de Estudiantes',
                minValue: 0,
                textStyle: {
                    fontSize: 10,
                }

            },
            backgroundColor: '#edecec',
            bar: {groupWidth: "85%"},
            vAxis: {
                title: 'Estudiantes DUAL'
            },

        };
        var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }



</script>

<script type="text/javascript">
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day', {role: 'style'}],
    <s:iterator value="ListaTotalEstatusUGeneral" id="ListaTotalEstatusUGeneral" status="stat">

            ['<s:property value="NOM_ESTATUS"/>', <s:property value="TOTAL_ESTATUS"/>, '<s:property value="COLOR"/>'],

    </s:iterator>

        ]);

        var options = {

            backgroundColor: '#edecec',

        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_general'));



        var counter = 0;

        var handler = setInterval(function () {
            counter = counter + 0.01
            options = {

                slices: {
                    3: {offset: counter},
                    4: {offset: counter},
                    5: {offset: counter},

                },

                backgroundColor: '#e7e6e6',
                chartArea: {left: 20, top: 25, width: '85%', height: '80%'},
                legend: {position: 'left', top: 20, width: '45%', textStyle: {fontSize: 13, color: 'black', fontName: 'Didactic'}},

                colors: ['28a745', '#ffc107', '#dc3545', '#893101', '#8B0000', '#DC143C'],

                textStyle: {

                    fontSize: 16,

                    // The color of the text.
                    color: '#848484'
                            // The color of the text outline.

                            // The transparency of the text.

                },

            };
            chart.draw(data, options);

            if (counter > 0.20)
                clearInterval(handler);
        }, 100);
    }
</script>  
<script type="text/javascript">



    google.charts.load('current', {packages: ['corechart', 'bar']});
    google.charts.setOnLoadCallback(drawBarColors);

    function drawBarColors() {
        var data = google.visualization.arrayToDataTable([
            ['City', '', {role: 'annotation'}, {role: 'style'}],

            ['Hombre', <s:property value="indic.HOMBRES"></s:property>, '<s:property value="indic.HOMBRES"></s:property>', '#6754B8'],
            ['Mujer', <s:property value="indic.MUJER"></s:property>, '<s:property value="indic.MUJER"></s:property>', '#2DCCCD'],
        ]);

        var options = {

            chartArea: {width: '50%'},
            colors: ['#ffab91', '#ffab91'],
            hAxis: {
                title: 'Total de Estudiantes',
                minValue: 0,
                textStyle: {
                    fontSize: 10,
                }

            },
            backgroundColor: '#edecec',
            bar: {groupWidth: "85%"},
            vAxis: {
                title: 'Estudiantes DUAL'
            },
            animation: {
                duration: 4500,
                startup: true //This is the new option
            },
            legend: 'none',

        };
        var chart = new google.visualization.BarChart(document.getElementById('chart_div_gen'));
        chart.draw(data, options);
    }



</script>



<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

<script>
    $.datepicker.regional['es'] = {
        closeText: 'Cerrar',
        prevText: '<Ant',
        nextText: 'Sig>',
        currentText: 'Hoy',
        monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
        dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Juv', 'Vie', 'Sáb'],
        dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá'],
        weekHeader: 'Sm',
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''
    };
    $.datepicker.setDefaults($.datepicker.regional['es']);
    $(function () {

        $("#Fecha").datepicker();

    });

    $(function () {
        $("#Fecha1").datepicker();
    });
</script>


<script type="text/javascript">

    function cerrar(accion) {

        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
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
        Registro_Aspirante.jsp

        document.formularioPrincipal.action = accion;

        document.formularioPrincipal.submit();

    }
    function act(accion, programa, escuela, estatus) {

        document.formularioPrincipal.AUXESCUELA.value = escuela;
        document.formularioPrincipal.AUXPLAN.value = programa;
        Registro_Aspirante.jsp
        document.formularioPrincipal.AUXSTATUS.value = estatus;

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

<script>
    var cssLink = document.createElement("assets/css/material-dashboard.css?v=2.1.0");
    cssLink.href = "style.css";
    cssLink.rel = "stylesheet";
    cssLink.type = "text/css";
    frames['miframe'].document.head.appendChild(cssLink);


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

                    <link href="assets/css/material-dashboard.css?v=2.1.0" rel="stylesheet" />

                    <link href="assets/demo/demo.css" rel="stylesheet" />
                    </head>

                    <body class="">
                    <s:form name="formularioPrincipal" id="formularioPrincipal" enctype="multipart/form-data">
                        <div class="wrapper ">
                            <div class="sidebar" data-color="rose" data-background-color="black" data-image="assets/img/sidebar-1.jpg">

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

                                                            <div class="clearfix"></div>

                                                        </div>


                                                        <div class="row"  >


                                                            <div class="form-group col-lg-3" style="padding: none;"  >

                                                                <div style="background: #a2a3b8; width: 100%; height: 100px; padding: 5px; border-radius: 3px; box-shadow: 2px 2px 5px #333; font-size: 14px;">

                                                                    <div style="width: 100%; height: 20px; color: white; text-align:center; ">    

                                                                        Total de Estudiantes 

                                                                    </div>
                                                                    <div style="width: 100%; height: 35px; color: white;   font-size: 30px; text-align: center; margin-top: 17px; ">    

                                                                    <s:property value="indic.TOTAL_ALUMNOS"/> <i class="fas fa-fw fa-wrench" style="position: absolute; z-index: 0; top : -2.5 rem; right: 0.9rem; opacity: 0.4; font-size: 4rem; transform: rotate(15deg)"></i>

                                                                </div>
                                                                <!--      
                                                                  <a href="#popup5" style="text-decoration: none; color:white;" >    <div style="width: 100%; height: 30px;  background: rgba(0, 0, 0, 0.05); padding: 3px; margin-top: 10px; border-top: 0.5px solid #666; font-size: 12px; text-align: left;">    

                                                                          Detalle de Alumnos        ->

                                                                      </div></a>-->
                                                                

                                                            </div>
                                                                    <div style="width: 100%; height: 25px; background: #a2a3b8; border-radius: 0px 0px 5px 5px; color:white; padding: 0px 0px 0px 10px; font-size: 9px; box-shadow: 2px 2px 5px #333; line-height: 10px; padding: 5px 0px 0px 10px; ">

                                                                        Estudiantes que han hecho ED durante el ciclo escolar

                                                                    </div>

                                                        </div>    


                                                        <div class="form-group col-lg-3" style="padding: none;"  >

                                                            <div style="background: #28a745; width: 100%; height: 100px; padding: 5px; border-radius: 3px; box-shadow: 2px 2px 5px #333; font-size: 14px;">

                                                                <div style="width: 100%; height: 20px; color: white; text-align:center; ">    

                                                                    Activos 

                                                                </div>
                                                                <div style="width: 100%; height: 35px; color: white;   font-size: 30px; text-align: center; margin-top: 17px; ">    

                                                                    <s:property value="indic.ACTIVOS"/> <i class="fas fa-fw fa-user" style="position: absolute; z-index: 0; top : -2 rem; right: 0.5rem; opacity: 0.4; font-size: 4rem; transform: rotate(15deg)"></i>

                                                                </div>

                                                                <!--        
                                                                 <a href="#popup6" style="text-decoration: none; color:white;" >      <div style="width: 100%; height: 30px;  background: rgba(0, 0, 0, 0.05); padding: 3px; margin-top: 10px; border-top: 0.5px solid #666; font-size: 12px; text-align: left;">    

                                                                         Detalle de Alumnos        ->

                                                                     </div></a> -->
                                                                
                                                                
                                                                
                                                                

                                                            </div>
                                                                    
                                                              
                                                                    <div style="width: 100%; height: 25px; background: #28a745; border-radius: 0px 0px 5px 5px; color:white; padding: 0px 0px 0px 10px; font-size: 9px; box-shadow: 2px 2px 5px #333; line-height: 10px; padding: 5px 0px 0px 10px;">

                                                                        Estudiantes de ED vigentes en el ciclo escolar

                                                                    </div>      

                                                        </div>  

                                                        <div class="form-group col-lg-3" style="padding: none;"  >

                                                            <div style="background: #dc3545; width: 100%; height: 100px; padding: 5px; border-radius: 3px; box-shadow: 2px 2px 5px #333; font-size: 14px;">

                                                                <div style="width: 100%; height: 20px; color: white; text-align:center; ">    

                                                                    Bajas

                                                                </div>
                                                                <div style="width: 100%; height: 35px; color: white;   font-size: 30px; text-align: center; margin-top: 17px; ">    

                                                                    <s:property value="indic.BAJAS"/> <i class="fas fa-fw fa-thumbs-down" style="position: absolute; z-index: 0; top : -2 rem; right: 0.5rem; opacity: 0.4; font-size: 4rem; transform: rotate(15deg)"></i>

                                                                </div> 
                                                                <!--     
                                                              <a href="#popup2" style="text-decoration: none; color: white;" > <div style="width: 100%; height: 30px;  background: rgba(0, 0, 0, 0.05); padding: 3px; margin-top: 10px; border-top: 0.5px solid #666; font-size: 12px; text-align: left;">    

                                                                      Detalle de Alumnos        ->

                                                                  </div>
                                                              </a> -->
                                                            </div>
                                                                    
                                                                <div style="width: 100%; height: 25px; background: #dc3545; border-radius: 0px 0px 5px 5px; color:white; padding: 0px 0px 0px 10px; font-size: 9px; box-shadow: 2px 2px 5px #333; line-height: 10px; padding: 5px 0px 0px 10px; ">

                                                                       Estudiantes que hicieron ED en el ciclo escolar y causaron baja

                                                                    </div>           
                                                                    

                                                        </div>  

                                                        <div class="form-group col-lg-3" style="padding: none;"  >

                                                            <div style="background: #1464a5; width: 100%; height: 100px; padding: 5px; border-radius: 3px; box-shadow: 2px 2px 5px #333; font-size: 14px;">

                                                                <div style="width: 100%; height: 20px; color: white; text-align:center; ">    

                                                                    Egresados

                                                                </div>
                                                                <div style="width: 100%; height: 35px; color: white;   font-size: 30px; text-align: center; margin-top: 17px;  ">    

                                                                    <s:property value="indic.EGRESADOS"/> <i class="fas fa-fw fa-graduation-cap" style="position: absolute; z-index: 0; top : -2.5 rem; right: 0.9rem; opacity: 0.4; font-size: 4rem; transform: rotate(15deg)"></i>

                                                                </div>

                                                                <!--  
                                                           <a href="#popup3" style="text-decoration: none; color: white;" >    <div style="width: 100%; height: 30px;  background: rgba(0, 0, 0, 0.05); padding: 3px; margin-top: 10px; border-top: 0.5px solid #666; font-size: 12px; text-align: left;">    

                                                                   Detalle de Alumnos        ->

                                                               </div>
                                                           </a> -->


                                                            </div>
                                                                    
                                                                    <div style="width: 100%; height: 25px; background: #1464a5; border-radius: 0px 0px 5px 5px; color:white; padding: 0px 0px 0px 10px; font-size: 9px; box-shadow: 2px 2px 5px #333; line-height: 10px; padding: 5px 0px 0px 10px; ">

                                                                       Estudiantes que hicieron ED en el ciclo escolar y egresaron

                                                                    </div>   


                                                        </div>     

                                                        <!--   <div class="form-group col-lg-2" style="padding: none;"  >

                                                               <div style="background: #008080; width: 100%; height: 100px; padding: 5px; border-radius: 3px; box-shadow: 2px 2px 5px #333; font-size: 14px;">

                                                                   <div style="width: 100%; height: 20px; color: white; text-align:center; ">    

                                                                       Con Beca

                                                                   </div>
                                                                   <div style="width: 100%; height: 35px; color: white;   font-size: 30px; text-align: center; ">    

                                                        <s:property value="datos.TOTAL_BECA_GENERAL"/> <i class="fas fa-fw fa-graduation-cap" style="position: absolute; z-index: 0; top : -2.5 rem; right: 0.9rem; opacity: 0.4; font-size: 4rem; transform: rotate(15deg)"></i>

                                                    </div>
                                                    <a href="#popup4" style="text-decoration: none; color: white;" >    <div style="width: 100%; height: 30px;  background: rgba(0, 0, 0, 0.05); padding: 3px; margin-top: 10px; border-top: 0.5px solid #666; font-size: 12px; text-align: left;">    

                                                            Detalle de Alumnos        ->

                                                        </div>
                                                    </a>


                                                </div>


                                            </div>      -->


                                                        <!--              
                                                           <div class="form-group col-lg-2" style="padding: none;"  >

                                                               <div style="background:blue; width: 100%; height: 100px; padding: 5px; border-radius: 3px; box-shadow: 2px 2px 5px #333; font-size: 14px;">

                                                                   <div style="width: 100%; height: 20px; color: white; text-align:center; ">    

                                                                       Contratados

                                                                   </div>
                                                                   <div style="width: 100%; height: 35px; color: white;   font-size: 30px; text-align: center; ">    

                                                                       0 <i class="fas fa-fw fa-wrench" style="position: absolute; z-index: 0; top : -2.5 rem; right: 0.9rem; opacity: 0.4; font-size: 4rem; transform: rotate(15deg)"></i>

                                                                   </div>
                                                                   <a href="#" style="text-decoration: none; color:white;" >    <div style="width: 100%; height: 30px;  background: rgba(0, 0, 0, 0.05); padding: 3px; margin-top: 10px; border-top: 0.5px solid #666; font-size: 12px; text-align: left;">    

                                                                           Detalle de Alumnos        ->

                                                                       </div></a>

                                                               </div>

                                                           </div>  -->




                                                        <div class="form-group col-lg-6" >


                                                            <div style="width:100%; text-align: center; background: #343a40; color: white; height: 35px; margin-top: 20px; border-radius: 8px 8px 0px 0px;  padding: 5px; ">Estudiantes por género</div>

                                                            <div id="chart_div_gen" style=" height: 240px;"></div>
                                                            
                                                             <div style="width: 100%; height: 25px; background: #343a40; border-radius: 0px 0px 5px 5px; color:white;  font-size: 10px; padding: 0px 0px 0px 10px;">
                                                                
                                                                Estudiantes del ciclo escolar por género
                                                                
                                                            </div>

                                                        </div>    

                                                        <div class="form-group col-lg-6"  >


                                                            <div style="width:100%; text-align: center; background: #343a40; color: white; height: 35px; margin-top: 20px; border-radius: 8px 8px 0px 0px;  padding: 5px; "> Por Nivel Educativo</div>
                                                            <div class="col-sm-auto  " style="background:  #edecec; margin-left: 00px;">


                                                                <div id="chart_div2" style="margin: auto; width: 100%; height: 240px;" ></div>

                                                            </div> 
                                                             <div style="width: 100%; height: 25px; background: #343a40; border-radius: 0px 0px 5px 5px; color:white; font-size: 10px; padding: 0px 0px 0px 10px;">
                                                                
                                                                Estudiantes por nivel educativo y estatus
                                                                
                                                            </div>
                                                            

                                                        </div>  
                                                        
                                                        <div class="form-group col-lg-6"   >


                                                            <div style="width:100%; text-align: center; background: #343a40; color: white; height: 35px; margin-top: 20px; border-radius: 8px 8px 0px 0px;  padding: 5px; ">Estudiantes por Unidad Económica</div>

                                                            <div style="height: 250px; overflow-y: scroll; border:  1px solid #999999;">

                                                                <div id="dvData7">    


                                                                    <table class="table table-hover"   style=" font-size: 14px; width: 100%;">



                                                                        <tr style="color: #333; border-bottom: 2px solid  #999; pointer-events: none; border-top: 2px solid #999; background: #10707f; color:white;">
                                                                          <!--  <td  scope="row" style="width: 10%;" align="center">RFC</td>-->
                                                                            <td style="width: 70%;" align="center">Empresa</td>
                                                                            <td style="width: 10%;" align="center">Activos</td>
                                                                           
                                                                            <td style="width: 10%;" align="center">Bajas</td>
                                                                            <td style="width: 10%;" align="center">Egresados</td>


                                                                        </tr>

                                                                        <s:iterator value="ListaUEEstatus" id="ListaUEEstatus" status="stat">



                                                                            <tr style="  font-size: 12px; color: #333;">
                                                                                <!--<td style="width: 10%;"><s:property value="RFC"/></td>-->
                                                                                <td style="width: 70%;"><s:property value="RAZON_SOCIAL"/></td>
                                                                                <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #28a745; border-radius: 50%; text-align: center; color: white;" ><s:property value="ACTIVO_UE"/></div></td>
                                                                                <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #dc3545; border-radius: 50%; text-align: center; color: white;" ><s:property value="BAJA_UE" /></div></td>
                                                                                <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #1464a5; border-radius: 50%; text-align: center; color: white;" ><s:property value="EGRESADO_UE" /></div></td>




                                                                            </tr>


                                                                        </s:iterator>


                                                                        <s:if test="ListaUEEstatus.size()<=0">

                                                                            <div style="color: #e1173e; font-size: 12px; text-align: center;">No hay información para mostrar </div>


                                                                        </s:if>
                                                                    </table>

                                                                    <!--
                                                                   <center>

                                                                       <input  align="top" type="image" id="btnExport2"  src="images/excel.png" style="width: 80px; margin-top: 20px;"  />


                                                                   </center>-->

                                                                    <script>
                                                                        $("#btnExport2").click(function (e) {
                                                                            window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#dvData7').html()));
                                                                            e.preventDefault();
                                                                        });
                                                                    </script>


                                                                </div>

                                                            </div> 
                                                            
                                                             <div style="width: 100%; height: 25px; background: #343a40; border-radius: 0px 0px 5px 5px; color:white;  font-size: 10px; line-height: 10px; padding: 5px 0px 0px 10px; ">
                                                                
                                                                Unidades Económicas ordenadas  de mayor a menor número  estudiantes activos y egresados
                                                                
                                                            </div>

                                                        </div>


                                                        <div class="form-group col-lg-6" >


                                                            <div style="width:100%; text-align: center; background: #343a40; color: white; height: 35px; margin-top: 20px; border-radius: 8px 8px 0px 0px;  padding: 5px; ">Estudiantes por Sector Productivo </div>

                                                            <div style="height: 250px; overflow-y: scroll; border:  1px solid #999999;">

                                                                <div id="dvData6">    


                                                                    <table class="table table-hover"   style=" font-size: 14px; width: 100%;">


                                                                            <tr style="color: #333; border-bottom: 2px solid  #999; pointer-events: none; border-top: 2px solid #999; background: #2DCCCD; color:white;">
                                                                                <td style="width: 70%;" align="center">Sector</td>
                                                                            <td style="width: 10%;" align="center">Activos</td>
                                                                           
                                                                            <td style="width: 10%;" align="center">Bajas</td>
                                                                            <td style="width: 10%;" align="center">Egresados</td>



                                                                            </tr>

                                                                        <s:iterator value="ListaSecEstatus" id="ListaSecEstatus" status="stat">



                                                                           
                                                                            <tr style="  font-size: 12px; color: #333;">
                                                                                <!--<td style="width: 10%;"><s:property value="RFC"/></td>-->
                                                                                <td style="width: 70%;"><s:property value="NOM_SECTOR"/></td>
                                                                                <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #28a745; border-radius: 50%; text-align: center; color: white;" ><s:property value="ACTIVO_UE"/></div></td>
                                                                                <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #dc3545; border-radius: 50%; text-align: center; color: white;" ><s:property value="BAJA_UE" /></div></td>
                                                                                <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #1464a5; border-radius: 50%; text-align: center; color: white;" ><s:property value="EGRESADO_UE" /></div></td>




                                                                            </tr>


                                                                        </s:iterator>


                                                                        <s:if test="ListaAvanceMetas.size()<=0">

                                                                            <div style="color: #e1173e; font-size: 12px; text-align: center;">No hay información para mostrar </div>


                                                                        </s:if>
                                                                    </table>

                                                                    <!--
                                                                   <center>

                                                                       <input  align="top" type="image" id="btnExport11"  src="images/excel.png" style="width: 80px; margin-top: 20px;"  />


                                                                   </center> -->

                                                                    <script>
                                                                        $("#btnExport11").click(function (e) {
                                                                            window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#dvData6').html()));
                                                                            e.preventDefault();
                                                                        });
                                                                    </script>


                                                                </div>
                                                                
                                                               

                                                            </div> 
                                                            <div style="width: 100%; height: 25px; background: #343a40; border-radius: 0px 0px 5px 5px; color:white;  font-size: 10px; line-height: 10px; padding: 5px 0px 0px 10px;">
                                                                
                                                                Sectores Productivos con mayor estudiantes activos y egresados
                                                            </div>
                                                            

                                                        </div>

                                                        <div class="form-group col-lg-6" >


                                                            <div style="width:100%; text-align: center; background: #343a40; color: white; height: 35px; margin-top: 20px; border-radius: 8px 8px 0px 0px;  padding: 5px; ">Estudiantes por Municipio</div>

                                                            <div style="height: 250px; overflow-y: scroll; border:  1px solid #999999;">

                                                                <div id="dvData9">    

                                                                    <table class="table table-hover"  style=" font-size: 14px; width: 100%;">



                                                                        <tr style="color: #333; border-bottom: 2px solid  #999; pointer-events: none; border-top: 2px solid #999; background: purple; color:white;">
                                                                            <td  scope="row" style="width: 70%;" align="center">Municipio</td>
                                                                            <td style="width: 10%;" align="center">Activos</td>
                                                                            
                                                                            <td style="width: 10%;" align="center">Bajas</td>
                                                                            <td style="width: 10%;" align="center">Egresados</td>



                                                                        </tr>

                                                                        <s:iterator value="ListaMunEstatus" id="ListaMunEstatus" status="stat">



                                                                            <tr style="font-size: 12px; color: #333;">
                                                                                <td style="width: 70%;"><s:property value="MUNICIPIO"/></td>
                                                                                <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #28a745; border-radius: 50%; text-align: center; color: white;" ><s:property value="ACTIVO_UE"/></div></td>
                                                                                <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #dc3545; border-radius: 50%; text-align: center; color: white;" ><s:property value="BAJA_UE" /></div></td>
                                                                                <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #1464a5; border-radius: 50%; text-align: center; color: white;" ><s:property value="EGRESADO_UE" /></div></td>




                                                                            </tr>


                                                                        </s:iterator>


                                                                        <s:if test="ListaMunicipioEscuela.size()<=0">

                                                                            <div style="color: #e1173e; font-size: 12px; text-align: center;">No hay información para mostrar </div>


                                                                        </s:if>
                                                                    </table>

                                                                    <!--
                                                                   <center>

                                                                       <input  align="top" type="image" id="btnExport8"  src="images/excel.png" style="width: 80px; margin-top: 20px;"  />


                                                                   </center>-->

                                                                    <script>
                                                                        $("#btnExport8").click(function (e) {
                                                                            window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#dvData9').html()));
                                                                            e.preventDefault();
                                                                        });
                                                                    </script>

                                                                </div>

                                                            </div> 
                                                            
                                                              <div style="width: 100%; height: 25px; background: #343a40; border-radius: 0px 0px 5px 5px; color:white;  font-size: 10px; padding: 0px 0px 0px 5px;">
                                                                
                                                              Municipios con mayor número de Estudiantes en el ciclo escolar, según su residencia
                                                                
                                                            </div>

                                                        </div>    


                                                        <div class="form-group col-lg-6" >


                                                            <div style="width:100%; text-align: center; background: #343a40; color: white; height: 35px; margin-top: 20px; border-radius: 8px 8px 0px 0px;  padding: 5px; ">Estudiantes por Programa Educativo</div>

                                                            <div style="height: 250px; overflow-y: scroll; border:  1px solid #999999;">

                                                                <div id="dvData8">    

                                                                    <table class="table table-hover"  style=" font-size: 14px; width: 100%;">



                                                                        <tr style="color: #333; border-bottom: 2px solid  #999; pointer-events: none; border-top: 2px solid #999; background: #dc3545; color:white;">

                                                                            <td style="width: 70%;" align="center">Programa Educativo</td>
                                                                            <td style="width: 10%;" align="center">Activos</td>
                                                                            <td style="width: 10%;" align="center">Bajas</td>
                                                                            <td style="width: 10%;" align="center">Egresados</td>




                                                                        </tr>

                                                                        <s:iterator value="ListaPlanEstatus" id="ListaPlanEstatus" status="stat">





                                                                            <td style="width: 70%;"><s:property value="NOM_CARRERA"/></td>
                                                                            <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #28a745; border-radius: 50%; text-align: center; color: white;" ><s:property value="ACTIVO_UE"/></div></td>
                                                                            <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #dc3545; border-radius: 50%; text-align: center; color: white;" ><s:property value="BAJA_UE" /></div></td>
                                                                            <td style="width: 10%;" align="center"><div style="width: 25px; height: 25px; background: #1464a5; border-radius: 50%; text-align: center; color: white;" ><s:property value="EGRESADO_UE" /></div></td>



                                                                            </tr>


                                                                        </s:iterator>


                                                                        <s:if test="ListaMunicipioEscuela.size()<=0">

                                                                            <div style="color: #e1173e; font-size: 12px; text-align: center;">No hay información para mostrar </div>


                                                                        </s:if>
                                                                    </table>

                                                                    <!--
                                                                    <center>

                                                                        <input  align="top" type="image" id="btnExport9"  src="images/excel.png" style="width: 80px; margin-top: 20px;"  />


                                                                    </center> -->

                                                                    <script>
                                                                        $("#btnExport9").click(function (e) {
                                                                            window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#dvData8').html()));
                                                                            e.preventDefault();
                                                                        });
                                                                    </script>



                                                                </div>

                                                            </div> 
                                                              <div style="width: 100%; height: 25px; background: #343a40; border-radius: 0px 0px 5px 5px; color:white;  font-size: 10px; padding: 0px 0px 0px 5px;">
                                                                
                                                               Programas Educativos con mayor número de Estudiantes en el ciclo escolar
                                                                
                                                            </div>


                                                        </div>               


                                                        

                                                        <div class="form-group col-lg-12" >                  



                                                        </div>  





                                                        <!-- indicadores de colores -->    


                                                        <div class="form-group col-lg-3" style="padding: none;"  >

                                                            <div style="background: #007bff; width: 100%; height: 100px; padding: 5px; border-radius: 3px; box-shadow: 2px 2px 5px #333; font-size: 14px;">

                                                                <div style="width: 100%; height: 20px; color: white; text-align:center; ">    

                                                                    Estudiantes de nuevo ingreso

                                                                </div>
                                                                <div style="width: 100%; height: 35px; color: white;   font-size: 30px; text-align: center; margin-top: 12px; ">    

                                                                    <s:property value="indic.NUEVO_INGRESO"/> <i class="fas fa-fw fa-wrench" style="position: absolute; z-index: 0; top : -2.5 rem; right: 0.9rem; opacity: 0.4; font-size: 4rem; transform: rotate(15deg)"></i>

                                                                </div>
                                                                    
                                                                    <a href="#popup11" style="text-decoration: none; color: white;" > <div style="width: 100%; height: 30px;  background: rgba(0, 0, 0, 0.05); padding: 3px;  border-top: 0.5px solid #666; font-size: 12px; text-align: left;">    

                                                                       
                                                                    </div>
                                                                </a>



                                                            </div>
                                                                 

                                                        </div>      


                                                        <div class="form-group col-lg-3" style="padding: none;"  >

                                                            <div style="background: #A5DF00; width: 100%; height: 100px; padding: 5px; border-radius: 3px; box-shadow: 2px 2px 5px #333; font-size: 14px;">

                                                                <div style="width: 100%; height: 20px; color: white; text-align:center; color: #333333; ">    

                                                                    Estudiantes del ciclo anterior

                                                                </div>
                                                                <div style="width: 100%; height: 35px; color: #333333;   font-size: 30px; text-align: center; margin-top: 12px; ">    

                                                                    <s:property value="indic.CONTUINIDAD"/> <i class="fas fa-fw fa-thumbs-down" style="position: absolute; z-index: 0; top : -2 rem; right: 0.5rem; opacity: 0.4; font-size: 4rem; transform: rotate(15deg)"></i>

                                                                </div> 
                                                                <a href="#popup11" style="text-decoration: none; color: #333333;" > <div style="width: 100%; height: 30px;  background: rgba(0, 0, 0, 0.05); padding: 3px;  border-top: 0.5px solid #666; font-size: 12px; text-align: left;">    

                                                                             

                                                                    </div>
                                                                </a>


                                                            </div>


                                                        </div>                



                                                        <div class="form-group col-lg-3" style="padding: none;"  >

                                                            <div style="background: purple; width: 100%; height: 100px; padding: 5px; border-radius: 3px; box-shadow: 2px 2px 5px #333; font-size: 14px;">

                                                                <div style="width: 100%; height: 20px; color: white; text-align:center; ">    

                                                                    Estudiantes con beca

                                                                </div>
                                                                <div style="width: 100%; height: 35px; color: white;   font-size: 30px; text-align: center; margin-top: 12px; ">    

                                                                    0 <i class="fas fa-fw fa-user" style="position: absolute; z-index: 0; top : -2 rem; right: 0.5rem; opacity: 0.4; font-size: 4rem; transform: rotate(15deg)"></i>

                                                                </div>
                                                                <a href="#" style="text-decoration: none; color:white;" >      <div style="width: 100%; height: 30px;  background: rgba(0, 0, 0, 0.05); padding: 3px;  border-top: 0.5px solid #666; font-size: 12px; text-align: left;">    

                                                                        <!-- Detalle de Alumnos        -->

                                                                    </div></a>


                                                            </div>


                                                        </div>  

                                                        <div class="form-group col-lg-3" style="padding: none;"  >

                                                            <div style="background: #008080; width: 100%; height: 100px; padding: 5px; border-radius: 3px; box-shadow: 2px 2px 5px #333; font-size: 14px;">

                                                                <div style="width: 100%; height: 20px; color: white; text-align:center; ">    

                                                                    Estudiantes contratados

                                                                </div>
                                                                <div style="width: 100%; height: 35px; color: white;   font-size: 30px; text-align: center;  margin-top: 12px;">    

                                                                   0 <i class="fas fa-fw fa-graduation-cap" style="position: absolute; z-index: 0; top : -2.5 rem; right: 0.9rem; opacity: 0.4; font-size: 4rem; transform: rotate(15deg)"></i>

                                                                </div>
                                                                <a href="#" style="text-decoration: none; color: white;" >    <div style="width: 100%; height: 30px;  background: rgba(0, 0, 0, 0.05); padding: 3px;  border-top: 0.5px solid #666; font-size: 12px; text-align: left;">    

                                                                        <!-- Detalle de Alumnos        -->

                                                                    </div>
                                                                </a>


                                                            </div>


                                                        </div>          


                                                        <!--  contenedores principlaes-->       


                                                        <!--     <div class="form-group col-lg-6" >


                                                                 <div style="width:100%; text-align: center; background: #343a40; color: white; height: 35px; margin-top: 20px; border-radius: 8px 8px 0px 0px;  padding: 5px; ">Total de Alumnos en DUAL</div>
                                                                  <div id="chart_div" style="margin-bottom: 50px; height: 240px;"></div>

                                                             </div>  

                                                             <div class="form-group col-lg-6"  >


                                                                 <div style="width:100%; text-align: center; background: #343a40; color: white; height: 35px; margin-top: 20px; border-radius: 8px 8px 0px 0px;  padding: 5px; ">Estatus de Alumnos en el periodo</div>
                                                                 <div class="col-sm-auto  " style="background:  #edecec; margin-left: 00px;">


                                                                     <div id="piechart" style="width: 100%; height: 240px; margin: auto; margin-top: 0px;"></div>

                                                                 </div> 

                                                             </div>  -->


                                                        <div class="form-group col-lg-12"  >


                                                            <div style="width:100%; text-align: center; background: #343a40; color: white; height: 35px; margin-top: 20px; border-radius: 8px 8px 0px 0px;  padding: 5px; ">Histórico de Estudiantes Dual  </div>
                                                            <div class="col-sm-auto  " style="background:  #edecec; margin-left: 00px;">

                                                                <div id="curve_chart" style="width: 100%; height: 300px;"></div>



                                                            </div> 
                                                            
                                                             <div style="width: 100%; height: 25px; background: #343a40; border-radius: 0px 0px 5px 5px; color:white; padding: 0px 0px 0px 10px; font-size: 10px;">
                                                                
                                                                Este ciclo escolar se encuentra en proceso por lo que las cifras tienden a incrementarse
                                                                
                                                            </div>    

                                                        </div>  

                                                    <!--    <div style="margin: auto; width: 120px; background: green; color: white; text-align: center; margin-bottom: 20px; border-radius: 20px;"><a href="javascript:imprSelec('wrapper2')" style="text-decoration: none; color: white; text-align: center;">Imprimir</a></div>

                                                        -->

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
                                                <s:textfield name="escuela.AUXIDCCTPLAN" id="AUXIDCCTPLAN" cssStyle="display:none;"></s:textfield>



                                                <s:hidden name="bnprog" value="%{bnprog}"></s:hidden>
                                                <s:hidden name="bnprogno" value="%{bnprogno}"></s:hidden>

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
