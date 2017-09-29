package ucla.si.controlador.gc.maestrico.rectificacion;


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
import ucla.si.modelo.Clase;
import ucla.si.modelo.Rectificacion;
import ucla.si.servicio.ServicioClase;
import ucla.si.servicio.ServicioRectificacion;

public class ControladorRectificacionCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioRectificacion servicioRectificacion;
	
	@Wire
	Listbox listRectificaciones;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Rectificacion> modeloRectificaciones = new ListModelList<Rectificacion>(servicioRectificacion.listarRectificaciones());
		modeloRectificaciones.setMultiple(false);
		listRectificaciones.setModel(modeloRectificaciones);
		listRectificaciones.setMultiple(false);
		listRectificaciones.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/rectificacion/frm-rectificacion-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Rectificacion> modeloRectificaciones = new ListModelList<Rectificacion>(
				servicioRectificacion.buscarRectificaciones(txtBuscar.getValue().trim().toString()));
		modeloRectificaciones.setMultiple(false);
		listRectificaciones.setModel(modeloRectificaciones);
		listRectificaciones.setMultiple(false);
		listRectificaciones.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listRectificaciones")
	public void actualizarListbox() {
		if(listRectificaciones.getItemCount() > 0){
			asignarEventos(listRectificaciones);
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
			Rectificacion rectificacion = (Rectificacion) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("rectificacion", rectificacion);
				String dir = "gc/rectificacion/frm-rectificacion-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("rectificacion", rectificacion);
				String dir = "gc/rectificacion/frm-rectificacion-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				rectificacion.setEstatus("Inactivo");

				if (servicioRectificacion.editarRectificacion(rectificacion)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/rectificacion/frm-rectificacion-catalogo.zul";
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
