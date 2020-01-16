<!DOCTYPE html>
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
                                    <li>
                                        <a href="login.html">
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
                                <li class="nav-item start ">
                                    <a href="#" class="nav-link nav-toggle">
                                        <i class="material-icons">label</i>
                                        <span id="opcion2" class="title">Opcion 2</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub-menu">
                                        <li class="nav-item ">
                                            <a id="subopcion2" class="nav-link ">
                                                <span class="title">Sub opcion</span>
                                            </a>
                                        </li>
                                    </ul>

                                </li>
                                <li class="nav-item start">
                                    <a href="#" class="nav-link nav-toggle">
                                        <i class="material-icons">label</i>
                                        <span id="Administrador" class="title">Administrador</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub-menu">
                                        <li class="nav-item">
                                            <a id="amalumnos" class="nav-link ">
                                                <span class="title">Mostrar/Agregar Alumnos</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="ampersonal" class="nav-link ">
                                                <span class="title">Mostrar/Agregar Personal</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- end sidebar menu --> 
                <!-- start page content -->

                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div id="content">
                            <img src="assets/img/Iterra-1.jpg" class="img-fluid img-responsive" style="width:100%" alt="Responsive image">
                        </div>
                    </div></div>
                <!-- end page content -->
            </div>
            <!-- end page container -->
            <!-- start footer -->
            <div class="page-footer">
                <div class="scroll-to-top">
                    <i class="icon-arrow-up"></i>
                </div>
            </div>
            <!-- end footer -->
        </div>
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

        <!-- summernote -->
        <script src="assets/plugins/summernote/summernote.min.js" ></script>
        <script src="assets/js/pages/summernote/summernote-data.js" ></script>
        <script src="assets/js/index.js" ></script>
        <script type="text/javascript" src="assets/js/pages/Alumnos/Reportes.js" ></script>
        <script type="text/javascript" src="assets/js/pages/AdminAlumnos/Alumnos.js"></script>
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

        <!-- end js include path -->
    </body>
</html>