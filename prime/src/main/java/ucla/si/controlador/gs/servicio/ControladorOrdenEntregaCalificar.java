package ucla.si.controlador.gs.servicio;

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
import ucla.si.modelo.OrdenEntrega;
import ucla.si.modelo.Vehiculo;
import ucla.si.servicio.ServicioOrdenEntrega;


public class ControladorOrdenEntregaCalificar extends ControladorInicio {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioOrdenEntrega servicioOrdenEntrega;
	
	private OrdenEntrega ordenEntrega;
	
	
	
	
	@Wire
	Listbox listOrdenEntrega;

	@Override
	protected void inicializar() {
		
		
		
		ListModelList<OrdenEntrega> modeloOrdenEntrega = new ListModelList<OrdenEntrega>(servicioOrdenEntrega.listarOrdenesEntregaPendientes());
		modeloOrdenEntrega.setMultiple(false);
		listOrdenEntrega.setModel(modeloOrdenEntrega);
		listOrdenEntrega.setMultiple(false);
		listOrdenEntrega.setCheckmark(false);
	}
	/*
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		
		String dir = "gc/ordenEntrega/frm-ordenEntrega-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	} */
	
	@Listen("onAfterRender =#listOrdenEntrega")
	public void actualizarListbox() {
		if(listOrdenEntrega.getItemCount() > 0){
			asignarEventos(listOrdenEntrega);
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
			OrdenEntrega ordenEntrega = (OrdenEntrega) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());
			//Vehiculo vehiculo = (Vehiculo) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Detalle")) {
				setAtributo("ordenEntrega", ordenEntrega);
				//setAtributo("vehiculo", vehiculo);
				String dir = "gs/calificar/frm-calificar-servicio-detail.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("ordenEntrega", ordenEntrega);
				String dir = "gc/ordenEntrega/frm-ordenEntrega-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				ordenEntrega.setEstatus("Inactivo");

				if (servicioOrdenEntrega.editarOrdenEntrega(ordenEntrega)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/ordenEntrega/frm-ordenEntrega-catalogo.zul";
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
