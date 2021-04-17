package Controlador;

import java.util.List;

import Modelo.AlgoritmoA;
import Modelo.Nodo;
import Presentacion.View;

public class Controller {

	private AlgoritmoA algoritmo;

	public Controller() {
		View vista = new View(this);
	}

	public void init(Integer filas, Integer columnas) {
		algoritmo = new AlgoritmoA(filas, columnas);
	}

	public boolean run(events evento, int i, int j) {
		switch (evento) {

		case GenObstaculo: {
			return algoritmo.meterObstaculos(i, j);

		}

		case GenIni: {
			return algoritmo.crearInicio(i, j);

		}

		case GenFin: {
			return algoritmo.crearFin(i, j);

		}
		case Run: {
			return algoritmo.generarSolucion();

		}
		case Waypoint: {
			algoritmo.crearWaypoint(i, j);
		}

		case GenZonaPeligrosa: {
			return algoritmo.meterZonaPeligrosa(i, j);

		}

		}
		return false;
	}

	public List<Nodo> print() {
		return algoritmo.sol();
	}

}
