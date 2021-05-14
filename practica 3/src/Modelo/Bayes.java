package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utils.element;

public class Bayes extends algoritmo {

	private Double medias[][];

	/*
	 * ENTRADA PARA EL ALGORITMO:
	 * 
	 * En medias primero decir el numero de medias que hay seguido del numero de
	 * dimensiones Double[][] medias = new Double[2][3]; Double[] punto = new Double
	 * [3];
	 * 
	 * Valores de ejemplo del ejercicio de 2017 medias[0][0] = 1; medias[0][1] = 1;
	 * medias[0][2] = 2; medias[1][0] = 3; medias[1][1] = 2; medias[1][2] = 3;
	 * medias[2][0] = 4; medias[2][1] = 3; medias[2][2] = 1; punto [0] = 3; punto
	 * [1] = 2; punto [2] = 2;
	 * 
	 * Bayes bayes = new Bayes(); int sol= bayes.ejecutarAlgoritmo(medias,punto);
	 * 
	 */

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

	public String ejecutarAlgoritmo() {
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
		if(pertenece==0)
			return "Iris-setosa";
		else return "Iris-versicolor";
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
