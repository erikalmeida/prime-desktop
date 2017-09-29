package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


import ucla.si.dao.NoticiaDAO;

import ucla.si.modelo.Noticia;
import ucla.si.modelo.Slider;

@Service("servicioNoticia")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioNoticia {

	@Autowired
	private NoticiaDAO noticiaDAO;

	public boolean incluirNoticia(Noticia noticia) {
		boolean guardado = false;
		try {
			guardado = noticiaDAO.incluirNoticia(noticia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public boolean editarNoticia(Noticia noticia) {
		boolean guardado = false;
		try {
			guardado = noticiaDAO.editarNoticia(noticia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;

	}

	public List<Noticia> transformarHtml() {
		List<Noticia> noticiasHtml = new ArrayList<Noticia>();
		List<Noticia> noticias = new ArrayList<Noticia>();
		try {
			noticiasHtml = noticiaDAO.listarNoticias();
			for (Noticia noticiaHtml : noticiasHtml) {
				String descripcion = noticiaHtml.getDescripcion();
				
				descripcion = Jsoup.parse(descripcion).text();

				noticiaHtml.setDescripcion(descripcion);
				noticias.add(noticiaHtml);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticias;
	}

	public List<Noticia> listarNoticias() {
		List<Noticia> noticias = new ArrayList<Noticia>();
		try {
			noticias = noticiaDAO.listarNoticias();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticias;

	}
	
	public boolean eliminarNoticia(Noticia noticia) {
		boolean eliminado = false;
		try {
			eliminado = noticiaDAO.eliminarNoticia(noticia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eliminado;
	}
	
	public List<Noticia> buscarNoticia(String titulo) {
		List<Noticia> noticias = new ArrayList<Noticia>();
		try {
			noticias = noticiaDAO.buscarNoticia(titulo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticias;
	}

}
