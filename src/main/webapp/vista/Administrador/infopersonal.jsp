<%-- 
    Document   : infopersonal
    Created on : 13/03/2020, 12:28:37 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Informacion del Personal</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Adminpersonal.tablaPersonal();">Tabla Personal</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Información del Personal</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="p-rl-20">
        <ul class="nav customtab nav-tabs" role="tablist">
            <li class="nav-item"><a href="#tab1" class="nav-link active show" data-toggle="tab">Personal</a></li>
        </ul>
    </div>
</div>
<div class="profile-content">
    <div class="tab-content">
        <div class="tab-pane fontawesome-demo active show" id="tab1">
            <div class="row">
                <div class="col-md-6 col-6 b-r">
                    <strong>Nombre completo del personal</strong> <br>
                    <p>${requestScope.personal.nombre} ${requestScope.personal.apellidop} ${requestScope.personal.apellidom}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Fecha de nacimiento</strong> <br>
                    <p>${requestScope.personal.fechanacimiento}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Curp</strong> <br>
                    <p>${requestScope.personal.curp}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Municipio y estdo de nacimiento</strong> <br>
                    <p>${requestScope.personal.municipionac} ${requestScope.personal.estadonac}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Nacionalidad</strong> <br>
                    <p>${requestScope.personal.nacionalidad}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Sexo</strong> <br>
                    <p><c:if test="${requestScope.personal.sexo == true}"> Hombre</c:if></p>
                    <p><c:if test="${requestScope.personal.sexo == false}"> Mujer</c:if></p>
                    </div>
                    <div class="col-md-6 col-6 b-r">
                        <strong>Calle, número y coonia de domicilio</strong> <br>
                        <p>${requestScope.personal.calledom} ${requestScope.personal.numerodom} ${requestScope.personal.coloniadom}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Código postal</strong> <br>
                    <p>${requestScope.personal.codigopostal}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Teléfono de casa</strong> <br>
                    <p>${requestScope.personal.telefonocasa}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Celular</strong> <br>
                    <p>${requestScope.personal.celular}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Correo</strong> <br>
                    <p>${requestScope.personal.correo}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Número de segur osocial</strong> <br>
                    <p>${requestScope.personal.nss}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>RFC</strong> <br>
                    <p>${requestScope.personal.rfc}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Nivel máximo de estudios</strong> <br>
                    <p>${requestScope.personal.nivelestudios}</p>
                </div>
                <c:if test="${requestScope.personal.licenciatura != null}">
                    <div class="col-md-6 col-6 b-r">
                        <strong>Licenciatura</strong> <br>
                        <p>${requestScope.personal.licenciatura}</p>
                    </div>
                </c:if>
                <c:if test="${requestScope.personal.maestria != null}">
                    <div class="col-md-6 col-6 b-r">
                        <strong>Maestria</strong> <br>
                        <p>${requestScope.personal.maestria}</p>
                    </div>
                </c:if>
                <c:if test="${requestScope.personal.doctorado != null}">
                    <div class="col-md-6 col-6 b-r">
                        <strong>Doctorado</strong> <br>
                        <p>${requestScope.personal.doctorado}</p>
                    </div>
                </c:if>
                <div class="col-md-6 col-6 b-r">
                    <strong>Puesto</strong> <br>
                    <p>${requestScope.personal.puesto}</p>
                </div>
            </div>
        </div>
    </div>
</div>
