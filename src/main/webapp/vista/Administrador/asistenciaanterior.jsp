<%-- 
    Document   : asistenciaanterior
    Created on : 10/06/2020, 12:28:08 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<br> <br> 
<div class="row">
    <p class="header">Cambio de asistencia</p>
</div>
<div class="row col-lg-12 col-md-12 colsm-12">
    
    <div class="col-lg-2x p-t-20">
        <button type="button" id="asistenciaanterior" class="btn btn-terra">Guardar cambios  </button>
    </div>

</div>
<div class="row col-lg-12 col-md-12 colsm-12">
    <table id="tablaasistenciaanterior" class="table table-bordered" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th class="center">Nombre</th>
                <th class="center">A. Paterno</th>
                <th class="center">A. Materno</th>
                <th class="center">Asistencia</th>
                <th class="center">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listasistencia}" var="listasistencia">
                <tr>
                    <td class="center" style="display: none">${listasistencia.idtbasistencia}</td>
                    <td class="center">${listasistencia.nombrealum}</td>
                    <td class="center">${listasistencia.apellidopa}</td>
                    <td class="center">${listasistencia.apellidoma}</td>
                    <td class="center">
                        <div id="check3" class="checkbox checkbox-icon-black p-0 col-lg-6 col-md-6">
                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                            <input id="lista${listasistencia.idtbasistencia}" value="${listasistencia.idtbasistencia}" type="checkbox" <c:if test="${listasistencia.asistencia == 1}">checked="checked"</c:if>>
                            <label for="lista${listasistencia.idtbasistencia}">
                                Asistencia
                            </label>
                            <br> <br>
                        </div>
                    </td>
                    <td class="center">
                        <div class="btn-group">
                            <a href="javascript:;" class="">
                                <i id="" class=""></i> No disponible aun </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
