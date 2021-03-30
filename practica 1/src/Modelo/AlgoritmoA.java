package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AlgoritmoA {

	public static final int costediagonal = 15;
	public static final int costerecto = 10;
	private Nodo[][] tablero;
	private Set<Nodo> listaAbierta;
	private HashMap<Integer, Nodo> listaCerrada;
	private Pair ini;
	private Pair fin;

	public AlgoritmoA(int N, int M) {
		this.tablero = new Nodo[N][M];
		this.listaAbierta = new TreeSet<Nodo>();
		this.listaCerrada = new HashMap<Integer, Nodo>();
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
			listaCerrada.put(tablero[i][j].getId(), tablero[i][j]);
			return true;
		} else {
			return false;
		}
	}

	private void generarIds() {
		int id = 1;
		for (int i = 0; i < tablero.length; ++i) {
			for (int j = 0; j < tablero[i].length; ++j) {
				tablero[i][j] = new Nodo();
				tablero[i][j].setId(id);
				tablero[i][j].setI(i);
				tablero[i][j].setJ(j);
				id++;
			}
		}
	}

	public void generarSolucion() {

		if (ini.equals(fin)) {
			listaCerrada.put(tablero[ini.getI()][ini.getJ()].getId(), tablero[ini.getI()][ini.getJ()]);

		} else {
			calcularCoste(ini.getI(), ini.getJ(), fin.getI(), fin.getJ(), 0);
			listaAbierta.add(tablero[ini.getI()][ini.getJ()]); // metemos el nodo inicial a la lista abierta para
																// empezar a calcular
			boolean encontrado = false;
			while (!encontrado && !listaAbierta.isEmpty()) {
				if (listaAbierta.iterator().hasNext()) {
					Nodo aux = listaAbierta.iterator().next();
					listaAbierta.remove(aux);
					listaCerrada.put(aux.getId(), aux);
					if (aux == tablero[fin.getI()][fin.getJ()])
						encontrado = true;
					else {
						calcularAdyacentes(aux);
					}
				}

			}
			if (listaAbierta.isEmpty())
				encontrado = true;
		}
	}
	public void sol() {
	Nodo aux=tablero[fin.getI()][fin.getJ()];
	while(aux!=tablero[ini.getI()][ini.getJ()]) {
		System.out.println(aux);
		aux=aux.getAnterior();
	}
	}

	private boolean comprobarNodo(int i, int j) {
		if (i < tablero.length && i >= 0) {
			if (j < tablero[i].length && j >= 0)
				return true;
		}
		return false;
	}

	private void calcularCoste(int i, int j, int n, int m, int costeAcumulado) {
		int f, g, h;
		// No estamos en diagonal
		if (i - n == 0 || j - m == 0) {
			g = costerecto;
		} else {
			g = costediagonal;
		}
		h = Math.abs(i - fin.getI()) + Math.abs(j - fin.getJ());
		f = g + h + costeAcumulado;
		if (f < tablero[i][j].getCoste()) {
			tablero[i][j].setAnterior(tablero[n][m]);
			tablero[i][j].setCoste(f);
			}
	}

	private void calcularAdyacentes(Nodo nodo) {
		int n = nodo.getI();
		int m = nodo.getJ();
		for (int i = n - 1; i <= n + 1; i++) {
			for (int j = m - 1; j <= m + 1; j++) {
				if (comprobarNodo(i, j) && !tablero[i][j].isObstaculo()
						&& !listaCerrada.containsKey(tablero[i][j].getId())) {
					calcularCoste(i, j, n, m, nodo.getCoste());
					
					listaAbierta.add(tablero[i][j]);
				}
			}
		}
	}

}
