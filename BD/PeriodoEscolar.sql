use terranova;
insert into ct_periodoescolar (nombre,status,tipoescuela) values ('Agosto 2019-Diciembre 2019',1,1),('Enero 2020-Mayo 2020',1,1),('Agosto 2020-Diciembre 2020',1,1);
select * from ct_periodoescolar order by idCt_PeriodoEscolar desc;
select * from ct_periodoescolar where status = 1 and tipoescuela = 1 order by idCt_PeriodoEscolar desc;