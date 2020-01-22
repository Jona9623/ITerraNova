
var Adminalumno = (function () {
    return {
        tablaAlumnos: function () {
            $.get("SAdminalumno", {
                ACCION: "MuestraAgregaAlumno"
            }).then(function () {
                $('#content').html(arguments[0]);
                $("#tablaalumnos").DataTable();
                $('#btnagregaA').on('click', function () {
                    Adminalumno.agregaAlumno();
                }),
                $('.editar').on('click', function () {
                    idalumno = $(this).parents("tr").find("td").eq(0).html();
                    idtutor = $(this).parents("tr").find("td").eq(1).html();
                    Adminalumno.editarAlumno(idalumno,idtutor);
                });
            });
        },
        formularios: function (argumento){
            $('#content').html(argumento);
                $('#guardatutor').on('click', function () {
                    Adminalumno.guardaTutor(Adminalumno.datosTutor(), 'GuardaTutor');
                });
                $('#guardaalumno').on('click', function () {
                    Adminalumno.guardaAlumno(Adminalumno.datosAlumno(), 'GuardaAlumno');
                });
        },
        editarAlumno: function(idalumno, idtutor){
            console.log(idalumno);
            console.log(idtutor);
            idalumno.trim();
            idtutor.trim();
            console.log(idalumno);
            console.log(idtutor);
            $.get("SAdminalumno",{
                ACCION: "editarAlumno",
                IDALUMNO: idalumno,
                IDTUTOR: idtutor
            }).then(function(){
               Adminalumno.formularios(arguments[0]); 
            });
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
                alert('Tutor guardado');
            });
        },
        guardaAlumno: function (objeto, accion) {
            $.get("SAdminalumno", {
                ACCION: accion,
                ALUMNO: JSON.stringify(objeto)
            }).then(function () {
                alert('Alumno guardado');
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
}
());


