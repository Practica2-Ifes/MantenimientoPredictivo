package domainapp.modules.simple.transformador;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;


@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Transformador.class)

public class TransformadorRepository {
	
	List<Transformador> listarTransformadores(){
		return repositoryService.allInstances(Transformador.class);
	}
	
	public UnidadDeMantenimiento crear(
			final EstadoUnidad estadoUnidad,
			final String descripcion,
			final double voltajeAnterior,
			final double voltajeTransformado) {
		final UnidadDeMantenimiento object = new Transformador(estadoUnidad, descripcion, voltajeAnterior, voltajeTransformado);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
