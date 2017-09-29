package ucla.si.controlador.gc.maestrico.modelo;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Modelo;
import ucla.si.modelo.Modelo;
import ucla.si.modelo.Marca;
import ucla.si.servicio.ServicioMarca;
import ucla.si.servicio.ServicioModelo;

public class ControladorModeloIncluir extends ControladorInicio{

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
		
	@Override
	protected void inicializar() {
		ListModelList<Marca> modeloMarcas = new ListModelList<Marca>(servicioMarca.listarMarcas());
		if(modeloMarcas.getSize() == 0){
			Messagebox.show("Debe Incluir una Marca primero", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/marca/frm-marca-incluir.zul";
			clearDivApp(dir);
		}
		//else{
			modeloMarcas.setMultiple(false);
			cmbMarca.setModel(modeloMarcas);
			cmbMarca.setMultiline(false);
		//}
	}
		
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtDescripcion.getValue().trim() =="" || cmbMarca.getSelectedIndex() == -1){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			String nombre = txtDescripcion.getValue().trim().toUpperCase();
			
			Modelo modelo = new Modelo(nombre, "Activo", new Date(), null);
			Marca marca = (Marca)cmbMarca.getSelectedItem().getValue();
			modelo.setMarca(marca);
			if(servicioModelo.incluirModelo(modelo)){
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/modelo/frm-modelo-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
