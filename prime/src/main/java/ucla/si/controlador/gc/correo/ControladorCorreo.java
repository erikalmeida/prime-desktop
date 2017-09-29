package ucla.si.controlador.gc.correo;

import org.zkforge.ckez.CKeditor;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.servicio.ServicioCorreo;

public class ControladorCorreo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioCorreo servicioCorreo;
	
	@Wire
	private Textbox txtDestinatario, txtAsunto, txtTitulo, txtSubTitulo;
	
	@Wire
	private CKeditor ckEditor;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
	}
	
	@Listen("onClick =#btnEnviar")
	public void enviar(){
		if(txtDestinatario.getValue().trim() == "" || txtAsunto.getValue().trim() == ""
				|| txtTitulo.getValue().trim() == "" || txtSubTitulo.getValue().trim() == ""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			String destinatario = txtDestinatario.getValue().trim().toLowerCase();
			String asunto = txtAsunto.getValue().trim();
			String titulo = txtTitulo.getValue().trim();
			String subTitulo = txtSubTitulo.getValue().trim();
			String mensaje = ckEditor.getValue();
			System.out.println("mensaje : "+ckEditor.getValue());
			//boolean enviado = servicioCorreo.enviarCorreo(destinatario, asunto, mensaje);
			boolean enviado = servicioCorreo.enviarCorreoHTML(destinatario, asunto, titulo, subTitulo, mensaje);
			if(enviado){
				Messagebox.show("Envio exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				Executions.sendRedirect("/prime/index.zul");
			}
			else{
				Messagebox.show("Error al Enviar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	@Listen("onClick =#btnCancelar")
	public void cancelar(){
		Executions.sendRedirect("/prime/index.zul");
	}

}
