

var Reportes = (function () {  
  
  return {
    reporteDisciplinar: function () {
        $.get("SReportes",{
            ACCION: "Rdisciplinar"            
        }).then(function(){
            $("#content").html(arguments[0]);
            $("#agregafalta").on('click',function(){
                Reportes.agregaFalta();
            });
        });
    },
    agregaFalta: function(){
      $.get("SReportes",{
          ACCION: "AgregaFalta"
      }).then(function(){
          $("#content").html(arguments[0]);
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
    agregaComportamiento: function(){
      $.get("SReportes",{
          ACCION: "AgregaComportamiento"
      }).then(function(){
         $("#content").html(arguments[0]); 
      });  
    },
    mostrargeneral(npersonal){
        $.get("Academico",{
            ACCION: "verdatosgeneral",
            npersonal : npersonal
        }).then(function(){
           $("#content").html(arguments[0]);
        });                       
    },
    agregar: function(){
        $.get("Academico",{
            ACCION: "AGREGAR"            
        }).then(function(){
           $("#content").html(arguments[0]);
           $("#guardar").unbind('click').bind('click',function(){
               Academico.guardar({
                   Nombre : $("inputNombre").val(),
                   Apellido_Paterno : $("inputApellidoPaterno").val()
               });
           });
        });
    },
    guardar : function(datos){
        $.get("Academico",{
            ACCION: "GUARDAR",
            DATOS : datos
        }).then(function(){
           $("#content").html(arguments[0]);
           $("#guardar").unbind('click').bind('click',function(){
               Academico.guardar();
           });
        });
    }
  };
}());