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
            <div class="page-title">Tabla Personal</div>
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
            <div class="btn-group">
                <a data-toggle="modal" data-target="#modalArchivoP" id="importaPersonal"type="button" class="btn btn-terra">Importar Alumno</a> <br> 
            </div>
        </div>
    </div>

    <table id="tablapersonal" class="table table-bordered" style="width:100%">
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
                    <td class="center" style="display: none">${listpersonal.idtbpersonal}</td>
                    <td class="center">${listpersonal.nombre}</td>
                    <td class="center">${listpersonal.apellidop}</td>
                    <td class="center">${listpersonal.apellidom}</td>
                    <td class="center">${listpersonal.correo}</td>
                    <td class="center">${listpersonal.puesto}</td>
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
<div class="modal fade" id="modalArchivoP" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                    </div></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="">
                            <form id="importarPersonal" enctype="multipart/form-data" method="POST" action="SAdminpersonal" name="importarpersonal">
                                <input type="text" name="ACCION" id="ACCION" value="importaPersonal" hidden="true">
                                <div class="card-body row">
                                    <div class="col-lg-10 p-t-20"> 
                                        <div class="form-group">
                                            <label>Seleccione archivo para importar</label>
                                            <input id="importapersonal" type="file" name="importaPersonal">
                                        </div>
                                    </div><br>
                                </div>
                                <button type="submit" id="" class="btn btn-terra" >Importar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="actualizaTablaP" class="btn btn-secondary" data-dismiss="modal">Cerrar y actualizar tabla</button>
            </div>
        </div>
    </div>
</div>
