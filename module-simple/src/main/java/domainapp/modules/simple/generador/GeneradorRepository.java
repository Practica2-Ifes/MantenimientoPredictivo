package domainapp.modules.simple.generador;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;


@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Generador.class)

public class GeneradorRepository {

	public List<Generador> listarGeneradores(){
		return repositoryService.allInstances(Generador.class);
	}
	
	public UnidadDeMantenimiento crear(
			final String numeroDeSerie,
			final EstadoUnidad estadoUnidad,
			final String descripcion,
			final Double consumoEnergetico) {
		final UnidadDeMantenimiento object = new Generador(numeroDeSerie, estadoUnidad, descripcion, consumoEnergetico);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}
	
	
	
	public Double sumaConsumo() {
		List<Generador> aux= repositoryService.allMatches(new QueryDefault<>(Generador.class, "sumarGasto"));
		Double consumo=0.0;
		for(int i=0; i<aux.size();i++) {
			consumo= consumo+ aux.get(i).getConsumoEnergetico();
		}
		return consumo;
	}
	
	
	
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
