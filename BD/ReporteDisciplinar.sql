use terranova;
insert into tb_reportedisciplinar (r_alumno,r_personal,hora,fecha,fechareporte,r_personalsolicita,r_personalllena,r_materia,lugar,r_tipoincidente,nivel,descripcion,foto,status,tipoescuela) values
(1,1,'13:45:00','2020-01-13','2020-01-13',3,1,3,'Salon 3-a',2,3,'Golpeo aun compañero, argumenta que lo insultó pero no hay preubas','image.jpg',1,1),
(1,2,'08:32:00','2020-01-09','2020-01-09',1,2,null,'Patio',1,2,'Insulto a un maestro','image2.jpg',1,1),
(1,1,'09:21:00','2020-01-11','2020-01-11',1,1,null,'Cafeteria',3,2,'El joven se puso a llorar','image3.jpg',1,1);
select * from tb_reportedisciplinar;
