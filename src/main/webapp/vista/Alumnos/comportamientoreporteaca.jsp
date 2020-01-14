<%-- 
    Document   : comportamientoreporteaca
    Created on : 14/01/2020, 11:41:08 AM
    Author     : complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Reporte Disciplinar</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:reporteDisciplinar()">Reporte Academico</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Agregar Comportamiento</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <div class="">
            <div class="card-head">
                <header>Formulario de comportamientos</header>
            </div>
            <form class="">
                <div class="card-body row">
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group">
                            <label>Comportamiento</label>
                            <input type="text" class="form-control" placeholder="Nombre del comportamiento">
                        </div>
                    </div><br>
                </div>
                <button type="button" class="btn btn-terra">Guardar</button>
            </form>
        </div>
    </div>
</div>
