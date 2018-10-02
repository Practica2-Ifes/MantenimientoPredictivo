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


import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.VersionStrategy;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.common.collect.ComparisonChain;

import domainapp.modules.simple.dom.domicilio.Domicilio;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.schema.utils.jaxbadapters.JodaDateTimeStringAdapter.ForJaxb;
import org.joda.time.LocalDate;

import lombok.AccessLevel;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "mantenimientodb")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="personaId")
@javax.jdo.annotations.Unique(name="Persona_documento_UNQ", members = {"documento"})
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@lombok.Getter @lombok.Setter
@lombok.RequiredArgsConstructor
public abstract class Persona{
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @XmlJavaTypeAdapter(ForJaxb.class)
    @Property() // editing disabled by default, see isis.properties
    private LocalDate fechaNacimiento;
    

	@Column(allowsNull="false",length=100)
	@Property(editing=Editing.DISABLED)
	@PropertyLayout(named="Nombre")
    private String name;


	@Column(allowsNull="false", length=100)
	@Property(editing=Editing.DISABLED)
	@PropertyLayout(named="Apellido")
	private String apellido;
	

	@Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	@PropertyLayout(named="Documento")
    private Integer documento;


	@Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	@PropertyLayout(named="Tipo de Documento")
    private TipoDeDocumento tipoDocumento;
    
  
	@Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	@PropertyLayout(named="Telefono")
    private Integer telefono;

  
  	@Column(allowsNull="false",length=100)
	@Property(editing=Editing.DISABLED)
	@PropertyLayout(named="Email")
    private String email;

	@Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	@PropertyLayout(named="Estado Civil")
    private EstadoCivil estadoCivil;
   
	@Column(allowsNull="false",name="DOMICILIO_ID")
	@Property(editing=Editing.DISABLED)
	@PropertyLayout(named="Email")
    private Domicilio domicilio;
    
    
    @javax.jdo.annotations.Column(allowsNull = "true", length = 4000)
    @Property(editing = Editing.ENABLED)
    private String notes;


    @Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "name")
    public Persona updateName(
            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Name")
            final String name) {
        setName(name);
        return this;
    }

    public String default0UpdateName() {
        return getName();
    }

    public TranslatableString validate0UpdateName(final String name) {
        return name != null && name.contains("!") ? TranslatableString.tr("Exclamation mark is not allowed") : null;
    }


    @javax.inject.Inject
    @javax.jdo.annotations.Column(allowsNull = "false")
    PersonaRepository personaRepository;


}