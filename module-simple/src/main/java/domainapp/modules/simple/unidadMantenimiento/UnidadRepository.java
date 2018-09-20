package domainapp.modules.simple.unidadMantenimiento;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.domicilio.Domicilio;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = UnidadDeMantenimiento.class)
public class UnidadRepository {
	
	public List<UnidadDeMantenimiento> listarUnidades(){
		return repositoryService.allInstances(UnidadDeMantenimiento.class);
	}
		
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
	@javax.inject.Inject
	IsisJdoSupport isisJdoSupport;
}
