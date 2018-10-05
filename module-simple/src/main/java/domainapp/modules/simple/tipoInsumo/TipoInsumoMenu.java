package domainapp.modules.simple.tipoInsumo;

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

import domainapp.modules.simple.insumo.Insumo;
import domainapp.modules.simple.insumo.InsumoRepository;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType="simple.TipoInsumo", repositoryFor = TipoInsumo.class)
@DomainServiceLayout(named = "TipoInsumo", menuOrder = "11")
public class TipoInsumoMenu {

	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar Tipos")
	@MemberOrder(sequence = "1")
	public List<TipoInsumo> listarTipos(){
		return tipoRepository.listarTipos();
	}
	
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Crear Tipo")
	@MemberOrder(sequence = "1.2")
	public TipoInsumo create(@ParameterLayout(named="Descripcion") final String descripcion) {
		return tipoRepository.crear(descripcion);
	}
	
	
	@javax.inject.Inject
	TipoInsumoRepository tipoRepository;
	
}
