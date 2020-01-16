use terranova;
insert into tb_personal (nombre,apellidopaterno,apellidomaterno,fechanacimiento,curp,municipionacimiento,estadonacimiento,nacionalidad,sexo,calledomicilio,numerodomicilio,coloniadomicilio,codigopostal,
telefonocasa,celular,correo,nss,rfc,nivelmaxestudios,licenciatura,maestria,doctorado,r_puesto,status) values 
('Diana Ivette','Montejo','Arroyo','1988-01-09','DIMA857362HVZNRW03','Veracruz','Veracruz','Mexicano',2,'Veracruz',23,
'Veracruz',986351,'394837','2291846142','diana.ivette@gmail.com','2345678912','DIMA757373','Licenciatura','Sistemas',null,null,
2,1), ('Carlos Arturo','Ceron','Alvarez','1988-06-01','CELO857362HVZNRW03','Veracruz','Veracruz','Mexicano',1,'Veracruz',123,
'Veracruz',986541,'323437','2291841267','carlos.ceron@gmail.com','245328912','CELO757373','Doctorado','Sistemas','Ingenieria de sofware'
,'Ingenieria',2,1),('Luis Felipe','Marin','Urias','1988-11-12','LUFE857362HVZNRW03','Veracruz','Veracruz','Mexicano',1,'Veracruz',90,
'Veracruz',954321,'34564337','2291846142','luis.marin@gmail.com','2346435912','LUFE757373','Doctorado','Informatica','Robotica','Inteligencia artificial',
2,1);
select * from tb_personal;