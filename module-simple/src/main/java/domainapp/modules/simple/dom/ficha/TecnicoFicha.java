package domainapp.modules.simple.dom.ficha;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Title;

import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.iinsumo.IInsumo;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "mantenimiento")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="id")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@lombok.Getter @lombok.Setter
@lombok.RequiredArgsConstructor
public class TecnicoFicha implements Comparable<TecnicoFicha>{

	@Column(name="TECNICO_ID")
	@lombok.NonNull
	@Property()
	private Tecnico tecnico;
	
	@Column()
	@lombok.NonNull
	@Property()
	@Title(prepend="Nombre: ")
	private String nombre;
	
	@Column()
	@lombok.NonNull
	@Property()
	@Title(prepend=", Apellido: ")
	private String apellido;
	
	@Column()
	@lombok.NonNull
	@Property()
	@Title(prepend=", Documento: ")
	private Integer documento;
	
	@Column()
	@lombok.NonNull
	@Property()
	private Integer horasTrabajo;
	
	@Override
	public int compareTo(TecnicoFicha o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
