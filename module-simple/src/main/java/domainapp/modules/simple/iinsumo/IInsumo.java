package domainapp.modules.simple.iinsumo;

import java.util.List;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.persona.Persona;
import domainapp.modules.simple.tipoInsumo.TipoInsumo;
import lombok.AccessLevel;


@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, column = "Insumos")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "mantenimiento", table = "Insumos")
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "idInsumos")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@lombok.Getter @lombok.Setter
public abstract class IInsumo implements Comparable<IInsumo> {

    public IInsumo(List<TipoInsumo> tipoInsumo, double precio, String descripcion, int cantidad) {
		super();
		this.tipoInsumo = tipoInsumo;
		this.precio = precio;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}



	@javax.jdo.annotations.Column(allowsNull = "false")
	@Persistent(table="IINSUMO_TIPOINSUMO")
	@Join(column="IINSUMO_ID_OID")
	@Element(column="TIPOINSUMO_ID_EID")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
	@CollectionLayout
	private List<TipoInsumo> tipoInsumo;
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    private Double precio;
    
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "precio")
	public IInsumo updatePrecio(@ParameterLayout(named = "Precio") final Double precio) {
		setPrecio(precio);
		return this;
	}
    
    @javax.jdo.annotations.Column(allowsNull = "false", length=100)
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend="Descripcion: ")
    private String descripcion;
    
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "descripcion")
	public IInsumo updateDescripcion(@ParameterLayout(named = "Descripcion") final String descripcion) {
		setDescripcion(descripcion);
		return this;
	}
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    private Integer cantidad;
    
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "cantidad")
	public IInsumo updateCantidad(@ParameterLayout(named = "Cantidad") final Integer cantidad) {
		setCantidad(cantidad);
		return this;
	}
    
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
