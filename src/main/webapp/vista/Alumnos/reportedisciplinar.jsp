<%-- 
    Document   : subopcion1
    Created on : 13/01/2020, 10:19:10 AM
    Author     : complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    int x = 0;
    int id_sesion;
    HttpSession objsesion = request.getSession(false);
    String usuario = (String) objsesion.getAttribute("user");
    x = (int) objsesion.getAttribute("tipo");
    id_sesion = (int) objsesion.getAttribute("id");

%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Reporte Disciplinar</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Reporte disciplinar</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <% if (x != 2) { %>
        <div style="text-align: right">
            <a id="mostrarreportes" type="button" class="btn btn-terra float-rt">Mostrar reportes</a>
        </div>
        <% }%>
        <div class="">
            <div class="card-head">
                <header>Formulario de reporte</header>

            </div> <br>
            <strong type="text" >Los campos con signo * son obligatorios</strong>
            <form  id="formReporteD" enctype="multipart/form-data" method="POST" action="SReportes" name="formreporteD">
                <input type="text" name="ACCION" id="ACCION" value="GUARDAR" hidden="true">
                <input type="text" name="savefile" id="savefile" value="" hidden="true">
                <div class="col-lg-4 p-t-20">
                    <label ><strong>Periodo: ${requestScope.periodo.nombre}</strong></label>
                    <input hidden="true" id="periodoD" name="PeriodoD" value="${requestScope.periodo.idtbperiodo}">
                </div>
                <div class="card-body row">
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione grado</label>
                        <select id="gradodisciplinar" class="custom-select" name="Gradodisciplinar">
                            <c:forEach items="${requestScope.listgrado}" var="listgrado">
                                <option value="${listgrado.idtbgrado}">${listgrado.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione grupo</label>
                        <select id="grupodisciplinar" name="Grupodisciplinar" class="custom-select">
                            <c:forEach items="${requestScope.listgrupo}" var="listgrupo">
                                <option value="${listgrupo.idtbgrupo}">${listgrupo.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div id="alumnogradoD">
                            <jsp:include page='alumnosgradogrupo.jsp'>
                                <jsp:param name="article1" value=""/>
                            </jsp:include>
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Seleccione Tipo de incidente</label>
                        <select id="incidente" name="Incidente" class="custom-select">
                            <c:forEach items="${requestScope.listincidente}" var="listincidente">
                                <option value="${listincidente.idtbincidente}">${listincidente.nombre} </option>
                            </c:forEach>
                        </select>
                        <a class="clic" data-toggle="modal" data-target="#exampleModalCenter">Si no encuentra un incidente puede agregar uno aqui</a>
                    </div>
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group">
                            <label>Nivel de incidente</label>
                            <select id="nivel" name="Nivel" class="custom-select">
                                <option value="1">No tan grave</option>
                                <option value="2">Grave</option>
                                <option value="3">Muy grave</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group" id="div-horaincidente">
                            <label>Hora del incidente *</label>
                            <input id="horaincidente" name="Horaincidente" type="time" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div class="form-group" id="div-fechaincidente">
                            <label>Fcha del incidente *</label>
                            <input id="fechaincidente" name="Fechaincidente" type="date" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div class="form-group" id="div-fechareporte">
                            <label>Fcha del reporte *</label>
                            <input id="fechareporte" name="Fechareporte" type="date" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Personal que solicita el reporte</label>
                        <select id="personalsolicita" name="Personalsolicita" class="custom-select">
                            <c:forEach items="${requestScope.listpersonal}" var="listpersonal">
                                <option value="${listpersonal.idtbpersonal}">${listpersonal.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Personal que llena el reporte</label>
                        <select id="personalllena" name="Personalllena" class="custom-select">
                            <c:forEach items="${requestScope.listpersonal}" var="listpersonal">
                                <option value="${listpersonal.idtbpersonal}">${listpersonal.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Materia durante el incidente (si aplica)</label>
                        <select id="materiaD" name="MateriaD" class="custom-select">
                            <option value="0">No aplica</option>
                            <c:forEach items="${requestScope.listmateria}" var="listmateria">
                                <option value="${listmateria.idtbmateria}">${listmateria.nombrecorto} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <label >Maestro de materia (si aplica)</label>
                        <select id="personalmateria" name="Personal" class="custom-select">
                            <option value="0">No aplica</option>
                            <c:forEach items="${requestScope.listpersonal}" var="listpersonal">
                                <option value="${listpersonal.idtbpersonal}">${listpersonal.nombre} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-4 p-t-20"> 
                        <div class="form-group" id="div-lugarincidente">
                            <label>Lugar del incidente *</label>
                            <input id="lugarincidente" name="Lugarincidente" type="text" class="form-control" placeholder="Enter ...">
                        </div>
                    </div>
                    <div class="col-lg-4 p-t-20">
                        <div class="form-group" id="div-descripcion">
                            <label>Descripciopn de la falta *</label>
                            <textarea id="descripcion" name="Descripcion" class="form-control" rows="5" placeholder="Enter ..."></textarea>
                        </div>
                    </div>
                </div>
                <div class="card-body row ">
                    <div class="form-group">
                        <a class="material-icons f-left">camera_enhance</a>
                        <a> Agregar fotografía (opcional) </a> <br>
                        <input id="foto" type="file" name="Archivo">
                        <input hidden="true" id="imagenRD" name="imagenRD" val="">
                    </div>
                </div>
                <button id="guardareporteD" type="submit" class="btn btn-terra">Guardar</button> &nbsp; &nbsp; &nbsp;
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" value="1" name="correotutor" id="correotutor">
                    Activar envío de correo a tutor
                </label>
                <input name="tutorcorreo" id="tutorcorreo" value="0" hidden="true">
                <script>
                    $(document).on('change', 'input[type="checkbox"]', function (e) {
                        if (this.id == "correotutor") {
                            if (this.checked)
                                $('#tutorcorreo').val(this.value);
                            else
                                $('#tutorcorreo').val(0);
                        }
                    });
                    var form = document.getElementById("formReporteD");
                    form.onsubmit = function (e) {
                        var valido = true;
                        if ($("#horaincidente").val().trim() == "") {
                            $("#div-horaincidente").addClass("has-error");
                            valido = false;
                        }
                        if ($("#fechaincidente").val().trim() == "") {
                            $("#div-fechaincidente").addClass("has-error");
                            valido = false;
                        }
                        if ($("#fechareporte").val().trim() == "") {
                            $("#div-fechareporte").addClass("has-error");
                            valido = false;
                        }
                        if ($("#lugarincidente").val().trim() == "") {
                            $("#div-lugarincidente").addClass("has-error");
                            valido = false;
                        }
                        if ($("#horaincidente").val().trim() == "") {
                            $("#div-horaincidente").addClass("has-error");
                            valido = false;
                        }
                        if ($("#descripcion").val().trim() == "") {
                            $("#div-descripcion").addClass("has-error");
                            valido = false;
                        }
                        if (valido == false) {
                            alert("entro al if");
                            swal("Faltan campos requeridos", "", "warning");
                            e.preventDefault();
                        }
                    }
                </script>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle"><div class="card-head">
                        <header>Formulario Incidente</header>
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
                                            <label>Incidente</label>
                                            <input type="text" id="incidenteD" class="form-control" placeholder="nombre...">
                                        </div>
                                    </div><br>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" id="guardaincidente" class="btn btn-terra" data-dismiss="modal">Guardar</button>
            </div>
        </div>
    </div>
</div>
