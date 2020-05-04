<%-- 
    Document   : listaalumnos
    Created on : 13/04/2020, 11:28:45 AM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <div class="page-title">Lista Alumnos</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <% if (x == 3 || x == 4 || x == 5) { %>  
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Adminpersonal.tablaPersonal();">Tabla Personal</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <% }%>
            <li class="active">Lista alumnos</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="col-lg-4 p-t-20">
            <label >Seleccione una materia</label>
            <select id="materiaLista" name="materiaLista" class="custom-select">
                <c:forEach items="${requestScope.listmateria}" var="listmateria">
                    <option value="${listmateria.idtbmateriapersonal}" grado="${listmateria.r_grado}" grupo="${listmateria.r_grupo}" area="${listmateria.r_area}" cpt="${listmateria.r_cpt}">${listmateria.materia} </option>
                </c:forEach>
            </select>
        </div>
        <!-- <div class="card-body row">
             <div class="col-lg-4 p-t-20">
                 <label >Seleccione grado</label>
                 <select id="gradoLista" class="custom-select" name="gradoLista">
        <c:forEach items="${requestScope.listgrado}" var="listgrado">
            <option value="${listgrado.idtbgrado}">${listgrado.nombre} </option>
        </c:forEach>
    </select>
</div>
<div class="col-lg-4 p-t-20">
    <label >Seleccione grupo</label>
    <select id="grupoLista" name="grupoLista" class="custom-select">
        <c:forEach items="${requestScope.listgrupo}" var="listgrupo">
            <option value="${listgrupo.idtbgrupo}">${listgrupo.nombre} </option>
        </c:forEach>
    </select>
</div>
</div> -->
        <div id="ListaAlumnos">
            <jsp:include page='alumnoslista.jsp'>
                <jsp:param name="article1" value=""/>
            </jsp:include>
        </div>
    </div>
</div>
