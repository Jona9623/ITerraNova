<%-- 
    Document   : infoalumno
    Created on : 13/03/2020, 11:38:52 AM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Informacion del Alumno</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Adminalumno.tablaAlumnos();">Tabla Alumnos</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Información del Alumno</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="p-rl-20">
        <ul class="nav customtab nav-tabs" role="tablist">
            <li class="nav-item"><a href="#tab1" class="nav-link active show" data-toggle="tab">Tutor</a></li>
            <li class="nav-item"><a href="#tab2" class="nav-link" data-toggle="tab">Tutor 2</a></li>
            <li class="nav-item"><a href="#tab3" class="nav-link" data-toggle="tab">Tutor 3</a></li>
            <li class="nav-item"><a href="#tab4" class="nav-link" data-toggle="tab">Datos Alumno</a></li>
        </ul>
    </div>
</div>
<div class="profile-content">
    <div class="tab-content">
        <div class="tab-pane fontawesome-demo active show" id="tab1">
            <div class="row">
                <div class="col-md-6 col-6 b-r">
                    <strong>Nombre completo del tutor</strong> <br>
                    <p>${requestScope.tutor.nombre} ${requestScope.tutor.apellidop} ${requestScope.tutor.apellidom}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Ocupacion</strong> <br>
                    <p>${requestScope.tutor.ocupacion}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Parentesco</strong> <br>
                    <p>${requestScope.tutor.parentesco}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Calle, número y colonia del domicilio</strong> <br>
                    <p>${requestScope.tutor.calledom} ${requestScope.tutor.numerodom} ${requestScope.tutor.coloniadom}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Código postal</strong> <br>
                    <p>${requestScope.tutor.codigopostal}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Teléfono de casa</strong> <br>
                    <p>${requestScope.tutor.telefonocasa}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Celular</strong> <br>
                    <p>${requestScope.tutor.celular}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Correo</strong> <br>
                    <p>${requestScope.tutor.correo}</p>
                </div>
            </div>
        </div>
        <div class="tab-pane" id="tab2">
            <div class="row">
                <div class="col-md-6 col-6 b-r">
                    <strong>Nombre completo del tutor</strong> <br>
                    <p>${requestScope.tutor.nombre2} ${requestScope.tutor.apellidop2} ${requestScope.tutor.apellidom2}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Ocupacion</strong> <br>
                    <p>${requestScope.tutor.ocupacion2}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Parentesco</strong> <br>
                    <p>${requestScope.tutor.parentesco2}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Calle, número y colonia del domicilio</strong> <br>
                    <p>${requestScope.tutor.calledom2} ${requestScope.tutor.numerodom2} ${requestScope.tutor.coloniadom2}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Código postal</strong> <br>
                    <p>${requestScope.tutor.codigopostal2}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Teléfono de casa</strong> <br>
                    <p>${requestScope.tutor.telefonocasa2}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Celular</strong> <br>
                    <p>${requestScope.tutor.celular2}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Correo</strong> <br>
                    <p>${requestScope.tutor.correo2}</p>
                </div>
            </div>
        </div>
        <div class="tab-pane" id="tab3">
            <div class="row">
                <div class="col-md-6 col-6 b-r">
                    <strong>Nombre completo del tutor</strong> <br>
                    <p>${requestScope.tutor.nombre3} ${requestScope.tutor.apellidop3} ${requestScope.tutor.apellidom3}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Ocupacion</strong> <br>
                    <p>${requestScope.tutor.ocupacion3}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Parentesco</strong> <br>
                    <p>${requestScope.tutor.parentesco3}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Calle, número y colonia del domicilio</strong> <br>
                    <p>${requestScope.tutor.calledom3} ${requestScope.tutor.numerodom3} ${requestScope.tutor.coloniadom3}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Código postal</strong> <br>
                    <p>${requestScope.tutor.codigopostal3}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Teléfono de casa</strong> <br>
                    <p>${requestScope.tutor.telefonocasa3}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Celular</strong> <br>
                    <p>${requestScope.tutor.celular3}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Correo</strong> <br>
                    <p>${requestScope.tutor.correo3}</p>
                </div>
            </div>
        </div>
        <div class="tab-pane" id="tab4">
            <div class="row">
                <div class="col-md-6 col-6 b-r">
                    <strong>Matrícula del alumno</strong> <br>
                    <p>${requestScope.alumno.matricula}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Nombre completo del alumno</strong> <br>
                    <p>${requestScope.alumno.nombre} ${requestScope.alumno.apellidop} ${requestScope.alumno.apellidom}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Fecha de nacimiento</strong> <br>
                    <p>${requestScope.alumno.fechanacimiento}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Curp</strong> <br>
                    <p>${requestScope.alumno.curp}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Municipio y estado de nacimiento</strong> <br>
                    <p>${requestScope.alumno.municipiona}, ${requestScope.alumno.estadona}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Nacionalidad</strong> <br>
                    <p>${requestScope.alumno.nacionalidad}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Sexo</strong> <br>
                    <p> <c:if test="${requestScope.alumno.sexo == true}"> Hombre</c:if></p>
                    <p> <c:if test="${requestScope.alumno.sexo == false}"> Mujer</c:if></p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Calle, número y colonia de domicilio</strong> <br>
                    <p>${requestScope.alumno.calledom} ${requestScope.alumno.numerodom} ${requestScope.alumno.coloniadom}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Código postal</strong> <br>
                    <p>${requestScope.alumno.codigopostal}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Teléfono de casa</strong> <br>
                    <p>${requestScope.alumno.telefonocasa}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Celular</strong> <br>
                    <p>${requestScope.alumno.celular}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Correo</strong> <br>
                    <p>${requestScope.alumno.correo}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Nivel cursando actualmente</strong> <br>
                    <p>${requestScope.alumno.nivelcursa}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Grado</strong> <br>
                    <p>${requestScope.alumno.grado}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Grupo</strong> <br>
                    <p>${requestScope.alumno.grupo}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Área</strong> <br>
                    <p>${requestScope.alumno.area}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Capacitacion para el trabajo</strong> <br>
                    <p>${requestScope.alumno.cpt}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Plantel de procedencia</strong> <br>
                    <p>${requestScope.alumno.plantelproce}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Nivel anterior</strong> <br>
                    <p>${requestScope.alumno.nivelanterior}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Grado anterior</strong> <br>
                    <p>${requestScope.alumno.gradoanterior}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Turno anterior</strong> <br>
                    <p>${requestScope.alumno.turnoanterior}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Municipio del plantel de procedencia</strong> <br>
                    <p>${requestScope.alumno.municipioante}</p>
                </div>
                <div class="col-md-6 col-6 b-r">
                    <strong>Fotografia del alumno</strong> <br>
                    <img src="file://${requestScope.alumno.foto}">
                </div>
            </div>
        </div>
    </div>
</div>
