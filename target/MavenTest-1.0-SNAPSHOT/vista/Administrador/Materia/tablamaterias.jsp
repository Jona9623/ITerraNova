<%-- 
    Document   : tablamaterias
    Created on : 17/02/2020, 11:48:14 AM
    Author     : Complx
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Tabla Materias</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Mostrar/Agregar Materia</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="row p-b-20">
        <div class="col-md-6 col-sm-6 col-6">
            <div class="btn-group">
                <a data-toggle="modal" data-target="#modalMateria" class="btn btn-terra">Asignar Materia</a> <br>
            </div>
        </div>
    </div>

    <table id="tablamateria" class="table table-bordered" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th class="center">Estatus</th>
                <th class="center">Nombre Largo</th>
                <th class="center">Nombre Corto</th>
                <th class="center">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listmateria}" var="listmateria">
                <tr>
                    <td class="center" style="display: none">${listmateria.idtbmateria}</td>
                    <td class="center"><c:if test="${listmateria.status == 1}">Activo</c:if></td>
                    <td class="center">${listmateria.nombrelargo}</td>
                    <td class="center">${listmateria.nombrecorto}</td>
                    <td class="center">
                        <div class="btn-group">
                            <button class="btn btn-xs btn-terra dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Acciones
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-left" role="menu">
                                <li>
                                    <a href="javascript:;" class="eliminamateria">
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
<div class="modal fade" id="modalMateria" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                        <header>Formulario Asignacion materias</header>
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
                                            <label >Seleccione una materia</label>
                                            <select id="materiaAdmin" class="custom-select">
                                                <c:forEach items="${requestScope.listmateriafaltante}" var="listmateriafaltante">
                                                    <option value="${listmateriafaltante.idtbdatosmateria}">${listmateriafaltante.nombrelargo} </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label >Seleccione una grado</label>
                                            <select id="gradoselect" class="custom-select">
                                                <c:forEach items="${requestScope.listgrado}" var="listgrado">
                                                    <option value="${listgrado.idtbgrado}">${listgrado.nombre} </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label >Seleccione una grupo</label>
                                            <select id="gruposelect" class="custom-select">
                                                <c:forEach items="${requestScope.listgrupo}" var="listgrupo">
                                                    <option value="${listgrupo.idtbgrupo}">${listgrupo.nombre} </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label >Seleccione una área</label>
                                            <select id="areaselect" class="custom-select">
                                                <option value="0"> No aplica</option>
                                                <c:forEach items="${requestScope.listarea}" var="listarea">
                                                    <option value="${listarea.idtbarea}">${listarea.nombre} </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label >Seleccione capaciatación para el trabajo</label>
                                            <select id="cptselect" class="custom-select">
                                                <option value="0"> No aplica</option>
                                                <c:forEach items="${requestScope.listcpt}" var="listcpt">
                                                    <option value="${listcpt.idtbcpt}">${listcpt.nombre} </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" id="guardamateria" class="btn btn-terra" data-dismiss="modal">Guardar</button>
            </div>
        </div>
    </div>
</div>
</div>
