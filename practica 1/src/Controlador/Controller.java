package Controlador;

import Modelo.AlgoritmoA;
import Presentacion.View;

public class Controller {

	private AlgoritmoA algoritmo;
	private View vista;

	public Controller() {
		vista = new View(this);
	}

	public void init(Integer filas, Integer columnas) {
		algoritmo = new AlgoritmoA(filas, columnas);
	}

	public void run(events evento, int i, int j) {
		switch (evento) {
		
		case GenObstaculo: {
			algoritmo.meterObstaculos(i, j);
			break;
		}
		
		case GenIni: {
			algoritmo.crearInicio(i, j);
			break;
		}
		
		case GenFin: {
			algoritmo.crearFin(i, j);
			break;
		}
		case Run:{
			algoritmo.generarSolucion();
			break;
		}
		}
	}

}
