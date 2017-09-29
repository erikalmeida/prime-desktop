package ucla.si.dao;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Conexion;
import ucla.si.modelo.movil.Presupuesto;
import ucla.si.dao.movil.ConexionDAO;
import ucla.si.modelo.ReporteServicio;
import ucla.si.modelo.movil.Log;
import ucla.si.modelo.movil.Notificacion;
import ucla.si.modelo.movil.Sistema;
import ucla.si.modelo.movil.Usuario;

public class ReporteServicioDAO extends ConexionDAO {

	public ReporteServicioDAO() {
		// System.out.println("La lista de Profesores se creo:
		// "+insertarProfesores());
	}

	public Log consultarLog(Integer id) {
		Log log = null;
		String tiraSQL = "Select * from log where id ='" + id + "' ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			if (resultSet.next()) {
				String message = resultSet.getString("message");
				Date date = resultSet.getDate("date");
				log = new Log(id, message, date);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return log;
	}

	public List<ReporteServicio> consultarReporteServicios() {
		List<ReporteServicio> reporteServicios = new ArrayList<ReporteServicio>();
		String tiraSQL = "select s.id, s.descripcion, count(ps.idservicio) as cantidad, p.fechamodificacion "
				+" from presupuestoservicio ps "
				+" inner join servicio s on s.id = ps.idservicio "
				+" inner join presupuesto p on ps.idpresupuesto = p.id "
				+" Inner join ordenServicio os on os.idPresupuesto = p.id "
				+" where os.fechaCreacion between '2017-01-01 00:00:00' and '2017-03-31 23:59:00.00' "
				+" and p.estado='Validada' "
				+" group by ps.idservicio, s.id, p.fechamodificacion "
				+" order by cantidad desc "
				+" limit 3 ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while (resultSet.next()) {
				Long idServicio = resultSet.getLong("id");
				String descripcion = resultSet.getString("descripcion");
				Integer cantidad = resultSet.getInt("cantidad");
				Date fechaModificacion = resultSet.getDate("fechamodificacion");
				ReporteServicio reporteServicio = new ReporteServicio(idServicio, descripcion, cantidad, fechaModificacion);
				reporteServicios.add(reporteServicio);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reporteServicios;
	}

}