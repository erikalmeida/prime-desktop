package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.GarantiaDAO;
import ucla.si.modelo.Garantia;


@Service("servicioGarantia")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioGarantia {
	
	@Autowired
	private GarantiaDAO garantiaDAO;
	
	public boolean incluirGarantia(Garantia garantia){
		boolean guardado = false;
		try {
			guardado = garantiaDAO.incluirGarantia(garantia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarGarantia(Garantia garantia){
		boolean guardado = false;
		try {
			guardado = garantiaDAO.editarGarantia(garantia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Garantia> listarGarantias(){
		List<Garantia> reclamos = new ArrayList<Garantia>();
		try {
			reclamos = garantiaDAO.listarGarantias();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamos;
	}
	
	public Garantia getGarantia(Long id){
		Garantia garantia = new Garantia();
		try {
			garantia = garantiaDAO.getGarantia(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return garantia;
	}
}
