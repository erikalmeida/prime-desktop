package ucla.si.controlador.gc.maestrico.grosorAceite;

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
import ucla.si.modelo.GrosorAceite;
import ucla.si.servicio.ServicioGrosorAceite;

public class ControladorGrosorAceiteCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioGrosorAceite servicioGrosorAceite;

	@Wire
	Listbox listGrosorAceites;

	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<GrosorAceite> modeloGrosorAceites = new ListModelList<GrosorAceite>(
				servicioGrosorAceite.listarGrosorAceites());
		modeloGrosorAceites.setMultiple(false);
		listGrosorAceites.setModel(modeloGrosorAceites);
		listGrosorAceites.setMultiple(false);
		listGrosorAceites.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/grosorAceite/frm-grosorAceite-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<GrosorAceite> modeloGrosorAceites = new ListModelList<GrosorAceite>(
				servicioGrosorAceite.buscarGrosorAceites(txtBuscar.getValue().trim().toString()));
		modeloGrosorAceites.setMultiple(false);
		listGrosorAceites.setModel(modeloGrosorAceites);
		listGrosorAceites.setMultiple(false);
		listGrosorAceites.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listGrosorAceites")
	public void actualizarListbox() {
		if (listGrosorAceites.getItemCount() > 0) {
			asignarEventos(listGrosorAceites);
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
			GrosorAceite grosorAceite = (GrosorAceite) (((Listitem) mouseEvent.getTarget().getParent().getParent())
					.getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("grosorAceite", grosorAceite);
				String dir = "gc/grosorAceite/frm-grosorAceite-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("grosorAceite", grosorAceite);
				String dir = "gc/grosorAceite/frm-grosorAceite-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				grosorAceite.setEstatus("Inactivo");

				if (servicioGrosorAceite.editarGrosorAceite(grosorAceite)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/grosorAceite/frm-grosorAceite-catalogo.zul";
					clearDivApp(dir);
				}

				else {
					Messagebox.show("Error al eliminarr", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		} catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		} catch (org.hibernate.TransactionException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		}
	}

}
