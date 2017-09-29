
package ucla.si.servicio;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.MotivoDAO;
import ucla.si.modelo.Motivo;



@Service("servicioMotivo")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioMotivo{
	@Autowired
	private MotivoDAO motivoDAO;

		
	public boolean incluirMotivo(Motivo motivo){
		boolean guardado = false;
		try {
			guardado = motivoDAO.incluirMotivo(motivo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

		public boolean editarMotivo(Motivo motivo){
			boolean guardado = false;
			try {
				guardado = motivoDAO.editarMotivo(motivo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return guardado;
		}
		
		public List<Motivo> buscarMotivos(String descripcion) {
			List<Motivo> motivos = new ArrayList<Motivo>();
			try {
				motivos = motivoDAO.buscarMotivos(descripcion);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return motivos;
		}
		
		public List<Motivo> listarMotivos(){
			List<Motivo> motivos = new ArrayList<Motivo>();
			try {
				motivos = motivoDAO.listarMotivos();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return motivos;
		}

}
