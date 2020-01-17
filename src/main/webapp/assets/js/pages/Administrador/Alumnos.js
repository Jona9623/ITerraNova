
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
                });
            });
        },
        agregaAlumno: function () {
            $.get("SAdminalumno", {
                ACCION: "AgregaAlumno"
            }).then(function () {
                $('#content').html(arguments[0]);
                $('#guardatutor').on('click', function () {
                    Adminalumno.guardaTutor(Adminalumno.datosTutor(), 'GuardaTutor');
                })
            });
        },
        guardaTutor: function (objeto, accion){
          $.get("SAdminalumno",{
              ACCION: accion,
              TUTOR: JSON.stringify(objeto)
          }).then(function(){
              swal("Hecho!", "Datos guardados correctamente", "success");
          })  
        },
        datosTutor: function () {
            var tutor = {
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
    }
}
());


