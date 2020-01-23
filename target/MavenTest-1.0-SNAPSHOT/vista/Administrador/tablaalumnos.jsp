<%-- 
    Document   : tablaalumnos
    Created on : 16/01/2020, 01:13:52 PM
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
            <li class="active">Mostrar/Agregar Alumnos</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="row p-b-20">
        <div class="col-md-6 col-sm-6 col-6">
            <div class="btn-group">
                <button id="btnagregaA"type="button" class="btn btn-terra">Agregar Alumno</button> <br>
            </div>
        </div>
    </div>

    <table id="tablaalumnos" class="table table-bordered">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th style="display: none"></th>
                <th class="center">Nombre</th>
                <th class="center">A. Paterno</th>
                <th class="center">A. Materno</th>
                <th class="center">Matricula</th>
                <th class="center">Grado y Grupo</th>
                <th class="center">Tutor</th>
                <th class="center">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listalumnos}" var="listalumnos">
                <tr>
                    <td class="center" style="display: none">${listalumnos.idtbalumnos}</td>
                    <td class="center" style="display: none">${listalumnos.rtutor}</td>
                    <td class="center">${listalumnos.nombre}</td>
                    <td class="center">${listalumnos.apellidop}</td>
                    <td class="center">${listalumnos.apellidom}</td>
                    <td class="center">${listalumnos.matricula}</td>
                    <td class="center">${listalumnos.rgrado}  ${listalumnos.grupo}</td>
                    <td class="center">${listalumnos.tutor}</td>
                    <td class="center">
                        <div class="btn-group">
                            <button class="btn btn-xs deepPink-bgcolor dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-left" role="menu">
                                <li>
                                    <a href="javascript:;" class="editaralu">
                                        <i id="editaralu" class="material-icons">create</i> Editar </a>
                                </li>
                                <li>
                                    <a href="javascript:;" class="aliminaralu">
                                        <i id="eliminaralu" class="material-icons">delete</i> Eliminar </a>
                                </li>
                                <li>
                                    <a href="javascript:;">
                                        <i class="icon-user"></i> otra opcion</a>
                                </li>
                                <li class="divider"> </li>
                                <li>
                                    <a href="javascript:;">
                                        <i class="icon-flag"></i> otra opcion </a>
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
