/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Admin = (function(){
    return {
        tablaPuesto: function(){
            $.get("SAdministrador",{
                ACCION: "tablaPuesto"
            }).then(function (){
               $("#content").html(arguments[0]); 
            });
        },
        tablaPeriodo: function(){
            $.get("SAdministrador",{
                ACCION: "tablaPeriodo"
            }).then(function (){
               $("#content").html(arguments[0]); 
            });
        },
        tablaArea: function(){
            $.get("SAdministrador",{
                ACCION: "tablaArea"
            }).then(function (){
               $("#content").html(arguments[0]); 
            });
        },
        tablaCpt: function(){
            $.get("SAdministrador",{
                ACCION: "tablaCpt"
            }).then(function (){
               $("#content").html(arguments[0]); 
            });
        },
        tablaGrado: function(){
            $.get("SAdministrador",{
                ACCION: "tablaGrado"
            }).then(function (){
               $("#content").html(arguments[0]); 
            });
        },
        tablaGrupo: function(){
            $.get("SAdministrador",{
                ACCION: "tablaGrupo"
            }).then(function (){
               $("#content").html(arguments[0]); 
            });
        },
        tablaTipoCalificacion: function(){
            $.get("SAdministrador",{
                ACCION: "tablaTipoCalificacion"
            }).then(function (){
               $("#content").html(arguments[0]); 
            });
        },
        tablaMateria: function(){
            $.get("SAdministrador",{
                ACCION: "tablaMateria"
            }).then(function (){
               $("#content").html(arguments[0]); 
            });
        },
    }
}());

