package domainapp.modules.simple.notificacion;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.joda.time.LocalDate;


@DomainService(nature=NatureOfService.DOMAIN)
public class NotificacionFichaControl1 implements INotificacion{




	@Override
	public LocalDate notificarFichaControl(LocalDate fecha) {
		// TODO Auto-generated method stub
//		LocalDate fecha=ficha.getFechaCreacion();
		LocalDate fechaRealizarControl = fecha.plusDays(7);
		return fechaRealizarControl;
	}}
