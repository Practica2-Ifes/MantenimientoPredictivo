package domainapp.modules.simple.generador;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.Title;

import domainapp.modules.simple.dom.domicilio.Provincia;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "mantenimiento", table = "Unidades")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="idGenerador")
@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "Generador")
@lombok.Getter @lombok.Setter
public class Generador extends UnidadDeMantenimiento {

    public Generador(EstadoUnidad estadoUnidad, String descripcion, double consumoEnergetico) {
		super(estadoUnidad, descripcion);
		this.consumoEnergetico = consumoEnergetico;
		// TODO Auto-generated constructor stub
	}

	@javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Consumo Energetico: ")
    private double consumoEnergetico;
	
	@Override
	public int compareTo(UnidadDeMantenimiento o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
