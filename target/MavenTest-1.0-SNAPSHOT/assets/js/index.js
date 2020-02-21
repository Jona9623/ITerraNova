var tipoescuela = 0;
$('#secundaria').on('click',function(){
      sessionStorage.setItem("tipoescuela","2");
    }),
    $('#preparatoria').on('click',function(){
        sessionStorage.setItem("tipoescuela","1");
    })   
$(document).ready(function () {
    var tipoescuela = JSON.parse(sessionStorage.getItem("tipoescuela")); 
    $("#reportedis").on('click', function () {
        Reportes.reporteDisciplinar();
    }),
    $("#reporteaca").on('click', function () {
        Reportes.reporteAcademico();
    }),
    $('#amalumnos').on('click', function () {
        Adminalumno.tablaAlumnos();
    }),
    $('#ampersonal').on('click',function(){
       Adminpersonal.tablaPersonal(); 
    });
    $('#ampuesto').on('click',function(){
       Admin.tablaPuesto();
    });
    $('#amperiodo').on('click',function(){
       Admin.tablaPeriodo();
    });
    $('#amarea').on('click',function(){
       Admin.tablaArea();
    });
    $('#amcpt').on('click',function(){
       Admin.tablaCpt();
    });
    $("#guardagrado").on('click',function(){
        var grado = {
            "nombre": $("#gradoAdmin").val()
        }
        $.get("SAdministrador",{
            ACCION: "guardaGrado",
            OBJETO: JSON.stringify(grado),
            TIPOESCUELA: tipoescuela
        }).then(function(){
            swal("Hecho!", "Datos guardados correctamente", "success");
        })
    });
    $("#guardagrupo").on('click',function(){
        var grupo = {
            "nombre": $("#grupoAdmin").val()
        }
        $.get("SAdministrador",{
            ACCION: "guardaGrupo",
            OBJETO: JSON.stringify(grupo),
            TIPOESCUELA: tipoescuela
        }).then(function(){
            swal("Hecho!", "Datos guardados correctamente", "success");
        })
    });
    $("#guardanombremateria").on('click',function(){
       var materia = {
           "nombrelargo": $("#nombrelargoAdmin").val(),
           "nombrecorto": $("#nombrecortoAdmin").val()
       }
       $.get("SAdministrador",{
           ACCION: "guardaNombreMateria",
           OBJETO: JSON.stringify(materia),
           TIPOESCUELA: tipoescuela
       }).then(function(){
           swal("Hecho!", "Datos guardados correctamente", "success");
       });
    });
    /*$('#amgradogrupo').on('click',function(){
       Admin.tablaGradoGrupo();
    });*/
    $('#amtipocalificacion').on('click',function(){
       Admin.tablaTipoCalificacion();
    });
    $('#amasignacionmaterias').on('click',function(){
       Admin.tablaMateria();
    });
    
    
    
});


