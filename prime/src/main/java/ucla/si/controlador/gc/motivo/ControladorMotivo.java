package ucla.si.controlador.gc.motivo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Motivo;
import ucla.si.dao.MotivoDAO;
import ucla.si.servicio.ServicioMotivo;




public class ControladorMotivo extends ControladorInicio{private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioMotivo servicioMotivo;

	@Wire
	Textbox txt_descripcion;
	

	public ControladorMotivo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub

	}



	@Listen("onClick =#btnGuardar")
	public void registrarMotivo(){
		if(txt_descripcion.getValue().trim() == ""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			String descripcion = txt_descripcion.getValue().trim();
			String estatus = "Activo";
			Motivo motivo = new Motivo(descripcion, estatus,new Date(), null);
			
			if(servicioMotivo.incluirMotivo(motivo)){
				Messagebox.show("Registro exitoso", "Información", Messagebox.OK, Messagebox.INFORMATION);
				Executions.sendRedirect("index.zul");
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}