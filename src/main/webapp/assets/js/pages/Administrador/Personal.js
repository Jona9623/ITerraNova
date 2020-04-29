var Adminpersonal = (function () {
    var tipoescuela = JSON.parse(sessionStorage.getItem("tipoescuela"));
    return {
        tablaPersonal: function () {
            $.get("SAdminpersonal", {
                ACCION: "MuestraAgregaPersonal",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $('#content').html(arguments[0]);
                    $('#tablapersonal').DataTable({
                        "scrollX": true
                    });
                    Adminpersonal.importarPersonal();
                    //$('#btnaregarP').on('click', function () {
                    $("body").on("click", "#btnaregarP", function (event) {
                        Adminpersonal.agregaPersonal();
                    });
                    //$(".infopersonal").on('click', function () {
                    $("body").on("click", ".infopersonal", function (event) {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.infoPersonal(idpersonal);
                    });
                    //$(".agregamateria").on('click', function () {
                    $("body").on("click", ".agregamateria", function (event) {
                        alert("entra");
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.agregaMateria(idpersonal);
                    });
                    //$(".listaalumnos").on('click', function () {
                    $("body").on("click", ".listaalumnos", function (event) {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.listaAlumnos(idpersonal);
                    });
                    //$('.editarpe').on('click', function () {
                    $("body").on("click", ".editarpe", function (event) {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.editarPersonal(idpersonal);
                    });
                    //$(".aliminarpe").on('click', function () {
                    $("body").on("click", ".eliminarpe", function (event) {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        swal({
                            title: "Estas seguro?",
                            text: "Se eliminara el registro",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "Confirmar",
                            closeOnConfirm: false
                        }, function () {
                            swal("Eliminado exitosamente!", "Click en el bot\u00F3n!", "success");
                            Adminpersonal.eliminarPersonal(idpersonal);
                        });

                    });
                }
            });
            //$("#actualizaTablaP").on('click', function () {
            $("body").on("click", "#actualizaTablaP", function (event) {
                Adminpersonal.tablaPersonal();
            })
        },
        importarPersonal: function () {
            $('form[name="importarpersonal"]').ajaxForm(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    swal("Hecho!", "Datos importados correctamente", "success");
            });
        },
        formulariospe: function (argumento) {
            $('#content').html(argumento);
            $('#guardapersonal').on('click', function () {
                if (validacionPersonal()) {
                    Adminpersonal.guardaPersonal(Adminpersonal.datosPersonal(), 'GuardaPersonal');
                } else
                    swal("Faltan campos requeridos", "", "warning");
            });
        },
        infoPersonal: function (idperosnal) {
            $.get("SAdminpersonal", {
                ACCION: "infoPersonal",
                IDPERSONAL: idperosnal
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                }
            });
        },
        agregaMateria: function (idpersonal) {
            $.get("SAdminpersonal", {
                ACCION: "agregaMateria",
                ID: idpersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $('#tablaasignamateria').DataTable({
                        "scrollX": true
                    });
                    var listmateria = [];
                    $("#asignamateria").on('click', function () {
                        alert(tipoescuela);
                        $("#check input[type='checkbox']:checked").each(function () {
                            var materia = {
                                "idtbmateriapersonal": $(this).val(),
                                "r_periodo": $("#periodomateria").val()
                            };
                            listmateria.push(materia);
                        });
                        console.log(listmateria);
                        $.get("SAdminpersonal", {
                            ACCION: "asignaMateria",
                            PRUEBA: JSON.stringify(listmateria),
                            TIPOESCUELA: tipoescuela,
                            ID: idpersonal
                        }).done(function (xhr, status, error) {
                            if (error.status != 200)
                                swal(error.getResponseHeader("ERROR"), "", "warning");
                            else {
                                listmateria = [];
                                swal("Hecho!", "Materias asignadas al maestro", "success");
                            }
                        });
                    });
                    $("#asignaralumnos").on('click', function () {
                        $.get("SAdminpersonal", {
                            ACCION: "agregaAlumnos",
                            ID: idpersonal,
                            TIPOESCUELA: tipoescuela
                        }).done(function (xhr, status, error) {
                            if (error.status != 200)
                                swal(error.getResponseHeader("ERROR"), "", "warning");
                            else {
                                $("#content").html(arguments[0]);
                                grado = $("#gradoAlumPer").val();
                                grupo = $("#grupoAlumPer").val();
                                materiapersonal = $("#materiaAlumPer").val();
                                Adminpersonal.getAsignaAlumno(grado, grupo, idpersonal, materiapersonal);
                                var alumnos = [];
                                $("body").on("click", "#alumnoasigna", function (event) {
                                    alert("entra");
                                    $("#check2 input[type='checkbox']:checked").each(function () {
                                        var alumno = {
                                            "r_alumno": $(this).val(),
                                            "r_materiapersonal": $("#materiaAlumPer").val()
                                        };
                                        alumnos.push(alumno);
                                    });
                                    console.log(alumnos);
                                    $.get("SAdminpersonal", {
                                        ACCION: "asignaAlumnos",
                                        OBJETO: JSON.stringify(alumnos),
                                        TIPOESCUELA: tipoescuela

                                    }).done(function (xhr, status, error) {
                                        if (error.status != 200)
                                            swal(error.getResponseHeader("ERROR"), "", "warning");
                                        else {
                                            swal("Hecho!", "Alumnos asignados al maestro", "success");
                                        }
                                    });
                                });
                            }
                        })
                    });
                }
            })
        },
        listaAlumnos: function (idpersonal) {
            alert(idpersonal);
            $.get("SAdminpersonal", {
                ACCION: "listaAlumnos",
                ID: idpersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    grado = $("#gradoLista").val();
                    grupo = $("#grupoLista").val();
                    materiapersonal = $("#materiaLista").val();
                    Adminpersonal.getListaAlumno(grado, grupo, materiapersonal,idpersonal);
                }
            });
        },
        getListaAlumno: function (grado, grupo, materiapersonal,idpersonal) {
            $.get("SAdminpersonal", {
                ACCION: "getListaAlumno",
                GRADO: grado,
                GRUPO: grupo,
                ID: idpersonal,
                IDM: materiapersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#ListaAlumnos").html(arguments[0]);
                    $('#tablalistaalumnos').DataTable({
                        "scrollX": true
                    });
                    $("#gradoLista").change(function () {
                        grado = $("#gradoLista").val()
                        Adminpersonal.getListaAlumno(grado, grupo, materiapersonal,idpersonal);
                    });
                    $("#grupoLista").change(function () {
                        grupo = $("#grupoLista").val();
                        Adminpersonal.getListaAlumno(grado, grupo, materiapersonal,idpersonal);
                    });
                    $("#materiaLista").change(function () {
                        materiapersonal = $("#materiaLista").val();
                        Adminpersonal.getListaAlumno(grado, grupo, materiapersonal,idpersonal);
                    });
                    $("#asistencia").on('click', function () {
                        var listasistencia = [];
                        var check = 0;
                        $("#check3 input[type='checkbox']").each(function () {
                            if ($(this).is(':checked')) {
                                check = 1;
                            } else {
                                check = 0;
                            }
                            var asistencia = {
                                "r_materiaalumno": $(this).val(),
                                "r_dia": $("#diaasistencia").val(),
                                "r_semana": $("#semanafiscalasistencia").val(),
                                "r_periodo": $("#periodoasistencia").val(),
                                "asistencia": check

                            };
                            listasistencia.push(asistencia);
                        });
                        console.log(listasistencia);
                        $.get("SAdminpersonal", {
                            ACCION: "guardaAsistencia",
                            OBJETO: JSON.stringify(listasistencia),
                            TIPOESCUELA: tipoescuela
                        }).done(function (xhr, status, error) {
                            if (error.status != 200)
                                swal(error.getResponseHeader("ERROR"), "", "warning");
                            else {
                                swal("Hecho!", "Asistencia del dia guardada", "success");
                            }
                        });
                    });
                }
            });
        },
        getAsignaAlumno: function (grado, grupo, idpersonal, materiapersonal) {
            $.get("SAdminpersonal", {
                ACCION: "getAsignaAlumno",
                GRADO: grado,
                GRUPO: grupo,
                ID: idpersonal,
                IDM: materiapersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#asignaAlumno").html(arguments[0]);
                    $("#gradoAlumPer").change(function () {
                        grado = $("#gradoAlumPer").val()
                        Adminpersonal.getAsignaAlumno(grado, grupo, idpersonal, materiapersonal);
                    });
                    $("#grupoAlumPer").change(function () {
                        grupo = $("#grupoAlumPer").val();
                        Adminpersonal.getAsignaAlumno(grado, grupo, idpersonal, materiapersonal);
                    });
                    $("#materiaAlumPer").change(function () {
                        materiapersonal = $("#materiaAlumPer").val();
                        Adminpersonal.getAsignaAlumno(grado, grupo, idpersonal, materiapersonal);
                    });
                }
            })
        },
        editarPersonal: function (idpersonal) {
            $.get("SAdminpersonal", {
                ACCION: "editarPersonal",
                IDPERSONAL: idpersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    Adminpersonal.formulariospe(arguments[0]);
                }
            });
        },
        eliminarPersonal: function (idpersonal) {
            $.get("SAdminpersonal", {
                ACCION: "eliminarPersonal",
                IDPERSONAL: idpersonal
            }).done(function (hxr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Adminpersonal.tablaPersonal();
            });
        },
        agregaPersonal: function () {
            $.get("SAdminpersonal", {
                ACCION: "AgregaPersonal",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Adminpersonal.formulariospe(arguments[0]);
            });
        },
        guardaPersonal: function (objeto, accion) {
            $.get("SAdminpersonal", {
                ACCION: accion,
                PERSONAL: JSON.stringify(objeto),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Adminpersonal.tablaPersonal();
                }
            });
        },
        datosPersonal: function () {
            var personal = {
                "idtbpersonal": $("#idpersonal").val(),
                "nombre": $('#nombrep').val(),
                "apellidop": $("#apellidopp").val(),
                "apellidom": $("#apellidomp").val(),
                "fechanacimiento": $("#fechanap").val(),
                "curp": $("#curpp").val(),
                "municipionac": $("#municipionacp").val(),
                "estadonac": $("#estadonacp").val(),
                "nacionalidad": $("#nacionalidadp").val(),
                "sexo": $("input[name = 'sexop']:checked").val(),
                "calledom": $("#calledomp").val(),
                "numerodom": $("#numerodomp").val(),
                "coloniadom": $("#coloniadomp").val(),
                "codigopostal": $("#codigopostalp").val(),
                "telefonocasa": $("#telcasatp").val(),
                "celular": $("#celularp").val(),
                "correo": $("#correop").val(),
                "nss": $("#nss").val(),
                "rfc": $("#rfc").val(),
                "nivelestudios": $("#nivelmax").val(),
                "licenciatura": $("#licenciatura").val(),
                "maestria": $("#maestria").val(),
                "doctorado": $("#doctorado").val(),
                "rpuesto": $("#puesto").val(),
                "status": 1,
                "tipoescuela": 1
            }
            return personal
        },
        seleccionaTodo: function () {
            for (i = 0; i < document.asignaAlumno.elements.length; i++)
                if (document.asignaAlumno.elements[i].type == "checkbox")
                    document.asignaAlumno.elements[i].checked = 1;
        },
        desmarcarTodo: function () {
            for (i = 0; i < document.asignaAlumno.elements.length; i++)
                if (document.asignaAlumno.elements[i].type == "checkbox")
                    document.asignaAlumno.elements[i].checked = 0;
        }
    }
}());
function validacionPersonal() {
    var valido = true;
    if ($("#nombrep").val().trim() == "") {
        $("#div-nombrep").addClass("has-error");
        valido = false;
    }
    if ($("#apellidopp").val().trim() == "") {
        $("#div-apellidopp").addClass("has-error");
        valido = false;
    }
    if ($("#apellidomp").val().trim() == "") {
        $("#div-apellidomp").addClass("has-error");
        valido = false;
    }
    if ($("#fechanap").val().trim() == "") {
        $("#div-fechanacimientop").addClass("has-error");
        valido = false;
    }
    if ($("#correop").val().trim() == "") {
        $("#div-correop").addClass("has-error");
        valido = false;
    }
    if ($("#nivelmax").val().trim() == "") {
        $("#div-nivelmax").addClass("has-error");
        valido = false;
    }
    return valido;
}


