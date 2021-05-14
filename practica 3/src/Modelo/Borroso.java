package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Borroso extends algoritmo {
	private int b;
	private Double tolerancia;
	private Double[][] medias, pertenencia, puntos;

	public Borroso(File entrada, File ejemplo) throws FileNotFoundException, IOException {
		super(entrada, ejemplo);
		b = 2;
		tolerancia = 0.01;
		pertenencia = new Double[2][numElementos]; //grado de pertenencia de un elemento a cada clase
		puntos = new Double[numElementos][4]; //4 dimensiones de cada punto
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

	public String resolver(Double[] punto) {
		Double valor_media1=0.0;
		Double valor_media2=0.0;
		Double pertenencia_media1=0.0;
		Double pertenencia_media2=0.0;
		for (int i = 0; i < medias[0].length; i++) {
			valor_media1 += Math.pow((punto[i] - medias[0][i]), 2);
		}
		for (int i = 0; i < medias[1].length; i++) {
			valor_media2 += Math.pow((punto[i] - medias[1][i]), 2);
		}
		//grado de pertenencia del elemento a la media 1
		pertenencia_media1= (1/valor_media1)/((1/valor_media1)+(1/valor_media2));
		//grado de pertenencia del elemento a la media 2
		pertenencia_media2= (1/valor_media2)/((1/valor_media1)+(1/valor_media2));
		
		if(pertenencia_media1>pertenencia_media2)
			return "El punto pertenece a la media 1";
		else
			return "El punto pertenene a la media 2";
	}
	
	
	public void entrenarAlgoritmo() {
			for(int elem=0;elem<pertenencia[0].length;elem++)
			{
				//Pertenencia del punto elem a las medias
				pertenenciaPunto(elem);
			}
	}
	private void pertenenciaPunto(int elem) {
		Double valor_media1=0.0;
		Double valor_media2=0.0;
		Double pertenencia_media1=0.0;
		Double pertenencia_media2=0.0;
		for (int i = 0; i < medias[0].length; i++) {
			valor_media1 += Math.pow((puntos[elem][i] - medias[0][i]), 2);
		}
		for (int i = 0; i < medias[1].length; i++) {
			valor_media2 += Math.pow((puntos[elem][i] - medias[1][i]), 2);
		}
		//grado de pertenencia del elemento a la media 1
		pertenencia_media1= (1/valor_media1)/((1/valor_media1)+(1/valor_media2));
		//grado de pertenencia del elemento a la media 2
		pertenencia_media2= (1/valor_media2)/((1/valor_media1)+(1/valor_media2));
		
		pertenencia[0][elem]=pertenencia_media1;
		pertenencia[1][elem]=pertenencia_media2;
		
	}
	//devuelve boolean diciendo si la media es mas baja que la tolerancia o no
	private Boolean recalcularMedias() {
		Double[][] nuevasMedias=new Double [2][4];
		
		for(int media=0;media<medias.length;media++)
		{
			Double[] numerador = {0.0, 0.0, 0.0, 0.0};
			Double denominador=0.0;
			Double pertcuadrado;
			for(int i=0;i<puntos.length;i++) {
				pertcuadrado = Math.pow(pertenencia[media][i], 2);
				
				for(int n=0;n<puntos[i].length;n++)
				{
					numerador[n] += pertcuadrado*puntos[i][n];
				}
				denominador+=pertcuadrado;
			}
			//terminada la fraccion tocha dividir el numerador por el denominador
			for(int i=0;i<nuevasMedias[0].length;i++)
			{
				nuevasMedias[media][i]=numerador[i]/denominador;
			}
		}
		
		boolean tolerancia = calcularTolerancia(nuevasMedias);
		
		//si no se cumple el grado de tolerancia, se sobreescriben las medias por las nuevas
		if(!tolerancia)
			medias=nuevasMedias;
		
		return tolerancia;
	}

	private boolean calcularTolerancia(Double[][] nuevasMedias) {
		boolean t=false;
		Double [] resultados = new Double [2];
		
		for(int i=0;i<medias.length;i++) {
			Double valor=0.0;
			for(int n=0;n<medias[i].length;n++) {
				valor+= nuevasMedias[i][n] - medias[i][n];
			}
		resultados[i]=Math.abs(valor);	
		}
		
		if(resultados[0]<tolerancia && resultados[1]<tolerancia)
			t=true;
		
		return t;
	}
}
