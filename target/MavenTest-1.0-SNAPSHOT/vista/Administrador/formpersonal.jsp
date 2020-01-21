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
                                                <form>
                                                    <div class="card-body row">
                                                        <div class="col-md-6 col-sm-6">
                                                            <div class="form-group">
                                                                <label>Nombre(s)</label>
                                                                <input id="nombrep" type="text" class="form-control" placeholder="nombre">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Apellido Paterno</label>
                                                                <input  id="apellidopp" type="text" class="form-control" placeholder="apellido paterno">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Apellido Materno</label>
                                                                <input id="apellidomp" type="text" class="form-control" placeholder="apellido materno">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>CURP</label>
                                                                <input id="curpp" type="text" class="form-control" placeholder="curp">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Fecha de nacimiento</label>
                                                                <input id="fechanap" type="date" class="form-control" placeholder="fecha nacimiento">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Municipio de nacimiento</label>
                                                                <input id="municipionacp" type="text" class="form-control" placeholder="municipio ncimiento">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Estado de nacimiento</label>
                                                                <input id="estadonacp" type="text" class="form-control" placeholder="estado nacimiento ">
                                                            </div>
                                                            <label>Sexo</label>
                                                            <div class="form-group">
                                                                <div class="radio p-0">
                                                                    <input checked="true" type="radio" name="sexop" id="sexoah" value="true">
                                                                    <label for="sexoah">
                                                                        Hombre
                                                                    </label>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="radio p-0">
                                                                    <input checked="true" type="radio" name="sexop" id="sexoam" value="false">
                                                                    <label for="sexoam">
                                                                        Mujer
                                                                    </label>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Correo</label>
                                                                <input id="correop" type="email" class="form-control" placeholder="codigo postal">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 col-sm-6">
                                                            <div class="form-group">
                                                                <label>Teleono de casa</label>
                                                                <input id="telcasatp" type="number" class="form-control" placeholder="telefono casa">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Celular</label>
                                                                <input id="celularp" type="number" class="form-control" placeholder="celular">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Nacionalidad</label>
                                                                <input id="nacionalidadp" maxlength="10" type="text" class="form-control" placeholder="nacionalidad">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Calle del domicilio</label>
                                                                <input id="calledomp" type="text" class="form-control" placeholder="calle">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Número del domicilio</label>
                                                                <input id="numerodomp" type="number" class="form-control" placeholder="numero">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Colonia del domicilio</label>
                                                                <input id="coloniadomp" type="text" class="form-control" placeholder="colonia">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Código postal</label>
                                                                <input id="codigopostalp" type="number" class="form-control" placeholder="codigo postal">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Número de seguro social</label>
                                                                <input id="nss" type="text" class="form-control" placeholder="seuro social">
                                                            </div>

                                                            <div class="form-group">
                                                                <label>RFC</label>
                                                                <input id="rfc" type="text" class="form-control" placeholder="rfc">
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
                                            <form>
                                                <div class="card-body row">
                                                    <div class="col-md-6 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Nivel máximo de estudios</label>
                                                            <input id="nivelmax" type="text" class="form-control" placeholder="nivel de estudios">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Licenciatura (si aplica)</label>
                                                            <input  id="licenciatura" type="text" class="form-control" placeholder="licenciatura">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Maestria (si aplica)</label>
                                                            <input id="maestria" type="text" class="form-control" placeholder="maestria">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Doctorado (si aplica)</label>
                                                            <input id="doctorado" type="text" class="form-control" placeholder="doctorado">
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Seleccione un puesto</label>
                                                            <select id="puesto" class="custom-select">
                                                                <c:forEach items="${requestScope.listpuesto}" var="listpuesto">
                                                                    <option value="${listpuesto.idtbpuesto}">${listpuesto.nombre}</option>
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
