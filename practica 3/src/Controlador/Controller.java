package Controlador;

import Presentacion.GUIPrincipal;

public class Controller {

	public Controller() {
		GUIPrincipal vista = new GUIPrincipal(this);
		vista.setVisible(true);
	}


	public boolean run(events evento, int i, int j) {
		switch (evento) {

		

		}
		return false;
	}

}
