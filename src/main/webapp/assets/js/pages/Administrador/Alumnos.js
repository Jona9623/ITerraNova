
var Adminalumno = (function(){
    return {
        tablaAlumnos: function(){
            $.get("SAdminalumno",{
                ACCION: "MuestraAgregaAlumno"
            }).then(function(){
               $('#content').html(arguments[0]);
               $("#tablaalumnos").DataTable();
               $('#btnagregaA').on('click',function(){
                  Adminalumno.agregaAlumno(); 
               });
            });
        },
        agregaAlumno: function(){
            $.get("SAdminalumno",{
                ACCION: "AgregaAlumno"
            }).then(function(){
               $('#content').html(arguments[0]); 
            });
        },
        tablaPersonal: function(){
          $.get("SAdminpersonal",{
              ACCION: "MuestraAgregaPersonal"
          }).then(function(){
              $('#content').html(arguments[0]);
          })  
        },
    }
}());


