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
                    Adminpersonal.importarPersonal();
                    //$('#btnaregarP').on('click', function () {
                    //s$("body").on("click", "#btnaregarP", function (event) {
                    $("body").find("button[id='btnaregarP']").unbind('click').bind('click', function () {
                        Adminpersonal.agregaPersonal();
                    });
                    //$(".infopersonal").on('click', function () {
                    //$("body").on("click", ".infopersonal", function (event) {
                    $("body").find('a.infopersonal').unbind('click').bind('click', function () {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.infoPersonal(idpersonal);
                    });
                    //$(".agregamateria").on('click', function () {
                    $("body").find('a.agregamateria').unbind('click').bind('click', function () {
                        //alert("entra");
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.agregaMateria(idpersonal);
                    });
                    //$("body").on("click", ".asignaralumnos", function (event) {
                    $("body").find('a.asignaralumnos').unbind('click').bind('click', function () {
                        //alert("entra");
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.asignarAlumnos(idpersonal);
                    });
                    //$(".listaalumnos").on('click', function () {
                    //$("body").on("click", ".listaalumnos", function (event) {
                    $("body").find('a.listaalumnos').unbind('click').bind('click', function () {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.listaAlumnos(idpersonal);
                    });
                    $("body").find('a.reporteasistencia').unbind('click').bind('click', function () {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.reporteAsistencia(idpersonal);
                    })
                    //$('.editarpe').on('click', function () {
                    //$("body").on("click", ".editarpe", function (event) {
                    $("body").find('a.editarpe').unbind('click').bind('click', function () {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.editarPersonal(idpersonal);
                    });
                    //$(".aliminarpe").on('click', function () {
                    //$("body").on("click", ".eliminarpe", function (event) {
                    $("body").find('a.eliminarpe').unbind('click').bind('click', function () {
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
                    var tablapersonal = $('#tablapersonal');
                    tablapersonal.DataTable({
                        //paging: false,
                        "scrollX": true
                    });
                    tablapersonal.rows().nodes().to$();
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
                        paging: false,
                        "scrollX": true
                    });

                    //$("#asignamateria").on('click', function () {
                    $("body").find("button[id='asignamateria']").unbind('click').bind('click', function () {
                        var listmateria = [];
                        //alert(tipoescuela);
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
                                swal("Hecho!", "Materias asignadas al maestro", "success");
                                listmateria = [];
                                Adminpersonal.tablaPersonal();
                            }
                        });
                    });
                }
            })
        },
        asignarAlumnos: function (idpersonal) {
            $.get("SAdminpersonal", {
                ACCION: "agregaAlumnos",
                ID: idpersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    grado = $('option:selected').attr('grado');
                    grupo = $('option:selected').attr('grupo');
                    area = $('option:selected').attr('area');
                    cpt = $('option:selected').attr('cpt');
                    materiapersonal = $("#materiaAlumPer").val();
                    Adminpersonal.getAsignaAlumno(grado, grupo, area, cpt, idpersonal, materiapersonal);
                    //$("#materiaAlumPer").change(function () {
                    $("body").find("select[id='materiaAlumPer']").unbind('change').bind('change', function () {
                        grado = $('option:selected').attr('grado');
                        grupo = $('option:selected').attr('grupo');
                        area = $('option:selected').attr('area');
                        cpt = $('option:selected').attr('cpt');
                        materiapersonal = $("#materiaAlumPer").val();
                        Adminpersonal.getAsignaAlumno(grado, grupo, area, cpt, idpersonal, materiapersonal);
                        //$("#materiaAlumPer").empty();
                    });
                    $("body").unbind('click').on("click", "#asignaalumnos", function (event) {
                        //$("body").find("button[id='asignaalumnos']").unbind('click').bind('click', function () {
                        let alumnos = [];
                        $("#check2 input[type='checkbox']:checked").each(function () {
                            let alumno = {
                                "r_alumno": $(this).val(),
                                "r_materiapersonal": $("#materiaAlumPer").val()
                            };
                            alumnos.push(alumno);
                            alumno = null;
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
                                alumnos = [];
                                Adminpersonal.asignarAlumnos(idpersonal);
                            }
                        });
                    });
                }
            });
        },
        listaAlumnos: function (idpersonal) {
            //alert(idpersonal);
            console.log(idpersonal);
            $.get("SAdminpersonal", {
                ACCION: "listaAlumnos",
                ID: idpersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    grado = $('option:selected').attr('grado');
                    grupo = $('option:selected').attr('grupo');
                    area = $('option:selected').attr('area');
                    cpt = $('option:selected').attr('cpt');
                    materiapersonal = $("#materiaLista").val();
                    Adminpersonal.getListaAlumno(grado, grupo, area, cpt, materiapersonal, idpersonal);
                    //$("#materiaLista").change(function () {
                    $("body").find("select[id='materiaLista']").unbind('change').bind('change', function () {
                        grado = $('option:selected').attr('grado');
                        grupo = $('option:selected').attr('grupo');
                        area = $('option:selected').attr('area');
                        cpt = $('option:selected').attr('cpt');
                        materiapersonal = $("#materiaLista").val();
                        Adminpersonal.getListaAlumno(grado, grupo, area, cpt, materiapersonal, idpersonal);
                    });
                }
            });
        },
        getListaAlumno: function (grado, grupo, area, cpt, materiapersonal, idpersonal) {
            console.log(idpersonal);
            $.get("SAdminpersonal", {
                ACCION: "getListaAlumno",
                GRADO: grado,
                GRUPO: grupo,
                AREA: area,
                CPT: cpt,
                ID: idpersonal,
                IDM: materiapersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#ListaAlumnos").html(arguments[0]);
                    $('#tablalistaalumnos').DataTable({
                        paging: false,
                        "scrollX": true
                    });
                    //$("#asistencia").on('click', function () {
                    $("body").find("button[id='asistencia']").unbind('click').bind('click', function () {
                        let listasistencia = [];
                        let check = 0;
                        $("#check3 input[type='checkbox']").each(function () {
                            if ($(this).is(':checked')) {
                                check = 1;
                            } else {
                                check = 0;
                            }
                            let asistencia = {
                                "r_materiaalumno": $(this).val(),
                                "r_dia": $("#diaasistencia").val(),
                                "r_semana": $("#semanafiscalasistencia").val(),
                                "r_periodo": $("#periodoasistencia").val(),
                                "asistencia": check

                            };
                            listasistencia.push(asistencia);
                            asistencia = null;
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
                                listasistencia = [];
                            }
                        });
                    });
                }
            });
        },
        getAsignaAlumno: function (grado, grupo, area, cpt, idpersonal, materiapersonal) {
            $.get("SAdminpersonal", {
                ACCION: "getAsignaAlumno",
                GRADO: grado,
                GRUPO: grupo,
                AREA: area,
                CPT: cpt,
                ID: idpersonal,
                IDM: materiapersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#asignaAlumno").html(arguments[0]);
                    /* $("#gradoAlumPer").change(function () {
                     grado = $("#gradoAlumPer").val()
                     Adminpersonal.getAsignaAlumno(grado, grupo, idpersonal, materiapersonal);
                     });
                     $("#grupoAlumPer").change(function () {
                     grupo = $("#grupoAlumPer").val();
                     Adminpersonal.getAsignaAlumno(grado, grupo, idpersonal, materiapersonal);
                     });*/
                }
            })
        },
        reporteAsistencia: function (idpersonal) {
            $.get("SAdminpersonal", {
                ACCION: "reporteAsistencia",
                ID: idpersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    idperiodo = $("#periodoReporte").val();
                    idmateria = $("#materiaReporte").val();
                    idsemana = $("#semanaReporte").val();
                    Adminpersonal.getreporteAsistencia(idperiodo, idmateria, idsemana,idpersonal);
                    $("body").find("select[id='periodoReporte']").unbind('change').bind('change', function () {
                        idperodo = $("#periodoReporte").val();
                        Adminpersonal.getreporteAsistencia(idperiodo, idmateria, idsemana,idpersonal);
                    });
                    $("body").find("select[id='materiaReporte']").unbind('change').bind('change', function () {
                        idmateria = $("#materiaReporte").val();
                        Adminpersonal.getreporteAsistencia(idperiodo, idmateria, idsemana,idpersonal);
                    });
                    $("body").find("select[id='semanaReporte']").unbind('change').bind('change', function () {
                        idsemana = $("#semanaReporte").val();
                        Adminpersonal.getreporteAsistencia(idperiodo, idmateria, idsemana,idpersonal);
                    });
                    $("#tablaasisencia").DataTable({
                        //paging: false,
                        "scrollX": true
                    });
                }
            })
        },
        getreporteAsistencia: function(idperiodo,idmateria,idsemana,idpersonal){
            $.get("SAdminpersonal", {
                ACCION: "getreporteAsistencia",
                ID: idpersonal,
                IDP: idperiodo,
                IDM: idmateria,
                IDS: idsemana,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#ReporteAsistencia").html(arguments[0]);
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


