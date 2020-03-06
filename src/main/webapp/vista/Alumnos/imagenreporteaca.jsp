<%-- 
    Document   : tablapersonal
    Created on : 17/01/2020, 10:56:41 AM
    Author     : complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<form id="formreporteA" name="formaReporteA" method="POST" action="SReportes">
    <input type="text" name="ACCION" id="ACCION" value="GuardaImagenA" hidden="true">
    <input hidden="true" id="inputA" name="inputa" value="">
    <div id="imagen" class="row imagen">
        <div class="center col-xl-12">
            <img alt="" src="assets/img/warning.png" style="width: 400px; height: 400px;">
        </div>
        <div class="col-xl-12 center"> <br> <br>
            Alumnos que requieren atencion debido al siguiente problema:<strong> ${requestScope.reporteA.atencion} </strong> <br>
            ${requestScope.reporteA.nombreatencion} ${requestScope.reporteA.apellidopatencion} ${requestScope.reporteA.apellidomatencion}
        </div><br> <br>
        <div class="col-xl-12"></div>
        <div class="col-xl-12"></div>
        <div class="col-xl-12"></div>
        <div class="col-xl-12"></div>
        <div class="center col-xl-12">
            <img alt="" src="assets/img/congratulations.png" style="width: 450px; height: 450px;">
        </div>
        <div class="col-xl-12 center">
            Alumnos que sobresalieron esta semana: <br>
            ${requestScope.reporteA.nombrehonor} ${requestScope.reporteA.apellidophonor} ${requestScope.reporteA.apellidomhonor}
        </div>
        <button id="guardarimagen" type="submit">Generar Imagen</button>
        <a href="" id="blanko"></a>
    </div>
</form>
