package ucla.si.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Correo {
	//smtp-relay.gmail.com 	smtp.gmail.com 	aspmx.l.google.com
    private final String host = "smtp.gmail.com";
    private final String user = "si.anonymous.prime@gmail.com";
    private final String password = "puerimsyumcaprlh";
    private final int port = 465;
    
    //public boolean enviarCorreo(String de, String clave, String[] para, String mensaje, String asunto){
    public boolean enviarCorreo(String destinatario, String asunto, String mensaje){
        boolean enviado = false;
            try{
                Properties prop = System.getProperties();
                prop.put("mail.smtp.starttls.enable","true");
                prop.put("mail.smtp.host", host);
                prop.put("mail.smtp.user", user);
                prop.put("mail.smtp.password", password);
                prop.put("mail.smtp.port",port);
                prop.put("mail.smtp.auth","true");
                prop.put("mail.smtp.socketFactory.port", port);
                prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                prop.put("mail.smtp.socketFactory.fallback", "false");
                
                Session sesion = Session.getDefaultInstance(prop,null);
                MimeMessage message = new MimeMessage(sesion);
                message.setFrom(new InternetAddress(user));
                
                /*
                    
                    NOTA: para enviar correo electronico masivo
                
                    InternetAddress[] direcciones = new InternetAddress[para.length];
                    for(int i=0;i<para.length;i++){
                        direcciones[i] = new InternetAddress(para[i]);
                    }
                
                    for(int i=0;i<direcciones.length;i++){
                        message.addRecipient(Message.RecipientType.TO, direcciones[i]);
                    }
                
                */
                
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
                message.setSubject(asunto);
                message.setText(mensaje);
                Transport transport = sesion.getTransport("smtp");
                transport.connect(host,user,password);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                enviado = true;
            }catch(Exception e){
                e.printStackTrace();
            }
        
        return enviado;
    }
    
    public boolean enviarCorreoHTML(String destinatario, String asunto, String titulo, String subTitulo, String mensaje){
        boolean enviado = false;
            try{
                Properties prop = System.getProperties();
                prop.put("mail.smtp.starttls.enable","true");
                prop.put("mail.smtp.host", host);
                prop.put("mail.smtp.user", user);
                prop.put("mail.smtp.password", password);
                prop.put("mail.smtp.port",port);
                prop.put("mail.smtp.auth","true");
                prop.put("mail.smtp.socketFactory.port", port);
                prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                prop.put("mail.smtp.socketFactory.fallback", "false");
                
                Session sesion = Session.getDefaultInstance(prop,null);
                BodyPart text = new MimeBodyPart();
                text.setContent(plantilla(titulo, subTitulo, mensaje),"text/html");
                
             // Una MultiParte para agrupar texto y adjunto. 
                MimeMultipart multiPart = new MimeMultipart(); 
                multiPart.addBodyPart(text); 
                MimeMessage message = new MimeMessage(sesion);
                message.setFrom(new InternetAddress(user));                
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
                message.setSubject(asunto);
                message.setContent(multiPart);
                Transport transport = sesion.getTransport("smtp");
                transport.connect(host,user,password);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                enviado = true;
            }catch(Exception e){
                e.printStackTrace();
            }
        
        return enviado;
    }
    
    public String plantilla(String titulo, String subTitulo, String mensaje){
    	String html ="";
    	html +="<html><body>";
    	html +="<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
    	html +="<tr>";
		html +="<td align='center' valign='top' bgcolor='#DDDDDD' style='background-color:#EEEEEE;'><br>";
		html +="<br>";
		html +="<table width='600' border='0' cellspacing='0' cellpadding='0'>";
		html +="<tr>";
		html +="<td align='center' valign='top' style='padding-left:13px; padding-right:13px; background-color:#ffffff;background-color: #fff;padding: 19px;margin-bottom: 20px;-webkit-box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);border-radius: 2px;border: 0;'>";
		html +="<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
		html +="<tr>";
		html +="<td><table width='84' border='0' cellspacing='0' cellpadding='0'>";
		html +="<tr>";
		html +="<td height='80' align='center' valign='middle' bgcolor='#009688' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;' width='100%'>";
		html +="<div style='font-size:15px;' bgcolor='#009688' width='100%'>";
		html +="<b>";
		html += "<div width='100%'><img src='https://i.imgur.com/iNLAII1.png'/></div>";
		html +="</b></div></td>";
		html +="</tr>";
		html +="</table></td>";
		html +="</tr>";
		html +="<tr>";
		html +="<td align='center' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif, font-size:36px;'>";
		html += titulo;
		html +="</td>";
		html +="</tr>";
		html +="<tr>";
		html +="<td align='center' valign='middle' style='padding-top:7px;'><table width='240' border='0' cellspacing='0' cellpadding='0'>";
		html +="<tr>";
		html +="<td height='31' align='center' valign='middle' bgcolor='#009688' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; font-size:19px; color: white;'>";
		html +="<div style='color:white;'>";
		html +=fecha();
		html +="</div></td>";
		html +="</tr>";
		html +="</table></td>";
		html +="</tr>";
		html +="<tr>";
		//html +="<td align='center' valign='middle' style='padding-top:15px;'><img src='https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/2011_Hyundai_Sonata_Limited_4_--_02-13-2010.jpg/1200px-2011_Hyundai_Sonata_Limited_4_--_02-13-2010.jpg' width='573' height='243' style='display:block;'></td>";
		html +="</tr>";
		html +="<tr>";
		html +="<td align='center' valign='middle' style='padding-bottom:15px; padding-top:15px;'><img src='http://oi67.tinypic.com/zjfndd.jpg' width='573' height='28'></td>";
		html +="</tr>";
		html +="<tr>";
		html +="<td align='center' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; color:#000000; font-size:24px; padding-bottom:5px;'>";
		html +="<i>";
		html += subTitulo;
		html +="</i>";
		html +="<tr>";
		html +="<td align='left' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; color:#000000; font-size:15px;'>";
		html += mensaje;
		html +="<div>";
		html +="Atentamente,<br>";
		html +="SERVIALDANA'S";
		html +="</div>";
		html +="</td>";
		html +="<tr>";
		html +="<td align='center' valign='middle' style='padding-bottom:15px; padding-top:15px;'><img src='http://oi67.tinypic.com/zjfndd.jpg' width='573' height='28'></td>";
		html +="</tr>";
		html +="<tr>";
		html +="<td align='left' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; font-size:12px; color:#000000;'>";
		html +="<div>";
		html +="Esta es una cuenta de correo no monitoreada. Por favor, no responda o reenvíe mensajes a esta cuenta.<br>";
		html +="<br>";
		html +="</div>";
		html +="<div style='font-size:15px;'><b><i>Contactanos<i></b></div>";
		html +="<div>";
		html +="<table width='120' border='0' cellspacing='0' cellpadding='0'>";
		html +="<tr>";
		html +="<td width='40' align='left' valign='middle'><img valign='middle' src='https://pbs.twimg.com/profile_images/829750285618683905/tSS3tIit_400x400.jpg' width='24' height='24'></td>";
		html +="<td width='40' align='left' valign='middle'><img valign='middle' src='https://pbs.twimg.com/profile_images/3513354941/24aaffa670e634a7da9a087bfa83abe6_400x400.png' width='24' height='24'></td>";
		html +="<td width='40' align='left' valign='middle'><img valign='middle' src='https://pbs.twimg.com/profile_images/786681705981673472/T5OKNZ1-_400x400.jpg' width='24' height='24'></td>";
		html +="</tr>";
		html +="</table>";
		html +="</div><div><br>";
		html +="Dirección<br>";
		html +="Carrera 22,<br>";
		html +="entre calles 12 y 13,<br>";
		html +="SERVIALDANA'S";
		html +=" CA,<br>";
		html +="(0416)5017020 <br>";
		html +="<br>";
		html +="<br>";
		html +="</div></td>";
		html +="</tr>";
		html +="</table></td>";
		html +="</tr>";
		html +="</table>";
		html +="<br>";
		html +="<br></td>";
		html +="</tr>";
		html +="<tr>";
		html +="<td align='center' valign='top'>&nbsp;</td>";
		html +="</tr>";
		html +="</table>"; 
		html +="</body></html>";
    	return html;
    }
    
    public String fecha(){
    	SimpleDateFormat formateador = new SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
    	Date fechaDate = new Date();
	   	String fecha = formateador.format(fechaDate);
	   	System.out.println(fecha);
	   	return fecha;
    }
    
    public String fechaHora(){
    	SimpleDateFormat formateador = new SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy 'a las' hh:mm aaa", new Locale("es", "ES"));
    	Date fechaDate = new Date();
    	String fecha = formateador.format(fechaDate);
    	System.out.println(fecha);
    	return fecha;
    }
    
    public String fechaCita(Date fechaDate){
    	SimpleDateFormat formateador = new SimpleDateFormat(
    			"dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
    	String fecha = formateador.format(fechaDate);
    	System.out.println(fecha);
    	return fecha;
    }
    
    public String mensajeSesion(String cliente){
    	String mensaje ="";
    	mensaje +="<p><span style='font-size:10pt'>Estimado ";
    	mensaje +=cliente;
    	mensaje +=",<br/><br/>";
    	mensaje +="Se ha detectado inicio de sesi&oacute;n exitosa en SERVIALDANA'S el d&iacute;a ";
    	mensaje +=fechaHora();
    	mensaje +="<br/><br/>";
    	mensaje +="Si Ud. desconoce esta operaci&oacute;n, comun&iacute;quese inmediatamente al (0416)5017020.</span></p>";
    	return mensaje;
    }
    
    public String mensajeAtenderCita(String cliente, String estado, Date fechaCita){
    	String mensaje ="";
    	mensaje +="<p><span style='font-size:10pt'>Estimado ";
    	mensaje +=cliente;
    	mensaje +=",<br/><br/>";
    	if(estado.equalsIgnoreCase("Aprobada")){
    		mensaje +="SERVIALDANA'S se complace en informarle que su solicitud de cita fue "+estado;
    		mensaje +=" para el d&iacute;a "+fechaCita(fechaCita);
    	}
    	else{
    		mensaje +="SERVIALDANA'S informa que su solicitud de cita fue "+estado+" por no disponer de espacio en el taller";
    		mensaje +=" por tiempo indeterminado, agradecemos su paciencia y pedimos disculpas por los inconvenientes causados.";
    	}
    	mensaje +="<br/><br/>";
    	mensaje +="Si Ud. tiene alguna duda, comun&iacute;quese al (0416)5017020 y con gusto le atenderemos.</span></p>";
    	return mensaje;
    }
    
    public String mensajeAfiliacion(String cliente, String email, String contrasenna){
    	String mensaje ="";
    	mensaje +="<p><span style='font-size:10pt'>Estimado ";
    	mensaje +=cliente;
    	mensaje +=",<br/><br/>";
		mensaje +="Bienvenido a SERVIALDANA'S sus credenciales para acceder a nuevo Sistema Web son: ";
    	mensaje +="<br/><br/>";
    	mensaje +="correo: "+email;
    	mensaje +="contrase&ntilde;a: "+contrasenna;
    	mensaje +="Si Ud. tiene alguna duda, comun&iacute;quese al (0416)5017020 y con gusto le atenderemos.</span></p>";
    	return mensaje;
    }
    
}