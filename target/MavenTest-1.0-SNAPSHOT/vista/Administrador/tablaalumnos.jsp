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
            <div class="btn-group">
                <a data-toggle="modal" data-target="#modalArchivoT" id="importaTutor"type="button" class="btn btn-terra">Importar Tutor</a> <br> 
            </div>
            <div class="btn-group">
                <a data-toggle="modal" data-target="#modalArchivo" id="importaAlumno"type="button" class="btn btn-terra">Importar Alumno</a> <br> 
            </div>
            
        </div>
    </div>

    <table id="tablaalumnos" class="table table-bordered" style="width:100%">
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
                            <button class="btn btn-xs btn-terra dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Acciones
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-left" role="menu">
                                <li>
                                    <a href="javascript:;" class="infoalumno">
                                        <i id="infoalumno" class="icon-plus"></i>Mostrar toda la información</a>
                                </li>
                                <li>
                                    <a href="javascript:;" class="editaralu">
                                        <i id="editaralu" class="icon-pencil"></i> Editar </a>
                                </li>
                                <li>
                                    <a href="javascript:;" class="aliminaralu">
                                        <i id="eliminaralu" class="icon-trash"></i> Eliminar </a>
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
<div class="modal fade" id="modalArchivo" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
                            <form id="importarAlumno" enctype="multipart/form-data" method="POST" action="SAdminalumno" name="importaralumno">
                                <input type="text" name="ACCION" id="ACCION" value="importaAlumno" hidden="true">
                                <div class="card-body row">
                                    <div class="col-lg-10 p-t-20"> 
                                        <div class="form-group">
                                            <label>Seleccione archivo para importar</label>
                                            <input id="importaalumno" type="file" name="importaAlumno">
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
                <button type="button" id="actualizaTablaA" class="btn btn-secondary" data-dismiss="modal">Cerrar y actualizar tabla</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modalArchivoT" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
                            <form id="importarTutor" enctype="multipart/form-data" method="POST" action="SAdminalumno" name="importartutor">
                                <input type="text" name="ACCION" id="ACCION" value="importaTutor" hidden="true">
                                <div class="card-body row">
                                    <div class="col-lg-10 p-t-20"> 
                                        <div class="form-group">
                                            <label>Seleccione archivo para importar</label>
                                            <input id="importatutor" type="file" name="importaTutor">
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
                <button type="button" id="actualizaTablaA" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>
