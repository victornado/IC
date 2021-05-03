package Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;

import Modelo.ID3;
import Presentacion.View;

public class Controller {

	private ID3 algoritmo;

	public Controller() {
		View vista = new View(this);
	}

	public void init(Integer filas, Integer columnas) {
		try {
			algoritmo = new ID3();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch(IOException io) {
			io.printStackTrace();
		}
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
