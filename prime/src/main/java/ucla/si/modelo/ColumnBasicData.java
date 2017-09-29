package ucla.si.modelo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;

import ucla.si.dao.ReporteServicioDAO;

public class ColumnBasicData {
    private static CategoryModel model;
    static {
    	ReporteServicioDAO rs = new ReporteServicioDAO();
    	List<ReporteServicio> reporteServicios = rs.consultarReporteServicios();
    	System.out.println("reportes "+reporteServicios.size());
        model = new DefaultCategoryModel();
        if(!reporteServicios.isEmpty()){
        	for (ReporteServicio reporteServicio : reporteServicios) {
        		model.setValue(reporteServicio.getDescripcion(),"Ultimos 4 Meses", reporteServicio.getCantidad());
			}
        }
        
        /*model.setValue("Balanceo", "Enero", 49);
        model.setValue("Balanceo", "Febrero", 71);
        model.setValue("Balanceo", "Marzo", 106);
        model.setValue("Balanceo", "Abril", 129);
        
        model.setValue("Cambio de Aceite", "Enero", 83);
        model.setValue("Cambio de Aceite", "Febrero", 78);
        model.setValue("Cambio de Aceite", "Marzo", 98);
        model.setValue("Cambio de Aceite", "Abril", 93);
        
        model.setValue("Motor", "Enero", 48);
        model.setValue("Motor", "Febrero", 38);
        model.setValue("Motor", "Marzo", 39);
        model.setValue("Motor", "Abril", 41);
        
        model.setValue("Motor", "Enero", 42);
        model.setValue("Motor", "Febrero", 33);
        model.setValue("Motor", "Marzo", 34);
        model.setValue("Motor", "Abril", 39);*/
    }
    
    public static CategoryModel getCategoryModel() {
        return model;
    }
    
    public Date sumarMeses(Date fecha, int meses) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fecha.getTime());
        cal.add(Calendar.MONTH, meses);
        return new Date(cal.getTimeInMillis());
    }

    public Date sumarHorasAFecha(Date fecha, int horas, int minutos, int segundos){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.HOUR, horas);
        cal.add(Calendar.MINUTE, minutos);
        cal.add(Calendar.SECOND, segundos);
        Date oneHourBack = cal.getTime();
        return oneHourBack;
    }

	public long calcularDiferenciaEntreDosFechas(Date fechaMenor, Date fechaMayor){
	    long milisegundos = 24*60*60*1000;
	    long diferencia = (fechaMayor.getTime() - fechaMenor.getTime())/milisegundos;
	    return diferencia;
	}
	
	public long calcularDiferenciaFechas(Date inicio, Date fin){
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    long diferencia = 0;
	    LocalDate fechaInicio = LocalDate.parse(inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formato), formato);
	    LocalDate fechaFin = LocalDate.parse(fin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formato), formato);
	    diferencia = ChronoUnit.DAYS.between(fechaInicio,fechaFin);
	    return diferencia;
	}
}



