package ucla.si.controlador.gc.maestrico.viaje;

import org.zkoss.zk.ui.Component;
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

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Viaje;
import ucla.si.servicio.ServicioViaje;

public class ControladorViajeCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioViaje servicioViaje;
	
	@Wire
	Listbox listViajes;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		ListModelList<Viaje> modeloViajes = new ListModelList<Viaje>(servicioViaje.listarViajes());
		if(modeloViajes.getSize() != 0){
			modeloViajes.setMultiple(false);
			listViajes.setModel(modeloViajes);
			listViajes.setMultiple(false);
			listViajes.setCheckmark(false);			
		}
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "pc/viaje/frm-viaje-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Viaje> modeloViajes = new ListModelList<Viaje>(
				servicioViaje.buscarViajes(txtBuscar.getValue().trim().toString()));
		modeloViajes.setMultiple(false);
		listViajes.setModel(modeloViajes);
		listViajes.setMultiple(false);
		listViajes.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listViajes")
	public void actualizarListbox() {
		if(listViajes.getItemCount() > 0){
			asignarEventos(listViajes);
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
			Viaje viaje = (Viaje) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("viaje", viaje);
				String dir = "pc/viaje/frm-viaje-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("viaje", viaje);
				String dir = "pc/viaje/frm-viaje-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				viaje.setEstatus("Inactivo");

				if (servicioViaje.editarViaje(viaje)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "pc/viaje/frm-viaje-catalogo.zul";
					clearDivApp(dir);
				}

				else {
					Messagebox.show("Error al Eliminar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
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
