package ucla.si.controlador.gc.web.noticia;

import java.util.Collections;

import org.zkforge.ckez.CKeditor;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Color;
import ucla.si.modelo.Noticia;
import ucla.si.modelo.Slider;
import ucla.si.servicio.ServicioNoticia;

public class ControladorConfiguracionNoticia extends ControladorInicio  {

	/**
	 * 
	 */
	
	@Wire
	private Textbox txtBuscar;
	
	@WireVariable
	private ServicioNoticia servicioNoticia;
	
	@Wire
	Listbox listNoticias;
	
	
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializar() {
		ListModelList<Noticia> noticias = new ListModelList<Noticia>(servicioNoticia.transformarHtml());
		Collections.reverse(noticias);
		noticias.setMultiple(false);
		listNoticias.setModel(noticias);
		listNoticias.setMultiple(false);
		listNoticias.setCheckmark(false);
		
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		ListModelList<Noticia> noticias = new ListModelList<Noticia>(
				servicioNoticia.buscarNoticia(txtBuscar.getValue().trim().toString()));
		Collections.reverse(noticias);
		noticias.setMultiple(false);
		listNoticias.setModel(noticias);
		listNoticias.setMultiple(false);
		listNoticias.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	
	@Listen("onAfterRender =#listNoticias")
	public void actualizarListbox() {
		if(listNoticias.getItemCount() > 0){
			asignarEventos(listNoticias);
		}
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/web/noticia/frm-incluir-noticia.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	private void asignarEventos(Component padre) {
		String click = "onClick";
		for (Component hijo : padre.getChildren()){
			if (hijo instanceof Button){
				hijo.addEventListener(click, new EventListener<MouseEvent>() {
					public void onEvent(MouseEvent mouseEvent) throws Exception {
						verificarAcciones(mouseEvent);
					}
				});
			}			
			asignarEventos(hijo);			
		}
	}
	
	public void verificarAcciones(MouseEvent mouseEvent) {
		try {
			Button boton = (Button) mouseEvent.getTarget();
			Noticia noticia = (Noticia) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("noticia", noticia);
				String dir = "gc/web/noticia/frm-editar-noticia.zul";
				clearDivAppWeb(dir);
			}
			if (boton.getTooltiptext().equals("Eliminar")) {
				setAtributo("noticia", noticia);
				Messagebox.show("¿Estas seguro de eliminar la Noticia?", "Confirmación",
						Messagebox.YES | Messagebox.CANCEL, Messagebox.QUESTION,
						new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event evt) throws InterruptedException {
								if (evt.getName().equals("onYes")) {
								    servicioNoticia.eliminarNoticia((Noticia)getAtributo("noticia"));
								    inicializar();
								    txtBuscar.setValue("");
								    Messagebox.show("Noticia Eliminada con exito", "Información", Messagebox.OK, Messagebox.INFORMATION);
			
								} 
							}
						});

			}
		}
		catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información",Messagebox.OK, Messagebox.ERROR);
		} 
		catch (org.hibernate.TransactionException e){
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información",Messagebox.OK, Messagebox.ERROR);
		}
	}

}
