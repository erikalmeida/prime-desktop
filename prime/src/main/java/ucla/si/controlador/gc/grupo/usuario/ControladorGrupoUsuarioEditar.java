package ucla.si.controlador.gc.grupo.usuario;


import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Funcion;
import ucla.si.modelo.Grupo;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Sistema;
import ucla.si.modelo.Usuario;
import ucla.si.servicio.ServicioFuncion;
import ucla.si.servicio.ServicioGrupo;
import ucla.si.servicio.ServicioUsuario;

public class ControladorGrupoUsuarioEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@WireVariable
	private ServicioUsuario servicioUsuario;
	
	@WireVariable
	private ServicioGrupo servicioGrupo;

	@Wire
	private Textbox txtCorreo, txtNombre;

	@Wire
	private Combobox cmbGrupo;

	private Sistema sistema;
	
	private Usuario usuario;

	@Override
	protected void inicializar() {
		ListModelList<Grupo> modeloGrupos = new ListModelList<Grupo>(servicioGrupo.listarGruposSinAdministrador());
		modeloGrupos.setMultiple(false);
		cmbGrupo.setModel(modeloGrupos);
		cmbGrupo.setMultiline(false);
		sistema = (Sistema) Sessions.getCurrent().getAttribute("sistema");
		if (sistema == null) {
			Messagebox.show("Error de sistema", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/grupo/usuario/frm-grupo-usuario-catalogo.zul";
			clearDivApp(dir);
		} 
		else {
			if(modeloGrupos.getSize() == 0){
				Messagebox.show("Debe Incluir un Grupo primero", "Error", Messagebox.OK, Messagebox.ERROR);
				String dir = "gc/grupo/frm-grupo-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				modeloGrupos.setMultiple(false);
				cmbGrupo.setModel(modeloGrupos);
				cmbGrupo.setMultiline(false);
				usuario = (Usuario)getAtributo("usuario");
				cargarDatos(usuario);
			}
		}
	}

	public void cargarDatos(Usuario usuario) {
		if (usuario != null) {
			txtCorreo.setValue(usuario.getCorreo());
			Persona persona = usuario.getPersona();
			txtNombre.setValue(persona.getNombre()+" "+persona.getApellido());
			cmbGrupo.setValue(usuario.getGrupo() == null ? "": usuario.getGrupo().getNombre());
			txtCorreo.setDisabled(true);
			txtNombre.setDisabled(true);
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/grupo/usuario/frm-grupo-usuario-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	@Listen("onChange =#cmbCombo")
	public void cambiarGrupo(){
		if(cmbGrupo.getSelectedIndex() != -1){
			usuario.setGrupo((Grupo)cmbGrupo.getSelectedItem().getValue());
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if(cmbGrupo.getSelectedIndex() != -1){
			usuario.setGrupo((Grupo)cmbGrupo.getSelectedItem().getValue());
		}
		if (servicioUsuario.editarCliente(usuario.getPersona(), usuario)) {
			Messagebox.show("Edición exitosa se ha activado la función", "Información", Messagebox.OK, Messagebox.INFORMATION);
			String dir = "gc/grupo/usuario/frm-grupo-usuario-catalogo.zul";
			clearDivApp(dir);
		} else {
			Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/grupo/usuario/frm-grupo-usuario-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbFuncion")
	public void funcion() {
		String dir = "gc/funcion/frm-funcion-catalogo.zul";
		clearDivApp(dir);
	}

}
