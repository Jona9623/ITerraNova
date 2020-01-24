

var Reportes = (function () {  
  var grado;
  var grupo;
  return {
    reporteDisciplinar: function () {
        $.get("SReportes",{
            ACCION: "Rdisciplinar"            
        }).then(function(){
            $("#content").html(arguments[0]);
            $("#gradodisciplinar").change(function(){
             grado =  $("#gradodisciplinar").val()
            }),
            $("#grupodisciplinar").change(function(){
             grupo =  $("#grupodisciplinar").val() 
             Reportes.getAlumno(grado,grupo);
            });
            
            $("#agregafalta").on('click',function(){
                Reportes.agregaFalta();
            });
        });
    },
    getAlumno: function (grado,grupo){
        $.get("SReportes",{
            ACCION: "alumnoGradoGrupo",
            GRADO: grado,
            GRUPO: grupo
        }).then(function(){
           $("#alumnogradoD").html(arguments[0]); 
           $("#gradodisciplinar").change(function(){
             grado =  $("#gradodisciplinar").val()
            }),
            $("#grupodisciplinar").change(function(){
             grupo =  $("#grupodisciplinar").val() 
             Reportes.getAlumno(grado,grupo);
            });
            $("#agregafalta").on('click',function(){
                Reportes.agregaFalta();
            });
        });
    },
    reporteAcademico: function (){
      $.get("SReportes",{
          ACCION: "Racademico"
      }).then(function(){
         $("#content").html(arguments[0]);
         $("#agregacomp").on('click',function(){
             Reportes.agregaComportamiento();
         })
      });  
    },
  };
}());