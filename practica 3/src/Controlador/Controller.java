package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import Modelo.Bayes;
import Modelo.algoritmo;
import Presentacion.GUIPrincipal;

public class Controller {

	public Controller() {
		GUIPrincipal vista = new GUIPrincipal(this);
		vista.setVisible(true);
	}

	public String run(String algoritmo, File entrada, File ejemplo) {
		try {
			// k medias
			if (algoritmo.equals("Algoritmo Borroso (K medias)")) {
				return "hola";
			}
			// bayes
			else if (algoritmo.equals("Algoritmo Bayes")) {
				Bayes b = new Bayes(entrada, ejemplo);
				return b.ejecutarAlgoritmo();
			}
			// Lloyd
			else if (algoritmo.equals("Algoritmo Lloyd")) {
				return "hola";
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Fichero no encontrado", "File error",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "error al leer el fichero de entrada", "File error",
					JOptionPane.ERROR_MESSAGE);
		}

		return "";
	}

}
