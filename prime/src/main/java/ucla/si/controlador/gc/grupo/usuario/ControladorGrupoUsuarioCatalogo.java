package ucla.si.controlador.gc.grupo.usuario;

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

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Funcion;
import ucla.si.modelo.Usuario;
import ucla.si.servicio.ServicioFuncion;
import ucla.si.servicio.ServicioUsuario;

public class ControladorGrupoUsuarioCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioUsuario servicioUsuario;

	@Wire
	Listbox listUsuarios;

	@Override
	protected void inicializar() {
		ListModelList<Usuario> modeloClases = new ListModelList<Usuario>(servicioUsuario.listarUsuariosSinAdministrador());
		modeloClases.setMultiple(false);
		listUsuarios.setModel(modeloClases);
		listUsuarios.setMultiple(false);
		listUsuarios.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/funcion/frm-funcion-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listUsuarios")
	public void actualizarListbox() {
		if (listUsuarios.getItemCount() > 0) {
			asignarEventos(listUsuarios);
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
			Usuario usuario = (Usuario) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("usuario", usuario);
				String dir = "gc/grupo/usuario/frm-grupo-usuario-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("usuario", usuario);
				//String dir = "gc/funcion/frm-funcion-consultar.zul";
				//clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				usuario.setEstatus("Inactivo");

				/*if (servicioUsuario.editarFuncion(funcion)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/funcion/frm-funcion-catalogo.zul";
					clearDivApp(dir);
				}

				else {
					Messagebox.show("Error al eliminarr", "Error", Messagebox.OK, Messagebox.ERROR);
				}*/

			} else {

			}
		} catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		} catch (org.hibernate.TransactionException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/grupo/usuario/frm-grupo-usuario-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbFuncion")
	public void funcion() {
		String dir = "gc/grupo/usuario/frm-grupo-usuario-catalogo.zul";
		clearDivApp(dir);
	}

}
