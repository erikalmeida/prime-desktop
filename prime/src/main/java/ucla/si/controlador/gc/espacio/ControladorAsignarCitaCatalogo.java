package ucla.si.controlador.gc.espacio;


import java.util.ArrayList;
import java.util.List;

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
import ucla.si.modelo.Cita;
import ucla.si.modelo.Espacio;
import ucla.si.modelo.Falla;
import ucla.si.modelo.Servicio;
import ucla.si.servicio.ServicioCita;
import ucla.si.servicio.ServicioEspacio;

public class ControladorAsignarCitaCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioCita servicioCita;
	@WireVariable
	private ServicioEspacio servicioEspacio;
	@WireVariable
	private Espacio espacio;
	
	@Wire
	Listbox listCitasA;

	@Override
	protected void inicializar() {
		espacio = (Espacio) getAtributo("espacio");
		System.out.println("Aqui va de controlador a controlador:"+espacio.getDescripcion());
		ListModelList<Cita> modeloCitas = new ListModelList<Cita>(servicioCita.listarAprobadas());
		modeloCitas.setMultiple(false);
		listCitasA.setModel(modeloCitas);
		listCitasA.setMultiple(false);
		listCitasA.setCheckmark(true);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/cita/frm-cita-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listCitas")
	public void actualizarListbox() {
		if(listCitasA.getItemCount() > 0){
			asignarEventos(listCitasA);
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
	
	@Listen("onClick =#btnAnnadirCitas")
	public void annadirCitas() {

		List<Listitem> listItemCitas = new ArrayList<Listitem>(listCitasA.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		if (!listCitasA.getItems().isEmpty()) {
			for (Listitem listitem : listItemCitas) {
				if (listitem.isSelected()) {
					Cita cita = (Cita) listitem.getValue();
					if (cita != null) {
						espacio.setEstado(true);
						servicioEspacio.editarEspacio(espacio);
						cita.setEspacio(espacio);
						cita.setEstado("Asignada");
						if (servicioCita.editarCita(cita)) {
							Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
							String dir = "gc/espacio/frm-asignar-espacio-catalogo.zul";
							clearDivApp(dir);
							espacio=new Espacio();
							setAtributo("espacio",espacio);
						} else {
							Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
						}

					}
				}
			}
		}
	}
	
	@Listen("onClick =#btnLiberar")
	public void liberarCitas() {

		List<Listitem> listItemCitas = new ArrayList<Listitem>(listCitasA.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		if (!listCitasA.getItems().isEmpty()) {
			for (Listitem listitem : listItemCitas) {
				if (listitem.isSelected()) {
					Cita cita = (Cita) listitem.getValue();
					if (cita != null) {
						
						espacio.setEstado(false);
						
					;
						servicioEspacio.editarEspacio(espacio);
						
						cita.setEspacio(null);
						if (servicioCita.editarCita(cita)) {
							Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
							String dir = "gc/espacio/frm-asignar-espacio-catalogo.zul";
							clearDivApp(dir);
						} else {
							Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
						}

					}
				}
			}
		}
	}
	
	public void verificarAcciones(MouseEvent mouseEvent) {
		try {
			Button boton = (Button) mouseEvent.getTarget();
			Cita cita = (Cita) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("cita", cita);
				String dir = "gc/cita/frm-cita-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("cita", cita);
				String dir = "gc/cita/frm-cita-consultar.zul";
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

