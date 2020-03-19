/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Admin = (function () {
    var tipoescuela = JSON.parse(sessionStorage.getItem("tipoescuela"));
    return {
        tablaPuesto: function () {
            $.get("SAdministrador", {
                ACCION: "tablaPuesto",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#guardapuesto").on('click', function () {
                        Admin.guardaPuesto();
                    });
                    $(".editarpuesto").on('click', function () {
                        idpuesto = $(this).parents("tr").find("td").eq(0).html();
                        Admin.editarPuesto(idpuesto);
                    });
                    $(".eliminarpuesto").on('click', function () {
                        idpuesto = $(this).parents("tr").find("td").eq(0).html();
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
                            Admin.eliminarPuesto(idpuesto);
                        });
                    });
                    $("#tablapuesto").DataTable({
                        "scrollX": true
                    });
                }
            });
        },
        guardaPuesto: function () {
            var puesto = {
                "nombre": $("#puestoAdmin").val()
            }
            $.get("SAdministrador", {
                ACCION: "guardaPuesto",
                OBJETO: JSON.stringify(puesto),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Admin.tablaPuesto();
                }
            });
        },
        editarPuesto: function (idpuesto) {
            $.get("SAdministrador", {
                ACCION: "editarPuesto"
            })
        },
        eliminarPuesto: function (idpuesto) {
            $.get("SAdministrador", {
                ACCION: "eliminaPuesto",
                ID: idpuesto
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Admin.tablaPuesto();
            });
        },
        tablaPeriodo: function () {
            $.get("SAdministrador", {
                ACCION: "tablaPeriodo",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#guardaperiodo").on('click', function () {
                        Admin.guardaPeriodo();
                    });
                    $(".eliminarperiodo").on('click', function () {
                        idperiodo = $(this).parents("tr").find("td").eq(0).html();
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
                            Admin.eliminarPeriodo(idperiodo);
                        });
                    });
                    $("#tablaperiodo").DataTable({
                        "scrollX": true
                    });
                }
            });
        },
        guardaPeriodo: function () {
            var periodo = {
                "nombre": $("#periodoAdmin").val(),
                "fechainicio": $("#fechainicioAdmin").val(),
                "fechafin": $("#fechafinAdmin").val()
            }
            $.get("SAdministrador", {
                ACCION: "guardaPeriodo",
                OBJETO: JSON.stringify(periodo),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Admin.tablaPeriodo();
                }
            });

        },
        eliminarPeriodo: function (idperiodo) {
            $.get("SAdministrador", {
                ACCION: "eliminaPeriodo",
                ID: idperiodo
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Admin.tablaPeriodo();
            });
        },
        tablaArea: function () {
            $.get("SAdministrador", {
                ACCION: "tablaArea",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#guardaarea").on('click', function () {
                        Admin.guardaArea();
                    });
                    $(".eliminararea").on('click', function () {
                        idarea = $(this).parents("tr").find("td").eq(0).html();
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
                            Admin.eliminarArea(idarea);
                        });
                    });
                    $("#tablaarea").DataTable({
                        "sccrollX": true
                    });
                }
            });
        },
        guardaArea: function () {
            var area = {
                "nombre": $("#areaAdmin").val()
            }
            $.get("SAdministrador", {
                ACCION: "guardaArea",
                OBJETO: JSON.stringify(area),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Admin.tablaArea();
                }
            });
        },
        eliminarArea: function (idarea) {
            $.get("SAdministrador", {
                ACCION: "eliminaArea",
                ID: idarea
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Admin.tablaArea();
            });
        },
        tablaCpt: function () {
            $.get("SAdministrador", {
                ACCION: "tablaCpt",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#guardarcpt").on('click', function () {
                        Admin.guardaCpt();
                    });
                    $(".eliminarcpt").on('click', function () {
                        idcpt = $(this).parents("tr").find("td").eq(0).html();
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
                            Admin.eliminarCpt(idcpt);
                        });
                    });
                    $("#tablacpt").DataTable({
                        "scrollX": true
                    });
                }
            });
        },
        guardaCpt: function () {
            var cpt = {
                "nombre": $("#cptAdmin").val()
            }
            $.get("SAdministrador", {
                ACCION: "guardaCpt",
                OBJETO: JSON.stringify(cpt),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Admin.tablaCpt();
                }
            });
        },
        eliminarCpt: function (idcpt) {
            $.get("SAdministrador", {
                ACCION: "eliminaCpt",
                ID: idcpt
            }).done(function (xhr, status, error) {

                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Admin.tablaCpt();
            });
        },
        tablaGradoGrupo: function () {
            $.get("SAdministrador", {
                ACCION: "tablaGradoGrupo",
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
        tablaTipoCalificacion: function () {
            $.get("SAdministrador", {
                ACCION: "tablaTipoCalificacion",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#guardatipocali").on('click', function () {
                        Admin.guardaTipoCali();
                    });
                    $(".eliminatipocali").on('click', function () {
                        idtipo = $(this).parents("tr").find("td").eq(0).html();
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
                            Admin.eliminarTipoCali(idtipo);
                        });
                    });
                    $("#tablatipocali").DataTable({
                        "scrollX": true
                    });
                }
            });
        },
        guardaTipoCali: function () {
            var tipocali = {
                "nombre": $("#tipocaliAdmin").val()
            }
            $.get("SAdministrador", {
                ACCION: "guardaTipoCali",
                OBJETO: JSON.stringify(tipocali),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Admin.tablaTipoCalificacion();
                }
            });
        },
        eliminarTipoCali: function (idtipo) {
            $.get("SAdministrador", {
                ACCION: "eliminaTipoCali",
                ID: idtipo
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Admin.tablaTipoCalificacion();
            });
        },
        tablaMateria: function () {
            $.get("SAdministrador", {
                ACCION: "tablaMateria",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#guardamateria").on('click', function () {
                        Admin.guardaMateria();
                    });
                    $(".eliminamateria").on('click', function () {
                        idmateria = $(this).parents("tr").find("td").eq(0).html();
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
                            Admin.eliminaMateria(idmateria);
                        });
                    });
                    $("#tablamateria").DataTable({
                        "scrollX": true
                    });
                }
            });
        },
        guardaMateria: function () {
            var materia = {
                "rdatosmateria": $("#materiaAdmin").val(),
                "rgrado": $("#gradoselect").val(),
                "rgrupo": $("#gruposelect").val(),
                "rarea": $("#areaselect").val(),
                "rcpt": $("#cptselect").val()
            }
            $.get("SAdministrador", {
                ACCION: "guardaMateria",
                OBJETO: JSON.stringify(materia),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Admin.tablaMateria();
                }
            });
        },
        eliminaMateria: function (idmateria) {
            $.get("SAdministrador", {
                ACCION: "eliminaMateria",
                ID: idmateria
            }).done(function (xhr, status, error) {
                if (eeror.status != 200)
                    wal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Admin.tablaMateria();
            });
        }
    }
}());

