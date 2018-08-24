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

package domainapp.modules.simple.fixture;

import org.apache.isis.applib.fixturescripts.BuilderScriptAbstract;
import org.apache.isis.applib.value.DateTime;

import domainapp.modules.simple.dom.impl.Domicilio;
import domainapp.modules.simple.dom.impl.EstadoCivil;
import domainapp.modules.simple.dom.impl.Persona;
import domainapp.modules.simple.dom.impl.Personas;
import domainapp.modules.simple.dom.impl.TipoDeDocumento;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class SimpleObjectBuilder extends BuilderScriptAbstract<Persona, SimpleObjectBuilder> {

    @Getter @Setter
    private String name;
    
    @Getter @Setter
    private String apellido;
    
    @Getter @Setter
    private Integer documento;
    
    @Getter @Setter
    private TipoDeDocumento td;
    
    @Getter @Setter
    private Integer telefono;
    
    @Getter @Setter
    private String email;
    
    @Getter @Setter
    private DateTime fechaNacimiento;
    
    @Getter @Setter
    private EstadoCivil estadoCivil;
    
    @Getter @Setter
    private Domicilio domicilio;

    @Getter
    private Persona object;

    @Override
    protected void execute(final ExecutionContext ec) {

        checkParam("name", ec, String.class);

        object = wrap(personas).create(name,apellido,documento,td,telefono,email, fechaNacimiento, estadoCivil, domicilio);
    }

    @javax.inject.Inject
    Personas personas;

}
