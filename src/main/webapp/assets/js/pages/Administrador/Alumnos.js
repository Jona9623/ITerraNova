
var Adminalumno = (function () {
    return {
        tablaAlumnos: function () {
            $.get("SAdminalumno", {
                ACCION: "MuestraAgregaAlumno"
            }).then(function () {
                $('#content').html(arguments[0]);
                $("#tablaalumnos").DataTable({
                    "scrollX": true
                });
                $('#btnagregaA').on('click', function () {
                    Adminalumno.agregaAlumno();
                }),
                        $('.editaralu').on('click', function () {
                    idalumno = $(this).parents("tr").find("td").eq(0).html();
                    idtutor = $(this).parents("tr").find("td").eq(1).html();
                    Adminalumno.editarAlumno(idalumno, idtutor);
                }),
                        $(".aliminaralu").on('click', function () {
                    idalumno = $(this).parents("tr").find("td").eq(0).html();
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
                        Adminalumno.eliminarAlumno(idalumno);
                    });

                });
            });
        },
        formularios: function (argumento) {
            $('#content').html(argumento);
            $('#guardatutor').on('click', function () {
                if (validacionTutor()) {
                    Adminalumno.guardaTutor(Adminalumno.datosTutor(), 'GuardaTutor');
                } else
                    swal("Faltan campos requeridos","","warning");
            });
            $('#guardaalumno').on('click', function () {
                if (validacionAlumno()) {
                    Adminalumno.guardaAlumno(Adminalumno.datosAlumno(), 'GuardaAlumno');
                }
                else
                    swal("Faltan campos requeridos","","warning");
            });
        },
        editarAlumno: function (idalumno, idtutor) {
            $.get("SAdminalumno", {
                ACCION: "editarAlumno",
                IDALUMNO: idalumno,
                IDTUTOR: idtutor
            }).then(function () {
                Adminalumno.formularios(arguments[0]);
            });
        },
        eliminarAlumno: function (idalumno) {
            $.get("SAdminalumno", {
                ACCION: "eliminarAlumno",
                IDALUMNO: idalumno
            }).then(function () {
                Adminalumno.tablaAlumnos();
            })
        },
        agregaAlumno: function () {
            $.get("SAdminalumno", {
                ACCION: "AgregaAlumno"
            }).then(function () {
                Adminalumno.formularios(arguments[0]);
            });
        },
        guardaTutor: function (objeto, accion) {
            $.get("SAdminalumno", {
                ACCION: accion,
                TUTOR: JSON.stringify(objeto)
            }).then(function () {
                swal("Hecho!", "Datos guardados correctamente", "success");
            });
        },
        guardaAlumno: function (objeto, accion) {
            $.get("SAdminalumno", {
                ACCION: accion,
                ALUMNO: JSON.stringify(objeto)
            }).then(function () {
                swal("Hecho!", "Datos guardados correctamente", "success");
                Adminalumno.tablaAlumnos();
            });
        },
        datosTutor: function () {
            var tutor = {
                "idtbtutor": $("#idtutor").val(),
                "nombre": $("#nombret").val(),
                "apellidop": $("#apellidomt").val(),
                "apellidom": $("#apellidopt").val(),
                "ocupacion": $("#ocupacion").val(),
                "parentesco": $("#parentesco").val(),
                "calledom": $("#calledomt").val(),
                "numerodom": $("#numerodomt").val(),
                "coloniadom": $("#coloniadomt").val(),
                "codigopostal": $("#codigopostalt").val(),
                "telefonocasa": $("#telcasat").val(),
                "celular": $("#celulart").val(),
                "correo": $("#correot").val(),
                "status": 1,
                "tipoescuela": 1
            }
            return tutor;
        },
        datosAlumno: function () {
            var alumno = {
                "idtbalumnos": $("#idalumno").val(),
                "matricula": $("#matricula").val(),
                "nombre": $("#nombrea").val(),
                "apellidop": $("#apellidopa").val(),
                "apellidom": $("#apellidoma").val(),
                "fechanacimiento": $("#fechanaa").val(),
                "curp": $("#curpa").val(),
                "municipiona": $("#municipionaca").val(),
                "estadona": $("#estadonaca").val(),
                "nacionalidad": $("#nacionalidada").val(),
                "sexo": $("input[name = 'sexo']:checked").val(),
                "calledom": $("#calledoma").val(),
                "numerodom": $("#numerodoma").val(),
                "coloniadom": $("#coloniadoa").val(),
                "codigopostal": $("#codigopostala").val(),
                "telefonocasa": $("#telcasaa").val(),
                "celular": $("#celulara").val(),
                "correo": $("#correoa").val(),
                "nivelcursa": $("#nivela").val(),
                "rgrado": $("#grado").val(),
                "rgrupo": $("#grupo").val(),
                "rarea": $("#area").val(),
                "rcpt": $("#cpt").val(),
                "plantelproce": $("#plantelproce").val(),
                "nivelanterior": $("#nivelanterior").val(),
                "gradoanterior": $("#gradoanterior").val(),
                "turnoanterior": $("#turnoanterior").val(),
                "municipioante": $("#plantelanterior").val(),
                "status": 1,
                "tipoescuela": 1
            }
            return alumno;
        }
    }
}());
$("body").on("focus","input",function(event){
    $(this).parent().removeClass('has-error');
});
$("body").on("focus","textarea",function(event){
    $(this).parent().removeClass('has-error');
});
function validacionAlumno() {
    var valido = true;
    if ($("#nombrea").val().trim() == "") {
        $("#div-nombrea").addClass("has-error");
        valido = false;
    }
    if ($("#apellidopa").val().trim() == "") {
        $("#div-apellidopa").addClass("has-error");
        valido = false;
    }
    if ($("#nivela").val().trim() == "") {
        $("#div-nivela").addClass("has-error");
        valido = false;
    }
    if ($("#celulara").val().trim() == "") {
        $("#div-celulara").addClass("has-error");
        valido = false;
    }
    return valido;
}
function validacionTutor() {
    var valido = true;
    if ($("#nombret").val().trim() == "") {
        $("#div-nombret").addClass("has-error");
        valido = false;
    }
    if ($("#apellidopt").val().trim() == "") {
        $("#div-apellidopt").addClass("has-error");
        valido = false;
    }
     if ($("#parentesco").val().trim() == "") {
         $("#div-parentesco").addClass("has-error");
         valido = false;
     }
     if ($("#celulart").val().trim() == "") {
         $("#div-celular").addClass("has-error");
         valido = false;
     }
    return valido;
}


