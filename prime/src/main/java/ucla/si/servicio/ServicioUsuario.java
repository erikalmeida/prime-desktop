package ucla.si.servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import ucla.si.dao.UsuarioDAO;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Usuario;

@Service("servicioUsuario")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioUsuario{
	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuario buscarUsuario(String correo, String contrasenna) {
		Usuario usuario = null;
		try {
			usuario = usuarioDAO.buscarUsuario(correo, contrasenna);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public Usuario buscarUsuarioMecanico(long id_grupo) {
		Usuario usuario = null;
		try {
			usuario = usuarioDAO.buscarUsuarioMecanico(id_grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public boolean registrarCliente(Persona persona, Usuario usuario){
		boolean guardado = false;
		try {
			guardado = usuarioDAO.registrarCliente(persona, usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Usuario> listarUsuarios(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			usuarios = usuarioDAO.listarUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public List<Usuario> listarUsuariosSinAdministrador(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			usuarios = usuarioDAO.listarUsuariosSinAdministrador();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public List<Usuario> listarUsuariosMecanicos(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			usuarios = usuarioDAO.listarUsuariosMecanicos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public boolean editarCliente(Persona persona, Usuario usuario){
		boolean guardado = false;
		try {
			guardado = usuarioDAO.editarCliente(persona, usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	
	public boolean editarUsuario(Usuario usuario){
		boolean guardado = false;
		try {
			guardado = usuarioDAO.editarUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	
	public Usuario getUsuario(Long id){
		Usuario usuario = new Usuario();
		try {
			usuario = usuarioDAO.getUsuario(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	

}
