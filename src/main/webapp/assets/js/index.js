$(document).ready(function () {
    $("#reportedis").on('click', function () {
        Reportes.reporteDisciplinar();
    }),
            $("#reporteaca").on('click', function () {
        Reportes.reporteAcademico();
    }),
            $('#amalumnos').on('click', function () {
        Adminalumno.tablaAlumnos();
    });
});


