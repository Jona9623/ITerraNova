<%-- 
    Document   : agregamateriaspersonal
    Created on : 6/04/2020, 02:27:51 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Agregar Materias</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Adminpersonal.tablaPersonal();">Tabla Personal</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Agregar materias</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-lg-12 col-md-12">
        <strong> Marque las casillas para agregar las materias a los maestros</strong>
        <table id="tablaasignamateria" class="table table-bordered" style="width:100%">
            <thead class="thead-light">
                <tr>
                    <th style="display: none"></th>
                    <th class="center">Estatus</th>
                    <th class="center">Nombre Largo</th>
                    <th class="center">Nombre Corto</th>
                    <th class="center">Marcar para agregar</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.listmateria}" var="listmateria">
                    <tr>
                        <td class="center" style="display: none">${listmateria.idtbmateria}</td>
                        <td class="center"><c:if test="${listmateria.status == 1}">Activo</c:if></td>
                        <td class="center">${listmateria.nombrelargo}</td>
                        <td class="center">${listmateria.nombrecorto}</td>
                        <td class="center">
                            <div id="check" class="checkbox checkbox-icon-black">
                                <input id="materia${listmateria.idtbmateria}" type="checkbox" value="${listmateria.idtbmateria}">
                                <label for="materia${listmateria.idtbmateria}"></label>

                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div style="text-align: right">
            <div class="btn-group">
                    <button id="asignamateria" type="button" class="btn btn-terra">Asignar Materia</button> <br>
            </div>
        </div>
    </div>
</div>

