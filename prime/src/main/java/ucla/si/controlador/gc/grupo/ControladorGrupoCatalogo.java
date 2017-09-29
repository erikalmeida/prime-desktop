package ucla.si.controlador.gc.grupo;

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
import ucla.si.modelo.Grupo;
import ucla.si.servicio.ServicioGrupo;

public class ControladorGrupoCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioGrupo servicioGrupo;

	@Wire
	Listbox listGrupos;

	@Override
	protected void inicializar() {
		ListModelList<Grupo> modeloGrupos = new ListModelList<Grupo>(servicioGrupo.listarGrupos());
		modeloGrupos.setMultiple(false);
		listGrupos.setModel(modeloGrupos);
		listGrupos.setMultiple(false);
		listGrupos.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/grupo/frm-grupo-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listGrupos")
	public void actualizarListbox() {
		if (listGrupos.getItemCount() > 0) {
			asignarEventos(listGrupos);
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
			Grupo grupo = (Grupo) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("grupo", grupo);
				String dir = "gc/grupo/frm-grupo-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("grupo", grupo);
				String dir = "gc/grupo/frm-grupo-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				Messagebox.show("¡En Construcción!", "Información", Messagebox.OK, Messagebox.EXCLAMATION);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				grupo.setEstatus("Inactivo");

				if (servicioGrupo.editarGrupo(grupo)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/grupo/frm-grupo-catalogo.zul";
					clearDivApp(dir);
				}

				else {
					Messagebox.show("Error al Eliminarr", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		} catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		} catch (org.hibernate.TransactionException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/grupo/frm-grupo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbGrupo")
	public void grupo() {
		String dir = "gc/grupo/frm-grupo-catalogo.zul";
		clearDivApp(dir);
	}

}
