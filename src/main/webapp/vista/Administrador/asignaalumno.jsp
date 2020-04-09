<%-- 
    Document   : asignaalumno
    Created on : 9/04/2020, 11:47:22 AM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br>
<div class="row col-lg-12 col-md-12 colsm-12">
    <div class="row col-lg-6 col-md-4 col-sm-4">
        <a href="javascript:Adminpersonal.seleccionaTodo();">Marcar todo <br></a>
    </div>
    <div class="row col-lg-6 col-md-6 col-sm-4">
        <a href="javascript:Adminpersonal.desmarcarTodo();">Desmarcar todo <br></a>
    </div>
</div>
<br> <br> 
<form name="asignaAlumno">
    <div class="row col-lg-12 col-md-12 colsm-12">
        <c:forEach items="${requestScope.listalumno}" var="listalumno">
            <div id="check2" class="checkbox checkbox-icon-black p-0 col-lg-6 col-md-6">
                <input id="alumno${listalumno.id}" value="${listalumno.id}" type="checkbox" checked="checked">
                <label for="alumno${listalumno.id}">
                    ${listalumno.nombre} ${listalumno.apellidop} ${listallumno.apellidom}
                </label>
                <br> <br>
            </div>
        </c:forEach>
    </div>
    <div style="text-align: right">
        <div class="btn-group">
            <br>
            <button id="alumnoasigna" type="button" class="btn btn-terra">Asignar Alumnos</button>
        </div>
    </div>
</form>

