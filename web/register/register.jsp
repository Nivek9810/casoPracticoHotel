<%-- 
    Document   : register
    Created on : 17/05/2020, 09:15:12 PM
    Author     : user
--%>

<%@page import="Modelo.DTO_Funcionario"%>
<%@page import="java.sql.Connection"%>
<%@page import="Modelo.Conexion"%>
<%@page import="Modelo.DTO_Persona"%>
<%@page import="Controlador.DAO_Genero"%>
<%@page import="Modelo.DTO_Genero"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Conexion con = new Conexion();
    Connection conexion = con.getConnection();
    DAO_Genero objDataGenero = new DAO_Genero(conexion);
    HttpSession sessionStatus = request.getSession();
    DTO_Persona objPersona = (DTO_Persona) sessionStatus.getAttribute("cliente");

    DTO_Funcionario objFuncionario = (DTO_Funcionario) request.getAttribute("editUser");
    /*GET GENRE*/
    ArrayList<DTO_Genero> listaGeneros = objDataGenero.getAllGenres();
%>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="../css/materialize.css" />
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link rel="stylesheet" type="text/css" href="../css/main.css">

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de usuario <%if (objPersona != null) {
                out.println("(" + objPersona.getNombre() + ")");
            }%></title>
        <script type="text/javascript">
            document.addEventListener('DOMContentLoaded', function () {
                var side = document.querySelectorAll('.sidenav');
                var sideNav = M.Sidenav.init(side, {});
                var tabs_1 = document.querySelector('.tabs');
                var instance = M.Tabs.init(tabs_1, {});
                var tabs_2 = document.querySelector('.tab-checkIn');
                var instance = M.Tabs.init(tabs_2, {});

                var date = new Date();

                var select = document.querySelectorAll('select');
                var instancesSelect = M.FormSelect.init(select, {});
                var picker = document.querySelectorAll('.datepicker');
                var instancesPick = M.Datepicker.init(picker, {
                    "defaultTime": (new Date(date.setDate(date.getDate() - (365 * 18)))),
                    format: "dd/mm/yyyy"
                });
            });
        </script>
    </head>
    <body>
        <div class="container">
            <h3><% if (objFuncionario != null) {
                    out.println("Modifica el usuario");
                } else {
                    out.println("Registra un usuario");
                } %></h3>
            <form class="col s12 register-form" action="../Servlet_Register" method="post">
                <div class="row">
                    <div class="input-field col s12 m6">
                        <input id="cedula" type="number" class="validate" name="cedula"  value=<% if (objFuncionario != null) {
                                out.println(objFuncionario.getCodigo_persona().getCedula());
                            } %>>
                        <label for="cedula">Número de documento:</label>
                    </div>

                    <div class="input-field col s12 m6">
                        <select name="genero" value=<% if (objFuncionario != null) {
                                out.println(objFuncionario.getCodigo_persona().getGenero().getCodigo_genero());
                            } %>>
                            <option value="0" disabled selected>Selecciona un genero</option>
                            <% for (DTO_Genero genero : listaGeneros) {%>
                            <option value=<% out.println(genero.getCodigo_genero()); %>><% out.println(genero.getNombre()); %></option>
                            <%}%>
                        </select>
                        <label>Selecciona un genero</label>
                    </div>
                    <div class="input-field col s12 m4">
                        <input id="name" type="text" class="validate" name="name" value=<% if (objFuncionario != null) {
                                out.println(objFuncionario.getCodigo_persona().getNombre());
                            } %>>
                        <label for="name">Nombre</label>
                    </div>
                    <div class="input-field col s12 m4">
                        <input id="last_name_father" type="text" class="validate" name="last_name_father" value=<% if (objFuncionario != null) {
                                out.println(objFuncionario.getCodigo_persona().getApellido_padre());
                            } %>> 
                        <label for="last_name_father">Primer apellido</label>
                    </div>
                    <div class="input-field col s12 m4">
                        <input id="last_name_mother" type="text" class="validate" name="last_name_mother" value=<% if (objFuncionario != null) {
                                out.println(objFuncionario.getCodigo_persona().getApellido_madre());
                            } %>> 
                        <label for="last_name_mother">Segundo apellido</label>
                    </div>
                    <div class="input-field col s12 m6">
                        <input type="text" class="datepicker"  name="birthdate" placeholder="Fecha de nacimiento" value=<% if (objFuncionario != null) {
                                out.println(objFuncionario.getCodigo_persona().getFecha_nacimiento());
                            } %>>
                    </div>
                    <div class="input-field col s12 m6">
                        <input id="email" type="email" class="validate" name="email" value=<% if (objFuncionario != null) {
                                out.println(objFuncionario.getCodigo_persona().getCorreo_electronico());
                            } %>> 
                        <label for="email">Correo electrónico</label>
                    </div>

                    <div class="input-field col s12 m6">
                        <input id="phone" type="number" class="validate" name="phone" value=<% if (objFuncionario != null) {
                                out.println(objFuncionario.getCodigo_persona().getTelefono());
                            } %>> 
                        <label for="phone">Teléfono</label>
                    </div>
                    <div class="input-field col s12 m6">
                        <input id="password" type="password" class="validate" name="password" value=<% if (objFuncionario != null) {
                                out.println(objFuncionario.getContrasena());
                            } %>> 
                        <label for="password">Contraseña</label>
                    </div>
                    <div class="input-field col s12" style="text-align: -webkit-center;">
                        <button class="btn waves-effect waves-light" type="submit"  value="Login" name="action">Registrarme
                            <i class="material-icons right">person_add</i>
                        </button>
                    </div>
                    <div class="input-field col s12" style="text-align: -webkit-center;">
                        <a class="btn waves-effect waves-light" href=<%if (objPersona != null && objFuncionario == null) {
                                out.println("../main/home.jsp");
                            } else if (objFuncionario != null) {
                                out.println("main/home.jsp");
                            } else {
                                out.println("../index.jsp");
                            }%>>Volver
                            <i class="material-icons left">arrow_back</i>
                        </a>
                    </div>
                </div>
            </form>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script type="text/javascript" src="../js/main.js"></script>
        <!--JavaScript at end of body for optimized loading-->    
        <!--<script type="text/javascript" src="js/materialize.js"></script>-->
    </body>
</html>
