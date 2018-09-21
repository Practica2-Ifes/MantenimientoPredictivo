package domainapp.modules.simple.iinsumo;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType="simple.IInsumos", repositoryFor = IInsumo.class)
@DomainServiceLayout(named = "Insumos", menuOrder = "10.8")
public class IInsumoMenu {
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar todos los Insumos")
	@MemberOrder(sequence = "1")
	public List<IInsumo> listarInsumos(){
		return iInsumoRepository.listarInsumos();
	}
	
	
	
	@javax.inject.Inject
	IInsumoRepository iInsumoRepository;
}
