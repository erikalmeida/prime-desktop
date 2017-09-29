package ucla.si.controlador.gs.servicio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.zkoss.image.Image;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.dao.UsuarioDAO;
import ucla.si.modelo.Accesorio;
import ucla.si.modelo.Aceite;
import ucla.si.modelo.Caja;
import ucla.si.modelo.Calificacion;
import ucla.si.modelo.OrdenEntrega;
import ucla.si.modelo.Clase;
import ucla.si.modelo.Color;
import ucla.si.modelo.Color;
import ucla.si.modelo.Combustible;
import ucla.si.modelo.Deporte;
import ucla.si.modelo.GrosorAceite;
import ucla.si.modelo.Modelo;
import ucla.si.modelo.Ocupacion;
import ucla.si.modelo.Pasatiempo;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Marca;
import ucla.si.modelo.Marca;
import ucla.si.modelo.Profesion;
import ucla.si.modelo.Refrigerante;
import ucla.si.modelo.TipoAceite;
import ucla.si.modelo.Usuario;
import ucla.si.modelo.Vehiculo;
import ucla.si.modelo.VehiculoAccesorio;
import ucla.si.servicio.ServicioAccesorio;
import ucla.si.servicio.ServicioCaja;
import ucla.si.servicio.ServicioCalificacion;
import ucla.si.servicio.ServicioOrdenEntrega;
import ucla.si.servicio.ServicioClase;
import ucla.si.servicio.ServicioColor;
import ucla.si.servicio.ServicioCombustible;
import ucla.si.servicio.ServicioDeporte;
import ucla.si.servicio.ServicioGrosorAceite;
import ucla.si.servicio.ServicioMarca;
import ucla.si.servicio.ServicioModelo;
import ucla.si.servicio.ServicioOcupacion;
import ucla.si.servicio.ServicioPasatiempo;
import ucla.si.servicio.ServicioPersona;
import ucla.si.servicio.ServicioProfesion;
import ucla.si.servicio.ServicioRefrigerante;
import ucla.si.servicio.ServicioTipoAceite;
import ucla.si.servicio.ServicioUsuario;
import ucla.si.servicio.ServicioVehiculo;
import ucla.si.servicio.ServicioVehiculoAccesorio;
import ucla.si.utils.Urls;

public class ControladorCalificar extends ControladorInicio {

	@WireVariable
	private Usuario usuario;
	@WireVariable
	private Persona persona;

	@WireVariable
	private Calificacion calificacion;

	@WireVariable
	private ServicioCalificacion servicioCalificacion;

	private OrdenEntrega ordenEntrega;

	@WireVariable
	private ServicioUsuario servicioUsuario;

	@WireVariable
	private ServicioPersona servicioPersona;

	@WireVariable
	private ServicioOrdenEntrega servicioOrdenEntrega;

	@Wire
	Checkbox servicioMalo, servicioRegular, servicioBueno, servicioMBueno, servicioExcelente, instalacionMalo,
			instalacionRegular, instalacionBueno, instalacionMBueno, instalacionExcelente, atencionMalo,
			atencionRegular, atencionBueno, atencionMBueno, atencionExcelente;

	@Wire
	Textbox txtComentario, txtApellido, txtCedula, txt_url_foto, txtServicio, txtTelefono, txt_correo, txtMarca,
			txtModelo, txt_contrasenna, txt_pregunta_secreta, txt_respuesta_secreta, txtPlaca, txtSerialCarro,
			txtSerialMotor;

	@Wire
	Datebox dtb_fecha_nac;

	@WireVariable
	private WebApp _wapp;

	@Wire
	Listbox listAccesorios;

	public ControladorCalificar() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void inicializar() {

		ordenEntrega = (OrdenEntrega) getAtributo("ordenEntrega");
		calificacion = new Calificacion();
		calificacion.setOrdenEntrega(ordenEntrega);
		servicioCalificacion.incluirCalificacion(calificacion);
		cargarDatos(ordenEntrega);

	}

