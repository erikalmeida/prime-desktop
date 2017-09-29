package ucla.si.controlador.gc.grupo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treerow;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Accion;
import ucla.si.modelo.Funcion;
import ucla.si.modelo.Grupo;
import ucla.si.modelo.Permiso;
import ucla.si.servicio.ServicioAccion;
import ucla.si.servicio.ServicioFuncion;
import ucla.si.servicio.ServicioGrupo;
import ucla.si.servicio.ServicioPermiso;

public class ControladorGrupoAsignacion extends ControladorInicio {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox cmbGrupos;

	@Wire
	private Treerow
	nivel1InicioTree,
	nivel1AdministracionRecursosTree,
		nivel2GeneralTree,
			nivel3ClienteTree,
				nivel4OcupacionTree,
				nivel4ProfesionTree,
			nivel3VehiculoTree,
				nivel4MarcaTree,
	nivel1PromocionOfertaTree,
		nivel2CatalogoServiciosTree,
			nivel3ConfigurarCatalogoServiciosTree,
				nivel3ActualizarCatalogoServiciosTree;

	@Wire
	private Tree tree;

	@WireVariable
	private ServicioAccion servicioAccion;

	@WireVariable
	private ServicioGrupo servicioGrupo;

	@WireVariable
	private ServicioFuncion servicioFuncion;

	@WireVariable
	private ServicioPermiso servicioPermiso;

	private Comboitem cmbiGrupo;

	private int contador;

	private List<Accion> acciones;
	private Accion accionDesplegar;

	private Map<String,Permiso> arbolGrupo = new HashMap<String, Permiso>();
	private Map<String,Long> contadorFunciones = new HashMap<String, Long>();
	private Map<String,Long> contadorFuncionesPorPermisos = new HashMap<String, Long>();
	private Map<String,Treerow> arbolTreerow = new HashMap<String, Treerow>();

	@Override
	protected void inicializar() {
		ListModelList<Grupo> modeloGrupos = new ListModelList<Grupo>(servicioGrupo.listarGruposSinAdministrador());
		cmbGrupos.setModel(modeloGrupos);
		this.contador = 0;
		this.acciones = servicioAccion.listarAccionesSinDesplegar();
		this.accionDesplegar = servicioAccion.buscarAccion("DESPLEGAR");
//		System.out.println("cantidad de hijos: "+rowOcupacion.getChildren().size());
//		Checkbox checkbox = (Checkbox) rowOcupacion.getChildren().get(1).getChildren().get(0);
//		System.out.println("el value del label es: "+checkbox.isChecked());
//		checkbox.setChecked(true);
//		System.out.println("el value del label es: "+checkbox.isChecked());

	}

	@Listen("onClick =#cmbGrupos")
	public void click(){
		if(cmbGrupos.getSelectedIndex() != -1){
			cmbiGrupo = cmbGrupos.getSelectedItem();
		}
	}

	@Listen("onChange =#cmbGrupos")
	public void grupos(){
		if(contador > 0){
			System.out.println(" change "+((Grupo)cmbGrupos.getSelectedItem().getValue()).getNombre());
			Messagebox.show("¿Desea guardar los cambios realizados?", "Confirmación",
			Messagebox.YES | Messagebox.NO, Messagebox.EXCLAMATION,
	        new EventListener<Event>() {
	            public void onEvent(Event evt) throws Exception {
	                if (evt.getName().equals("onYes")) {
	                	System.out.println("si");
	                	cmbGrupos.setSelectedItem(cmbiGrupo);
	                }
	                else{
	                	System.out.println("NO");
	                	cmbiGrupo = cmbGrupos.getSelectedItem();
	                	buscarPermisos((Grupo)cmbGrupos.getSelectedItem().getValue());
	                }
	            }
			});
		}
		else{
			contador++;
			//deberia buscar la configuracion correspondiente al grupo seleccionado
			buscarPermisos((Grupo)cmbGrupos.getSelectedItem().getValue());
		}
	}

