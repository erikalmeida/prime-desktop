package ucla.si.controlador.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;

import ucla.si.modelo.Grupo;
import ucla.si.modelo.Permiso;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Sistema;
import ucla.si.modelo.Usuario;
import ucla.si.servicio.ServicioPermiso;
import ucla.si.servicio.ServicioCorreo;
import ucla.si.servicio.ServicioGrupo;
import ucla.si.servicio.ServicioSistema;

import org.zkoss.zk.ui.Session;

import org.zkoss.zk.ui.select.annotation.Wire;

public class ControladorMenu2 extends ControladorInicio {

	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioSistema servicioSistema;

	@Wire
	private Label labelUser;

	@Wire
	private Bandbox bboxBuscar;

	@Wire
	private Navbar menu;

	@WireVariable
	private Page page;

	@WireVariable
	private ServicioCorreo servicioCorreo;

	@WireVariable
	private ServicioPermiso servicioPermiso;

	@WireVariable
	private ServicioGrupo servicioGrupo;

	private Sistema sistema;

	private Map<String,Object> menuFunciones = new LinkedHashMap<String, Object>();
	private Map<String,Permiso> menuGrupo = new HashMap<String, Permiso>();
	private Map<String,Navitem> nivel4Navitem = new HashMap<String, Navitem>();
	private Map<String,Navitem> nivel3Navitem = new HashMap<String, Navitem>();
	private Map<String,Navitem> nivel2Navitem = new HashMap<String, Navitem>();
	private Map<String,Navitem> nivel1Navitem = new HashMap<String, Navitem>();
	private Map<String,Nav> nivel1Nav = new HashMap<String, Nav>();
	private Map<String,Nav> nivel2Nav = new HashMap<String, Nav>();
	private Map<String,Nav> nivel3Nav = new HashMap<String, Nav>();
	private Map<String,String> nivelBloqueado = new HashMap<String, String>();

	@Override
	public void inicializar() {
		//servicioCorreo.enviarCorreo();
		sistema = (Sistema)Sessions.getCurrent().getAttribute("sistema");
		if (autenticados != null) {
			if (autenticados.containsKey("hola")) {
				Usuario usuario = autenticados.get("hola");
				System.out.println("usuario " + usuario.getCorreo());
			}
		}

		Usuario usuario = (Usuario) Sessions.getCurrent().getAttribute("user");
		if (usuario == null) {
			Executions.sendRedirect("/");
		} else {
			Persona persona = usuario.getPersona();
			Grupo grupo = usuario.getGrupo() == null ? servicioGrupo.buscarGrupo("DEFAULT") : usuario.getGrupo() ;
			String nombreUsuario = persona.getNombre().toUpperCase()
					+ " " + persona.getApellido().toUpperCase();
			this.page.setTitle("Bienvenido " + nombreUsuario);
			labelUser.setValue(nombreUsuario);
			cargarMenu(menu);
			cargarMapaNivelArbol();
			System.out.println("menu cant "+menuFunciones.size());
			menuGrupo = servicioPermiso.menuGrupo(grupo);
			System.out.println("menuGrupo "+menuGrupo);
			if(grupo != null){
				if(!grupo.getNombre().equalsIgnoreCase("ADMINISTRADOR")){
					menuDinamicoGrupo();
				}
			}
		}
		System.out.println("sesion cant: " + Sessions.getCount());

	}

	//1.- 	nivel1Inicio						Inicio
	@Listen("onClick =#nivel1Inicio")
	public void nivel1Inicio(){
		//String dir = "inicio/inicio.zul";
		//clearDivApp(dir);
	}

	//2.- 	nivel1AdministracionRecursos				Administración de Recursos
	@Listen("onClick =#nivel1AdministracionRecursos")
	public void nivel1AdministracionRecursos(){
		//String dir = "administracion-recursos/";
		//clearDivApp(dir);
	}

	//2.1.-		nivel2General					General
	@Listen("onClick =#nivel2General")
	public void nivel2General(){
		//String dir = "administracion-recursos/general";
		//clearDivApp(dir);
	}

	//2.1.1.- 		nivel3Cliente				Opciones de Cliente
/*	@Listen("onClick =#nivel3Cliente")
	public void nivel3Cliente(){
		//String dir = "administracion-recursos/general/cliente";
		//clearDivApp(dir);
	}*/

