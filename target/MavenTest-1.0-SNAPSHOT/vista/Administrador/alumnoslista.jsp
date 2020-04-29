<%-- 
    Document   : alumnoslista
    Created on : 13/04/2020, 12:01:00 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br>
<br> <br> 
<div class="row">
    <p class="header">Toma de asistencia</p>
</div>
<div class="row col-lg-12 col-md-12 colsm-12">
    <div class="col-lg-4 p-t-20">
        <label >Seleccione periodo escolar</label>
        <select id="periodoasistencia" class="custom-select" name="periodoasistencia">
            <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                <option value="${listperiodo.idtbperiodo}">${listperiodo.nombre} </option>
            </c:forEach>
        </select>
    </div>
    <div class="col-lg-4 p-t-20">
        <label >Seleccione Semana fiscal</label>
        <select id="semanafiscalasistencia" name="semanafiscalasistencia" class="custom-select">
            <c:forEach items="${requestScope.listsemana}" var="listsemana">
                <option value="${listsemana.idtbsemana}">${listsemana.nombre} </option>
            </c:forEach>
        </select>
    </div>
    <div class="col-lg-4 p-t-20">
        <label >Seleccione el dia actual</label>
        <select id="diaasistencia" name="diaasistencia" class="custom-select">
            <c:forEach items="${requestScope.listdia}" var="listdia">
                <option value="${listdia.idtbdia}">${listdia.nombre} </option>
            </c:forEach>
        </select>
    </div>
    <div class="col-lg-4 p-t-20">
        <button type="button" id="asistencia" class="btn btn-terra">Guardar asistencia</button>
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
                            <a href="javascript:;" class="aliminarpe">
                                <i id="aliminarpe" class="icon-trash"></i> Eliminar </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>