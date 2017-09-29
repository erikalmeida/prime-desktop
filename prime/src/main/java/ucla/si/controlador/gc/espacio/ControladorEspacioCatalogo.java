package ucla.si.controlador.gc.espacio;

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
import ucla.si.modelo.Espacio;
import ucla.si.servicio.ServicioEspacio;

public class ControladorEspacioCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioEspacio servicioEspacio;

	@Wire
	Listbox listEspacios;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Espacio> modeloEspacios = new ListModelList<Espacio>(servicioEspacio.listarEspacios());
		modeloEspacios.setMultiple(false);
		listEspacios.setModel(modeloEspacios);
		listEspacios.setMultiple(false);
		listEspacios.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/espacio/frm-espacio-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	

	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Espacio> modeloEspacios = new ListModelList<Espacio>(
				servicioEspacio.buscarEspacios(txtBuscar.getValue().trim().toString()));
		modeloEspacios.setMultiple(false);
		listEspacios.setModel(modeloEspacios);
		listEspacios.setMultiple(false);
		listEspacios.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}


	@Listen("onAfterRender =#listEspacios")
	public void actualizarListbox() {
		if (listEspacios.getItemCount() > 0) {
			asignarEventos(listEspacios);
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
			Espacio espacio = (Espacio) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("espacio", espacio);
				String dir = "gc/espacio/frm-espacio-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("espacio", espacio);
				String dir = "gc/espacio/frm-espacio-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				Messagebox.show("¡En Construcción!", "Información", Messagebox.OK, Messagebox.EXCLAMATION);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				espacio.setEstatus("Inactivo");

				if (servicioEspacio.editarEspacio(espacio)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/espacio/frm-espacio-catalogo.zul";
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
