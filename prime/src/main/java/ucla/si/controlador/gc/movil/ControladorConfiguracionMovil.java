package ucla.si.controlador.gc.movil;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;


import ucla.si.controlador.app.ControladorInicio;


public class ControladorConfiguracionMovil extends ControladorInicio {
	
	@Wire
	private Tab slider;
	
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializar() {
		String dir = "gc/movil/slider/cat-slider.zul";
		
		clearDivAppWeb(dir);
		// TODO Auto-generated method stub

	}
	
	@Listen("onClick =#sliderMovil")
	public void slider() {
		String dir = "gc/movil/slider/cat-slider.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#promocionMovil")
	public void promocion() {
		System.out.println("promocion");
		String dir = "gc/movil/promocion/cat-promocion.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#servicioMovil")
	public void servicio() {
		String dir = "gc/movil/servicio/cat-servicio.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

}
