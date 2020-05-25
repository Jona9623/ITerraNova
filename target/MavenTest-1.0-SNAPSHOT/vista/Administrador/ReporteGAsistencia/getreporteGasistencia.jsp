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
    <table id="tablaaGsisencia" class="table table-bordered" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th class="center">Nombre completo alumno</th>
                    <c:forEach items="${requestScope.listmateria}" var="listmateria">
                    <th class="center"><p class="center vertical">${listmateria.nombrelargo}</p></th>
                    </c:forEach>

            </tr>
        </thead>
        <tbody>
            <c:set var="bandera" value="0"></c:set> 
            <c:forEach items="${requestScope.listalumnos}" var="listalumnos" varStatus="i">
                <tr class="" >
                    <td class="center">${listalumnos.apellidop} ${listalumnos.apellidom} ${listalumnos.nombre}</td>
                    <c:forEach items="${requestScope.listmateria}" var="listmateria" varStatus="j">
                        <c:forEach items="${requestScope.listasistencia}" var="listasistencia" varStatus="k">
                            <c:if test="${listasistencia.r_materia == listmateria.idtbdatosmateria && listasistencia.r_alumno == listalumnos.id}">
                                <c:set var="bandera" value="${listasistencia.asistencia}"></c:set> 
                            </c:if>
                        </c:forEach>
                        <c:if test="${bandera >0}">
                            <td class="center <c:if test="${bandera == 1}">asi</c:if> <c:if test="${bandera == 2}">asi-media</c:if> <c:if test="${bandera >= 3}">asi-grave</c:if>">
                                <c:out value="${bandera}"></c:out>
                            </td> 
                        </c:if>
                            <c:if test="${bandera == 0}">
                            <td class="center">
                                0
                            </td> 
                        </c:if>
                            <c:set var="bandera" value="0"></c:set> 
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

