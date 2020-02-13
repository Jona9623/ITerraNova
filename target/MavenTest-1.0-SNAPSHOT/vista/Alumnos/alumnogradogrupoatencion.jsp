<%-- 
    Document   : alumnogradogrupohonor
    Created on : 12/02/2020, 12:52:18 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <label >Seleccione un alumno</label>
    <select id="alumnodisciplinar" name="Alumnodisciplinar" class="custom-select">
        <c:forEach items="${requestScope.listalumnoA}" var="listalumnoH">
            <option value="${listalumnoA.id}">${listalumnoA.nombre} </option>
        </c:forEach>
    </select>
</html>
