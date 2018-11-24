package domainapp.modules.simple.notificacion;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.ficha.Ficha;

@DomainService(nature=NatureOfService.DOMAIN)
public interface INotificacion {
	public LocalDate notificarFichaControl(LocalDate fecha);
	
}
