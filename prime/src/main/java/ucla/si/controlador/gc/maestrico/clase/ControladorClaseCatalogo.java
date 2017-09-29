package ucla.si.controlador.gc.maestrico.clase;


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
import ucla.si.modelo.Clase;
import ucla.si.servicio.ServicioClase;

public class ControladorClaseCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	
	private Clase clase;
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioClase servicioClase;
	
	@Wire
	Listbox listClases;
	

	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Clase> modeloClases = new ListModelList<Clase>(servicioClase.listarClases());
		modeloClases.setMultiple(false);
		listClases.setModel(modeloClases);
		listClases.setMultiple(false);
		listClases.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/clase/frm-clase-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Clase> modeloClases = new ListModelList<Clase>(
				servicioClase.buscarClases(txtBuscar.getValue().trim().toString()));
		modeloClases.setMultiple(false);
		listClases.setModel(modeloClases);
		listClases.setMultiple(false);
		listClases.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listClases")
	public void actualizarListbox() {
		if(listClases.getItemCount() > 0){
			asignarEventos(listClases);
		}
	}
	
	private void asignarEventos(Component padre) {
		String click = "onClick";
		for (Component hijo : padre.getChildren()){
			if (hijo instanceof Button){
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
			Clase clase = (Clase) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("clase", clase);
				String dir = "gc/clase/frm-clase-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("clase", clase);
				String dir = "gc/clase/frm-clase-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				clase.setEstatus("Inactivo");

				if (servicioClase.editarClase(clase)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/clase/frm-clase-catalogo.zul";
					clearDivApp(dir);
				}
			}
			
		}
		catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información",Messagebox.OK, Messagebox.ERROR);
		} 
		catch (org.hibernate.TransactionException e){
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información",Messagebox.OK, Messagebox.ERROR);
		}
	}
	

}
