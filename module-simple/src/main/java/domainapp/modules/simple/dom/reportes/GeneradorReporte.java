package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;

public class GeneradorReporte {
	
	public GeneradorReporte() {
	}
	
	public GeneradorReporte(EstadoUnidad estadoUnidad, String descripcion, Double consumoEnergetico) {
		super();
		this.estadoUnidad = estadoUnidad;
		this.descripcion = descripcion;
		this.consumoEnergetico = consumoEnergetico;
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
