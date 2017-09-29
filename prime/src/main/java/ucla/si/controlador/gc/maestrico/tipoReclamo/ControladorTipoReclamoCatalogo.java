package ucla.si.controlador.gc.maestrico.tipoReclamo;


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
import ucla.si.modelo.TipoReclamo;
import ucla.si.servicio.ServicioTipoReclamo;

public class ControladorTipoReclamoCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoReclamo servicioTipoReclamo;
	
	@Wire
	Listbox listTipoReclamos;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<TipoReclamo> modeloTipoReclamos = new ListModelList<TipoReclamo>(servicioTipoReclamo.listarTipoReclamos());
		if(!modeloTipoReclamos.isEmpty()){
			modeloTipoReclamos.setMultiple(false);
			listTipoReclamos.setModel(modeloTipoReclamos);
			listTipoReclamos.setMultiple(false);
			listTipoReclamos.setCheckmark(false);
		}
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/tipoReclamo/frm-tipoReclamo-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<TipoReclamo> modeloTipoReclamos = new ListModelList<TipoReclamo>(
				servicioTipoReclamo.buscarTipoReclamos(txtBuscar.getValue().trim().toString()));
		modeloTipoReclamos.setMultiple(false);
		listTipoReclamos.setModel(modeloTipoReclamos);
		listTipoReclamos.setMultiple(false);
		listTipoReclamos.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listTipoReclamos")
	public void actualizarListbox() {
		if(listTipoReclamos.getItemCount() > 0){
			asignarEventos(listTipoReclamos);
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
			TipoReclamo tipoReclamo = (TipoReclamo) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tipoReclamo", tipoReclamo);
				String dir = "gc/tipoReclamo/frm-tipoReclamo-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tipoReclamo", tipoReclamo);
				String dir = "gc/tipoReclamo/frm-tipoReclamo-consultar.zul";
				clearDivApp(dir);
			}
			else if(boton.getTooltiptext().equals("Eliminar")){
				Messagebox.show("¡En Construcción!", "Información",Messagebox.OK, Messagebox.EXCLAMATION);
			}
			else {
				
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
