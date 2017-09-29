package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.PersonaDAO;
import ucla.si.modelo.Persona;


@Service("servicioPersona")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioPersona {
	
	@Autowired
	private PersonaDAO personaDAO;
	
	public boolean incluirPersona(Persona persona){
		boolean guardado = false;
		try {
			guardado = personaDAO.incluirPersona(persona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarPersona(Persona persona){
		boolean guardado = false;
		try {
			guardado = personaDAO.editarPersona(persona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Persona> listarPersonas(){
		List<Persona> personas = new ArrayList<Persona>();
		try {
			personas = personaDAO.listarPersonas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personas;
	}
	
	public Persona getPersona(Long id){
		Persona persona = new Persona();
		try {
			persona = personaDAO.getPersona(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persona;
	}
	

}
