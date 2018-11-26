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

import domainapp.modules.simple.dom.tecnico.TecnicoRepository;
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
			@ParameterLayout(named = "Tipo para Busqueda") final TipoDeFicha tipoBusqueda) {
		return fichaRepository.findByTipo(tipoBusqueda);
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
	
	@javax.inject.Inject
	FichaRepository fichaRepository;
	
	@javax.inject.Inject
	TecnicoRepository tecnicoRepository;
	
	@javax.inject.Inject
	UnidadRepository unidades;
	
	@javax.inject.Inject	
	SelectStra selectStra;
}
