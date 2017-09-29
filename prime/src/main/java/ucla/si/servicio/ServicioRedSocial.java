package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


import ucla.si.dao.NoticiaDAO;
import ucla.si.dao.RedSocialDAO;
import ucla.si.modelo.Noticia;
import ucla.si.modelo.RedSocial;

@Service("servicioRedSocial")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioRedSocial {

	@Autowired
	private RedSocialDAO redSocialDAO;

	public boolean incluirRedSocial(RedSocial redSocial) {
		boolean guardado = false;
		try {
			guardado = redSocialDAO.incluirRedSocial(redSocial);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public boolean editarRedSocial(RedSocial redSocial) {
		boolean guardado = false;
		try {
			guardado = redSocialDAO.editarRedSocial(redSocial);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;

	}

	

	public List<RedSocial> listarRedesSociales() {
		List<RedSocial> redesSociales = new ArrayList<RedSocial>();
		try {
			redesSociales  = redSocialDAO.listarRedesSociales();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redesSociales ;

	}

}
