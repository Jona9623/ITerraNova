

var Reportes = (function () {
    var grado;
    var grupo;
    return {
        reporteDisciplinar: function () {
            $.get("SReportes", {
                ACCION: "Rdisciplinar"
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
                  //$("#guardareporteD").on('click', function () {
                  //    Reportes.guardaReporteD(Reportes.datosReporteD(), "guardaReporteD");
                //  });
                $("#mostrarreportes").on('click', function () {
                    Reportes.mostrarReportes();
                });
            });
        },
        guararD: function(){
          $('form[name="fileinfo"]').ajaxForm(function(){
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
        guardaReporteD: function (objeto, accion) {
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
        },
        getAlumno: function (grado, grupo) {
            $.get("SReportes", {
                ACCION: "alumnoGradoGrupo",
                GRADO: grado,
                GRUPO: grupo
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
        }
        ,
        guardaIncidente: function (objeto, accion) {
            $.get("SReportes", {
                ACCION: accion,
                OBJETO: JSON.stringify(objeto)
            }).then(function () {
                swal("Hecho!", "Datos guardados correctamente", "success");
                Reportes.reporteDisciplinar();
            });
        },
        mostrarReportes: function () {
            $.get("SReportes", {
                ACCION: "mostrarReportes"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#tablareporteD").DataTable();
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
                    reporteperiodo = $(this).parents("tr").find("td").eq(2).html();
                    Reportes.infoReporteD(alumnoreporte, reportefecha, reporteperiodo);
                });
            });
        },
        infoReporteD: function (alumnoreporte, reportefecha, reporteperiodo) {
            $.get("SReportes", {
                ACCION: "infoReporteD",
                ID: alumnoreporte,
                FECHA: reportefecha,
                PERIODO: reporteperiodo
            }).then(function () {
                $("#content").html(arguments[0]);
            });
        },
        reporteAcademico: function () {
            $.get("SReportes", {
                ACCION: "Racademico"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#agregacomp").on('click', function () {
                    Reportes.agregaComportamiento();
                })
            });
        }
        ,
    }
    ;
}());