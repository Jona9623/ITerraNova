<%-- 
    Document   : gethorario
    Created on : 17/04/2020, 01:30:00 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="row">
    <br> <br>
    <c:forEach items="${requestScope.listhorario}" var="listhorario">
        <div class="col-lg-4 col-md-4 col-sm-4">
            <div class="panel">
                <header class="panel-heading btn-terra">
                    ${listhorario.nombrelargo}</header>
                <div class="panel-body btn-terra2">
                    <strong>Materia:  </strong>${listhorario.nombrecorto} <br> <br>
                    <strong>Maestro:  </strong>${listhorario.nombrepe} ${listhorario.apellidopp} ${listhorario.apellidomp} <br> <br>
                    <strong>Hora:     </strong>${listhorario.hora} <br> <br>
                </div>
            </div>
        </div>
    </c:forEach>
