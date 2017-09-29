package ucla.si.controlador.gc.maestrico.deporte;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Deporte;
import ucla.si.servicio.ServicioDeporte;

public class ControladorDeporteCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Deporte deporte;
	
	@WireVariable
	private ServicioDeporte servicioDeporte;
	
	@Wire
	Listbox listDeportes;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		
		ListModelList<Deporte> modeloDeportes = new ListModelList<Deporte>(servicioDeporte.listarDeportes());
		if(modeloDeportes.getSize() != 0){
			modeloDeportes.setMultiple(false);
			listDeportes.setModel(modeloDeportes);
			listDeportes.setMultiple(false);
			listDeportes.setCheckmark(false);			
		}

	}
	

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
	String dir = "pc/deporte/frm-deporte-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Deporte> modeloDeportes = new ListModelList<Deporte>(
				servicioDeporte.buscarDeportes(txtBuscar.getValue().trim().toString()));
		modeloDeportes.setMultiple(false);
		listDeportes.setModel(modeloDeportes);
		listDeportes.setMultiple(false);
		listDeportes.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listDeportes")
	public void actualizarListbox() {
		if(listDeportes.getItemCount() > 0){
			asignarEventos(listDeportes);
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
			Deporte deporte = (Deporte) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("deporte", deporte);
				String dir = "pc/deporte/frm-deporte-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("deporte", deporte);
				String dir = "pc/deporte/frm-deporte-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				deporte.setEstatus("Inactivo");

				if (servicioDeporte.editarDeporte(deporte)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "pc/deporte/frm-deporte-catalogo.zul";
					clearDivApp(dir);
				}

				else {
					Messagebox.show("Error al eliminarr", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			} 
			

		}
		catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("Tiempo Expirado para la transaccion!", "Informacion",Messagebox.OK, Messagebox.ERROR);
		} 
		catch (org.hibernate.TransactionException e){
			Messagebox.show("Tiempo Expirado para la transaccion!", "Informacion",Messagebox.OK, Messagebox.ERROR);
		}
	}

}
