<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>



<script type="text/javascript">

    function rfcValido(rfc, aceptarGenerico = true) {
        const re = /^([A-ZÑ&]{3,4}) ?(?:- ?)?(\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\d|3[01])) ?(?:- ?)?([A-Z\d]{2})([A\d])$/;
        var validado = rfc.match(re);

        if (!validado)  //Coincide con el formato general del regex?
            return false;

        //Separar el dígito verificador del resto del RFC
        const digitoVerificador = validado.pop(),
                rfcSinDigito = validado.slice(1).join(''),
                len = rfcSinDigito.length,
                //Obtener el digito esperado
                diccionario = "0123456789ABCDEFGHIJKLMN&OPQRSTUVWXYZ Ñ",
                indice = len + 1;
        var suma,
                digitoEsperado;

        if (len == 12)
            suma = 0
        else
            suma = 481; //Ajuste para persona moral

        for (var i = 0; i < len; i++)
            suma += diccionario.indexOf(rfcSinDigito.charAt(i)) * (indice - i);
        digitoEsperado = 11 - suma % 11;
        if (digitoEsperado == 11)
            digitoEsperado = 0;
        else if (digitoEsperado == 10)
            digitoEsperado = "A";

        //El dígito verificador coincide con el esperado?
        // o es un RFC Genérico (ventas a público general)?
        if ((digitoVerificador != digitoEsperado)
                && (!aceptarGenerico || rfcSinDigito + digitoVerificador != "XAXX010101000"))
            return false;
        else if (!aceptarGenerico && rfcSinDigito + digitoVerificador == "XEXX010101000")
            return false;
        return rfcSinDigito + digitoVerificador;
    }


