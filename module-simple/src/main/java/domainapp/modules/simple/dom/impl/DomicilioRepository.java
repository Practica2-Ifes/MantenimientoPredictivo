package domainapp.modules.simple.dom.impl;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.DateTime;
import org.datanucleus.query.typesafe.TypesafeQuery;

import domainapp.modules.simple.dom.impl.Personas.CreateDomainEvent;


@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "simple.SimpleObject", repositoryFor = Domicilio.class)
@DomainServiceLayout(named = "Domicilios", menuOrder = "10")
public class DomicilioRepository {
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	@MemberOrder(sequence = "1")
	public List<Domicilio> listAll() {
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

	public static class CreateDomainEvent extends ActionDomainEvent<Domicilio> {
	}

	@Action(domainEvent = CreateDomainEvent.class)
	@MemberOrder(sequence = "3")
	public Domicilio create(@ParameterLayout(named = "Calle") final String calle,
						  @ParameterLayout(named = "Altura") final Integer altura,
						  @ParameterLayout(named = "Barrio") final String barrio,
						  @ParameterLayout(named = "Provincia") final Provincia provincia,
						  @ParameterLayout(named = "Localidad") final String localidad,
						  @ParameterLayout(named = "Departamento") final String departamento)


	{
		return repositoryService.persist(new Domicilio(calle,altura,barrio,provincia,localidad,departamento));
	}

	@javax.inject.Inject
	RepositoryService repositoryService;

	@javax.inject.Inject
	IsisJdoSupport isisJdoSupport;

}
