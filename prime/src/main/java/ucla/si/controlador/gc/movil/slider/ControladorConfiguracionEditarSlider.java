package ucla.si.controlador.gc.movil.slider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

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

import ucla.si.modelo.Slider;
import ucla.si.modelo.TipoServicio;
import ucla.si.servicio.ServicioSlider;
import ucla.si.utils.Urls;

public class ControladorConfiguracionEditarSlider extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioSlider servicioSlider;

	@Wire
	private Textbox txtDescripcion;

	@Wire
	private Textbox txtNombre;

	@Wire
	private org.zkoss.zul.Image pics;

	@WireVariable
	private WebApp _wapp;

	private Slider slider;

	@Wire
	private Caption tituloSlider;
	
	@Wire
	private Checkbox chkActivo;

	@Override
	protected void inicializar() {
		this.slider = (Slider) getAtributo("slider");
		txtDescripcion.setValue(slider.getDescripcion());
		txtNombre.setValue(slider.getNombre());
		pics.setSrc(slider.getUrlImagen());
		tituloSlider.setLabel("Editar: " + slider.getNombre());
		if (slider.getEstatus().equals("Activo")) {
			chkActivo.setChecked(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/movil/slider/cat-slider.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (this.pics.getAttribute("url_subida") != null) {
			File f_temp = new File((String) this.pics.getAttribute("url_subida"));
			try {
				File dir = new File(this._wapp.getRealPath(Urls.URL_MEDIA_SLIDER));
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File fo = new File(dir.getAbsolutePath() + "/" + f_temp.getName());
				Files.move(f_temp.toPath(), fo.toPath());
				this.pics.setAttribute("url_subida", null);
				this.pics.setSrc("/static/img/img_question.png");
			} catch (IOException e) {
				Messagebox.show("Error 100. No se pudo guardar el equipo.", "Error", Messagebox.OK, Messagebox.ERROR);
				e.printStackTrace();
			}
			if (txtDescripcion.getValue().trim().toString().equals("")
					|| txtNombre.getValue().trim().toString().equals("")) {
				Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
			} else {
				this.slider.setDescripcion(txtDescripcion.getValue().trim().toString());
				this.slider.setNombre(txtNombre.getValue().trim().toString());
				this.slider.setUrlImagen(Urls.URL_MEDIA_SLIDER + "/" + f_temp.getName());
				if (!chkActivo.isChecked()) {
					this.slider.setEstatus("Inactivo");
				}else{
					this.slider.setEstatus("Activo");					
				}
				if (servicioSlider.editarSlider(slider)) {
					Messagebox.show("Actualización exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/movil/slider/cat-slider.zul";
					clearDivAppWeb(dir);
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		} else {

			if (txtDescripcion.getValue().trim().toString().equals("")
					|| txtNombre.getValue().trim().toString().equals("")) {
				Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
			} else {
				this.slider.setDescripcion(txtDescripcion.getValue().trim().toString());
				this.slider.setNombre(txtNombre.getValue().trim().toString());
				if (!chkActivo.isChecked()) {
					this.slider.setEstatus("Inactivo");
				}else{
					this.slider.setEstatus("Activo");					
				}
				if (servicioSlider.editarSlider(slider)) {
					Messagebox.show("Actualización exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gc/movil/slider/cat-slider.zul";
					clearDivAppWeb(dir);
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}

		}

	}

	@Listen("onUpload=#btnSubirImagen")
	public void subirImagenLogo(UploadEvent event) {
		org.zkoss.util.media.Media media = event.getMedia();

		if (media instanceof org.zkoss.image.Image) {
			File f;
			String url_subida = "";
			try {
				f = File.createTempFile("img_slider", "." + media.getFormat());

				FileOutputStream fo = new FileOutputStream(f);
				fo.write(media.getByteData());
				fo.close();
				url_subida = f.getAbsolutePath();
				this.pics.setAttribute("url_subida", url_subida);
			} catch (IOException e1) {
				Messagebox.show("Error 101, no se pudo subir la imagen", "Error", Messagebox.OK, Messagebox.ERROR);
				this.pics.setAttribute("url_subida", null);
				this.pics.setSrc("/static/img/img_question.png");
				Messagebox.show("ERROR! no se pudo subir la imagen");
				e1.printStackTrace();
			}

			this.pics.setWidth("150px");
			this.pics.setHeight("75px");
			this.pics.setContent((Image) media);

		} else {
			this.pics.setSrc("/static/img/img_question.png");
			this.pics.setAttribute("url_subida", null);
			Messagebox.show("El archivo no es una imagen: " + media, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

}
