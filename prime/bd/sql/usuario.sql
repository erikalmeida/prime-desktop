insert into persona (id,nombre,apellido,cedula,estatus,fechacreacion) 
values(1,'Andy','Aldana','V-12345678','Activo','2017-01-21 16:00:00'),
(2,'Jhon','Perez','V-17573439','Activo','2017-01-21 16:00:00');

insert into usuario (id,correo,contrasenna,pregunta,respuesta,estatus,fechacreacion,idpersona)
values(1,'andy@prime.com',md5('123'),'nombre del sistema','prime','Activo','2017-01-21 16:00:00',1),
(2,'jhon@prime.com',md5('123'),'nombre del perro','milo','Activo','2017-01-21 16:00:00',2);
