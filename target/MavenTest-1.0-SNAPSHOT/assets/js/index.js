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
    $('#amgrado').on('click',function(){
       Admin.tablaGrado();
    });
    $('#amgrupo').on('click',function(){
       Admin.tablaGrupo();
    });
    $('#amtipocalificacion').on('click',function(){
       Admin.tablaTipoCalificacion();
    });
    $('#amasignacionmaterias').on('click',function(){
       Admin.tablaMateria();
    });
    
    
    
});


