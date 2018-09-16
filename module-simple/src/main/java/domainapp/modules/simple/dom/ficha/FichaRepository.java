package domainapp.modules.simple.dom.ficha;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.LocalDate;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Ficha.class)
public class FichaRepository {

	public List<Ficha> listarFichas() {
		return repositoryService.allInstances(Ficha.class);
	}
	
	public Ficha crear(final LocalDate fechaCreacion, final TipoDeFicha tipoFicha) {
		final Ficha object = new Ficha(fechaCreacion, tipoFicha);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	@javax.inject.Inject
	RepositoryService repositoryService;

	@javax.inject.Inject
	IsisJdoSupport isisJdoSupport;
	
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
