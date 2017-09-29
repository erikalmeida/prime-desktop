package ucla.si.controlador.gc.maestrico.marcaTecnologia;

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

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.MarcaTecnologia;
import ucla.si.servicio.ServicioMarcaTecnologia;

public class ControladorMarcaTecnologiaCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MarcaTecnologia marcaTecnologia;
	@WireVariable
	private ServicioMarcaTecnologia servicioMarcaTecnologia;

	@Wire
	Listbox listMarcaTecnologias;

	@Override
	protected void inicializar() {
		ListModelList<MarcaTecnologia> modeloMarcaTecnologias = new ListModelList<MarcaTecnologia>(servicioMarcaTecnologia.listarMarcaTecnologias());
		modeloMarcaTecnologias.setMultiple(false);
		listMarcaTecnologias.setModel(modeloMarcaTecnologias);
		listMarcaTecnologias.setMultiple(false);
		listMarcaTecnologias.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/marcaTecnologia/frm-marcaTecnologia-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listMarcaTecnologias")
	public void actualizarListbox() {
		if (listMarcaTecnologias.getItemCount() > 0) {
			asignarEventos(listMarcaTecnologias);
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
			MarcaTecnologia marcaTecnologia = (MarcaTecnologia) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("marcaTecnologia", marcaTecnologia);
				String dir = "gc/marcaTecnologia/frm-marcaTecnologia-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("marcaTecnologia", marcaTecnologia);
				String dir = "gc/marcaTecnologia/frm-marcaTecnologia-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				marcaTecnologia.setEstatus("Inactivo");

				if (servicioMarcaTecnologia.editarMarcaTecnologia(marcaTecnologia)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul";
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
