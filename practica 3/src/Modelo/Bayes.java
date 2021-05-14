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
		medias[0][0] = 0.0;
		medias[0][1] = 0.0;
		medias[0][2] = 0.0;
		medias[0][3] = 0.0;

		medias[1][0] = 0.0;
		medias[1][1] = 0.0;
		medias[1][2] = 0.0;
		medias[1][3] = 0.0;

	}

	public String resolver() {
		calculaMedias();
		return calcularEjemplo();
	}

	private void calculaMedias() {
		int cont1=0;
		int cont2=0;
		for (int i = 0; i < numElementos; i++) {// por cada elemento
			if(lista.get(i).getClase().equals("Iris-setosa")) {
				for(int n=0;n<4;n++)
				{
					medias[0][n]+=lista.get(i).getElement().get(n);
				}
				cont1++;
			}
			else {
				for(int n=0;n<4;n++)
				{
					medias[1][n]+=lista.get(i).getElement().get(n);
				}
				cont2++;
			}
		}
		for(int i=0;i<4;i++)
		{
			medias[0][i]=medias[0][i]/cont1;
			medias[1][i]=medias[1][i]/cont2;
		}
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
