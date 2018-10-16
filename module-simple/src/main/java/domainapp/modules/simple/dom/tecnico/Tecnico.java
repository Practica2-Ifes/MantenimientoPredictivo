package domainapp.modules.simple.dom.tecnico;


import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.joda.time.LocalDate;

import domainapp.modules.simple.dom.domicilio.Domicilio;
import domainapp.modules.simple.dom.persona.EstadoCivil;
import domainapp.modules.simple.dom.persona.Persona;
import domainapp.modules.simple.dom.persona.TipoDeDocumento;
import lombok.AccessLevel;

@PersistenceCapable(identityType=IdentityType.DATASTORE, schema="mantenimiento",table="empleados")
@DatastoreIdentity(strategy=IdGeneratorStrategy.IDENTITY,column="IdEmpleado")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@lombok.Getter @lombok.Setter
public class Tecnico extends Persona implements Comparable<Tecnico>{
	

	public Tecnico(final String name, final String apellido, final Integer documento, final TipoDeDocumento tipoDocumento, final String telefono,
			final String email, final LocalDate fechaNacimiento,final EstadoCivil estadoCivil, final Domicilio domicilio, final int numeroEmpleado,
			final SectorDeTrabajo sectorTrabajo, final ObraSocial obraSocial, final ART art,final String matriculaProfesional,final Titulo titulo) {
		super();
		setName(name);
		setApellido(apellido);
		setDocumento(documento);
		setTipoDocumento(tipoDocumento);
		setTelefono(telefono);
		setEmail(email);
		setFechaNacimiento(fechaNacimiento);
		setEstadoCivil(estadoCivil);
		setDomicilio(domicilio);
		setNumeroEmpleado(numeroEmpleado);
		setSectorDeTrabajo(sectorTrabajo);
		setObraSocial(obraSocial);
		setArt(art);
		setMatriculaProfesional(matriculaProfesional);
		setTitulo(titulo);
	}
	public List<Tecnico> listarEmpleados(){
		return repositoryService.allInstances(Tecnico.class);
	}
	
	
	
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property()
	private int numeroEmpleado;
	
	public int getNumeroEmpleado() {
		return numeroEmpleado;
	}
	public void setNumeroEmpleado(int numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property()
	private SectorDeTrabajo sectorDeTrabajo;
	
	public SectorDeTrabajo getSectorDeTrabajo() {
		return sectorDeTrabajo;
	}
	public void setSectorDeTrabajo(SectorDeTrabajo sectorDeTrabajo) {
		this.sectorDeTrabajo = sectorDeTrabajo;
	}
	
	
	
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property()
	private ObraSocial obraSocial;
	
	public ObraSocial getObraSocial() {
		return obraSocial;
	}
	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property()
	private ART art;
	
	public ART getArt() {
		return art;
	}
	public void setArt(ART art) {
		this.art = art;
	}

	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property()
	private String matriculaProfesional;
	
	public String getMatriculaProfesional() {
		return matriculaProfesional;
	}
	public void setMatriculaProfesional(String matriculaProfesional) {
		this.matriculaProfesional = matriculaProfesional;
	}
	
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property()
	private Titulo titulo;
	
	public Titulo getTitulo() {
		return titulo;
	}
	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	// region > delete (action)
		@Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
		public void borrarTecnico() {
			final String title = titleService.titleOf(this);
			messageService.informUser(String.format("'%s' deleted", title));
		}


		@Override
		public int compareTo(Tecnico o) {
			// TODO Auto-generated method stub
			return 0;
		}

	@Inject
	@javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
	RepositoryService repositoryService;
	

	@Inject
	@javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
	TitleService titleService;
	
	@Inject
	@javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
	MessageService messageService;
}