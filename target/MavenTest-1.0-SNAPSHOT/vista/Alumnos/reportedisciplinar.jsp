<%-- 
    Document   : subopcion1
    Created on : 13/01/2020, 10:19:10 AM
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
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Reporte disciplinar</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <div class="">
            <div class="card-head">
                <header>Formulario de reporte</header>
            </div>
            <form>
                <div class="col-lg-4 p-t-20">
                    <label >Seleccione ciclo académico</label>
                    <select class="custom-select">
                        <option value="1">ciclo</option>
                        <option value="2">ciclo</option>
                        <option value="3">ciclo</option>
                        <option value="3">ciclo</option>
                    </select>
                </div>
                <div class="card-body row">
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione grado y grupo</label>
                        <select class="custom-select">
                            <option value="1">1er A</option>
                            <option value="2">1er B</option>
                            <option value="3">2do A</option>
                            <option value="3">2do B</option>
                            <option value="3">3er A</option>
                            <option value="3">3er B</option>
                            <option value="3">4to A</option>
                            <option value="3">4to B</option>
                            <option value="3">5to A</option>
                            <option value="3">5to B</option>
                            <option value="3">6to A</option>
                            <option value="3">6to B</option>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione un alumno</label>
                        <select class="custom-select">
                            <option value="1">Nombre</option>
                            <option value="2">Nombre</option>
                            <option value="3">Nombre</option>
                            <option value="3">Nombre</option>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione Tipo de falta</label>
                        <select class="custom-select">
                            <option value="1">Falta</option>
                            <option value="2">Falta</option>
                            <option value="3">Falta</option>
                            <option value="3">Falta</option>
                        </select>
                        <a id="agregafalta" class="clic">Si no encuentra la falta puede agregar una aqui</a>
                    </div>
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group">
                            <label>Fcha</label>
                            <input type="date" class="form-control" placeholder="Enter ...">
                        </div> <br>  
                        <div class="form-group">
                            <label>Descripciopn de la falta</label>
                            <textarea class="form-control" rows="3" placeholder="Enter ..."></textarea>
                        </div>
                        <div class="form-group">
                            <a class="material-icons f-left">camera_enhance</a>
                            <a> Agregar fotografía (opcional) </a>
                        </div> <!--
                        <div class="form-group">
                            <div class="checkbox checkbox-icon-black p-0">
                                <input id="checkbox1" type="checkbox">
                                <label for="checkbox1">
                                    Checkbox 1
                                </label>
                            </div>
                            <div class="checkbox checkbox-icon-black p-0">
                                <input id="checkbox2" type="checkbox" checked="checked">
                                <label for="checkbox2">
                                    Checkbox 2
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="radio p-0">
                                <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
                                <label for="optionsRadios1">
                                    Option 1
                                </label>
                            </div>
                            <div class="radio p-0">
                                <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                                <label for="optionsRadios2">
                                    Option 2
                                </label>
                            </div>
                        </div> -->
                    </div>
                </div>
                <button type="button" class="btn btn-terra">Guardar</button>
            </form>
        </div>
    </div>
</div>
