<%-- 
    Document   : alumnosgradogrupo
    Created on : 24/01/2020, 12:46:43 PM
    Author     : complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <label >Seleccione un alumno</label>
    <select id="alumnodisciplinar" name="Alumnodisciplinar" class="custom-select">
        <c:forEach items="${requestScope.listalumno}" var="listalumno">
            <option value="${listalumno.id}">${listalumno.nombre} </option>
        </c:forEach>
    </select>
</html>
