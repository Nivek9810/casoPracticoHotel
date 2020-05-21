<%-- 
    Document   : home
    Created on : 17/05/2020, 12:04:13 PM
    Author     : user
--%>

<%@page import="Controlador.DAO_Calificacion"%>
<%@page import="Modelo.DTO_Calificacion"%>
<%@page import="Controlador.DAO_Pago"%>
<%@page import="Modelo.DTO_Pago"%>
<%@page import="Controlador.DAO_Tipo_habitacion"%>
<%@page import="Modelo.DTO_Tipo_habitacion"%>
<%@page import="Controlador.DAO_Hotel"%>
<%@page import="Modelo.DTO_Hotel"%>
<%@page import="Controlador.DAO_Ciudad"%>
<%@page import="Modelo.DTO_Ciudad"%>
<%@page import="java.sql.Connection"%>
<%@page import="Modelo.Conexion"%>
<%@page import="Controlador.DAO_Forma_pago"%>
<%@page import="Modelo.DTO_Forma_pago"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="Controlador.DAO_Habitacion"%>
<%@page import="Modelo.DTO_Habitacion"%>
<%@page import="Modelo.DTO_Persona"%>
<%@page import="Controlador.DAO_Funcionario"%>
<%@page import="Modelo.DTO_Funcionario"%>
<%@page import="Modelo.DTO_Reservacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controlador.DAO_Reservacion"%>
<%@page import="Modelo.DTO_Rol"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sessionStatus = request.getSession();
    DTO_Persona username = (DTO_Persona) sessionStatus.getAttribute("cliente");
    DTO_Rol rol = (DTO_Rol) sessionStatus.getAttribute("rol");
    ArrayList<DTO_Reservacion> listaReservaciones = null;
    ArrayList<DTO_Funcionario> listaClientes = null;
    ArrayList<DTO_Funcionario> listaRepresentante = null;
    ArrayList<DTO_Ciudad> listaCiudad = null;
    ArrayList<DTO_Hotel> listaHoteles = null;
    ArrayList<DTO_Tipo_habitacion> listaTipoHabitaciones = null;
    ArrayList<DTO_Pago> listaReservasPagadas = null;
    ArrayList<DTO_Calificacion> listaCalificaciones = null;

    ArrayList<DTO_Habitacion> listaHabitaciones = null;
    ArrayList<DTO_Forma_pago> listaMetodosDePago = null;
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Conexion con = new Conexion();
    Connection conexion = con.getConnection();

    if (rol != null) {
        DAO_Habitacion objDataHabitacion = new DAO_Habitacion(conexion);
        DAO_Reservacion objDataReservacion = new DAO_Reservacion(conexion);
        DAO_Calificacion objDataCalificacion = new DAO_Calificacion(conexion);

        listaHabitaciones = objDataHabitacion.getAllRooms();

        if (rol.getCodigo_rol() == 1) {
            DAO_Ciudad objDataCiudad = new DAO_Ciudad(conexion);
            DAO_Funcionario objDataFuncionario = new DAO_Funcionario(conexion);
            DAO_Hotel objDataHoteles = new DAO_Hotel(conexion);
            DAO_Tipo_habitacion objDataTipoHabitacion = new DAO_Tipo_habitacion(conexion);

            listaReservaciones = objDataReservacion.getAllReservations();
            listaRepresentante = objDataFuncionario.getAllFuncionarios(1);
            listaClientes = objDataFuncionario.getAllFuncionarios(3);
            listaCiudad = objDataCiudad.getAllPaymentMethods();
            listaHoteles = objDataHoteles.getAllHotels();
            listaTipoHabitaciones = objDataTipoHabitacion.getAllTypeRooms();
            listaCalificaciones = objDataCalificacion.getAllReviews();
        } else {
            DAO_Forma_pago objDataMetodoPago = new DAO_Forma_pago(conexion);
            DAO_Pago objDataPago = new DAO_Pago(conexion);

            listaReservaciones = objDataReservacion.getUserReservations(username.getCedula());
            listaMetodosDePago = objDataMetodoPago.getAllPaymentMethods();
            listaReservasPagadas = objDataPago.getUserPayments(username.getCedula());
            listaCalificaciones = objDataCalificacion.getUserReviews(username.getCedula());
        }
    }

    if (username == null) {
        sessionStatus.invalidate();
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <!--<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>-->
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link rel="stylesheet" type="text/css" href="../css/main.css">

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hola <% out.println(username.getNombre());%></title>
    </head>
    <body>
        <nav class="nav-extended">
            <div class="nav-wrapper">
                <a href="#" class="brand-logo" style="font-size: 3vw;">Bienvenido(a) <% out.println(username.getNombre() + " " + username.getApellido_padre() + " " + username.getApellido_madre());%></a>
                <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="../index.jsp">Salir</a></li>
                </ul>
            </div>
            <div class="nav-content">
                <ul class="tabs tabs-transparent">
                    <li class="tab"><a class="active" href="#checkIn">Check in</a></li>
                    <li class="tab"><a href="#checkOut">Check out</a></li>
                    <li class="tab"><a href="#ratings">Calificaciones</a></li>
                    <li class="tab"><a href="#users">Clientes</a></li>
                    <li class="tab"><a href="#hotels">Hoteles</a></li>
                </ul>
            </div>
        </nav>

        <ul class="sidenav" id="mobile-demo">
            <li><a href="../index.jsp">Salir</a></li>
        </ul>

        <div class="container">
            <!-- @CHECK-IN SECTION -->
            <div id="checkIn" class="col s12">
                <div>
                    <h3>Check in - Administración</h3>

                    <ul class="tab-checkIn tabs tabs-transparent">
                        <li class="tab"><a class="active" href="#reservation">Reservación</a></li>
                        <li class="tab"><a href="#register">Registro</a></li>
                    </ul>

                    <div id="reservation" class="col s12">  
                        <h4>Lista de reservaciones</h4>
                        <ul class="collection">
                            <%
                                if (listaReservaciones != null && !listaReservaciones.isEmpty()) {
                                    for (DTO_Reservacion reservacon : listaReservaciones) {

                            %>
                            <li class="collection-item avatar">
                                <!--<img src="images/yuna.jpg" alt="" class="circle">-->
                                <i class="material-icons circle">hotel</i>
                                <span class="title"><% out.println(reservacon.getCliente().getNombre());%></span>
                                <p> <b>Hotel:</b> <% out.println(reservacon.getHabitacion().getHotel().getNombre());%> <br>
                                    <b>Tipo:</b> <% out.println(reservacon.getHabitacion().getTipo().getNombre());%> <br>
                                    <b>Valor:</b> <% out.println(reservacon.getHabitacion().getValor());%>
                                </p>

                                <a href="#!" class="secondary-content"><i class="material-icons">business_center</i></a>

                            </li>
                            <% }
                            } else {%>
                            <li class="collection-item avatar">
                                <i class="material-icons circle">hotel</i>
                                <p>No hay reservaciones activas</p>
                            </li>

                            <%}%>
                        </ul>  
                    </div>
                    <div id="register" class="col s12">
                        <h4>Registro de reservación</h4>
                        <div class="row">
                            <div class="col s12 m8">
                                <h5>Seleccionar un usuario</h5>
                                <form class="col s12 register-form" action="../Servlet_CheckIn" method="post">
                                    <% if (listaClientes != null && !listaClientes.isEmpty()) { %>
                                    <div class="input-field col s12 m6">
                                        <select name="cliente">
                                            <option value="0" disabled selected>Selecciona un cliente</option>
                                            <% for (DTO_Funcionario cliente : listaClientes) {%>
                                            <option value=<% out.println(cliente.getCodigo_persona().getCedula()); %>><% out.println(cliente.getCodigo_persona().getNombre()); %></option>
                                            <%}%>

                                        </select>
                                        <label>Selecciona un cliente</label>
                                    </div>
                                    <%}%>
                                    <div class="input-field col s12 m6">
                                        <select name="habitacion">
                                            <option value="0" disabled selected>Selecciona una habitación</option>
                                            <% for (DTO_Habitacion habitacion : listaHabitaciones) {%>
                                            <option value=<% out.println(habitacion.getCodigo_habitacion()); %>><% out.println(habitacion.getTipo().getNombre()); %></option>
                                            <%}%>
                                        </select>
                                        <label>Selecciona una habitación</label>
                                    </div>
                                    <% if (listaMetodosDePago != null && !listaMetodosDePago.isEmpty()) { %>
                                    <div class="input-field col s12 m6">
                                        <select name="payment">
                                            <option value="0" disabled selected>Selecciona una opción</option>
                                            <% for (DTO_Forma_pago paymentMethod : listaMetodosDePago) {%>
                                            <option value=<% out.println(paymentMethod.getCodigo_forma_pago()); %>><% out.println(paymentMethod.getNombre()); %></option>
                                            <%}%>
                                        </select>
                                        <label>Selecciona un método de pago</label>
                                    </div>
                                    <%}%>
                                    <div class="input-field col s12 m6">
                                        <input type="text" class="datepicker"  name="fecha_ingreso" placeholder="Fecha de ingreso" required="true">
                                    </div>
                                    <div class="input-field col s12 m6">
                                        <input type="text" class="datepicker"  name="fecha_salida" placeholder="Fecha de salida" required="true">
                                    </div>
                                    <div class="input-field col s12">
                                        <button class="btn waves-effect waves-light" type="submit"  value="Login" name="action">Reservar
                                            <i class="material-icons right">hotel</i>
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <%if (rol.getCodigo_rol() == 1) {%>                                        
                            <div class="col s12 m4">
                                <h5>Registro de usuario</h5>
                                <a class="btn waves-effect waves-light" href="../register/register.jsp">Registrar usuario
                                    <i class="material-icons left">person_add</i>
                                </a>
                            </div>
                            <%}%>                                        
                        </div>
                    </div>
                </div>
            </div>
            <!-- @CHECK-OUT SECTION -->
            <div id="checkOut" class="col s12">
                <section>
                    <h3>Check out</h3>
                    <table class="striped highlight responsive-table">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Hotel</th>
                                <th>Valor</th>
                                <th>Fecha Inicio</th>
                                <th>Fecha Fin</th>
                                <th>Check out</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                if (listaReservaciones != null && !listaReservaciones.isEmpty()) {
                                    for (DTO_Reservacion reservacion : listaReservaciones) {

                            %>
                            <tr>
                                <td>
                                    <% out.println(reservacion.getCliente().getNombre());%>
                                </td>
                                <td>
                                    <% out.println(reservacion.getHabitacion().getHotel().getNombre());%>
                                </td>
                                <td>
                                    <% out.println(reservacion.getHabitacion().getValor());%>
                                </td>
                                <td>
                                    <% out.println(reservacion.getFecha_ingreso());%>
                                </td>
                                <td>
                                    <% out.println(reservacion.getFecha_salida());%>
                                </td>
                                <td>
                                    <form class="col s12 register-form" action="../Servlet_CheckOut" method="post">
                                        <div class="input-field col s12">
                                            <button onclick="M.toast({html: 'Estamos procesando su solicitud...', classes: 'rounded'})" class="btn waves-effect waves-light" type="submit" name="reservation" value=<% out.println(reservacion.getCodigo_reservacion());%> >Pagar
                                                <i class="material-icons right">payment</i>
                                            </button>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                            <% }
                            } else {%>
                            <tr>
                                <td colspan="6">                                    
                                    <p>No hay reservaciones activas</p>
                                </td>
                            </tr>

                            <%}%>
                        </tbody>
                    </table>
                </section>
            </div>
            <!-- @RATING SECTION -->
            <div id="ratings" class="col s12">
                <section>
                    <h3>Calificaciones</h3>
                    <% if (listaReservasPagadas != null && !listaReservasPagadas.isEmpty()) { %>
                    <div class="row">
                        <form class="col s12 register-form" action="../Servlet_Calificacion" method="post">

                            <div class="input-field col s8">
                                <p class="range-field">
                                    <b>Seleciona una de tus experiencias.</b>
                                </p>
                            </div>
                            <div class="input-field col s4">

                                <select name="book">
                                    <% for (DTO_Pago reservas : listaReservasPagadas) {%>
                                    <option value=<% out.println(reservas.getCodigo_pago()); %>><% out.println(reservas.getReservacion().getHabitacion().getTipo().getNombre() + " - " + reservas.getReservacion().getHabitacion().getHotel().getNombre()); %></option>
                                    <%}%>

                                </select>
                                <label>Selecciona un hotel</label>

                            </div>

                            <div class="input-field col s8">
                                <p class="range-field">
                                    ¿Que tal te pareció el servicio de hotel?
                                </p>
                            </div>
                            <div class="input-field col s4">
                                <p class="range-field">
                                    <input type="range" name="ranking-hotel" min="1" max="5" />
                                </p>
                            </div>
                            <div class="input-field col s8">
                                <p class="range-field">
                                    ¿Que tal te pareció la sucursal donde está ubicado el hotel?
                                </p>
                            </div>
                            <div class="input-field col s4">
                                <p class="range-field">
                                    <input type="range" name="ranking-sucursal" min="1" max="5" />
                                </p>
                            </div>
                            <div class="input-field col s8">
                                <p class="range-field">
                                    ¿El tipo de habitación era realmente lo que esperabas?
                                </p>
                            </div>
                            <div class="input-field col s4">
                                <p class="range-field">
                                    <input type="range" name="ranking-room" min="1" max="5" />
                                </p>
                            </div>
                            <div class="input-field col s8">
                                <p class="range-field">
                                    ¿Cómo calificas la calidad del servicio?
                                </p>
                            </div>
                            <div class="input-field col s4">
                                <p class="range-field">
                                    <input type="range" name="ranking-service" min="1" max="5" />
                                </p>
                            </div>
                            <div class="input-field col s12" style="text-align: center;">
                                <button class="btn waves-effect waves-light" type="submit" name="userId" >Enviar calificación
                                    <i class="material-icons right">check</i>
                                </button>
                            </div>
                        </form>
                    </div>
                    <%}%>
                    <% if (listaCalificaciones != null && !listaCalificaciones.isEmpty()) { %>
                    <div class="carousel carousel-slider">
                        <% for (int i = 0; i < listaCalificaciones.size(); i++) { %>
                        <div class="carousel-item" href=<%out.println(i + 1);%>>
                            <div class="card blue-grey darken-1 calification-card">
                                <div class="card-content white-text">
                                    <span class="card-title"><%out.println(listaCalificaciones.get(i).getPago().getReservacion().getCliente().getNombre());%></span>
                                    <p>
                                    <%out.println("<b>Hotel ("+listaCalificaciones.get(i).getPago().getReservacion().getHabitacion().getHotel().getNombre()+")</b>: "+listaCalificaciones.get(i).getHotel());%><br>
                                    <%out.println("<b>Suite ("+listaCalificaciones.get(i).getPago().getReservacion().getHabitacion().getTipo().getNombre()+")</b>: "+listaCalificaciones.get(i).getTipo_habitacion());%><br>
                                    <%out.println("<b>Sucursal </b>: "+listaCalificaciones.get(i).getSucursal());%><br>
                                    <%out.println("<b>Valor final ("+listaCalificaciones.get(i).getPago().getValor_de_pago()+")</b>: "+listaCalificaciones.get(i).getCalidad_del_servicio());%>
                                    </p>
                                </div>
                                <div class="card-action">
                                    
                                </div>
                            </div>
                        </div>
                        <%}%>
                    </div>
                    <%}%>
                </section>
            </div>
            <!-- @USERS SECTION -->
            <div id="users" class="col s12">
                <section>
                    <h3>Usuarios</h3>
                    <table class="striped highlight responsive-table">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Cédula</th>
                                <th>Genero</th>
                                <th>Edad</th>
                                <th>Correo Electrónico</th>
                                <th>Telèfono</th>
                                <th>Último acceso</th>
                                <th>Editar</th>
                            </tr>

                        </thead>

                        <tbody>
                            <%
                                if (listaClientes != null && !listaClientes.isEmpty()) {
                                    for (DTO_Funcionario cliente : listaClientes) {
                                        LocalDate fechaNac = LocalDate.parse(cliente.getCodigo_persona().getFecha_nacimiento().toString(), fmt);
                                        LocalDate ahora = LocalDate.now();

                                        Period periodo = Period.between(fechaNac, ahora);
                            %>
                            <tr>
                                <td>
                                    <% out.println(cliente.getCodigo_persona().getNombre() + " " + cliente.getCodigo_persona().getApellido_padre() + " " + cliente.getCodigo_persona().getApellido_madre());%>
                                </td>
                                <td>
                                    <% out.println(cliente.getCodigo_persona().getCedula());%>
                                </td>
                                <td>
                                    <% out.println(cliente.getCodigo_persona().getGenero().getNombre());%>
                                </td>
                                <td>
                                    <% out.println(periodo.getYears());%>
                                </td>
                                <td>
                                    <% out.println(cliente.getCodigo_persona().getCorreo_electronico());%>
                                </td>
                                <td>
                                    <% out.println(cliente.getCodigo_persona().getTelefono());%>
                                </td>
                                <td>
                                    <% out.println(cliente.getLast_login());%>
                                </td>
                                <td>
                                    <form class="col s12 register-form" action="../Servlet_Register" method="get">
                                        <div class="input-field col s12">
                                            <button class="btn waves-effect waves-light" type="submit" name="userId" value=<% out.println(cliente.getCodigo_persona().getCedula());%> >Editar
                                                <i class="material-icons right">person</i>
                                            </button>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                            <% }
                            } else {%>
                            <tr>
                                <td colspan="6">                                    
                                    <p>No hay reservaciones activas</p>
                                </td>
                            </tr>

                            <%}%>
                        </tbody>
                    </table>
                </section>

            </div>
            <!-- @HOTEL SECTION -->
            <div id="hotels" class="col s12">
                <section>
                    <h3>Hoteles</h3>
                    <ul class="tab-hotels tabs tabs-transparent">
                        <li class="tab"><a class="active" href="#add-hotel">Registrar hotel</a></li>
                        <li class="tab"><a href="#add-room">Registrar habitación</a></li>
                    </ul>

                    <form id="add-hotel" class="col s12 register-form" action="../Servlet_Hotel" method="post">
                        <h4>Agregar hotel </h4>
                        <div class="row">
                            <div class="input-field col s12 m4">
                                <% if (listaRepresentante != null && !listaRepresentante.isEmpty()) { %>
                                <select name="chief">
                                    <option value="0" disabled selected>Selecciona un representante</option>
                                    <% for (DTO_Funcionario cliente : listaRepresentante) {%>
                                    <option value=<% out.println(cliente.getCodigo_persona().getCedula()); %>><% out.println(cliente.getCodigo_persona().getNombre()); %></option>
                                    <%}%>

                                </select>
                                <label>Selecciona un representante</label>
                                <%}%>
                            </div>
                            <div class="input-field col s12 m4">
                                <input type="text" class="datepicker"  name="date_founded" placeholder="Fecha de creación" required="true">
                            </div>
                            <div class="input-field col s12 m4">
                                <input id="nit" type="number" class="validate" name="nit" required="true"> 
                                <label for="nit">Nit</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="name" type="text" class="validate" name="name" required="true"> 
                                <label for="name">Nombre</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="phone" type="number" class="validate" name="phone" required="true"> 
                                <label for="phone">Teléfono</label>
                            </div>
                        </div>

                        <h4>Información de la sucursal </h4>
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <% if (listaCiudad != null && !listaCiudad.isEmpty()) { %>
                                <select name="city">
                                    <option value="0" disabled selected>Selecciona una ciudad</option>
                                    <% for (DTO_Ciudad ciudad : listaCiudad) {%>
                                    <option value=<% out.println(ciudad.getCodigo_ciudad()); %>><% out.println(ciudad.getNombre()); %></option>
                                    <%}%>

                                </select>
                                <label>Selecciona una ciudad</label>
                                <%}%>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="direction" type="text" class="validate" name="direction" required="true"> 
                                <label for="direction">Dirección</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="phone-sucursal" type="number" class="validate" name="phoneSucursal" required="true"> 
                                <label for="phone-sucursal">Teléfono</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input type="text" class="datepicker"  name="dateFoundedSucursal" placeholder="Fecha de creación" required="true">
                            </div>
                            <div class="input-field col s12">
                                <button class="btn waves-effect waves-light" type="submit" name="action">Agregar
                                    <i class="material-icons right">business</i>
                                </button>
                            </div>
                        </div>
                    </form>


                    <div id="add-room" class="row">
                        <form id="add-hotel" class="col s12 register-form" action="../Servlet_Habitacion" method="post">

                            <div class="col s12">
                                <h5>Agregar habitación </h5>
                            </div>
                            <div class="input-field col s12 m6">
                                <% if (listaHoteles != null && !listaHoteles.isEmpty()) { %>
                                <select name="hotel" required="true">
                                    <option value="0" disabled selected>Selecciona un hotel</option>
                                    <% for (DTO_Hotel hotel : listaHoteles) {%>
                                    <option value=<% out.println(hotel.getCodigo_hotel()); %>><% out.println(hotel.getNombre()); %></option>
                                    <%}%>

                                </select>
                                <label>Selecciona un hotel</label>
                                <%}%>
                            </div>
                            <div class="input-field col s12 m6">
                                <% if (listaTipoHabitaciones != null && !listaTipoHabitaciones.isEmpty()) { %>
                                <select name="tipoHabitacion" required="true">
                                    <option value="0" disabled selected>Selecciona un tipo de habitación</option>
                                    <% for (DTO_Tipo_habitacion Tipo : listaTipoHabitaciones) {%>
                                    <option value=<% out.println(Tipo.getCodigo_tipo_habitacion()); %>><% out.println(Tipo.getNombre()); %></option>
                                    <%}%>

                                </select>
                                <label>Selecciona un tipo de habitación</label>
                                <%}%>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="cost-night" type="number" class="validate" name="costNight" required="true"> 
                                <label for="cost-night">Valor</label>
                            </div>
                            <div class="input-field col s12">
                                <button class="btn waves-effect waves-light" type="submit" name="action">Añadir
                                    <i class="material-icons right">hotel</i>
                                </button>
                            </div>
                        </form>
                    </div>
                </section>

            </div>
        </div>        

        <footer class="page-footer">
            <div class="container">
                <div class="row">
                    <div class="col l6 s12">
                        <h5 class="white-text">Un proyecto realizado por:</h5>
                        <p class="grey-text text-lighten-4">Daniel's</p>
                    </div>
                    <div class="col l4 offset-l2 s12">
                        <h5 class="white-text">Logo</h5>
                    </div>
                </div>
            </div>
            <div class="footer-copyright">
                <div class="container">
                    © 2020 Universidad unipau
                    <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
                </div>
            </div>
        </footer>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.js"></script>
        <script type="text/javascript" src="../js/main.js"></script>
    </body>
</html>
