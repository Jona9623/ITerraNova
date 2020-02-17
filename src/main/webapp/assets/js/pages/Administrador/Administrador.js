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
               $("#tablapuesto").DataTable({
                   "scrollX": true
               });
            });
        },
        tablaPeriodo: function(){
            $.get("SAdministrador",{
                ACCION: "tablaPeriodo"
            }).then(function (){
               $("#content").html(arguments[0]); 
               $("#tablaperiodo").DataTable({
                   "scrollX": true
               });
            });
        },
        tablaArea: function(){
            $.get("SAdministrador",{
                ACCION: "tablaArea"
            }).then(function (){
               $("#content").html(arguments[0]); 
               $("#tablaarea").DataTable({
                   "sccrollX": true
               });
            });
        },
        tablaCpt: function(){
            $.get("SAdministrador",{
                ACCION: "tablaCpt"
            }).then(function (){
               $("#content").html(arguments[0]); 
               $("#tablacpt").DataTable({
                   "scrollX": true
               });
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
               $("#tablatipocali").DataTable({
                   "scrollX": true
               });
            });
        },
        tablaMateria: function(){
            $.get("SAdministrador",{
                ACCION: "tablaMateria"
            }).then(function (){
               $("#content").html(arguments[0]); 
               $("#tablamateria").DataTable({
                   "scrollX": true
               });
            });
        },
    }
}());

