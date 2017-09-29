package ucla.si.controlador.gc.web;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;


import ucla.si.controlador.app.ControladorInicio;


public class ControladorConfiguracionWeb extends ControladorInicio {
	
	@Wire
	private Tab slider;
	
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializar() {
		String dir = "gc/web/sistema/frm-sistema.zul";
		
		clearDivAppWeb(dir);
		// TODO Auto-generated method stub

	}
	
	@Listen("onClick =#sistema")
	public void sistema() {
		String dir = "gc/web/sistema/frm-sistema.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#noticias")
	public void noticias() {
		String dir = "gc/web/noticia/cat-noticia.zul";
		//slider.setAction("onClick");
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#promocionesWeb")
	public void promociones() {
		String dir = "gc/web/promocion/cat-promocion.zul";
		//slider.setAction("onClick");
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#preguntasFrecuentes")
	public void preguntasFrecuentes() {
		String dir = "gc/web/preguntaFrecuente/cat-pregunta-frecuente.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	@Listen("onClick =#sociales")
	public void redesSociales() {
		String dir = "gc/web/redSocial/frm-red-social.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#slider")
	public void slider() {
		String dir = "gc/web/slider/cat-slider.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

}