	@SuppressWarnings("rawtypes")
	public void buscarPermisos(Grupo grupo){
		arbolGrupo.clear();
		contadorFunciones.clear();
		contadorFuncionesPorPermisos.clear();
		//List<Permiso> permisos = servicioPermiso.listarPermisos(grupo);
		arbolGrupo = servicioPermiso.arbolGrupo(grupo);
		contadorFunciones = servicioFuncion.contadorFunciones();
		contadorFuncionesPorPermisos = servicioFuncion.contadorFuncionesPorPermisos(grupo);
		if(!arbolGrupo.isEmpty()){
			System.out.println("guardo permisos "+arbolGrupo.size()+" contador de funciones "+contadorFunciones.size()+" permisos "+contadorFuncionesPorPermisos.size());
			mostrarPermisos();
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
						servicioPermiso.incluirPermiso(permiso);
					}
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void mostrarPermisos(){
		Iterator iterator = arbolGrupo.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry e =  (Map.Entry) iterator.next();
			Permiso permiso = (Permiso) e.getValue();
			String key = (String) e.getKey();
			String clave = separarClaveOAccion(key, 1)+"Tree";
			String accion = separarClaveOAccion(key, 2);
			//System.out.println("key "+key+" clave "+clave+" accion "+accion);
			//System.out.println("arbolTreerow "+arbolTreerow.size() +" clave "+clave);
			if(arbolTreerow.containsKey(clave)){
				Treerow treerow = arbolTreerow.get(clave);
				//System.out.println("clave encontrada "+clave+" treerow.getChildren() "+treerow.getChildren().size());
				if(treerow.getChildren().size() > 1){
					if(accion.equalsIgnoreCase("INCLUIR")){
						Checkbox checkbox = (Checkbox) treerow.getChildren().get(1).getChildren().get(0);
						checkbox.setChecked(permiso.isAcceso());
						((Treecell)treerow.getChildren().get(1)).setStyle(!checkbox.isChecked() ? "text-align: center; background: #d9534f;" : "text-align: center; background: #4caf50;");
						System.out.println("Incluir "+clave+" "+permiso.isAcceso());
					}
					else if(accion.equalsIgnoreCase("CONSULTAR")){
						Checkbox checkbox = (Checkbox) treerow.getChildren().get(2).getChildren().get(0);
						checkbox.setChecked(permiso.isAcceso());
						((Treecell)treerow.getChildren().get(2)).setStyle(!checkbox.isChecked() ? "text-align: center; background: #d9534f;" : "text-align: center; background: #4caf50;");
						System.out.println("Consultar "+clave+" "+permiso.isAcceso());
					}
					else if(accion.equalsIgnoreCase("EDITAR")){
						Checkbox checkbox = (Checkbox) treerow.getChildren().get(3).getChildren().get(0);
						checkbox.setChecked(permiso.isAcceso());
						((Treecell)treerow.getChildren().get(3)).setStyle(!checkbox.isChecked() ? "text-align: center; background: #d9534f;" : "text-align: center; background: #4caf50;");
						System.out.println("Editar "+clave+" "+permiso.isAcceso());
					}
					else if(accion.equalsIgnoreCase("ELIMINAR")){
						Checkbox checkbox = (Checkbox) treerow.getChildren().get(4).getChildren().get(0);
						checkbox.setChecked(permiso.isAcceso());
						((Treecell)treerow.getChildren().get(4)).setStyle(!checkbox.isChecked() ? "text-align: center; background: #d9534f;" : "text-align: center; background: #4caf50;");
						System.out.println("Eliminar "+clave+" "+permiso.isAcceso());
					}
				}
			}
		}
	}

