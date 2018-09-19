package domainapp.modules.simple.dom.tecnico;

import java.util.List;

import javax.inject.Inject;

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
import domainapp.modules.simple.dom.persona.EstadoCivil;
import domainapp.modules.simple.dom.persona.TipoDeDocumento;

@DomainService(nature=NatureOfService.VIEW_MENU_ONLY, repositoryFor=Tecnico.class, objectType="simple.TecnicoMenu")
@DomainServiceLayout(named="Tecnicos",menuOrder="10.1")
public class TecnicoMenu {
	
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar Todos los Tecnicos")
	@MemberOrder(sequence = "1")
	public List<Tecnico> listar(){
		return tecnicoRepository.listar();
	}
	
	public List<Domicilio> choices8Crear() {
		return domicilioRepository.listarDomicilios();
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Crear Tecnicos")
	@MemberOrder(sequence = "1.2")
	public Tecnico crear(
			@ParameterLayout(named = "Nombre") final String name,
			@ParameterLayout(named = "Apellido") final String apellido,
			@ParameterLayout(named = "Documento") final Integer documento,
			@ParameterLayout(named = "TipoDocumento") final TipoDeDocumento tipoDocumento,
			@ParameterLayout(named = "Telefono") final Integer telefono,
			@ParameterLayout(named = "Email") final String email,
			@ParameterLayout(named = "Fecha Nacimiento") final DateTime fechaNacimiento,
			@ParameterLayout(named = "Estado Civil") final EstadoCivil estadoCivil, 
			@ParameterLayout(named = "Domicilio") final Domicilio domicilio, 
			@ParameterLayout(named = "Numero Empleado") final int numeroEmpleado,
			@ParameterLayout(named = "Sector de Trabajo") final SectorDeTrabajo sectorTrabajo,
			@ParameterLayout(named = "Obra Social") final ObraSocial obraSocial,
			@ParameterLayout(named = "Art") final ART art,
			@ParameterLayout(named = "Matricula Profecional") final String matriculaProfecional,
			@ParameterLayout(named = "Titulo") final Titulo titulo
			) {
		return tecnicoRepository.crear(name, apellido, documento, tipoDocumento, telefono, email, fechaNacimiento, estadoCivil, domicilio, numeroEmpleado, sectorTrabajo, obraSocial, art, matriculaProfecional, titulo);
	}
	

	
	@Inject
	TecnicoRepository tecnicoRepository;
	
	@Inject
	DomicilioRepository domicilioRepository;
}
