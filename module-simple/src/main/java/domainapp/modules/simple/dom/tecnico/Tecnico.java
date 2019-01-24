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
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Publishing;
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


@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(
	        name = "listarTecnico", language = "JDOQL",
	        value = "SELECT "
	                + "FROM domainapp.modules.simple.dom.tecnico.Tecnico "
	                + "WHERE sectorDeTrabajo == 'mantenimiento' "),
	
	@javax.jdo.annotations.Query(
	        name = "listarAdministrativo", language = "JDOQL",
	        value = "SELECT "
	                + "FROM domainapp.modules.simple.dom.tecnico.Tecnico "
	                + "WHERE sectorDeTrabajo == 'administracion' "),
	@javax.jdo.annotations.Query(
	        name = "auxTecnico", language = "JDOQL",
	        value = "SELECT "
	                + "FROM domainapp.modules.simple.dom.tecnico.Tecnico "
	                + "WHERE documento == :documento ")
})


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
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "numeroEmpleado")
	public Tecnico updateNumeroEmpleado(
			@Parameter() @ParameterLayout(named = "numero empleado") final int numeroEmpleado) {
		setNumeroEmpleado(numeroEmpleado);
		return this;
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
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "sectorDeTrabajo")
	public Tecnico updateSectorDeTrabajo(
			@Parameter() @ParameterLayout(named = "Sector de Trabajo") final SectorDeTrabajo sectorTrabajo) {
		setSectorDeTrabajo(sectorDeTrabajo);
		return this;
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
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "obraSocial")
	public Tecnico updateObraSocial(
			@Parameter() @ParameterLayout(named = "Obra Social") final ObraSocial obraSocial) {
		setObraSocial(obraSocial);
		return this;
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
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "art")
	public Tecnico updateArt(
			@Parameter() @ParameterLayout(named = "ART") final ART art) {
		setArt(art);
		return this;
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
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "matriculaProfesional")
	public Tecnico updateMatricula(
			@Parameter() @ParameterLayout(named = "matricula") final String matricula) {
		setMatriculaProfesional(matricula);
		return this;
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
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "titulo")
	public Tecnico updateTirulo(
			@Parameter() @ParameterLayout(named = "titulo") final Titulo titulo) {
		setTitulo(titulo);
		return this;
	}

	// region > delete (action)
		@Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
		public void borrarTecnico() {
			final String title = titleService.titleOf(this);
			messageService.informUser(String.format("'%s' deleted", title));
	        repositoryService.remove(this);

		}


		@Override
		public int compareTo(Tecnico o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
//		public String validateFechaNacimiento() {
//		String mensaje="";
//		Calendar hoy = Calendar.getInstance();
//		int hoyDate = hoy.getTime().getYear();
//		if (getFechaNacimiento() == null && (hoyDate-getFechaNacimiento().getYear()<18)) {
//			mensaje="la persona es menor de edad o esta mal cargada la fecha";
//		}
//		return mensaje;
//	}

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