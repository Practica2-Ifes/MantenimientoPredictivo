package domainapp.application.fixture;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.joda.time.LocalDate;
import com.google.common.collect.Lists;
import domainapp.modules.simple.dom.domicilio.DomicilioRepository;
import domainapp.modules.simple.dom.persona.EstadoCivil;
import domainapp.modules.simple.dom.persona.TipoDeDocumento;
import domainapp.modules.simple.dom.tecnico.ART;
import domainapp.modules.simple.dom.tecnico.ObraSocial;
import domainapp.modules.simple.dom.tecnico.SectorDeTrabajo;
import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.dom.tecnico.TecnicoMenu;
import domainapp.modules.simple.dom.tecnico.TecnicoRepository;
import domainapp.modules.simple.dom.tecnico.Titulo;


public class RecreateTecnico extends FixtureScript{
	
	public final List<String> names=Collections.unmodifiableList(Arrays.asList("andres","gaston","federico"));
	public final List<String> apellidos= Collections.unmodifiableList(Arrays.asList("perez","campos","gonzalez"));
	public final List<Integer> documentos=Collections.unmodifiableList(Arrays.asList(36775544,24156789,37554466));
	public final List<String> telefonos= Collections.unmodifiableList(Arrays.asList("155123465","155444443","157654159"));
	public final List<String> emails = Collections.unmodifiableList(Arrays.asList("romero92@gmail.com","a@a.com","b@n.com"));
	final List<Integer> numeroEmpleados=Collections.unmodifiableList(Arrays.asList(156,687,159));
	final List<String> matriculaProfesionales=Collections.unmodifiableList(Arrays.asList("516","6460","1597"));
	
	public RecreateTecnico() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	private Integer numero;

	public Integer getNumero() {
		return numero;
	}
	
	public RecreateTecnico setNumero(final Integer numero) {
		this.numero = numero;
		return this;
	}
	
	private final List<Tecnico> tecnicos = Lists.newArrayList();
	
	@Programmatic
	public List<Tecnico> getTecnicos() {
		return tecnicos;
	}
	
	@Override
	protected void execute(ExecutionContext ec) {
		// TODO Auto-generated method stub
		final int numero = defaultParam("numero", ec, 3);

		if (numero < 0 || numero > names.size()) {
			throw new IllegalArgumentException(String.format("number must be in range [0,%d)", names.size()+"SIZE = "+names.size()));
		}
		
		 LocalDate fechaNacimientos = new LocalDate(1985,6,8);
		
		for (int i = 0; i < numero; i++) {
			final Tecnico set = wrap (tecnicoMenu).crear(names.get(i), apellidos.get(i), documentos.get(i), TipoDeDocumento.DNI, telefonos.get(i), emails.get(i),
					fechaNacimientos,EstadoCivil.Soltero, domicilioRepository.listarDomicilios().get(0), numeroEmpleados.get(i), SectorDeTrabajo.mantenimiento,
					ObraSocial.OSDE, ART.SANCOR_ART, matriculaProfesionales.get(i), Titulo.Tecnico_Electrico);
			tecnicos.add(set);
		}
	}
	@Inject
	private TecnicoMenu tecnicoMenu;
	@Inject
	private DomicilioRepository domicilioRepository;
}
