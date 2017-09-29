package ucla.si.controlador.gc.maestrico.caja;


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
import ucla.si.modelo.Caja;
import ucla.si.servicio.ServicioCaja;

public class ControladorCajaCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioCaja servicioCaja;
	
	@Wire
	Listbox listCajas;
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Caja> modeloCajas = new ListModelList<Caja>(servicioCaja.listarCajas());
		modeloCajas.setMultiple(false);
		listCajas.setModel(modeloCajas);
		listCajas.setMultiple(false);
		listCajas.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/caja/frm-caja-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Caja> modeloCajas = new ListModelList<Caja>(
				servicioCaja.buscarCajas(txtBuscar.getValue().trim().toString()));
		modeloCajas.setMultiple(false);
		listCajas.setModel(modeloCajas);
		listCajas.setMultiple(false);
		listCajas.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listCajas")
	public void actualizarListbox() {
		if(listCajas.getItemCount() > 0){
			asignarEventos(listCajas);
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
			Caja caja = (Caja) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("caja", caja);
				String dir = "gc/caja/frm-caja-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("caja", caja);
				String dir = "gc/caja/frm-caja-consultar.zul";
				clearDivApp(dir);
			}
			else if (boton.getTooltiptext().equals("Eliminar")) {
				caja.setEstatus("Inactivo");

				if (servicioCaja.editarCaja(caja)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/caja/frm-caja-catalogo.zul";
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
