package ucla.si.controlador.gc.maestrico.falla;

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
import ucla.si.modelo.Falla;
import ucla.si.servicio.ServicioFalla;

public class ControladorFallaCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Falla falla;
	@WireVariable
	private ServicioFalla servicioFalla;

	@Wire
	Listbox listFallas;

	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Falla> modeloFallas = new ListModelList<Falla>(servicioFalla.listarFallas());
		modeloFallas.setMultiple(false);
		listFallas.setModel(modeloFallas);
		listFallas.setMultiple(false);
		listFallas.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/falla/frm-falla-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Falla> modeloFallas = new ListModelList<Falla>(
				servicioFalla.buscarFallas(txtBuscar.getValue().trim().toString()));
		modeloFallas.setMultiple(false);
		listFallas.setModel(modeloFallas);
		listFallas.setMultiple(false);
		listFallas.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listFallas")
	public void actualizarListbox() {
		if (listFallas.getItemCount() > 0) {
			asignarEventos(listFallas);
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
			Falla falla = (Falla) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("falla", falla);
				String dir = "gc/falla/frm-falla-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("falla", falla);
				String dir = "gc/falla/frm-falla-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				falla.setEstatus("Inactivo");

				if (servicioFalla.editarFalla(falla)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/falla/frm-falla-catalogo.zul";
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
