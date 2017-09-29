package ucla.si.controlador.gc.maestrico.motor;


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
import ucla.si.modelo.Motor;
import ucla.si.servicio.ServicioMotor;

public class ControladorMotorCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	
	private Motor motor;
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioMotor servicioMotor;
	
	@Wire
	Listbox listMotores;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		ListModelList<Motor> modeloMotores = new ListModelList<Motor>(servicioMotor.listarMotores());
		modeloMotores.setMultiple(false);
		listMotores.setModel(modeloMotores);
		listMotores.setMultiple(false);
		listMotores.setCheckmark(false);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/motor/frm-motor-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Motor> modeloMotores = new ListModelList<Motor>(
				servicioMotor.buscarMotores(txtBuscar.getValue().trim().toString()));
		modeloMotores.setMultiple(false);
		listMotores.setModel(modeloMotores);
		listMotores.setMultiple(false);
		listMotores.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listMotores")
	public void actualizarListbox() {
		if(listMotores.getItemCount() > 0){
			asignarEventos(listMotores);
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
			Motor motor = (Motor) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("motor", motor);
				String dir = "gc/motor/frm-motor-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("motor", motor);
				String dir = "gc/motor/frm-motor-consultar.zul";
				clearDivApp(dir);
			}
			else if (boton.getTooltiptext().equals("Eliminar")) {
				motor.setEstatus("Inactivo");

				if (servicioMotor.editarMotor(motor)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/motor/frm-motor-catalogo.zul";
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
