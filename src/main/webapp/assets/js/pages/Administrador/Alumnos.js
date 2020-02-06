
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
                }
            });
            $('#guardaalumno').on('click', function () {
                if (validacionAlumno()) {
                    Adminalumno.guardaAlumno(Adminalumno.datosAlumno(), 'GuardaAlumno');
                }
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
function validacionAlumno() {
    var text = "";
    if ($.trim($("#nombrea").val()).length === 0) {
        text = text + "Nombre(s) del alumno\n";
    }
    if ($.trim($("#apellidopa").val()).length === 0) {
        text = text + "Apellido paterno del alumno\n";
    }
    if ($.trim($("#nivela").val()).length === 0) {
        text = text + "Nivel que cursa\n";
    }
    if (text.length > 0) {
        showWithTitleMessage('Existen campos vacios', text);
        return false;
    } else {
        return true;
    }
}
function validacionTutor() {
    alert("entra");
    var text = "";
    if ($.trim($("#nombret").val()).length === 0) {
        text = text + "Nombre(s) del tutor\n";
    }
    if ($.trim($("#apelliopt").val()).length === 0) {
        text = text + "Apellido paterno del tutor\n";
    }
     if ($.trim($("#parentesco").val()).length === 0) {
     text = text + "Parentesco\n";
     }
     if ($.trim($("#celulart").val()).length === 0) {
     text = text + "Celular del tutor\n";
     }
    if (text.length > 0) {
        showWithTitleMessage('Existen campos vacios', text);
        alert("Entra faltan campos");
        return false;
    } else {
        alert("Entra todo correcto");
        return true;
    }
}


