<%-- 
    Document   : tablapersonal
    Created on : 17/01/2020, 10:56:41 AM
    Author     : complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Tabla Alumnos</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Mostrar/Agregar Personal</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="row p-b-20">
                    <div class="col-md-6 col-sm-6 col-6">
                        <div class="btn-group">
                            <button id="btnaregarP"type="button" class="btn btn-terra">Agregar Personal</button> <br>
                        </div>
                    </div>
                </div>
        
    <table id="tablapersonal" class="table table-bordered">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th class="center">Nombre</th>
                <th class="center">A. Paterno</th>
                <th class="center">A. Materno</th>
                <th class="center">Correo</th>
                <th class="center">Puesto</th>
                <th class="center">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listpersonal}" var="listpersonal">
            <tr>
                <th class="center" style="display: none"> ${listpersonal.idtbpersonal}</th>
                <th class="center">${listpersonal.nombre}</th>
                <td class="center">${listpersonal.apellidop}</td>
                <td class="center">${listpersonal.apellidom}</td>
                <td class="center">${listpersonal.correo}</td>
                <td class="center">${listpersonal.puesto}</td>
                <td class="center">Botones de acciones</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
