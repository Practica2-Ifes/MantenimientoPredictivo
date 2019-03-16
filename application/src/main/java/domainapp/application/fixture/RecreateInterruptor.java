package domainapp.application.fixture;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import com.google.common.collect.Lists;

import domainapp.modules.simple.interruptor.Interruptor;
import domainapp.modules.simple.interruptor.InterruptorMenu;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;

public class RecreateInterruptor extends FixtureScript{
	List<String> numeroDeSerie =Collections.unmodifiableList(Arrays.asList("INT11FF12AB45","INT12AB48BC15","INT13AC22DA21"));
	List<String> descripcion=Collections.unmodifiableList(Arrays.asList(" Schneider "," Estanco "," Merlin Gerin "));
	List<Double> amperajeSoportado=Collections.unmodifiableList(Arrays.asList(100000.00,200000.00,250000.00));
	
	public RecreateInterruptor() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	private Integer numero;
	
	
	public Integer getNumero() {
		return numero;
	}
	
	public RecreateInterruptor setNumero(final Integer numero) {
		this.numero=numero;
		return this;
	}
	
	private List<Interruptor> interruptores=Lists.newArrayList();
	
	public List<Interruptor> getInterruptores(){
		return interruptores;
	}
	
	@Override
	protected void execute(ExecutionContext ec) {
		// TODO Auto-generated method stub
		final int numero=defaultParam("numero", ec, 3);
		if(numero < 0 || numero>numeroDeSerie.size()) {
			throw new IllegalArgumentException(String.format("number must be in range [0,%d)", numeroDeSerie.size()));
		}
		for(int i=0; i<numero;i++) {
			final Interruptor set = (Interruptor) wrap (interruptorMenu).crear(numeroDeSerie.get(i), EstadoUnidad.Encendido, descripcion.get(i), amperajeSoportado.get(i));
			interruptores.add(set);
		}
	}
	
	@Inject
	InterruptorMenu interruptorMenu;
}
