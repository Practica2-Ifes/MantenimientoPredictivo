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


import java.util.Calendar;
import java.util.regex.Pattern;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import domainapp.modules.simple.dom.domicilio.Domicilio;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.i18n.TranslatableString;

import org.apache.isis.schema.utils.jaxbadapters.JodaDateTimeStringAdapter.ForJaxb;
import org.joda.time.LocalDate;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "mantenimiento")
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "personaId")
@javax.jdo.annotations.Unique(name = "Persona_documento_UNQ", members = { "documento" })
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)

@lombok.Getter
@lombok.Setter
@lombok.RequiredArgsConstructor
public abstract class Persona {

	public Persona() {

	}

	@javax.jdo.annotations.Column(allowsNull = "false")
	@lombok.NonNull
	@Property(editing = Editing.DISABLED) // editing disabled by default, see isis.properties
	@XmlJavaTypeAdapter(ForJaxb.class)
	@PropertyLayout(named = "Fecha Nacimiento")
	private LocalDate fechaNacimiento;
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "Fecha Nacimiento")
	public Persona updateFechaNacimiento(@ParameterLayout(named = "fechaNacimiento") final LocalDate fechaNacimiento) {
		setFechaNacimiento(fechaNacimiento);
		return this;
	}

	public LocalDate defaul0UpdateFechaNacimiento() {
		return getFechaNacimiento();
	}

	public TranslatableString validate0UpdateFechaNacimiento(final LocalDate fechaNacimiento) {
		Calendar hoy = Calendar.getInstance();
		int hoyDate = hoy.get(Calendar.YEAR);
		return (fechaNacimiento == null || (hoyDate - fechaNacimiento.getYear() < 18))
				? TranslatableString.tr("la persona es menor de edad o esta mal cargada la fecha ")
				: null;
	}

	@Column(allowsNull = "false", length = 100)
	@Property(editing = Editing.DISABLED)
	@Title()
	@PropertyLayout(named = "Nombre")
	private String name;
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "name")
	public Persona updateName(@Parameter(maxLength = 40) @ParameterLayout(named = "Name") final String name) {
		setName(name);
		return this;
	}

	public String default0UpdateName() {
		return getName();
	}

	public TranslatableString validate0UpdateName(final String name) {
		return name != null && name.contains("!") ? TranslatableString.tr("Exclamation mark is not allowed") : null;
	}

	@Column(allowsNull = "false", length = 100)
	@Property(editing = Editing.DISABLED)
	@Title()
	@PropertyLayout(named = "Apellido")
	private String apellido;
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "Apellido")
	public Persona updateApellido(
			@Parameter(maxLength = 40) @ParameterLayout(named = "apellido") final String apellido) {
		setApellido(apellido);
		return this;
	}

	public String default0UpdateApellido() {
		return getApellido();
	}

	public TranslatableString validate0UpdateApellido(final String apellido) {
		boolean contieneCaracter = Pattern.matches("^[a-zA-z]*$", apellido);
		return (apellido == null || contieneCaracter == false)
				? TranslatableString.tr("contiene caracteres no permitidos" + contieneCaracter)
				: null;
	}

	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	@PropertyLayout(named = "Documento")
	private Integer documento;


	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "Documento")
	public Persona updateDocumento(
			@Parameter(maxLength = 40) @ParameterLayout(named = "documento") final Integer documento) {
		setDocumento(documento);
		return this;
	}
	
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	@PropertyLayout(named = "Tipo de Documento")
	private TipoDeDocumento tipoDocumento;

	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "Tipo de Documento")
	public Persona updateTipoDocumento(
			@Parameter(maxLength = 40) @ParameterLayout(named = "tipoDocumento") final TipoDeDocumento tipoDocumento) {
		setTipoDocumento(tipoDocumento);
		return this;
	}

	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	@PropertyLayout(named = "Telefono")
	private String telefono;
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "Telefono")
	public Persona updateTelefono(
			@Parameter(maxLength = 40) @ParameterLayout(named = "Telefono") final String telefono) {
		setTelefono(telefono);
		return this;
	}

	@Column(allowsNull = "false", length = 100)
	@Property(editing = Editing.DISABLED)
	@PropertyLayout(named = "Email")
	private String email;
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "Email")
	public Persona updateEmail(
			@Parameter(maxLength = 40) @ParameterLayout(named = "email") final String email) {
		setEmail(email);
		return this;
	}


	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	@PropertyLayout(named = "Estado Civil")
	private EstadoCivil estadoCivil;
	
	@Action(semantics = SemanticsOf.IDEMPOTENT, command = CommandReification.ENABLED, publishing = Publishing.ENABLED, associateWith = "estadoCivil")
	public Persona updateEstadoCivil(
			@Parameter(maxLength = 40) @ParameterLayout(named = "Estado Civil") final EstadoCivil estadoCivil) {
		setEstadoCivil(estadoCivil);
		return this;
	}

	@Column(allowsNull = "false", name = "DOMICILIO_ID")
	@Property(editing = Editing.DISABLED)
	@PropertyLayout(named = "Domicilio")
	private Domicilio domicilio;
	
	@javax.jdo.annotations.Column(allowsNull = "true", length = 4000)
	@Property(editing = Editing.ENABLED)
	private String notes;

	// public Integer default0UpdateDocumento() {
	// return getDocumento();
	// }
	//
	// public TranslatableString validate0UpdateDocumento(final String apellido) {
	// boolean contieneCaracter = Pattern.matches("^[a-zA-z]*$", apellido);
	// return (apellido == null) ? TranslatableString.tr("contiene caracteres no
	// permitidos" + contieneCaracter) : null;
	// }

}