	public void cargarDatos(OrdenEntrega ordenEntrega) {
		if (ordenEntrega != null) {

			dtb_fecha_nac.setValue(ordenEntrega.getFechaCreacion());
			dtb_fecha_nac.setDisabled(true);
			dtb_fecha_nac.setReadonly(true);

			txtPlaca.setValue(
					ordenEntrega.getPrueba().getOrdenServicio().getPresupuesto().getCita().getVehiculo().getPlaca());
			txtPlaca.setDisabled(true);
			txtPlaca.setReadonly(true);

			txtMarca.setValue(ordenEntrega.getPrueba().getOrdenServicio().getPresupuesto().getCita().getVehiculo()
					.getMarca().getNombre());
			txtMarca.setDisabled(true);
			txtMarca.setReadonly(true);

			txtModelo.setValue(ordenEntrega.getPrueba().getOrdenServicio().getPresupuesto().getCita().getVehiculo()
					.getModelo().getNombre());
			txtModelo.setDisabled(true);
			txtModelo.setReadonly(true);

			txtServicio.setValue(
					ordenEntrega.getPrueba().getOrdenServicio().getPresupuesto().getCita().getServicio().getTitulo());
			txtServicio.setDisabled(true);
			txtServicio.setReadonly(true);
			/*
			 * 
			 * txtDireccion.setValue(ordenEntrega.getVehiculo().getUsuario().
			 * getPersona().getDireccion()); txtDireccion.setDisabled(false);
			 * txtDireccion.setReadonly(false);
			 * 
			 * txtTelefono.setValue(ordenEntrega.getVehiculo().getUsuario().
			 * getPersona().getTelefono()); txtTelefono.setDisabled(false);
			 * txtTelefono.setReadonly(false);
			 * 
			 * 
			 * cmbSexo.setValue(ordenEntrega.getVehiculo().getUsuario().
			 * getPersona().getSexo()); cmbSexo.setDisabled(false);
			 * cmbSexo.setReadonly(false);
			 * 
			 * dtb_fecha_nac.setValue(ordenEntrega.getVehiculo().getUsuario().
			 * getPersona().getFechaNacimiento());
			 * dtb_fecha_nac.setDisabled(false);
			 * dtb_fecha_nac.setReadonly(false);
			 * 
			 * cmbDeporte.setValue(ordenEntrega.getVehiculo().getUsuario().
			 * getPersona().getDeporte().getDescripcion());
			 * cmbDeporte.setDisabled(false); cmbDeporte.setReadonly(false);
			 * 
			 * cmbPasatiempo.setValue(ordenEntrega.getVehiculo().getUsuario().
			 * getPersona().getPasatiempo().getDescripcion());
			 * cmbPasatiempo.setDisabled(false);
			 * cmbPasatiempo.setReadonly(false);
			 * 
			 * cmbProfesion.setValue(ordenEntrega.getVehiculo().getUsuario().
			 * getPersona().getProfesion().getDescripcion());
			 * cmbProfesion.setDisabled(false); cmbProfesion.setReadonly(false);
			 * 
			 * cmbOcupacion.setValue(ordenEntrega.getVehiculo().getUsuario().
			 * getPersona().getOcupacion().getDescripcion());
			 * cmbOcupacion.setDisabled(false); cmbOcupacion.setReadonly(false);
			 * 
			 * 
			 */

			// --------------------- Vehiculo
			/*
			 * txtMarca.setValue(ordenEntrega.getVehiculo().getMarca().getNombre
			 * ()); txtMarca.setDisabled(true); txtMarca.setReadonly(true);
			 * 
			 * txtPlaca.setValue(ordenEntrega.getVehiculo().getPlaca());
			 * txtPlaca.setDisabled(true); txtPlaca.setReadonly(true);
			 * 
			 * 
			 * txtModelo.setValue(ordenEntrega.getVehiculo().getModelo().
			 * getNombre()); txtModelo.setDisabled(true);
			 * txtModelo.setReadonly(true);
			 * 
			 * intAnno.setValue(ordenEntrega.getVehiculo().getAnno());
			 * intAnno.setDisabled(false); intAnno.setReadonly(false);
			 * 
			 * txtSerialCarro.setValue(ordenEntrega.getVehiculo().
			 * getSerialCarroceria()); txtSerialCarro.setDisabled(false);
			 * txtSerialCarro.setReadonly(false);
			 * 
			 * txtSerialMotor.setValue(ordenEntrega.getVehiculo().getSerialMotor
			 * ()); txtSerialMotor.setDisabled(false);
			 * txtSerialMotor.setReadonly(false);
			 * 
			 * 
			 * 
			 * doubleKilometraje.setValue(ordenEntrega.getVehiculo().
			 * getKilometraje()); doubleKilometraje.setDisabled(false);
			 * doubleKilometraje.setReadonly(false);
			 * 
			 * intNroPuestos.setValue(ordenEntrega.getVehiculo().getNroPuestos()
			 * ); intNroPuestos.setDisabled(false);
			 * intNroPuestos.setReadonly(false);
			 * 
			 * 
			 * txtServicio.setValue(ordenEntrega.getServicio().getTitulo());
			 * txtServicio.setDisabled(true); txtServicio.setReadonly(true);
			 * 
			 * txtReferido.setValue(ordenEntrega.getNombreReferido());
			 * txtReferido.setDisabled(true); txtReferido.setReadonly(true);
			 */
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

	public void verificarAcciones(MouseEvent mouseEvent) {
		/*
		 * try { Button boton = (Button) mouseEvent.getTarget(); Accesorio
		 * accesorio = (Accesorio) (((Listitem)
		 * mouseEvent.getTarget().getParent().getParent()).getValue());
		 * 
		 * if (boton.getTooltiptext().equals("Editar")) {
		 * setAtributo("accesorio", accesorio); String dir =
		 * "gc/aceite/frm-aceite-editar.zul"; clearDivApp(dir); }else if
		 * (boton.getTooltiptext().equals("Consultar")) {
		 * setAtributo("accesorio", accesorio); String dir =
		 * "gc/aceite/frm-aceite-consultar.zul"; clearDivApp(dir); }else if
		 * (boton.getTooltiptext().equals("Eliminar")) {
		 * accesorio.setEstatus("Inactivo");
		 * 
		 * if (servicioAccesorio.editarAccesorio(accesorio)) {
		 * Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK,
		 * Messagebox.INFORMATION); String dir =
		 * "pc/aceite/frm-aceite-catalogo.zul"; clearDivApp(dir); } }
		 * 
		 * } catch (org.springframework.transaction.TransactionTimedOutException
		 * e) { Messagebox.show("¡Tiempo Expirado para la transacción!",
		 * "Información",Messagebox.OK, Messagebox.ERROR); } catch
		 * (org.hibernate.TransactionException e){
		 * Messagebox.show("¡Tiempo Expirado para la transacción!",
		 * "Información",Messagebox.OK, Messagebox.ERROR); }
		 */
	}
	

	@Listen("onClick =#btnServicio")
	public void calificarServicio() {
		// System.out.println("Registro Usuario");
		// calificacion = new Calificacion();

		if (servicioMalo.isChecked()) {

			calificacion.setCalificacionServicio(1);
			calificacion.setOrdenEntrega(ordenEntrega);
			// calificacion.setFecha(new Date());

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Servicio exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				// String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				// clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else if (servicioRegular.isChecked()) {

			calificacion.setCalificacionServicio(2);
			calificacion.setOrdenEntrega(ordenEntrega);
			// calificacion.setFecha(new Date());

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Servicio exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				// String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				// clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else if (servicioBueno.isChecked()) {

			calificacion.setCalificacionServicio(3);
			calificacion.setOrdenEntrega(ordenEntrega);
			// calificacion.setFecha(new Date());

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Servicio exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				// String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				// clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else if (servicioMBueno.isChecked()) {

			calificacion.setCalificacionServicio(4);
			calificacion.setOrdenEntrega(ordenEntrega);
			// calificacion.setFecha(new Date());

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Servicio exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				// String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				// clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else {

			calificacion.setCalificacionServicio(5);
			calificacion.setOrdenEntrega(ordenEntrega);
			// calificacion.setFecha(new Date());

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Servicio exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				// String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				// clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		}

	}
	
	

	@Listen("onClick =#btnInstalacion")
	public void calificarInstalacion() {

		// calificacion = new Calificacion();

		if (instalacionMalo.isChecked()) {

			calificacion.setCalificacionInstalacion(1);
			calificacion.setOrdenEntrega(ordenEntrega);
			// calificacion.setFecha(new Date());

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Instalacion exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				// String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				// clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else if (instalacionRegular.isChecked()) {

			calificacion.setCalificacionInstalacion(2);
			;
			calificacion.setOrdenEntrega(ordenEntrega);
			// calificacion.setFecha(new Date());

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Instalacion exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				// String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				// clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else if (instalacionBueno.isChecked()) {

			calificacion.setCalificacionInstalacion(3);
			;
			calificacion.setOrdenEntrega(ordenEntrega);
			// calificacion.setFecha(new Date());

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Instalacion exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				// String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				// clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else if (instalacionMBueno.isChecked()) {

			calificacion.setCalificacionInstalacion(4);
			;
			calificacion.setOrdenEntrega(ordenEntrega);
			// calificacion.setFecha(new Date());

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Instalacion exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				// String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				// clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else {

			calificacion.setCalificacionInstalacion(5);
			;
			calificacion.setOrdenEntrega(ordenEntrega);
			// calificacion.setFecha(new Date());

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Instalacion exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				// String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				// clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		}

	}

	@Listen("onClick =#btnAtencion")
	public void calificarAtencion() {

		// calificacion = new Calificacion();

		if (atencionMalo.isChecked()) {

			calificacion.setCalificacionAtencion(1);
			;
			calificacion.setOrdenEntrega(ordenEntrega);
			calificacion.setFecha(new Date());
			calificacion.setComentario(txtComentario.getValue());
			calificacion.setEstatus("Calificada");
			ordenEntrega.setEstado("Calificada");
			servicioOrdenEntrega.editarOrdenEntrega(ordenEntrega);

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Atencion exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				String dir = "gs/calificar/frm-orden-entrega.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else if (atencionRegular.isChecked()) {

			calificacion.setCalificacionAtencion(2);
			calificacion.setOrdenEntrega(ordenEntrega);
			calificacion.setFecha(new Date());
			calificacion.setComentario(txtComentario.getValue());
			calificacion.setEstatus("Calificada");
			ordenEntrega.setEstado("Calificada");
			servicioOrdenEntrega.editarOrdenEntrega(ordenEntrega);

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Atencion exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				String dir = "gs/calificar/frm-orden-entrega.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else if (atencionBueno.isChecked()) {

			calificacion.setCalificacionAtencion(3);
			calificacion.setOrdenEntrega(ordenEntrega);
			calificacion.setFecha(new Date());
			calificacion.setComentario(txtComentario.getValue());
			calificacion.setEstatus("Calificada");
			ordenEntrega.setEstado("Calificada");
			servicioOrdenEntrega.editarOrdenEntrega(ordenEntrega);

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Atencion exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				String dir = "gs/calificar/frm-orden-entrega.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else if (atencionMBueno.isChecked()) {

			calificacion.setCalificacionAtencion(4);
			calificacion.setOrdenEntrega(ordenEntrega);
			calificacion.setFecha(new Date());
			calificacion.setComentario(txtComentario.getValue());
			calificacion.setEstatus("Calificada");
			ordenEntrega.setEstado("Calificada");
			servicioOrdenEntrega.editarOrdenEntrega(ordenEntrega);

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Atencion exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				String dir = "gs/calificar/frm-orden-entrega.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		} else {

			calificacion.setCalificacionAtencion(5);
			calificacion.setOrdenEntrega(ordenEntrega);
			calificacion.setFecha(new Date());
			calificacion.setComentario(txtComentario.getValue());
			calificacion.setEstatus("Calificada");
			ordenEntrega.setEstado("Calificada");
			servicioOrdenEntrega.editarOrdenEntrega(ordenEntrega);

			if (servicioCalificacion.editarCalificacion(calificacion)) {
				Messagebox.show("Calificacion de Atencion exitosa", "Información", Messagebox.OK,
						Messagebox.INFORMATION);
				String dir = "gs/calificar/frm-orden-entrega.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}

		}

	}

}
