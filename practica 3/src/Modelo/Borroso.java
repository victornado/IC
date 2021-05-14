package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Borroso extends algoritmo {
	private int b;
	private Double tolerancia;
	private Double[][] v, u;

	public Borroso(File entrada, File ejemplo) throws FileNotFoundException, IOException {
		super(entrada, ejemplo);
		b = 2;
		tolerancia = 0.01;
		u = new Double[2][numElementos];
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
		calcularU();
	}
	private void calcularU() {
		for(int i=0;i<u.length;i++) {
			for(int j=0;j<u[i].length;j++) {
				//p(v/x)
				int solucion = 0;
				for (int cont = 0; cont < v[i].length; cont++) {
					solucion += Math.pow((ejemplo.getElement().get(cont) - v[i][cont]), 2);
				}
			}
		}
	}

}
