<%-- 
    Document   : getdiasfaltas
    Created on : 30/05/2020, 12:31:25 PM
    Author     : Complx
--%>

>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class="col-lg-12 col-md-12">
        <table id="" class="table table-bordered" style="width:100%">
            <thead class="thead-light">
                <tr>
                    <th style="display: none"></th>
                    <th class="center">Dia</th>
                    <th class="center">Marcar para justificar</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.listdias}" var="listdias">
                    <tr>
                        <td class="center" style="display: none">${listdias.idtbdia}</td>
                        <td class="center">${listdias.nombre}</td>
                        <td class="center">
                            <div id="checkfaltas" class="checkbox checkbox-icon-black">
                                <input id="dia${listdias.idtbdia}" type="checkbox" value="${listdias.idtbdia}">
                                <label for="dia${listdias.idtbdia}"></label>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div style="text-align: right">
            <div class="btn-group">
                <br>
                <button id="justificarfaltas" type="button" class="btn btn-terra">Justificar</button>  &nbsp; &nbsp;
               <!-- <div class="btn-group">
                    <button id="asignaralumnos" type="button" class="btn btn-terra">Asignar Alumnos</button> 
                </div> -->
            </div>
        </div>
    </div>
</div>
