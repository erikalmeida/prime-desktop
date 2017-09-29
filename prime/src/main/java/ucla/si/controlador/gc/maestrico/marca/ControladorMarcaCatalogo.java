package ucla.si.controlador.gc.maestrico.marca;


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

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Marca;
import ucla.si.servicio.ServicioMarca;

public class ControladorMarcaCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioMarca servicioMarca;
	
	@Wire
	Listbox listMarcas;

	@Override
	protected void inicializar() {
		ListModelList<Marca> modeloMarcas = new ListModelList<Marca>(servicioMarca.listarMarcas());
		modeloMarcas.setMultiple(false);
		listMarcas.setModel(modeloMarcas);
		listMarcas.setMultiple(false);
		listMarcas.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/marca/frm-marca-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listMarcas")
	public void actualizarListbox() {
		if(listMarcas.getItemCount() > 0){
			asignarEventos(listMarcas);
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
			Marca marca = (Marca) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("marca", marca);
				String dir = "gc/marca/frm-marca-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("marca", marca);
				String dir = "gc/marca/frm-marca-consultar.zul";
				clearDivApp(dir);
			}
			else if (boton.getTooltiptext().equals("Eliminar")) {
				marca.setEstatus("Inactivo");

				if (servicioMarca.editarMarca(marca)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/marca/frm-marca-catalogo.zul";
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
