

var Reportes = (function () {
    var tipoescuela = JSON.parse(sessionStorage.getItem("tipoescuela"));
    var grado;
    var grupo;
    var data;
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
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
            });
        },
        guardarD: function () {
            $('form[name="formreporteD"]').ajaxForm(function () {
                swal("Hecho!", "Datos guardados correctamente", "success");
                Reportes.reporteDisciplinar();
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
        /*       guardaReporteD: function (objeto, accion) {
         var form = $("#fileinfo")[0];
         var data = new FormData(form);
         $.ajax({
         type: 'POST',
         enctype: 'multipart/form-data',
         url: "SReportes/guardaReporteD",
         data: data,
         processData: false,
         contentType: false,
         cahce: false,
         dataType: 'json',
         success: function (e) {
         alert("succes");
         },
         error: function (e) {
         alert("error");
         }
         })
         $.get("SReportes", {
         ACCION: accion,
         REPORTE: JSON.stringify(objeto)
         }).then(function () {
         swal("Hecho!", "Datos guardados correctamente", "success");
         Reportes.reporteDisciplinar();
         });
         },*/
        getAlumno: function (grado, grupo) {
            $.get("SReportes", {
                ACCION: "alumnoGradoGrupo",
                GRADO: grado,
                GRUPO: grupo,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
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
            });
        },
        getAlumnoAca: function (gradohonor, grupohonor) {
            $.get("SReportes", {
                ACCION: "alumnoGradoGrupoAca",
                GRADOHONOR: gradohonor,
                GRUPOHONOR: grupohonor,
                TIPOESCUELA: tipoescuela
            }).then(function () {
                //  $("#alumnogradoA").html(arguments[0]);
                $("#alumnogradoAA").html(arguments[0]);
                $("#gradohonor").change(function () {
                    gradohonor = $("#gradohonor").val();
                    Reportes.getAlumnoAca(gradohonor, grupohonor)
                }),
                        $("#grupohonor").change(function () {
                    grupohonor = $("#grupohonor").val();
                    Reportes.getAlumnoAca(gradohonor, grupohonor)
                })
                /* $("#gradoatencion").change(function () {
                 gradoatencion = $("#gradoatencion").val();
                 Reportes.getAlumnoAca(gradohonor, grupohonor, gradoatencion,grupoatencion)
                 }),
                 $("#grupoatencion").change(function () {
                 grupoatencion = $("#grupoatencion").val();
                 Reportes.getAlumnoAca(gradohonor, grupohonor, gradoatencion,grupoatencion)
                 });
                 $("#agregacomp").on('click', function () {
                 Reportes.agregaComportamiento();
                 });*/
            });
        },
        getAlumnoAcaAtencion: function (gradoatencion, grupoatencion) {
            $.get("SReportes", {
                ACCION: "alumnoGradoGrupoAcaAtencion",
                GRADOATENCION: gradoatencion,
                GRUPOATENCION: grupoatencion,
                TIPOESCUELA: tipoescuela
            }).then(function () {
                $("#alumnogradoA").html(arguments[0]);
                $("#gradoatencion").change(function () {
                    gradoatencion = $("#gradoatencion").val();
                    Reportes.getAlumnoAcaAtencion(gradoatencion, grupoatencion);
                }),
                        $("#grupoatencion").change(function () {
                    grupoatencion = $("#grupoatencion").val();
                    Reportes.getAlumnoAcaAtencion(gradoatencion, grupoatencion);
                });
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {

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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
                $("#content").html(arguments[0]);
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#editarreporteD").on('click', function () {
                    Reportes.guardaeditarReporteD(Reportes.datosReporteD(), "guardaeditarReporteD")
                });
            })
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
                swal("Hecho!", "Datos actualizados correctamente", "success");
                Reportes.mostrarReportes();
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
                $("#content").html(arguments[0]);
                gradohonor = $("#gradohonor").val();
                grupohonor = $("#grupohonor").val();
                gradoatencion = $("#gradoatencion").val();
                grupoatencion = $("#grupoatencion").val();
                Reportes.getAlumnoAca(gradohonor, grupohonor);
                Reportes.getAlumnoAcaAtencion(gradoatencion, grupoatencion);
                $("#guardacomportammiento").on('click', function () {
                    Reportes.guardaComportamiento();
                });
                $("#guardasemana").on('click', function () {
                    Reportes.guardaSemana();
                });
                $("#guardasemana2").on('click', function () {
                    Reportes.guardaSemana2();
                });
                $("#guardareporteA").on('click', function () {
                    Reportes.guardarA();
                });
                $("#actividadsemanal").on('click', function () {
                    Reportes.guardaActividadSemanal();
                });
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
                "rperiodo": $("#periodoA").val()
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
                swal("Hecho!", "Datos guardados correctamente", "success");
                Reportes.imagenReporteAca();
            });
        },
        guardaActividadSemanal: function () {
            var actividad = {
                "rsemana": $("#semanafiscalactividad").val(),
                "tarea": $("#actividad").val(),
                "rdia": $("#diaactividad").val(),
                "rpersonal": $("#personalactividad").val()
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
                swal("Hecho!", "Datos guardados correctamente", "success");
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
                swal("Hecho!", "Datos guardados correctamente", "success");
                Reportes.reporteDisciplinar();
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
                swal("Hecho!", "Datos guardados correctamente", "success");
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
            }).fail(function (xhr, status, error) {
                console.log(
                        window.location.pathname);
            }).then(function () {
                swal("Hecho!", "Datos guardados correctamente", "success");
            });
        },
        imagenReporteAca: function () {
            $.get("SReportes", {
                ACCION: "imagenReporteAca"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#guardarimagen").on('click', function () {
                    html2canvas([document.getElementById('imagen')], {
                        onrendered: function (canvas) {
                            var data = canvas.toDataURL('image/png');
                            var image = new Image();
                            image.src = data;
                            console.log(data);
                            console.log(image);
                            $("#blanko").attr('href', canvas.toDataURL("image/png"));
                             $("#blanko").attr('download', "Image.png")
                             $("#blanko")[0].click();
                        }
                    });
                   /* var file = dataURLtoFile(data, 'image.png');
                    console.log(file);*/

                    /* html2canvas($(".imagen"), {
                     dpi: 192,
                     onrendered: function (canvas) {
                     $("#blanko").attr('href', canvas.toDataURL("image/png"));
                     $("#blanko").attr('download', "Image.png")
                     $("#blanko")[0].click();
                     }
                     })*/
                })

            });
        },
        
    }
    ;
}());
function dataURLtoFile(dataurl, filename) {
 
        var arr = dataurl.split(','),
            mime = arr[0].match(/:(.*?);/)[1],
            bstr = atob(arr[1]), 
            n = bstr.length, 
            u8arr = new Uint8Array(n);
            
        while(n--){
            u8arr[n] = bstr.charCodeAt(n);
        }
        
        return new File([u8arr], filename, {type:mime});
    }