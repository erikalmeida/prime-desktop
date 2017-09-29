package ucla.si.controlador.gc.tiposervicio;

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
import ucla.si.modelo.TipoServicio;
import ucla.si.servicio.ServicioTipoServicio;

public class ControladorTipoServicioCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoServicio tipoServicio;
	@WireVariable
	private ServicioTipoServicio servicioTipoServicio;

	@Wire
	Listbox listTipoServicios;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<TipoServicio> modeloTipoServicios = new ListModelList<TipoServicio>(servicioTipoServicio.listarTipoServicios());
		modeloTipoServicios.setMultiple(false);
		listTipoServicios.setModel(modeloTipoServicios);
		listTipoServicios.setMultiple(false);
		listTipoServicios.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/tipoServicio/frm-tipoServicio-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<TipoServicio> modeloTipoServicios = new ListModelList<TipoServicio>(
				servicioTipoServicio.buscarTipoServicios(txtBuscar.getValue().trim().toString()));
		modeloTipoServicios.setMultiple(false);
		listTipoServicios.setModel(modeloTipoServicios);
		listTipoServicios.setMultiple(false);
		listTipoServicios.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listTipoServicios")
	public void actualizarListbox() {
		if (listTipoServicios.getItemCount() > 0) {
			asignarEventos(listTipoServicios);
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
			TipoServicio tipoServicio = (TipoServicio) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tipoServicio", tipoServicio);
				String dir = "gc/tipoServicio/frm-tipoServicio-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tipoServicio", tipoServicio);
				String dir = "gc/tipoServicio/frm-tipoServicio-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				tipoServicio.setEstatus("Inactivo");

				if (servicioTipoServicio.editarTipoServicio(tipoServicio)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/tipoServicio/frm-tipoServicio-catalogo.zul";
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
