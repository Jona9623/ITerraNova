<%-- 
    Document   : asignahorario
    Created on : 15/04/2020, 12:37:48 PM
    Author     : Complx
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Crear Horario</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Adminalumno.tablaAlumnos();">Tabla Alumnos</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Crear horario</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 s">
        <div class="col-lg-4 p-t-20"></div>
        <div class="col-lg-4 p-t-20">
            <label >Seleccione periodo</label>
            <select id="periodohorario" class="custom-select" name="periodohorario">
                <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                    <option value="${listperiodo.idtbperiodo}">${listperiodo.nombre} </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-lg-4 p-t-20">
            <label >Seleccione una materia</label>
            <select id="materiahorario" name="materiahorario" class="custom-select">
                <c:forEach items="${requestScope.listmateriaalum}" var="listmateriaalum">
                    <option value="${listmateriaalum.idtbmateriaalumno}">${listmateriaalum.nombrecorto} </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-lg-4 p-t-20">
            <label>Hora</label>
            <input id="horahorario" type="text" class="form-control" placeholder="Ej: 12:00-13:00" maxlength="18">
        </div>
        <div class="btn-group">
            <button id="asignahorario" type="button" class="btn btn-terra">Agregar a horario</button> 
        </div>
    </div>
</div>
