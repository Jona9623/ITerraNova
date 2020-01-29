select * from tb_reportedisciplinar;

select * from ((((((((((tb_reportedisciplinar inner join tb_alumnos on tb_reportedisciplinar.r_alumno = tb_alumnos.idTb_Alumnos)inner join tb_personal on tb_reportedisciplinar.r_personal = tb_personal.idTb_Personal)
inner join tb_materia on tb_reportedisciplinar.r_materia = tb_materia.idTb_Materia)inner join ct_incidente on tb_reportedisciplinar.r_tipoincidente = ct_incidente.idCt_incidente)
inner join ct_periodoescolar on tb_reportedisciplinar.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar)inner join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria)
inner join ct_grado on tb_alumnos.r_grado = ct_grado.idCt_Grado)inner join ct_grupo on tb_alumnos.r_grupo = ct_grupo.idCt_Grupo)inner join tb_personal as tb1 on tb_reportedisciplinar.r_personalllena = tb1.idTb_Personal)
inner join tb_personal as tb2 on tb_reportedisciplinar.r_personalsolicita = tb2.idTb_Personal)
 where tb_reportedisciplinar.status = 1 and tb_reportedisciplinar.tipoescuela = 1;
 
 select * from (tb_reportedisciplinar inner join tb_personal on tb_reportedisciplinar.r_personalsolicita = tb_personal.idTb_Personal);
  select * from (tb_reportedisciplinar inner join tb_personal on tb_reportedisciplinar.r_personalllena = tb_personal.idTb_Personal) where tb_reportedisciplinar.status =1 and tb_Reportedisciplinar.tipoescuela = 1;