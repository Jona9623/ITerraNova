<%-- 
    Document   : justificar
    Created on : 30/05/2020, 11:06:16 AM
    Author     : Complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br>
<br> <br>
<div class="row ">
    <div class="col-lg-4 p-t-20">
        <label >Seleccione periodo escolar</label>
        <select id="periodojustificar" class="custom-select" name="periodojustificar">
            <c:forEach items="${requestScope.listperiodo}" var="listperiodo">
                <option value="${listperiodo.idtbperiodo}">${listperiodo.nombre} </option>
            </c:forEach>
        </select>
    </div>
    <div class="col-lg-4 p-t-20">
        <label >Seleccione Semana fiscal</label>
        <select id="semanajustificar" name="semanajustificar" class="custom-select">
            <c:forEach items="${requestScope.listsemana}" var="listsemana">
                <option value="${listsemana.idtbsemana}">${listsemana.nombre} </option>
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
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div id="JustificarFaltas">
            <jsp:include page='getdiasfaltas.jsp'>
                <jsp:param name="article1" value=""/>
            </jsp:include>
        </div>
    </div>
