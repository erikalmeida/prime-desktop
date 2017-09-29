package ucla.si.controlador.gc.maestrico.tipoAceite;


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
import ucla.si.modelo.TipoAceite;
import ucla.si.servicio.ServicioTipoAceite;

public class ControladorTipoAceiteCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoAceite servicioTipoAceite;
	
	@Wire
	Listbox listTipoAceites;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<TipoAceite> modeloTipoAceites = new ListModelList<TipoAceite>(servicioTipoAceite.listarTipoAceites());
		modeloTipoAceites.setMultiple(false);
		listTipoAceites.setModel(modeloTipoAceites);
		listTipoAceites.setMultiple(false);
		listTipoAceites.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/tipoAceite/frm-tipoAceite-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<TipoAceite> modeloTipoAceites = new ListModelList<TipoAceite>(
				servicioTipoAceite.buscarTipoAceites(txtBuscar.getValue().trim().toString()));
		modeloTipoAceites.setMultiple(false);
		listTipoAceites.setModel(modeloTipoAceites);
		listTipoAceites.setMultiple(false);
		listTipoAceites.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listTipoAceites")
	public void actualizarListbox() {
		if(listTipoAceites.getItemCount() > 0){
			asignarEventos(listTipoAceites);
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
			TipoAceite tipoAceite = (TipoAceite) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tipoAceite", tipoAceite);
				String dir = "gc/tipoAceite/frm-tipoAceite-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tipoAceite", tipoAceite);
				String dir = "gc/tipoAceite/frm-tipoAceite-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				tipoAceite.setEstatus("Inactivo");

				if (servicioTipoAceite.editarTipoAceite(tipoAceite)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/tipoAceite/frm-tipoAceite-catalogo.zul";
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
