package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.Collections;

import co.edu.unbosque.model.Estudiante;

public class EstudianteDAO {
	private static ArrayList<Estudiante> estudiantes;

	public EstudianteDAO() {
		estudiantes = new ArrayList<Estudiante>();
	}

	public String mostrarLista() {

		String lista = "";

		if (estudiantes.size() >= 1) {
			lista = "Información de los estudiantes guardados: " + "\n" + "\n";

			for (int i = 0; i < estudiantes.size(); i++) {

				lista = lista.concat(estudiantes.get(i).toString() + "\n");
			}
		} else {
			lista = "No se ha encontrado ningún registro por el momento";
		}

		return lista;
	}
	public String mostrarPorCurso(String curso) {
		String lista="";
		
		for(int i=0; i< estudiantes.size();i++) {
			if(estudiantes.get(i).getCurso().equals(curso)) {
				
				lista = lista.concat(estudiantes.get(i).toString() + "\n");
			}
		}
		return lista;
		
	}

	public static ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

}
