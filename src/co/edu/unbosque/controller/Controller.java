package co.edu.unbosque.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import co.edu.unbosque.model.Estudiante;
import co.edu.unbosque.model.persistence.EstudianteDAO;
import co.edu.unbosque.model.persistence.Operacion_Access;
import co.edu.unbosque.view.VistaPrincipal;

/**
 * 
 * @author Brayan Camilo Moreno Romero Class Controller
 */
public class Controller {

	private VistaPrincipal vistaP;
	private Operacion_Access accesoMDB;
	private EstudianteDAO estudianteDAO;

	public Controller() {
		final String rutaBD = "ACADEMIA.accdb";
		vistaP = new VistaPrincipal();
		accesoMDB = new Operacion_Access(rutaBD);

		accesoMDB.conectarBD();

		vistaP.mostrarMensajeConsola("Bienvenido a la Base de datos 'ACADEMIA' \n");

		funcionar();

	}

	public void funcionar() {
		try {
			String opcion = vistaP.capturarDato("A través de este menú elija la opción que necesita"
					+ "\n\t1. Registrar" + "\n\t2. Consultar por curso" + "\n\t3. Terminar");
			int opcion1 = Integer.parseInt(opcion);

			switch (opcion1) {

			case 1: {

				if (accesoMDB.conectarBD()) {
					String apellido = vistaP.capturarDato("Por favor ingrese los apellidos: ");
					while (validarCampos(apellido.replace(" ", "")) == false || apellido.equals("")) {
						vistaP.mostrarErrorConsola(
								"Por favor revise que el campo no este vacio y\nque este correctamente\n");
						apellido = vistaP.capturarDato("Por favor ingrese los apellidos: ");
					}

					String nombre = vistaP.capturarDato("Por favor ingrese los nombres: ");
					while (validarCampos(nombre.replace(" ", "")) == false || nombre.equals("")) {
						vistaP.mostrarErrorConsola(
								"Por favor revise que el campo no este vacio y\nque este correctamente\n");
						nombre = vistaP.capturarDato("Por favor ingrese los nombres: ");
					}
					String sexo = vistaP
							.capturarDato("Por favor ingrese el sexo " + "F para femenino o M para masculino:");
					while (validarCampoSexo(sexo) == false || sexo.equals("")) {
						vistaP.mostrarErrorConsola(
								"Por favor revise que el campo no este vacio y\nque este correctamente\n");
						sexo = vistaP.capturarDato("Por favor ingrese el sexo " + "F para femenino o M para masculino:");
					}

					String edad1 = vistaP.capturarDato("Por favor ingrese el edad: ");
					while (isNumeric(edad1) == false || edad1.equals("")) {
						vistaP.mostrarErrorConsola(
								"Por favor revise que el campo no este vacio y\nque este correctamente\n");
						edad1 = vistaP.capturarDato("Por favor ingrese el edad: ");
					}
					int edad = Integer.parseInt(edad1);
					while (edad > 100 || edad < 5) {
						vistaP.mostrarErrorConsola("Por favor ingrese una edad valida\n");
						edad1 = vistaP.capturarDato("Por favor ingrese el edad: ");
						edad = Integer.parseInt(edad1);
					}

					String curso1 = vistaP.capturarDato(
							"Por favor ingrese el curso" + "\n\t1.Access" + "\n\t2.Windows" + "\n\t3.Word");

					while (verificarCurso(curso1)==false) {
						vistaP.mostrarErrorConsola("Por favor ingrese solo alguna de las 3 opciones en número\n");
						curso1 = vistaP.capturarDato(
								"Por favor ingrese el curso: " + "\n\t1.Access" + "\n\t2.Windows" + "\n\t3.Word");
					}

					int opcion2 = Integer.parseInt(curso1);
					String curso = "";

					switch (opcion2) {
					case 1: {
						curso = "Access";
						break;
					}
					case 2: {
						curso = "Windows";
						break;
					}
					case 3: {
						curso = "Word";
						break;
					}

					}
					apellido = arreglarPalabras(apellido);
					nombre = arreglarPalabras(nombre);
					sexo = arreglarLetras(sexo);
					accesoMDB.insertarEstudiantes("Estudiante", apellido, nombre, sexo, edad, curso);

					vistaP.mostrarMensajeConsola("Estudiante agregado satisfactoriamente\n");

					funcionar();

				}
				break;
			}

			case 2: {

				String opcionCaso2 = vistaP.capturarDato(
						"Por favor ingrese el curso a filtrar \n(En caso de no ingresar nada se mostraran todos los datos)");
				opcionCaso2 = opcionCaso2.toLowerCase();

				while (verificarConsulta(opcionCaso2) == false) {

					vistaP.mostrarErrorConsola("Por favor verificar que sea un curso valido (Access, Windows o Word)");
					opcionCaso2 = vistaP.capturarDato(
							"Por favor ingrese el curso a filtrar \n(En caso de no ingresar nada se mostraran todos los datos)");
					opcionCaso2 = opcionCaso2.toLowerCase();

				}

				opcionCaso2 = arreglarLetras(opcionCaso2);
				if (opcionCaso2.equals("Access")) {
					estudianteDAO = new EstudianteDAO();

					accesoMDB.llenarListaEstudiantes("Estudiante", "Matrícula", "Apellidos", "Nombres", "Sexo", "Edad",
							"Curso");

					Collections.sort(estudianteDAO.getEstudiantes(), new Comparator<Estudiante>() {

						@Override
						public int compare(Estudiante o1, Estudiante o2) {
							return new String(o2.getApellido()).compareTo(new String(o1.getApellido()));

						}
					});
					vistaP.mostrarMensajeConsola(estudianteDAO.mostrarPorCurso("Access") + "\n");
					funcionar();
				}
				if (opcionCaso2.equals("Windows")) {
					estudianteDAO = new EstudianteDAO();

					accesoMDB.llenarListaEstudiantes("Estudiante", "Matrícula", "Apellidos", "Nombres", "Sexo", "Edad",
							"Curso");

					Collections.sort(estudianteDAO.getEstudiantes(), new Comparator<Estudiante>() {

						@Override
						public int compare(Estudiante o1, Estudiante o2) {
							return new String(o2.getApellido()).compareTo(new String(o1.getApellido()));

						}
					});
					vistaP.mostrarMensajeConsola(estudianteDAO.mostrarPorCurso("Windows") + "\n");
					funcionar();
				}
				if (opcionCaso2.equals("Word")) {
					estudianteDAO = new EstudianteDAO();

					accesoMDB.llenarListaEstudiantes("Estudiante", "Matrícula", "Apellidos", "Nombres", "Sexo", "Edad",
							"Curso");

					Collections.sort(estudianteDAO.getEstudiantes(), new Comparator<Estudiante>() {

						@Override
						public int compare(Estudiante o1, Estudiante o2) {
							return new String(o2.getApellido()).compareTo(new String(o1.getApellido()));

						}
					});
					vistaP.mostrarMensajeConsola(estudianteDAO.mostrarPorCurso("Word") + "\n");
					funcionar();
				}
				if (opcionCaso2.equals(" ")) {
					estudianteDAO = new EstudianteDAO();

					accesoMDB.llenarListaEstudiantes("Estudiante", "Matrícula", "Apellidos", "Nombres", "Sexo", "Edad",
							"Curso");

					Collections.sort(estudianteDAO.getEstudiantes(), new Comparator<Estudiante>() {

						@Override
						public int compare(Estudiante o1, Estudiante o2) {
							return new String(o2.getApellido()).compareTo(new String(o1.getApellido()));

						}
					});
					vistaP.mostrarMensajeConsola(estudianteDAO.mostrarLista() + "\n");
					funcionar();
				}

			}
				break;
			case 3: {
				accesoMDB.desconectarBD();
				vistaP.mostrarMensajeConsola("Aplicación cerrada");
				break;
			}
			default:
				vistaP.mostrarErrorConsola("Por favor ingrese valores numericos enteros positivos\n");
				funcionar();
			}

		} catch (Exception e) {
			vistaP.mostrarErrorConsola("Por favor ingrese valores numericos enteros positivos\n");
			funcionar();
		}

	}

