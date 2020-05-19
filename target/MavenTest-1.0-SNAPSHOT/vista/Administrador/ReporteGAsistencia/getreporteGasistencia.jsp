<%-- 
    Document   : getreporteGasistencia
    Created on : 19/05/2020, 12:30:12 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<br>
<div class="row col-lg-12 col-md-12 col-sm-12">
    <table id="tablaasisencia" class="table table-bordered" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th class="center">Nombre</th>
                <th class="center">A. Paterno</th>
                <th class="center">A. Materno</th>
                <th class="center">Materia</th>
                <th class="center">Materia</th>
                <th class="center">Materia</th>
                <th class="center">Materia</th>
                <th class="center">Materia</th>
                <th class="center">Materia</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listalumno}" var="listalumno">
                <tr class=" <c:if test="${listalumno.asistencia == 1}">asi</c:if> <c:if test="${listalumno.asistencia == 2}">asi-media</c:if> <c:if test="${listalumno.asistencia >= 3}">asi-grave</c:if>">
                    <td class="center" style="display: none">${listalumno.idtbasistencia}</td>
                    <td class="center">${listalumno.nombrealum}</td>
                    <td class="center">${listalumno.apellidopa}</td>
                    <td class="center">${listalumno.apellidoma}</td>
                    <td class="center">${listalumno.asistencia}</td>
                    <td class="center">${listalumno.asistencia}</td>
                </tr>
                </c:forEach>
        </tbody>
    </table>
</div>
