package ucla.si.controlador.gc.maestrico.modelo;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Modelo;

import ucla.si.modelo.Marca;
import ucla.si.servicio.ServicioMarca;
import ucla.si.servicio.ServicioModelo;


public class ControladorModeloEditar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioModelo servicioModelo;
	
	@WireVariable
	private ServicioMarca servicioMarca;
	
	@Wire
	Textbox txtDescripcion;
	
	@Wire
	private Combobox cmbMarca;
	
	
	private Modelo modelo;

	
	
	
	@Override
	protected void inicializar() {
		ListModelList<Marca> modeloMarcas = new ListModelList<Marca>(servicioMarca.listarMarcas());
		if(modeloMarcas.getSize() == 0){
			Messagebox.show("Debe Incluir un Tipo de Modelo primero", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/marca/frm-marca-incluir.zul";
			clearDivApp(dir);
		}
		else{
			modeloMarcas.setMultiple(false);
			cmbMarca.setModel(modeloMarcas);
			cmbMarca.setMultiline(false);
			modelo = (Modelo)getAtributo("modelo");
			cargarDatos(modelo);
		}
	}
	
	public void cargarDatos(Modelo modelo){
		if(modelo != null){
			txtDescripcion.setValue(modelo.getNombre().trim());
			
			cmbMarca.setValue(modelo.getMarca().getNombre());
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/modelo/frm-modelo-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtDescripcion.getValue().trim() == "" || cmbMarca.getSelectedIndex() == -1){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			modelo.setNombre(txtDescripcion.getValue().trim().toUpperCase());
			
			modelo.setMarca((Marca) cmbMarca.getSelectedItem().getValue());
			if(servicioModelo.editarModelo(modelo)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/modelo/frm-modelo-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	

}
