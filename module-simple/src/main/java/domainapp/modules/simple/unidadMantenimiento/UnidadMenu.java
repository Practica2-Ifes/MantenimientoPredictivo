package domainapp.modules.simple.unidadMantenimiento;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;


@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType="simple.UnidadMenu", repositoryFor = UnidadDeMantenimiento.class)
@DomainServiceLayout(named = "Unidades", menuOrder = "10.3")
public class UnidadMenu {
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar todas las Unidades")
	@MemberOrder(sequence = "1")
	public List<UnidadDeMantenimiento> listar(){
		return unidadRepository.listarUnidades();
	}
	
	
	@javax.inject.Inject
	UnidadRepository unidadRepository;

}
