use terranova;
insert into tb_tutor (nombre,apellidopaterno,apellidomaterno,ocupacion,parentesco,calledomicilio,numerodomicilio,
coloniadomicilio,codigopostal,telefonocasa,celular,correo,status) values ('Dolores','Capetillo','Perez','Ama de casa','Madre',
'Av jamapa poniente',0,'Jamapa',94260,01800232,2291837462,'dolores.capetillo@gmail.com',1),('Ventura','Trujillo','Quintana','Pintor','Padre',
'Extranjero',0,'Extranjero',34760,13450232,2297845421,'ventura.trujillo@gmail.com',1),('Ivan','Molina','Juarez','Contador','Tio',
'Av Diaz Miron',112,'Veracruz',97560,0189372,2291534821,'ivan.juarez@gmail.com',1),('Monica','Gutierrez','Paz','Doctora','Madre',
'Av Xalapa',564,'Veracruz',94760,11340232,2299345262,'monica.paz@gmail.com',1),('Jorge Esteban','Carrillo','Zamora','Ingeniero','Hermano',
'Venustiano Carranza',12,'Caranza',98960,01823612,2298152681,'jorge.carrillo@gmail.com',1);
select * from tb_tutor;