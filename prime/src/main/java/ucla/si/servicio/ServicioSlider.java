package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.NoticiaDAO;
import ucla.si.dao.ServicioDAO;
import ucla.si.dao.SliderDAO;
import ucla.si.modelo.Combustible;
import ucla.si.modelo.Noticia;
import ucla.si.modelo.Slider;

@Service("servicioSlider")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioSlider {

	@Autowired
	private SliderDAO sliderDAO;

	public boolean incluirSlider(Slider slider) {
		boolean guardado = false;
		try {
			guardado = sliderDAO.incluirSlider(slider);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean eliminarSlider(Slider slider) {
		boolean eliminado = false;
		try {
			eliminado = sliderDAO.eliminarSlider(slider);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eliminado;
	}


	public boolean editarSlider(Slider slider) {
		boolean guardado = false;
		try {
			guardado = sliderDAO.editarSlider(slider);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;

	}
	
	public List<Slider> buscarSlider(String nombre) {
		List<Slider> sliders = new ArrayList<Slider>();
		try {
			sliders = sliderDAO.buscarSlider(nombre);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sliders;
	}

	public List<Slider> listarSliders() {
		List<Slider> sliders = new ArrayList<Slider>();
		try {
			sliders = sliderDAO.listarSliders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sliders;

	}

}
