package ucla.si.controlador.gc.maestrico.tipoEventualidad;

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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoEventualidad;
import ucla.si.servicio.ServicioTipoEventualidad;

public class ControladorTipoEventualidadCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoEventualidad servicioTipoEventualidad;

	@Wire
	Listbox listTipoEventualidades;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		ListModelList<TipoEventualidad> modeloTipoEventualidad = new ListModelList<TipoEventualidad>(
				servicioTipoEventualidad.listarTipoEventualidades());
		if (modeloTipoEventualidad.getSize() != 0) {
			modeloTipoEventualidad.setMultiple(false);
			listTipoEventualidades.setModel(modeloTipoEventualidad);
			listTipoEventualidades.setMultiple(false);
			listTipoEventualidades.setCheckmark(false);
		}
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "pc/tipoEventualidad/frm-tipoEventualidad-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<TipoEventualidad> modeloTipoEventualidades = new ListModelList<TipoEventualidad>(
				servicioTipoEventualidad.buscarTipoEventualidades(txtBuscar.getValue().trim().toString()));
		modeloTipoEventualidades.setMultiple(false);
		listTipoEventualidades.setModel(modeloTipoEventualidades);
		listTipoEventualidades.setMultiple(false);
		listTipoEventualidades.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listTipoEventualidades")
	public void actualizarListbox() {
		if (listTipoEventualidades.getItemCount() > 0) {
			asignarEventos(listTipoEventualidades);
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
			TipoEventualidad tipoEventualidad = (TipoEventualidad) (((Listitem) mouseEvent.getTarget().getParent()
					.getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tipoEventualidad", tipoEventualidad);
				String dir = "pc/tipoEventualidad/frm-tipoEventualidad-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tipoEventualidad", tipoEventualidad);
				String dir = "pc/tipoEventualidad/frm-tipoEventualidad-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				tipoEventualidad.setEstatus("Inactivo");

				if (servicioTipoEventualidad.editarTipoEventualidad(tipoEventualidad)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
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