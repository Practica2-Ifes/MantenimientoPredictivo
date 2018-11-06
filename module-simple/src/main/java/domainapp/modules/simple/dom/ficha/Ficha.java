package domainapp.modules.simple.dom.ficha;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.schema.utils.jaxbadapters.JodaDateTimeStringAdapter.ForJaxb;
import org.joda.time.LocalDate;

import lombok.AccessLevel;
import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.iinsumo.IInsumo;
import domainapp.modules.simple.iinsumo.IInsumoRepository;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "mantenimiento")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="id")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@lombok.Getter @lombok.Setter
@lombok.RequiredArgsConstructor
public class Ficha implements Comparable<Ficha> {
	
    @javax.jdo.annotations.Persistent()
	@Collection()
	@Property(editing=Editing.ENABLED)
	private SortedSet<InsumoFicha> insumos = new TreeSet<InsumoFicha>();
	
	@Column()
	@Property(editing=Editing.ENABLED)
	@PropertyLayout(named="Unidad de mantenimiento encendida")
	private boolean estadoUnidad;
	
	@Column(allowsNull="false", name="TECNICO_ID")
	@lombok.NonNull
	@Property()
	private Tecnico tecnico;

    @Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @XmlJavaTypeAdapter(ForJaxb.class)
    @Title(prepend = "Ficha de: ")
    private LocalDate fechaCreacion;
    
    @Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = ", ")
    private TipoDeFicha tipoDeFicha;

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.remove(this);
    }
    
    @Action()
    public Ficha agregarInsumo(
    		@ParameterLayout(named="Insumo") final IInsumo insumo, 
    		@ParameterLayout(named="Cantidad usada")final Integer cantidadUsada) {
    	return fichaRepository.agregarInsumo(this, insumo, cantidadUsada);
    }
    
    @Action()
    public Ficha eliminarInsumo(final InsumoFicha insumo) {
    	return fichaRepository.eliminarInsumo(this, insumo);
    }
    
    public SortedSet<InsumoFicha> choices0EliminarInsumo() {
    	return getInsumos();
    }
    
    public List<IInsumo> choices0AgregarInsumo() {
    	return insumoRepository.listarInsumos();
    }


    //region > toString, compareTo
    @Override
    public String toString() {
        return "Ficha del dia: " + getFechaCreacion().toString();
    }

	@Override
	public int compareTo(Ficha o) {
		return 0;
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
    
    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    FichaRepository fichaRepository;
    
    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    IInsumoRepository insumoRepository;

}