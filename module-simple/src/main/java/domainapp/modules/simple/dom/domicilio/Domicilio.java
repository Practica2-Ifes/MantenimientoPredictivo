package domainapp.modules.simple.dom.domicilio;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import com.google.common.collect.ComparisonChain;

import lombok.AccessLevel;


@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "mantenimiento", table = "Domicilio")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="idDireccion")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@javax.jdo.annotations.Unique(name="Domicilio_calle_UNQ", members = {"calle"})
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@lombok.Getter @lombok.Setter
@lombok.RequiredArgsConstructor
public class Domicilio implements Comparable<Domicilio> {

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(sequence="1",prepend = "Calle: ")
    private String calle;
    
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "calle")
	public Domicilio updateCalle(
			@Parameter(maxLength = 40) @ParameterLayout(named = "Calle") final String calle) {
		setCalle(calle);
		return this;
	}

    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(sequence="2",append = ", ")
    private Integer altura;
    
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "altura")
	public Domicilio updateAltura(
			@Parameter(maxLength = 40) @ParameterLayout(named = "Calle") final Integer altura) {
		setAltura(altura);
		return this;
	}
    
    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    private String barrio;
    
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "barrio")
	public Domicilio updateBarrio(
			@Parameter(maxLength = 40) @ParameterLayout(named = "Barrio") final String barrio) {
		setBarrio(barrio);
		return this;
	}
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    private Provincia provincia;
    
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "provincia")
	public Domicilio updateProvincia(
			@Parameter(maxLength = 40) @ParameterLayout(named = "Barrio") final Provincia provincia) {
		setProvincia(provincia);
		return this;
	}
    
    @javax.jdo.annotations.Column(allowsNull = "false", length=40)
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(sequence="3")
    private String localidad;
    
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "localidad")
	public Domicilio updateLocalidad(
			@Parameter(maxLength = 40) @ParameterLayout(named = "localidad") final String localidad) {
		setLocalidad(localidad);
		return this;
	}
    
    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    private String departamento;
   
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "departamento")
	public Domicilio updateDepartamento(
			@Parameter(maxLength = 40) @ParameterLayout(named = "departamento") final String departamento) {
		setDepartamento(departamento);
		return this;
	}



    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.remove(this);
    }



    //region > toString, compareTo
    @Override
    public String toString() {
        return getProvincia().toString();
    }

    public int compareTo(final Domicilio other) {
        return ComparisonChain.start()
                .compare(this.getProvincia(), other.getProvincia())
                .result();
    }
    //endregion


    //region > injected services
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
