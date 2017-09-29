package ucla.si.controlador.gc.funcion;

import java.util.Date;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Caja;
import ucla.si.modelo.Clase;
import ucla.si.modelo.Funcion;
import ucla.si.modelo.Sistema;
import ucla.si.modelo.TipoClase;
import ucla.si.servicio.ServicioClase;
import ucla.si.servicio.ServicioFuncion;
import ucla.si.servicio.ServicioTipoClase;

public class ControladorFuncionEditar extends ControladorInicio {

	/**
	 * 
	 */
	@WireVariable
	private ServicioFuncion servicioFuncion;

	@Wire
	private Textbox txtClave, txtNombre, txtDescripcion, txtIcono, txtUrl;

	@Wire
	private Combobox cmbFuncionPadre;

	private Sistema sistema;
	
	private Funcion funcion;

	@Override
	protected void inicializar() {
		ListModelList<Funcion> modeloFunciones = new ListModelList<Funcion>(servicioFuncion.listarFunciones());
		modeloFunciones.setMultiple(false);
		cmbFuncionPadre.setModel(modeloFunciones);
		cmbFuncionPadre.setMultiline(false);
		sistema = (Sistema) Sessions.getCurrent().getAttribute("sistema");
		if (sistema == null) {
			Messagebox.show("Error de sistema", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/funcion/frm-funcion-catalogo.zul";
			clearDivApp(dir);
		} 
		else {
			if(modeloFunciones.getSize() == 0){
				Messagebox.show("Debe Incluir una función primero", "Error", Messagebox.OK, Messagebox.ERROR);
				String dir = "gc/funcion/frm-funcion-incluir.zul";
				clearDivApp(dir);
			}
			else{
				modeloFunciones.setMultiple(false);
				cmbFuncionPadre.setModel(modeloFunciones);
				cmbFuncionPadre.setMultiline(false);
				funcion = (Funcion)getAtributo("funcion");
				cargarDatos(funcion);
			}
		}
	}

	public void cargarDatos(Funcion funcion) {
		if (funcion != null) {
			txtClave.setValue(funcion.getClave());
			txtNombre.setValue(funcion.getNombre());
			txtDescripcion.setValue(funcion.getDescripcion());
			txtIcono.setValue(funcion.getIcono());
			txtUrl.setValue(funcion.getUrl());;
			cmbFuncionPadre.setValue(funcion.getFuncionPadre() != null ? funcion.getFuncionPadre().getClave() : "");			
			txtClave.setDisabled(true);
			cmbFuncionPadre.setDisabled(true);
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/funcion/frm-funcion-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtClave.getValue().trim() == "" || txtNombre.getValue().trim() == ""
				|| txtDescripcion.getValue().trim() == "" || txtUrl.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}  else {
			funcion.setNombre(txtNombre.getValue().trim().toUpperCase());
			funcion.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			funcion.setIcono(txtIcono.getValue().trim());
			funcion.setUrl(txtUrl.getValue().trim());
			if(funcion.getEstatus().equalsIgnoreCase("Inactivo")){
				funcion.setEstatus("Activo");
				if (servicioFuncion.editarFuncion(funcion)) {
					Messagebox.show("Edición exitosa se ha activado la función", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/funcion/frm-funcion-catalogo.zul";
					clearDivApp(dir);
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
			else{
				if (servicioFuncion.editarFuncion(funcion)) {
					Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/funcion/frm-funcion-catalogo.zul";
					clearDivApp(dir);
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/funcion/frm-funcion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbFuncion")
	public void funcion() {
		String dir = "gc/funcion/frm-funcion-catalogo.zul";
		clearDivApp(dir);
	}

}
