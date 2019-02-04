package domainapp.modules.simple.dom.tecnico;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.domicilio.Domicilio;
import domainapp.modules.simple.dom.persona.EstadoCivil;
import domainapp.modules.simple.dom.persona.TipoDeDocumento;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Tecnico.class)
public class TecnicoRepository {
	
	public Tecnico crear(final String name, final String apellido, final Integer documento, final TipoDeDocumento tipoDocumento, final String telefono,
			final String email, final LocalDate fechaNacimiento,final EstadoCivil estadoCivil, final Domicilio domicilio, final int numeroEmpleado,
			final SectorDeTrabajo sectorTrabajo, final ObraSocial obraSocial, final ART art,final String matriculaProfesional,final Titulo titulo
			) {
		final Tecnico object=new Tecnico(name, apellido,documento, tipoDocumento,telefono,email,fechaNacimiento,estadoCivil,domicilio,numeroEmpleado,
				sectorTrabajo,obraSocial,art,matriculaProfesional,titulo);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	
	public List<Tecnico> listarTecnico() {
		return repositoryService.allMatches(new QueryDefault<>(Tecnico.class, "listarTecnico"));
	}
	
	public List<Tecnico> listarAdministrativo() {
		return repositoryService.allMatches(new QueryDefault<>(Tecnico.class, "listarAdministrativo"));
	}
	
	
	@Inject
	RepositoryService repositoryService;
	
	@Inject
	ServiceRegistry2 serviceRegistry;
	@javax.inject.Inject
	IsisJdoSupport isisJdoSupport;

}
