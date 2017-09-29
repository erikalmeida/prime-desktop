package ucla.si.controlador.gs.servicio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.zkoss.image.Image;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Accesorio;
import ucla.si.modelo.Caja;
import ucla.si.modelo.Cita;
import ucla.si.modelo.Clase;
import ucla.si.modelo.Color;
import ucla.si.modelo.Combustible;
import ucla.si.modelo.Deporte;
import ucla.si.modelo.GrosorAceite;
import ucla.si.modelo.Ocupacion;
import ucla.si.modelo.Pasatiempo;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Profesion;
import ucla.si.modelo.Refrigerante;
import ucla.si.modelo.TipoAceite;
import ucla.si.modelo.Usuario;
import ucla.si.modelo.Vehiculo;
import ucla.si.modelo.VehiculoAccesorio;
import ucla.si.servicio.ServicioAccesorio;
import ucla.si.servicio.ServicioCaja;
import ucla.si.servicio.ServicioCita;
import ucla.si.servicio.ServicioClase;
import ucla.si.servicio.ServicioColor;
import ucla.si.servicio.ServicioCombustible;
import ucla.si.servicio.ServicioCorreo;
import ucla.si.servicio.ServicioDeporte;
import ucla.si.servicio.ServicioGrosorAceite;
import ucla.si.servicio.ServicioGrupo;
import ucla.si.servicio.ServicioMarca;
import ucla.si.servicio.ServicioModelo;
import ucla.si.servicio.ServicioOcupacion;
import ucla.si.servicio.ServicioPasatiempo;
import ucla.si.servicio.ServicioProfesion;
import ucla.si.servicio.ServicioRefrigerante;
import ucla.si.servicio.ServicioTipoAceite;
import ucla.si.servicio.ServicioUsuario;
import ucla.si.servicio.ServicioVehiculo;
import ucla.si.servicio.ServicioVehiculoAccesorio;

