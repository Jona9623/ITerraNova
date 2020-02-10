<%-- 
    Document   : tablareportes
    Created on : 28/01/2020, 11:41:29 AM
    Author     : Jonat
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
            <div class="page-title">Tabla Reportes</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Reportes.reporteDisciplinar();">Reporte Disciplinar</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Tabla reportes disciplinares</li>
        </ol>
    </div>
</div>
<div class="card-body row">
    <h3> Criterios de búsqueda</h3>
</div>
<div id="consultareporteD">
    <div class="card-body row">
        <div class="col-lg-4 p-t-10"> 
            <div class="form-group">
                <label >Seleccione periodo escolar</label>
                <select id="periodotablaD" class="custom-select">
                    <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                        <option value="${listperiodo.idtbperiodo}">${listperiodo.nombre} </option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="card-body row">
        <table id="tablareporteD" class="table table-bordered" style="width:100%">
            <thead class="thead-light">
                <tr>
                    <th style="display: none"></th>
                    <th style="display: none"></th>
                    <th style="display: none"></th>
                    <th style="display: none"></th>
                    <th class="center">Alumno</th>
                    <th class="center">Grado y Grupo</th>
                    <th class="center">Tipo de incidente</th>
                    <th class="center">Fecha del incidente</th>
                    <th class="center">Personal que llena</th>
                    <th class="center">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.alumnosdisciplinar}" var="alumnosdisciplinar">
                    <tr name="alumno-${alumnosdisciplinar.rperiodo}">
                        <td class="center" style="display: none">${alumnosdisciplinar.ralumno}</td>
                        <td class="center" style="display: none">${alumnosdisciplinar.fecha}</td>
                        <td class="center" style="display: none">${alumnosdisciplinar.rperiodo}</td>
                        <td class="center" style="display: none">${alumnosdisciplinar.hora}</td>
                        <td class="center">${alumnosdisciplinar.alumno} ${alumnosdisciplinar.alumnoapep}</td>
                        <td class="center">${alumnosdisciplinar.grado} ${alumnosdisciplinar.grupo}</td>
                        <td class="center">${alumnosdisciplinar.tipoincidente}</td>
                        <td type="date" class="center">${alumnosdisciplinar.fecha}</td>
                        <td class="center">${alumnosdisciplinar.personalllena}</td>
                        <td class="center">
                            <div class="btn-group">
                                <button class="btn btn-xs btn-terra dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Acciones
                                    <i class="fa fa-angle-down"></i>
                                </button>
                                <ul class="dropdown-menu pull-left" role="menu">
                                    <li> 
                                        <% if (x == 1 || x == 3 || x == 5) { %>
                                        <a  href="javascript:;" class="inforeporteD">
                                            <i id="inforeporteD" class="icon-arrow-right"></i> Toda la información </a>
                                        <% } %>
                                        <% if (x == 3 || x == 5) { %>
                                        <a  href="javascript:;" class="editarreporteD">
                                            <i id="editarreporteD" class="icon-pencil"></i>Editar reporte</a>
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

