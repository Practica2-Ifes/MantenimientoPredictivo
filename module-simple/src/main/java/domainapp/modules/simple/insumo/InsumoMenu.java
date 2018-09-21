package domainapp.modules.simple.insumo;

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

import domainapp.modules.simple.generador.Generador;
import domainapp.modules.simple.iinsumo.IInsumo;
import domainapp.modules.simple.iinsumo.TipoInsumo;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType="simple.Insumo", repositoryFor = Insumo.class)
@DomainServiceLayout(named = "Insumo", menuOrder = "10.9")
public class InsumoMenu {
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar todos los Insumos")
	@MemberOrder(sequence = "1")
	public List<Insumo> listarInsumos(){
		return insumoRepository.listarInsumos();
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Crear Insumo")
	@MemberOrder(sequence = "1.2")
	public IInsumo create(@ParameterLayout(named="Tipo Insumo") final List<TipoInsumo> tipoInsumo,
						  @ParameterLayout(named="Precio") final double precio,
						  @ParameterLayout(named="Descripcion") final String descripcion,
						  @ParameterLayout(named="Cantidad") final int cantidad) {
		return insumoRepository.crear(tipoInsumo, descripcion, precio, cantidad);
	}
	
	@javax.inject.Inject
	InsumoRepository insumoRepository;
	
}
