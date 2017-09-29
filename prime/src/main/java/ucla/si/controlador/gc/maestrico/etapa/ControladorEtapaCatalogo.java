package ucla.si.controlador.gc.maestrico.etapa;

import org.zkoss.zhtml.Messagebox;
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
import org.zkoss.zul.Textbox;
import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Etapa;
import ucla.si.servicio.ServicioEtapa;

public class ControladorEtapaCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Etapa etapa;
	@WireVariable
	private ServicioEtapa servicioEtapa;

	@Wire
	Listbox listEtapas;
	

	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Etapa> modeloEtapas = new ListModelList<Etapa>(servicioEtapa.listarEtapas());
		modeloEtapas.setMultiple(false);
		listEtapas.setModel(modeloEtapas);
		listEtapas.setMultiple(false);
		listEtapas.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/etapa/frm-etapa-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listEtapas")
	public void actualizarListbox() {
		if (listEtapas.getItemCount() > 0) {
			asignarEventos(listEtapas);
		}
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Etapa> modeloEtapas = new ListModelList<Etapa>(
				servicioEtapa.buscarEtapas(txtBuscar.getValue().trim().toString()));
		modeloEtapas.setMultiple(false);
		listEtapas.setModel(modeloEtapas);
		listEtapas.setMultiple(false);
		listEtapas.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
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
			Etapa etapa = (Etapa) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("etapa", etapa);
				String dir = "gc/etapa/frm-etapa-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("etapa", etapa);
				String dir = "gc/etapa/frm-etapa-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				etapa.setEstatus("Inactivo");

				if (servicioEtapa.editarEtapa(etapa)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/etapa/frm-etapa-catalogo.zul";
					clearDivApp(dir);
				}

				else {
					Messagebox.show("Error al Eliminar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			} 
		} catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		} catch (org.hibernate.TransactionException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		}
	}


}
