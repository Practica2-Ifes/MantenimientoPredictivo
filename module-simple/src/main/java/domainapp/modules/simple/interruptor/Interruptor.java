package domainapp.modules.simple.interruptor;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Publishing;

import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "mantenimientodb", table = "Unidades")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="idInterruptor")
@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "Interruptor")
@lombok.Getter @lombok.Setter
public class Interruptor extends UnidadDeMantenimiento {

	public Interruptor(EstadoUnidad estadoUnidad, String descripcion) {
		super(estadoUnidad, descripcion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(UnidadDeMantenimiento arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