	//2.1.1.1.-			nivel4Ocupacion			Ocupación
	@Listen("onClick =#nivel4Ocupacion")
	public void nivel4Ocupacion(){
		//String dir = "administracion-recursos/general/cliente/ocupacion/ruta";
		String dir ="pc/ocupacion/frm-ocupacion-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.1.2.-			nivel4Profesion			Profesión
	@Listen("onClick =#nivel4Profesion")
	public void nivel4Profesion(){
		//String dir = "administracion-recursos/general/cliente/profesion/ruta";
		String dir = "pc/profesion/frm-profesion-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.1.3.-			nivel4Pasatiempo		Pasatiempo
	@Listen("onClick =#nivel4Pasatiempo")
	public void nivel4Pasatiempo(){
		//String dir = "administracion-recursos/general/cliente/pasatiempo/ruta";
		String dir = "pc/pasatiempo/frm-pasatiempo-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.1.4.-			nivel4Ciudad			Ciudad
	@Listen("onClick =#nivel4Ciudad")
	public void nivel4Ciudad(){
		//String dir = "administracion-recursos/general/cliente/ciudad/ruta";
		String dir ="gc/ciudad/frm-ciudad-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.1.5.-			nivel4Estado			Estado
	@Listen("onClick =#nivel4Estado")
	public void nivel4Estado(){
		//String dir = "administracion-recursos/general/cliente/estado/ruta";
		String dir ="gc/estado/frm-estado-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.1.6.-			nivel4Cliente			Cliente
	@Listen("onClick =#nivel4Cliente")
	public void nivel4Cliente(){
		//String dir = "administracion-recursos/general/cliente/cliente/ruta";
		String dir ="gs/recepcion/frm-recepcion-cliente.zul";
		clearDivApp(dir);
	}

	//2.1.1.7.-			nivel4Motivo		Motivo
	@Listen("onClick =#nivel4Motivo")
	public void nivel4Motivo(){
		//String dir = "administracion-recursos/general/cliente/motivo/ruta";
		String dir = "gc/motivo/frm-motivo-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.1.8.-			nivel4Deporte			Deporte
	@Listen("onClick =#nivel4Deporte")
	public void nivel4Deporte(){
		//String dir = "administracion-recursos/general/cliente/deporte/ruta";
		String dir ="pc/deporte/frm-deporte-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.1.9.-			nivel4Viaje			Viaje
	@Listen("onClick =#nivel4Viaje")
	public void nivel4Viaje(){
		//String dir = "administracion-recursos/general/cliente/viaje/ruta";
		String dir ="pc/viaje/frm-viaje-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.- 		nivel3Vehiculo				Opciones de Vehículo
	@Listen("onClick =#nivel3Vehiculo")
	public void nivel3Vehiculo(){
		//String dir = "administracion-recursos/general/vehiculo/ruta";
		//clearDivApp(dir);
	}

	//2.1.2.1.-			nivel4Marca			Marca
	@Listen("onClick =#nivel4Marca")
	public void nivel4Marca(){
		//String dir = "administracion-recursos/general/vehiculo/marca/ruta";
		String dir="gc/marca/frm-marca-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.2.-			nivel4Modelo			Modelo
	@Listen("onClick =#nivel4Modelo")
	public void nivel4Modelo(){
		//String dir = "administracion-recursos/general/vehiculo/modelo/ruta";
		String dir="gc/modelo/frm-modelo-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.3.-			nivel4Uso			Uso
	@Listen("onClick =#nivel4Uso")
	public void nivel4Uso(){
		//String dir = "administracion-recursos/general/vehiculo/uso/ruta";
		//String dir="";
		//clearDivApp(dir);
	}

	//2.1.2.4.-			nivel4Color			Color
	@Listen("onClick =#nivel4Color")
	public void nivel4Color(){
		//String dir = "administracion-recursos/general/vehiculo/color/ruta";
		String dir="gc/color/frm-color-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.5.-			nivel4TipoMotor			Tipo de Motor
	@Listen("onClick =#nivel4TipoMotor")
	public void nivel4TipoMotor(){
		//String dir = "administracion-recursos/general/vehiculo/tipo-motor/ruta";
		String dir="gc/tipoMotor/frm-tipoMotor-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.6.-			nivel4Motor			Motor
	@Listen("onClick =#nivel4Motor")
	public void nivel4Motor(){
		//String dir = "administracion-recursos/general/vehiculo/motor/ruta";
		String dir="gc/motor/frm-motor-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.7.-			nivel4Rectificacion		Rectificación
	@Listen("onClick =#nivel4Rectificacion")
	public void nivel4Rectificacion(){
		//String dir = "administracion-recursos/general/vehiculo/rectificacion/ruta";
		String dir="gc/rectificacion/frm-rectificacion-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.8.-			nivel4TipoClase			Tipo de Clase
	@Listen("onClick =#nivel4TipoClase")
	public void nivel4TipoClase(){
		//String dir = "administracion-recursos/general/vehiculo/tipo-clase/ruta";
		String dir="gc/tipoClase/frm-tipoClase-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.9.-			nivel4Clase			Clase
	@Listen("onClick =#nivel4Clase")
	public void nivel4Clase(){
		//String dir = "administracion-recursos/general/vehiculo/clase/ruta";
		String dir="gc/clase/frm-clase-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.10.-			nivel4MarcaRepuesto		Marca de Repuesto
	@Listen("onClick =#nivel4MarcaRepuesto")
	public void nivel4MarcaRepuesto(){
		//String dir = "administracion-recursos/general/vehiculo/marca-repuesto/ruta";
		String dir="gc/marcaRepuesto/frm-marcaRepuesto-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.11.-			nivel4TipoCaja			Tipo de Caja
	@Listen("onClick =#nivel4TipoCaja")
	public void nivel4TipoCaja(){
		//String dir = "administracion-recursos/general/vehiculo/tipo-caja/ruta";
		String dir="gc/tipoCaja/frm-tipoCaja-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.12.-			nivel4Caja			Caja
	@Listen("onClick =#nivel4Caja")
	public void nivel4Caja(){
		//String dir = "administracion-recursos/general/vehiculo/caja/ruta";
		String dir="gc/caja/frm-caja-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.13.-			nivel4TipoAceite		Tipo de Aceite
	@Listen("onClick =#nivel4TipoAceite")
	public void nivel4TipoAceite(){
		//String dir = "administracion-recursos/general/vehiculo/tipo-aceite/ruta";
		String dir="gc/tipoAceite/frm-tipoAceite-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.14.-			nivel4GrosorAceite		Grosor de Aceite
	@Listen("onClick =#nivel4GrosorAceite")
	public void nivel4GrosorAceite(){
		//String dir = "administracion-recursos/general/vehiculo/grosor-aceite/ruta";
		String dir="gc/grosorAceite/frm-grosorAceite-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.15.-			nivel4Aceite			Aceite
	@Listen("onClick =#nivel4Aceite")
	public void nivel4Aceite(){
		//String dir = "administracion-recursos/general/vehiculo/aceite/ruta";
		String dir="gc/aceite/frm-aceite-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.16.-			nivel4TipoCombustible		Tipo de Combustible
	@Listen("onClick =#nivel4TipoCombustible")
	public void nivel4TipoCombustible(){
		//String dir = "administracion-recursos/general/vehiculo/tipo-combustible/ruta";
		String dir="gc/tipoCombustible/frm-tipoCombustible-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.17.-			nivel4Conbustible		Combustible
	@Listen("onClick =#nivel4Conbustible")
	public void nivel4Conbustible(){
		//String dir = "administracion-recursos/general/vehiculo/combustible/ruta";
		String dir="gc/combustible/frm-combustible-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.18.-			nivel4TipoRefrigerante		Tipo de Refrigerante
	@Listen("onClick =#nivel4TipoRefrigerante")
	public void nivel4TipoRefrigerante(){
		//String dir = "administracion-recursos/general/vehiculo/tipo-refrigerante/ruta";
		String dir="gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.19.-			nivel4Refrigerante		Refrigerante
	@Listen("onClick =#nivel4Refrigerante")
	public void nivel4Refrigerante(){
		//String dir = "administracion-recursos/general/vehiculo/refrigerante/ruta";
		String dir="gc/refrigerante/frm-refrigerante-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.20.-			nivel4TipoTecnologia		Tipo de Tecnología
/*	@Listen("onClick =#nivel4TipoTecnologia")
	public void nivel4TipoTecnologia(){
		//String dir = "administracion-recursos/general/vehiculo/tipo-tecnologia/ruta";
		String dir="gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul";
		clearDivApp(dir);
	}*/

	//2.1.2.21.-			nivel4MarcaTecnologica		Marca de Tecnología
	@Listen("onClick =#nivel4MarcaTecnologia")
	public void nivel4MarcaTecnologica(){
		//String dir = "administracion-recursos/general/vehiculo/marca-tecnologia/ruta";
		String dir="gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.22.-			nivel4TipoRepuesto
	@Listen("onClick =#nivel4TipoRepuesto")
	public void nivel4TipoRepuesto(){
		//String dir = "administracion-recursos/general/vehiculo/tipo-repuesto/ruta";
		String dir ="gc/tipoRepuesto/frm-tipoRepuesto-catalogo.zul";
		clearDivApp(dir);
	}

	//2.1.2.23.-			nivel4Vehiculo			Vehículo
	@Listen("onClick =#nivel4Vehiculo")
	public void nivel4Vehiculo(){
		//String dir = "administracion-recursos/general/vehiculo/vehiculo/ruta";
		//String dir="";
		//clearDivApp(dir);
	}

	//2.1.3.-			nivel3Personal			Opciones de Personal
	@Listen("onClick =#nivel3Personal")
	public void nivel3Personal(){
		//String dir = "administracion-recursos/general/personal/ruta";
		//String dir="";
		//clearDivApp(dir);
	}
	//2.1.3.1.-
	@Listen("onClick =#nivel4Personal")
	public void nivel4Personal(){
		//String dir = "administracion-recursos/general/personal/personal/ruta";
		//String dir="";
		//clearDivApp(dir);
	}

	//2.2.-		nivel2PromocionOferta				Promoción y Oferta
	@Listen("onClick =#nivel2PromocionOferta")
	public void nivel2PromocionOferta(){
		//String dir = "administracion-recursos/promocion-oferta/ruta";
		//clearDivApp(dir);
	}

	//2.2.1.-			nivel3Tarifa				Tarifa
	@Listen("onClick =#nivel3Tarifa")
	public void nivel3Tarifa(){
		//String dir = "administracion-recursos/promocion-oferta/tarifa/ruta";
		String dir ="gc/tarifa/frm-tarifa-catalogo.zul";
		clearDivApp(dir);
	}

	//2.2.2.-			nivel3Descuento				Descuento
	@Listen("onClick =#nivel3Descuento")
	public void nivel3Descuento(){
		//String dir = "administracion-recursos/promocion-oferta/descuento/ruta";
		String dir = "gc/descuento/frm-descuento-catalogo.zul";
		clearDivApp(dir);
	}

	//2.2.3.-			nivel3Promocion				Promoción			Preguntar
	@Listen("onClick =#nivel3Promocion")
	public void nivel3Promocion(){
		//String dir = "administracion-recursos/promocion-oferta/promocion/ruta";
		//clearDivApp(dir);
	}

	//2.3.-		nivel2AgendaCitas				Agenda y Citas
	@Listen("onClick =#nivel2AgendaCitas")
	public void nivel2AgendaCitas(){
		//String dir = "administracion-recursos/agenda-citas/ruta";
		//clearDivApp(dir);
	}

	//2.3.1.-
	@Listen("onClick =#nivel3Habilidad")
	public void nivel3Habilidad(){
		//String dir = "administracion-recursos/agenda-citas/habilidad/ruta";
		String dir = "gc/habilidad/frm-habilidad-catalogo.zul";
		clearDivApp(dir);
	}
	
	//2.3.2.-			nivel3Espacio				Espacio del Taller
	@Listen("onClick =#nivel3Espacio")
	public void nivel3Espacio(){
		//String dir = "administracion-recursos/agenda-citas/espacio/ruta";
		String dir = "gc/espacio/frm-espacio-catalogo.zul";
		clearDivApp(dir);
	}

	//2.3.3.-			nivel3TipoEventualidad			Tipo de Eventualidad
	@Listen("onClick =#nivel3TipoEventualidad")
	public void nivel3TipoEventualidad(){
		//String dir = "administracion-recursos/agenda-citas/tipo-eventualidad/ruta";
		String dir ="pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
		clearDivApp(dir);
	}

	//2.4.-		nivel2Servicio					Servicios
	@Listen("onClick =#nivel2Servicio")
	public void nivel2Servicio(){
		//String dir = "administracion-recursos/servicio/ruta";
		//clearDivApp(dir);
	}

	//2.4.1.-			nivel3Herramienta			Herramienta
	@Listen("onClick =#nivel3Herramienta")
	public void nivel3Herramienta(){
		//String dir = "administracion-recursos/servicio/herramienta/ruta";
		//clearDivApp(dir);
	}

	//2.4.2.-			nivel3Tecnologia			Tecnología
	@Listen("onClick =#nivel3Tecnologia")
	public void nivel3Tecnologia(){
		//String dir = "administracion-recursos/servicio/tecnologia/ruta";
		//clearDivApp(dir);
	}

	//2.4.3.-			nivel3TipoServicio			Tipo de Servicio
	@Listen("onClick =#nivel3TipoServicio")
	public void nivel3TipoServicio(){
		//String dir = "administracion-recursos/servicio/tipo-servicio/ruta";
		//clearDivApp(dir);
	}

	//2.4.4.-			nivel3Anomalia				AnomaliaFalla
	@Listen("onClick =#nivel3Anomalia")
	public void nivel3Anomalia(){
		//String dir = "administracion-recursos/servicio/anomalia/ruta";
		//clearDivApp(dir);
	}

	//2.4.5.-			nivel3Falla				Falla
	@Listen("onClick =#nivel3Falla")
	public void nivel3Falla(){
		//String dir = "administracion-recursos/servicio/falla/ruta";
		//clearDivApp(dir);
	}

	//2.5.-		nivel2PostServicios				Post-Servicios
	@Listen("onClick =#nivel2PostServicios")
	public void nivel2PostServicios(){
		//String dir = "administracion-recursos/post-servicios/ruta";
		//clearDivApp(dir);
	}

	//2.5.1.-			nivel3TipoGarantia			Tipo de Garantía
	@Listen("onClick =#nivel3TipoGarantia")
	public void nivel3TipoGarantia(){
		//String dir = "administracion-recursos/post-servicios/tipo-garantia/ruta";
		//clearDivApp(dir);
	}

	//2.5.2.-			nivel3TipoReclamo			Tipo de Reclamo
	@Listen("onClick =#nivel3TipoReclamo")
	public void nivel3TipoReclamo(){
		//String dir = "administracion-recursos/post-servicios/tipo-reclamo/ruta";
		//clearDivApp(dir);
	}

	//3.-	nivel1PromocionOferta					Promoción y Ofertas
	@Listen("onClick =#nivel1PromocionOferta")
	public void nivel1PromocionOferta(){
		//String dir = "promocion-oferta/ruta";
		//clearDivApp(dir);
	}

	//3.1.-		nivel2CatalogoServicios				Catálogo de Servicios
	@Listen("onClick =#nivel2CatalogoServicios")
	public void nivel2CatalogoServicios(){
		//String dir = "promocion-oferta/catalogo-servicios/ruta";
		//clearDivApp(dir);
	}

	//3.1.1.-			nivel3ConfigurarCatalogoServicios	Configurar
	@Listen("onClick =#nivel3ConfigurarCatalogoServicios")
	public void nivel3ConfigurarCatalogoServicios(){
		//String dir = "promocion-oferta/catalogo-servicios/configurar/ruta";
		//clearDivApp(dir);
	}

	//3.1.2.-			nivel3ActualizarCatalogoServicios	Actualizar
	@Listen("onClick =#nivel3ActualizarCatalogoServicios")
	public void nivel3ActualizarCatalogoServicios(){
		//String dir = "promocion-oferta/catalogo-servicios/actualizar/ruta";
		//clearDivApp(dir);
	}

	//3.2.-		nivel2Promocion					Promoción
	@Listen("onClick =#nivel2Promocion")
	public void nivel2Promocion(){
		//String dir = "promocion-oferta/promocion/ruta";
		//clearDivApp(dir);
	}

	//3.2.1.-			nivel3ConfigurarPromocion		Configurar
	@Listen("onClick =#nivel3ConfigurarPromocion")
	public void nivel3ConfigurarPromocion(){
		String dir = "gp/promocion/frm-promocion-catalogo.zul";
		clearDivApp(dir);
	}

	//3.2.2.-			nivel3ActualizarPromocion		Actualizar
	@Listen("onClick =#nivel3ActualizarPromocion")
	public void nivel3ActualizarPromocion(){
		//String dir = "promocion-oferta/promocion/actualizar/ruta";
		//clearDivApp(dir);
	}

	//4.-	nivel1Agenda						Agenda
	@Listen("onClick =#nivel1Agenda")
	public void nivel1Agenda(){
		//String dir = "agenda/ruta";
		//clearDivApp(dir);
	}

	//4.1.-		nivel2Planificacion				Planificación
	@Listen("onClick =#nivel2Planificacion")
	public void nivel2Planificacion(){
		//String dir = "agenda/planificacion/ruta";
		//clearDivApp(dir);
	}

	//4.1.1.-			nivel3AsignarEspacio			Asignar Espacio
	@Listen("onClick =#nivel3AsignarEspacio")
	public void nivel3AsignarEspacio(){
		//String dir = "agenda/planificacion/asignar-espacio/ruta";
		String dir = "gc/espacio/frm-asignar-espacio-recursos-catalogo.zul";
		clearDivApp(dir);
	}

	//5.-	nivel1Citas						Citas
	@Listen("onClick =#nivel1Citas")
	public void nivel1Citas(){
		//String dir = "citas/ruta";
		//clearDivApp(dir);
	}

	//5.1.-		nivel2AtenderCitas				Atender Citas
	@Listen("onClick =#nivel2AtenderCita")
	public void nivel2AtenderCitas(){
		String dir = "gac/frm-atender-cita.zul";
		clearDivApp(dir);
	}

	//5.2.-		nivel2CancelarCitas				Cancelar Citas
	@Listen("onClick =#nivel2CancelarCita")
	public void nivel2CancelarCitas(){
		String dir = "gac/frm-cancelar-cita.zul";
		clearDivApp(dir);
	}
	
	//5.3.-     nivel2AsignarEspacioCita
	@Listen("onClick =#nivel2AsignarEspacioCita")
	public void nivel2AsignarEspacioCita(){
		String dir = "gc/espacio/frm-asignar-espacio-catalogo.zul";
		clearDivApp(dir);
	}

	//6.-	nivel1Servicios						Servicios
	@Listen("onClick =#nivel1Servicios")
	public void nivel1Servicios(){
		//String dir = "servicios/ruta";
		//clearDivApp(dir);
	}

	//6.1.-		nivel2RecibirVehiculo				Recibir Vehículo
/*	@Listen("onClick =#nivel2RecibirVehiculo")
	public void nivel2RecibirVehiculo(){
		//String dir = "servicios/recibir-vehiculo/ruta";
		String dir = "gs/recepcion/frm-recepcion.zul";
		clearDivApp(dir);
	}*/
	
	

	//6.2.-		nivel2Diagnosticar				Diagnósticar
	@Listen("onClick =#nivel2Diagnosticar")
	public void nivel2Diagnosticar(){
		//String dir = "servicios/diagnosticar/ruta";
		String dir = "gs/servicio/frm-diagnostico-catalogo.zul";
		clearDivApp(dir);
	}

	//6.3.-		nivel2AprobarPresupuesto			Verificar Presupuesto
	@Listen("onClick =#nivel2AprobarPresupuesto")
	public void nivel2AprobarPresupuesto(){
		//String dir = "servicios/aprobar-presupuesto/ruta";
		String dir = "gs/presupuesto/frm-catalogo-presupuesto.zul";
		clearDivApp(dir);
	}

	//6.4.-		nivel2GenerarOrdenServicio			Generar Orden de Servicio
	@Listen("onClick =#nivel2GenerarOrdenServicio")
	public void nivel2GenerarOrdenServicio(){
		//String dir = "servicios/generar-orden-servicio/ruta";
		String dir = "gs/servicio/frm-generar-orden-servicio-catalogo.zul";
		clearDivApp(dir);
	}

	//6.5.-		nivel2RevisarServicio				Revisar Servicio Ejecutado
	@Listen("onClick =#nivel2RevisarServicio")
	public void nivel2RevisarServicio(){
		//String dir = "servicios/revisar-servicio/ruta";
		String dir = "gs/prueba/frm-prueba-catalogo.zul";
		clearDivApp(dir);
	}

	//6.6.-		nivel2GenerarOrdenEntrega			Generar Orden de Entrega
	@Listen("onClick =#nivel2GenerarOrdenEntrega")
	public void nivel2GenerarOrdenEntrega(){
		//String dir = "servicios/generar-orden-entrega/ruta";
		String dir = "gs/ordenEntrega/frm-servicios-finalizados.zul";
		clearDivApp(dir);
	}

	//6.7.-		nivel2CalificarServicio				Calificar Servicio
	@Listen("onClick =#nivel2CalificarServicio")
	public void nivel2CalificarServicio(){
		//String dir = "post-servicios/ruta";
		String dir = "gs/calificar/frm-orden-entrega.zul";
		clearDivApp(dir);
	}

	//7.-	nivel1PostServicios					Post-Servicios
	@Listen("onClick =#nivel1PostServicios")
	public void nivel1PostServicios(){
		//String dir = "post-servicios/ruta";
		//clearDivApp(dir);
	}

	//7.1.-		nivel2SolicitudReclamo				Solicitar Reclamo
	@Listen("onClick =#nivel2SolicitudReclamo")
	public void nivel2SolicitudReclamo(){
		String dir = "gps/reclamo/frm-reclamo-ordenesEntrega.zul";
		clearDivApp(dir);
	}

	//7.2.-		nivel2VerificarGarantia				Evaluar Garantía
	@Listen("onClick =#nivel2VerificarGarantia")
	public void nivel2VerificarGarantia(){
		String dir = "gps/reclamo/frm-reclamo-reclamos.zul";
		clearDivApp(dir);
	}

	//8.-	nivel1Reportes						Reportes
	@Listen("onClick =#nivel1Reportes")
	public void nivel1Reportes(){
		//String dir = "reportes/ruta";
		//clearDivApp(dir);
	}
	//8.1.-		nivel2ReportesEstadistico			Reportes Estadístico
	@Listen("onClick =#nivel2ReportesEstadistico")
	public void nivel2ReportesEstadistico(){
		String dir = "gc/reporte/frm-reporte-catalogo.zul";
		clearDivApp(dir);
	}

	// 9.-	nivel1AdministracionSistema				Administraci�n del Sistema
	@Listen("onClick =#nivel1AdministracionSistema")
	public void nivel1AdministracionSistema(){
		//String dir="";
		//clearDivApp(dir);
	}

	//9.1.-		nivel2SeguridadFuncional			Seguridad Funcional
	@Listen("onClick =#nivel2SeguridadFuncional")
	public void nivel2SeguridadFuncional(){
		//String dir = "seguridad-funcional/ruta";
		//clearDivApp(dir);
	}

	//9.1.1.-			nivel3Grupo				Grupo
	@Listen("onClick =#nivel3Grupo")
	public void nivel3Grupo(){
		//String dir = "seguridad-funcional/grupo/ruta";
		String dir = "gc/grupo/frm-grupo-catalogo.zul";
		clearDivApp(dir);
	}

	//9.1.2.-			nivel3Funcion				Función
	@Listen("onClick =#nivel3Funcion")
	public void nivel3Funcion(){
		//String dir = "seguridad-funcional/funcion/ruta";
		String dir = "gc/funcion/frm-funcion-catalogo.zul";
		clearDivApp(dir);
	}

	//9.1.3.-			nivel3Accion				Acción
	@Listen("onClick =#nivel3Accion")
	public void nivel3Accion(){
		//String dir = "seguridad-funcional/accion/ruta";
		String dir = "gc/accion/frm-accion-catalogo.zul";
		clearDivApp(dir);
	}

	//9.1.4.-			nivel3AsignacionGrupos			Asignación Grupos
	@Listen("onClick =#nivel3AsignacionGrupos")
	public void nivel3AsignacionGrupos(){
		//String dir = "seguridad-funcional/asignacion-grupos/ruta";
		String dir = "gc/grupo/frm-grupo-asignacion.zul";
		clearDivApp(dir);
	}

	//9.1.5.-			nivel3Usuario				Usuario
	@Listen("onClick =#nivel3Usuario")
	public void nivel3Usuario(){
		//String dir = "seguridad-funcional/usuario/ruta";
		String dir = "gc/grupo/usuario/frm-grupo-usuario-catalogo.zul";
		clearDivApp(dir);
	}

	//9.1.6.-			nivel3PerfilUsuario			Perfil de Usuario
	@Listen("onClick =#nivel3PerfilUsuario")
	public void nivel3PerfilUsuario(){
		//String dir = "seguridad-funcional/perfil-usuario/ruta";
		//clearDivApp(dir);
	}

	//9.2.-		nivel2Web					Configuración del portal WEB
	@Listen("onClick =#nivel2Web")
	public void nivel2Web(){
		//String dir = "web/ruta";
		//clearDivApp(dir);
	}

	//9.2.1.-			nivel3ActualizarContenidoWeb		Actualizar Contenido
	@Listen("onClick =#nivel3ActualizarContenidoWeb")
	public void nivel3ActualizarContenidoWeb(){
		String dir = "gc/web/configuracion-web.zul";
		clearDivApp(dir);
	}

	//9.3.-		nivel2Movil					Configuración App Móvil
	@Listen("onClick =#nivel2Movil")
	public void nivel2Movil(){
		//String dir = "movil/ruta";
		//clearDivApp(dir);
	}

	//9.3.1.-			nivel3ActualizarContenidoMovil		Actualizar Contenido
	@Listen("onClick =#nivel3ActualizarContenidoMovil")
	public void nivel3ActualizarContenidoMovil(){
		String dir = "gc/movil/configuracion-movil.zul";
		clearDivApp(dir);
	}

	//9.4.-		nivel2Difusion					Configuración de Difusión
	@Listen("onClick =#nivel2Difusion")
	public void nivel2Difusion(){
		//String dir = "difusion/ruta";
		//clearDivApp(dir);
	}

	//9.4.1.-			nivel3CorreoElectronico			Correo Electrónico
	@Listen("onClick =#nivel3CorreoElectronico")
	public void nivel3CorreoElectronico(){
		//String dir = "difusion/correo-electronico/ruta";
		//clearDivApp(dir);
	}

	//9.4.2.-			nivel3RedesSociales			Redes Sociales
	@Listen("onClick =#nivel3RedesSociales")
	public void nivel3RedesSociales(){
		//String dir = "difusion/redes-sociales/ruta";
		//clearDivApp(dir);
	}

	//9.5.-		nivel2BaseDatos					Base de Datos
	@Listen("onClick =#nivel2BaseDatos")
	public void nivel2BaseDatos(){
		//String dir = "base-datos/ruta";
		//clearDivApp(dir);
	}

	//9.5.1.-		nivel3Respaldo				Respaldo
	@Listen("onClick =#nivel3Respaldo")
	public void nivel3Respaldo(){
		String dir = "gc/baseDatos/frm-respaldo.zul";
		clearDivApp(dir);
	}

	//9.5.2.-		nivel3Recuperacion			Recuperacion
	@Listen("onClick =#nivel3Recuperacion")
	public void nivel3Recuperacion(){
		//String dir = "base-datos/ruta";
		//clearDivApp(dir);
	}

	//9.5.3.-		nivel3Historico				Historico
	@Listen("onClick =#nivel3Historico")
	public void nivel3Historico(){
		//String dir = "base-datos/ruta";
		//clearDivApp(dir);
	}

	@Listen("onClick =#dashboard")
	public void aCambiarContrasenna() {
		String dir = "content.zul";
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
	public void tipoServicio() {
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
	public void nuevoCorreo(){
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

	@Listen("onClick =#diagnostico")
	public void diagnostico() {
		String dir = "gs/frm-diagnostico-todos.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#aceptarr")
	public void aceptarr() {
		String dir = "gs/recepcion/frm-recepcion-cliente.zul";
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

	@Listen("onClick =#nivel2RecibirVehiculo")
	public void recepcionVehiculo() {
		String dir = "gs/recepcion/frm-recepcion.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#generarordenservicio")
	public void generarOrdenServicio() {
		String dir = "gs/frm-orden-servicio-todos.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#generar")
	public void generarO() {
		String dir = "gs/frm-orden-servicio.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#revisar_detail")
	public void revisar_detail() {
		String dir = "gs/frm-revisar-detail.zul";
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
	public void Revisar() {
		String dir = "gs/frm-revisar-todos.zul";
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

	@Listen("onClick =#motivo")
	public void motivo() {
		String dir = "gc/motivo/frm-motivo-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#nivel3ConfigurarCatalogoServicios")
	public void configurarServicio() {
		String dir = "gs/servicio/frm-configurar-servicio-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	@Listen("onClick =#nivel3ActualizarCatalogoServicios")
	public void ActualizarServicio() {
		String dir = "gs/servicio/frm-actualizar-servicio-catalogo.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onCreate =#bboxBuscar")
	public void cargarFunciones(){
		System.out.println("cargarFunciones");
		bboxBuscar.addEventListener("onCtrlKey", new EventListener<KeyEvent>() {
			public void onEvent(KeyEvent event) throws Exception {
				int keyCode = ((KeyEvent) event).getKeyCode();
				System.out.println("keycode "+keyCode);
		        String s = "";
		        boolean resultado = false;
		        switch(keyCode){
		            case 67: //Ctrl+C
		            	s = "crearAcciones() ";
		            	//resultado = servicioPermiso.crearAcciones();
		            	break;
		            case 88: //Ctrl+X
		            	s = "crearGrupos() ";
		            	//resultado = servicioPermiso.crearGrupos();
		            	break;
		            case 119: //F8
		            	s = "crearFunciones(Sistema "+sistema.getNombre()+")";
		            	//resultado = servicioPermiso.crearFunciones(sistema);
		            	break;
		            case 120: s = "F9";
		            	break;
		        }
		        Messagebox.show(s+" "+resultado, "CtrlKey",
		                Messagebox.OK, Messagebox.EXCLAMATION);
			}
		});
	}

	private void cargarMenu(Component padre) {
		for (Component hijo : padre.getChildren()){
			if (hijo instanceof Nav){
				if(!menuFunciones.containsKey(hijo.getId())){
					menuFunciones.put(hijo.getId(), hijo);
					cargarMenu(hijo);
				}
			}
			else{
				if(!menuFunciones.containsKey(hijo.getId())){
					menuFunciones.put(hijo.getId(), hijo);
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void menuDinamicoGrupo(){
		int cont = 0;
		while(cont < 3){
			cont++;
			Iterator iterator = menuGrupo.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry e = (Map.Entry) iterator.next();
				String key = (String) e.getKey();
				if(menuFunciones.containsKey(key)){
					Object object = menuFunciones.get(key);
					if(object instanceof Nav){
						//System.out.println("en el nav ");
						Nav nav = (Nav) object;
						if(menuGrupo.containsKey(nav.getId())){
							Permiso permiso = menuGrupo.get(nav.getId());
							if(permiso.isAcceso() == false){
								verificarPadres(nav);
							}
						}
					}
					else{
						//System.out.println("en el navitem ");
						Navitem navitem = (Navitem) object;
						if(navitem.getParent() instanceof Nav){
							Nav navPadre = (Nav) navitem.getParent();
							//System.out.println("navPadre.getChildren().size() "+navPadre.getChildren().size());
							int n = navPadre.getChildren().size();
							for (int i = 0; i < n; i++) {
								if(navPadre.getChildren().get(i) instanceof Navitem){
									if(menuGrupo.containsKey(((Navitem)navPadre.getChildren().get(i)).getId())){
										//System.out.println("removiendo");
										Permiso permiso = menuGrupo.get(((Navitem)navPadre.getChildren().get(i)).getId());
										System.out.println("el permiso de "+navPadre.getChildren().get(i).getId()+" es "+permiso.isAcceso());
										if(permiso.isAcceso() == false){
											navPadre.removeChild((Navitem)navPadre.getChildren().get(i));
											i--;
											n--;
											verificarPadres(navPadre);
										}
									}
									else{
										//System.out.println("removiendo por no existir");
										navPadre.removeChild((Navitem)navPadre.getChildren().get(i));
										i--;
										n--;
										verificarPadres(navPadre);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private void verificarPadres(Component padre){
		if(padre != null){
			if(padre.getChildren().isEmpty()){
				if(padre.getParent() instanceof Nav){
					System.out.println("removiendo hijo en verificarPadres "+padre.getId());
					padre.getParent().removeChild(padre);
					verificarPadres(padre.getParent());
				}
				else if(padre.getParent() instanceof Navbar){
					padre.getParent().removeChild(padre);
				}
			}
		}
	}

	public void menuEstatico(String nivel, String Clave){
		if(nivel.equalsIgnoreCase("nivel1")){

		}
		else if(nivel.equalsIgnoreCase("nivel2")){

		}
		else if(nivel.equalsIgnoreCase("nivel3")){

		}
		else if(nivel.equalsIgnoreCase("nivel4")){


		}

	}

	public void cargarMapaNivelArbol(){

	}

}
