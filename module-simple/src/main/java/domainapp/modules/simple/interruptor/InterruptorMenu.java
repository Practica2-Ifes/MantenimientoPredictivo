package domainapp.modules.simple.interruptor;

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
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType="simple.Interruptor", repositoryFor = Interruptor.class)
@DomainServiceLayout(named = "Interruptor", menuOrder = "10.5")
public class InterruptorMenu {
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar todos los interruptores")
	@MemberOrder(sequence = "1")
	public List<Interruptor> listar(){
		return interruptorRepository.listarInterruptores();
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Crear Interruptor")
	@MemberOrder(sequence = "1.1")
	public UnidadDeMantenimiento crear(@ParameterLayout(named="Estado Unidad")final EstadoUnidad estadoUnidad,
										@ParameterLayout(named="Descripcion")final String descripcion) {
		return interruptorRepository.crear(estadoUnidad, descripcion);
	}
	
	@javax.inject.Inject
	InterruptorRepository interruptorRepository;
}
