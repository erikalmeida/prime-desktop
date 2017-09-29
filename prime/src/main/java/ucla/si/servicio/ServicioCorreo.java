package ucla.si.servicio;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.modelo.Correo;

@Service("servicioCorreo")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioCorreo {
	private Correo correo = new Correo();
	
	public boolean enviarCorreo(String destinatario, String asunto, String mensaje){
		boolean enviado = correo.enviarCorreo(destinatario, asunto, mensaje);
		return enviado;
	}
	
	public boolean enviarCorreoHTML(String destinatario, String asunto, String titulo, String subTitulo, String mensaje){
		boolean enviado = correo.enviarCorreoHTML(destinatario, asunto, titulo, subTitulo, mensaje);
		return enviado;
	}
	
	public String mensajeSesion(String cliente){
		String mensaje = correo.mensajeSesion(cliente);
		return mensaje;
	}
	
	public String mensajeAtenderCita(String cliente, String estado, Date fechaCita){
		String mensaje = correo.mensajeAtenderCita(cliente, estado, fechaCita);
		return mensaje;
	}
	
	public String mensajeAfiliacion(String cliente, String email, String contrasenna){
		String mensaje = correo.mensajeAfiliacion(cliente, email, contrasenna);
		return mensaje;
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
