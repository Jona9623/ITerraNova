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
        <div style="text-align: right">
            <a id="mostrarreportes" type="button" class="btn btn-terra float-rt">Mostrar reportes</a>
        </div>
        <div class="">
            <div class="card-head">
                <header>Formulario de reporte</header>

            </div>
            <form   enctype="multipart/form-data" method="POST" action="SReportes" name="fileinfo">
                <input type="text" name="ACCION" id="ACCION" value="GUARDAR" hidden="true">
                <div class="col-lg-4 p-t-20">
                    <label >Seleccione periodo escolar</label>
                    <select id="periodoD" class="custom-select" name="PeriodoD">
                        <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                            <option value="${listperiodo.idtbperiodo}">${listperiodo.nombre} </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="card-body row">
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione grado</label>
                        <select id="gradodisciplinar" class="custom-select" name="Gradodisciplinar">
                            <c:forEach items="${requestScope.listgrado}" var="listgrado">
                                <option value="${listgrado.idtbgrado}">${listgrado.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione grupo</label>
                        <select id="grupodisciplinar" name="Grupodisciplinar" class="custom-select">
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
                        <select id="incidente" name="Incidente" class="custom-select">
                            <c:forEach items="${requestScope.listincidente}" var="listincidente">
                                <option value="${listincidente.idtbincidente}">${listincidente.nombre} </option>
                            </c:forEach>
                        </select>
                        <a class="clic" data-toggle="modal" data-target="#exampleModalCenter">Si no encuentra un incidente puede agregar uno aqui</a>
                    </div>
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group">
                            <label>Nivel de incidente</label>
                            <select id="nivel" name="Nivel" class="custom-select">
                                <option value="1">No tan grave</option>
                                <option value="2">Grave</option>
                                <option value="3">Muy grave</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group">
                            <label>Hora del incidente</label>
                            <input id="horaincidente" name="Horaincidente" type="time" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div class="form-group">
                            <label>Fcha del incidente</label>
                            <input id="fechaincidente" name="Fechaincidente" type="date" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div class="form-group">
                            <label>Fcha del reporte</label>
                            <input id="fechareporte" name="Fechareporte" type="date" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Personal que solicita el reporte</label>
                        <select id="personalsolicita" name="Personalsolicita" class="custom-select">
                            <c:forEach items="${requestScope.listpersonal}" var="listpersonal">
                                <option value="${listpersonal.idtbpersonal}">${listpersonal.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Personal que llena el reporte</label>
                        <select id="personalllena" name="Personalllena" class="custom-select">
                            <c:forEach items="${requestScope.listpersonal}" var="listpersonal">
                                <option value="${listpersonal.idtbpersonal}">${listpersonal.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Materia durante el incidente (si aplica)</label>
                        <select id="materiaD" name="MateriaD" class="custom-select">
                            <option value="">No aplica</option>
                            <c:forEach items="${requestScope.listmateria}" var="listmateria">
                                <option value="${listmateria.idtbmateria}">${listmateria.nombrecorto} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Maestro de materia (si aplica)</label>
                        <select id="personalmateria" name="Personal" class="custom-select">
                            <option value="">No aplica</option>
                            <c:forEach items="${requestScope.listpersonal}" var="listpersonal">
                                <option value="${listpersonal.idtbpersonal}">${listpersonal.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group">
                            <label>Lugar del incidente</label>
                            <input id="lugarincidente" name="Lugarincidente" type="text" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div class="form-group">
                            <label>Descripciopn de la falta</label>
                            <textarea id="descripcion" name="Descripcion" class="form-control" rows="5" placeholder="Enter ..."></textarea>
                        </div>
                    </div>
                </div>
                <div class="card-body row ">
                    <div class="form-group">
                        <a class="material-icons f-left">camera_enhance</a>
                        <a> Agregar fotografía (opcional) </a> <br>
                        <input id="foto" type="file" name="Archivo">
                    </div>
                </div>
                <button id="guardareporteD" value="GuardarreporteD" type="submit" class="btn btn-terra">Guardar</button>
            </form>
        </div>
    </div>
</div>
