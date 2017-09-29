package ucla.si.controlador.gc.movil.slider;

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
import ucla.si.modelo.Slider;
import ucla.si.servicio.ServicioSlider;

public class ControladorConfiguracionSlider extends ControladorInicio {

	/**
	 * 
	 */

	@WireVariable
	private ServicioSlider servicioSlider;

	@Wire
	Listbox listSlider;

	@Wire
	private Textbox txtBuscar;

	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializar() {
		ListModelList<Slider> sliders = new ListModelList<Slider>(servicioSlider.listarSliders());
		Collections.reverse(sliders);
		sliders.setMultiple(false);
		listSlider.setModel(sliders);
		listSlider.setMultiple(false);
		listSlider.setCheckmark(false);

	}

	@Listen("onClick =#slider")
	public void sliderCatalogo() {
		String dir = "gc/movil/slider/cat-slider.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listSlider")
	public void actualizarListbox() {
		if (listSlider.getItemCount() > 0) {
			asignarEventos(listSlider);
		}
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {

		String dir = "gc/movil/slider/frm-incluir-slider.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
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

	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		ListModelList<Slider> sliders = new ListModelList<Slider>(
				servicioSlider.buscarSlider(txtBuscar.getValue().trim().toString()));
		Collections.reverse(sliders);
		sliders.setMultiple(false);
		listSlider.setModel(sliders);
		listSlider.setMultiple(false);
		listSlider.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	public void verificarAcciones(MouseEvent mouseEvent) {
		try {
			Button boton = (Button) mouseEvent.getTarget();
			Slider slider = (Slider) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("slider", slider);
				String dir = "gc/movil/slider/frm-editar-slider.zul";
				clearDivAppWeb(dir);
			}
			if (boton.getTooltiptext().equals("Eliminar")) {
				setAtributo("slider", slider);
				Messagebox.show("¿Estas seguro de eliminar el Slider?", "Confirmación",
						Messagebox.YES | Messagebox.CANCEL, Messagebox.QUESTION,
						new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event evt) throws InterruptedException {
								if (evt.getName().equals("onYes")) {
									servicioSlider.eliminarSlider((Slider) getAtributo("slider"));
									inicializar();
									txtBuscar.setValue("");
									Messagebox.show("Slider Eliminado con exito", "Información", Messagebox.OK,
											Messagebox.INFORMATION);

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
