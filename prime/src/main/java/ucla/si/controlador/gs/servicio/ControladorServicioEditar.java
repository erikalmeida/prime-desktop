package ucla.si.controlador.gs.servicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Grupo;
import ucla.si.modelo.Servicio;
import ucla.si.servicio.ServicioServicio;
import ucla.si.utils.Urls;

public class ControladorServicioEditar extends ControladorInicio {

	/**
	 * 
	 */

	private Servicio servicio;
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioServicio servicioServicio;

	@Wire
	Textbox txtEstado, txtDescripcion, txtTitulo, txtUrlImagen, txtEstatus;

	@Wire
	Doublebox dbTarifa;

	@Wire
	private org.zkoss.zul.Image pics;
	@WireVariable
	private WebApp _wapp;

	@Override
	protected void inicializar() {
		servicio = (Servicio) getAtributo("servicio");
		cargarDatos(servicio);
	}

	public void cargarDatos(Servicio servicio) {
		if (servicio != null) {
			txtDescripcion.setValue(servicio.getDescripcion().trim());
			// txtEstado.setValue(servicio.getEstado().trim());
			txtTitulo.setValue(servicio.getTitulo().trim());
			pics.setSrc(servicio.getUrlImagen());
			// txtEstatus.setValue(servicio.getEstatus().trim());
			dbTarifa.setValue(servicio.getTarifa());
			// txtDescripcion.setReadonly(true);
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gs/servicio/frm-servicio-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
	/*	if (this.pics.getAttribute("url_subida") != null) {
			File f_temp = new File((String) this.pics.getAttribute("url_subida"));
			try {
				File dir = new File(this._wapp.getRealPath(Urls.URL_MEDIA_NOTICIA));
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
			}*/

			if (txtDescripcion.getValue().trim() == "" || txtDescripcion.getValue().trim() == "") {
				Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
			} else {
			//	servicio.setUrlImagen(Urls.URL_MEDIA_NOTICIA + "/" + f_temp.getName());
				servicio.setDescripcion(txtDescripcion.getValue().trim());
				servicio.setTitulo(txtTitulo.getValue().trim());
				servicio.setTarifa(dbTarifa.getValue());
				if (servicioServicio.editarServicio(servicio)) {
					Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gs/servicio/frm-servicio-catalogo.zul";
					clearDivApp(dir);
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		}

	}
//}
