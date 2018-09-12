//package Empleados;
//
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.jdo.annotations.DatastoreIdentity;
//import javax.jdo.annotations.IdGeneratorStrategy;
//import javax.jdo.annotations.IdentityType;
//import javax.jdo.annotations.PersistenceCapable;
//import javax.jdo.annotations.VersionStrategy;
//
//import org.apache.isis.applib.annotation.Auditing;
//import org.apache.isis.applib.annotation.DomainObject;
//import org.apache.isis.applib.annotation.DomainService;
//import org.apache.isis.applib.annotation.NatureOfService;
//import org.apache.isis.applib.annotation.Publishing;
//import org.apache.isis.applib.services.repository.RepositoryService;
//
//@PersistenceCapable(identityType=IdentityType.DATASTORE, schema="simple")
//@DatastoreIdentity(strategy=IdGeneratorStrategy.IDENTITY,column="id")
//@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
//@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
//
//public class Empleado{
//	public List<Empleado> listarEmpleados(){
//		return repositoryService.allInstances(Empleado.class);
//	}
//	
//	
//	
//	@Inject
//	RepositoryService repositoryService;
//}