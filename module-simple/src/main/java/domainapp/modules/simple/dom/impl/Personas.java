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
package domainapp.modules.simple.dom.impl;

import java.util.List;

import org.datanucleus.query.typesafe.TypesafeQuery;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.xactn.TransactionService;
import org.apache.isis.applib.value.DateTime;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "simple.SimpleObjectMenu", repositoryFor = Persona.class)
@DomainServiceLayout(named = "Personas", menuOrder = "10")
public class Personas {

	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	@MemberOrder(sequence = "1")
	public List<Persona> listAll() {
		return repositoryService.allInstances(Persona.class);
	}

	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	@MemberOrder(sequence = "2")
	public List<Persona> findByName(@ParameterLayout(named = "Name") final String name) {
		TypesafeQuery<Persona> q = isisJdoSupport.newTypesafeQuery(Persona.class);
		final QPersona cand = QPersona.candidate();
		q = q.filter(cand.name.indexOf(q.stringParameter("name")).ne(-1));
		return q.setParameter("name", name).executeList();
	}

	@Programmatic
	public Persona findByNameExact(final String name) {
		TypesafeQuery<Persona> q = isisJdoSupport.newTypesafeQuery(Persona.class);
		final QPersona cand = QPersona.candidate();
		q = q.filter(cand.name.eq(q.stringParameter("name")));
		return q.setParameter("name", name).executeUnique();
	}

	public static class CreateDomainEvent extends ActionDomainEvent<Personas> {
	}

	@Action(domainEvent = CreateDomainEvent.class)
	@MemberOrder(sequence = "3")
	public Persona create(@ParameterLayout(named = "Name") final String name,
						  @ParameterLayout(named = "Apellido") final String apellido,
						  @ParameterLayout(named = "Documento") final Integer documento,
						  @ParameterLayout(named = "Tipo De Documento") final TipoDeDocumento td,
						  @ParameterLayout(named = "Telefono") final Integer telefono,
						  @ParameterLayout(named = "E-Mail") final String email,
						  @ParameterLayout(named = "Fecha de Nacimiento") final DateTime fechaNacimiento,
						  @ParameterLayout(named = "Estado Civil") final EstadoCivil estadoCivil,
						  @ParameterLayout(named = "Domicilio") final Domicilio domicilio)

	{
		return repositoryService.persist(new Persona(name,apellido,documento,td,telefono,email,fechaNacimiento,estadoCivil,domicilio));
	}

	@javax.inject.Inject
	RepositoryService repositoryService;

	@javax.inject.Inject
	IsisJdoSupport isisJdoSupport;

}
