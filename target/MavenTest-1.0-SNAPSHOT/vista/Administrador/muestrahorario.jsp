<%-- 
    Document   : muestrahorario
    Created on : 17/04/2020, 12:48:57 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Horarios</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Adminalumno.tablaAlumnos();">Tabla Alumnos</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Horarios</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 s">
        <div class="col-lg-4 p-t-20"></div>
        <div class="col-lg-4 p-t-20">
            <label >Seleccione periodo</label>
            <select id="periodogethorario" class="custom-select" name="periodogetshorario">
                <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                    <option value="${listperiodo.idtbperiodo}">${listperiodo.nombre} </option>
                </c:forEach>
            </select>
        </div>
        <br> <br>
        <div id="getHorarios">
        </div>
    </div>
</div>
