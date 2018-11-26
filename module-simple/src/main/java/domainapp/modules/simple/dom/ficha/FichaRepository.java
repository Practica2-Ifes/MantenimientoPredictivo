package domainapp.modules.simple.dom.ficha;

import java.util.List;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.generador.Generador;
import domainapp.modules.simple.iinsumo.IInsumo;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;
import domainapp.modules.simple.unidadMantenimiento.UnidadRepository;
import domainapp.modules.simple.notificacion.SelectStra;


@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Ficha.class)
public class FichaRepository {

	public List<Ficha> listarFichas() {
		return repositoryService.allInstances(Ficha.class);
	}
	
	public List<Ficha> findByFecha(LocalDate fechaCreacionOrigen, LocalDate fechaCreacionFin) {
		return repositoryService.allMatches(new QueryDefault<>(Ficha.class, "fichasDia", "fechaCreacionOrigen", fechaCreacionOrigen, "fechaCreacionFin", fechaCreacionFin));

	}
	
	public List<Ficha> findByTipo(TipoDeFicha tipoDeFicha) {
		return repositoryService.allMatches(new QueryDefault<>(Ficha.class, "tipoBusqueda", "tipoDeFicha", tipoDeFicha));

	}
	
	public Ficha agregarInsumo(final Ficha ficha, final IInsumo insumo, final String descripcion, final Integer cantidadUsada) {
		List<InsumoFicha> insumos = ficha.getInsumos();
		InsumoFicha insumoFicha = new InsumoFicha(insumo, descripcion, cantidadUsada);
		insumos.add(insumoFicha);
		ficha.setInsumos(insumos);
		return ficha;
	}
	
	public Ficha agregarUnidad(final Ficha ficha, final UnidadDeMantenimiento unidad,final Integer horasUso, final String descripcion, final EstadoUnidad estadoUnidad) {
		List<UnidadFicha> unidades = ficha.getUnidades();
		UnidadFicha unidadFicha = new UnidadFicha(unidad, descripcion, estadoUnidad, horasUso);
		unidades.add(unidadFicha);
		ficha.setUnidades(unidades);
		return ficha;
	}
	
	public Ficha agregarTecnico(final Ficha ficha, final Tecnico tecnico, final String nombre, final String apellido, final Integer documento, final Integer horasTrabajo) {
		List<TecnicoFicha> tecnicos = ficha.getTecnicos();
		TecnicoFicha tecnicoFicha = new TecnicoFicha(tecnico, nombre, apellido, documento, horasTrabajo);
		tecnicos.add(tecnicoFicha);
		ficha.setTecnicos(tecnicos);
		return ficha;
	}
	
	public Ficha eliminarInsumo(final Ficha ficha, final InsumoFicha insumo) {
		List<InsumoFicha> insumos = ficha.getInsumos();
		insumos.remove(insumo);
		ficha.setInsumos(insumos);
		return ficha;
	}
	
	public Ficha eliminarUnidad(final Ficha ficha, final UnidadFicha unidad) {
		List<UnidadFicha> unidades = ficha.getUnidades();
		unidades.remove(unidad);
		ficha.setUnidades(unidades);
		return ficha;
	}
	
	public Ficha eliminarTecnico(final Ficha ficha, final TecnicoFicha tecnico) {
		List<TecnicoFicha> tecnicos = ficha.getTecnicos();
		tecnicos.remove(tecnico);
		ficha.setTecnicos(tecnicos);
		return ficha;
	}
	
	public Ficha crear(final LocalDate fechaCreacion, final TipoDeFicha tipoFicha) {
		final Ficha object = new Ficha(fechaCreacion,tipoFicha);
		LocalDate fechaRealizacion = select.CalcularFechaRealizacion(fechaCreacion, tipoFicha);
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
	
	@javax.inject.Inject
	UnidadRepository unidades;
	
	@javax.inject.Inject
	SelectStra select;
}
