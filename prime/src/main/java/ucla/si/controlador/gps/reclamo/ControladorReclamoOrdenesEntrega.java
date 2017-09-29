package ucla.si.controlador.gps.reclamo;

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

import ucla.si.modelo.OrdenEntrega;
import ucla.si.modelo.TipoReclamo;

import ucla.si.servicio.ServicioOrdenEntrega;
import ucla.si.servicio.ServicioReclamo;
import ucla.si.servicio.ServicioTipoReclamo;

import ucla.si.controlador.app.ControladorInicio;

public class ControladorReclamoOrdenesEntrega extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioOrdenEntrega servicioOrdenEntrega;

	@WireVariable
	private ServicioTipoReclamo servicioTipoReclamo;

	@WireVariable
	private ServicioReclamo servicioReclamo;

	@Wire
	Listbox listOrdenes;

	protected void inicializar() {
		// TODO Auto-generated method stub
		ListModelList<OrdenEntrega> modeloOrdenes = new ListModelList<OrdenEntrega>(
				servicioOrdenEntrega.listarOrdenesEntrega());
		if (!modeloOrdenes.isEmpty()) {
			modeloOrdenes.setMultiple(false);
			listOrdenes.setModel(modeloOrdenes);
			listOrdenes.setMultiple(false);
			listOrdenes.setCheckmark(false);
		}
	}

	@Listen("onAfterRender =#listOrdenes")
	public void actualizarListbox() {
		if (listOrdenes.getItemCount() > 0) {
			asignarEventos(listOrdenes);
		}
	}

	private void asignarEventos(Component padre) {
		String click = "onClick";
		for (Component hijo : padre.getChildren()) {
			if (hijo instanceof Button) {
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
			OrdenEntrega ordenEntrega = (OrdenEntrega) (((Listitem) mouseEvent.getTarget().getParent().getParent())
					.getValue());

			if (boton.getLabel().equals("Solicitar Reclamo")) {
				setAtributo("ordenEntrega", ordenEntrega);
				ListModelList<TipoReclamo> modeloTipoReclamos = new ListModelList<TipoReclamo>(
						servicioTipoReclamo.listarTipoReclamos());
				if (modeloTipoReclamos.isEmpty()) {
					Messagebox.show(
							"No existen Tipos de Reclamos Registrados, contactar con el administrador del sistema",
							"Error", Messagebox.OK, Messagebox.ERROR);
					String dir = "gps/reclamo/frm-reclamo-ordenesEntrega.zul";
					clearDivApp(dir);

				} else {
					String dir = "gps/reclamo/frm-reclamo-incluir.zul";
					clearDivApp(dir);
				}
			} else {

			}
		} catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transaccion!", "Informacion", Messagebox.OK, Messagebox.ERROR);
		} catch (org.hibernate.TransactionException e) {
		}
	}
}