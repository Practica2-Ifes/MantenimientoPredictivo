package domainapp.application.fixture;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import com.google.common.collect.Lists;

import domainapp.modules.simple.generador.Generador;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;

public class RecreateGenerador extends FixtureScript{
	public final List<String> numeroDeSerie =Collections.unmodifiableList(Arrays.asList("GNR11AF34AA49","GNR12AB54BB49","GNR13AD89CD44")); 
	public final List<String> descripcion=  Collections.unmodifiableList(Arrays.asList(" "," "," "));
	public final List<Double> consumoEnergetico =Collections.unmodifiableList(Arrays.asList(2200000.00,400000.00,1000000.00));
	
	private Integer number;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	private final List<Generador> generadores=Lists.newArrayList();
	
	public RecreateGenerador() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	@Override
	protected void execute(ExecutionContext ec) {
		// TODO Auto-generated method stub
		final int number=defaultParam("number", ec, 3);
		if (number < 0 || number > numeroDeSerie.size() ) {
			throw new IllegalArgumentException(String.format("number must be in range [0,%d)", numeroDeSerie.size()));
		}
		ec.executeChild(this, new GeneradorTearDown());
		for (int i = 0 ; i < number ; i++) {
			final GeneradorCreate generadorCreate=new GeneradorCreate();
			generadorCreate.setConsumoEnergetico(consumoEnergetico.get(i));
			generadorCreate.setNumeroDeSerie(numeroDeSerie.get(i));
			generadorCreate.setEstadoUnidad(EstadoUnidad.Encendido);
			generadorCreate.setDescripcion(descripcion.get(i));
			ec.executeChild(this, generadorCreate.getNumeroDeSerie(), generadorCreate);
			generadores.add(generadorCreate.getGenerador());
			
		}
	}

}
