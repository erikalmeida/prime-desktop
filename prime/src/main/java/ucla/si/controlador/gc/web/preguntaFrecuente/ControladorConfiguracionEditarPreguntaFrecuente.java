package ucla.si.controlador.gc.web.preguntaFrecuente;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.zkforge.ckez.CKeditor;
import org.zkoss.image.Image;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Color;
import ucla.si.modelo.Noticia;
import ucla.si.modelo.PreguntaFrecuente;
import ucla.si.modelo.Slider;
import ucla.si.servicio.ServicioColor;
import ucla.si.servicio.ServicioNoticia;
import ucla.si.servicio.ServicioPreguntaFrecuente;
import ucla.si.utils.Urls;

public class ControladorConfiguracionEditarPreguntaFrecuente extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioPreguntaFrecuente servicioPreguntaFrecuente;

	@WireVariable
	private WebApp _wapp;
	
	
	@Wire
	private Textbox txtRespuesta;
	
	@Wire
	private Textbox txtPregunta;
	
	@Wire
	private Caption tituloPreguntaFrecuente;

	@Wire
	private Checkbox chkActivo;
	
	private PreguntaFrecuente preguntaFrecuente;
	
	@Wire
	private CKeditor ckPregunta;
	
	@Wire
	private CKeditor ckRespuesta;

	@Override
	protected void inicializar() {
		this.preguntaFrecuente = (PreguntaFrecuente) getAtributo("preguntaFrecuente");
		ckPregunta.setValue(this.preguntaFrecuente.getPregunta().toString());
		ckRespuesta.setValue(this.preguntaFrecuente.getRespuesta().toString());
		txtPregunta.setValue(this.preguntaFrecuente.getPregunta().toString());
		txtRespuesta.setValue(this.preguntaFrecuente.getRespuesta().toString());
		
		tituloPreguntaFrecuente.setLabel("Editar: " + this.preguntaFrecuente.getPregunta());
		if (preguntaFrecuente.getEstatus().equals("Activo")) {
			chkActivo.setChecked(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/web/preguntaFrecuente/cat-pregunta-frecuente.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {

		if (txtRespuesta.getValue().trim().toString().equals("")
				|| txtPregunta.getValue().trim().toString().equals("")) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {

			String respuesta = txtRespuesta.getValue().trim().toString();
			String pregunta = txtPregunta.getValue().trim().toString();
			
			if (!chkActivo.isChecked()) {
				this.preguntaFrecuente.setEstatus("Inactivo");
			}else{
				this.preguntaFrecuente.setEstatus("Activo");					
			}
			
			this.preguntaFrecuente.setPregunta(pregunta);
			this.preguntaFrecuente.setRespuesta(respuesta);

			if (servicioPreguntaFrecuente.editarPreguntaFrecuente(preguntaFrecuente)) {
				Messagebox.show("Actualización exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/web/preguntaFrecuente/cat-pregunta-frecuente.zul";
				clearDivAppWeb(dir);

			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}

	}

}
