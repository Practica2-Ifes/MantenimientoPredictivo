package domainapp.modules.simple.dom.ficha;

import java.util.List;
import java.util.SortedSet;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.iinsumo.IInsumo;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Ficha.class)
public class FichaRepository {

	public List<Ficha> listarFichas() {
		return repositoryService.allInstances(Ficha.class);
	}
	
	public Ficha agregarInsumo(final Ficha ficha, final IInsumo insumo, final Integer cantidadUsada) {
		SortedSet<InsumoFicha> insumos = ficha.getInsumos();
		InsumoFicha insumoFicha = new InsumoFicha(insumo, cantidadUsada);
		insumos.add(insumoFicha);
		ficha.setInsumos(insumos);
		return ficha;
	}
	
	public Ficha eliminarInsumo(final Ficha ficha, final InsumoFicha insumo) {
		SortedSet<InsumoFicha> insumos = ficha.getInsumos();
		insumos.remove(insumo);
		ficha.setInsumos(insumos);
		return ficha;
	}
	
	public Ficha crear(final LocalDate fechaCreacion, final TipoDeFicha tipoFicha, final Tecnico tecnico) {
		final Ficha object = new Ficha(tecnico, fechaCreacion, tipoFicha);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	@javax.inject.Inject
	RepositoryService repositoryService;

	@javax.inject.Inject
	IsisJdoSupport isisJdoSupport;
	
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
	
}
