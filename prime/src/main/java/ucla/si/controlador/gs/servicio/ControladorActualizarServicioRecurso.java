package ucla.si.controlador.gs.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Treecell;

import ucla.si.controlador.app.ControladorInicio;

import ucla.si.modelo.Herramienta;
import ucla.si.modelo.ServiciosHerramientas;
import ucla.si.modelo.ServiciosTecnologias;
import ucla.si.modelo.Servicio;
import ucla.si.modelo.ServiciosEtapas;
import ucla.si.modelo.Etapa;

import ucla.si.servicio.ServicioHerramienta;
import ucla.si.servicio.ServicioServicio;
import ucla.si.servicio.ServicioServiciosEtapas;
import ucla.si.servicio.ServicioServiciosHerramientas;
import ucla.si.servicio.ServicioServiciosTecnologias;
import ucla.si.servicio.ServicioEtapa;
import ucla.si.servicio.ServicioTecnologia;
import ucla.si.servicio.ServicioVehiculo;
import ucla.si.servicio.ServicioVehiculoServicio;
import ucla.si.modelo.Tecnologia;
import ucla.si.modelo.Vehiculo;
import ucla.si.modelo.VehiculoServicio;
import ucla.si.modelo.ServiciosHerramientas;

public class ControladorActualizarServicioRecurso extends ControladorInicio {

	/**
	 * 
	 */
	private Servicio servicio;// inventando
	@WireVariable
	private ServiciosHerramientas serviciosHerramientas;
	// private ServiciosTecnologias serviciosTecnologias;
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServiciosTecnologias serviciosTecnologias;

	@WireVariable
	private ServiciosEtapas serviciosEtapas;

	@WireVariable
	private ServicioServicio servicioServicio;

	@WireVariable
	private ServicioServiciosHerramientas servicioServiciosHerramientas;

	@WireVariable
	private ServicioServiciosTecnologias servicioServiciosTecnologias;

	@WireVariable
	private ServicioServiciosEtapas servicioServiciosEtapas;

	@WireVariable
	private ServicioEtapa servicioEtapa;
	
	@WireVariable
	private ServicioVehiculo servicioVehiculo;
	
	@WireVariable
	private VehiculoServicio vehiculoServicio;

	@WireVariable
	private Herramienta herramienta;

	/*
	 * @WireVariable private ServiciosHerramientas serviciosHerramientas;
	 */
	@WireVariable
	private ServicioHerramienta servicioHerramienta;
	@WireVariable
	private ServicioTecnologia servicioTecnologia;
	
