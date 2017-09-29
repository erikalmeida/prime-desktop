package ucla.si.controlador.gc.maestrico.horario;

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
import ucla.si.modelo.Horario;
import ucla.si.modelo.Horario;
import ucla.si.servicio.ServicioHorario;
import ucla.si.servicio.ServicioHorario;

public class ControladorHorarioCatalogo extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioHorario servicioHorario;

	@Wire
	Listbox listHorarios;

	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Horario> modeloHorarios = new ListModelList<Horario>(servicioHorario.listarHorarios());
		modeloHorarios.setMultiple(false);
		listHorarios.setModel(modeloHorarios);
		listHorarios.setMultiple(false);
		listHorarios.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/horario/frm-horario-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Horario> modeloHorarios = new ListModelList<Horario>(
				servicioHorario.buscarHorarios(txtBuscar.getValue().trim().toString()));
		modeloHorarios.setMultiple(false);
		listHorarios.setModel(modeloHorarios);
		listHorarios.setMultiple(false);
		listHorarios.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listHorarios")
	public void actualizarListbox() {
		if (listHorarios.getItemCount() > 0) {
			asignarEventos(listHorarios);
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
			Horario horario = (Horario) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("horario", horario);
				String dir = "gc/horario/frm-horario-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("horario", horario);
				String dir = "gc/horario/frm-horario-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				horario.setEstatus("Inactivo");

				if (servicioHorario.editarHorario(horario)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/horario/frm-horario-catalogo.zul";
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
