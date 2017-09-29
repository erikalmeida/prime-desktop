package ucla.si.controlador.gc.maestrico.tipoCaja;


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
import ucla.si.modelo.TipoCaja;
import ucla.si.servicio.ServicioTipoCaja;

public class ControladorTipoCajaCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoCaja servicioTipoCaja;
	
	@Wire
	Listbox listTipoCajas;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<TipoCaja> modeloTipoCajas = new ListModelList<TipoCaja>(servicioTipoCaja.listarTipoCajas());
		modeloTipoCajas.setMultiple(false);
		listTipoCajas.setModel(modeloTipoCajas);
		listTipoCajas.setMultiple(false);
		listTipoCajas.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/tipoCaja/frm-tipoCaja-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<TipoCaja> modeloTipoCajas = new ListModelList<TipoCaja>(
				servicioTipoCaja.buscarTipoCajas(txtBuscar.getValue().trim().toString()));
		modeloTipoCajas.setMultiple(false);
		listTipoCajas.setModel(modeloTipoCajas);
		listTipoCajas.setMultiple(false);
		listTipoCajas.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listTipoCajas")
	public void actualizarListbox() {
		if(listTipoCajas.getItemCount() > 0){
			asignarEventos(listTipoCajas);
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
			TipoCaja tipoCaja = (TipoCaja) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tipoCaja", tipoCaja);
				String dir = "gc/tipoCaja/frm-tipoCaja-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tipoCaja", tipoCaja);
				String dir = "gc/tipoCaja/frm-tipoCaja-consultar.zul";
				clearDivApp(dir);
			}
			else if (boton.getTooltiptext().equals("Eliminar")) {
				tipoCaja.setEstatus("Inactivo");

				if (servicioTipoCaja.editarTipoCaja(tipoCaja)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/tipoCaja/frm-tipoCaja-catalogo.zul";
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
