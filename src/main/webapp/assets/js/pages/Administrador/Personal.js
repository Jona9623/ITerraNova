var Adminpersonal = (function () {
    /*Variable para saber si estamos en secundaria o preparatoria*/
    var tipoescuela = JSON.parse(sessionStorage.getItem("tipoescuela"));
    var ban = 0;
    return {
        /*Traemos la vista de la tabla*/
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
        /*Funcion creada de un solo uso, siver para importar el personal y guardar mucha informacion de golpe*/
        importarPersonal: function () {
            $('form[name="importarpersonal"]').ajaxForm(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    swal("Hecho!", "Datos importados correctamente", "success");
            });
        },
        /*Se usa para msotrar el formulario del personal*/
        formulariospe: function (argumento) {
            $('#content').html(argumento);
            $('#guardapersonal').on('click', function () {
                if (validacionPersonal()) {
                    Adminpersonal.guardaPersonal(Adminpersonal.datosPersonal(), 'GuardaPersonal');
                } else
                    swal("Faltan campos requeridos", "", "warning");
            });
        },
        /*Se muestra la vista ocn la info completa del personal seleccionado*/
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
        /*Se usa esta funcion cuando se quiere agregar materia a el profesor, tomamos su id al presionar el boton de agregar materia
         * y se muestra una tabla con las materias disponibles para agregar*/
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
                        /*Al presionar el boton de guardar iteramos con each los checkboxes para saber que materias ha marcado y agregarlas creando un arreglo de materias y mandarlo
                         * al servlet*/
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
        /*Se usa esta funcion cuando presiona el boton de asignar alumnos, se toma el id del profe seleccionado*/
        asignarAlumnos: function (idpersonal) {
            $.get("SAdminpersonal", {
                ACCION: "agregaAlumnos",
                ID: idpersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    /*Aqui tomamos estos valores porque necesitamos tomarlos de la materia*/
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
                        /*Aqui despues de presionar el boton de asignar alumnos iteramos de nuevo en un each los checkboxes para saber que alumnos fueron marcados
                         * los agregamos aun arreglo y lo mandamos como objeto al servlet*/
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
        /*Se muestra una vista con una tabla y alumnos para el pase de asistencia*/
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
                    /*Igual tomamos valores provenientes de la materia para mostrar alumnos*/
                    $("#content").html(arguments[0]);
                    grado = $('option:selected').attr('grado');
                    grupo = $('option:selected').attr('grupo');
                    area = $('option:selected').attr('area');
                    cpt = $('option:selected').attr('cpt');
                    materiapersonal = $("#materiaLista").val();
                    Adminpersonal.getListaAlumno(grado, grupo, area, cpt, materiapersonal, idpersonal, $("#periodoasistencia").val(), $("#semanafiscalasistencia").val(), $("#diaasistencia").val());
                    //$("#materiaLista").change(function () {
                    $("body").find("select[id='materiaLista']").unbind('change').bind('change', function () {
                        grado = $('option:selected').attr('grado');
                        grupo = $('option:selected').attr('grupo');
                        area = $('option:selected').attr('area');
                        cpt = $('option:selected').attr('cpt');
                        materiapersonal = $("#materiaLista").val();
                        Adminpersonal.getListaAlumno(grado, grupo, area, cpt, materiapersonal, idpersonal, $("#periodoasistencia").val(), $("#semanafiscalasistencia").val(), $("#diaasistencia").val());
                    });
                    $("body").find("select[id='periodoasistencia']").unbind('change').bind('change', function () {
                        Adminpersonal.getListaAlumno(grado, grupo, area, cpt, materiapersonal, idpersonal, $("#periodoasistencia").val(), $("#semanafiscalasistencia").val(), $("#diaasistencia").val());
                    });
                    $("body").find("select[id='semanafiscalasistencia']").unbind('change').bind('change', function () {
                        Adminpersonal.getListaAlumno(grado, grupo, area, cpt, materiapersonal, idpersonal, $("#periodoasistencia").val(), $("#semanafiscalasistencia").val(), $("#diaasistencia").val());
                    });
                    $("body").find("select[id='diaasistencia']").unbind('change').bind('change', function () {
                        Adminpersonal.getListaAlumno(grado, grupo, area, cpt, materiapersonal, idpersonal, $("#periodoasistencia").val(), $("#semanafiscalasistencia").val(), $("#diaasistencia").val());
                    });
                }
            });
        },
        /*Teniendo en cuenta los parametros mostramos en la misma vista la tabla con alumnos*/
        getListaAlumno: function (grado, grupo, area, cpt, materiapersonal, idpersonal, idperiodo, idsemana, iddia) {
            console.log(idpersonal);
            $.get("SAdminpersonal", {
                ACCION: "getListaAlumno",
                GRADO: grado,
                GRUPO: grupo,
                AREA: area,
                CPT: cpt,
                ID: idpersonal,
                IDM: materiapersonal,
                IDP: idperiodo,
                IDS: idsemana,
                IDD: iddia,
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
                    /*Esta validacion funciona para determinar el dia donde se toma asistencia, debido a ser dos escuelas se repiten 2 veces cada dia
                     * y por esto tiene id diferentes un mismo dia*/
                    //$("#asistencia").on('click', function () {
                    $("body").find("button[id='asistencia']").unbind('click').bind('click', function () {

                        var dia = $("#diaasistencia").val();
                        var nombredia;
                        if (dia == 1 || dia == 6)
                            nombredia = "Lunes";
                        if (dia == 2 || dia == 7)
                            nombredia = "Martes";
                        if (dia == 3 || dia == 8)
                            nombredia = "Miercoles";
                        if (dia == 4 || dia == 9)
                            nombredia = "Jueves";
                        if (dia == 5 || dia == 10)
                            nombredia = "Viernes";
                        swal({
                            title: "Estas seguro?",
                            text: "Se guardara asistencia para el dia: " + nombredia,
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "Confirmar",
                            closeOnConfirm: false
                        }, function () {
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
                            /*Se itera con each los checkboxes para determinar si el alumno faltó o asistió 1 asistencia, 0 falta*/
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
                                    Adminpersonal.listaAlumnos(idpersonal);
                                }
                            });
                        });
                    });
                    /*Aqui escuchamso el boton que muestra la asistencia anterior, la funcion es mostrar una vista de asistencia con los datos anteriores
                     * para su actualizacion*/
                    $("body").find("button[id='asistenciaanterior']").unbind('click').bind('click', function () {

                        var dia = $("#diaasistencia").val();
                        console.log(dia);
                        var idperiodo = $("#periodoasistencia").val();
                        var idsemana = $("#semanafiscalasistencia").val();
                        console.log(dia);
                        $.get("SAdminpersonal", {
                            ACCION: "asistenciaAnterior",
                            TIPOESCUELA: tipoescuela,
                            IDD: dia,
                            IDP: idperiodo,
                            IDS: idsemana,
                            ID: idpersonal,
                            IDM: materiapersonal
                        }).done(function (xhr, status, error) {
                            if (error.status != 200)
                                swal(error.getResponseHeader("ERROR"), "", "warning");
                            else {
                                $("#content").html(arguments[0]);
                                $('#tablaasistenciaanterior').DataTable({
                                    paging: false,
                                    "scrollX": true
                                });
                                $("body").find("button[id='actualizarasistencia']").unbind('click').bind('click', function () {
                                    let listasistencia = [];
                                    let check = 0;
                                    $("#check3 input[type='checkbox']").each(function () {
                                        if ($(this).is(':checked')) {
                                            check = 1;
                                        } else {
                                            check = 0;
                                        }
                                        let asistencia = {
                                            "idtbasistencia": $(this).val(),
                                            "asistencia": check

                                        };
                                        listasistencia.push(asistencia);
                                        asistencia = null;
                                    });
                                    $.get("SAdminpersonal", {
                                        ACCION: "actualizaAsistencia",
                                        OBJETO: JSON.stringify(listasistencia)
                                    }).done(function (xhr, status, error) {
                                        if (error.status != 200)
                                            swal(error.getResponseHeader("ERROR"), "", "warning");
                                        else {
                                            swal("Hecho!", "Asistencia del dia actualizada", "success");
                                        }
                                    })
                                });
                            }
                        });
                    });
                }
            });
        },
        /*Aqui se usa esta funcion param mostrar la lista de alumnos de acuerdo a los parametros requeridos en la misma vista de asignar alumnos*/
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
                }
            })
        },
        /*Aqui se mostrará una tabla para los profes con las faltas de los alumnos o faltas justificadas*/
        reporteAsistencia: function (idpersonal) {
            $.get("SAdminpersonal", {
                ACCION: "reporteAsistencia",
                ID: idpersonal,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    /*Se tomaran parametros para mostrar los alumnos con fatlas o dias justificados*/
                    $("#content").html(arguments[0]);
                    idperiodo = $("#periodoReporte").val();
                    idmateria = $("#materiaReporte").val();
                    idsemana = $("#semanaReporte").val();
                    Adminpersonal.getreporteAsistencia(idperiodo, idmateria, idsemana, idpersonal);
                    $("body").find("select[id='periodoReporte']").unbind('change').bind('change', function () {
                        idperodo = $("#periodoReporte").val();
                        Adminpersonal.getreporteAsistencia(idperiodo, idmateria, idsemana, idpersonal);
                    });
                    $("body").find("select[id='materiaReporte']").unbind('change').bind('change', function () {
                        idmateria = $("#materiaReporte").val();
                        Adminpersonal.getreporteAsistencia(idperiodo, idmateria, idsemana, idpersonal);
                    });
                    $("body").find("select[id='semanaReporte']").unbind('change').bind('change', function () {
                        idsemana = $("#semanaReporte").val();
                        Adminpersonal.getreporteAsistencia(idperiodo, idmateria, idsemana, idpersonal);
                    });
                }
            })
        },
        /*Aqui con los parametros requeridos se muestra la tabla con los alumnos, esto es para tener un control de la informacion que se muestra basada en los parametros*/
        getreporteAsistencia: function (idperiodo, idmateria, idsemana, idpersonal) {
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
                    $("#tablaasisencia").DataTable({
                        //paging: false,
                        "scrollX": true
                    });
                }
            })
        },
        /*Se mostrará el formulario del personal para editar su info*/
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
        /*Funcion para eliminar registro, tomamos el id al presionar el boton de eliminar*/
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
        /*Mostrará el formulario de lpersonal al preisonar el boton agregar personal*/
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
        /*Se cativa funcion a usar el boton de guardar*/
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
        /*Recopilamos los valores de los inputs del formulario para retornar el objeto y mandrlo al servlet*/
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
        /*Funciones solo para marcar o desmarcar, se usan cuando se asignan alumnos*/
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
/*Funcion para validar los campos del formulario del personal*/
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


