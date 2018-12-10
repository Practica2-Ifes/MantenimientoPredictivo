package domainapp.application.fixture;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import com.google.common.collect.Lists;

import domainapp.modules.simple.dom.domicilio.Domicilio;
import domainapp.modules.simple.dom.domicilio.DomicilioMenu;
import domainapp.modules.simple.dom.domicilio.Provincia;

public class RecreateDomicilio extends FixtureScript {
	public final List<String> calles = Collections.unmodifiableList(Arrays.asList("borges", "richeri", "paimun"));
	public final List<Integer> alturas = Collections.unmodifiableList(Arrays.asList(123, 111, 159));
	public final List<String> departamentos = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
	public final List<String> barrios = Collections.unmodifiableList(Arrays.asList("confluencia", "belgrano", "mudon"));
	public final List<String> localidades = Collections.unmodifiableList(Arrays.asList("pehuenia", "copahue", "zapala"));
	

	public RecreateDomicilio() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private Integer numero;

	public Integer getNumero() {
		return numero;
	}

	public RecreateDomicilio setNumero(final Integer numero) {
		this.numero = numero;
		return this;
	}

	private final List<Domicilio> domicilios = Lists.newArrayList();

	@Programmatic
	public List<Domicilio> getDomicilios() {
		return domicilios;
	}

	@Override
	protected void execute(ExecutionContext ec) {
		// TODO Auto-generated method stub
		final int numero = defaultParam("numero", ec, 3);

		if (numero < 0 || numero > calles.size()) {
			throw new IllegalArgumentException(String.format("number must be in range [0,%d)", calles.size()));
		}

		for (int i = 0; i < numero; i++) {
			final Domicilio set = wrap(domicilioMenu).crear(calles.get(i), alturas.get(i), barrios.get(i), Provincia.Neuquen, localidades.get(i), departamentos.get(i));
			domicilios.add(set);
		}

	}
	@Inject
	private DomicilioMenu domicilioMenu;
}
