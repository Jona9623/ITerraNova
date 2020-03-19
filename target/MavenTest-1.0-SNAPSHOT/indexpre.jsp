<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    int x = 0;
    int id_sesion;
    HttpSession objsesion = request.getSession(false);
    String usuario = (String) objsesion.getAttribute("user");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
    } else {
        x = (int) objsesion.getAttribute("tipo");
        id_sesion = (int) objsesion.getAttribute("id");
    }
%><!DOCTYPE html>
<html lang="en">
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta name="description" content="Responsive Admin Template" />
        <meta name="author" content="SmartUniversity" />
        <title>Instituto Terra Nova</title>
        <!-- google font -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet" type="text/css" />
        <!-- icons -->
        <link href="assets/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="fonts/material-design-icons/material-icon.css" rel="stylesheet" type="text/css" />
        <!--bootstrap -->
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/plugins/summernote/summernote.css" rel="stylesheet">
        <!-- Material Design Lite CSS -->
        <link rel="stylesheet" href="assets/plugins/material/material.min.css">
        <link rel="stylesheet" href="assets/css/material_style.css">
        <!-- animation -->
        <link href="assets/css/pages/animate_page.css" rel="stylesheet">
        <!-- inbox style -->
        <link href="assets/css/pages/inbox.min.css" rel="stylesheet" type="text/css" />
        <!-- data tables -->
        <link href="assets/plugins/datatables/plugins/bootstrap/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css"/>

        <!-- Theme Styles -->
        <link href="assets/css/style.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/pages/formlayout.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/theme-color.css" rel="stylesheet" type="text/css" />

        <!-- Sweet alert-->
        <link rel="stylesheet" href="assets/plugins/sweet-alert/sweetalert.min.css">

        <!-- Data Tables -->
        <link href="assets/plugins/datatables/plugins/bootstrap/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css"/>

        <link href="assets/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/style.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/pages/formlayout.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/theme-color.css" rel="stylesheet" type="text/css" />
        <!-- favicon -->
        <link rel="shortcut icon" href="assets/img/LogoIT.png" /> 
    </head>
    <!-- END HEAD -->
    <body class="page-header-fixed sidemenu-closed-hidelogo page-content-white page-md header-terra1 white-sidebar-color logo-white">
        <img style="width: 500; height: 500;" src="file://C:/Users/Complx/Desktop/Paisaje.jpg">
        
        <div class="page-wrapper">
            <!-- start header -->
            <div class="page-header navbar navbar-fixed-top">
                <div class="page-header-inner ">
                    <!-- logo start -->
                    <div class="page-logo">
                        <a href="index.html">
                            <img alt="" src="assets/img/LogoIT.png">
                            <span class="logo-default" >Instituto Terra Nova</span> </a>
                    </div>
                    <!-- logo end -->
                    <ul class="nav navbar-nav navbar-left in">
                        <li><a href="#" class="menu-toggler sidebar-toggler"><i class="icon-menu"></i></a></li>
                    </ul>                    
                    <!-- start mobile menu -->
                    <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
                        <span></span>
                    </a>
                    <!-- end mobile menu -->
                    <!-- start header menu -->
                    <div class="top-menu">
                        <ul class="nav navbar-nav pull-right">
                            <!-- start notification dropdown -->
                            <!-- start manage user dropdown -->
                            <li class="dropdown dropdown-user">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <span class="username username-hide-on-mobile"> Opciones </span>
                                    <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-default animated jello">                                    
                                    <% if (x == 5) { %>
                                    <li>
                                        <a data-toggle="modal" data-target="#modalUsuario">
                                            <i class="icon-bell"></i> Agregar usuario </a>
                                    </li>
                                    <% } %>
                                    <li>
                                        <a href="Logout">
                                            <i class="icon-logout"></i> Salir </a>
                                    </li>
                                </ul>
                            </li>
                            <!-- end manage user dropdown -->                            
                        </ul>
                    </div>
                </div>
            </div>
            <!-- end header -->
            <!-- start page container -->
            <div class="page-container">
                <!-- start sidebar menu -->
                <div class="sidebar-container">
                    <div class="sidemenu-container navbar-collapse collapse fixed-menu">
                        <div id="remove-scroll">
                            <ul class="sidemenu  page-header-fixed" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
                                <li class="sidebar-toggler-wrapper hide">
                                    <div class="sidebar-toggler">
                                        <span></span>
                                    </div>
                                </li>
                                <li class="sidebar-user-panel">
                                    <div class="user-panel">
                                        <div class="pull-left image">
                                        </div>
                                    </div>
                                </li>
                                <% if (x == 1 || x == 2 || x == 3 || x == 5) { %>
                                <li class="nav-item start">
                                    <a href="#" class="nav-link nav-toggle">
                                        <i class="material-icons">label</i>
                                        <span id="Alumnos" class="title">Alumnos</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub-menu">
                                        <li class="nav-item">
                                            <a id="reportedis" class="nav-link ">
                                                <span class="title">Reporte disciplinar</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="reporteaca" class="nav-link ">
                                                <span class="title">Reporte académico</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <% } %>
                                <% if (x == 3 || x == 4 || x == 5) { %>
                                <li class="nav-item start">
                                    <a href="#" class="nav-link nav-toggle">
                                        <i class="material-icons">label</i>
                                        <span id="Administrador" class="title">Control Escolar</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub-menu">
                                        <li class="nav-item">
                                            <a id="amalumnos" class="nav-link ">
                                                <span class="title"> <strong>Mostrar/Agregar Alumnos</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="ampersonal" class="nav-link ">
                                                <span class="title"><strong>Mostrar/Agregar Personal</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="ampuesto" class="nav-link ">
                                                <span class="title"><strong>Mostrar/Agregar Puesto</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="amperiodo" class="nav-link ">
                                                <span class="title"><strong>Mostrar/Agregar Periodo escolar</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="amsemana" class="nav-link" data-toggle="modal" data-target="#exampleModalCenter1">
                                                <span class="title"><strong>Agregar Semana Fiscal</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="amarea" class="nav-link ">
                                                <span class="title"><strong>Mostrar/Agregar Área de alumno</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="amcpt" class="nav-link ">
                                                <span class="title"><strong>Mostrar/Agregar Capacitacion para el Trabajo alumno</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="amgrado" class="nav-link " data-toggle="modal" data-target="#modalGrado">
                                                <span class="title"><strong>Agregar Grado</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="amgrupo" class="nav-link " data-toggle="modal" data-target="#modalGrupo">
                                                <span class="title"><strong>Agregar Grupo</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="amtipocalificacion" class="nav-link ">
                                                <span class="title"><strong>Mostrar/Agregar Tipo calificacion</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="amnombrematerias" class="nav-link " data-toggle="modal" data-target="#modalNombreMateria">
                                                <span class="title"><strong>Agregar Nombres de materias</strong></span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="amasignacionmaterias" class="nav-link ">
                                                <span class="title"><strong>Asignacion de Materias</strong></span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <% }%>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- end sidebar menu --> 
                <!-- start page content -->

                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div id="content">
                            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img class="d-block w-100 img-responsive" src="assets/img/Slider1.jpg" style="width: 100%" alt="First slide">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5>Planteles</h5>
                                            <p>Entorno óptimo para tu desrrollo</p>
                                        </div>
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block w-100 img-responsive" src="assets/img/Slider2.jpg" style="width: 100%" alt="Second slide">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5>Nosotros</h5>
                                            <p>Modelo educativo inovador</p>
                                        </div>
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block w-100 img-responsive" src="assets/img/slider3.jpg" style="width: 100%" alt="Third slide">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5>Nosotros</h5>
                                            <p>Potenciando tus habilidades</p>
                                        </div>
                                    </div>
                                </div>
                                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>
                    </div></div>
                <!-- end page content -->
                <!-- start footer -->
                <div class="page-footer">
                    <div class="scroll-to-top">
                        <i class="icon-arrow-up">Instituto Terra Nova</i>
                    </div>
                </div>
                <!-- end footer -->
            </div>
            <!-- end page container -->
        </div>
        <% if (x == 5 || x == 4 || x == 3) { %>
        <div class="modal fade" id="modalGrado" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                                <header>Formulario Grado</header>
                            </div></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="">
                                    <form class="">
                                        <div class="card-body row">
                                            <div class="col-lg-10 p-t-20"> 
                                                <div class="form-group">
                                                    <label>Grado</label>
                                                    <input type="number" id="gradoAdmin" class="form-control" placeholder="nombre...">
                                                </div>
                                            </div><br>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        <button type="button" id="guardagrado" class="btn btn-terra">Guardar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalGrupo" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                                <header>Formulario Grupo</header>
                            </div></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="">
                                    <form class="">
                                        <div class="card-body row">
                                            <div class="col-lg-10 p-t-20"> 
                                                <div class="form-group">
                                                    <label>Grupo</label>
                                                    <input type="text" id="grupoAdmin" class="form-control" placeholder="nombre...">
                                                </div>
                                            </div><br>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        <button type="button" id="guardagrupo" class="btn btn-terra">Guardar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="exampleModalCenter1" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                                <header>Agregar semana fiscal</header>
                            </div></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="">
                                    <form class="">
                                        <div class="card-body row">
                                            <div class="col-lg-10 p-t-20"> 
                                                <div class="form-group">
                                                    <label>Semana fiscal</label>
                                                    <input type="text" id="semana" class="form-control" placeholder="Ej: lunes 15 sep- viernes 25 sep">
                                                </div>
                                            </div><br>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary guardasemana" data-dismiss="modal">Cerrar</button>
                        <button type="button" id="guardasemana" class="btn btn-terra" data-dismiss="modal">Guardar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalNombreMateria" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                                <header>Formulario Materia</header>
                            </div></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="">
                                    <form class="">
                                        <div class="card-body row">
                                            <div class="col-lg-10 p-t-20"> 
                                                <div class="form-group">
                                                    <label>Nombre largo de materia</label>
                                                    <input type="text" id="nombrelargoAdmin" class="form-control" placeholder="nombre...">
                                                </div>
                                                <div class="form-group">
                                                    <label>Nombre corto de materia</label>
                                                    <input type="text" id="nombrecortoAdmin" class="form-control" placeholder="nombre...">
                                                </div>
                                            </div><br>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        <button type="button" id="guardanombremateria" class="btn btn-terra">Guardar</button>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        <% if (x == 5) {%>
        <div class="modal fade" id="modalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                                <header>Formulario Usuario</header>
                            </div></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="">
                                    <div class="">
                                        <h2>Nueva cuenta</h2>
                                        <form action="RegistroLogin" method="POST">
                                            <input name="newuser"  type="text" placeholder="Usuario" />
                                            <input name="newpassword" type="password" placeholder="Contraseña" /> <br>
                                            <label >Seleccione un tipo de usuario</label>
                                            <select name="newtipo" id="tipousuario" class="custom-select" name="tipousuario">
                                                <option value="1">Prefectos</option>
                                                <option value="2">Maestros</option>
                                                <option value="3">Sub director</option>
                                                <option value="4">Control escolar</option>
                                                <option value="5">Administrador</option>
                                            </select> <br> <br>
                                            <button class="btn-terra2">Registrar</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <% }%>
        <!-- start js include path -->
        <script src="assets/plugins/jquery/jquery.min.js" ></script>
        <script src="assets/plugins/popper/popper.min.js" ></script>
        <script src="assets/plugins/jquery-blockui/jquery.blockui.min.js" ></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- bootstrap -->
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js" ></script>
        <script src="assets/plugins/sparkline/jquery.sparkline.min.js" ></script>
        <script src="assets/js/pages/sparkline/sparkline-data.js" ></script>
        <script src="assets/plugins/bootstrap-inputmask/bootstrap-inputmask.min.js" ></script>
        <script src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"  charset="UTF-8"></script>
        <script src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker-init.js"  charset="UTF-8"></script>

        <!--Alerts-->
        <script src="assets/plugins/sweet-alert/sweetalert.min.js" ></script>
        <script src="assets/js/pages/sweet-alert/sweet-alert-data.js" ></script>

        <script src="assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"  charset="UTF-8"></script>
        <script src="assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker-init.js"  charset="UTF-8"></script>
        <!-- Common js-->
        <script src="assets/js/app.js" ></script>
        <script src="assets/js/layout.js" ></script>
        <script src="assets/js/theme-color.js" ></script>
        <!-- material -->
        <script src="assets/plugins/material/material.min.js"></script>
        <!-- animation -->
        <script src="assets/js/pages/ui/animations.js" ></script>
        <script src="assets/js/pages/material_select/getmdl-select.js" ></script>
        <script  src="assets/plugins/material-datetimepicker/moment-with-locales.min.js"></script>
        <script  src="assets/plugins/material-datetimepicker/bootstrap-material-datetimepicker.js"></script>
        <script  src="assets/plugins/material-datetimepicker/datetimepicker.js"></script>
        <!-- chart js -->
        <script src="assets/plugins/chart-js/Chart.bundle.js" ></script>
        <script src="assets/plugins/chart-js/utils.js" ></script>
        <script src="assets/js/pages/chart/chartjs/home-data.js" ></script>
        <!-- data tables -->
        <script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="assets/plugins/datatables/plugins/bootstrap/dataTables.bootstrap4.min.js" ></script>
        <script src="assets/js/pages/table/table_data.js" ></script>

        <script type="text/javascript" src="assets/js/jquery-form.js"></script>

        <!-- summernote -->
        <script src="assets/plugins/summernote/summernote.min.js" ></script>
        <script src="assets/js/pages/summernote/summernote-data.js" ></script>
        <script src="assets/js/index.js" ></script>
        <!-- others scripts-->
        <script type="text/javascript" src="assets/js/pages/Principal/index.js"></script>
        <script type="text/javascript" src="assets/js/pages/Alumnos/Reportes.js" ></script>
        <script type="text/javascript" src="assets/js/pages/Administrador/Alumnos.js"></script>
        <script type="text/javascript" src="assets/js/pages/Administrador/Personal.js"></script>
        <script type="text/javascript" src="assets/js/pages/Administrador/Administrador.js"></script>

        <!-- data tables -->
        <script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="assets/plugins/datatables/plugins/bootstrap/dataTables.bootstrap4.min.js" ></script>
        <script src="assets/js/pages/table/table_data.js" ></script>
        <!-- dropzone -->
        <script src="assets/plugins/dropzone/dropzone.js" ></script>
        <!--tags input-->
        <script src="assets/plugins/jquery-tags-input/jquery-tags-input.js" ></script>
        <script src="assets/plugins/jquery-tags-input/jquery-tags-input-init.js" ></script>
        <!--select2-->
        <script src="assets/plugins/select2/js/select2.js" ></script>
        <script src="assets/js/pages/select2/select2-init.js" ></script>
        <!--Canvas script-->
        <script type="text/javascript" src="assets/js/canvas/canvasjs.min.js"></script>
        <script type="text/javascript" src="assets/js/canvas/canvasjs.react.js"></script>
        <script type="text/javascript" src="assets/js/canvas/jquery.canvasjs.min.js"></script>
        <script type="text/javascript" src="assets/js/canvas/html2canvas.js"></script>
        <script type="text/javascript" src="assets/js/canvas/FileSaver.js"></script>

        <!-- end js include path -->
    </body>
</html>