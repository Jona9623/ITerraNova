<%-- 
    Document   : faltareportedisc
    Created on : 14/01/2020, 10:21:07 AM
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
                &nbsp;<a class="parent-item" href="javascript:reporteDisciplinar()">Reporte Disciplinar</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Agregar Fatla</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <div class="">
            <div class="card-head">
                <header>Formulario de faltas</header>
            </div>
            <form class="">
                <div class="card-body row">
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group">
                            <label>Falta</label>
                            <input type="text" class="form-control" placeholder="Nombre de la falta">
                        </div>
                    </div><br>
                </div>
                <button type="button" class="btn btn-terra">Guardar</button>
            </form>
        </div>
    </div>
</div>
