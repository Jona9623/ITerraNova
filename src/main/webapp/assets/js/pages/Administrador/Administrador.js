/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Admin = (function () {
    var tipoescuela = JSON.parse(sessionStorage.getItem("tipoescuela"));
    return {
        tablaPuesto: function () {
            $.get("SAdministrador", {
                ACCION: "tablaPuesto"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#guardapuesto").on('click', function () {
                    Admin.guardaPuesto();
                });
                $(".editarpuesto").on('click', function () {
                    idpuesto = $(this).parents("tr").find("td").eq(0).html();
                    Admin.editarPuesto(idpuesto);
                });
                $(".eliminarpuesto").on('click', function () {
                    idpuesto = $(this).parents("tr").find("td").eq(0).html();
                    swal({
                        title: "Estas seguro?",
                        text: "Se eliminara el registro",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Confirmar",
                        closeOnConfirm: false
                    }, function () {
                        swal("Eliminado exitosamente!", "Click en el bot\u00F3n!", "success");
                        Admin.eliminarPuesto(idpuesto);
                    });
                });
                $("#tablapuesto").DataTable({
                    "scrollX": true
                });
            });
        },
        guardaPuesto: function () {
            var puesto = {
                "nombre": $("#puestoAdmin").val()
            }
            $.get("SAdministrador", {
                ACCION: "guardaPuesto",
                OBJETO: JSON.stringify(puesto),
                TIPOESCUELA: tipoescuela
            }).then(function () {
                swal("Hecho!", "Datos guardados correctamente", "success");
            });
        },
        editarPuesto: function (idpuesto) {
            $.get("SAdministrador", {
                ACCION: "editarPuesto"
            })
        },
        eliminarPuesto: function(idpuesto){
          $.get("SAdministrador",{
              ACCION: "eliminaPuesto",
              ID: idpuesto
          }).then(function(){
             Admin.tablaPuesto(); 
          });  
        },
        tablaPeriodo: function () {
            $.get("SAdministrador", {
                ACCION: "tablaPeriodo"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#guardaperiodo").on('click', function(){
                   Admin.guardaPeriodo(); 
                });
                $(".eliminarperiodo").on('click',function(){
                    idperiodo = $(this).parents("tr").find("td").eq(0).html();
                    swal({
                        title: "Estas seguro?",
                        text: "Se eliminara el registro",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Confirmar",
                        closeOnConfirm: false
                    }, function () {
                        swal("Eliminado exitosamente!", "Click en el bot\u00F3n!", "success");
                        Admin.eliminarPeriodo(idperiodo);
                    });
                });
                $("#tablaperiodo").DataTable({
                    "scrollX": true
                });
            });
        },
        guardaPeriodo: function(){
          var periodo = {
              "nombre": $("#periodoAdmin").val(),
              "fechainicio": $("#fechainicioAdmin").val(),
              "fechafin": $("#fechafinAdmin").val()
          }
          $.get("SAdministrador",{
              ACCION: "guardaPeriodo",
              OBJETO: JSON.stringify(periodo)
          }).then(function(){
              swal("Hecho!", "Datos guardados correctamente", "success");
          });
                 
        },
        eliminarPeriodo: function(idperiodo){
            $.get("SAdministrador",{
                ACCION: "eliminaPeriodo",
                ID: idperiodo
            }).then(function(){
               Admin.tablaPeriodo(); 
            });
        },
        tablaArea: function () {
            $.get("SAdministrador", {
                ACCION: "tablaArea"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#guardaarea").on('click',function(){
                    alert("entra");
                   Admin.guardaArea(); 
                });
                $(".eliminararea").on('click',function(){
                    idarea = $(this).parents("tr").find("td").eq(0).html();
                    swal({
                        title: "Estas seguro?",
                        text: "Se eliminara el registro",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Confirmar",
                        closeOnConfirm: false
                    }, function () {
                        swal("Eliminado exitosamente!", "Click en el bot\u00F3n!", "success");
                        Admin.eliminarArea(idarea);
                    });
                });
                $("#tablaarea").DataTable({
                    "sccrollX": true
                });
            });
        },
        guardaArea: function(){
          var area = {
              "nombre": $("#areaAdmin").val()
          }
          $.get("SAdministrador",{
              ACCION: "guardaArea",
              OBJETO: JSON.stringify(area)
          }).then(function(){
              swal("Hecho!", "Datos guardados correctamente", "success");
          });
        },
        eliminarArea: function(idarea){
          $.get("SAdministrador",{
              ACCION: "eliminaArea",
              ID: idarea
          }).then(function(){
              Admin.tablaArea();
          })  
        },
        tablaCpt: function () {
            $.get("SAdministrador", {
                ACCION: "tablaCpt"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#guardarcpt").on('click',function(){
                   Admin.guardaCpt(); 
                });
                $(".eliminarcpt").on('click',function(){
                    idcpt = $(this).parents("tr").find("td").eq(0).html();
                    swal({
                        title: "Estas seguro?",
                        text: "Se eliminara el registro",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Confirmar",
                        closeOnConfirm: false
                    }, function () {
                        swal("Eliminado exitosamente!", "Click en el bot\u00F3n!", "success");
                        Admin.eliminarCpt(idcpt);
                    });
                });
                $("#tablacpt").DataTable({
                    "scrollX": true
                });
            });
        },
        guardaCpt: function(){
          var cpt = {
              "nombre": $("#cptAdmin").val()
          }
          $.get("SAdministrador",{
              ACCION: "guardaCpt",
              OBJETO: JSON.stringify(cpt)
          }).then(function(){
              swal("Hecho!", "Datos guardados correctamente", "success");
          });
        },
        eliminarCpt: function (idcpt){
            $.get("SAdministrador",{
                ACCION: "eliminaCpt",
                ID: idcpt
            }).then(function(){
               Admin.tablaCpt(); 
            });
        },
        tablaGradoGrupo: function () {
            $.get("SAdministrador", {
                ACCION: "tablaGradoGrupo"
            }).then(function () {
                $("#content").html(arguments[0]);
            });
        },
        tablaTipoCalificacion: function () {
            $.get("SAdministrador", {
                ACCION: "tablaTipoCalificacion"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#guardatipocali").on('click',function(){
                   Admin.guardaTipoCali(); 
                });
                $(".eliminatipocali").on('click',function(){
                    idtipo = $(this).parents("tr").find("td").eq(0).html();
                    swal({
                        title: "Estas seguro?",
                        text: "Se eliminara el registro",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Confirmar",
                        closeOnConfirm: false
                    }, function () {
                        swal("Eliminado exitosamente!", "Click en el bot\u00F3n!", "success");
                        Admin.eliminarTipoCali(idtipo);
                    });
                });
                $("#tablatipocali").DataTable({
                    "scrollX": true
                });
            });
        },
        guardaTipoCali: function(){
          var tipocali = {
              "nombre": $("#tipocaliAdmin").val()
          }
          $.get("SAdministrador",{
              ACCION: "guardaTipoCali",
              OBJETO: JSON.stringify(tipocali)
          }).then(function(){
              swal("Hecho!", "Datos guardados correctamente", "success");
          });
        },
        eliminarTipoCali: function (idtipo){
          $.get("SAdministrador",{
              ACCION: "eliminaTipoCali",
              ID: idtipo
          }).then(function(){
              Admin.tablaTipoCalificacion();
          })  
        },
        tablaMateria: function () {
            $.get("SAdministrador", {
                ACCION: "tablaMateria"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#guardamateria").on('click',function(){
                   Admin.guardaMateria(); 
                });
                $(".eliminamateria").on('click',function(){
                   idmateria = $(this).parents("tr").find("td").eq(0).html();
                    swal({
                        title: "Estas seguro?",
                        text: "Se eliminara el registro",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Confirmar",
                        closeOnConfirm: false
                    }, function () {
                        swal("Eliminado exitosamente!", "Click en el bot\u00F3n!", "success");
                        Admin.eliminaMateria(idmateria);
                    });
                });
                $("#tablamateria").DataTable({
                    "scrollX": true
                });
            });
        },
        guardaMateria: function(){
            var materia = {
                "rdatosmateria": $("#materiaAdmin").val(),
                "rgrado": $("#gradoselect").val(),
                "rgrupo": $("#gruposelect").val(),
                "rarea": $("#areaselect").val(),
                "rcpt": $("#cptselect").val()
            }
            $.get("SAdministrador",{
                ACCION: "guardaMateria",
                OBJETO: JSON.stringify(materia)
            }).then(function(){
                swal("Hecho!", "Datos guardados correctamente", "success");
            });
        },
        eliminaMateria: function(idmateria){
            $.get("SAdministrador",{
                ACCION: "eliminaMateria",
                ID: idmateria
            }).then(function(){
               Admin.tablaMateria(); 
            });
        }
    }
}());

