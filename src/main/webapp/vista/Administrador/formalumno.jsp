<%-- 
    Document   : formalumno
    Created on : 16/01/2020, 02:05:21 PM
    Author     : complx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                                        <div class="col-md-6 col-sm-6">
                                                            <div class="form-group">
                                                                <label>Nombre(s)</label>
                                                                <input id="nombret" type="text" class="form-control" placeholder="nombre">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Apellido Paterno</label>
                                                                <input  id="apellidopt" type="text" class="form-control" placeholder="apellido paterno">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Apellido Materno</label>
                                                                <input id="apellidomt" type="text" class="form-control" placeholder="apellido materno">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Ocupacion</label>
                                                                <input id="ocupacion" type="text" class="form-control" placeholder="ocupacion">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Parentesco</label>
                                                                <input id="parentesco" type="text" class="form-control" placeholder="parentesco">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Teléfono casa</label>
                                                                <input id="telcasat" type="number" class="form-control" placeholder="telefono casa">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 col-sm-6">
                                                            <div class="form-group">
                                                                <label>Celular</label>
                                                                <input id="celulart" maxlength="10" type="number" class="form-control" placeholder="celular">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Correo</label>
                                                                <input id="correot" type="email" class="form-control" placeholder="correo">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Calle del domicilio</label>
                                                                <input id="calledomt" type="text" class="form-control" placeholder="calle">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Número del domicilio</label>
                                                                <input id="numerodomt" type="number" class="form-control" placeholder="numero">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Colonia del domicilio</label>
                                                                <input id="coloniadomt" type="text" class="form-control" placeholder="colonia">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Código postal</label>
                                                                <input id="codigopostalt" type="number" class="form-control" placeholder="codigo postal">
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
                                                            <input id="matricula" type="text" class="form-control" placeholder="matricula">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Nombre(s)</label>
                                                            <input  id="nombrea" type="text" class="form-control" placeholder="nombre(s)">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Apellido paterno</label>
                                                            <input id="apellidopa" type="text" class="form-control" placeholder="apellido paterno">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Apellido materno</label>
                                                            <input id="apellidoma" type="text" class="form-control" placeholder="apellido materno">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Fecha de nacimiento</label>
                                                            <input id="fechanaa" type="date" class="form-control" placeholder="fecha nacimiento">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>CURP</label>
                                                            <input id="curpa" type="text" class="form-control" placeholder="curp">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Teléfono de casa</label>
                                                            <input id="telcasaa" type="number" class="form-control" placeholder="telefono casa">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Celular</label>
                                                            <input id="celulara" type="number" class="form-control" placeholder="celular">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Correo</label>
                                                            <input id="correoa" type="email" class="form-control" placeholder="correo">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Municipio de nacimiento</label>
                                                            <input id="municipionaca" type="text" class="form-control" placeholder="municipio nacimiento">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Estado de nacimiento</label>
                                                            <input id="estadonaca" type="text" class="form-control" placeholder="estado nacimiento">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Nacionalidad</label>
                                                            <input id="nacionalidada" type="text" class="form-control" placeholder="nacionalidad">
                                                        </div>
                                                        <label>Sexo</label>
                                                        <div class="form-group">
                                                            <div class="radio p-0">
                                                                <input checked="true" type="radio" name="optionsRadios1" id="sexoah" value="true">
                                                                <label for="sexoah">
                                                                    Hombre
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="radio p-0">
                                                                <input checked="true" type="radio" name="optionsRadios1" id="sexoam" value="false">
                                                                <label for="sexoam">
                                                                    Mujer
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Calle del domicilio</label>
                                                            <input id="calledoma" type="text" class="form-control" placeholder="calle domicilio">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Número del domicilio</label>
                                                            <input id="numerodoma" type="number" class="form-control" placeholder="numero domicilio">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Colonia del domicilio</label>
                                                            <input id="numerodoma" type="text" class="form-control" placeholder="colonia del domicilio">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Código postal</label>
                                                            <input id="codigopostala" type="number" class="form-control" placeholder="codigo postal">
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
                                <div class="card-head">
                                    <header>Reporte de actividades</header>
                                </div>
                                <div class="card-body " id="bar-parent2">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12">
                                            <form>
                                                <div class="card-body row">
                                                    <div class="col-md-6 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Nivel que cursa</label>
                                                            <input id="nivela" type="text" class="form-control" placeholder="nivel">
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Seleccione el grado</label>
                                                            <select class="custom-select">
                                                                <option value="1">1</option>
                                                                <option value="2">2</option>
                                                                <option value="3">3</option>
                                                                <option value="3">4</option>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Seleccione el grupo</label>
                                                            <select class="custom-select">
                                                                <option value="1">A</option>
                                                                <option value="2">B</option>
                                                                <option value="3">C</option>
                                                                <option value="3">D</option>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Seleccione el area</label>
                                                            <select class="custom-select">
                                                                <option value="1">No aun</option>
                                                                <option value="2">B</option>
                                                                <option value="3">C</option>
                                                                <option value="3">D</option>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label >Seleccione capacitacion para el trabajo</label>
                                                            <select class="custom-select">
                                                                <option value="1">No aun</option>
                                                                <option value="2">B</option>
                                                                <option value="3">C</option>
                                                                <option value="3">D</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Plantel de procedencia</label>
                                                            <input id="plantelproce" type="text" class="form-control" placeholder="plantel procedencia">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Nivel cursado anteriormente</label>
                                                            <input id="nivelanterior" type="number" class="form-control" placeholder="nivel anterior">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Grado cursado anteriormente</label>
                                                            <input id="gradoanterior" type="text" class="form-control" placeholder="grado anterior">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Turno cursado anteriormente</label>
                                                            <input id="turnoanterior" type="text" class="form-control" placeholder="turno anterior">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Municipio del plantel anterior</label>
                                                            <input id="plantelanterior" type="text" class="form-control" placeholder="plantel anterior">
                                                        </div>
                                                    </div>
                                                </div>
                                                <button type="button" class="btn btn-terra">Guardar</button>
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
