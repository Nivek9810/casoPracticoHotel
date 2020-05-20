<%-- 
    Document   : index
    Created on : 16/05/2020, 09:44:08 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sessionStatus = request.getSession();
    sessionStatus.invalidate();
%>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.css" />
        <!-- Compiled and minified CSS -->
        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">-->
        <link rel="stylesheet" type="text/css" href="css/main.css">

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Caso practico hotel</title>
    </head>

    <body>
        <nav>
            <div class="nav-wrapper">
                <a href="#" class="brand-logo right">Logo</a>
            </div>
        </nav>

        <section class="main-content">
            <div class="row">
                <div class="col s12 m6">
                    <h4>Bienvenidos</h4>
                </div>
                <div class="col s12 m6">
                    <div class="card">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="https://edge.media.datahc.com/HI370632075.jpg">
                        </div>
                        <div class="card-content">
                            <span class="card-title activator grey-text text-darken-4">Ingresa aquí <i class="material-icons right">vertical_align_top</i></span>
                            <p><a href="register/register.jsp">Si no estás registrado, ingresa aquí</a></p>
                        </div>
                        <div class="card-reveal">
                            <span class="card-title grey-text text-darken-4">Bienvenido a nuestro hotel<i class="material-icons right">close</i></span>
                            <form class="col s12 login-form" action="Login" method="post">
                                <div class="row">
                                    <div class="input-field col s12 m6">
                                        <input id="username" type="number" class="validate" name="username" required="true">
                                        <label for="username">Usuario</label>
                                    </div>
                                    <div class="input-field col s12 m6">
                                        <input id="password" type="password" class="validate" name="password" required="true"> 
                                        <label for="password">Contraseña</label>
                                    </div>
                                    <div class="input-field col s12" style="text-align: -webkit-center;">
                                        <button class="btn waves-effect waves-light" type="submit"  value="Login" name="action">Ingresar
                                            <i class="material-icons right">person</i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </section>


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
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <!--JavaScript at end of body for optimized loading-->    
        <!--<script type="text/javascript" src="js/materialize.js"></script>-->
    </body>
</html>
