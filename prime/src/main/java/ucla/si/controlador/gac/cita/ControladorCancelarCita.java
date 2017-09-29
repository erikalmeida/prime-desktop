package ucla.si.controlador.gac.cita;

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
import ucla.si.modelo.Cita;
import ucla.si.servicio.ServicioCita;

public class ControladorCancelarCita extends ControladorInicio {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioCita servicioCita;
	
	private Cita cita;
	
	
	@Wire
	Listbox listCancelarCitas;

	@Override
	protected void inicializar() {
		
		
		
		ListModelList<Cita> modeloCancelarCitas = new ListModelList<Cita>(servicioCita.listarCancelarCitas());
		modeloCancelarCitas.setMultiple(false);
		listCancelarCitas.setModel(modeloCancelarCitas);
		listCancelarCitas.setMultiple(false);
		listCancelarCitas.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		
		String dir = "gc/cita/frm-cita-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listCancelarCitas")
	public void actualizarListbox() {
		if(listCancelarCitas.getItemCount() > 0){
			asignarEventos(listCancelarCitas);
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
			Cita cita = (Cita) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Cancelar")) {
				setAtributo("cita", cita);
				String dir = "gac/frm-cancelar-cita-detalle.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("cita", cita);
				String dir = "gc/cita/frm-cita-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				cita.setEstatus("Inactivo");

				if (servicioCita.editarCita(cita)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/cita/frm-cita-catalogo.zul";
					clearDivApp(dir);
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
