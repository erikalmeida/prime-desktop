package ucla.si.controlador.gs.servicio;

	import java.util.ArrayList;
	import java.util.Date;
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

	import ucla.si.modelo.Herramienta;
import ucla.si.modelo.PresupuestoTipoRepuesto;
import ucla.si.modelo.Espacio;
import ucla.si.modelo.EspacioHerramienta;
import ucla.si.modelo.EspacioMecanico;
import ucla.si.modelo.EspacioTecnologia;
import ucla.si.modelo.ServiciosTecnologias;
	import ucla.si.modelo.Servicio;
	import ucla.si.modelo.ServiciosEtapas;
	import ucla.si.modelo.Etapa;

	import ucla.si.servicio.ServicioHerramienta;
import ucla.si.servicio.ServicioPresupuestoTipoRepuesto;
import ucla.si.servicio.ServicioServicio;
	import ucla.si.servicio.ServicioServiciosEtapas;
	import ucla.si.servicio.ServicioEspacioHerramienta;
import ucla.si.servicio.ServicioEspacioMecanico;
import ucla.si.servicio.ServicioEspacioTecnologia;
import ucla.si.servicio.ServicioServiciosTecnologias;
	import ucla.si.servicio.ServicioEtapa;
	import ucla.si.servicio.ServicioTecnologia;