//Handler para el evento cuando cambia el input
// -Lleva la RFC a mayúsculas para validarlo
// -Elimina los espacios que pueda tener antes o después
    function validarInput(input, accion) {

        var rfc = input.value.trim().toUpperCase(),
                resultado = document.getElementById("resultado"),
                valido;

        var rfcCorrecto = rfcValido(rfc);   // ⬅️ Acá se comprueba

        if (rfcCorrecto) {
            valido = "Válido";
            resultado.classList.add("ok");

        } else {
            valido = "No válido"
            resultado.classList.remove("ok");
        }

        resultado.innerText = "\nFormato de RFC: " + valido;


        if (valido == "Válido") {

            document.formularioPrincipal.action = accion;
            document.formularioPrincipal.submit();

        }
    }

    function validarInput2(input) {

        var rfc = input.value.trim().toUpperCase(),
                resultado2 = document.getElementById("resultado2"),
                valido;

        var rfcCorrecto = rfcValido(rfc);   // ⬅️ Acá se comprueba

        if (rfcCorrecto) {
            valido = "Válido";
            document.formularioPrincipal.rfcvalidado.value = valido;
            resultado2.classList.add("ok");

        } else {
            valido = "No válido"
            document.formularioPrincipal.rfcvalidado.value = valido;
            resultado2.classList.remove("ok");
        }

        resultado2.innerText = "\nFormato de RFC: " + valido;
    }


    function validarFormulario() {



        resultado2 = document.getElementById("resultado2");
        var rfc = document.getElementById('RFCR').value;
        var razsoc = document.getElementById('RAZON_SOCIAL').value;
        var nomcomer = document.getElementById('NOM_COMER').value;
        var giro = document.getElementById('GIRO').value;
        var sector = document.getElementById('SECTOR').value;
        var dom = document.getElementById('DOMICILIO').value;
        var cp = document.getElementById('CP').value;
        var col = document.getElementById('COLONIA').value;
        var loc = document.getElementById('LOCALIDAD').value;
        var mun = document.getElementById('MUNICIPIO').value;
        var valor = document.getElementById('VALOR').value;


        //*********VAR SUCURSAL




        //alert('ERROR: El campo razon social no debe ir vacío.');



        var rfcCorrecto = rfcValido(rfc);   // ⬅️ Acá se comprueba



        if (rfcCorrecto) {
            valido = "Válido";
            document.formularioPrincipal.rfcvalidado.value = valido;
            resultado2.classList.add("ok");


        } else {
            valido = "No válido"
            document.formularioPrincipal.rfcvalidado.value = valido;
            resultado2.classList.remove("ok");
            return false;
        }

        resultado2.innerText = "\nFormato de RFC: " + valido;





        if (razsoc == null || razsoc.length == 0 || /^\s+$/.test(razsoc)) {
            //alert('ERROR: El campo razon social no debe ir vacío.');
            document.getElementById('RAZON_SOCIAL').style.boxShadow = "0px 1px 5px red";
            document.getElementById('RAZON_SOCIAL').focus();
            return false;
        } else {
            document.getElementById('RAZON_SOCIAL').style.boxShadow = "";
        }

        if (nomcomer == null || nomcomer.length == 0 || /^\s+$/.test(nomcomer)) {
            //alert('ERROR: El campo razon social no debe ir vacío.');
            document.getElementById('NOM_COMER').style.boxShadow = "0px 1px 5px red";
            document.getElementById('NOM_COMER').focus();
            return false;
        } else {
            document.getElementById('NOM_COMER').style.boxShadow = "";
        }

        if (giro == null || giro.length == 0 || /^\s+$/.test(giro)) {
            //alert('ERROR: El campo giro no debe ir vacío.');
            document.getElementById('GIRO').style.boxShadow = "0px 1px 5px red";
            document.getElementById('GIRO').focus();
            return false;
        } else {
            document.getElementById('GIRO').style.boxShadow = "";
        }
        if (sector == null || sector.length == 0 || /^\s+$/.test(sector)) {
            //alert('ERROR: El campo sector no debe ir vacío.');
            document.getElementById('SECTOR').style.boxShadow = "0px 1px 5px red";
            document.getElementById('SECTOR').focus();
            return false;
        } else {
            document.getElementById('SECTOR').style.boxShadow = "";
        }
        if (dom == null || dom.length == 0 || /^\s+$/.test(dom)) {
            //alert('ERROR: El campo domicilio no debe ir vacío.');
            document.getElementById('DOMICILIO').style.boxShadow = "0px 1px 5px red";
            document.getElementById('DOMICILIO').focus();
            return false;
        } else {
            document.getElementById('DOMICILIO').style.boxShadow = "";
        }
        if (cp == null || cp.length == 0 || /^\s+$/.test(cp)) {
            //alert('ERROR: El campo código postal no debe ir vacío.');
            document.getElementById('CP').style.boxShadow = "0px 1px 5px red";
            document.getElementById('CP').focus();
            return false;
        } else {
            document.getElementById('CP').style.boxShadow = "";
        }
        if (col == null || col.length == 0 || /^\s+$/.test(col)) {
            //alert('ERROR: El campo colonia no debe ir vacío.');
            document.getElementById('COLONIA').style.boxShadow = "0px 1px 5px red";
            document.getElementById('COLONIA').focus();
            return false;
        } else {
            document.getElementById('COLONIA').style.boxShadow = "";
        }
        if (loc == null || loc.length == 0 || /^\s+$/.test(loc)) {
            //alert('ERROR: El campo localidad no debe ir vacío.');
            document.getElementById('LOCALIDAD').style.boxShadow = "0px 1px 5px red";
            document.getElementById('LOCALIDAD').focus();
            return false;
        } else {
            document.getElementById('LOCALIDAD').style.boxShadow = "";
        }
        if (mun == null || mun.length == 0 || /^\s+$/.test(mun)) {
            //alert('ERROR: El campo municipio no debe ir vacío.');
            document.getElementById('MUNICIPIO').style.boxShadow = "0px 1px 5px red";
            document.getElementById('MUNICIPIO').focus();
            return false;
        } else {
            document.getElementById('MUNICIPIO').style.boxShadow = "";
        }


        //alert("ENTRO A VALIDAR");

        if (valor == 'SIADD') {

            var opcion = document.getElementById("OPCION").selectedIndex;




            if (opcion == 0) {
                //alert('ERROR: debe elegir una opción.');

                document.getElementById('OPCION').focus();
                return false;
            }

            //**********VAR PLANTEL
            //alert("OPCION: "+opcion);


            //**********VAR SUCURSAL





            if (opcion == 1) {

                var cct_pla = document.getElementById("CCT_PLA").value;
                var nom_pla = document.getElementById("NOM_PLA").value;
                var dom_pla = document.getElementById("DOM_PLA").value;
                var cp_pla = document.getElementById("CP_PLA").value;
                var colonia_pla = document.getElementById("COLONIA_PLA").value;

                if (cct_pla == null || cct_pla.length == 0 || /^\s+$/.test(cct_pla)) {
                    //alert('ERROR: El campo razon social no debe ir vacío.');
                    document.getElementById('CCT_PLA').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('CCT_PLA').focus();
                    return false;
                } else {
                    document.getElementById('CCT_PLA').style.boxShadow = "";
                }
                if (nom_pla == null || nom_pla.length == 0 || /^\s+$/.test(nom_pla)) {
                    //alert('ERROR: El campo razon social no debe ir vacío.');
                    document.getElementById('NOM_PLA').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('NOM_PLA').focus();
                    return false;
                } else {
                    document.getElementById('NOM_PLA').style.boxShadow = "";
                }
                if (dom_pla == null || dom_pla.length == 0 || /^\s+$/.test(dom_pla)) {
                    //alert('ERROR: El campo razon social no debe ir vacío.');
                    document.getElementById('DOM_PLA').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('DOM_PLA').focus();
                    return false;
                } else {
                    document.getElementById('DOM_PLA').style.boxShadow = "";
                }
                if (cp_pla == null || cp_pla.length == 0 || /^\s+$/.test(cp_pla)) {
                    //alert('ERROR: El campo razon social no debe ir vacío.');
                    document.getElementById('CP_PLA').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('CP_PLA').focus();
                    return false;
                } else {
                    document.getElementById('CP_PLA').style.boxShadow = "";
                }
                if (colonia_pla == null || colonia_pla.length == 0 || /^\s+$/.test(colonia_pla)) {
                    //alert('ERROR: El campo razon social no debe ir vacío.');
                    document.getElementById('COLONIA_PLA').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('COLONIA_PLA').focus();
                    return false;
                } else {
                    document.getElementById('COLONIA_PLA').style.boxShadow = "";
                }

            } else if (opcion == 2) {

                //alert("entro a 2");

                var nom_suc = document.getElementById("NOM_SUC").value;
                var dom_suc = document.getElementById("DOM_SUC").value;
                var cp_suc = document.getElementById("CP_SUC").value;


                if (nom_suc == null || nom_suc.length == 0 || /^\s+$/.test(nom_suc)) {
                    //alert('ERROR: El campo razon social no debe ir vacío.');
                    document.getElementById('NOM_SUC').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('NOM_SUC').focus();
                    return false;
                } else {
                    document.getElementById('NOM_SUC').style.boxShadow = "";
                }
                if (dom_suc == null || dom_suc.length == 0 || /^\s+$/.test(dom_suc)) {
                    //alert('ERROR: El campo razon social no debe ir vacío.');
                    document.getElementById('DOM_SUC').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('DOM_SUC').focus();
                    return false;
                } else {
                    document.getElementById('DOM_SUC').style.boxShadow = "";
                }
                if (cp_suc == null || cp_suc.length == 0 || /^\s+$/.test(cp_suc)) {
                    //alert('ERROR: El campo razon social no debe ir vacío.');
                    document.getElementById('CP_SUC').style.boxShadow = "0px 1px 5px red";
                    document.getElementById('CP_SUC').focus();
                    return false;
                } else {
                    document.getElementById('CP_SUC').style.boxShadow = "";

                }


            }

        }


        return true;

    }

    function validaPlan() {

        var cct = document.getElementById('CCT_PLA').value;
        var nom_pla = document.getElementById('NOM_PLA').value;
        var dom_pla = document.getElementById('DOM_PLA').value;
        var cp_pla = document.getElementById('CP_PLA').value;
        var colonia_pla = document.getElementById('COLONIA_PLA').value;
        var mun_pla = document.getElementById('MUN_PLA').value;



        if (cct == null || cct.length == 0 || /^\s+$/.test(cct)) {
            //alert('ERROR: El campo razon social no debe ir vacío.');
            document.getElementById('CCT_PLA').style.boxShadow = "0px 1px 5px red";
            document.getElementById('CCT_PLA').focus();
            return false;
        } else {
            document.getElementById('CCT_PLA').style.boxShadow = "";
        }

        if (nom_pla == null || nom_pla.length == 0 || /^\s+$/.test(nom_pla)) {
            //alert('ERROR: El campo razon social no debe ir vacío.');
            document.getElementById('NOM_PLA').style.boxShadow = "0px 1px 5px red";
            document.getElementById('NOM_PLA').focus();
            return false;
        } else {
            document.getElementById('NOM_PLA').style.boxShadow = "";
        }

        if (dom_pla == null || dom_pla.length == 0 || /^\s+$/.test(dom_pla)) {
            //alert('ERROR: El campo giro no debe ir vacío.');
            document.getElementById('DOM_PLA').style.boxShadow = "0px 1px 5px red";
            document.getElementById('DOM_PLA').focus();
            return false;
        } else {
            document.getElementById('DOM_PLA').style.boxShadow = "";
        }
        if (cp_pla == null || cp_pla.length == 0 || /^\s+$/.test(cp_pla)) {
            //alert('ERROR: El campo sector no debe ir vacío.');
            document.getElementById('CP_PLA').style.boxShadow = "0px 1px 5px red";
            document.getElementById('CP_PLA').focus();
            return false;
        } else {
            document.getElementById('CP_PLA').style.boxShadow = "";
        }
        if (colonia_pla == null || colonia_pla.length == 0 || /^\s+$/.test(colonia_pla)) {
            //alert('ERROR: El campo domicilio no debe ir vacío.');
            document.getElementById('COLONIA_PLA').style.boxShadow = "0px 1px 5px red";
            document.getElementById('COLONIA_PLA').focus();
            return false;
        } else {
            document.getElementById('COLONIA_PLA').style.boxShadow = "";
        }
        if (mun_pla == null || mun_pla.length == 0 || /^\s+$/.test(mun_pla)) {
            //alert('ERROR: El campo código postal no debe ir vacío.');
            document.getElementById('MUN_PLA').style.boxShadow = "0px 1px 5px red";
            document.getElementById('MUN_PLA').focus();
            return false;
        } else {
            document.getElementById('MUN_PLA').style.boxShadow = "";
        }



        return true;

    }

    function validaSuc() {


        var nom_suc = document.getElementById('NOM_SUC').value;
        var dom_suc = document.getElementById('DOM_SUC').value;
        var cp_suc = document.getElementById('CP_SUC').value;
        var colonia_suc = document.getElementById('COLONIA_SUC').value;
        var mun_suc = document.getElementById('MUN_SUC').value;





        if (nom_suc == null || nom_suc.length == 0 || /^\s+$/.test(nom_suc)) {
            //alert('ERROR: El campo razon social no debe ir vacío.');
            document.getElementById('NOM_SUC').style.boxShadow = "0px 1px 5px red";
            document.getElementById('NOM_SUC').focus();
            return false;
        } else {
            document.getElementById('NOM_SUC').style.boxShadow = "";
        }

        if (dom_suc == null || dom_suc.length == 0 || /^\s+$/.test(dom_suc)) {
            //alert('ERROR: El campo giro no debe ir vacío.');
            document.getElementById('DOM_SUC').style.boxShadow = "0px 1px 5px red";
            document.getElementById('DOM_SUC').focus();
            return false;
        } else {
            document.getElementById('DOM_SUC').style.boxShadow = "";
        }
        if (cp_suc == null || cp_suc.length == 0 || /^\s+$/.test(cp_suc)) {
            //alert('ERROR: El campo sector no debe ir vacío.');
            document.getElementById('CP_SUC').style.boxShadow = "0px 1px 5px red";
            document.getElementById('CP_SUC').focus();
            return false;
        } else {
            document.getElementById('CP_SUC').style.boxShadow = "";
        }
        if (colonia_suc == null || colonia_suc.length == 0 || /^\s+$/.test(colonia_suc)) {
            //alert('ERROR: El campo domicilio no debe ir vacío.');
            document.getElementById('COLONIA_SUC').style.boxShadow = "0px 1px 5px red";
            document.getElementById('COLONIA_SUC').focus();
            return false;
        } else {
            document.getElementById('COLONIA_SUC').style.boxShadow = "";
        }
        if (mun_suc == null || mun_suc.length == 0 || /^\s+$/.test(mun_suc)) {
            //alert('ERROR: El campo código postal no debe ir vacío.');
            document.getElementById('MUN_SUC').style.boxShadow = "0px 1px 5px red";
            document.getElementById('MUN_SUC').focus();
            return false;
        } else {
            document.getElementById('MUN_SUC').style.boxShadow = "";
        }



        return true;

    }






    function cerrar(accion) {

        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();
    }
    
   
    
    
    function enviar(accion) {

        var rfc = document.getElementById("rfc_input").value;

        if (rfc.length > 0) {

            document.formularioPrincipal.action = accion;
            document.formularioPrincipal.submit();
        } else {
            alert("Favor de capturar el RFC de la empresa");
            captura();
        }
        function captura() {
            document.getElementById("rfc_input").style.backgroundColor = "#e8f0fe";
            document.getElementById("rfc_input").focus();
        }


    }

    function AgregarRFC(accion) {

        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }

    function agregarUE(accion, id_suc) {
        document.formularioPrincipal.id_suc.value = id_suc;
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }
    
        function ActualizarUE(accion, id_suc) {
        document.formularioPrincipal.id_suc.value = id_suc;
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }

    function Activa(accion, id_ue_ie, status) {

        document.formularioPrincipal.id_ue_ie.value = id_ue_ie;
        document.formularioPrincipal.status.value = status;
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }

    function elimina(accion, id_ue_ie) {

        document.formularioPrincipal.id_ue_ie.value = id_ue_ie;
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }

    function GuardaUE(accion) {



        if (validarFormulario()) {
            document.formularioPrincipal.action = accion;
            document.formularioPrincipal.submit();

        }




    }
    
        function actualizarSucPlan(accion,suc) {

        document.formularioPrincipal.idSucPlantel.value = suc;
            document.formularioPrincipal.action = accion;
            document.formularioPrincipal.submit();




    }

    function MuestraForm(accion) {


        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }

    function ConsultaCP(accion) {
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }

    function ConsultaSubsector(accion) {
        document.formularioPrincipal.action = accion;
        document.formularioPrincipal.submit();

    }

    function actualizarPlantel(accion) {

        if (validaPlan()) {
            document.formularioPrincipal.action = accion;
            document.formularioPrincipal.submit();

        }


    }

    function actualizarSucursal(accion) {

        if (validaSuc()) {
            document.formularioPrincipal.action = accion;
            document.formularioPrincipal.submit();

        }


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

<style>

    #resultado {

        color: red;
        font-weight: bold;
    }
    #resultado.ok {

        color: green;
        font-weight: bold;
    }

    #resultado2 {

        color: red;
        font-weight: bold;
    }
    #resultado2.ok {

        color: green;
        font-weight: bold;
    }

