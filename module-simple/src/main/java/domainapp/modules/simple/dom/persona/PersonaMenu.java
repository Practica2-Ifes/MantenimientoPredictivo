package domainapp.modules.simple.dom.persona;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.domicilio.Domicilio;
import domainapp.modules.simple.dom.domicilio.DomicilioRepository;


@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType= "simple.PersonaMenu", repositoryFor = Persona.class)
@DomainServiceLayout(named = "Personas", menuOrder = "10.1")
public class PersonaMenu {

	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named= "Listar todos los Clientes")
	@MemberOrder(sequence = "1")
	public List<Persona> listar() {
		return personas.listarPersonas();
	}
	
	@javax.inject.Inject
	PersonaRepository personas;
}
