<%-- 
    Document   : formpersonal
    Created on : 17/01/2020, 11:28:57 AM
    Author     : complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Formulario Personal</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Formulario Personal</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="p-rl-20">
        <ul class="nav customtab nav-tabs" role="tablist">
            <li class="nav-item"><a href="#tab1" class="nav-link active show" data-toggle="tab">Datos Personales</a></li>
            <li class="nav-item"><a href="#tab2" class="nav-link" data-toggle="tab">Datos Académicos</a></li>
        </ul>
    </div>
</div>
<div class="profile-content">
    <div class="row">
        <div class="col-md-12 col-sm-12">
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
                                                <strong type="text" >Los campos con signo * son obligatorios</strong>
                                                <form>
                                                    <div class="card-body row">
                                                        <div class="col-md-6 col-sm-6" idDatos="${requestScope.TbPersonal.idtbpersonal}">
                                                            <input id="idpersonal" type="hidden" value="${requestScope.personal.idtbpersonal}">
                                                            <div class="form-group" id="div-nombrep">
                                                                <label>Nombre(s)*</label>
                                                                <input value="${requestScope.personal.nombre}" id="nombrep" type="text" class="form-control" placeholder="nombre" maxlength="35">
                                                            </div>
                                                            <div class="form-group" id="div-apellidopp">
                                                                <label>Apellido Paterno*</label>
                                                                <input value="${requestScope.personal.apellidop}"  id="apellidopp" type="text" class="form-control" placeholder="apellido paterno" maxlength="20">
                                                            </div>
                                                            <div class="form-group" id="div-apellidomp">
                                                                <label>Apellido Materno*</label>
                                                                <input value="${requestScope.personal.apellidom}" id="apellidomp" type="text" class="form-control" placeholder="apellido materno" maxlength="20">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>CURP</label>
                                                                <input value="${requestScope.personal.curp}" id="curpp" type="text" class="form-control" placeholder="curp" maxlength="18">
                                                            </div>
                                                            <div class="form-group" id="div-fechanacimientop">
                                                                <label>Fecha de nacimiento*</label>
                                                                <input value="${requestScope.personal.fechanacimiento}" id="fechanap" type="date" class="form-control" placeholder="fecha nacimiento">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Municipio de nacimiento</label>
                                                                <input value="${requestScope.personal.municipionac}" id="municipionacp" type="text" class="form-control" placeholder="municipio ncimiento" maxlength="15">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Estado de nacimiento</label>
                                                                <input value="${requestScope.personal.estadonac}" id="estadonacp" type="text" class="form-control" placeholder="estado nacimiento " maxlength="15">
                                                            </div>
                                                            <label>Sexo</label>
                                                            <div class="form-group">
                                                                <c:choose>
                                                                    <c:when test="${requestScope.personal.sexo}">
                                                                        <div class="radio p-0">
                                                                            <input checked="true" type="radio" name="sexop" id="sexoah" value="true">
                                                                            <label for="sexoah">
                                                                                Hombre
                                                                            </label>
                                                                        </div>
                                                                        <div class="radio p-0">
                                                                            <input   type="radio" name="sexop" id="sexoam" value="false">
                                                                            <label for="sexoam">
                                                                                Mujer
                                                                            </label>
                                                                        </div>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <div class="radio p-0">
                                                                            <input  type="radio" name="sexop" id="sexoah" value="true">
                                                                            <label for="sexoah">
                                                                                Hombre
                                                                            </label>
                                                                        </div>
                                                                        <div class="radio p-0">
                                                                            <input checked="true"  type="radio" name="sexop" id="sexoam" value="false">
                                                                            <label for="sexoam">
                                                                                Mujer
                                                                            </label>
                                                                        </div>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                            <div class="form-group" id="div-correop">
                                                                <label>Correo*</label>
                                                                <input value="${requestScope.personal.correo}" id="correop" type="email" class="form-control" placeholder="correo" maxlength="50">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 col-sm-6">
                                                            <div class="form-group">
                                                                <label>Teleono de casa</label>
                                                                <input value="${requestScope.personal.telefonocasa}" id="telcasatp" type="number" class="form-control" placeholder="telefono casa" maxlength="15">
                                                            </div>
                                                            <div class="form-group" id="div-celularp">
                                                                <label>Celular*</label>
                                                                <input value="${requestScope.personal.celular}" id="celularp" type="number" class="form-control" placeholder="celular" maxlength="10">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Nacionalidad</label>
                                                                <input value="${requestScope.personal.nacionalidad}" id="nacionalidadp" maxlength="10" type="text" class="form-control" placeholder="nacionalidad" maxlength="15">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Calle del domicilio</label>
                                                                <input value="${requestScope.personal.calledom}" id="calledomp" type="text" class="form-control" placeholder="calle" maxlength="20">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Número del domicilio</label>
                                                                <input value="${requestScope.personal.numerodom}" id="numerodomp" type="number" class="form-control" placeholder="numero" maxlength="4">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Colonia del domicilio</label>
                                                                <input value="${requestScope.personal.coloniadom}" id="coloniadomp" type="text" class="form-control" placeholder="colonia" maxlength="15">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Código postal</label>
                                                                <input value="${requestScope.personal.codigopostal}" id="codigopostalp" type="number" class="form-control" placeholder="codigo postal" maxlength="5">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Número de seguro social</label>
                                                                <input value="${requestScope.personal.nss}" id="nss" type="text" class="form-control" placeholder="seuro social" maxlength="1">
                                                            </div>

                                                            <div class="form-group">
                                                                <label>RFC</label>
                                                                <input value="${requestScope.personal.rfc}" id="rfc" type="text" class="form-control" placeholder="rfc" maxlength="18">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <label> Al término de éste formulario pasar a siguiente pestaña</label>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab2">
                        <div class="row">
                            <div class="col-md-12 col-sm-12">
                                <div class="card-body " id="bar-parent2">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12">
                                            <strong type="text" >Los campos con signo * son obligatorios</strong>
                                            <form>
                                                <div class="card-body row">
                                                    <div class="col-md-6 col-sm-6">
                                                        <div class="form-group" id="div-nivelmax">
                                                            <label>Nivel máximo de estudios*</label>
                                                            <input value="${requestScope.personal.nivelestudios}" id="nivelmax" type="text" class="form-control" placeholder="nivel de estudios">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Licenciatura (si aplica)</label>
                                                            <input value="${requestScope.personal.licenciatura}"  id="licenciatura" type="text" class="form-control" placeholder="licenciatura">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Maestria (si aplica)</label>
                                                            <input value="${requestScope.personal.maestria}" id="maestria" type="text" class="form-control" placeholder="maestria">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Doctorado (si aplica)</label>
                                                            <input value="${requestScope.personal.doctorado}" id="doctorado" type="text" class="form-control" placeholder="doctorado">
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Seleccione un puesto</label>
                                                            <select id="puesto" class="custom-select">
                                                                <c:forEach items="${requestScope.listpuesto}" var="listpuesto">
                                                                    <option value="${listpuesto.idtbpuesto}" 
                                                                            <c:if test="${listpuesto.idtbpuesto == requestScope.personal.rpuesto}">
                                                                                selected=""
                                                                            </c:if>
                                                                            >${listpuesto.nombre} </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <button id="guardapersonal" type="button" class="btn btn-terra">Guardar</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>           
    </div>
</div>
