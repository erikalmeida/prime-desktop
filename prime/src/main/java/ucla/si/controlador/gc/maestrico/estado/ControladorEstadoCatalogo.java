package ucla.si.controlador.gc.maestrico.estado;

import org.zkoss.zhtml.Messagebox;
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
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Estado;
import ucla.si.servicio.ServicioEstado;

public class ControladorEstadoCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Estado estado;
	@WireVariable
	private ServicioEstado servicioEstado;

	@Wire
	Listbox listEstados;

	@Wire
	Textbox txtBuscar;
	
	@Override
	protected void inicializar() {
		ListModelList<Estado> modeloEstados = new ListModelList<Estado>(servicioEstado.listarEstados());
		modeloEstados.setMultiple(false);
		listEstados.setModel(modeloEstados);
		listEstados.setMultiple(false);
		listEstados.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/estado/frm-estado-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Estado> modeloEstados = new ListModelList<Estado>(
				servicioEstado.buscarEstados(txtBuscar.getValue().trim().toString()));
		modeloEstados.setMultiple(false);
		listEstados.setModel(modeloEstados);
		listEstados.setMultiple(false);
		listEstados.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listEstados")
	public void actualizarListbox() {
		if (listEstados.getItemCount() > 0) {
			asignarEventos(listEstados);
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
			Estado estado = (Estado) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("estado", estado);
				String dir = "gc/estado/frm-estado-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("estado", estado);
				String dir = "gc/estado/frm-estado-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				estado.setEstatus("Inactivo");

				if (servicioEstado.editarEstado(estado)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/estado/frm-estado-catalogo.zul";
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