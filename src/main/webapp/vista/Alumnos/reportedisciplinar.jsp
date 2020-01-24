<%-- 
    Document   : subopcion1
    Created on : 13/01/2020, 10:19:10 AM
    Author     : complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <form>
            <div class="">
                <div class="card-head">
                    <header>Formulario de reporte</header>
                </div>
                <div class="col-lg-4 p-t-20">
                    <label >Seleccione periodo escolar</label>
                    <select id="periodo" class="custom-select">
                        <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                            <option value="${listperiodo.idtbperiodo}">${listperiodo.nombre} </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="card-body row">
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione grado</label>
                        <select id="gradodisciplinar" class="custom-select">
                            <c:forEach items="${requestScope.listgrado}" var="listgrado">
                                <option value="${listgrado.idtbgrado}">${listgrado.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione grupo</label>
                        <select id="grupodisciplinar" class="custom-select">
                            <c:forEach items="${requestScope.listgrupo}" var="listgrupo">
                                <option value="${listgrupo.idtbgrupo}">${listgrupo.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div id="alumnogradoD">
                            <jsp:include page='alumnosgradogrupo.jsp'>
                                <jsp:param name="article1" value=""/>
                            </jsp:include>
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione Tipo de incidente</label>
                        <select id="incidente" class="custom-select">
                            <c:forEach items="${requestScope.listincidente}" var="listincidente">
                                <option value="${listincidente.idtbincidente}">${listincidente.nombre} </option>
                            </c:forEach>
                        </select>
                        <a class="clic" data-toggle="modal" data-target="#exampleModalCenter">Si no encuentra un incidente puede agregar uno aqui</a>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                                                <header>Formulario de incidentes</header>
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
                                                                    <label>Incidente</label>
                                                                    <input type="text" class="form-control" placeholder="Nombre del incidente">
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
                                        <button type="button" class="btn btn-terra">Guardar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
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
            </div>
        </form>
    </div>
</div>
