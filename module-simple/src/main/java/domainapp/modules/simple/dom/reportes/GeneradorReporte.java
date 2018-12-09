package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;

public class GeneradorReporte {
	




	public GeneradorReporte() {
	}
	
	public GeneradorReporte(String numeroDeSerie, EstadoUnidad estadoUnidad, String descripcion, Double consumoEnergetico) {
		super();
		this.numeroDeSerie=numeroDeSerie;
		this.estadoUnidad = estadoUnidad;
		this.descripcion = descripcion;
		this.consumoEnergetico = consumoEnergetico;
	}
	
	private String numeroDeSerie;
	
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

	private EstadoUnidad estadoUnidad;
	
	public EstadoUnidad getEstadoUnidad() {
		return estadoUnidad;
	}

	public void setEstadoUnidad(EstadoUnidad estadoUnidad) {
		this.estadoUnidad = estadoUnidad;
	}

	private String descripcion;
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private Double consumoEnergetico;
	
	public Double getConsumoEnergetico() {
		return consumoEnergetico;
	}

	public void setConsumoEnergetico(Double consumoEnergetico) {
		this.consumoEnergetico = consumoEnergetico;
	}
}
