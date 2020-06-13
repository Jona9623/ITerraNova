<%-- 
    Document   : alumnoslista
    Created on : 13/04/2020, 12:01:00 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 
    int x = 0;
    int personal = 0;
    HttpSession objsesion = request.getSession(false);
    String usuario = (String) objsesion.getAttribute("user");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
    } else {
        x = (int) objsesion.getAttribute("tipo");
        personal = (int) objsesion.getAttribute("personal");
    }
%>
<br>
<br> <br> 
<div class="row">
    <p class="header">Toma de asistencia</p>
</div>
<div class="row col-lg-12 col-md-12 colsm-12">
    
    <div class="col-lg-2 p-t-20">
        <button type="button"<c:if test="${requestScope.bandera == 1}">hidden="true"</c:if>
                id="asistencia" class="btn btn-terra">Guardar asistencia</button>
    </div>
    <div class="col-lg-2 p-t-20">
        <button type="button"<c:if test="${requestScope.bandera == 0}">hidden="true"</c:if>
            id="asistenciaanterior" class="btn btn-terra">Actualizar asistencia </button>
    </div>

</div>
<div class="row col-lg-12 col-md-12 colsm-12">
    <table id="tablalistaalumnos" class="table table-bordered" style="width:100%">
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
            <c:forEach items="${requestScope.listalumno}" var="listalumno">
                <tr>
                    <td class="center" style="display: none">${listalumno.id}</td>
                    <td class="center">${listalumno.nombre}</td>
                    <td class="center">${listalumno.apellidop}</td>
                    <td class="center">${listalumno.apellidom}</td>
                    <td class="center">
                        <div id="check3" class="checkbox checkbox-icon-black p-0 col-lg-6 col-md-6">
                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                            <input id="lista${listalumno.id}" value="${listalumno.id}" type="checkbox" checked="checked">
                            <label for="lista${listalumno.id}">
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