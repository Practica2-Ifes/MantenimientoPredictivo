package domainapp.modules.simple.iinsumo;

import java.util.List;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import lombok.AccessLevel;


@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, column = "Insumos")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "mantenimientodb", table = "Insumos")
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "idInsumos")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@lombok.Getter @lombok.Setter
@lombok.RequiredArgsConstructor
public abstract class IInsumo implements Comparable<IInsumo> {

    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Tipo Insumo: ")
	private List<TipoInsumo> tipoInsumo;
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Precio Unidad: ")
    private double precio;
    
    @javax.jdo.annotations.Column(allowsNull = "false", length=100)
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Descripcion: ")
    private String descripcion;
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Cantidad Usada: ")
    private int cantidad;
    
    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.remove(this);
    }
    
    
    
    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    RepositoryService repositoryService;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    TitleService titleService;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    MessageService messageService;
	
}
