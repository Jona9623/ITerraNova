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
                    $('#tablapersonal').DataTable({
                        "scrollX": true
                    });
                    Adminpersonal.importarPersonal();
                    $('#btnaregarP').on('click', function () {
                        Adminpersonal.agregaPersonal();
                    });
                    $(".infopersonal").on('click', function () {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.infoPersonal(idpersonal);
                    });
                    $(".agregamateria").on('click', function () {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.agregaMateria(idpersonal);
                    });
                    $('.editarpe').on('click', function () {
                        idpersonal = $(this).parents("tr").find("td").eq(0).html();
                        Adminpersonal.editarPersonal(idpersonal);
                    });
                    $(".aliminarpe").on('click', function () {
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
                }
            });
            $("#actualizaTablaP").on('click', function () {
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
                    var listmateria =[] ;;
                    $("#asignamateria").on('click', function () {
                        alert("entra");
                        $("#check input[type='checkbox']:checked").each(function () {
                            var materia = {
                                "idtbmateria": $(this).val()
                            };
                            listmateria.push(materia);
                        });
                        console.log(listmateria);
                        $.get("SAdminpersonal",{
                            ACCION: "guardaMateria",
                            PRUEBA: JSON.stringify(listmateria)
                        });
                        listmateria = [];
                    });
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


