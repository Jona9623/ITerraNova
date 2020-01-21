var Adminpersonal = (function () {
    return {
        tablaPersonal: function () {
            $.get("SAdminpersonal", {
                ACCION: "MuestraAgregaPersonal"
            }).then(function () {
                $('#content').html(arguments[0]);
                $('#tablapersonal').DataTable();
                $('#btnaregarP').on('click', function () {
                    Adminpersonal.agregaPersonal();
                });
            });
        },
        agregaPersonal: function () {
            $.get("SAdminpersonal", {
                ACCION: "AgregaPersonal"
            }).then(function () {
                $('#content').html(arguments[0]);
                $('#guardapersonal').on('click', function () {
                    Adminpersonal.guardaPersonal(Adminpersonal.datosPersonal(), 'GuardaPersonal');
                });
            });
        },
        guardaPersonal: function (objeto, accion) {
            $.get("SAdminpersonal", {
                ACCION: accion,
                PERSONAL: JSON.stringify(objeto)
            }).then(function () {
                alert('Personal guardado');
                Adminpersonal.tablaPersonal();
            });
        },
        datosPersonal: function () {
            var personal = {
                "nombre": $('#nombrep').val(),
                "apellidop": $("#apellidopp").val(),
                "apellidom": $("#apellidomp").val(),
                "fechanacimiento": $("#fechanap").val(),
                "curp": $("#curpp").val(),
                "municipionac": $("#municipionacp").val(),
                "estadonac": $("#estadonacp").val(),
                "nacionalidad": $("#nacionalidadp").val(),
                "sexo": $("input[name = 'sexop']:checked").val(),
                "calledom": $("#calledomp").val(),
                "numerodom": $("#numerodomp").val(),
                "coloniadom": $("#coloniadomp").val(),
                "codigopostal": $("#codigopostalp").val(),
                "telefonocasa": $("#telcasatp").val(),
                "celular": $("#celularp").val(),
                "correo": $("#correop").val(),
                "nss": $("#nss").val(),
                "rfc": $("#rfc").val(),
                "nivelestudios": $("#nivelmax").val(),
                "licenciatura": $("#licenciatura").val(),
                "maestria": $("#maestria").val(),
                "doctorado": $("#doctorado").val(),
                "rpuesto": $("#puesto").val(),
                "status": 1,
                "tipoescuela": 1
            }
            return personal
        }
    }
}());


