<%-- 
    Document   : imagentareasemanal
    Created on : 6/03/2020, 12:04:42 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<form id="formractividad" name="formActividad" method="POST" action="SReportes">
    <input type="text" name="ACCION" id="ACCION" value="GuardaImagenActividad" hidden="true">
    <input hidden="true" id="inputB" name="inputb" value="">
    <div id="imagenAc" class="row imagen">
        <div class="center col-xl-12">
            <img alt="" src="assets/img/homework.jpeg" style="width: 400px; height: 400px;">
        </div>
        <div class="col-xl-12 center"> <br> <br> <br>
            La actividad fué encargada el dia<strong> ${requestScope.tarea.dia} </strong> <br>
            Por el maestro <strong> ${requestScope.tarea.personal} ${requestScope.tarea.apellidop} ${requestScope.tarea.apellidom}</strong>
            Con fecha límite de entrega para la siguiente fecha: <strong> ${requestScope.tarea.fechaentrega}</strong>
            Descripcion de la actividad: <br>
            ${requestScope.tarea.tarea}
        </div>
        <button id="guardarimagen" type="submit">Generar Imagen</button>
        <a href="" id="blank"></a>
    </div>
</form>