public  class ControladorRecepcionDetalle extends ControladorInicio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@WireVariable
	private Usuario usuario;
	@WireVariable
	private Persona persona;
	
	private Cita cita;
	
	@WireVariable
	private VehiculoAccesorio vehiculoAccesorio;
	
	@WireVariable
	private Vehiculo vehiculo;

	@WireVariable
	private ServicioUsuario servicioUsuario;
	
	@WireVariable
	private ServicioDeporte servicioDeporte;
	
	@WireVariable
	private ServicioOcupacion servicioOcupacion;
	
	@WireVariable
	private ServicioProfesion servicioProfesion;
	
	@WireVariable
	private ServicioPasatiempo servicioPasatiempo;
	
	@WireVariable
	private ServicioColor servicioColor;
	
	@WireVariable
	private ServicioCita servicioCita;
	
	@WireVariable
	private ServicioGrupo servicioGrupo;
	
	@WireVariable
	private ServicioMarca servicioMarca;
	
	@WireVariable
	private ServicioModelo servicioModelo;
	
	@WireVariable
	private ServicioCombustible servicioCombustible;
	
	@WireVariable
	private ServicioCaja servicioCaja;
	
	@WireVariable
	private ServicioAccesorio servicioAccesorio;
	
	@WireVariable
	private ServicioVehiculoAccesorio servicioVehiculoAccesorio;
	
	@WireVariable
	private ServicioRefrigerante servicioRefrigerante;
	
	@WireVariable
	private ServicioTipoAceite servicioTipoAceite;
	
	@WireVariable
	private ServicioGrosorAceite servicioGrosorAceite;
	
	@WireVariable
	private ServicioClase servicioClase;
	
	@WireVariable
	private ServicioCorreo servicioCorreo;
	
	@WireVariable
	private ServicioVehiculo servicioVehiculo;

	@Wire
	Textbox txtNombre, txtApellido, txtCedula, txt_url_foto, txtDireccion, txtTelefono, txt_correo,txtMarca,txtModelo,
			txt_contrasenna, txt_pregunta_secreta, txt_respuesta_secreta, txtPlaca, txtSerialCarro, txtSerialMotor;
	@Wire
	Combobox cmbSexo, cmbMarca, cmbModelo, cmbColor, cmbCombustible, cmbUsuario, cmbCaja, cmbRefrigerante, cmbGrosorAceite, cmbTipoAceite, cmbClase,
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

	public ControladorRecepcionDetalle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void inicializar() {
		
		cita=(Cita) getAtributo("cita");
		 cargarDatos(cita);
			
			
			
			
		
		
	//	vehiculo = (Vehiculo)getAtributo("vehiculo");
		
		//ListModelList<Marca> modeloMarcas = new ListModelList<Marca>(servicioMarca.listarMarcas());
		ListModelList<Color> modeloColores = new ListModelList<Color>(servicioColor.listarColores());
		ListModelList<Clase> modeloClases = new ListModelList<Clase>(servicioClase.listarClases());
		//ListModelList<Modelo> modeloModelos = new ListModelList<Modelo>(servicioModelo.listarModelos());
		ListModelList<Refrigerante> modeloRefrigerantes = new ListModelList<Refrigerante>(servicioRefrigerante.listarRefrigerantes());
		ListModelList<Caja> modeloCajas = new ListModelList<Caja>(servicioCaja.listarCajas());
		ListModelList<TipoAceite> modeloTiposAceites = new ListModelList<TipoAceite>(servicioTipoAceite.listarTipoAceites());
		ListModelList<GrosorAceite> modeloGrosorAceites = new ListModelList<GrosorAceite>(servicioGrosorAceite.listarGrosorAceites());
		//ListModelList<Usuario> modeloUsuarios = new ListModelList<Usuario>(servicioUsuario.listarUsuarios());
		ListModelList<Combustible> modeloCombustibles = new ListModelList<Combustible>(servicioCombustible.listarCombustibles());
		ListModelList<Ocupacion> modeloOcupacion = new ListModelList<Ocupacion>(servicioOcupacion.listarOcupaciones());
		ListModelList<Deporte> modeloDeportes = new ListModelList<Deporte>(servicioDeporte.listarDeportes());
		ListModelList<Profesion> modeloProfesiones = new ListModelList<Profesion>(servicioProfesion.listarProfesiones());
		ListModelList<Pasatiempo> modeloPasatiempos = new ListModelList<Pasatiempo>(servicioPasatiempo.listarPasatiempos());
		ListModelList<Accesorio> modeloAccesorios = new ListModelList<Accesorio>(servicioAccesorio.listarAccesorios());
		
		
		
		
		
		//	modeloMarcas.setMultiple(false);	
			modeloColores.setMultiple(false);
			modeloClases.setMultiple(false);
			modeloCajas.setMultiple(false);
			modeloCombustibles.setMultiple(false);
			modeloGrosorAceites.setMultiple(false);
			modeloTiposAceites.setMultiple(false);
			modeloRefrigerantes.setMultiple(false);
			//modeloUsuarios.setMultiple(false);
		//	modeloModelos.setMultiple(false);			
			modeloDeportes.setMultiple(false);
			modeloOcupacion.setMultiple(false);
			modeloProfesiones.setMultiple(false);
			modeloPasatiempos.setMultiple(false);
			modeloAccesorios.setMultiple(false);

			//cmbMarca.setModel(modeloMarcas);
			cmbColor.setModel(modeloColores);
			cmbClase.setModel(modeloClases);
			cmbCaja.setModel(modeloCajas);
			cmbCombustible.setModel(modeloCombustibles);
			cmbGrosorAceite.setModel(modeloGrosorAceites);
			cmbTipoAceite.setModel(modeloTiposAceites);
			cmbCombustible.setModel(modeloCombustibles);
			cmbRefrigerante.setModel(modeloRefrigerantes);
			//cmbUsuario.setModel(modeloUsuarios);
			//cmbModelo.setModel(modeloModelos);			
			cmbDeporte.setModel(modeloDeportes);
			cmbOcupacion.setModel(modeloOcupacion);
			cmbProfesion.setModel(modeloProfesiones);
			cmbPasatiempo.setModel(modeloPasatiempos);
			listAccesorios.setModel(modeloAccesorios);
			
			
			
			
			
			//cmbMarca.setMultiline(false);	
			cmbColor.setMultiline(false);
			cmbClase.setMultiline(false);
			cmbCaja.setMultiline(false);
			cmbCombustible.setMultiline(false);
			cmbGrosorAceite.setMultiline(false);
			cmbTipoAceite.setMultiline(false);
			cmbCombustible.setMultiline(false);
			cmbRefrigerante.setMultiline(false);
			//cmbUsuario.setMultiline(false);
			//cmbModelo.setMultiline(false);			
			cmbDeporte.setMultiline(false);
			cmbOcupacion.setMultiline(false);
			cmbProfesion.setMultiline(false);
			cmbPasatiempo.setMultiline(false);
			listAccesorios.setMultiple(true);
			listAccesorios.setCheckmark(true);
			
			
			
		
		
		
		
		
		// TODO Auto-generated method stub

	}
	
	
	public void cargarDatos(Cita cita) {
		if (cita != null) {

			txtNombre.setValue(cita.getVehiculo().getUsuario().getPersona().getNombre());;;
			txtNombre.setDisabled(true);
			txtNombre.setReadonly(true); 
			
			txtApellido.setValue(cita.getVehiculo().getUsuario().getPersona().getApellido());;;
			txtApellido.setDisabled(true);
			txtApellido.setReadonly(true);
			
			txtCedula.setValue(cita.getVehiculo().getUsuario().getPersona().getCedula());;;
			txtCedula.setDisabled(true);
			txtCedula.setReadonly(true);
			
			
			txtDireccion.setValue(cita.getVehiculo().getUsuario().getPersona().getDireccion());
			txtDireccion.setDisabled(false);
			txtDireccion.setReadonly(false);
			
			txtTelefono.setValue(cita.getVehiculo().getUsuario().getPersona().getTelefono());
			txtTelefono.setDisabled(false);
			txtTelefono.setReadonly(false);
			

			cmbSexo.setValue(cita.getVehiculo().getUsuario().getPersona().getSexo());
			cmbSexo.setDisabled(false);
			cmbSexo.setReadonly(false);
			
			dtb_fecha_nac.setValue(cita.getVehiculo().getUsuario().getPersona().getFechaNacimiento());
			dtb_fecha_nac.setDisabled(false);
			dtb_fecha_nac.setReadonly(false);
			/*
			cmbDeporte.setValue(cita.getVehiculo().getUsuario().getPersona().getDeporte().getDescripcion());
			cmbDeporte.setDisabled(false);
			cmbDeporte.setReadonly(false);
			
			cmbPasatiempo.setValue(cita.getVehiculo().getUsuario().getPersona().getPasatiempo().getDescripcion());
			cmbPasatiempo.setDisabled(false);
			cmbPasatiempo.setReadonly(false);
			
			cmbProfesion.setValue(cita.getVehiculo().getUsuario().getPersona().getProfesion().getDescripcion());
			cmbProfesion.setDisabled(false);
			cmbProfesion.setReadonly(false);
			
			cmbOcupacion.setValue(cita.getVehiculo().getUsuario().getPersona().getOcupacion().getDescripcion());
			cmbOcupacion.setDisabled(false);
			cmbOcupacion.setReadonly(false);
			*/
			
			
			
			
			
			
			//  --------------------- Vehiculo
			txtMarca.setValue(cita.getVehiculo().getMarca().getNombre());
			txtMarca.setDisabled(true);
			txtMarca.setReadonly(true); 
			
			txtPlaca.setValue(cita.getVehiculo().getPlaca());
			txtPlaca.setDisabled(true);
			txtPlaca.setReadonly(true); 
			
			 
			txtModelo.setValue(cita.getVehiculo().getModelo().getNombre());
			txtModelo.setDisabled(true);
			txtModelo.setReadonly(true); 
			
			intAnno.setValue(cita.getVehiculo().getAnno());
			intAnno.setDisabled(false);
			intAnno.setReadonly(false);
			
			txtSerialCarro.setValue(cita.getVehiculo().getSerialCarroceria());
			txtSerialCarro.setDisabled(false);
			txtSerialCarro.setReadonly(false);
			
			txtSerialMotor.setValue(cita.getVehiculo().getSerialMotor());
			txtSerialMotor.setDisabled(false);
			txtSerialMotor.setReadonly(false);
			
						
			if (cita.getVehiculo().getKilometraje() != null) {
				doubleKilometraje.setValue(cita.getVehiculo().getKilometraje());
			} else{
				doubleKilometraje.setValue(0);
			}
			
		//	doubleKilometraje.setValue(cita.getVehiculo().getKilometraje());
			doubleKilometraje.setDisabled(false);
			doubleKilometraje.setReadonly(false);
			
			intNroPuestos.setValue(cita.getVehiculo().getNroPuestos());
			intNroPuestos.setDisabled(false);
			intNroPuestos.setReadonly(false);
			
		/*	
			txtServicio.setValue(cita.getServicio().getTitulo());
			txtServicio.setDisabled(true);
			txtServicio.setReadonly(true); 
			
			txtReferido.setValue(cita.getNombreReferido());
			txtReferido.setDisabled(true);
			txtReferido.setReadonly(true); */
		}
	}
	
	
	
	@Listen("onAfterRender =#listAccesorios")
	public void actualizarListbox() {
		if(listAccesorios.getItemCount() > 0){
			asignarEventos(listAccesorios);
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
			Accesorio accesorio = (Accesorio) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());
            
			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("accesorio", accesorio);
				String dir = "gc/aceite/frm-aceite-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("accesorio", accesorio);
				String dir = "gc/aceite/frm-aceite-consultar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Eliminar")) {
				accesorio.setEstatus("Inactivo");

				if (servicioAccesorio.editarAccesorio(accesorio)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "pc/aceite/frm-aceite-catalogo.zul";
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

	@Listen("onClick =#btnRegistrarUsuario")
	public void registrar() {
		System.out.println("Registro Usuario");

	/*	if (this.pics.getAttribute("url_subida") == null) {
			Clients.showNotification("Debes seleccionar una imagen, haz click para seleccionar.", "error", this.pics,
					"end_center", 3000);

			return;
		}

		File f_temp = new File((String) this.pics.getAttribute("url_subida"));

	/*	try {
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

		System.out.println(pics); */

		if (txtDireccion.getValue().trim() == "" || txtTelefono.getValue().trim()=="" || cmbSexo.getSelectedItem().getValue() == "" || dtb_fecha_nac.getValue() == null 
				|| cmbOcupacion.getSelectedIndex() == -1				
				|| cmbDeporte.getSelectedIndex() == -1
				|| cmbProfesion.getSelectedIndex() == -1
				|| cmbPasatiempo.getSelectedIndex()== -1 ) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
		
			cita.getVehiculo().getUsuario().getPersona().setDireccion(txtDireccion.getValue());
			cita.getVehiculo().getUsuario().getPersona().setTelefono(txtTelefono.getValue());
			cita.getVehiculo().getUsuario().getPersona().setSexo(cmbSexo.getValue());
			cita.getVehiculo().getUsuario().getPersona().setFechaNacimiento(dtb_fecha_nac.getValue());
			cita.getVehiculo().getUsuario().getPersona().setOcupacion((Ocupacion)cmbOcupacion.getSelectedItem().getValue());
			cita.getVehiculo().getUsuario().getPersona().setDeporte((Deporte)cmbDeporte.getSelectedItem().getValue());
			cita.getVehiculo().getUsuario().getPersona().setProfesion((Profesion)cmbProfesion.getSelectedItem().getValue());
			cita.getVehiculo().getUsuario().getPersona().setPasatiempo((Pasatiempo)cmbPasatiempo.getSelectedItem().getValue());
			Usuario usuario = cita.getVehiculo().getUsuario();
			usuario.setContrasenna(DigestUtils.md5Hex("123"));
			usuario.setFechaModificacion(new Date());
			if(usuario.getGrupo() == null){
				usuario.setGrupo(servicioGrupo.buscarGrupo("CLIENTE"));
			}
			else{
				usuario.setGrupo(servicioGrupo.buscarGrupo("CLIENTE"));
			}
			Persona persona = usuario.getPersona();
            if (servicioUsuario.editarCliente(cita.getVehiculo().getUsuario().getPersona(),usuario)) {
				String nombre = servicioCorreo.cortesia(persona.getSexo())+" "+persona.getNombre().toUpperCase()+" "+persona.getApellido().toUpperCase();
            	String mensaje = servicioCorreo.mensajeAfiliacion(nombre,usuario.getCorreo(),"123");
            	servicioCorreo.enviarCorreoHTML(usuario.getCorreo(),
            			"Notificación SERVIALDANA'S te da la Bienvenida a nuestro humilde taller","","", mensaje);
            	Messagebox.show("Registro exitoso", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}

	}
	
	
	
	
	@Listen("onClick =#btnRegistrarVehiculo")
	
	
	public void RegistrarVehiculo(){
		if(txtPlaca.getValue().trim() =="" || txtSerialCarro.getValue().trim()=="" || txtSerialMotor.getValue().trim()==""
				||  cmbColor.getSelectedIndex() == -1){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{/*
			String placa = txtPlaca.getValue().trim().toUpperCase();
			String serialCarroceria = txtSerialCarro.getValue().trim().toUpperCase();
			String serialMotor = txtSerialMotor.getValue().trim().toUpperCase();
			Integer anno = intAnno.getValue().intValue();
			Integer nroPuestos = intNroPuestos.getValue().intValue();
			float kilometraje = doubleKilometraje.getValue().floatValue();
		
			vehiculo = new Vehiculo(placa, anno, nroPuestos, serialCarroceria, serialMotor, kilometraje,"Activo",new Date(), null);
			*/ //Marca marca = (Marca)cmbMarca.getSelectedItem().getValue();
		/*	Color color = (Color)cmbColor.getSelectedItem().getValue();
			Clase clase = (Clase)cmbClase.getSelectedItem().getValue();
			Refrigerante refrigerante = (Refrigerante)cmbRefrigerante.getSelectedItem().getValue();
			Caja caja = (Caja)cmbCaja.getSelectedItem().getValue();
			TipoAceite tipoAceite = (TipoAceite)cmbTipoAceite.getSelectedItem().getValue();
			GrosorAceite grosorAceite = (GrosorAceite)cmbGrosorAceite.getSelectedItem().getValue();
			//Usuario usuario = (Usuario)cmbUsuario.getSelectedItem().getValue();
			//Modelo modelo = (Modelo)cmbModelo.getSelectedItem().getValue();
			Combustible combustible = (Combustible)cmbCombustible.getSelectedItem().getValue(); */
						
			//vehiculo.setMarca(marca);
			
			
			System.out.println(cita.getVehiculo().getPlaca()+"=========================================================");
			cita.getVehiculo().setAnno(intAnno.getValue());
			cita.getVehiculo().setNroPuestos(intNroPuestos.getValue());
			cita.getVehiculo().setSerialCarroceria(txtSerialCarro.getValue().trim().toUpperCase());
			cita.getVehiculo().setSerialMotor(txtSerialMotor.getValue().trim().toUpperCase());
			cita.getVehiculo().setKilometraje(doubleKilometraje.getValue().floatValue());
			cita.getVehiculo().setColor((Color)cmbColor.getSelectedItem().getValue());
			cita.getVehiculo().setCombustible((Combustible)cmbCombustible.getSelectedItem().getValue());
			cita.getVehiculo().setCaja((Caja)cmbCaja.getSelectedItem().getValue());
			cita.getVehiculo().setRefrigerante((Refrigerante)cmbRefrigerante.getSelectedItem().getValue());
			cita.getVehiculo().setColor((Color)cmbColor.getSelectedItem().getValue());
			cita.getVehiculo().setTipoAceite((TipoAceite)cmbTipoAceite.getSelectedItem().getValue());
			cita.getVehiculo().setGrosorAceite((GrosorAceite)cmbGrosorAceite.getSelectedItem().getValue());
			cita.getVehiculo().setClase((Clase)cmbClase.getSelectedItem().getValue());
			
			
			
			
			
			
						
		
			//vehiculo.setClase(clase);
			
			//setAtributo("vehiculo", vehiculo);
			
			
			
			if(servicioVehiculo.editarVehiculo(cita.getVehiculo())){
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/recepcion/frm-recepcion-detalle.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	
	@Listen("onClick =#btnRegistrarAccesorios")
	public void aRegistrarAccesorio() {
		
	vehiculo=	cita.getVehiculo();
		
		
	//	System.out.println("AQUIIII: "+  vehiculo.getId()+"descripcion: "+vehiculo.getPlaca());

		List<Listitem> listItemAccesorios = new ArrayList<Listitem>(listAccesorios.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		boolean test = false;

		if (!listAccesorios.getItems().isEmpty()) {
			for (Listitem listitem : listItemAccesorios) {
				if (listitem.isSelected()) {
					vehiculoAccesorio = new VehiculoAccesorio();
					Accesorio accesorio = (Accesorio) listitem.getValue();

					if (accesorio != null) {
					

						vehiculoAccesorio.setVehiculo(vehiculo);
						vehiculoAccesorio.setAccesorio(accesorio);
						
						vehiculoAccesorio.setEstatus("Activo");
						vehiculoAccesorio.setFechaCreacion(new Date());
						// servicioServiciosHerramientas.incluirServiciosHerramientas(serviciosHerramientas);
						cita.setEstado("Recibida");
						servicioVehiculoAccesorio.incluirVehiculoAccesorio(vehiculoAccesorio);
						servicioCita.editarCita(cita);
						
						test = true;
						// System.out.println("sdsadsadsads:"+servicio.getFalla().getId());

					}
				}
			}

			if (test == true) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/recepcion/frm-recepcion.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	

}
