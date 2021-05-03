package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ID3 {
	private Tabla tablaIni;

	public ID3() throws FileNotFoundException, IOException {
		tablaIni = new Tabla(5, 14);
		cogerDatos(14);
		cogerNombres(5);
	}

	private void cogerNombres(int columnas) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("resources/AtributosJuego.txt"));
		String fila = br.readLine();
		tablaIni.meterNombres(fila, ",");
	}

	private void cogerDatos(int columnas) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("resources/juego.txt"));

		for (int i = 0; i < columnas; i++) {
			String fila = br.readLine();
			tablaIni.meterFila(fila, ",");
		}
	}
}
