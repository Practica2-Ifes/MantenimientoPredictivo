package domainapp.modules.simple.dom.ficha;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.dom.tecnico.TecnicoRepository;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;
import domainapp.modules.simple.unidadMantenimiento.UnidadRepository;
import domainapp.modules.simple.notificacion.SelectStra;


@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType= "simple.FichaMenu", repositoryFor = Ficha.class)
@DomainServiceLayout(named = "Fichas", menuOrder = "10.2")
public class FichaMenu {
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar fichas")
	@MemberOrder(sequence = "1")
	public List<Ficha> listar() {
		return fichaRepository.listarFichas();
	}
	
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Crear Ficha")
	@MemberOrder(sequence = "2")
	public Ficha crear(
			@ParameterLayout(named = "Fecha de creacion") final LocalDate fechaCreacion,
			@ParameterLayout(named = "Tipo de ficha") final TipoDeFicha tipoFicha
			) {
		return fichaRepository.crear(fechaCreacion,tipoFicha);
			}
	
    
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Busqueda por Fecha")
	@MemberOrder(sequence = "3")
	public List<Ficha> findByFecha(
			@ParameterLayout(named = "Desde la Fecha: ") final LocalDate fechaBusqueda,
			@ParameterLayout(named = "Hasta la Fecha: ") final LocalDate fechaBusquedaFin)
	{	
		return fichaRepository.findByFecha(fechaBusqueda, fechaBusquedaFin);
	}
      
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Busqueda por Tipo de Ficha")
	@MemberOrder(sequence = "4")
	public List<Ficha> findByTipo(
			@ParameterLayout(named = "Tipo de Ficha para Busqueda: ") final TipoDeFicha tipoBusqueda) {
		return fichaRepository.findByTipo(tipoBusqueda);
	}
	
    public List<Tecnico> choices0FindByTecnico() {
    	return tecnicoRepository.listarTecnico();
    }
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Busqueda por Tecnico")
	@MemberOrder(sequence = "5")
	public List<Ficha> findByTecnico(
			@ParameterLayout(named = "Tecnico para Busqueda: ") final Tecnico tecnico) {
		return fichaRepository.findByTecnico(tecnico);
	}
	
