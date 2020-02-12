

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
        },
        getAlumnoAca: function (gradohonor, grupohonor, gradoatencion, grupoatencion) {
            
            $.get("SReportes", {
                ACCION: "alumnoGradoGrupoAca",
                GRADOHONOR: gradohonor,
                GRUPOHONOR: grupohonor,
                GRADOATENCION: gradoatencion,
                GRUPOATENCION: grupoatencion
            }).then(function () {
                $("#alumnogradoA").html(arguments[0]);
                $("#alumnogradoAA").html(arguments[0]);
                $("#gradohonor").change(function () {
                    gradohonor = $("#gradohonor").val();
                    Reportes.getAlumnoAca(gradohonor, grupohonor, gradoatencion,grupoatencion)
                }),
                $("#grupohonor").change(function () {
                    grupohonor = $("#grupohonor").val();
                    Reportes.getAlumnoAca(gradohonor, grupohonor, gradoatencion,grupoatencion)
                }),
                 $("#gradoatencion").change(function () {
                    gradoatencion = $("#gradoatencion").val();
                    Reportes.getAlumnoAca(gradohonor, grupohonor, gradoatencion,grupoatencion)
                }),
                $("#grupoatencion").change(function () {
                    grupoatencion = $("#grupoatencion").val();
                    Reportes.getAlumnoAca(gradohonor, grupohonor, gradoatencion,grupoatencion)
                });
                $("#agregacomp").on('click', function () {
                    Reportes.agregaComportamiento();
                });
            });
        },
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
                $(".editarreporteD").on('click',function(){
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
                HORA: hora
            }).then(function () {
                $("#content").html(arguments[0]);
            });
        },
        editarReporteD: function (id,fecha,hora){
            $.get("SReportes",{
                ACCION: "editarReporteD",
                ID: id,
                FECHA: fecha,
                HORA: hora
            }).then(function(){
                $("#content").html(arguments[0]);
                $("#editarreporteD").on('click',function(){
                    alert("entra");
                   Reportes.guardaeditarReporteD(Reportes.datosReporteD(),"guardaeditarReporteD") 
                });
            })
        },
        guardaeditarReporteD: function(objeto,accion){
            $.get("SReportes",{
                ACCION: accion,
                OBJETO: JSON.stringify(objeto)
            }).then(function (){
                swal("Hecho!", "Datos actualizados correctamente", "success");
                Reportes.mostrarReportes();
            });
            
        },
        reporteAcademico: function () {
            $.get("SReportes", {
                ACCION: "Racademico"
            }).then(function () {
                $("#content").html(arguments[0]);
                gradohonor = $("#gradohonor").val();
                grupohonor = $("#grupohonor").val();
                gradoatencion = $("#gradoatencion").val();
                grupoatencion = $("#grupoatencion").val();
                Reportes.getAlumnoAca(gradohonor, grupohonor,gradoatencion,grupoatencion);
                $("#agregacomp").on('click', function () {
                    Reportes.agregaComportamiento();
                })
            });
        }
        ,
    }
    ;
}());