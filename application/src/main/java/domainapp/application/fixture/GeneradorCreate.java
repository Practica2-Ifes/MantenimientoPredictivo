package domainapp.application.fixture;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import domainapp.modules.simple.generador.Generador;
import domainapp.modules.simple.generador.GeneradorMenu;
import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;

public class GeneradorCreate extends FixtureScript{

	private String numeroDeSerie;
	private EstadoUnidad estadoUnidad;
	private String descripcion;
	private double consumoEnergetico;
	
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public GeneradorCreate setNumeroDeSerie(final String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
		return this;
	}

	public EstadoUnidad getEstadoUnidad() {
		return estadoUnidad;
	}

	public GeneradorCreate setEstadoUnidad(final EstadoUnidad estadoUnidad) {
		this.estadoUnidad = estadoUnidad;
		return this;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public GeneradorCreate setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public double getConsumoEnergetico() {
		return consumoEnergetico;
	}

	public GeneradorCreate setConsumoEnergetico(final double consumoEnergetico) {
		this.consumoEnergetico = consumoEnergetico;
		return this;
	}
	
	private Generador generador;
	
	
	
	public Generador getGenerador() {
		return generador;
	}


	@Override
	protected void execute(ExecutionContext ec) {
		// TODO Auto-generated method stub
		String numeroDeSerie=checkParam("numeroDeSerie", ec, String.class);
		String descripcion=checkParam("descripcion", ec, String.class);
		double consumoEnergetico=checkParam("consumoEnergetico", ec, double.class);
		
		this.generador=(Generador) wrap(generadorMenu).crear(numeroDeSerie, EstadoUnidad.Encendido, descripcion, consumoEnergetico);
	}
	@Inject
	GeneradorMenu generadorMenu;
}
