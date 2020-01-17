

var Principal = (function(){
    return {
        Secundaria: function (){
            $.get("SPrincipal",{
                ACCION: "Secundaria"
            }).then(function(){
               $('#content').html(arguments[0]); 
            });
        },
        Preparatoria: function (){
            
            $.get("SPrincipal",{
                ACCION: "Preparatoria"
            }).then(function(){
               $('#content').html(arguments[0]); 
            });
        }
    }
}());

