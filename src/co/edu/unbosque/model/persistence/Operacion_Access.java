package co.edu.unbosque.model.persistence;

import java.io.File;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import co.edu.unbosque.model.Estudiante;

public class Operacion_Access {

	private String rutaBD;
	private Database BDAccess;

	public Operacion_Access(String rutaBD) {
		this.rutaBD = rutaBD;
	}

	public boolean conectarBD() {
		try {
			BDAccess = DatabaseBuilder.open(new File(rutaBD));
			return true;
		} catch (Exception e) {
			System.out.println("Error al abrir BD [" + rutaBD + "]: " + e.getMessage());
			return false;
		}
	}

	public String buscarRegistroValor(String nombreTabla, String campo, Object filtro, String campoDevuelto) {
		String valor = "";
		try {
			Table tabla = BDAccess.getTable(nombreTabla);
			Cursor cursor = CursorBuilder.createCursor(tabla);
			for (Row registro : cursor.newIterable().addMatchPattern(campo, filtro)) {
				valor = registro.get(campoDevuelto).toString();
			}
			return valor;
		} catch (Exception e) {
			System.out.println("Error obtener valor de tabla [" + nombreTabla + "]: " + e.getMessage());
			return "";
		}
	}

	public void llenarListaEstudiantes(String nombreTabla, String campo1, String campo2, String campo3, String campo4,
			String campo5, String campo6) {
		
		try {
			int matricula=0,  edad=0;
			String apellido="", nombre="", sexo="", curso="" ;
	
			Table tabla = BDAccess.getTable(nombreTabla);
			for (Row registro : tabla) {
		
				if (!campo1.equalsIgnoreCase(""))
					matricula=Integer.parseInt(String.valueOf(registro.get(campo1)));
			
				if (!campo2.equalsIgnoreCase(""))
					apellido=(String) registro.get(campo2);
				if (!campo3.equalsIgnoreCase(""))
					nombre=(String) registro.get(campo3);
				if (!campo4.equalsIgnoreCase(""))
					sexo=(String) registro.get(campo4);
				if (!campo5.equalsIgnoreCase(""))
					edad=Integer.parseInt(String.valueOf(registro.get(campo5)));
			
				if (!campo6.equalsIgnoreCase(""))
					curso=(String) registro.get(campo6);
				

				Estudiante estudiante =  new Estudiante(matricula, apellido, nombre, sexo, edad, curso);
				EstudianteDAO.getEstudiantes().add(estudiante);
				
			}
			
		} catch (Exception e) {
			System.out.println("Error al mostrar tabla [" + nombreTabla + "]: " + e.getMessage());
		}
	}

	public void mostrarRegistrosTabla(String nombreTabla, String nombreTablaSecundaria, String campoFiltrarTabla1,
			String campoFiltrarTabla2, String campoMostrarTabla2, String campo1, String campo2, String campo3,
			String campo4, String campo5) {
		try {
			Table tabla = BDAccess.getTable(nombreTabla);
			for (Row registro : tabla) {
				System.out.println("-------------------------------------");
				if (!campo1.equalsIgnoreCase(""))
					System.out.println(campo1 + ": " + registro.get(campo1));
				if (!campo2.equalsIgnoreCase(""))
					System.out.println(campo2 + ": " + registro.get(campo2));
				if (!campo3.equalsIgnoreCase(""))
					System.out.println(campo3 + ": " + registro.get(campo3));
				if (!campo4.equalsIgnoreCase(""))
					System.out.println(campo4 + ": " + registro.get(campo4));
				if (!campo5.equalsIgnoreCase(""))
					System.out.println(campo5 + ": " + registro.get(campo5));
				if (!campoFiltrarTabla1.equalsIgnoreCase("") & (!campoFiltrarTabla2.equalsIgnoreCase("")))
					System.out.println(campoMostrarTabla2 + ": " + buscarRegistroValor(nombreTablaSecundaria,
							campoFiltrarTabla2, registro.get(campoFiltrarTabla2), campoMostrarTabla2));
			}
		} catch (Exception e) {
			System.out.println("Error al mostrar tabla [" + nombreTabla + "]: " + e.getMessage());
		}
	}

	public void insertarEstudiantes(String nombreTabla, String apellido, String nombre, String sexo, int edad,
			String curso) {
		try {
			Table table = BDAccess.getTable(nombreTabla);
			table.addRow(null, apellido, nombre, sexo, edad, curso);
		} catch (Exception e) {
			System.out.println("Error al insertar registro en tabla [" + nombreTabla + "]: " + e.getMessage());
		}
	}

	public void eliminarRegistroTabla(String nombreTabla, String campo, int valorFiltro) {
		try {
			Table tabla = BDAccess.getTable(nombreTabla);
			Cursor cursor = CursorBuilder.createCursor(tabla);
			for (Row registro : cursor.newIterable().addMatchPattern(campo, valorFiltro)) {
				tabla.deleteRow(registro);
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar registro en tabla [" + nombreTabla + "]: " + e.getMessage());
		}
	}

	public void actualizarCampoTabla(String nombreTabla, String campo, Object filtro, String nuevoValor) {
		try {
			Table tabla = BDAccess.getTable(nombreTabla);
			Cursor cursor = CursorBuilder.createCursor(tabla);
			for (Row registro : cursor.newIterable().addMatchPattern(campo, filtro)) {
				registro.put(campo, nuevoValor);
				tabla.updateRow(registro);
			}
		} catch (Exception e) {
			System.out.println("Error al actualizar tabla [" + nombreTabla + "]: " + e.getMessage());
		}
	}

	public int numRegistros(String nombreTabla) {
		try {
			Table tabla = BDAccess.getTable(nombreTabla);
			return tabla.getRowCount();
		} catch (Exception e) {
			System.out.println("Error al contar registros de [" + nombreTabla + "]: " + e.getMessage());
			return 0;

		}
	}

	public void desconectarBD() {
		try {
			BDAccess.close();
		} catch (Exception e) {
			System.out.println("Error al cerrar BD [" + rutaBD + "]: " + e.getMessage());
		}
	}

}
