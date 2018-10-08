package domainapp.modules.simple.tipoInsumo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;


@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = TipoInsumo.class)

public class TipoInsumoRepository {

	public List<TipoInsumo> listarTipos(){
		return repositoryService.allInstances(TipoInsumo.class);
	}
	
	public TipoInsumo crear(
			final String descripcion) {
		final TipoInsumo object= new TipoInsumo(descripcion);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
