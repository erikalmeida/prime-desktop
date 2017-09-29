package ucla.si.controlador.gc.maestrico.pasatiempo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Pasatiempo;
import ucla.si.servicio.ServicioPasatiempo;

public class ControladorPasatiempoCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pasatiempo pasatiempo;
	@WireVariable
	private ServicioPasatiempo servicioPasatiempo;
	
	@Wire
	Listbox listPasatiempos;
	
	@Wire
	Textbox txtBuscar;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		ListModelList<Pasatiempo> modeloPasatiempos = new ListModelList<Pasatiempo>(servicioPasatiempo.listarPasatiempos());
		if(modeloPasatiempos.getSize() != 0){
			modeloPasatiempos.setMultiple(false);
			listPasatiempos.setModel(modeloPasatiempos);
			listPasatiempos.setMultiple(false);
			listPasatiempos.setCheckmark(false);			
		}

	}
	
	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "pc/pasatiempo/frm-pasatiempo-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		System.out.println(txtBuscar.getValue().trim().toString());
		ListModelList<Pasatiempo> modeloPasatiempos = new ListModelList<Pasatiempo>(
				servicioPasatiempo.buscarPasatiempos(txtBuscar.getValue().trim().toString()));
		modeloPasatiempos.setMultiple(false);
		listPasatiempos.setModel(modeloPasatiempos);
		listPasatiempos.setMultiple(false);
		listPasatiempos.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onAfterRender =#listPasatiempos")
	public void actualizarListbox() {
		if(listPasatiempos.getItemCount() > 0){
			asignarEventos(listPasatiempos);
		}
	}
	
	private void asignarEventos(Component padre) {
		String click = "onClick";
		for (Component hijo : padre.getChildren()){
			if (hijo instanceof Button){
				hijo.addEventListener(click, new EventListener<MouseEvent>() {
					public void onEvent(MouseEvent mouseEvent) throws Exception {
						verificarAcciones(mouseEvent);
					}
				});
			}			
			asignarEventos(hijo);			
		}
	}
	
	public void verificarAcciones(MouseEvent mouseEvent) {
		try {
			Button boton = (Button) mouseEvent.getTarget();
			Pasatiempo pasatiempo = (Pasatiempo) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("pasatiempo", pasatiempo);
				String dir = "pc/pasatiempo/frm-pasatiempo-editar.zul";
				clearDivApp(dir);
			}else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("pasatiempo", pasatiempo);
				String dir = "pc/pasatiempo/frm-pasatiempo-consultar.zul";
				clearDivApp(dir);
			}
			else if (boton.getTooltiptext().equals("Eliminar")) {
				pasatiempo.setEstatus("Inactivo");

				if (servicioPasatiempo.editarPasatiempo(pasatiempo)) {
					Messagebox.show("Eliminacion exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "pc/pasatiempo/frm-pasatiempo-catalogo.zul";
					clearDivApp(dir);
				}

				else {
					Messagebox.show("Error al eliminarr", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			} 
		}
		catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información",Messagebox.OK, Messagebox.ERROR);
		} 
		catch (org.hibernate.TransactionException e){
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información",Messagebox.OK, Messagebox.ERROR);
		}
	}

	
}
