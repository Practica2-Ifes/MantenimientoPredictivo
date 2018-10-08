package domainapp.modules.simple.interruptor;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.generador.Generador;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Interruptor.class)
public class InterruptorRepository {
	public List<Interruptor> listarInterruptores(){
		return repositoryService.allInstances(Interruptor.class);
	}
	
	public UnidadDeMantenimiento crear(
			final EstadoUnidad estadoUnidad,
			final String descripcion) {
		final UnidadDeMantenimiento object = new Interruptor(estadoUnidad, descripcion);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	
	
	
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
