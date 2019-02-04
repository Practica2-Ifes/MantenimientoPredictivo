package domainapp.modules.simple.dom.domicilio;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;



@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Domicilio.class)
public class DomicilioRepository {

	public List<Domicilio> listarDomicilios() {
		return repositoryService.allInstances(Domicilio.class);
	}

//	@Action(semantics = SemanticsOf.SAFE)
//	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
//	@MemberOrder(sequence = "2")
//	public List<Domicilio> findByName(@ParameterLayout(named = "Name") final String name) {
//		TypesafeQuery<Persona> q = isisJdoSupport.newTypesafeQuery(Persona.class);
//		final QPersona cand = QPersona.candidate();
//		q = q.filter(cand.name.indexOf(q.stringParameter("name")).ne(-1));
//		return q.setParameter("name", name).executeList();
//	}

//	@Programmatic
//	public Persona findByNameExact(final String name) {
//		TypesafeQuery<Persona> q = isisJdoSupport.newTypesafeQuery(Persona.class);
//		final QPersona cand = QPersona.candidate();
//		q = q.filter(cand.name.eq(q.stringParameter("name")));
//		return q.setParameter("name", name).executeUnique();
//	}

//	public static class CreateDomainEvent extends ActionDomainEvent<Domicilio> {
//	}

	public Domicilio crear(final String calle,
			final Integer altura,
			final String barrio,
			final Provincia provincia,
			final String localidad,
			final String departamento) {
		final Domicilio object = new Domicilio(calle, altura, barrio, provincia, localidad, departamento);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	


	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
	@javax.inject.Inject
	IsisJdoSupport isisJdoSupport;

}
