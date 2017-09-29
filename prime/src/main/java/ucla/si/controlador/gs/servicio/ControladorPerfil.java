package ucla.si.controlador.gs.servicio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.zkoss.image.Image;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
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
import ucla.si.modelo.Cita;
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
import ucla.si.servicio.ServicioAccesorio;
import ucla.si.servicio.ServicioCaja;
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

public class ControladorPerfil extends ControladorInicio {
	
	
	
	private Usuario usuarios = (Usuario) Sessions.getCurrent().getAttribute("user");
/*
	@WireVariable
	private Usuario usuario;*/
	
	private Vehiculo vehiculo;

	@WireVariable
	private ServicioUsuario servicioUsuario;
	
	@WireVariable
	private ServicioPersona servicioPersona;
	
	@WireVariable
	private ServicioDeporte servicioDeporte;
	
	@WireVariable
	private ServicioOcupacion servicioOcupacion;
	
	@WireVariable
	private ServicioProfesion servicioProfesion;
	
	@WireVariable
	private ServicioPasatiempo servicioPasatiempo;
	
	@WireVariable
	private UsuarioDAO usuarioDao;
	
	

	@Wire
	Textbox txt_nombre, txt_apellido, txt_cedula, txt_url_foto, txt_direccion, txt_telefono, txt_correo,
			txt_contrasenna, txt_pregunta_secreta, txt_respuesta_secreta, txtPlaca, txtSerialCarro, txtSerialMotor;
	@Wire
	Combobox cmb_sexo, cmbMarca, cmbModelo, cmbColor, cmbCombustible, cmbUsuario, cmbCaja, cmbRefrigerante, cmbGrosorAceite, cmbTipoAceite, cmbClase,
	cmbDeporte, cmbOcupacion, cmbProfesion, cmbPasatiempo;
	
	@Wire
	Doublebox doubleKilometraje;
	
	@Wire
	Datebox dtb_fecha_nac;
	
	@Wire
	Intbox intAnno, intNroPuestos;
	
	@Wire
	private org.zkoss.zul.Image pics;

	@WireVariable
	private WebApp _wapp;
	
	@Wire
	Listbox listAccesorios;

	 public ControladorPerfil() {
		
	}

