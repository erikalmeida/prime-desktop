package ucla.si.controlador.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ucla.si.dao.movil.PeticionesDAO;
import ucla.si.modelo.movil.Calificacion;
import ucla.si.modelo.movil.Presupuesto;
import ucla.si.modelo.movil.Log;
import ucla.si.modelo.movil.Notificacion;
import ucla.si.modelo.movil.Sistema;
import ucla.si.modelo.movil.Usuario;


/**
 * Servlet implementation class ControladorPeticion
 */
public class ControladorPeticion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Log log;
	private ArrayList<Log> logs;
	private PeticionesDAO peticionesDAO;
	/**
	 * Default constructor.
	 */
	public ControladorPeticion() {
		super();
        this.logs = new ArrayList<Log>();
        this.log = null;
        this.peticionesDAO = new PeticionesDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		peticionAcceso(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		peticionAcceso(request, response);
	}

	public void getUsuario() {
		try {
			Class generico = Class.forName("panel.Administrador.aplicacion.controlador.componente.ControladorMenu");
			String metodo = "escribir";
			Object obj;
			obj = (ControladorMenu) generico.getConstructor(null).newInstance(null);
			obj.getClass().getMethod(metodo, null).invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void peticionAcceso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String solicitud = request.getParameter("solicitud");
		if(solicitud.equalsIgnoreCase("sistema")){
			Sistema sistema = peticionesDAO.buscarSistema();
			if (sistema != null) {
				writeObject(response, sistema);
			} else {
				write(response, "no se encontro respuesta");
			}
		}
		else if(solicitud.equalsIgnoreCase("login")){
			String correo = request.getParameter("correo");
			Usuario usuario = peticionesDAO.buscarUsuario(correo);
			if(usuario!=null){
				writeObject(response, usuario);
			} else {
				writeObject(response, "No se pudo encontrar usuario");
			}
		} else if(solicitud.equalsIgnoreCase("notificaciones")) {
			List<Notificacion> notificaciones = new ArrayList<Notificacion>();
			String idusuario = request.getParameter("idusuario");
			notificaciones = peticionesDAO.consultarNotificaciones(idusuario);			
			writeObject(response, notificaciones);
		}else if(solicitud.equalsIgnoreCase("presupuesto")) {
			Presupuesto presupuesto = new Presupuesto();
			String idpresupuesto = request.getParameter("idpresupuesto");
			String estado = request.getParameter("estado");
			peticionesDAO.actualizarPresupuesto(idpresupuesto, estado);			
			writeObject(response, presupuesto);
		}else if(solicitud.equalsIgnoreCase("calificacion")) {
			Calificacion calificacion = new Calificacion();
			String idordenentrega = request.getParameter("idordenentrega");
			String calificacionServicio = request.getParameter("calificacionServicio");
			String calificacionInstalacion = request.getParameter("calificacionInstalacion");
			String calificacionAtencion = request.getParameter("calificacionAtencion");
			String comentario = request.getParameter("comentario");
			peticionesDAO.insertarCalificacion(idordenentrega, comentario, calificacionServicio, calificacionInstalacion, calificacionAtencion);			
			writeObject(response, calificacion);
		}else if(solicitud.equalsIgnoreCase("presupuestobuscar")){
			Presupuesto presupuesto = peticionesDAO.buscarPresupuesto("idpresupuesto");
			if (presupuesto != null) {
				writeObject(response, presupuesto);
			} else {
				write(response, "no se encontro respuesta");
			}
		}
	}
	
	private void write(HttpServletResponse response, String resultado) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultado);
	}
	
	private void writeObject(HttpServletResponse response, Object obj) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		//response.setCharacterEncoding("UTF-8");
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(obj));
		response.getWriter().write(gson.toJson(obj));
	}

}