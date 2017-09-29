package ucla.si.controlador.gc.maestrico.caja;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Caja;
import ucla.si.modelo.TipoCaja;
import ucla.si.servicio.ServicioCaja;
import ucla.si.servicio.ServicioTipoCaja;

public class ControladorCajaIncluir extends ControladorInicio{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoCaja servicioTipoCaja;
	
	@WireVariable
	private ServicioCaja servicioCaja;
		
	@Wire
	private Textbox txtDescripcion;
	
	@Wire
	private Combobox cmbTipoCaja;
		
	@Override
	protected void inicializar() {
		ListModelList<TipoCaja> modeloTipoCajas = new ListModelList<TipoCaja>(servicioTipoCaja.listarTipoCajas());
		if(modeloTipoCajas.getSize() == 0){
			Messagebox.show("Debe Incluir un Tipo de Caja primero", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoCaja/frm-tipoCaja-incluir.zul";
			clearDivApp(dir);
		}
		else{
			modeloTipoCajas.setMultiple(false);
			cmbTipoCaja.setModel(modeloTipoCajas);
			cmbTipoCaja.setMultiline(false);
		}
	}
		
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtDescripcion.getValue().trim() =="" || cmbTipoCaja.getSelectedIndex() == -1){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			
			Caja caja = new Caja(descripcion,new Date(), null, "Activo");
			TipoCaja tipoCaja = (TipoCaja)cmbTipoCaja.getSelectedItem().getValue();
			caja.setTipoCaja(tipoCaja);
			if(servicioCaja.incluirCaja(caja)){
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/caja/frm-caja-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
