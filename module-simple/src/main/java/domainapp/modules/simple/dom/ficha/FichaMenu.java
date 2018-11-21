package domainapp.modules.simple.dom.ficha;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.dom.tecnico.TecnicoRepository;
import domainapp.modules.simple.tipoInsumo.TipoInsumo;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;
import domainapp.modules.simple.unidadMantenimiento.UnidadRepository;


@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType= "simple.FichaMenu", repositoryFor = Ficha.class)
@DomainServiceLayout(named = "Fichas", menuOrder = "10.2")
public class FichaMenu {
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar fichas")
	@MemberOrder(sequence = "1")
	public List<Ficha> listar() {
		return fichaRepository.listarFichas();
	}
	
	public List<Tecnico> choices0Crear() {
		return tecnicoRepository.listarTecnico();
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Crear Ficha")
	@MemberOrder(sequence = "1.2")
	public Ficha crear(
			@ParameterLayout(named = "Tecnico") final Tecnico tecnico,
			@ParameterLayout(named = "Fecha de creacion") final LocalDate fechaCreacion,
			@ParameterLayout(named = "Tipo de ficha") final TipoDeFicha tipoFicha
			) {
		return fichaRepository.crear(fechaCreacion, tipoFicha, tecnico);
	}
	
	@javax.inject.Inject
	FichaRepository fichaRepository;
	
	@javax.inject.Inject
	TecnicoRepository tecnicoRepository;
	
	@javax.inject.Inject
	UnidadRepository unidades;
}
