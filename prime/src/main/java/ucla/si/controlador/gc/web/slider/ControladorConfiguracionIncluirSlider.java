package ucla.si.controlador.gc.web.slider;

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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;

import ucla.si.modelo.Slider;

import ucla.si.servicio.ServicioSlider;
import ucla.si.utils.Urls;

public class ControladorConfiguracionIncluirSlider extends ControladorInicio {

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
	private Textbox txtBuscar;
	
	@Wire
	private org.zkoss.zul.Image pics;

	@WireVariable
	private WebApp _wapp;

	Slider slider;

	@Override
	protected void inicializar() {

	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/web/slider/cat-slider.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {

		if (this.pics.getAttribute("url_subida") == null) {
			if (this.pics.getSrc().equals("/static/img/img_question.png")) {
				Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);

			}
			Clients.showNotification("Debes seleccionar una imagen, haz click para seleccionar.", "error", this.pics,
					"end_center", 3000);

			return;
		}

		File f_temp = new File((String) this.pics.getAttribute("url_subida"));

		try {
			File dir = new File(this._wapp.getRealPath(Urls.URL_MEDIA_SLIDER));

			if (!dir.exists()) {
				dir.mkdirs();
			}
			File fo = new File(dir.getAbsolutePath() + "/" + f_temp.getName());

			// Lo movemos para no dejar copia en tmp.
			Files.move(f_temp.toPath(), fo.toPath());
			// Luego de moverlo no queda otra que limpiar las referencias ya
			// que el archivo deja de existir en el path original!
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

			String nombre = txtNombre.getValue().trim().toString();
			String descripcion = txtDescripcion.getValue().trim().toString();

			Slider slider = new Slider(nombre, descripcion, "Activo", new Date(), new Date(),
					Urls.URL_MEDIA_SLIDER + "/" + f_temp.getName());
			if (servicioSlider.incluirSlider(slider)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/web/slider/cat-slider.zul";
				clearDivAppWeb(dir);

			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
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
