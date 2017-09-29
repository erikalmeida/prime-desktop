package ucla.si.controlador.gc.maestrico.anomalia;

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
import org.zkoss.zul.Textbox;
import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Anomalia;
import ucla.si.servicio.ServicioAnomalia;

public class ControladorAnomaliaCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioAnomalia servicioAnomalia;

	@Wire
	Listbox listAnomalias;
	@Wire 
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Anomalia> modeloAnomalias = new ListModelList<Anomalia>(servicioAnomalia.listarAnomalias());
		modeloAnomalias.setMultiple(false);
		listAnomalias.setModel(modeloAnomalias);
		listAnomalias.setMultiple(false);
		listAnomalias.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/anomalia/frm-anomalia-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Anomalia> modeloAnomalias = new ListModelList<Anomalia>(
				servicioAnomalia.buscarAnomalias(txtBuscar.getValue().trim().toString()));
		modeloAnomalias.setMultiple(false);
		listAnomalias.setModel(modeloAnomalias);
		listAnomalias.setMultiple(false);
		listAnomalias.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listAnomalias")
	public void actualizarListbox() {
		if (listAnomalias.getItemCount() > 0) {
			asignarEventos(listAnomalias);
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
			Anomalia anomalia = (Anomalia) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("anomalia", anomalia);
				String dir = "gc/anomalia/frm-anomalia-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("anomalia", anomalia);
				String dir = "gc/anomalia/frm-anomalia-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				anomalia.setEstatus("Inactivo");

				if (servicioAnomalia.editarAnomalia(anomalia)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/anomalia/frm-anomalia-catalogo.zul";
					clearDivApp(dir);
				}
			} else {

			}
		} catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		} catch (org.hibernate.TransactionException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		}
	}

}
