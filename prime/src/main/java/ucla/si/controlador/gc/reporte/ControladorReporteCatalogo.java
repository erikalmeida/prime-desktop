package ucla.si.controlador.gc.reporte;


import org.zkoss.chart.Chart;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Series;
import org.zkoss.chart.Tooltip;
import org.zkoss.chart.YAxis;
import org.zkoss.chart.options3D.Options3D;
import org.zkoss.chart.plotOptions.PieDataLabels;
import org.zkoss.chart.plotOptions.PiePlotOptions;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Accion;
import ucla.si.modelo.ColumnBasicData;
import ucla.si.servicio.ServicioAccion;

public class ControladorReporteCatalogo extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Wire
    private Charts chart;
	@Wire
	private Label punt;

	@Override
	protected void inicializar() {

		System.out.println("iniciando reportes");
      	chart.setModel(ColumnBasicData.getCategoryModel());
          
      	Options3D opt = chart.getOptions3D();
          opt.setEnabled(true);
          opt.setAlpha(15);
          opt.setBeta(15);
          opt.setViewDistance(25);
          opt.setDepth(40);
          chart.setMarginTop(80);
          chart.setMarginRight(40);
          
          YAxis y0 = new YAxis();
          y0.setAllowDecimals(false);
          y0.setMin(0);
          y0.setTitle("Numero de Servicios");
          chart.getPlotData().setYAxis(y0);

          Tooltip tooltip = chart.getTooltip();
          tooltip.setHeaderFormat("<span style=\"font-size:10px\">{point.key}</span><table>");
          tooltip.setPointFormat("<tr><td style=\"color:{series.color};padding:0\">{series.name}: </td>"
              + "<td style=\"padding:0\"><b>{point.y:.1f}</b></td></tr>");
          tooltip.setFooterFormat("</table>");
          tooltip.setShared(true);
          tooltip.setUseHTML(true);
          
          chart.getPlotOptions().getColumn().setPointPadding(0.2);
          chart.getPlotOptions().getColumn().setBorderWidth(0);
          
          chart.getPlotOptions().getColumn().setDepth(40);
		
	}


}
