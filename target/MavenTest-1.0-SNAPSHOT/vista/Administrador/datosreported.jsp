<%-- 
    Document   : datosreported
    Created on : 29/01/2020, 01:49:19 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                                <header>Reporte disciplinar</header>
                            </div></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.alumno}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.alumnoapep}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.alumnoapem}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.grado}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.grupo}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.personalsolicita}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.personalllena}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.personal}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.materia}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.fecha}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.fechareporte}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.hora}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.lugar}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.tipoincidente}</p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p> <c:if test="${requestScope.reporteD.nivel == 1}">No tan grave</c:if></p>
                                <p> <c:if test="${requestScope.reporteD.nivel == 21}">Grave</c:if></p>
                                <p> <c:if test="${requestScope.reporteD.nivel == 3}">Muy grave</c:if></p>
                            </div>
                            <div class="col-md-6 col-6 b-r">
                                <strong> Titulo</strong> <br>
                                <p>${requestScope.reporteD.descripcion}</p>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary center" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
