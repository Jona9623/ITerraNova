<%-- 
    Document   : justificar
    Created on : 30/05/2020, 11:06:16 AM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Justificar/Mostrar faltas</div> <br>
            <p>Alumno(a): <strong>${requestScope.alumno.apellidop} ${requestScope.alumno.apellidom} ${requestScope.alumno.nombre}</strong></p>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Admin.reporteGAsistencia();">Reporte asistencia general</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Justificar/Mostrar faltas</li>
        </ol>
    </div>
</div>
<br>
<br> <br>
<div class="row ">
    <div class="col-lg-4 p-t-20">
        <label >Seleccione periodo escolar</label>
        <select id="periodojustificar" class="custom-select" name="periodojustificar">
            <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                <option value="${listperiodo.idtbperiodo}" 
                        <c:if test="${listperiodo.idtbperiodo == requestScope.idperiodo}">
                            selected=""
                        </c:if>
                        >${listperiodo.nombre} </option>
            </c:forEach>
        </select>
    </div>
    <div class="col-lg-4 p-t-20">
        <label >Seleccione Semana fiscal</label>
        <select id="semanajustificar" name="semanajustificar" class="custom-select">
            <c:forEach items="${requestScope.listsemana}" var="listsemana">
                <option value="${listsemana.idtbsemana}" 
                        <c:if test="${listsemana.idtbsemana == requestScope.idsemana}">
                            selected=""
                        </c:if>
                        >${listsemana.nombre} </option>
            </c:forEach>
        </select>
    </div>
    <div class="col-lg-4 p-t-20">
        <label >Seleccione una materia</label>
        <select id="materiajustificar" name="materiajustificar" class="custom-select">
            <c:forEach items="${requestScope.listmateria}" var="listmateria">
                <option value="${listmateria.idtbdatosmateria}">${listmateria.nombrelargo} </option>
            </c:forEach>
        </select>
    </div>

</div>
<div id="Justificacion">
    <jsp:include page='getdiasfaltas.jsp'>
        <jsp:param name="article1" value=""/>
    </jsp:include>
</div>
