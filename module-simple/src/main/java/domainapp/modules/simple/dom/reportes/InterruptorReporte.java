package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;

public class InterruptorReporte {
	
	private String numeroDeSerie;
	private EstadoUnidad estadoUnidad;
	private String descripcion;
	private Double amperajeSoportado;
	
	public InterruptorReporte() {
		
	}
	
	public InterruptorReporte(String numeroDeSerie, EstadoUnidad estadoUnidad, String descripcion,
			Double amperajeSoportado) {
		super();
		this.numeroDeSerie = numeroDeSerie;
		this.estadoUnidad = estadoUnidad;
		this.descripcion = descripcion;
		this.amperajeSoportado = amperajeSoportado;
	}
	
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	public EstadoUnidad getEstadoUnidad() {
		return estadoUnidad;
	}
	public void setEstadoUnidad(EstadoUnidad estadoUnidad) {
		this.estadoUnidad = estadoUnidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getAmperajeSoportado() {
		return amperajeSoportado;
	}
	public void setAmperajeSoportado(Double amperajeSoportado) {
		this.amperajeSoportado = amperajeSoportado;
	}

}
