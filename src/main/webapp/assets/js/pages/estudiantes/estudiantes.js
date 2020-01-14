
var EstudianteDatosGenerales = (function () {
    return {
        cargaForm: function () {
            $.get("Estudiante", {
                ACCION: "Form_alumnos"
            }).then(function () {
                $("#content").html(arguments[0]);
                $("#example4").DataTable();
                $("#btn_agrega").on('click', function () {
                    EstudianteDatosGenerales.agregar();
                }),
                $("#masinfo").on('click',function (){
                    EstudianteDatosGenerales.muestra();
                });
            });
        },
        agregar: function () {
            $.get("Estudiante", {
                ACCION: "Agrega_alumnos"
            }).then(function () {
                $("#content").html(arguments[0]);
            });
        },
        muestra: function(){
            $.get("Estudiante",{
                ACCION: "Muestra_Estudiante"
            }).then(function(){
                $("#content").html(arguments[0]);
                $("#infopafi").on('click', function(){
                    EstudianteDatosGenerales.muestrapafi();
                });
            });
        },
        muestrapafi: function(){
            $.get("Estudiante",{
                ACCION: "Info_pafi"
            }).then(function(){
                $("#content").html(arguments[0]);
            });
        }
    }
}());
var EstudianteBuzon = (function(){
    return{
        cargaBuzon: function(){
            $.get("Estudiante",{
                ACCION: "Form_buzon"
            }).then(function(){
                $("#content").html(arguments[0]);
            });
        }
    }
}());
var EstudiantePafi = (function(){    
    return{
        cargaPafi: function(){           
            $.get("Estudiante",{
                ACCION: "Pafi"
            }).then(function(){              
                $("#content").html(arguments[0]);
            });
        }
    }
}());
var EstudianteMovilidad = (function(){    
    return{
        cargaMovi: function(){           
            $.get("Estudiante",{
                ACCION: "Movilidad"
            }).then(function(){              
                $("#content").html(arguments[0]);
            });
        }
    }
}());


