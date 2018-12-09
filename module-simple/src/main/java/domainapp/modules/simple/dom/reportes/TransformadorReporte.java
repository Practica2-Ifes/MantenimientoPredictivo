package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.unidadMantenimiento.EstadoUnidad;

public class TransformadorReporte {
	
	private Double voltajeEntrada;
	private Double voltajeSalida;
	private EstadoUnidad estadoUnidad;
	private String descripcion;
	
	public TransformadorReporte() {
	}
	
	public TransformadorReporte(Double voltajeEntrada, Double voltajeSalida, EstadoUnidad estadoUnidad,
			String descripcion) {
		super();
		this.voltajeEntrada = voltajeEntrada;
		this.voltajeSalida = voltajeSalida;
		this.estadoUnidad = estadoUnidad;
		this.descripcion = descripcion;
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
