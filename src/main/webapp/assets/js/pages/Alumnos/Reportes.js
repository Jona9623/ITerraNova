

var Reportes = (function () {
    var tipoescuela = JSON.parse(sessionStorage.getItem("tipoescuela"));
    var grado;
    var grupo;
    return {
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
                    $("#agregafalta").on('click', function () {

                        Reportes.agregaFalta();
                    });
                    $("#guardaincidente").on('click', function () {
                        Reportes.guardaIncidente(Reportes.datosIncidente(), "guardaIncidente");
                    });
                    Reportes.guardarD();
                    $("#mostrarreportes").on('click', function () {
                        Reportes.mostrarReportes();
                    });
                    $("#foto").click(function () {
                        $(this).val("");
                    });

                    $("#foto").change(function () {
                        convertBase64();
                    });
                }
            });
        },
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
                    $("#gradodisciplinar").change(function () {
                        grado = $("#gradodisciplinar").val()
                        Reportes.getAlumno(grado, grupo)
                    }),
                            $("#grupodisciplinar").change(function () {
                        grupo = $("#grupodisciplinar").val();
                        Reportes.getAlumno(grado, grupo);
                    });
                    $("#agregafalta").on('click', function () {
                        Reportes.agregaFalta();
                    });
                }
            });
        },
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
                    $("#gradohonor").change(function () {
                        gradohonor = $("#gradohonor").val();
                        Reportes.getAlumnoAca(gradohonor, grupohonor)
                    }),
                            $("#grupohonor").change(function () {
                        grupohonor = $("#grupohonor").val();
                        Reportes.getAlumnoAca(gradohonor, grupohonor)
                    })
                }
            });
        },
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
                    $("#gradoatencion").change(function () {
                        gradoatencion = $("#gradoatencion").val();
                        Reportes.getAlumnoAcaAtencion(gradoatencion, grupoatencion);
                    }),
                            $("#grupoatencion").change(function () {
                        grupoatencion = $("#grupoatencion").val();
                        Reportes.getAlumnoAcaAtencion(gradoatencion, grupoatencion);
                    });
                }
            });
        },
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
                }
            });
        },
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
                    $("#content").html(arguments[0]);
                    $("#tablareporteD").DataTable({
                        "scrollX": true
                    });
                    var id = $('#consultareporteD').find('#periodotablaD').val();
                    $('#consultareporteD').find('tr[name^="alumno-"]').hide();
                    $('#consultareporteD').find('tr[name="alumno-' + id + '"]').show();
                    $('#consultareporteD').find('#periodotablaD').change(function () {
                        $('#consultareporteD').find('tr[name^="alumno-"]').hide();
                        $('#consultareporteD').find('tr[name="alumno-' + $(this).val() + '"]').show();
                    });
                    $(".inforeporteD").on('click', function () {
                        alumnoreporte = $(this).parents("tr").find("td").eq(0).html();
                        reportefecha = $(this).parents("tr").find("td").eq(1).html();
                        hora = $(this).parents("tr").find("td").eq(3).html();
                        Reportes.infoReporteD(alumnoreporte, reportefecha, hora);
                    });
                    $(".editarreporteD").on('click', function () {
                        editaralumnoreporte = $(this).parents("tr").find("td").eq(0).html();
                        editarreportefecha = $(this).parents("tr").find("td").eq(1).html();
                        editarhora = $(this).parents("tr").find("td").eq(3).html();
                        Reportes.editarReporteD(editaralumnoreporte, editarreportefecha, editarhora);
                    });
                    $(".eliminarreporteD").on('click', function () {
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
                    gradohonor = $("#gradohonor").val();
                    grupohonor = $("#grupohonor").val();
                    gradoatencion = $("#gradoatencion").val();
                    grupoatencion = $("#grupoatencion").val();
                    Reportes.getAlumnoAca(gradohonor, grupohonor);
                    Reportes.getAlumnoAcaAtencion(gradoatencion, grupoatencion);
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
        }, eliminarTarea: function (id) {
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
        guardarA: function () {
            var reporteA = {
                "rpersonal": $("#personalreporteA").val(),
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
                    Reportes.reporteAcademico();
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
async function convertBase64(){
    const file = document.querySelector('#foto').files[0];
    $("#savefile").val(await toBase64(file));
}
const toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
});
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