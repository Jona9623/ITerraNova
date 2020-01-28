<%-- 
    Document   : tablareportes
    Created on : 28/01/2020, 11:41:29 AM
    Author     : Jonat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Tabla Reportes</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Reportes.reporteDisciplinar();">Reporte Disciplinar</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Tabla reportes disciplinares</li>
        </ol>
    </div>
</div>
<div class="card-body row">
    <h3> Criterios de búsqueda</h3>
</div>
<div id="consultareporteD">
    <div class="card-body row">
        <div class="col-lg-4 p-t-10"> 
            <div class="form-group">
                <label>Buscar por alumno</label>
                <select id="sealumno" class="custom-select">
                    <option value="1">No tan grave</option>
                    <option value="2">Grave</option>
                    <option value="3">Muy grave</option>
                </select>
            </div>
        </div>
        <div class="col-lg-4 p-t-10"> 
            <div class="form-group">
                <label>Buscar por grado</label>
                <select id="nivel" class="custom-select">
                    <option value="1">No tan grave</option>
                    <option value="2">Grave</option>
                    <option value="3">Muy grave</option>
                </select>
            </div>
        </div>
        <div class="col-lg-4 p-t-10"> 
            <div class="form-group">
                <label>Buscar por grupo</label>
                <select id="nivel" class="custom-select">
                    <option value="1">No tan grave adsdsdddsfsd</option>
                    <option value="2">Grave qweeeeeeeeeeds</option>
                    <option value="3">Muy grave qwewefewdfsffs</option>
                </select>
            </div>
        </div>
        <div class="col-lg-4 p-t-10"> 
            <div class="form-group">
                <label>Buscar por tipo de incidente</label>
                <select id="nivel" class="custom-select">
                    <option value="1">No tan grave</option>
                    <option value="2">Grave</option>
                    <option value="3">Muy grave</option>
                </select>
            </div>
        </div>
        <div class="col-lg-4 p-t-10"> 
            <div class="form-group">
                <label>Buscar por personal que llena reporte</label>
                <select id="nivel" class="custom-select">
                    <option value="1">No tan grave</option>
                    <option value="2">Grave</option>
                    <option value="3">Muy grave</option>
                </select>
            </div>
        </div>
        <div class="col-lg-4 p-t-10"> 
            <div class="form-group">
                <label>Buscar por personal que solicita</label>
                <select id="nivel" class="custom-select">
                    <option value="1">No tan grave</option>
                    <option value="2">Grave</option>
                    <option value="3">Muy grave</option>
                </select>
            </div>
        </div>
    </div>
    <div class="card-body row" name="alumno-${alumnoreporte.id}">
        <table id="tablareporteD" class="table table-bordered">
            <thead class="thead-light">
                <tr>
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
                <tr>
                    <td class="center">sdfsdfsd</td>
                    <td class="center">sdfsdfsd</td>
                    <td class="center">dsfsdfsd</td>
                    <td class="center">sdfsdfsd</td>
                    <td class="center">sfdfsg</td>
                    <td class="center">ersdfdf</td>
                    <td class="center">
                        <div class="btn-group">
                            <button class="btn btn-xs deepPink-bgcolor dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-left" role="menu">
                                <li>
                                    <a href="javascript:;" class="">
                                        <i id="editaralu" class="material-icons">create</i> Editar </a>
                                </li>
                                <li>
                                    <a href="javascript:;" class="">
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
            </tbody>
        </table>
    </div>
</div>

