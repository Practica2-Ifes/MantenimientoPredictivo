/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package domainapp.modules.simple.dom.persona;

import java.util.List;

import domainapp.modules.simple.dom.domicilio.Domicilio;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.DateTime;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Persona.class)
public class PersonaRepository {

	public List<Persona> listarPersonas() {
		return repositoryService.allInstances(Persona.class);
	}

//	@Action(semantics = SemanticsOf.SAFE)
//	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
//	@MemberOrder(sequence = "2")
//	public List<Persona> findByName(@ParameterLayout(named = "Name") final String name) {
//		TypesafeQuery<Persona> q = isisJdoSupport.newTypesafeQuery(Persona.class);
//		final QPersona cand = QPersona.candidate();
//		q = q.filter(cand.name.indexOf(q.stringParameter("name")).ne(-1));
//		return q.setParameter("name", name).executeList();
//	}
//
//	@Programmatic
//	public Persona findByNameExact(final String name) {
//		TypesafeQuery<Persona> q = isisJdoSupport.newTypesafeQuery(Persona.class);
//		final QPersona cand = QPersona.candidate();
//		q = q.filter(cand.name.eq(q.stringParameter("name")));
//		return q.setParameter("name", name).executeUnique();
//	}
//
//	public static class CreateDomainEvent extends ActionDomainEvent<Personas> {
//	}
	
	
	public Persona crear(
			  final String name,
			  final String apellido,
			  final Integer documento,
			  final TipoDeDocumento td,
			  final Integer telefono,
			  final String email,
			  final DateTime fechaNacimiento,
			  final EstadoCivil estadoCivil,
			  final Domicilio domicilio) {
		final Persona object = new Persona(name, apellido, documento, td,
				telefono, email, fechaNacimiento, estadoCivil, domicilio);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}

//	@Action(domainEvent = CreateDomainEvent.class)
//	@MemberOrder(sequence = "3")
//	public Persona create(@ParameterLayout(named = "Name") final String name,
//						  @ParameterLayout(named = "Apellido") final String apellido,
//						  @ParameterLayout(named = "Documento") final Integer documento,
//						  @ParameterLayout(named = "Tipo De Documento") final TipoDeDocumento td,
//						  @ParameterLayout(named = "Telefono") final Integer telefono,
//						  @ParameterLayout(named = "E-Mail") final String email,
//						  @ParameterLayout(named = "Fecha de Nacimiento") final DateTime fechaNacimiento,
//						  @ParameterLayout(named = "Estado Civil") final EstadoCivil estadoCivil,
//						  @ParameterLayout(named = "Domicilio") final Domicilio domicilio)
//
//	{
//		Persona p = repositoryService.instantiate(Persona.class);
//		p.setName(name);
//		p.setApellido(apellido);
//		p.setDocumento(documento);
//		p.setTd(td);
//		p.setTelefono(telefono);
//		p.setEmail(email);
//		p.setFechaNacimiento(fechaNacimiento);
//		p.setEstadoCivil(estadoCivil);
//		p.setDomicilio(domicilio);
//		return repositoryService.persist(p);
//	}

	@javax.inject.Inject
	RepositoryService repositoryService;

	@javax.inject.Inject
	IsisJdoSupport isisJdoSupport;
	
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;


}
