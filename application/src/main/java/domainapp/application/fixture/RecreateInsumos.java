package domainapp.application.fixture;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import com.google.common.collect.Lists;

import domainapp.modules.simple.insumo.Insumo;
import domainapp.modules.simple.insumo.InsumoMenu;
import domainapp.modules.simple.tipoInsumo.TipoInsumo;
import domainapp.modules.simple.tipoInsumo.TipoInsumoRepository;

public class RecreateInsumos extends FixtureScript{
	
	List<Double> precios = Collections.unmodifiableList(Arrays.asList(25.80,300.00,200.90));
	List<String> descripciones=Collections.unmodifiableList(Arrays.asList("juntas de goma 20cm diametro", "destornillador estrella n°13", "llave francesa"));
	
	public RecreateInsumos() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	private Integer numero;
	
	public Integer getNumero() {
		return numero;
	}
	
	public RecreateInsumos setNumero(final Integer numero) {
		this.numero = numero;
		return this;
	}
	
	private List<Insumo> insumos=Lists.newArrayList();
	
	@Programmatic
	public List<Insumo> getInsumo(){
		return insumos;
	}
	
	@Override
	protected void execute(ExecutionContext ec) {
		// TODO Auto-generated method stub
		final int numero=defaultParam("numero", ec, 3);
		if (numero < 0 || numero > descripciones.size()) {
			throw new IllegalArgumentException(String.format("number must be in range [0,%d)", descripciones.size()));
		}
		
		for (int i = 0; i < numero; i++) {
			final Insumo set = (Insumo) wrap (insumoMenu).crear(tipoInsumoRepository.listar() ,precios.get(i), descripciones.get(i));
			insumos.add(set);
		}
	}
	@Inject
	InsumoMenu insumoMenu;
	@Inject
	private TipoInsumoRepository tipoInsumoRepository;
}
