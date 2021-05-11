package Main;

import java.io.FileNotFoundException;
import java.io.IOException;

import Modelo.ID3;

public class MAIN {

	public static void main(String[] args) {

		// Controller c =new Controller();
		try {
			ID3 e = new ID3(5, 14);
		} catch (FileNotFoundException e) {
			System.err.println("no ha encontrado el fichero Juego.txt, ni atributosJuego.txt");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("error en la entrada de datos en la tabla");
			e.printStackTrace();
		}
	}

}
