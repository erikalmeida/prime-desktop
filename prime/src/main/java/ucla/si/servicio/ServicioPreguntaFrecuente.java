package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ucla.si.dao.PreguntaFrecuenteDAO;
import ucla.si.modelo.Noticia;
import ucla.si.modelo.PreguntaFrecuente;

@Service("servicioPreguntaFrecuente")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioPreguntaFrecuente {

	@Autowired
	private PreguntaFrecuenteDAO preguntaFrecuenteDAO;

	public boolean incluirPreguntaFrecuente(PreguntaFrecuente preguntaFrecuente) {
		boolean guardado = false;
		try {
			guardado = preguntaFrecuenteDAO.incluirPreguntaFrecuente(preguntaFrecuente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public boolean editarPreguntaFrecuente(PreguntaFrecuente preguntaFrecuente) {
		boolean guardado = false;
		try {
			guardado = preguntaFrecuenteDAO.editarPreguntaFrecuente(preguntaFrecuente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public List<PreguntaFrecuente> transformarHtml() {
		List<PreguntaFrecuente> preguntasFrecuentesHtml = new ArrayList<PreguntaFrecuente>();
		List<PreguntaFrecuente> preguntasFrecuentes = new ArrayList<PreguntaFrecuente>();
		try {
			preguntasFrecuentesHtml = preguntaFrecuenteDAO.listarPreguntasFrecuentes();
			for (PreguntaFrecuente preguntaFrecuenteHtml : preguntasFrecuentesHtml) {
				String pregunta = preguntaFrecuenteHtml.getPregunta();
				String respuesta = preguntaFrecuenteHtml.getRespuesta();
				respuesta = Jsoup.parse(respuesta).text();
				pregunta = Jsoup.parse(pregunta).text();
				preguntaFrecuenteHtml.setRespuesta(respuesta);
				preguntaFrecuenteHtml.setPregunta(pregunta);
				preguntasFrecuentes.add(preguntaFrecuenteHtml);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return preguntasFrecuentes;
	}

	public List<PreguntaFrecuente> transformarHtml(List<PreguntaFrecuente> preguntasFrecuentesHtml) {
		List<PreguntaFrecuente> preguntasFrecuentes = new ArrayList<PreguntaFrecuente>();
		try {
			for (PreguntaFrecuente preguntaFrecuenteHtml : preguntasFrecuentesHtml) {
				String pregunta = preguntaFrecuenteHtml.getPregunta();
				String respuesta = preguntaFrecuenteHtml.getRespuesta();
				respuesta = Jsoup.parse(respuesta).text();
				pregunta = Jsoup.parse(pregunta).text();
				preguntaFrecuenteHtml.setRespuesta(respuesta);
				preguntaFrecuenteHtml.setPregunta(pregunta);
				preguntasFrecuentes.add(preguntaFrecuenteHtml);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return preguntasFrecuentes;
	}

	public List<PreguntaFrecuente> listarPreguntaFrecuente() {
		List<PreguntaFrecuente> preguntasFrecuentes = new ArrayList<PreguntaFrecuente>();
		try {
			preguntasFrecuentes = preguntaFrecuenteDAO.listarPreguntasFrecuentes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preguntasFrecuentes;
	}

	public List<PreguntaFrecuente> buscarPreguntaFrecuente(String pregunta) {
		List<PreguntaFrecuente> preguntasFrecuentes = new ArrayList<PreguntaFrecuente>();
		try {
			preguntasFrecuentes = preguntaFrecuenteDAO.buscarPreguntaFrecuente(pregunta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preguntasFrecuentes;
	}
	
	public boolean eliminarPreguntaFrecuente(PreguntaFrecuente preguntaFrecuente) {
		boolean eliminado = false;
		try {
			eliminado = preguntaFrecuenteDAO.eliminarPreguntaFrecuente(preguntaFrecuente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eliminado;
	}

}
