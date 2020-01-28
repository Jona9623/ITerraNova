

var Reportes = (function () {
    var grado;
    var grupo;
    return {
        reporteDisciplinar: function () {
            $.get("SReportes", {
                ACCION: "Rdisciplinar"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#gradodisciplinar").change(function () {
                    grado = $("#gradodisciplinar").val()
                }),
                        $("#grupodisciplinar").change(function () {
                    grupo = $("#grupodisciplinar").val()
                    Reportes.getAlumno(grado, grupo);
                });

                $("#agregafalta").on('click', function () {
                    Reportes.agregaFalta();
                });
                $("#guardaincidente").on('click', function () {
                    Reportes.guardaIncidente(Reportes.datosIncidente(), "guardaIncidente");
                });
                $("#guardareporteD").on('click', function () {
                    Reportes.guardaReporteD(Reportes.datosReporteD(), "guardaReporteD");
                });
                $("#mostrarreportes").on('click',function(){
                   Reportes.mostrarReportes(); 
                });
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
        guardaReporteD: function(objeto,accion){
            $.get("SReportes",{
                ACCION: accion,
                REPORTE: JSON.stringify(objeto)
            }).then(function(){
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
                }),
                        $("#grupodisciplinar").change(function () {
                    grupo = $("#grupodisciplinar").val()
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
        mostrarReportes: function(){
          $.get("SReportes",{
              ACCION: "mostrarReportes"
          }).then(function(){
             $("#content").html(arguments[0]);
             $("#tablareporteD").DataTable();
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