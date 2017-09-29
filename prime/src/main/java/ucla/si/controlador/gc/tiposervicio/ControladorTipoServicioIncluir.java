package ucla.si.controlador.gc.tiposervicio;
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
import ucla.si.modelo.TipoServicio;
import ucla.si.modelo.Servicio;
import ucla.si.dao.TipoServicioDAO;
import ucla.si.servicio.ServicioTipoServicio;



import ucla.si.servicio.ServicioTipoServicio;

public class ControladorTipoServicioIncluir extends ControladorInicio{private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoServicio servicioTipoServicio;

	@Wire
	Textbox txt_descripcion;
	

	public ControladorTipoServicioIncluir() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub

	}


	@Listen("onClick =#btnGuardar")
	public void registrarTipoServicio(){
		if(txt_descripcion.getValue().trim() == ""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			String descripcion = txt_descripcion.getValue().trim();
			String estatus = "Activo";
			TipoServicio tipoServicio = new TipoServicio(descripcion, estatus,new Date(), null);
			
			if(servicioTipoServicio.incluirTipoServicio(tipoServicio)){
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoServicio/frm-tipoServicio-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
