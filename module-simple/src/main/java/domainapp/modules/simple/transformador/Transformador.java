package domainapp.modules.simple.transformador;

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
import org.apache.isis.applib.annotation.Title;

import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "mantenimiento", table = "Unidades")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="idTransformador")
@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "Transformador")
@lombok.Getter @lombok.Setter
public class Transformador extends UnidadDeMantenimiento {

	
	@javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
	private Double voltajeAnterior;
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "voltajeAnterior")
	public Transformador updateVoltajeAnterior(
			@Parameter() @ParameterLayout(named = "Voltaje Anterior") final Double voltajeAnterior) {
		setVoltajeAnterior(voltajeAnterior);
		return this;
	}
	
	@javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
	private Double voltajeTransformado;
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "voltajeTransformado")
	public Transformador updateVoltajeTransformado(
			@Parameter() @ParameterLayout(named = "Voltaje Transformado") final Double voltajeTransformado) {
		setVoltajeTransformado(voltajeTransformado);
		return this;
	}
	
	public Transformador(EstadoUnidad estadoUnidad, String descripcion, double voltajeAnterior, double voltajeTransformado) {
		super(estadoUnidad, descripcion);
		this.voltajeAnterior= voltajeAnterior;
		this.voltajeTransformado= voltajeTransformado;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(UnidadDeMantenimiento o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
