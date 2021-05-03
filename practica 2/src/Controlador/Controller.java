package Controlador;

import Modelo.ID3;
import Presentacion.View;

public class Controller {

	private ID3 algoritmo;

	public Controller() {
		View vista = new View(this);
	}

	public void init(Integer filas, Integer columnas) {
		algoritmo = new ID3();
	}

	public boolean run(events evento, int i, int j) {
		switch (evento) {

		case GenObstaculo: {
			

		}

		case GenIni: {
			
		}

		case GenFin: {
			
		}
		case Run: {
			

		}
		case Waypoint: {
			
		}

		case GenZonaPeligrosa: {
			

		}

		}
		return false;
	}

}
