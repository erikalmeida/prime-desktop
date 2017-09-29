package ucla.si.controlador.gc.accion;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Accion;
import ucla.si.servicio.ServicioAccion;

public class ControladorAccionCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioAccion servicioAccion;
	
	@Wire
	Listbox listAcciones;

	@Override
	protected void inicializar() {
		ListModelList<Accion> modeloTipoClases = new ListModelList<Accion>(servicioAccion.listarAcciones());
		modeloTipoClases.setMultiple(false);
		listAcciones.setModel(modeloTipoClases);
		listAcciones.setMultiple(false);
		listAcciones.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/accion/frm-accion-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listAcciones")
	public void actualizarListbox() {
		if(listAcciones.getItemCount() > 0){
			asignarEventos(listAcciones);
		}
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
			Accion accion = (Accion) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("accion", accion);
				String dir = "gc/accion/frm-accion-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("accion", accion);
				String dir = "gc/accion/frm-accion-consultar.zul";
				clearDivApp(dir);
			}
			else if(boton.getTooltiptext().equals("Eliminar")){
				Messagebox.show("¡En Construcción!", "Información",Messagebox.OK, Messagebox.EXCLAMATION);
			}
			else {
				
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
