<%-- 
    Document   : tablareportesAca
    Created on : 9/03/2020, 01:11:57 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
    int x=0;
    int id_sesion;
    HttpSession objsesion= request.getSession(false);
    String usuario = (String) objsesion.getAttribute("user");
     x = (int)objsesion.getAttribute("tipo");       
     id_sesion = (int) objsesion.getAttribute("id");
       
%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Tabla Reportes Academico</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Reportes.reporteAcademico();">Reporte Academico</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Tabla Reportes Academicos</li>
        </ol>
    </div>
</div>
<div class="card-body row">
    <h3> Criterios de búsqueda</h3>
</div>
<div id="consultareporteA">
    <div class="card-body row">
        <div class="col-lg-4 p-t-10"> 
            <div class="form-group">
                <label >Seleccione periodo escolar</label>
                <select id="periodotablaA" class="custom-select">
                    <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                        <option value="${listperiodo.idtbperiodo}">${listperiodo.nombre} </option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="card-body row">
        <table id="tablareporteA" class="table table-bordered" style="width:100%">
            <thead class="thead-light">
                <tr>
                    <th style="display: none"></th>
                    <th class="center">Personal</th>
                    <th class="center">Materia</th>
                    <th class="center">Semana</th>
                    <th class="center">Alumno cuadro de honor</th>
                    <th class="center">Alumno cuadro de atencion</th>
                    <th class="center">Atencion</th>
                    <th class="center">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.alumnosacademico}" var="alumnosacademico">
                    <tr name="alumno-${alumnosacademico.rperiodo}">
                        <td class="center" style="display: none">${alumnosacademico.idtbreporte}</td>
                        <td class="center">${alumnosacademico.nombrepersonal} ${alumnosacademico.apellidoppersonal} ${alumnosacademico.apellidompersonal}</td>
                        <td class="center">${alumnosacademico.materia}</td>
                        <td class="center">${alumnosacademico.semana}</td>
                        <td class="center">${alumnosacademico.nombrehonor} ${alumnosacademico.apellidophonor} ${alumnosacademico.apellidomhonor}</td>
                        <td class="center">${alumnosacademico.nombreatencion} ${alumnosacademico.apellidopatencion} ${alumnosacademico.apellidomatencion}</td>
                        <td type="date" class="center">${alumnosacademico.atencion}</td>
                        <td class="center">
                            <div class="btn-group">
                                <button class="btn btn-xs btn-terra dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Acciones
                                    <i class="fa fa-angle-down"></i>
                                </button>
                                <ul class="dropdown-menu pull-left" role="menu">
                                    <li>
                                        <% if (x == 3 || x == 5) { %>
                                        <a  href="javascript:;" class="eliminarreporteA">
                                            <i id="editarreporteD" class="icon-pencil"></i>Eliminar</a>
                                        <% } %>   
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
