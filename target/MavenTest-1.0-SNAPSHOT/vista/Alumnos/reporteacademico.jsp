<%-- 
    Document   : reporteacademico
    Created on : 14/01/2020, 11:14:14 AM
    Author     : complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
    int x = 0;
    int id_sesion;
    HttpSession objsesion = request.getSession(false);
    String usuario = (String) objsesion.getAttribute("user");
    x = (int) objsesion.getAttribute("tipo");
    id_sesion = (int) objsesion.getAttribute("id");

%>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Reporte Academico</div> <br>
            <p>Personal: <strong>${requestScope.personal.apellidop} ${requestScope.personal.apellidom} ${requestScope.personal.nombre}</strong></p>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Reporte academico</li>
        </ol>
    </div>
</div>
<% if (x == 1 || x == 2) { %>
<div class="row">
    <div class="p-rl-20">
        <ul class="nav customtab nav-tabs" role="tablist">
            <li class="nav-item"><a href="#tab1" class="nav-link active show" data-toggle="tab">Academico</a></li>
            <li class="nav-item"><a href="#tab2" class="nav-link" data-toggle="tab">Semana de actividades</a></li>
        </ul>
    </div>
</div>
<%}%>
<div class="profile-content">
    <div class="row">
        <% if (x == 3 || x == 4 || x == 5) { %>  
        <div class="col-md-12 col-sm-12">
            <div style="text-align: right">
                <a id="mostrarreportesaca" type="button" class="btn btn-terra float-rt">Mostrar cuadro de honor/atencion</a>
            </div>
            <div ><br></div>
            <div class="row">
                <div class="col-md-12 col-sm-12">
                    <div style="text-align: right">
                        <a id="mostraractividades" type="button" class="btn btn-terra float-rt">Mostrar actividades semanales</a>
                    </div>  
                </div>
            </div>
            <% }%>
            <% if (x == 1 || x == 2) { %>  
            <div class="">
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fontawesome-demo active show" id="tab1">
                        <div class="row">
                            <div class="col-md-12 col-sm-12">
                                <div class="card-body " id="bar-parent2">
                                    <!-- text input -->
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="">
                                                <div class="col-lg-4 p-t-20">
                                                    <label ><strong>Periodo: ${requestScope.periodo.nombre}</strong></label>
                                                    <input hidden="true" id="periodoA" name="PeriodoA" value="${requestScope.periodo.idtbperiodo}">
                                                </div>
                                                <form>
                                                    <div class="card-body row">
                                                        <div class="col-lg-4 p-t-20">
                                                            <label ><strong>Semana: ${requestScope.semana.nombre}</strong></label>
                                                            <input hidden="true" id="semanafiscal" name="semanafiscal" value="${requestScope.semana.idtbsemana}">
                                                        </div>
                                                        <div class="col-lg-4 p-t-20">
                                                            <label >Maestro de materia</label> <br>
                                                            <strong>${requestScope.personal.apellidop} ${requestScope.personal.apellidom} ${requestScope.personal.nombre}</strong>
                                                        </div>
                                                        <div class="col-lg-4 p-t-20">
                                                                <label >Materia</label>
                                                                <select id="materiaA" name="MateriaD" class="custom-select">
                                                                    <c:forEach items="${requestScope.listmateria}" var="listmateria">

                                                                        <option value="${listmateria.idtbmateriapersonal}">${listmateria.materia} </option>
                                                                    </c:forEach>
                                                                </select>
                                                        </div>
                                                    </div>
                                                    <div class="card-head">
                                                        <header>Cuadro de honor</header>
                                                    </div>
                                                    <div class="card-body row">
                                                        <div class="col-lg-4 p-t-20">
                                                            <label >Seleccione grado</label>
                                                            <select id="gradohonor" class="custom-select" name="gradohonor">
                                                                <c:forEach items="${requestScope.listgrado}" var="listgrado">
                                                                    <option value="${listgrado.idtbgrado}">${listgrado.nombre} </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-lg-4 p-t-20">
                                                            <label >Seleccione grupo</label>
                                                            <select id="grupohonor" name="grupohonor" class="custom-select">
                                                                <c:forEach items="${requestScope.listgrupo}" var="listgrupo">
                                                                    <option value="${listgrupo.idtbgrupo}">${listgrupo.nombre} </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-lg-4 p-t-20">
                                                            <div id="alumnogradoAA">
                                                                <jsp:include page='alumnosgradogrupo.jsp'>
                                                                    <jsp:param name="article1" value=""/>
                                                                </jsp:include>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div id="honor">
                                                        <div class="card-head">
                                                            <header>Cuadro de atención</header>
                                                        </div>
                                                        <div class="card-body row">
                                                            <div class="col-lg-4 p-t-20">
                                                                <label >Seleccione grado</label>
                                                                <select id="gradoatencion" class="custom-select" name="gradoatencion">
                                                                    <c:forEach items="${requestScope.listgrado}" var="listgrado">
                                                                        <option value="${listgrado.idtbgrado}">${listgrado.nombre} </option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="col-lg-4 p-t-20">
                                                                <label >Seleccione grupo</label>
                                                                <select id="grupoatencion" name="grupoatencion" class="custom-select">
                                                                    <c:forEach items="${requestScope.listgrupo}" var="listgrupo">
                                                                        <option value="${listgrupo.idtbgrupo}">${listgrupo.nombre} </option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="col-lg-4 p-t-20">
                                                                <div id="alumnogradoA">
                                                                    <jsp:include page='alumnogradogrupoatencion.jsp'>
                                                                        <jsp:param name="article1" value=""/>
                                                                    </jsp:include>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-4 p-t-20">
                                                                <label >Seleccione Tipo de comportamiento</label>
                                                                <select id="comportamiento" class="custom-select">
                                                                    <c:forEach items="${requestScope.listatencion}" var="listatencion">
                                                                        <option value="${listatencion.idtbatencion}">${listatencion.nombre} </option>
                                                                    </c:forEach>
                                                                </select>
                                                                <br> 
                                                                <a class="clic" data-toggle="modal" data-target="#exampleModalCenter">Si no encuentra comportmaiento puede agregar uno aqui</a>


                                                            </div>
                                                        </div>
                                                    </div>
                                                    <button type="button" id="guardareporteA" class="btn btn-terra">Guardar</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane captura" id="tab2">
                        <div class="row">
                            <div class="col-md-12 col-sm-12">
                                <div class="card-head">
                                    <header>Reporte de actividades</header>
                                </div>
                                <div class="card-body " id="bar-parent2">
                                    <div class="row">
                                        <div class="col-lg-4 p-t-20">
                                            <label ><strong>Periodo: ${requestScope.periodo.nombre}</strong></label>
                                            <input hidden="true" id="periodoactividad" name="periodoactividad" value="${requestScope.periodo.idtbperiodo}">
                                        </div>  
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4 p-t-20">
                                            <label ><strong>Semana: ${requestScope.semana.nombre}</strong></label>
                                            <input hidden="true" id="semanafiscalactividad" name="semanafiscal" value="${requestScope.semana.idtbsemana}">
                                        </div>
                                        <div class="col-md-12 col-sm-12">
                                            <form>
                                                <div class="card-body row">
                                                    <div class="col-lg-4 p-t-20">
                                                        <label >Seleccione el dia actual</label>
                                                        <select id="diaactividad" name="diaactividad" class="custom-select">
                                                            <c:forEach items="${requestScope.listdia}" var="listdia">
                                                                <option value="${listdia.idtbdia}">${listdia.nombre} </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-lg-4 p-t-20">
                                                        <div class="form-group" id="div-actividad">
                                                            <label> Descripción de la actividad*</label>
                                                            <textarea id="actividad" class="form-control" rows="5" placeholder="Describa la actividad..."></textarea>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 p-t-20">
                                                        <label >Maestro que asigna la actividad</label> <br>
                                                        <strong>${requestScope.personal.apellidop} ${requestScope.personal.apellidom} ${requestScope.personal.nombre}</strong>
                                                        <input hidden="true" value="${requestScope.personal.idtbpersonal}">
                                                    </div>
                                                    <div class="col-lg-4 p-t-20">
                                                        <label >Materia</label>
                                                        <select id="personalactividad" name="personalactividad" class="custom-select">
                                                            <c:forEach items="${requestScope.listmateria}" var="listmateria">
                                                                <option value="${listmateria.idtbmateriapersonal}">${listmateria.materia} </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-lg-4 p-t-20">
                                                        <div class="form-group" id="div-fechaentrega">
                                                            <label> Fecha de entrega de la actividad*</label>
                                                            <input id="fechaentrega" name="Fechaentrega" type="date" class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                                <button type="button" id="actividadsemanal" class="btn btn-terra">Guardar</button>
                                            </form>
                                        </div>
                                    </div>
                                    <a href="" id="blank"></a>
                                </div>
                            </div>
                        </div>
                    </div>                      
                </div>
                <%}%>
                <!-- Modal -->
                <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                                        <header>Formulario de comportamientos</header>
                                    </div></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="">
                                            <form class="">
                                                <div class="card-body row">
                                                    <div class="col-lg-10 p-t-20"> 
                                                        <div class="form-group">
                                                            <label>Comportamiento</label>
                                                            <input type="text" id="comport" class="form-control" placeholder="Nombre del comportamiento">
                                                        </div>
                                                    </div><br>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary guardacomportammiento" data-dismiss="modal">Cerrar</button>
                                <button type="button" id="guardacomportammiento" class="btn btn-terra" data-dismiss="modal">Guardar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>           
    </div>
</div>
