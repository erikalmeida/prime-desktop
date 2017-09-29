package ucla.si.controlador.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.modelo.Accion;
import ucla.si.modelo.Funcion;
import ucla.si.modelo.Grupo;
import ucla.si.modelo.Job;
import ucla.si.modelo.Permiso;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Sistema;
import ucla.si.modelo.Usuario;
import ucla.si.servicio.ServicioAccion;
import ucla.si.servicio.ServicioCorreo;
import ucla.si.servicio.ServicioFuncion;
import ucla.si.servicio.ServicioGrupo;
import ucla.si.servicio.ServicioJob;
import ucla.si.servicio.ServicioPermiso;
import ucla.si.servicio.ServicioSistema;
import ucla.si.servicio.ServicioUsuario;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class ControladorSesion extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioUsuario servicioUsuario;
	
	@WireVariable
	private ServicioJob servicioJob;
	
	@WireVariable
	private ServicioPermiso servicioPermiso;
	
	@WireVariable
	private ServicioAccion servicioAccion;
	
	@WireVariable
	private ServicioFuncion servicioFuncion;
	
	@WireVariable
	private ServicioGrupo servicioGrupo;
	
	@WireVariable
	private ServicioSistema servicioSistema;
	
	@WireVariable
	private ServicioCorreo servicioCorreo;
	
	private Sistema sistema;
	
	/*Componentes*/
	@Wire
	private Textbox txt_correo, txt_contrasenna;
	@Wire
	private Div login;
	
	@Override
	protected void inicializar() {
		sistema = servicioSistema.buscar();
		if(sistema == null){
			sistema= new Sistema("Prime", "SI", "wwww","Activo", new Date(), null);
			sistema.setCorreo("prime@prime.com");
			System.out.println("Sistema incluido: " + servicioSistema.incluirSistema(sistema));
		}
		Sessions.getCurrent().setAttribute("sistema", sistema);
		boolean mostrar = false;
		if(servicioJob.jobs().isEmpty()){
			login.setVisible(false);
			List<Job> jobs = new ArrayList<Job>();
			Job job1 = new Job(1,"crearGrupos","Pendiente","Activo",new Date(), null);
			jobs.add(job1);
			Job job2 = new Job(2,"crearAcciones","Pendiente","Activo",new Date(), null);
			jobs.add(job2);
			Job job3 = new Job(3,"crearFunciones","Pendiente","Activo",new Date(), null);
			jobs.add(job3);
			Job job4 = new Job(4,"crearUsuarios","Pendiente","Activo",new Date(), null);
			jobs.add(job4);
			Job job5 = new Job(5,"crearPermisos","Pendiente","Activo",new Date(), null);
			jobs.add(job5);
			for (Job job : jobs) {
				mostrar = servicioJob.incluirJob(job);
			}
			if(mostrar){
				List<Job> jobsPendientes = servicioJob.jobsPendientes();
				if(!jobsPendientes.isEmpty()){
					for (Job job : jobsPendientes) {
						job.setEstado(jobsEjecutados(job.getClave()) ? "Ejecutado" : "Pendiente");
						mostrar = servicioJob.editarJob(job);
					}
					login.setVisible(mostrar);
				}
				else{//Si no hay jobs pendientes sigue su curso normal
					login.setVisible(mostrar);
				}
			}
		}
		else{
			List<Job> jobsPendientes = servicioJob.jobsPendientes();
			if(!jobsPendientes.isEmpty()){
				for (Job job : jobsPendientes) {
					job.setEstado(jobsEjecutados(job.getClave()) ? "Ejecutado" : "Pendiente");
					mostrar = servicioJob.editarJob(job);
				}
				login.setVisible(mostrar);
			}
			else{//Si no hay jobs pendientes sigue su curso normal
				login.setVisible(mostrar);
			}
		}
		login.setVisible(true);
	}
	
	@Listen("onClick = #btn_entrar")
	public void entrar(){
		if(txt_correo.getValue().trim() =="" || txt_contrasenna.getValue().trim() == ""){
			Messagebox.show("Completar los campos", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		else{
			String correo = txt_correo.getValue().trim().toLowerCase();
			String contrasenna = DigestUtils.md5Hex(txt_contrasenna.getValue().trim());
			Usuario usuario = servicioUsuario.buscarUsuario(correo, contrasenna);
			if(usuario != null){
				Session sess = Sessions.getCurrent();
				sess.setAttribute("user", usuario);
				Executions.sendRedirect("prime/index.zul");
				Persona persona = usuario.getPersona();
				String nombre = cortesia(persona.getSexo())+" "+persona.getNombre().toUpperCase()+" "+persona.getApellido().toUpperCase();
				String mensaje = servicioCorreo.mensajeSesion(nombre);
				servicioCorreo.enviarCorreoHTML(usuario.getCorreo(),"Notificación SERVIALDANA'S","","", mensaje);
			}
			else{
				Messagebox.show("El correo o contraseña invalido", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
		}
		
	}
	
	public boolean jobsEjecutados(int clave){
        boolean resultado = false;
        switch(clave){
            case 1: 
            	resultado = servicioPermiso.crearGrupos();
            	break;
            case 2:
            	resultado = servicioPermiso.crearAcciones();
            	break;
            case 3: 
            	resultado = servicioPermiso.crearFunciones(sistema);
            	break;
            case 4: 
            	resultado = crearUsuarios();
            	break;
            case 5: 
            	resultado = crearPermisos();
            	break;
        }
		return resultado;
	}
	
	public boolean crearPermisos(){
		boolean resultado = false;
		List<Grupo> grupos = servicioGrupo.listarGruposSinAdministrador();
		if(!grupos.isEmpty()){
			for (Grupo grupo : grupos) {
				resultado = construirPermisosPorGrupo(grupo);
			}
		}
		return resultado;
	}
	
	@SuppressWarnings("rawtypes")
	public boolean construirPermisosPorGrupo(Grupo grupo){
		boolean resultado = false;
		List<Accion> acciones = servicioAccion.listarAccionesSinDesplegar();
		Accion accionDesplegar = servicioAccion.buscarAccion("DESPLEGAR");
		Map<String,Permiso> arbolGrupo = servicioPermiso.arbolGrupo(grupo);
		if(!arbolGrupo.isEmpty()){
			return true;
		}
		else{
			List<Funcion> funcionesHojas = servicioFuncion.listarFuncionesHojas();
			if(!funcionesHojas.isEmpty()){
				List<Permiso> permisosIncluir = new ArrayList<Permiso>();
				Map<String,Funcion> funcionesPadre = new LinkedHashMap<String,Funcion>();
				for (Funcion funcionHoja : funcionesHojas) {
					for (Accion accion : acciones) {
						Funcion funcionPadre = funcionHoja.getFuncionPadre();
						if(funcionPadre != null){
							if(!funcionesPadre.containsKey(funcionPadre.getClave())){
								funcionesPadre.put(funcionPadre.getClave(), funcionPadre);
								//System.out.println("clave primer For "+funcionPadre.getClave());
							}
						}
						Permiso permiso = new Permiso(false, "Activo", new Date(), null);
						permiso.setGrupo(grupo);
						permiso.setFuncion(funcionHoja);
						//La accion varia con respecto al numero de acciones atomicas
						permiso.setAccion(accion);
						permisosIncluir.add(permiso);
					}
				}
				//System.out.println("la cantidad de permisos a incluir es: "+funcionesHojas.size()+"*"+acciones.size()+" = "+permisosIncluir.size());
				//System.out.println("la cantidad de padres de hojas es: "+funcionesPadre.size());
				//Clasificamos los padres directos de las hojas los cuales tienen solo la accion de desplegar
				Map<String,Funcion> funcionesAuxiliares = new LinkedHashMap<String,Funcion>();
				Iterator iterator = funcionesPadre.entrySet().iterator();
				while(iterator.hasNext()){
					Map.Entry e =  (Map.Entry) iterator.next();
					Funcion funcionPadre = (Funcion) e.getValue();
					if(funcionPadre != null){
						if(!funcionesAuxiliares.containsKey(funcionPadre.getClave())){
							funcionesAuxiliares.put(funcionPadre.getClave(), funcionPadre);
							//System.out.println("clave funcionPadre "+funcionPadre.getClave());
							Funcion funcionAbuelo = servicioFuncion.validarPadre(funcionPadre.getId()) ?
								servicioFuncion.buscarFuncion(funcionPadre.getFuncionPadre().getId()) : null;
							while(funcionAbuelo != null){
								if(!funcionesAuxiliares.containsKey(funcionAbuelo.getClave())){
									funcionesAuxiliares.put(funcionAbuelo.getClave(), funcionAbuelo);
									//System.out.println("clave funcionAbuelo "+funcionAbuelo.getClave());
									funcionAbuelo = servicioFuncion.validarPadre(funcionAbuelo.getId()) ?
										servicioFuncion.buscarFuncion(funcionAbuelo.getFuncionPadre().getId()) : null;
								}
								else{
									funcionAbuelo = null;
								}
							}
						}
					}
				}
				//System.out.println("la cantidad de padres de hojas es: "+funcionesAuxiliares.size());
				Iterator it = funcionesAuxiliares.entrySet().iterator();
				while(it.hasNext()){
					Map.Entry e =  (Map.Entry) it.next();
					Funcion funcion = (Funcion) e.getValue();
					Permiso permiso = new Permiso(false, "Activo", new Date(), null);
					permiso.setGrupo(grupo);
					permiso.setFuncion(funcion);
					//La accion varia con respecto al numero de acciones atomicas
					permiso.setAccion(accionDesplegar);
					permisosIncluir.add(permiso);
				}
				//System.out.println("Permisos a Incluir entro hojas y padres "+permisosIncluir.size());
				if(!permisosIncluir.isEmpty()){
					for (Permiso permiso : permisosIncluir) {
						resultado = servicioPermiso.incluirPermiso(permiso);
					}
				}
			}
		}
		return resultado;
	}
	
	public boolean crearUsuarios(){
		boolean resultado = false;
		/*Administrador*/
		String nombre = "ANDY";
		String apellido = "ALDANA";
		String cedula = "V-1";
		String sexo = "Masculino";
		String direccion = "Carrera 22 entre calles 12 y 13";
		String telefono = "(0416)5017020";
		Date fechaNacimiento = new Date();
		String estatus = "Activo";
		Persona persona = new Persona(nombre, apellido, cedula, null, sexo, direccion, telefono,
				fechaNacimiento, estatus, new Date(), null);
		String correo = "andy@prime.com";
		String contrasenna = DigestUtils.md5Hex("123");
		String pregunta = "prime";
		String respuesta = "prime";
		Usuario usuario = new Usuario(correo, contrasenna, pregunta, respuesta, estatus, new Date(), null);
		Grupo grupo = servicioGrupo.buscarGrupo("ADMINISTRADOR");
		usuario.setGrupo(grupo);
		resultado = servicioUsuario.registrarCliente(persona, usuario);
		
		/*Cliente*/
		String nombre1 = "JOSE LUIS";
		String apellido1 = "RAMOS";
		String cedula1 = "V-2";
		String sexo1 = "Masculino";
		String direccion1 = "Carrera 22 entre calles 12 y 13";
		String telefono1 = "(0416)5017020";
		Date fechaNacimiento1 = new Date();
		String estatus1 = "Activo";
		Persona persona1 = new Persona(nombre1, apellido1, cedula1, null, sexo1, direccion1, telefono1,
				fechaNacimiento1, estatus1, new Date(), null);
		String correo1 = "jose@prime.com";
		String contrasenna1 = DigestUtils.md5Hex("123");
		String pregunta1 = "prime";
		String respuesta1 = "prime";
		Usuario usuario1 = new Usuario(correo1, contrasenna1, pregunta1, respuesta1, estatus1, new Date(), null);
		Grupo grupo1 = servicioGrupo.buscarGrupo("CLIENTE");
		usuario1.setGrupo(grupo1);
		resultado = servicioUsuario.registrarCliente(persona1, usuario1);
		/*Mecanico*/
		String nombre2 = "JOSE JAVIER";
		String apellido2 = "LISCANO";
		String cedula2 = "V-3";
		String sexo2 = "Masculino";
		String direccion2 = "Carrera 22 entre calles 12 y 13";
		String telefono2 = "(0416)5017020";
		Date fechaNacimiento2 = new Date();
		String estatus2 = "Activo";
		Persona persona2 = new Persona(nombre2, apellido2, cedula2, null, sexo2, direccion2, telefono2,
				fechaNacimiento2, estatus2, new Date(), null);
		String correo2 = "javier@prime.com";
		String contrasenna2 = DigestUtils.md5Hex("123");
		String pregunta2 = "prime";
		String respuesta2 = "prime";
		Usuario usuario2 = new Usuario(correo2, contrasenna2, pregunta2, respuesta2, estatus2, new Date(), null);
		Grupo grupo2 = servicioGrupo.buscarGrupo("MECANICO");
		usuario2.setGrupo(grupo2);
		resultado = servicioUsuario.registrarCliente(persona2, usuario2);
		return resultado;
	}
	
	public String cortesia(String sexo){
		String cortesia ="";
		if(sexo.equalsIgnoreCase("Masculino")){
			cortesia ="Sr.";
		}
		else if(sexo.equalsIgnoreCase("Femenino")){
			cortesia ="Sra.";
		}
		else{
			cortesia ="";
		}
		return cortesia;
	}

}
