package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.dom.tecnico.Titulo;

public class TecnicoReporte {
	
	public TecnicoReporte(String name, String apellido, String matricula, Titulo titulo, Integer numeroEmpleado,
			String email) {
		super();
		this.name = name;
		this.apellido = apellido;
		this.matricula = matricula;
		this.titulo = titulo;
		this.numeroEmpleado = numeroEmpleado;
		this.email = email;
	}
	
	public TecnicoReporte() {
		super();
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private String apellido;
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	private String matricula;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	private Titulo titulo;
	
	public Titulo getTitulo() {
		return titulo;
	}
	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}
		
	private Integer numeroEmpleado;
	
	public Integer getNumeroEmpleado() {
		return numeroEmpleado;
	}
	public void setNumeroEmpleado(Integer numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}
	
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
