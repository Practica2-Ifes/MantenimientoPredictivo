package domainapp.modules.simple.generador;

import javax.inject.Inject;
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
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;
import lombok.AccessLevel;


@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(
	        name = "sumarGasto", language = "JDOQL",
	        value = "SELECT "
	                + "FROM domainapp.modules.simple.generador.Generador"),

})
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "mantenimiento", table = "Unidades")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="idGenerador")
@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "Generador")
@lombok.Getter @lombok.Setter
public class Generador extends UnidadDeMantenimiento {

    public Generador(String numeroDeSerie, EstadoUnidad estadoUnidad, String descripcion, double consumoEnergetico) {
		super(numeroDeSerie, estadoUnidad, descripcion);
		this.consumoEnergetico = consumoEnergetico;
		// TODO Auto-generated constructor stub
	}

	@javax.jdo.annotations.Column(allowsNull = "false")
    @Property() // editing disabled by default, see isis.properties
    private Double consumoEnergetico;
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "consumoEnergetico")
	public Generador updateConsumoEnergetico(
			@Parameter() @ParameterLayout(named = "Consumo Energetico: ") final double consumoEnergetico) {
		setConsumoEnergetico(consumoEnergetico);
		return this;
	}
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED)
	public Double sumaConsumo() {
		return generadorRepository.sumaConsumo();
	}
	
	@Override
	public int compareTo(UnidadDeMantenimiento o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Inject
	@javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
	GeneradorRepository generadorRepository;

}
