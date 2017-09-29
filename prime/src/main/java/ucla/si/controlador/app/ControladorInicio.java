package ucla.si.controlador.app;

import java.util.LinkedHashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import ucla.si.modelo.Usuario;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public abstract class ControladorInicio extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static long time_start = 0, time_end = 0;
	private static Runtime basurero = Runtime.getRuntime();
	protected static Map<String,Usuario> autenticados;
	
	/** Componente de conexión entre variables del sistema. */
	private static Component componente;
	
	protected Map<String,Usuario> getAutenticados(){
		if(autenticados == null){
			autenticados = new LinkedHashMap<String,Usuario>();
		}
		
		return autenticados;
	}
	
	private static Component getInstanceComponente() {
		if (componente == null)
			componente = new Label();
		return componente;
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		inicializar();
	}
	
	@Wire
	protected Div app;
	
	@Wire
	protected Div appWeb;
	
	@Wire
	protected Div appRecepcion;
	
	protected void clearDivApp(String dir){
		try {
			if(app != null){
				app.getChildren().clear();
				Div div = (Div)Executions.createComponents(dir,null,null);
				app.appendChild(div);
				Clients.evalJavaScript("window.scrollTo(0, 0);");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//agregarMensaje("Ha ocurrido un error inesperado");
			Executions.sendRedirect("/prime/index.zul");
		}
	}
		
	protected void clearDivAppWeb(String dir){
		try {
			if(appWeb != null){
				appWeb.getChildren().clear();
				Div div = (Div)Executions.createComponents(dir,null,null);
				appWeb.appendChild(div);
				Clients.evalJavaScript("window.scrollTo(0, 0);");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//agregarMensaje("Ha ocurrido un error inesperado");
			Executions.sendRedirect("/prime/index.zul");
		}
	}
	
	protected void clearDivAppRecepcion(String dir){
		try {
			if(appRecepcion != null){
				appRecepcion.getChildren().clear();
				Div div = (Div)Executions.createComponents(dir,null,null);
				appRecepcion.appendChild(div);
				Clients.evalJavaScript("window.scrollTo(0, 0);");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//agregarMensaje("Ha ocurrido un error inesperado");
			Executions.sendRedirect("/prime/index.zul");
		}
	}
	
	protected abstract void inicializar();
	
	//Asigna el Objeto que se utilizara a la vista correspondiente
	protected void setAtributo(String nombre, Object objeto) {
		getInstanceComponente().setAttribute(nombre, objeto);
	}
	
	//Devuelve el Objeto asignado en la vista previa
	protected Object getAtributo(String nombre) {
		return getInstanceComponente().getAttribute(nombre);
	}
	
	//Remueve el Objeto asignado en la vista correspondiente
	protected void removerAtributo(String nombre) {
		getInstanceComponente().removeAttribute(nombre);
	}
	
	public long consultarMemoriaTotal(){
		long memoria = 0;
		//memoria = basurero.totalMemory()/1048576;
		return memoria;
	}
	
	public long consultarMemoriaDisponible(){
		long memoria = 0;
		//memoria = basurero.freeMemory()/1048576;
		return memoria;
	}
	
	//Se realiza peticion de limpieza de memoria a JVM
	public void limpiarMemoria(){
		//basurero.gc();
	}

}
