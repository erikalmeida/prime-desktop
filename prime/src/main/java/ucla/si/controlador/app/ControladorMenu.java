package ucla.si.controlador.app;

import java.util.Date;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;

import ucla.si.modelo.Sistema;
import ucla.si.modelo.Usuario;
import ucla.si.servicio.ServicioCorreo;
import ucla.si.servicio.ServicioSistema;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import org.zkoss.zk.ui.select.annotation.Wire;

public class ControladorMenu extends ControladorInicio {

	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioSistema servicioSistema;

	@Wire
	Label labelUser;

	@WireVariable
	private Page page;

	@WireVariable
	private ServicioCorreo servicioCorreo;

	@Wire
	private Nav administracionRecursos;

	private Sistema sistema;

	@Override
	public void inicializar() {

		// servicioCorreo.enviarCorreo();
		sistema = servicioSistema.buscar();
		if (sistema == null) {
			sistema = new Sistema("Prime", "SI", "wwww", "Activo", new Date(), null);
			System.out.println("Sistema incluido: " + servicioSistema.incluirSistema(sistema));
		}
		Sessions.getCurrent().setAttribute("sistema", sistema);
		if (autenticados != null) {
			if (autenticados.containsKey("hola")) {
				Usuario usuario = autenticados.get("hola");
				System.out.println("usuario " + usuario.getCorreo());
			}
		}
		Usuario usuario = (Usuario) Sessions.getCurrent().getAttribute("user");
		/*if (usuario.getId() == 2) {
			System.out.println("lorem");
			this.administracionRecursos.setClass("display-none");
		}*/
		if (usuario == null) {
			Executions.sendRedirect("/");
		} else {
			String nombreUsuario = ((Usuario) (Sessions.getCurrent().getAttribute("user"))).getPersona().getNombre()
					+ " " + ((Usuario) (Sessions.getCurrent().getAttribute("user"))).getPersona().getApellido();
			this.page.setTitle("Bienvenido " + nombreUsuario);
			labelUser.setValue(nombreUsuario);
		}
		System.out.println("sesion cant: " + Sessions.getCount());
	}

	@Listen("onClick =#dashboard")
	public void aCambiarContrasenna() {
		String dir = "perfil.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#logout")
	public void logout() {
		Session sess = Sessions.getCurrent();
		sess.removeAttribute("user");
		Executions.sendRedirect("/");
	}

