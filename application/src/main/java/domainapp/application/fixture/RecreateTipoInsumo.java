package domainapp.application.fixture;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import com.google.common.collect.Lists;

import domainapp.modules.simple.tipoInsumo.TipoInsumo;
import domainapp.modules.simple.tipoInsumo.TipoInsumoMenu;

public class RecreateTipoInsumo extends FixtureScript{
	
	List<String> descripciones=Collections.unmodifiableList(Arrays.asList("Electrico","Mecanico", "Electronico"));
	
	public RecreateTipoInsumo() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	private Integer numero;
	
	public Integer getNumero() {
		return numero;
	}
	
	public RecreateTipoInsumo setNumero(final Integer numero) {
		this.numero = numero;
		return this;
	}
	
	private List<TipoInsumo> tipoInsumos=Lists.newArrayList();
	
	@Programmatic
	public List<TipoInsumo> getInsumo(){
		return tipoInsumos;
	}
	
	@Override
	protected void execute(ExecutionContext ec) {
		// TODO Auto-generated method stub
		final int numero=defaultParam("numero", ec, 3);
		if (numero < 0 || numero > descripciones.size()) {
			throw new IllegalArgumentException(String.format("number must be in range [0,%d)", descripciones.size()));
		}
		
		for (int i = 0; i < numero; i++) {
			final TipoInsumo set = wrap (tipoInsumoMenu).crear(descripciones.get(i));
			tipoInsumos.add(set);
		}
	}
	
	@Inject
	TipoInsumoMenu tipoInsumoMenu;
}
