package ucla.si.controlador.gc.cliente;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.zkoss.image.Image;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.utils.ImageUtils;
import ucla.si.utils.Urls;
import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Usuario;
import ucla.si.servicio.ServicioUsuario;

import java.awt.image.RenderedImage;

import org.zkoss.image.Images;
import org.zkoss.lang.Objects;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.ext.render.DynamicMedia;
import org.zkoss.zul.impl.Utils;
import org.zkoss.zul.impl.XulElement;

public class ControladorCliente extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioUsuario servicioUsuario;

	@Wire
	Textbox txt_nombre, txt_apellido, txt_cedula, txt_url_foto, txt_direccion, txt_telefono, txt_correo,
			txt_contrasenna, txt_pregunta_secreta, txt_respuesta_secreta;
	@Wire
	Combobox cmb_sexo;
	@Wire
	Datebox dtb_fecha_nac;
	@Wire
	private org.zkoss.zul.Image pics;

	@WireVariable
	private WebApp _wapp;

	public ControladorCliente() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub

	}

	@Listen("onUpload=#btnSubirImagen")
	public void subirImagenLogo(UploadEvent event) {
		org.zkoss.util.media.Media media = event.getMedia();

		if (media instanceof org.zkoss.image.Image) {
			File f;
			String url_subida = "";
			try {
				f = File.createTempFile("img_team_logo", "." + media.getFormat());

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

			this.pics.setWidth("120px");
			this.pics.setHeight("120px");
			this.pics.setContent((Image) media);

		} else {
			this.pics.setSrc("/static/img/img_question.png");
			this.pics.setAttribute("url_subida", null);
			Messagebox.show("El archivo no es una imagen: " + media, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Listen("onClick =#btn_registrar")
	public void registrar() {
		System.out.println("Registro Usuario");

		if (this.pics.getAttribute("url_subida") == null) {
			Clients.showNotification("Debes seleccionar una imagen, haz click para seleccionar.", "error", this.pics,
					"end_center", 3000);

			return;
		}

		File f_temp = new File((String) this.pics.getAttribute("url_subida"));

		try {
			File dir = new File(this._wapp.getRealPath(Urls.URL_MEDIA_USER));

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

		System.out.println(pics);

		if (txt_nombre.getValue().trim() == "" || txt_apellido.getValue().trim() == ""
				|| txt_cedula.getValue().trim() == "" || txt_direccion.getValue().trim() == ""
				|| txt_correo.getValue().trim() == "" || txt_contrasenna.getValue().trim() == ""
				|| txt_pregunta_secreta.getValue().trim() == "" || txt_respuesta_secreta.getValue().trim() == ""
				|| cmb_sexo.getSelectedItem().getValue() == "" || dtb_fecha_nac.getValue() == null) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String nombre = txt_nombre.getValue().trim();
			String apellido = txt_apellido.getValue().trim();
			String cedula = txt_cedula.getValue().trim();
			String url_foto = txt_url_foto.getValue().trim();
			String sexo = cmb_sexo.getValue().trim();
			String direccion = txt_direccion.getValue().trim();
			String telefono = txt_telefono.getValue().trim();
			Date fecha_nacimiento = dtb_fecha_nac.getValue();
			String estatus = "Activo";

			Persona persona = new Persona(nombre, apellido, cedula, Urls.URL_MEDIA_USER + "/" + f_temp.getName(), sexo, direccion, telefono,
					fecha_nacimiento, estatus, new Date(), null);
			String correo = txt_correo.getValue().trim().toLowerCase();
			String contrasenna = DigestUtils.md5Hex(txt_contrasenna.getValue().trim());
			String pregunta = txt_pregunta_secreta.getValue().trim();
			String respuesta = txt_respuesta_secreta.getValue().trim();
			Usuario usuario = new Usuario(correo, contrasenna, pregunta, respuesta, estatus, new Date(), null);

			if (servicioUsuario.registrarCliente(persona, usuario)) {
				Messagebox.show("Registro exitoso", "Información", Messagebox.OK, Messagebox.INFORMATION);
				Executions.sendRedirect("/");
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}

	}

}
