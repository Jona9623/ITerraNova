<%-- 
    Document   : tablagrupo
    Created on : 17/02/2020, 11:46:33 AM
    Author     : Complx
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
        </div>
    </div>

    <table id="tablapersonal" class="table table-bordered" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th class="center">Estatus</th>
                <th class="center">Nombre</th>
                <th class="center">Acciones</th>
            </tr>
        </thead>
        <tbody>
                <tr>
                    <td class="center" style="display: none"></td>
                    <td class="center"></td>
                    <td class="center"></td>
                    <td class="center">
                        <div class="btn-group">
                            <button class="btn btn-xs btn-terra dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Acciones
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-left" role="menu">
                                <li>
                                    <a href="javascript:;" class="aliminarpe">
                                        <i id="aliminarpe" class="icon-trash"></i> Eliminar </a>
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>
        </tbody>
    </table>
</div>
