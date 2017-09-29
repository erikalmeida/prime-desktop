package ucla.si.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.AccionDAO;
import ucla.si.dao.FuncionDAO;
import ucla.si.dao.GrupoDAO;
import ucla.si.dao.PermisoDAO;
import ucla.si.modelo.Accion;
import ucla.si.modelo.Funcion;
import ucla.si.modelo.Grupo;
import ucla.si.modelo.Permiso;
import ucla.si.modelo.Sistema;

@Service("servicioPermiso")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioPermiso {

	@Autowired
	private AccionDAO accionDAO;

	@Autowired
	private GrupoDAO grupoDAO;

	@Autowired
	private FuncionDAO funcionDAO;

	@Autowired
	private PermisoDAO permisoDAO;

	public boolean incluirPermiso(Permiso permiso){
		boolean guardado = false;
		try {
			guardado = permisoDAO.incluirPermiso(permiso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public boolean editarPermiso(Permiso permiso){
		boolean guardado = false;
		try {
			guardado = permisoDAO.editarPermiso(permiso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public List<Permiso> listarPermisos(Grupo grupo){
		List<Permiso> permisos = new ArrayList<Permiso>();
		try {
			permisos = permisoDAO.listarPermisos(grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permisos;
	}

	public Map<String,Permiso> arbolGrupo(Grupo grupo){
		Map<String,Permiso> arbolGrupo = new HashMap<String, Permiso>();
		try {
			arbolGrupo = permisoDAO.arbolGrupo(grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arbolGrupo;
	}

	public Map<String,Permiso> menuGrupo(Grupo grupo){
		Map<String,Permiso> menuGrupo = new HashMap<String, Permiso>();
		try {
			menuGrupo = permisoDAO.menuGrupo(grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuGrupo;
	}

	public boolean crearAcciones() {
		boolean resultado = false;
		int contador = 0;
		ArrayList<Accion> acciones = new ArrayList<Accion>();
		acciones.add(new Accion("INCLUIR", "Activo", new Date(), null));
		acciones.add(new Accion("CONSULTAR", "Activo", new Date(), null));
		acciones.add(new Accion("EDITAR", "Activo", new Date(), null));
		acciones.add(new Accion("ELIMINAR", "Activo", new Date(), null));
		acciones.add(new Accion("DESPLEGAR", "Activo", new Date(), null));
		acciones.add(new Accion("LISTAR", "Activo", new Date(), null));
		for (Accion accion : acciones) {
			if (accionDAO.buscarAccion(accion.getNombre()) == null) {
				resultado = accionDAO.incluirAccion(accion);
				if (resultado) {
					contador++;
				} else {
					resultado = false;
					break;
				}
			}
		}
		System.out.println("contador " + contador);
		return resultado;
	}

	public boolean crearGrupos() {
		boolean resultado = false;
		int contador = 0;
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		grupos.add(new Grupo("ADMINISTRADOR", "ADMINISTRADOR", "Activo", new Date(), null));
		grupos.add(new Grupo("CLIENTE", "CLIENTE", "Activo", new Date(), null));
		grupos.add(new Grupo("MECANICO", "MECANICO", "Activo", new Date(), null));
		grupos.add(new Grupo("JEFE MECANICO", "JEFE MECANICO", "Activo", new Date(), null));
		grupos.add(new Grupo("SECRETARIA", "SECRETARIA", "Activo", new Date(), null));
		grupos.add(new Grupo("DEFAULT", "DEFAULT", "Activo", new Date(), null));
		for (Grupo grupo : grupos) {
			if (grupoDAO.buscarGrupo(grupo.getNombre()) == null) {
				resultado = grupoDAO.incluirGrupo(grupo);
				if (resultado) {
					contador++;
				} else {
					resultado = false;
					break;
				}
			}
		}
		System.out.println("contador " + contador);
		return resultado;
	}

	public boolean crearFunciones(Sistema sistema) {
		boolean resultado = false;
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		// 1.-
		Funcion nivel1Inicio = new Funcion("nivel1Inicio", "Inicio", "Inicio", "", "prime.zul", "Activo", new Date(), null);
		nivel1Inicio.setSistema(sistema);
		nivel1Inicio = funcionDAO.incluir(nivel1Inicio);
		// 2.-
		Funcion nivel1AdministracionRecursos = new Funcion("nivel1AdministracionRecursos", "Administracion de Recursos", "", "", null, "Activo", new Date(), null);
		nivel1AdministracionRecursos.setSistema(sistema);
		nivel1AdministracionRecursos = funcionDAO.incluir(nivel1AdministracionRecursos);
		// 2.1.-
		Funcion nivel2General = new Funcion("nivel2General", "General", "", "", null, "Activo", new Date(), null);
		nivel2General.setSistema(sistema);
		nivel2General.setFuncionPadre(nivel1AdministracionRecursos);
		nivel2General = funcionDAO.incluir(nivel2General);
		// 2.1.1.-
		Funcion nivel3Cliente = new Funcion("nivel3Cliente", "Opciones de Cliente", "", "", null, "Activo", new Date(), null);
		nivel3Cliente.setSistema(sistema);
		nivel3Cliente.setFuncionPadre(nivel2General);
		nivel3Cliente = funcionDAO.incluir(nivel3Cliente);
		// 2.1.1.1.-
		Funcion nivel4Ocupacion = new Funcion("nivel4Ocupacion", "Ocupación", "", "", "pc/ocupacion/frm-ocupacion-catalogo.zul", "Activo", new Date(), null);
		nivel4Ocupacion.setSistema(sistema);
		nivel4Ocupacion.setFuncionPadre(nivel3Cliente);
		nivel4Ocupacion = funcionDAO.incluir(nivel4Ocupacion);
		// 2.1.1.2.-
		Funcion nivel4Profesion = new Funcion("nivel4Profesion", "Profesión", "", "", "pc/profesion/frm-profesion-catalogo.zul", "Activo", new Date(), null);
		nivel4Profesion.setSistema(sistema);
		nivel4Profesion.setFuncionPadre(nivel3Cliente);
		nivel4Profesion = funcionDAO.incluir(nivel4Profesion);
		// 2.1.1.3.-
		Funcion nivel4Pasatiempo = new Funcion("nivel4Pasatiempo", "Pasatiempo", "", "", "pc/pasatiempo/frm-pasatiempo-catalogo.zul", "Activo", new Date(), null);
		nivel4Pasatiempo.setSistema(sistema);
		nivel4Pasatiempo.setFuncionPadre(nivel3Cliente);
		nivel4Pasatiempo = funcionDAO.incluir(nivel4Pasatiempo);
		// 2.1.1.4.-
		Funcion nivel4Ciudad = new Funcion("nivel4Ciudad", "Ciudad", "", "", "gc/ciudad/frm-ciudad-catalogo.zul", "Activo", new Date(), null);
		nivel4Ciudad.setSistema(sistema);
		nivel4Ciudad.setFuncionPadre(nivel3Cliente);
		nivel4Ciudad = funcionDAO.incluir(nivel4Ciudad);
		// 2.1.1.5.-
		Funcion nivel4Estado = new Funcion("nivel4Estado", "Estado", "", "", "gc/estado/frm-estado-catalogo.zul", "Activo", new Date(), null);
		nivel4Estado.setSistema(sistema);
		nivel4Estado.setFuncionPadre(nivel3Cliente);
		nivel4Estado = funcionDAO.incluir(nivel4Estado);
		// 2.1.1.6.-
		Funcion nivel4Cliente = new Funcion("nivel4Cliente", "Cliente", "", "", "gs/recepcion/frm-recepcion-cliente.zul", "Activo", new Date(), null);
		nivel4Cliente.setSistema(sistema);
		nivel4Cliente.setFuncionPadre(nivel3Cliente);
		nivel4Cliente = funcionDAO.incluir(nivel4Cliente);
		// 2.1.1.7.-
		Funcion nivel4Motivo = new Funcion("nivel4Motivo", "Motivo", "", "", "gc/motivo/frm-motivo-catalogo.zul", "Activo", new Date(), null);
		nivel4Motivo.setSistema(sistema);
		nivel4Motivo.setFuncionPadre(nivel3Cliente);
		nivel4Motivo = funcionDAO.incluir(nivel4Motivo);
		// 2.1.1.8.-
		Funcion nivel4Deporte = new Funcion("nivel4Deporte", "Deporte", "", "", "pc/deporte/frm-deporte-catalogo.zul", "Activo", new Date(), null);
		nivel4Deporte.setSistema(sistema);
		nivel4Deporte.setFuncionPadre(nivel3Cliente);
		nivel4Deporte = funcionDAO.incluir(nivel4Deporte);
		// 2.1.1.9.-
		Funcion nivel4Viaje = new Funcion("nivel4Viaje", "Viaje", "", "", "pc/viaje/frm-viaje-catalogo.zul", "Activo", new Date(), null);
		nivel4Viaje.setSistema(sistema);
		nivel4Viaje.setFuncionPadre(nivel3Cliente);
		nivel4Viaje = funcionDAO.incluir(nivel4Viaje);
		// 2.1.2.-
		Funcion nivel3Vehiculo = new Funcion("nivel3Vehiculo", "Opciones de Vehículo", "", "", null, "Activo", new Date(), null);
		nivel3Vehiculo.setSistema(sistema);
		nivel3Vehiculo.setFuncionPadre(nivel2General);
		nivel3Vehiculo = funcionDAO.incluir(nivel3Vehiculo);
		// 2.1.2.1.-
		Funcion nivel4Marca = new Funcion("nivel4Marca", "Marca", "", "", "gc/marca/frm-marca-catalogo.zul", "Activo", new Date(), null);
		nivel4Marca.setSistema(sistema);
		nivel4Marca.setFuncionPadre(nivel3Vehiculo);
		nivel4Marca = funcionDAO.incluir(nivel4Marca);
		// 2.1.2.2.-
		Funcion nivel4Modelo = new Funcion("nivel4Modelo", "Modelo", "", "", "gc/modelo/frm-modelo-catalogo.zul", "Activo", new Date(), null);
		nivel4Modelo.setSistema(sistema);
		nivel4Modelo.setFuncionPadre(nivel3Vehiculo);
		nivel4Modelo = funcionDAO.incluir(nivel4Modelo);
		// 2.1.2.3.-
		Funcion nivel4Uso = new Funcion("nivel4Uso", "Uso", "", "", null, "Activo", new Date(), null);
		nivel4Uso.setSistema(sistema);
		nivel4Uso.setFuncionPadre(nivel3Vehiculo);
		nivel4Uso = funcionDAO.incluir(nivel4Uso);
		// 2.1.2.4.-
		Funcion nivel4Color = new Funcion("nivel4Color", "Color", "", "", "gc/color/frm-color-catalogo.zul", "Activo", new Date(), null);
		nivel4Color.setSistema(sistema);
		nivel4Color.setFuncionPadre(nivel3Vehiculo);
		nivel4Color = funcionDAO.incluir(nivel4Color);
		// 2.1.2.5.-
		Funcion nivel4TipoMotor = new Funcion("nivel4TipoMotor", "Tipo de Motor", "", "", "gc/tipoMotor/frm-tipoMotor-catalogo.zul", "Activo", new Date(), null);
		nivel4TipoMotor.setSistema(sistema);
		nivel4TipoMotor.setFuncionPadre(nivel3Vehiculo);
		nivel4TipoMotor = funcionDAO.incluir(nivel4TipoMotor);
		// 2.1.2.6.-
		Funcion nivel4Motor = new Funcion("nivel4Motor", "Motor", "", "", "gc/motor/frm-motor-catalogo.zul", "Activo", new Date(), null);
		nivel4Motor.setSistema(sistema);
		nivel4Motor.setFuncionPadre(nivel3Vehiculo);
		nivel4Motor = funcionDAO.incluir(nivel4Motor);
		// 2.1.2.7.-
		Funcion nivel4Rectificacion = new Funcion("nivel4Rectificacion", "Rectificación", "", "", "gc/rectificacion/frm-rectificacion-catalogo.zul", "Activo", new Date(), null);
		nivel4Rectificacion.setSistema(sistema);
		nivel4Rectificacion.setFuncionPadre(nivel3Vehiculo);
		nivel4Rectificacion = funcionDAO.incluir(nivel4Rectificacion);
		// 2.1.2.8.-
		Funcion nivel4TipoClase = new Funcion("nivel4TipoClase", "Tipo de Clase", "", "", "gc/tipoClase/frm-tipoClase-catalogo.zul", "Activo", new Date(), null);
		nivel4TipoClase.setSistema(sistema);
		nivel4TipoClase.setFuncionPadre(nivel3Vehiculo);
		nivel4TipoClase = funcionDAO.incluir(nivel4TipoClase);
		// 2.1.2.9.-
		Funcion nivel4Clase = new Funcion("nivel4Clase", "Clase", "", "", "gc/clase/frm-clase-catalogo.zul", "Activo", new Date(), null);
		nivel4Clase.setSistema(sistema);
		nivel4Clase.setFuncionPadre(nivel3Vehiculo);
		nivel4Clase = funcionDAO.incluir(nivel4Clase);
		// 2.1.2.10.-
		Funcion nivel4MarcaRepuesto = new Funcion("nivel4MarcaRepuesto", "Marca de Repuesto", "", "", "gc/marcaRepuesto/frm-marcaRepuesto-catalogo.zul", "Activo", new Date(), null);
		nivel4MarcaRepuesto.setSistema(sistema);
		nivel4MarcaRepuesto.setFuncionPadre(nivel3Vehiculo);
		nivel4MarcaRepuesto = funcionDAO.incluir(nivel4MarcaRepuesto);
		// 2.1.2.11.-
		Funcion nivel4TipoCaja = new Funcion("nivel4TipoCaja", "Tipo de Caja", "", "", "gc/tipoCaja/frm-tipoCaja-catalogo.zul", "Activo", new Date(), null);
		nivel4TipoCaja.setSistema(sistema);
		nivel4TipoCaja.setFuncionPadre(nivel3Vehiculo);
		nivel4TipoCaja = funcionDAO.incluir(nivel4TipoCaja);
		// 2.1.2.12.-
		Funcion nivel4Caja = new Funcion("nivel4Caja", "Caja", "", "", "gc/caja/frm-caja-catalogo.zul", "Activo", new Date(), null);
		nivel4Caja.setSistema(sistema);
		nivel4Caja.setFuncionPadre(nivel3Vehiculo);
		nivel4Caja = funcionDAO.incluir(nivel4Caja);
		// 2.1.2.13.-
		Funcion nivel4TipoAceite = new Funcion("nivel4TipoAceite", "Tipo de Aceite", "", "", "gc/tipoAceite/frm-tipoAceite-catalogo.zul", "Activo", new Date(), null);
		nivel4TipoAceite.setSistema(sistema);
		nivel4TipoAceite.setFuncionPadre(nivel3Vehiculo);
		nivel4TipoAceite = funcionDAO.incluir(nivel4TipoAceite);
		// 2.1.2.14.-
		Funcion nivel4GrosorAceite = new Funcion("nivel4GrosorAceite", "Grosor de Aceite", "", "", "gc/grosorAceite/frm-grosorAceite-catalogo.zul", "Activo", new Date(), null);
		nivel4GrosorAceite.setSistema(sistema);
		nivel4GrosorAceite.setFuncionPadre(nivel3Vehiculo);
		nivel4GrosorAceite = funcionDAO.incluir(nivel4GrosorAceite);
		// 2.1.2.15.-
		Funcion nivel4Aceite = new Funcion("nivel4Aceite", "Aceite", "", "", "gc/aceite/frm-aceite-catalogo.zul", "Activo", new Date(), null);
		nivel4Aceite.setSistema(sistema);
		nivel4Aceite.setFuncionPadre(nivel3Vehiculo);
		nivel4Aceite = funcionDAO.incluir(nivel4Aceite);
		// 2.1.2.16.-
		Funcion nivel4TipoCombustible = new Funcion("nivel4TipoCombustible", "Tipo de Combustible", "", "", "gc/tipoCombustible/frm-tipoCombustible-catalogo.zul", "Activo", new Date(), null);
		nivel4TipoCombustible.setSistema(sistema);
		nivel4TipoCombustible.setFuncionPadre(nivel3Vehiculo);
		nivel4TipoCombustible = funcionDAO.incluir(nivel4TipoCombustible);
		// 2.1.2.17.-
		Funcion nivel4Conbustible = new Funcion("nivel4Conbustible", "Combustible", "", "", "gc/combustible/frm-combustible-catalogo.zul", "Activo", new Date(), null);
		nivel4Conbustible.setSistema(sistema);
		nivel4Conbustible.setFuncionPadre(nivel3Vehiculo);
		nivel4Conbustible = funcionDAO.incluir(nivel4Conbustible);
		// 2.1.2.18.-
		Funcion nivel4TipoRefrigerante = new Funcion("nivel4TipoRefrigerante", "Tipo Refrigerante", "", "", "gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul", "Activo", new Date(), null);
		nivel4TipoRefrigerante.setSistema(sistema);
		nivel4TipoRefrigerante.setFuncionPadre(nivel3Vehiculo);
		nivel4TipoRefrigerante = funcionDAO.incluir(nivel4TipoRefrigerante);
		// 2.1.2.19.-
		Funcion nivel4Refrigerante = new Funcion("nivel4Refrigerante", "Refrigerante", "", "", "gc/refrigerante/frm-refrigerante-catalogo.zul", "Activo", new Date(), null);
		nivel4Refrigerante.setSistema(sistema);
		nivel4Refrigerante.setFuncionPadre(nivel3Vehiculo);
		nivel4Refrigerante = funcionDAO.incluir(nivel4Refrigerante);
		// 2.1.2.20.-
		Funcion nivel4TipoTecnologia = new Funcion("nivel4TipoTecnologia", "Tipo de Tecnología", "", "", "gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul", "Activo", new Date(), null);
		nivel4TipoTecnologia.setSistema(sistema);
		nivel4TipoTecnologia.setFuncionPadre(nivel3Vehiculo);
		nivel4TipoTecnologia = funcionDAO.incluir(nivel4TipoTecnologia);
		// 2.1.2.21.-
		Funcion nivel4MarcaTecnologia = new Funcion("nivel4MarcaTecnologia", "Marca de Tecnología", "", "", "gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul", "Activo", new Date(), null);
		nivel4MarcaTecnologia.setSistema(sistema);
		nivel4MarcaTecnologia.setFuncionPadre(nivel3Vehiculo);
		nivel4MarcaTecnologia = funcionDAO.incluir(nivel4MarcaTecnologia);
		// 2.1.2.22.-
		Funcion nivel4TipoRepuesto = new Funcion("nivel4TipoRepuesto", "Tipo de Repuesto", "", "", "gc/tipoRepuesto/frm-tipoRepuesto-catalogo.zul", "Activo", new Date(), null);
		nivel4TipoRepuesto.setSistema(sistema);
		nivel4TipoRepuesto.setFuncionPadre(nivel3Vehiculo);
		nivel4TipoRepuesto = funcionDAO.incluir(nivel4TipoRepuesto);
		// 2.1.2.23.-
		Funcion nivel4Vehiculo = new Funcion("nivel4Vehiculo", "Vehículo", "", "", null, "Activo", new Date(), null);
		nivel4Vehiculo.setSistema(sistema);
		nivel4Vehiculo.setFuncionPadre(nivel3Vehiculo);
		nivel4Vehiculo = funcionDAO.incluir(nivel4Vehiculo);
		// 2.1.3.-
		Funcion nivel3Personal = new Funcion("nivel3Personal", "Opciones de Personal", "", "", null, "Activo", new Date(), null);
		nivel3Personal.setSistema(sistema);
		nivel3Personal.setFuncionPadre(nivel2General);
		nivel3Personal = funcionDAO.incluir(nivel3Personal);
		// 2.1.3.1.-
		Funcion nivel4Personal = new Funcion("nivel4Personal", "Personal", "", "", null, "Activo", new Date(), null);
		nivel4Personal.setSistema(sistema);
		nivel4Personal.setFuncionPadre(nivel3Personal);
		nivel4Personal = funcionDAO.incluir(nivel4Personal);
		// 2.2.-
		Funcion nivel2PromocionOferta = new Funcion("nivel2PromocionOferta", "Promoción y Oferta", "", "", null, "Activo", new Date(), null);
		nivel2PromocionOferta.setSistema(sistema);
		nivel2PromocionOferta.setFuncionPadre(nivel1AdministracionRecursos);
		nivel2PromocionOferta = funcionDAO.incluir(nivel2PromocionOferta);
		// 2.2.1.-
		Funcion nivel3Tarifa = new Funcion("nivel3Tarifa", "Tarifa", "", "", "gc/tarifa/frm-tarifa-catalogo.zul", "Activo", new Date(), null);
		nivel3Tarifa.setSistema(sistema);
		nivel3Tarifa.setFuncionPadre(nivel2PromocionOferta);
		nivel3Tarifa = funcionDAO.incluir(nivel3Tarifa);
		// 2.2.2.-
		Funcion nivel3Descuento = new Funcion("nivel3Descuento", "Descuento", "", "", "gc/descuento/frm-descuento-catalogo.zul", "Activo", new Date(), null);
		nivel3Descuento.setSistema(sistema);
		nivel3Descuento.setFuncionPadre(nivel2PromocionOferta);
		nivel3Descuento = funcionDAO.incluir(nivel3Descuento);
		// 2.2.3.-
		Funcion nivel3Promocion = new Funcion("nivel3Promocion", "Promoción", "", "", null, "Activo", new Date(), null);
		nivel3Promocion.setSistema(sistema);
		nivel3Promocion.setFuncionPadre(nivel2PromocionOferta);
		nivel3Promocion = funcionDAO.incluir(nivel3Promocion);
		// 2.3.-
		Funcion nivel2AgendaCitas = new Funcion("nivel2AgendaCitas", "Agenda y Citas", "", "", null, "Activo", new Date(), null);
		nivel2AgendaCitas.setSistema(sistema);
		nivel2AgendaCitas.setFuncionPadre(nivel1AdministracionRecursos);
		nivel2AgendaCitas = funcionDAO.incluir(nivel2AgendaCitas);
		// 2.3.1.-
		Funcion nivel3Habilidad = new Funcion("nivel3Habilidad", "Habilidades", "", "", "gc/habilidad/frm-habilidad-catalogo.zul", "Activo", new Date(), null);
		nivel3Habilidad.setSistema(sistema);
		nivel3Habilidad.setFuncionPadre(nivel2AgendaCitas);
		nivel3Habilidad = funcionDAO.incluir(nivel3Habilidad);
		// 2.3.2.-
		Funcion nivel3Espacio = new Funcion("nivel3Espacio", "Espacios del Taller", "", "", null, "Activo", new Date(), null);
		nivel3Espacio.setSistema(sistema);
		nivel3Espacio.setFuncionPadre(nivel2AgendaCitas);
		nivel3Espacio = funcionDAO.incluir(nivel3Espacio);
		// 2.3.3.-
		Funcion nivel3TipoEventualidad = new Funcion("nivel3TipoEventualidad", "Tipo de Eventualidad", "", "", "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul", "Activo", new Date(), null);
		nivel3TipoEventualidad.setSistema(sistema);
		nivel3TipoEventualidad.setFuncionPadre(nivel2AgendaCitas);
		nivel3TipoEventualidad = funcionDAO.incluir(nivel3TipoEventualidad);
		// 2.4.-
		Funcion nivel2Servicio = new Funcion("nivel2Servicio", "Servicios", "", "", null, "Activo", new Date(), null);
		nivel2Servicio.setSistema(sistema);
		nivel2Servicio.setFuncionPadre(nivel1AdministracionRecursos);
		nivel2Servicio = funcionDAO.incluir(nivel2Servicio);
		// 2.4.1.-
		Funcion nivel3Herramienta = new Funcion("nivel3Herramienta", "Herramienta", "", "", null, "Activo", new Date(), null);
		nivel3Herramienta.setSistema(sistema);
		nivel3Herramienta.setFuncionPadre(nivel2Servicio);
		nivel3Herramienta = funcionDAO.incluir(nivel3Herramienta);
		// 2.4.2.-
		Funcion nivel3Tecnologia = new Funcion("nivel3Tecnologia", "Tecnología", "", "", null, "Activo", new Date(), null);
		nivel3Tecnologia.setSistema(sistema);
		nivel3Tecnologia.setFuncionPadre(nivel2Servicio);
		nivel3Tecnologia = funcionDAO.incluir(nivel3Tecnologia);
		// 2.4.3.-
		Funcion nivel3TipoServicio = new Funcion("nivel3TipoServicio", "Tipo de Servicio", "", "", null, "Activo", new Date(), null);
		nivel3TipoServicio.setSistema(sistema);
		nivel3TipoServicio.setFuncionPadre(nivel2Servicio);
		nivel3TipoServicio = funcionDAO.incluir(nivel3TipoServicio);
		// 2.4.4.-
		Funcion nivel3Anomalia = new Funcion("nivel3Anomalia", "Anomalia", "", "", null, "Activo", new Date(), null);
		nivel3Anomalia.setSistema(sistema);
		nivel3Anomalia.setFuncionPadre(nivel2Servicio);
		nivel3Anomalia = funcionDAO.incluir(nivel3Anomalia);
		// 2.4.5.-
		Funcion nivel3Falla = new Funcion("nivel3Falla", "Falla", "", "", null, "Activo", new Date(), null);
		nivel3Falla.setSistema(sistema);
		nivel3Falla.setFuncionPadre(nivel2Servicio);
		nivel3Falla = funcionDAO.incluir(nivel3Falla);
		// 2.5.-
		Funcion nivel2PostServicios = new Funcion("nivel2PostServicios", "Post-Servicios", "", "", null, "Activo", new Date(), null);
		nivel2PostServicios.setSistema(sistema);
		nivel2PostServicios.setFuncionPadre(nivel1AdministracionRecursos);
		nivel2PostServicios = funcionDAO.incluir(nivel2PostServicios);
		// 2.5.1.-
		Funcion nivel3TipoGarantia = new Funcion("nivel3TipoGarantia", "Tipo de Garantía", "", "", null, "Activo", new Date(), null);
		nivel3TipoGarantia.setSistema(sistema);
		nivel3TipoGarantia.setFuncionPadre(nivel2PostServicios);
		nivel3TipoGarantia = funcionDAO.incluir(nivel3TipoGarantia);
		// 2.5.2.-
		Funcion nivel3TipoReclamo = new Funcion("nivel3TipoReclamo", "Tipo de Reclamo", "", "", null, "Activo", new Date(), null);
		nivel3TipoReclamo.setSistema(sistema);
		nivel3TipoReclamo.setFuncionPadre(nivel2PostServicios);
		nivel3TipoReclamo = funcionDAO.incluir(nivel3TipoReclamo);
		// 3.-
		Funcion nivel1PromocionOferta = new Funcion("nivel1PromocionOferta", "Promoción y Ofertas", "", "", null, "Activo", new Date(), null);
		nivel1PromocionOferta.setSistema(sistema);
		nivel1PromocionOferta = funcionDAO.incluir(nivel1PromocionOferta);
		// 3.1.-
		Funcion nivel2CatalogoServicios = new Funcion("nivel2CatalogoServicios", "Catálogo de Servicios", "", "", null, "Activo", new Date(), null);
		nivel2CatalogoServicios.setSistema(sistema);
		nivel2CatalogoServicios.setFuncionPadre(nivel1PromocionOferta);
		nivel2CatalogoServicios = funcionDAO.incluir(nivel2CatalogoServicios);
		// 3.1.1.-
		Funcion nivel3ConfigurarCatalogoServicios = new Funcion("nivel3ConfigurarCatalogoServicios", "Configurar", "", "", null, "Activo", new Date(), null);
		nivel3ConfigurarCatalogoServicios.setSistema(sistema);
		nivel3ConfigurarCatalogoServicios.setFuncionPadre(nivel2CatalogoServicios);
		nivel3ConfigurarCatalogoServicios = funcionDAO.incluir(nivel3ConfigurarCatalogoServicios);
		// 3.1.2.-
		Funcion nivel3ActualizarCatalogoServicios = new Funcion("nivel3ActualizarCatalogoServicios", "Actualizar", "", "", null, "Activo", new Date(), null);
		nivel3ActualizarCatalogoServicios.setSistema(sistema);
		nivel3ActualizarCatalogoServicios.setFuncionPadre(nivel2CatalogoServicios);
		nivel3ActualizarCatalogoServicios = funcionDAO.incluir(nivel3ActualizarCatalogoServicios);
		// 3.2.-
		Funcion nivel2Promocion = new Funcion("nivel2Promocion", "Promoción", "", "", null, "Activo", new Date(), null);
		nivel2Promocion.setSistema(sistema);
		nivel2Promocion.setFuncionPadre(nivel1PromocionOferta);
		nivel2Promocion = funcionDAO.incluir(nivel2Promocion);
		// 3.2.1.-
		Funcion nivel3ConfigurarPromocion = new Funcion("nivel3ConfigurarPromocion", "Configurar", "", "", "gp/promocion/frm-promocion-catalogo.zul", "Activo", new Date(), null);
		nivel3ConfigurarPromocion.setSistema(sistema);
		nivel3ConfigurarPromocion.setFuncionPadre(nivel2Promocion);
		nivel3ConfigurarPromocion = funcionDAO.incluir(nivel3ConfigurarPromocion);
		// 3.2.2.-
		Funcion nivel3ActualizarPromocion = new Funcion("nivel3ActualizarPromocion", "Actualizar", "", "", null, "Activo", new Date(), null);
		nivel3ActualizarPromocion.setSistema(sistema);
		nivel3ActualizarPromocion.setFuncionPadre(nivel2Promocion);
		nivel3ActualizarPromocion = funcionDAO.incluir(nivel3ActualizarPromocion);
		// 4.-
		Funcion nivel1Agenda = new Funcion("nivel1Agenda", "Agenda", "", "", null, "Activo", new Date(), null);
		nivel1Agenda.setSistema(sistema);
		nivel1Agenda = funcionDAO.incluir(nivel1Agenda);
		// 4.1.-
		Funcion nivel2Planificacion = new Funcion("nivel2Planificacion", "Planificación", "", "", null, "Activo", new Date(), null);
		nivel2Planificacion.setSistema(sistema);
		nivel2Planificacion.setFuncionPadre(nivel1Agenda);
		nivel2Planificacion = funcionDAO.incluir(nivel2Planificacion);
		// 4.1.1.-
		Funcion nivel3AsignarEspacio = new Funcion("nivel3AsignarEspacio", "Asignar Espacio", "", "","gc/espacio/frm-asignar-espacio-recursos-catalogo.zul", "Activo", new Date(), null);
		nivel3AsignarEspacio.setSistema(sistema);
		nivel3AsignarEspacio.setFuncionPadre(nivel2Planificacion);
		nivel3AsignarEspacio = funcionDAO.incluir(nivel3AsignarEspacio);
		// 5.-
		Funcion nivel1Citas = new Funcion("nivel1Citas", "Citas", "", "", null, "Activo", new Date(), null);
		nivel1Citas.setSistema(sistema);
		nivel1Citas = funcionDAO.incluir(nivel1Citas);
		// 5.1.-
		Funcion nivel2AtenderCita = new Funcion("nivel2AtenderCita", "Atender Citas", "", "", "gac/frm-atender-cita.zul", "Activo", new Date(), null);
		nivel2AtenderCita.setSistema(sistema);
		nivel2AtenderCita.setFuncionPadre(nivel1Citas);
		nivel2AtenderCita = funcionDAO.incluir(nivel2AtenderCita);
		// 5.2.-
		Funcion nivel2CancelarCita = new Funcion("nivel2CancelarCita", "Cancelar Citas", "", "", "gac/frm-cancelar-cita.zul", "Activo", new Date(), null);
		nivel2CancelarCita.setSistema(sistema);
		nivel2CancelarCita.setFuncionPadre(nivel1Citas);
		nivel2CancelarCita = funcionDAO.incluir(nivel2CancelarCita);
		// 5.3.-
		Funcion nivel2AsignarEspacio = new Funcion("nivel2AsignarEspacioCita", "Asignar Espacio a Citas", "", "","gc/espacio/frm-asignar-espacio-catalogo.zul", "Activo", new Date(), null);
		nivel2AsignarEspacio.setSistema(sistema);
		nivel2AsignarEspacio.setFuncionPadre(nivel1Citas);
		nivel2AsignarEspacio = funcionDAO.incluir(nivel2AsignarEspacio);
		// 6.-
		Funcion nivel1Servicios	 = new Funcion("nivel1Servicios", "Servicios", "", "", null, "Activo", new Date(), null);
		nivel1Servicios.setSistema(sistema);
		nivel1Servicios = funcionDAO.incluir(nivel1Servicios);
		// 6.1.-
		Funcion nivel2RecibirVehiculo = new Funcion("nivel2RecibirVehiculo", "Recibir Vehículo", "", "", "gs/recepcion/frm-recepcion.zul", "Activo", new Date(), null);
		nivel2RecibirVehiculo.setSistema(sistema);
		nivel2RecibirVehiculo.setFuncionPadre(nivel1Servicios);
		nivel2RecibirVehiculo = funcionDAO.incluir(nivel2RecibirVehiculo);
		// 6.2.-
		Funcion nivel2Diagnosticar = new Funcion("nivel2Diagnosticar", "Diagnósticar", "", "", "gs/servicio/frm-diagnostico-catalogo.zul", "Activo", new Date(), null);
		nivel2Diagnosticar.setSistema(sistema);
		nivel2Diagnosticar.setFuncionPadre(nivel1Servicios);
		nivel2Diagnosticar = funcionDAO.incluir(nivel2Diagnosticar);
		// 6.3.-
		Funcion nivel2AprobarPresupuesto = new Funcion("nivel2AprobarPresupuesto", "Aprobar Presupuesto", "", "", "gs/aprobarPresupuesto/frm-presupuesto-catalogo.zul", "Activo", new Date(), null);
		nivel2AprobarPresupuesto.setSistema(sistema);
		nivel2AprobarPresupuesto.setFuncionPadre(nivel1Servicios);
		nivel2AprobarPresupuesto = funcionDAO.incluir(nivel2AprobarPresupuesto);
		// 6.4.-
		Funcion nivel2GenerarOrdenServicio = new Funcion("nivel2GenerarOrdenServicio", "Generar Orden de Servicio", "", "","gs/frm-orden-servicio-todos.zul", "Activo", new Date(), null);
		nivel2GenerarOrdenServicio.setSistema(sistema);
		nivel2GenerarOrdenServicio.setFuncionPadre(nivel1Servicios);
		nivel2GenerarOrdenServicio = funcionDAO.incluir(nivel2GenerarOrdenServicio);
		// 6.5.-
		Funcion nivel2RevisarServicio = new Funcion("nivel2RevisarServicio", "Revisar Servicio", "", "", "gs/revisarServicioEjecutado/frm-revisar-catalogo.zul", "Activo", new Date(), null);
		nivel2RevisarServicio.setSistema(sistema);
		nivel2RevisarServicio.setFuncionPadre(nivel1Servicios);
		nivel2RevisarServicio = funcionDAO.incluir(nivel2RevisarServicio);
		// 6.6.-
		Funcion nivel2GenerarOrdenEntrega = new Funcion("nivel2GenerarOrdenEntrega", "Generar Orden de Entrega", "", "", "gs/ordenEntrega/frm-orden-entrega-catalogo.zul", "Activo", new Date(), null);
		nivel2GenerarOrdenEntrega.setSistema(sistema);
		nivel2GenerarOrdenEntrega.setFuncionPadre(nivel1Servicios);
		nivel2GenerarOrdenEntrega = funcionDAO.incluir(nivel2GenerarOrdenEntrega);
		// 6.7.-
		Funcion nivel2CalificarServicio = new Funcion("nivel2CalificarServicio", "Calificar Servicio", "", "", "gs/calificarServicio/frm-calificar-servicio-catalogo.zul", "Activo", new Date(), null);
		nivel2CalificarServicio.setSistema(sistema);
		nivel2CalificarServicio.setFuncionPadre(nivel1Servicios);
		nivel2CalificarServicio = funcionDAO.incluir(nivel2CalificarServicio);
		// 7.-
		Funcion nivel1PostServicios = new Funcion("nivel1PostServicios", "Post-Servicios", "", "", null, "Activo", new Date(), null);
		nivel1PostServicios.setSistema(sistema);
		nivel1PostServicios = funcionDAO.incluir(nivel1PostServicios);
		// 7.1.-
		Funcion nivel2SolicitudReclamo = new Funcion("nivel2SolicitudReclamo", "Solicitud Reclamo", "", "", "gps/reclamo/frm-reclamo-ordenesEntrega.zul", "Activo", new Date(), null);
		nivel2SolicitudReclamo.setSistema(sistema);
		nivel2SolicitudReclamo.setFuncionPadre(nivel1PostServicios);
		nivel2SolicitudReclamo = funcionDAO.incluir(nivel2SolicitudReclamo);
		// 7.2.-
		Funcion nivel2VerificarGarantia = new Funcion("nivel2VerificarGarantia", "Verificar Garantía", "", "", "gps/reclamo/frm-reclamo-reclamos.zul", "Activo", new Date(), null);
		nivel2VerificarGarantia.setSistema(sistema);
		nivel2VerificarGarantia.setFuncionPadre(nivel1PostServicios);
		nivel2VerificarGarantia = funcionDAO.incluir(nivel2VerificarGarantia);
		// 8.-
		Funcion nivel1Reportes = new Funcion("nivel1Reportes", "Reportes", "", "", null, "Activo", new Date(), null);
		nivel1Reportes.setSistema(sistema);
		nivel1Reportes = funcionDAO.incluir(nivel1Reportes);
		// 8.1.-
		Funcion nivel2ReportesEstadistico = new Funcion("nivel2ReportesEstadistico", "Reportes Estadístico", "", "", null, "Activo", new Date(), null);
		nivel2ReportesEstadistico.setSistema(sistema);
		nivel2ReportesEstadistico.setFuncionPadre(nivel1Reportes);
		nivel1Reportes = funcionDAO.incluir(nivel2ReportesEstadistico);
		// 9.-
		Funcion nivel1AdministracionSistema	= new Funcion("nivel1AdministracionSistema", "Administraci�n del Sistema", "", "", null, "Activo", new Date(), null);
		nivel1AdministracionSistema.setSistema(sistema);
		nivel1AdministracionSistema = funcionDAO.incluir(nivel1AdministracionSistema);
		// 9.1.-
		Funcion nivel2SeguridadFuncional = new Funcion("nivel2SeguridadFuncional", "Seguridad Funcional", "", "", null, "Activo", new Date(), null);
		nivel2SeguridadFuncional.setSistema(sistema);
		nivel2SeguridadFuncional.setFuncionPadre(nivel1AdministracionSistema);
		nivel2SeguridadFuncional = funcionDAO.incluir(nivel2SeguridadFuncional);
		// 9.1.1.-
		Funcion nivel3Grupo = new Funcion("nivel3Grupo", "Grupo", "", "", "gc/grupo/frm-grupo-catalogo.zul", "Activo", new Date(), null);
		nivel3Grupo.setSistema(sistema);
		nivel3Grupo.setFuncionPadre(nivel2SeguridadFuncional);
		nivel3Grupo = funcionDAO.incluir(nivel3Grupo);
		// 9.1.2.-
		Funcion nivel3Funcion = new Funcion("nivel3Funcion", "Función", "", "", "gc/funcion/frm-funcion-catalogo.zul", "Activo", new Date(), null);
		nivel3Funcion.setSistema(sistema);
		nivel3Funcion.setFuncionPadre(nivel2SeguridadFuncional);
		nivel3Funcion = funcionDAO.incluir(nivel3Funcion);
		// 9.1.3.-
		Funcion nivel3Accion = new Funcion("nivel3Accion", "Acción", "", "", "gc/accion/frm-accion-catalogo.zul", "Activo", new Date(), null);
		nivel3Accion.setSistema(sistema);
		nivel3Accion.setFuncionPadre(nivel2SeguridadFuncional);
		nivel3Accion = funcionDAO.incluir(nivel3Accion);
		// 9.1.4.-
		Funcion nivel3AsignacionGrupos = new Funcion("nivel3AsignacionGrupos", "Asignación Grupos", "", "", "gc/grupo/frm-grupo-asignacion.zul", "Activo", new Date(), null);
		nivel3AsignacionGrupos.setSistema(sistema);
		nivel3AsignacionGrupos.setFuncionPadre(nivel2SeguridadFuncional);
		nivel3AsignacionGrupos = funcionDAO.incluir(nivel3AsignacionGrupos);
		// 9.1.5.-
		Funcion nivel3Usuario = new Funcion("nivel3Usuario", "Usuario", "", "", "gc/grupo/usuario/frm-grupo-usuario-catalogo.zul", "Activo", new Date(), null);
		nivel3Usuario.setSistema(sistema);
		nivel3Usuario.setFuncionPadre(nivel2SeguridadFuncional);
		nivel3Usuario = funcionDAO.incluir(nivel3Usuario);
		// 9.1.6.-
		Funcion nivel3PerfilUsuario = new Funcion("nivel3PerfilUsuario", "Perfil de Usuario", "", "", null, "Activo", new Date(), null);
		nivel3PerfilUsuario.setSistema(sistema);
		nivel3PerfilUsuario.setFuncionPadre(nivel2SeguridadFuncional);
		nivel3PerfilUsuario = funcionDAO.incluir(nivel3PerfilUsuario);
		// 9.2.-
		Funcion nivel2Web = new Funcion("nivel2Web", "Configuración del portal WEB", "", "", "gc/web/configuracion-web.zul", "Activo", new Date(), null);
		nivel2Web.setSistema(sistema);
		nivel2Web.setFuncionPadre(nivel1AdministracionSistema);
		nivel2Web = funcionDAO.incluir(nivel2Web);
		// 9.2.1.-
		Funcion nivel3ActualizarContenidoWeb = new Funcion("nivel3ActualizarContenidoWeb", "Actualizar Contenido", "", "", null, "Activo", new Date(), null);
		nivel3ActualizarContenidoWeb.setSistema(sistema);
		nivel3ActualizarContenidoWeb.setFuncionPadre(nivel2SeguridadFuncional);
		nivel3ActualizarContenidoWeb = funcionDAO.incluir(nivel3ActualizarContenidoWeb);
		// 9.3.-
		Funcion nivel2Movil = new Funcion("nivel2Movil", "Configuración App Movil", "", "", null, "Activo", new Date(), null);
		nivel2Movil.setSistema(sistema);
		nivel2Movil.setFuncionPadre(nivel1AdministracionSistema);
		nivel2Movil = funcionDAO.incluir(nivel2Movil);
		// 9.3.1.-
		Funcion nivel3ActualizarContenidoMovil = new Funcion("nivel3ActualizarContenidoMovil", "Actualizar Contenido", "", "", null, "Activo", new Date(), null);
		nivel3ActualizarContenidoMovil.setSistema(sistema);
		nivel3ActualizarContenidoMovil.setFuncionPadre(nivel2Movil);
		nivel3ActualizarContenidoMovil = funcionDAO.incluir(nivel3ActualizarContenidoMovil);
		// 9.4.-
		Funcion nivel2Difusion = new Funcion("nivel2Difusion", "Configuración de Difusión", "", "", null, "Activo", new Date(), null);
		nivel2Difusion.setSistema(sistema);
		nivel2Difusion.setFuncionPadre(nivel1AdministracionSistema);
		nivel2Difusion = funcionDAO.incluir(nivel2Difusion);
		// 9.4.1.-
		Funcion nivel3CorreoElectronico = new Funcion("nivel3CorreoElectronico", "Correo Electr�nico", "", "", null, "Activo", new Date(), null);
		nivel3CorreoElectronico.setSistema(sistema);
		nivel3CorreoElectronico.setFuncionPadre(nivel2Difusion);
		nivel3CorreoElectronico = funcionDAO.incluir(nivel3CorreoElectronico);
		// 9.4.2.-
		Funcion nivel3RedesSociales = new Funcion("nivel3RedesSociales", "Redes Sociales", "", "", null, "Activo", new Date(), null);
		nivel3RedesSociales.setSistema(sistema);
		nivel3RedesSociales.setFuncionPadre(nivel2Difusion);
		nivel3RedesSociales = funcionDAO.incluir(nivel3RedesSociales);
		// 9.5.-
		Funcion nivel2BaseDatos = new Funcion("nivel2BaseDatos", "Base de Datos", "", "", null, "Activo", new Date(), null);
		nivel2BaseDatos.setSistema(sistema);
		nivel2BaseDatos.setFuncionPadre(nivel1AdministracionSistema);
		nivel2BaseDatos = funcionDAO.incluir(nivel2BaseDatos);
		// 9.5.1.-
		Funcion nivel3Respaldo = new Funcion("nivel3Respaldo", "Respaldo", "", "", null, "Activo", new Date(), null);
		nivel3Respaldo.setSistema(sistema);
		nivel3Respaldo.setFuncionPadre(nivel2BaseDatos);
		nivel3Respaldo = funcionDAO.incluir(nivel3Respaldo);
		// 9.5.2.-
		Funcion nivel3Recuperacion = new Funcion("nivel3Recuperacion", "Recuperaci�n", "", "", null, "Activo", new Date(), null);
		nivel3Recuperacion.setSistema(sistema);
		nivel3Recuperacion.setFuncionPadre(nivel2BaseDatos);
		nivel3Recuperacion = funcionDAO.incluir(nivel3Recuperacion);
		// 9.5.3.-
		Funcion nivel3Historico = new Funcion("nivel3Historico", "Historico", "", "", null, "Activo", new Date(), null);
		nivel3Historico.setSistema(sistema);
		nivel3Historico.setFuncionPadre(nivel2BaseDatos);
		nivel3Historico = funcionDAO.incluir(nivel3Historico);
		resultado = true;
		return resultado;
	}

}
