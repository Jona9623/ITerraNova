

var Reportes = (function () {
    /*Variable para saber si estamos en secundaria o preparatoria*/
    var tipoescuela = JSON.parse(sessionStorage.getItem("tipoescuela"));
    var grado;
    var grupo;
    return {
        /*Muestra el formulario de reporte disciplinar*/
        reporteDisciplinar: function () {

            $.get("SReportes", {
                ACCION: "Rdisciplinar",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    grado = $("#gradodisciplinar").val();
                    grupo = $("#grupodisciplinar").val();
                    Reportes.getAlumno(grado, grupo);
                    //$("#gradodisciplinar").change(function () {
                    $("body").find("select[id='gradodisciplinar']").unbind('change').bind('change', function () {
                        grado = $("#gradodisciplinar").val()
                        Reportes.getAlumno(grado, grupo);
                    });
                    //$("#grupodisciplinar").change(function () {
                    $("body").find("select[id='grupodisciplinar']").unbind('change').bind('change', function () {
                        grupo = $("#grupodisciplinar").val();
                        Reportes.getAlumno(grado, grupo);
                    });
                    /*Para agregar una falta del alumno dentro de la vista del formulario*/
                    $("#agregafalta").on('click', function () {
                        Reportes.agregaFalta();
                    });
                    /*Aqui guarda la falta (lo sé tiene nombre diferente :v)*/
                    $("#guardaincidente").on('click', function () {
                        Reportes.guardaIncidente(Reportes.datosIncidente(), "guardaIncidente");
                    });
                    Reportes.guardarD();
                    /*Existen un boton para mostrar todos lso reportes guardados*/
                    $("#mostrarreportes").on('click', function () {
                        Reportes.mostrarReportes();
                    });
                    /*Aqui se hace lo mismo para poder guardar la foto, se asignamos valor vacio al inpuot de la foto*/
                    $("#foto").click(function () {
                        $(this).val("");
                    });
                    /*Se convierte a base64 la imagen para poder guardarla*/
                    $("#foto").change(function () {
                        convertBase64();
                    });
                }
            });
        },
        /*Como tenemos que guardar una imagen utilizo el formulario por ajax para poder tratar la imagen y guardar los elementos del formulario*/
        guardarD: function () {
            $('form[name="formreporteD"]').ajaxForm(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Reportes.reporteDisciplinar();
                }
            });
        },
        /*Aqui son funciones que crean objetos tomando valores de los inputs de los distintos formulario*/
        datosIncidente: function () {
            var incidente = {
                "nombre": $("#incidenteD").val()
            }
            return incidente;
        },
        datosReporteD: function () {
            var reporteD = {
                "idtbreporte": $("#idtbreporteD").val(),
                "rperiodo": $("#periodoD").val(),
                "ralumno": $("#alumnodisciplinar").val(),
                "rpersonal": $("#personalmateria").val(),
                "hora": $("#horaincidente").val(),
                "fecha": $("#fechaincidente").val(),
                "fechareporte": $("#fechareporte").val(),
                "rpersonalsolicita": $("#personalsolicita").val(),
                "rpersonalllena": $("#personalllena").val(),
                "rmateria": $("#materiaD").val(),
                "lugar": $("#lugarincidente").val(),
                "rtipoincidente": $("#incidente").val(),
                "nivel": $("#nivel").val(),
                "descripcion": $("#descripcion").val(),
                "foto": $("#foto").val(),
                "status": 1,
                "tipoescuela": 1
            }
            return reporteD;
        },
        /*Funcion qu esirve para mostrar en un select los alumnos correspondientes a los parametros para reporte disciplinar*/
        getAlumno: function (grado, grupo) {
            $.get("SReportes", {
                ACCION: "alumnoGradoGrupo",
                GRADO: grado,
                GRUPO: grupo,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#alumnogradoD").html(arguments[0]);
                }
            });
        },
        /*Funcion que sirve para msotrar en un select alumnos correspondientes para cuadro de honor en reporte academico*/
        getAlumnoAca: function (gradohonor, grupohonor) {
            $.get("SReportes", {
                ACCION: "alumnoGradoGrupoAca",
                GRADOHONOR: gradohonor,
                GRUPOHONOR: grupohonor,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#alumnogradoAA").html(arguments[0]);
                }
            });
        },
        /*Funcion que sirve para msotrar en un select alumnos correspondientes para cuadro de atencion en reporte academico*/
        getAlumnoAcaAtencion: function (gradoatencion, grupoatencion) {
            
            $.get("SReportes", {
                ACCION: "alumnoGradoGrupoAcaAtencion",
                GRADOATENCION: gradoatencion,
                GRUPOATENCION: grupoatencion,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#alumnogradoA").html(arguments[0]);
                }
            });
        },
        /*Aqui solo se guarda el incidente al preisonar el boton de agregar falta*/
        guardaIncidente: function (objeto, accion) {
            $.get("SReportes", {
                ACCION: accion,
                OBJETO: JSON.stringify(objeto),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos actualizados correctamente", "success");
                    Reportes.reporteDisciplinar();

                }
            });
        },
        /*Se muestra la tabla con todos los reportes al presionar el boton de mostrar reportes*/
        mostrarReportes: function () {
            $.get("SReportes", {
                ACCION: "mostrarReportes",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    /*Vamos a mostrar los alumnos con reporte pero debemos tomar ciertos parametros para mostraar informacion mas concisa*/
                    $("#content").html(arguments[0]);
                    $("#tablareporteD").DataTable({
                        "scrollX": true
                    });
                    /*Esta funcion sirve para la interaccion entre la tabla mostrada y los registros mostrado sean acorde al periodo seleccionado, se podia usar esto mas veces 
                     * pero se notará que en siguientes funciones que aplican la misma idea se usa el jspincllude (se peude ver esto en los archivos jsp)*/
                    var id = $('#consultareporteD').find('#periodotablaD').val();
                    $('#consultareporteD').find('tr[name^="alumno-"]').hide();
                    $('#consultareporteD').find('tr[name="alumno-' + id + '"]').show();
                    $('#consultareporteD').find('#periodotablaD').change(function () {
                        $('#consultareporteD').find('tr[name^="alumno-"]').hide();
                        $('#consultareporteD').find('tr[name="alumno-' + $(this).val() + '"]').show();
                    });
                    //$(".inforeporteD").on('click', function () {
                        $("body").find('a.inforeporteD').unbind('click').bind('click', function () {
                        alumnoreporte = $(this).parents("tr").find("td").eq(0).html();
                        reportefecha = $(this).parents("tr").find("td").eq(1).html();
                        hora = $(this).parents("tr").find("td").eq(3).html();
                        Reportes.infoReporteD(alumnoreporte, reportefecha, hora);
                    });
                    //$(".editarreporteD").on('click', function () {
                          $("body").find('a.editarreporteD').unbind('click').bind('click', function () {
                        editaralumnoreporte = $(this).parents("tr").find("td").eq(0).html();
                        editarreportefecha = $(this).parents("tr").find("td").eq(1).html();
                        editarhora = $(this).parents("tr").find("td").eq(3).html();
                        Reportes.editarReporteD(editaralumnoreporte, editarreportefecha, editarhora);
                    });
                    //$(".eliminarreporteD").on('click', function () {
                          $("body").find('a.eliminarreporteD').unbind('click').bind('click', function () {
                        eliminarreporteD = $(this).parents("tr").find("td").eq(4).html();
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
                            Reportes.eliminarReporteD(eliminarreporteD);
                        });
                    });
                }
            });
        },
        /*Se elimina el reporte disciplinar seleccionado*/
        eliminarReporteD: function (id) {
            $.get("SReportes", {
                ACCION: "eliminarReporteD",
                ID: id
            }).done(function (hxr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Reportes.mostrarReportes();
            });
        },
        /*Se muestra la info del reporte seleccionado de acuerdo a los parametros*/
        infoReporteD: function (alumnoreporte, reportefecha, hora) {
            $.get("SReportes", {
                ACCION: "infoReporteD",
                ID: alumnoreporte,
                FECHA: reportefecha,
                HORA: hora,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                }
            });
        },
        /*Se muestran formulario para editar elementos del reporte*/
        editarReporteD: function (id, fecha, hora) {
            $.get("SReportes", {
                ACCION: "editarReporteD",
                ID: id,
                FECHA: fecha,
                HORA: hora,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#editarreporteD").on('click', function () {
                        Reportes.guardaeditarReporteD(Reportes.datosReporteD(), "guardaeditarReporteD")
                    });
                }
            });
        },
        /*Para guardar la info editada en el reprote disciplinar*/
        guardaeditarReporteD: function (objeto, accion) {
            $.get("SReportes", {
                ACCION: accion,
                OBJETO: JSON.stringify(objeto),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos actualizados correctamente", "success");
                    Reportes.mostrarReportes();
                }
            });
        },
        /*Se meustra el formulario del reporte academico*/
        reporteAcademico: function () {
            $.get("SReportes", {
                ACCION: "Racademico",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    //$("#honor").hide();
                    $("#mostrarreportesaca").on('click', function () {
                        Reportes.mostrarReportesAcademicos();
                    });
                    $("#mostraractividades").on('click', function () {
                        Reportes.mostrarActividades();
                    });
                    /*$("#guardarhonor").on('click', function () {
                     Reportes.guardarHonor();
                     });
                     $("#guardaratencion").on('click', function () {
                     Reportes.guardarAtencion();
                     })*/
                   /* gradohonor = $("#gradohonor").val();
                    grupohonor = $("#grupohonor").val();
                    gradoatencion = $("#gradoatencion").val();
                    grupoatencion = $("#grupoatencion").val();
                    console.log(gradohonor);
                    console.log(grupohonor);
                    console.log(gradoatencion);
                    console.log(grupoatencion);*/
                    Reportes.getAlumnoAca(1, 1);
                    Reportes.getAlumnoAcaAtencion(1, 1);
                    //$("#gradohonor").change(function () {
                        $("body").find("select[id='gradohonor']").unbind('change').bind('change', function () {
                        gradohonor = $("#gradohonor").val();
                        Reportes.getAlumnoAca(gradohonor, grupohonor)
                    });
                    //$("#grupohonor").change(function () {
                        $("body").find("select[id='grupohonor']").unbind('change').bind('change', function () {
                        grupohonor = $("#grupohonor").val();
                        Reportes.getAlumnoAca(gradohonor, grupohonor)
                    });
                    //$("#gradoatencion").change(function () {
                        $("body").find("select[id='gradoatencion']").unbind('change').bind('change', function () {
                        gradoatencion = $("#gradoatencion").val();
                        Reportes.getAlumnoAcaAtencion(gradoatencion, grupoatencion);
                    });
                    //s$("#grupoatencion").change(function () {
                        $("body").find("select[id='grupoatencion']").unbind('change').bind('change', function () {
                        grupoatencion = $("#grupoatencion").val();
                        Reportes.getAlumnoAcaAtencion(gradoatencion, grupoatencion);
                    });
                    $("#guardacomportammiento").on('click', function () {
                        Reportes.guardaComportamiento();
                    });
                    $("#guardareporteA").on('click', function () {
                        Reportes.guardarA();
                    });
                    $("#actividadsemanal").on('click', function () {
                        if (validacionActividadSemanal())
                            Reportes.guardaActividadSemanal();
                        else
                            swal("Faltan campos requeridos", "", "warning");
                    });
                }
            });
        },
        /*Creamos objeto para guardar alumno a cuadro de honor*/
        guardarHonor: function () {
            var honor = {
                "rpersonal": $("#personalreporteA").val(),
                "rmateria": $("#materiaA").val(),
                "rsemana": $("#semanafiscal").val(),
                "ralumnohonor": $("#alumnohonor").val(),
                "rperiodo": $("#periodoA").val()
            }
            console.log(honor);
            $.get("SReportes", {
                ACCION: "guardarHonor",
                OBJETO: JSON.stringify(honor),
                TIPOESCUELA: tipoescuela
            }).done(function (hxr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    $("#honor").show();
                gradoatencion = $("#gradoatencion").val();
                grupoatencion = $("#grupoatencion").val();
                //alert(gradoatencion);
                Reportes.getAlumnoAcaAtencion(gradoatencion, grupoatencion);
            });
        },
        /*Creamo objeto para guardar alumno atencion*/
        guardarAtencion: function () {
            var atencion = {
                "ralumnoatencion": $("#alumnoatencion").val(),
                "ratencion": $("#comportamiento").val()
            }
            $.get("SReportes", {
                ACCION: "guardarAtencion",
                OBJETO: JSON.stringify(atencion)
            }).don(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Reportes.imagenReporteAca();
                }
            });
        },
        /*Se muestra la tabla con los reportes academicos de manera similar a reporte disciplinar*/
        mostrarReportesAcademicos: function () {
            $.get("SReportes", {
                ACCION: "mostrarReportesAca",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#tablareporteA").DataTable({
                        "scrollX": true
                    });
                    /*Usamos el mismo metodo para interactuar con los reportes mostrados en funcion del periodo seleccionado*/
                    var id = $('#consultareporteA').find('#periodotablaA').val();
                    $('#consultareporteA').find('tr[name^="alumno-"]').hide();
                    $('#consultareporteA').find('tr[name="alumno-' + id + '"]').show();
                    $('#consultareporteA').find('#periodotablaA').change(function () {
                        $('#consultareporteA').find('tr[name^="alumno-"]').hide();
                        $('#consultareporteA').find('tr[name="alumno-' + $(this).val() + '"]').show();
                    });
                    $(".eliminarreporteA").on('click', function () {
                        eliminarreporteA = $(this).parents("tr").find("td").eq(0).html();
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
                            Reportes.eliminarReporteA(eliminarreporteA);
                        });
                    });
                }

            });
        },
        /*Se elimina el reporte seleccionado*/
        eliminarReporteA: function (id) {
            $.get("SReportes", {
                ACCION: "eliminarReporteA",
                ID: id,
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Reportes.mostrarReportesAcademicos();
            });
        },
        /*Se muestran las actividades semanales al presioanr el boton*/
        mostrarActividades: function () {
            $.get("SReportes", {
                ACCION: "mostrarActividades",
                TIPOESCUELA: tipoescuela
            }).done(function (hxr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#tablaActividades").DataTable({
                        "scrollX": true
                    });
                    /*Usamos el mismo metodo para interactuar con las actividades mostradas en funcion del periodo seleccionado*/
                    var id = $('#consultareporteAc').find('#periodotablaAc').val();
                    $('#consultareporteAc').find('tr[name^="alumno-"]').hide();
                    $('#consultareporteAc').find('tr[name="alumno-' + id + '"]').show();
                    $('#consultareporteAc').find('#periodotablaAc').change(function () {
                        $('#consultareporteAc').find('tr[name^="alumno-"]').hide();
                        $('#consultareporteAc').find('tr[name="alumno-' + $(this).val() + '"]').show();
                    });
                    $(".eliminartarea").on('click', function () {
                        eliminartarea = $(this).parents("tr").find("td").eq(0).html();
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
                            Reportes.eliminarTarea(eliminartarea);
                        });
                    })
                }
            });
        },
        /*Se elimina la actividad seleccionada*/
        eliminarTarea: function (id) {
            $.get("SReportes", {
                ACCION: "eliminarTarea",
                ID: id
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Reportes.mostrarActividades();
            });
        },
        /*Se crea el objeto para guardar la info de los inputs del reporte academico y actividad semanal*/
        guardarA: function () {
            var reporteA = {
                //"rpersonal": $("#personalreporteA").val(),
                "rmateria": $("#materiaA").val(),
                "rsemana": $("#semanafiscal").val(),
                "ralumnohonor": $("#alumnohonor").val(),
                "ralumnoatencion": $("#alumnoatencion").val(),
                "ratencion": $("#comportamiento").val(),
                "rperiodo": $("#periodoA").val(),
            }
            $.get("SReportes", {
                ACCION: "guardaReporteAcademico",
                OBJETO: JSON.stringify(reporteA),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Reportes.imagenReporteAca();
                }
            });
        },
        guardaActividadSemanal: function () {
            var actividad = {
                "rperiodo": $("#periodoactividad").val(),
                "rsemana": $("#semanafiscalactividad").val(),
                "tarea": $("#actividad").val(),
                "rdia": $("#diaactividad").val(),
                "rpersonal": $("#personalactividad").val(),
                //aqui poner r_materiapersonal
                "fechaentrega": $("#fechaentrega").val(),
                "nombreimagen": $("#nombrearchivoa").val()
            }
            $.get("SReportes", {
                ACCION: "guardaActividadSemanal",
                OBJETO: JSON.stringify(actividad),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Reportes.imagenReporteActividad();
                }
            });
        },
        /*Se guarda un comportaamiento para el alumno en caso de que se agrege*/
        guardaComportamiento: function () {
            var comportamiento = {"nombre": $("#comport").val()}
            $.get("SReportes", {
                ACCION: "guardaComportamiento",
                OBJETO: JSON.stringify(comportamiento),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Reportes.reporteAcademico();
                }
            });
        },
        /*Se guarda una nueva semana en caso de solicitarse*/
        guardaSemana: function () {
            var semana = {"nombre": $("#semana").val()}
            $.get("SReportes", {
                ACCION: "guardaSemana",
                OBJETO: JSON.stringify(semana),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                }
            });
        },
        guardaSemana2: function () {
            var semana = {"nombre": $("#semana2").val()}
            $.get("SReportes", {
                ACCION: "guardaSemana",
                OBJETO: JSON.stringify(semana),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                }
            });
        },
        /*Se crea una imagen con los datos del alumno en cuadro de honor y atencion*/
        imagenReporteAca: function () {
            $.get("SReportes", {
                ACCION: "imagenReporteAca"
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#guardarimagen").hide();
                    html2canvas([document.getElementById('imagen')], {
                        onrendered: function (canvas) {
                            //alert("entra");
                            var data = canvas.toDataURL();
                            var image = new Image();
                            image.src = data;
                            console.log(data);
                            var x = $("#inputA").val(data);
                            // var x = document.getElementById('inputA').value(data);
                            console.log(x);
                            $("#blanko").attr('href', canvas.toDataURL("image/png"));
                            //$("#blanko").attr('download', "Image.png");
                            $("#blanko")[0].click();
                        }
                    });
                    $("#guardarimagen").show();
                    Reportes.formReporteA();
                }
            });
        },
        /*Se crea imagen con los datos de la actividad guardada*/
        imagenReporteActividad: function () {
            $.get("SReportes", {
                ACCION: "imagenReporteActividad"
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#guardarimagen").hide();
                    html2canvas([document.getElementById('imagenAc')], {
                        onrendered: function (canvas) {
                            var data = canvas.toDataURL();
                            var image = new Image();
                            image.src = data;
                            console.log(data);
                            var x = $("#inputB").val(data);
                            // var x = document.getElementById('inputA').value(data);
                            console.log(x);
                            $("#blank").attr('href', canvas.toDataURL("image/png"));
                            //$("#blanko").attr('download', "Image.png");
                            $("#blank")[0].click();
                        }
                    });
                    $("#guardarimagen").show();
                    Reportes.formImagenActividad();
                }
            });
        },
        /*Se uasa por medio de ajax el guardado de formulario al contener una image nde por medio para ser mas facil su tratado*/
        formReporteA: function () {
            $('form[name="formaReporteA"]').ajaxForm(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    swal("Hecho!", "Imagen guardada correctamente", "success");
            });
        },
        formImagenActividad: function (xhr, status, error) {
            $('form[name="formActividad"]').ajaxForm(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    swal("Hecho!", "Imagen guardada correctamente", "success");
            });
        }

    }
    ;
}());
/*Para ocnvertir a base 64 la imagen y guardarla*/
async function convertBase64() {
    const file = document.querySelector('#foto').files[0];
    $("#savefile").val(await toBase64(file));
}
const toBase64 = file => new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
    /*validamos los campos de activida dsemanal*/
function validacionActividadSemanal() {
    var valido = true;
    if ($("#fechaentrega").val().trim() == "") {
        $("#div-fechaentrega").addClass("has-error");
        valido = false;
    }
    if ($("#actividad").val().trim() == "") {
        $("#div-actividad").addClass("has-error");
        valido = false;
    }
    return valido;
}
function dataURLtoFile(dataurl, filename) {

    var arr = dataurl.split(','),
            mime = arr[0].match(/:(.*?);/)[1],
            bstr = atob(arr[1]),
            n = bstr.length,
            u8arr = new Uint8Array(n);

    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }

    return new File([u8arr], filename, {type: mime});
}