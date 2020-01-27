use terranova;
insert into tb_alumnos (matricula,nombre,apellidopaterno,apellidomaterno,fechanacimiento,curp,municipionacimiento,estadonacimiento,
nacionalidad,sexo,calledomicilio,numerodomicilio,coloniadomicilio,codigopostal,telefonocasa,celularalumno,correoalumno,nivelcursa,
r_grado,r_grupo,r_area,r_cpt,plantelprocedencia,nivelanterior,gradoanterior,turnoanterior,municipioanterior,r_tutor,status,tipoescuela) values
('zS15001384','Jonathan','Trujillo','Capetillo','1996-12-23','TUCJ961223HVZRPN02','Veracruz','Veracruz','Mexicano',1,
'Av jamapa poniente',0,'Jamapa',94260,'018001234','2294603816','jonathan9623@hotmail.es','Alto',3,4,1,2,'IMA',4,
6,1,'Tejar',1,1,1),('zS17111484','Juan','Gonzales','Medina','1996-01-22','JUCA961212HVZRPN02','Veracruz','Veracruz','Mexicano',1,
'Veracruz',23,'Veracruz',99262,'01899567','2291612356','Juanca@hotmail.com','Alto',3,3,1,2,'Cbtis',3,
6,2,'Veracruz',2,1,1),('zS14938163','Maria','Barradas','Mujica','1998-08-01','MABA961223HVZRPN02','Queretaro','Queretaro','Mexicano',2,
'Boca del Rio',67,'Boca del Rio',91290,'01800890','2294617912','maria@hotmail.es','Alto',2,2,2,2,'Escuela Queretaro',2,
6,2,'Queretaro',1,1,1),('zS13563434','Melissa Guadalupe','Perez','Cabrera','1994-12-12','MPCG961212HVZRPN02','Veracruz','Veracruz','Mexicano',2,
'Av jamapa oriente',3,'Jamapa',94260,'018001234','2292354612','melissa.perez@hotmail.es','Alto',1,1,2,1,'Conalep',2,
6,1,'Veracruz',3,1,1);
select * from tb_alumnos;

update `terranova`.`tb_alumnos` set `status` = 2 where `idTb_Alumnos` = 10;


select tb_alumnos.r_tutor, tb_alumnos.idTb_Alumnos, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno, tb_alumnos.matricula, ct_grado.nombre, ct_grupo.nombre, tb_tutor.nombre from
(((tb_alumnos inner join ct_grado on tb_alumnos.r_grado = ct_grado.idCt_Grado) inner join ct_grupo on tb_alumnos.r_grupo = ct_grupo.idCt_Grupo) inner join
tb_tutor on tb_alumnos.r_tutor = tb_tutor.idTb_Tutor) where tb_alumnos.status = 1 and tb_alumnos.tipoescuela = 1;

UPDATE `terranova`.`tb_alumnos` SET `matricula` = 'e', `nombre` = 'e', `apellidopaterno` = 'e', `apellidomaterno` = 'e', `fechanacimiento` = '1996-12-24', `curp` = 'e', 
`municipionacimiento` = 'e', `estadonacimiento` = 'e', `nacionalidad` = 'e', `sexo` = '1', `calledomicilio` = 'ere', `numerodomicilio` = '2', `coloniadomicilio` = 'rgr',
 `codigopostal` = '5645', `telefonocasa` = '54645', `celularalumno` = '4546', `correoalumno` = 'grtgr@refg.rge', `nivelcursa` = 'regrg', `r_grado` = '2', `r_grupo` = '3',
 `r_area` = '1', `r_cpt` = '1', `plantelprocedencia` = 'jtyujty', `nivelanterior` = '5', `gradoanterior` = '2', `turnoanterior` = '1', `municipioanterior` = 'sfs', `r_tutor` = '1' 
 WHERE (`idTb_Alumnos` = '10');