	@Listen("onClick =#informacion")
	public void aInformacion() {
		String dir = "gc/frm-servicios.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#grupos")
	public void aGrupos() {
		String dir = "gc/grupo/frm-grupo-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#funciones")
	public void aFunciones() {
		String dir = "gc/funcion/frm-funcion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#acciones")
	public void aAcciones() {
		String dir = "gc/accion/frm-accion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#servicio")
	public void servicio() {
		String dir = "gs/servicio/frm-servicio-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#configurarCatalogoServicios")
	public void configurarServicio() {
		String dir = "gs/servicio/frm-configurar-servicio-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	@Listen("onClick =#actualizarCatalogoServicios")
	public void ActualizarServicio() {
		String dir = "gs/servicio/frm-actualizar-servicio-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#asignacionGrupos")
	public void aAsignacionGrupos() {
		String dir = "gc/grupo/frm-grupo-asignacion.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#clase")
	public void aClases() {
		String dir = "gc/clase/frm-clase-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#espacio")
	public void espacio() {
		String dir = "gc/espacio/frm-espacio-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#horario")
	public void horario() {
		String dir = "gc/horario/frm-horario-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#falla")
	public void falla() {
		String dir = "gc/falla/frm-falla-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#modelo")
	public void modelo() {
		String dir = "gc/modelo/frm-modelo-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#anomalia")
	public void anomalia() {
		String dir = "gc/anomalia/frm-anomalia-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoReclamo")
	public void tipoReclamo() {
		String dir = "gc/tipoReclamo/frm-tipoReclamo-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoClase")
	public void aTiposClases() {
		String dir = "gc/tipoClase/frm-tipoClase-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#usuarios")
	public void aUsuarios() {
		String dir = "gc/grupo/frm-grupo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#msg2")
	public void msg() {
		Messagebox.show("hola");
	}

	@Listen("onClick =#nuevoCorreo")
	public void nuevoCorreo() {
		String dir = "gc/correo/frm-correo.zul";
		clearDivApp(dir);
	}

	public void escribir() {
		Map<String, Usuario> autenticados = getAutenticados();
		Usuario usuario = new Usuario();
		usuario.setCorreo("dreik@gmail.com");
		autenticados.put("hola", usuario);
	}

	@Listen("onClick =#tipoCombustibles")
	public void aTipoCombustibles() {
		String dir = "gc/tipoCombustible/frm-tipoCombustible-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoAceites")
	public void aTipoAceites() {
		String dir = "gc/tipoAceite/frm-tipoAceite-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#atender_cita")
	public void atenderCita() {
		String dir = "gac/frm-atender-cita.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#asignarCita")
	public void asignarCita() {
		String dir = "gac/frm-asignar-cita.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	/*@Listen("onClick =#validarPresupuesto")
	public void asignarRecursos() {
		//String dir = "gac/atender/frm-planificacion-agenda.zul";
		String dir = "gs/presupuesto/frm-validar-presupuesto.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}*/
	
	@Listen("onClick =#validarPresupuesto")
	public void asignarRecursos() {
		//String dir = "gac/atender/frm-planificacion-agenda.zul";
		String dir = "gs/presupuesto/frm-catalogo-presupuesto.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#asignarEspacio")
	public void Planificacion() {
		//String dir = "gac/atender/frm-planificacion-agenda.zul";
		String dir = "gc/espacio/frm-asignar-espacio-recursos-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#asignarEspacioCita")
	public void asginarEspacioCita() {
		//String dir = "gac/atender/frm-planificacion-agenda.zul";
		//String dir = "gc/espacio/frm-catalogo-cita-aprobada.zul";
		String dir = "gc/espacio/frm-asignar-espacio-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	
	@Listen("onClick =#cancelarCita")
	public void cancelarCita() {
		String dir = "gac/frm-cancelar-cita.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#rectificacion")
	public void aRectificaciones() {
		String dir = "gc/rectificacion/frm-rectificacion-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#marcaRepuesto")
	public void marcaRepuesto() {
		String dir = "gc/marcaRepuesto/frm-marcaRepuesto-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#aceite")
	public void aAceites() {
		String dir = "gc/aceite/frm-aceite-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#motor")
	public void aMotores() {
		String dir = "gc/motor/frm-motor-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#grosorAceite")
	public void aGrosorAceites() {
		String dir = "gc/grosorAceite/frm-grosorAceite-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#habilidad")
	public void aHabilidades() {
		String dir = "gc/habilidad/frm-habilidad-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#color")
	public void aColores() {
		String dir = "gc/color/frm-color-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoMotor")
	public void aTiposMotores() {
		String dir = "gc/tipoMotor/frm-tipoMotor-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	/*
	 * TODO: Diagnostico (.zul)
	 * 
	 */

	@Listen("onClick =#diagnostico")
	public void diagnostico() {
		String dir = "gs/servicio/frm-diagnostico-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#actualizacion")
	public void actualizacionRecursos() {
		String dir = "gc/espacio/frm-actualizar-espacio-recurso-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#recepcion")
	public void aceptarr() {
		String dir = "gs/recepcion/frm-recepcion.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#Aceptarr")
	public void Aceptarr() {
		String dir = "gs/frm-diagnostico-presupuesto.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#Aceptar")
	public void aceptar() {
		String dir = "gs/frm-orden-servicio.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#recepcionVehiculo")
	public void recepcionVehiculo() {
		String dir = "gs/recepcion/frm-recepcion.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#generarordenservicio")
	public void generarOrdenServicio() {
		String dir = "gs/servicio/frm-generar-orden-servicio-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#generar")
	public void generarO() {
		String dir = "gs/frm-orden-servicio.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#generarGarantia")
	public void Garantia() {
		String dir = "gs/frm-generar-garantia-todos.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#garantiaDetail")
	public void GarantiaDetail() {
		String dir = "gs/frm-generar-garantia-detail.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#deporte")
	public void deporte() {
		String dir = "pc/deporte/frm-deporte-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#ocupacion")
	public void ocupacion() {
		String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#pasatiempo")
	public void pasatiempo() {
		String dir = "pc/pasatiempo/frm-pasatiempo-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#profesion")
	public void profesion() {
		String dir = "pc/profesion/frm-profesion-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoEventualidad")
	public void tipoEventualidad() {
		String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#etapas")
	public void etapas() {
		String dir = "gc/etapa/frm-etapa-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#viaje")
	public void viaje() {
		String dir = "pc/viaje/frm-viaje-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#ciudad")
	public void ciudad() {
		String dir = "gc/ciudad/frm-ciudad-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#estado")
	public void estado() {
		String dir = "gc/estado/frm-estado-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#descuento")
	public void descuento() {
		String dir = "gc/descuento/frm-descuento-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#marcaTecnologia")
	public void marcaTecnologia() {
		String dir = "gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tarifa")
	public void tarifa() {
		String dir = "gc/tarifa/frm-tarifa-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#chequearServicio")
	public void pruebaServicio() {
		String dir = "gs/prueba/frm-prueba-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#generarOrdenEntrega")
	public void serviciosFinalizados() {
		String dir = "gs/ordenEntrega/frm-servicios-finalizados.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#atender")
	public void atencion() {
		String dir = "gac/atender/frm-atender-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoAceite")
	public void tipoAceite() {
		String dir = "gc/tipoAceite/frm-tipoAceite-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoServicio")
	public void TipoServicio() {
		String dir = "gc/tipoServicio/frm-tipoServicio-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#combustible")
	public void combustible() {
		String dir = "gc/combustible/frm-combustible-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#refrigerante")
	public void refrigerante() {
		String dir = "gc/refrigerante/frm-refrigerante-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoCombustible")
	public void tipoCombustible() {
		String dir = "gc/tipoCombustible/frm-tipoCombustible-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoRefrigerante")
	public void tipoRefrigerante() {
		String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoTecnologia")
	public void tipoTecnologia() {
		String dir = "gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoRepuesto")
	public void tipoRepuesto() {
		String dir = "gc/tipoRepuesto/frm-tipoRepuesto-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#herramientas")
	public void herramienta() {
		String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tecnologias")
	public void tecnologia() {
		String dir = "gc/tecnologia/frm-tecnologia-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#actualizarContenidoWeb")
	public void configuracionWeb() {
		String dir = "gc/web/configuracion-web.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#actualizarContenidoMovil")
	public void configuracionMovil() {
		String dir = "gc/movil/configuracion-movil.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#actualizarPromocion")
	public void actualizarPromocion() {
		String dir = "gp/promocion/frm-actualizar-promocion-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#configurarPromocion")
	public void configurarPromocion() {
		String dir = "gp/promocion/frm-promocion-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	
	
	
	@Listen("onClick =#motivo")
	public void motivo() {
		String dir = "gc/motivo/frm-motivo-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	

	/*
	 * Jose Rodriguez GESTION POST-SERVICIO
	 */

		@Listen("onClick =#solicitudReclamo")
		public void Reclamo() {
			String dir = "gps/reclamo/frm-reclamo-ordenesEntrega.zul";
			clearDivApp(dir);
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		}
		
		@Listen("onClick =#verificarGarantia")
		public void Reclamos() {
			String dir = "gps/reclamo/frm-reclamo-reclamos.zul";
			clearDivApp(dir);
			// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		}

	
	@Listen("onClick =#marca")
	public void Marca() {
		String dir = "gc/marca/frm-marca-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#tipoCaja")
	public void aTiposCajas() {
		String dir = "gc/tipoCaja/frm-tipoCaja-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#caja")
	public void aCajas() {
		String dir = "gc/caja/frm-caja-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#accesorio")
	public void aAccesorios() {
		String dir = "gc/accesorio/frm-accesorio-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}


	@Listen("onClick =#difusionPersonalizada")
	public void difusionPersonalizada() {
		String dir = "gdd/frm-difusion-personalizada.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#calificarServicio")
	public void calificarServicio() {
		String dir = "gs/calificar/frm-orden-entrega.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
}
