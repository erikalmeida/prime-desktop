package ucla.si.utils;

/*
 * Provee una lista de constante con las ubicaciones dentro del sitio
 * Los metodos GET publicos y NO STATICOS son requeridos para usar
 * los valores dentro de las plantillas .ZUL
 */
public class Urls {

	// Base
	public static final String URL_INDEX = "/";

	// Archivos
	public static final String URL_STATIC = URL_INDEX + "static";
	public static final String URL_STATIC_IMG = URL_STATIC + "/images";
	public static final String URL_STATIC_QUESTIONIMG = URL_STATIC_IMG + "/img_question.png";

	public static final String URL_MEDIA = URL_INDEX + "media";
	public static final String URL_MEDIA_USER = URL_MEDIA + "/user";
	public static final String URL_MEDIA_USER_PERFIL = URL_MEDIA_USER + "/profile";
	public static final String URL_MEDIA_USER_IMAGENPERFIL_CACHE = URL_MEDIA_USER_PERFIL + "/cache";

	// Noticia

	public static final String URL_MEDIA_NOTICIA = URL_MEDIA + "/images/noticias";
	public static final String URL_MEDIA_NOTICIA_CACHE = URL_MEDIA_NOTICIA + "/cache";

	// Slider
	public static final String URL_MEDIA_SLIDER = URL_MEDIA + "/images/slider";
	public static final String URL_MEDIA_SLIDER_CACHE = URL_MEDIA_SLIDER + "/cache";

	// Cabezera
	public static final String URL_MEDIA_CABEZERA = URL_MEDIA + "/images/cabezera";
	public static final String URL_MEDIA_CABEZERA_CACHE = URL_MEDIA_SLIDER + "/cache";

	// Servicios
	public static final String URL_MEDIA_SERVICIOS = URL_MEDIA + "/images/servicios";
	public static final String URL_MEDIA_SERVICIOS_CACHE = URL_MEDIA_SERVICIOS + "/cache";

	// Promociones
	public static final String URL_MEDIA_PROMOCIONES = URL_MEDIA + "/images/promociones";
	public static final String URL_MEDIA_PROMOCIONES_CACHE = URL_MEDIA_PROMOCIONES + "/cache";

	public String getURL_INDEX() {
		return URL_INDEX;
	}

}
