package Controlador;

import Modelo.AlgoritmoA;
import Presentacion.View;

public class Controller {


	private AlgoritmoA algoritmo;

	public Controller() {
		View vista = new View();
	}


	public void start(Integer filas, Integer columnas) {
		algoritmo = new AlgoritmoA(filas,columnas);
	}

}
