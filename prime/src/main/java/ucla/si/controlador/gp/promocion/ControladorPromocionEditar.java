package ucla.si.controlador.gp.promocion;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Color;
import ucla.si.modelo.Grupo;
import ucla.si.modelo.Promocion;
import ucla.si.modelo.Servicio;
import ucla.si.servicio.ServicioPromocion;
import ucla.si.servicio.ServicioServicio;

public class ControladorPromocionEditar extends ControladorInicio{

	/**
	 * 
	 */
	
	@WireVariable
	private Promocion promocion;
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioPromocion servicioPromocion;
	
	@WireVariable
	private ServicioServicio servicioServicio;
	
	
	@Wire
	private org.zkoss.zul.Image pics;
	
	@Wire
	Textbox  txtDescripcion,txtTitulo,txtEstatus;
	
	@Wire
	Combobox cmb_estado;

	@WireVariable
	private WebApp _wapp;

	@Wire
	Doublebox dbDescuento;
	
	@Wire
	Listbox listarServicio;
	
	@Wire
	Datebox dboxFechaIni, dboxFechaFin;


	@Wire
	private Combobox cmbServicios;

	
	
	@Override
	protected void inicializar() {
		promocion = (Promocion)getAtributo("promocion");
		cargarDatos(promocion);
		
		ListModelList<Servicio> modeloServicios = new ListModelList<Servicio>(servicioServicio.listarServicios());
		
		modeloServicios.setMultiple(false);
		cmbServicios.setModel(modeloServicios);
		cmbServicios.setMultiline(false);
		
		
	}
	
	
	
	public void cargarDatos(Promocion promocion){
		if(promocion != null){
			txtDescripcion.setValue(promocion.getDescripcion().trim());
			txtTitulo.setValue(promocion.getTitulo().trim());
			//txtEstatus.setValue(promocion.getEstatus().trim());
			cmb_estado.setValue(promocion.getEstado().trim());
			dbDescuento.setValue(promocion.getDescuento());
			dboxFechaFin.setValue(promocion.getFechaVigenciaFin());
			dboxFechaIni.setValue(promocion.getFechaVigenciaInicio());
			
			cmbServicios.setValue(promocion.getServicio().getTitulo());
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gp/promocion/frm-promocion-editar.zul";
			clearDivApp(dir);
		}
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtDescripcion.getValue().trim() == "" || txtTitulo.getValue().trim() ==""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			
			promocion.setDescripcion(txtDescripcion.getValue().trim());
			promocion.setTitulo(txtTitulo.getValue().trim());
			//promocion.setEstatus(txtEstatus.getValue().trim());
			promocion.setEstado(cmb_estado.getValue());
			promocion.setDescuento(dbDescuento.getValue());
			promocion.setFechaVigenciaInicio(dboxFechaIni.getValue());
			promocion.setFechaVigenciaFin(dboxFechaFin.getValue());
			promocion.setServicio((Servicio)cmbServicios.getSelectedItem().getValue());
			
			if(servicioPromocion.editarPromocion(promocion)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gp/promocion/frm-promocion-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
