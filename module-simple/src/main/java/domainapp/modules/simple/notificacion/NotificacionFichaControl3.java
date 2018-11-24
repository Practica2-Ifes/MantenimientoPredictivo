package domainapp.modules.simple.notificacion;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.ficha.Ficha;

@DomainService(nature=NatureOfService.DOMAIN)
public class NotificacionFichaControl3 implements INotificacion{

	@Override
	public LocalDate notificarFichaControl(LocalDate fecha) {
		LocalDate fechaRealizarControl = fecha.plusDays(60);
		return fechaRealizarControl;
	}


}
