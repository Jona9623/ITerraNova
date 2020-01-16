use terranova;
insert into tb_alumnos (matricula,nombre,apellidopaterno,apellidomaterno,fechanacimiento,curp,municipionacimiento,estadonacimiento,
nacionalidad,sexo,calledomicilio,numerodomicilio,coloniadomicilio,codigopostal,telefonocasa,celularalumno,correoalumno,nivelcursa,
r_grado,r_grupo,r_area,r_cpt,plantelprocedencia,nivelanterior,gradoanterior,turnoanterior,municipioanterior,r_tutor,status) values
('zS15001384','Jonathan','Trujillo','Capetillo','1996-12-23','TUCJ961223HVZRPN02','Veracruz','Veracruz','Mexicano',1,
'Av jamapa poniente',0,'Jamapa',94260,'018001234','2294603816','jonathan9623@hotmail.es','Alto',3,4,1,2,'IMA',4,
6,1,'Tejar',1,1),('zS17111484','Juan','Gonzales','Medina','1996-01-22','JUCA961212HVZRPN02','Veracruz','Veracruz','Mexicano',1,
'Veracruz',23,'Veracruz',99262,'01899567','2291612356','Juanca@hotmail.com','Alto',3,3,1,2,'Cbtis',3,
6,2,'Veracruz',2,1),('zS14938163','Maria','Barradas','Mujica','1998-08-01','MABA961223HVZRPN02','Queretaro','Queretaro','Mexicano',2,
'Boca del Rio',67,'Boca del Rio',91290,'01800890','2294617912','maria@hotmail.es','Alto',2,2,2,2,'Escuela Queretaro',2,
6,2,'Queretaro',1,1),('zS13563434','Melissa Guadalupe','Perez','Cabrera','1994-12-12','MPCG961212HVZRPN02','Veracruz','Veracruz','Mexicano',2,
'Av jamapa oriente',3,'Jamapa',94260,'018001234','2292354612','melissa.perez@hotmail.es','Alto',1,1,2,1,'Conalep',2,
6,1,'Veracruz',3,1);
select * from tb_alumnos;