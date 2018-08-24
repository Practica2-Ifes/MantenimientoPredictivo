///*
// *  Licensed to the Apache Software Foundation (ASF) under one
// *  or more contributor license agreements.  See the NOTICE file
// *  distributed with this work for additional information
// *  regarding copyright ownership.  The ASF licenses this file
// *  to you under the Apache License, Version 2.0 (the
// *  "License"); you may not use this file except in compliance
// *  with the License.  You may obtain a copy of the License at
// *
// *        http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing,
// *  software distributed under the License is distributed on an
// *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// *  KIND, either express or implied.  See the License for the
// *  specific language governing permissions and limitations
// *  under the License.
// */
//package domainapp.application.integtests;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.junit.Test;
//
//import domainapp.modules.simple.dom.impl.Persona;
//import domainapp.modules.simple.dom.impl.Personas;
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class Smoke_IntegTest extends DomainAppIntegTestAbstract {
//
//    @Inject
//    Personas menu;
//
//    @Test
//    public void create() {
//
//        // when
//        List<Persona> all = wrap(menu).listAll();
//
//     // *  Licensed to the Apache Software Foundation (ASF) under one or more
//      //package domainapp.modules.simple.specglue;
//      //
//      //import java.util.List;
//      //
//      //public class SimpleObjectMenuGlue extends CukeGlueAbstract2 {
//      //
////          @Given("^there are.* (\\d+) simple objects$")
////          public void there_are_N_simple_objects(int n) throws Throwable {
////              final List<Persona> list = wrap(personas).listAll();
////              assertThat(list.size(), is(n));
////          }
//      //    
////          @When("^.*create a .*simple object$")
////          public void create_a_simple_object() throws Throwable {
////              wrap(personas).create(UUID.randomUUID().toString());
////          }
//      //
////          @Inject
////          Personas personas;
//      //
//      //}
//        // then
//        assertThat(all).isEmpty();
//
//
//
//        // when
//        final Persona fred = wrap(menu).create("Fred");
//        transactionService.flushTransaction();
//
//        // then
//        all = wrap(menu).listAll();
//        assertThat(all).hasSize(1);
//        assertThat(all).contains(fred);
//
//
//
//        // when
//        final Persona bill = wrap(menu).create("Bill");
//        transactionService.flushTransaction();
//
//        // then
//        all = wrap(menu).listAll();
//        assertThat(all).hasSize(2);
//        assertThat(all).contains(fred, bill);
//
//
//
//        // when
//        wrap(fred).updateName("Freddy");
//        transactionService.flushTransaction();
//
//        // then
//        assertThat(wrap(fred).getName()).isEqualTo("Freddy");
//
//
//        // when
//        wrap(fred).setNotes("These are some notes");
//
//        // then
//        assertThat(wrap(fred).getNotes()).isEqualTo("These are some notes");
//
//
//        // when
//        wrap(fred).delete();
//        transactionService.flushTransaction();
//
//
//        all = wrap(menu).listAll();
//        assertThat(all).hasSize(1);
//
//    }
//
//}

