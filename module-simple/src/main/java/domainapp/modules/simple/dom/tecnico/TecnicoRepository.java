package domainapp.modules.simple.dom.tecnico;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.DateTime;

import domainapp.modules.simple.dom.domicilio.Domicilio;
import domainapp.modules.simple.dom.persona.EstadoCivil;
import domainapp.modules.simple.dom.persona.TipoDeDocumento;


@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Tecnico.class)
public class TecnicoRepository {

	public List<Tecnico> listar(){
		return repositoryService.allInstances(Tecnico.class);
	}
	
	public Tecnico crear(final String name, final String apellido, final Integer documento, final TipoDeDocumento tipoDocumento, final Integer telefono,
			final String email, final DateTime fechaNacimiento,final EstadoCivil estadoCivil, final Domicilio domicilio, final int numeroEmpleado,
			final SectorDeTrabajo sectorTrabajo, final ObraSocial obraSocial, final ART art,final String matriculaProfecional,final Titulo titulo
			) {
		final Tecnico object=new Tecnico(name, apellido,documento, tipoDocumento,telefono,email,fechaNacimiento,estadoCivil,domicilio,numeroEmpleado,
				sectorTrabajo,obraSocial,art,matriculaProfecional,titulo);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	
	
	@Inject
	RepositoryService repositoryService;
	
	@Inject
	ServiceRegistry2 serviceRegistry;
	@javax.inject.Inject
	IsisJdoSupport isisJdoSupport;

}
