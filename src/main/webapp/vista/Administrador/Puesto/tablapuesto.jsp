<%-- 
    Document   : tablapuesto
    Created on : 17/02/2020, 11:42:09 AM
    Author     : Complx
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Tabla Puesto</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Mostrar/Agregar Puesto</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="row p-b-20">
        <div class="col-md-6 col-sm-6 col-6">
            <div class="btn-group">
                <a data-toggle="modal" data-target="#modalPuesto" class="btn btn-terra">Agregar Puesto</a> <br>
            </div>
        </div>
    </div>

    <table id="tablapuesto" class="table table-bordered" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th class="center">Estatus</th>
                <th class="center">Nombre</th>
                <th class="center">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listpuesto}" var="listpuesto">
                <tr>
                    <td class="center" style="display: none">${listpuesto.idtbpuesto}</td>
                    <td class="center"><c:if test="${listpuesto.status == 1}">Activo</c:if></td>
                    <td class="center">${listpuesto.nombre}</td>
                    <td class="center">
                        <div class="btn-group">
                            <button class="btn btn-xs btn-terra dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Acciones
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-left" role="menu">
                                <li>
                                    <a href="javascript:;" class="editarpuesto" data-toggle="modal" data-target="#modalPuestoEditar">
                                        <i id="editarpe" class="icon-pencil"></i> Editar </a>
                                </li>
                                <li>
                                    <a href="javascript:;" class="eliminarpuesto">
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
<div class="modal fade" id="modalPuesto" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                        <header>Formulario Puesto</header>
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
                                            <label>Puesto</label>
                                            <input id="idpuesto" type="hidden" value="${requestScope.puesto.idtbpuesto}">
                                            <input type="text" id="puestoAdmin" class="form-control" value="" placeholder="nombre...">
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
                <button type="button" id="guardapuesto" class="btn btn-terra">Guardar</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modalPuestoEditar" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                        <header>Formulario Puesto</header>
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
                                            <label>Puesto</label>
                                            <input id="idpuesto" type="hidden" value="${requestScope.puesto.idtbpuesto}">
                                            <input type="text" id="puestoAdmin" class="form-control" value="${requestScope.puesto.nombre}" placeholder="nombre...">
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
                <button type="button" id="guardapuesto" class="btn btn-terra">Guardar</button>
            </div>
        </div>
    </div>
</div>                                            
