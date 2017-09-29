package ucla.si.controlador.gc.baseDatos;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.dao.jdbc.BaseDatosDAO;
import ucla.si.modelo.Accion;
import ucla.si.servicio.ServicioAccion;

public class ControladorRespaldo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioAccion servicioAccion;
	
	private BaseDatosDAO baseDatosDAO = new BaseDatosDAO();
	
	@Wire
	private Listbox listTablas, listTablasRespaldo;
	
	@Wire
	private Combobox cmbRespaldo;
	
	private String rutaBackup = "";

	@Override
	protected void inicializar() {
		ListModelList<String> modeloTipoClases = new ListModelList<String>(baseDatosDAO.consultarTablas());
		modeloTipoClases.setMultiple(false);
		listTablas.setModel(modeloTipoClases);
		listTablas.setMultiple(false);
		listTablas.setCheckmark(false);
		listTablasRespaldo.setMultiple(false);
		listTablasRespaldo.setCheckmark(false);
		URL url = this.getClass().getResource("/");
		rutaBackup = url.toString().substring(0, url.toString().length()-16)+"prime/prime_prueba.backup";
		System.out.println("URL "+rutaBackup);
	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gc/accion/frm-accion-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#btnRespaldar")
	public void btnRespaldar(){
		if(cmbRespaldo.getSelectedIndex() != -1){
			if(cmbRespaldo.getSelectedIndex() == 0){
				baseDatosDAO.generarBackup(rutaBackup,"", true);
			}
			else{
				if(listTablasRespaldo.getItemCount() > 0){
					String tabla = "";
					List<Listitem> tablas = listTablasRespaldo.getItems();
					if(!tablas.isEmpty()){
						for (Listitem listitem : tablas) {
							//Se recorren todas las tablas y concatenan en el mismo atributo "tablas" agregando ", -t," antes de cada nombre de tabla
			                tabla += ",-t," + listitem.getValue().toString();
						}
					}
					baseDatosDAO.generarBackup(rutaBackup,tabla, true);
				}
				else{
					Messagebox.show("¡La lista debe tener al menos una Tabla!", "Error", Messagebox.OK,	Messagebox.ERROR);
				}
			}
		}
		else{
			Messagebox.show("¡Debe seleccionar un tipo de Respaldo!", "Error", Messagebox.OK,	Messagebox.ERROR);
		}
	}

}
