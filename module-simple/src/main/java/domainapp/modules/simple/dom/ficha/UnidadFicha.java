package domainapp.modules.simple.dom.ficha;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Title;

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
	
	@Column()
	@lombok.NonNull
	@Property()
	@Title(prepend="Unidad: ")
	private String descripcion;

    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
//	@Title(prepend="Estado Unidad: ")
	private EstadoUnidad estadoUnidad;
    
	@Column()
	@lombok.NonNull
	@Property()
//	@Title(prepend="Horas de Uso: ")
    private Integer horasUso;
	
	@Override
	public int compareTo(UnidadFicha o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
