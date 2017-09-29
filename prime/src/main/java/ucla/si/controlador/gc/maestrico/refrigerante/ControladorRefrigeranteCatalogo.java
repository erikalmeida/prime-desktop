package ucla.si.controlador.gc.maestrico.refrigerante;

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
import ucla.si.modelo.Refrigerante;
import ucla.si.servicio.ServicioRefrigerante;

public class ControladorRefrigeranteCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioRefrigerante servicioRefrigerante;

	@Wire
	Listbox listRefrigerantes;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Refrigerante> modeloRefrigerantes = new ListModelList<Refrigerante>(
				servicioRefrigerante.listarRefrigerantes());
		modeloRefrigerantes.setMultiple(false);
		listRefrigerantes.setModel(modeloRefrigerantes);
		listRefrigerantes.setMultiple(false);
		listRefrigerantes.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/refrigerante/frm-refrigerante-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Refrigerante> modeloRefrigerantes = new ListModelList<Refrigerante>(
				servicioRefrigerante.buscarRefrigerantes(txtBuscar.getValue().trim().toString()));
		modeloRefrigerantes.setMultiple(false);
		listRefrigerantes.setModel(modeloRefrigerantes);
		listRefrigerantes.setMultiple(false);
		listRefrigerantes.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listRefrigerantes")
	public void actualizarListbox() {
		if (listRefrigerantes.getItemCount() > 0) {
			asignarEventos(listRefrigerantes);
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
			Refrigerante refrigerante = (Refrigerante) (((Listitem) mouseEvent.getTarget().getParent().getParent())
					.getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("refrigerante", refrigerante);
				String dir = "gc/refrigerante/frm-refrigerante-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("refrigerante", refrigerante);
				String dir = "gc/refrigerante/frm-refrigerante-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				refrigerante.setEstatus("Inactivo");

				if (servicioRefrigerante.editarRefrigerante(refrigerante)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/refrigerante/frm-refrigerante-catalogo.zul";
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
