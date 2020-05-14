<%-- 
    Document   : reporteasistencia
    Created on : 12/05/2020, 12:06:41 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Reporte de Asistencia </div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <% if (x == 3 || x == 4 || x == 5) { %>  
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Adminpersonal.tablaPersonal();">Tabla Personal</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <% }%>
            <li class="active">Reporte de asistencia </li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="col-lg-4 ">
            <label >Seleccione periodo escolar</label>
            <select id="periodoReporte" class="custom-select" name="periodoasistencia">
                <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                    <option value="${listperiodo.idtbperiodo}">${listperiodo.nombre} </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-lg-4 ">
            <label >Seleccione una materia</label>
            <select id="materiaReporte" name="MateriaReporte" class="custom-select">
                <c:forEach items="${requestScope.listmateria}" var="listmateria">
                    <option value="${listmateria.idtbmateriapersonal}" grado="${listmateria.r_grado}" grupo="${listmateria.r_grupo}" area="${listmateria.r_area}" cpt="${listmateria.r_cpt}">${listmateria.materia} </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-lg-4 ">
            <label >Seleccione Semana fiscal</label>
            <select id="semanaReporte" name="semanafiscalasistencia" class="custom-select">
                <c:forEach items="${requestScope.listsemana}" var="listsemana">
                    <option value="${listsemana.idtbsemana}">${listsemana.nombre} </option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div id="ReporteAsistencia">
            <jsp:include page='getreporteasistencia.jsp'>
                <jsp:param name="article1" value=""/>
            </jsp:include>
        </div>
    </div>
</div>
