<%-- 
    Document   : home
    Created on : 17/05/2020, 12:04:13 PM
    Author     : user
--%>

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

    ArrayList<DTO_Habitacion> listaHabitaciones = null;
    ArrayList<DTO_Forma_pago> listaMetodosDePago = null;
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Conexion con = new Conexion();
    Connection conexion = con.getConnection();

    if (rol != null) {
        DAO_Habitacion objDataHabitacion = new DAO_Habitacion(conexion);
        DAO_Reservacion objDataReservacion = new DAO_Reservacion(conexion);

        listaHabitaciones = objDataHabitacion.getAllRooms();

        if (rol.getCodigo_rol() == 1) {
            DAO_Ciudad objDataCiudad = new DAO_Ciudad(conexion);
            DAO_Funcionario objDataFuncionario = new DAO_Funcionario(conexion);
            DAO_Hotel objDataHoteles = new DAO_Hotel(conexion);
            DAO_Tipo_habitacion objDataTipoHabitacion = new DAO_Tipo_habitacion(conexion);

            listaReservaciones = objDataReservacion.getAllReservations();
            listaClientes = objDataFuncionario.getAllFuncionarios(3);
            listaRepresentante = objDataFuncionario.getAllFuncionarios(1);
            listaCiudad = objDataCiudad.getAllPaymentMethods();
            listaHoteles = objDataHoteles.getAllHotels();
            listaTipoHabitaciones = objDataTipoHabitacion.getAllTypeRooms();
        } else {
            DAO_Forma_pago objDataMetodoPago = new DAO_Forma_pago(conexion);

            listaReservaciones = objDataReservacion.getUserReservations(username.getCedula());
            listaMetodosDePago = objDataMetodoPago.getAllPaymentMethods();
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
        <style type="text/css">
            .tab-checkIn {
                margin-bottom: 5%;
            }

            .tab-checkIn > .tab > a,
            .tab-checkIn > .tab > a.active,
            .tab-hotels  > .tab > a,
            .tab-hotels > .tab > a.active{
                color: cornflowerblue !important;
                background-color: transparent !important;
            }
            .tab-checkIn > .indicator,
            .tab-hotels > .indicator{ 
                background: cornflowerblue !important;
            }

            .btn {
                background: cornflowerblue;
                border-radius: 50px;
            }

            .btn:hover {
                background: lightblue;
            }

            .card{ 
                width: 50%;
                margin: auto;
            }

            @media only screen and (min-width: 992px) {
                .brand-logo {
                    margin: 0% 5%;
                }
            }
        </style>
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
                                        <input type="text" class="datepicker"  name="fecha_ingreso" placeholder="Fecha de ingreso">
                                    </div>
                                    <div class="input-field col s12 m6">
                                        <input type="text" class="datepicker"  name="fecha_salida" placeholder="Fecha de salida">
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
                    <div class="carousel carousel-slider">
                        <div class="carousel-item" href="#one!">
                            <div class="card blue-grey darken-1">
                                <div class="card-content white-text">
                                    <span class="card-title">Card Title</span>
                                    <p>I am a very simple card. I am good at containing small bits of information.
                                        I am convenient because I require little markup to use effectively.</p>
                                </div>
                                <div class="card-action">
                                    <a href="#">This is a link</a>
                                    <a href="#">This is a link</a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item" href="#two!">
                            <div class="card blue-grey darken-1">
                                <div class="card-content white-text">
                                    <span class="card-title">Card Title</span>
                                    <p>I am a very simple card. I am good at containing small bits of information.
                                        I am convenient because I require little markup to use effectively.</p>
                                </div>
                                <div class="card-action">
                                    <a href="#">This is a link</a>
                                    <a href="#">This is a link</a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item" href="#three!">
                            <div class="card blue-grey darken-1">
                                <div class="card-content white-text">
                                    <span class="card-title">Card Title</span>
                                    <p>I am a very simple card. I am good at containing small bits of information.
                                        I am convenient because I require little markup to use effectively.</p>
                                </div>
                                <div class="card-action">
                                    <a href="#">This is a link</a>
                                    <a href="#">This is a link</a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item" href="#four!">
                            <div class="card blue-grey darken-1">
                                <div class="card-content white-text">
                                    <span class="card-title">Card Title</span>
                                    <p>I am a very simple card. I am good at containing small bits of information.
                                        I am convenient because I require little markup to use effectively.</p>
                                </div>
                                <div class="card-action">
                                    <a href="#">This is a link</a>
                                    <a href="#">This is a link</a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item" href="#five!">
                            <div class="card blue-grey darken-1">
                                <div class="card-content white-text">
                                    <span class="card-title">Card Title</span>
                                    <p>I am a very simple card. I am good at containing small bits of information.
                                        I am convenient because I require little markup to use effectively.</p>
                                </div>
                                <div class="card-action">
                                    <a href="#">This is a link</a>
                                    <a href="#">This is a link</a>
                                </div>
                            </div>
                        </div>
                    </div>
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
                                <input id="name" type="text" class="validate" name="name"> 
                                <label for="name">Nombre</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input id="nit" type="number" class="validate" name="nit"> 
                                <label for="nit">Nit</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="phone" type="number" class="validate" name="phone"> 
                                <label for="phone">Teléfono</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input type="text" class="datepicker"  name="date_founded" placeholder="Fecha de creación">
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
                                <input id="direction" type="text" class="validate" name="direction"> 
                                <label for="direction">Dirección</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="phone-sucursal" type="number" class="validate" name="phoneSucursal"> 
                                <label for="phone-sucursal">Teléfono</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input type="text" class="datepicker"  name="dateFoundedSucursal" placeholder="Fecha de creación">
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

                            <div class="input-field col s12 m6">
                                <h5>Agregar habitación </h5>
                                <% if (listaHoteles != null && !listaHoteles.isEmpty()) { %>
                                <select name="hotel">
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
                                <select name="tipoHabitacion">
                                    <option value="0" disabled selected>Selecciona un tipo de habitación</option>
                                    <% for (DTO_Tipo_habitacion Tipo : listaTipoHabitaciones) {%>
                                    <option value=<% out.println(Tipo.getCodigo_tipo_habitacion()); %>><% out.println(Tipo.getNombre()); %></option>
                                    <%}%>

                                </select>
                                <label>Selecciona un tipo de habitación</label>
                                <%}%>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="cost-night" type="number" class="validate" name="costNight"> 
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
