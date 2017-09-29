package ucla.si.controlador.gc.maestrico.combustible;


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
import ucla.si.modelo.Combustible;
import ucla.si.servicio.ServicioCombustible;

public class ControladorCombustibleCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioCombustible servicioCombustible;
	
	@Wire
	Listbox listCombustibles;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Combustible> modeloCombustibles = new ListModelList<Combustible>(servicioCombustible.listarCombustibles());
		modeloCombustibles.setMultiple(false);
		listCombustibles.setModel(modeloCombustibles);
		listCombustibles.setMultiple(false);
		listCombustibles.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/combustible/frm-combustible-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Combustible> modeloCombustibles = new ListModelList<Combustible>(
				servicioCombustible.buscarCombustibles(txtBuscar.getValue().trim().toString()));
		modeloCombustibles.setMultiple(false);
		listCombustibles.setModel(modeloCombustibles);
		listCombustibles.setMultiple(false);
		listCombustibles.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listCombustibles")
	public void actualizarListbox() {
		if(listCombustibles.getItemCount() > 0){
			asignarEventos(listCombustibles);
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
			Combustible Combustible = (Combustible) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("Combustible", Combustible);
				String dir = "gc/combustible/frm-combustible-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("Combustible", Combustible);
				String dir = "gc/combustible/frm-combustible-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				Combustible.setEstatus("Inactivo");

				if (servicioCombustible.editarCombustible(Combustible)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/combustible/frm-combustible-catalogo.zul";
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
