<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
    int x=0;
    int id_sesion;
    HttpSession objsesion= request.getSession(false);
    String usuario = (String) objsesion.getAttribute("user");
    if(usuario==null){
       response.sendRedirect("Login.jsp");
    }else{
     x = (int)objsesion.getAttribute("tipo");       
     id_sesion = (int) objsesion.getAttribute("id");
    }   
%>

<html>
    <head>
        <title>Instituto Terra Nova</title>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta name="description" content="Responsive Admin Template" />
        <meta name="author" content="SmartUniversity" />
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
        <link href="assets/css/inicio.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/pages/formlayout.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/theme-color.css" rel="stylesheet" type="text/css" />
        <!-- favicon -->
        <link rel="shortcut icon" href="assets/img/LogoIT.png" />
    </head>
    <body>
        <div class="row">
            <div class="col-lg-12 col-md-12 margen containerMargin" id="inicio">
                <div class="row">
                    <div class="col-lg-4 col-md-4"></div>
                    <div class=" col-lg-2 col-md-4 col-sm-4">
                        <a id="secundaria" href="indexpre.jsp" style="margin-top: 12.9px;" class="btn btn-terra2">Secundaria</a>
                    </div> <br>
                    <div class="col-lg-2 col-md-4 col-sm-4">
                        <a id="preparatoria" href="indexpre.jsp" style="margin-top:12.9px; " class="btn btn-terra2">Preparatoria</a> 
                    </div>

                    <div class="col-lg-4 col-md-4"></div>
                </div>
            </div>
        </div>

        <!-- others scripts-->
        <script type="text/javascript" src="assets/js/pages/Principal/index.js"></script>
        <script type="text/javascript" src="assets/js/pages/Alumnos/Reportes.js" ></script>
        <script type="text/javascript" src="assets/js/pages/Administrador/Alumnos.js"></script>
        <script type="text/javascript" src="assets/js/pages/Administrador/Personal.js"></script>
        <script type="text/javascript" src="assets/js/pages/Administrador/Administrador.js"></script>
        
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
    </body>
</html>
