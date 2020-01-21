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
});


