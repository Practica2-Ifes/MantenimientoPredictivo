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


import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

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
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.value.DateTime;

import lombok.AccessLevel;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "mantenimientodb")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="id")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@javax.jdo.annotations.Unique(name="Persona_apellido_UNQ", members = {"apellido"})
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@lombok.Getter @lombok.Setter
@lombok.RequiredArgsConstructor
public class Persona implements Comparable<Persona> {

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Nombre: ")
    private String name;

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Apellido: ")
    private String apellido;
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Documento: ")
    private Integer documento;
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Tipo de Documento: ")
    private TipoDeDocumento td;
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Telefono: ")
    private Integer telefono;
    
    @javax.jdo.annotations.Column(allowsNull = "false", length = 100)
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "E-Mail: ")
    private String email;
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Fecha de Nacimiento: ")
    private DateTime fechaNacimiento;
    
    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Estado Civil: ")
    private EstadoCivil estadoCivil;
    
    @javax.jdo.annotations.Column(allowsNull = "false", name="DOMICILIO_ID")
    @lombok.NonNull
    @Property() // editing disabled by default, see isis.properties
    @Title(prepend = "Domicilio: ")
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


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.remove(this);
    }



    //region > toString, compareTo
    @Override
    public String toString() {
        return getName();
    }

    public int compareTo(final Persona other) {
        return ComparisonChain.start()
                .compare(this.getName(), other.getName())
                .result();
    }
    //endregion


    //region > injected services
    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    RepositoryService repositoryService;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    TitleService titleService;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    MessageService messageService;
    
    @javax.inject.Inject
    @javax.jdo.annotations.Column(allowsNull = "false")
    PersonaRepository personas;
    //endregion

}