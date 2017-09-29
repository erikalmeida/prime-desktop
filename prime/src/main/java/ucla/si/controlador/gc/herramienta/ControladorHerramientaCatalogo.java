package ucla.si.controlador.gc.herramienta;



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
import ucla.si.modelo.Herramienta;
import ucla.si.servicio.ServicioHerramienta;

public class ControladorHerramientaCatalogo  extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Herramienta herramienta;
	@WireVariable
	private ServicioHerramienta servicioHerramienta;

	@Wire
	Listbox listHerramientas;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Herramienta> modeloHerramientas = new ListModelList<Herramienta>(servicioHerramienta.listarHerramientas());
		modeloHerramientas.setMultiple(false);
		listHerramientas.setModel(modeloHerramientas);
		listHerramientas.setMultiple(false);
		listHerramientas.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/herramienta/frm-herramienta-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	

	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Herramienta> modeloHerramientas = new ListModelList<Herramienta>(
				servicioHerramienta.buscarHerramientas(txtBuscar.getValue().trim().toString()));
		modeloHerramientas.setMultiple(false);
		listHerramientas.setModel(modeloHerramientas);
		listHerramientas.setMultiple(false);
		listHerramientas.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listHerramientas")
	public void actualizarListbox() {
		if (listHerramientas.getItemCount() > 0) {
			asignarEventos(listHerramientas);
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
			Herramienta herramienta = (Herramienta) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("herramienta", herramienta);
				String dir = "gc/herramienta/frm-herramienta-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("herramienta", herramienta);
				String dir = "gc/herramienta/frm-herramienta-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				herramienta.setEstatus("Inactivo");

				if (servicioHerramienta.editarHerramienta(herramienta)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
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

