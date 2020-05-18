<%-- 
    Document   : register
    Created on : 17/05/2020, 09:15:12 PM
    Author     : user
--%>

<%@page import="Modelo.DTO_Persona"%>
<%@page import="Controlador.DAO_Genero"%>
<%@page import="Modelo.DTO_Genero"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DAO_Genero objDataGenero = new DAO_Genero();
    HttpSession sessionStatus = request.getSession();
    DTO_Persona objPersona = (DTO_Persona) sessionStatus.getAttribute("cliente");
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
        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">-->
        <link rel="stylesheet" type="text/css" href="../css/main.css">

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de usuario <%if (objPersona != null) {
                out.println("(" + objPersona.getNombre() + ")");
            }%></title>
    </head>
    <body>
        <div class="container">
            <h3>Registra un usuario</h3>
            <form class="col s12 register-form" action="../Servlet_Register" method="post">
                <div class="row">
                    <div class="input-field col s12 m6">
                        <input id="cedula" type="number" class="validate" name="cedula" >
                        <label for="cedula">Número de documento:</label>
                    </div>

                    <div class="input-field col s12 m6">
                        <select name="genero">
                            <option value="0" disabled selected>Selecciona un genero</option>
                            <% for (DTO_Genero genero : listaGeneros) {%>
                            <option value=<% out.println(genero.getCodigo_genero()); %>><% out.println(genero.getNombre()); %></option>
                            <%}%>
                        </select>
                        <label>Selecciona un genero</label>
                    </div>
                    <div class="input-field col s12 m4">
                        <input id="name" type="text" class="validate" name="name" >
                        <label for="name">Nombre</label>
                    </div>
                    <div class="input-field col s12 m4">
                        <input id="last_name_father" type="text" class="validate" name="last_name_father"> 
                        <label for="last_name_father">Primer apellido</label>
                    </div>
                    <div class="input-field col s12 m4">
                        <input id="last_name_mother" type="text" class="validate" name="last_name_mother"> 
                        <label for="last_name_mother">Segundo apellido</label>
                    </div>
                    <div class="input-field col s12 m6">
                        <input type="text" class="datepicker"  name="birthdate" placeholder="Fecha de nacimiento">
                    </div>
                    <div class="input-field col s12 m6">
                        <input id="email" type="email" class="validate" name="email"> 
                        <label for="email">Correo electrónico</label>
                    </div>

                    <div class="input-field col s12 m6">
                        <input id="phone" type="number" class="validate" name="phone"> 
                        <label for="phone">Teléfono</label>
                    </div>
                    <div class="input-field col s12 m6">
                        <input id="password" type="password" class="validate" name="password"> 
                        <label for="password">Contraseña</label>
                    </div>
                    <div class="input-field col s12" style="text-align: -webkit-center;">
                        <button class="btn waves-effect waves-light" type="submit"  value="Login" name="action">Registrarme
                            <i class="material-icons right">person_add</i>
                        </button>
                    </div>
                    <div class="input-field col s12" style="text-align: -webkit-center;">
                        <a class="btn waves-effect waves-light" href=<%if (objPersona != null) {
                                out.println("../main/home.jsp");
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
