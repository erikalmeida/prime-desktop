package ucla.si.controlador.gc.promocion;


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
import ucla.si.modelo.Promocion;
import ucla.si.servicio.ServicioPromocion;

public class ControladorPromoCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioPromocion servicioPromocion;
	
	@Wire
	Listbox listPromociones;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Promocion> modeloPromociones = new ListModelList<Promocion>(servicioPromocion.listarPromocions());
		modeloPromociones.setMultiple(false);
		listPromociones.setModel(modeloPromociones);
		listPromociones.setMultiple(false);
		listPromociones.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/Promocion/frm-Promocion-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Promocion> modeloPromociones = new ListModelList<Promocion>(
				servicioPromocion.buscarPromociones(txtBuscar.getValue().trim().toString()));
		modeloPromociones.setMultiple(false);
		listPromociones.setModel(modeloPromociones);
		listPromociones.setMultiple(false);
		listPromociones.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listPromocions")
	public void actualizarListbox() {
		if(listPromociones.getItemCount() > 0){
			asignarEventos(listPromociones);
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
			Promocion Promocion = (Promocion) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("Promocion", Promocion);
				String dir = "gc/Promocion/frm-Promocion-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("Promocion", Promocion);
				String dir = "gc/Promocion/frm-Promocion-consultar.zul";
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
