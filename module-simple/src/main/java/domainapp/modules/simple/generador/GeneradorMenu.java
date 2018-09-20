package domainapp.modules.simple.generador;

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

import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;


@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType="simple.Generador", repositoryFor = Generador.class)
@DomainServiceLayout(named = "Generador", menuOrder = "10.4")
public class GeneradorMenu {
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar todos los generadores")
	@MemberOrder(sequence = "1")
	public List<Generador> listarGeneradores(){
		return generadorRepository.listarGeneradores();
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Crear Generador")
	@MemberOrder(sequence = "1.2")
	public UnidadDeMantenimiento create(@ParameterLayout(named="Estado Unidad")final EstadoUnidad estadoUnidad,
										@ParameterLayout(named="Descripcion")final String descripcion,
										@ParameterLayout(named="Consumo Energetico")final double consumoEnergetico) {
		return generadorRepository.crear(estadoUnidad, descripcion, consumoEnergetico);
	}
	
	@javax.inject.Inject
	GeneradorRepository generadorRepository;
}