</style>



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
                            <s:hidden name="actSuc" id="actSuc"></s:hidden>
                            <s:hidden name="actPlantel" id="actPlantel"></s:hidden>
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

                                                                        <h3> Actualizaci&oacute;n de unidades económicas</h3>
                                                                    </div>



                                                                    <div style="width: 90%; margin: auto; " >

                                                                        <div class="card-body">

                                                                            <div class="row" style="margin-top: 20px;">
                                                                                <div class="col-md-12">

                                                                                    <div class="form-group col-md-12">
                                                                                        <label for="exampleEmail" class="bmd-label-floating col-lg-12 font-weight-bold ">Ingrese el RFC de la unidad económica </label>

                                                                                    <s:textfield name="ue.RFCAUX" id="rfc_input" cssClass="form-control " oninput="validarInput(this,'ConsultaRFCA')" ></s:textfield>
                                                                                        <pre id="resultado"></pre>
                                                                                    <s:fielderror fieldName="CURP" id="CURP" ></s:fielderror>
                                                                                    </div>
                                                                                    <div class="form-group">
                                                                                        <a href="Javascript:enviar('ConsultaRFCA')" class="btn btn-rose pull-left">Consultar</a>
                                                                                        <div class="clearfix"></div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>                                                                                     

                                                                    <s:if test="bnUENO">
                                                                        <div class="form-inline ">
                                                                            <div class="col-lg-8 text-center text-danger font-weight-bold" style=" height: 40px; padding: 5px; margin: auto;">
                                                                                ¡No se encontr&oacute; informaci&oacute;n, favor de buscar nuevamente!                                                                                                                                                                                                                       
                                                                            </div>                                                                                  
                                                                        </div>
                                                                    </s:if>            




                                                                    <s:if test="bnYaRegistrado">
                                                                        <div class="col-lg-12 text-center text-danger font-weight-bold" style=" height: 30px; padding: 5px; margin: auto;">¡La unidad económica que intenta agregar ya se encuentra vinculada a esta institución educativa!</div>
                                                                    </s:if> 
                                                                    <s:if test="PlantelYaRegistrado">
                                                                        <div class="col-lg-12 text-center text-danger font-weight-bold" style=" height: 30px; padding: 5px; margin: auto;">¡El plantel que desea agregar para esta unidad económica ya se encuentra registrado!</div>
                                                                    </s:if>    
                                                                    <s:if test="SucYaRegistrado">
                                                                        <div class="col-lg-12 text-center text-danger font-weight-bold" style=" height: 30px; padding: 5px; margin: auto;">¡La sucursal que desea agregar para esta unidad económica ya se encuentra registrada!</div>
                                                                    </s:if>                    

                                                                    <!-- UE REGISTRDA-->                      
                                                                    <s:if test="UERegistrada">  
                                                                        <s:hidden name="UERegistrada" id="%{UERegistrada}"></s:hidden>
                                                                        <s:hidden name="ue.TIPO_CON" id="ue.TIPO_CON"></s:hidden>
                                                                            <div class="col-md-12" >
                                                                                <div class="card">
                                                                                    <div class="card-header card-header-primary card-header-icon">
                                                                                        <div class="card-icon">
                                                                                            <i class="material-icons">domain</i>
                                                                                        </div>
                                                                                        <h4 class="card-title">Informaci&oacute;n de la unidad econ&oacute;mica</h4>
                                                                                    </div>
                                                                                    <div class="card-body">
                                                                                        <div class="row">
                                                                                            <div class="col-lg-4">
                                                                                                <label  for="RFC">ID UE</label>                                                                                       
                                                                                            <s:textfield  cssClass="form-control" name="ue.ID_UE" id="ue.ID_UE" maxLength="20" readonly="true" ></s:textfield>
                                                                                            <s:fielderror fieldName="ErrorID" cssClass="alert alert-danger"></s:fielderror>
                                                                                                <pre id="resultado2"></pre>
                                                                                            </div>
                                                                                            <div class="col-lg-4">
                                                                                                <label  for="RFC">RFC:</label>                                                                                       
                                                                                            <s:textfield  cssClass="form-control" name="ue.RFC" id="RFC" maxLength="20" readonly="true" ></s:textfield>
                                                                                            <s:fielderror fieldName="ErrorRFC" cssClass="alert alert-danger"></s:fielderror>
                                                                                                <pre id="resultado2"></pre>
                                                                                            </div>
                                                                                            <div class="col-lg-4 ">
                                                                                                <label   for="RAZON">RAZÓN SOCIAL:</label>
                                                                                            <s:textfield  cssClass="form-control" name="ue.RAZON_SOCIAL" id="RAZON_SOCIAL"  ></s:textfield>
                                                                                            <s:fielderror fieldName="ErrorRS" cssClass="alert alert-danger"/>

                                                                                        </div>
                                                                                        <div class="col-lg-4 ">
                                                                                            <label   for="RAZON">NOMBRE COMERCIAL</label>
                                                                                            <s:textfield  cssClass="form-control" name="ue.NOMBRE_COMER" id="NOM_COMER"  ></s:textfield>
                                                                                            <s:fielderror fieldName="ErrorRS" cssClass="alert alert-danger"/>

                                                                                        </div>
                                                                                        <div class="col-lg-6">
                                                                                            <label for="SECTOR">SECTOR:</label>
                                                                                            <s:select  data-style="select-with-transition"   name="ue.SECTOR" id="SECTOR" list="ListaSector"  listKey="ID_SECTOR"  listValue="NOM_SECTOR" headerKey=""  headerValue="--SELECCIONE--"  cssClass="selectpicker col-lg-12" onchange="Javascript:ConsultaSubsector('ConsultaSubsectorA')" />                                                                                                                
                             


                                                                                            </div>   

                                                                                            <div class="col-lg-6">
                                                                                                <label  for="GIRO">SUBSECTOR:</label>

                                                                                            <s:select  data-style="select-with-transition"   name="ue.GIRO" id="GIRO" list="ListaSubSector"  listKey="ID_SUBSECTOR"  listValue="NOM_SUBSECTOR" headerKey="" headerValue="--SELECCIONE--"  cssClass="selectpicker col-lg-12"  />


                                                                                            </div>   
                                                                                            <div class="col-lg-4">
                                                                                                <label  for="Domicilio">DOMICILIO:</label>

                                                                                            <s:textfield  cssClass="form-control" name="ue.DOMICILIO" id="DOMICILIO" ></s:textfield>
                                                                                            <s:fielderror fieldName="ErrorDomicilioE" cssClass="alert alert-danger"/>

                                                                                        </div>       
                                                                                        <div class=" col-md-4">

                                                                                            <label for="examplePass">CÓDIGO POSTAL</label>
                                                                                            <s:textfield cssClass="form-control" name="ue.CP" id="CP" onchange="Javascript:ConsultaCP('ConsultaCPA')" maxLength="5" />
                                                                                            <s:fielderror fieldName="NoCP" cssClass="alert alert-danger"></s:fielderror>
                                                                                            <s:fielderror fieldName="CP" cssClass="alert alert-danger"></s:fielderror>


                                                                                            </div>

                                                                                            <div class=" col-md-4">
                                                                                                <label for="examplePass">COLONIA</label>
                                                                                            <s:select  data-style="select-with-transition"   name="ue.COLONIA" id="COLONIA" list="ListaColonia"  listKey="COLONIA_AUX"  listValue="COLONIA_AUX" headerValue="COLONIA"  cssClass="selectpicker "  />
                                                                                            <s:iterator value="ListaColonia" id="ListaColonia" status="stat">                        
                                                                                                <s:hidden  name = "ListaColonia[%{#stat.index}].COLONIA_AUX" id="COLONIA_AUX"></s:hidden>
                                                                                            </s:iterator>

                                                                                            <s:fielderror fieldName="COLONIA" cssClass="alert alert-danger"></s:fielderror>
                                                                                            </div> 
                                                                                            <div class="col-lg-4">
                                                                                                <label  for="Domicilio">LOCALIDAD:</label>

                                                                                            <s:textfield  cssClass="form-control" name="ue.LOCALIDAD" id="LOCALIDAD"  ></s:textfield>
                                                                                            <s:fielderror fieldName="ErrorLocalidad" cssClass="alert alert-danger"/>

                                                                                        </div>  
                                                                                        <div class=" col-md-4">
                                                                                            <label for="examplePass" >MUNICIPIO</label>
                                                                                            <s:textfield cssClass="form-control" name="ue.MUNICIPIO" id="MUNICIPIO"  />
                                                                                            <s:fielderror fieldName="MUNICIPIO" cssClass="alert alert-danger"></s:fielderror>
                                                                                            </div>
                                                                                        </div> 
                                                                                        &nbsp;
                                                                                        &nbsp;
                               
                                                                                        <div class=" col-md-12 text-center">
                                                                                            <a class="btn btn-round btn-rose"  href="Javascript:ActualizarUE('ActualizarUE')">Actualizar</a>
                                                                                        </div> 
                                                                                        <s:hidden  name = "idSucPlantel" id="idSucPlantel"></s:hidden>
                                                                                        <s:fielderror fieldName="actualiza" cssClass="alert alert-danger"/>



                                                                                    



                                                                                    <br/>
                                                                                    <br/>                                                                                                      

                                                                                    <s:if test="ListaSucursales.size()>0">
                                                                                        <div class="col-lg-12 col-md-12 col-sm-12 text-center text-primary "><h4>SUCURSALES O PLANTELES REGISTRADOS PARA LA UNIDAD ECONÓMICA</h4> </div>    
                                                                                        <div class="col-md-12">

                                                                                            <div style="height: 200px; overflow-y: scroll;">
                                                                                                <table id="datatables" class="table table-striped">
                                                                                                    <thead class="text-center" style="background: #302f2f; color:white;">
                                                                                                        <tr >
                                                                                                            <th>NOMBRE</th>                                                                       
                                                                                                            <th>MUNICIPIO</th>
                                                                                                            <th>TIPO</th>
                                                                                                            <th>CCT DEL PLANTEL</th>
                                                                                                            <th>MODIFICAR</th>
                                                                                                        </tr>
                                                                                                    </thead>

                                                                                                    <tbody>
                                                                                                        <s:iterator value="ListaSucursales" id="ListaSucursales" status="stat">                                                                                                                                      
                                                                                                            <tr class="font-weight-bold text-center">
                                                                                                                <td><s:property value="NOM_SUC_CON"/></td>
                                                                                                                <td><s:property value="MUNICIPIO_SUC_CON"/></td>
                                                                                                                <td>
                                                                                                                    <s:if test="TIPO_CON==1">
                                                                                                                        PLANTEL
                                                                                                                    </s:if>
                                                                                                                    <s:if test="TIPO_CON==2">
                                                                                                                        SUCURSAL
                                                                                                                    </s:if>    

                                                                                                                </td>
                                                                                                                <td><s:property value="CCT_SUC_CON"/></td>

                                                                                                                <td>
                                                                                                                    <a href="Javascript:actualizarSucPlan('ActualizarSucPlan','<s:property value="ID_SUC_CON"/>')" style="text-decoration: none; "><i class="material-icons" style="color:green;">autorenew</i></a>
                                                                                                                </td>
                                                                                                            </tr>

                                                                                                            <s:hidden  name = "ListaSucursales[%{#stat.index}].ID_SUC_CON" id="ID_SUC_CON"></s:hidden>
                                                                                                            <s:hidden  name = "ListaSucursales[%{#stat.index}].NOM_SUC_CON" id="NOM_SUC_CON"></s:hidden>
                                                                                                            <s:hidden  name = "ListaSucursales[%{#stat.index}].DOM_SUC_CON" id="DOM_SUC_CON"></s:hidden>
                                                                                                            <s:hidden  name = "ListaSucursales[%{#stat.index}].CP_SUC_CON" id="CP_SUC_CON"></s:hidden>
                                                                                                            <s:hidden  name = "ListaSucursales[%{#stat.index}].COLONIA_SUC_CON" id="COLONIA_SUC_CON"></s:hidden>
                                                                                                            <s:hidden name =  "ListaSucursales[%{#stat.index}].MUNICIPIO_SUC_CON" id="MUNICIPIO_SUC_CON"></s:hidden>
                                                                                                            <s:hidden name =  "ListaSucursales[%{#stat.index}].CCT_SUC_CON" id="CCT_SUC_CON"></s:hidden>
                                                                                                            <s:hidden name = "ListaSucursales[%{#stat.index}].TIPO_CON" id="TIPO_CON"></s:hidden>

                                                                                                        </s:iterator>  



                                                                                                    </tbody>
                                                                                                </table>                                                  
                                                                                            </div>

                                                                                        </div>
                                                                                    </s:if>
                                                                                        <br/><br/>
                                                                                        <s:if test="actPlantel">
                                                                                        <s:hidden name="FormPlantel" id="%{FormPlantel}"></s:hidden>
                                                                                            <div class="card">
                                                                                                <div class="card-header card-header-primary card-header-icon">
                                                                                                    <div class="card-icon" style="background: indigo;">
                                                                                                        <i class="material-icons">group_add</i>
                                                                                                    </div>
                                                                                                    <h4 class="card-title">Actualizar plantel de la unidad económica</h4>
                                                                                                </div>
                                                                                                &nbsp;

                                                                                                <div class="card-body">
                                                                                                    <div class="row">


                                                                                                        <div class="col-lg-4">
                                                                                                            <label  for="RFC">CCT del plantel</label>                                                                                       
                                                                                                        <s:textfield  cssClass="form-control" name="ue.CCT_PLA" id="CCT_PLA" ></s:textfield>                                                                                                             
                                                                                                        </div>


                                                                                                        <div class="col-lg-4">
                                                                                                            <label  for="RFC">Nombre del plantel</label>                                                                                       
                                                                                                        <s:textfield  cssClass="form-control" name="ue.NOM_PLA" id="NOM_PLA" ></s:textfield>                                                                                                             
                                                                                                        </div>
                                                                                                        <div class="col-lg-4">
                                                                                                            <label  for="RFC">Domicilio:</label>                                                                                       
                                                                                                        <s:textfield  cssClass="form-control" name="ue.DOM_PLA" id="DOM_PLA" ></s:textfield>                                                                                                             
                                                                                                        </div>
                                                                                                        <div class=" col-md-4">
                                                                                                            <label for="examplePass">Código postal</label>
                                                                                                        <s:textfield cssClass="form-control" name="ue.CP_PLA" id="CP_PLA" onchange="Javascript:ConsultaCP('ConsultaCP_PLAA')" maxLength="5" />
                                                                                                        <s:fielderror fieldName="NoCP_PLA" cssClass="alert alert-danger"></s:fielderror>
                                                                                                        <s:fielderror fieldName="CP_PLA" cssClass="alert alert-danger"></s:fielderror>
                                                                                                        </div>

                                                                                                        <div class=" col-md-4">
                                                                                                            <label for="examplePass">Colonia:</label>
                                                                                                        <s:select  data-style="select-with-transition"   name="ue.COLONIA_PLA" id="COLONIA_PLA" list="ListaColonia_Pla"  listKey="COLONIA_AUX"  listValue="COLONIA_AUX" headerValue="COLONIA"  cssClass="selectpicker "  />
                                                                                                        <s:iterator value="ListaColonia_Pla" id="ListaColonia_Pla" status="stat">                        
                                                                                                            <s:hidden  name = "ListaColonia_Pla[%{#stat.index}].COLONIA_AUX" id="COLONIA_AUX"></s:hidden>
                                                                                                        </s:iterator>                                      
                                                                                                    </div>  

                                                                                                    <div class=" col-md-4">
                                                                                                        <label for="examplePass" >Municipio:</label>
                                                                                                        <s:textfield cssClass="form-control" name="ue.MUN_PLA" id="MUN_PLA"  />
                                                                                                    </div>
                                                                                                </div>
                                                                                                &nbsp;
                                                                                                &nbsp;
                                                                                                <div class=" col-md-12 text-center">
                                                                                                    <a class="btn btn-round btn-rose"  href="Javascript:actualizarPlantel('actualizarSP')">Actualizar Plantel</a>
                                                                                                </div>   
                                                                                                   &nbsp;
                                                                                                &nbsp;
                                                                                                <s:fielderror fieldName="actualizaSP" cssClass="alert alert-danger"/>
                                                                                            </div>
                                                                                        </div>
                                                                                    </s:if>

                                                                                    <s:if test="actSuc">
                                                                                        <s:hidden name="FormSucursal" id="%{FormSucursal}"></s:hidden>
                                                                                            <div class="card">
                                                                                                <div class="card-header card-header-primary card-header-icon">
                                                                                                    <div class="card-icon" style="background: indigo;">
                                                                                                        <i class="material-icons">group_add</i>
                                                                                                    </div>
                                                                                                    <h4 class="card-title">Actualizar sucursal de la unidad económica</h4>
                                                                                                </div>
                                                                                                &nbsp;

                                                                                                <div class="card-body">
                                                                                                    <div class="row">
                                                                                                        <div class="col-lg-4">
                                                                                                            <label  for="RFC">Nombre del la sucursal:</label>                                                                                       
                                                                                                        <s:textfield  cssClass="form-control" name="ue.NOM_SUC" id="NOM_SUC" ></s:textfield>                                                                                                             
                                                                                                        </div>
                                                                                                        <div class="col-lg-4">
                                                                                                            <label  for="RFC">Domicilio:</label>                                                                                       
                                                                                                        <s:textfield  cssClass="form-control" name="ue.DOM_SUC" id="DOM_SUC" ></s:textfield>                                                                                                             
                                                                                                        </div>
                                                                                                        <div class=" col-md-4">
                                                                                                            <label for="examplePass">Código postal</label>
                                                                                                        <s:textfield cssClass="form-control" name="ue.CP_SUC" id="CP_SUC" onchange="Javascript:ConsultaCP('ConsultaCP_SUCA')" maxLength="5" />
                                                                                                        <s:fielderror fieldName="NoCP_SUC" cssClass="alert alert-danger"></s:fielderror>
                                                                                                        <s:fielderror fieldName="CP_SUC" cssClass="alert alert-danger"></s:fielderror>
                                                                                                        </div>

                                                                                                        <div class=" col-md-4">
                                                                                                            <label for="examplePass">Colonia:</label>
                                                                                                        <s:select  data-style="select-with-transition"   name="ue.COLONIA_SUC" id="COLONIA_SUC" list="ListaColonia_Suc"  listKey="COLONIA_AUX"  listValue="COLONIA_AUX" headerValue="COLONIA"  cssClass="selectpicker "  />
                                                                                                        <s:iterator value="ListaColonia_Suc" id="ListaColonia_Suc" status="stat">                        
                                                                                                            <s:hidden  name = "ListaColonia_Suc[%{#stat.index}].COLONIA_AUX" id="COLONIA_AUX"></s:hidden>
                                                                                                        </s:iterator>                                      
                                                                                                    </div>  

                                                                                                    <div class=" col-md-4">
                                                                                                        <label for="examplePass" >Municipio:</label>
                                                                                                        <s:textfield cssClass="form-control" name="ue.MUN_SUC" id="MUN_SUC"  />
                                                                                                    </div>
                                                                                                </div>
                                                                                                &nbsp;
                                                                                                &nbsp;
                                                                                                <div class=" col-md-12 text-center">
                                                                                                    <a class="btn btn-round btn-rose"  href="Javascript:actualizarSucursal('actualizarSP')">Actualizar Sucursal</a>
                                                                                                </div>
                                                                                                 &nbsp;
                                                                                                &nbsp;
                                                                                                <s:fielderror fieldName="actualizaSP" cssClass="alert alert-danger"/>
                                                                                            </div>
                                                                                        </div>
                                                                                    </s:if>

                                                                                </div>
                                                                                <!--  end card  -->
                                                                            </div>               
                                                                        </s:if>              


                                                                        <!--UNIDADES ECONOMICAS -->                  


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

                                                        <s:textfield name="ue.ID_ESC" id="ue.ID_ESC" cssStyle="display:none;"></s:textfield>
                                                        <s:textfield name="ue.ID_IE_UE" id="id_ue_ie" cssStyle="display:none;"></s:textfield>
                                                        <s:textfield name="ue.ID_SUC" id="id_suc" cssStyle="display:none;"></s:textfield>
                                                        <s:textfield name="ue.STATUS_GENERAL" id="status" cssStyle="display:none;"></s:textfield>
                                                        <s:textfield name="ue.CVE_MUNICIPIO" id="ue.CVE_MUNICIPIO" cssStyle="display:none;"></s:textfield>
                                                        <s:textfield name="ue.CVE_MUNICIPIO_PLA" id="ue.CVE_MUNICIPIO_PLA" cssStyle="display:none;"></s:textfield>
                                                        <s:textfield name="ue.CVE_MUNICIPIO_SUC" id="ue.CVE_MUNICIPIO_SUC" cssStyle="display:none;"></s:textfield>
                                                        <s:textfield name="ue.RFCVALIDO" id="rfcvalidado" cssStyle="display:none;"></s:textfield>
                                                        <s:textfield name="ue.VALOR" id="VALOR" cssStyle="display:none;"></s:textfield>

                                                        <s:iterator value="ListaSector" id="ListaSector" status="stat">                        
                                                            <s:hidden  name = "ListaSector[%{#stat.index}].ID_SECTOR" id="ID_SECTOR"></s:hidden>
                                                            <s:hidden  name = "ListaSector[%{#stat.index}].NOM_SECTOR" id="NOM_SECTOR"></s:hidden>
                                                        </s:iterator>
                                                        <s:iterator value="ListaSubSector" id="ListaSubSector" status="stat">                        
                                                            <s:hidden  name = "ListaSubSector[%{#stat.index}].ID_SUBSECTOR" id="ID_SUBSECTOR"></s:hidden>
                                                            <s:hidden  name = "ListaSubSector[%{#stat.index}].NOM_SUBSECTOR" id="NOM_SUBSECTOR"></s:hidden>
                                                        </s:iterator>


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
