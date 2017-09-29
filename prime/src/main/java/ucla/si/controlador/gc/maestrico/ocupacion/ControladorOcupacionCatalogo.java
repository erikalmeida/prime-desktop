package ucla.si.controlador.gc.maestrico.ocupacion;

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
import ucla.si.modelo.Ocupacion;
import ucla.si.servicio.ServicioOcupacion;

public class ControladorOcupacionCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Ocupacion ocupacion;
	
	@WireVariable
	private ServicioOcupacion servicioOcupacion;
	
	@Wire
	Listbox listOcupaciones;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		ListModelList<Ocupacion> modeloOcupaciones = new ListModelList<Ocupacion>(servicioOcupacion.listarOcupaciones());
		if(modeloOcupaciones.getSize() != 0){
			modeloOcupaciones.setMultiple(false);
			listOcupaciones.setModel(modeloOcupaciones);
			listOcupaciones.setMultiple(false);
			listOcupaciones.setCheckmark(false);			
		}
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "pc/ocupacion/frm-ocupacion-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Ocupacion> modeloOcupaciones = new ListModelList<Ocupacion>(
				servicioOcupacion.buscarOcupaciones(txtBuscar.getValue().trim().toString()));
		modeloOcupaciones.setMultiple(false);
		listOcupaciones.setModel(modeloOcupaciones);
		listOcupaciones.setMultiple(false);
		listOcupaciones.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listOcupaciones")
	public void actualizarListbox() {
		if(listOcupaciones.getItemCount() > 0){
			asignarEventos(listOcupaciones);
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
			Ocupacion ocupacion = (Ocupacion) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("ocupacion", ocupacion);
				String dir = "pc/ocupacion/frm-ocupacion-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("ocupacion", ocupacion);
				String dir = "pc/ocupacion/frm-ocupacion-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				ocupacion.setEstatus("Inactivo");

				if (servicioOcupacion.editarOcupacion(ocupacion)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
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
