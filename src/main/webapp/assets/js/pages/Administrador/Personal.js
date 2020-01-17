var Adminpersonal = (function(){
    return {
        tablaPersonal: function(){
          $.get("SAdminpersonal",{
              ACCION: "MuestraAgregaPersonal"
          }).then(function(){
              $('#content').html(arguments[0]);
              $('#tablapersonal').DataTable();
              $('#btnagregaP').on('click',function(){
                  Adminpersonal.agregaPersonal(); 
               });
          })  
        },
        agregaPersonal: function(){
          $.get("SAdminpersonal",{
              ACCION: "AgregaPersonal"
          }).then(function(){
             $('#content').html(arguments[0]); 
          });  
        },
    }
}());


