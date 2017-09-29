package ucla.si.controlador.gc.tecnologia;



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
import ucla.si.modelo.Tecnologia;
import ucla.si.servicio.ServicioTecnologia;

public class ControladorTecnologiaCatalogo  extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tecnologia tecnologia;
	@WireVariable
	private ServicioTecnologia servicioTecnologia;

	@Wire
	Listbox listTecnologias;
	

	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Tecnologia> modeloTecnologias = new ListModelList<Tecnologia>(servicioTecnologia.listarTecnologias());
		modeloTecnologias.setMultiple(false);
		listTecnologias.setModel(modeloTecnologias);
		listTecnologias.setMultiple(false);
		listTecnologias.setCheckmark(false);
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/tecnologia/frm-tecnologia-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Tecnologia> modeloTecnologias = new ListModelList<Tecnologia>(
				servicioTecnologia.buscarTecnologias(txtBuscar.getValue().trim().toString()));
		modeloTecnologias.setMultiple(false);
		listTecnologias.setModel(modeloTecnologias);
		listTecnologias.setMultiple(false);
		listTecnologias.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listTecnologias")
	public void actualizarListbox() {
		if (listTecnologias.getItemCount() > 0) {
			asignarEventos(listTecnologias);
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
			Tecnologia tecnologia = (Tecnologia) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tecnologia", tecnologia);
				String dir = "gc/tecnologia/frm-tecnologia-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tecnologia", tecnologia);
				String dir = "gc/tecnologia/frm-tecnologia-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				tecnologia.setEstatus("Inactivo");

				if (servicioTecnologia.editarTecnologia(tecnologia)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/tecnologia/frm-tecnologia-catalogo.zul";
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

