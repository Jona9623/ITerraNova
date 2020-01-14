var Pafis = (function () {  
  
  return {
    cargarModulo: function () { 
        $.get("Pafis",{
            ACCION: "MODULO"
        }).then(function(){
            $("#content").html(arguments[0]);
            $("#example4").DataTable();
            $("#agregar").unbind('click').bind('click',function(){
                Pafis.agregar();
            });            
        });
    },
    agregar: function(){
        $.get("Pafis",{
            ACCION: "AGREGAR"            
        }).then(function(){
           $("#content").html(arguments[0]);
        });
    }
  };
}());