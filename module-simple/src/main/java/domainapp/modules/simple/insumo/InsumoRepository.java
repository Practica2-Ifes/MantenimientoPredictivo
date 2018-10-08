package domainapp.modules.simple.insumo;

import java.util.List;

import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.iinsumo.IInsumo;
import domainapp.modules.simple.tipoInsumo.TipoInsumo;
import domainapp.modules.simple.tipoInsumo.TipoInsumoMenu;


@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Insumo.class)

public class InsumoRepository {
	public List<Insumo> listarInsumos(){
		return repositoryService.allInstances(Insumo.class);
	}

	public List<TipoInsumo> choices0Crear(){
		return tipoInsumoMenu.listarTipos();
	}
	
	@ActionLayout
	public IInsumo crear(
			final List<TipoInsumo> tipoInsumo,
			final String descripcion,
			final double precio,
			final int cantidad) {
		final IInsumo object= new Insumo(tipoInsumo, precio, descripcion, cantidad);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	
	
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
	@javax.inject.Inject
	TipoInsumoMenu tipoInsumoMenu;
}
