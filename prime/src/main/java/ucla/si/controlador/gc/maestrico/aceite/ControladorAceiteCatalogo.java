package ucla.si.controlador.gc.maestrico.aceite;


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
import ucla.si.modelo.Aceite;
import ucla.si.servicio.ServicioAceite;

public class ControladorAceiteCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioAceite servicioAceite;
	
	@Wire
	Listbox listAceites;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Aceite> modeloAceites = new ListModelList<Aceite>(servicioAceite.listarAceites());
		modeloAceites.setMultiple(false);
		listAceites.setModel(modeloAceites);
		listAceites.setMultiple(false);
		listAceites.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/aceite/frm-aceite-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Aceite> modeloAceites = new ListModelList<Aceite>(
				servicioAceite.buscarAceites(txtBuscar.getValue().trim().toString()));
		modeloAceites.setMultiple(false);
		listAceites.setModel(modeloAceites);
		listAceites.setMultiple(false);
		listAceites.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	
	@Listen("onAfterRender =#listAceites")
	public void actualizarListbox() {
		if(listAceites.getItemCount() > 0){
			asignarEventos(listAceites);
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
			Aceite aceite = (Aceite) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("aceite", aceite);
				String dir = "gc/aceite/frm-aceite-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("aceite", aceite);
				String dir = "gc/aceite/frm-aceite-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				aceite.setEstatus("Inactivo");

				if (servicioAceite.editarAceite(aceite)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/aceite/frm-aceite-catalogo.zul";
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
