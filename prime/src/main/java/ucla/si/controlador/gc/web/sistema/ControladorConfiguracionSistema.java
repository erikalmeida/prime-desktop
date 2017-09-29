package ucla.si.controlador.gc.web.sistema;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import org.zkoss.image.Image;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import sun.print.resources.serviceui;
import ucla.si.controlador.app.ControladorInicio;
import ucla.si.dao.NoticiaDAO;
import ucla.si.dao.SistemaDAO;
import ucla.si.modelo.Noticia;
import ucla.si.modelo.Sistema;
import ucla.si.modelo.Slider;
import ucla.si.servicio.ServicioNoticia;
import ucla.si.servicio.ServicioSistema;
import ucla.si.servicio.ServicioSlider;
import ucla.si.utils.Urls;

public class ControladorConfiguracionSistema extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox txtNombre, txtCorreo, txtTelefono, txtCelular, txtDescripcion, txtDireccion, txtMision, txtVision,
			txtFilosofia, txtMapa, txtTwitter;

	@Wire
	private org.zkoss.zul.Image pics;
	
	@Wire
	private Checkbox chkActivoCabezera, chkActivoTwitter;

	@WireVariable
	private WebApp _wapp;

	@WireVariable
	private ServicioSistema servicioSistema;

	@Override
	protected void inicializar() {

		if (servicioSistema.buscar().getNombre() != null) {
			txtNombre.setValue(servicioSistema.buscar().getNombre().toString());
		}
		if (servicioSistema.buscar().getCorreo() != null) {
			txtCorreo.setValue(servicioSistema.buscar().getCorreo().toString());
		}
		if (servicioSistema.buscar().getTelefono() != null) {
			txtTelefono.setValue(servicioSistema.buscar().getTelefono().toString());
		}
		if (servicioSistema.buscar().getCelular() != null) {
			txtCelular.setValue(servicioSistema.buscar().getCelular().toString());
		}
		if (servicioSistema.buscar().getDescripcion() != null) {
			txtDescripcion.setValue(servicioSistema.buscar().getDescripcion().toString());
		}
		if (servicioSistema.buscar().getDireccion() != null) {
			txtDireccion.setValue(servicioSistema.buscar().getDireccion().toString());
		}
		if (servicioSistema.buscar().getMision() != null) {
			txtMision.setValue(servicioSistema.buscar().getMision().toString());
		}
		if (servicioSistema.buscar().getVision() != null) {
			txtVision.setValue(servicioSistema.buscar().getVision().toString());
		}
		if (servicioSistema.buscar().getFilosofia() != null) {
			txtFilosofia.setValue(servicioSistema.buscar().getFilosofia().toString());
		}
		if (servicioSistema.buscar().getMapa() != null) {
			txtMapa.setValue(servicioSistema.buscar().getMapa().toString());
		}
		if (servicioSistema.buscar().getTimelineTwitter() != null) {
			txtTwitter.setValue(servicioSistema.buscar().getTimelineTwitter().toString());
		}
		if (servicioSistema.buscar().getUrlImagenCabezera() != null) {
			pics.setSrc(servicioSistema.buscar().getUrlImagenCabezera());
		}
		
		if (servicioSistema.buscar().getUrlImagenCabezeraVisible() != null) {
			chkActivoCabezera.setChecked(servicioSistema.buscar().getUrlImagenCabezeraVisible());
			if(servicioSistema.buscar().getUrlImagenCabezeraVisible()){
				chkActivoCabezera.setChecked(true);
			}else{
				chkActivoCabezera.setChecked(false);
			}
		}
		
		if (servicioSistema.buscar().getTimelineTwitterVisible() != null) {
			chkActivoTwitter.setChecked(servicioSistema.buscar().getTimelineTwitterVisible());
			if(servicioSistema.buscar().getTimelineTwitterVisible()){
				chkActivoTwitter.setChecked(true);
			}else{
				chkActivoTwitter.setChecked(false);
			}
		}
		
		
		
	}

	@Listen("onClick =#btnActualizar")
	public void aceptar() {
		Sistema sistema = servicioSistema.buscar();

		if (this.pics.getAttribute("url_subida") != null) {

			File f_temp = new File((String) this.pics.getAttribute("url_subida"));

			try {
				File dir = new File(this._wapp.getRealPath(Urls.URL_MEDIA_CABEZERA));

				if (!dir.exists()) {
					dir.mkdirs();
				}
				File fo = new File(dir.getAbsolutePath() + "/" + f_temp.getName());

				// Lo movemos para no dejar copia en tmp.
				Files.move(f_temp.toPath(), fo.toPath());
				// Luego de moverlo no queda otra que limpiar las referencias ya
				// que el archivo deja de existir en el path original!
				this.pics.setAttribute("url_subida", null);
				this.pics.setSrc("/static/img/img_cabezera.png");

			} catch (IOException e) {
				Messagebox.show("Error 100. No se pudo guardar el equipo.", "Error", Messagebox.OK, Messagebox.ERROR);
				e.printStackTrace();
			}
			sistema.setUrlImagenCabezera(Urls.URL_MEDIA_CABEZERA + "/" + f_temp.getName());
		}

		if (txtNombre.getValue().trim().toString().equals("")) {
			Messagebox.show("Debe llenar el campo Nombre", "Error", Messagebox.OK, Messagebox.ERROR);
			
		} else if (txtDescripcion.getValue().trim().toString().equals("")) {
			Messagebox.show("Debe llenar el campo Descripción", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			
			String nombre = txtNombre.getValue().trim().toString();
			String correo = txtCorreo.getValue().trim().toString();
			String telefono = txtTelefono.getValue().trim().toString();
			String celular = txtCelular.getValue().trim().toString();
			String descripcion = txtDescripcion.getValue().trim().toString();
			String direccion = txtDireccion.getValue().trim().toString();
			String mision = txtMision.getValue().trim().toString();
			String vision = txtVision.getValue().trim().toString();
			String filosofia = txtFilosofia.getValue().trim().toString();
			String twitter = txtTwitter.getValue().trim().toString();
			String mapa = txtMapa.getValue().trim().toString();
			Boolean urlImagenCabezeraVisible = chkActivoCabezera.isChecked();
			Boolean timelineTwitterVisible = chkActivoTwitter.isChecked();

			sistema.setNombre(nombre);
			sistema.setCorreo(correo);
			sistema.setTelefono(telefono);
			sistema.setCelular(celular);
			sistema.setDescripcion(descripcion);
			sistema.setDireccion(direccion);
			sistema.setMision(mision);
			sistema.setVision(vision);
			sistema.setFilosofia(filosofia);
			sistema.setTimelineTwitter(twitter);
			sistema.setMapa(mapa);
			sistema.setTimelineTwitterVisible(timelineTwitterVisible);
			sistema.setUrlImagenCabezeraVisible(urlImagenCabezeraVisible);

			if (servicioSistema.editarSistema(sistema)) {
				Messagebox.show("Actualización exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/web/configuracion-web.zul";
				clearDivApp(dir);
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
				f = File.createTempFile("img_cabezera", "." + media.getFormat());

				FileOutputStream fo = new FileOutputStream(f);
				fo.write(media.getByteData());
				fo.close();
				url_subida = f.getAbsolutePath();
				this.pics.setAttribute("url_subida", url_subida);
			} catch (IOException e1) {
				Messagebox.show("Error 101, no se pudo subir la imagen", "Error", Messagebox.OK, Messagebox.ERROR);
				this.pics.setAttribute("url_subida", null);
				this.pics.setSrc("/static/img/img_cabezera.png");
				Messagebox.show("ERROR! no se pudo subir la imagen");
				e1.printStackTrace();
			}

			this.pics.setWidth("92%");
			this.pics.setHeight("40px");
			this.pics.setContent((Image) media);

		} else {
			this.pics.setSrc("/static/img/img_cabezera.png");
			this.pics.setAttribute("url_subida", null);
			Messagebox.show("El archivo no es una imagen: " + media, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

}
