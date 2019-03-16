package domainapp.application.fixture;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import com.google.common.collect.Lists;

import domainapp.modules.simple.transformador.Transformador;
import domainapp.modules.simple.transformador.TransformadorMenu;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;

public class RecreateTransformador extends FixtureScript{
	
	List<String> numeroDeSerie =Collections.unmodifiableList(Arrays.asList("TRF11FF12AB45","TRF12AB48BC15","TRF13AC22DA21"));
	List<String> descripcion=Collections.unmodifiableList(Arrays.asList(" Balma "," Lipari "," GBE Power "));
	List<Double> voltajeAnterior=Collections.unmodifiableList(Arrays.asList(800000.00,500000.00,900000.00));
	List<Double> voltajeTransformado=Collections.unmodifiableList(Arrays.asList(400000.00,250000.00,450000.00));
	
	public RecreateTransformador() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	Integer numero;
	
	
	public Integer getNumero() {
		return numero;
	}


	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	List<Transformador> transformadores=Lists.newArrayList();
	
	public List<Transformador> getTransformadores(){
		return transformadores;
	}
	
	

	@Override
	protected void execute(ExecutionContext ec) {
		// TODO Auto-generated method stub
		final int numero=defaultParam("numero", ec, 3);
		if(numero < 0 || numero>numeroDeSerie.size()) {
			throw new IllegalArgumentException(String.format("number must be in range [0,%d)", numeroDeSerie.size()));
		}
		for(int i=0; i<numero;i++) {
			final Transformador set = (Transformador) wrap (transformadorMenu).crear(numeroDeSerie.get(i), EstadoUnidad.Encendido, descripcion.get(i), voltajeAnterior.get(i), voltajeTransformado.get(i));
			transformadores.add(set);
		}
	}
	@Inject
	TransformadorMenu transformadorMenu;
}
