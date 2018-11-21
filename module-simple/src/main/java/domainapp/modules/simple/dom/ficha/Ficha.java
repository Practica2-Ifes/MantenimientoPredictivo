package domainapp.modules.simple.dom.ficha;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
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
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;
import domainapp.modules.simple.unidadMantenimiento.UnidadRepository;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "mantenimiento")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="id")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@lombok.Getter @lombok.Setter
public class Ficha implements Comparable<Ficha> {
	
    @javax.jdo.annotations.Persistent()
	@Collection()
	@Property(editing=Editing.ENABLED)
	private SortedSet<InsumoFicha> insumos = new TreeSet<InsumoFicha>();
	
//	@Column()
//	@Property(editing=Editing.ENABLED)
//	@PropertyLayout(named="Unidad de mantenimiento encendida")
//	private boolean estadoUnidad;
	
    @javax.jdo.annotations.Persistent()
	@Collection()
	@Property(editing=Editing.ENABLED)
	private SortedSet<UnidadFicha> unidades = new TreeSet<UnidadFicha>();
    
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
    
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "insumos")
    public Ficha agregarInsumo(
    		@ParameterLayout(named="Insumo") final IInsumo insumo, 
    		@ParameterLayout(named="Cantidad usada")final Integer cantidadUsada) {
    	return fichaRepository.agregarInsumo(this, insumo, cantidadUsada);
    }
    
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "unidades")
    public Ficha agregarUnidad(
    		@ParameterLayout(named="Unidad") final UnidadDeMantenimiento unidad, 
    		@ParameterLayout(named="Horas") final Integer horasUso, 
    		@ParameterLayout(named="Estado Unidad")final EstadoUnidad estadoUnidad) {
    	return fichaRepository.agregarUnidad(this, unidad, horasUso, estadoUnidad);
    }
    
    
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "insumos")
    public Ficha eliminarInsumo(final InsumoFicha insumo) {
    	return fichaRepository.eliminarInsumo(this, insumo);
    }
    
    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "unidades")
    public Ficha eliminarUnidad(final UnidadFicha unidad) {
    	return fichaRepository.eliminarUnidad(this, unidad);
    }
    
    public SortedSet<InsumoFicha> choices0EliminarInsumo() {
    	return getInsumos();
    }
    
    public SortedSet<UnidadFicha> choices0EliminarUnidad() {
    	return getUnidades();
    }
    
    public List<IInsumo> choices0AgregarInsumo() {
    	return insumoRepository.listarInsumos();
    }
    
    public List<UnidadDeMantenimiento> choices0AgregarUnidad() {
    	return unidadRepository.listarUnidades();
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


    public Ficha(Tecnico tecnico, LocalDate fechaCreacion,
			TipoDeFicha tipoDeFicha) {
		super();
		this.tecnico = tecnico;
		this.fechaCreacion = fechaCreacion;
		this.tipoDeFicha = tipoDeFicha;
	}
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
    
    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    UnidadRepository unidadRepository;

}