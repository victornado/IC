package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ID3 {
	private Tabla tablaIni;
	private double p, n;
	private List<Double> meritos;

	public ID3(int columnas, int filas) throws FileNotFoundException, IOException {
		tablaIni = new Tabla(columnas, filas);
		cogerDatos(filas);
		cogerNombres(columnas);
		p = tablaIni.getVeces().get("si") / (tablaIni.getVeces().get("si") + tablaIni.getVeces().get("no"));
		n = tablaIni.getVeces().get("no") / (tablaIni.getVeces().get("si") + tablaIni.getVeces().get("no"));
		meritos = new ArrayList<Double>(columnas);
		calculaMeritos(columnas-1);
		
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

	private void calculaMeritos(int columnas) {
		String[][] tabla = tablaIni.getTabla();
		for (int i = 0; i < columnas; i++) {

			// N
			int N = tabla.length;

			// a
			double[] a = new double[tablaIni.getTipos().get(i).size()];
			String[] s = new String[tablaIni.getTipos().get(i).size()];
			tablaIni.getTipos().get(i).toArray(s);
			for (int j = 0; j < s.length; j++) {
				String string = s[j];
				a[j] = tablaIni.getVeces().get(string);
			}

			// r
			double[] r = new double[a.length];
			for (int j = 0; j < s.length; j++) {
				r[j] = a[j] / N;
			}

			// merito
			double p, n;
			double sol = 0.0;
			for (int k = 0; k < s.length; k++) {
				p = tablaIni.getPositivos().get(s[k]) / tablaIni.getVeces().get(s[k]);
				n = (tablaIni.getVeces().get(s[k]) - tablaIni.getPositivos().get(s[k])) / tablaIni.getVeces().get(s[k]);
				sol += entropia(p, n) * r[k];
			}
			meritos.add(sol);

		}
	}

	private double log(double num, double base) {
		if(num!=0)
			return (Math.log(num) / Math.log(base));
		else return 0.0;
	}

	private double entropia(double p, double n) {
		return (-log(p, 2) * (p) - n * log(n, 2));
	}
}
