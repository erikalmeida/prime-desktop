package ucla.si.controlador.gc.web.redSocial;

import java.util.List;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;

import ucla.si.modelo.RedSocial;

import ucla.si.servicio.ServicioRedSocial;

public class ControladorConfiguracionRedSocial extends ControladorInicio {

	/**
	 * 
	 */

	@WireVariable
	private ServicioRedSocial servicioRedSocial;

	@Wire
	private Textbox txtTwitter, txtFacebook, txtInstagram, txtVimeo, txtGPlus, txtPinterest;

	private static final long serialVersionUID = 1L;

	private List<RedSocial> redesSociales;
			
			

	@Override
	protected void inicializar() {
		
		redesSociales = servicioRedSocial.listarRedesSociales();

		for (int i = 0; i < redesSociales.size(); i++) {
			System.out.println(redesSociales.get(i).getNombre());
		}

		if (redesSociales.get(0).getId() == 1) {
			txtTwitter.setValue(redesSociales.get(0).getUrl());
		}
		if (redesSociales.get(1).getId() == 2) {
			txtFacebook.setValue(redesSociales.get(1).getUrl());
		}
		if (redesSociales.get(2).getId() == 3) {
			txtInstagram.setValue(redesSociales.get(2).getUrl());
		}
		if (redesSociales.get(3).getId() == 4) {
			txtVimeo.setValue(redesSociales.get(3).getUrl());
		}
		if (redesSociales.get(4).getId() == 5) {
			txtGPlus.setValue(redesSociales.get(4).getUrl());
		}
		if (redesSociales.get(5).getId() == 6) {
			txtPinterest.setValue(redesSociales.get(5).getUrl());
		}

	}

	@Listen("onClick =#btnActualizar")
	public void aceptar() {
		try {
			
			redesSociales.get(0).setUrl(txtTwitter.getValue());
			servicioRedSocial.editarRedSocial(redesSociales.get(0));
			
			redesSociales.get(1).setUrl(txtFacebook.getValue());
			servicioRedSocial.editarRedSocial(redesSociales.get(1));
			
			redesSociales.get(2).setUrl(txtInstagram.getValue());
			servicioRedSocial.editarRedSocial(redesSociales.get(2));
			
			redesSociales.get(3).setUrl(txtVimeo.getValue());
			servicioRedSocial.editarRedSocial(redesSociales.get(3));
			
			redesSociales.get(4).setUrl(txtGPlus.getValue());
			servicioRedSocial.editarRedSocial(redesSociales.get(4));
			
			redesSociales.get(5).setUrl(txtPinterest.getValue());
			servicioRedSocial.editarRedSocial(redesSociales.get(5));
			
			inicializar();
			Messagebox.show("Actualización exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
			
			
		} catch (Exception e) {
			Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		
		
		
		
		
		
	}

}
