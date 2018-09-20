package domainapp.modules.simple.generador;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;


@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Generador.class)

public class GeneradorRepository {

	public List<Generador> listarGeneradores(){
		return repositoryService.allInstances(Generador.class);
	}
	
	public UnidadDeMantenimiento crear(
			final EstadoUnidad estadoUnidad,
			final String descripcion,
			final double consumoEnergetico) {
		final UnidadDeMantenimiento object = new Generador(estadoUnidad, descripcion, consumoEnergetico);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	
	
	
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