import ucla.si.servicio.ServicioTipoRepuesto;
import ucla.si.servicio.ServicioUsuario;
import ucla.si.modelo.Tecnologia;
import ucla.si.modelo.TipoRepuesto;
import ucla.si.modelo.Usuario;
import ucla.si.servicio.ServicioEspacio;
import ucla.si.modelo.EspacioHerramienta;

	public class ControladorConfigurarEspacioRecurso extends ControladorInicio {

		/**
		 * 
		 */
		@WireVariable
		private Espacio espacio;
		
		@WireVariable
		private EspacioHerramienta espacioHerramienta;
	
		// private ServiciosTecnologias espaciosTecnologias;
		private static final long serialVersionUID = 1L;

		@WireVariable
		private EspacioTecnologia espacioTecnologia;
		
		

		

		

		@WireVariable
		private ServicioEspacio servicioEspacio;
		
		
		@WireVariable
		private PresupuestoTipoRepuesto presupuestoTipoRepuesto;
		
		@WireVariable
		private ServicioTipoRepuesto servicioTipoRepuesto;
		
		@WireVariable
		private ServicioPresupuestoTipoRepuesto servicioPresupuestoTipoRepuesto;
		
		@WireVariable
		private EspacioMecanico espacioMecanico;

		@WireVariable
		private ServicioEspacioHerramienta servicioEspacioHerramienta;
		
	

		

		/*
		 * @WireVariable private EspacioHerramienta espacioHerramienta;
		 */
		@WireVariable
		private ServicioHerramienta servicioHerramienta;
		
		@WireVariable
		private ServicioEspacioMecanico servicioEspacioMecanico;
		@WireVariable
		private ServicioUsuario servicioUsuario;
		@WireVariable
		private ServicioTecnologia servicioTecnologia;
		
		@WireVariable
		private ServicioEspacioTecnologia servicioEspacioTecnologia;
		
	
		@WireVariable
		private TipoRepuesto tiporepuesto;

		@Wire
		Listbox   listHerramientas, listTecnologias,listRepuestos,listMecanicos;

		@Override
		protected void inicializar() {

			espacio = (Espacio) getAtributo("espacio");

			

			

			ListModelList<Herramienta> modeloHerramientas = new ListModelList<Herramienta>(
					servicioHerramienta.listarHerramientas());
			modeloHerramientas.setMultiple(true);
			listHerramientas.setModel(modeloHerramientas);
			listHerramientas.setMultiple(true);
			listHerramientas.setCheckmark(true);

			ListModelList<Tecnologia> modeloTecnologias = new ListModelList<Tecnologia>(
					servicioTecnologia.listarTecnologias());
			modeloTecnologias.setMultiple(true);
			listTecnologias.setModel(modeloTecnologias);
			listTecnologias.setMultiple(true);
			listTecnologias.setCheckmark(true);
			
			ListModelList<Usuario> modeloMecanicos = new ListModelList<Usuario>(
					servicioUsuario.listarUsuariosMecanicos());
			modeloMecanicos.setMultiple(true);
			listMecanicos.setModel(modeloMecanicos);
			listMecanicos.setMultiple(true);
			listMecanicos.setCheckmark(true);
			


		}

		
		

		@Listen("onClick =#btnAnnadirHerramienta")
		public void annadirHerramientas() {

			List<Listitem> listItemHerramientas = new ArrayList<Listitem>(listHerramientas.getItems());
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
			boolean test = false;

			if (!listHerramientas.getItems().isEmpty()) {
				for (Listitem listitem : listItemHerramientas) {
					if (listitem.isSelected()) {
						espacioHerramienta = new EspacioHerramienta();
						Herramienta herramienta = (Herramienta) listitem.getValue();

						if (herramienta != null) {
						

							
							espacioHerramienta.setHerramienta(herramienta);
							espacioHerramienta.setEspacio(espacio);
							espacioHerramienta.setEstatus("Activo");
							espacioHerramienta.setFechaCreacion(new Date());
							System.out.println("y Entonces: aqui deberia ir un espacio: "+espacio.getDescripcion());
							// espacioEspacioHerramienta.incluirEspacioHerramienta(espacioHerramienta);
							servicioEspacioHerramienta.incluirEspacioHerramienta(espacioHerramienta);
							test = true;
							// System.out.println("sdsadsadsads:"+espacio.getRepuesto().getId());

						}
					}
				}

				if (test == true) {
					Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				/*	String dir = "gc/espacio/frm-asignar-espacio-recursos-catalogo.zul";
					clearDivApp(dir);*/
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		}
		
		@Listen("onClick =#btnAnnadirTecnologia")
		public void annadirTecnologias() {

			List<Listitem> listItemTecnologias = new ArrayList<Listitem>(listTecnologias.getItems());
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
			boolean test = false;

			if (!listTecnologias.getItems().isEmpty()) {
				for (Listitem listitem : listItemTecnologias) {
					if (listitem.isSelected()) {
						espacioTecnologia = new EspacioTecnologia();
						Tecnologia tecnologia = (Tecnologia) listitem.getValue();

						if (tecnologia != null) {
						

							
							espacioTecnologia.setTecnologia(tecnologia);
							espacioTecnologia.setEspacio(espacio);
							espacioTecnologia.setEstatus("Activo");
							espacioTecnologia.setFechaCreacion(new Date());
							System.out.println("y Entonces: aqui deberia ir un espacio: "+espacio.getDescripcion());
							// espacioEspacioHerramienta.incluirEspacioHerramienta(espacioHerramienta);
							servicioEspacioTecnologia.incluirEspacioTecnologia(espacioTecnologia);
							test = true;
							espacio.setDescripcion("Configurado");
							servicioEspacio.editarEspacio(espacio);
							// System.out.println("sdsadsadsads:"+espacio.getRepuesto().getId());

						}
					}
				}

				if (test == true) {
					String dir = "gc/espacio/frm-asignar-espacio-recursos-catalogo.zul";
					clearDivApp(dir);
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		}
		
		@Listen("onClick =#btnAnnadirMecanicos")
		public void annadirMecanicos() {

			List<Listitem> listItemMecanicos = new ArrayList<Listitem>(listMecanicos.getItems());
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
			boolean test = false;

			if (!listMecanicos.getItems().isEmpty()) {
				for (Listitem listitem : listItemMecanicos) {
					if (listitem.isSelected()) {
						espacioMecanico = new EspacioMecanico();
						Usuario usuario = (Usuario) listitem.getValue();

						if (usuario != null) {
						

							
							espacioMecanico.setMecanico(usuario);
							espacioMecanico.setEspacio(espacio);
							espacioMecanico.setEstatus("Activo");
							espacio.setDescripcion("Configurado");
							espacioMecanico.setFechaCreacion(new Date());
							servicioEspacio.editarEspacio(espacio);
							System.out.println("y Entonces: aqui deberia ir un espacio: "+espacio.getDescripcion());
							// espacioEspacioHerramienta.incluirEspacioHerramienta(espacioHerramienta);
							servicioEspacioMecanico.incluirEspacioMecanico(espacioMecanico);
							test = true;
							// System.out.println("sdsadsadsads:"+espacio.getRepuesto().getId());

						}
					}
				}

				if (test == true) {
					Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/espacio/frm-asignar-espacio-recursos-catalogo.zul";
					clearDivApp(dir);
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		}


	/*	@Listen("onClick =#btnAnnadirTecnologia")
		public void annadirTecnologia() {

			List<Listitem> listItemTecnologias = new ArrayList<Listitem>(listTecnologias.getItems());
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");

			boolean test = false;
			if (!listTecnologias.getItems().isEmpty()) {

				for (Listitem listitem : listItemTecnologias) {
					if (listitem.isSelected()) {
						servicioTecnologias = new ServiciosTecnologias();
						Tecnologia tecnologia = (Tecnologia) listitem.getValue();
						if (tecnologia != null) {
						//	System.out.println("La tecnologia id es: " + tecnologia.getId());
							//System.out.println("el espacio id es: " + espacio.getId());
							// System.out.println("Servicio:
							// "+espacio.getDescripcion());

							espaciosTecnologias.setTecnologia(tecnologia);
							espaciosTecnologias.setServicio(espacio);
							espaciosTecnologias.setEstatus("Activo");
							espaciosTecnologias.setFechaCreacion(new Date());
							// espacioEspacioHerramienta.incluirEspacioHerramienta(espacioHerramienta);
							espacioServiciosTecnologias.incluirServiciosTecnologias(espaciosTecnologias);
							test = true;
						
							// System.out.println("sdsadsadsads:"+espacio.getRepuesto().getId());

						}
					}
				}

				if (test == true) {
					Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gs/espacio/frm-configurar-espacio-catalogo.zul";
					clearDivApp(dir);
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		}
*/
		

		@Listen("onClick =#navHerramienta")
		public void herramienta() {
			String dir = "gs/espacio/frm-configurar-espacio-herramienta.zul";
			clearDivApp(dir);
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		}

		@Listen("onClick =#navTecnologia")
		public void tecnologia() {
			String dir = "gs/espacio/frm-configurar-espacio-tecnologia.zul";
			clearDivApp(dir);
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		}

		@Listen("onClick =#navEtapa")
		public void etapa() {
			String dir = "gs/espacio/frm-configurar-espacio-etapa.zul";
			clearDivApp(dir);
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		}

		@Listen("onClick =#navVehiculo")
		public void vehiculo() {
			String dir = "gs/espacio/frm-configurar-espacio-vehiculo.zul";
			clearDivApp(dir);
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		}

		@Listen("onClick =#mItemIncluir")
		public void mItemIncluir() {
			String dir = "gs/espacio/frm-espacioRepuesto.zul";
			clearDivApp(dir);
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		}

		

		


	}
