package ucla.si.controlador.gc.maestrico.tipoTecnologia;

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
import ucla.si.modelo.TipoTecnologia;
import ucla.si.servicio.ServicioTipoTecnologia;

public class ControladorTipoTecnologiaCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoTecnologia tipoTecnologia;
	@WireVariable
	private ServicioTipoTecnologia servicioTipoTecnologia;

	@Wire
	Listbox listTipoTecnologias;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<TipoTecnologia> modeloTipoTecnologias = new ListModelList<TipoTecnologia>(servicioTipoTecnologia.listarTipoTecnologias());
		modeloTipoTecnologias.setMultiple(false);
		listTipoTecnologias.setModel(modeloTipoTecnologias);
		listTipoTecnologias.setMultiple(false);
		listTipoTecnologias.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/tipoTecnologia/frm-tipoTecnologia-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<TipoTecnologia> modeloTipoTecnologias = new ListModelList<TipoTecnologia>(
				servicioTipoTecnologia.buscarTipoTecnologias(txtBuscar.getValue().trim().toString()));
		modeloTipoTecnologias.setMultiple(false);
		listTipoTecnologias.setModel(modeloTipoTecnologias);
		listTipoTecnologias.setMultiple(false);
		listTipoTecnologias.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listTipoTecnologias")
	public void actualizarListbox() {
		if (listTipoTecnologias.getItemCount() > 0) {
			asignarEventos(listTipoTecnologias);
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
			TipoTecnologia tipoTecnologia = (TipoTecnologia) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tipoTecnologia", tipoTecnologia);
				String dir = "gc/tipoTecnologia/frm-tipoTecnologia-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tipoTecnologia", tipoTecnologia);
				String dir = "gc/tipoTecnologia/frm-tipoTecnologia-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				tipoTecnologia.setEstatus("Inactivo");

				if (servicioTipoTecnologia.editarTipoTecnologia(tipoTecnologia)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul";
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