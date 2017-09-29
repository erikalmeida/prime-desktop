select s.descripcion, count(s.id)
from servicio s 
Inner join presupuestoServicio sp on sp .idServicio=s.id 
Inner join presupuesto p on p.id=sp.idPresupuesto
Inner join ordenServicio os on os.idPresupuesto=p.id
where os.fechaCreacion between '2017-03-04 00:00:00' and '2017-03-05 00:00:00' and p.estado='Validada' 
group by s.id