	@WireVariable
	private ServicioVehiculoServicio servicioVehiculoServicio;
	@Wire
	Listbox listEtapas, listHerramientas, listTecnologias,listVehiculos;
	@Wire
	Checkbox checkbox;
	@Override
	protected void inicializar() {

		servicio = (Servicio) getAtributo("servicio");

		ListModelList<Etapa> modeloEtapas = new ListModelList<Etapa>(
				servicioEtapa.listarEtapasXServicio(servicio.getId()));
		servicioEtapa.listarEtapasXServicio(servicio.getId());
		modeloEtapas.setMultiple(true);
		listEtapas.setModel(modeloEtapas);
		listEtapas.setMultiple(true);
		listEtapas.setCheckmark(true);
		
		
		
		
		
		

		ListModelList<Herramienta> modeloHerramientas = new ListModelList<Herramienta>(
				servicioHerramienta.listarHerramientasXServicio(servicio.getId()));
		servicioHerramienta.listarHerramientasXServicio(servicio.getId());
		modeloHerramientas.setMultiple(true);
		listHerramientas.setModel(modeloHerramientas);
		listHerramientas.setMultiple(true);
		listHerramientas.setCheckmark(true);
		
		
		
	

		// System.out.println("tamaño:
		// "+servicioHerramienta.listarHerramientas().size());
		/*
		 * for (int k = 0; k < servicioHerramienta.listarHerramientas().size();
		 * k++) {
		 * System.out.println("tamaño: "+servicioHerramienta.listarHerramientas(
		 * ).get(k).getServiciosHerramientas().size()); for (int i = 0; i <
		 * servicioHerramienta.listarHerramientas().get(k).
		 * getServiciosHerramientas().size(); i++) if
		 * (servicioHerramienta.listarHerramientas().get(k).
		 * getServiciosHerramientas().get(i).getServicio().getId() ==
		 * servicio.getId()) { List<Herramienta> herramientas = new
		 * ArrayList<Herramienta>(); herramientas.add(herramienta);
		 * ListModelList<Herramienta> modeloHerramientas = new
		 * ListModelList<Herramienta>(herramientas);
		 * modeloHerramientas.setMultiple(true);
		 * listHerramientas.setModel(modeloHerramientas);
		 * listHerramientas.setMultiple(true);
		 * listHerramientas.setCheckmark(true);
		 * 
		 * } }
		 */

		/*
		 * ListModelList<Herramienta> modeloHerramientasXServicio = new
		 * ListModelList<Herramienta>(); //
		 * servicioServiciosHerramientas.listarServiciosHerramientasXServicio(
		 * servicio.getId());
		 * 
		 * modeloHerramientasXServicio.setMultiple(true);
		 * listServicioHerramientas.setModel(modeloHerramientasXServicio);
		 * listServicioHerramientas.setMultiple(true);
		 * listServicioHerramientas.setCheckmark(true);
		 * 
		 */
		
		
		
		
		
		
		
		
		ListModelList<Tecnologia> modeloTecnologias = new ListModelList<Tecnologia>(
				servicioTecnologia.listarTecnologiasXServicio(servicio.getId()));
		modeloTecnologias.setMultiple(true);
		listTecnologias.setModel(modeloTecnologias);
		listTecnologias.setMultiple(true);
		listTecnologias.setCheckmark(true);
		
		ListModelList<Vehiculo> modeloVehiculos = new ListModelList<Vehiculo>(
				servicioVehiculo.listarVehiculosXServicio(servicio.getId()));
		modeloVehiculos.setMultiple(true);
		listVehiculos.setModel(modeloVehiculos);
		listVehiculos.setMultiple(true);
		listVehiculos.setCheckmark(true);

	}

