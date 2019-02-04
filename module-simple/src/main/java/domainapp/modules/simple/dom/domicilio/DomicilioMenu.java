package domainapp.modules.simple.dom.domicilio;

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


@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType= "simple.DomicilioMenu", repositoryFor = Domicilio.class)
@DomainServiceLayout(named = "Domicilios", menuOrder = "10.2")
public class DomicilioMenu {
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar todos los domicilios")
	@MemberOrder(sequence = "1")
	public List<Domicilio> listar() {
		return domicilioRepository.listarDomicilios();
	}
	
	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Crear Domicilio")
	@MemberOrder(sequence = "1.2")
	public Domicilio crear(@ParameterLayout(named = "Calle") final String calle,
						  @ParameterLayout(named = "Altura") final Integer altura,
						  @ParameterLayout(named = "Barrio") final String barrio,
						  @ParameterLayout(named = "Provincia") final Provincia provincia,
						  @ParameterLayout(named = "Localidad") final String localidad,
						  @ParameterLayout(named = "Numero de Departamento") final String departamento)


	{
		return domicilioRepository.crear(calle, altura, barrio, provincia, localidad, departamento);
	}
	
	@javax.inject.Inject
	DomicilioRepository domicilioRepository;
}
