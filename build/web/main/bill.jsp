<%-- 
    Document   : bill
    Created on : 18/05/2020, 05:53:21 PM
    Author     : user
--%>

<%@page import="Tools.MoneyChange"%>
<%@page import="Modelo.DTO_Pago"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        DTO_Pago objPago = (DTO_Pago) request.getAttribute("userPayment");
        MoneyChange objMoney = new MoneyChange();
    %>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <!--<link type="text/css" rel="stylesheet" href="../css/materialize.css" />-->
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link rel="stylesheet" type="text/css" href="../css/main.css">

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <style type="text/css">
            body{ 
                height: 100%;
                margin: auto;
                display: flex;
                flex-flow: column;
                justify-content: center;
            }
            .collection-item {
                background-color: transparent !important;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col s12 m6">
                    <h3>Hola <strong><% out.println(objPago.getReservacion().getCliente().getNombre());%></strong>, aqui te presentamos a detalle tu factura.</h3>
                </div>
                <div class="col s12 m6">
                    <div class="card blue-grey darken-1">
                        <div class="card-content white-text">
                            <span class="card-title">Detalle: </span>
                            <ul class="collection">
                                <li class="collection-item avatar">
                                    <i class="material-icons circle">business</i>
                                    <span class="title">Hotel:</span>
                                    <p><% out.println(objPago.getReservacion().getHabitacion().getHotel().getNombre());%></p>
                                </li>
                                <li class="collection-item avatar">
                                    <i class="material-icons circle">hotel</i>
                                    <span class="title">Habitación:</span>
                                    <p><% out.println(objPago.getReservacion().getHabitacion().getTipo().getNombre());%>
                                    </p>
                                </li>
                                <li class="collection-item avatar">
                                    <i class="material-icons circle green">attach_money</i>
                                    <span class="title">Valor: </span>
                                    <p><% out.println(objPago.getReservacion().getHabitacion().getValor());%>
                                    </p>
                                </li>
                                <li class="collection-item avatar">
                                    <i class="material-icons circle red">event_available</i>
                                    <span class="title">Fecha ingreso: </span>
                                    <p><% out.println(objPago.getReservacion().getFecha_ingreso());%>
                                    </p>
                                </li>
                                <li class="collection-item avatar">
                                    <i class="material-icons circle red">event</i>
                                    <span class="title">Fecha salida: </span>
                                    <p><% out.println(objPago.getReservacion().getFecha_salida());%>
                                    </p>
                                </li>
                                <li class="collection-item avatar">
                                    <i class="material-icons circle red">style</i>
                                    <span class="title">Medio de pago : </span>
                                    <p><% out.println(objPago.getForma_pago().getNombre());%>
                                    </p>
                                 </li>
                                <li class="collection-item avatar">
                                    <i class="material-icons circle red">credit_card</i>
                                    <span class="title">Valor a pagar : </span>
                                    <p><% out.println(objMoney.getAmoung(objPago.getReservacion().getHabitacion().getValor()) - objMoney.getAmoung(objPago.getValor_de_pago()));%>
                                    </p>
                                </li>
                            </ul>
                        </div>
                        <div class="card-action">
                            <a href="main/home.jsp">Aceptar</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
