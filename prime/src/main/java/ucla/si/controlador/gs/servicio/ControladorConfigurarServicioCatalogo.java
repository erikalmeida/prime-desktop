package ucla.si.controlador.gs.servicio;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.metamodel.SetAttribute;

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

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Falla;
import ucla.si.modelo.Servicio;
import ucla.si.servicio.ServicioFalla;
import ucla.si.servicio.ServicioServicio;

public class ControladorConfigurarServicioCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioServicio servicioServicio;
	
	@WireVariable
	private ServicioFalla servicioFalla;
	
	@Wire
	Listbox listServicios;
	
	@Wire
	Listbox listFallas;

	@Override
	protected void inicializar() {
		
		
		ListModelList<Servicio> modeloServicios = new ListModelList<Servicio>(servicioServicio.listarServicios());
		modeloServicios.setMultiple(false);
		listServicios.setModel(modeloServicios);
		listServicios.setMultiple(false);
		listServicios.setCheckmark(true);
		

		
		
		
		
		
		/*	ListModelList<Falla> modeloFallas = new ListModelList<Falla>(servicioFalla.listarFallas());
			modeloFallas.setMultiple(false);
			listFallas.setModel(modeloFallas);
			listFallas.setMultiple(false);
			listFallas.setCheckmark(false);
		*/
		
		
		
	}
	
	@Listen("onClick =#btnConfigurar")
	public void annadirConfigurar() {
		System.out.println("entro bien");
		List<Listitem> listItemServicios = new ArrayList<Listitem>(listServicios.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		if (!listServicios.getItems().isEmpty()) {
			for (Listitem listitem : listItemServicios) {
				if (listitem.isSelected()) {
					Servicio servicio=(Servicio) listitem.getValue();
					if (servicio!=null){
						System.out.println("El servicio: "+ servicio.getDescripcion()+" su id es: "+servicio.getId());
						
						
						
						setAtributo("servicio", servicio);
						String dir = "gs/servicio/frm-configurar-servicio-falla.zul";
						clearDivApp(dir);
						
					}
				}
			}
		}
	}
	
	
	
	@Listen("onClick =#navFalla")
	public void fallas() {
		
		ListModelList<Falla> modeloFallas = new ListModelList<Falla>(servicioFalla.listarFallas());
		modeloFallas.setMultiple(false);
		listFallas.setModel(modeloFallas);
		listFallas.setMultiple(false);
		listFallas.setCheckmark(false);
		
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gs/servicio/frm-servicio-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listServicios")
	public void actualizarListbox() {
		if(listServicios.getItemCount() > 0){
			asignarEventos(listServicios);
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
			Servicio servicio = (Servicio) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Configurar")) {
				setAtributo("servicio", servicio);
				String dir = "gs/servicio/frm-configurar-servicio-falla.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("servicio", servicio);
				String dir = "gs/servicio/frm-servicio-consultar.zul";
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
