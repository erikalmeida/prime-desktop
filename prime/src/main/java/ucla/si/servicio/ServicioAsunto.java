package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.AsuntoDAO;
import ucla.si.dao.NoticiaDAO;
import ucla.si.dao.ServicioDAO;
import ucla.si.dao.SliderDAO;
import ucla.si.modelo.Asunto;
import ucla.si.modelo.Noticia;
import ucla.si.modelo.Slider;

@Service("servicioAsunto")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioAsunto {

	@Autowired
	private AsuntoDAO asuntoDAO;

	public boolean incluirAsunto(Asunto asunto) {
		boolean guardado = false;
		try {
			guardado = asuntoDAO.incluirAsunto(asunto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public boolean editarAsunto(Asunto asunto) {
		boolean guardado = false;
		try {
			guardado = asuntoDAO.editarAsunto(asunto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;

	}

	public List<Asunto> listarAsuntos() {
		List<Asunto> asuntos = new ArrayList<Asunto>();
		try {
			asuntos = asuntoDAO.listarAsuntos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return asuntos;

	}

}