    public List<UnidadDeMantenimiento> choices0FindByUnidad() {
    	return unidades.listarUnidades();
    }
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Busqueda por Unidad de Mantenimiento")
	@MemberOrder(sequence = "6")
	public List<Ficha> findByUnidad(
	@ParameterLayout(named = "Elija la Unidad de Mantenimiento para Busqueda: ") final UnidadDeMantenimiento unidad) {
		return fichaRepository.findByUnidades(unidad);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo costos de Insumos en Fichas")
	@MemberOrder(sequence = "1")
	public String calculoCostosInsumos()
	{
			return fichaRepository.calculoInsumos();
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo costos de Insumos por Tipo de Ficha")
	@MemberOrder(sequence = "2")
	public String calculoCostosPorTipo(			
	@ParameterLayout(named = "Tipo de Ficha para Busqueda: ") final TipoDeFicha tipoBusqueda) 
	{
			return fichaRepository.calculoInsumosPorTipoFicha(tipoBusqueda);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo costos de Insumos entre Fechas")
	@MemberOrder(sequence = "3")
	public String calculoCostosPorFechas(			
	@ParameterLayout(named = "Desde la Fecha: ") final LocalDate fechaBusqueda,
	@ParameterLayout(named = "Hasta la Fecha: ") final LocalDate fechaBusquedaFin)
	{
			return fichaRepository.calculoInsumosPorFechas(fechaBusqueda, fechaBusquedaFin);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo costos por Tecnicos")
	@MemberOrder(sequence = "4")
	public String calculoCostosTecnico(			
	@ParameterLayout(named = "Costo de Horas de Tecnico: ") final Double costoHora) 
	{
			return fichaRepository.calculoTrabajo(costoHora);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo costos por Tecnicos por Tipo de Ficha")
	@MemberOrder(sequence = "5")
	public String calculoCostosTecnicoPorTipo(			
	@ParameterLayout(named = "Costo de Horas de Tecnico: ") final Double costoHora,
	@ParameterLayout(named = "Tipo de Ficha para Busqueda: ") final TipoDeFicha tipoBusqueda) 
	{
			return fichaRepository.calculoTrabajoPorTipoFicha(costoHora, tipoBusqueda);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo costos por Tecnicos entre Fechas")
	@MemberOrder(sequence = "6")
	public String calculoCostosTecnicoPorFechas(			
	@ParameterLayout(named = "Costo de Horas de Tecnico: ") final Double costoHora,
	@ParameterLayout(named = "Desde la Fecha: ") final LocalDate fechaBusqueda,
	@ParameterLayout(named = "Hasta la Fecha: ") final LocalDate fechaBusquedaFin)
	{
			return fichaRepository.calculoTrabajoPorFechas(costoHora, fechaBusqueda, fechaBusquedaFin);
	}
	
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo Horas Totales Trabajadas")
	@MemberOrder(sequence = "7")
	public String calculoHorasTrabajo()			
	{
			return fichaRepository.horasTrabajo();
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo Horas Trabajadas por Tipo de Ficha")
	@MemberOrder(sequence = "7")
	public String calculoHorasTrabajoPorTipoDeFicha(
	@ParameterLayout(named = "Tipo de Ficha para Busqueda: ") final TipoDeFicha tipoBusqueda) 			
	{
			return fichaRepository.horasTrabajoPorTipoFicha(tipoBusqueda);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo Horas Trabajadas entre Fechas")
	@MemberOrder(sequence = "7")
	public String calculoHorasTrabajoPorFechas(
	@ParameterLayout(named = "Desde la Fecha: ") final LocalDate fechaBusqueda,
	@ParameterLayout(named = "Hasta la Fecha: ") final LocalDate fechaBusquedaFin)			
	{
			return fichaRepository.horasTrabajoPorFechas(fechaBusqueda, fechaBusquedaFin);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo Costo Total de Trabajo y De Insumos")
	@MemberOrder(sequence = "8")
	public String calculoCostoTotal(
			@ParameterLayout(named = "Costo de Horas de Tecnico: ") final Double costoHora)		
	{
			return fichaRepository.calculoCostoTotal(costoHora);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo Costo de Trabajo y de Insumos Por Tipo de Ficha")
	@MemberOrder(sequence = "9")
	public String calculoCostoTotalPorTipo(
			@ParameterLayout(named = "Costo de Horas de Tecnico: ") final Double costoHora,
			@ParameterLayout(named = "Tipo de Ficha para Busqueda: ") final TipoDeFicha tipoBusqueda) 			
	{
			return fichaRepository.calculoCostoTotalPorTipo(costoHora, tipoBusqueda);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo Costo de Trabajo y de Insumos entre Fechas")
	@MemberOrder(sequence = "10")
	public String calculoCostoTotalPorFechas(
			@ParameterLayout(named = "Costo de Horas de Tecnico: ") final Double costoHora,
			@ParameterLayout(named = "Desde la Fecha: ") final LocalDate fechaBusqueda,
			@ParameterLayout(named = "Hasta la Fecha: ") final LocalDate fechaBusquedaFin)		
	{
			return fichaRepository.calculoCostoTotalPorFechas(costoHora, fechaBusqueda, fechaBusquedaFin);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo Costo de Uso de las Unidades en Ciertos Estados")
	@MemberOrder(sequence = "11")
	public String calculoCostoPromedioUnidades(
			@ParameterLayout(named = "Costo Promedio de Horas de Uso de las Unidades: ") final Double costoPromedio,
			@ParameterLayout(named = "Estado de las Unidades que quiere calcular el costo: ") final EstadoUnidad estadoUnidad)
	{
			return fichaRepository.costoPromedioUnidades(costoPromedio, estadoUnidad);
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Calculo Horas de Uso de las Unidades en Ciertos Estados")
	@MemberOrder(sequence = "12")
	public String calculoHorasUnidades(
			@ParameterLayout(named = "Estado de las Unidades que quiere calcular las horas: ") final EstadoUnidad estadoUnidad)
	{
			return fichaRepository.horasUsoPorEstado(estadoUnidad);
	}
	
	

	

	@javax.inject.Inject
	FichaRepository fichaRepository;
	
	@javax.inject.Inject
	TecnicoRepository tecnicoRepository;
	
	@javax.inject.Inject
	UnidadRepository unidades;
	
	@javax.inject.Inject	
	SelectStra selectStra;
}
