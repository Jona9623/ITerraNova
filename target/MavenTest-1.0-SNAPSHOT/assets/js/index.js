$(document).ready(function(){
   $("#reportedis").on('click',function(){
      Reportes.reporteDisciplinar(); 
   }),
   $("#reporteaca").on('click',function(){
       Reportes.reporteAcademico();
   }),
   $("#buzon").on('click',function(){
       EstudianteBuzon.cargaBuzon();
   }),
   $("#pafisalum").on('click',function(){
       EstudiantePafi.cargaPafi();
   }),
   $("#movilidad").on('click',function(){
       EstudianteMovilidad.cargaMovi();
   });
   $("#pafis").on('click',function(){       
      Pafis.cargarModulo(); 
   });
});