	@Override
	protected void inicializar() {
		
		
		
		
		
		cargarDatos(usuarios);
		
	
		ListModelList<Usuario> modeloUsuarios = new ListModelList<Usuario>(servicioUsuario.listarUsuarios());
		
		ListModelList<Ocupacion> modeloOcupacion = new ListModelList<Ocupacion>(servicioOcupacion.listarOcupaciones());
		ListModelList<Deporte> modeloDeportes = new ListModelList<Deporte>(servicioDeporte.listarDeportes());
		ListModelList<Profesion> modeloProfesiones = new ListModelList<Profesion>(servicioProfesion.listarProfesiones());
		ListModelList<Pasatiempo> modeloPasatiempos = new ListModelList<Pasatiempo>(servicioPasatiempo.listarPasatiempos());
	
		
		
		
		
		if(modeloPasatiempos.getSize() == 0 || modeloProfesiones.getSize() == 0 || modeloDeportes.getSize() == 0
				|| modeloOcupacion.getSize() == 0){
			Messagebox.show("Debe Incluir datos primero", "Error", Messagebox.OK, Messagebox.ERROR);
			//String dir = "gc/marca/frm-marca-incluir.zul";
			//clearDivApp(dir);
		}
			
		
		else{
			
			//modeloUsuarios.setMultiple(false);
						
			modeloDeportes.setMultiple(false);
			modeloOcupacion.setMultiple(false);
			modeloProfesiones.setMultiple(false);
			modeloPasatiempos.setMultiple(false);
			

			
		//	cmbUsuario.setModel(modeloUsuarios);
					
			cmbDeporte.setModel(modeloDeportes);
			cmbOcupacion.setModel(modeloOcupacion);
			cmbProfesion.setModel(modeloProfesiones);
			cmbPasatiempo.setModel(modeloPasatiempos);
			
			
			
			
			
			
		
			//cmbUsuario.setMultiline(false);
				
			cmbDeporte.setMultiline(false);
			cmbOcupacion.setMultiline(false);
			cmbProfesion.setMultiline(false);
			cmbPasatiempo.setMultiline(false);
		
			
			
		}
		
		
		
		
		// TODO Auto-generated method stub

	}
	
	
	public void cargarDatos(Usuario usuarios) {
		if (usuarios != null) {

			txt_nombre.setValue(usuarios.getPersona().getNombre());
			txt_nombre.setDisabled(false);
			txt_nombre.setReadonly(false); 
			
			txt_apellido.setValue(usuarios.getPersona().getApellido());
			txt_apellido.setDisabled(false);
			txt_apellido.setReadonly(false);
			
			txt_cedula.setValue(usuarios.getPersona().getCedula());
			txt_cedula.setDisabled(true);
			txt_cedula.setReadonly(true);
			
			txt_direccion.setValue(usuarios.getPersona().getDireccion());
			txt_direccion.setDisabled(false);
			txt_direccion.setReadonly(false); 
			
			txt_telefono.setValue(usuarios.getPersona().getTelefono());
			txt_telefono.setDisabled(false);
			txt_telefono.setReadonly(false); 
			
			cmb_sexo.setValue(usuarios.getPersona().getSexo());
			cmb_sexo.setDisabled(false);
			cmb_sexo.setReadonly(false); 
			
			dtb_fecha_nac.setValue(usuarios.getPersona().getFechaNacimiento());
			dtb_fecha_nac.setDisabled(false);
			dtb_fecha_nac.setReadonly(false); 
			
			
			
			
			txt_telefono.setValue(usuarios.getPersona().getTelefono());
			txt_telefono.setDisabled(false);
			txt_telefono.setReadonly(false);
			
			txt_correo.setValue(usuarios.getCorreo());
			txt_correo.setDisabled(false);
			txt_correo.setReadonly(false);
			
			txt_contrasenna.setValue(usuarios.getContrasenna());
			txt_contrasenna.setDisabled(false);
			txt_contrasenna.setReadonly(false);
			
			txt_pregunta_secreta.setValue(usuarios.getPregunta());
			txt_pregunta_secreta.setDisabled(false);
			txt_pregunta_secreta.setReadonly(false);
			
			txt_respuesta_secreta.setValue(usuarios.getRespuesta());
			txt_respuesta_secreta.setDisabled(false);
			txt_respuesta_secreta.setReadonly(false);
			
			cmbDeporte.setValue(usuarios.getPersona().getDeporte().getDescripcion());
			cmbProfesion.setValue(usuarios.getPersona().getProfesion().getDescripcion());
			cmbPasatiempo.setValue(usuarios.getPersona().getPasatiempo().getDescripcion());
			cmbOcupacion.setValue(usuarios.getPersona().getOcupacion().getDescripcion());
			
			
			
			/* 
			txtModelo.setValue(usuarios.getVehiculo().getModelo().getNombre());
			txtModelo.setDisabled(true);
			txtModelo.setReadonly(true); 
			
			txtServicio.setValue(usuarios.getServicio().getTitulo());
			txtServicio.setDisabled(true);
			txtServicio.setReadonly(true); 
			
			txtReferido.setValue(usuarios.getNombreReferido());
			txtReferido.setDisabled(true);
			txtReferido.setReadonly(true);  */
		}
	}
	

