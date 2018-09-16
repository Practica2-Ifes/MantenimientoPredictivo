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
import org.apache.isis.applib.value.DateTime;


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
	@MemberOrder(sequence = "1.2")
	public Ficha create(
			@ParameterLayout(named = "Fecha de creacion") final DateTime fechaCreacion,
			@ParameterLayout(named = "Tipo de ficha") final TipoDeFicha tipoFicha
			) {
		return fichaRepository.crear(fechaCreacion, tipoFicha);
	}
	
	@javax.inject.Inject
	FichaRepository fichaRepository;
}
