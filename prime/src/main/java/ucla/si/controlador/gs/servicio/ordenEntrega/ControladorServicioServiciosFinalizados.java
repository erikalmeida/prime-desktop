package ucla.si.controlador.gs.servicio.ordenEntrega;


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

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.OrdenServicio;
import ucla.si.servicio.ServicioOrdenServicio;

public class ControladorServicioServiciosFinalizados  extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioOrdenServicio servicioOrdenServicio;
	
	@Wire
	Listbox listOrdenesServicio;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		ListModelList<OrdenServicio> modeloOrdenesServicio = new ListModelList<OrdenServicio>(servicioOrdenServicio.listarOrdenServiciosFinalizados());
		if (!modeloOrdenesServicio.isEmpty()) {
			modeloOrdenesServicio.setMultiple(false);
			listOrdenesServicio.setModel(modeloOrdenesServicio);
			listOrdenesServicio.setMultiple(false);
			listOrdenesServicio.setCheckmark(false);
		}	
	}
	
	
	@Listen("onAfterRender =#listOrdenesServicio")
	public void actualizarListbox() {
		if (listOrdenesServicio.getItemCount() > 0) {
			asignarEventos(listOrdenesServicio);
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
			OrdenServicio ordenServicio = (OrdenServicio) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getLabel().equals("Generar Orden Entrega")) {
				setAtributo("ordenServicio", ordenServicio);
				String dir = "gs/ordenEntrega/frm-generar-orden-entrega.zul";
				clearDivApp(dir);
			} else {

			}
		} catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("Tiempo Expirado para la transaccion!", "Informacion", Messagebox.OK, Messagebox.ERROR);
		} catch (org.hibernate.TransactionException e) {
			Messagebox.show("Tiempo Expirado para la transaccion!", "Informacion", Messagebox.OK, Messagebox.ERROR);
		}
	}

}
