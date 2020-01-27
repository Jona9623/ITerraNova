use terranova;
insert into tb_materiapersonal (r_materia,r_personal,status,tipoescuela) values (1,1,1,1),(2,1,1,1),(3,2,1,1),(4,2,1,1),(5,1,1,1);
select tb_materia.nombrecorto, tb_personal.nombre, tb_personal.apellidopaterno,tb_personal.apellidomaterno, tb_materiapersonal.idTb_MateriaPersonal from ((tb_materiapersonal inner join
tb_materia on tb_materiapersonal.r_materia = tb_materia.idTb_Materia)inner join tb_personal on tb_materiapersonal.r_personal = tb_personal.idTb_Personal)
 where tb_materiapersonal.status  = 1 and tb_materiapersonal.tipoescuela = 1;
