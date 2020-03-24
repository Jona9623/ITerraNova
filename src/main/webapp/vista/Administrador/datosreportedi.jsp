<%-- 
    Document   : datosreportedi
    Created on : 30/01/2020, 11:28:32 AM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Reporte Disciplinar</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Reportes.reporteDisciplinar();">Reporte Disciplinar</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Reportes.mostrarReportes();">Tabla Reportes Disciplinares</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Datos Reporte Disciplinar</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-md-6 col-6 b-r">
        <strong>Nombre completo del alumno</strong> <br>
        <p>${requestScope.reporteD.alumno} ${requestScope.reporteD.alumnoapep} ${requestScope.reporteD.alumnoapem}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Grado</strong> <br>
        <p>${requestScope.reporteD.grado}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Grupo</strong> <br>
        <p>${requestScope.reporteD.grupo}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Personal que solicitó el reporte</strong> <br>
        <p>${requestScope.reporteD.personalsolicita}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Perosnal que llenó el reporte</strong> <br>
        <p>${requestScope.reporteD.personalllena}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Personal de la materia</strong> <br>
        <p> <c:if test="${requestScope.reporteD.personal == null}"> No aplica</c:if>
            ${requestScope.reporteD.personal}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Materia</strong> <br>
        <p><c:if test="${requestScope.reporteD.materia == null}"> No aplica</c:if>
            ${requestScope.reporteD.materia}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Fecha</strong> <br>
        <p>${requestScope.reporteD.fecha}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Fecha del reporte</strong> <br>
        <p>${requestScope.reporteD.fechareporte}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Hora</strong> <br>
        <p>${requestScope.reporteD.hora}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Lugar</strong> <br>
        <p>${requestScope.reporteD.lugar}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Incidente ocurrido</strong> <br>
        <p>${requestScope.reporteD.tipoincidente}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <strong>Nivel de gravedad</strong> <br>
        <p> <c:if test="${requestScope.reporteD.nivel == 1}">No tan grave</c:if></p>
        <p> <c:if test="${requestScope.reporteD.nivel == 21}">Grave</c:if></p>
        <p> <c:if test="${requestScope.reporteD.nivel == 3}">Muy grave</c:if></p>
        </div>
        <div class="col-md-6 col-6 b-r">
            <strong>Descripcion</strong> <br>
            <p>${requestScope.reporteD.descripcion}</p>
    </div>
    <div class="col-md-6 col-6 b-r">
        <br> <br>
        <strong class="center">Eviddencia del reporte</strong> <br>
        <img class="img-responsive center" src="${requestScope.reporteD.foto}">
    </div>
</div>