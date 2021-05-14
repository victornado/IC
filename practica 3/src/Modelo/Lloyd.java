package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Lloyd extends algoritmo {
	private Double tolerancia;
	private int kMax;
	private Double constante;
	private Double[][] v;

	public Lloyd(File entrada, File ejemplo) throws FileNotFoundException, IOException {
		super(entrada, ejemplo);
		tolerancia = Math.pow(10, -10);
		kMax = 10;
		constante = 0.1;
		v = new Double[2][4];
		v[0][0] = 4.6;
		v[0][1] = 3.0;
		v[0][2] = 4.0;
		v[0][3] = 0.0;

		v[1][0] = 6.8;
		v[1][1] = 3.4;
		v[1][2] = 4.6;
		v[1][3] = 0.7;

	}
	
	public String resolver() {
		Boolean ok = false;
		int cont = 0;
		while (!ok && cont < kMax) {
			Double[][] copia = new Double[2][4];
			for (int i = 0; i < copia.length; i++) {
				for (int j = 0; j < copia[i].length; j++) {
					copia[i][j]=v[i][j];
				}
			}

			for (int i = 0; i < numElementos; i++) {
				int centro = determinarCentro(i);// centro mas cercano a ese elemento
				actualizarCentro(centro, i);
			}
			// tolerancia(¿seguir iterando?)
			ok = comprobarTolerancia(copia);
			cont++;
		}
		return SeleccionarClase();
	}

	private String SeleccionarClase() {
		ArrayList<Double> elemento = ejemplo.getElement();
		ArrayList<Double> distancias = new ArrayList<Double>();
		

		for (int i = 0; i < v.length; i++) {
			double dist = 0;
			for (int j = 0; j < v[i].length; j++) {
				dist += Math.pow(elemento.get(j) - v[i][j], 2);
			}
			distancias.add(dist);
		}
		
		int cont = 0;
		double min = distancias.get(0);
		for (int i = 1; i < distancias.size(); i++) { // seleccionamos el menor centro
			if (distancias.get(i) < min) {
				min = distancias.get(i);
				cont = i;
			}
		}
		if (cont == 0)
			return "Iris-setosa";
		else
			return "Iris-versicolor";
	}

	private boolean comprobarTolerancia(Double[][] copia) {
		ArrayList<Double> incrementos = new ArrayList<Double>();
		
		for (int i = 0; i < v.length; i++) {
			double dist = 0;
			for (int j = 0; j < v[i].length; j++) {
				dist += Math.pow(v[i][j] - copia[i][j], 2);
			}
			incrementos.add(Math.sqrt(dist));
		}
		// recorremos la los incrementos para ver si hay que seguir iterando
		for (int i = 0; i < incrementos.size(); i++) {
			if (incrementos.get(i) >= tolerancia) {
				return false;
			}
		}
		return true;
	}

	private int determinarCentro(int x) {
		ArrayList<Double> elemento = lista.get(x).getElement();
		ArrayList<Double> distancias = new ArrayList<Double>();
		

		for (int i = 0; i < v.length; i++) {// calculamos las d
			double dist = 0;
			for (int j = 0; j < v[i].length; j++) {
				dist += Math.pow(elemento.get(j) - v[i][j], 2);
			}
			distancias.add(dist);
		}

		int cont = 0;
		double min = distancias.get(0);

		for (int i = 1; i < distancias.size(); i++) { // seleccionamos el menor centro
			if (distancias.get(i) < min) {
				min = distancias.get(i);
				cont = i;
			}
		}
		return cont;
	}

	private void actualizarCentro(int centro, int elemento) {
		for (int i = 0; i < v[centro].length; i++) {
			v[centro][i] += (constante * (lista.get(elemento).getElement().get(i) - v[centro][i]));
		}
	}
}
