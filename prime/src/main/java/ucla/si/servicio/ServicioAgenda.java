package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.AgendaDAO;
import ucla.si.modelo.Aceite;
import ucla.si.modelo.Agenda;

@Service("servicioAgenda")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioAgenda {
	
	@Autowired
	private AgendaDAO agendadao;
	
	public boolean incluirAceite(Agenda agenda){
		boolean guardado = false;
		try {
			guardado = agendadao.incluirAgenda(agenda);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarAceite(Agenda agenda){
		boolean guardado = false;
		try {
			guardado = agendadao.editarAgenda(agenda);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Agenda> listarAceites(){
		List<Agenda> agendas = new ArrayList<Agenda>();
		try {
			agendas = agendadao.listarAgendas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agendas;
	}

}
