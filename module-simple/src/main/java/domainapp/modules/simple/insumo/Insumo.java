package domainapp.modules.simple.insumo;

import java.util.List;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Publishing;

import domainapp.modules.simple.iinsumo.IInsumo;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "mantenimiento", table = "Insumos")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="idInsumo")
@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "Insumo")
@lombok.Getter @lombok.Setter
public class Insumo extends IInsumo {

	public Insumo(List<domainapp.modules.simple.tipoInsumo.TipoInsumo> tipoInsumo, double precio, String descripcion,
			int cantidad) {
		super(tipoInsumo, precio, descripcion, cantidad);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(IInsumo o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