	@Listen("onClick =#btnActualizarHerramienta")
	public void annadirHerramientas() {

		List<Listitem> listItemHerramientas = new ArrayList<Listitem>(listHerramientas.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		boolean test = false;

		// servicioServiciosHerramientas.listarServiciosHerramientasXServicio(servicio.getId());
		if (!listHerramientas.getItems().isEmpty()) {
			for (Listitem listitem : listItemHerramientas) {
				if (listitem.isSelected()) {
					serviciosHerramientas = new ServiciosHerramientas();
					Herramienta herramienta = (Herramienta) listitem.getValue();

					if (herramienta != null) {

						serviciosHerramientas.setHerramienta(herramienta);
						serviciosHerramientas.setServicio(servicio);
						serviciosHerramientas.setEstatus("Activo");
						serviciosHerramientas.setFechaCreacion(new Date());
						// servicioServiciosHerramientas.incluirServiciosHerramientas(serviciosHerramientas);
						servicioServiciosHerramientas.editarServiciosHerramientas(serviciosHerramientas);
						test = true;
						// System.out.println("sdsadsadsads:"+servicio.getFalla().getId());

					}
				}
			}

			if (test == true) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/servicio/frm-configurar-servicio-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnAnnadirTecnologia")
	public void annadirTecnologia() {

		List<Listitem> listItemTecnologias = new ArrayList<Listitem>(listTecnologias.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");

		boolean test = false;
		if (!listTecnologias.getItems().isEmpty()) {

			for (Listitem listitem : listItemTecnologias) {
				if (listitem.isSelected()) {
					serviciosTecnologias = new ServiciosTecnologias();
					Tecnologia tecnologia = (Tecnologia) listitem.getValue();
					if (tecnologia != null) {
						// System.out.println("La tecnologia id es: " +
						// tecnologia.getId());
						// System.out.println("el servicio id es: " +
						// servicio.getId());
						// System.out.println("Servicio:
						// "+servicio.getDescripcion());

						serviciosTecnologias.setTecnologia(tecnologia);
						serviciosTecnologias.setServicio(servicio);
						serviciosTecnologias.setEstatus("Activo");
						serviciosTecnologias.setFechaCreacion(new Date());
						// servicioServiciosHerramientas.incluirServiciosHerramientas(serviciosHerramientas);
						servicioServiciosTecnologias.editarServiciosTecnologias(serviciosTecnologias);
						test = true;

						// System.out.println("sdsadsadsads:"+servicio.getFalla().getId());

					}
				}
			}

			if (test == true) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/servicio/frm-configurar-servicio-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	@Listen("onClick =#btnActualizarVehiculo")
	public void annadirVehiculos() {

		List<Listitem> listItemVehiculos = new ArrayList<Listitem>(listVehiculos.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		boolean test = false;

		// servicioServiciosHerramientas.listarServiciosHerramientasXServicio(servicio.getId());
		if (!listVehiculos.getItems().isEmpty()) {
			for (Listitem listitem : listItemVehiculos) {
				if (listitem.isSelected()) {
					vehiculoServicio = new VehiculoServicio();
					Vehiculo vehiculo = (Vehiculo) listitem.getValue();

					if (vehiculo != null) {

						vehiculoServicio.setVehiculo(vehiculo);
						vehiculoServicio.setServicio(servicio);
						vehiculoServicio.setEstatus("Activo");
						vehiculoServicio.setFechaCreacion(new Date());
						// servicioServiciosHerramientas.incluirServiciosHerramientas(serviciosHerramientas);
						servicioVehiculoServicio.editarVehiculoServicio(vehiculoServicio);
						test = true;
						// System.out.println("sdsadsadsads:"+servicio.getFalla().getId());

					}
				}
			}

			if (test == true) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/servicio/frm-configurar-servicio-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnAnnadirEtapa")
	public void annadirEtapa() {

		List<Listitem> listItemEtapas = new ArrayList<Listitem>(listEtapas.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");

		boolean test = false;
		if (!listEtapas.getItems().isEmpty()) {

			for (Listitem listitem : listItemEtapas) {
				if (listitem.isSelected()) {
					serviciosEtapas = new ServiciosEtapas();
					Etapa etapa = (Etapa) listitem.getValue();
					if (etapa != null) {
						// System.out.println("La etapa id es: " +
						// etapa.getId());
						// System.out.println("el servicio id es: " +
						// servicio.getId());
						// System.out.println("Servicio:
						// "+servicio.getDescripcion());

						serviciosEtapas.setEtapa(etapa);
						serviciosEtapas.setServicio(servicio);
						serviciosEtapas.setEstatus("Activo");
						serviciosEtapas.setFechaCreacion(new Date());
						// servicioServiciosHerramientas.incluirServiciosHerramientas(serviciosHerramientas);
						servicioServiciosEtapas.editarServiciosEtapas(serviciosEtapas);
						test = true;

						// System.out.println("sdsadsadsads:"+servicio.getFalla().getId());

					}
				}
			}

			if (test == true) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/servicio/frm-configurar-servicio-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#navHerramienta")
	public void herramienta() {
		String dir = "gs/servicio/frm-configurar-servicio-herramienta.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#navTecnologia")
	public void tecnologia() {
		String dir = "gs/servicio/frm-configurar-servicio-tecnologia.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#navEtapa")
	public void etapa() {
		String dir = "gs/servicio/frm-configurar-servicio-etapa.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#navVehiculo")
	public void vehiculo() {
		String dir = "gs/servicio/frm-configurar-servicio-vehiculo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gs/servicio/frm-servicioFalla.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

}
