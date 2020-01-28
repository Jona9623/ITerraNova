<%-- 
    Document   : formalumno
    Created on : 16/01/2020, 02:05:21 PM
    Author     : complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="page-bar  card-topline-terra2">
    <div class="page-title-breadcrumb">
        <div class=" pull-left">
            <div class="page-title">Formulario Alumno</div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="indexpre.jsp">Inicio</a>&nbsp;<i class="fa fa-angle-right"></i>
            </li>
            <li class="active">Formulario Alumno</li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="p-rl-20">
        <ul class="nav customtab nav-tabs" role="tablist">
            <li class="nav-item"><a href="#tab1" class="nav-link active show" data-toggle="tab">Tutor(es)</a></li>
            <li class="nav-item"><a href="#tab2" class="nav-link" data-toggle="tab">Datos Personales</a></li>
            <li class="nav-item"><a href="#tab3" class="nav-link" data-toggle="tab">Datos Escolridad</a></li>
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
                                                        <div class="col-md-6 col-sm-6" idDatos="${requestScope.TbAlumnos.idtbalumnos}">
                                                            <input id="idalumno" type="hidden" value="${requestScope.alumno.idtbalumnos}">
                                                            <input id="idtutor" type="hidden" value="${requestScope.tutor.idtbtutor}">
                                                            <div class="form-group">
                                                                <label>Nombre(s)</label>
                                                                <input id="nombret" value="${requestScope.tutor.nombre}" type="text" class="form-control" placeholder="nombre">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Apellido Paterno</label>
                                                                <input  id="apellidopt" value="${requestScope.tutor.apellidop}" type="text" class="form-control" placeholder="apellido paterno">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Apellido Materno</label>
                                                                <input id="apellidomt" value="${requestScope.tutor.apellidom}" type="text" class="form-control" placeholder="apellido materno">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Ocupacion</label>
                                                                <input id="ocupacion" value="${requestScope.tutor.ocupacion}" type="text" class="form-control" placeholder="ocupacion">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Parentesco</label>
                                                                <input id="parentesco" value="${requestScope.tutor.parentesco}" type="text" class="form-control" placeholder="parentesco">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Teléfono casa</label>
                                                                <input id="telcasat" value="${requestScope.tutor.telefonocasa}" type="number" class="form-control" placeholder="telefono casa">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 col-sm-6">
                                                            <div class="form-group">
                                                                <label>Celular</label>
                                                                <input id="celulart" value="${requestScope.tutor.celular}" maxlength="10" type="number" class="form-control" placeholder="celular">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Correo</label>
                                                                <input id="correot" value="${requestScope.tutor.correo}" type="email" class="form-control" placeholder="correo">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Calle del domicilio</label>
                                                                <input id="calledomt" value="${requestScope.tutor.calledom}" type="text" class="form-control" placeholder="calle">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Número del domicilio</label>
                                                                <input id="numerodomt" value="${requestScope.tutor.numerodom}" type="number" class="form-control" placeholder="numero">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Colonia del domicilio</label>
                                                                <input id="coloniadomt" value="${requestScope.tutor.coloniadom}" type="text" class="form-control" placeholder="colonia">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Código postal</label>
                                                                <input id="codigopostalt" value="${requestScope.tutor.codigopostal}" type="number" class="form-control" placeholder="codigo postal">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <button id="guardatutor" type="button" class="btn btn-terra">Guardar</button>
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
                                                            <label>Matricula</label>
                                                            <input id="matricula" value="${requestScope.alumno.matricula}" type="text" class="form-control" placeholder="matricula">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Nombre(s)</label>
                                                            <input  id="nombrea" value="${requestScope.alumno.nombre}" type="text" class="form-control" placeholder="nombre(s)">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Apellido paterno</label>
                                                            <input id="apellidopa" value="${requestScope.alumno.apellidop}" type="text" class="form-control" placeholder="apellido paterno">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Apellido materno</label>
                                                            <input id="apellidoma" value="${requestScope.alumno.apellidom}" type="text" class="form-control" placeholder="apellido materno">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Fecha de nacimiento</label>
                                                            <input id="fechanaa" value="${requestScope.alumno.fechanacimiento}" type="date" class="form-control" placeholder="fecha nacimiento">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>CURP</label>
                                                            <input id="curpa" value="${requestScope.alumno.curp}" type="text" class="form-control" placeholder="curp">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Teléfono de casa</label>
                                                            <input id="telcasaa" value="${requestScope.alumno.telefonocasa}" type="number" class="form-control" placeholder="telefono casa">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Celular</label>
                                                            <input id="celulara" value="${requestScope.alumno.celular}" type="number" class="form-control" placeholder="celular">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Correo</label>
                                                            <input id="correoa" value="${requestScope.alumno.correo}" type="email" class="form-control" placeholder="correo">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Municipio de nacimiento</label>
                                                            <input id="municipionaca" value="${requestScope.alumno.municipiona}" type="text" class="form-control" placeholder="municipio nacimiento">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Estado de nacimiento</label>
                                                            <input id="estadonaca" value="${requestScope.alumno.estadona}" type="text" class="form-control" placeholder="estado nacimiento">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Nacionalidad</label>
                                                            <input id="nacionalidada" value="${requestScope.alumno.nacionalidad}" type="text" class="form-control" placeholder="nacionalidad">
                                                        </div>
                                                        <label>Sexo</label>
                                                        <div class="form-group">
                                                            <c:choose>
                                                                <c:when test="${requestScope.alumno.sexo}">
                                                                    <div class="radio p-0">
                                                                        <input checked="true" type="radio" name="sexo" id="sexoah" value="true">
                                                                        <label for="sexoah">
                                                                            Hombre
                                                                        </label>
                                                                    </div>
                                                                    <div class="radio p-0">
                                                                        <input   type="radio" name="sexo" id="sexoam" value="false">
                                                                        <label for="sexoam">
                                                                            Mujer
                                                                        </label>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <div class="radio p-0">
                                                                        <input  type="radio" name="sexo" id="sexoah" value="true">
                                                                        <label for="sexoah">
                                                                            Hombre
                                                                        </label>
                                                                    </div>
                                                                    <div class="radio p-0">
                                                                        <input checked="true"  type="radio" name="sexo" id="sexoam" value="false">
                                                                        <label for="sexoam">
                                                                            Mujer
                                                                        </label>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Calle del domicilio</label>
                                                            <input id="calledoma" value="${requestScope.alumno.calledom}" type="text" class="form-control" placeholder="calle domicilio">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Número del domicilio</label>
                                                            <input id="numerodoma" value="${requestScope.alumno.numerodom}" type="number" class="form-control" placeholder="numero domicilio">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Colonia del domicilio</label>
                                                            <input id="coloniadoa" value="${requestScope.alumno.coloniadom}" type="text" class="form-control" placeholder="colonia del domicilio">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Código postal</label>
                                                            <input id="codigopostala" value="${requestScope.alumno.codigopostal}" type="number" class="form-control" placeholder="codigo postal">
                                                        </div>
                                                    </div>
                                                </div>
                                                <label>Al término de éste formulario pasar a siguiente pestaña</label>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab3">
                        <div class="row">
                            <div class="col-md-12 col-sm-12">
                                <div class="card-body " id="bar-parent2">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12">
                                            <form>
                                                <div class="card-body row">
                                                    <div class="col-md-6 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Nivel que cursa</label>
                                                            <input id="nivela" value="${requestScope.alumno.nivelcursa}" type="text" class="form-control" placeholder="nivel">
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Seleccione el grado</label>
                                                            <select id="grado" class="custom-select">
                                                                <c:forEach items="${requestScope.listgrado}" var="listgrado">
                                                                    <option value="${listgrado.idtbgrado}" 
                                                                            <c:if test="${listgrado.idtbgrado == requestScope.alumno.rgrado}">
                                                                                selected=""
                                                                            </c:if>
                                                                            >${listgrado.nombre} </option>

                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Seleccione el grupo</label>
                                                            <select id="grupo" class="custom-select">
                                                                <c:forEach items="${requestScope.listgrupo}" var="listgrupo">
                                                                    <option value="${listgrupo.idtbgrupo}" 
                                                                            <c:if test="${listgrupo.idtbgrupo == requestScope.alumno.rgrupo}">
                                                                                selected=""
                                                                            </c:if>
                                                                            >${listgrupo.nombre} </option>

                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Seleccione el area</label>
                                                            <select id="area" class="custom-select">
                                                                <option value="">No aún</option>
                                                                <c:forEach items="${requestScope.listarea}" var="listarea">
                                                                    <option value="${listarea.idtbarea}" 
                                                                            <c:if test="${listarea.idtbarea == requestScope.alumno.rarea}">
                                                                                selected=""
                                                                            </c:if>
                                                                            >${listarea.nombre} </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Seleccione capacitacion para el trabajo</label>
                                                            <select id="cpt" class="custom-select">
                                                                <option value="">No aún</option>
                                                                <c:forEach items="${requestScope.listcpt}" var="listcpt">
                                                                   <option value="${listcpt.idtbcpt}" 
                                                                            <c:if test="${listcpt.idtbcpt == requestScope.alumno.rcpt}">
                                                                                selected=""
                                                                            </c:if>
                                                                            >${listcpt.nombre} </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Plantel de procedencia</label>
                                                            <input id="plantelproce" value="${requestScope.alumno.plantelproce}" type="text" class="form-control" placeholder="plantel procedencia">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Nivel cursado anteriormente</label>
                                                            <input id="nivelanterior" value="${requestScope.alumno.nivelanterior}" type="number" class="form-control" placeholder="nivel anterior">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Grado cursado anteriormente</label>
                                                            <input id="gradoanterior" value="${requestScope.alumno.gradoanterior}" type="number" class="form-control" placeholder="grado anterior">
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Turno cursado anteriormente</label>
                                                            <select id="turnoanterior" class="custom-select">
                                                                <option value="1" <c:if test="${requestScope.alumno.turnoanterior == 1}"> selected=""</c:if>>Matutino</option>
                                                                <option value="2" <c:if test="${requestScope.alumno.turnoanterior == 2}"> selected=""</c:if>>Vespertino</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Municipio del plantel anterior</label>
                                                                <input id="plantelanterior" value="${requestScope.alumno.municipioante}" type="text" class="form-control" placeholder="plantel anterior">
                                                        </div>
                                                    </div>
                                                </div>
                                                <button id="guardaalumno" type="button" class="btn btn-terra">Guardar</button>
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
