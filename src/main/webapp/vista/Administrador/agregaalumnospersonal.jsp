<%-- 
    Document   : agregaalumnospersonal
    Created on : 8/04/2020, 02:53:35 PM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Agregar Alumnos</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
                &nbsp;<a class="parent-item" href="javascript:Adminpersonal.tablaPersonal();">Tabla Personal</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Agregar alumnos</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="col-lg-4 p-t-20">
            <label >Materia a agregar alumnos</label>
            <select id="materiaAlumPer" name="materiaAlumPer" class="custom-select">
                <c:forEach items="${requestScope.listmateria}" var="listmateria">
                    <option value="${listmateria.idtbmateriapersonal}">${listmateria.materia} </option>
                </c:forEach>
            </select>
        </div>
        <div class="card-body row">
            <div class="col-lg-4 p-t-20">
                <label >Seleccione grado</label>
                <select id="gradoAlumPer" class="custom-select" name="gradoAlumPer">
                    <c:forEach items="${requestScope.listgrado}" var="listgrado">
                        <option value="${listgrado.idtbgrado}">${listgrado.nombre} </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-lg-4 p-t-20">
                <label >Seleccione grupo</label>
                <select id="grupoAlumPer" name="grupoAlumPer" class="custom-select">
                    <c:forEach items="${requestScope.listgrupo}" var="listgrupo">
                        <option value="${listgrupo.idtbgrupo}">${listgrupo.nombre} </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div id="asignaAlumno">
            <jsp:include page='asignaalumno.jsp'>
                <jsp:param name="article1" value=""/>
            </jsp:include>
        </div>
    </div>
</div>
