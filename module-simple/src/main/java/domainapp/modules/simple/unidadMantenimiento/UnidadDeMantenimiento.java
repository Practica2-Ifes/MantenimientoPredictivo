package domainapp.modules.simple.unidadMantenimiento;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import domainapp.modules.simple.dom.tecnico.Tecnico;
import lombok.AccessLevel;

@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, column = "UnidadMantenimiento")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "mantenimiento", table = "Unidades")
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "idUnidad")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@lombok.Getter @lombok.Setter
@lombok.RequiredArgsConstructor
public abstract class UnidadDeMantenimiento implements Comparable<UnidadDeMantenimiento> {
	
	@Column(allowsNull="false")
	@lombok.NonNull
	@Property()
	@Title(prepend="Numero de Serie: ")
	private String numeroDeSerie;
	
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "numeroDeSerie")
	public UnidadDeMantenimiento updateNumeroDeSerie(
			@Parameter() @ParameterLayout(named = "Numero de Serie") final String numeroDeSerie) {
		setNumeroDeSerie(numeroDeSerie);
		return this;
	}

    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Estado Unidad: ")
    private EstadoUnidad estadoUnidad;
    

    
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "estadoUnidad")
	public UnidadDeMantenimiento updateEstadoUnidad(
			@Parameter() @ParameterLayout(named = "Estado Unidad") final EstadoUnidad estadoUnidad) {
		setEstadoUnidad(estadoUnidad);
		return this;
	}
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = ", Descripcion: ")
    private String descripcion;
    
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "descripcion")
	public UnidadDeMantenimiento updateDescripcion(
			@Parameter() @ParameterLayout(named = "Descripcion") final String descripcion) {
		setDescripcion(descripcion);
		return this;
	}
    
    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public void borrarUnidad() {
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
