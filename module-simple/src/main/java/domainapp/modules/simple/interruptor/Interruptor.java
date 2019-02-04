package domainapp.modules.simple.interruptor;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;

import domainapp.modules.simple.transformador.Transformador;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "mantenimiento", table = "Unidades")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="idInterruptor")
@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "Interruptor")
@lombok.Getter @lombok.Setter
public class Interruptor extends UnidadDeMantenimiento {
	
	@Column(allowsNull="false")
	@lombok.NonNull
	@Property()
	private Double amperajeSoportado;
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "amperajeSoportado")
	public Interruptor updateAmperajeSoportado(
			@Parameter() @ParameterLayout(named = "Amperaje Soportado") final Double amperajeSoportado) {
		setAmperajeSoportado(amperajeSoportado);
		return this;
	}
	
	


	@Override
	public int compareTo(UnidadDeMantenimiento arg0) {
		// TODO Auto-generated method stub
		return 0;
	}




	public Interruptor(String numeroDeSerie, EstadoUnidad estadoUnidad, String descripcion,Double amperajeSoportado) {
		super(numeroDeSerie, estadoUnidad, descripcion);
		this.amperajeSoportado=amperajeSoportado;
		// TODO Auto-generated constructor stub
	}

}
