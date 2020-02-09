var Adminpersonal = (function () {
    return {
        tablaPersonal: function () {
            $.get("SAdminpersonal", {
                ACCION: "MuestraAgregaPersonal"
            }).then(function () {
                $('#content').html(arguments[0]);
                $('#tablapersonal').DataTable({
                    "scrollX": true
                });
                $('#btnaregarP').on('click', function () {
                    Adminpersonal.agregaPersonal();
                }),
                        $('.editarpe').on('click', function () {
                    idpersonal = $(this).parents("tr").find("td").eq(0).html();
                    Adminpersonal.editarPersonal(idpersonal);
                }),
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
            });
        },
        formulariospe: function (argumento) {
            $('#content').html(argumento);
            $('#guardapersonal').on('click', function () {
                if (validacionPersonal()) {
                    Adminpersonal.guardaPersonal(Adminpersonal.datosPersonal(), 'GuardaPersonal');
                }else
                    swal("Faltan campos requeridos","","warning");
            });
        },
        editarPersonal: function (idpersonal) {
            $.get("SAdminpersonal", {
                ACCION: "editarPersonal",
                IDPERSONAL: idpersonal
            }).then(function () {
                Adminpersonal.formulariospe(arguments[0]);
            });
        },
        eliminarPersonal: function (idpersonal) {
            $.get("SAdminpersonal", {
                ACCION: "eliminarPersonal",
                IDPERSONAL: idpersonal
            }).then(function () {
                Adminpersonal.tablaPersonal();
            })
        },
        agregaPersonal: function () {
            $.get("SAdminpersonal", {
                ACCION: "AgregaPersonal"
            }).then(function () {
                Adminpersonal.formulariospe(arguments[0]);
            });
        },
        guardaPersonal: function (objeto, accion) {
            $.get("SAdminpersonal", {
                ACCION: accion,
                PERSONAL: JSON.stringify(objeto)
            }).then(function () {
                alert('Personal guardado');
                Adminpersonal.tablaPersonal();
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


