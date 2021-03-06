<%-- 
    Document   : tablaperiodo
    Created on : 17/02/2020, 11:44:37 AM
    Author     : Complx
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Tabla Periodo</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Mostrar/Agregar Periodo</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="row p-b-20">
        <div class="col-md-6 col-sm-6 col-6">
            <div class="btn-group">
                <a data-toggle="modal" data-target="#modalPeriodo" class="btn btn-terra">Agregar Periodo</a> <br>
            </div>
        </div>
    </div>

    <table id="tablaperiodo" class="table table-bordered" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th class="center">Estatus</th>
                <th class="center">Nombre</th>
                <th class="center">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                <tr>
                    <td class="center" style="display: none">${listperiodo.idtbperiodo}</td>
                    <td class="center"><c:if test="${listperiodo.status == 1}">Activo</c:if></td>
                    <td class="center">${listperiodo.nombre}</td>
                    <td class="center">
                        <div class="btn-group">
                            <button class="btn btn-xs btn-terra dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Acciones
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-left" role="menu">
                                <li>
                                    <a href="javascript:;" class="eliminarperiodo">
                                        <i id="aliminarpe" class="icon-trash"></i> Eliminar </a>
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </c:forEach> 
        </tbody>
    </table>
</div>
<div class="modal fade" id="modalPeriodo" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                        <header>Formulario Periodo</header>
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
                                            <label>Periodo completo</label>
                                            <input type="text" id="periodoAdmin" class="form-control" placeholder="Ej: Ago 2020 - Dic 2020">
                                        </div>
                                        <div class="form-group">
                                            <label>Fecha inicio</label>
                                            <input type="date" id="fechainicioAdmin" class="form-control" placeholder="">
                                        </div>
                                        <div class="form-group">
                                            <label>Fecha fin </label>
                                            <input type="date" id="fechafinAdmin" class="form-control" placeholder="">
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
                <button type="button" id="guardaperiodo" class="btn btn-terra" data-dismiss="modal">Guardar</button>
            </div>
        </div>
    </div>
</div>