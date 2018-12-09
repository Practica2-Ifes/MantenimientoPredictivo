package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;

public class TransformadorReporte {
	
	private String numeroDeSerie;
	private Double voltajeEntrada;
	private Double voltajeSalida;
	private EstadoUnidad estadoUnidad;
	private String descripcion;
	
	public TransformadorReporte() {
	}
	
	public TransformadorReporte(String numeroDeSerie, Double voltajeEntrada, Double voltajeSalida, EstadoUnidad estadoUnidad,
			String descripcion) {
		super();
		this.numeroDeSerie=numeroDeSerie;
		this.voltajeEntrada = voltajeEntrada;
		this.voltajeSalida = voltajeSalida;
		this.estadoUnidad = estadoUnidad;
		this.descripcion = descripcion;
	}
	
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	
	public Double getVoltajeEntrada() {
		return voltajeEntrada;
	}
	public void setVoltajeEntrada(Double voltajeEntrada) {
		this.voltajeEntrada = voltajeEntrada;
	}
	public Double getVoltajeSalida() {
		return voltajeSalida;
	}
	public void setVoltajeSalida(Double voltajeSalida) {
		this.voltajeSalida = voltajeSalida;
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
}
