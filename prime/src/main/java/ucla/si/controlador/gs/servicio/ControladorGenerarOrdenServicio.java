package ucla.si.controlador.gs.servicio;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.GeneratedValue;

import org.hibernate.Hibernate;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.FallaPresupuesto;
import ucla.si.modelo.OrdenServicio;
import ucla.si.modelo.OrdenServicioUsuario;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.Servicio;
import ucla.si.modelo.Usuario;
import ucla.si.servicio.ServicioOrdenServicio;
import ucla.si.servicio.ServicioPresupuesto;
import ucla.si.servicio.ServicioUsuario;

public class ControladorGenerarOrdenServicio extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioUsuario servicioUsuario;
	
	@WireVariable
	private ServicioPresupuesto servicioPresupuesto;
	
	@WireVariable
	private ServicioOrdenServicio servicioOrdenServicio;
	
	private Presupuesto presupuesto;
	
	@Wire
	Listbox listMecanicos;
	
	@Wire
	Listbox listPresupuestos;
	
	@Wire
	Listbox listServicio;
	
	@Wire
	Listbox listFallaPresupuesto;

	@Wire
	Textbox txtNombre, txtApellido, txtTelefono, txtCorreo;
	
	@Wire
	Label lblPlaca, lblMarca, lblModelo, lblAnno;
	
	@Wire
	Textbox txtBuscar;
	
	@Wire
	Textbox txtDescripcion;
	
	@Override
	protected void inicializar() {
		System.out.println("servicioPresupuesto");
		System.out.println(servicioPresupuesto);
		if(listPresupuestos != null){
			ListModelList<Presupuesto> modeloPresupuestos;
			List<Presupuesto> list =servicioPresupuesto.listarPresupuestosOrdenDeServicio();
			
			//System.out.println(servicioPresupuesto.listarPresupuestosOrdenDeServicio().get(0));
			
			modeloPresupuestos = new ListModelList<Presupuesto>(list);
			modeloPresupuestos.setMultiple(false);
			listPresupuestos.setModel(modeloPresupuestos);
			listPresupuestos.setMultiple(false);
		}
		
	
		if(listMecanicos != null){
			cargarDatosPresupuesto();
			ListModelList<Usuario> modeloMecanicos = new ListModelList<Usuario>(
					servicioUsuario.listarUsuariosMecanicos());
			if (modeloMecanicos.getSize() == 0) {
				Messagebox.show("Debe Incluir algun usuario Mecanico primero", "Error", Messagebox.OK, Messagebox.ERROR);
			}else {
				modeloMecanicos.setMultiple(true);
				listMecanicos.setModel(modeloMecanicos);
				listMecanicos.setMultiple(true);
				listMecanicos.setCheckmark(true);
			}
		}
		
		if(listFallaPresupuesto != null){
			ListModelList<FallaPresupuesto> modeloFallaPresupuesto = new ListModelList<FallaPresupuesto>(presupuesto.getFallaPresupuesto());
			modeloFallaPresupuesto.setMultiple(false);
			listFallaPresupuesto.setModel(modeloFallaPresupuesto);
			listFallaPresupuesto.setMultiple(false);
			listFallaPresupuesto.setCheckmark(false);
		}
		
		if(listServicio != null){
			ListModelList<Servicio> modeloServicio = new ListModelList<Servicio>(servicioPresupuesto.listarServiciosXPresupuesto(presupuesto.getId()));
			modeloServicio.setMultiple(false);
			listServicio.setModel(modeloServicio);
			listServicio.setMultiple(false);
			listServicio.setCheckmark(false);
		}
		
		
	}
	
	private void cargarDatosPresupuesto() {
		this.presupuesto = (Presupuesto)getAtributo("presupuesto");
		
		System.out.println(presupuesto);
		txtNombre.setValue(servicioPresupuesto.nestedPersona(this.presupuesto.getId()).getNombre());
		txtNombre.setDisabled(true);
		txtNombre.setReadonly(true); 
		
		txtApellido.setValue(servicioPresupuesto.nestedPersona(this.presupuesto.getId()).getApellido());
		txtApellido.setDisabled(true);
		txtApellido.setReadonly(true);
		
		txtTelefono.setValue(servicioPresupuesto.nestedPersona(this.presupuesto.getId()).getTelefono());
		txtTelefono.setDisabled(true);
		txtTelefono.setReadonly(true);
		
		txtCorreo.setValue(servicioPresupuesto.nestedUsuario(this.presupuesto.getId()).getCorreo());
		txtCorreo.setDisabled(true);
		txtCorreo.setReadonly(true);
		
		lblPlaca.setValue(servicioPresupuesto.nestedVehiculo(this.presupuesto.getId()).getPlaca());
		
		lblMarca.setValue(servicioPresupuesto.nombreMarca(this.presupuesto.getId()));
		
		lblModelo.setValue(servicioPresupuesto.nombreModelo(this.presupuesto.getId()));
		
		lblAnno.setValue(servicioPresupuesto.nestedVehiculo(this.presupuesto.getId()).getAnno().toString());
		
			
		
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gs/servicio/frm-generar-orden-servicio.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	
	
	
	/*@Listen("onAfterRender =#listUsuariosMecanicos")
	public void actualizarListbox() {
		if(listMecanicos.getItemCount() > 0){
			asignarEventos(listMecanicos);
		}
	}*/
	
	@Listen("onAfterRender =#listPresupuestos")
	public void actualizarListbox() {
		if(listPresupuestos.getItemCount() > 0){
			asignarEventos(listPresupuestos);
		}
	}
	
	// btnConfigurar
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
			Presupuesto presupuesto = (Presupuesto) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());
			if (boton.getTooltiptext().equals("Detalle")) {
				setAtributo("presupuesto", presupuesto);
				String dir = "gs/servicio/frm-generar-orden-servicio-detalle.zul";
				clearDivApp(dir);	
			}
		}
		catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información",Messagebox.OK, Messagebox.ERROR);
		} 
		catch (org.hibernate.TransactionException e){
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información",Messagebox.OK, Messagebox.ERROR);
		}
		
	}
	
	public String nombreUsuario(Long presupuestoId){
		System.out.println("ENTRANDO EN NOMBREUSUARIO");
		return servicioPresupuesto.nombreUsuario(presupuestoId);
	}
	
	public String nombreMarca(Long presupuestoId){		
		return servicioPresupuesto.nombreMarca(presupuestoId);
	}
	
	public String nombreModelo(Long presupuestoId){
		String nombre = servicioPresupuesto.nombreModelo(presupuestoId);
		System.out.println(nombre);
		return servicioPresupuesto.nombreModelo(presupuestoId);
	}
	
	
	@Listen("onClick =#btnGenerarOrdenServicio")
	public void registrar() {
		System.out.println("Generando orden servicio");
			if (listMecanicos.getSelectedCount() == 0) {
				Messagebox.show("Debe seleccionar al menos un Mecanico", "Error", Messagebox.OK, Messagebox.ERROR);
			} else {
				String descripcion = txtDescripcion.getValue();
				OrdenServicio ordenServicio = new OrdenServicio(descripcion, "En proceso", "Activa",new Date(), null);
				List<OrdenServicioUsuario> mecanicos = new ArrayList<OrdenServicioUsuario>();
				for (Listitem usuarioItem : listMecanicos.getSelectedItems()) {
					OrdenServicioUsuario orden_usuario = new OrdenServicioUsuario("Activo", new Date(), null);
					orden_usuario.setUsuario((Usuario)usuarioItem.getValue());
					orden_usuario.setOrdenServicio(ordenServicio);
					mecanicos.add(orden_usuario);
				}
				ordenServicio.setOrdenServicioUsuario(mecanicos);
				this.presupuesto.setEstado("En proceso");
				servicioPresupuesto.editarPresupuesto(presupuesto);
				ordenServicio.setPresupuesto(presupuesto);
				if (servicioOrdenServicio.incluirOrdenServicio(ordenServicio)) {
					Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gs/servicio/frm-generar-orden-servicio-catalogo.zul";
					clearDivApp(dir);
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		}	
			/*
			presupuesto.getCita().getVehiculo().getUsuario().getPersona().setDireccion(txtDireccion.getValue());
			presupuesto.getCita().getVehiculo().getUsuario().getPersona().setTelefono(txtTelefono.getValue());
			presupuesto.getCita().getVehiculo().getUsuario().getPersona().setSexo(cmbSexo.getValue());
			presupuesto.getCita().getVehiculo().getUsuario().getPersona().setFechaNacimiento(dtb_fecha_nac.getValue());
			presupuesto.getCita().getVehiculo().getUsuario().getPersona().setOcupacion((Ocupacion)cmbOcupacion.getSelectedItem().getValue());
			presupuesto.getCita().getVehiculo().getUsuario().getPersona().setDeporte((Deporte)cmbDeporte.getSelectedItem().getValue());
			presupuesto.getCita().getVehiculo().getUsuario().getPersona().setProfesion((Profesion)cmbProfesion.getSelectedItem().getValue());
			presupuesto.getCita().getVehiculo().getUsuario().getPersona().setPasatiempo((Pasatiempo)cmbPasatiempo.getSelectedItem().getValue());
		
		 
		
             System.out.println(presupuesto.getCita().getVehiculo().getUsuario().getPersona().getPasatiempo().getDescripcion()+"=============================================="+"==============================================================");
			if (servicioPersona.editarPersona(cita.getVehiculo().getUsuario().getPersona())) {
				Messagebox.show("Registro exitoso", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}*/

	}
	


