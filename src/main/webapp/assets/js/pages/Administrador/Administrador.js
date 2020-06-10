/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Admin = (function () {
    /*Variable para saber si estamos en secundaria o preparatoria*/
    var tipoescuela = JSON.parse(sessionStorage.getItem("tipoescuela"));
    return {
        /*Funcion que recoge los datos del formulario de registro de nuevo usuario por medio de post*/
        registro: function () {
            $('form[name="formregistro"]').ajaxForm(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    swal("Listo!", "Registrado correctamente, favor de revisar su correo", "success");
            });
        },
        /*Funcion que hace mostrar la vista de reporte de asistencia general en pestaña control escolar*/
        reporteGAsistencia: function () {
            $.get("SAdministrador", {
                ACCION: "reporteGAsistencia",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    /*Recogemos los valores de los selects para cargar informacion de la siguiente funcion*/
                    $("#content").html(arguments[0]);
                    idperiodo = $("#periodoGasistencia").val();
                    idsemana = $("#semanafiscalGasistencia").val();
                    idgrado = $("#gradoGasistencia").val();
                    idgrupo = $("#grupoGasistencia").val();
                    Admin.getreporteGAsistencia(idperiodo, idsemana, idgrado, idgrupo);
                    $("body").find("select[id='periodoGasistencia']").unbind('change').bind('change', function () {
                        idperiodo = $("#periodoGasistencia").val();
                        Admin.getreporteGAsistencia(idperiodo, idsemana, idgrado, idgrupo);
                    });
                    $("body").find("select[id='semanafiscalGasistencia']").unbind('change').bind('change', function () {
                        idsemana = $("#semanafiscalGasistencia").val();
                        Admin.getreporteGAsistencia(idperiodo, idsemana, idgrado, idgrupo);
                    });
                    $("body").find("select[id='gradoGasistencia']").unbind('change').bind('change', function () {
                        idgrado = $("#gradoGasistencia").val();
                        Admin.getreporteGAsistencia(idperiodo, idsemana, idgrado, idgrupo);
                    });
                    $("body").find("select[id='grupoGasistencia']").unbind('change').bind('change', function () {
                        idgrupo = $("#grupoGasistencia").val();
                        Admin.getreporteGAsistencia(idperiodo, idsemana, idgrado, idgrupo);
                    });
                }
            });
        },
        /*Funcion que sirve para traer infomacion y cargarla en la vista de la funcion anterior*/
        getreporteGAsistencia: function (idperiodo, idsemana, idgrado, idgrupo) {
            //alert (idperiodo);
            $.get("SAdministrador", {
                ACCION: "getreporteGAsistencia",
                IDP: idperiodo,
                IDS: idsemana,
                IDGRADO: idgrado,
                IDGRUPO: idgrupo,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, stats, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    /*No usamos el id content porque solo queremos cargar informacion en otra vista*/
                    $("#ReporteGAsistencia").html(arguments[0]);
                    $("body").find('a.btnjustificar').unbind('click').bind('click', function () {
                        idalumno = $(this).parents("tr").find("td").eq(0).html();
                        Admin.justificarFaltas(idalumno, idsemana, idperiodo);
                    });
                }
            });
        },
        /*Funcion que se ejecuta al presionar el boton de justificar*/
        justificarFaltas: function (idalumno, idsemana, idperiodo) {
            $.get("SAdministrador", {
                ACCION: "justificarFaltas",
                TIPOESCUELA: tipoescuela,
                IDA: idalumno,
                IDS: idsemana,
                IDP: idperiodo
            }).done(function (xhr, staturs, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    /*De igual manera recogemos valores de los selects para cargar informacion de la siguiente funcion*/
                    $("#content").html(arguments[0]);
                    idperiodo = $("#periodojustificar").val();
                    idsemana = $("#semanajustificar").val();
                    idmateria = $("#materiajustificar").val();
                    Admin.getDiasFaltas(idalumno, idperiodo, idsemana, idmateria);
                    $("body").find("select[id='periodojustificar']").unbind('change').bind('change', function () {
                        idperiodo = $("#periodojustificar").val();
                        Admin.getDiasFaltas(idalumno, idperiodo, idsemana, idmateria);
                    });
                    $("body").find("select[id='semanajustificar']").unbind('change').bind('change', function () {
                        idsemana = $("#semanajustificar").val();
                        Admin.getDiasFaltas(idalumno, idperiodo, idsemana, idmateria);
                    });
                    $("body").find("select[id='materiajustificar']").unbind('change').bind('change', function () {
                        idmateria = $("#materiajustificar").val();
                        Admin.getDiasFaltas(idalumno, idperiodo, idsemana, idmateria);
                    });
                    /*Funciones como esta se usan cuando se desea guardar informacion proveniente de algun formulario o no necesariamente formulario
                     * lo que se hace es crear variables para almacenar informacion de los inputs del formulario, en este caso la informacion se recoge de un conjunto de checkboxes
                     * utilizamos la funcion each para iterar los checkboxes y toma en cuenta los valores marcados*/
                    $("body").unbind('click').on("click", "#justificarfaltas", function (event) {
                        let faltas = [];
                        $("#checkfaltas input[type='checkbox']:checked").each(function () {
                            let falta = {
                                "idtbasistencia": $(this).val()
                            };
                            faltas.push(falta);
                            falta = null;
                        });
                        /*Mandamos por get el objeto al servlet*/
                        console.log(faltas);
                        $.get("SAdministrador", {
                            ACCION: "updateJustificar",
                            OBJETO: JSON.stringify(faltas)
                        }).done(function (xhr, status, error) {
                            if (error.status != 200)
                                swal(error.getResponseHeader("ERROR"), "", "warning");
                            else {
                                swal("Hecho!", "Faltas justificadas", "success");
                                alumnos = [];
                            }
                        });
                    });
                }
            });
        },
        /*Funcion que sirve para traer infomacion y cargarla en la vista de la funcion anterior*/
        getDiasFaltas: function (idalumno, idperiodo, idsemana, idmateria) {
            $.get("SAdministrador", {
                ACCION: "getDiasFaltas",
                TIPOESCUELA: tipoescuela,
                IDA: idalumno,
                IDS: idsemana,
                IDP: idperiodo,
                IDM: idmateria
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#Justificacion").html(arguments[0]);
                    console.log("entra");
                }
            })
        },
        /*Funcion que muestra la tabla del apartado de puesto*/
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
                        paging: false,
                        "scrollX": true
                    });
                }
            });
        },
        /*Funcion lanzada al presionar el boton de guardar puesto*/
        guardaPuesto: function () {
            var puesto = {
                "nombre": $("#puestoAdmin").val()
            }
            /*Creamos variable para poder mandar el objeto al servlet y tratarlo*/
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
        /*Funcion para eliminar puesto, sólo tomamos el id del puesto que tomamos por medio de lboton editar en la tabla*/
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
        /*Traemos la vista de la tabla*/
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
                        paging: false,
                        "scrollX": true
                    });
                }
            });
        },
        /*Se cativa funcion a usar el boton de guardar*/
        guardaPeriodo: function () {
            var periodo = {
                "nombre": $("#periodoAdmin").val(),
                "fechainicio": $("#fechainicioAdmin").val(),
                "fechafin": $("#fechafinAdmin").val()
            }
            /*Creamos variable para poder mandar el objeto al servlet y tratarlo*/
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
        /*Funcion para eliminar registro, sólo traemos el id al presionar el boton de eliminar*/
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
        /*Traemos la vista de la tabla*/
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
                        paging: false,
                        "sccrollX": true
                    });
                }
            });
        },
        /*Se cativa funcion a usar el boton de guardar*/
        guardaArea: function () {
            var area = {
                "nombre": $("#areaAdmin").val()
            }
            /*Creamos variable para poder mandar el objeto al servlet y tratarlo*/
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
        /*Funcion para eliminar registro, tomamos el id al presionar el boton de eliminar*/
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
        /*Traemos la vista de la tabla*/
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
                        paging: false,
                        "scrollX": true
                    });
                }
            });
        },
        /*Se cativa funcion a usar el boton de guardar*/
        guardaCpt: function () {
            var cpt = {
                "nombre": $("#cptAdmin").val()
            }
            /*Creamos variable para poder mandar el objeto al servlet y tratarlo*/
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
        /*Funcion para eliminar registro, tomamos el id al presionar el boton de eliminar*/
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
        /*Traemos la vista de la tabla*/
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
                        paging: false,
                        "scrollX": true
                    });
                }
            });
        },
        /*Se cativa funcion a usar el boton de guardar*/
        guardaTipoCali: function () {
            var tipocali = {
                "nombre": $("#tipocaliAdmin").val()
            }
            /*Creamos variable para poder mandar el objeto al servlet y tratarlo*/
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
        /*Funcion para eliminar registro, tomamos el id al presionar el boton de eliminar*/
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
        /*Traemos la vista de la tabla*/
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
                        paging: false,
                        "scrollX": true
                    });
                }
            });
        },
        /*Se cativa funcion a usar el boton de guardar*/
        guardaMateria: function () {
            var materia = {
                "rdatosmateria": $("#materiaAdmin").val(),
                "rgrado": $("#gradoselect").val(),
                "rgrupo": $("#gruposelect").val(),
                "rarea": $("#areaselect").val(),
                "rcpt": $("#cptselect").val()
            }          
            /*Creamos variable para poder mandar el objeto al servlet y tratarlo*/
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
        /*Funcion para eliminar registro, tomamos el id al presionar el boton de eliminar*/
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

