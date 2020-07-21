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
                &nbsp;<a class="parent-item" href="javascript:Reportes.reporteDisciplinar();">Reporte Disciplinar</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Reportes.mostrarReportes();">Tabla Reporte Disciplinar</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Editar Reporte Disciplinar</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <div class="">
            <div class="card-head">
                <header>Formulario de reporte</header>

            </div> <br>
            <strong type="text" >Los campos con signo * son obligatorios</strong>
            <form>
                <input id="idtbreporteD" hidden="true" value="${requestScope.reporteD.idtbreporte}">
                <div class="col-lg-4 p-t-20">
                    <label ><strong>Periodo: ${requestScope.periodo.nombre}</strong></label>
                    <input hidden="true" id="periodoD" name="PeriodoD" value="${requestScope.periodo.idtbperiodo}">
                </div>
                <div class="card-body row">
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione Tipo de incidente</label>
                        <select id="incidente" name="Incidente" class="custom-select">
                            <c:forEach items="${requestScope.listincidente}" var="listincidente">
                                <option value="${listincidente.idtbincidente}" 
                                        <c:if test="${listincidente.idtbincidente == requestScope.reporteD.rtipoincidente}">
                                            selected=""
                                        </c:if>
                                        >${listincidente.nombre}</option>
                            </c:forEach>
                        </select>
                        <a class="clic" data-toggle="modal" data-target="#exampleModalCenter">Si no encuentra un incidente puede agregar uno aqui</a>
                    </div>
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group">
                            <label>Nivel de incidente</label>
                            <select id="nivel" name="Nivel" class="custom-select">
                                <option value="1" <c:if test="${requestScope.reporteD.nivel == 1}">
                                        selected=""
                                    </c:if>
                                    >No tan grave</option>
                                <option value="2" <c:if test="${requestScope.reporteD.nivel == 2}">
                                        selected=""
                                    </c:if>
                                    >Grave</option>
                                <option value="3" <c:if test="${requestScope.reporteD.nivel == 3}">
                                        selected=""
                                    </c:if>
                                    >Muy grave</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group" id="div-horaincidente">
                            <label>Hora del incidente *</label>
                            <input id="horaincidente" name="Horaincidente" value="${requestScope.reporteD.hora}" type="time" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div class="form-group" id="div-fechaincidente">
                            <label>Fcha del incidente *</label>
                            <input id="fechaincidente" name="Fechaincidente" value="${requestScope.reporteD.fecha}" type="date" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div class="form-group" id="div-fechareporte">
                            <label>Fcha del reporte *</label>
                            <input id="fechareporte" name="Fechareporte" value="${requestScope.reporteD.fechareporte}" type="date" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Personal que solicita el reporte</label>
                        <select id="personalsolicita" name="Personalsolicita" class="custom-select">
                            <c:forEach items="${requestScope.listpersonal}" var="listpersonal">
                                <option value="${listpersonal.idtbpersonal}" 
                                        <c:if test="${listpersonal.idtbpersonal == requestScope.reporteD.rpersonalsolicita}">
                                            selected=""
                                        </c:if> 
                                        >${listpersonal.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Personal que llena el reporte</label>
                        <select id="personalllena" name="Personalllena" class="custom-select">
                            <c:forEach items="${requestScope.listpersonal}" var="listpersonal">
                                <option value="${listpersonal.idtbpersonal}"
                                        <c:if test="${listpersonal.idtbpersonal == requestScope.reporteD.rpersonalllena}">
                                            selected=""
                                        </c:if>
                                        >${listpersonal.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Materia durante el incidente (si aplica)</label>
                        <select id="materiaD" name="MateriaD" class="custom-select">
                            <option value="">No aplica</option>
                            <c:forEach items="${requestScope.listmateria}" var="listmateria">
                                <option value="${listmateria.idtbmateria}"
                                        <c:if test="${listmateria.idtbmateria == requestScope.reporteD.rmateria}">
                                            selected=""
                                        </c:if>
                                        >${listmateria.nombrecorto} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Maestro de materia (si aplica)</label>
                        <select id="personalmateria" name="Personal" class="custom-select">
                            <option value="">No aplica</option>
                            <c:forEach items="${requestScope.listpersonal}" var="listpersonal">
                                <option value="${listpersonal.idtbpersonal}" 
                                        <c:if test="${listpersonal.idtbpersonal == requestScope.reporteD.rpersonal}">
                                            selected=""
                                        </c:if>
                                        >${listpersonal.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group" id="div-lugarincidente">
                            <label>Lugar del incidente *</label>
                            <input id="lugarincidente" name="Lugarincidente" value="${requestScope.reporteD.lugar}" type="text" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div class="form-group" id="div-descripcion">
                            <label>Descripciopn de la falta *</label>
                            <textarea id="descripcion" name="Descripcion" value="" class="form-control" rows="5" placeholder="Enter ...">${requestScope.reporteD.descripcion}</textarea>
                        </div>
                    </div>
                </div>
                        <button id="editarreporteD" type="button" class="btn btn-terra">Guardar</button>
            </form>
        </div>
    </div>
</div>

