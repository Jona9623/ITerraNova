<%-- 
    Document   : tablacptalumno
    Created on : 17/02/2020, 11:45:46 AM
    Author     : Complx
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Tabla Cpt Alumno</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Mostrar/Agregar Cpt</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="row p-b-20">
        <div class="col-md-6 col-sm-6 col-6">
            <div class="btn-group">
                <a data-toggle="modal" data-target="#modalCpt" class="btn btn-terra">Agregar Cpt</a> <br>
            </div>
        </div>
    </div>

    <table id="tablacpt" class="table table-bordered" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th class="center">Estatus</th>
                <th class="center">Nombre</th>
                <th class="center">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listcpt}" var="listcpt">
                <tr>
                    <td class="center" style="display: none"></td>
                    <td class="center"><c:if test="${listcpt.status == 1}">Activo</c:if></td>
                    <td class="center">${listcpt.nombre}</td>
                    <td class="center">
                        <div class="btn-group">
                            <button class="btn btn-xs btn-terra dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Acciones
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-left" role="menu">
                                <li>
                                    <a href="javascript:;" class="editarpe">
                                        <i id="editarpe" class="icon-pencil"></i> Editar </a>
                                </li>
                                <li>
                                    <a href="javascript:;" class="aliminarpe">
                                        <i id="aliminarpe" class="icon-trash"></i> Eliminar </a>
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
<div class="modal fade" id="modalCpt" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                        <header>Formulario CPT</header>
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
                                            <label>Capacitacion Para el Trabajo</label>
                                            <input type="text" id="cptAdmin" class="form-control" placeholder="nombre...">
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
                <button type="button" id="guardarcpt" class="btn btn-terra">Guardar</button>
            </div>
        </div>
    </div>
</div>
