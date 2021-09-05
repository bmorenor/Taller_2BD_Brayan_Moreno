package co.edu.unbosque.model;

public class Estudiante {
	
	private int matricula;
	private String apellido;
	private String nombre;
	private String sexo;
	private int edad;
	private String curso;
	
	public Estudiante(int matricula, String apellido, String nombre, String sexo, int edad, String curso) {
		this.matricula = matricula;
		this.apellido = apellido;
		this.nombre = nombre;
		this.sexo = sexo;
		this.edad = edad;
		this.curso = curso;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}


	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Matricula: " + getMatricula() + ", Apellido: " + getApellido() + ", Nombre: "
				+ getNombre() + ", Sexo: " + getSexo() + ", Edad: " + getEdad() + ", Curso: " + getCurso();
	}
	
	
}
