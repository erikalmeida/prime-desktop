package ucla.si.controlador.gs.servicio;


	

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
import ucla.si.modelo.Servicio;
import ucla.si.servicio.ServicioCita;
import ucla.si.servicio.ServicioEspacio;

	public class ControladorActualizarEspacioRecursoCatalogo  extends ControladorInicio {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@WireVariable
		private ServicioEspacio servicioEspacio;
		
		@WireVariable
		private Cita citaLiberar;
		
		@WireVariable
		private ServicioCita servicioCita;
	
		@WireVariable
		private Espacio espacio;

		@Wire
		Listbox listEspacios;
		
		@Wire
		Listbox listCitas;
		@Override
		protected void inicializar() {
			ListModelList<Espacio> modeloEspacios = new ListModelList<Espacio>(servicioEspacio.listarEspaciosConfigurados());
			modeloEspacios.setMultiple(false);
			listEspacios.setModel(modeloEspacios);
			listEspacios.setMultiple(false);
			listEspacios.setCheckmark(true);
			
			
		
			
		
		}

		@Listen("onClick =#mItemIncluir")
		public void mItemIncluir() {
			String dir = "gc/espacio/frm-espacio-incluir.zul";
			clearDivApp(dir);
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		}

		@Listen("onAfterRender =#listEspacios")
		public void actualizarListbox() {
			if (listEspacios.getItemCount() > 0) {
				asignarEventos(listEspacios);
			}
		}

		private void asignarEventos(Component padre) {
			String click = "onClick";
			for (Component hijo : padre.getChildren()) {
				if (hijo instanceof Button) {
					hijo.addEventListener(click, new EventListener<MouseEvent>() {
						public void onEvent(MouseEvent mouseEvent) throws Exception {
							verificarAcciones(mouseEvent);
						}
					});
				}
				asignarEventos(hijo);
			}
		}
		
		@Listen("onClick =#btnActualizar")
		public void annadirAsignar() {
			System.out.println("entro bien");
			List<Listitem> listItemEspacios = new ArrayList<Listitem>(listEspacios.getItems());
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
			if (!listEspacios.getItems().isEmpty()) {
				for (Listitem listitem : listItemEspacios) {
					if (listitem.isSelected()) {
						Espacio espacio=(Espacio) listitem.getValue();
						
						if (espacio!=null){
							System.out.println("El espacio: "+ espacio.getDescripcion()+" su id es: "+espacio.getId());
							
							
							
							setAtributo("espacio", espacio);
							String dir = "gc/espacio/frm-configurar-espacio-recurso.zul";
							clearDivApp(dir);
							
						}
					}
				}
			}
		}
		
		
		
		
		@Listen("onClick =#btnLiberar")
		public void Liberar() {
			
			List<Listitem> listItemEspacios = new ArrayList<Listitem>(listEspacios.getItems());
			if (!listEspacios.getItems().isEmpty()) {
				for (Listitem listitem : listItemEspacios) {
					if (listitem.isSelected()) {
						
						
						
						Espacio espacio=(Espacio) listitem.getValue();
						citaLiberar=servicioCita.buscarCitaPorIdEspacio(espacio.getId());
						citaLiberar.setEstado("Cancelada");
						servicioCita.editarCita(citaLiberar);
						if (espacio!=null){
						setAtributo("espacio", espacio);
						}
					}
				}
			}
			espacio=(Espacio)getAtributo("espacio");
			if(espacio!=null )
			{System.out.println("traje algo xD:"+espacio);
			
			//List<Listitem> listItemCitas = new ArrayList<Listitem>(listCitas.getItems());
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		//	if (!listCitas.getItems().isEmpty()) {
			//	espacio= new Espacio();
				
					System.out.println("----------------------------------------->"+espacio.getId());
			
				citaLiberar=servicioCita.buscarCitaPorIdEspacio(espacio.getId());
				System.out.println("joda no busca: "+citaLiberar.getId());
				System.out.println("====================>"+citaLiberar.getDescripcion());
						if (citaLiberar != null) {
							espacio.setEstado(false);
							servicioEspacio.editarEspacio(espacio);
							citaLiberar.setEspacio(null);
							servicioCita.editarCita(citaLiberar);
							
						
							if (servicioCita.editarCita(citaLiberar)) {
								Messagebox.show("Espacio liberado exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
								String dir = "gc/espacio/frm-asignar-espacio-catalogo.zul";
								clearDivApp(dir);
								espacio=null;
								setAtributo("espacio",espacio);
							} else {
								Messagebox.show("Error al Liberar", "Error", Messagebox.OK, Messagebox.ERROR);
							}

						}
					
				
			}
			
				else {
					Messagebox.show("Debe seleccionar un espacio ocupado", "Error", Messagebox.OK, Messagebox.ERROR);
				}
				
		}

		public void verificarAcciones(MouseEvent mouseEvent) {
			try {
				Button boton = (Button) mouseEvent.getTarget();
				Espacio espacio = (Espacio) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

				if (boton.getTooltiptext().equals("Editar")) {
					setAtributo("espacio", espacio);
					String dir = "gc/espacio/frm-espacio-editar.zul";
					clearDivApp(dir);
				} else if (boton.getTooltiptext().equals("Detalle")) {
					setAtributo("espacio", espacio);
					String dir = "gac/frm-agenda-cita-detalle.zul";
					clearDivApp(dir);
				} else if (boton.getTooltiptext().equals("Eliminar")) {
					Messagebox.show("¡En Construcción!", "Información", Messagebox.OK, Messagebox.EXCLAMATION);
				} else if (boton.getTooltiptext().equals("Eliminar")) {
					espacio.setEstatus("Inactivo");

					if (servicioEspacio.editarEspacio(espacio)) {
						Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
						String dir = "gc/espacio/frm-espacio-catalogo.zul";
						clearDivApp(dir);
					}

					else {
						Messagebox.show("Error al eliminarr", "Error", Messagebox.OK, Messagebox.ERROR);
					}
				}
			} catch (org.springframework.transaction.TransactionTimedOutException e) {
				Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
			} catch (org.hibernate.TransactionException e) {
				Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
			}
		}

	}
