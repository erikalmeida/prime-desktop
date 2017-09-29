package ucla.si.controlador.gc.maestrico.habilidad;

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
import ucla.si.modelo.Habilidad;
import ucla.si.servicio.ServicioHabilidad;

public class ControladorHabilidadCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioHabilidad servicioHabilidad;

	@Wire
	Listbox listHabilidades;
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Habilidad> modeloHabilidades = new ListModelList<Habilidad>(
				servicioHabilidad.listarHabilidades());
		modeloHabilidades.setMultiple(false);
		listHabilidades.setModel(modeloHabilidades);
		listHabilidades.setMultiple(false);
		listHabilidades.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/habilidad/frm-habilidad-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Habilidad> modeloHabilidades = new ListModelList<Habilidad>(
				servicioHabilidad.buscarHabilidades(txtBuscar.getValue().trim().toString()));
		modeloHabilidades.setMultiple(false);
		listHabilidades.setModel(modeloHabilidades);
		listHabilidades.setMultiple(false);
		listHabilidades.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listHabilidades")
	public void actualizarListbox() {
		if (listHabilidades.getItemCount() > 0) {
			asignarEventos(listHabilidades);
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
			Habilidad habilidad = (Habilidad) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("habilidad", habilidad);
				String dir = "gc/habilidad/frm-habilidad-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("habilidad", habilidad);
				String dir = "gc/habilidad/frm-habilidad-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				habilidad.setEstatus("Inactivo");

				if (servicioHabilidad.editarHabilidad(habilidad)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/habilidad/frm-habilidad-catalogo.zul";
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
