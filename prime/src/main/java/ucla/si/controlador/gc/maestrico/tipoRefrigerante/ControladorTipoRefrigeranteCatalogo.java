package ucla.si.controlador.gc.maestrico.tipoRefrigerante;

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
import ucla.si.modelo.TipoRefrigerante;
import ucla.si.servicio.ServicioTipoRefrigerante;

public class ControladorTipoRefrigeranteCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoRefrigerante servicioTipoRefrigerante;

	@Wire
	Listbox listTipoRefrigerantes;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<TipoRefrigerante> modeloTipoRefrigerantes = new ListModelList<TipoRefrigerante>(
				servicioTipoRefrigerante.listarTipoRefrigerantes());
		modeloTipoRefrigerantes.setMultiple(false);
		listTipoRefrigerantes.setModel(modeloTipoRefrigerantes);
		listTipoRefrigerantes.setMultiple(false);
		listTipoRefrigerantes.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<TipoRefrigerante> modeloTipoRefrigerantes = new ListModelList<TipoRefrigerante>(
				servicioTipoRefrigerante.buscarTipoRefrigerantes(txtBuscar.getValue().trim().toString()));
		modeloTipoRefrigerantes.setMultiple(false);
		listTipoRefrigerantes.setModel(modeloTipoRefrigerantes);
		listTipoRefrigerantes.setMultiple(false);
		listTipoRefrigerantes.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listTipoRefrigerantes")
	public void actualizarListbox() {
		if (listTipoRefrigerantes.getItemCount() > 0) {
			asignarEventos(listTipoRefrigerantes);
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
			TipoRefrigerante tipoRefrigerante = (TipoRefrigerante) (((Listitem) mouseEvent.getTarget().getParent()
					.getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tipoRefrigerante", tipoRefrigerante);
				String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tipoRefrigerante", tipoRefrigerante);
				String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				tipoRefrigerante.setEstatus("Inactivo");

				if (servicioTipoRefrigerante.editarTipoRefrigerante(tipoRefrigerante)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul";
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
