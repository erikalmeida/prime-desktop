package ucla.si.controlador.gc.maestrico.profesion;

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
import ucla.si.modelo.Profesion;
import ucla.si.servicio.ServicioProfesion;

public class ControladorProfesionCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Profesion profesion;
	
	@WireVariable
	private ServicioProfesion servicioProfesion;
	
	@Wire
	Listbox listProfesiones;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		ListModelList<Profesion> modeloProfesiones = new ListModelList<Profesion>(servicioProfesion.listarProfesiones());
		if(modeloProfesiones.getSize() != 0){
			modeloProfesiones.setMultiple(false);
			listProfesiones.setModel(modeloProfesiones);
			listProfesiones.setMultiple(false);
			listProfesiones.setCheckmark(false);			
		}

		
	}
	
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "pc/profesion/frm-profesion-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Profesion> modeloProfesiones = new ListModelList<Profesion>(
				servicioProfesion.buscarProfesiones(txtBuscar.getValue().trim().toString()));
		modeloProfesiones.setMultiple(false);
		listProfesiones.setModel(modeloProfesiones);
		listProfesiones.setMultiple(false);
		listProfesiones.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listProfesiones")
	public void actualizarListbox() {
		if(listProfesiones.getItemCount() > 0){
			asignarEventos(listProfesiones);
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
			Profesion profesion = (Profesion) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("profesion", profesion);
				String dir = "pc/profesion/frm-profesion-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("profesion", profesion);
				String dir = "pc/profesion/frm-profesion-consultar.zul";
				clearDivApp(dir);
			}
			else if (boton.getTooltiptext().equals("Eliminar")) {
				profesion.setEstatus("Inactivo");

				if (servicioProfesion.editarProfesion(profesion)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "pc/profesion/frm-profesion-catalogo.zul";
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
