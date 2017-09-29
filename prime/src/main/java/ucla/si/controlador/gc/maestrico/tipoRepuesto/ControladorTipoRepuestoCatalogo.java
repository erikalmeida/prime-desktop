package ucla.si.controlador.gc.maestrico.tipoRepuesto;

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
import ucla.si.modelo.TipoRepuesto;
import ucla.si.servicio.ServicioTipoRepuesto;

public class ControladorTipoRepuestoCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoRepuesto servicioTipoRepuesto;

	@Wire
	Listbox listTipoRepuestos;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<TipoRepuesto> modeloTipoRepuestoes = new ListModelList<TipoRepuesto>(
				servicioTipoRepuesto.listarTipoRepuestos());
		modeloTipoRepuestoes.setMultiple(false);
		listTipoRepuestos.setModel(modeloTipoRepuestoes);
		listTipoRepuestos.setMultiple(false);
		listTipoRepuestos.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/tipoRepuesto/frm-tipoRepuesto-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<TipoRepuesto> modeloTipoRepuestos = new ListModelList<TipoRepuesto>(
				servicioTipoRepuesto.buscarTipoRepuestos(txtBuscar.getValue().trim().toString()));
		modeloTipoRepuestos.setMultiple(false);
		listTipoRepuestos.setModel(modeloTipoRepuestos);
		listTipoRepuestos.setMultiple(false);
		listTipoRepuestos.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listTipoRepuestos")
	public void actualizarListbox() {
		if (listTipoRepuestos.getItemCount() > 0) {
			asignarEventos(listTipoRepuestos);
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
			TipoRepuesto tipoRepuesto = (TipoRepuesto) (((Listitem) mouseEvent.getTarget().getParent().getParent())
					.getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tipoRepuesto", tipoRepuesto);
				String dir = "gc/tipoRepuesto/frm-tipoRepuesto-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tipoRepuesto", tipoRepuesto);
				String dir = "gc/tipoRepuesto/frm-tipoRepuesto-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				tipoRepuesto.setEstatus("Inactivo");

				if (servicioTipoRepuesto.editarTipoRepuesto(tipoRepuesto)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/tipoRepuesto/frm-tipoRepuesto-catalogo.zul";
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