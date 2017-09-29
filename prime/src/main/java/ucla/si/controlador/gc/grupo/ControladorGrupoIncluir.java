package ucla.si.controlador.gc.grupo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Accion;
import ucla.si.modelo.Funcion;
import ucla.si.modelo.Grupo;
import ucla.si.modelo.Permiso;
import ucla.si.servicio.ServicioAccion;
import ucla.si.servicio.ServicioFuncion;
import ucla.si.servicio.ServicioGrupo;
import ucla.si.servicio.ServicioPermiso;

public class ControladorGrupoIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioGrupo servicioGrupo;
	
	@WireVariable
	private ServicioPermiso servicioPermiso;
	
	@WireVariable
	private ServicioAccion servicioAccion;
	
	@WireVariable
	private ServicioFuncion servicioFuncion;

	@Wire
	Textbox txtNombre, txtDescripcion;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtNombre.getValue().trim() == "" || txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String nombre = txtNombre.getValue().trim().toUpperCase();
			String descripcion = txtDescripcion.getValue().trim();
			String estatus = "Activo";
			Grupo grupo = new Grupo(nombre, descripcion, estatus, new Date(), null);
			if (servicioGrupo.incluirGrupo(grupo)) {
				if(construirPermisosPorGrupo(grupo)){
					Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/grupo/frm-grupo-catalogo.zul";
					clearDivApp(dir);
				}
				else{
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/grupo/frm-grupo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbGrupo")
	public void grupo() {
		String dir = "gc/grupo/frm-grupo-catalogo.zul";
		clearDivApp(dir);
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

}
