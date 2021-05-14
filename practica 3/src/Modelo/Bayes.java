package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utils.element;

public class Bayes extends algoritmo {

	private Double medias[][];

	public Bayes(File entrada, File ejemplo) throws FileNotFoundException, IOException {
		super(entrada, ejemplo);
		medias = new Double[2][4];
		medias[0][0] = 4.6;
		medias[0][1] = 3.0;
		medias[0][2] = 4.0;
		medias[0][3] = 0.0;

		medias[1][0] = 6.8;
		medias[1][1] = 3.4;
		medias[1][2] = 4.6;
		medias[1][3] = 0.7;

	}

	public String resolver() {
		ArrayList<Double> m = new ArrayList<Double>();
		m = calculaMedias();
		for (int i = 0; i < numElementos; i++) {// por cada elemento

		}
		return calcularEjemplo();
	}

	private ArrayList<Double> calculaMedias() {
		ArrayList<Double> m = new ArrayList<Double>();
		for (int i = 0; i < numElementos; i++) {// por cada elemento
			
		}

		return m;
	}

	private String calcularEjemplo() {
		int minimoabsoluto = 9999999, minimoactual, pertenece = 0;
		// recorre cada media
		for (int i = 0; i < medias.length; i++) {
			// dentro de cada media hace la resta
			minimoactual = algoritmoBayes(i);
			if (minimoactual < minimoabsoluto) {
				minimoabsoluto = minimoactual;
				pertenece = i;
			}
		}
		if (pertenece == 0)
			return "Iris-setosa";
		else
			return "Iris-versicolor";
	}

//le resta al punto la media y calcula la distacia devolviendo el int
	private int algoritmoBayes(int media) {
		int solucion = 0;
		for (int i = 0; i < medias.length; i++) {
			solucion += Math.pow((ejemplo.getElement().get(i) - medias[media][i]), 2);
		}
		return solucion;
	}

}
