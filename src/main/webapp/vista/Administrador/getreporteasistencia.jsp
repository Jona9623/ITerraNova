<%-- 
    Document   : getreporteasistencia
    Created on : 13/05/2020, 11:39:24 AM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="row col-lg-12 col-md-12 col-sm-12">
    <table id="tablaasisencia" class="table table-bordered" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th class="center">Nombre</th>
                <th class="center">A. Paterno</th>
                <th class="center">A. Materno</th>
                <th class="center">Número de faltas</th>
                <th class="center">Días faltados</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listalumno}" var="listalumno">
                <tr class=" <c:if test="${listalumno.asistencia == 1}">asi</c:if> <c:if test="${listalumno.asistencia == 2}">asi-media</c:if> <c:if test="${listalumno.asistencia >= 3}">asi-grave</c:if>">
                    <td class="center" style="display: none">${listalumno.idtbasistencia}</td>
                    <td class="center"><strong>${listalumno.nombrealum}</strong></td>
                    <td class="center"><strong>${listalumno.apellidopa}</strong></td>
                    <td class="center"><strong>${listalumno.apellidoma}</strong></td>
                    <td class="center"><strong>${listalumno.asistencia}</strong></td>
                    <td class="center"><strong>${listalumno.dia}</strong></td>
                </tr>
                </c:forEach>
        </tbody>
    </table>
</div>
