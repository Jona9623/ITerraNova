
<%-- 
    Document   : Login
    Created on : 6/02/2020, 11:20:45 AM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
    HttpSession objsesion = request.getSession(false);
    String usuario = (String) objsesion.getAttribute("user");
    if (usuario != null) {
        response.sendRedirect("indexpre.jsp");
    }
%>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta name="description" content="Responsive Admin Template" />
        <meta name="author" content="SmartUniversity" />
        <title>Inicio de sesión | Instituto Terra Nova</title>
        <!-- google font -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet" type="text/css" />
        <!-- icons -->
        <link href="fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="fonts/material-design-icons/material-icon.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap -->
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- style -->
        <link rel="stylesheet" href="assets/css/pages/extra_pages.css">
        <!-- favicon -->
        <link rel="shortcut icon" href="assets/img/LogoIT.png" /> 
    </head>
    <body>
        <div class="form-title">
            <img class="img-responsive center" src="assets/img/LogoTerraNova.png" style="width: 30%;">
        </div>
        <!-- Login Form-->
        <div class="login-form text-center">
            <div class=""><i class=""></i>
            </div>
            <div class="form formLogin">
                <h2>Inicia Sesión </h2>
                <form id="formlogin" name="formlogin" action="Login" method="POST">
                    <input name="user" type="text" placeholder="Usuario" />
                    <input name="password" type="password" placeholder="Contraseña" />
                    <div class="form-group">
                        <div class="radio p-0">
                            <label for="tipoescuela">
                                Secundaria
                            </label>
                            <input type="radio" name="escuela" id="secundaria" value="secundaria">
                            <label for="tipoescuela">
                                Preparatoria
                            </label>
                            <input type="radio" name="escuela" id="preparatoria" value="preparatoria">
                        </div>
                    </div>
                    <input type="text" name="input" id="input" value="" hidden="true">
                    <button type="submit" class="btn-terra2">Iniciar sesión</button>
                </form>
            </div>
        </div>
        <!-- start js include path -->
        <script src="assets/plugins/jquery/jquery.min.js" ></script>
        <script src="assets/js/pages/extra_pages/pages.js" ></script>
        <script type="text/javascript" src="assets/js/pages/Administrador/Administrador.js"></script>
        <script src="assets/js/index.js"></script>
        <!-- end js include path -->
    </body>
</html>
