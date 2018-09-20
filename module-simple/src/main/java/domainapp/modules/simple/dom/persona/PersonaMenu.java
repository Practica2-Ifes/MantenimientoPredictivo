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
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.DateTime;

import domainapp.modules.simple.dom.domicilio.Domicilio;
import domainapp.modules.simple.dom.domicilio.DomicilioRepository;


@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType= "simple.PersonaMenu", repositoryFor = Persona.class)
@DomainServiceLayout(named = "Personas", menuOrder = "10.1")
public class PersonaMenu {

	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named= "Listar todos las Personas")
	@MemberOrder(sequence = "1")
	public List<Persona> listar() {
		return personas.listarPersonas();
	}
	
	public List<Domicilio> choices8Crear() {
		return domicilioRepository.listarDomicilios();
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking=BookmarkPolicy.AS_ROOT, named = "Crear Persona")
	@MemberOrder(sequence="1")
	public Persona crear(@ParameterLayout(named = "Name") final String name,
						  @ParameterLayout(named = "Apellido") final String apellido,
						  @ParameterLayout(named = "Documento") final Integer documento,
						  @ParameterLayout(named = "Tipo De Documento") final TipoDeDocumento td,
						  @ParameterLayout(named = "Telefono") final Integer telefono,
						  @ParameterLayout(named = "E-Mail") final String email,
						  @ParameterLayout(named = "Fecha de Nacimiento") final DateTime fechaNacimiento,
						  @ParameterLayout(named = "Estado Civil") final EstadoCivil estadoCivil,
						  @ParameterLayout(named = "Domicilio") final Domicilio domicilio) {
		return personas.crear(name, apellido, documento, td, telefono, email, fechaNacimiento, estadoCivil, domicilio);
	}
	
	@javax.inject.Inject
	PersonaRepository personas;
	
	@javax.inject.Inject
	DomicilioRepository domicilioRepository;
}
