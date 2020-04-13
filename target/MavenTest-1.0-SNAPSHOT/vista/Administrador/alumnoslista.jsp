<%-- 
    Document   : alumnoslista
    Created on : 13/04/2020, 12:01:00 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br>
<br> <br> 
<div class="row col-lg-12 col-md-12 colsm-12">
    <table id="tablapersonal" class="table table-bordered" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th style="display: none"></th>
                <th class="center">Nombre</th>
                <th class="center">A. Paterno</th>
                <th class="center">A. Materno</th>
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
                        <div class="btn-group">
                            <a href="javascript:;" class="aliminarpe">
                                <i id="aliminarpe" class="icon-trash"></i> Eliminar </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>