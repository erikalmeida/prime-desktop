package ucla.si.controlador.gc.web.preguntaFrecuente;

import java.util.Collections;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
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
import ucla.si.modelo.PreguntaFrecuente;
import ucla.si.servicio.ServicioPreguntaFrecuente;

public class ControladorConfiguracionPreguntaFrecuente extends ControladorInicio {

	/**
	 * 
	 */

	@WireVariable
	private ServicioPreguntaFrecuente servicioPreguntaFrecuente;

	@Wire
	Listbox listPreguntasFrecuentes;

	@Wire
	private Textbox txtBuscar;

	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializar() {
		ListModelList<PreguntaFrecuente> preguntasFrecuentes = new ListModelList<PreguntaFrecuente>(
				servicioPreguntaFrecuente.transformarHtml());
		Collections.reverse(preguntasFrecuentes);
		preguntasFrecuentes.setMultiple(false);
		listPreguntasFrecuentes.setModel(preguntasFrecuentes);
		listPreguntasFrecuentes.setMultiple(false);
		listPreguntasFrecuentes.setCheckmark(false);

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

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/web/preguntaFrecuente/frm-incluir-pregunta-frecuente.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listPreguntasFrecuentes")
	public void actualizarListbox() {
		if (listPreguntasFrecuentes.getItemCount() > 0) {
			asignarEventos(listPreguntasFrecuentes);
		}
	}

	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		ListModelList<PreguntaFrecuente> preguntasFrecuentes = new ListModelList<PreguntaFrecuente>(
				servicioPreguntaFrecuente.buscarPreguntaFrecuente(txtBuscar.getValue().trim().toString()));
		Collections.reverse(servicioPreguntaFrecuente.transformarHtml(preguntasFrecuentes));
		preguntasFrecuentes.setMultiple(false);
		listPreguntasFrecuentes.setModel(preguntasFrecuentes);
		listPreguntasFrecuentes.setMultiple(false);
		listPreguntasFrecuentes.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	public void verificarAcciones(MouseEvent mouseEvent) {
		try {
			Button boton = (Button) mouseEvent.getTarget();
			PreguntaFrecuente preguntaFrecuente = (PreguntaFrecuente) (((Listitem) mouseEvent.getTarget().getParent()
					.getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("preguntaFrecuente", preguntaFrecuente);
				String dir = "gc/web/preguntaFrecuente/frm-editar-pregunta-frecuente.zul";
				clearDivAppWeb(dir);
			}
			if (boton.getTooltiptext().equals("Eliminar")) {
				setAtributo("preguntaFrecuente", preguntaFrecuente);
				Messagebox.show("¿Estas seguro de eliminar la Pregunta Frecuente?", "Confirmación",
						Messagebox.YES | Messagebox.CANCEL, Messagebox.QUESTION,
						new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event evt) throws InterruptedException {
								if (evt.getName().equals("onYes")) {
									servicioPreguntaFrecuente.eliminarPreguntaFrecuente(
											(PreguntaFrecuente) getAtributo("preguntaFrecuente"));
									inicializar();
									txtBuscar.setValue("");
									Messagebox.show("Pregunta Frecuente Eliminado con exito", "Información",
											Messagebox.OK, Messagebox.INFORMATION);
								}
							}
						});

			}

		} catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		} catch (org.hibernate.TransactionException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		}
	}
}
