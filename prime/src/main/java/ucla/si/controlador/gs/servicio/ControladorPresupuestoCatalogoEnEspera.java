package ucla.si.controlador.gs.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.metamodel.SetAttribute;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Falla;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.Servicio;
import ucla.si.servicio.ServicioFalla;
import ucla.si.servicio.ServicioPresupuesto;
import ucla.si.servicio.ServicioServicio;

public class ControladorPresupuestoCatalogoEnEspera extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioPresupuesto servicioPresupuesto;

	@Wire
	Listbox listPresupuestosE;

	@Override
	protected void inicializar() {

		ListModelList<Presupuesto> modeloPresupuestos = new ListModelList<Presupuesto>(
				servicioPresupuesto.listarPresupuestos());
		modeloPresupuestos.setMultiple(false);
		listPresupuestosE.setModel(modeloPresupuestos);
		listPresupuestosE.setMultiple(false);
		listPresupuestosE.setCheckmark(true);

		/*
		 * ListModelList<Falla> modeloFallas = new
		 * ListModelList<Falla>(servicioFalla.listarFallas());
		 * modeloFallas.setMultiple(false); listFallas.setModel(modeloFallas);
		 * listFallas.setMultiple(false); listFallas.setCheckmark(false);
		 */

	}

	@Listen("onClick =#btnActualizar")
	public void annadirConfigurar() {
		System.out.println("entro bien");
		List<Listitem> listItemPresupuestosV = new ArrayList<Listitem>(listPresupuestosE.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		if (!listPresupuestosE.getItems().isEmpty()) {
			for (Listitem listitem : listItemPresupuestosV) {
				if (listitem.isSelected()) {
					Presupuesto presupuesto = (Presupuesto) listitem.getValue();
					if (presupuesto != null) {
						// System.out.println("El servicio: "+
						// servicio.getDescripcion()+" su id es:
						// "+servicio.getId());

						setAtributo("presupuesto", presupuesto);
						String dir = "gs/presupuesto/frm-validar-presupuesto.zul";
						clearDivApp(dir);

					}
				}
			}
		}
	}

	@Listen("onClick =#btnDetalle")
	public void detallePresupuesto() {
		//String dir = "gs/presupuesto/frm-validar-presupuesto.zul";
		//clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");

		List<Listitem> listItemPresupuestosE = new ArrayList<Listitem>(listPresupuestosE.getItems());
		if (!listPresupuestosE.getItems().isEmpty()) {
			for (Listitem listitem : listItemPresupuestosE) {
				if (listitem.isSelected()) {
					Presupuesto presupuesto = (Presupuesto) listitem.getValue();
					if (presupuesto != null) {
						// System.out.println("El servicio: "+
						// se.getDescripcion()+" su id es: "+servicio.getId());

						setAtributo("presupuesto", presupuesto);
						String dir = "gs/presupuesto/frm-validar-presupuesto.zul";
						clearDivApp(dir);

					}
				}
			}
		}
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gs/servicio/frm-servicio-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

}
