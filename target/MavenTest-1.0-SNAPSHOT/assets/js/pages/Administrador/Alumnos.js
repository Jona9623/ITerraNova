
var Adminalumno = (function () {
    var tipoescuela = JSON.parse(sessionStorage.getItem("tipoescuela"));
    return {
        tablaAlumnos: function () {
            $.get("SAdminalumno", {
                ACCION: "MuestraAgregaAlumno",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $('#content').html(arguments[0]);
                    $("#tablaalumnos").DataTable({
                        "scrollX": true
                    });
                    Adminalumno.importaTutor();
                    Adminalumno.importaAlumno();
                    $('#btnagregaA').on('click', function () {
                        Adminalumno.agregaAlumno();
                    });
                    $(".infoalumno").on('click', function () {
                        idalumno = $(this).parents("tr").find("td").eq(0).html();
                        idtutor = $(this).parents("tr").find("td").eq(1).html();
                        Adminalumno.infoAlumno(idalumno, idtutor);
                    });
                    $(".horairoalum").on('click', function () {
                        idalumno = $(this).parents("tr").find("td").eq(0).html();
                        Adminalumno.asignaHorario(idalumno);
                    });
                    $(".muestrahorario").on('click', function () {
                        alert("entra");
                        idalumno = $(this).parents("tr").find("td").eq(0).html();
                        Adminalumno.muestraHorario(idalumno);
                    });
                    $('.editaralu').on('click', function () {
                        idalumno = $(this).parents("tr").find("td").eq(0).html();
                        idtutor = $(this).parents("tr").find("td").eq(1).html();
                        Adminalumno.editarAlumno(idalumno, idtutor);
                    });
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
                }
            });
            $("#actualizaTablaA").on('click', function () {
                Adminalumno.tablaAlumnos();
            });
        },
        infoAlumno: function (idalumno, idtutor) {
            $.get("SAdminalumno", {
                ACCION: "infoAlumno",
                IDALUMNO: idalumno,
                IDTUTOR: idtutor
            }).done(function (hxr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                }
            })
        },
        asignaHorario: function (idalumno) {
            $.get("SAdminalumno", {
                ACCION: "getMateriasAlum",
                ID: idalumno,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    $("#asignahorario").on('click', function () {
                        var horario = {
                            "r_periodo": $("#periodohorario").val(),
                            "r_materiaalumno": $("#materiahorario").val(),
                            "hora": $("#horahorario").val()
                        }
                        console.log(horario);
                        $.get("SAdminalumno", {
                            ACCION: "asignaHorario",
                            OBJETO: JSON.stringify(horario),
                            TIPOESCUELA: tipoescuela
                        }).done(function (xhr, status, error) {
                            if (error.status != 200)
                                swal(error.getResponseHeader("ERROR"), "", "warning");
                            else
                                swal("Hecho!", "Datos importados correctamente", "success");
                        });
                    });
                }
            });
        },
        muestraHorario: function (idalumno) {
            $.get("SAdminalumno", {
                ACCION: "muestraHorario",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    $("#content").html(arguments[0]);
                    Adminalumno.getHorario(idalumno,0);
                }
            });
        },
        getHorario: function (idalumno,idperiodo) {
            idperiodo = $("#periodogethorario").val()
            $.get("SAdminalumno", {
                ACCION: "getHorario",
                IDP: idperiodo,
                ID: idalumno,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else{
                    $("#getHorarios").html(arguments[0]);
                    $("#periodogethorario").change(function () {
                        idperiodo = $("#periodogethorario").val();
                        Adminalumno.getHorario(idalumno,idperiodo)
                    });
                }
            })
        },
        importaTutor: function () {
            $('form[name="importartutor"]').ajaxForm(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    swal("Hecho!", "Datos importados correctamente", "success");
            });
        },
        importaAlumno: function () {
            $('form[name="importaralumno"]').ajaxForm(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    swal("Hecho!", "Datos importados correctamente", "success");
            });
        },
        formularios: function (argumento) {
            $('#content').html(argumento);
            Adminalumno.guardarAlumno();
            $('#guardatutor').on('click', function () {
                if (validacionTutor()) {
                    Adminalumno.guardaTutor(Adminalumno.datosTutor(), 'GuardaTutor');
                } else
                    swal("Faltan campos requeridos", "", "warning");
            });
            $("#fotoalumno").click(function () {
                $(this).val("");
            });
            $("#fotoalumno").change(function () {
                convertBase64A();
            });
            /* $('#guardaalumno').on('click', function () {
             if (validacionAlumno()) {
             Adminalumno.guardaAlumno(Adminalumno.datosAlumno(), 'GuardaAlumno');
             }
             else
             swal("Faltan campos requeridos","","warning");
             });*/
        },
        guardarAlumno: function () {
            $('form[name="formAlumno"]').ajaxForm(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    swal("Hecho!", "Datos guardados correctamente", "success");
                    Adminalumno.tablaAlumnos();
                }
            });
        },
        editarAlumno: function (idalumno, idtutor) {
            $.get("SAdminalumno", {
                ACCION: "editarAlumno",
                IDALUMNO: idalumno,
                IDTUTOR: idtutor,
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else {
                    Adminalumno.formularios(arguments[0]);
                }
            });
        },
        eliminarAlumno: function (idalumno) {
            $.get("SAdminalumno", {
                ACCION: "eliminarAlumno",
                IDALUMNO: idalumno
            }).done(function (xhr, status, error) {
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Adminalumno.tablaAlumnos();
            });
        },
        agregaAlumno: function () {
            $.get("SAdminalumno", {
                ACCION: "AgregaAlumno",
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    Adminalumno.formularios(arguments[0]);
            });
        },
        guardaTutor: function (objeto, accion) {
            $.get("SAdminalumno", {
                ACCION: accion,
                TUTOR: JSON.stringify(objeto),
                TIPOESCUELA: tipoescuela
            }).done(function (xhr, status, error) {
                console.log(arguments);
                console.log(error.status);
                console.log(error.getResponseHeader("ERROR"));
                if (error.status != 200)
                    swal(error.getResponseHeader("ERROR"), "", "warning");
                else
                    swal("Hecho!", "Datos guardados correctamente", "success");
            });
        },
        /*  guardaAlumno: function (objeto, accion) {
         $.get("SAdminalumno", {
         ACCION: accion,
         ALUMNO: JSON.stringify(objeto),
         TIPOESCUELA: tipoescuela
         }).done(function(xhr, status, error){
         console.log(arguments);
         console.log(error.status);
         console.log(error.getResponseHeader("ERROR"));
         if(error.status != 200)
         swal(error.getResponseHeader("ERROR"),"","warning");
         }).fail(function(xhr, status, error){
         console.log(
         window.location.pathname);
         }).then(function () {
         swal("Hecho!", "Datos guardados correctamente", "success");
         Adminalumno.tablaAlumnos();
         });
         },*/
        datosTutor: function () {
            var tutor = {
                "idtbtutor": $("#idtutor").val(),
                "nombre": $("#nombret").val(),
                "apellidop": $("#apellidopt").val(),
                "apellidom": $("#apellidomt").val(),
                "ocupacion": $("#ocupacion").val(),
                "parentesco": $("#parentesco").val(),
                "calledom": $("#calledomt").val(),
                "numerodom": $("#numerodomt").val(),
                "coloniadom": $("#coloniadomt").val(),
                "codigopostal": $("#codigopostalt").val(),
                "telefonocasa": $("#telcasat").val(),
                "celular": $("#celulart").val(),
                "correo": $("#correot").val(),
                "nombre2": $("#nombret2").val(),
                "apellidop2": $("#apellidopt2").val(),
                "apellidom2": $("#apellidomt2").val(),
                "ocupacion2": $("#ocupacion2").val(),
                "parentesco2": $("#parentesco2").val(),
                "calledom2": $("#calledomt2").val(),
                "numerodom2": $("#numerodomt2").val(),
                "coloniadom2": $("#coloniadomt2").val(),
                "codigopostal2": $("#codigopostalt2").val(),
                "telefonocasa2": $("#telcasat2").val(),
                "celular2": $("#celulart2").val(),
                "correo2": $("#correot2").val(),
                "nombre3": $("#nombret3").val(),
                "apellidop3": $("#apellidopt3").val(),
                "apellidom3": $("#apellidomt3").val(),
                "ocupacion3": $("#ocupacion3").val(),
                "parentesco3": $("#parentesco3").val(),
                "calledom3": $("#calledomt3").val(),
                "numerodom3": $("#numerodomt3").val(),
                "coloniadom3": $("#coloniadomt3").val(),
                "codigopostal3": $("#codigopostalt3").val(),
                "telefonocasa3": $("#telcasat3").val(),
                "celular3": $("#celulart3").val(),
                "correo3": $("#correot3").val(),
                "status": 1,
                "tipoescuela": 1
            }
            return tutor;
        }
        /*  datosAlumno: function () {
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
         }*/
    }
}());
$("body").on("focus", "input", function (event) {
    $(this).parent().removeClass('has-error');
});
$("body").on("focus", "textarea", function (event) {
    $(this).parent().removeClass('has-error');
});
async function convertBase64A() {
    const file = document.querySelector('#fotoalumno').files[0];
    $("#filealumno").val(await toBase64A(file));
}
const toBase64A = file => new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
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


