package domainapp.modules.simple.transformador;

import java.util.List;

import javax.inject.Inject;

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

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType="simple.Transformador", repositoryFor = Transformador.class)
@DomainServiceLayout(named = "Transformador", menuOrder = "10.6")
public class TransformadorMenu {
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar todos los transformadores")
	@MemberOrder(sequence = "1")
	public List<Transformador> listarTransformadores(){
		return transformadorRepository.listarTransformadores();
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Crear Transformador")
	@MemberOrder(sequence = "1.2")
	public UnidadDeMantenimiento create(@ParameterLayout(named="Estado Unidad") final EstadoUnidad estadoUnidad,
										@ParameterLayout(named="Descripcion") final String descripcion,
										@ParameterLayout(named="Voltaje Anterior") final double voltajeAnterior,
										@ParameterLayout(named="Voltaje Transformado") final double voltajeTransformado) {
		return transformadorRepository.crear(estadoUnidad, descripcion, voltajeAnterior, voltajeTransformado);
	}

	
	@javax.inject.Inject
	TransformadorRepository transformadorRepository;
}
