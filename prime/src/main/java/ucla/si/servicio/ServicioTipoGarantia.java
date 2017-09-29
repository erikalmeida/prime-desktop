package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoGarantiaDAO;
import ucla.si.modelo.TipoGarantia;

@Service("servicioTipoGarantia")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioTipoGarantia {
	
	@Autowired
	private TipoGarantiaDAO tipoGarantiaDAO;
	
	public boolean incluirTipoGarantia(TipoGarantia tipoGarantia){
		boolean guardado = false;
		try {
			guardado = tipoGarantiaDAO.incluirTipoGarantia(tipoGarantia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoGarantia(TipoGarantia tipoGarantia){
		boolean guardado = false;
		try {
			guardado = tipoGarantiaDAO.editarTipoGarantia(tipoGarantia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoGarantia> listarTipoGarantias(){
		List<TipoGarantia> tipoGarantias = new ArrayList<TipoGarantia>();
		try {
			tipoGarantias = tipoGarantiaDAO.listarTipoGarantias();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoGarantias;
	}

}