	@Listen("onUpload=#btnSubirImagen")
	public void subirImagenLogo(UploadEvent event) {
		org.zkoss.util.media.Media media = event.getMedia();

		if (media instanceof org.zkoss.image.Image) {
			File f;
			String url_subida = "";
			try {
				f = File.createTempFile("img_team_logo", "." + media.getFormat());

				FileOutputStream fo = new FileOutputStream(f);
				fo.write(media.getByteData());
				fo.close();
				url_subida = f.getAbsolutePath();
				this.pics.setAttribute("url_subida", url_subida);
			} catch (IOException e1) {
				Messagebox.show("Error 101, no se pudo subir la imagen", "Error", Messagebox.OK, Messagebox.ERROR);
				this.pics.setAttribute("url_subida", null);
				this.pics.setSrc("/static/img/img_question.png");
				Messagebox.show("ERROR! no se pudo subir la imagen");
				e1.printStackTrace();
			}

			this.pics.setWidth("120px");
			this.pics.setHeight("120px");
			this.pics.setContent((Image) media);

		} else {
			this.pics.setSrc("/static/img/img_question.png");
			this.pics.setAttribute("url_subida", null);
			Messagebox.show("El archivo no es una imagen: " + media, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Listen("onClick =#btnModificarPerfil")
	public void ModificarPerfil() {
		System.out.println("Registro Usuario Modificado");

		if (this.pics.getAttribute("url_subida") == null) {
			Clients.showNotification("Debes seleccionar una imagen, haz click para seleccionar.", "error", this.pics,
					"end_center", 3000);

			return;
		}

		File f_temp = new File((String) this.pics.getAttribute("url_subida"));

		try {
			File dir = new File(this._wapp.getRealPath(Urls.URL_MEDIA_USER));

			if (!dir.exists()) {
				dir.mkdirs();
			}
			File fo = new File(dir.getAbsolutePath() + "/" + f_temp.getName());

			// Lo movemos para no dejar copia en tmp.
			Files.move(f_temp.toPath(), fo.toPath());
			// Luego de moverlo no queda otra que limpiar las referencias ya
			// que el archivo deja de existir en el path original!
			this.pics.setAttribute("url_subida", null);
			this.pics.setSrc("/static/img/img_question.png");

		} catch (IOException e) {
			Messagebox.show("Error 100. No se pudo guardar el equipo.", "Error", Messagebox.OK, Messagebox.ERROR);
			e.printStackTrace();
		}

		System.out.println(pics);

		if (txt_nombre.getValue().trim() == "" || txt_apellido.getValue().trim() == ""
				|| txt_direccion.getValue().trim() == ""
				|| txt_telefono.getValue().trim() == "" || cmb_sexo.getSelectedItem().getValue() == "" 
				|| dtb_fecha_nac.getValue() == null || cmbDeporte.getValue()=="" || cmbOcupacion.getValue()==""
				|| cmbPasatiempo.getValue()=="" || cmbProfesion.getValue()=="") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			/*
			String nombre = txt_nombre.getValue().trim();
			String apellido = txt_apellido.getValue().trim();
			String cedula = txt_cedula.getValue().trim();
			String url_foto = txt_url_foto.getValue().trim();
			String sexo = cmb_sexo.getValue().trim();
			String direccion = txt_direccion.getValue().trim();
			String telefono = txt_telefono.getValue().trim();
			Date fecha_nacimiento = dtb_fecha_nac.getValue();
			String estatus = "Activo";
			
			
			
			

			Persona persona = new Persona(nombre, apellido, cedula, Urls.URL_MEDIA_USER + "/" + f_temp.getName(), sexo, direccion, telefono,
					fecha_nacimiento, estatus, new Date(), null);
			Ocupacion ocupacion = (Ocupacion)cmbOcupacion.getSelectedItem().getValue();
			Deporte deporte = (Deporte)cmbDeporte.getSelectedItem().getValue();
			Profesion profesion = (Profesion)cmbProfesion.getSelectedItem().getValue();
			Pasatiempo pasatiempo = (Pasatiempo)cmbPasatiempo.getSelectedItem().getValue();
			persona.setOcupacion(ocupacion);
			persona.setDeporte(deporte);
			persona.setProfesion(profesion);
			persona.setPasatiempo(pasatiempo);
			
			String correo = txt_correo.getValue().trim().toLowerCase();
			String contrasenna = DigestUtils.md5Hex(txt_contrasenna.getValue().trim());
			String pregunta = txt_pregunta_secreta.getValue().trim();
			String respuesta = txt_respuesta_secreta.getValue().trim();
			Usuario usuario = new Usuario(correo, contrasenna, pregunta, respuesta, estatus, new Date(), null); */
			
			
			
			usuarios.getPersona().setDireccion(txt_direccion.getValue().trim().toUpperCase());
			usuarios.getPersona().setTelefono(txt_telefono.getValue().trim().toUpperCase());
			usuarios.getPersona().setUrlFoto(txt_url_foto.getName());
			usuarios.getPersona().setSexo(cmb_sexo.getValue());
			usuarios.getPersona().setFechaNacimiento(dtb_fecha_nac.getValue());
			usuarios.getPersona().setOcupacion((Ocupacion)cmbOcupacion.getSelectedItem().getValue());
			usuarios.getPersona().setDeporte((Deporte)cmbDeporte.getSelectedItem().getValue());
			usuarios.getPersona().setProfesion((Profesion)cmbProfesion.getSelectedItem().getValue());
			usuarios.getPersona().setPasatiempo((Pasatiempo)cmbPasatiempo.getSelectedItem().getValue());
			
			

			if (servicioPersona.editarPersona(usuarios.getPersona())) {
				Messagebox.show("Registro exitoso", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "perfil.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}

	}
	
	
	
	
	@Listen("onClick =#btnUsuarioContrasena")
	public void UsuarioContrasena() {
		System.out.println("Registro Usuario Modificado");

		

		if (txt_correo.getValue().trim() == "" || txt_contrasenna.getValue().trim() == ""
				|| txt_pregunta_secreta.getValue().trim() == ""
				|| txt_respuesta_secreta.getValue().trim() == "" ) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
		
			
			usuarios.setCorreo(txt_correo.getValue());
			usuarios.setContrasenna(txt_contrasenna.getValue());
			usuarios.setPregunta(txt_pregunta_secreta.getValue());
			usuarios.setRespuesta(txt_respuesta_secreta.getValue());
			
			
			

			if (servicioUsuario.editarUsuario(usuarios)) {
				Messagebox.show("Registro exitoso", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "perfil.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}

	}
	
	
	
	
	
	

}
