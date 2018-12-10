package domainapp.modules.simple.dom.ficha;

import java.util.ArrayList;
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
	
	@SuppressWarnings("unlikely-arg-type")
	public List<Ficha> findByTecnico(Tecnico tecnico) {
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "busquedaFicha"));
		List<Ficha> contador=new ArrayList<Ficha>();
		for (int k=0; k<aux.size(); k++) {
			for(int i=0; i<aux.get(k).getTecnicos().size();i++) {
					if(aux.get(k).getTecnicos().get(i).getTecnico().equals(tecnico)) {
						contador.add(aux.get(k));
					}
				}
			}				
		return contador;
	}
	
	

	
	@SuppressWarnings("unlikely-arg-type")
	public List<Ficha> findByUnidades(UnidadDeMantenimiento unidad){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "busquedaFicha"));
		List<Ficha> contador= new ArrayList<Ficha>();
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getUnidades().size();j++) {
				if(aux.get(k).getUnidades().get(j).getUnidad().equals(unidad)) {
					contador.add(aux.get(k));
				}

			}
		}
		return contador;
	}
	
	public String calculoInsumos(){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "busquedaFicha"));
		double contPrecio=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getInsumos().size();j++) {
				double precioTot= aux.get(k).getInsumos().get(j).getInsumoUsado().getPrecio() *aux.get(k).getInsumos().get(j).getCantidadUsada();
				contPrecio = contPrecio +precioTot;
			}
		}
		return "El costo total de todos los Insumos usados es de: "+contPrecio+"$";
	}
	
	public String calculoInsumosPorTipoFicha(TipoDeFicha tipoDeFicha){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "tipoBusqueda", "tipoDeFicha", tipoDeFicha));
		double contPrecio=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getInsumos().size();j++) {
				double precioTot= aux.get(k).getInsumos().get(j).getInsumoUsado().getPrecio() *aux.get(k).getInsumos().get(j).getCantidadUsada();
				contPrecio = contPrecio +precioTot;
			}
		}
		return "El costo total de todos los Insumos usados para los tipo de Fichas: "+tipoDeFicha+ " es de: "+contPrecio+"$";
	}
	
	public String calculoInsumosPorFechas(LocalDate fechaCreacionOrigen, LocalDate fechaCreacionFin){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "fichasDia", "fechaCreacionOrigen", fechaCreacionOrigen, "fechaCreacionFin", fechaCreacionFin));
		double contPrecio=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getInsumos().size();j++) {
				double precioTot= aux.get(k).getInsumos().get(j).getInsumoUsado().getPrecio() *aux.get(k).getInsumos().get(j).getCantidadUsada();
				contPrecio = contPrecio +precioTot;
			}
		}
		return "El costo total de todos los Insumos usados entre: "+fechaCreacionOrigen+ " y: "+fechaCreacionFin+ " es de: "+contPrecio+"$";
	}
	
	public String calculoTrabajo(double costoHora){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "busquedaFicha"));
		double costoTecnico=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getTecnicos().size();j++) {
				double costoTecnicoTotal= aux.get(k).getTecnicos().get(j).getHorasTrabajo() * costoHora;
				costoTecnico = costoTecnico +costoTecnicoTotal;
			}
		}
		return "El costo total de todos los Tecnicos es de: "+costoTecnico+"$";
	}
	
	public String calculoTrabajoPorTipoFicha(double costoHora, TipoDeFicha tipoDeFicha){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "tipoBusqueda", "tipoDeFicha", tipoDeFicha));
		double costoTecnico=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getTecnicos().size();j++) {
				double costoTecnicoTotal= aux.get(k).getTecnicos().get(j).getHorasTrabajo() * costoHora;
				costoTecnico = costoTecnico +costoTecnicoTotal;
			}
		}
		return "El costo por Tecnicos durante el trabajo para el tipo de Fichas: "+tipoDeFicha+ " es de: "+costoTecnico+"$";
	}
	
	public String calculoTrabajoPorFechas(double costoHora, LocalDate fechaCreacionOrigen, LocalDate fechaCreacionFin){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "fichasDia", "fechaCreacionOrigen", fechaCreacionOrigen, "fechaCreacionFin", fechaCreacionFin));
		double costoTecnico=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getTecnicos().size();j++) {
				double costoTecnicoTotal= aux.get(k).getTecnicos().get(j).getHorasTrabajo() * costoHora;
				costoTecnico = costoTecnico +costoTecnicoTotal;
			}
		}
		return "El costo por Tecnicos durante el trabajo entre las Fechas: "+fechaCreacionOrigen+ " y: "+ fechaCreacionFin+ " es de: "+costoTecnico+"$";
	}
	
	public String horasTrabajo(){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "busquedaFicha"));
		int horasTrabajo=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getTecnicos().size();j++) {
				horasTrabajo = horasTrabajo +aux.get(k).getTecnicos().get(j).getHorasTrabajo();
			}
		}
		return "La cantidad de horas de Trabajo de todos los Tecnicos es de: "+horasTrabajo+" horas";
	}
	
	public String horasTrabajoPorTipoFicha(TipoDeFicha tipoDeFicha){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "tipoBusqueda", "tipoDeFicha", tipoDeFicha));
		int horasTrabajo=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getTecnicos().size();j++) {
				horasTrabajo = horasTrabajo +aux.get(k).getTecnicos().get(j).getHorasTrabajo();
			}
		}
		return "La cantidad de horas de Trabajo de los Tecnicos para el tipo de Ficha: "+tipoDeFicha+ " es de: "+horasTrabajo+" horas";
	}
	
	public String horasTrabajoPorFechas(LocalDate fechaCreacionOrigen, LocalDate fechaCreacionFin){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "fichasDia", "fechaCreacionOrigen", fechaCreacionOrigen, "fechaCreacionFin", fechaCreacionFin));
		int horasTrabajo=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getTecnicos().size();j++) {
				horasTrabajo = horasTrabajo +aux.get(k).getTecnicos().get(j).getHorasTrabajo();
			}
		}
		return "La cantidad de horas de Trabajo de los Tecnicos entre las fechas: "+fechaCreacionOrigen+ " y: "+fechaCreacionFin+ " es de: "+horasTrabajo+" horas";
	}
	
	public String calculoCostoTotal(double costoHora){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "busquedaFicha"));
		double contPrecio=0;
		double costoTecnico=0;
		double costoTotal=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getInsumos().size();j++) {
				double precioTot= aux.get(k).getInsumos().get(j).getInsumoUsado().getPrecio() *aux.get(k).getInsumos().get(j).getCantidadUsada();
				contPrecio = contPrecio +precioTot;
			}
			for (int j=0; j<aux.get(k).getTecnicos().size();j++) {
				double costoTecnicoTotal= aux.get(k).getTecnicos().get(j).getHorasTrabajo() * costoHora;
				costoTecnico = costoTecnico +costoTecnicoTotal;
			}		
		}
		costoTotal=contPrecio+costoTecnico;
		return "El costo total de todos los Tecnicos e Insumos es de: "+costoTotal+"$";
	}
	
	public String calculoCostoTotalPorTipo(double costoHora, TipoDeFicha tipoDeFicha){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "tipoBusqueda", "tipoDeFicha", tipoDeFicha));
		double contPrecio=0;
		double costoTecnico=0;
		double costoTotal=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getInsumos().size();j++) {
				double precioTot= aux.get(k).getInsumos().get(j).getInsumoUsado().getPrecio() *aux.get(k).getInsumos().get(j).getCantidadUsada();
				contPrecio = contPrecio +precioTot;
			}
			for (int j=0; j<aux.get(k).getTecnicos().size();j++) {
				double costoTecnicoTotal= aux.get(k).getTecnicos().get(j).getHorasTrabajo() * costoHora;
				costoTecnico = costoTecnico +costoTecnicoTotal;
			}		
		}
		costoTotal=contPrecio+costoTecnico;
		return "El costo total de todos los Tecnicos e Insumos para las fichas: "+tipoDeFicha+ " es de: "+costoTotal+"$";
	}
	
	public String calculoCostoTotalPorFechas(double costoHora, LocalDate fechaCreacionOrigen, LocalDate fechaCreacionFin){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "fichasDia", "fechaCreacionOrigen", fechaCreacionOrigen, "fechaCreacionFin", fechaCreacionFin));
		double contPrecio=0;
		double costoTecnico=0;
		double costoTotal=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getInsumos().size();j++) {
				double precioTot= aux.get(k).getInsumos().get(j).getInsumoUsado().getPrecio() *aux.get(k).getInsumos().get(j).getCantidadUsada();
				contPrecio = contPrecio +precioTot;
			}
			for (int j=0; j<aux.get(k).getTecnicos().size();j++) {
				double costoTecnicoTotal= aux.get(k).getTecnicos().get(j).getHorasTrabajo() * costoHora;
				costoTecnico = costoTecnico +costoTecnicoTotal;
			}		
		}
		costoTotal=contPrecio+costoTecnico;
		return "El costo total de todos los Tecnicos e Insumos entre las Fechas: "+ fechaCreacionOrigen+ " y: "+ fechaCreacionFin+ " es de: "+costoTotal+"$";
	}
	
	public String costoPromedioUnidades(double costoPromedio, EstadoUnidad estadoUnidad){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "busquedaFicha"));
		double contCosto=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getUnidades().size();j++) {
				if(aux.get(k).getUnidades().get(j).getEstadoUnidad().equals(estadoUnidad)) {
					double costoPromedioTotal = aux.get(k).getUnidades().get(j).getHorasUso() * costoPromedio;
					contCosto= contCosto + costoPromedioTotal;
				}
			}
		}
		return "El costo de Mantener Unidades en el estado: "+estadoUnidad+ " es de: "+contCosto+"$";
	}
	
	public String horasUsoPorEstado(EstadoUnidad estadoUnidad){
		List<Ficha> aux= repositoryService.allMatches(new QueryDefault<>(Ficha.class, "busquedaFicha"));
		int contHoras=0;
		for(int k=0 ;k<aux.size(); k++) {
			for (int j=0; j<aux.get(k).getUnidades().size();j++) {
				if(aux.get(k).getUnidades().get(j).getEstadoUnidad().equals(estadoUnidad)) {
					contHoras= contHoras + aux.get(k).getUnidades().get(j).getHorasUso();
				}
			}
		}
		return "La cantidad de Horas que las Unidades pasaron en el estado: "+estadoUnidad+ " es de: "+contHoras+" horas";
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
		unidad.setEstadoUnidad(estadoUnidad);
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
	
	public Ficha crear(final LocalDate fechaCreacion, final TipoDeFicha tipoFicha, final String observaciones) {
		final Ficha object = new Ficha(fechaCreacion, tipoFicha, observaciones);
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
