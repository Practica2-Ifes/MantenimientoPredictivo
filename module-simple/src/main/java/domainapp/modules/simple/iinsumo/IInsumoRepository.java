package domainapp.modules.simple.iinsumo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;


@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = IInsumo.class)

public class IInsumoRepository {

	public List<IInsumo> listarInsumos(){
		return repositoryService.allInstances(IInsumo.class);
	}
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
	@javax.inject.Inject
	IsisJdoSupport isisJdoSupport;
}
