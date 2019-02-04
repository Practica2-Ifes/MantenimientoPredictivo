package domainapp.modules.simple.notificacion;

import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.ficha.TipoDeFicha;

//@DomainService(nature=NatureOfService.DOMAIN)
public class SelectStra {
	
	static LocalDate notificacion;
	public static LocalDate CalcularFechaRealizacion(LocalDate fechaCrea, TipoDeFicha tipo) {
		
		if (tipo==TipoDeFicha.C1) {
			notificacion=new NotificacionFichaControl1().notificarFichaControl(fechaCrea);
		}else if (tipo==TipoDeFicha.C2) {
			notificacion=new NotificacionFichaControl2().notificarFichaControl(fechaCrea);
		}else {
			notificacion=new NotificacionFichaControl3().notificarFichaControl(fechaCrea);
		}
		
		return notificacion;
			
		
	}
	
}
