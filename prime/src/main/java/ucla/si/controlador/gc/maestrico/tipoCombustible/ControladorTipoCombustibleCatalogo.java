package ucla.si.controlador.gc.maestrico.tipoCombustible;


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
import ucla.si.modelo.TipoCombustible;
import ucla.si.servicio.ServicioTipoCombustible;

public class ControladorTipoCombustibleCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoCombustible servicioTipoCombustible;
	
	@Wire
	Listbox listTipoCombustibles;
	
	@Wire
	Textbox txtBuscar;
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<TipoCombustible> modeloTipoCombustibles = new ListModelList<TipoCombustible>(
				servicioTipoCombustible.buscarTipoCombustibles(txtBuscar.getValue().trim().toString()));
		modeloTipoCombustibles.setMultiple(false);
		listTipoCombustibles.setModel(modeloTipoCombustibles);
		listTipoCombustibles.setMultiple(false);
		listTipoCombustibles.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Override
	protected void inicializar() {
		ListModelList<TipoCombustible> modeloTipoCombustibles = new ListModelList<TipoCombustible>(servicioTipoCombustible.listarTipoCombustibles());
		modeloTipoCombustibles.setMultiple(false);
		listTipoCombustibles.setModel(modeloTipoCombustibles);
		listTipoCombustibles.setMultiple(false);
		listTipoCombustibles.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/tipoCombustible/frm-tipoCombustible-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listTipoCombustibles")
	public void actualizarListbox() {
		if(listTipoCombustibles.getItemCount() > 0){
			asignarEventos(listTipoCombustibles);
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
			TipoCombustible tipoCombustible = (TipoCombustible) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("tipoCombustible", tipoCombustible);
				String dir = "gc/tipoCombustible/frm-tipoCombustible-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("tipoCombustible", tipoCombustible);
				String dir = "gc/tipoCombustible/frm-tipoCombustible-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				tipoCombustible.setEstatus("Inactivo");

				if (servicioTipoCombustible.editarTipoCombustible(tipoCombustible)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/tipoCombustible/frm-tipoCombustible-catalogo.zul";
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
