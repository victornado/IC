package Modelo;


public class Bayes {
 private float medias[][];
 private float punto[];
 
 
 /*
  * ENTRADA PARA EL ALGORITMO:
  * 	
  * 	En medias primero decir el numero de medias que hay seguido del numero de dimensiones
  * 	float[][] medias = new float[2][3];
		float[] punto = new float [3];
		
		Valores de ejemplo del ejercicio de 2017
		medias[0][0] = 1;
		medias[0][1] = 1;
		medias[0][2] = 2;
		medias[1][0] = 3;
		medias[1][1] = 2;
		medias[1][2] = 3;
		medias[2][0] = 4;
		medias[2][1] = 3;
		medias[2][2] = 1;
		punto [0] = 3;
		punto [1] = 2;
		punto [2] = 2;
		
		Bayes bayes = new Bayes();
		int sol= bayes.ejecutarAlgoritmo(medias,punto);
  * 
  * */
 
 
 
 public Bayes() {
	 
 }
 
 public int ejecutarAlgoritmo(float[][] medias, float[] punto) { 
	int minimoabsoluto=9999999, minimoactual, pertenece=0;
	//recorre cada media
	for(int i=0;i<medias.length;i++) {
		//dentro de cada media hace la resta
		minimoactual=algoritmoBayes(medias[i], punto);
		if(minimoactual<minimoabsoluto) {
			minimoabsoluto=minimoactual;
			pertenece=i;
		}
	}
	System.out.print("El numero pertenece a la clase " + pertenece);
	return pertenece;
 }
 
//le resta al punto la media y calcula la distacia devolviendo el int
 private int algoritmoBayes(float[] media,float[] punto) {
	 int solucion=0;
	 for(int i=0;i<media.length;i++)
	 {
		solucion += Math.pow((punto[i]-media[i]),2);
	 }
	 return solucion;
 }
 
}