	private static boolean isNumeric(String cadena) {
		return cadena.matches("[0-9]*");
	}

	private boolean validarCampos(String cadena) {
		return cadena.matches("[a-zA-Z_ñ_Ñ]*");

	}

	private boolean validarCampoSexo(String cadena) {
		boolean validado = true;
		if (cadena.equals("F") || cadena.equals("f") || cadena.equals("M") || cadena.equals("m")) {
			validado = true;
		} else {
			validado = false;
		}

		return validado;

	}

	public String arreglarLetras(String cadena) {
		char[] arr = cadena.toCharArray();
		arr[0] = Character.toUpperCase(arr[0]);
		return new String(arr);

	}

	public String arreglarPalabras(String cadena) {
		String nuevaCadena = "";
		cadena = cadena.toLowerCase();
		String[] parts = cadena.split(" ");
		for (int i = 0; i < parts.length; i++) {
			String parte = parts[i];

			if (i == parts.length - 1) {
				nuevaCadena += arreglarLetras(parte);
			} else {
				nuevaCadena += arreglarLetras(parte) + " ";
			}

		}
		return nuevaCadena;

	}

	public boolean verificarConsulta(String cadena) {
		boolean verificacion = true;
		if (cadena.equals("access") || cadena.equals("windows") || cadena.equals("word") || cadena.equals(" ")) {
			verificacion = true;
		} else {
			verificacion = false;
		}

		return verificacion;

	}
	public boolean verificarCurso(String curso1) {
		boolean verificar=true;
		if(curso1.equals("1") || curso1.equals("2") || curso1.equals("3")) {
			verificar=true;
		}else {
			verificar=false;
		}
		return verificar;
		
	}

}
