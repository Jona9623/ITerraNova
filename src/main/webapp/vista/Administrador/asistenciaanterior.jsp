<%-- 
    Document   : asistenciaanterior
    Created on : 10/06/2020, 12:28:08 PM
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
<!DOCTYPE html>
<div class="page-bar  card-topline-red">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title"> <strong>Aqui podrá actualizar alguna asistencia de acuerdo con el reporte generado el dia de hoy</strong> </div><br>
            <p>Personal: <strong>${requestScope.personal.apellidop} ${requestScope.personal.apellidom} ${requestScope.personal.nombre}</strong></p> <br><br>
            <p>Materia a actualizar <strong>${requestScope.materia.nombrelargo}</strong></p>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <% if (x == 3 || x == 4 || x == 5) { %>
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Adminpersonal.tablaPersonal();">Tabla Personal</a>&nbsp;<i class="fa fa-angle-right"></i>
                <%}%>
                &nbsp;<a class="parent-item" href="javascript:Adminpersonal.listaAlumnos(${requestScope.personal.idtbpersonal});">Lista de alumnos</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Actualizar asistencia </li>
        </ol>
    </div>
</div>
<div class="row col-lg-12 col-md-12 colsm-12">
    
    <div class="col-lg-2x p-t-20">
        <button type="button" id="actualizarasistencia" class="btn btn-terra">Guardar cambios  </button>
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
