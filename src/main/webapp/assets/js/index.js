$(document).ready(function () {
    $('#secundaria').on('click',function(){
       Principal.Secundaria(); 
    }),
    $('#preparatoria').on('click',function(){
       Principal.Preparatoria(); 
    }),
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
            OBJETO: JSON.stringify(grado)
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
            OBJETO: JSON.stringify(grupo)
        }).then(function(){
            swal("Hecho!", "Datos guardados correctamente", "success");
        })
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


