package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AlgoritmoA {

	public static final int costediagonal = 15;
	public static final int costerecto = 10;
	private Nodo[][] tablero;
	private Set<Nodo> listaAbierta;
	private List<Nodo> listaCerrada;
	private Pair ini;
	private Pair fin;

	public AlgoritmoA(int N, int M) {
		this.tablero = new Nodo[N][M];
		this.listaAbierta = new TreeSet<Nodo>();
		this.listaCerrada = new ArrayList<Nodo>();
		this.ini = null;
		this.fin = null;
		generarIds();
	}

	public boolean crearInicio(int i, int j) {
		if (comprobarNodo(i, j)) {
			ini = new Pair(i, j);
			return true;
		}
		return false;
	}

	public boolean crearFin(int i, int j) {
		if (comprobarNodo(i, j)) {
			fin = new Pair(i, j);
			return true;
		}
		return false;
	}

	public boolean meterObstaculos(int i, int j) {
		if (comprobarNodo(i, j)) {
			tablero[i][j].setObstaculo();
			listaCerrada.add(tablero[i][j]);
			return true;
		} else {
			return false;
		}
	}

	private void generarIds() {
		int id = 1;
		for (int i = 0; i < tablero.length; ++i) {
			for (int j = 0; j < tablero[i].length; ++j) {
				tablero[i][j].setId(id);
				id++;
			}
		}
	}

	public void generarSolucion() {

		if (ini.equals(fin)) {
			listaCerrada.add(tablero[ini.getI()][ini.getJ()]);

		} else {

		}

	}

	public boolean comprobarNodo(int i, int j) {
		if (i < tablero.length && i >= 0) {
			if (j < tablero[i].length && j >= 0)
				return true;
		}
		return false;
	}

	public void calcularCoste(int i, int j, int n, int m) {
		int f, g, h;
		// No estamos en diagonal
		if (i - n == 0 || j - m == 0) {
			g = costerecto;
		} else {
			g = costediagonal;
		}
		h = Math.abs(i - fin.getI()) + Math.abs(j - fin.getJ());
		f = g + h;
		tablero[i][j].setCoste(f);
	}

	public void calcularAdyacentes(int n, int m) {
		for (int i = n - 1; i <= n + 1; i++) {
			for (int j = m - 1; j <= m + 1; j++) {
				if (comprobarNodo(i,j) && tablero[i][j].isObstaculo() && i!=n && j!=m && //comprobar que no este en la lista cerrada ) {
					calcularCoste(i,j,n,m);
					listaAbierta.add(tablero[i][j]);
				}
			}
		}
	}

}
