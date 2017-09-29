package ucla.si.controlador.gp.promocion;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ComboBox;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Aceite;
import ucla.si.modelo.Cita;
import ucla.si.modelo.Color;
import ucla.si.modelo.Profesion;
import ucla.si.modelo.Promocion;
import ucla.si.modelo.Servicio;
import ucla.si.servicio.ServicioPromocion;
import ucla.si.servicio.ServicioServicio;

public class ControladorPromocionConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Promocion promocion;

	@WireVariable
	private ServicioPromocion servicioPromocion;

	@WireVariable
	private ServicioServicio servicioServicio;

	@Wire
	Combobox cmbServicios, cmb_estado;

	@Wire
	Textbox txtEstado, txtDescripcion, txtNombre, txtUrlImagen, txtEstatus, txtServicio,txtTitulo;

	@Wire
	Datebox dboxFechaIni, dboxFechaFin;

	@Wire
	Doublebox dbTarifa, dbDescuento;

	@Override
	protected void inicializar() {
		this.promocion = (Promocion) getAtributo("promocion");
		
		cargarDatos(promocion);

	/*	 ListModelList<Servicio> modeloServicios = new ListModelList<Servicio>(servicioServicio.listarServicios());
		 modeloServicios.setMultiple(false);
			cmbServicios.setModel(modeloServicios);
			cmbServicios.setMultiline(false); */
		 
	}

	public void cargarDatos(Promocion promocion) {
		if (promocion != null) {

			txtTitulo.setValue(promocion.getTitulo());
			txtTitulo.setDisabled(true);
			txtTitulo.setReadonly(true);

			txtDescripcion.setValue(promocion.getDescripcion());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);


			dboxFechaIni.setValue(promocion.getFechaVigenciaInicio());
			dboxFechaIni.setDisabled(true);
			dboxFechaIni.setReadonly(true);

			dboxFechaFin.setValue(promocion.getFechaVigenciaFin());
			dboxFechaFin.setDisabled(true);
			dboxFechaFin.setReadonly(true);
			
			cmbServicios.setValue(promocion.getServicio().getTitulo());
			cmbServicios.setDisabled(true);
			cmbServicios.setReadonly(true);
		
			// cmbServicios.setValue(promocion.getServicio().getTitulo());
			// cmbServicios.setDisabled(false);
			// cmbServicios.setReadonly(false);

			//List<Servicio> servicios = new ArrayList<Servicio>();
			//servicios.add((Servicio) promocion.getServicio());
			//System.out.println(servicios.get(0).getTitulo());

			//ListModelList<Servicio> modeloServicios = new ListModelList<Servicio>(
				//	servicios);
			//modeloServicios.setMultiple(false);

			//cmbServicios.setModel(modeloServicios);
			/*
			cmbServicios.setValue(promocion.getServicio().getTitulo());
			cmbServicios.setDisabled(false);
			cmbServicios.setReadonly(false);*/

			dbDescuento.setValue(promocion.getDescuento());
			dbDescuento.setDisabled(true);
			dbDescuento.setReadonly(true);

			cmb_estado.setValue(promocion.getEstado());
			cmb_estado.setDisabled(true);
			cmb_estado.setReadonly(true);
			/*
			 * cmbPasatiempo.setValue(promocion.getVehiculo().getUsuario().
			 * getPersona().getPasatiempo().getDescripcion());
			 * cmbPasatiempo.setDisabled(false);
			 * cmbPasatiempo.setReadonly(false);
			 * 
			 * cmbProfesion.setValue(promocion.getVehiculo().getUsuario().getPersona
			 * ().getProfesion().getDescripcion());
			 * cmbProfesion.setDisabled(false); cmbProfesion.setReadonly(false);
			 * 
			 * cmbOcupacion.setValue(promocion.getVehiculo().getUsuario().getPersona
			 * ().getOcupacion().getDescripcion());
			 * cmbOcupacion.setDisabled(false); cmbOcupacion.setReadonly(false);
			 */

			// --------------------- Vehiculo
			/*
			 * txtMarca.setValue(promocion.getVehiculo().getMarca().getNombre());
			 * txtMarca.setDisabled(true); txtMarca.setReadonly(true);
			 * 
			 * txtPlaca.setValue(promocion.getVehiculo().getPlaca());
			 * txtPlaca.setDisabled(true); txtPlaca.setReadonly(true);
			 * 
			 * 
			 * txtModelo.setValue(promocion.getVehiculo().getModelo().getNombre()
			 * ); txtModelo.setDisabled(true); txtModelo.setReadonly(true);
			 * 
			 * intAnno.setValue(promocion.getVehiculo().getAnno());
			 * intAnno.setDisabled(false); intAnno.setReadonly(false);
			 * 
			 * txtSerialCarro.setValue(promocion.getVehiculo().getSerialCarroceria
			 * ()); txtSerialCarro.setDisabled(false);
			 * txtSerialCarro.setReadonly(false);
			 * 
			 * txtSerialMotor.setValue(promocion.getVehiculo().getSerialMotor());
			 * txtSerialMotor.setDisabled(false);
			 * txtSerialMotor.setReadonly(false);
			 * 
			 * doubleKilometraje.setValue(promocion.getVehiculo().getKilometraje(
			 * )); doubleKilometraje.setDisabled(false);
			 * doubleKilometraje.setReadonly(false);
			 * 
			 * intNroPuestos.setValue(promocion.getVehiculo().getNroPuestos());
			 * intNroPuestos.setDisabled(false);
			 * intNroPuestos.setReadonly(false);
			 * 
			 * /* txtServicio.setValue(promocion.getServicio().getTitulo());
			 * txtServicio.setDisabled(true); txtServicio.setReadonly(true);
			 * 
			 * txtReferido.setValue(promocion.getNombreReferido());
			 * txtReferido.setDisabled(true); txtReferido.setReadonly(true);
			 */
		}
	}

	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar() {
		String dir = "gp/promocion/frm-promocion-catalogo.zul";
		clearDivApp(dir);
	}

}
