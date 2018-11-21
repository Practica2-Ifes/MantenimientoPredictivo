package domainapp.modules.simple.dom.ficha;

import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Title;

import domainapp.modules.simple.iinsumo.IInsumo;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;
import domainapp.modules.simple.unidadMantenimiento.UnidadDeMantenimiento;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "mantenimiento")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="id")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@lombok.Getter @lombok.Setter
@lombok.RequiredArgsConstructor
public class UnidadFicha implements Comparable<UnidadFicha>{

	@Column(name="UNIDAD_ID")
	@lombok.NonNull
	@Property()
    private UnidadDeMantenimiento unidad;

    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
	private EstadoUnidad estadoUnidad;
    
	@Column()
	@lombok.NonNull
	@Property()
    private Integer horasUso;
	
	@Override
	public int compareTo(UnidadFicha o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