	public String separarClaveOAccion(String claveOAccion, int lugar){
		String str = "";
		if(claveOAccion.length() > 0){
			for (int i = 0; i < claveOAccion.length(); i++) {
				if(claveOAccion.charAt(i) =='-'){
					if(lugar == 1){
						str = claveOAccion.substring(0, i);
						break;
					}
					else{
						str = claveOAccion.substring(i+1, claveOAccion.length());
						break;
					}
				}
			}
		}
		return str;
	}

	@Listen("onCreate =#tree")
	public void cargarArbol(){
		System.out.println("cargando");
		asignarEventos(tree);
	}

	private void asignarEventos(Component padre) {
		String click = "onClick";
		for (final Component hijo : padre.getChildren()){
			if (hijo instanceof Treerow){
				//System.out.println("hijo arbolTreerow "+hijo.getId());
				arbolTreerow.put(hijo.getId(), (Treerow)hijo);
				if(hijo.getChildren().size() == 5){
					//System.out.println("hijos 5 de : "+hijo.getId());
					for(final Component hoja : hijo.getChildren()){
						if(hoja instanceof Treecell){
							if(hoja.getChildren().size() == 1){
								if(hoja.getChildren().get(0) instanceof Checkbox){
									//System.out.println("checkbox");
									hoja.getChildren().get(0).addEventListener(click, new EventListener<Event>() {
										public void onEvent(Event event) throws Exception {
											Checkbox checkbox = (Checkbox) hoja.getChildren().get(0);
											if(checkbox.isChecked() == false){
												((Treecell) hoja).setStyle("text-align: center; background: #d9534f;");
												actualizarPermisos(hijo.getId(), checkbox.getTooltiptext(), false);
											}
											else{
												((Treecell) hoja).setStyle("text-align: center; background: #4caf50;");
												actualizarPermisos(hijo.getId(), checkbox.getTooltiptext(), true);
											}
										}
									});
								}
							}
						}
					}
				}
			}
			asignarEventos(hijo);
		}
	}

	public void actualizarPermisos(String nivel, String accion, boolean isChecked) {
		String clave = nivel.substring(0, nivel.length() - 4);
		String key = clave+"-"+accion.toUpperCase();
		if(arbolGrupo.containsKey(key)){
			Permiso permiso = arbolGrupo.get(key);
			permiso.setAcceso(isChecked);
			arbolGrupo.remove(key);
			arbolGrupo.put(key, permiso);
			System.out.println("la clave es: "+clave+" y la accion "+accion+" y el check "+isChecked+" permiso "+permiso.isAcceso());
			/*String padre = permiso.getFuncion().getFuncionPadre() != null ? permiso.getFuncion().getFuncionPadre().getClave() : "";
			if(contadorFunciones.containsKey(padre)){
				//System.out.println("del nivel "+padre
				//		+" su cantidad de hijos es: "+contadorFunciones.get(padre));
				Long hijos = contadorFunciones.get(padre);
				if(hijos > 0){
					Long permisos = contadorFuncionesPorPermisos.get(clave);
					if(isChecked){
						permisos = permisos + 1;
						contadorFuncionesPorPermisos.put(clave, permisos);
					//	System.out.println("Permisos asociados "+contadorFuncionesPorPermisos.get(clave));
					}
					else{
						permisos = permisos - 1;
						contadorFuncionesPorPermisos.put(clave, permisos);
					//	System.out.println("Permisos asociados "+contadorFuncionesPorPermisos.get(clave));
					}
				}
				else{
					//preguntar si es isChecked = true para actualizar
				}
			}*/
		}
	}

	@SuppressWarnings("rawtypes")
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(cmbGrupos.getSelectedIndex() == -1 || arbolGrupo.size() == 0){
			Messagebox.show("debe seleccionar un grupo o no se ha creado configuración para este grupo");
		}
		else{
			Iterator it = arbolGrupo.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry e =  (Map.Entry) it.next();
				Permiso permiso = (Permiso) e.getValue();
				permiso.setFechaModificacion(new Date());
				servicioPermiso.editarPermiso(permiso);
			}
			Messagebox.show("Configuración actualizada");
		}
	}

}
