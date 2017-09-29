package ucla.si.controlador.gc.maestrico.tarifa;

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
import ucla.si.modelo.Tarifa;
import ucla.si.servicio.ServicioTarifa;

public class ControladorTarifaCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tarifa tarifa;
	@WireVariable
	private ServicioTarifa servicioTarifa;

	@Wire
	Listbox listTarifas;

	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Tarifa> modeloTarifas = new ListModelList<Tarifa>(servicioTarifa.listarTarifas());
		modeloTarifas.setMultiple(false);
		listTarifas.setModel(modeloTarifas);
		listTarifas.setMultiple(false);
		listTarifas.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/tarifa/frm-tarifa-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Tarifa> modeloTarifas = new ListModelList<Tarifa>(
				servicioTarifa.buscarTarifas(txtBuscar.getValue().trim().toString()));
		modeloTarifas.setMultiple(false);
		listTarifas.setModel(modeloTarifas);
		listTarifas.setMultiple(false);
		listTarifas.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listTarifas")
	public void actualizarListbox() {
		if (listTarifas.getItemCount() > 0) {
			asignarEventos(listTarifas);
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
			Tarifa tarifa = (Tarifa) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tarifa", tarifa);
				String dir = "gc/tarifa/frm-tarifa-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tarifa", tarifa);
				String dir = "gc/tarifa/frm-tarifa-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				tarifa.setEstatus("Inactivo");

				if (servicioTarifa.editarTarifa(tarifa)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/tarifa/frm-tarifa-catalogo.zul";
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
