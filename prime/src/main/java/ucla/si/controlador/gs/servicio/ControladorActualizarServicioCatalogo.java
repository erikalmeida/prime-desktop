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

public class ControladorActualizarServicioCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioServicio servicioServicio;
	
	
	
	@Wire
	Listbox listServicios;
	
	

	@Override
	protected void inicializar() {
		
		
		ListModelList<Servicio> modeloServicios = new ListModelList<Servicio>(servicioServicio.listarServiciosConfigurados());
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
	
	@Listen("onClick =#btnActualizar")
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
						String dir = "gs/servicio/frm-actualizar-servicio-recurso.zul";
						clearDivApp(dir);
						
					}
				}
			}
		}
	}
	
	
	
	
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gs/servicio/frm-servicio-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	
	
	
	
	
				
		
	

}
