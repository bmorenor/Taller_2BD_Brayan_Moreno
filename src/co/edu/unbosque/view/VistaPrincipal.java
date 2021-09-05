package co.edu.unbosque.view;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class VistaPrincipal {

	private Scanner leer;
	

	public VistaPrincipal() {

		leer = new Scanner(System.in);

	}

	public void mostrarErrorConsola(String mensaje) {
		System.out.println("AVISO: "+mensaje);
	}

	public String mostrarMensajeConsola(String mensaje) {
		System.out.println(mensaje);
		return mensaje;
	}

	public void mostrarErrorVentana(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMensajeVentana(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public String capturarDato(String m) {
		String resultado = "";
		System.out.println(m);
		resultado = leer.nextLine();
		if(resultado.isEmpty()) {
			resultado=" ";
		}

		return resultado;
	}

}
